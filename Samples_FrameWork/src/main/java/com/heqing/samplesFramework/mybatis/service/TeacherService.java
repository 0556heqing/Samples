package com.heqing.samplesFramework.mybatis.service;

import java.util.List;

import com.heqing.samplesFramework.mybatis.base.BaseService;
import com.heqing.samplesFramework.mybatis.bean.Teacher;

public interface TeacherService extends BaseService<Teacher> {
	
	/**
	 * 根据班级ID查找所有授课教师
	 * @param id 班级ID
	 * @return List<T>  实体列表
	 */
	public List<Teacher> getTeacherByClassId(long classId);
}
