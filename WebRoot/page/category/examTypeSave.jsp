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
		<form class="fm1" id="fm1" name="fm1" action="${ctx}/exam/category/examTypeSave.do" method="post">
			<input type="hidden" id="curTypeId" name="curTypeId" value="${param.curTypeId}" />
			<input type="hidden" id="id" name="id" value="${ExamType.id}" />
			<input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="${sessionScope['org.apache.struts.action.TOKEN']}" />
			<table border="0" cellspacing="1" cellpadding="0" width="100%" class="gridtable">
				<tr>
					<td align="center" colspan="3" class="row_tip" height="25">
						考试分类信息
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						父考试分类名称：
					</td>
					<td width="20%">
						<c:set var="ExamExaminTypeSize" value="${fn:length(ExamExaminTypeList) - 1}"></c:set>
					    <c:forEach items="${ExamExaminTypeList}" var="list" varStatus="status">
					        ${list.name}
					        <c:if test="${ExamExaminTypeSize != status.index }">
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
						<font class="red_star">*</font>考试分类名称：
					</td>
					<td width="20%">
						<input type="text" id="examinTypeName" name="examinTypeName" class="editInput" maxlength="100" size="25" value="${ExamType.name}" datatype="s1-100" nullmsg="请输入考试分类名称!" />
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>

				<tr>
					<td class="row_tip" height="25">
						备注：
					</td>
					<td>
						<textarea rows="5" cols="50" id="remark" name="remark" ignore="ignore" datatype="*1-100" errormsg="备注最多100个字符！">${ExamType.remark}</textarea>
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