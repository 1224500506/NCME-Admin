<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<%@include file="/commons/taglibs.jsp"%>
</head>

<%-- <%@include file="/peixun_page/top_sentence.jsp"%> --%>
<%@include file="/peixun_page/userManage/message/top_sentence.jsp"%>
<body>
<!-- 查询条件 -->
 
<div class="center">
	<h2 class="main_title">
		<c:if test="${id==null ||id==0}">添加消息</c:if>
		<c:if test="${id>0}">编辑消息</c:if>
	</h2>
</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="form_cont">
	<form name="messageForm" id="messageForm" action="${ctx}/contentManage/messageManage.do?method=save" method="post">
		<input type="hidden" id="reId" name="id" value="${id}" />
	<!-- 		消息类型 -->
		<div class="input_div">
			<label class="fl" style="width:6em;text-align:right;margin-top:6px;"><em style="color:red">*</em>消息类型：</label>
			
			<div class="fl select">
				<div class=""  style="margin:0 20px 0 0">
					<div class="tik_position">
						<b class="ml5">全部</b>
						<p class="position01"><i class="bjt10"></i></p>
					</div>
					<ul class="fl list" style="display:none;">
				 		<select class="fl select" style="display:none;" name="model.messageType" value="">
				 			<option value='0' <c:if test="${model.messageType == 0}">selected</c:if> >全部</option>
					 		<option value='1' <c:if test="${model.messageType == 1}">selected</c:if> >版本更新</option>
							<option value='2' <c:if test="${model.messageType == 2}">selected</c:if> >项目更新</option>
				 			<option value='3' <c:if test="${model.messageType == 3}">selected</c:if> >项目下线</option>
				 			<option value='4' <c:if test="${model.messageType == 4}">selected</c:if> >活动通知</option>
				 			<option value='5' <c:if test="${model.messageType == 5}">selected</c:if> >新闻通知</option>
			 		</select>
			 		<li>全部</li>
					 <li>版本更新</li>
					 <li>项目更新</li>
					 <li>项目下线</li>
					 <li>活动通知</li>
					 <li>新闻通知</li>
					</ul>
				</div>
	 		</div>
		</div>
		<div class="clear" style="height:15px"></div> 
<!-- 		授权站点 -->
		<div class="input_div">
			<label class="fl" style="width:6em;text-align:right;margin-top:6px;"><em style="color:red">*</em>授权站点：</label>
			<ul class="fl">
				<c:forEach items="${siteList}" var="site">
					<li><input class="site_check" name="site" type="checkbox" checked="checked" value="${site.id}"/>${site.domainName}</li>
				</c:forEach>	
			</ul>
			<input type="hidden" id="rsSids" value="<c:forEach items="${checkSite}" var="site">${site.id},</c:forEach>"/>
		</div>
		<div class="clear" style="height:15px"></div> 
<!-- 		消息标题 -->
		<div class="input_div">
			<label class="fl" style="width:6em;text-align:right;margin-top:6px;"><em style="color:red">*</em>消息标题：</label>
			<input type="text" id="reTitle" size="50" name="model.title" maxlength="50" value="${name}"/>
			<span style="color:gray;">不超过50个字</span>
		</div>
		<div class="clear"></div>
		<!-- 接受成员 -->
		<div class="input_div">
			<label class="fl" style="width:6em;text-align:right;margin-top:6px;"><em style="color:red">*</em>接收人员：</label>
			<ul class="fl">
				<li><input type="checkbox" name="model.receiver" value="全体成员" checked="checked"> 全体成员</li> 
			</ul>
		</div>
		<div class="clear"></div>
		<!-- 发送方式 -->
		<div class="input_div">
			<label class="fl" style="width:6em;text-align:right;margin-top:6px;" style="vertical-align:middle;"><em style="color:red">*</em>发送方式：</label>
			<input name="model.sendcheck" type="checkbox" id="checkbox2" value="1" checked="checked"/>平台
			<input name="model.sendcheck" type="checkbox" id="checkbox2" value="2" />短信
			
		</div>
		<div class="clear"></div>
		<div class="input_div">
			<label style="width:6em;text-align:right;margin-top:6px;"><em style="color:red">*</em>消息内容：</label>
			<textarea rows="5" cols="100"  id="content" name="model.content" placeholder="不超过10000字">${model.content}</textarea>
		</div>
		<p class="fl cas_anniu">
			<a href="javascript:save();" class="fr" style="width:140px;margin:10px 20px 0 0;">确认提交</a>
		</p>
		<p class="fl cas_anniu">
			<span style="width:5em;">&nbsp;</span>
			<a href="${ctx}/contentManage/messageManage.do?method=list" class="fl" style="width:70px;margin-top:10px;">返回</a>
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
			
	var siteIds = $('#rsSids').val().split(',');
	$('input[name="site"]').prop('checked', '');
	$('input[name="site"]').each(function(){		   			
		if (siteIds.indexOf($(this).val()) >= 0){
			$(this).prop('checked', 'checked');
		}
	});

	selectInit();

	
});
function selectInit(){
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
		var optionsValue=$("#reTitle option:selected").val();
		
		if(optionsValue=='3'){
			$('#province').css("display","block");//province的display属性设置为block（显示）
		}else {
			$('#province').css("display","none");//province的display属性设置为none（隐藏）
		}
		
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

function save() {
	var reTitle = $('#reTitle').val();
	if ($.trim(reTitle) == ""){
		alert ("请选择消息类型！");
		return;
	}
	var reTitle = $('#reTitle').val();
	if ($.trim(reTitle) == ""){
		alert ("请输入标题！");
		return;
	}
	
	
	var cbs = document.getElementsByName("model.sendcheck");
	var checkNum = 0;
	for (var i = 0; i < cbs.length; i++) {
	    if (cbs[i].checked) {
	        checkNum++;
	    }
	}
// 	alert("选中数量=" + checkNum);
	if (checkNum == 0) {
		alert("请选择发送方式！");
	    return false;
	}else if(checkNum >0){
		
	}
	
		//判断发送方式
		/* var falg = 0; 
		$('#checkbox2').each(function () { 
		if ($(this).attr("checked")) { 
		falg =0; 
			return false; 
		} 
		}) 
		if (falg > 0){
			
		}
		else{ 
			alert("请选择发送方式！");
			return false; 
		}*/
		var content = $('#content').val();
		if ($.trim(content) == ""){
			alert ("消息内容不能为空！");
			return;
		} 
		
	
	var url = "${ctx}/contentManage/messageManage.do?method=save";
	var params = $('#messageForm').serialize();

	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'text',
	    success: function(result){
		   if(result == 'success'){
		   		alert('保存成功！');
		   		document.location.href = "${ctx}/contentManage/messageManage.do?method=list";
		   }else{
		   		alert('标题重复，保存失败！');
		   }
	    }
	});
    
}
</script>
</body>
</html> 