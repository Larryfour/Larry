package com.xuebaclass.sato.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.xuebaclass.sato.exception.CrmException;
import com.xuebaclass.sato.mapper.crm.CustomerTagMapper;
import com.xuebaclass.sato.mapper.crm.TagGroupMapper;
import com.xuebaclass.sato.mapper.crm.TagMapper;
import com.xuebaclass.sato.model.CustomerTag;
import com.xuebaclass.sato.model.Tag;
import com.xuebaclass.sato.model.TagGroup;
import com.xuebaclass.sato.model.request.TagSetRequest;
import com.xuebaclass.sato.model.response.ManagementTagsResponse;
import com.xuebaclass.sato.model.response.TagSetResponse;
import com.xuebaclass.sato.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class TagServiceImpl implements TagService {
    private static final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);

    @Autowired
    private TagGroupMapper tagGroupMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private CustomerTagMapper customerTagMapper;

    @Override
    public void createTag(Tag tag) {
        tagMapper.create(tag);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagMapper.getAllTags();
    }

    @Override
    public void createTagGroup(TagGroup tagGroup) {
        tagGroupMapper.create(tagGroup);
    }

    @Override
    public List<ManagementTagsResponse> getManagementTags() {

        List<Tag> tags = tagMapper.getAllTags();

        Map<Integer, List<Tag>> map = tags.stream().collect(Collectors.groupingBy(Tag::getTagGroupId));

        List<TagGroup> groups = tagGroupMapper.getAllTagGroups();

        List<ManagementTagsResponse> responses = new ArrayList<>();
        groups.forEach(tagGroup -> {
            ManagementTagsResponse response = new ManagementTagsResponse();
            response.setTagGroupId(Integer.valueOf(tagGroup.getId()));
            response.setTagGroupName(tagGroup.getName());
            response.setTags(map.get(StringUtils.stringToInteger(tagGroup.getId())));
            responses.add(response);
        });

        return responses;
    }

    @Override
    public TagSetResponse customerTagSetting(String customerId, TagSetRequest tagSetRequest) throws Exception {
        TagSetResponse response = new TagSetResponse();
        try {

            if (!tagSetRequest.getCancelTagIds().isEmpty()) {
                customerTagMapper.cancel(customerId, tagSetRequest.getCancelTagIds());
            }

            List<String> setTagIds = tagSetRequest.getSetTagIds();

            if (!setTagIds.isEmpty()) {
                CustomerTag customerTag = new CustomerTag();
                customerTag.setCustomerId(customerId);
                for (String setTagId : setTagIds) {
                    customerTag.setTagId(setTagId);
                    customerTagMapper.create(customerTag);
                }
            }

            response.setCustomerId(customerId);
            response.setSetTagIds(tagSetRequest.getSetTagIds());
            response.setCancelTagIds(tagSetRequest.getCancelTagIds());

        } catch (Exception e) {
            logger.error("tag setting error occurred.", e);
            throw new CrmException("标签设置失败，请稍后尝试设置。");
        }
        return response;
    }

    public List<CustomerTag> getCustomerTags(String customerId)throws Exception{
        return customerTagMapper.getCustomerTags(customerId);
    }


}
