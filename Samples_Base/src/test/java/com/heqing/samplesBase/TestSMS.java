package com.heqing.samplesBase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.heqing.samplesBase.utils.SMSUtil;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

public class TestSMS {

	private final Log logger = LogFactory.getLog(getClass());
	
	@Test
	public void sendEmail() throws MessagingException {

//		int mobile_code = (int)((Math.random()*9+1)*100000); //验证码
		String phone = "13061756506";
		
		String[] phones = phone.split(";");
		for(String p : phones){
			try{
				Thread.sleep(1000L*2);
				String content = new String("您好！he 医生向您分享了病例《test》，请点击查看详情 33");
				content = content.replace("33", "http://www.boholo.com/web/index.html");
				SMSUtil.sendSMS(logger, p, content);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
	}
}