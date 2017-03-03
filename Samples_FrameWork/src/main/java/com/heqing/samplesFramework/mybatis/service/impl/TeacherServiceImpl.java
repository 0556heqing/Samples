package com.heqing.samplesFramework.mybatis.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.heqing.samplesFramework.mybatis.base.BaseServiceImpl;
import com.heqing.samplesFramework.mybatis.bean.Class;
import com.heqing.samplesFramework.mybatis.bean.Teacher;
import com.heqing.samplesFramework.mybatis.dao.ClassDao;
import com.heqing.samplesFramework.mybatis.dao.TeacherDao;
import com.heqing.samplesFramework.mybatis.service.TeacherService;

@Service
public class TeacherServiceImpl extends BaseServiceImpl<Teacher> implements TeacherService{

	@Resource
	private TeacherDao teacherDao; 
	@Resource
	private ClassDao classesDao; 

	public void save(Teacher teacher) {
		teacherDao.save(teacher);
		//管理班级
		Class superviseClass = teacher.getSuperviseClass();
		if(superviseClass != null) {
			superviseClass.setHeadTeacher(teacher);
			if(classesDao.getById(superviseClass.getId()) != null) classesDao.update(superviseClass);
			else classesDao.save(superviseClass);
		} 
		//管理年级
		ArrayList<Class> classDirectors = (ArrayList<Class>) teacher.getClassDirector();
		if(classDirectors != null && classDirectors.size() > 0){
			for(Class classDirector : classDirectors) {
				classDirector.setClassDirector(teacher);
				if(classesDao.getById(classDirector.getId()) != null) classesDao.update(classDirector);
				else classesDao.save(classDirector);
			}
		}
		//授课班级
		ArrayList<Class> teachClasses = (ArrayList<Class>) teacher.getTeachClasses();
		if(teachClasses != null && teachClasses.size() > 0){
			for(Class teachClass : teachClasses) {
				if(teachClass != null) {
					if(classesDao.getById(teachClass.getId())!= null) classesDao.update(teachClass);
					else classesDao.save(teachClass);
				}
				teacherDao.deleteTeacherClass(teachClass.getId(), teacher.getId());
				teacherDao.saveTeacherClass(teachClass.getId(), teacher.getId());
			}
		}
	}
	
	public void update(Teacher teacher) {
		teacherDao.update(teacher);
		//管理班级
		Class superviseClass = teacher.getSuperviseClass();
		if(superviseClass != null) {
			superviseClass.setHeadTeacher(teacher);
			if(classesDao.getById(superviseClass.getId()) != null) classesDao.update(superviseClass);
			else classesDao.save(superviseClass);
		}
		//授课班级
		ArrayList<Class> teachClasses = (ArrayList<Class>) teacher.getTeachClasses();
		if(teachClasses != null && teachClasses.size() > 0){
			for(Class teachClass : teachClasses) {
				if(classesDao.getById(teachClass.getId()) != null) classesDao.update(teachClass);
				else classesDao.save(teachClass);
				teacherDao.deleteTeacherClass(teachClass.getId(), teacher.getId());
				teacherDao.saveTeacherClass(teachClass.getId(), teacher.getId());
			}
		}
		//管理年级
		ArrayList<Class> classDirectors = (ArrayList<Class>) teacher.getClassDirector();
		if(classDirectors != null && classDirectors.size() > 0){
			for(Class classDirector : classDirectors) {
				if(classesDao.getById(classDirector.getId()) != null) classesDao.update(classDirector);
				else classesDao.save(classDirector);
			}
		}
	}
	
	public void delete(Long id) {
		Teacher teacher = teacherDao.getById(id);
		teacherDao.deleteTeacherClass(0, teacher.getId());
		if(teacher != null) {
			//管理班级
			Class superviseClass = teacher.getSuperviseClass();
			if(superviseClass !=null && classesDao.getById(superviseClass.getId()) != null) {
				superviseClass.setHeadTeacher(null);
				if(superviseClass.getClassDirector() !=null && superviseClass.getClassDirector().getId() == teacher.getId()) 
					superviseClass.setClassDirector(null);
				classesDao.update(superviseClass);
			}
			//管理年级
			ArrayList<Class> classDirectors = (ArrayList<Class>) teacher.getClassDirector();
			if(classDirectors != null && classDirectors.size() > 0){
				for(Class classDirector : classDirectors) {
					if(classesDao.getById(classDirector.getId()) != null) {
						classDirector.setClassDirector(null);
						if(classDirector.getHeadTeacher() !=null && classDirector.getHeadTeacher().getId() == teacher.getId()) 
							classDirector.setHeadTeacher(null);
						classesDao.update(classDirector);
					}
				}
			}
			teacherDao.delete(teacher.getId());
		}
	}

	@Override
	public List<Teacher> getTeacherByClassId(long classId) {
		// TODO Auto-generated method stub
		return teacherDao.getTeacherByClassId(classId);
	}

}
