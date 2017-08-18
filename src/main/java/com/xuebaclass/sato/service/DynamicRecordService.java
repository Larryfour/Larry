package com.xuebaclass.sato.service;

import com.xuebaclass.sato.model.DynamicRecord;

import java.util.List;

/**
 * Created by kangfei on 2017-08-11.
 */
public interface DynamicRecordService {
    void create(DynamicRecord record);

    List<DynamicRecord> getRecordByCustomerId(String customerId);

}
