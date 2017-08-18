package com.xuebaclass.sato.controller;

import com.xuebaclass.sato.model.Customer;
import com.xuebaclass.sato.model.request.DistributionRequest;
import com.xuebaclass.sato.service.CustomerService;
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
 * Created by kangfei on 2017-08-11.
 */
@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Customer customer) {
        customerService.create(customer);
        return ResponseEntity.ok(customer);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Customer customer) {
        customerService.update(customer);
        return ResponseEntity.ok(customer);
    }

    @RequestMapping(value = "/page-get", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<Resource<Customer>>>
    getCustomers(@PageableDefault Pageable pageable,
                 PagedResourcesAssembler<Customer> pagedResourcesAssembler, Customer customer) {
        Page<Customer> customers = customerService.getCustomers(pageable, customer);
        return ResponseEntity.ok(pagedResourcesAssembler.toResource(customers));
    }

    @RequestMapping(value = "/id/{customerId}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.getById(customerId));
    }


    @RequestMapping(value = "/distribution", method = RequestMethod.POST)
    public ResponseEntity distribution(@RequestBody DistributionRequest distributionRequest) {
        customerService.distribution(distributionRequest);
        return ResponseEntity.ok(distributionRequest);
    }

}
