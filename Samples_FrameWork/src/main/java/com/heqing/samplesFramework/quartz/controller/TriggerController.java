package com.heqing.samplesFramework.quartz.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heqing.samplesFramework.quartz.bean.MyTrigger;
import com.heqing.samplesFramework.quartz.service.MyTriggerService;

@Controller
@RequestMapping("/Trigger")
public class TriggerController {

	private Log log = LogFactory.getLog(getClass());
	
	@Resource	
	private MyTriggerService myTriggerService;
	
	@RequestMapping("/saveTrigger")  
	@ResponseBody
	public void saveTrigger(MyTrigger myTrigger) {
		myTriggerService.save(myTrigger);
	}
	
	@RequestMapping("/deleteTrigger")  
	@ResponseBody
	public void deleteTrigger(MyTrigger myTrigger) {
		myTriggerService.delete(myTrigger.getId());
	}

	@RequestMapping("/updateTrigger")  
	@ResponseBody
	public void updateTrigger(MyTrigger myTrigger) {
		myTriggerService.update(myTrigger);
	}
	
	@RequestMapping("/getTriggerById")  
	@ResponseBody
	public void getTriggerById(MyTrigger myTrigger) {
		myTriggerService.getById(myTrigger.getId());
	}

	@RequestMapping("/getTriggerList")  
	@ResponseBody
	public void getTriggerList(MyTrigger myTrigger, int pageNum, int pageSize) {
		myTriggerService.getPageBean(myTrigger, pageNum, pageSize);
	}
}
