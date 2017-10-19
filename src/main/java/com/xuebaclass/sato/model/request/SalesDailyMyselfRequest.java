package com.xuebaclass.sato.model.request;

/**
 * Created by sunhao on 2017-10-18.
 */
public class SalesDailyMyselfRequest {
    private String salesId;
    private String from;
    private String to;

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
