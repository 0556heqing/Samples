package com.heqing.samplesFramework.struts2.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.heqing.samplesFramework.struts2.base.BaseAction;
import com.heqing.samplesFramework.struts2.bean.Test;

@Controller
@Scope("prototype")
public class TestAction extends BaseAction<Test> {

	public String index() {
		System.out.println("===struts2===");
		return "struts2";
	}

}
