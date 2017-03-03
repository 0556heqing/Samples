package com.heqing.samplesFramework.spring.service.impl;

import org.springframework.stereotype.Service;

import com.heqing.samplesFramework.spring.bean.Test;
import com.heqing.samplesFramework.spring.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	
	@Override
	public String test(int id, String name, Test test) {
		// TODO Auto-generated method stub
		System.out.println("id="+id+", name="+name+", "+test.toString());
		return "Hello Wrold!";
	}

}
