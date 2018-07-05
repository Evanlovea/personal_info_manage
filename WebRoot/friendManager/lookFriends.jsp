<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<%@page import="dataObject.MyFriBean"%>
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

     <title><s:text name="个人信息管理系统->增加联系人"></s:text></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body bgcolor="gray">
      <hr noshade/>
     
      <s:form action="findFriAction" method="post">
      <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
          <tr>
              <td width="33%">
                  <s:a href="friendManager/addFriend.jsp">增加联系人</s:a>
              </td>
              <td width="33%">
                  <s:text name="查看联系人"></s:text>
              </td>
              <td width="33%">
                  <s:text name="修删联系人:"></s:text>
                  <input type="text" name="friendname"/>
                  <input type="submit" value="查找"/>
              </td>
          </tr>
      </table>
      </s:form>
  
      <hr noshade/>
      <table border="5" cellspacing="0" cellpadding="0" bgcolor="#95BDFF" width="60%" align="center">
          <tr>
              <th height="30">好友姓名</th>
              <th height="30">好友电话</th>
              <th height="30">邮箱地址</th>
              <th height="30">工作单位</th>
              <th height="30">家庭住址</th>
              <th height="30">QQ</th>
          </tr>
          <%
            ArrayList friends=(ArrayList)session.getAttribute("friends");
            if(friends==null||friends.size()==0){
            %>
            <%="您还没有添加联系人！"%>
            <%
            }else{
                for(int i=friends.size()-1;i>=0;i--){
                    MyFriBean ff=(MyFriBean)friends.get(i);
                    %> 
                   <tr>
                     <td><%=ff.getName()%></td>
                     <td><%=ff.getPhone()%></td>
                     <td><%=ff.getEmail()%></td>
                     <td><%=ff.getWorkplace()%></td>
                     <td><%=ff.getPlace()%></td>
                     <td><%=ff.getQQ()%></td>
                   </tr>
                    <%
                }
            }
          %>
        </table>
    </body>
</html>
