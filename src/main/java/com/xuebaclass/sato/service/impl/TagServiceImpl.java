package com.xuebaclass.sato.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.xuebaclass.sato.exception.CrmException;
import com.xuebaclass.sato.mapper.crm.*;
import com.xuebaclass.sato.mapper.sato.SalesMapper;
import com.xuebaclass.sato.model.*;
import com.xuebaclass.sato.model.request.TagSetRequest;
import com.xuebaclass.sato.model.response.CustomerTagResponse;
import com.xuebaclass.sato.model.response.ManagementTagsResponse;
import com.xuebaclass.sato.model.response.TagSetResponse;
import com.xuebaclass.sato.service.TagService;
import com.xuebaclass.sato.utils.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

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

    @Autowired
    private DynamicRecordMapper dynamicRecordMapper;

    @Autowired
    private SalesMapper salesMapper;

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
            String cancelNames = null;
            String setNames = null;

            Customer customer = customerMapper.getById(customerId);
            if (customer == null) {
                throw CrmException.newException("客户不存在!");
            }

            List<TagSetRequest.InnerTag> cancelTags = tagSetRequest.getCancelTags();
            if (!cancelTags.isEmpty()) {
                customerTagMapper.cancel(customerId, cancelTags);
                List<String> cancelTagNames = cancelTags.stream().map(TagSetRequest.InnerTag::getName).collect(Collectors.toList());
                cancelNames = org.thymeleaf.util.StringUtils.join(cancelTagNames.toArray(), "、");

            }

            List<TagSetRequest.InnerTag> setTags = tagSetRequest.getSetTags();

            if (!setTags.isEmpty()) {
                CustomerTag customerTag = new CustomerTag();
                customerTag.setCustomerId(customerId);
                setTags.forEach(t -> {
                    customerTag.setTagId(t.getId());
                    customerTagMapper.create(customerTag);
                });
                List<String> setTagNames = setTags.stream().map(TagSetRequest.InnerTag::getName).collect(Collectors.toList());
                setNames = org.thymeleaf.util.StringUtils.join(setTagNames.toArray(), "、");
            }

            String userName = CurrentUser.getInstance().getCurrentAuditorName();
            Sales sales = salesMapper.getSalesByUserName(userName);
            if (!nonNull(sales)) {
                throw CrmException.newException("修改账户不存在！");
            }

            StringBuffer recordComment = new StringBuffer();
            if (!StringUtils.isEmpty(setNames)) {
                recordComment.append("添加标签:");
                recordComment.append(setNames);
            }

            if (!StringUtils.isEmpty(recordComment) && !StringUtils.isEmpty(cancelNames)) {
                recordComment.append(",");
            }

            if (!StringUtils.isEmpty(cancelNames)) {
                recordComment.append("取消标签:");
                recordComment.append(cancelNames);
            }

            recordComment.append("。");

            DynamicRecord record = new DynamicRecord();
            record.setCustomerId(customerId);
            record.setComment(recordComment.toString());
            record.setType(DynamicRecord.RecordType.ARTIFICIAL.getCode());
            record.setName(sales.getName());
            record.setUserName(sales.getUserName());

            dynamicRecordMapper.create(record);

            response.setCustomerId(customerId);
            response.setSetTags(tagSetRequest.getSetTags());
            response.setCancelTags(tagSetRequest.getCancelTags());

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
            if (!nonNull(customer)) {
                throw CrmException.newException("客户不存在!");
            }

            customerTags = customerTagMapper.getCustomerTags(customerId);
        } catch (Exception e) {
            throw new CrmException(e.getMessage());
        }

        return customerTags;
    }
}
