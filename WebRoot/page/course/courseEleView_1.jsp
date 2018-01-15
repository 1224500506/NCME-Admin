<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<%@include file="/commons/taglibs.jsp"%>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/fxbg.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css" />
		
		<link href="${ctx}/js/UI/themes/gray/easyui.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/js/UI/themes/icon.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="${ctx}/js/UI/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
		
	</head>
	<body>
		<div id="container">

			<div id="content">
				<div class="warp">
					
					<div id="main_cont" style="width:860px;">
						<div style="text-align:center;font-size:14px;padding-bottom:10px;">
							本课件共含课件：&nbsp;<strong>${curEle.wareNumber}</strong>&nbsp;&nbsp;个，
							练习&nbsp;<strong>${curEle.quesNumber}</strong>&nbsp;&nbsp;套。
							<!-- 
								课件时长：&nbsp;<strong>6</strong>&nbsp;&nbsp;分钟。
							 -->
						</div>

						<c:set value="0" var="curIndex"></c:set>
						<div id="tab_ui" class="easyui-tabs">
							<c:forEach items="${curEle.eleList}" var="ele" varStatus="eleStatus">
								<c:choose>
									<c:when test="${ele.courseElementType >= 11}">
										<div id="tabs-${eleStatus.index+1}" 
											<c:choose>
												<c:when test="${ele.courseElementType eq 11}">
													title="课&nbsp;前&nbsp;练&nbsp;习&nbsp;"		
												</c:when>
												<c:when test="${ele.courseElementType eq 12}">
													title="课&nbsp;中&nbsp;练&nbsp;习&nbsp;"		
												</c:when>
												<c:when test="${ele.courseElementType eq 13}">
													title="课&nbsp;后&nbsp;练&nbsp;习&nbsp;"		
												</c:when>
											</c:choose>	
										style="overflow:hidden;"></div>
									</c:when>
									<c:otherwise>
										<c:if test="${curIndex eq 0}">
											<div id="tabs-${eleStatus.index+1}" title="学&nbsp;习&nbsp;课&nbsp;件" style="overflow:hidden;"></div>
										</c:if>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							
						</div>
						
						
						
						<div id="fxbg_center">
							<div id="kspj_top">
								<b>试题信息：</b>
							</div>
							<div id="fxbg_main">
								<c:forEach items="${retList}" var="que" varStatus="i">
									<div id="ques_info" class="ques_info">
										<div class="ksts_title1">
											<span>${i.index+1}.&nbsp;&nbsp;
												<c:choose>
													<c:when test="${que.question_label_id eq 9}">
														<div>
															<c:out value="${que.content}" escapeXml="false" />&nbsp;&nbsp;(分数：${que.question_score})&nbsp;&nbsp;试题编号：${que.id}
														</div>
													</c:when>
													<c:otherwise>
														<c:out value="${que.content}" escapeXml="false" />&nbsp;&nbsp;(分数：${que.question_score})&nbsp;&nbsp;试题编号：${que.id}
													</c:otherwise>
												</c:choose>
											</span>
										</div>
										<div id="ques_key">
											<ol>
												<c:forEach items="${que.questionKeyList}" var="key" varStatus="j">
													<li>
														<c:choose>
															<c:when test="${que.question_label_id eq 1 || que.question_label_id eq 2 ||que.question_label_id eq 9}">
																<span><b>${result[j.index]}.</b>&nbsp;&nbsp;${key.content}</span>
															</c:when>
															<c:when test="${que.question_label_id eq 11}">
																<span><b>${result[j.index]}.</b>&nbsp;&nbsp;${key.content}</span>
															</c:when>
															<c:when test="${que.question_label_id eq 19 || que.question_label_id eq 13}">
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
											<c:forEach items="${que.childQuestionList}" var="child"	varStatus="j">
												<div id="sub_ques_cont">
													<div id="sub_ques_title">
														<span><b>${i.index+1}-${j.index+1}.</b>
														<c:out value="${child.content}" escapeXml="false" /></span>
													</div>
													<div id="sub_ques_key">
														<ol>
															<c:forEach items="${child.questionKeyList}" var="key" varStatus="k">
																<li>
																	<c:if test="${child.question_label_id eq 4 || child.question_label_id eq 5 }">
																		<span><b>${result[k.index]}.</b>${key.content}</span>
																	</c:if>
																</li>
															</c:forEach>
														</ol>
													</div>
													<div id="sub_ques_result">
														<c:if test="${child.question_label_id eq 4 || child.question_label_id eq 5 || que.question_label_id eq 9}">
															<div id="ques_result_true">
																正确答案:
																<c:if test="${child.question_label_id eq 10}">
																	<c:forEach items="${child.questionKeyList}" var="key" varStatus="j">
																		${key.content}
																	</c:forEach>
																</c:if>
																<c:if test="${child.question_label_id eq 4 || child.question_label_id eq 5 }">
																	<c:forEach items="${child.questionKeyList}" var="key" varStatus="j">
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
											<c:if test="${que.question_label_id eq 1 || que.question_label_id eq 2 ||que.question_label_id eq 11}">
												<div id="ques_result_true">
													正确答案:
													<c:forEach items="${que.questionKeyList}" var="key" varStatus="j">
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
											<c:if test="${que.question_label_id eq 12 || que.question_label_id eq 14 || que.question_label_id eq 18 || que.question_label_id eq 20}">
												<div id="ques_result_true">
													正确答案:
													<c:forEach items="${que.questionKeyList}" var="key" varStatus="j">
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
											<c:if test="${que.question_label_id eq 13}">
												<div id="ques_result_true">
													正确答案:
													<c:forEach items="${que.questionKeyList}" var="key" varStatus="j">
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
							
							<div style="text-align:center;height:50px;padding-top:10px;z-index:1000;">
								<br />
								<c:set var="allIndex" value="${curEle.wareNumber + curEle.quesNumber}"></c:set>
									
								<c:if test="${eleIndex > 1 && eleIndex <= allIndex}">
									<input type="button" class="btn_03s" value="上一步" onclick="selectType('${eleIndex-1}')" />&nbsp;&nbsp;&nbsp;	
								</c:if>
								<c:if test="${eleIndex > 0 && eleIndex < allIndex}">
									<input type="button" class="btn_03s" value="下一步" onclick="selectType('${eleIndex+1}')" />
								</c:if>
							</div>
							
							<br /><br />
						</div>
						
					</div>
				</div>
			</div>
		<form id="subForm" name="subForm" method="post">
			<input type="hidden" id="urlId" name="urlId" value="${param.urlId}" />
			<input type="hidden" id="curTypeId" name="curTypeId" value="${param.curTypeId}" />
			<input type="hidden" id="eleIndex" name="eleIndex" />
			<input type="hidden" id="id" name="id" value="${param.id}" />
		</form>
		</div>
		<script type="text/JavaScript">
			function selectType(eleIndex){
				var temForm = document.subForm ; 
				temForm.eleIndex.value = eleIndex ;
				temForm.action = "${ctx}/course/studyCoursePreview.do" ;
				temForm.submit() ;
			}
			
			$(document).ready(function(){
				var tabs = $('#tab_ui').tabs('tabs');
				for(var i = 0; i < tabs.length; i++){
					tabs[i].panel('options').tab.unbind();
				}
				
				var eleIndex = '${param.eleIndex eq null ? 1 : param.eleIndex}' ;
				$("#tab_ui").tabs("select", eleIndex-1);
			});
		</script>
	</body>
</html>