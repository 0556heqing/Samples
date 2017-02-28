package com.heqing.samplesFramework.quartz.bean;

import java.util.Date;
import java.util.Random;

//自定义的工作详情
public class MyJobDetail {

	private long id = System.currentTimeMillis()+new Random().nextInt(10000);
	private String className= "";			//目标类名
	private String methodsName= "";			//方法名
	private String content;					//内容（json字段）
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
	 * @return the 目标类名
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @param className the 目标类名 to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * @return the 目标类中的方法名
	 */
	public String getMethodsName() {
		return methodsName;
	}
	/**
	 * @param methodsName the 目标类中的方法名 to set
	 */
	public void setMethodsName(String methodsName) {
		this.methodsName = methodsName;
	}
	/**
	 * @return the 内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the 内容 to set
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * @param createTime the 创建时间 to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
