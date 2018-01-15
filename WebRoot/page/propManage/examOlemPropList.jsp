<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/prototype.js"></script>
<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
	
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
	filter: alpha(opacity = 80);
	opacity: 0.8;
}	
</style>
</head>
<body>
<table cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td>
			<c:choose>
				<c:when test="${olemPropType eq '1'}"><a href="javascript:addPropShow('${olemPropType}');">添加专业</a></c:when>
				<c:when test="${olemPropType eq '2'}">
					<a href="javascript:addPropShow('2');">添加基础理论</a> ||
					<a href="javascript:addPropShow('3');">添加基本知识</a> ||
					<a href="javascript:addPropShow('4');">添加基本技能</a>
				</c:when>
				<c:when test="${olemPropType eq '5'}"><a href="javascript:addPropShow('${olemPropType}');">添加学科</a></c:when>	
				<c:when test="${olemPropType eq '6'}"><a href="javascript:addPropShow('${olemPropType}');">添加知识单元</a></c:when>
				<c:when test="${olemPropType eq '7'}"><a href="javascript:addPropShow('${olemPropType}');">添加知识点</a></c:when>	
				<c:when test="${olemPropType eq '8'}"><a href="javascript:addPropShow('${olemPropType}');">添加科室</a></c:when>
				<c:when test="${olemPropType eq '9'}"><a href="javascript:addPropShow('${olemPropType}');">添加二级科室</a></c:when>
				<c:when test="${olemPropType eq '10'}"><a href="javascript:addPropShow('${olemPropType}');">添加知识点</a></c:when>
				<c:when test="${olemPropType eq '11'}"><a href="javascript:addPropShow('${olemPropType}');">添加考察内容</a></c:when>
				<c:when test="${olemPropType eq '12'}"><a href="javascript:addPropShow('${olemPropType}');">添加知识点</a></c:when>	
			</c:choose>
			
			|| <a href="javascript:history.go(-1)">返回</a> 
		</td>
	</tr>
	<tr>
		<td>
			<display:table name="olemProplist" requestURI="${ctx}/propManage/olemPropList.do" id="p" style="width:100%;" class="its">
				<display:column title="ID" style="width:40px;text-align:center" property="id"></display:column>
				<display:column title="名称" style="width:120px;" property="olem_prop_name"></display:column>
				<display:column title="类型" style="width:80px;text-align:center">
					<c:choose>
						<c:when test="${p.olem_prop_type eq '1'}">专业</c:when>
						<c:when test="${p.olem_prop_type eq '2'}">基础理论</c:when>
						<c:when test="${p.olem_prop_type eq '3'}">基本知识</c:when>	
						<c:when test="${p.olem_prop_type eq '4'}">基本技能</c:when>	
						<c:when test="${p.olem_prop_type eq '5'}">学科</c:when>	
						<c:when test="${p.olem_prop_type eq '6'}">知识单元</c:when>
						<c:when test="${p.olem_prop_type eq '7'}">知识点</c:when>	
						<c:when test="${p.olem_prop_type eq '8'}">科室</c:when>
						<c:when test="${p.olem_prop_type eq '9'}">二级科室</c:when>
						<c:when test="${p.olem_prop_type eq '10'}">知识点</c:when>
						<c:when test="${p.olem_prop_type eq '11'}">考察内容</c:when>
						<c:when test="${p.olem_prop_type eq '12'}">知识点</c:when>	
					</c:choose>				
				</display:column>
				<display:column title="操作" style="width:200px;text-align:center">
					<a href="javascript:editPropShow('${p.id}','${p.olem_prop_name}');">修改</a> || 
					<a href="javascript:doDelProp(${p.id});">删除</a>				
					<c:choose>
						<c:when test="${p.olem_prop_type eq '1'}">|| <a href="${ctx}/propManage/olemPropList.do?id=${p.id}&type=2">考察方向列表</a></c:when>
						<c:when test="${p.olem_prop_type eq '2'}">|| <a href="${ctx}/propManage/olemPropList.do?id=${p.id}&type=5">学科列表</a> || <a href="${ctx}/page/propManage/examImportOlemProp.jsp?id=${p.id}&type=${p.olem_prop_type}">导入属性</a></c:when>
						<c:when test="${p.olem_prop_type eq '3'}">|| <a href="${ctx}/propManage/olemPropList.do?id=${p.id}&type=8">科室列表</a> || <a href="${ctx}/page/propManage/examImportOlemProp.jsp?id=${p.id}&type=${p.olem_prop_type}">导入属性</a></c:when>
						<c:when test="${p.olem_prop_type eq '4'}">|| <a href="${ctx}/propManage/olemPropList.do?id=${p.id}&type=11">考察内容列表</a> || <a href="${ctx}/page/propManage/examImportOlemProp.jsp?id=${p.id}&type=${p.olem_prop_type}">导入属性</a></c:when>
						<c:when test="${p.olem_prop_type eq '5'}">|| <a href="${ctx}/propManage/olemPropList.do?id=${p.id}&type=6">知识单元列表</a></c:when>
						<c:when test="${p.olem_prop_type eq '6'}">|| <a href="${ctx}/propManage/olemPropList.do?id=${p.id}&type=7">知识点列表</a></c:when>
						<c:when test="${p.olem_prop_type eq '7'}">|| <a href="${ctx}/propManage/olemBasePropList.do?olemPropId=${p.id}">基本属性列表</a></c:when>
						<c:when test="${p.olem_prop_type eq '8'}">|| <a href="${ctx}/propManage/olemPropList.do?id=${p.id}&type=9">二级科室列表</a></c:when>
						<c:when test="${p.olem_prop_type eq '9'}">|| <a href="${ctx}/propManage/olemPropList.do?id=${p.id}&type=10">知识点列表</a></c:when>
						<c:when test="${p.olem_prop_type eq '10'}">|| <a href="${ctx}/propManage/olemBasePropList.do?olemPropId=${p.id}">基本属性列表</a></c:when>
						<c:when test="${p.olem_prop_type eq '11'}">|| <a href="${ctx}/propManage/olemPropList.do?id=${p.id}&type=12">知识点列表</a></c:when>
						<c:when test="${p.olem_prop_type eq '12'}">|| <a href="${ctx}/propManage/olemBasePropList.do?olemPropId=${p.id}">基本属性列表</a>||</c:when>
					</c:choose>
				</display:column>
			</display:table>
		</td>
	</tr>
