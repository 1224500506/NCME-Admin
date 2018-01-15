<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
		<title>更新用户</title>
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
	</head>
	<body
		style="border: 0px solid blue; margin: 0px; padding: 0px; overflow: hidden; float: left;">
		<div class="q4all">
			<div class="subnav">
				&nbsp;站点更新
			</div>
			<table class="main_table" border=0
				style="width: 100%; height: 100%; overflow: hidden;">
				<tr>
					<td width="*" valign="top">
						<div
							style="border: 0px solid black; * padding-bottom: 0px; overflow: hidden;">
							<form class="fm1" id="fm1" name="fm1"
								action="${ctx}/system/systemSite.do?method=update"
								method="post">
						<input type="hidden" id="id" name="model.id" value="${item.id}" />


						<div id="main" style="width: 100%; display: block">
							<table border=0
								style="width: 100%; border: 0px; height: 20px; line-height: 25px; margin-bottom: 0px;"
								cellpadding="3" cellspacing="1">
								<tr valign="top">
									<td style="width: 90%;" align="center">
										<table border=0>
											<tr>
												<td>
													站点域名:
												</td>
												<td>
													<input type="text" id="domainName" name="model.domainName"
														value="${item.domainName}" maxlength="250" size="25"
														 datatype="s1-250" errormsg="请输入站点域名！" /><span style="color:red">*</span>
												</td>
												<td>
													<div class="Validform_checktip"></div>
												</td>
												<td>
													所属客户:
												</td>
												<td>
													<select id="clientId" name="model.clientId" datatype="n" errormsg="请输入所属客户！" >
													<option value="-1">----请选择---</option>
													<c:forEach var="item" items="${clients}" varStatus="stat" >
													<option value="${item.id}">${item.clientName}</option>
													</c:forEach>
													</select><span style="color:red">*</span>
												</td>
												<td>
													<div class="Validform_checktip"></div>
												</td>
											</tr>
											<tr>
												<td>
													所属应用:
												</td>
												<td>
													<select id="applicationId" name="model.applicationId" datatype="n" errormsg="请输入所属应用！" >
													<option value="-1">----请选择---</option>
													<c:forEach var="item" items="${applications}" varStatus="stat" >
													<option value="${item.id}">${item.applicationName}</option>
													</c:forEach>
													</select><span style="color:red">*</span>
												</td>
												<td>
													<div class="Validform_checktip"></div>
												</td>
												<td>
													注册角色:
												</td>
												<td>
													<select id="roleId" name="model.roleId" datatype="n" errormsg="请输入注册角色！" >
													<option value="-1">----请选择---</option>
													<c:forEach var="item" items="${roles}" varStatus="stat" >
													<option value="${item.roleId}">${item.roleName}</option>
													</c:forEach>
													</select><span style="color:red">*</span>
												</td>
												<td>
													<div class="Validform_checktip"></div>
												</td>
											</tr>
											<tr>
												<td>
													备注:
												</td>
												<td>
													<input type="text" id="remark"
														name="model.remark" value="${item.remark}"
														maxlength="11" size="25" ignore="ignore" datatype="*"
														errormsg="请输入备注！" />
												</td>
												<td>
													<div class="Validform_checktip"></div>
												</td>
											</tr>
										</table>
									</td>
								</tr>

								<tr>
									<td style="text-align: center; border-top: 1px solid #c9c9c9;">
										<input type="submit" class="btn_01s" value="" />
										<input type="button" class="btn_03s" class="btn_02s"
											onclick="window.location='${ctx}/system/systemSite.do?method=list'" />
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
		 //select 选中已选项
		  $("#clientId ").val(${item.roleId});   
		  $("#applicationId ").val(${item.applicationId});   
		  $("#roleId ").val(${item.roleId});   
	  </script> 
	</body>
</html>
