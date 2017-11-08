package com.xuebaclass.sato.model;

/**
 * Created by sunhao on 2017-08-17.
 */
public class PayingCustomer extends Customer {
    private Integer totalHours;
    private Integer resetHours;

    public Integer getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }

    public Integer getResetHours() {
        return resetHours;
    }

    public void setResetHours(Integer resetHours) {
        this.resetHours = resetHours;
    }
}
