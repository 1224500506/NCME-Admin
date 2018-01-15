<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
</style>
</head>
<body>
<form action="${ctx}/system/SystemPostHistory.do?method=list" method="post" name="queryForm">
<table class="its" width="90%" cellpadding="1" cellspacing="0" border="0">
	<tr>
		<td >
			手机号：<input type="text" name="mobilePhone" id="mobilePhone" maxlength="100" ">&nbsp;
			姓名：<input type="text" name="userName" id="userName" maxlength="100" ">&nbsp;
			证书名：<input type="text" name="certificateName" id="certificateNo" maxlength="100" ">&nbsp;
			<input type="submit" class="btn_03s" value="查询" />
		</td>
	</tr>
</table>
</form>
<br/>
	<form id="listfm" name="listfm" method="post" action="${ctx}/system/SystemPostHistory.do?method=list">
	<display:table requestURI="${ctx}/system/SystemPostHistory.do?method=list"
				 id="item" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:100%;" class="ITS" keepStatus="true">
		 
		<display:column title="序号" style="width:20px;text-align:center">
			${item_rowNum}
		</display:column>
		<display:column title="姓名" property="userName" style="width:120px;text-align:center" />
		<display:column title="手机号" property="mobilePhone" style="width:120px;text-align:center" />
		<display:column title="地址" property="address" style="width:200px;text-align:center" />
		<display:column title="证书名" property="certificateName" style="width:200px;text-align:center" />
		<display:column title="创建时间" style="text-align:center" sortable="true">
			<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</display:column>
		<display:column title="操作" style="width:120px;text-align:center">
			<a href="${ctx}/system/SystemPostHistory.do?method=toUpdate&id=${item.id}" title="修改">修改</a> 
		</display:column>
	</display:table>
	</form>
	
</body>
</html>
