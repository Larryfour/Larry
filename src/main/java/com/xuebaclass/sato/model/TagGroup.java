package com.xuebaclass.sato.model;

/**
 * Created by sunhao on 2017-08-17.
 */
public class TagGroup extends AbstractPersistable {
    private String name;
    private String comment;
    private Boolean flag = false;
    private Boolean multiSelect = false;

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

    public Boolean getMultiSelect() {
        return multiSelect;
    }

    public void setMultiSelect(Boolean multiSelect) {
        this.multiSelect = multiSelect;
    }
}
