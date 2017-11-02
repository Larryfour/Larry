package com.xuebaclass.sato.model;

import java.util.Date;

/**
 * Created by sunhao on 2017-11-02.
 */
public class Offset {
    Integer id;
    Integer salesId;
    Integer offsetBefore;
    Integer offsetAfter;
    Integer rewards;
    Integer offset;
    Date offsetDate;
    Boolean status;

    public Offset() {
        this.offsetBefore = 0;
        this.offsetAfter = 0;
        this.rewards = 0;
        this.status = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
    }

    public Integer getOffsetBefore() {
        return offsetBefore;
    }

    public void setOffsetBefore(Integer offsetBefore) {
        this.offsetBefore = offsetBefore;
    }

    public Integer getOffsetAfter() {
        return offsetAfter;
    }

    public void setOffsetAfter(Integer offsetAfter) {
        this.offsetAfter = offsetAfter;
    }

    public Integer getRewards() {
        return rewards;
    }

    public void setRewards(Integer rewards) {
        this.rewards = rewards;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Date getOffsetDate() {
        return offsetDate;
    }

    public void setOffsetDate(Date offsetDate) {
        this.offsetDate = offsetDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
