package com.xuebaclass.sato.model;

/**
 * Created by sunhao on 2017-08-17.
 */
public class Tag extends AbstractPersistable {
    private String name;
    private String comment;
    private Integer tagGroupId;
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

    public Integer getTagGroupId() {
        return tagGroupId;
    }

    public void setTagGroupId(Integer tagGroupId) {
        this.tagGroupId = tagGroupId;
    }
}
