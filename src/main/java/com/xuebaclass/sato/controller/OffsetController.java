package com.xuebaclass.sato.controller;

import com.xuebaclass.sato.model.Offset;
import com.xuebaclass.sato.service.OffsetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
