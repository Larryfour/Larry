package com.xuebaclass.sato.service.impl;

import com.xuebaclass.sato.mapper.crm.TagGroupMapper;
import com.xuebaclass.sato.mapper.crm.TagMapper;
import com.xuebaclass.sato.model.Tag;
import com.xuebaclass.sato.model.TagGroup;
import com.xuebaclass.sato.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
	public List<Tag> getTags() {
		return tagMapper.getTags();
	}

	@Override
	public void createTagGroup(TagGroup tagGroup) {
		tagGroupMapper.create(tagGroup);
	}
}
