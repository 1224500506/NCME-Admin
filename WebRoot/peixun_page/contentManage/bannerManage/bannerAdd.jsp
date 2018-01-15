<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.hys.exam.common.util.LogicUtil" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>

<%@include file="/commons/taglibs.jsp"%>
</head>

<%@include file="/peixun_page/top_banner.jsp"%>

<body>
<!-- 查询条件 -->
 
<div class="center">
	<h2 class="main_title">
		<c:if test="${advert.id==null ||advert.id==0}">添加Banner</c:if>
		<c:if test="${advert.id>0}">编辑Banner</c:if>
	</h2>
</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="form_cont">											
	<form name="bannerForm" id="frm_add"
	 action="${ctx}/contentManage/bannerManage.do?method=save" method="post" enctype="multipart/form-data">
		<input type="hidden" id="reId" name="model.id" value="${id}" />
		<div class="input_div">
			<label class="fl" style="width:6em;text-align:right;margin-top:6px;"><em style="color:red">*</em>banner分类：</label>
			<div class="fl select">
				<div class=""  style="margin:0 20px 0 0">
					<div class="tik_position">
						<b class="ml5">全部</b>
						<p class="position01"><i class="bjt10"></i></p>
					</div>
					<ul class="fl list" style="display:none;">
				 		<select  class="fl select" id="reType" style="display:none;" name="model.type">
					 		<option value=''>全部</option>
					 		<option value='1'<c:if test="${type == 1}">selected</c:if>>网页端</option>
							<option value='2'<c:if test="${type == 2}">selected</c:if>>移动端</option>
					 	</select>
					 	<li>全部</li>
					 	 <li>网页端</li>
						 <li>移动端</li>
					</ul>
				</div>
	 		</div>
		</div>
		<div class="clear" style="height:15px"></div> 
		
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

		<div class="input_div">
			<label class="fl" style="width:6em;text-align:right;margin-top:6px;"><em style="color:red">*</em>banner标题：</label>
			<input type="text" id="reTitle" size="50" name="model.name" maxlength="50" value="${name}"/>
			<span style="color:gray;">不超过50个字</span>
			
		</div>
		<div class="clear" style="height:15px"></div> 
		<!-- 图片上传 -->
		<div class="input_div">
			<label class="fl" style="width:6em;text-align:right;margin-top:6px;"><em style="color:red">*</em>banner图片：</label>
			<div class=" fileinput fileinput-new" data-provides="fileinput">
			<input type="file" id="matFile" name="matFile" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
			<input type="hidden" id="cover" name="model.cover" >
			<span style="color: gray;">支持jpg,png,jpeg,gif格式文件，建议宽高1920*500像素，大小不超过20M（移动端banner根据移动端需求，提供建议大小。）</span>
			</div>
  		</div>
	
		<div class="clear"></div> 
		<div class="input_div">
			<label class="fl" style="width:6em;text-align:right;margin-top:6px;">链接地址：</label>
			<input type="text" id="reSource" size="40" name="model.target_url" value="${target_url}"/>
			<span style="color: gray;">点击banner，可以链接至该地(网页端路径格式：test/test.do或者/test/test.do?id=123)</span>
		</div>
		<div class="clear"></div>
		<div class="input_div">
			<label style="width:6em;text-align:right;margin-top:6px;">banner内容：</label>
			<span style="color: gray;">（若添加链接地址，则banner内容无须添加）</span>
			<script id="editor" name="model.remark" type="text/plain">
			</script>
		</div>
		<div class="clear"></div> 
		<p class="fl cas_anniu">
			<a href="javascript:save();" class="fr" style="width:140px;margin:10px 20px 0 0;">确认提交</a>
		</p>
		<p class="fl cas_anniu">
			<span style="width:5em;">&nbsp;</span>
			<a href="${ctx}/contentManage/bannerManage.do?method=list" class="fl" style="width:70px;margin-top:10px;">返回</a>
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

	 $("#reSource").click(function(){
		  $("#container").hide();
		  });
	
	var reSource = $('#reSource').val();
	if ($.trim(reSource) != ""){
		$("#editor").hide();
		return;
	}
	
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
		alert ("请选择banner分类！");
		return;
	}
	var reTitle = $('#reTitle').val();
	if ($.trim(reTitle) == ""){
		alert ("请输入banner标题！");
		$("#reTitle").focus();
		return;
	}
	var site_check =$('.site_check').val();
	if ($.trim(site_check) ==""){
		alert ("请选择授权站点");
		return;
	}
	
	if($("#reTitle").val().length>100){   //长度可自定义
        alert("超出了标题的最大长度，请控制标题数为50字");
        var a= $("#reTitle").val().substring(0,20);
        $("#reTitle").val(a);
        $("#reTitle").focus();
		return;
    }else{
        //未超出……
    }
 
	
	var imageFile =$('#matFile').val();
	if ($.trim(imageFile) ==""){
		alert ("请选择要添加的banner图片");
		return;
	} 
	//判断上传文件类型
	var imageFile = $('#matFile').val();
	if (imageFile != '') {
		if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG|bmp)$/.test(imageFile)) {
			 alert("头像必须是.gif,jpeg,jpg,png,bmp中的一种");
			 return false;
		}
		 //判断上传文件大小
	    var size = $("#matFile").get(0).files[0].size / 1024;  
	    if(size > 20480){  
	        alert('图片大小不能超过20M');  
	        return false;  
	    }
	}
	
	$("#frm_add").ajaxSubmit({
		type:'post',
		url:'${ctx}/file/fileUpload.do',
		success:function(data){
			var v= JSON.parse(data).message;
			if(v!="Fail"){
				$('#cover').val(v);
				$.ajax({
					url :"${ctx}/contentManage/bannerManage.do?method=save",
					data:$("#frm_add").serialize(),
					type:'post',
					dataType:'text',
					success:function(result){
					if(result != 'fail'){
						alert('保存成功');
						document.location.href = "${ctx}/contentManage/bannerManage.do?method=list";
						 return true;
					   }else{
					   		alert('banner标题重复，请重新命名');
					   		doucment.getElementById("reTitle").value = "请重新输入标题";
					   		return false;  
					   }
					},
					error:function(){
						alert('网络不好，保存失败！');
						return false;
					}
				})
			}
			else {
				alert('图片上传失败');
				return false;
			}
			
		},
		error:function(){
			alert('图片上传失败！');
		}
	})
	
	
}	

</script>
</body>
</html> 