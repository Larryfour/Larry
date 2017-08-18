package com.xuebaclass.sato.service.impl;

import com.xuebaclass.sato.mapper.crm.DynamicRecordMapper;
import com.xuebaclass.sato.model.DynamicRecord;
import com.xuebaclass.sato.service.DynamicRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DynamicRecordServiceImpl implements DynamicRecordService {
	private static final Logger logger = LoggerFactory.getLogger(DynamicRecordServiceImpl.class);

	@Autowired
	private DynamicRecordMapper dynamicRecordMapper;

	@Override
	public void create(DynamicRecord record) {
		dynamicRecordMapper.create(record);
	}

	@Override
	public List<DynamicRecord> getRecordByCustomerId(String customerId){
		return dynamicRecordMapper.getRecordByCustomerId(customerId);
	}

}
