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
    
    <title>scheduler</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="scheduler">
	<meta http-equiv="description" content="This is my test page">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">
	<script src="<%=request.getContextPath()%>/script/jquery-3.1.1.min.js"></script> 
  </head>
  
  <body>
     <div class="mainCenter clearfix">
		<div class="mainCenterRight">
				<div class="mainCenterRightD">
				<h3>监听器</h3>
				<div class="inputArea">
					<ul class="clearfix">
						<li>名称<input id="name"></input></li>
						<li>组<input id="heightVal"></input></li>
						<li>开启或关闭<input id="headCircumferenceVal"></input></li>
					</ul>		
				</div>
				<div class="submitInfo">
					<input type="button" value="提交" id="submitData">
				</div>
			</div>
		</div>
	</div>      
  </body>
  
  <script>

  </script>
</html>