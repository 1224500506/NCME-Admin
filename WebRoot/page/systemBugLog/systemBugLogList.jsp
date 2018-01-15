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
	var systemBugIds = $("input[type='checkbox'][name='systemBugIds']:checked");
	if(systemBugIds.length == 0){
		alert("请先选择要删除的BUG!");
	    return;
	}
	var selectedIds = '';
	if(systemBugIds != '' && systemBugIds.length >0 ){
		for(var i=0; i<systemBugIds.length; i++){
			selectedIds += systemBugIds[i].value + ',';
		}
	}
	if(confirm("确认删除吗?")){
		var p = {'ids':selectedIds};
		$.post("${ctx }/system/SystemBugLog.do?method=delete", p,
  			function(data){
  				if(data >0){
  					alert("BUG删除成功!");
  				}
  				else{
  					alert("由于网络原因,BUG删除不成功,请稍候再试!");
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
	window.location.href = "${ctx }/system/SystemBugLog.do?method=view&id="+typeId;
}

//修改
function updateType(typeId){
	window.location.href = "${ctx }/system/SystemBugLog.do?method=update&id="+typeId;
}

//新增
function addType(type){
	window.location.href = "${ctx }/system/SystemBugLog.do?method=update&type="+type;
}

//日志查询
function queryLog(type){
	window.location.href = "${ctx }/system/SystemBugLog.do?method=list&type="+type;
}


</script>
	</head>
	<jsp:include page="/page/tableColor.jsp"></jsp:include>
	<body bgcolor="#E7E7E7" >
	
		<div style="margin: auto; text-align: right;">
				<center>
		  			<form name="queryForm" action="${ctx}/system/SystemBugLog.do" method="post">
					<input type="hidden" id="method" name="method" value="list" />
					<input type="hidden" id="type" name="type" value="${type }" />
					<table style="font-size:12px;width:95%;text-align: center;" class="ITS" >
					<tr>
						<td>BUG日志管理</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>BUG标题&nbsp;<input type="text" name="title" id="bugTitle" value="" /></td>
						<td>BUG内容&nbsp;<input type="text" name="content" value="" /></td>
						<td>
							<button type="button" class="btn_03s" onclick="javascript:query();" >搜索</button>&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn_03s" onclick="javascript:addType('${type}');" >添加</button>
							<button type="button" class="btn_03s" onclick="javascript:delBatch();" >删除</button>&nbsp;&nbsp;&nbsp;&nbsp;
							<c:if test="${type == 1 }">
								<button type="button" class="btn_03s" onclick="javascript:queryLog(2);" >日志列表</button>
							</c:if>
							<c:if test="${type == 2 }">
								<button type="button" class="btn_03s" onclick="javascript:queryLog(1);" >BUG列表</button>
							</c:if>
						</td>
					</tr>
					</table>
					
					<br/>
					
					<display:table requestURI="${ctx}/system/SystemBugLog.do?method=list"
					id="systemBugLog" cellpadding="0" cellspacing="0" partialList="true" 
					excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
					style="font-size:12px;width:95%;" class="ITS" keepStatus="true" decorator="com.hys.exam.displaytag.TableOverOutWrapper">
						
							<display:column title='<input type="checkbox" id="systemBugIds" onclick="select_all(this)" />全选' style="text-align:center">
								<input type="checkbox" name="systemBugIds" value="${systemBugLog.id }"/>
							</display:column>
							<display:column title="序号" style="text-align:center">
								${systemBugLog_rowNum}
							</display:column>
							<display:column property="id" title="ID" style="text-align:center"/>
							
							<display:column title="BUG/日志标题" style="text-align:center" sortable="true">
								${systemBugLog.title }
							</display:column>
							<display:column title="BUG/日志内容" style="text-align:center" sortable="true" >
								<c:if test="${fn:length(systemBugLog.content)>50 }">
									${fn:substring(systemBugLog.content,0,50) }......
								</c:if>
								<c:if test="${fn:length(systemBugLog.content)<=50 }">
									${systemBugLog.content }
								</c:if>
							</display:column>
							<display:column title="BUG级别" style="text-align:center" sortable="true">
								<c:if test="${systemBugLog.bugLevel == 1 }">一级</c:if>
								<c:if test="${systemBugLog.bugLevel == 2 }">二级</c:if>
								<c:if test="${systemBugLog.bugLevel == 3 }">三级</c:if>
							</display:column>
							<display:column title="BUG修改状态" style="text-align:center" sortable="true">
								<c:if test="${systemBugLog.bugStatus == 0 }"><font color="red">未修改</font></c:if>
								<c:if test="${systemBugLog.bugStatus == 1 }">已修改</c:if>
								<c:if test="${systemBugLog.bugStatus == 2 }">不需修改</c:if>
								<c:if test="${systemBugLog.bugStatus == 3 }">暂无法修改</c:if>
								<c:if test="${systemBugLog.bugStatus == 4 }">不是问题,打回</c:if>
							</display:column>
							<display:column  title="BUG状态" style="text-align:center" sortable="true">
								<c:if test="${systemBugLog.status == 1 }">正常</c:if>
								<c:if test="${systemBugLog.status == -1 }">删除</c:if>
							</display:column>
							<display:column  title="类别" style="text-align:center" sortable="true">
								<c:if test="${systemBugLog.type == 1 }">BUG</c:if>
								<c:if test="${systemBugLog.type == 2 }">日志</c:if>
							</display:column>
							<display:column title="操作" media="html" style="text-align:center">
									<a href="javascript:updateType('${systemBugLog.id }')" >修改</a>&nbsp;&nbsp;
									<a href="javascript:viewType('${systemBugLog.id }')" >详情</a>
							</display:column>
					</display:table>
				</form>
			</center>
		</div>
	</body>
</html>