package com.xuebaclass.sato.service;

import com.xuebaclass.sato.model.Sales;
import com.xuebaclass.sato.model.request.SalesTargetRequest;
import com.xuebaclass.sato.model.response.SalesTargetResponse;

import java.util.List;

/**
 * Created by sunhao on 2017-08-11.
 */
public interface SalesService {

    List<SalesTargetResponse> getSalesMonthTarget(String targetMonth);

    void setSalesMonthTarget(SalesTargetRequest request) throws Exception;

    List<Sales> getTelephoneSales() throws Exception;
}
