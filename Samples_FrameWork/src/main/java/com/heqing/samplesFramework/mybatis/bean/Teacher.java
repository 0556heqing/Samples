package com.heqing.samplesFramework.mybatis.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.heqing.samplesBase.utils.TimeUtil;

public class Teacher {
	
	//注：一堆一，一对多，多对多实际运用不应设计set方法，此处只为做实验
	private long id = System.currentTimeMillis()+new Random().nextInt(10000);
	private String name;				//名字
	private Date birthDay;				//生日
	private int age;					//年龄
	private Class superviseClass;		//管理班级
	private List<Class> teachClasses;	//授课班级
	private List<Class> classDirector;	//管理年级
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
	 * @return the 名字
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the 名字 to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the 生日
	 */
	public Date getBirthDay() {
		return birthDay;
	}
	/**
	 * @param birthDay the 生日 to set
	 */
	public void setBirthDay(String birthDay) {
		try {  
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		    this.birthDay = sdf.parse(birthDay);  
		} catch (Exception e)   {  
		    e.printStackTrace();
		}  
	}
	
	/**
	 * @return the 年龄
	 */
	public int getAge() {
		return TimeUtil.getAge(this.birthDay);
	}
	/**
	 * @param age the 年龄 to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the 管理班级
	 */
	public Class getSuperviseClass() {
		return superviseClass;
	}
	/**
	 * @param superviseClass the 管理班级 to set
	 */
	public void setSuperviseClass(Class superviseClass) {
		this.superviseClass = superviseClass;
	}
	
	/**
	 * @return the 授课班级
	 */
	public List<Class> getTeachClasses() {
		return teachClasses;
	}
	/**
	 * @param teachClasses the 授课班级 to set
	 */
	public void setTeachClasses(List<Class> teachClasses) {
		this.teachClasses = teachClasses;
	}
	
	/**
	 * @return the 管理年级
	 */
	public List<Class> getClassDirector() {
		return classDirector;
	}
	/**
	 * @param classDirector the 管理年级 to set
	 */
	public void setClassDirector(List<Class> classDirector) {
		this.classDirector = classDirector;
	}
}
