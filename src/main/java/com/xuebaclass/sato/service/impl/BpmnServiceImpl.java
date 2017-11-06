package com.xuebaclass.sato.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.util.StringUtil;
import com.xuebaclass.sato.common.Grade;
import com.xuebaclass.sato.exception.CrmException;
import com.xuebaclass.sato.mapper.crm.CustomerMapper;
import com.xuebaclass.sato.mapper.sato.StudentMapper;
import com.xuebaclass.sato.model.Customer;
import com.xuebaclass.sato.model.Student;
import com.xuebaclass.sato.model.request.StartProcessInstanceRequest;
import com.xuebaclass.sato.model.request.TaskActionsRequest;
import com.xuebaclass.sato.service.BpmnService;
import com.xuebaclass.sato.utils.CurrentUser;
import com.xuebaclass.sato.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@Transactional
@Service
public class BpmnServiceImpl implements BpmnService {
    private static final Logger logger = LoggerFactory.getLogger(BpmnServiceImpl.class);

    @Value("${sato.http-base-url}")
    private String satoUrl;

    @Value("${sato.bpmn-base-url}")
    private String bpmnUrl;

    @Value("${sato.sales-leads-base-url}")
    private String salesLeadsUrl;

    @Value("${sato.process-definition-key:experiences-course-process}")
    private String processDefinitionKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Customer bookExperienceCourse(String customerId) throws Exception {

        logger.info("book customer id[" + customerId + "]");

        Customer customer = customerMapper.getById(customerId);
        if (!nonNull(customer)) {
            throw CrmException.newException("客户不存在!");
        }

        if (!nonNull(customer.getXuebaNo())) {
            throw CrmException.newException("学吧号不能为空!");
        }

        if (StringUtils.isEmpty(customer.getName())) {
            throw CrmException.newException("客户姓名不能为空!");
        }

        if (StringUtils.isEmpty(customer.getMobile())) {
            throw CrmException.newException("客户电话不能为空!");
        }

        if (StringUtils.isEmpty(customer.getParentsMobile())) {
            throw CrmException.newException("父母电话不能为空!");
        }

        // 创建学生
        try {
            String uid = Utils.xuebaNo2Uid(customer.getXuebaNo().toString());

            Student existStudent = studentMapper.getStudentByUid(uid);
            if (!nonNull(existStudent)) {

                Student student = new Student();

                student.setUid(uid);
                student.setName(customer.getName());
                student.setMobile(customer.getMobile());
                student.setQq(customer.getQq());
                student.setGender(customer.getGender());
                student.setProvince(customer.getProvince());
                student.setCity(customer.getCity());
                student.setSchool(customer.getSchool());
                student.setRelation("父亲".equals(customer.getParents()) ? "man" : "woman");
                student.setParentName(customer.getParents());
                student.setParentMobile(customer.getParentsMobile());
                student.setAnswerTime(customer.getAnswerInterval());
                student.setNimAccountId(getNimAccountId(uid));

                Map extensions = new HashMap();
                if (!StringUtils.isEmpty(customer.getLearningProcess())) {
                    extensions.put("学习进度", customer.getLearningProcess());
                }
                if (nonNull(customer.getScores())) {
                    extensions.put("成绩", customer.getScores().toString());
                }
                if (!StringUtils.isEmpty(customer.getTeachingAterial())) {
                    extensions.put("使用教材", customer.getTeachingAterial());
                }
                if (!StringUtils.isEmpty(customer.getGrade())) {
                    extensions.put("年级", customer.getGrade());
                    extensions.put(Grade.NCEETIME_F_NAME, Grade.getNCEETimeFromGradeName(customer.getGrade()));
                }
                if (nonNull(customer.getFullMarks())) {
                    extensions.put("满分", customer.getFullMarks().toString());
                }
                if (nonNull(customer.getTutorialFlag())) {
                    extensions.put("是否补习", customer.getTutorialFlag() == false ? "否" : "是");
                }
                if (!StringUtils.isEmpty((customer.getNextTest()))) {
                    extensions.put("下次大考名称", customer.getNextTest());
                }
                if (nonNull(customer.getNextTestDate())) {
                    extensions.put("下次大考时间", Utils.parseDate(customer.getNextTestDate()));
                }
                student.setExtensions(extensions);

                studentMapper.create(student);

                if (nonNull(student.getExtensions()) && !student.getExtensions().isEmpty()) {
                    student.getExtensions().forEach((k, v) -> studentMapper.createStudentExtension(student.getId(), k, v));
                }
            }

        } catch (Exception e) {
            logger.info("Book Experience Course:Add Student:", e);
            throw CrmException.newException("同步添加学生失败.");
        }

        // 启动流程
        String pid = null;
        try {
            pid = bpmnStartProcessInstance(customer);
            if (StringUtil.isEmpty(pid)) {
                logger.info("Book Experience Course:start process instance:xuebaNo: " + customer.getXuebaNo());
                throw CrmException.newException("启动流程失败！");
            }
        } catch (Exception e) {
            logger.info("Book Experience Course:start process instance:xuebaNo: " + customer.getXuebaNo());
            throw CrmException.newException("启动流程失败！");
        }

        //获取任务id
        String taskId = null;
        try {
            taskId = getTaskId(pid);
        } catch (Exception e) {
            throw CrmException.newException("获取任务失败！");
        }

        //分配任务
        try {
            taskActions(taskId);
        } catch (Exception e) {
            throw CrmException.newException("分配任务失败！");
        }

        return customer;
    }

