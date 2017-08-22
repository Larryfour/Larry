package com.xuebaclass.sato.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.util.StringUtil;
import com.xuebaclass.sato.exception.CrmException;
import com.xuebaclass.sato.mapper.crm.CustomerMapper;
import com.xuebaclass.sato.model.Customer;
import com.xuebaclass.sato.model.Student;
import com.xuebaclass.sato.model.request.StartProcessInstanceRequest;
import com.xuebaclass.sato.service.BpmnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Transactional
@Service
public class BpmnServiceImpl implements BpmnService {
    private static final Logger logger = LoggerFactory.getLogger(BpmnServiceImpl.class);

    @Value("${sato.http-base-url}")
    String satoUrl;

    @Value("${sato.bpmn-base-url}")
    String bpmnUrl;

    @Value("${sato.process-definition-key:experiences-course-process}")
    String processDefinitionKey;

    @Autowired
    RestTemplate restTemplate;


    @Autowired
    private CustomerMapper customerMapper;


    @Override
    public Customer bookExperienceCourse(String customerId) throws Exception {

        logger.info("update customer id[" + customerId + "]");

        Customer customer = customerMapper.getById(customerId);
        if (customer == null) {
            throw CrmException.newException("客户不存在!");
        }

        if (customer.getXuebaNo() == null) {
            throw CrmException.newException("学吧号不能为空!");
        }


        Student student = new Student();

        // 创建学生
        String url = satoUrl + "students";
        try {
            Student resp = restTemplate.postForObject(url, student, Student.class);
        } catch (HttpClientErrorException e) {
            logger.info("Book Experience Course [" + e.getResponseBodyAsString() + "]");
        } catch (Exception e) {
            logger.info("Book Experience Course:Add Student:", e);
            throw CrmException.newException("添加学生失败.");
        }

        // 启动流程
        String pid = bpmnStartProcessInstance(student);
        if (StringUtil.isEmpty(pid)) {
            throw CrmException.newException("can not start bpmn process instance. xuebaNo: " + customer.getXuebaNo());
        } else {
            student.setProcessInstanceId(pid);
        }

        return customer;
    }

    private String bpmnStartProcessInstance(Student s) {
        String url = bpmnUrl + "runtime/process-instances";

        StartProcessInstanceRequest req = new StartProcessInstanceRequest();
        req.setProcessDefinitionKey(processDefinitionKey);
        req.setBusinessKey(s.getUid());
        req.setVariables(new ArrayList<>());

        req.getVariables().add(textVariable("student", s.getUid()));
        req.getVariables().add(textVariable("studentName", s.getName()));
        req.getVariables().add(textVariable("studentMobile", s.getMobile()));
        req.getVariables().add(textVariable("parentMobile", s.getParentMobile()));
        req.getVariables().add(textVariable("parent", s.getRelation()));
        req.getVariables().add(textVariable("call_period", s.getCallperiod()));
        req.getVariables().add(textVariable("title", s.getTitle()));
        req.getVariables().add(textVariable("attend_training", s.getAttendtraining()));

        try {
            JsonNode resp = restTemplate.postForEntity(url, req, JsonNode.class).getBody();
            return resp.has("id") ? resp.get("id").asText() : "";
        } catch (HttpClientErrorException e) {
            logger.info("start bpmn error:[" + e.getResponseBodyAsString() + "]");
        }
        return "";
    }

    private Map<String, String> textVariable(String name, String value) {
        Map<String, String> var = new HashMap<>();
        var.put("name", name);
        var.put("value", value);
        return var;
    }

}