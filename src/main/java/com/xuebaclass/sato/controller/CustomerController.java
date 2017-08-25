package com.xuebaclass.sato.controller;

import com.xuebaclass.sato.model.Customer;
import com.xuebaclass.sato.model.request.DistributionRequest;
import com.xuebaclass.sato.model.response.CustomersResponse;
import com.xuebaclass.sato.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sunhao on 2017-08-11.
 */
@RestController
@RequestMapping("customers")
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    /**
     * 创建客户
     *
     * @return
     */
    @PostMapping
    public ResponseEntity create(@RequestBody Customer customer) throws Exception {
        logger.info("################### create customer #######################");

        customerService.create(customer);
        return ResponseEntity.ok(customer);
    }

    /**
     * 更新客户信息
     *
     * @return
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable String id, @RequestBody Customer customer) throws Exception {
        logger.info("################### update customer #######################");

        customerService.update(id, customer);
        return ResponseEntity.ok(customer);
    }

    /**
     * 获取客户列表
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<PagedResources<Resource<CustomersResponse>>>
    getCustomers(@PageableDefault Pageable pageable,
                 PagedResourcesAssembler<CustomersResponse> pagedResourcesAssembler, Customer customer) throws Exception {
        logger.info("################### get page customer #######################");

        Page<CustomersResponse> customers = customerService.getCustomers(pageable, customer);
        return ResponseEntity.ok(pagedResourcesAssembler.toResource(customers));
    }

    /**
     * 根据客户id，获取客户内容
     *
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable String id) throws Exception {
        logger.info("################### get customer info by id #######################");

        return ResponseEntity.ok(customerService.getById(id));
    }

    /**
     * 客户分配
     *
     * @return
     */
    @PostMapping(value = "/distribution")
    public ResponseEntity distribution(@RequestBody DistributionRequest distributionRequest) throws Exception {
        logger.info("################### distribution customers for sales #######################");

        return ResponseEntity.ok(customerService.distribution(distributionRequest));
    }

    /**
     * 获取销售的客户信息
     *
     * @return
     */
    @GetMapping(value = "/myself")
    public ResponseEntity<PagedResources<Resource<CustomersResponse>>>
    getMyselfCustomers(@PageableDefault Pageable pageable,
                       PagedResourcesAssembler<CustomersResponse> pagedResourcesAssembler, Customer customer) throws Exception {
        logger.info("################### get customers for myself  #######################");

        Page<CustomersResponse> customers = customerService.getMyselfCustomers(pageable, customer);
        return ResponseEntity.ok(pagedResourcesAssembler.toResource(customers));
    }

}
