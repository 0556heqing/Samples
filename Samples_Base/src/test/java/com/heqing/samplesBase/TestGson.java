package com.heqing.samplesBase;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.heqing.samplesBase.bean.json.GsonUser;
import com.heqing.samplesBase.utils.GsonUtil;

public class TestGson {

	Gson gson = new Gson();
	
//	@Test
	public void testBase(){
		//基本数据类型的解析
		int i = gson.fromJson("100", int.class);              //100
		double d = gson.fromJson("\"99.99\"", double.class);  //99.99
		boolean b = gson.fromJson("true", boolean.class);     // true
		String str = gson.fromJson("String", String.class);   // String
		System.out.println("i="+i+", d="+d+", b="+b+", str="+str);
		
		//基本数据类型的生成
		String jsonNumber = gson.toJson(100);       // 100
		String jsonBoolean = gson.toJson(false);    // false
		String jsonString = gson.toJson("String"); //"String"
		System.out.println("jsonNumber="+jsonNumber+", jsonBoolean="+jsonBoolean+", jsonString="+jsonString);
		
		//POJO类的生成与解析
		GsonUser user = new GsonUser();
		user.setName("heqing");
		user.setAge(27);
		user.setDate(new Date());
		String jsonObject = gson.toJson(user);
		System.out.println("jsonObject="+jsonObject);
	}
	
//	@Test
	public void testSerializedName() {
		//POJO类的生成与解析
		GsonUser user = new GsonUser();
		user.setName("heqing");
		user.setAge(27);
		user.setDate(new Date());
		user.setEmailAddress("975656343@qq.com");
		String jsonObject = gson.toJson(user);
		System.out.println("jsonObject="+jsonObject);
		
		//@SerializedName为POJO字段提供重用、备选属性名
		String json1 = "{\"id\":0,\"name\":\"heqing\",\"age\":27,\"date\":\"Feb 9, 2017 7:55:09 PM\",\"emailAddress\":\"975656343@qq.com\"}";
		user = gson.fromJson(json1, GsonUser.class);
		System.out.println(user.getEmailAddress());
		String json2 = "{\"id\":0,\"name\":\"heqing\",\"age\":27,\"date\":\"Feb 9, 2017 7:55:09 PM\",\"email_address\":\"975656343@qq.com\"}";
		user = gson.fromJson(json2, GsonUser.class);
		System.out.println(user.getEmailAddress());
	}
	
//	@Test
	public void testObject() {
		//JSON字符串数组
		String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
		String[] strings = gson.fromJson(jsonArray, String[].class);
		System.out.println("jsonArray.length="+strings.length);
		
		//JSON List
		List<String> stringList = gson.fromJson(jsonArray, new TypeToken<List<String>>() {}.getType());
		System.out.println("stringList.length="+stringList.size());
		
		//JSON Object
		GsonUser user = new GsonUser();
		user.setName("heqing");
		user.setAge(27);
		user.setDate(new Date());
		user.setEmailAddress("975656343@qq.com");
		GsonUtil<GsonUser> userResult = new GsonUtil<GsonUser>();
		userResult.setSuccess(true);
		userResult.setData(user);
	}
	
//	@Test
	public void testGsonBuilder() {
		GsonUser user = new GsonUser();
		user.setName("heqing");
		user.setAge(27);
		System.out.println(gson.toJson(user)); 
		
		gson = new GsonBuilder().serializeNulls().create();
		System.out.println(gson.toJson(user)); 
		
		gson = new GsonBuilder()
        //序列化null
        .serializeNulls()
        // 设置日期时间格式，另有2个重载方法
        // 在序列化和反序化时均生效
        .setDateFormat("yyyy-MM-dd")
        // 禁此序列化内部类
        .disableInnerClassSerialization()
        //生成不可执行的Json（多了 )]}' 这4个字符）
//        .generateNonExecutableJson()
        //禁止转义html标签
        .disableHtmlEscaping()
        //格式化输出
        .setPrettyPrinting()
        .create();
	    System.out.println(gson.toJson(user)); 
	}
	
	@Test
	public void testExpose() {
		GsonUser user = new GsonUser();
		user.setName("heqing");
		user.setAge(27);
		System.out.println(gson.toJson(user)); 
		
		gson = new GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create();
		System.out.println(gson.toJson(user)); 
	}
}
