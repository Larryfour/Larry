package com.xuebaclass.sato.service;

import com.xuebaclass.sato.model.Customer;
import com.xuebaclass.sato.model.PayingCustomer;
import com.xuebaclass.sato.model.request.CustomersMyselfRequest;
import com.xuebaclass.sato.model.request.CustomersRequest;
import com.xuebaclass.sato.model.request.DistributionRequest;
import com.xuebaclass.sato.model.request.PayingCustomersRequest;
import com.xuebaclass.sato.model.response.CustomersResponse;
import com.xuebaclass.sato.model.response.DistributionResponse;
import com.xuebaclass.sato.model.response.PayingCustomersResponse;
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

    Page<CustomersResponse> getCustomers(Pageable pageable, CustomersRequest request) throws Exception;

    Page<CustomersResponse> getMyselfCustomers(Pageable pageable, CustomersMyselfRequest request) throws Exception;

    Page<PayingCustomersResponse> getPayingCustomers(Pageable pageable, PayingCustomersRequest request) throws Exception;

    Page<PayingCustomersResponse> getMyselfPayingCustomers(Pageable pageable, PayingCustomersRequest request) throws Exception;

    DistributionResponse distribution(DistributionRequest request) throws Exception;

}
