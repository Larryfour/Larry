package com.xuebaclass.sato.service;

import com.xuebaclass.sato.model.Customer;
import com.xuebaclass.sato.model.request.DistributionRequest;
import com.xuebaclass.sato.model.response.DistributionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by sunhao on 2017-08-11.
 */
public interface CustomerService {
    void create(Customer customer) throws Exception;

    void update(String id, Customer customer) throws Exception;

    Customer getById(String id) throws Exception;

    Customer getByContactMobile(String contactMobile) throws Exception;

    Page<Customer> getCustomers(Pageable pageable, Customer customer) throws Exception;

    Page<Customer> getMyselfCustomers(Pageable pageable, Customer customer) throws Exception;

    DistributionResponse distribution(DistributionRequest request) throws Exception;

}
