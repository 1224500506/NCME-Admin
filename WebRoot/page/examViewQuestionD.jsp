<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
</head>
<table border="0" cellspacing="1" cellpadding="0" width="100%" class="gridtable">
	<tr>
		<td align="center" colspan="4" class="row_tip" height="25">
			添加试题
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			试题题型：
		</td>
		<td width="200">
			<c:choose>
				<c:when test="${quest.question_label_id eq 1}">单选题(A1)</c:when>
				<c:when test="${quest.question_label_id eq 2}">单选题(A2)</c:when>
				<c:when test="${quest.question_label_id eq 3}">单选题(A3/A4)</c:when>
				<c:when test="${quest.question_label_id eq 9}">单选题(B1)</c:when>
				<c:when test="${quest.question_label_id eq 11}">多选题(X)</c:when>
				<c:when test="${quest.question_label_id eq 12}">填空题</c:when>
				<c:when test="${quest.question_label_id eq 13}">判断题</c:when>
				<c:when test="${quest.question_label_id eq 14}">简答题</c:when>
				<c:when test="${quest.question_label_id eq 18}">名词解释</c:when>
				<c:when test="${quest.question_label_id eq 20}">案例分析题</c:when>
			</c:choose>
		</td>
		<td class="row_tip" height="25">试题难度：</td>
		<td>${quest.grade}</td>		
	</tr>
	<tr>
		<td class="row_tip" height="25">
			试题区分度：
		</td>
		<td>
			${quest.differ}
		</td>
		<td class="row_tip">
			试题来源：
		</td>
		<td>
			${quest.source}
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">是否为多媒体试题：</td>
		<td colspan=3>
			<c:if test="${quest.isnot_multimedia eq 0}">否</c:if>
			<c:if test="${quest.isnot_multimedia eq 1}">是</c:if>		
		</td>
	</tr>	
	<!-- <tr>
		<td class="row_tip">
			试题属性：
		</td>
		<td colspan="3">
			<%request.setAttribute("vEnter", "\n"); %>
			<c:set var ="re" value="${fn:replace(relationPropList,'+',vEnter)}"></c:set>
			<textarea class="inp_txt inp_txtsel inp_wm inp_cue" id="txtLoc" name="txtLoc" style="width: 92%" rows="3" readonly="readonly"><c:out value="${re}"/></textarea>
		</td>
	</tr> -->
	<tr>
		<td colspan="4">
			<table border="0" cellspacing="0" cellpadding="0" width="100%" class="gridtable">
				<tr>
					<td class="row_tip">试题分类：</td>
					<td>
						<select multiple="multiple" size="4" id="_questLib_9"  class="stcx_form">
							<c:forEach items="${quest.subSysQuestTypeList}" var="types">
								<option value="${types.sub_type_id}">${types.sub_type_name}</option>
							</c:forEach>							
						</select>							
					</td>
					<td class="row_tip">行业：</td>
					<td>
						<select multiple="multiple" size="4" id="_questPoint2_7"  class="stcx_form">
							<c:forEach items="${quest.questionPropMap['7']}" var="point2">
								<option value="${point2.prop_val_id}">${point2.prop_val_name}</option>
							</c:forEach>
						</select>						
					</td>
				</tr>
				<tr>				
					<td class="row_tip">知识分类：</td>
					<td>
						<select multiple="multiple" size="4" id="_questCognize_8"  class="stcx_form">
							<c:forEach items="${quest.questionPropMap['8']}" var="cognize">
								<option value="${cognize.prop_val_id}">${cognize.prop_val_name}</option>
							</c:forEach>												
						</select>		
					</td>	
					<td class="row_tip">适用对象：</td>
					<td>
						<select multiple="multiple" size="4" id="_questCognize_8"  class="stcx_form">
							<c:forEach items="${quest.questionPropMap['9']}" var="title">
								<option value="${title.prop_val_id}">${title.prop_val_name}</option>
							</c:forEach>												
						</select>		
					</td>										
			</table>
		</td>
	</tr>
	<tr>
		<td class="row_tip">
			试题内容：
		</td>
		<td colspan="3">
			<textarea style="width: 92%" rows="3" readonly="readonly">${quest.content}</textarea>
		</td>
	</tr>
	<tr>
		<td class="row_tip">
			候选项：
		</td>
		<td colspan="3">
			<c:forEach var="qk" items="${quest.questionKeyList}">
			<input type="hidden" value="${qk.id}" name="questionkey_id"/>
			<input type="radio" value="1" name="answer_key" id="answer_key" <c:if test="${qk.isnot_true eq 1}">checked="checked"</c:if> <c:if test="${qk.isnot_true eq 0}">disabled="disabled"</c:if> />正确 &nbsp; 
			<input type="radio" value="2" name="answer_key" id="answer_key" <c:if test="${qk.isnot_true eq 0}">checked="checked"</c:if>  <c:if test="${qk.isnot_true eq 1}">disabled="disabled"</c:if> />错误
			</c:forEach>
		</td>
	</tr>	
	<tr>
		<td class="row_tip">
			试题分析：
		</td>
		<td colspan="3">
			<textarea readonly="readonly" style="width: 92%" class="editTextarea" rows="3" elname="试题分析" pattern="string">${quest.analyse}</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="4" align="center" class="row_tip">
			<c:if test="${handle == 'ads_v'}">
				<img src="${ctx}/images/xjsj_fh.gif" onclick="window.location='${ctx}/questionManage/adSearchQuestion.do?handle=list'" style="cursor: pointer;"/>&nbsp;&nbsp;
			</c:if>
			<c:if test="${handle == 'v'}">
				<img src="${ctx}/images/xjsj_fh.gif" onclick="window.location='${ctx}/questionManage/questionManage.do'" style="cursor: pointer;"/>&nbsp;&nbsp;
			</c:if>			
		</td>
	</tr>
</table>
	</body>
</html>