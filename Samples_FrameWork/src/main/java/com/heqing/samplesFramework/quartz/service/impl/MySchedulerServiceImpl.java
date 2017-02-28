package com.heqing.samplesFramework.quartz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.quartz.SchedulerException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.heqing.samplesFramework.quartz.base.BaseServiceImpl;
import com.heqing.samplesFramework.quartz.bean.MyScheduler;
import com.heqing.samplesFramework.quartz.dao.MySchedulerDao;
import com.heqing.samplesFramework.quartz.service.MySchedulerService;
import com.heqing.samplesFramework.quartz.util.QuartzUtil;

@Service
public class MySchedulerServiceImpl extends BaseServiceImpl<MyScheduler> implements MySchedulerService {

	@Resource
	private MySchedulerDao mySchedulerDao;
	
	@CacheEvict(value = "data", allEntries = true) 
	public boolean save(MyScheduler myScheduler) {
		if(myScheduler.getMyJobDetail()==null || myScheduler.getMyTrigger()==null) return false;
		mySchedulerDao.save(myScheduler);
		try {
			QuartzUtil.saveScheduler(myScheduler);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@CachePut(value = "data")    
	public boolean update(MyScheduler myScheduler) {
		if(myScheduler.getMyJobDetail()==null || myScheduler.getMyTrigger()==null) return false;
		try {
			MyScheduler oldMyScheduler = mySchedulerDao.getById(myScheduler.getId());
			if(oldMyScheduler != null) {
				QuartzUtil.deleteScheduler(oldMyScheduler.getName(), oldMyScheduler.getGroup());
				QuartzUtil.saveScheduler(myScheduler);
				
				mySchedulerDao.update(myScheduler);
			} else return false;
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@CacheEvict(value = "data", allEntries = true)    
	public boolean delete(Long id) {
		MyScheduler myScheduler = mySchedulerDao.getById(id);
		if(myScheduler == null) return false;
		try {
			mySchedulerDao.delete(id);
			QuartzUtil.deleteScheduler(myScheduler.getName(), myScheduler.getGroup());
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Cacheable(value = "data") 
	public List<MyScheduler> getSchedulerListByJobDetail(long myJobDetailId,
			boolean onOrOff, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return mySchedulerDao.getSchedulerListByJobDetail(myJobDetailId, onOrOff?1:0, pageNum, pageSize);
	}

	@Cacheable(value = "data") 
	public List<MyScheduler> getSchedulerListByTrigger(long myTriggerId,
			boolean onOrOff, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return mySchedulerDao.getSchedulerListByTrigger(myTriggerId, onOrOff?1:0, pageNum, pageSize);
	}

	@CacheEvict(value = "data", allEntries = true)   
	public void deleteSchedulerByJobDetail(long myJobDetailId) {
		// TODO Auto-generated method stub
		mySchedulerDao.deleteSchedulerByJobDetail(myJobDetailId);
	}

	@CacheEvict(value = "data", allEntries = true)   
	public void deleteSchedulerByTrigger(long myTriggerId) {
		// TODO Auto-generated method stub
		mySchedulerDao.deleteSchedulerByTrigger(myTriggerId);
	}
	
}
