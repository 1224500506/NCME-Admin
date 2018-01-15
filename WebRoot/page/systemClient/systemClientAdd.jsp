<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<!DOCTYPE HTML>
<html>
	<head>
	<%@ include file="/commons/taglibs.jsp"%>
		<link href="${ctx}/css/auth.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/lay.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
		<script type="text/javascript" src="${ctx}/js/prototype.js"></script>

		<link rel="stylesheet" href="${ctx}/js/Validform_v5.3/css/valid.css"
			type="text/css" media="all" />
		<script type="text/javascript"
			src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/Validform_v5.3/js/Validform_v5.3.js"></script>
		<title>新增客户</title>
		<style>
html {
	height: 100%;
}

#budget_div {
	width: 100%;
	height: 100%;
	padding: 8px;
	overflow: auto;
}

#list_div {
	width: 95%;
	height: 95%;
	padding: 8px;
	overflow: auto;
}

#bgdiv {
	position: absolute;
	top: 0px;
	left: 0px;
	width: 100%;
	height: 100%;
	background-color: #AAAAAA;
	z-index: 10;
	filter: alpha(opacity =     80);
	opacity: 0.8;
}

.main_table {
	border: 1px solid #c9c9c9;
}

table {
	margin: 5px 0 5px 0 !important;
}

body {
	width: 100%;
	height: 100%;
	margin: auto;
	padding: 0px;
	font-size: 12px;
	color: #000000;
	text-align: center;
}

.q4all {
	width: 100%;
	height: 94%;
	font-size: 12px;
	background-color: #FFFFFF;
	margin-bottom: 10px;
}

.subnav {
	width: 100%;
	height: 20px;
	background-image: url(../images/XYZY_04.jpg);
	background-repeat: repeat-x;
	padding-top: 4px;
	text-align: center;
}
</style>
		<!--[if IE]>
<style type="text/css">
.q4all { height: 90%; }
</style>
<![endif]-->
		<!--[if gte IE 7]>
<style type="text/css">
.q4all { height: 90%; }
</style>
<![endif]-->
		<script type="text/javascript">
function formSubmit(){
	if(document.getElementById('loginName').value==""){
		alert("请输入用户名！");
		return false;
	}
	if(document.getElementById('password1').value==""){
		alert("请输入密码！");
		return false;
	}
	if(document.getElementById('password2').value==""){
		alert("请输入确认密码！");
		return false;
	}
	if(document.getElementById('password1').value!=document.getElementById('password2').value){
		alert("密码与确认密码应该相同！");
		return false;
	}
	document.getElementById('password').value=document.getElementById('password1').value;
	return true;
}

</script>
	</head>
	<body
		style="border: 0px solid blue; margin: 0px; padding: 0px; overflow: hidden; float: left;">
		<div class="q4all">
			<div class="subnav">
				&nbsp;用户更新
			</div>
			<table class="main_table" border=0
				style="width: 100%; height: 100%; overflow: hidden;">
				<tr>
					<td width="*" valign="top">
						<div
							style="border: 0px solid black; * padding-bottom: 0px; overflow: hidden;">
							<form class="fm1" id="fm1" name="fm1"
								action="${ctx}/system/systemClient.do?method=save"
								method="post">
 				<input type="hidden" name="org.apache.struts.taglib.html.TOKEN"  value="${sessionScope['org.apache.struts.action.TOKEN']}" />
						<div id="main" style="width: 100%; display: block">
							<table border=0
								style="width: 100%; border: 0px; height: 20px; line-height: 25px; margin-bottom: 0px;"
								cellpadding="3" cellspacing="1">
								<tr valign="top">
									<td style="width: 90%;" align="center">
										<table border=0>
											<tr>
												<td>
													客户名称
												</td>
												<td>
													<input type="text" id="clientName" name="model.clientName"
														value="${item.clientName}" maxlength="250" size="25"
														datatype="s1-50" errormsg="请输入客户名称！" /><span style="color:red">*</span>
												</td>
												<td>
													<div class="Validform_checktip"></div>
												</td>
												<td>
													省份
												</td>
												<td>
													<select id="orgId" name="model.orgId" datatype="s1-50" errormsg="请输入省份！" >
													<option value="-1">----请选择---</option>
													<c:forEach var="item" items="${orgs}" varStatus="stat" >
													<option value="${item.id}">${item.orgName}</option>
													</c:forEach>
													</select><span style="color:red">*</span>
												</td>
												<td>
													<div class="Validform_checktip"></div>
												</td>
											</tr>
											<tr>
												<td>
													联系人&nbsp;&nbsp;&nbsp;
												</td>
												<td>
													<input type="text" id="contact" name="model.contact"
														value="${item.contact}" maxlength="50" size="25"
														datatype="s1-50" errormsg="请输入联系人！" /><span style="color:red">*</span>
												</td>
												<td>
													<div class="Validform_checktip"></div>
												</td>
												<td>
													联系电话
												</td>
												<td>
													<input type="text" id="contactPhone"
														name="model.contactPhone" value="${item.contactPhone}"
														maxlength="11" size="25" ignore="ignore" datatype="m"
														errormsg="请输入联系电话(11位手机号码)！" />
												</td>
												<td>
													<div class="Validform_checktip"></div>
												</td>
											</tr>
											<tr>
												<td>
													备用电话
												</td>
												<td>
													<input type="text" id="backupPhone"
														name="model.backupPhone" value="${item.backupPhone}"
														maxlength="11" size="25" ignore="ignore" datatype="m"
														errormsg="请输入备用电话(11位手机号码)！" />
												</td>
												<td>
													<div class="Validform_checktip"></div>
												</td>
												<td>
													主要负责人
												</td>
												<td>
													<input type="text" id="mainCharge" name="model.mainCharge"
														value="${item.mainCharge}" maxlength="50" size="25"
														ignore="ignore" datatype="s1-50" errormsg="请输入主要负责人！" />
												</td>
												<td>
													<div class="Validform_checktip"></div>
												</td>
											</tr>
											<tr>
												<td>
													主要负责人电话
												</td>
												<td>
													<input type="text" id="mainChargeContact"
														name="model.mainChargeContact"
														value="${item.mainChargeContact}" maxlength="50" size="25"
														ignore="ignore" datatype="s1-50" errormsg="请输入主要负责人电话！" />
												</td>
												<td>
													<div class="Validform_checktip"></div>
												</td>
												<td>
												</td>
											</tr>
										</table>
									</td>
								</tr>

								<tr>
									<td style="text-align: center; border-top: 1px solid #c9c9c9;">
										<input type="submit" class="btn_03s" value="保存" />
										<input type="button" class="btn_03s" value="返回"
											onclick="window.location='${ctx}/system/systemClient.do?method=list'" />
									</td>
								</tr>
							</table>

						</div>

						</form>
						<div style="clear: both"></div>
						</div>
						<div
							style="border: 0px solid red; padding-top: 0px; width: 100%; height: 100%;">
						</div>
					</td>
				</tr>
			</table>

			<script type="text/javascript">
	$(function() {
       $(".fm1").Validform();
    })
</script>
	</body>
</html>
