<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>考试分类管理</title>
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/button.css" rel="stylesheet" type="text/css" />
		<%@include file="/commons/taglibs.jsp"%>
		<style media="all" type="text/css">
			@import url("${ctx}/css/displaytag/site.css");
			@import url("${ctx}/css/displaytag/screen.css");
		</style>
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/bootstrap/easyui.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/icon.css">

		<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/locale/easyui-lang-zh_CN.js"></script>        
	</head>
	<body>
		<div style="padding-left:12px;width:99%;">
			<form action="" method="post" id="fmx" name="fmx">
			<table class="gridtable" cellpadding="1" cellspacing="0" border="0" width="100%">
				<tr>
					<td class="row">分类名称:
						<input type="text" id="queryExamTypeName" name="queryExamTypeName" maxlength="100" value="${param.queryExamTypeName}" />
					</td>
					<td class="row">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td class="row">
						&nbsp;
					</td>
					<td class="row">
						<input type="submit" class="btn_03s" value="查 询"/>
						<input type="button" class="btn_03s" value="添加分类" onclick="javascript:window.location='${ctx}/exam/category/examTypeAdd.do?curTypeId=${param.curTypeId}'" />
						<input type="button" class="btn_03s" value="删除分类" onclick="javascript:deleteExamType();" />
					</td>
				</tr>
			</table>
			</form>
			<br />
		</div>
		<div>
			<center>
				<form id="listfm" name="listfm" method="post" action="${ctx}/exam/category/examTypeDelete.do">
				<display:table id="row" name="${ExamTypeList}" requestURI="${ctx}/course/studyCourseTypeList.do" style="font-size:12px;width:98%;" class="ITS">
					<display:caption>用户查询结果</display:caption>
					<display:column title="<input type='checkbox' name='chkall' id='chkall' value='on' onclick='checkAll(this);'>" style="width:2%;text-align:center">
			            <input type="checkbox" name="ids" value="${row.id}" id="qcid"/>
		            </display:column>

					<display:column property="name" title="考试分类名称" style="text-align:center" />
					<display:column property="remark" title="备注" style="text-align:center" />

					<display:column title="操作" style="text-align:center">
						<input type="button" class="btn_03s" value="修改" onclick="javascript:window.location='${ctx}/exam/category/examTypeUpdate.do?curTypeId=${param.curTypeId}&id=${row.id}'"/>
					</display:column>
				</display:table>
				<input type="hidden" name="curTypeId" value="${param.curTypeId}" />
                <input type="hidden" name="org.apache.struts.taglib.html.TOKEN"  value="${sessionScope['org.apache.struts.action.TOKEN']}" />
				</form>
			</center>
		</div>
<script type="text/javascript">
	<c:if test="${MESSAGE != null && MESSAGE != ''}">
        alert('${MESSAGE}');
        <c:set var="MESSAGE" scope="session" value=""></c:set>
	</c:if>
	<c:if test="${IS_RELOAD_ADD != null}">
	    parent.reloadAdd();
	</c:if>
	<c:if test="${IS_RELOAD_DELETE != null}">
	    parent.reloadDelete();
	</c:if>	
</script>
	</body>
</html>
<script type="text/javascript">
function checkAll(element){
	$("input[name='ids']").each(function(){
		$(this).attr("checked", element.checked) ;
	});
}

function deleteExamType(){
  if($("input[name='ids']:checked").length == 0){
    alert('请选择需要删除的考试分类!');
    return ;
  }

  if(confirm("确认删除!")){
    $("#listfm").submit();
  }
}
</script>
