package com.xuebaclass.sato.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuebaclass.sato.common.SatoSort;
import com.xuebaclass.sato.config.SpringSecurityKeycloakAutditorAware;
import com.xuebaclass.sato.exception.CrmException;
import com.xuebaclass.sato.mapper.crm.CustomerMapper;
import com.xuebaclass.sato.mapper.crm.DynamicRecordMapper;
import com.xuebaclass.sato.mapper.sato.SalesMapper;
import com.xuebaclass.sato.model.Customer;
import com.xuebaclass.sato.model.DynamicRecord;
import com.xuebaclass.sato.model.Sales;
import com.xuebaclass.sato.model.request.DistributionRequest;
import com.xuebaclass.sato.service.CustomerService;
import com.xuebaclass.sato.utils.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private SalesMapper salesMapper;

    @Autowired
    private DynamicRecordMapper dynamicRecordMapper;

    @Override
    public void create(Customer customer) throws Exception {
        logger.info("contact mobile:[" + customer.getContactMobile() + "]");

        try {
            Customer existCustomer = customerMapper.getByContactMobile(customer.getContactMobile());
            if (existCustomer != null) {
                throw CrmException.newException("客户联络电话已存在!");
            }

            Sales sales = null;

            DynamicRecord record = new DynamicRecord();
            record.setType("1");
            record.setComment("创建客户。");
            record.setCustomerId(customer.getId());

            if (customer.getOwnedSalesID() == null
                    && StringUtils.isEmpty(customer.getOwnedSalesUserName())
                    && StringUtils.isEmpty(customer.getOwnedSalesUserName())) {

                String userName = CurrentUser.getInstance().getCurrentAuditorName();
                sales = salesMapper.getSalesByUserName(userName);
                if (sales == null) {
                    throw CrmException.newException("创建销售不存在！");
                }

                customer.setOwnedSalesID(Integer.valueOf(sales.getId()));
                customer.setOwnedSalesName(sales.getName());
                customer.setOwnedSalesUserName(sales.getUserName());

                record.setName(sales.getName());
                record.setUserName(sales.getUserName());
            } else {
                record.setName(customer.getOwnedSalesName());
                record.setUserName(customer.getOwnedSalesUserName());
            }

            customerMapper.create(customer);


            try {
                dynamicRecordMapper.create(record);
            } catch (Exception e) {
                logger.error("customer create dynamic record error occurred.", e);
            }

        } catch (Exception e) {
            throw CrmException.newException(e.getMessage());
        }
    }

    @Override
    public void update(Customer customer) throws Exception {
        logger.info("update customer id[" + customer.getId() + "]");

        try {
            Customer existCustomer = customerMapper.getById(customer.getId());
            if (existCustomer == null) {
                throw CrmException.newException("客户不存在!");
            }

            existCustomer = customerMapper.checkMobileExist(customer.getMobile(), customer.getId());
            if (existCustomer != null) {
                throw CrmException.newException("学生电话已存在!");
            }

            customerMapper.update(customer);

            Sales sales = salesMapper.getSalesByUserName(CurrentUser.getInstance().getCurrentAuditorName());
            if (sales == null) {
                throw CrmException.newException("分配账户不存在！");
            }


            try {
                DynamicRecord record = new DynamicRecord();
                record.setType("2");
                record.setComment("更新客户信息。");
                record.setCustomerId(customer.getId());
                record.setName(sales.getName());
                record.setUserName(sales.getUserName());
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
    public Page<Customer> getCustomers(Pageable pageable, Customer customer) throws Exception {
        PageInfo<Customer> pageInfo = null;
        try {
            PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
            String sort = SatoSort.getSort(pageable, "CREATED_DATE");
            pageInfo = new PageInfo<>(customerMapper.getCustomers(sort, customer));
        } catch (Exception e) {
            throw CrmException.newException(e.getMessage());
        }
        return new PageImpl<Customer>(pageInfo.getList(), pageable, pageInfo.getTotal());
    }

    @Override
    public Page<Customer> getMyselfCustomers(Pageable pageable, Customer customer) throws Exception {
        PageInfo<Customer> pageInfo = null;
        try {
            PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
            String sort = SatoSort.getSort(pageable, "CREATED_DATE");
            pageInfo = new PageInfo<>(customerMapper.getMyselfCustomers(sort, customer));
        } catch (Exception e) {
            throw CrmException.newException(e.getMessage());
        }
        return new PageImpl<Customer>(pageInfo.getList(), pageable, pageInfo.getTotal());
    }

    @Override
    public void distribution(DistributionRequest request) throws Exception {
        try {

            Sales sales = salesMapper.getSalesByUserName(CurrentUser.getInstance().getCurrentAuditorName());
            if (sales == null) {
                throw CrmException.newException("分配账户不存在！");
            }

            customerMapper.distribution(request);

            dynamicRecordDistributionCreate(sales, request);

        } catch (Exception e) {
            throw CrmException.newException(e.getMessage());
        }
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
}
