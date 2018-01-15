<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base target="_self" />
<title>卡类型与机构授权</title>
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

//选择机构
function add(typeId){
	var dataSourceInfos=window.showModalDialog("${ctx }/system/SystemCardType.do?method=getSystemOrgList&isAuthorized=0&typeId="+typeId,
	    	null,'scrollbars=yes;resizable=no;help=no;status=no;dialogTop=100;dialogLeft=200;dialogHeight=520px;dialogWidth=750px');
	if(dataSourceInfos){
		var ret = dataSourceInfos[0];
		if(ret>0){
			alert("卡类型与机构授权成功！");
		}else{
			alert("由于网络原因,卡类型与机构授权不成功,请稍候再试!");
		}
		window.location.reload(true);
	}
}

//解除授权
function deleteOrgAuthorized(typeId, orgId){
	if(confirm("确认解除吗?")){
		var p = {'typeId':typeId,'orgId':orgId};
		$.post("${ctx }/system/SystemCardType.do?method=deleteOrgAuthorized", p,
  			function(data){
  				if(data >0){
  					alert("解除成功!");
  				}
  				else{
  					alert("由于网络原因,解除不成功,请稍候再试!");
  				}
  				window.location.reload(true);
  		});
	}
}

</script>
	</head>
	<body bgcolor="#E7E7E7">
		
		<div style="margin: auto; text-align: right;">
		<center>
		
		<form name="queryForm" action="${ctx}/system/SystemCardType.do?method=getSystemOrgList" method="post">
		<input type="hidden" id="isAuthorized" name="isAuthorized" value="1" />
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
				&nbsp;<button type="button" class="btn_03s" onclick="javascript:add('${typeId}');" >选择机构</button>
				&nbsp;<button type="button" class="btn_03s" onClick="window.location.href='${ctx}/system/SystemCardType.do?method=list'" >返回</button>
			</td>
		</tr>
		</table>
		
		<br/>
				<display:table requestURI="${ctx}/system/SystemCardType.do?method=getSystemOrgList&isAuthorized=${isAuthorized }&type=${typeId }"
				 id="iSystemOrg" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:95%;" class="ITS" keepStatus="true">
					<display:caption><span style="color:red">${meg}</span></display:caption>
					<display:column title="序号" style="text-align:center">
						${iSystemOrg_rowNum}
					</display:column>
					<display:column property="organId" title="ID" style="text-align:center"/>
					<display:column property="name" title="机构名称" style="text-align:center" sortable="true"/>
					<display:column property="shortname" title="简称" style="text-align:center" sortable="true"/>
					<display:column title="操作" media="html" style="text-align:center">
						<a href="javascript:deleteOrgAuthorized('${typeId }','${iSystemOrg.organId }')" >解除授权</a>&nbsp;&nbsp;
					</display:column>
				</display:table>
			</center>
		</div>
		</form>
	 		
	</body>
</html>
