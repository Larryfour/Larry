package com.xuebaclass.sato.model.response;

import java.util.List;

/**
 * Created by sunhao on 2017-08-18.
 */
public class DistributionResponse {
    private Integer ownedSalesID;
    private String ownedSalesName;
    private String ownedSalesUserName;
    private List<String> customerIds;

    public Integer getOwnedSalesID() {
        return ownedSalesID;
    }

    public void setOwnedSalesID(Integer ownedSalesID) {
        this.ownedSalesID = ownedSalesID;
    }

    public String getOwnedSalesName() {
        return ownedSalesName;
    }

    public void setOwnedSalesName(String ownedSalesName) {
        this.ownedSalesName = ownedSalesName;
    }

    public String getOwnedSalesUserName() {
        return ownedSalesUserName;
    }

    public void setOwnedSalesUserName(String ownedSalesUserName) {
        this.ownedSalesUserName = ownedSalesUserName;
    }

    public List<String> getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(List<String> customerIds) {
        this.customerIds = customerIds;
    }
}
