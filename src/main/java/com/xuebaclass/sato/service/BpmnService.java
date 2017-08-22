package com.xuebaclass.sato.service;

import com.xuebaclass.sato.model.Customer;
import com.xuebaclass.sato.model.request.DistributionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by sunhao on 2017-08-11.
 */
public interface BpmnService {
    Customer bookExperienceCourse(String customerId) throws Exception;
}
