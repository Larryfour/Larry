package com.xuebaclass.sato.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.xuebaclass.sato.exception.CrmException;
import com.xuebaclass.sato.mapper.crm.CustomerMapper;
import com.xuebaclass.sato.mapper.crm.DynamicRecordMapper;
import com.xuebaclass.sato.mapper.sato.SalesMapper;
import com.xuebaclass.sato.model.Customer;
import com.xuebaclass.sato.model.DynamicRecord;
import com.xuebaclass.sato.model.Sales;
import com.xuebaclass.sato.service.DynamicRecordService;
import com.xuebaclass.sato.utils.CurrentUser;
import com.xuebaclass.sato.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.nonNull;

@Transactional
@Service
public class DynamicRecordServiceImpl implements DynamicRecordService {

    private static final Logger logger = LoggerFactory.getLogger(DynamicRecordServiceImpl.class);

    @Autowired
    private DynamicRecordMapper dynamicRecordMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private SalesMapper salesMapper;

    /**
     * 创建动态日志
     *
     * @param record
     * @return
     */
    @Override
    public DynamicRecord create(DynamicRecord record) throws Exception {
        logger.info("customer id:[" + record.getCustomerId() + "]");

        try {
            Customer customer = customerMapper.getById(record.getCustomerId());
            if (customer == null) {
                throw CrmException.newException("客户不存在！");
            }

            Sales sales = salesMapper.getSalesByUserName(CurrentUser.getInstance().getCurrentAuditorName());
            if (sales == null) {
                throw CrmException.newException("添加账户不存在！");
            }

            record.setName(sales.getName());
            record.setUserName(sales.getUserName());
            record.setComment("添加动态记录:" + record.getComment());
            dynamicRecordMapper.create(record);

            record.setCreatedBy(CurrentUser.getInstance().getCurrentAuditor());
            record.setCreatedDate(Utils.getUTC().toInstant());
        } catch (Exception e) {
            throw CrmException.newException(e.getMessage());
        }

        return record;
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

        List<DynamicRecord> records = null;
        try {

            if (StringUtils.isEmpty(customerId)) {
                throw CrmException.newException("客户ID不能为空。");
            }

            Customer customer = customerMapper.getById(customerId);
            if (!nonNull(customer)) {
                throw CrmException.newException("客户不存在！");
            }

            logger.info("customer id:[" + customerId + "]");

            records = dynamicRecordMapper.getRecordByCustomerId(customerId);

        } catch (Exception e) {
            throw CrmException.newException(e.getMessage());
        }

        return records;
    }

}
