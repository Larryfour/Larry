package com.xuebaclass.sato.model.response;

import com.xuebaclass.sato.model.request.TagSetRequest;

import java.util.List;

/**
 * Created by sunhao on 2017-08-18.
 */
public class TagSetResponse {
    private String customerId;
    private List<TagSetRequest.InnerTag> setTags;
    private List<TagSetRequest.InnerTag> cancelTags;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<TagSetRequest.InnerTag> getSetTags() {
        return setTags;
    }

    public void setSetTags(List<TagSetRequest.InnerTag> setTags) {
        this.setTags = setTags;
    }

    public List<TagSetRequest.InnerTag> getCancelTags() {
        return cancelTags;
    }

    public void setCancelTags(List<TagSetRequest.InnerTag> cancelTags) {
        this.cancelTags = cancelTags;
    }
}
