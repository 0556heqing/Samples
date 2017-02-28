package com.heqing.samplesFramework.spring.thread;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class AfterInitialization implements ApplicationListener<ContextRefreshedEvent>{
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//root application context 没有parent，他就是老大. 
		if(((ApplicationContextEvent) event).getApplicationContext().getParent() == null){
			System.out.println("============Spring Container initialized============");
       }  
	}
}
