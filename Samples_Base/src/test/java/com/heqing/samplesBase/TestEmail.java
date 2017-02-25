package com.heqing.samplesBase;

import java.io.File;
import java.util.ArrayList;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.heqing.samplesBase.bean.Email;
import com.heqing.samplesBase.utils.EmailUtil;

public class TestEmail {

	private final Log logger = LogFactory.getLog(getClass());
	
	@Test
	public void sendEmail() throws MessagingException {
		
		Email email = new Email();
		ArrayList<String> toUser = new ArrayList<String>();
		toUser.add("975656343@qq.com");toUser.add("3187344038@qq.com");
		email.setToUser(toUser);
		email.setFromUser("heqing_test@sina.com");
		email.setSubject("测试文件");
		email.setText("<html><head></head><body><h1>hello!!this is test send email page</h1></body></html>");
		ArrayList<File> files = new ArrayList<File>();
		files.add(new File("D:/test/test.jpg"));
		email.setFiles(files);
		
		Boolean isSuccess = EmailUtil.sendEmail(logger, email);
		if(isSuccess) System.out.println("发送成功");
		else System.out.println("发送失败");

	}
}
