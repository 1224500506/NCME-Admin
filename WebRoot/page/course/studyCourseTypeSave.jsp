<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<%@include file="/commons/taglibs.jsp"%>
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/default090909.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/lay.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">

		<link rel="stylesheet" href="${ctx}/js/Validform_v5.3/css/valid.css" type="text/css" media="all" />
		<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/Validform_v5.3.js"></script>
	</head>
	<body>
		<form class="fm1" id="fm1" name="fm1" action="${ctx}/course/studyCourseTypeSave.do" method="post">
			<input type="hidden" id="curTypeId" name="curTypeId" value="${param.curTypeId}" />
			<input type="hidden" id="id" name="id" value="${StudyCourseType.id}" />
			<input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="${sessionScope['org.apache.struts.action.TOKEN']}" />
			<table border="0" cellspacing="1" cellpadding="0" width="100%" class="gridtable">
				<tr>
					<td align="center" colspan="3" class="row_tip" height="25">
						课程分类信息
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						父课程分类名称：
					</td>
					<td width="20%">
						<c:set var="StudyCourseTypeListSize" value="${fn:length(StudyCourseTypeList) - 1}"></c:set>
					    <c:forEach items="${StudyCourseTypeList}" var="list" varStatus="status">
					        ${list.courseTypeName}
					        <c:if test="${StudyCourseTypeListSize != status.index }">
					        ->
					        </c:if>
					    </c:forEach>
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>课程分类名称：
					</td>
					<td width="20%">
						<input type="text" id="courseTypeName" name="courseTypeName" class="editInput" maxlength="100" size="25" value="${StudyCourseType.courseTypeName}" datatype="*1-100" nullmsg="请输入课程分类名称!" />
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>

				<tr>
					<td class="row_tip" height="25">
						顺序：
					</td>
					<td>
						<input type="text" id="seq" name="seq" class="editInput" maxlength="10" size="25" value="${StudyCourseType.seq}" ignore="ignore" datatype="n" errormsg="请输入数字类型的顺序！" />
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						总学时：
					</td>
					<td>
						<input type="text" id="allClassHours" name="allClassHours" class="editInput" maxlength="10" size="25" value="${StudyCourseType.allClassHours}"  ignore="ignore" datatype="n" errormsg="请输入数字类型的总学时！"/>
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>

				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>类型：
					</td>
					<td>
					<select name="type" id="type" datatype="*" nullmsg="请选择课程分类类型！" errormsg="请选择课程分类类型！">
		              <option value="">请选择
			          <option value="1" <c:if test="${StudyCourseType.type eq 1}"><c:out value="selected" escapeXml="false"></c:out></c:if>>培训课程级
			          <option value="2" <c:if test="${StudyCourseType.type eq 2}"><c:out value="selected" escapeXml="false"></c:out></c:if>>分类级
		            </select>
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td colspan="6" align="center" class="row_tip">
						<input type="submit" class="btn_03s" value="保 存" />
						<input type="button" class="btn_03s" value="返 回" onclick="history.go(-1)" />
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">
			$(function() {
		       $(".fm1").Validform();
		    })
		</script>
	</body>
</html>