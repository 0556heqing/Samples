package com.heqing.samplesBase.bean.json;

import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GsonUser {

	private int id;
	private String name;
	private int age;
	private String sex;
	private Date date = new Date();
	@SerializedName(value = "emailAddress", alternate = {"email", "email_address"})
	public String emailAddress;
	/*
	 * @Expose(deserialize = true,serialize = true) //序列化和反序列化都都生效
	 * @Expose(deserialize = true,serialize = false) //反序列化时生效
	 * @Expose(deserialize = false,serialize = true) //序列化时生效
	 * @Expose(deserialize = false,serialize = false) // 和不写一样
	 * */
	@Expose(deserialize = false,serialize = false)
	public GsonUser patient;

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
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public GsonUser getPatient() {
		return patient;
	}
	public void setPatient(GsonUser patient) {
		this.patient = patient;
	} 
}
