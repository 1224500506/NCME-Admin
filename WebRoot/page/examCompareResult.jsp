<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
</style>
</head>
<body>
<table>
	<tr>
		<td>
			<display:table name="resultList" id="qt" 
			style="width:100%;" 
			class="its" 
			pagesize="50" 
			requestURI="${ctx}/questionManage/compareQuestion.do">
				<display:column title="题型" style="width:120px;text-align:center">
				<c:choose>
					<c:when test="${qt.question_label_id eq 1}">单选题(A1)</c:when>
					<c:when test="${qt.question_label_id eq 2}">单选题(A2)</c:when>
					<c:when test="${qt.question_label_id eq 3}">单选题(A3/A4)</c:when>
					<c:when test="${qt.question_label_id eq 9}">单选题(B1)</c:when>
					<c:when test="${qt.question_label_id eq 11}">多选题(X)</c:when>
					<c:when test="${qt.question_label_id eq 12}">填空题</c:when>
					<c:when test="${qt.question_label_id eq 13}">判断题</c:when>
					<c:when test="${qt.question_label_id eq 14}">简答题</c:when>
					<c:when test="${qt.question_label_id eq 18}">名词解释</c:when>
					<c:when test="${qt.question_label_id eq 20}">案例分析题</c:when>
				</c:choose>
				</display:column>				
				<display:column title="内容" style="white-space:normal;word-break:break-all;overflow:hidden;text-align: left;">
					<c:choose>
						<c:when test="${qt.state==99}">
							<c:choose>
								<c:when test="${fn:length(qt.content)>30}">
									<c:out value="${fn:substring(qt.content,0,30)}..." escapeXml="true" />
									--第 ${qt.id} 行
								</c:when>
								<c:otherwise>
									<c:out value="${qt.content}" escapeXml="true" />
										--第 ${qt.id} 行
								</c:otherwise>
							</c:choose>					
						</c:when>
					</c:choose>	
				</display:column>				
				<display:column title="状态" style="width:100;text-align:center">
					<c:if test="${qt.state==1}">正常</c:if>
					<c:if test="${qt.state==2}">禁用</c:if>
					<c:if test="${qt.state==3}">已过期</c:if>
					<c:if test="${qt.state==4}">已审核</c:if>
					<c:if test="${qt.state==5}">未审核</c:if>
					<c:if test="${qt.state==6}">导入试题</c:if>
					<c:if test="${qt.state==99}">比对试题</c:if>
				</display:column>
				<display:column title="导入时间" style="width:120px;text-align:center">${qt.create_date}</display:column>
				<display:column title="创建者" style="width:100px;text-align:center">${qt.author}
				</display:column>				
			</display:table>		
		</td>
	</tr>
</table>
</body>
</html>