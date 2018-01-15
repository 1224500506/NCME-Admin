<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
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


//[查询] 
function query() {
		document.getElementById('method').value = 'list';
		document.forms[0].submit();
}

</script>
	</head>
	<body bgcolor="#E7E7E7">
		<div style="margin: auto; text-align: right;">
		<center>
		
		<form name="queryForm" action="${ctx}/system/systemAdminOrgan.do" method="post">
		<input type="hidden" id="method" name="method" value="list" />
		<table style="font-size:12px;width:95%;text-align: center;" class="its" >
		<tr>
		<td>名称:&nbsp;&nbsp;<input name="name" id="name" value="${name }" size="50"/>&nbsp;&nbsp;
			<button type="button" class="btn_03s" onclick="javascript:query();" >查询</button></td>
		</tr>
		
		</table>
		
		<br/>
			
				<display:table requestURI="${ctx}/system/systemAdminOrgan.do?method=list"
				 id="systemAdminOrgan" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:95%;" class="ITS" keepStatus="true">
					<display:caption>查询结果  <span style="color:red">${meg}</span></display:caption>
					<display:column title='<input type="checkbox" name="sel_all" id="selId" value="checkbox" onclick="select_all(this)" />全选' style="text-align:center;width:5%;">
					<input type="checkbox" name="selId" value="${systemAdminOrgan.organId}"/>
					</display:column>
					<display:column title="序号" style="text-align:center;width:10%">
					${systemAdminOrgan_rowNum}
					</display:column>
					<display:column property="name" title="名称" style="text-align:center;width:30%" />
					<display:column property="description" title="描述" style="text-align:center;width:30%"/>
				</display:table>
			</center>
		</div>
		</form>
		
	</body>
</html>
