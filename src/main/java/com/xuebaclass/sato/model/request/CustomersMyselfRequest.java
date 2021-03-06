package com.xuebaclass.sato.model.request;

import java.util.List;

/**
 * Created by sunhao on 2017-09-04.
 */
public class CustomersMyselfRequest {
    private String name;
    private String mobile;
    private String grade;
    private String xuebaNo;
    private String from;
    private String to;
    private String source;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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
}
