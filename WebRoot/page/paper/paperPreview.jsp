<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>预览试卷</title>
		<%@include file="/commons/taglibs.jsp"%>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/fxbg.css" />
        <link rel="stylesheet" type="text/css" href="${ctx}/css/question/exam.css" />
        <link rel="stylesheet" type="text/css" href="${ctx}/css/button.css" />
        
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	</head>

	<body>
		<div>
			<div id="fxbg_center">
				<br />
				<div id="kspj_top">
					<b>试卷信息：</b>
				</div>
				<div id="paper_info">
					<ul>
						<li>
							试卷名称：${name}
						</li>
					</ul>
				</div>
				<div id="fxbg_main">
					<c:forEach items="${quesList}" var="que" varStatus="i">
						<div id="ques_info_${que.id}" class="ques_info">
							<div class="ksts_title1">
								<span id="ques_index_${que.id}" class="ques_index">${i.index+1}</span>.&nbsp;&nbsp;
								<c:choose>
									<c:when test="${que.question_label_id eq 9}">
										<div>
											<c:out value="${que.content}"
											escapeXml="false" />&nbsp;&nbsp;(分数：${que.question_score})&nbsp;&nbsp;试题编号：${que.id}
										</div>
									</c:when>
									<c:otherwise>
										<c:out value="${que.content}"
											escapeXml="false" />&nbsp;&nbsp;(分数：${que.question_score})&nbsp;&nbsp;试题编号：${que.id}
									</c:otherwise>
								</c:choose>
							</div>
							<div id="ques_key">
								<ol>
									<c:forEach items="${que.questionKeyList}" var="key"
										varStatus="j">
										<li>
											<c:choose>
												<c:when
													test="${que.question_label_id eq 1 || que.question_label_id eq 2 ||que.question_label_id eq 9}">
													<span><b>${result[j.index]}.</b>&nbsp;&nbsp;${key.content}</span>
												</c:when>
												<c:when test="${que.question_label_id eq 11}">
													<span><b>${result[j.index]}.</b>&nbsp;&nbsp;${key.content}</span>
												</c:when>
												<c:when
													test="${que.question_label_id eq 19 || que.question_label_id eq 13}">
													<span></span>
												</c:when>
												<c:when test="${que.question_label_id eq 16}">
													<span class="style4">题目：<c:out value="${key.content}" />
													</span>
												</c:when>
											</c:choose>
										</li>
									</c:forEach>
								</ol>
							</div>
							<div id="sub_ques_info">
								<c:forEach items="${que.childQuestionList}" var="child"
									varStatus="j">
									<div id="sub_ques_cont">
										<div id="sub_ques_title">
											<span id="sub_ques_index">${i.index+1}-${j.index+1}</span>.
											<c:out value="${child.content}" escapeXml="false" />
										</div>
										<div id="sub_ques_key">
											<ol>
												<c:forEach items="${child.questionKeyList}" var="key"
													varStatus="k">
													<li>
														<c:if
															test="${child.question_label_id eq 4 || child.question_label_id eq 5 }">
															<span><b>${result[k.index]}.</b>${key.content}</span>
														</c:if>
													</li>
												</c:forEach>
											</ol>
										</div>
										<div id="sub_ques_result">
											<c:if
												test="${child.question_label_id eq 4 || child.question_label_id eq 5 
												||que.question_label_id eq 9}">
												<div id="ques_result_true">
													正确答案:
													<c:if test="${child.question_label_id eq 10}">
														<c:forEach items="${child.questionKeyList}" var="key"
														varStatus="j">
															${key.content}
														</c:forEach>
													</c:if>
													<c:if
												test="${child.question_label_id eq 4 || child.question_label_id eq 5 }">
													<c:forEach items="${child.questionKeyList}" var="key"
														varStatus="j">
														<c:if test="${key.isnot_true eq 1}">${result[j.index]}</c:if>
													</c:forEach>
													</c:if>
												</div>
												<div id="ques_result_anal">
													<b>试题解析:</b>
													<c:if test="${child.analyse == null}">
														暂无解析
													</c:if>
													${child.analyse}
												</div>
											</c:if>
										</div>
									</div>
								</c:forEach>
							</div>
							<div id="ques_result">
								<c:if
									test="${que.question_label_id eq 1 || que.question_label_id eq 2 ||que.question_label_id eq 11}">
									<div id="ques_result_true">
										正确答案:
										<c:forEach items="${que.questionKeyList}" var="key"
											varStatus="j">
											<c:if test="${key.isnot_true eq 1}">${result[j.index]}</c:if>
										</c:forEach>
									</div>
									<div id="ques_result_anal">
										<b>试题解析:</b>
										<c:if test="${que.analyse == null}">
											暂无解析
										</c:if>
										${que.analyse}
									</div>
								</c:if>
								<c:if
									test="${que.question_label_id eq 12 || que.question_label_id eq 14 || que.question_label_id eq 18 || que.question_label_id eq 20}">
									<div id="ques_result_true">
										正确答案:
										<c:forEach items="${que.questionKeyList}" var="key"
											varStatus="j">
											${key.content}
										</c:forEach>
									</div>
									<div id="ques_result_anal">
										<b>试题解析:</b>
										<c:if test="${que.analyse == null}">
											暂无解析
										</c:if>
										${que.analyse}
									</div>
								</c:if>
								<c:if
									test="${que.question_label_id eq 13}">
									<div id="ques_result_true">
										正确答案:
										<c:forEach items="${que.questionKeyList}" var="key"
											varStatus="j">
											<c:if test="${key.isnot_true eq 0}">错误</c:if>
											<c:if test="${key.isnot_true eq 1}">正确</c:if>
										</c:forEach>
									</div>
									<div id="ques_result_anal">
										<b>试题解析:</b>
										<c:if test="${que.analyse == null}">
											暂无解析
										</c:if>
										${que.analyse}
									</div>
								</c:if>
							</div>
						</div>
					</c:forEach>
				</div>
				<div id="kspj_main_bottom" style="text-align: center;">
					<input type="button" class="btn_03s" value="返 回" onclick="window.close();" />
				</div>
			</div>
		</div>
	</body>
</html>
