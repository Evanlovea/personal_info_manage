<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<%@page import="dataObject.MyDayBean"%>
<%@page import="java.util.ArrayList"%>
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
 
      <s:form action="findDayAction" method="post">
      <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
          <tr>
              <td width="30%">
                  <s:a href="dateTimeManager/addDay.jsp">增加日程</s:a>
              </td>
              <td width="30%">
                  <s:text name="查看日程"></s:text>
              </td>
              <td width="40%">
                  <s:text name="日程时间:"></s:text>
                  20<input type="text" size="1" name="year"/>年
                 <input type="text" size="1" name="month"/>月
                 <input type="text" size="1" name="day"/>日
                  <input type="submit" value="修删日程"/>
              </td>
          </tr>
      </table>
      </s:form>
     
      <hr noshade/>
      <table border="5" cellspacing="0" cellpadding="0" bgcolor="#95BDFF" width="60%" align="center">
          <tr>
                <th width="40%">日程时间</th>
                <th width="60%">日程内容</th>
          </tr>
          <%
            ArrayList day=(ArrayList)session.getAttribute("day");
            if(day==null||day.size()==0){
            %>
            <%="您还没有任何日程安排！"%>
            <%
        }else{
            for(int i=day.size()-1;i>=0;i--){
                MyDayBean dd=(MyDayBean)day.get(i);
                %>
                    <tr>
                        <td><%=dd.getDay()%></td>
                        <td><%=dd.getThing()%></td>
                    </tr>
                    <%
                }
            }
          %>
      </table>
    </body>
</html>

