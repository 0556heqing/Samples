package com.heqing.samplesBase;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.Labels;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.heqing.samplesBase.bean.json.FastjsonUser;

public class TestFastJson {
	
//	序列化(JavaBean --> json)时配置到对应字段的get()方法上
//	反序列化(json --> JavaBean)时配置到对应字段的set()方法上
	
//	需要注意的是fastjson在序列化和反序列化的时候，默认是开启ASM的。关于ASM的介绍请查看官方文档https:
//		github.com/alibaba/fastjson/wiki/ASMDeserializerFactory%E8%AE%BE%E8%AE%A1
//	SerializeConfig.getGlobalInstance().setAsmEnable(false); // 序列化的时候关闭ASM  
//	ParserConfig.getGlobalInstance().setAsmEnable(false); // 反序列化的时候关闭ASM 
	
	@Test
	public void testCreateJson() {
		JSONObject object = new JSONObject();
		object.put("String", "value1");	// String
		object.put("int", 1); 			// int
		object.put("double", 2.6); 		// double
		object.put("numberString", "55.8");
		System.out.println("object="+object);
		
		object = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("map_stringKey", "map_stringKey");
		map.put("map_floatKey", 22.12);
		object.put("map", map); 		// 如果是Map,则添加里层JSONObject</span>
		object.putAll(map); 			// 如果是通过putAll,则会循环依次作为Object对象插入JSONObject数据</span>
		System.out.println("object="+object);
		
		object = new JSONObject();
		FastjsonUser user = new FastjsonUser();
		user.setAge(18);
		user.setName("张三");
		object.put("user", user);		//如果是实体类对象,则会将实体类组装成JSONObject放进去
		List<FastjsonUser> users = new ArrayList<FastjsonUser>();
		for (int i = 0; i < 2; i++) {
		    FastjsonUser u = new FastjsonUser();
		    u.setAge(i + 18);
		    u.setSex(i % 2 == 0 ? "男" : "女");
		    users.add(u);
		}
		object.put("list", users); // List
		System.out.println("object="+object);
		
		object = new JSONObject();
		// 构造JSONArray格式的数据
		JSONArray array = new JSONArray();
		array.add("a");
		array.add("b");
		object.put("array", array);		
		System.out.println("object="+object);
	}
	
//	@Test
	public void testParsingJson() {
		String json = "{\"String\":\"value1\",\"array\":[\"a\",\"b\"],\"double\":2.6,\"int\":1,"
				+ "\"list\":[{\"age\":18,\"sex\":\"男\"},{\"age\":19,\"sex\":\"女\"}],\"user\":{\"age\":18,\"name\":\"张三\"},"
				+ "\"map\":{\"map_floatKey\":22.12,\"map_stringKey\":\"map_stringKey\"},\"map_floatKey\":22.12,\"map_stringKey\":\"map_stringKey\",\"numberString\":\"55.8\"}";
		
		JSONObject object = (JSONObject) JSON.parse(json); 	// 测试创建JSONObject
		System.out.println("JSONObject = " + object.toJSONString());
		
		System.out.println("map_stringKey = " + object.getString("map_stringKey"));
		JSONObject mapObject = object.getJSONObject("map");
		System.out.println("map_stringKey = " + mapObject.getString("map_stringKey"));
		System.out.println("map_floatKey = " + mapObject.getFloatValue("map_floatKey"));

		System.out.println("double = " + object.getDoubleValue("double"));
		System.out.println("double = " + object.getDouble("double"));
		System.out.println("double = " + object.getString("double"));

		System.out.println("int = " + object.getInteger("int"));
		System.out.println("int = " + object.getIntValue("int"));
		System.out.println("int = " + object.getDouble("int"));
		System.out.println("int = " + object.getDoubleValue("int"));
		System.out.println("int = " + object.getString("int"));
		
		System.out.println("numberString = " + object.getString("numberString"));
		System.out.println("numberString = " + object.getDouble("numberString"));

		JSONObject userObject = object.getJSONObject("user");
		System.out.println("user : " + userObject.toJSONString());
		FastjsonUser user = JSON.toJavaObject(userObject, FastjsonUser.class);
		user.setSex("男");
		System.out.println(user.toString());
		// JavaBean --> String
		String userString = JSON.toJSONString(user);
		System.out.println("userString " + userString);

		JSONArray array = object.getJSONArray("list");
		for (int i = 0; i < array.size(); i++) {
		    FastjsonUser user2 = JSON.toJavaObject(array.getJSONObject(i), FastjsonUser.class);
		    System.out.println(user2.toString());
		}
		
		// 解析失败
		System.out.println("not exit : " + object.getInteger("integer")); // null
		System.out.println("not exit : " + object.getIntValue("integer")); // 0
	}
	
//	@Test
	public void testSerialization() {
		//java对象 转换成 json
		FastjsonUser user = new FastjsonUser();
		user.setAge(18);
		user.setName("张三");
		JSONObject object = (JSONObject)JSON.toJSON(user);  
		System.out.println(object.toJSONString()); 
		
		//json 转换为  java对象
		String jsonString = JSON.toJSONString(user); 
		FastjsonUser u = JSON.parseObject(jsonString, FastjsonUser.class);
		System.out.println(u.toString()); 
		
		//List<T> 转换为 json
		List<FastjsonUser> users = new ArrayList<FastjsonUser>();  
		for (int i = 0; i < 3; i++) {  
			user = new FastjsonUser();
			user.setAge(18+i);
			user.setName("张三"+i);
			users.add(user);  
		}  
		System.out.println(JSON.toJSON(users));  
		
		String listString = JSON.toJSONString(users);
		List<FastjsonUser> usList = (List<FastjsonUser>)JSON.parseObject(listString, new TypeReference<List<FastjsonUser>>(){});  
		for (FastjsonUser us : usList) {  
		    System.out.println(us.toString());  
		}
		
		//Map 转换为 json
		Map<String, Object> map = new HashMap<String, Object>();  
		map.put("id", 1);  
		map.put("name", "一年级");  
		System.out.println(JSON.toJSON(map));
	}
	
//	@Test
	public void testSerializationConfig() {
		// 序列化的时候, 不序列化id, sex字段序列化成gender  
		FastjsonUser user = new FastjsonUser();   
		user.setId(1);   
		user.setAge(10); 
		user.setName("李四");  
		user.setSex("男");  
		System.out.println("@JSONField --> " + JSON.toJSONString(user)); 
		
		JSONObject userObject = new JSONObject();  
		userObject.put("id", "1");  
		userObject.put("name", "LiLei");  
		userObject.put("age", 26.5);  
		userObject.put("gender", "男");  
	    user = JSON.parseObject(userObject.toJSONString(), FastjsonUser.class);  
		System.out.println("id="+user.getId()+"  name="+user.getName()+"  age="+user.getAge()+"  sex="+user.getSex());
	
		user = new FastjsonUser();
		user.setDate(new Date());
		// 日期不做处理  
		System.out.println("1>>"+JSON.toJSONString(user)); 
		// 序列化日期为ISO-8601日期格式  
		System.out.println("2>>"+JSON.toJSONString(user, SerializerFeature.UseISO8601DateFormat));  
		// 序列化日期为指定格式  
		System.out.println("3>>"+JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss.SSS"));  
		// 修改全局的全局日期格式  
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";  
		// 使用默认的全局日期格式  
		System.out.println("4>>"+JSON.toJSONString(user, SerializerFeature.WriteDateUseDateFormat)); 
		
		// 序列化label = "normal"的字段  
		System.out.println(JSON.toJSONString(user, Labels.includes("normal")));  
		// 序列化label != "normal"的字段  
		System.out.println(JSON.toJSONString(user, Labels.excludes("normal")));  
	}
	
//	@Test
	public void testJSONPath() {
		String jsonStr = "{ \"store\": {\"book\": [{ \"category\": \"reference\","+  
                "\"author\": \"Nigel Rees\",\"title\": \"Sayings of the Century\","+  
                "\"price\": 8.95},{ \"category\": \"fiction\",\"author\": \"Evelyn Waugh\","+  
                "\"title\": \"Sword of Honour\",\"price\": 12.99,\"isbn\": \"0-553-21311-3\""+  
                "}],\"bicycle\": {\"color\": \"red\",\"price\": 19.95}}}";  
		JSONObject jsonObject = JSON.parseObject(jsonStr);  
		
		System.out.println("\n Book数目：" + JSONPath.eval(jsonObject, "$.store.book.size()"));  
		System.out.println("第一本书title：" + JSONPath.eval(jsonObject, "$.store.book[0].title"));  
		System.out.println("price大于10元的book：" + JSONPath.eval(jsonObject, "$.store.book[price > 10]"));  
		System.out.println("price大于10元的title：" + JSONPath.eval(jsonObject, "$.store.book[price > 10][0].title"));  
		System.out.println("category(类别)为fiction(小说)的book：" + JSONPath.eval(jsonObject, "$.store.book[category = 'fiction']"));  
		System.out.println("bicycle的所有属性值" + JSONPath.eval(jsonObject, "$.store.bicycle.*"));  
		System.out.println("bicycle的color和price属性值" + JSONPath.eval(jsonObject, "$.store.bicycle['color','price']"));  
	}
}
