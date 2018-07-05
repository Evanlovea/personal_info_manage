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
    
    <title><s:text name="个人信息管理系统->查找"></s:text></title>
    
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
                  <s:a href="fileManager/fileUp.jsp">上传文件</s:a>
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
      <table border="5" cellspacing="0" cellpadding="0" bgcolor="#95BDFF" width="60%" align="center">
          <tr>
              <th height="30">文件标题</th>
              <th height="30">文件名字</th>
              <th height="30">文件类型</th>
              <th height="30">文件大小</th>
              <th height="30">用户操作</th>
          </tr>
          <%
            ArrayList file=(ArrayList)session.getAttribute("findfile");
            if(file==null||file.size()==0){
            %>
            <%="您还没有上传文件！"%>
            <%
            }else{
                for(int i=file.size()-1;i>=0;i--){
                    MyFileBean ff=(MyFileBean)file.get(i);
                    %> 
                   <tr>
                     <td><%=ff.getTitle()%></td>
                     <td><%=ff.getName()%></td>
                     <td><%=ff.getContentType()%></td>
                     <td><%=ff.getSize()%></td>
                     <td>
                         <s:a href="downFileAction">下载</s:a>
                         <s:a href="deleteFileAction">删除</s:a>
                     </td>
                   </tr>
                   <tr align="center">
                       <td colspan="5">
                           <img src="../save/<%=ff.getName()%>"/>
                       </td>
                   </tr>
                    <%
                }
            }
          %>
        </table>
    </body>
</html>
