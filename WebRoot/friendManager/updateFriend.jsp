<%@page import="dataObject.MyFriBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    
        <title><s:text name="个人信息管理系统->修改联系人"></s:text></title>
    </head>
    <body bgcolor="gray">
      <hr noshade/>
     
      <s:form action="findFriAction" method="post">
      <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
          <tr>
              <td width="33%">
                  <s:a href="http://localhost:8080/ch13/friendManager/addFriend.jsp">增加联系人</s:a>
              </td>
              <td width="33%">
                  <s:a href="http://localhost:8080/ch13/friendManager/lookFriends.jsp">查看联系人</s:a>
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
      <s:form action="upFriAction" method="post">
          <table border="2" cellspacing="0" cellpadding="0" bgcolor="95BDFF" width="60%" align="center">
              <%
            ArrayList delemess=(ArrayList)session.getAttribute("findfriend");
            if(delemess==null||delemess.size()==0){
            %>
          <%="您还没有添加联系人！"%>
            <%
            }else{
                for(int i=delemess.size()-1;i>=0;i--){
                    MyFriBean ff=(MyFriBean)delemess.get(i);
                    %> 
               <tr>
                     <td><s:text name="用户姓名"></s:text></td>
                     <td>
                         <input type="text" name="name" value="<%=ff.getName()%>"/>
                     </td>
               </tr>
               <tr>
                   <td><s:text name="用户电话"></s:text></td>
                     <td>
                         <input type="text" name="phone" value="<%=ff.getPhone()%>"/>
                     </td>
               </tr>
               <tr>
                   <td><s:text name="邮箱地址"></s:text></td>
                     <td>
                         <input type="text" name="email" value="<%=ff.getEmail()%>"/>
                     </td>
               </tr>
               <tr>
                   <td><s:text name="工作单位"></s:text></td>
                     <td>
                        <input type="text" name="workplace" value="<%=ff.getWorkplace()%>"/>
                     </td>
               </tr>
               <tr>
                   <td><s:text name="家庭住址"></s:text></td>
                     <td>
                         <input type="text" name="place" value="<%=ff.getPlace()%>"/>
                     </td>
               </tr>
               <tr>
                   <td><s:text name="用户QQ"></s:text></td>
                     <td>
                         <input type="text" name="QQ" value="<%=ff.getQQ()%>"/>
                     </td>
               </tr>
               <tr>
                 <td colspan="2" align="center">
                     <input type="submit" value="确 定" size="12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     <input type="reset" value="清 除" size="12">
                 </td>
               </tr>
               <%
                }
            }
          %>
            </table>
        </s:form>
    </body>
</html>
