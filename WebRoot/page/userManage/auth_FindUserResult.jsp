<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<%@ include file="/commons/taglibs.jsp"%>
		<title>查询结果</title>
<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
</style>
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/prototype.js"></script>
		<style>

}
</style>
		<script type="text/javascript">
function deleteUser(id){
if(confirm('确认删除该用户吗?')){
   var url = '${ctx}/userManage/deleteUser.do';
   var params = 'id=' + id;
   var doAjax = new Ajax.Request(
   url,
   {
      method : 'POST',
      parameters : params,
      onComplete : doResult
   }
   );
   }
}

function doResult(originalRequest){
   var result = originalRequest.responseText;
   if(result == '1'){
      window.location = window.location;
   }else{
      alert('操作失败');
   }
}
</script>
	</head>
	<body bgcolor="#E7E7E7">
		<div style="margin: auto; text-align: right;">
			<center>
				<display:table name="testList" requestURI="${ctx}/userManage/getUsers.do" style="font-size:12px;width:95%;" id="element" class="ITS">
					<display:caption>用户查询结果</display:caption>
					<display:column property="realName" title="真实姓名" style="text-align:center"/>
					<display:column property="loginName" title="登录名" style="text-align:center"/>
					<display:column property="password" title="密码" style="text-align:center"/>
					<display:column property="id" title="更新" decorator="com.hys.auth.displaytag.UpdateUserWrapper" media="html" style="text-align:center"/>
					<display:column property="id" title="删除" decorator="com.hys.auth.displaytag.DeleteUserWrapper" media="html" style="text-align:center"/>
				</display:table>
			</center>
		</div>
	</body>
</html>
