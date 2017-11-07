package com.xuebaclass.sato.model.request;

import java.util.List;

/**
 * Created by sunhao on 2017-09-04.
 */
public class PayingCustomersRequest {
    private String name;
    private String mobile;
    private String grade;
    private String xuebaNo;
    private String parentMobile;
    private String resetHours;
    private List<String> tagIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<String> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<String> tagIds) {
        this.tagIds = tagIds;
    }

    public String getXuebaNo() {
        return xuebaNo;
    }

    public void setXuebaNo(String xuebaNo) {
        this.xuebaNo = xuebaNo;
    }

    public String getParentMobile() {
        return parentMobile;
    }

    public void setParentMobile(String parentMobile) {
        this.parentMobile = parentMobile;
    }

    public String getResetHours() {
        return resetHours;
    }

    public void setResetHours(String resetHours) {
        this.resetHours = resetHours;
    }
}
