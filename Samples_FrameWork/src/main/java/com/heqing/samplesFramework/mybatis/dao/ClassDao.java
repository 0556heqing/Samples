package com.heqing.samplesFramework.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.heqing.samplesFramework.mybatis.base.BaseDao;
import com.heqing.samplesFramework.mybatis.bean.Class;

/**
 * 持久层，数据访问对象
 */
public interface ClassDao extends BaseDao<Class> {
	
	/**
	 * 根据年级主任ID查找所管理的班级
	 * @param id 实体类ID
	 * @return List<T>  实体列表
	 */
	public List<Class> getClassListByTeacher(long teacherId);
	
	/**
	 * 根据教师ID查找所有教授的班级
	 * @param id 实体类ID
	 * @return List<T>  实体列表
	 */
	public List<Class> getClassByTeacherId(long teacherId);
	
	/**
	 * 多个教师对多个班级的增加
	 * @param classId 	班级ID
	 * @param teacherId 教师ID
	 */
	public void saveTeacherClass(@Param(value="classId")long classId, @Param(value="teacherId")long teacherId);

	/**
	 * 多个教师对多个班级的删除
	 * @param classId 	班级ID
	 * @param teacherId 教师ID
	 */
	public void deleteTeacherClass(@Param(value="classId")long classId, @Param(value="teacherId")long teacherId);
}
