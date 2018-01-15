<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/default090909.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/lay.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">

<link rel="stylesheet" href="${ctx}/js/Validform_v5.3/css/valid.css" type="text/css" media="all" />
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/Validform_v5.3.js"></script>
</head>
<body>
<form class="fm1" id="fm1" name="fm1" action="${ctx}/course/coursewareSave.do" method="post">
<table border="0" cellspacing="1" cellpadding="0" width="100%" class="gridtable">
	<tr>
		<td align="center" colspan="2" class="row_tip" height="25">
			查看用户信息
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			姓名：
		</td>
		<td align="left">
		${item.realName}
		</td>
	</tr>
	<tr>
		<td class="row_tip"  height="25">
			证件号码：
		</td>
		<td width="25">
		    ${item.certificateNo}
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			登录名：
		</td>
		<td>
		    ${item.accountName}
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			邮箱：
		</td>
		<td>
		    ${item.email}
		</td>
	</tr>
	
	<tr>
		<td class="row_tip" height="25">
			性别：
		</td>
		<td>
		 <c:if test="${item.sex eq 1}">男</c:if>
		<c:if test="${item.sex eq 2}">女</c:if>
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			出生年月：
		</td>
		<td>
		<fmt:formatDate value="${item.birthday}" pattern="yyyy-MM-dd"/>&nbsp;
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			手机：
		</td>
		<td>
		  ${item.mobilPhone}
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			单位地址：
		</td>
		<td>
		  ${config.userProvinceName }&nbsp;${config.userCityName }&nbsp;${config.userCountiesName }&nbsp;${config.userStreetName }
		</td>
     </tr>
	<tr>
		<td class="row_tip" height="25">
			单位名称：
		</td>
		<td>
		  ${item.workUnit}
		</td>
     </tr>
     <tr>
		<td class="row_tip" height="25">
			行业岗位名称：
		</td>
		<td>
		  ${config.userIndustryName}
		</td>
     </tr>
     <tr>
		<td class="row_tip" height="25">
			注册日期：
		</td>
		<td>
		  ${item.regDate}
		</td>
     </tr>
	 
	<tr>
		<td colspan="6" align="center" class="row_tip">
		    <input type="button" class="btn_02s" onclick="javascript:window.history.go(-1);"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>