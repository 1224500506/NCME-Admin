<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<%@ include file="/commons/taglibs.jsp"%>
		<title>角色资源关联</title>
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

BODY {
	WIDTH: 100%;
	HEIGHT: 100%;
	SCROLLBAR-FACE-COLOR: #e8e7e7;
	SCROLLBAR-HIGHLIGHT-COLOR: #ffffff;
	SCROLLBAR-SHADOW-COLOR: #ffffff;
	SCROLLBAR-3DLIGHT-COLOR: #cccccc;
	SCROLLBAR-ARROW-COLOR: #03B7EC;
	SCROLLBAR-TRACK-COLOR: #EFEFEF;
	SCROLLBAR-DARKSHADOW-COLOR: #b2b2b2;
	SCROLLBAR-BASE-COLOR: #000000;
	margin: 0px;
	overflow: auto;
	float: left;
}

ul li {
	list-style-type: none;
}

th,td {
	height: 20px;
	vertical-align: middle;
}

#bgdiv {
	position: absolute;
	top: 0px;
	left: 0px;
	width: 100%;
	height: 100%;
	background-color: #AAAAAA;
	z-index: 10;
	filter: alpha(opacity =             80);
	opacity: 0.8;
}
</style>
		<script type="text/javascript">
function getRoleIds(){
	var boxes = document.getElementsByName('role');
	var k = 0;
	var arr = new Array();
	for(var i = 0;i<boxes.length;i++){
		if(boxes[i].checked){
			arr[k++] = boxes[i].value;
		}
	}
	return arr.join();
}

