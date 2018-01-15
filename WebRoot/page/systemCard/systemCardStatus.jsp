<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.hys.auth.model.HysUsers" %>
<%@ page import="com.hys.exam.common.util.LogicUtil" %>
<%
	HysUsers user = LogicUtil.getSystemUser(request);
	String loginame = "";
	if(null != user){
		if(null != user.getLoginName()){
			loginame = user.getLoginName();
		}
	}
%>
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

//[查询] 
function query() {
	
	document.forms[0].submit();
}

//选择卡类型
function selectCardType(){
	var dataSourceInfos=window.showModalDialog('${ctx}/system/SystemCard.do?method=selectCardType',
	    	null,'scrollbars=yes;resizable=no;help=no;status=no;dialogTop=100;dialogLeft=200;dialogHeight=520px;dialogWidth=750px');
	    if(dataSourceInfos){
			queryForm.cardTypeName.value=dataSourceInfos[1];
			queryForm.cardTypeId.value=dataSourceInfos[0];
		}
}


//查看学习卡详细
function viewDetail(cardId){
	var url = "${ctx }/system/SystemCardStatus.do?method=viewDetail&cardId="+cardId;
	window.location.href = url;
}

</script>
	</head>
	<body bgcolor="#E7E7E7">
		
		<div style="margin: auto; text-align: right;">
		<center>
		
		<form name="queryForm" action="${ctx}/system/SystemCardStatus.do" method="post">
		<input type="hidden" id="method" name="method" value="list" />
		<table style="font-size:12px;width:95%;text-align: center;" class="ITS" >
		<tr>
			<td>卡状态管理</td>
			<td>&nbsp;</td>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td>
				<%
					if(loginame.equals("admin")){
				%>
					开始卡号&nbsp;<input name="cardNumberStart" id="cardNumberStart" value="${cardNumberStart }"/>
					结束卡号&nbsp;<input name="cardNumberEnd" id="cardNumberEnd" value="${cardNumberEnd }"/>
				<%}else{ %>
					卡号&nbsp;<input name="cardNumber" id="cardNumber" value="${cardNumber }"/></td>
				<%} %>
			<td>
				<button type="button" class="btn_03s" onclick="javascript:query();" >搜索</button>&nbsp;
			</td>
			<td colspan="2">
				&nbsp;
			</td>
		</tr>
		</table>
		
		<br/>
			
				<display:table requestURI="${ctx}/system/SystemCardStatus.do?method=list"
				 id="systemCard" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:95%;" class="ITS" keepStatus="true">
					<display:caption><span style="color:red">${meg}</span></display:caption>
					<display:column title='<input type="checkbox" id="cardIds" value="" onclick="select_all(this)" />全选' style="text-align:center">
						<input type="checkbox" name="cardIds" value="${systemCard.id }"/>
					</display:column>
					<display:column title="序号" style="text-align:center">
						${systemCard_rowNum}
					</display:column>
					<display:column property="id" title="ID" style="text-align:center"/>
					<display:column property="cardNumber" title="卡号" style="text-align:center" sortable="true"/>
					<display:column property="cardPassword" title="卡密码" style="text-align:center" sortable="true"/>
					<display:column title="导入时间" style="text-align:center" sortable="true">
						<fmt:formatDate value="${systemCard.importDate}" pattern="yyyy-MM-dd"/>
					</display:column>
					<display:column title="有效期" style="text-align:center" sortable="true">
						<fmt:formatDate value="${systemCard.usefulDate}" pattern="yyyy-MM-dd"/>
					</display:column>
					<display:column property="balance" title="剩余学时" style="text-align:center" sortable="true"/>
					<display:column style="text-align:center; width:10%" title="卡状态">
						<c:if test="${systemCard.status == 1 }">有效</c:if>
						<c:if test="${systemCard.status == 0 }">待生效</c:if>
						<c:if test="${systemCard.status == -2}">禁用</c:if>
						<c:if test="${systemCard.status == -1}">无效/删除</c:if>
					</display:column>
					<display:column title="操作" media="html" style="text-align:center">
						<a href="javascript:viewDetail('${systemCard.id }')" >查看详细</a>
					</display:column>
				</display:table>
			</center>
		</div>
		</form>
	 		
	</body>
</html>
