<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bind user</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
</style>

<script type="text/javascript">
	function query(){
		document.forms[0].submit();
	}

	//绑定/解绑
	function updateBindUser(cardId, isBind){
		var userIds = $("input[type='checkbox'][name='userIds']:checked");
		if(userIds == '' || userIds.length == 0){
			alert("请先选择学员!");
		    return;
		}
		var selectedIds = '';
		for(var i=0; i<userIds.length; i++){
			selectedIds += userIds[i].value + ',';
		}
		//isBind为0,表示需绑定;为1表示解绑
		if(isBind==0){
			var retdata = $.ajax({url: "${ctx }/system/SystemCardStatus.do?method=bindUser&cardId="
				+ cardId+"&userIds="+selectedIds+"&siteId=${siteId}", async: false}).responseText;
			if(retdata>0){
				alert("绑定用户成功!");
			}else{
				alert("由于网络原因,绑定用户不成功,请稍候再试!");
			}
		}else if(isBind == 1){
			var retdata = $.ajax({url: "${ctx }/system/SystemCardStatus.do?method=unBindUser&cardId="
				+ cardId+"&userIds="+selectedIds, async: false}).responseText;
			if(retdata >0){
 				alert("解绑用户成功!");
 			}
 			else{
 				alert("由于网络原因,解绑用户不成功,请稍候再试!");
 			}
		}
		window.location.reload(true);
		
	}
</script>
</head>
<body>
<form action="${ctx}/system/SystemCardStatus.do?method=getBindUser" method="post" name="queryForm">
<input type="hidden" name="isBind" id="isBind" value="${isBind }"/>
<input type="hidden" name="cardId" id="cardId" value="${cardId }"/>
<table class="its" width="90%" cellpadding="1" cellspacing="0" border="0">
	<tr>
		<td >&nbsp;
		</td>
		<td >&nbsp;
		</td>
	</tr>
	<tr>
		<td>站点:
			<select id="domainName" name="domainName">
		      <c:forEach items="${siteList}" var="site">
		       	 <option value="${site.domainName}" <c:if test="${param.domainName eq  site.domainName}">selected</c:if> >${site.aliasName}
		      </c:forEach>
		    </select>
		</td>
		<td>
			姓名:
			<input type="text" name="name" id="name" maxlength="20" value="${param.realName}">
		</td>
		<td>
			证件号:
			<input type="text" name="certificateNo" id="certificateNo" maxlength="20" value="${systemUser.certificateNo}">
		</td>
		<td align="center">
			<input type="button" class="btn_03s" value="查询" onclick="query();" />&nbsp;
			<input type="button" class="btn_03s" value="<c:if test="${isBind ==1 }">解绑用户</c:if><c:if test="${isBind ==0 }">绑定用户</c:if>" onclick="updateBindUser('${cardId }','${isBind}');" />&nbsp;
			<input type="button" class="btn_03s" value="返回" onclick="window.location.href='${ctx}/system/SystemCard.do?method=list'" />
		</td>	
	</tr>
</table>
</form>
<br/>
	
	<display:table requestURI="${ctx}/system/SystemCardStatus.do?method=getBindUser"
				 id="iSystemUser" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:100%;" class="ITS" keepStatus="true">
		<display:caption>查询结果  <span style="color:red">${meg}</span></display:caption>
		<display:column title="序号" style="width:20px;text-align:center">
		${iSystemUser_rowNum}
		</display:column>
		<display:column title='<input type="checkbox" id="userIds" value="checkbox" onclick="select_all(this)" />全选' style="width:20px;text-align:center">
			<input type="checkbox" name="userIds" value="${iSystemUser.userId }"/>
		</display:column>
		<display:column title="证件号码" property="certificateNo" style="width:300px;text-align:center">
		</display:column>
		
		<display:column title="姓名" property="realName" style="width:220px;text-align:center">
		</display:column>
		
		<display:column title="手机" property="mobilPhone"  style="width:200px;text-align:center">
		</display:column>
		<display:column title="单位" property="workUnit"  style="width:350px;text-align:center">
		</display:column>
		
	</display:table>
    <input type="hidden" name="org.apache.struts.taglib.html.TOKEN"  value="${sessionScope['org.apache.struts.action.TOKEN']}" />
	
</body>
</html>
