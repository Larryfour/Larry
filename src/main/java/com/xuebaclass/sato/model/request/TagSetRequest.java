package com.xuebaclass.sato.model.request;

import java.util.List;

/**
 * Created by sunhao on 2017-08-18.
 */
public class TagSetRequest {
    private List<String> setTagIds;
    private List<String> cancelTagIds;

    public List<String> getSetTagIds() {
        return setTagIds;
    }

    public void setSetTagIds(List<String> setTagIds) {
        this.setTagIds = setTagIds;
    }

    public List<String> getCancelTagIds() {
        return cancelTagIds;
    }

    public void setCancelTagIds(List<String> cancelTagIds) {
        this.cancelTagIds = cancelTagIds;
    }
}
