<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/commons/taglibs.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>系统管理主页面</title>
		<link href="${ctx}/css/auth.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
<!--
body{
	margin: 0 !important;
}
.STYLE1 {
	font-size: 14px;
	color: #FFFFFF;
	font-weight: bold;
}
-->
</style>
	<script type="text/javascript">

function privilege(){
	parent.mainframe.location = '${ctx}/userManage/findUsers.do?t=' + Math.random();
}


function roleOperation(){
	parent.mainframe.location = '${ctx}/security/preSaveRole.do?t=' + Math.random();
}

function ResourceAdd(){
	parent.mainframe.location = '${ctx}/page/userManage/auth_AddResource.jsp?t=' + Math.random();
}

function ResourceRelation(){
	parent.mainframe.location = '${ctx}/security/findResources.do?t=' + Math.random();
}

function RefreshResource() {
	parent.mainframe.location = '${ctx}/page/userManage/auth_RefreshResources.jsp?t=' + Math.random();
}

//用户管理使用
function findUser(){
	parent.mainframe.location = '${ctx}/userManage/getUsers.do?t=' + Math.random();
}
function addUser(){
	parent.mainframe.location = '${ctx}/userManage/preSaveUser.do?t=' + Math.random();
}	
	
function examPropValFile(sid,t){
	parent.mainframe.location = '${ctx}/security/examPropValFile.do?t=' + Math.random()+"&sid="+sid+"&type="+t;
}		
    </script>
	</head>
	<center>
		<body>
			<div class="q4all">
				<div class="subnav">
					&nbsp;&nbsp;当前位置：&gt; 系统管理
				</div>
				<div class="q3_1"></div>
				<div class="q3_2">
						<table width="50%" border="0" cellspacing="1" cellpadding="1">
							<tr>
								<td align="center" style="font-size: 18px;font-weight: bold;">
									<ul>
										<security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_MANAGE">
											
											<li>
												<a href="javascript:findUser();">用户查询</a>
											</li>
											<li>
												<a href="javascript:addUser();">用户添加</a>
											</li>
											
											<li>
												<a href="javascript:privilege();">权限管理</a>
											</li>
											</security:authorize>
											
											<security:authorize ifAllGranted="ROLE_ADMIN">
											<li>
												<a href="javascript:roleOperation();">角色管理</a>
											</li>
											<li>
												<a href="javascript:ResourceAdd();">资源添加</a>
											</li>
											<li>
												<a href="javascript:ResourceRelation();">资源关联</a>
											</li>
											<li>
												<a href="javascript:RefreshResource();">资源刷新</a>
											</li>
											<li>
												<a href="javascript:examPropValFile(0,1);">生成试题所有属性文件</a>
											</li>
											<li>
												&nbsp;
											</li>											
											<li>
												&nbsp;
											</li>		
											<li>
												&nbsp;
											</li>																					
										</security:authorize>
										</ul>
								</td>
							</tr>
						</table>
				</div>
				<div class="q3_3"></div>
			</div>
		</body>
	</center>
</html>