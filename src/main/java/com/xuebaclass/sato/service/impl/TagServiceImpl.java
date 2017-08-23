package com.xuebaclass.sato.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.xuebaclass.sato.mapper.crm.TagGroupMapper;
import com.xuebaclass.sato.mapper.crm.TagMapper;
import com.xuebaclass.sato.model.Tag;
import com.xuebaclass.sato.model.TagGroup;
import com.xuebaclass.sato.model.response.ManagementTagsResponse;
import com.xuebaclass.sato.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Transactional
@Service
public class TagServiceImpl implements TagService {
    private static final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);

    @Autowired
    private TagGroupMapper tagGroupMapper;

    @Autowired
    private TagMapper tagMapper;

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
}
