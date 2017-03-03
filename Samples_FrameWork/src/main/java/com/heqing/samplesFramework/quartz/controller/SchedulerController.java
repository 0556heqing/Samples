package com.heqing.samplesFramework.quartz.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heqing.samplesFramework.quartz.bean.MyJobDetail;
import com.heqing.samplesFramework.quartz.bean.MyScheduler;
import com.heqing.samplesFramework.quartz.bean.MyTrigger;
import com.heqing.samplesFramework.quartz.service.MySchedulerService;
import com.heqing.samplesFramework.quartz.util.QuartzUtil;

@Controller
@RequestMapping("/Scheduler")
public class SchedulerController {

	private Log log = LogFactory.getLog(getClass());

	@Resource	
	private MySchedulerService mySchedulerService;
	
	@RequestMapping("/index")
	public String index(Map<String,Object> map) {
		return "scheduler";
	} 
	
	@RequestMapping("/saveScheduler")  
	@ResponseBody
    public void saveScheduler(MyScheduler myScheduler) {   
		mySchedulerService.save(myScheduler);
    }
	
	@RequestMapping("/deleteScheduler")  
	@ResponseBody
	public void deleteScheduler(MyScheduler myScheduler) {
		mySchedulerService.delete(myScheduler.getId());
	}

	@RequestMapping("/updateScheduler")  
	@ResponseBody
	public void updateScheduler(MyScheduler myScheduler) {
		mySchedulerService.update(myScheduler);
	}
	
	@RequestMapping("/getSchedulerById")  
	@ResponseBody
	public void getSchedulerById(MyScheduler myScheduler) {
		mySchedulerService.getById(myScheduler.getId());
	}

	@RequestMapping("/getSchedulerList")  
	@ResponseBody
	public void getSchedulerList(MyScheduler myScheduler, int pageNum, int pageSize) {
		mySchedulerService.getPageBean(myScheduler, pageNum, pageSize);
	}
	
	@RequestMapping("/getSchedulerListByJobDetail")  
	@ResponseBody
	public void getSchedulerListByJobDetail(MyJobDetail myJobDetail, boolean onOrOff, int pageNum, int pageSize) {
		mySchedulerService.getSchedulerListByJobDetail(myJobDetail.getId(), onOrOff, pageNum, pageSize);
	}
	
	@RequestMapping("/getSchedulerListByTrigger")  
	@ResponseBody
	public void getSchedulerListByTrigger(MyTrigger myTrigger, boolean onOrOff, int pageNum, int pageSize) {
		mySchedulerService.getSchedulerListByTrigger(myTrigger.getId(), onOrOff, pageNum, pageSize);
	}
	
	@RequestMapping("/deleteSchedulerByJobDetail")  
	@ResponseBody
	public void deleteSchedulerByJobDetail(MyJobDetail myJobDetail) {
		mySchedulerService.deleteSchedulerByJobDetail(myJobDetail.getId());
	}
	
	@RequestMapping("/deleteSchedulerByTrigger")  
	@ResponseBody
	public void deleteSchedulerByTrigger(MyTrigger myTrigger) {
		mySchedulerService.deleteSchedulerByTrigger(myTrigger.getId());
	}
	
	@RequestMapping("/pause")  
	@ResponseBody
    public void pauseScheduler(MyScheduler myScheduler) {  
		try {
			QuartzUtil.pauseScheduler(myScheduler.getName(), myScheduler.getGroup());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/resume")  
	@ResponseBody
    public void resumeScheduler(MyScheduler myScheduler) {  
		try {
			QuartzUtil.resumeScheduler(myScheduler.getName(), myScheduler.getGroup());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/trigger")  
	@ResponseBody
    public void triggerScheduler(MyScheduler myScheduler) {  
		try {
			QuartzUtil.triggerScheduler(myScheduler.getName(), myScheduler.getGroup());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/reschedule")  
	@ResponseBody
    public void rescheduleScheduler(MyScheduler myScheduler) {  
		try {
			QuartzUtil.rescheduleScheduler(myScheduler);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
