package com.heqing.samplesFramework.mybatis.bean;

import java.util.Date;
import java.util.Set;

public class Teacher {
	
	private long id;
	private String name;
	private Date birthday;
	private int age;
	private String post;
	private Classes superviseClass;		//管理班级
	private Set<Classes> teachClasses;	//授课班级
	private Set<Classes> classDirector;	//班级主任

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public Classes getSuperviseClass() {
		return superviseClass;
	}
	public void setSuperviseClass(Classes superviseClass) {
		this.superviseClass = superviseClass;
	}
	
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	
	public Set<Classes> getTeachClasses() {
		return teachClasses;
	}
	public void setTeachClasses(Set<Classes> teachClasses) {
		this.teachClasses = teachClasses;
	}
	
	public Set<Classes> getClassDirector() {
		return classDirector;
	}
	public void setClassDirector(Set<Classes> classDirector) {
		this.classDirector = classDirector;
	}
}
