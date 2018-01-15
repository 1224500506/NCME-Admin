<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<%@include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
</head>

<%@include file="/peixun_page/top.jsp"%>
<body style="overflow-x:auto;">

    
    
    
    


<!-- 内容 -->

<div  class="center none" style="float:right; width:1500px; height:800px;position:absolute; top:200px; left:200px; ">
	<div class="tk_center01" >		
		    <form id="form" action="${ctx}/system/loginConfig.do?method=update" method="post" style="float:right;border-right-width: 100px;margin-right: 700px;">  
        限制时间间隔(h)：<input type="text" id="time" name="time" readonly="readonly" value="${systemConfigVO.time}"> 
              
        
        限制次数(次)：<input type="text" id= "loginNum" name="loginNum" readonly="readonly" value="${systemConfigVO.loginNum}"> 
               
        
        锁定时间(h)：<input type="text" id= "lockTime" name="lockTime" readonly="readonly" value="${systemConfigVO.lockTime}">
        <br/><br/><br/><br/><br/>
        
        <div style="margin-left: 355px;">
        <button id="update" type="button" class="btn_blue" style="width:100px;">修改</button> &nbsp;&nbsp;&nbsp;&nbsp;
        <button id="save" type="button" class="btn_blue" style="width:100px;">提交</button>
        </div>
    </form>  
	</div>
</div>

</body>

<script type="text/javascript">

$('#update').click(function(){
	$('input:text').removeAttr("readonly");
	
})

$('#save').click(function(){

	var reg = /^[1-9]\d*$/;
	
	var time = $("#time").val();
	var loginNum = $("#loginNum").val();
	var lockTime = $("#lockTime").val();
	
	if(!reg.test(time) || !reg.test(loginNum)|| !reg.test(lockTime)){
	
		alert("限制条件均必须为正整数！");
		return;
	}else{
	
		$("#form").submit(); 
		alert("保存成功！");
	
	}
	
	
	
	
	
	
})





</script>
</html>