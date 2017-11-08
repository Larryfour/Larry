package com.xuebaclass.sato.model.response;

import com.xuebaclass.sato.model.PayingCustomer;

/**
 * Created by sunhao on 2017-08-18.
 */
public class PayingCustomersResponse extends PayingCustomer {
    private String tagNames;

    public String getTagNames() {
        return tagNames;
    }

    public void setTagNames(String tagNames) {
        this.tagNames = tagNames;
    }
}
