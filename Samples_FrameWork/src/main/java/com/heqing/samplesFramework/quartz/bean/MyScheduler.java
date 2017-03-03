package com.heqing.samplesFramework.quartz.bean;

import java.util.Date;
import java.util.Random;

//自定义的任务
public class MyScheduler {

	public static final String None     = "None";		//Trigger已经完成，且不会在执行，或者找不到该触发器，或者Trigger已经被删除
	public static final String NORMAL   = "NORMAL";		//正常状态	
	public static final String PAUSED   = "PAUSED";		//暂停状态	
	public static final String COMPLETE = "COMPLETE";	//触发器完成，但是任务可能还正在执行中
	public static final String BLOCKED  = "BLOCKED";	//线程阻塞状态
	public static final String ERROR    = "ERROR";		//出现错误	
	public static final String CLOSE    = "CLOSE";		//关闭状态	
	
	private long id = System.currentTimeMillis()+new Random().nextInt(10000);
	private String name;			    	//名称
	private String group;			    	//组
	private String state = NORMAL;			//状态
	private MyJobDetail myJobDetail;		//任务ID
	private MyTrigger myTrigger;			//规则ID
	private boolean onOrOff = false;		//开启或关闭
	private String describe;			    //描述
	private String createUser;  			//创建者
	private Date createTime = new Date();   //创建时间

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
	 * @return the 名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the 名称 to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the 组
	 */
	public String getGroup() {
		return group;
	}
	/**
	 * @param group the 组 to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}
	
	/**
	 * @return the 状态
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the 状态 to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * @return the 任务ID
	 */
	public MyJobDetail getMyJobDetail() {
		return myJobDetail;
	}
	/**
	 * @param jobDetailId the 任务ID to set
	 */
	public void setMyJobDetail(MyJobDetail myJobDetail) {
		this.myJobDetail= myJobDetail;
	}
	
	/**
	 * @return the 规则ID
	 */
	public MyTrigger getMyTrigger() {
		return myTrigger;
	}
	/**
	 * @param jobTriggerId the 规则ID to set
	 */
	public void setMyTrigger(MyTrigger myTrigger) {
		this.myTrigger = myTrigger;
	}
	
	/**
	 * @return the 开/关
	 */
	public boolean isOnOrOff() {
		return onOrOff;
	}
	/**
	 * @param onOrOff the 开/关 to set
	 */
	public void setOnOrOff(boolean onOrOff) {
		this.onOrOff = onOrOff;
	}
	
	/**
	 * @return the 描述
	 */
	public String getDescribe() {
		return describe;
	}
	/**
	 * @param describe the 描述 to set
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
	 * @param createUser the 创建者 to set
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
	 * @param createTime the 创建时间  to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
