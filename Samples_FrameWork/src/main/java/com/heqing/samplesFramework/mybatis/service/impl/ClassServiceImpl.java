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
import com.heqing.samplesFramework.mybatis.service.ClassService;

@Service
public class ClassServiceImpl extends BaseServiceImpl<Class> implements ClassService {

	@Resource
	private ClassDao classesDao; 
	@Resource
	private TeacherDao teacherDao; 

	public void save(Class myClass) {
		classesDao.save(myClass);
		//班主任
		Teacher headTeacher = myClass.getHeadTeacher();
		if(headTeacher !=null) {
			if(teacherDao.getById(headTeacher.getId()) != null) teacherDao.update(headTeacher);
			else teacherDao.save(headTeacher);
		}
		//年级主任
		Teacher classDirector = myClass.getClassDirector();
		if(classDirector !=null) {
			if(teacherDao.getById(classDirector.getId()) != null) teacherDao.update(classDirector);
			else teacherDao.save(classDirector);
		}
		//授课教师
		ArrayList<Teacher> teachClasses = (ArrayList<Teacher>) myClass.getTeachers();
		if(teachClasses !=null && teachClasses.size()>0) {
			for(Teacher teachClass : teachClasses) {
				if(teacherDao.getById(teachClass.getId()) != null) teacherDao.update(teachClass);
				else teacherDao.save(teachClass);
				classesDao.deleteTeacherClass(myClass.getId(), teachClass.getId());
				classesDao.saveTeacherClass(myClass.getId(), teachClass.getId());
			}
		}
	}
	
	public void update(Class myClass) {
		classesDao.update(myClass);
		//班主任
		Teacher headTeacher = myClass.getHeadTeacher();
		if(headTeacher !=null) {
			if(teacherDao.getById(headTeacher.getId()) != null) teacherDao.update(headTeacher);
			else teacherDao.save(headTeacher);
		}
		//年级主任
		Teacher classDirector = myClass.getClassDirector();
		if(classDirector !=null && teacherDao.getById(classDirector.getId()) != null) {
			if(teacherDao.getById(classDirector.getId()) != null) teacherDao.update(classDirector);
			else teacherDao.save(classDirector);
		}
		//授课教师
		ArrayList<Teacher> teachClasses = (ArrayList<Teacher>) myClass.getTeachers();
		if(teachClasses != null && teachClasses.size()>0) {
			for(Teacher teachClass : teachClasses) {
				if(teacherDao.getById(teachClass.getId()) != null) teacherDao.update(teachClass);
				else teacherDao.save(teachClass);
				classesDao.deleteTeacherClass(myClass.getId(), teachClass.getId());
				classesDao.saveTeacherClass(myClass.getId(), teachClass.getId());
			}
		}
	}
	
	public void delete(Long id) {
		Class myClass = classesDao.getById(id);
		classesDao.deleteTeacherClass(myClass.getId(), 0);
		//修改班主任信息
		if(myClass != null) {
			Teacher teacher = myClass.getHeadTeacher();
			if(teacher !=null && teacherDao.getById(teacher.getId()) != null) {
				teacher.setSuperviseClass(null);
				teacherDao.update(teacher);
			}
			classesDao.delete(id);
		}
		//修改年级主任信息
		Teacher teacher = new Teacher();
		teacher.setSuperviseClass(myClass);
		List<Teacher> teachers = teacherDao.findAll(teacher);
		for(Teacher t : teachers) {
			t.setSuperviseClass(null);
			teacherDao.update(t);
		}
	}

	@Override
	public List<Class> getClassListByTeacher(long teacherId) {
		// TODO Auto-generated method stub
		return classesDao.getClassListByTeacher(teacherId);
	}
	
	@Override
	public List<Class> getClassByTeacherId(long teacherId) {
		// TODO Auto-generated method stub
		return classesDao.getClassByTeacherId(teacherId);
	}

}
