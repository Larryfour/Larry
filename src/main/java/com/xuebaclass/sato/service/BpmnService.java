package com.xuebaclass.sato.service;

import com.xuebaclass.sato.model.Customer;

/**
 * Created by sunhao on 2017-08-11.
 */
public interface BpmnService {
    Customer bookExperienceCourse(String customerId) throws Exception;
}
