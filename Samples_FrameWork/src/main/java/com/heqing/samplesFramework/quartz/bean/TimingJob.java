package com.heqing.samplesFramework.quartz.bean;

import java.util.Date;

//定时工作
public class TimingJob {

	private int id;				
	private String name = "";			//工作名(必填)
	private String group = "";			//工作组(必填)
	private String className = "";		//执行的类名(必填)
	private String methodName = "";		//执行的方法名(必填)
	private String rules = "";			//执行规则(必填)
	private String content;				//内容（json字段）
	private String createUser;  		//创建者
	private Date createTime;			//创建时间
	private boolean isNormal = true;	//是否正常工作

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}

	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getRules() {
		return rules;
	}
	public void setRules(String rules) {
		this.rules = rules;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean isNormal() {
		return isNormal;
	}
	public void setNormal(boolean isNormal) {
		this.isNormal = isNormal;
	}

}
