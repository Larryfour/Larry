package com.xuebaclass.sato.controller;

import com.xuebaclass.sato.model.request.SalesDailyMyselfRequest;
import com.xuebaclass.sato.model.request.SalesDailyRequest;
import com.xuebaclass.sato.model.request.SevenDayCallRateRequest;
import com.xuebaclass.sato.service.OffsetService;
import com.xuebaclass.sato.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sunhao on 2017-10-13.
 */
@RestController
@RequestMapping("report")
public class ReportController {
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    ReportService reportService;

    @GetMapping(value = "/sales/daily/myself")
    public ResponseEntity salesDailyMyself(SalesDailyMyselfRequest request) throws Exception {
        logger.info("################### sales daily myself report #######################");

        return ResponseEntity.ok(reportService.salesMyselfDaily(request));
    }

    @GetMapping(value = "/sales/daily")
    public ResponseEntity salesDaily(SalesDailyRequest request) throws Exception {
        logger.info("################### sales daily report #######################");

        return ResponseEntity.ok(reportService.salesDaily(request));
    }

    @GetMapping(value = "/sales/daily/seven-day/call-rate")
    public ResponseEntity salesDaily(SevenDayCallRateRequest request) throws Exception {
        logger.info("################### sales daily seven-day call rate #######################");

        return ResponseEntity.ok(reportService.sevenDayCallRate(request));
    }
}
