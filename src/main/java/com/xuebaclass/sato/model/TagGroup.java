package com.xuebaclass.sato.model;

/**
 * Created by kangfei on 2017-08-17.
 */
public class TagGroup extends AbstractPersistable {
    private String name;
    private String comment;
    private Boolean flag = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
