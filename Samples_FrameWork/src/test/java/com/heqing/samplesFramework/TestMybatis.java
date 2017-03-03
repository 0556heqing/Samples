package com.heqing.samplesFramework;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.heqing.samplesFramework.mybatis.bean.Teacher;
import com.heqing.samplesFramework.mybatis.bean.Class;
import com.heqing.samplesFramework.mybatis.service.ClassService;
import com.heqing.samplesFramework.mybatis.service.TeacherService;

@RunWith(SpringJUnit4ClassRunner.class)		// 表示继承了 SpringJUnit4ClassRunner 类
@ContextConfiguration(locations = {"classpath*:spring_core.xml","classpath:spring_mybatis.xml"})
public class TestMybatis {

	//测试前须修改spring_core.xml 14行 ： com.heqing.samplesFramework.Mybatis.*
	
	@Resource	
	private ClassService classesService;
	@Resource	
	private TeacherService teacherService;
	
//	@Test
	public void testSave() {
		List<Class> classList1 = new ArrayList<Class>();
		List<Class> classList2 = new ArrayList<Class>();
		Class myClass1 = new Class();
		Class myClass2 = new Class();
		Class myClass3 = new Class();
		
		Teacher teacher1 = new Teacher();
		myClass1.setName("c1");
		myClass2.setName("c2");
		myClass3.setName("c3");
		classList1.add(myClass1);classList1.add(myClass2);		
		classList2.add(myClass1);classList2.add(myClass2);classList2.add(myClass3);	
		teacher1.setName("t1");
		teacher1.setBirthDay("1991-01-01");
		teacher1.setSuperviseClass(myClass1);
		teacher1.setClassDirector(classList2);
		teacher1.setTeachClasses(classList1);
		teacherService.save(teacher1);	

		List<Teacher> teacherList1 = new ArrayList<Teacher>();
		Teacher teacher2 = new Teacher();
		Teacher teacher3 = new Teacher();
		Class myClass4 = new Class();
		teacher2.setName("t2");teacher2.setBirthDay("1992-01-01");teacher2.setSuperviseClass(myClass4);
		teacher3.setName("t3");teacher3.setBirthDay("1993-01-01");
		myClass4.setName("c4");
		myClass4.setHeadTeacher(teacher2);
		teacherList1.add(teacher2);teacherList1.add(teacher3);
		myClass4.setClassDirector(teacher2);
		myClass4.setTeachers(teacherList1);
		classesService.save(myClass4);
	} 
	
//	@Test
	public void testUpdate() {
		Teacher teacher2 = teacherService.getById(1488518036767L);
		Teacher teacher3 = teacherService.getById(1488518037497L);
		Class myClass3 = classesService.getById(1488518042864L);
		
		List<Class> classList = new ArrayList<Class>();
		myClass3.setHeadTeacher(teacher3);
		classList.add(myClass3);
		teacher3.setSuperviseClass(myClass3);
		teacher3.setTeachClasses(classList);
		teacherService.update(teacher3);
		
		myClass3.setName("c33");
		myClass3.setClassDirector(teacher3);
		List<Teacher> teacherList = new ArrayList<Teacher>();
		teacherList.add(teacher2);teacherList.add(teacher3);
		myClass3.setTeachers(teacherList);
		classesService.update(myClass3);
	}

//	@Test 
	public void testFind() {
		Teacher teacher3 = (Teacher)teacherService.getById(1488518037497L);
		System.out.println("教师名为："+teacher3.getName());
		System.out.println("	他是 "+teacher3.getSuperviseClass().getName()+" 的班主任");
		System.out.print("	他管理的班级有："); for(Class c : teacher3.getClassDirector()) System.out.print(c.getName()+"  ");System.out.println(); 
		System.out.print("	他教授的班级有："); for(Class c : teacher3.getTeachClasses()) System.out.print(c.getName()+"  ");System.out.println(); 
		
		Class classes3 = (Class)classesService.getById(1488518042864L);
		System.out.println("班级名为："+classes3.getName());
		System.out.println("	它的班主任为："+classes3.getHeadTeacher().getName());
		System.out.println("	它的系主任为："+classes3.getClassDirector().getName()); 
		System.out.print("	它的授课教师有："); for(Teacher t : classes3.getTeachers()) System.out.print(t.getName()+"  ");System.out.println(); 
	}
	
//	@Test
	public void testDelete() {
		teacherService.delete(1488518037497L);
		classesService.delete(1488518042864L);
	}

}
