<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjr" uri="/struts-jquery-richtext-tags"%>
<head>
<s:head />
<sj:head />

</head>
<s:debug/>
<s:form name="loginForm" method="post">
	<s:textfield name="name" label="name" />
	<s:textfield name="qq" label="qq" />
	<sjr:ckeditor id="richTextEditor" name="memo" label="备注" rows="10" cols="80"
		width="730" height="250" value=""/>
	<s:submit />
</s:form>