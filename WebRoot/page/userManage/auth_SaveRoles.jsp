<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/commons/taglibs.jsp"%>
		<title>角色管理</title>
		<style>
html {
	height: 100%;
}

body {
	width: 100%;
	height: 100%;
	margin: 0px;
	padding: 0px;
	font-size: 12px;
	font-family: Verdana;
	background: ;
	filter: progid : DXImageTransform . Microsoft .
		Gradient(gradientType =   0, startColorStr =   #ffffff, endColorStr =  
		#003366);
}
</style>
		<script>
			function delOp(rid){
				if(confirm('确认要删除该角色吗？')){
					window.location.href = '${ctx}/security/deleteRole.do?rid=' + rid;
				}
			}
		</script>
	</head>
	<body>
		<div id="body">
			<div id="msg">
				<center>
					<span style="color: red;">${msg}</span>
				</center>
			</div>
			<div id="addrole">
				<center>
					<form action="${ctx}/security/saveRole.do">
						<table border=0>
							<tr>
								<td>
									角色名称：
								</td>
								<td>
									<input type="text" name="nameDesc" />
								</td>
								<td>
									角色标识：
								</td>
								<td>
									<input type="text" name="name" />
								</td>
								<td>
									<input type="submit" name="sub" value="添加角色" />
								</td>
							</tr>
						</table>
					</form>
				</center>
			</div>
			<div style="height: 50px;"></div>
			<div id="listrole">
				<center>
					<table border="1" style="width: 600px; border: #035 solid;">
						<thead>
							<tr>
								<td>
									序号
								</td>
								<td>
									角色名称
								</td>
								<td>
									角色标识
								</td>
								<td>
									操作
								</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${roles}" var="role" varStatus="status">
								<tr>
									<td>
										${status.index+1}
									</td>
									<td>
										${role.nameDesc}
									</td>
									<td>
										${role.name}
									</td>
									<td>
										<a href="javascript:delOp('${role.id}')">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</center>
			</div>
			<div id="foot">
				<center>
					<span style="color: red;">注意：这是角色，请勿随意修改！</span>
				</center>
			</div>
		</div>
	</body>
</html>
