<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<head>
<title>输入日期</title>
<s:head/>
<sj:head/>
</head>
<s:debug/>
<s:actionerror/>
<s:form name="validatonForm">
   <sj:datepicker value="today" id="date" name="date" key="date" displayFormat="%{getText('format.date.input')}"
   changeMonth="true" changeYear="true"/>
</s:form>