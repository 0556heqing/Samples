package com.heqing.samplesFramework.struts2.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.heqing.samplesFramework.struts2.base.BaseAction;
import com.heqing.samplesFramework.struts2.bean.Test;

@Controller
@Action("/struts2")
@ParentPackage("crud-default")
@Results({	
	@Result(name="struts2", location="struts2.jsp"),
	@Result(name="json", 	type="json", params={"root","result","callbackParameter","callback","excludeProperties","rows.*"})
})
//拦截栈的引用，蓝色字体即拦截栈的引用名称
@InterceptorRefs(@InterceptorRef("checkStack"))
public class TestAction extends BaseAction<Test> {
	
	Test test;
	private String school;
	
	public Test getModel() {
		if(test == null){
			test = new Test();
		}
		return test;
	}

	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}

	@Action("/index")
	public String index() {
		System.out.println("===struts2===");
		return "struts2";
	}

	@Action("/json")
	public String json() {
		System.out.println("===json===");
		result.put("success", true);	
		result.put("name", "heqing");
		result.put("age", 27);
		return "json";
	}

	@Action("/returnJson")
	public String returnJson() {
		System.out.println("===returnJson===");
		result.put("success", true);
		result.put("name", test.getName());
		result.put("age", test.getAge());
		result.put("school", school);
		return "json";
	}
}
