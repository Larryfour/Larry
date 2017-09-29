package com.xuebaclass.sato.model.response;

import com.xuebaclass.sato.model.Tag;

import java.util.List;

/**
 * Created by sunhao on 2017-08-23.
 */
public class ManagementTagsResponse {
    private Integer tagGroupId;
    private String tagGroupName;
    private Boolean multiSelect;
    private List<Tag> tags;

    public Integer getTagGroupId() {
        return tagGroupId;
    }

    public void setTagGroupId(Integer tagGroupId) {
        this.tagGroupId = tagGroupId;
    }

    public String getTagGroupName() {
        return tagGroupName;
    }

    public void setTagGroupName(String tagGroupName) {
        this.tagGroupName = tagGroupName;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Boolean getMultiSelect() {
        return multiSelect;
    }

    public void setMultiSelect(Boolean multiSelect) {
        this.multiSelect = multiSelect;
    }
}
