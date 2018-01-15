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
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
</style>
 <script type="text/javascript">


//[批量删除] 
function delBatch() {
	alert("暂不允许删除卡类型！");
	return;
	var cardTypeIds = $("input[type='checkbox'][name='cardTypeIds']:checked");
	if(cardTypeIds.length == 0){
		alert("请先选择学习卡类型!");
	    return;
	}
	if(confirm("确认删除吗?")){
		var p = {'typeIds':cardTypeIds};
		$.post("${ctx }/system/SystemCardType.do?method=delete", p,
  			function(data){
  				if(data >0){
  					alert("卡类型删除成功!");
  				}
  				else{
  					alert("由于网络原因,卡类型删除不成功,请稍候再试!");
  				}
  				location = location;
  		});
	}
}

//[查询] 
function query() {
		document.forms[0].submit();
}

//查看
function viewType(typeId){
	window.location.href = "${ctx }/system/SystemCardType.do?method=view&id="+typeId;
}

//修改
function updateType(typeId){
	window.location.href = "${ctx }/system/SystemCardType.do?method=update&id="+typeId;
}

//新增
function addType(){
	window.location.href = "${ctx }/system/SystemCardType.do?method=update";
}

//授权/非授权的课程列表
function authorizeCourse(typeId){
	window.location.href = "${ctx }/system/SystemCardType.do?method=getStudyCourse&isAuthorized=1&typeId="+typeId;
}

//授权站点
function authorizeSite(typeId){
	window.location.href = "${ctx }/system/SystemCardTypeSiteOrg.do?method=getSystemSiteList&typeId="+typeId;
}

//授权
function authorizePaycardOrgan(typeId){
	window.location.href = "${ctx }/system/SystemCardTypeSiteOrg.do?method=getSystemPaycardOrganList&typeId="+typeId;
}

function doRefresh(){
	var meg = '${meg}';
	if(meg != null && meg !=''){
		alert(meg);
		document.forms[0].submit();
	}
}

</script>
	</head>
	<jsp:include page="/page/tableColor.jsp"></jsp:include>
	<body bgcolor="#E7E7E7" onload="doRefresh()">
		
		<div style="margin: auto; text-align: right;">
		<center>
		
		<form name="queryForm" action="${ctx}/system/SystemCardType.do" method="post">
		<input type="hidden" id="method" name="method" value="list" />
		<table style="font-size:12px;width:95%;text-align: center;" class="ITS" >
		<tr>
			<td>卡类型管理</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>卡类型名称&nbsp;<input name="cardTypeName" id="cardTypeName" value=""/></td>
			<td>有效时间&nbsp;<input type="text" name="startDate" value="${startDate}" onClick="WdatePicker()"/></td>
			<td>失效时间&nbsp;<input type="text" name="endDate" value="${endDate}" onClick="WdatePicker()"/></td>
			<td>
				<button type="button" class="btn_03s" onclick="javascript:query();" >搜索</button>&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn_03s" onclick="javascript:addType();" >添加</button>
				<button type="button" class="btn_03s" onclick="javascript:delBatch();" disabled>删除</button>
			</td>
		</tr>
		</table>
		
		<br/>
			
				<display:table requestURI="${ctx}/system/SystemCardType.do?method=list"
				 id="systemCardType" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:95%;" class="ITS" keepStatus="true"
				 decorator="com.hys.exam.displaytag.TableOverOutWrapper" >
					<display:column title='<input type="checkbox" id="cardTypeIds" value="checkbox" 
					onclick="select_all(this)" />全选' style="text-align:center">
					<input type="checkbox" name="cardTypeIds" value="${systemCardType.id }"/>
					</display:column>
					<display:column title="序号" style="text-align:center">
						${systemCardType_rowNum}
					</display:column>
					<display:column property="id" title="ID" style="text-align:center"/>
					<display:column property="cardTypeName" title="卡类型名称" style="text-align:center" sortable="true"/>
					<display:column title="有效时间" style="text-align:center" sortable="true">
						<fmt:formatDate value="${systemCardType.startDate }" pattern="yyyy-MM-dd"/>
					</display:column>
					<display:column title="失效时间" style="text-align:center" sortable="true">
						<fmt:formatDate value="${systemCardType.endDate }" pattern="yyyy-MM-dd" />
					</display:column>
					<display:column property="creditSum" title="总学时" style="text-align:center" sortable="true"/>
					<display:column property="price" title="产品价格" style="text-align:center" sortable="true"/>
					<display:column property="allNum" title="总数" style="text-align:center" sortable="true"/>
					<display:column property="userdNum" title="已使用" style="text-align:center" sortable="true"/>
					<display:column title="剩余" style="text-align:center" sortable="true">
						<c:if test="${systemCardType.remainNum <100}"><font color='red'>${systemCardType.remainNum}</font></c:if>
						<c:if test="${systemCardType.remainNum >=100}">${systemCardType.remainNum}</c:if>
					</display:column>
					<display:column title="操作(<font color='red'>先授权站点,再授权课程!!!</font>)" media="html" style="text-align:center">
						<a href="javascript:viewType('${systemCardType.id }')" >查看卡类型</a>&nbsp;&nbsp;
						<a href="javascript:updateType('${systemCardType.id }')" >修改卡类型</a>&nbsp;&nbsp;
						<a href="javascript:authorizeSite('${systemCardType.id }')" >授权站点</a>&nbsp;&nbsp;
						<a href="javascript:authorizePaycardOrgan('${systemCardType.id }')" >授权地区</a>&nbsp;&nbsp;
						<a href="javascript:authorizeCourse('${systemCardType.id }')" >授权课程列表</a>&nbsp;&nbsp;
					</display:column>
				</display:table>
			</center>
		</div>
		</form>
	 		
	</body>
</html>
