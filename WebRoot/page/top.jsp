<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.hys.auth.model.HysUsers" %>
<%@ page import="com.hys.exam.common.util.LogicUtil" %>
<%
	HysUsers user = LogicUtil.getSystemUser(request);
	String username = "";
	if(null != user){
		if(null != user.getRealName()){
			username = user.getRealName();
		}
	}
%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<%@include file="/commons/taglibs.jsp"%>
		<style type="text/css">
<!--
.style2 {
	font-size: 12px
}

.style3 {
	font-size: 12px;
	color: #FFFFFF;
}

.style4 {
	color: #333333
}

.style5 {
	color: #000000
}

.tablebg1 {
	background-color: #3983C0;
}

.tablebg2 {
	background: url(../images/bg.jpg);
}
-->
</style>
	</head>
	<body class="body2">
		<table class="tablebg1" border="0" cellpadding="0" cellspacing="0"
			height="100%" width="100%">
			<tbody>
				<tr>
					<td>
						<!-- 
						<table class="tablebg1" border="0" cellpadding="0" cellspacing="0"
							width="100%">
							<tbody>
								<tr>
									<td align="left" width="50%">
										<img src="${ctx}/images/logoleft.jpg" border="0">
									</td>
									<td align="right" width="50%">
										<img src="${ctx}/images/logoright.jpg" border="0">
									</td>
								</tr>
							</tbody>
						</table>
						 -->
						<table class="tablebg2" border="0" cellpadding="3" cellspacing="0"
							height="25" width="100%">
							<tbody>
								<tr>
									<td class="class2SectionHead style3" nowrap="nowrap"
										width="180">
										您好<%=username %>! 欢迎使用本系统
									</td>
									<td class="class2SectionHead style3" width="70%">
										
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>