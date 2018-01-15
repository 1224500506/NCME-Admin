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
				<c:when test="${type eq '1'}"><a href="javascript:addPropShow();">添加一级学科</a></c:when>
				<c:when test="${type eq '2'}"><a href="javascript:addPropShow();">添加二级学科</a></c:when>
				<c:when test="${type eq '3'}"><a href="javascript:addPropShow();">添加三级学科</a></c:when>
				<c:when test="${type eq '4'}"><a href="javascript:addPropShow();">添加单元</a></c:when>
				<c:when test="${type eq '5'}"><a href="javascript:addPropShow();">添加知识点</a></c:when>
			</c:choose>
			|| <a href="javascript:history.go(-1)">返回</a> 
		</td>
	</tr>
	<tr>
		<td>
			<display:table name="propList" requestURI="${ctx}/propManage/propList.do" id="p" style="width:100%;" class="its">
				<display:column title="ID" style="width:40px;text-align:center" property="id"></display:column>
				<display:column title="名称" style="width:120px;" property="name"></display:column>
				<display:column title="类型" style="width:80px;text-align:center" property="typeName"></display:column>
				<display:column title="所属系统" style="width:100px;text-align:center" property="c_type_name"></display:column>
				<display:column title="试题数" style="width:100px;text-align:center" property="questionNum"></display:column>
				<display:column title="操作" style="width:260px;text-align:center">
					<a href="javascript:editPropShow('${p.id}','${p.name}','${p.code}');">修改</a> || 
					<a href="javascript:doDelProp(${p.id},${p.prop_val_id});">删除</a>
					<c:if test="${p.type>2}">
						||  <a href="javascript:movePropShow('${p.prop_val_id}','${p.type}','${p.name}','${p.id}');">移动属性</a>
						||  <a href="${ctx}/questionManage/exportQuestionAndProp.do?id=${p.id}&state=${p.type}">导出试题</a>
					</c:if>
					<c:choose>					
						<c:when test="${p.type==1}"> || <a href="${ctx}/propManage/propList.do?id=${p.prop_val_id}&type=2">二级学科列表</a></c:when>
						<c:when test="${p.type==2}"> || <a href="${ctx}/propManage/propList.do?id=${p.prop_val_id}&type=3">三级学科列表</a></c:when>
						<c:when test="${p.type==3}"> || <a href="${ctx}/propManage/propList.do?id=${p.prop_val_id}&type=4">单元列表</a></c:when>
						<c:when test="${p.type==4}"> || <a href="${ctx}/propManage/propList.do?id=${p.prop_val_id}&type=5">知识点列表</a></c:when>
						<c:when test="${p.type==5}"> || <a href="javascript:moveQuestionShow('${p.id}');">移动试题</a></c:when>
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
					<input type="hidden" id="rsType"  value="${type}" />
					<span style="color: red; padding-left: 5px;">*</span>
				</li>
				<li>
					属性编码：
					<input type="text" id="rsCode" size="40"/>
					<input type="hidden" id="rsProp_val1" value="${prop_val1}" />
				</li>
				<li>
					属性类别：
					<select id="rsCType">
						<c:forEach items="${ctype}" var="ct">
							<option value="${ct.prop_type}">${ct.prop_type_name}</option>
						</c:forEach>
					</select>
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
					<input type="text" id="esCode" size="40" readonly="readonly"/>
					<input type="hidden" id="esId" />
				</li>
			</ul>
			<center>
				<input type="button" name="saverr" value="保存" onclick="doEditProp();" />
				<input type="button" name="saverr" value="取消" onclick="hiddenPropShow();" />			
			</center>
		</div>
		
		<div id="moveProp" style="border: #036 solid; padding-top: 30px; width: 500px; height: 200px; position: absolute; left: 450px; top: 150px; background-color: #FFFFFF; display: none; z-index: 20;">
			<ul>
				<li>
					要移动属性名称：
					<input type="text" id="cname" size="20" readonly="readonly"/>
					<input type="hidden" id="cid" />
					<input type="hidden" id="ctype" />
					<input type="hidden" id="cpid" />
					<span style="color: red; padding-left: 5px;">*</span>
				</li>			
				<li>
					目标属性 ID：
					<input type="text" id="mid" size="20"/>
					<span style="color: red; padding-left: 5px;">*</span>
				</li>
				<li>
					目标属性名称：
					<input type="text" id="mname" size="20" readonly="readonly"/>
					<input type="hidden" id="msid" />
					<input type="hidden" id="mtype" />
				</li>
			</ul>
			<center>
				<input type="button" name="check" value="检查" onclick="doCheckProp();" />
				<input type="button" name="saverr" value="保存" onclick="doMoveProp();" />
				<input type="button" name="saverr" value="取消" onclick="hiddenMovePropShow();" />
			</center>
		</div>		

		<div id="moveQuestion" style="border: #036 solid; padding-top: 30px; width: 500px; height: 200px; position: absolute; left: 450px; top: 150px; background-color: #FFFFFF; display: none; z-index: 20;">
			<ul>			
				<li>
					目标知识点ID：
					<input id="qsid" type="hidden"/>
					<input type="text" id="qmid" size="20"/>
					<span style="color: red; padding-left: 5px;">*</span>
				</li>
				<li>
					目标知识点名称：
					<input type="text" id="mqname" size="20" readonly="readonly"/>
					<input type="hidden" id="rid" />
					<input type="hidden" id="rtype" />
				</li>
			</ul>
			<center>
				<input type="button" name="check" value="检查" onclick="doCheckProps();" />
				<input type="button" name="saverr" value="保存" onclick="doReplaceQuestion()" />
				<input type="button" name="saverr" value="取消" onclick="hiddenMoveQuestion();" />
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

