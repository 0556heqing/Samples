package com.heqing.samplesFramework.quartz.task;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Task implements Job {

	@Override
	public void execute(JobExecutionContext arg){
		// TODO Auto-generated method stub
		boolean normal = true;
		JobDetail job = arg.getJobDetail();
		String methodName = job.getJobDataMap().getString("methodName");
		if(!methodName.equals("")) {
			if(methodName.equals("test")) test(job);
			else normal = false;
		} else {
			normal = false;
		}
		if(!normal) {
			try {
				arg.getScheduler().shutdown();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void test(JobDetail job) {
		String content = job.getJobDataMap().getString("content");
		JSONObject object = (JSONObject) JSON.parse(content);
		System.out.println("content = " + object.toJSONString());
	}
}
