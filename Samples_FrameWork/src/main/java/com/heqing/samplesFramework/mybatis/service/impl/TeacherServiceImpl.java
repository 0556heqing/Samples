package com.heqing.samplesFramework.mybatis.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.heqing.samplesFramework.mybatis.base.BaseServiceImpl;
import com.heqing.samplesFramework.mybatis.bean.Teacher;
import com.heqing.samplesFramework.mybatis.dao.TeacherDao;
import com.heqing.samplesFramework.mybatis.service.TeacherService;

@Service
public class TeacherServiceImpl extends BaseServiceImpl<Teacher> implements TeacherService{

	@Resource
	private TeacherDao teacherDao;

}