function movePropShow(_id,_type,_name,_pid){
	document.getElementById('moveProp').style.display='block';
	document.getElementById('bgdiv').style.display='block';
	$('cid').value = _id;
	$('ctype').value = _type;
	$('cpid').value = _pid;
	$('cname').value = _name;
}


function moveQuestionShow(_id){
	document.getElementById('moveQuestion').style.display='block';
	document.getElementById('bgdiv').style.display='block';
	$('qsid').value = _id;
}

function hiddenMoveQuestion(){
	document.getElementById('moveQuestion').style.display='none';
	document.getElementById('bgdiv').style.display='none';
	$('qsid').value='';
	$('qmid').value='';
	$('mqname').value='';
	$('rid').value='';
	$('rtype').value='';
}

function hiddenPropShow(){
	document.getElementById('editProp').style.display='none';
	document.getElementById('bgdiv').style.display='none';
	$('esName').value='';
	$('esCode').value='';
	$('esId').value = '';
}

function hiddenMovePropShow(){
	document.getElementById('moveProp').style.display='none';
	document.getElementById('bgdiv').style.display='none';
	$('mid').value='';
	$('mname').value='';
	$('msid').value='';
	$('mtype').value='';
	$('cid').value='';
	$('ctype').value='';
	$('cpid').value = '';
	$('cname').value='';
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
   var _ctype = document.getElementById('rsCType').value;
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
   params += '&c_type='+_ctype;
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


function doCheckProps(){
	var url = '${ctx}/propManage/viewProp.do';
	var _mid = document.getElementById('qmid').value;
	
	if(_mid.getBytes()==0){
		alert('请输入属性ID!');
		return;
	}
	
	var params = 'id=' + _mid;
	
	var doAjax = new Ajax.Request(
	url,
	{
	   method : 'POST',
	   parameters : params,
	   onComplete : doChecksResourceResult
	}
	);
}

function doChecksResourceResult(originalRequest){
	var result = originalRequest.responseText;
	if(result != ''){
		var tmp = result.split('||');
		$('mqname').value=tmp[0];
		$('rid').value=tmp[1];
		$('rtype').value=tmp[2];
	}else{
		$('qmid').value='';
		$('mqname').value='';
		$('rid').value='';
		$('rtype').value='';
		alert('没有找到可用属性！');
	}
}

function doCheckProp(){
	var url = '${ctx}/propManage/viewProp.do';
	var _mid = document.getElementById('mid').value;
	
	if(_mid.getBytes()==0){
		alert('请输入属性ID!');
		return;
	}
	
	var params = 'id=' + _mid;
	
	var doAjax = new Ajax.Request(
	url,
	{
	   method : 'POST',
	   parameters : params,
	   onComplete : doCheckResourceResult
	}
	);
}

function doCheckResourceResult(originalRequest){
	var result = originalRequest.responseText;
	if(result != ''){
		var tmp = result.split('||');
		$('mname').value=tmp[0];
		$('msid').value=tmp[1];
		$('mtype').value=tmp[2];
	}else{
		$('mid').value='';
		$('mname').value='';
		$('msid').value='';
		$('mtype').value='';
		alert('没有找到可用属性！');
	}
}

function doMoveProp(){
	
	var url = '${ctx}/propManage/moveProp.do';
	var _msid = document.getElementById('msid').value;
	var _mtype = document.getElementById('mtype').value;
	var _cid = document.getElementById('cid').value;
	var _cpid = document.getElementById('cpid').value;
	var _ctype = document.getElementById('ctype').value;
	
	if(_msid.getBytes()==0 || _cid.getBytes()==0){
		alert('目标属性不存在!');
		return;
	}
	if((_ctype-1)!=_mtype){
		alert('所移动的级别不符合!');
		return;
	}
	var params = 'prop_val1=' + _msid;
	params += '&prop_val_id='+_cid;
	params += '&id='+_cpid;
	params += '&type='+_ctype;

	var doAjax = new Ajax.Request(
		url,
		{
			method : 'POST',
			parameters : params,
			onComplete : doMoveResourceResult
		}
	);
}

function doMoveResourceResult(originalRequest){
	var result = originalRequest.responseText;
	if(result == 'success'){
		alert('移动成功！');
		window.location = window.location;
	}else{
		alert('移动失败');
	}
}

function doReplaceQuestion(){
	
	var url = '${ctx}/propManage/replaceQuestionProp.do';
	var _qsid = document.getElementById('qsid').value;
	var _rtype = document.getElementById('rtype').value;
	var _rid = document.getElementById('rid').value;
	
	if(_qsid==_rid){
		alert('目标属性与移动属性相同!');
		return;
	}
	if(_qsid==''){
		return;
	}
	
	if(_rid==''){
		alert('目标属性不存在!');
		return;
	}
	if(_rtype!=5){
		alert('所移动的级别不符合!');
		return;
	}
	
	var params = 'prop_val_id=' + _rid;
	params += '&id='+_qsid;
	params += '&type='+_rtype;
	var doAjax = new Ajax.Request(
		url,
		{
			method : 'POST',
			parameters : params,
			onComplete : doMoveResourceResult
		}
	);
}
</script>		
</body>
</html>