    private String bpmnStartProcessInstance(Customer customer) throws Exception {
        String url = bpmnUrl + "runtime/process-instances";
        String uid = Utils.xuebaNo2Uid(customer.getXuebaNo().toString());

        StartProcessInstanceRequest req = new StartProcessInstanceRequest();
        req.setProcessDefinitionKey(processDefinitionKey);
        req.setBusinessKey(uid);
        req.setVariables(new ArrayList<>());

        req.getVariables().add(textVariable("student", uid));
        req.getVariables().add(textVariable("studentName", customer.getName()));
        req.getVariables().add(textVariable("studentMobile", customer.getMobile()));
        req.getVariables().add(textVariable("parentMobile", customer.getParentsMobile()));
        if (!StringUtils.isEmpty(customer.getParents())) {
            req.getVariables().add(textVariable("parent", "父亲".equals(customer.getParents()) ? "man" : "woman"));
        }
        req.getVariables().add(textVariable("call_period", customer.getAnswerInterval()));
        req.getVariables().add(textVariable("title", customer.getTeachingAterialNote()));
        if (nonNull(customer.getTutorialFlag())) {
            req.getVariables().add(textVariable("attend_training", customer.getTutorialFlag() == false ? "否" : "是"));
        }

        try {
            JsonNode resp = restTemplate.postForEntity(url, req, JsonNode.class).getBody();
            return resp.has("id") ? resp.get("id").asText() : "";
        } catch (HttpClientErrorException e) {
            logger.info("start bpmn error:[" + e.getResponseBodyAsString() + "]");
            throw new CrmException(e.getResponseBodyAsString());
        }
    }

    private String getTaskId(String pid) throws Exception {
        String url = bpmnUrl + "runtime/tasks?processInstanceId=" + pid;

        try {
            JsonNode resp = restTemplate.getForEntity(url, JsonNode.class).getBody();
            return resp.get("data").get(0).get("id").asText();
        } catch (HttpClientErrorException e) {
            logger.info("get task id error:[" + e.getResponseBodyAsString() + "]");
            throw new CrmException(e.getResponseBodyAsString());
        }
    }

    private void taskActions(String taskId) throws Exception {
        String url = bpmnUrl + "runtime/tasks/" + taskId;

        TaskActionsRequest request = new TaskActionsRequest();
        request.setAction("claim");
        request.setAssignee(CurrentUser.getInstance().getCurrentAuditorName());

        try {
            restTemplate.postForEntity(url, request, JsonNode.class).getBody();
        } catch (HttpClientErrorException e) {
            logger.info("task actions error:[" + e.getResponseBodyAsString() + "]");
            throw new CrmException(e.getResponseBodyAsString());
        }
    }

    private String getNimAccountId(String uid) {
        String url = salesLeadsUrl + "im/student/" + uid;

        try {
            JsonNode resp = restTemplate.getForEntity(url, JsonNode.class).getBody();

            return resp.get("accid").textValue();
        } catch (HttpClientErrorException e) {
            logger.info("get nim account error:[" + e.getResponseBodyAsString() + "]");
            throw new CrmException(e.getResponseBodyAsString());
        }

    }

    private Map<String, String> textVariable(String name, String value) {
        Map<String, String> var = new HashMap<>();
        var.put("name", name);
        var.put("value", value);
        return var;
    }
}