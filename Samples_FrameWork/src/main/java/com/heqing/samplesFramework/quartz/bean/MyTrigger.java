package com.heqing.samplesFramework.quartz.bean;

import java.util.Date;
import java.util.Random;

//自定义的规则
public class MyTrigger {
	
	private long id = System.currentTimeMillis()+new Random().nextInt(10000);
	private String rule = "";				//规则 ：QuartZ Cron表达式
	private String describe;			    //描述
	private String createUser;  			//创建者
	private Date   createTime = new Date(); //创建时间
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the 规则 ：QuartZ Cron表达式
	 */
	public String getRule() {
		return rule;
	}
	/**
	 * @param 规则  the 规则  to set (QuartZ Cron表达式)
	 */
	public void setRule(String rule) {
		this.rule = rule;
	}
	
	/**
	 * @return the 描述
	 */
	public String getDescribe() {
		return describe;
	}
	/**
	 * @param 描述 the 描述 to set
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	/**
	 * @return the 创建者
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * @param 创建者 the 创建者 to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	/**
	 * @return the 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param 创建时间 the 创建时间 to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
