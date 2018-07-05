<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="dataObject.MyFileBean"%>
<%@page import="java.util.ArrayList"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
      <title><s:text name="个人信息管理系统->查看"></s:text></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body bgcolor="gray">
       
            <h1>文件上传成功！</h1>
            <s:a href="fileManager/lookFile.jsp"><h3>3秒后自动跳转......</h3></s:a>
            <hr/>
            文件标题:<s:property value="+title"/><br/>
            <s:property value="uploadFileName"/><br/>
            <image src="<s:property value="'../save/'+uploadFileName"/>"/>
           
    </body>
</html>

