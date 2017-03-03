package com.heqing.samplesFramework.quartz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.heqing.samplesFramework.quartz.base.BaseDao;
import com.heqing.samplesFramework.quartz.bean.MyScheduler;

public interface MySchedulerDao extends BaseDao<MyScheduler> {
	
	/**
	 * 根据工作细节获取任务列表
	 * @param myJobDetailId 工作细节实体ID
	 * @param onOrOff 		任务开启还是关闭
	 * @param pageNum 	             多少页
	 * @param pageSize 	             一页多少条
	 * @return List<T> 任务实体列表
	 */
	List<MyScheduler> getSchedulerListByJobDetail(@Param(value="myJobDetailId")long myJobDetailId, 
			@Param(value="onOrOff")int onOrOff, @Param(value="pageNum")int pageNum, @Param(value="pageSize")int pageSize);
	
	/**
	 * 根据使用规则获取任务列表
	 * @param myTriggerId 规则ID
	 * @param onOrOff 	      任务开启还是关闭
	 * @param pageNum 	      多少页
	 * @param pageSize 	      一页多少条
	 * @return List<T> 任务实体列表
	 */
	List<MyScheduler> getSchedulerListByTrigger(@Param(value="myTriggerId")long myTriggerId, 
			@Param(value="onOrOff")int onOrOff, @Param(value="pageNum")int pageNum, @Param(value="pageSize")int pageSize);
	
	/**
	 * 根据使用工作细节删除任务
	 * @param myTriggerId 工作细节ID
	 */
	void deleteSchedulerByJobDetail(@Param(value="myJobDetailId")long myJobDetailId);
	
	/**
	 * 根据使用规则删除任务
	 * @param myTriggerId 规则ID
	 */
	void deleteSchedulerByTrigger(@Param(value="myTriggerId")long myTriggerId);
}
