package com.heqing.samplesFramework.mybatis.bean;

import java.util.List;
import java.util.Random;

public class Class {

	//注：一堆一，多对一，多对多实际运用不应设计set方法，此处只为做实验
	private long id = System.currentTimeMillis()+new Random().nextInt(10000);
	private String name;			//名称
	private Teacher headTeacher;	//班主任
	private Teacher classDirector;	//年级主任
	private List<Teacher> teachers;	//授课教师
	
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
	 * @return the 班主任
	 */
	public Teacher getHeadTeacher() {
		return headTeacher;
	}
	/**
	 * @param headTeacher the 班主任 to set
	 */
	public void setHeadTeacher(Teacher headTeacher) {
		this.headTeacher = headTeacher;
	}
	
	/**
	 * @return the 年级主任
	 */
	public Teacher getClassDirector() {
		return classDirector;
	}
	/**
	 * @param classDirector the 年级主任 to set
	 */
	public void setClassDirector(Teacher classDirector) {
		this.classDirector = classDirector;
	}
	
	/**
	 * @return the 授课教师
	 */
	public List<Teacher> getTeachers() {
		return teachers;
	}
	/**
	 * @param teachers the 授课教师 to set
	 */
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	
}
