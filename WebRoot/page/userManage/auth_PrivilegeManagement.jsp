<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>权限管理</title>
		<%@ include file="/commons/taglibs.jsp"%>
		<style>
html {
	height: 100%;
}

body {
	margin: 0px;
	padding: 0px;
	height: 100%;
	font-size: 12px;
	overflow: hidden
}
</style>
	</head>
	<body>
		<div class="q3all">
			<div class="subnav">
				&nbsp;当前位置：系统&nbsp;>&nbsp;权限管理
			</div>
			<center>
				<form action="${ctx }/userManage/findUsers.do" target="privilegeFrame">
					<table border="0" style="width: 500px; border: 0px solid black;">
						<tr>
							<td>
								查找用户名：
							</td>
							<td>
								<input type="text" name="loginName" />
							</td>
							<td>
								<input type="submit" value="查找" />
							</td>
						</tr>
					</table>
				</form>
				<iframe name="privilegeFrame" style="width: 520px; height: 430px;" frameborder=0 src=""></iframe>
			</center>
		</div>
	</body>
</html>
