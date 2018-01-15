<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
</style>
 <script type="text/javascript">

function del(id){
   if(confirm('确认删除吗?')){
	   var url = '${ctx}/system/systemClient.do?method=delete';
	   var params = 'selId=' + id;
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
   	 alert('操作成功');
      window.location = window.location;
   }else{
      alert('操作失败');
   }
}


//[批量删除] 
function delBatch() {
	if (!hadChk('selId')){
		alert("至少选择一项!");
		return;
	}
	if(confirm('确认删除吗?')){
		document.getElementById('method').value = 'delete';
		document.forms[0].submit();
	}		
}

//[查询] 
function query() {
		document.getElementById('method').value = 'list';
		document.forms[0].submit();
}
//[新增] 
function add() {
		document.getElementById('method').value = 'add';
		document.forms[0].submit();
}
//[修改] 
function edit(id) {
		document.location.href = "${ctx}/system/systemClient.do?method=edit&id="+id;
}


</script>
	</head>
	<body bgcolor="#E7E7E7">
		<div style="margin: auto; text-align: right;">
		<center>
		
		<form name="queryForm" action="${ctx}/system/systemClient.do" method="post">
		<input type="hidden" id="method" name="method" value="list" />
		<table style="font-size:12px;width:95%;text-align: center;" class="ITS" >
		<tr>
		<td>客户名称:</td><td><input type="text" name="model.clientName" value="${systemClientForm.model.clientName}"/></td>
		<td>所在省份:</td>
		<td>
			<select id="orgId" name="model.orgId">
			<option value="-1">----请选择---</option>
			<c:forEach var="item" items="${orgs}" varStatus="stat" >
			<option value="${item.id}">${item.orgName}</option>
			</c:forEach>
			</select>
		</td>   
		</tr>
		<tr><td colspan="4">
			<button type="button" class="btn_03s" onclick="javascript:query();" >查询</button>
			<button type="button" class="btn_03s" onclick="clearForm('queryForm');return false;" title="清空所有查询条件">清空</button>
			<button type="button" class="btn_03s" onclick="javascript:add();" title="点击新增">新增</button>
			<button type="button" class="btn_03s" onclick="javascript:delBatch();" title="选中删除">删除</button>
			</td>
		</tr>
		</table>
		
		<br/>
			
				<display:table requestURI="${ctx}/system/systemClient.do?method=list"
				 id="systemClient" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:95%;" class="ITS" keepStatus="true">
					<display:caption>查询结果  <span style="color:red">${meg}</span></display:caption>
					<display:column title='<input type="checkbox" name="sel_all" id="selId" value="checkbox" onclick="select_all(this)" />全选' style="text-align:center">
					<input type="checkbox" name="selId" value="${systemClient.id}"/>
					</display:column>
					<display:column title="序号" style="text-align:center">
					${systemClient_rowNum}
					</display:column>
					<display:column property="clientName" title="客户姓名" style="text-align:center"/>
					<display:column property="orgName" title="省份" style="text-align:center"/>
					<display:column property="contact" title="联系人" style="text-align:center"/>
					<display:column property="contactPhone" title="联系电话" style="text-align:center"/>
					<display:column property="backupPhone" title="备用电话" style="text-align:center"/>
					<display:column property="mainCharge" title="主要负责人" style="text-align:center"/>
					<display:column property="mainChargeContact" title="主要负责人联系电话" style="text-align:center"/>
					<display:column title="操作" media="html" style="text-align:center">
					<a href="javascript:edit(${systemClient.id})" >编辑</a>
					 
					</display:column>
				</display:table>
			</center>
		</div>
		</form>
	  <script type="text/JavaScript">
		 //select 选中已选项
		  $("#orgId ").val(${systemClientForm.model.orgId}); 
	  </script>  		
	</body>
</html>
