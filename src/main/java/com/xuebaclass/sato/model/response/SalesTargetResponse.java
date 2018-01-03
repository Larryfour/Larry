package com.xuebaclass.sato.model.response;

/**
 * Created by sunhao on 2017-08-22.
 */
public class SalesTargetResponse {
    private Integer id;
    private String salesId;
    private Integer targetAmount;
    private Integer targetOrders;
    private String targetMonth;
    private String salesName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public Integer getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Integer targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Integer getTargetOrders() {
        return targetOrders;
    }

    public void setTargetOrders(Integer targetOrders) {
        this.targetOrders = targetOrders;
    }

    public String getTargetMonth() {
        return targetMonth;
    }

    public void setTargetMonth(String targetMonth) {
        this.targetMonth = targetMonth;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }
}
