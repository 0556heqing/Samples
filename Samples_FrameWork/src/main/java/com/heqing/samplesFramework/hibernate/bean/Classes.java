package com.heqing.samplesFramework.hibernate.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Classes {
	
	private long id;
	private String name;
	private Teacher headTeacher;	//班主任
	private Set<Teacher> teachers;	//授课教师
	private Teacher classDirector;	//年级主任
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	//mappedBy 对应主表Model中的 set*() 方法
	@OneToOne(mappedBy="superviseClass")
	public Teacher getHeadTeacher() {
		return headTeacher;
	}
	public void setHeadTeacher(Teacher headTeacher) {
		this.headTeacher = headTeacher;
	}
	
	@ManyToMany(mappedBy="teachClasses",fetch=FetchType.EAGER)
	public Set<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	@ManyToOne( targetEntity = Teacher.class,cascade = CascadeType.ALL,fetch=FetchType.EAGER)  
	@JoinColumn(name = "classDirector_id",referencedColumnName="id") 
	public Teacher getClassDirector() {
		return classDirector;
	}
	public void setClassDirector(Teacher classDirector) {
		this.classDirector = classDirector;
	}
}
