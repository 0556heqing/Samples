package com.heqing.samplesFramework.quartz.task;

import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.heqing.samplesFramework.quartz.bean.TimingJob;

public class TimingTaskUtil {
	
	/**
     * 启动定时
     * @param timingJob 定时工作实体类
     */
	public static void cereteTask(TimingJob timingJob) {
		if(timingJob.isNormal() && !timingJob.getClassName().equals("") && !timingJob.getMethodName().equals("") &&
				!timingJob.getName().equals("") && !timingJob.getGroup().equals("") && !timingJob.getRules().equals("")) {
			
			//通过schedulerFactory获取一个调度器
			SchedulerFactory schedulerfactory=new StdSchedulerFactory();
			Scheduler scheduler=null;
			try{
				//通过schedulerFactory获取一个调度器
				scheduler=schedulerfactory.getScheduler(); 
				//创建jobDetail实例，绑定Job实现类。 指明job的名称，所在组的名称，以及绑定job类
				Class c = Class.forName(timingJob.getClassName());
				JobDetail job = JobBuilder.newJob(c).withIdentity(timingJob.getName(), timingJob.getGroup()).build();
				job.getJobDataMap().put("methodName", timingJob.getMethodName()); 
				job.getJobDataMap().put("content", timingJob.getContent()); 
				//定义调度触发规则
				Trigger trigger=TriggerBuilder.newTrigger().withIdentity(timingJob.getName(), timingJob.getGroup())
			    	.withSchedule(CronScheduleBuilder.cronSchedule(timingJob.getRules()))
			    	.startNow().build(); 
				//把作业和触发器注册到任务调度中
				scheduler.scheduleJob(job, trigger);
				//启动调度
			    scheduler.start(); 
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
     * 启动定时
     * @param timingJob 多个定时工作实体类
     */
	public static void cereteTasks(List<TimingJob> timingJobs) {
		for(TimingJob timingJob : timingJobs){
			cereteTask(timingJob);
		}
	}
	
}
