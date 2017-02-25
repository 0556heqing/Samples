package com.heqing.samplesFramework.mybatis.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.heqing.samplesFramework.mybatis.base.BaseServiceImpl;
import com.heqing.samplesFramework.mybatis.bean.Classes;
import com.heqing.samplesFramework.mybatis.dao.ClassesDao;
import com.heqing.samplesFramework.mybatis.service.ClassesService;

@Service
public class ClassesServiceImpl extends BaseServiceImpl<Classes>  implements ClassesService {

	@Resource
	private ClassesDao classesDao;

}
