package com.xuebaclass.sato.model.response;

/**
 * Created by sunhao on 2017-08-18.
 */
public class CustomerTagResponse {
    private String customerId;
    private String tagId;
    private String tagName;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
