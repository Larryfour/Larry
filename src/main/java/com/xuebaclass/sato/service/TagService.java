package com.xuebaclass.sato.service;

import com.xuebaclass.sato.model.Tag;
import com.xuebaclass.sato.model.TagGroup;

import java.util.List;

/**
 * Created by sunhao on 2017-08-11.
 */
public interface TagService {
    void createTag(Tag tag);

    void createTagGroup(TagGroup tagGroup);

    List<Tag> getTags();
}
