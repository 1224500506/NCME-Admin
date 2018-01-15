<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/commons/taglibs.jsp"%>
		<title>权限列表</title>
<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
</style>
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/prototype.js"></script>
		<style>
html {
	height: 100%;
}

body {
	margin: 0px;
	padding: 0px;
	height: 100%;
	font-size: 12px;
}

div#divshow {
	position: absolute;
	display: none;
	background-color: #FFFFFF;
	top: 30%;
	left: 10%;
	width: 400px;
	height: 70px;
	border: #036 solid;
	border-width: 1 1 3 1;
	z-index: 202;
}

div#popdiv {
	position: absolute;
	top: 0px;
	left: 0px;
	width: 100%;
	height: 100%;
	display: none;
	z-index: 10;
	background-color: #AAAAAA;
	filter: alpha(opacity =     80);
	opacity: 0.5;
}

span.pagebanner {
	width: 486px;
}

span.pagelinks {
	width: 486px;
}
</style>
		<script>
var uid='';
function showEditRole(id){
	uid=id;
	document.getElementById('divshow').style.display = 'block';
	document.getElementById('popdiv').style.display = 'block';
}
function subRole(){
	var url = '${ctx}/userManage/updateRole.do';
   var params = 'uid=' + uid + '&rid=' + document.getElementById('role').value;
   var doAjax = new Ajax.Request(
   url,
   {
      method : 'POST',
      parameters : params,
      onComplete : doResult
   }
   );
}
function doResult(originalRequest){
   var result = originalRequest.responseText;
   if(result == '200'){
      alert('权限修改成功');
      window.location = window.location;
   }
   else{
      alert('操作失败');
   }
}

function cancelEdit(){
	document.getElementById('divshow').style.display = 'none';
	document.getElementById('popdiv').style.display = 'none';
}
</script>
	</head>
	<body>
		<div>
			<center>
				<display:table name="users" requestURI="${ctx}/findUsers.do" style="font-size:12px;width: 95%;top:0px; margin:0px;padding:0px;" id="element" class="ITS">
					<display:column property="realName" title="真实姓名" style="text-align:center"></display:column>
					<display:column property="roleName" title="所属角色" style="text-align:center"></display:column>
					<display:column property="id" title="修改权限" style="text-align:center" decorator="com.hys.auth.displaytag.UpdatePrivilegeColumnWrapper"></display:column>
				</display:table>
			</center>
		</div>
		<div id="popdiv"></div>
		<div id="divshow">
			<center>
				<table border="0" style="width: 100px; border: 0px solid black;">
					<tr>
						<td>
							请选择权限：
						</td>
						<td>
							<select id="role">
								<c:forEach items="${roles}" var="role" varStatus="status">
									<option value="${role.id}">
										${role.nameDesc}
									</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<input type="button" id="sub_role" value="确定" onclick="subRole();" />
						</td>
						<td>
							<input type="button" id="sub_role" value="取消" onclick="cancelEdit();" />
						</td>
					</tr>
				</table>
			</center>
		</div>
	</body>
</html>
