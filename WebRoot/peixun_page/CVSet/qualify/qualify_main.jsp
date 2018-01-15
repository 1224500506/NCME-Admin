<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<%@include file="/commons/taglibs.jsp"%>
	<style>
		input:disabled{background:#f0f0f0;color:#ccc;}
		.course_list{width:350px;height:300px;float:left;margin-right:20px;background-color:#f9f9f9;}
		.course_list .course_img{width:350px;height:200px;overflow:hidden;}
		.course_list .course_img img{width:100%;}
		.course_list a{outline: none;display:inline-block;width:100%;height:100%;}
		.course_list .title{font-size:16px;color:#333;font-weight:600;margin:10px;}
		.course_list .desc{margn:10px 0;padding:5px 10px;list-style:none;}
		.course_list .desc li{width:50%;float:left;line-height:20px;font-size:14px;color:#333;}
	</style>
</head>
<%@include file="/peixun_page/top.jsp"%>

<body>
<!-- 查询条件 -->
<div class="center">
	<div class="tiaojian" style="min-height:40px;">
	<form id="qualify_main_frm" action="${ctx}/CVSetQualify.do" method="post">
		<input type="hidden" value="qualify" name="mode"/>
		<p class="fl">
			<span>学科：</span>
		</p>
		<div class="fl select" style="margin-right:20px;">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display: none;" id="xkdisplay">
			
			</ul>
		</div>
		<p class="fl" style="margin-right:20px;">
			<span>项目名称：</span>
			<input type="text" name="CVSetName" value="${sname}"/>
		</p>
		<p class="fl" >
			<span>项目状态：</span>
		</p>
		<div class="fl select">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display: none;">
			<select name="CVSetStatus" id="CVSetStatus" style="display:none;">
				<option value="10" <c:if test="${status == 10}">selected</c:if>>全部</option>	
				<option value="2" <c:if test="${status == 2}">selected</c:if>>审核中</option>			
				<%-- <option value="2" <c:if test="${status == 2}">selected</c:if> selected = "selected">审核中</option> --%>
				<option value="3" <c:if test="${status == 3}">selected</c:if>>审核合格</option>
				<option value="4" <c:if test="${status == 4}">selected</c:if>>审核不合格</option>				
			</select>
				<li>全部</li>				
				<li>审核中</li>
				<li>审核合格</li>
				<li>审核不合格</li>				
			</ul>
		</div>
	</form>
		<p class="fl cas_anniu">
			<a href="javascript:$(qualify_main_frm).submit();" class="fl" style="width:70px;margin-left:10px;">查询</a>
		</p>
		<input type="hidden" value="${expertXueke.propNames}" id="propNames"/>
		<input type="hidden" value="${expertXueke.propIds}" id="propIds"/>
		<div class="clear"></div>
	
	</div>
</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01" id="main_page">
	<c:forEach items = "${CVSet}" var = "item">
		<div class="course_list" style="margin-bottom:20px;height:350px">
			<a href="${ctx}/CVSetQualify.do?id=${item.id}&mode=get_CVS" style="color:black">
				<div class="course_img">
					<img src="${item.file_path}" />
				</div>
				<div class="title">${item.name}</div>
				<ul class="fl">
					<li>项目负责人： <c:forEach items = "${item.managerList}" var="manager">${manager.name}&nbsp;</c:forEach></li>
					<li>项目归属机构：<c:forEach items = "${item.organizationList}" var="org">${org.name}&nbsp;</c:forEach></li>
					<li>状态：<c:if test="${item.status == 1}">未提交</c:if>
							<c:if test="${item.status == 2}">审核中</c:if>
							<c:if test="${item.status == 3}">审核合格</c:if>
							<c:if test="${item.status == 4}">审核不合格</c:if>
							<c:if test="${item.status == 5}">已发布</c:if>
							<c:if test="${item.status == 6}">已下线</c:if>  </li>
					<li class="clear" style="width:100%;">项目审核截止日期：${item.deli_date}</li>
				</ul>
			</a>
		</div>
	</c:forEach>
		

		<div class="clear"></div>
	</div>
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
	//下拉框
		select_init();
});

	
	
		var str ='<select name="propIds" style="display:none;"><option value="">全部</option>';
		var xkNames = $('#propNames').val().split(",");
		var xkIds = $('#propIds').val().split(",");
		for(var i=0; i<xkNames.length; i++){
			str += '<option value ='+xkIds[i]+'>'+xkNames[i]+'</option>';
		}
		str += '</select><li>全部</li>';
		for(var i=0; i<xkNames.length; i++){
			str += '<li>'+xkNames[i]+'</li>';
		}	
		$('#xkdisplay').html(str); 
	

function select_init() {
	$('.select').click(function(){
		$(".list").css("display","none");
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

	$(document).click(function(){
		$('.list').hide('fast');
	});	
}
</script>
</body>
</html>