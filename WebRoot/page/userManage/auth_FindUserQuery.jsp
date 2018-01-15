<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML>
<html>
	<head>
	<%@ include file="/commons/taglibs.jsp"%>
		<link href="${ctx}/css/olatStyle.css" type=text/css rel=stylesheet>
		<script type="text/javascript" src="${ctx}/js/prototype.js"></script>
		<title>用户查询</title>
		<style>
#organization {
	width: 100%;
	height: 100%;
	overflow: auto;
}

#list_div {
	width: 95%;
	height: 95%;
	padding: 8px;
	overflow: auto;
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
</script>
	</head>
	<body style="border: 0px solid blue; margin: 0px; padding: 0px; overflow: hidden; float: left;">
		<div class="q4all">
			<div class="subnav">
				&nbsp;当前位置：首页 > 用户查询
			</div>
			<table class="main_table" border=0 style="width: 100%; height: 100%; overflow: hidden;">
				<tr>
					<td width="*" valign="top">
						<div style="border: 0px solid black; width: 100%; * padding-bottom: 0px; overflow: hidden;">
							<form id="searchForm" action="${ctx}/userManage/getUsers.do" method="post" target="listframe" onsubmit="" style="width: 55%; border: 0px solid green; margin: 0px; float: left; overflow: hidden;">
								<div id="main" style="border: 0px solid red; width: 100%;">
									<table border=0 style="width: 100%; border: 0px; height: 20px; line-height: 25px; margin-bottom: 0px; border-bottom: 1px solid #c9c9c9;" cellpadding="3" cellspacing="1">
										<tr valign="top">
											<td style="text-align: right; width: 25%">
												<input type="hidden" value="780" id="orgIds" name="orgIds" />
												请输入登录名
											</td>
											<td style="width: 15%">
												<input type="text" id="cvalue" name="cvalue" />
											</td>
											<td style="text-align: left;">
												<input type="submit" value="查询" style="width: 60px; height: 24px; font-size: 12px;" id="submitBtn" />
											</td>
										</tr>
									</table>
								</div>
							</form>
							<div style="clear: both"></div>
						</div>
						<div style="border: 0px solid red; padding-top: 0px; width: 100%; height: 91%;">
							<iframe id="listframe" name="listframe" marginWidth=0 marginHeight=0 frameborder="0" scrolling="auto" style="width: 100%; height: 99%;" src=""></iframe>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>