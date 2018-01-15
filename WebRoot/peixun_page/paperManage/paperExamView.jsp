<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.hys.exam.model.SystemUser" %>
<%@ page import="com.hys.exam.common.util.LogicUtil" %>
<%
	SystemUser user = LogicUtil.getSystemUser(request);
	String username = "";
	if(null != user){
		if(null != user.getRealName()){
			username = user.getRealName();
		}
	}
%>
<!DOCTYPE HTML>
<html>
	<head>
	<%@include file="/commons/taglibs.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
		<title>培训后台</title>
		<link rel="stylesheet" href="${ctx}/peixun_page/css/reset.css" />
		<link rel="stylesheet" href="${ctx}/peixun_page/css/index.css" />
		<link rel="stylesheet" href="${ctx}/peixun_page/css/pickadate/default.css" />
		<link rel="stylesheet" href="${ctx}/peixun_page/css/pickadate/default.date.css" />
		<link rel="stylesheet" href="${ctx}/peixun_page/css/fontawesome/css/font-awesome.css" />
		<link rel="stylesheet" href="${ctx}/peixun_page/css/ueditor.min.css" />
		
		<script type="text/javascript" src="${ctx}/peixun_page/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/layer/layer.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/picker.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/picker.date.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/legacy.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/ueditor/ueditor.config.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/ueditor/ueditor.all.min.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/ueditor/lang/zh-cn/zh-cn.js"></script>
		<style>
			input[type=text]{padding:0 5px;}
			input:disabled{background:#f0f0f0;color:#ccc;}
			.center{margin:15px auto 0;}
		</style>
	</head>
<%-- <%@include file="/peixun_page/top.jsp"%> --%>
<!-- 内容 -->
<div class="center" style="">
	<div class="ks_biaoti">
		<span class="ml20">${paper.name}</span>
	</div>
	<div class="kaos_shiti">
		<c:forEach items="${quesList}" var="que" varStatus="i">
		<div class="kshi">
			<dl>
				<dt>${i.index+1}. ${que.content}</dt>
				<c:forEach items="${que.questionKeyList}" var="key" varStatus="j">
					<dd>
						<c:choose>
							<c:when
								test="${que.question_label_id eq 1 || que.question_label_id eq 2 ||que.question_label_id eq 9}">
								<input type="radio" name="dan_${que.id}" disabled="disabled"/>
								<%-- <input type="radio" name="dan_${que.id}" <c:if test="${key.isnot_true eq 1}">checked="checked"</c:if> disabled="disabled"/> --%>
								<em><b>${result[j.index]}.</b>&nbsp;&nbsp;${key.content}</em>
							</c:when>
							<c:when test="${que.question_label_id eq 11}">
								<input type="checkbox" name="dan_${que.id}" <c:if test="${key.isnot_true eq 1}">checked="checked"</c:if> disabled="disabled"/>
								<em><b>${result[j.index]}.</b>&nbsp;&nbsp;${key.content}</em>
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
					</dd>
				</c:forEach>
			</dl>
			<div class="mt10 jiexi">
				<p>正确答案：<c:forEach items="${que.questionKeyList}" var="key"	varStatus="j"><c:if test="${key.isnot_true eq 1}">${result[j.index]}</c:if></c:forEach></p>
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