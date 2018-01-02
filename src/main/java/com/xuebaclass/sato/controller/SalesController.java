package com.xuebaclass.sato.controller;

import com.xuebaclass.sato.model.request.SalesTargetRequest;
import com.xuebaclass.sato.service.SalesTargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sunhao on 2017-08-11.
 */
@RestController
@RequestMapping("sales")
public class SalesController {
    @Autowired
    private SalesTargetService salesTargetService;

    /**
     * 根据月份获取月目标
     *
     * @return
     */
    @GetMapping(value = "/{targetMonth}/target")
    public ResponseEntity getSalesMonthTarget(@PathVariable(value = "targetMonth") String targetMonth) {
        return ResponseEntity.ok(salesTargetService.getSalesMonthTarget(targetMonth));
    }

    /**
     * 根据月份获取月目标
     *
     * @return
     */
    @PostMapping(value = "/target")
    public ResponseEntity setSalesMonthTarget(@RequestBody SalesTargetRequest request) throws Exception {
        salesTargetService.setSalesMonthTarget(request);
        return ResponseEntity.ok(salesTargetService.getSalesMonthTarget(request.getTargetMonth()));
    }
}
