<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/new_page/top.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理平台</title>
</head>

<script type="text/javascript" src="${ctx}/js/question.js"></script>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>

<body class="bjs">
	<div class="center">
	<form action="" name="fm1" id="fm1" method="post" enctype="multipart/form-data">
		<div class="tk_xuanxiag">
			<div class="xingzeng_k">
				<i></i><h2 class="fl">批量导入试题</h2>
			</div>
			
				<div class="back_kti" style="width:100%;height:600px;background:#ffffff!important;">
					<div class="tik_piliang">
						<p class="fl mt30"><em>*</em><span>请先下载模板文件：</span></p>
						<a href="${ctx}/questionManage/exportQuestionAndPropModel.do" target="_blank" class="fl xiazai">下载导入模板（含学科）</a>
					</div>
					<div class="clear"></div>
					<div class="tik_piliang">
						<p class="fl mt30"><em>*</em><span>选择文件：</span></p>
						<p class="fl xuan_wenjian">
							<a class="fl xiazai01"><input type="file" name="uploadfile" id="uploadfile" onchange="javascript:$(this).parent().siblings().eq(0).val($(this).val());" style="position:absolute;opacity:0;right:0px;padding:0px;"/>选择文件</a>
							<input type="text" readonly value=""/>
						</p>
						<p class="fl ml10" style="margin-top:35px;font-size:14px;color:#47474b;">(注：上传文件大小不能超过1MB,总行数不能超过1000!)</p>
					</div>
					<div class="clear"></div>
					<div class="ca1_anniu" style="width:200px;">
						<a href="javascript:submitForm('a2');" class="fl anniu">导入</a>
						<a href="javascript:void(0);" class="fl ml30 anniu banfui">返回</a>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</form>
		</div>
	<div class="clear"></div>
	
<script>
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
	
	
		$('.banfui').click(function(){
			document.location.href = "${ctx}/questionManage/questionManage.do";
		});
});

function submitForm(tmp){
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
	if(window.confirm("您确定提交吗？")){
		if(tmp=='a'){
			document.fm1.action="${ctx}/questionManage/importQuestion.do"
			document.fm1.submit();
		}
		if(tmp=='a2'){
			document.fm1.action="${ctx}/questionManage/importQuestion2.do"
			document.fm1.submit();
		}
		if(tmp=='c'){
			document.fm1.action="${ctx}/questionManage/compareQuestion.do?type=F"
			document.fm1.submit();
		}
		
		if(tmp=='cd'){
			document.fm1.action="${ctx}/questionManage/importQuestionProp.do"
			document.fm1.submit();
		}
	}
}
<c:if test="${not empty errMsg}">
alert("${errMsg}");
</c:if>
</script>
</body>
</html>
