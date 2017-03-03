package com.heqing.samplesFramework.quartz.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heqing.samplesFramework.quartz.bean.MyJobDetail;
import com.heqing.samplesFramework.quartz.service.MyJobDetailService;

@Controller
@RequestMapping("/JobDetail")
public class JobDetailController {

	private Log log = LogFactory.getLog(getClass());
	
	@Resource	
	private MyJobDetailService myJobDetailService;
	
	@RequestMapping("/saveJobDetail")  
	@ResponseBody
	public void saveJobDetail(MyJobDetail myJobDetail) {
		myJobDetailService.save(myJobDetail);
	}
	
	@RequestMapping("/deleteJobDetail")  
	@ResponseBody
	public void deleteJobDetail(MyJobDetail myJobDetail) {
		myJobDetailService.delete(myJobDetail.getId());
	}

	@RequestMapping("/updateJobDetail")  
	@ResponseBody
	public void updateJobDetail(MyJobDetail myJobDetail) {
		myJobDetailService.update(myJobDetail);
	}
	
	@RequestMapping("/getJobDetailById")  
	@ResponseBody
	public void getJobDetailById(MyJobDetail myJobDetail) {
		myJobDetailService.getById(myJobDetail.getId());
	}

	@RequestMapping("/getJobDetailList")  
	@ResponseBody
	public void getJobDetailList(MyJobDetail myJobDetail, int pageNum, int pageSize) {
		myJobDetailService.getPageBean(myJobDetail, pageNum, pageSize);
	}
}
