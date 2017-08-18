package com.xuebaclass.sato.service.impl;

import com.xuebaclass.sato.model.Student;
import com.xuebaclass.sato.mapper.sato.StudentMapper;
import com.xuebaclass.sato.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Service
public class StudentServiceImpl implements StudentService {
	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	@Resource
	private StudentMapper studentMapper;

	@Override
	public Student getStudentByUid(String uid) {
		return studentMapper.getStudentByUid(uid);
	}
}
