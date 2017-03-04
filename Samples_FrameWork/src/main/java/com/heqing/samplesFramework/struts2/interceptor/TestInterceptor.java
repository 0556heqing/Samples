package com.heqing.samplesFramework.struts2.interceptor;

import java.util.Map;

import com.heqing.samplesFramework.struts2.action.TestAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

//直接在类名称的上端写入即可，value中指定要引入的拦截器的名称即可
public class TestInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("====拦截====");
        // 验证 session  
        Map<String, Object> session = ActionContext.getContext().getSession();  
        // 对LoginAction不做该项拦截  
        Object action = actionInvocation.getAction();  
        if (action instanceof TestAction) {  
            return actionInvocation.invoke();  
        } 
        return "index"; 
	}

}