function doRelate(){
	if(confirm('确认关联维护吗?')){
   var url = '${ctx}/security/relate.do';
   var id = document.getElementById('hiddenid').value;
   var params = 'resourceId=' + id + '&roleIds=' + getRoleIds();
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
   if(result == 'success'){
      window.location = window.location;
      alert('修改成功！');
   }else{
   		alert(result);
   		document.getElementById('editrole').style.display='none';
   }
}

function showRelate(id){
	var boxes = document.getElementsByName('role');
	for(var i = 0;i<boxes.length;i++){
				document.getElementsByName('role')[i].checked=false;
	}
	document.getElementById('hiddenid').value=id;
	var url = '${ctx}/security/getRoles.do';
   var params = 'rid=' + id;
   var doAjax = new Ajax.Request(
   url,
   {
      method : 'POST',
      parameters : params,
      onComplete : doShow
   }
   );
}
function doShow(originalRequest){
	var result = originalRequest.responseText;
	var idarr = result.split(',');
	var boxes = document.getElementsByName('role');
	for(var i = 0;i<boxes.length;i++){
		for(var j=0;j<idarr.length;j++){
			if(idarr[j]==boxes[i].value){
				document.getElementsByName('role')[i].checked=true;
			}
		}
		
	}
	document.getElementById('editrole').style.display='block';
	document.getElementById('bgdiv').style.display='block';
}

function doShowResource(id){
   var url = '${ctx}/security/getResource.do';
   var params = 'resourceId=' + id;
   var doAjax = new Ajax.Request(
   url,
   {
      method : 'POST',
      parameters : params,
      onComplete : doShowResourceResult
   }
   );
}

function doShowResourceResult(originalRequest){
   var result = originalRequest.responseText;
   if(result != ''){
      doResourceSplit(result);
      document.getElementById('editResource').style.display='block';
      document.getElementById('bgdiv').style.display='block';
   }else{
   		alert(result);
   }
}

function doResourceSplit(str){
	var arr = str.split(',');
	document.getElementById('rsId').value=arr[0];
	document.getElementById('rsValue').value=arr[1];
	document.getElementById('rsType').value=arr[2];
	document.getElementById('rsName').value=arr[3];
}

function doUpdateResource(){
   var url = '${ctx}/security/updateResource.do';
   var id = document.getElementById('rsId').value;
   var value = document.getElementById('rsValue').value;
   var type = document.getElementById('rsType').value;
   var name = document.getElementById('rsName').value;
   var params = 'rsId=' + id;
   params += '&rsValue='+value;
   params += '&rsType='+type;
   params += '&rsName='+name;
   var doAjax = new Ajax.Request(
   url,
   {
      method : 'POST',
      parameters : params,
      onComplete : doUpdateResourceResult
   }
   );
}

function doUpdateResourceResult(originalRequest){
   var result = originalRequest.responseText;
   if(result == 'success'){
      alert('资源修改成功！');
      window.location = window.location;
   }else{
   		alert(result);
   }
}

function doDeleteResource(id){
	if(confirm('确认要删除吗？')){
	   var url = '${ctx}/security/deleteResource.do';
	   var params = 'id=' + id;
	   var doAjax = new Ajax.Request(
	   url,
	   {
	      method : 'POST',
	      parameters : params,
	      onComplete : doDeleteResourceResult
	   }
	   );
   }
}

function doDeleteResourceResult(originalRequest){
   var result = originalRequest.responseText;
   if(result == 'success'){
      alert('资源删除成功！');
      window.location = window.location;
   }else{
   		alert(result);
   }
}
</script>
	</head>
	<body>
		<div style="border: 1px solid white; margin: 0px; overflow: auto;">
			<display:table name="resourceList" requestURI="${ctx}/security/findResources.do" style="font-size:12px;width:98%;" id="element" class="ITS" decorator="com.hys.auth.displaytag.RelateTableDecoratorWrapper">
				<display:caption style="font-size: 18px;">资源角色关联</display:caption>
				<display:column property="value" title="资源链接" style="width: 40%;text-align: left;" />
				<display:column property="name" title="资源名称" style="width: 20%;text-align:center" />
				<display:column property="type" title="资源类型" style="width: 20%;text-align:center" />
				<display:column property="rsOperation" title="操作" style="width: 10%;text-align:center" />
			</display:table>
		</div>
		<div style="text-align: center; margin-top: 20px;">
			<span style="color: red; font-family: Verdana; font-size: 12px;">注意：操作完后，必须要进行刷新，否则不会及时奏效，请点击<a href="${ctx}/page/userManage/auth_RefreshResources.jsp">刷新</a> </span>
		</div>
		<div id="editrole" style="text-align:center;border: #036 solid; padding: 20px 0px 0px 0px; width: 350px; height: 200px; position: absolute; left: 450px; top: 150px; background-color: #FFFFFF; display: none; z-index: 20;">
			<table border=0 style="margin: auto; border: 0;">
				<c:forEach items="${roles}" var="role">
					<tr>
						<td>
							<input type="checkbox" name="role" value="${role.id}" />
						</td>
						<td>
							${role.name}
						</td>
						<td>
							${role.nameDesc}
						</td>
					</tr>
				</c:forEach>
			</table>
			<input type="hidden" id="hiddenid" value="" />
			<center>
				<input type="button" name="saverr" value="保存" onclick="doRelate()" />
				<input type="button" name="saverr" value="取消" onclick="document.getElementById('editrole').style.display='none';document.getElementById('bgdiv').style.display='none';" />
			</center>
		</div>
		<div id="editResource" style="border: #036 solid; padding-top: 30px; width: 500px; height: 200px; position: absolute; left: 450px; top: 150px; background-color: #FFFFFF; display: none; z-index: 20;">
			<ul>
				<li>
					资源链接：
					<input type="text" id="rsValue" value="" size="50" />
					<span style="color: red; padding-left: 5px;">*</span>
				</li>
				<li>
					资源类型：
					<input type="text" id="rsType" value="" />
				</li>
				<li>
					资源名称：
					<input type="text" id="rsName" value="" />
				</li>
			</ul>
			<input type="hidden" id="rsId" value="" />
			<center>
				<input type="button" name="saverr" value="保存" onclick="doUpdateResource()" />
				<input type="button" name="saverr" value="取消" onclick="document.getElementById('editResource').style.display='none';document.getElementById('bgdiv').style.display='none';" />
			</center>
		</div>
		<div id="bgdiv" style="display: none;"></div>
	</body>
</html>
