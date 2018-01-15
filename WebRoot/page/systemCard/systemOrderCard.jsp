<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看订单下的卡信息</title>
<%@ include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
</style>

</head>
	<body bgcolor="#E7E7E7">
		
		<div style="margin: auto; text-align: right;">
		<center>
		
		<form name="queryForm" action="" method="post">
		<input type="hidden" id="method" name="method" value="list" />
		<table style="font-size:12px;width:95%;text-align: center;" class="ITS" >
		<tr>
			<td>查看订单下的学习卡</td>
			<td>&nbsp;</td>
			<td>
				<!-- 
				<input type="button" class="btn_03s" value="返 回" onclick="window.location.href='${ctx}/system/SystemCardOrder.do?method=list'"/>
				 -->
			</td>
			<td>&nbsp;</td> 
		</tr>
		
		</table>
		
		<br/>
			
				<display:table requestURI="${ctx}/system/SystemCardOrder.do?method=getCardList"
				 id="systemOrderCard" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:95%;" class="ITS" keepStatus="true">
					<display:caption><span style="color:red">${meg}</span></display:caption>
					
					<display:column title="序号" style="text-align:center">
						${systemOrderCard_rowNum}
					</display:column>
					<display:column property="id" title="ID" style="text-align:center"/>
					<display:column property="cardNumber" title="卡号" style="text-align:center" sortable="true"/>
					<display:column property="cardPassword" title="卡密码" style="text-align:center" sortable="true"/>
					<display:column property="importDate" title="导入时间" style="text-align:center" sortable="true"/>
					<display:column property="usefulDate" title="有效期" style="text-align:center" sortable="true"/>
					<display:column style="text-align:center; width:10%" title="卡状态">
						<c:if test="${systemOrderCard.status == 1 }">有效</c:if>
						<c:if test="${systemOrderCard.status == 0 }">待生效</c:if>
						<c:if test="${systemOrderCard.status == -2}">禁用</c:if>
						<c:if test="${systemOrderCard.status == -1}">无效/删除</c:if>
					</display:column>
					
				</display:table>
			</center>
		</form>
	 	</div>
	 	<div style="text-align:center">
	 		<input type="button" class="btn_03s" value="关闭" onclick="window.close();"/>
	 	</div>
	</body>
</html>
