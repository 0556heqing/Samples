package com.heqing.samplesBase.bean.json;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

/*
 * 注意和@JSONField不同的是,@JSONType是配置在类上的，而@JSONField是配置在字段和方法上的
 * 使用ignores属性配置序列化的时候,不序列化id字段 .
 * 使用includes配置只序列化name,sex字段 
 */
//@JSONType(ignores = {"id"})
//@JSONType(includes={"name", "sex"})  
public class FastjsonUser {

	// 配置序列化的时候,不序列化id  
	@JSONField(serialize=false)  
	private int id;
	private String name;
	private int age;
	// 配置序列化的名称  
	@JSONField(name="gender") 
	private String sex;
	// 配置format只在序列化(JavaBean --> json)的时候有用  
//	@JSONField(format="yyyy-MM-dd")  
	private Date date = new Date(); 
	
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
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override  
    public String toString() {  
        return "user = [id="+id+",name="+name+",age="+age+",sex="+sex+",date="+date+"]";  
    }  
}
