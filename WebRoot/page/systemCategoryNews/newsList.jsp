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
	var cardIds = $("input[type='checkbox'][name='systemNewsIds']:checked");
	if(cardIds.length == 0){
		alert("请先选择需要删除的新闻!");
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
function deleteNews(id) {
	if(confirm('确认删除吗?')){
		var checkObj = document.getElementById("systemNewsIds_"+id);
		checkObj.checked = true;
		document.getElementById("method").value = "delete";
		document.forms[0].submit();
	}
	
}

//[更新] 
function update(newsId) {
	document.getElementById("method").value = "updateUI";
	document.getElementById("newsId").value = newsId;
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
		
		<form name="queryForm" action="${ctx}/system/SystemNews.do" method="post">
		<input type="hidden" id="method" name="method" value="list" />
		<input type="hidden" id="categoryId" name="categoryId" value="${categoryId }" />
		<input type="hidden" id="newsId" name="newsId" />
		<table style="font-size:12px;width:95%;text-align: center;" class="ITS" >
			<tr>
				<td>标题&nbsp;
					<input type="text" name="title" value="${title }"/>
				</td>
				<td>
					每页显示条数&nbsp;<input name="pageSize" id="pageSize" value="${pageSize }"/>
					<button type="button" class="btn_03s" onclick="javascript:query();" >搜索</button>
					<button type="button" class="btn_03s" onclick="javascript:add();" >添加新闻 </button>&nbsp;&nbsp;
					<button type="button" class="btn_03s" onclick="window.location.href='${ctx}/system/SystemCategory.do'" >返回栏目 </button>
				</td>
			</tr>
		</table>
		
		<br/>
			
				<display:table requestURI="${ctx}/system/SystemNews.do?method=list"
				 id="systemNews" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:95%;" class="ITS" keepStatus="true">
					<display:caption><span style="color:red">${meg}</span></display:caption>
					<display:column title='<input type="checkbox" name="systemCategoryIds"  value="checkbox" onclick="select_all(this)" />全选' style="text-align:center">
					<input type="checkbox" name="systemNewsIds" value="${systemNews.id }" id="systemNewsIds_${systemNews.id }"/>
					</display:column>
					<display:column title="序号" style="text-align:center">
						${systemNews_rowNum}
					</display:column>
					<display:column property="id" title="ID" style="text-align:center"/>
					<display:column property="title" title="标题" style="text-align:center" sortable="true"/>
					<display:column title="文章类型" style="text-align:center" >
						<c:if test="${systemNews.type == 1}">
							普通文章
						</c:if>
						<c:if test="${systemNews.type == 2}">
							下载资源
						</c:if>
						<c:if test="${systemNews.type == 3}">
							外部连接
						</c:if>
					</display:column>
					<display:column title="行业限制" style="text-align:center" >
						<c:if test="${fn:length(systemNews.systemIndustryList)==0 }">
							无限制
						</c:if>
						<c:if test="${fn:length(systemNews.systemIndustryList)!=0 }">
							<c:forEach items="${systemNews.systemIndustryList }" var="industry" varStatus="status">
								${industry.industryName }
								<c:if test="${status.index != (fn:length(systemNews.systemIndustryList)-1) }">,</c:if>
							</c:forEach>
						</c:if>
					</display:column>
					<display:column property="createDate" title="创建时间" style="text-align:center" sortable="true"/>
					<display:column title="操作" media="html" style="text-align:center">
						<a href="javascript:deleteNews('${systemNews.id }')" >删除</a>
						&nbsp;&nbsp;&nbsp;
						<a href="javascript:update('${systemNews.id }')" >修改</a>
					</display:column>
				</display:table>
			</center>
		</div>
		</form>
	 		
	</body>
</html>
