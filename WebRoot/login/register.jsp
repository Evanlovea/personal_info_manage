<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   <title><s:text name="个人信息管理系统->注册"/></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body bgcolor="#CCCCFF">
        <s:form action="registerAction" method="post">
            <table align="center">
                <tr>
                 
                    <td width="40%">
                        <table border="2" bgcolor="#AABBCCDD" width="100%" align="center">
                            <tr>
                                <td colspan="2" align="center">
                                    <font color="yellow"><s:text name="请填写以下注册信息"/></font>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:textfield name="userName" label="登录名"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:password name="password1" label="密码" size="21"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:password name="password2" label="再次输入密码" size="21"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:textfield name="name" label="用户真是姓名"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:text name="用户性别:"></s:text>
                                </td>
                                <td>
                                    <input type="radio"  name="sex" value="男" checked/>男
                                    <input type="radio"  name="sex" value="女"/>女
                                </td>
                            </tr>
                            <tr>
                                <td>
                                <s:textfield name="birth" label="出生日期"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:textfield name="nation" label="用户民族"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:select name="edu" label="用户学历" headerValue="----------请选择----------" headerKey="1"
                                              list="{'硕士','博士','教授','本科','专科','高中','初中','小学','其它'}">    
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:select name="work" label="用户职称" headerValue="----------请选择----------" headerKey="1"
                                              list="{'软件测试工程师','软件开发工程师','教师','学生','职员','经理','老板','公务员','其它'}">    
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:textfield name="phone" label="用户电话"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:textfield name="place" label="用户住址"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:textfield name="email" label="用户邮箱"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center">
                                    <input type="submit" value="确定"/>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="reset" value="清空"/>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <s:a href="login/index.jsp">返回</s:a>
                                </td>
                            </tr>
                        </table>
                    </td>
                  
                </tr>
            </table>
        </s:form>
    </body>
</html>
