<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/commons/taglibs.jsp"%>
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
		<title>培训后台</title>
	</head>
<%@include file="/peixun_page/top.jsp"%>
<!-- 内容 -->
<div class="center" style="">
	<div class="ks_biaoti">
		<span class="ml20">${paper.name}</span>
	</div>
	
	<c:set value=""  var="keyAll"        scope="request" />
	
	<c:set value="0" var="radioQuestNum" scope="request" />
	<c:set value="0" var="checkQuestNum" scope="request" />
	<c:set value="0" var="pdQuestNum"    scope="request" />
	<c:set value="0" var="tkQuestNum"    scope="request" />
	<c:set value="0" var="jdQuestNum"    scope="request" />
	<c:set value="0" var="mcjsQuestNum"  scope="request" />
	<c:set value="0" var="nlfxQuestNum"  scope="request" />	
	
	<div class="kaos_shiti">
		<c:forEach items="${quesList}" var="que" varStatus="i">
		<div class="kshi">
			<dl>
				<dt>${i.index+1}. ${que.content}</dt>				
				
				<c:if test="${que.question_label_id eq 1 || que.question_label_id eq 2}">
					<c:set var="radioQuestNum" value="${radioQuestNum + 1 }" />
				</c:if>				
				<c:if test="${que.question_label_id eq 11}">
					<c:set var="checkQuestNum" value="${checkQuestNum + 1 }" />
				</c:if>
				<c:if test="${que.question_label_id eq 13}">
					<c:set var="pdQuestNum" value="${pdQuestNum + 1 }" />
				</c:if>
				
				<c:if test="${que.question_label_id eq 12}">
					<c:set var="tkQuestNum" value="${tkQuestNum + 1 }" />
				</c:if>
				<c:if test="${que.question_label_id eq 14}">
					<c:set var="jdQuestNum" value="${jdQuestNum + 1 }" />
				</c:if>
				<c:if test="${que.question_label_id eq 18}">
					<c:set var="mcjsQuestNum" value="${mcjsQuestNum + 1 }" />
				</c:if>
				<c:if test="${que.question_label_id eq 20}">
					<c:set var="nlfxQuestNum" value="${nlfxQuestNum + 1 }" />
				</c:if>
				
				<c:set var="keyAll" value="" />
				<c:forEach items="${que.questionKeyList}" var="key" varStatus="j">				   
					<dd>
						<c:choose>
							<c:when test="${que.question_label_id eq 1 || que.question_label_id eq 2}">							    								
								<input type="radio" name="dan_${que.id}" <c:if test="${key.isnot_true eq 1}">checked="checked" <c:set var="keyAll" value="${result[j.index]}" /> </c:if>  disabled="disabled"/>
								<em><b>${result[j.index]}.</b>&nbsp;&nbsp;${key.content}</em>								
							</c:when>
							
							<c:when test="${que.question_label_id eq 11}">
								<input type="checkbox" name="dan_${que.id}" <c:if test="${key.isnot_true eq 1}">checked="checked" <c:set var="keyAll">${keyAll} ${result[j.index]}</c:set> </c:if> disabled="disabled"/>
								<em><b>${result[j.index]}.</b>&nbsp;&nbsp;${key.content}</em>
							</c:when>
							
							<c:when test="${que.question_label_id eq 13}">
								<c:if test="${key.isnot_true eq 1}"><c:set var="keyAll" value="正确" /></c:if>
								<c:if test="${key.isnot_true eq 0}"><c:set var="keyAll" value="错误" /></c:if>
								<span></span>
							</c:when>
							
							<c:when test="${que.question_label_id eq 12}">								
								<c:set var="keyAll" value="${key.content}" />
							</c:when>
							<c:when test="${que.question_label_id eq 14}">								
								<c:set var="keyAll" value="${key.content}" />
							</c:when>
							<c:when test="${que.question_label_id eq 18}">								
								<c:set var="keyAll" value="${key.content}" />
							</c:when>
							<c:when test="${que.question_label_id eq 20}">								
								<c:set var="keyAll" value="${key.content}" />
							</c:when>
						</c:choose>
					</dd>
				</c:forEach>
			</dl>
			<div class="mt10 jiexi">
				<p>正确答案：
					<c:out value="${keyAll}" />
				</p>
				<p class="mt5">试题解析： <c:if test="${que.analyse == null}">暂无解析</c:if>${que.analyse}</p>
			</div>
		</div>
		</c:forEach>

	</div>
	<div class="cas_anniu" style="width:90px;margin:30px auto;">
		<a href="javascript:history.go(-1);" class="fl queren" style="width:70px;">返回</a>
	</div>
	<div style="height:70px;"></div>
</div>
<script type="text/javascript">
$(function(){
	
		//select menu
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "lc_left0" + menuindex;
		$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
		
		$('.select').click(function(){
			$('.list').css("display","none");
			$(this).find('ul').show();
			return false;
		});
		
		$('.list li').click(function(){
			var str=$(this).text();
			$(this).parent().parent().find('div').find('b').text(str);
			$(this).parent().find('option').prop('selected', '');
			$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
			$('.list').slideUp(50);
		});
		
		$('.list option:selected').each(function(){
			var str=$(this).text();
			$(this).parent().parent().parent().find('b').text(str);
		});
});
</script>
</html>