<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
  </head>

	<%@include file="/new_page/top.jsp"%>
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/question.js"></script>
	<script type="text/javascript" src=""></script>

  <body class="bjs">
  <form action="${ctx}/system/systemUserInfImport.do" 
				  enctype="multipart/form-data" method="post" name = "downloadFrm" id = "downloadFrm">
	<div>
		<div class="center">
		<div class="tk_xuanxiag">
			<div class="xingzeng_k">
				<i></i><h2 class="fl">批量导入用户</h2>
			</div>
			
				<div class="back_kti" style="width:100%;height:600px;background:#ffffff!important;">
					<div class="tik_piliang">
						<p class="fl mt30"><em>*</em><span>请先下载模板文件：</span></p>
						<a href="${ctx}/new_page/systemUser/systemUserInf.xls" class="fl xiazai">下载导入模板（含学科）</a>
					</div>
					<div class="clear"></div>
					<div class="tik_piliang">
						<p class="fl mt30"><em>*</em><span>选择文件：</span></p>
						<p class="fl xuan_wenjian">
							<a class="fl xiazai01"><input type="file" name="uploadfile" type="file" id="uploadfile" onchange="javascript:$(this).parent().siblings().eq(0).val($(this).val());" style="position:absolute;opacity:0;right:0px;padding:0px;"/>选择文件</a>
							<input type="text" readonly value="*.xlsx"/>
						</p>
						<p class="fl ml10" style="margin-top:35px;font-size:14px;color:#47474b;">(注：上传文件大小不能超过1MB,总行数不能超过1000!)</p>
					</div>
					<div class="clear"></div>
					<div class="mt10" style="margin-top:15px;">
						<div class="ca1_anniu" style="width:200px;">
							<a href="javascript:importUserInfos();" class="fl anniu">导入</a>
							<a href="${ctx}/userManage/getUsers.do?method=list" class="fl ml30 anniu">返回</a>										
						</div>
						<div class="clear"></div>
					</div>					
					</div>
				<div class="clear"></div>
			</div>
		</div>
		<div class="clear"></div>
	</form>
<script type="text/javascript">
function importUserInfos(){

	if($('#uploadfile').val()==""){
		alert("请选择要导入的文件！");
		$("uploadfile").focus();
		return;
	}
	if($('#uploadfile').val().length>4){
		if($('#uploadfile').val().substring($('#uploadfile').val().length-3,$('#uploadfile').val().length)!= "xls" && $('#uploadfile').val().substring($('#uploadfile').val().length-4,$('#uploadfile').val().length)!= "xlsx"){
			alert("只能上传.xls和.xlsx的文件");
			return;
		}
	}
	
	$('#downloadFrm').submit();
}
 
$(function(){
	//select menu
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "mar_left0" + menuindex;
		$('.tk_mainmenu>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");

});


</script>
	
  </body>
</html>
