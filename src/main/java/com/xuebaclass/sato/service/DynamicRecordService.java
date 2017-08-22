package com.xuebaclass.sato.service;

import com.xuebaclass.sato.model.DynamicRecord;

import java.util.List;

/**
 * Created by sunhao on 2017-08-11.
 */
public interface DynamicRecordService {
    void create(DynamicRecord record) throws Exception;

    List<DynamicRecord> getRecordByCustomerId(String customerId) throws Exception;

}
