package com.xuebaclass.sato.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuebaclass.sato.common.SatoSort;
import com.xuebaclass.sato.exception.CrmException;
import com.xuebaclass.sato.mapper.crm.CustomerMapper;
import com.xuebaclass.sato.mapper.crm.DynamicRecordMapper;
import com.xuebaclass.sato.mapper.sato.SalesMapper;
import com.xuebaclass.sato.mapper.sato.StudentMapper;
import com.xuebaclass.sato.model.Customer;
import com.xuebaclass.sato.model.DynamicRecord;
import com.xuebaclass.sato.model.Sales;
import com.xuebaclass.sato.model.Student;
import com.xuebaclass.sato.model.request.CustomersMyselfRequest;
import com.xuebaclass.sato.model.request.CustomersRequest;
import com.xuebaclass.sato.model.request.DistributionRequest;
import com.xuebaclass.sato.model.response.CustomersResponse;
import com.xuebaclass.sato.model.response.DistributionResponse;
import com.xuebaclass.sato.service.CustomerService;
import com.xuebaclass.sato.utils.CurrentUser;
import com.xuebaclass.sato.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Value("${sato.sales-leads-base-url}")
    private String salesLeadsUrl;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private SalesMapper salesMapper;

    @Autowired
    private DynamicRecordMapper dynamicRecordMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void create(Customer customer) throws Exception {
        logger.info("contact mobile:[" + customer.getContactMobile() + "]");

        try {

            if (StringUtils.isEmpty(customer.getContactMobile())) {
                throw CrmException.newException("电话号码不能为空!");
            }

            if (!Utils.isMobile(customer.getContactMobile())) {
                throw CrmException.newException("电话号码不符合规范!");
            }

            Customer existCustomer = customerMapper.getByContactMobile(customer.getContactMobile());
            if (!nonNull(existCustomer)) {
                throw CrmException.newException("客户联络电话已存在!");
            }

            DynamicRecord record = new DynamicRecord();

            if (Customer.Source.APP_POPUP.getCode().equals(customer.getSource())) {

                existCustomer = customerMapper.getCustomerByXuebaNo(customer.getXuebaNo());
                if (!nonNull(existCustomer)) {
                    throw CrmException.newException("学吧号已存在!");
                }

                record.setType(DynamicRecord.RecordType.SYSTEM.getCode());
                record.setName("APP弹出导入");
                record.setUserName(Customer.Source.APP_POPUP.toString());
            } else if (Customer.Source.APP_ENTRANCE.getCode().equals(customer.getSource())) {
                //TODO: to develop

            } else if (Customer.Source.WEBSITE.getCode().equals(customer.getSource())) {
                record.setType(DynamicRecord.RecordType.SYSTEM.getCode());
                record.setName("网站导入");
                record.setUserName(Customer.Source.WEBSITE.toString());
            } else if (Customer.Source.EC.getCode().equals(customer.getSource())) {
                //TODO: to develop

            } else if (Customer.Source.BACKEND.getCode().equals(customer.getSource())) {
                String userName = CurrentUser.getInstance().getCurrentAuditorName();
                Sales sales = salesMapper.getSalesByUserName(userName);
                if (sales == null) {
                    throw CrmException.newException("创建销售不存在！");
                }

                customer.setOwnedSalesID(Integer.valueOf(sales.getId()));
                customer.setOwnedSalesName(sales.getName());
                customer.setOwnedSalesUserName(sales.getUserName());

                record.setType(DynamicRecord.RecordType.ARTIFICIAL.getCode());
                record.setName(sales.getName());
                record.setUserName(sales.getUserName());
            }

            customerMapper.create(customer);

            try {
                record.setCustomerId(customer.getId());
                record.setComment("创建客户。");
                dynamicRecordMapper.create(record);
            } catch (Exception e) {
                logger.error("customer create dynamic record error occurred.", e);
            }

        } catch (Exception e) {
            throw CrmException.newException(e.getMessage());
        }
    }

    @Override
    public void update(String id, Customer customer) throws Exception {
        logger.info("update customer id[" + id + "]");

        try {

            if (!StringUtils.isEmpty(customer.getMobile()) && !Utils.isMobile(customer.getMobile())) {
                throw CrmException.newException("学生电话号码不符合规范!");
            }

            if (!StringUtils.isEmpty(customer.getParentsMobile()) && !Utils.isMobile(customer.getParentsMobile())) {
                throw CrmException.newException("家长电话号码不符合规范!");
            }

            Customer existCustomer = customerMapper.getById(customer.getId());
            if (!nonNull(existCustomer)) {
                throw CrmException.newException("客户不存在!");
            }

            existCustomer = customerMapper.checkMobileExist(customer.getMobile(), customer.getId());
            if (!nonNull(existCustomer)) {
                throw CrmException.newException("学生电话已存在!");
            }

            if (nonNull(customer.getXuebaNo())) {
                existCustomer = customerMapper.getCustomerByXuebaNo(customer.getXuebaNo());
                if (existCustomer != null && !existCustomer.getId().equals(id)) {
                    throw CrmException.newException("学吧号已存在!");
                }
            }

            String userName = CurrentUser.getInstance().getCurrentAuditorName();
            Sales sales = salesMapper.getSalesByUserName(userName);
            if (!nonNull(sales)) {
                throw CrmException.newException("修改账户不存在！");
            }

            try {
                customerMapper.update(id, customer);
            } catch (Exception e) {
                logger.error("update customer error occurred.", e);
                throw CrmException.newException("更新客户失败！");
            }

            try {
                if (nonNull(customer.getXuebaNo())) {

                    String uid = Utils.xuebaNo2Uid(String.valueOf(customer.getXuebaNo()));

                    Student student = studentMapper.getStudentByUid(uid);
                    if (nonNull(student)) {
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

                        Map extensions = new HashMap();
                        if (!org.thymeleaf.util.StringUtils.isEmpty(customer.getLearningProcess())) {
                            extensions.put("学习进度", customer.getLearningProcess());
                        }
                        if (nonNull(customer.getScores())) {
                            extensions.put("成绩", customer.getScores().toString());
                        }
                        if (!org.thymeleaf.util.StringUtils.isEmpty(customer.getTeachingAterial())) {
                            extensions.put("使用教材", customer.getTeachingAterial());
                        }
                        if (!org.thymeleaf.util.StringUtils.isEmpty(customer.getGrade())) {
                            extensions.put("年级", customer.getGrade());
                        }
                        if (nonNull(customer.getFullMarks())) {
                            extensions.put("满分", customer.getFullMarks().toString());
                        }
                        if (!org.thymeleaf.util.StringUtils.isEmpty(customer.getNextTest())) {
                            extensions.put("是否补习", customer.getTutorialFlag() == false ? "否" : "是");
                        }
                        if (nonNull(customer.getNextTestDate())) {
                            extensions.put("下次大考名称", customer.getNextTest());
                        }
                        if (nonNull(customer.getNextTestDate())) {
                            extensions.put("下次大考时间", Utils.parseDate(customer.getNextTestDate()));
                        }
                        student.setExtensions(extensions);

                        studentMapper.update(student.getId(), student);
                        if (nonNull(student.getExtensions()) && !student.getExtensions().isEmpty()) {
                            studentMapper.deleteStudentExtension(student.getId());
                            Student finalStudent = student;
                            student.getExtensions().forEach((k, v) -> studentMapper.createStudentExtension(finalStudent.getId(), k, v));
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("update sato student error occurred.", e);
            }

            try {
                DynamicRecord record = new DynamicRecord();

                record.setName(sales.getName());
                record.setUserName(sales.getUserName());
                record.setType(DynamicRecord.RecordType.ARTIFICIAL.getCode());
                record.setComment("更新客户信息。");
                record.setCustomerId(customer.getId());

                dynamicRecordMapper.create(record);
            } catch (Exception e) {
                logger.error("customer update dynamic record error occurred.", e);
            }
        } catch (Exception e) {
            throw CrmException.newException(e.getMessage());
        }
    }

    @Override
    public Customer getById(String id) throws Exception {
        logger.info("customer id:[" + id + "]");
        Customer customer = null;
        try {
            customer = customerMapper.getById(id);
        } catch (Exception e) {
            throw CrmException.newException(e.getMessage());
        }
        return customer;
    }

    @Override
    public Customer getByContactMobile(String contactMobile) throws Exception {
        logger.info("contact mobile:[" + contactMobile + "]");
        Customer customer = null;
        try {
            customerMapper.getByContactMobile(contactMobile);
        } catch (Exception e) {
            throw CrmException.newException(e.getMessage());
        }
        return customer;
    }

    @Override
    public Page<CustomersResponse> getCustomers(Pageable pageable, CustomersRequest request) throws Exception {
        PageInfo<CustomersResponse> pageInfo = null;
        try {
            PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
            String sort = SatoSort.getSort(pageable, "CREATED_DATE");
            pageInfo = new PageInfo<>(customerMapper.getCustomers(sort, request));
        } catch (Exception e) {
            throw CrmException.newException(e.getMessage());
        }
        return new PageImpl<CustomersResponse>(pageInfo.getList(), pageable, pageInfo.getTotal());
    }

    @Override
    public Page<CustomersResponse> getMyselfCustomers(Pageable pageable, CustomersMyselfRequest request) throws Exception {
        PageInfo<CustomersResponse> pageInfo = null;
        try {
            PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
            String sort = SatoSort.getSort(pageable, "CREATED_DATE");
            pageInfo = new PageInfo<CustomersResponse>(customerMapper.getMyselfCustomers(sort, request));
        } catch (Exception e) {
            throw CrmException.newException(e.getMessage());
        }
        return new PageImpl<CustomersResponse>(pageInfo.getList(), pageable, pageInfo.getTotal());
    }

    @Override
    public DistributionResponse distribution(DistributionRequest request) throws Exception {
        DistributionResponse response = new DistributionResponse();
        try {

            Sales sales = salesMapper.getSalesByUserName(CurrentUser.getInstance().getCurrentAuditorName());
            if (sales == null) {
                throw CrmException.newException("分配账户不存在！");
            }

            customerMapper.distribution(request);

            dynamicRecordDistributionCreate(sales, request);

            response.setCustomerIds(request.getCustomerIds());
            response.setOwnedSalesID(request.getOwnedSalesID());
            response.setOwnedSalesName(request.getOwnedSalesName());
            response.setOwnedSalesUserName(request.getOwnedSalesUserName());

        } catch (Exception e) {
            throw CrmException.newException(e.getMessage());
        }
        return response;
    }

    private void dynamicRecordDistributionCreate(Sales sales, DistributionRequest request) throws Exception {
        List<String> ids = request.getCustomerIds();
        for (String id : ids) {
            Customer customer = customerMapper.getById(id);
            if (customer == null) {
                throw CrmException.newException("客户不存在！");
            }

            DynamicRecord record = new DynamicRecord();
            record.setType("2");
            record.setComment("分配客户给【" + request.getOwnedSalesName() + "】。");
            record.setCustomerId(id);
            record.setName(sales.getName());
            record.setUserName(sales.getUserName());

            dynamicRecordMapper.create(record);
        }
    }

    private String getNimAccountId(String uid) {
        String url = salesLeadsUrl + "im/student/" + uid;
//        String url = "http://localhost:9090/sales-leads/im/student/1";
        return "xbim0000088674";

//        try {
//            JsonNode resp = restTemplate.getForEntity(url, JsonNode.class).getBody();
//
//            return resp.get("accid").textValue();
//        } catch (HttpClientErrorException e) {
//            logger.info("get nim account error:[" + e.getResponseBodyAsString() + "]");
//            throw new CrmException(e.getResponseBodyAsString());
//        }

    }
}
