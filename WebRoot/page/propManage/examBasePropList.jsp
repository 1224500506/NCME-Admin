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
				<c:when test="${type eq '7'}"><a href="javascript:addPropShow();">添加行业</a></c:when>
				<c:when test="${type eq '8'}"><a href="javascript:addPropShow();">添加知识分类</a></c:when>
				<c:when test="${type eq '9'}"><a href="javascript:addPropShow();">添加适用对象</a></c:when>
			</c:choose>
			<a href="${ctx }/page/propManage/propManage.jsp">返回上一级</a>
		</td>
	</tr>
	<tr>
		<td>
			<display:table name="propList" id="p" style="width:100%;" class="its">
				<display:column title="ID" style="width:80px;text-align:center" property="id"></display:column>
				<display:column title="名称" style="width:180px;" property="name"></display:column>
				<display:column title="类型" style="width:100px;text-align:center" property="typeName"></display:column>
				<display:column title="编码" style="width:100px;text-align:center" property="code"></display:column>
				<display:column title="操作" style="width:200px;text-align:center">
					<a href="javascript:editPropShow('${p.id}','${p.name}','${p.code}');">修改</a> || 
					<a href="javascript:doDelProp(${p.id},${p.prop_val_id});">删除</a>				
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
					<input type="hidden" id="rsType"  value="${type}" />
					<span style="color: red; padding-left: 5px;">*</span>
				</li>
				<li>
					属性编码：
					<input type="text" id="rsCode" size="40"/>
					<input type="hidden" id="rsProp_val1" value="0" />
				</li>
			</ul>
			<center>
				<input type="button" name="saverr" value="保存" onclick="doAddProp();" />
				<input type="button" name="saverr" value="取消" onclick="document.getElementById('addProp').style.display='none';document.getElementById('bgdiv').style.display='none';" />
			</center>
		</div>
		
		<div id="editProp" style="border: #036 solid; padding-top: 30px; width: 500px; height: 200px; position: absolute; left: 450px; top: 150px; background-color: #FFFFFF; display: none; z-index: 20;">
			<ul>
				<li>
					属性名称：
					<input type="text" id="esName" size="40"/>
					<span style="color: red; padding-left: 5px;">*</span>
				</li>
				<li>
					属性编码：
					<input type="text" id="esCode" size="40"/>
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
function addPropShow(){
	document.getElementById('addProp').style.display='block';
	document.getElementById('bgdiv').style.display='block';
}

function editPropShow(_id,_name,_code){
	document.getElementById('editProp').style.display='block';
	document.getElementById('bgdiv').style.display='block';
	$('esName').value=_name;
	$('esCode').value=_code;
	$('esId').value = _id;
}

function hiddenPropShow(){
	document.getElementById('editProp').style.display='none';
	document.getElementById('bgdiv').style.display='none';
	$('esName').value='';
	$('esCode').value='';
	$('esId').value = '';
}

function doEditProp(){
	var url = '${ctx}/propManage/editProp.do';
	var _name = document.getElementById('esName').value;
	var _code = document.getElementById('esCode').value;
	var _id = document.getElementById('esId').value;
	
	if(_id==''){
		return;
	}
   
	if(_name.getBytes()>200 || _name.getBytes()==0){
		alert('名称不能为空且不能大于200个字符或者100个汉字!');
		return;
	}
	if(_code.getBytes()>200){
		alert('编码不能不能大于200个字符!');
		return;
	}
	var params = 'propName=' + _name;
	params += '&code='+_code;
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

function doDelProp(id,prop_val_id){
	var url = '${ctx}/propManage/delProp.do';
	if(id!='' && prop_val_id!=''){
		var params = 'id=' +id;
		params +='&prop_val_id='+prop_val_id
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
	
   var url = '${ctx}/propManage/addProp.do';
   var _name = document.getElementById('rsName').value;
   var _code = document.getElementById('rsCode').value;
   var _type = document.getElementById('rsType').value;
   var _Prop_val1 = document.getElementById('rsProp_val1').value;
   
  if(_name.getBytes()>200 || _name.getBytes()==0){
	 alert('名称不能为空且不能大于200个字符或者100个汉字!');
	 return;
  }
  
  if(_code.getBytes()>200){
	 alert('编码不能不能大于200个字符!');
	 return;
  }
   
   var params = 'propName=' + _name;
   params += '&code='+_code;
   params += '&type='+_type;
   params += '&prop_val1='+_Prop_val1;
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