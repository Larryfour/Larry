package com.xuebaclass.sato.controller;

import com.xuebaclass.sato.model.DynamicRecord;
import com.xuebaclass.sato.service.DynamicRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sunhao on 2017-08-11.
 */
@RestController
@RequestMapping("record")
public class DynamicRecordController {
    private static final Logger logger = LoggerFactory.getLogger(DynamicRecordController.class);

    @Autowired
    private DynamicRecordService dynamicRecordService;

    /**
     * 创建动态日志
     *
     * @return
     */
    @PostMapping
    public ResponseEntity create(@RequestBody DynamicRecord dynamicRecord) throws Exception {
        logger.info("################### create dynamic record #######################");

        return ResponseEntity.ok(dynamicRecordService.create(dynamicRecord));
    }

    /**
     * 根据客户id，获取动态日志
     *
     * @return
     */
    @GetMapping(value = "/customer-id/{customerId}")
    public ResponseEntity getRecordByCustomerId(@PathVariable String customerId) throws Exception {
        logger.info("################### get record by customer id #######################");

        List<DynamicRecord> dynamicRecords = dynamicRecordService.getRecordByCustomerId(customerId);
        return ResponseEntity.ok(dynamicRecords);
    }


}
