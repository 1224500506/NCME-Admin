<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table cellpadding="0" cellspacing="0" border="1" align="center" width="400" class="dataintable">
	<!-- 
	<tr>
		<td align="center"><a href="${ctx}/questionManage/exportProp.do?type=0">导出属性代码</a></td>
	</tr>
 	-->	
	<tr>
		<td align="center"><a href="${ctx}/propManage/propList.do">关联属性</a></td>
	</tr> 
	<tr>
		<td align="center"><a href="${ctx}/propManage/propBaseList.do?type=7">行业</a></td>
	</tr>	
	<tr>
		<td align="center"><a href="${ctx}/propManage/propBaseList.do?type=8">知识分类</a></td>
	</tr>
	<tr>
		<td align="center"><a href="${ctx}/propManage/propBaseList.do?type=9">适用对象</a></td>
	</tr>
	<tr>
		<td align="center"><a href="${ctx}/questionManage/exportProp.do?type=1">导出属性ID</a></td>
	</tr>
	<!--
	<tr>
		<td align="center"><a href="${ctx}/propManage/olemPropList.do">三基属性维护</a></td>
	</tr>
	-->
	<tr>
		<td align="center"><a href="${ctx}/propManage/exportQuestionPropNum.do">导出试题分布</a></td>
	</tr>
</table>
</body>
</html>