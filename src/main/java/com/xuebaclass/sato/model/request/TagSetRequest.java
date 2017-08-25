package com.xuebaclass.sato.model.request;

import com.xuebaclass.sato.model.Tag;

import java.util.List;

/**
 * Created by sunhao on 2017-08-18.
 */
public class TagSetRequest {
    private List<Tag> setTags;
    private List<Tag> cancelTags;

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
