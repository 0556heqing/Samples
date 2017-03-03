package com.heqing.samplesFramework.spring.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.heqing.samplesBase.utils.JsonUtil;
import com.heqing.samplesFramework.spring.bean.Test;

@Controller
@RequestMapping("/springMVC")
public class TestController {

	private Log log = LogFactory.getLog(getClass());
	
	@ModelAttribute   
    public void modelAttribute() {    
       System.out.println("===before===");
    }
	
	@ExceptionHandler  
    public void exceptionHandler(Exception e) {    
       System.out.println("===@ExceptionHandler===");
       System.out.println(e.getMessage());  
    }
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder){
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),true));
	}
	
	@RequestMapping("/index")
	public String index(Map<String,Object> map) {
		System.out.println("===springMVC===");
		map.put("message", "Hello World!");
		return "springMVC";
	}  
	
	@RequestMapping("/view")
	public ModelAndView view(HttpServletRequest req, HttpServletResponse resp) throws Exception {  
       //1、收集参数、验证参数  
       //2、绑定参数到命令对象  
       //3、将命令对象传入业务对象进行业务处理  
       //4、选择下一个页面  
       ModelAndView mv = new ModelAndView();  
       //添加模型数据 可以是任意的POJO对象  
       mv.addObject("message", "Hello World!");  
       //设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面  
       mv.setViewName("springMVC");  
       return mv;  
    }  
	
	@RequestMapping("/jsonUtil")
	public void jsonUtil(HttpServletResponse response,HttpServletRequest request, int id, String name) {
		Test test = new Test();
		test.setId(id);
		test.setName(name);
		List<String> list = new ArrayList<>();
		list.add("test1"); list.add("test2");
		Map<String,Object> paramsMap=new LinkedHashMap<String,Object>();
		paramsMap.put("id", 1);
		paramsMap.put("message", "Hello World!");
		paramsMap.put("test", test);
		paramsMap.put("list", list);
		JsonUtil.outPutJsonMessage(log, "test", response, paramsMap);
	}
	
	@RequestMapping(value = "/requestBody", method = {RequestMethod.POST })
	@ResponseBody
	public void requestBody(@RequestBody List<Test> tests) {
		System.out.println("===requestBody===");
		System.out.println("size="+tests.size()+"	name1="+tests.get(0).getName());
	}  

	@RequestMapping("/responseBody")  
	@ResponseBody
	public Map<String, Object> responseBody() throws IOException {  
		System.out.println("===responseBody===");
		Map<String,Object> paramsMap = new LinkedHashMap<String,Object>();
		paramsMap.put("id", 1);
		paramsMap.put("name", "name1");
		return paramsMap;
	}  

	/**
	 * value: 路径地址
	 * method：请求方法的规则，比如：如果设置了RequestMethod.POST，那么你的表单提交就必须使用POST提交，否则将报405错误 
	 * params： 提交中的数据中一定要有配置的参数（如id），否则将报400的错误
	 * */
	@RequestMapping(value = "/requestParam", method = {RequestMethod.POST }, params="id") 
	@ResponseBody
	public void requestParam(@RequestParam int id, @RequestParam String name) {  
		System.out.println("===requestParam===");  
        System.out.println("id="+id+", name="+name);  
    }
	
	@RequestMapping("/requestParamAuto")
	@ResponseBody
	public void toPerson(String id, String name){
		System.out.println("===requestParamAuto==="); 
		System.out.println("id="+id+", name="+name);  
	}
	
	@RequestMapping("/requestBeanAuto")
	@ResponseBody
	public void requestBeanAuto(Test test){
		System.out.println("===requestBeanAuto==="); 
		System.out.println("id="+test.getId()+", name="+test.getName());  
	}
	
	@RequestMapping("/pathVariable/{id}") 
	@ResponseBody
	public void pathVariable(@PathVariable("id") String id) {  
		System.out.println("===pathVariable===");  
        System.out.println("id="+id);  
    }
	
	@RequestMapping("/initBinder") 
	@ResponseBody
	public void initBinder(Date date) {  
		System.out.println("===initBinder===");  
        System.out.println("date="+date);  
    }
	
	@RequestMapping("/upload")  
	@ResponseBody
    public void upload(HttpServletRequest request) throws IllegalStateException, IOException {
		System.out.println("===upload===");
        //创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();
            while(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        //重命名上传后的文件名  
                        String fileName = file.getOriginalFilename();  
                        //定义上传路径  
                        String path = "D:/test/" + fileName;  
                        File localFile = new File(path);  
                        file.transferTo(localFile);  
                    }
                }
                //记录上传该文件后的时间  
                int finaltime = (int) System.currentTimeMillis();  
                System.out.println("上传用时："+(finaltime - pre)+"秒");  
            }  
        }  
    }  
}
