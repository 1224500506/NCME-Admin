<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base target="_self" />
<title>title</title>
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


//[查询] 
function query() {
	document.forms[0].submit();
}

//授权
function add(){
	var typeId = '${typeId}';
	var orgIds = $("input[type='checkbox'][name='orgIds']:checked");
	if(orgIds =='' || orgIds.length == 0){
		alert("请选择要授权的机构!");
	    return;
	}
	var selectedIds = '';
	for(var i=0; i<orgIds.length; i++){
		selectedIds += orgIds[i].value + ',';
	}
	var retdata = $.ajax({url: "${ctx }/system/SystemCardType.do?method=saveSystemPaycardOrgan&typeId="
		+ typeId+"&orgIds="+selectedIds, async: false}).responseText;

	var items = new Array();
	items[0]= retdata;
	window.returnValue=items;
	window.close();
}

</script>
	</head>
	<body bgcolor="#E7E7E7">
		
		<div style="margin: auto; text-align: right;">
		<center>
		
		<form name="queryForm" action="${ctx}/system/SystemCardType.do?method=getSystemOrgList" method="post">
		<input type="hidden" id="isAuthorized" name="isAuthorized" value="0" />
		<input type="hidden" id="typeId" name="typeId" value="${typeId }" />
		<table style="font-size:12px;width:95%;text-align: center;" class="ITS" >
		<tr>
			<td>&nbsp;</td><td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>机构名称&nbsp;<input name="orgName" id="orgName" value=""/></td>
			<td><button type="button" class="btn_03s" onclick="javascript:query();" >搜索</button></td>
			<td>&nbsp;</td>
			<td>
				&nbsp;<button type="button" class="btn_03s" onclick="javascript:add();" >授权</button>
				&nbsp;<button type="button" class="btn_03s" onclick="javascript:window.close();" >关闭</button>
			</td>
		</tr>
		</table>
		
		<br/>
				<display:table requestURI="${ctx}/system/SystemCardType.do?method=getSystemOrgList&isAuthorized=0&type=${typeId }"
				 id="iSystemOrg" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:95%;" class="ITS" keepStatus="true">
					<display:caption><span style="color:red">${meg}</span></display:caption>
					<display:column title='选择' style="text-align:center">
					<input type="checkbox" name="orgIds" value="${iSystemOrg.organId }"/>
					</display:column>
					<display:column title="序号" style="text-align:center">
						${iSystemOrg_rowNum}
					</display:column>
					<display:column property="organId" title="ID" style="text-align:center"/>
					<display:column property="name" title="机构名称" style="text-align:center" sortable="true"/>
					
				</display:table>
			</center>
		</div>
		</form>
	 		
	</body>
</html>
