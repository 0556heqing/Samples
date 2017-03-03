package com.heqing.samplesFramework.mybatis.service;

import java.util.List;

import com.heqing.samplesFramework.mybatis.base.BaseService;
import com.heqing.samplesFramework.mybatis.bean.Class;

public interface ClassService extends BaseService<Class> {
	
	/**
	 * 根据教师ID查找所有教授的班级
	 * @param id 教师ID
	 * @return List<T>  实体列表
	 */
	public List<Class> getClassByTeacherId(long teacherId);
	
	/**
	 * 根据年级主任ID查找所管理的班级
	 * @param id 教师ID
	 * @return List<T>  实体列表
	 */
	public List<Class> getClassListByTeacher(long teacherId);
	
}
