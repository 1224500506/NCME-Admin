<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML>
<html>
	<head>
	<%@ include file="/commons/taglibs.jsp"%>
		<link href="${ctx}/css/auth.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/prototype.js"></script>
		<title>添加用户</title>
		<style>
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
	filter: alpha(opacity =   80);
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
	margin: 0px;
	padding: 0px;
	font-size: 12px;
	color: #000000;
	text-align: center;
	background-image: url(../images/06.gif);
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
	text-align: left;
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
		alert("请输入登录名！");
		return false;
	}
	if(document.getElementById('realName').value==""){
		alert("请输入真实姓名！");
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
	if(document.getElementById('roleId').value=="-1"){
		alert("请选择角色！");
		return false;
	}
	return true;
}

</script>
	</head>
	<body style="border: 0px solid blue; margin: 0px; padding: 0px; overflow: hidden; float: left;">
		<div class="q4all">
			<div class="subnav">
				&nbsp;当前位置：首页 > 添加用户
			</div>
			<table class="main_table" border=0 style="width: 100%; height: 100%; overflow: hidden;">
				<tr>
					<td width="*" valign="top">
						<div style="border: 0px solid black; * padding-bottom: 0px; overflow: hidden;">
							<form id="searchForm" action="${ctx}/userManage/addUsers.do" method="post" onsubmit="return formSubmit();" style="width: 100%; border: 0px solid green; margin: 0px; float: left; overflow: hidden;">
								<div id="main" style="display: block">
									<table border=0 style="width: 100%; border: 0px; height: 20px; line-height: 25px; margin-bottom: 0px;" cellpadding="3" cellspacing="1">
										<tr valign="top">
											<td style="text-align: right; width: 25%">
												<input type="hidden" value="780" id="orgIds" name="orgIds" />
												请输入登录名
												<input type="text" id="loginName" name="loginName" size="15" width="20" />
											</td>
										</tr>
										<tr >
											<td style="text-align: right; width: 25%">
												请输入真实姓名
												<input type="text" id="realName" name="realName" size="15" width="20" />
											</td>
										</tr>
										<tr>
											<td style="text-align: right; width: 25%">
												请输入密码
												<input type="password" id="password1" name="password1" size="15" width="20" />
												<input type="hidden" id="password" name="password" />
											</td>
										</tr>
										<tr>
											<td style="text-align: right; width: 25%">
												请再次输入密码
												<input type="password" id="password2" name="password2" size="15" width="20" />
											</td>
										</tr>
										<tr>
											<td style="text-align: right; width: 25%">
												角色
												<select name="roleId">
													<option value="-1" selected>请选择</option>
													<c:forEach items="${roles}" var="role">
														<option value="${role.id }">${role.nameDesc }</option>
													</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<td style="text-align: right; border-top: 1px solid #c9c9c9;">
												<input type="submit" value="添加" style="width: 60px; height: 24px; font-size: 12px;" id="submitBtn" />
											</td>
											<td style="border-top: 1px solid #c9c9c9;">
												&nbsp;
											</td>
										</tr>
									</table>
								</div>
							</form>
							<div style="clear: both"></div>
						</div>
						<div style="border: 0px solid red; padding-top: 0px; width: 100%; height: 100%;">
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div id="bgdiv" style="display: none;"></div>
	</body>
</html>

