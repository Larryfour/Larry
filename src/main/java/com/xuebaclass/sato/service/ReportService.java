package com.xuebaclass.sato.service;

import com.xuebaclass.sato.model.request.SalesDailyMyselfRequest;
import com.xuebaclass.sato.model.response.SalesDailyResponse;

import java.text.ParseException;

/**
 * Created by sunhao on 2017-10-13.
 */
public interface ReportService {
    SalesDailyResponse salesDaily(SalesDailyMyselfRequest request) throws ParseException;
}
