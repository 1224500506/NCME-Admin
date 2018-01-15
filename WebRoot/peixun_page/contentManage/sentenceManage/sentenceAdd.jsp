<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<%@include file="/commons/taglibs.jsp"%>
</head>

<%@include file="/peixun_page/top_sentence.jsp"%>
<body>
<!-- 查询条件 -->
 
<div class="center">
	<h2 class="main_title">
		<c:if test="${id==null ||id==0}">添加文章</c:if>
		<c:if test="${id>0}">编辑文章</c:if>
	</h2>
</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="form_cont">
	<form name="sentenceForm" id="sentenceForm" action="${ctx}/contentManage/sentenceManage.do?method=save" method="post">
		<input type="hidden" id="reId" name="model.id" value="${id}" />
		<div class="input_div">
			<label class="fl" style="width:6em;text-align:right;margin-top:6px;"><em style="color:red">*</em>文章分类：</label>
			<div class="fl select">
				<div class=""  style="margin:0 20px 0 0">
					<div class="tik_position">
						<b class="ml5">全部</b>
						<p class="position01"><i class="bjt10"></i></p>
					</div>
					<ul class="fl list" style="display:none;">
				 		<select class="fl select" id="reType" style="display:none;" name="model.type">
					 		<option value=''>全部</option>
					 		<option value='1'<c:if test="${type == 1}">selected</c:if>>资讯公告</option>
							<option value='2'<c:if test="${type == 2}">selected</c:if>>政策法规</option>
							<option value='3'<c:if test="${type == 3}">selected</c:if>>乡医培训</option> 
							<option value='4'<c:if test="${type == 4}">selected</c:if>>分类4</option>
					 	</select>
					 	 <li>全部</li>
						 <li>资讯公告</li>
						 <li>政策法规</li>
						 <li>乡医培训</li>
						 <li>分类4</li>
					</ul>
				</div>
	 		</div>
		</div>
		<div class="clear" style="height:15px"></div> 
		
		<div class="input_div" id="province" style="display:none;" >
			<label class="fl" style="width:6em;text-align:right;margin-top:6px;"><em style="color:red">*</em>省份：</label>
			<ul class="fl">
				<c:forEach items="${provinceList}" var="province">
					<li ><input class="site_check" name="province" type="checkbox" value="${province.provinceId}"/>${province.provinceName}</li>
				</c:forEach>	
			</ul>
			<input type="hidden" id="rsPids" value="<c:forEach items="${checkProvince}" var="province">${province.provinceId},</c:forEach>"/>
		</div>
		<div class="clear" style="height:15px"></div> 
		
		<div class="input_div">
			<label class="fl" style="width:6em;text-align:right;margin-top:6px;">授权站点：</label>
			<ul class="fl">
				<c:forEach items="${siteList}" var="site">
					<li><input class="site_check" name="site" type="checkbox" value="${site.id}"/>${site.domainName}</li>
				</c:forEach>	
			</ul>
			<input type="hidden" id="rsSids" value="<c:forEach items="${checkSite}" var="site">${site.id},</c:forEach>"/>
			
		</div>
		<div class="clear" style="height:15px"></div> 

		<div class="input_div">
			<label class="fl" style="width:6em;text-align:right;margin-top:6px;"><em style="color:red">*</em>文章标题：</label>
			<input type="text" id="reTitle" size="40" name="model.title" value="${title}"/>
		</div>
		<div class="clear"></div> 
		<div class="input_div">
			<label class="fl" style="width:6em;text-align:right;margin-top:6px;">文章来源：</label>
			<input type="text" id="reSource" size="40" name="model.source" value="${source}"/>
		</div>
		<div class="clear"></div>
		<div class="input_div">
			<label style="width:6em;text-align:right;margin-top:6px;">文章内容：</label>
			<script id="editor" name="model.content" type="text/plain">${content}
			</script>
		</div>
		<p class="fl cas_anniu">
			<a href="javascript:save();" class="fr" style="width:140px;margin:10px 20px 0 0;">确认提交</a>
		</p>
		<p class="fl cas_anniu">
			<span style="width:5em;">&nbsp;</span>
			<a href="${ctx}/contentManage/sentenceManage.do?method=list" class="fl" style="width:70px;margin-top:10px;">返回</a>
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

	window.ue = UE.getEditor('editor');
	/*IE9.0、IE10.0下,编辑器输入内容过多时,下方的 "确认提交"及"返回" 按钮会被遮挡住
	  $("#editor").css({"height":"500px"}); 
	*/
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
		var optionsValue=$("#reType option:selected").val();
		
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
	var reType = $('#reType').val();
	if ($.trim(reType) == ""){
		alert ("请选择文章分类！");
		return;
	}
	var reTitle = $('#reTitle').val();
	if ($.trim(reTitle) == ""){
		alert ("请输入标题！");
		return;
	}
	
	var optionsValue=$("#reType option:selected").val();
	
	if(optionsValue=='3'){
		if($("input[name='province']:checked").length=='0'){
			alert ("请选择省份！");
			return;
		};
		
	}
	
	var url = "${ctx}/contentManage/sentenceManage.do?method=save";
	var params = $('#sentenceForm').serialize();

	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'text',
	    success: function(result){
		   if(result == 'success'){
		   		alert('保存成功！');
		   		document.location.href = "${ctx}/contentManage/sentenceManage.do?method=list";
		   }else{
		   		alert('文章标题重复，保存失败！');
		   }
	    }
	});
    
}
</script>
</body>
</html> 