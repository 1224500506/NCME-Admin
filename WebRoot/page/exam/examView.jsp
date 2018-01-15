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
						查看考试信息
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25" width="20%">
						考试名称：
					</td>
					<td >
					    ${ExamExamination.name}
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25" width="20%">
						所属站点：
					</td>
					<td >
					    ${ExamExamination.aliasName}(${ExamExamination.domainName})
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						所属目录：
					</td>
					<td >
						<c:set var="ExamTypeListSize" value="${fn:length(ExamTypeList) - 1}"></c:set>
				        <c:forEach items="${ExamTypeList}" var="list" varStatus="status">
					        ${list.name}
					        <c:if test="${ExamTypeListSize != status.index }">
					        ->
					        </c:if>
					    </c:forEach>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						考试类别：
					</td>
					<td>
					    <c:if test="${ExamExamination.exam_type eq 1}">考试</c:if>
					    <c:if test="${ExamExamination.exam_type eq 2}">练习</c:if>
					</td>
				</tr>

				<tr>
					<td class="row_tip" height="25">
						练习次数: 
					</td>
					<td>
					    ${ExamExamination.exam_num}
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						考试时长（分钟）: 
					</td>
					<td>
					    <fmt:formatNumber value="${ExamExamination.exam_times / 1000 }" pattern="0"/>
					</td>
				</tr>

				<tr>
					<td class="row_tip" height="25">
						考试时间: 
					</td>
					<td>
					    <c:if test="${ExamExamination.exam_time eq 1}">随时</c:if>
					    <c:if test="${ExamExamination.exam_time eq 2}">固定</c:if>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						创建时间: 
					</td>
					<td>
					    ${fn:substring(ExamExamination.create_time,0,19)}
					</td>
				</tr>				
				<tr>
					<td class="row_tip" height="25">
						开始时间: 
					</td>
					<td>
					    ${fn:substring(ExamExamination.start_time,0,19)}
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						结束时间: 
					</td>
					<td>
					    ${fn:substring(ExamExamination.end_time,0,19)}
					</td>
				</tr>				
				<tr>
					<td class="row_tip" height="25">
						通过标准:
					</td>
					<td>
					    <c:if test="${ExamExamination.pass_condition eq 1}">分数</c:if>
					    <c:if test="${ExamExamination.pass_condition eq 2}">得分率</c:if>					    
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						通过值:
					</td>
					<td>
					   ${ExamExamination.pass_value}
					</td>
				</tr>				
				<tr>
					<td class="row_tip" height="25">
						考生查看测评报告: 
					</td>
					<td>
					    <c:choose>
					        <c:when test="${ExamExamination.isnot_see_test_report eq 1}">允许</c:when>
					        <c:otherwise>不允许</c:otherwise>
					    </c:choose>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						考试状态: 
					</td>
					<td>
					    <c:choose>
					        <c:when test="${ExamExamination.state eq 1}">正常</c:when>
					        <c:when test="${ExamExamination.state eq 2}">禁用</c:when>
					        <c:when test="${ExamExamination.state eq 3}">删除</c:when>
					        <c:otherwise></c:otherwise>
					    </c:choose>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						是否允许切屏: 
					</td>
					<td>
					    <c:choose>
					        <c:when test="${ExamExamination.is_cut_screen eq 1 || empty ExamExamination.is_cut_screen }">允许</c:when>
					        <c:when test="${ExamExamination.state eq 0}">限制</c:when>
					        <c:otherwise></c:otherwise>
					    </c:choose>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						限制切屏次数: 
					</td>
					<td>
					    ${ExamExamination.cut_screen_num }
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						选择试卷: 
					</td>
					<td>
                        <c:forEach items="${ExamExamination.examinPaperList}" var="list" varStatus="status">
                          <c:if test="${status.index >0 }"><br/></c:if>
                          ${list.paper_name}
                        </c:forEach>
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