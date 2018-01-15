
<%@page import="com.hys.exam.model.ExamPaper"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ page contentType="application/msword;charset=utf-8"%>
		<%@include file="/commons/taglibs.jsp"%>
		<title>三基考试</title>
		<style type="text/css">

body {
	height: 100%;
	margin: 0px;
	padding: 0px;
	font-size: 13px;
	color: #000000;
	bgcolor: #f5faff;
}
.style4 {
	margin-rihgt: 0px
}
.ksts_title1 {
	font-weight: bold;
}

.r_siderbar_1 {
	margin: 0;
	padding: 0;
	background: url(images/r_siderbar_1.gif) no-repeat center top;
	height: 316px;
}
</style>
	</head>
	<%
		ExamPaper paper = (ExamPaper)request.getAttribute("paper") ;
		String paperName = "测试试卷" ; 	
		if(paper != null){
			paperName = paper.getName() ;
		}
		paperName = new String(paperName.getBytes("gbk"), "iso-8859-1") + ".doc" ;
		response.setHeader("Content-disposition", "attachment; filename="+paperName);
	%>
	<body>

		<div align="center">
			<b><font size="5">${paper.name}</font> </b>
		</div>
		<br />
		<br />
		<div align="center">
			姓名：______________学号：________________成绩：_________总分:${paper.paper_score}
		</div>
		<br />
		<div style="margin-left: 30px;">
			<div>
				<c:if test="${jieshi_list!=null}">
					<br />
					<br />
					<font size="5">名词解释</font>
					<br />
					<br />
					<c:forEach items="${jieshi_list}" var="que" varStatus="i">
						<div class="ksts_title1">
							${i.index+1}.
							<c:out value="${que.content}" escapeXml="false" />&nbsp;${que.question_score}分
						</div>
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
					</c:forEach>
				</c:if>
				<c:if test="${tiankong_list!=null}">
					<br />
					<br />
					<font size="5">填空题</font>
					<br />
					<br />
					<c:forEach items="${tiankong_list}" var="que" varStatus="i">
						<div>
							${i.index+1}.
							<c:out value="${que.content}" escapeXml="false" />&nbsp;${que.question_score}分
						</div>
						<br />
					</c:forEach>
				</c:if>
				<c:if test="${danxuan_list!=null}">
					<font size="5">单选题</font>
					<br />
					<br />

					<c:forEach items="${danxuan_list}" var="que" varStatus="i">
						<div>
							<c:if
								test="${ que.question_label_id eq 1 || que.question_label_id eq 2 ||que.question_label_id eq 3}">
								<div class="ksts_title1">
									${i.index+1}.
									<c:out value="${que.content}" escapeXml="false" />
									<c:if
										test="${ que.question_label_id eq 1 || que.question_label_id eq 2}">
										&nbsp;<span class="style4">(&nbsp;&nbsp;&nbsp;)</span>
									</c:if>
									&nbsp;${que.question_score}分
								</div>
							</c:if>
							<c:if test="${ que.question_label_id eq 9}">
								${i.index+1}.<br/>
								<div class="ksts_title1" style="margin-left: 20px;">
									<c:out value="${que.content}" escapeXml="false" />&nbsp;${que.question_score}分
								</div>
							</c:if>
							<div style="line-height: 5px">
								<br />
							</div>

							<c:forEach items="${que.questionKeyList}" var="key" varStatus="j">
							&nbsp;&nbsp;${result[j.index]}.${key.content}
							<br />
							</c:forEach>

							<c:forEach items="${que.childQuestionList}" var="child"
								varStatus="j">
								<div>
									<c:if
										test="${ child.question_label_id eq 4  || child.question_label_id eq 7 || child.question_label_id eq 10}">
										&nbsp;&nbsp;${i.index+1}-${j.index+1}.<c:out
											value="${child.content}" escapeXml="false" />&nbsp;&nbsp;
										(&nbsp;&nbsp;&nbsp;)&nbsp;&nbsp;
									</c:if>
									<br />
									<c:forEach items="${child.questionKeyList}" var="key"
										varStatus="k">
										<c:if
											test="${ child.question_label_id eq 4  || child.question_label_id eq 7 }">
										&nbsp;&nbsp;&nbsp;
										${result[k.index]}.${key.content}<br />
										</c:if>
									</c:forEach>

									<c:forEach items="${child.questionKeyList}" var="key"
										varStatus="k">
										<c:if
											test="${ child.question_label_id eq 1 || child.question_label_id eq 2 || child.question_label_id eq 3}">
									&nbsp;&nbsp;&nbsp;${result[k.index]}.${key.content}
								    </c:if>
									</c:forEach>
									<br />
								</div>
							</c:forEach>
						</div>
						<br />
					</c:forEach>
				</c:if>
				<c:if test="${duoxuan_list!=null}">
					<br />
					<br />
					<font size="5">多选题</font>
					<br />
					<br />
					<c:forEach items="${duoxuan_list}" var="que" varStatus="i">
						<div>
							<div class="ksts_title1">
								${i.index+1}.
								<c:out value="${que.content}" escapeXml="false" />
								&nbsp;(&nbsp;&nbsp;&nbsp;)&nbsp;${que.question_score}分
							</div>
								<br />
								<c:forEach items="${que.questionKeyList}" var="key"
									varStatus="j">
							&nbsp;&nbsp;&nbsp;${result[j.index]}.${key.content}
							<br />
								</c:forEach>

								<c:forEach items="${que.childQuestionList}" var="child"
									varStatus="j">
									<div>
										&nbsp;&nbsp;&nbsp;${i.index+1}-${j.index+1}.
										<c:out value="${child.content}" escapeXml="false" />
										&nbsp;&nbsp; (&nbsp;&nbsp;&nbsp;)&nbsp;&nbsp;
										<br />
									</div>
									<br />
								</c:forEach>
						</div>
						<br/>
					</c:forEach>
				</c:if>
				<c:if test="${panduan_list!=null}">
					<br />
					<br />
					<font size="5">判断题</font>&nbsp;&nbsp;(请将正确答案打√，错误打╳)
					<br />
					<br />
					<c:forEach items="${panduan_list}" var="que" varStatus="i">
						<div>
							${i.index+1}.
							<c:out value="${que.content}" escapeXml="false" />
							&nbsp;&nbsp;&nbsp;(&nbsp;&nbsp;&nbsp;)&nbsp;${que.question_score}分
						</div>
						<br />
					</c:forEach>
				</c:if>
				<c:if test="${jianda_list!=null}">
					<br />
					<br />
					<font size="5">简答题</font>
					<br />
					<br />
					<c:forEach items="${jianda_list}" var="que" varStatus="i">
						<div class="ksts_title1">
							${i.index+1}.
							<c:out value="${que.content}" escapeXml="false" />&nbsp;${que.question_score}分
						</div>
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
					</c:forEach>
				</c:if>
				
				<c:if test="${anli_list!=null}">
					<br />
					<br />
					<font size="5">案例分析</font>
					<br />
					<br />
					<c:forEach items="${anli_list}" var="que" varStatus="i">
						<div class="ksts_title1">
							${i.index+1}.
							<c:out value="${que.content}" escapeXml="false" />&nbsp;${que.question_score}分
						</div>
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
					</c:forEach>
				</c:if>
			</div>
		</div>

	</body>
</html>
