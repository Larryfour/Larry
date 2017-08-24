package com.xuebaclass.sato.service;

import com.xuebaclass.sato.model.Tag;
import com.xuebaclass.sato.model.TagGroup;
import com.xuebaclass.sato.model.request.TagSetRequest;
import com.xuebaclass.sato.model.response.CustomerTagResponse;
import com.xuebaclass.sato.model.response.ManagementTagsResponse;
import com.xuebaclass.sato.model.response.TagSetResponse;

import java.util.List;

/**
 * Created by sunhao on 2017-08-11.
 */
public interface TagService {
    void createTag(Tag tag) throws Exception;

    void createTagGroup(TagGroup tagGroup) throws Exception;

    List<Tag> getAllTags() throws Exception;

    List<ManagementTagsResponse> getManagementTags() throws Exception;

    TagSetResponse customerTagSetting(String customerId, TagSetRequest tagSetRequest) throws Exception;

    List<CustomerTagResponse> getCustomerTags(String customerId) throws Exception;

}
