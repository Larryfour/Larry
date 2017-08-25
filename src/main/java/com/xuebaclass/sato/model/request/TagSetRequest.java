package com.xuebaclass.sato.model.request;

import java.util.List;

/**
 * Created by sunhao on 2017-08-18.
 */
public class TagSetRequest {
    public static class InnerTag{
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private List<InnerTag> setTags;
    private List<InnerTag> cancelTags;

    public List<InnerTag> getSetTags() {
        return setTags;
    }

    public void setSetTags(List<InnerTag> setTags) {
        this.setTags = setTags;
    }

    public List<InnerTag> getCancelTags() {
        return cancelTags;
    }

    public void setCancelTags(List<InnerTag> cancelTags) {
        this.cancelTags = cancelTags;
    }
}
