package com.heqing.samplesFramework.quartz.service.impl;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.heqing.samplesFramework.quartz.base.BaseServiceImpl;
import com.heqing.samplesFramework.quartz.bean.MyJobDetail;
import com.heqing.samplesFramework.quartz.dao.MyJobDetailDao;
import com.heqing.samplesFramework.quartz.dao.MySchedulerDao;
import com.heqing.samplesFramework.quartz.service.MyJobDetailService;

@Service
public class MyJobDetailServiceImpl extends BaseServiceImpl<MyJobDetail> implements MyJobDetailService  {

	@Resource
	private MyJobDetailDao myJobDetailDao;
	@Resource
	private MySchedulerDao mySchedulerDao;

	@CacheEvict(value = "data", allEntries = true)    
	public boolean delete(Long id) {
		myJobDetailDao.delete(id);
		mySchedulerDao.deleteSchedulerByJobDetail(id);
		return true;
	}
}
