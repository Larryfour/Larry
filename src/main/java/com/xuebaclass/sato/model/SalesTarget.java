package com.xuebaclass.sato.model;

/**
 * Created by sunhao on 2017-08-22.
 */
public class SalesTarget {
    private Integer id;
    private String salesId;
    private Integer groupId;
    private Integer targetAmount;
    private Integer targetOrders;
    private Integer targetMonth;

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

    public Integer getTargetMonth() {
        return targetMonth;
    }

    public void setTargetMonth(Integer targetMonth) {
        this.targetMonth = targetMonth;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
