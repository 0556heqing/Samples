package com.heqing.samplesBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;
import org.junit.Test;

public class TestJson {

	//JSONObject将String转为JSON对象
	@Test
	public void testJsonObject() {
		JSONObject getObj = new JSONObject("{'name':'heqing','age':27}");  
		String name = getObj.getString("name");  
	    int age = getObj.getInt("age");  
		System.out.println("name="+name+", age="+age);
		
		JSONObject putObj = new JSONObject();
		putObj.put("name", "heqing");
		putObj.put("num", new Integer(27));
		System.out.println("putObj="+putObj);
	}
	
	//JSONArray将String转为JSON数组
//	@Test
	public void testJsonArray() {
		 JSONArray jsonarray = new JSONArray("[{'name':'heqing','age':27},{'name':'hequan','age':15}]");  
		 for(int i=0;i<jsonarray.length();i++){  
		 	String name = jsonarray.getJSONObject(i).getString("name");  
		 	int age = jsonarray.getJSONObject(i).getInt("age");  
		 	System.out.println("name="+name+", age="+age);
		 }  

	}
	
	//嵌套JSONObject和JSONArray代码实例
//	@Test
	public void testJsonObjectAndArray() {
		String str = "{'name':'heqing','age':27,'book':['java','html5']}";  
		JSONObject obj = new JSONObject(str);  
		System.out.println(obj.getJSONArray("book").getString(0));  
	}
	
	//JSONStringer可以用来快速构建一个JSON格式的文本，并转换成String，可以写入文件
//	@Test
	public void testJsonStringer() {
		JSONStringer stringer = new JSONStringer();  
		String str = stringer.object().key("name").value("heqing").key("age").value(27).endObject().toString();  
		System.out.println(str);
	}
	
	//JSONTokener是用来读取JSON格式的文件
//	@Test
	public void testJsonTokener() {
		try {
			 JSONObject obj = new JSONObject(new JSONTokener(new FileReader(new File("D:/test/json.txt"))));
			 System.out.println(obj.getJSONArray("book").getString(0));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
