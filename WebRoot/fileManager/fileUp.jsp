<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title><s:text name="个人信息管理系统->文件上传"></s:text></title>
    
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
        <hr noshade/>
    
      <s:form action="findFileAction" method="post">
      <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
          <tr>
              <td width="33%">
                  <s:text name="上传文件"></s:text>
              </td>
              <td width="33%">
                  <s:a href="fileManager/lookFile.jsp">文件列表</s:a>
              </td>
              <td width="33%">
                  <s:text name="文件标题:"></s:text>
                  <input type="text" name="title"/>
                  <input type="submit" value="下载"/>
              </td>
          </tr>
      </table>
      </s:form>
      
      <hr noshade/>
      <s:form action="addFileAction" method="post" enctype="multipart/form-data">
          <table border="5" cellspacing="0" cellpadding="0" bgcolor="#95BDFF" width="60%" align="center">
                <tr>
                     <td>
                         <s:textfield name="title" label="文件标题" size="30"></s:textfield>
                     </td>
                </tr>
                <tr>
                     <td height="30">
                         <s:file name="upload" label="选择文件" size="30"></s:file>
                     </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="上 传" size="12"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="reset" value="清 空" size="12"/>
                    </td>
                </tr>
            </table>
        </s:form>
    </body>
</html>

