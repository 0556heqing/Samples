package com.heqing.samplesBase.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

	/** 
     * 向接口调用处返回json格式数据
     * @param logger 调用日志类
     * @param methodName 调用方法名
     * @param response WEB response
     * @param jsonMap 返回的map型数据
     * @throws IOException 
     */ 
    public static void outPutJsonMessage(Log logger, String methodName, HttpServletResponse response, Map<String,Object> jsonMap){
        //"Access-Control-Allow-Origin: *"
        response.addHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json; charset=UTF-8");
    	
    	//输出json字符串
		PrintWriter out = null;
		JSONObject jsonObject = new JSONObject();
		for(String key:jsonMap.keySet()){
			jsonObject.put(key,jsonMap.get(key));
		}
		try {
			out = response.getWriter();
			out.write(jsonObject.toString());
			logger.info("返回JSON结果："+jsonObject.toString());
		} catch (IOException e) {
			logger.error("流输出异常：",e);
		}finally{
			out.close();
		}
    }
}
