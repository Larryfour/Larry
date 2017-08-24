package com.xuebaclass.sato.model;

/**
 * Created by sunhao on 2017-08-17.
 */
public class CustomerTag extends AbstractPersistable {
    private String customerId;
    private String tagId;
    private Boolean flag = false;

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

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
