<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>栏目列表</title>
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


//[批量删除] 
function delBatch() {
	var cardIds = $("input[type='checkbox'][name='systemCategoryIds']:checked");
	if(cardIds.length == 0){
		alert("请先选择需要删除的栏目!");
	    return;
	}
	if(confirm('确认删除吗?')){
		document.getElementById("method").value = "delete";
		document.forms[0].submit();
	}		
}

//[查询] 
function query() {
	document.getElementById("method").value = "list";
	document.forms[0].submit();
}
//[添加] 
function add() {
	document.getElementById("method").value = "addUI";
	document.forms[0].submit();
}
//[删除] 
function deleteSystemCategory(id,newsNum) {
	if(newsNum > 0){
		alert("该栏目下存在新闻，不可以删除");
		return;
	}
	if(confirm('确认删除吗?')){
		var checkObj = document.getElementById("systemCategoryIds_"+id);
		checkObj.checked = true;
		document.getElementById("method").value = "delete";
		document.forms[0].submit();
	}
	
}

//[更新] 
function update(categoryId) {
	document.getElementById("method").value = "updateUI";
	document.getElementById("categoryId").value = categoryId;
	document.forms[0].submit();
}

<c:if test="${message != null}">
alert("${message}");
</c:if>
</script>
	</head>
	<body bgcolor="#E7E7E7">
		
		<div style="margin: auto; text-align: right;">
		<center>
		
		<form name="queryForm" action="${ctx}/system/SystemCategory.do" method="post">
		<input type="hidden" id="method" name="method" value="list" />
		<input type="hidden" id="categoryId" name="categoryId" value="" />
		<table style="font-size:12px;width:95%;text-align: center;" class="ITS" >
			<tr>
				<td>站点&nbsp;
					<select name="applicationId" id="applicationId">
						<option value=''>全部</option>
						<c:forEach items="${siteList }" var="site">
							<option value="${site.id }" ${applicationId == site.id ? 'selected' : ''}>${site.domainName }</option>
						</c:forEach>
					</select>
				</td>
				<td>标题&nbsp;
					<input type="text" name="title" value="${title }"/>
				</td>
				<td>
				每页显示条数&nbsp;<input name="pageSize" id="pageSize" value="${pageSize }"/>
				<button type="button" class="btn_03s" onclick="javascript:query();" >搜索</button>
				<button type="button" class="btn_03s" onclick="javascript:add();" >添加栏目 </button>
				</td>
			</tr>
		</table>
		
		<br/>
			
				<display:table requestURI="${ctx}/system/SystemCategory.do?method=list"
				 id="systemCategory" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:95%;" class="ITS" keepStatus="true">
					<display:caption><span style="color:red">${meg}</span></display:caption>
					<display:column title='<input type="checkbox" name="systemCategoryIds"  value="checkbox" onclick="select_all(this)" />全选' style="text-align:center">
					<input type="checkbox" name="cardIds" value="${systemCategory.id }" id="systemCategoryIds_${systemCategory.id }"/>
					</display:column>
					<display:column title="序号" style="text-align:center">
						${systemCategory_rowNum}
					</display:column>
					<display:column property="id" title="ID" style="text-align:center"/>
					<display:column property="title" title="标题" style="text-align:center" sortable="true"/>
					<display:column property="domainName" title="站点" style="text-align:center" sortable="true"/>
					<display:column property="createDate" title="创建时间" style="text-align:center" sortable="true"/>
					<display:column title="操作" media="html" style="text-align:center">
						<a href="javascript:deleteSystemCategory('${systemCategory.id }','${systemCategory.newsNum }')" >删除</a>
						&nbsp;&nbsp;&nbsp;
						<a href="javascript:update('${systemCategory.id }')" >修改</a>
						&nbsp;&nbsp;&nbsp;
						<a href="${ctx}/system/SystemNews.do?method=list&categoryId=${systemCategory.id }" >文章管理</a>
					</display:column>
				</display:table>
			</center>
		</div>
		</form>
	 		
	</body>
</html>
