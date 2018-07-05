<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<%
    String path = request.getContextPath();
    // 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
   <head>
      <base href="<%=basePath%>">
   </head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="个人信息管理系统"/></title>
    </head>
    <body bgcolor="#CCCCFF">
        <s:form action="loginAction" method="post">
            <table align="center" width="100%">
                <tr>
                    <td align="right" width="50%">
                        <img src="images/logo.png" height="80"/>
                    </td>
                    <td align="left" width="50%">
                        <h1>个人信息管理系统</h1>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <hr align="center" width="100%" size="20" color="grey"/>
                    </td>
                </tr>
                <tr>
                    <td width="30%" align="center">
                        <image src="images/welcome.jpg" height="70%"/>
                    </td>
                    <td width="70%" style="width: 458px; height: 314px">
                        <table border="2" align="center" bgcolor="#99aadd" style="width: 407px; height: 328px">
                            <tr>
                                <td style="width: 478px; ">
                                    <s:textfield name="userName" label="登录名" size="18"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:password name="password" label="登录密码" size="18"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center">
                                    <input type="submit" value="确定" style="width: 87px; height: 38px; "/>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="reset" value="清空" style="width: 87px; height: 38px; "/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4" align="center">
                                    <s:a href="login/register.jsp">注册</s:a>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </s:form>
    </body>
</html>