</table>
		<div id="addProp" style="border: #036 solid; padding-top: 30px; width: 500px; height: 200px; position: absolute; left: 450px; top: 150px; background-color: #FFFFFF; display: none; z-index: 20;">
			<ul>
				<li>
					属性名称：
					<input type="text" id="rsName" size="40"/>
					<input type="hidden" id="rsType" />
					<input type="hidden" id="rsPropParentId" value="${olemPropParentId}" />
					<span style="color: red; padding-left: 5px;">*</span>
				</li>
			</ul>
			<center>
				<input type="button" name="saverr" value="保存" onclick="doAddProp();" />
				<input type="button" name="saverr" value="取消" onclick="hiddenaddPropShow()" />
			</center>
		</div>
		
		<div id="editProp" style="border: #036 solid; padding-top: 30px; width: 500px; height: 200px; position: absolute; left: 450px; top: 150px; background-color: #FFFFFF; display: none; z-index: 20;">
			<ul>
				<li>
					属性名称：
					<input type="text" id="esName" size="40"/>
					<span style="color: red; padding-left: 5px;">*</span>
					<input type="hidden" id="esId" />
				</li>
			</ul>
			<center>
				<input type="button" name="saverr" value="保存" onclick="doEditProp();" />
				<input type="button" name="saverr" value="取消" onclick="hiddenPropShow();" />
			</center>
		</div>		
		
		<div id="bgdiv" style="display: none;"></div>
<script type="text/javascript">
function addPropShow(_type){
	document.getElementById('addProp').style.display='block';
	document.getElementById('bgdiv').style.display='block';
	document.getElementById('rsType').value=_type;
}

function hiddenaddPropShow(){
	document.getElementById('addProp').style.display='none';
	document.getElementById('bgdiv').style.display='none';
	document.getElementById('rsType').value='';
	document.getElementById('rsName').value='';
}

function editPropShow(_id,_name){
	document.getElementById('editProp').style.display='block';
	document.getElementById('bgdiv').style.display='block';
	$('esName').value=_name;
	$('esId').value = _id;
}

function hiddenPropShow(){
	document.getElementById('editProp').style.display='none';
	document.getElementById('bgdiv').style.display='none';
	$('esName').value='';
	$('esId').value = '';
}

function doEditProp(){
	var url = '${ctx}/propManage/editOlemProp.do';
	var _name = document.getElementById('esName').value;
	var _id = document.getElementById('esId').value;
	
	if(_id==''){
		return;
	}
   
	if(_name.getBytes()>200 || _name.getBytes()==0){
		alert('名称不能为空且不能大于200个字符或者100个汉字!');
		return;
	}

	var params = 'olem_prop_name=' + _name;
	params += '&id='+_id;
	var doAjax = new Ajax.Request(
	url,
	{
		method : 'POST',
		parameters : params,
		onComplete : doEditResourceResult
	}
	);
}

function doEditResourceResult(originalRequest){
	var result = originalRequest.responseText;
   if(result == 'success'){
      alert('修改成功！');
      window.location = window.location;
   }else{
   		alert('修改失败!');
   }
}

function doDelProp(id){
	var url = '${ctx}/propManage/delOlemProp.do';
	if(id!=''){
		var params = 'id=' +id;
	}
	var doAjax = new Ajax.Request(
    url,
    {
      method : 'POST',
      parameters : params,
      onComplete : doDelResourceResult
    }
    );
}

function doDelResourceResult(originalRequest){
	var result = originalRequest.responseText;
   if(result == 'success'){
      alert('删除成功！');
      window.location = window.location;
   }else{
   		alert('删除失败请检查属性的关联!');
   }
}

function doAddProp(){
	
   var url = '${ctx}/propManage/olemAddProp.do';
   var _name = document.getElementById('rsName').value;
   var _type = document.getElementById('rsType').value;
   var _PropParentId = document.getElementById('rsPropParentId').value;
   
  if(_name.getBytes()>200 || _name.getBytes()==0){
	 alert('名称不能为空且不能大于200个字符或者100个汉字!');
	 return;
  }
  
   var params = 'olem_prop_name=' + _name;
   params += '&parent_id='+_PropParentId;
   params += '&olem_prop_type='+_type;
   var doAjax = new Ajax.Request(
   url,
   {
      method : 'POST',
      parameters : params,
      onComplete : doAddResourceResult
   }
   );
}

function doAddResourceResult(originalRequest){
   var result = originalRequest.responseText;
   if(result == 'success'){
      alert('添加成功！');
      window.location = window.location;
   }else{
   		alert('添加失败');
   }
}
</script>		
</body>
</html>