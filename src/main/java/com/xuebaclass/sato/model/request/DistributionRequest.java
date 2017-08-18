package com.xuebaclass.sato.model.request;

import java.util.List;

/**
 * Created by kangfei on 2017-08-18.
 */
public class DistributionRequest {
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
