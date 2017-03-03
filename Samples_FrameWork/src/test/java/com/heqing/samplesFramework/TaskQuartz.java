package com.heqing.samplesFramework;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.heqing.samplesFramework.quartz.bean.MyJobDetail;
import com.heqing.samplesFramework.quartz.bean.MyScheduler;
import com.heqing.samplesFramework.quartz.bean.MyTrigger;
import com.heqing.samplesFramework.quartz.service.MyJobDetailService;
import com.heqing.samplesFramework.quartz.service.MySchedulerService;
import com.heqing.samplesFramework.quartz.service.MyTriggerService;

@RunWith(SpringJUnit4ClassRunner.class)		// 表示继承了 SpringJUnit4ClassRunner 类
@ContextConfiguration(locations = {"classpath:spring_quartz.xml"})
public class TaskQuartz {

	@Resource	
	private MyJobDetailService myJobDetailService;
	@Resource	
	private MyTriggerService myTriggerService;
	@Resource	
	private MySchedulerService mySchedulerService;
	
//	@Test
	public void create() {
		MyJobDetail myJobDetail = new MyJobDetail();
		myJobDetail.setClassName("com.heqing.samplesFramework.quartz.task.Task");
		myJobDetail.setMethodsName("test");
		myJobDetail.setContent("{name:'heqing',age:27}");
		myJobDetail.setDescribe("this is test");
		myJobDetailService.save(myJobDetail);
		
		MyTrigger myTrigger = new MyTrigger();
		myTrigger.setRule("0/5 * *  * * ? ");
		myTrigger.setCreateUser("heqing");
		myTriggerService.save(myTrigger);

		MyScheduler myScheduler = new MyScheduler();
		myScheduler.setName("name1");
		myScheduler.setGroup("group1");
		myScheduler.setMyJobDetail(myJobDetail);
		myScheduler.setMyTrigger(myTrigger);
		mySchedulerService.save(myScheduler);
	}
	
//	@Test
	public void update() {
		MyJobDetail myJobDetail = myJobDetailService.getById(1488526606251L);
		myJobDetail.setCreateUser("he qing");
		myJobDetailService.update(myJobDetail);
		
		MyTrigger myTrigger = myTriggerService.getById(1488526602369L);
		myTrigger.setCreateUser("he qing");
		myTriggerService.update(myTrigger);
		
		MyScheduler myScheduler = mySchedulerService.getById(1488526606819L);
		myScheduler.setName("name");
		myScheduler.setGroup("group");
		mySchedulerService.update(myScheduler);
	}
	
//	@Test
	public void find() {
		
		System.out.println("------------myJobDetail------------");
		MyJobDetail myJobDetail = myJobDetailService.getById(1488526606251L);
		System.out.println(myJobDetail.getCreateTime());
		
		System.out.println("------------myTrigger------------");
		MyTrigger myTrigger = myTriggerService.getById(1488526602369L);
		System.out.println(myTrigger.getCreateTime());
		
		System.out.println("------------myScheduler------------");
		MyScheduler myScheduler = mySchedulerService.getById(1488526606819L);
		System.out.println(myScheduler.getMyJobDetail().getDescribe());
		System.out.println(myScheduler.getMyTrigger().getCreateUser());
		
		List j = mySchedulerService.getSchedulerListByJobDetail(1488526606251L, false, 0, 1);
		System.out.println(j.size());
		
		List t = mySchedulerService.getSchedulerListByTrigger(1488526602369L, false, 0, 0);
		System.out.println(t.size());
		
		myJobDetail = new MyJobDetail();
		myJobDetail.setClassName("com.heqing.samplesFramework.quartz.task.Task");
		myJobDetail.setMethodsName("test");
		System.out.println("----"+myJobDetailService.findAll(myJobDetail).size());
		
		myTrigger = new MyTrigger();
		myTrigger.setRule("0/5 * *  * * ? ");
		System.out.println("----"+myTriggerService.findAll(myTrigger).size());
	}
	
//	@Test
	public void delete() {
		myJobDetailService.delete(1488256945878L);
		myTriggerService.delete(1488256948855L);
		mySchedulerService.delete(1488251863180L);
	}

}
