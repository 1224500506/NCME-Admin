<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>课程管理</title>
		<%@include file="/commons/taglibs.jsp"%>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css" />

		<script type="text/javascript" src="${ctx}/js/UI/jquery-1.8.0.min.js"></script>
		
		<style media="all" type="text/css">
			@import url("${ctx}/css/displaytag/site.css");
			@import url("${ctx}/css/displaytag/screen.css");
		</style>
	</head>
	<body>
		<div style="overflow:auto;width:1255px;">
		<form action="${ctx}/course/studyCurWareList.do" method="post" name="fmx">
			<table class="gridtable" width="100%" cellpadding="1" cellspacing="0" border="0">
				<tr>
					<td class="row">
						课件名称:
						<input type="text" name="queryName" id="queryName" maxlength="100" value="${param.queryName}">
					</td>
					<td class="row">
						授课老师:
						<input type="text" name="queryTeacherName" id="queryTeacherName" maxlength="20" value="${param.queryTeacherName}">
					</td>
				</tr>
				<tr>
					<td class="row">
						关键字:&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text" name="queryKeyWord" id="queryKeyWord" maxlength="250" value="${param.queryKeyWord}" />
					</td>
					<td class="row">
						制作年份:
						<input type="checkbox" name="queryYear" id="queryYear" value="2014" <c:if test="${param.queryYear eq '2014'}">checked</c:if>/><label for="queryYear">2014</label>
					</td>
				</tr>
				<tr>
					<td class="row" colspan="4">行业:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<c:forEach items="${industryList}" var="list" varStatus="status">
							<input type="checkbox" name="industryId" id="industryId_${list.id}" value="${list.id}"
							<c:forEach items="${industryArray}" var="studyCoursewareList">	
							<c:if test="${list.id eq studyCoursewareList}">checked</c:if>
							</c:forEach>
							/>
						<label for="industryId_${list.id}">${list.name}</label>
						<c:if test="${status.index % 8 eq 7}"><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
					</c:forEach>
					</td>
				</tr>
				<tr>
					<td class="row" colspan="4">适用对象:
					<c:forEach items="${applicableList}" var="list" varStatus="status">
						<input type="checkbox" name="applicableId" id="applicableId_${list.id}" value="${list.id}"
						<c:forEach items="${applicableArray}" var="studyCoursewareList">	
						<c:if test="${list.id eq studyCoursewareList }">checked</c:if>
						</c:forEach>
						/>
						<label for="applicableId_${list.id}">${list.name}</label>
						<c:if test="${status.index % 8 eq 7}"><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
					</c:forEach>
					</td>
				</tr>
				<tr>
					<td class="row" colspan="4">知识分类:
					<c:forEach items="${knowList}" var="list">
					<input type="checkbox" name="knowId" id="knowId_${list.id}" value="${list.id}"
							<c:forEach items="${knowArray}" var="studyCoursewareList">	
					<c:if test="${list.id eq studyCoursewareList}">checked</c:if>
					</c:forEach>
					/>
					<label for="knowId_${list.id}">${list.name}</label>
					</c:forEach>
					</td>
				</tr>
				<tr>
					<td class="row"></td>
					<td class="row">
						<input type="submit" class="btn_03s" value="查询课件" />&nbsp;&nbsp;
						<input type="button" class="btn_03s" value="新增课件" onclick="addCurWare()" />
					</td>
				</tr>
			</table>
			<input type="hidden" name="curId" id="curId" value="${param.curId}">
		</form>
		<form id="formA" name="formA" method="post" action="">
			<input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="${sessionScope['org.apache.struts.action.TOKEN']}" />
			<display:table name="${StudyCoursewareList}" id="qt" requestURI="${ctx}/course/studyCurWareList.do" style="width:100%;" class="its">
				<display:column title="<input type='checkbox' name='chkall' id='chkall' onclick='checkAll(this);'>" style="width:2%;text-align:center">
					<input type="checkbox" id="qcid" name="elements[${qt_rowNum-1}].eleWareId" value="${qt.id}" />
					<input type="hidden" id="courseId" name="elements[${qt_rowNum-1}].courseId" value="${param.curId}" />
					<input type="hidden" id="courseId" name="elements[${qt_rowNum-1}].courseElementType" value="${qt.type}" />
				</display:column>
				<display:column title="课件名称" property="name" style="width:300;text-align:center">
				</display:column>
				<display:column title="行业" style="width:120;text-align:center">
					<c:forEach items="${qt.industryList}" var="list">
						${list.name}&nbsp;&nbsp;
					</c:forEach>
				</display:column>
				<display:column title="知识分类" style="width:120;text-align:center">
					<c:forEach items="${qt.knowList}" var="list">
						${list.name}&nbsp;&nbsp;
					</c:forEach>
				</display:column>
				<display:column title="授课老师" property="teacherName" style="width:120;text-align:center">
				</display:column>
				<display:column title="时长" property="times" style="width:50;text-align:center">
				</display:column>		
				<display:column title="类型" style="width:100;text-align:center">
				<c:if test="${qt.type eq 1}">单屏课程</c:if>
				<c:if test="${qt.type eq 2}">三屏课程</c:if>
				<c:if test="${qt.type eq 3}">flash课程</c:if>
				<c:if test="${qt.type eq 4}">静态页面</c:if>
				</display:column>
				<display:column title="制作年份" property="makeYear" style="width:50;text-align:center">
				</display:column>
				<display:column title="操作" style="width:120;text-align:center">
					<a href="${ctx}/course/coursewareView.do?id=${qt.id}">查看</a> 
				</display:column>
			</display:table>
		</form>
		</div>
		<script type="text/javascript">
			function checkAll(element){
				$("input[name^='elements'][type='checkbox']").each(function(){
					$(this).attr("checked", element.checked) ;
				});
			}
			
			function addCurWare(){
				var checks = $("input[name^='elements'][type='checkbox']:checked") ;
				if(checks.length == 0){
					alert("请您至少选择一个需要添加的组件信息!!!") ;
					return false ;
				}
				//parent.reloadPage();
				document.formA.action = "${ctx}/course/studyCurWareAdd.do" ;
				document.formA.submit() ;
			}
			
			if('${succFlag}' != null && '${succFlag}' != ''){
				parent.reloadPage();
			}
		</script>
	</body>
</html>