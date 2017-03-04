package com.heqing.samplesFramework.hibernate.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heqing.samplesFramework.hibernate.base.BaseServiceImpl;
import com.heqing.samplesFramework.hibernate.bean.Classes;
import com.heqing.samplesFramework.hibernate.dao.ClassesDao;
import com.heqing.samplesFramework.hibernate.service.ClassesService;

@Service
@Transactional
public class ClassesServiceImpl  extends BaseServiceImpl<Classes> implements ClassesService{
	
	@Resource
	private ClassesDao classesDao;
	
}
