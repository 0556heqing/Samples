package com.heqing.samplesFramework.quartz.service;

import java.util.List;

import com.heqing.samplesFramework.quartz.base.BaseService;
import com.heqing.samplesFramework.quartz.bean.MyScheduler;

public interface MySchedulerService extends BaseService<MyScheduler> {

	/**
	 * 根据工作细节获取任务列表
	 * @param myJobDetailId 工作细节实体ID
	 * @param onOrOff 		任务开启还是关闭
	 * @param pageNum 	             多少页
	 * @param pageSize 	             一页多少条
	 * @return List<T> 任务实体列表
	 */
	public List<MyScheduler> getSchedulerListByJobDetail(long myJobDetailId, boolean onOrOff, int pageNum, int pageSize);
	
	/**
	 * 根据使用规则获取任务列表
	 * @param myTriggerId 规则ID
	 * @param onOrOff 	      任务开启还是关闭
	 * @param pageNum 	      多少页
	 * @param pageSize 	      一页多少条
	 * @return List<T> 任务实体列表
	 */
	public List<MyScheduler> getSchedulerListByTrigger(long myTriggerId, boolean onOrOff, int pageNum, int pageSize);
	
	/**
	 * 根据使用工作细节删除任务
	 * @param myTriggerId 规则ID
	 */
	public void deleteSchedulerByJobDetail(long myJobDetailId);
	
	/**
	 * 根据使用规则删除任务
	 * @param myTriggerId 规则ID
	 */
	public void deleteSchedulerByTrigger(long myTriggerId);
}
