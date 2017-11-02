package com.xuebaclass.sato.controller;

import com.xuebaclass.sato.model.Customer;
import com.xuebaclass.sato.model.Offset;
import com.xuebaclass.sato.service.OffsetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sunhao on 2017-10-13.
 */
@RestController
@RequestMapping("offset")
public class OffsetController {
    private static final Logger logger = LoggerFactory.getLogger(OffsetController.class);

    @Autowired
    OffsetService offsetService;

    /**
     * 创建抵消时长记录
     *
     * @return
     */
    @PostMapping
    public ResponseEntity create(@RequestBody Offset offset) throws Exception {
        logger.info("################### create offset #######################");

        offsetService.create(offset);
        return ResponseEntity.ok(offset);
    }

    /**
     * 获取指定销售指定日期的offset
     *
     * @return
     */
    @GetMapping(value = "sales/{salesId}/date/{offsetDate}")
    public ResponseEntity getById(@PathVariable Integer salesId, @PathVariable String offsetDate) throws Exception {
        logger.info("################### get offset info by id and date #######################");

        return ResponseEntity.ok(offsetService.getByDate(salesId, offsetDate));
    }
}
