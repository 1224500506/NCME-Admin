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
				<c:when test="${type==3}">
					<a href="javascript:addBaseProp(${type}, ${olemPropId});">添加 ||</a> 
				</c:when>
				<c:when test="${type==4}">
					<a href="javascript:addBaseProp(${type}, ${olemPropId});">添加 ||</a> 
				</c:when>
				<c:when test="${type==5}">
					<a href="javascript:addBaseProp(${type}, ${olemPropId});">添加 ||</a> 
				</c:when>
			</c:choose>	
			<a href="javascript:history.go(-1)">返回</a>
		</td>
	</tr>
	<tr>
		<td>
			<form name="dfm" method="post">
			<display:table name="propList" requestURI="${ctx}/propManage/olemAddPropList.do" id="p" style="width:100%;" class="its">
				<c:choose>					
					<c:when test="${p.type==3}">
						<display:column title="<input type='checkbox' name='chkall' id='chkall' value='on' onclick='CheckAll(this.form);'>" style="width:2%;text-align:center">
							<input type="checkbox" name="propId" value="${p.prop_val_id}" id="propId"/>
						</display:column>						
					</c:when>
					<c:when test="${p.type==4}">
						<display:column title="<input type='checkbox' name='chkall' id='chkall' value='on' onclick='CheckAll(this.form);'>" style="width:2%;text-align:center">
							<input type="checkbox" name="propId" value="${p.prop_val_id}" id="propId"/>
						</display:column>						
					</c:when>
					<c:when test="${p.type==5}">
						<display:column title="<input type='checkbox' name='chkall' id='chkall' value='on' onclick='CheckAll(this.form);'>" style="width:2%;text-align:center">
							<input type="checkbox" name="propId" value="${p.prop_val_id}" id="propId"/>
						</display:column>						
					</c:when>
				</c:choose>
				<display:column title="ID" style="width:80px;text-align:center" property="prop_val_id"></display:column>	
				<display:column title="代码" style="width:80px;text-align:center" property="id"></display:column>
				<display:column title="名称" style="width:120px;" property="name"></display:column>
				<display:column title="类型" style="width:100px;text-align:center" property="typeName"></display:column>
				<display:column title="操作" style="width:200px;text-align:center">
					<c:choose>					
						<c:when test="${p.type==1}"><a href="${ctx}/propManage/olemAddPropList.do?prop_id=${p.prop_val_id}&type=2&olemPropId=${olemPropId}">二级学科列表</a></c:when>
						<c:when test="${p.type==2}"><a href="${ctx}/propManage/olemAddPropList.do?prop_id=${p.prop_val_id}&type=3&olemPropId=${olemPropId}">三级学科列表</a></c:when>
						<c:when test="${p.type==3}"><a href="${ctx}/propManage/olemAddPropList.do?prop_id=${p.prop_val_id}&type=4&olemPropId=${olemPropId}">单元列表</a></c:when>
						<c:when test="${p.type==4}"><a href="${ctx}/propManage/olemAddPropList.do?prop_id=${p.prop_val_id}&type=5&olemPropId=${olemPropId}">知识点列表</a></c:when>
					</c:choose>
				</display:column>
			</display:table>
			</form>
		</td>
	</tr>
</table>
<script type="text/javascript">
function CheckAll(form) {
	for (var i=0;i<form.elements.length;i++) {
		var e = form.elements[i];
		if (e.name != 'chkall' && e.type=='checkbox')
			e.checked = form.chkall.checked;
	}
}

function addBaseProp(_type,_olemPropId){
	var id = '';
	if(document.dfm.propId!=null){
		var length = document.dfm.propId.length;
		if(!document.dfm.propId.length){
			if(document.dfm.propId.checked==true){
				id = document.dfm.propId.value;
			}
		}else{
			for(var i=0;i<length;i++){
				if(document.dfm.propId[i].checked==true){
					id+=document.dfm.propId[i].value+',';
				}
			}
		}
	}
	
	if(id==''){
		alert('请选择要添加的属性!');
	}else{
		if(confirm('您确定要进行操作吗？')){
			var url = '${ctx}/propManage/olemAddBaseProp.do';
			var params = 'idArr=' +id;
			params +='&olemPropId='+_olemPropId;
			params +='&propType='+_type;
			var doAjax = new Ajax.Request(
		    url,
		    {
		      method : 'POST',
		      parameters : params,
		      onComplete : doAddResourceResult
		    }
		    );
		}
	}
}

function doAddResourceResult(originalRequest){
	var result = originalRequest.responseText;
	if(result == 0){
		alert('添加成功！');
		window.opener.location.href = window.opener.location.href; 
		if (window.opener.progressWindow){ 
			window.opener.progressWindow.close(); 
		} 
	window.close();
   } else if(result==1){
   		alert('添加失败请检查属性类别!');
   } else{
	   alert('添加失败请检查属性值是否已经存在!');
   }
}
</script>
</body>
</html>