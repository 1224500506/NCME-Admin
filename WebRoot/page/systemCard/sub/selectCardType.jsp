<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base target="_self" />
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
		parent.document.forms[0].submit();
}

//选择
function doSelect(){
	var cardIds = $("input[type='radio'][name='cardTypeIds']:checked");
	if(cardIds.length == 0){
		alert("请选择卡类型!");
	    return;
	}
	var params = cardIds.val();
	var arr = params.split("_");
	/* var items = new Array();
	items[0]=arr[0];
	items[1]=arr[1];
	window.returnValue=items; */
	window.opener.document.getElementById("cardTypeName").value=arr[1];
	window.opener.document.getElementById("cardTypeId").value=arr[0];
	window.close();
}

</script>
	</head>
	<jsp:include page="/page/tableColor.jsp"></jsp:include>
	<body bgcolor="#E7E7E7">
		
		<div style="margin: auto; text-align: right;">
		<center>
		
		<form name="queryForm" action="${ctx}/system/SystemCard.do" method="post">
		<input type="hidden" id="method" name="method" value="selectCardType" />
		<table style="font-size:12px;width:95%;text-align: center;" class="ITS" >
		<tr>
			<td>&nbsp;</td><td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>卡类型名称&nbsp;<input name="cardTypeName" id="cardTypeName" value=""/></td>
			<td><button type="button" class="btn_03s" onclick="javascript:query();" >搜索</button></td>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn_03s" onclick="javascript:doSelect();" >确认选择</button>&nbsp;
				<button type="button" class="btn_03s" onclick="javascript:window.close();" >关闭</button>
			</td>
		</tr>
		</table>
		
		<br/>
				<display:table requestURI="${ctx}/system/SystemCard.do?method=selectCardType"
				 id="isystemCardType" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:95%;" class="ITS" keepStatus="true" decorator="com.hys.exam.displaytag.TableOverOutWrapper">
					<display:caption><span style="color:red">${meg}</span></display:caption>
					<display:column title='选择' style="text-align:center">
					<input type="radio" name="cardTypeIds" value="${isystemCardType.id }_${isystemCardType.cardTypeName }"/>
					</display:column>
					<display:column title="序号" style="text-align:center">
						${isystemCardType_rowNum}
					</display:column>
					<display:column property="id" title="ID" style="text-align:center"/>
					<display:column property="cardTypeName" title="卡类型名称" style="text-align:center" sortable="true"/>
					<display:column title="有效时间" style="text-align:center" sortable="true">
						<fmt:formatDate value="${isystemCardType.startDate}" pattern="yyyy-MM-dd"/>
					</display:column>
					<display:column title="失效时间" style="text-align:center" sortable="true">
						<fmt:formatDate value="${isystemCardType.endDate}" pattern="yyyy-MM-dd"/>
					</display:column>
					<display:column property="creditSum" title="总学分" style="text-align:center" sortable="true"/>
					<display:column property="price" title="产品价格" style="text-align:center" sortable="true"/>
					
				</display:table>
			</center>
		</div>
		</form>
	 		
	</body>
</html>
