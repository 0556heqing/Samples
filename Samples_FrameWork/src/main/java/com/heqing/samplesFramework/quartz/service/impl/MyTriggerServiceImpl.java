package com.heqing.samplesFramework.quartz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.heqing.samplesFramework.quartz.base.BaseServiceImpl;
import com.heqing.samplesFramework.quartz.bean.MyScheduler;
import com.heqing.samplesFramework.quartz.bean.MyTrigger;
import com.heqing.samplesFramework.quartz.dao.MySchedulerDao;
import com.heqing.samplesFramework.quartz.dao.MyTriggerDao;
import com.heqing.samplesFramework.quartz.service.MyTriggerService;
import com.heqing.samplesFramework.quartz.util.QuartzUtil;

@Service
public class MyTriggerServiceImpl extends BaseServiceImpl<MyTrigger> implements MyTriggerService {

	@Resource
	private MyTriggerDao myTriggerDao;
	@Resource
	private MySchedulerDao mySchedulerDao;

	@CacheEvict(value = "data", allEntries = true)    
	public boolean delete(Long id) {
		myTriggerDao.delete(id);
		mySchedulerDao.deleteSchedulerByTrigger(id);
		return true;
	}
	
	@CachePut(value = "data")    
	public boolean update(MyTrigger myTrigger) {
		try {
			List<MyScheduler> mySchedulers = mySchedulerDao.getSchedulerListByTrigger(myTrigger.getId(), 1, 0, 0);
			for(MyScheduler myScheduler : mySchedulers) {
				QuartzUtil.rescheduleScheduler(myScheduler);
			}

			myTriggerDao.update(myTrigger);
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
