package com.xuebaclass.sato.service;

import com.xuebaclass.sato.model.Customer;
import com.xuebaclass.sato.model.request.DistributionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by kangfei on 2017-08-11.
 */
public interface CustomerService {
    void create(Customer customer);

    void update(Customer customer);

    Customer getById(String id);

    Page<Customer> getCustomers(Pageable pageable, Customer customer) ;

    void distribution(DistributionRequest request);



}
