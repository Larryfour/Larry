package com.xuebaclass.sato.model.response;

import com.xuebaclass.sato.model.Customer;

/**
 * Created by sunhao on 2017-08-18.
 */
public class CustomersResponse extends Customer {
    private String tagNames;

    public String getTagNames() {
        return tagNames;
    }

    public void setTagNames(String tagNames) {
        this.tagNames = tagNames;
    }
}
