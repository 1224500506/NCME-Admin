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
		<td><a href="javascript:showBaseProp(${olemPropId});">添加基本属性</a>||<a href="javascript:history.go(-1)">返回</a></td>
	</tr>
	<tr>
		<td>
			<display:table name="proplist" requestURI="${ctx}/propManage/olemBasePropList.do" id="p" style="width:100%;" class="its">
				<display:column title="ID" style="width:80px;text-align:center" property="base_prop_id"></display:column>
				<display:column title="代码" style="width:80px;text-align:center" property="id"></display:column>
				<display:column title="名称" style="width:120px;" property="name"></display:column>
				<display:column title="类型" style="width:100px;text-align:center">
					<c:choose>
						<c:when test="${p.type eq '3'}">三级学科</c:when>
						<c:when test="${p.type eq '4'}">单元</c:when>
						<c:when test="${p.type eq '5'}">知识点</c:when>
					</c:choose>				
				</display:column>
				<display:column title="操作" style="width:200px;text-align:center">
					<a href="javascript:doDelref(${p.olem_prop_id},${p.base_prop_id});">删除对应关系</a>
				</display:column>
			</display:table>
		</td>
	</tr>
</table>
<script type="text/javascript">
function showBaseProp(id){
	var url = '${ctx}/propManage/olemAddPropList.do?olemPropId='+id;
	window.open(url, 'newwindow', 'height=400, width=600, top=40, left=40, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no')
}

function doDelref(olem_prop_id,base_prop_id){
	var url = '${ctx}/propManage/olemDelBaseProp.do';
	if(olem_prop_id!='' && base_prop_id!=''){
		var params = 'olem_prop_id=' +olem_prop_id;
		params +='&base_prop_id='+base_prop_id;
	}else{
		return;
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
   		alert('删除失败!');
   }
}
</script>
</body>
</html>