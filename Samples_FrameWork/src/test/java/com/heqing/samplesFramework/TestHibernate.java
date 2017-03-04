package com.heqing.samplesFramework;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.heqing.samplesFramework.hibernate.bean.Classes;
import com.heqing.samplesFramework.hibernate.bean.Teacher;
import com.heqing.samplesFramework.hibernate.service.ClassesService;
import com.heqing.samplesFramework.hibernate.service.TeacherService;

@RunWith(SpringJUnit4ClassRunner.class)		// 表示继承了 SpringJUnit4ClassRunner 类
@ContextConfiguration(locations = {"classpath*:spring_core.xml","classpath:spring_hibernate.xml"})
public class TestHibernate {

	//测试前须修改spring_core.xml 14行 ： com.heqing.samplesFramework.Hibernate.*
	
	@Resource
	private SessionFactory sessionFactory;
	@Resource
	protected ClassesService classesService;
	@Resource
	protected TeacherService teacherService;

    //测试SessionFactory建表
//	@Test
	public void createTable() throws Exception {
		System.out.println(sessionFactory);
	}
	
//	@Test
	public void testSave() throws Exception {
		Classes classes1 = new Classes();
		classes1.setName("一班");
		Classes classes2 = new Classes();
		classes2.setName("二班");
		Teacher teacher1 = new Teacher();
		teacher1.setName("小贺");
		Teacher teacher2 = new Teacher();
		teacher2.setName("小刘");
		
		Set<Classes> classList = new HashSet<Classes>();
		classList.add(classes1);
		classList.add(classes2);
		Set<Teacher> teacherList = new HashSet<Teacher>();
		teacherList.add(teacher1);
		teacherList.add(teacher2);

		//一对一
		teacher1.setSuperviseClass(classes1);
		teacher2.setSuperviseClass(classes2);
		//一对多
		classes1.setClassDirector(teacher1);
		classes1.setHeadTeacher(teacher1);
		classes2.setClassDirector(teacher1);
		classes2.setHeadTeacher(teacher2);
		teacher1.setClassDirector(classList);
		//多对多
		teacher1.setTeachClasses(classList);
		teacher2.setTeachClasses(classList);
		
		teacherService.save(teacher1);
		teacherService.save(teacher2);
	}
	
//	@Test
	public void testFindById() throws Exception {
		Teacher teacher = teacherService.getById(1L);
		String name = teacher.getName();
		System.out.println(name+"为："+teacher.getSuperviseClass().getName()+"主任");	//一对一
		if(teacher.getTeachClasses() != null) {										//多对多
			System.out.print(name+"教授的班级有:");
			for(Classes c : teacher.getTeachClasses()) {
				System.out.print(c.getName()+",");
			}
			System.out.println();
		}
		if(teacher.getClassDirector() != null) {									//一对多
			System.out.print(name+"管理的的班级有:");
			for(Classes c : teacher.getClassDirector()) {
				System.out.print(c.getName()+",");
			}
			System.out.println();
		}
		
		System.out.println();
		
		Classes classes = classesService.getById(1L);						
		name = classes.getName();
		System.out.println(name+"的班主任为："+classes.getHeadTeacher().getName());		//一对一
		if(classes.getTeachers() != null) {											//多对多
			System.out.print(name+"的授课教师有:");
			for(Teacher t : classes.getTeachers()) {
				System.out.print(t.getName()+",");
			}
			System.out.println();
		}
		System.out.println(name+"的年级主任为："+classes.getClassDirector().getName());	//多对一
	}
	
//	@Test
	public void testOneToOne() throws Exception {
		Teacher teacher = teacherService.getById(1L);
		System.out.println(teacher.getSuperviseClass().getName());
		
		Classes classes = classesService.getById(1l);
		System.out.println(classes.getHeadTeacher().getName());
	}
	
//	@Test
	public void testManyToMany() throws Exception {
		Teacher teacher = teacherService.getById(1L);
		for(Classes c : teacher.getTeachClasses()) {
			System.out.println(c.getName());
		}
		
		Classes classes = classesService.getById(1l);
		for(Teacher t : classes.getTeachers()) {
			System.out.println(t.getName());
		}
	}
	
//	@Test
	public void testOneToMany() throws Exception {
		Teacher teacher = teacherService.getById(1L);
		for(Classes c : teacher.getTeachClasses()) {
			System.out.println(c.getName());
		}
		
		Classes classes = classesService.getById(1l);
		for(Teacher t : classes.getTeachers()) {
			System.out.println(t.getName());
		}
	}
	
//	@Test
	public void testUpdate() {
		Teacher teacher = teacherService.getById(1L);
		teacher.setBirthday(new Date());
		teacher.setPost("安庆");
		teacherService.update(teacher);
	}
	
//	@Test
	public void testDelete() {
		//注意，此处因为主键-外键关系过于复杂，无法删除。需删除需以下步骤（hibernate无法自动做）
		//1.删除 多对多 表中与此教师相关的。
		//2.将班级表中与此教师相关的设为空。
		//3.删除此教师
		teacherService.delete(1L);
	}
	
//	@Test
	public void testCache() {
		Teacher teacher1 = teacherService.getById(1L);
		System.out.println("1------->"+teacher1.getName());
		Teacher teacher2 = teacherService.getById(1L);
		System.out.println("2------->"+teacher2.getName());
	}

}
