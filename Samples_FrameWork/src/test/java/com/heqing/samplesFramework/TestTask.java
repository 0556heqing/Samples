package com.heqing.samplesFramework;

import com.heqing.samplesFramework.quartz.bean.TimingJob;
import com.heqing.samplesFramework.quartz.task.TimingTaskUtil;

public class TestTask {

	public static void main(String args[]) {
		TimingJob job = new TimingJob();
		job.setClassName("com.heqing.samplesFramework.quartz.task.Task");
		job.setMethodName("test");
		job.setName("name1");
		job.setGroup("group1");
		job.setContent("{\"name\":\"heqing\",\"age\":27}");
		job.setRules("0/5 * *  * * ? ");
		TimingTaskUtil.cereteTask(job);
	}
}
