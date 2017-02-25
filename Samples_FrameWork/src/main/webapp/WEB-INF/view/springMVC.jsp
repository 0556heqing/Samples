<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>springMVC</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="springMVC">
	<meta http-equiv="description" content="This is my test page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script> 
	<script src="<%=request.getContextPath()%>/js/ajaxfileupload.js"></script> 
  </head>
  
  <body>
             后台传递过来的消息：${message}  <br/>
  	<spring:message code="start"/><br/>
    <button id="RequestBody" 	  type="button">测试  @RequestBody</button><br/>
    <button id="ResponseBody" 	  type="button">测试  @ResponseBody</button><br/>
    <button id="RequestParam" 	  type="button">测试  @RequestParam</button><br/>
    <button id="RequestParamAuto" type="button">测试  自动匹配参数</button><br/>
    <button id="RequestBeanAuto"  type="button">测试  自动装箱</button><br/>
    <button id="PathVariable" 	  type="button">测试  @PathVariable</button><br/>
    <button id="InitBinder" 	  type="button">测试  @InitBinder</button><br/>
    <button id="JsonUtil" 	  	  type="button">测试 JsonUtil</button><br/>
    <input type="file" id="uFile" name="uFile" multiple="multiple"/>
    	<button id="uploadAll" type="button">上传文件</button>
  </body>
  
  <script>
  	$("#RequestBody").click(function(){
      	var testAry=[];  
        var test1={"id":1,"name":"name1"};  
        var test2={"id":2,"name":"name2"};  
        testAry.push(test1);  
        testAry.push(test2);    
        
        $.ajax({
        	type: "POST",
            url: "/Samples_FrameWork/springMVC/requestBody",
            data:JSON.stringify(testAry),
            dataType: "json", 
            contentType: "application/json;",
            success: function(data){
            	console.info(data);
            }
       });
    });
    
    $("#JsonUtil").click(function(){
        $.ajax({
        	type: "POST",
            url: "/Samples_FrameWork/springMVC/jsonUtil",
            data: { 
            	"id" : 1,
            	"name" : "name"
            },
            success: function(data){
            	console.info(data);
            }
       });
    });
    
    $("#ResponseBody").click(function(){
        $.ajax({
        	type: "POST",
            url: "/Samples_FrameWork/springMVC/responseBody",
            success: function(data){
            	console.info(data);
            }
       });
    });
    
    $("#RequestParam").click(function(){
        $.ajax({
        	type: "POST",
            url: "/Samples_FrameWork/springMVC/requestParam",
            data: { 
            	"id" : 3,
            	"name" : "name3"
            },
            success: function(data){
            	console.info(data);
            }
       });
    });
    
    $("#RequestParamAuto").click(function(){
        $.ajax({
        	type: "POST",
            url: "/Samples_FrameWork/springMVC/requestParamAuto",
            data: { 
            	"id" : 3,
            	"name" : "name3"
            },
            success: function(data){
            	console.info(data);
            }
       });
    });
    
    $("#RequestBeanAuto").click(function(){
        $.ajax({
        	type: "POST",
            url: "/Samples_FrameWork/springMVC/requestBeanAuto",
            data: { 
            	"id" : 3,
            	"name" : "name3"
            },
            success: function(data){
            	console.info(data);
            }
       });
    });
    
    $("#PathVariable").click(function(){
        $.ajax({
        	type: "POST",
            url: "/Samples_FrameWork/springMVC/pathVariable/4",
            success: function(data){
            	console.info(data);
            }
       });
    });
    
    $("#InitBinder").click(function(){
        $.ajax({
        	type: "POST",
            url: "/Samples_FrameWork/springMVC/initBinder",
            data: { 
            	"date" : "2017-01-16"
            },
            success: function(data){
            	console.info(data);
            }
       });
    });
    
    $("#uploadAll").click(function(){
    	$.ajaxFileUpload({
		   url:"/Samples_FrameWork/springMVC/upload",
		   fileElementId:"uFile",
		   success: function (data, status){
		        alert("上传成功");
		   },
		});
    });
  </script>
</html>