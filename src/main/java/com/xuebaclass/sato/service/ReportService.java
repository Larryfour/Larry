package com.xuebaclass.sato.service;

import com.xuebaclass.sato.model.request.SalesDailyMyselfRequest;
import com.xuebaclass.sato.model.request.SalesDailyRequest;
import com.xuebaclass.sato.model.request.SevenDayCallRateRequest;
import com.xuebaclass.sato.model.response.SalesDailyMyselfResponse;
import com.xuebaclass.sato.model.response.SalesDailyResponse;
import com.xuebaclass.sato.model.response.SevenDayCallRateResponse;

/**
 * Created by sunhao on 2017-10-13.
 */
public interface ReportService {
    SalesDailyResponse salesDaily(SalesDailyRequest request) throws Exception;

    SalesDailyMyselfResponse salesMyselfDaily(SalesDailyMyselfRequest request) throws Exception;

    SevenDayCallRateResponse sevenDayCallRate(SevenDayCallRateRequest request) throws Exception;
}
