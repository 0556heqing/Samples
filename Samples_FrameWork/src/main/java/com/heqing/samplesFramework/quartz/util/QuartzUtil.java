package com.heqing.samplesFramework.quartz.util;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.heqing.samplesFramework.quartz.bean.MyScheduler;

public class QuartzUtil {
	
	public static SchedulerFactory schedulerfactory=new StdSchedulerFactory();
	
	//开启定时
	public static void saveScheduler(MyScheduler myScheduler) throws SchedulerException, ClassNotFoundException {
		Scheduler scheduler = schedulerfactory.getScheduler(); 
		
		//创建jobDetail实例，绑定Job实现类。 指明job的名称，所在组的名称，以及绑定job类
		Class c = Class.forName(myScheduler.getMyJobDetail().getClassName());
		JobDetail jobDetail = JobBuilder.newJob(c).withIdentity(myScheduler.getName(), myScheduler.getGroup()).build();
		jobDetail.getJobDataMap().put("methodName", myScheduler.getMyJobDetail().getMethodsName()); 
		jobDetail.getJobDataMap().put("content", myScheduler.getMyJobDetail().getContent()); 
		jobDetail.getJobDataMap().put("describe", myScheduler.getMyJobDetail().getDescribe()); 
		jobDetail.getJobDataMap().put("createUser", myScheduler.getMyJobDetail().getCreateTime()); 
		jobDetail.getJobDataMap().put("createTime", myScheduler.getMyJobDetail().getCreateTime()); 
		
		//定义调度触发规则
		Trigger trigger=TriggerBuilder.newTrigger().withIdentity(myScheduler.getName(), myScheduler.getGroup())
	    	.withSchedule(CronScheduleBuilder.cronSchedule(myScheduler.getMyTrigger().getRule()))
	    	.startNow().build(); 
		
		//把作业和触发器注册到任务调度中
		scheduler.scheduleJob(jobDetail, trigger);
		//启动调度
	    if(myScheduler.isOnOrOff()) scheduler.start();
	}
	
	//暂定定时
	public static void pauseScheduler(String name, String group) throws SchedulerException {
		Scheduler scheduler = schedulerfactory.getScheduler(); 
		JobKey jobKey = JobKey.jobKey(name, group);
		scheduler.pauseJob(jobKey);
	}
	
	//恢复任务
	public static void resumeScheduler(String name, String group) throws SchedulerException {
		Scheduler scheduler = schedulerfactory.getScheduler();
		JobKey jobKey = JobKey.jobKey(name, group);
		scheduler.resumeJob(jobKey);
	}
	
	//立即执行任务
	public static void triggerScheduler(String name, String group) throws SchedulerException {
		Scheduler scheduler = schedulerfactory.getScheduler();
		JobKey jobKey = JobKey.jobKey(name, group);
		scheduler.triggerJob(jobKey);
	}
	
	//删除任务
	public static void deleteScheduler(String name, String group) throws SchedulerException {
		Scheduler scheduler = schedulerfactory.getScheduler();
		JobKey jobKey = JobKey.jobKey(name, group);
		scheduler.deleteJob(jobKey);
	}
	
	//更新任务的时间表达式
	public static void rescheduleScheduler(MyScheduler myScheduler) throws SchedulerException {
		Scheduler scheduler = schedulerfactory.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(myScheduler.getName(), myScheduler.getGroup());
		//获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		//表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(myScheduler.getMyTrigger().getRule());
		//按新的cronExpression表达式重新构建trigger
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
		    .withSchedule(scheduleBuilder).build();
		//按新的trigger重新设置job执行
		scheduler.rescheduleJob(triggerKey, trigger);
	}
}
