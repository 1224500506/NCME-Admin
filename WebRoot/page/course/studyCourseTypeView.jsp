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
	</head>
	<body>
			<table border="0" cellspacing="1" cellpadding="0" width="100%" class="gridtable">
				<tr>
					<td align="center" colspan="2" class="row_tip" height="25">
						查看课程分类
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25" width="20%">
						父课程分类名称：
					</td>
					<td >
						<c:set var="StudyCourseTypeListSize" value="${fn:length(StudyCourseTypeList) - 1}"></c:set>
					    <c:forEach items="${StudyCourseTypeList}" var="list" varStatus="status">
					        ${list.courseTypeName}
					        <c:if test="${StudyCourseTypeListSize != status.index }">
					        ->
					        </c:if>
					    </c:forEach>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						课程分类名称：
					</td>
					<td >
						${StudyCourseType.courseTypeName}
					</td>
				</tr>

				<tr>
					<td class="row_tip" height="25">
						顺序：
					</td>
					<td>
						${StudyCourseType.seq}
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						总学时：
					</td>
					<td>
						${StudyCourseType.allClassHours}
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						类型：
					</td>
					<td>
					    <c:if test="${StudyCourseType.type eq 1}"><c:out value="培训课程级" ></c:out></c:if>
					    <c:if test="${StudyCourseType.type eq 2}"><c:out value="分类级"     ></c:out></c:if>
					</td>
				</tr>
				<tr>
					<td colspan="6" align="center" class="row_tip">
						<input type="button" class="btn_03s" value="返 回" onclick="history.go(-1)" />
					</td>
				</tr>
			</table>
	</body>
</html>