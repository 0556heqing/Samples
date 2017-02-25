package com.heqing.samplesFramework;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.heqing.samplesFramework.mybatis.bean.Classes;
import com.heqing.samplesFramework.mybatis.bean.Teacher;
import com.heqing.samplesFramework.mybatis.service.ClassesService;
import com.heqing.samplesFramework.mybatis.service.TeacherService;

@RunWith(SpringJUnit4ClassRunner.class)		// 表示继承了 SpringJUnit4ClassRunner 类
@ContextConfiguration(locations = {"classpath*:spring_core.xml","classpath:spring_mybatis.xml"})
public class TestMybatis {

	//测试前须修改spring_core.xml 14行 ： com.heqing.samplesFramework.Mybatis.*
	
	@Resource	
	private ClassesService classesService;
	@Resource	
	private TeacherService teacherService;
	
	@Test
	public void testCache() {
		Teacher teacher1 = teacherService.getById(1L);
		System.out.println("1------->"+teacher1.getName());
		Teacher teacher2 = teacherService.getById(1L);
		System.out.println("2------->"+teacher2.getName());
	}
	
//	@Test
	public void testFind() {
		List<Teacher> teachers = (List<Teacher>)teacherService.getByIds(new Long[]{1l,2l});
		for(Teacher teacher : teachers) {
			System.out.println("编号'"+teacher.getId()+"'的教师名为："+teacher.getName());
			System.out.println("	他是 "+teacher.getSuperviseClass().getName()+" 的班主任");
			System.out.print("	他管理的班级有："); for(Classes c : teacher.getClassDirector()) System.out.print(c.getName()+"  ");System.out.println(); 
			System.out.print("	他教授的班级有："); for(Classes c : teacher.getTeachClasses()) System.out.print(c.getName()+"  ");System.out.println(); 
		}
		System.out.println("================");
		List<Classes> classes = (List<Classes>)classesService.findAll();
		for(Classes c : classes) {
			System.out.println("编号'"+c.getId()+"'的班级名为："+c.getName());
			System.out.println("	它的班主任为："+c.getHeadTeacher().getName());
			System.out.println("	它的系主任为："+c.getClassDirector().getName()); 
			System.out.print("	它的授课教师有："); for(Teacher t : c.getTeachers()) System.out.print(t.getName()+"  ");System.out.println(); 
		}
	}
	
//	@Test
	public void testSave() {
		Teacher teacher = new Teacher();
		teacher.setName("小赵");
		teacher.setBirthday(new Date());
		teacher.setPost("上海");
		teacherService.save(teacher);
	}
	
//	@Test
	public void testUpdate() {
		Teacher teacher = (Teacher)teacherService.getById(3L);
		teacher.setPost("上海浦东");
		teacherService.update(teacher);
	}
	
//	@Test
	public void testDelete() {
		teacherService.delete(3L);
	}
}
