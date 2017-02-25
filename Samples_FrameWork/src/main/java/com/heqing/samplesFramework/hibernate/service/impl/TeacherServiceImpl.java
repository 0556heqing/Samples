package com.heqing.samplesFramework.hibernate.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heqing.samplesFramework.hibernate.base.BaseServiceImpl;
import com.heqing.samplesFramework.hibernate.bean.Teacher;
import com.heqing.samplesFramework.hibernate.dao.TeacherDao;
import com.heqing.samplesFramework.hibernate.service.TeacherService;

@Service
@Transactional
public class TeacherServiceImpl  extends BaseServiceImpl<Teacher> implements TeacherService{

	@Resource
	private TeacherDao teacherDao;
	
}
