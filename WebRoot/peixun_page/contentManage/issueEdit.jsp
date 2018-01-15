<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<%@include file="/commons/taglibs.jsp"%>
</head>

<%@include file="/peixun_page/top.jsp"%>
<body>
<!-- 查询条件 -->
<div class="center">
	<h2 class="main_title">
		<c:if test="${issue.id==null ||issue.id==0}">添加通知公告</c:if>
		<c:if test="${issue.id>0}">编辑通知公告</c:if>
	</h2>
</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="form_cont">
	<form name="editfrm" id="editfrm" action="${ctx}/contentManage/issueManage.do?method=save" method="post">
		<input type="hidden" id="reId" name="model.id" value="${issue.id}" />
		<div class="input_div">
			<label class="fl" style="width:6em;text-align:right;margin-top:6px;"><em style="color:red">*</em>类别：</label>
			<ul class="fl"><li>
			<input type="radio" name="model.type" value="1" class="reType" /> 通知
			<input type="radio" name="model.type" value="2" class="reType" /> 公告
			</li></ul>
		</div>
		<div class="clear" style="height:15px"></div> 
		<div class="input_div">
			<label class="fl" style="width:6em;text-align:right;margin-top:6px;"><em style="color:red">*</em>标题：</label>
			<input type="text" size="40" name="model.title" id="reTitle" value="${issue.title}"/>
		</div>
		<div class="clear"></div> 
		<div class="input_div">
			<label class="fl" style="width:6em;text-align:right;margin-top:6px;"><em style="color:red">*</em>开始时间：</label>
			<p class="fl"><input type="text" class="start_date datepicker" id="reStartDate" name="model.startDate" value="${issue.startDate}" /></p>
			
			<label class="fl" style="width:6em;text-align:right;margin-top:6px;"><em style="color:red">*</em>结束时间：</label>
			<p  class="fl"><input type="text" class="end_date datepicker" id="reEndDate" name="model.endDate" value="${issue.endDate}" /></p>
		</div>
		<div class="clear" style="height:15px"></div> 
		<div class="input_div">
			<label class="fl" style="width:6em;text-align:right;margin-top:6px;">授权站点：</label>
			<ul class="fl">
				<c:forEach items="${sitelist}" var="site">
					<li><input class="reSiteid" name="model.siteIds" type="checkbox" value="${site.id}"/>${site.domainName}</li>
				</c:forEach>
			</ul>
			<input type="hidden" id="rsSids" value="<c:forEach items="${issue.siteIds}" var="siteid">${siteid},</c:forEach>"/>
		</div>
		<div class="clear" style="height:15px"></div> 
		<div class="input_div">
			<label style="width:6em;text-align:right;margin-top:6px;">内容：</label>
			<script id="editor" name="model.content" type="text/plain">${issue.content}

			</script>
		</div>
		<div class="clear"></div> 
		<p class="fl cas_anniu">
			<a href="javascript:saveForm();" class="fl" style="width:70px;margin:10px 20px 0 0;">保存</a>
			<a href="${ctx}/contentManage/issueManage.do?method=list" class="fl" style="width:70px;margin-top:10px;">返回</a>
		</p>
	</form>
</div>
<div id="container"></div>

<script type="text/javascript">
$(function(){
//下拉框
	
	var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
	var submenu = "lc_left0" + menuindex;
	$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
	$('.'+submenu+'').show();
	$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");

	var sitetype = "${issue.type}";

	$('.reType').prop('checked', '');
	$('.reType').each(function(){
		if ($(this).val() == sitetype)
			$(this).prop('checked', 'checked');
	 });
	 		
	var siteIds = $('#rsSids').val().split(',');
	$('.reSiteid').prop('checked', '');
	$('.reSiteid').each(function(){		   			
		if (siteIds.indexOf($(this).val()) >= 0){
			$(this).prop('checked', 'checked');
		}
	});

	$.extend( $.fn.pickadate.defaults, {
		monthsFull: [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
		monthsShort: [ '一', '二', '三', '四', '五', '六', '七', '八', '九', '十', '十一', '十二' ],
		weekdaysFull: [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
		weekdaysShort: [ '日', '一', '二', '三', '四', '五', '六' ],
		today: '今日',
		clear: '删',
		firstDay: 1,
		format: 'yyyy-mm-dd',
		formatSubmit: 'yyyy-mm-dd'
	});

	// 日期选择控件
	$(".datepicker").pickadate({
		monthSelector: true,
//		yearSelector: true,
		today:false,
		clear:false,
		dateMax : true,
		dateMin : [2010, 1, 1],
		yearSelector: 100
	});
	
	window.ue = UE.getEditor('editor');
	$("#editor").css({"height":"500px"});


});

function saveForm()
{
	var typeval = $('.reType:checked').val();
	if (typeval == undefined){
		alert ("请选择类别！");
		return false;
	}
	if ($('#reTitle').val().trim() == ""){
		alert ("请输入标题！");
		return false;
	}
	if ($('#reStartDate').val() == ""){
		alert ("请输入开始时间！");
		return false; 
 	}
	if( $('#reEndDate').val() == ""){
		alert ("请输入结束时间！");
		return false; 
	}
	/* if ($('#reContent').val().trim() == ""){
		alert ("请输入内容！");
		return false;
	} */

	var url = "${ctx}/contentManage/issueManage.do?method=save";
	var params = $('#editfrm').serialize();

	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'text',
	    success: function(result){
		   if(result == 'success'){
		   		alert('保存成功！');
		   		document.location.href = "${ctx}/contentManage/issueManage.do?method=list";
		   }else{
		   		alert('无法保存，名称不能重复!');
		   }
	    }
	});

//	$('#editfrm').submit();
}
</script>
</body>
</html> 