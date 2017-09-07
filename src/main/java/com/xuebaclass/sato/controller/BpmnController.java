package com.xuebaclass.sato.controller;

import com.xuebaclass.sato.model.Customer;
import com.xuebaclass.sato.service.BpmnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sunhao on 2017-08-21.
 */
@RestController
@RequestMapping("bpmn")
public class BpmnController {

    private static final Logger logger = LoggerFactory.getLogger(BpmnController.class);


    @Autowired
    BpmnService bpmnService;


    /**
     * 预约体验课
     *
     * @return
     */
    @PostMapping(value = "/experience/book-course/{customerId}")
    public ResponseEntity bookExperienceCourse(@PathVariable String customerId) throws Exception {
        logger.info("################### book experience course #######################");

        Customer customer = bpmnService.bookExperienceCourse(customerId);

        return ResponseEntity.ok(customer);
    }

}
