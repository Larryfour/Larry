package com.xuebaclass.sato.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.xuebaclass.sato.exception.CrmException;
import com.xuebaclass.sato.mapper.crm.CustomerMapper;
import com.xuebaclass.sato.mapper.crm.DynamicRecordMapper;
import com.xuebaclass.sato.model.Customer;
import com.xuebaclass.sato.model.DynamicRecord;
import com.xuebaclass.sato.service.DynamicRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DynamicRecordServiceImpl implements DynamicRecordService {

    private static final Logger logger = LoggerFactory.getLogger(DynamicRecordServiceImpl.class);

    @Autowired
    private DynamicRecordMapper dynamicRecordMapper;

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 创建动态日志
     *
     * @param record
     * @return
     */
    @Override
    public void create(DynamicRecord record) throws Exception {
        logger.info("customer id:[" + record.getCustomerId() + "]");

        Customer customer = customerMapper.getById(record.getCustomerId());
        if (customer == null) {
            throw CrmException.newException("客户不存在！");
        }

        try {
            dynamicRecordMapper.create(record);
        } catch (Exception e) {
            throw CrmException.newException(e.getMessage());
        }
        dynamicRecordMapper.create(record);
    }

    /**
     * 查询客户动态日志
     *
     * @param customerId
     * @return
     */
    @Override
    public List<DynamicRecord> getRecordByCustomerId(String customerId) throws Exception {

        logger.info("################### get record by customer id #######################");

        if (StringUtils.isEmpty(customerId)) {
            throw CrmException.newException("客户ID不能为空。");
        }

        Customer customer = customerMapper.getById(customerId);
        if (customer == null) {
            throw CrmException.newException("客户不存在！");
        }

        List<DynamicRecord> records = null;
        try {
            logger.info("customer id:[" + customerId + "]");
            records = dynamicRecordMapper.getRecordByCustomerId(customerId);
        } catch (Exception e) {
            throw CrmException.newException(e.getMessage());
        }
        return records;
    }

}
