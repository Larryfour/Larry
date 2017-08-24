package com.xuebaclass.sato.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.xuebaclass.sato.exception.CrmException;
import com.xuebaclass.sato.mapper.crm.CustomerMapper;
import com.xuebaclass.sato.mapper.crm.CustomerTagMapper;
import com.xuebaclass.sato.mapper.crm.TagGroupMapper;
import com.xuebaclass.sato.mapper.crm.TagMapper;
import com.xuebaclass.sato.model.Customer;
import com.xuebaclass.sato.model.CustomerTag;
import com.xuebaclass.sato.model.Tag;
import com.xuebaclass.sato.model.TagGroup;
import com.xuebaclass.sato.model.request.TagSetRequest;
import com.xuebaclass.sato.model.response.CustomerTagResponse;
import com.xuebaclass.sato.model.response.ManagementTagsResponse;
import com.xuebaclass.sato.model.response.TagSetResponse;
import com.xuebaclass.sato.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public void createTag(Tag tag) throws Exception {
        try {
            tagMapper.create(tag);
        } catch (Exception e) {
            throw new CrmException(e.getMessage());
        }
    }

    @Override
    public List<Tag> getAllTags() throws Exception {
        List<Tag> tags = null;
        try {
            tags = tagMapper.getAllTags();
        } catch (Exception e) {
            throw new CrmException(e.getMessage());
        }
        return tags;
    }

    @Override
    public void createTagGroup(TagGroup tagGroup) throws Exception {
        try {
            tagGroupMapper.create(tagGroup);
        } catch (Exception e) {
            throw new CrmException(e.getMessage());
        }
    }

    @Override
    public List<ManagementTagsResponse> getManagementTags() throws Exception {
        List<ManagementTagsResponse> responses = new ArrayList<>();

        try {
            List<Tag> tags = tagMapper.getAllTags();

            Map<Integer, List<Tag>> map = tags.stream().collect(Collectors.groupingBy(Tag::getTagGroupId));

            List<TagGroup> groups = tagGroupMapper.getAllTagGroups();

            groups.forEach(tagGroup -> {
                ManagementTagsResponse response = new ManagementTagsResponse();
                response.setTagGroupId(Integer.valueOf(tagGroup.getId()));
                response.setTagGroupName(tagGroup.getName());
                response.setTags(map.get(StringUtils.stringToInteger(tagGroup.getId())));
                responses.add(response);
            });
        } catch (Exception e) {
            throw new CrmException(e.getMessage());
        }

        return responses;
    }

    @Override
    public TagSetResponse customerTagSetting(String customerId, TagSetRequest tagSetRequest) throws Exception {
        TagSetResponse response = new TagSetResponse();

        try {
            Customer customer = customerMapper.getById(customerId);
            if (customer == null) {
                throw CrmException.newException("客户不存在!");
            }

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

    @Override
    public List<CustomerTagResponse> getCustomerTags(String customerId) throws Exception {
        List<CustomerTagResponse> customerTags = null;

        try {

            Customer customer = customerMapper.getById(customerId);
            if (customer == null) {
                throw CrmException.newException("客户不存在!");
            }

            customerTags = customerTagMapper.getCustomerTags(customerId);
        } catch (Exception e) {
            throw new CrmException(e.getMessage());
        }

        return customerTags;
    }
}
