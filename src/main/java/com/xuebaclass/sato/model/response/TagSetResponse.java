package com.xuebaclass.sato.model.response;

import com.xuebaclass.sato.model.Tag;

import java.util.List;

/**
 * Created by sunhao on 2017-08-18.
 */
public class TagSetResponse {
    private String customerId;
    private List<Tag> setTags;
    private List<Tag> cancelTags;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<Tag> getSetTags() {
        return setTags;
    }

    public void setSetTags(List<Tag> setTags) {
        this.setTags = setTags;
    }

    public List<Tag> getCancelTags() {
        return cancelTags;
    }

    public void setCancelTags(List<Tag> cancelTags) {
        this.cancelTags = cancelTags;
    }
}
