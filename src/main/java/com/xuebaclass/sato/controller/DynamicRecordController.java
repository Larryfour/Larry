package com.xuebaclass.sato.controller;

import com.xuebaclass.sato.model.DynamicRecord;
import com.xuebaclass.sato.service.DynamicRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kangfei on 2017-08-11.
 */
@RestController
@RequestMapping("record")
public class DynamicRecordController {

    @Autowired
    private DynamicRecordService dynamicRecordService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody DynamicRecord dynamicRecord) {
        dynamicRecordService.create(dynamicRecord);
        return ResponseEntity.ok(dynamicRecord);
    }

    @RequestMapping(value = "/customer-id/{customerId}", method = RequestMethod.GET)
    public ResponseEntity getRecordByCustomerId(@PathVariable String customerId) {
        return ResponseEntity.ok(dynamicRecordService.getRecordByCustomerId(customerId));
    }


}
