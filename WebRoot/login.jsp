<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>用户登录</title>
<link rel="stylesheet" href="css/index.css" />
<%@include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/jquery.js"></script>
<style>
   ::-ms-clear, ::-ms-reveal{display: none;}
</style>
</head>
<body style="background:url('images/jp2.png');background-repeat:no-repeat;position:relative;">
		<div class="header">
			<div style="margin:0 auto;width:92%;">
				<img src="images/logo3.png" alt="" />
			</div>
		</div>
	
		 <div class="tk_er">
			<div class="tk_float mar_left07"  style="">
				<a href="${ctxAdminURL}">资源库</a>
				<a href="${ctxPeixunURL}" class="action">教学管理</a>
				<a href="${ctxQiantaiURL}">学习前台</a>
			</div>
		</div>
		<div class="lc_bjt"></div>
		<div class="clear"></div>
		<div class="ld_center">
			<div class="ld_img" style="height:20px;">
				
			</div>
<form id="loginForm" name="loginForm" action="${ctx}/j_spring_security_check" method="post">
			<div class="ld_denglukuang">
				<div style="height:70px;"><span style="padding:50px 70px;display:${ param.error == true ? 'block':'none'}">登录失败</span></div>
				<p class="">
					<span class="fl span"></span>
					<input type="text" id="username" name="j_username"  class="fl" onkeypress="javascript:fnKeyPress01(event);"/>
				</p>
				<div class="clear"></div>
				<p class="mt20">
					<span class="fl span1"></span>
					<input type="password" id="password" name="j_password"  class="fl" onkeypress="javascript:fnKeyPress02(event);"/>
				</p>
				<a href="javascript:doSubmit();" style="margin-top:30px;">登录</a>
			</div>
</form>
		</div>
	
<script type="text/javascript">
function doSubmit(){
	var un=document.getElementById('username').value;
	var pw=document.getElementById('password').value;
	if(un==''){
		alert('请输入用户名');
		document.getElementById('username').focus();
		return ;
	}
	if(pw==''){
		alert('请输入密码');
		document.getElementById('password').focus();
		return;
	}
		
	document.getElementById('loginForm').submit();
	
}

function fnKeyPress01(event){
	if (event.keyCode == 13){
		password.focus();
	}
}

function fnKeyPress02(event){
	if (event.keyCode == 13){
		doSubmit();
	}
}

$(document).ready(function(){   
   var doc=document,
    inputs=doc.getElementsByTagName('input'),
    supportPlaceholder='placeholder'in doc.createElement('input'),
    
    placeholder=function(input){
     var text=input.getAttribute('placeholder'),
     defaultValue=input.defaultValue;
     if(defaultValue==''){
        input.value=text
     }
     input.onfocus=function(){
        if(input.value===text)
        {
            this.value=''
        }
      };
     input.onblur=function(){
        if(input.value===''){
            this.value=text
        }
      }
  };
  
  if(!supportPlaceholder){
     for(var i=0,len=inputs.length;i<len;i++){
          var input=inputs[i],
          text=input.getAttribute('placeholder');
          if(input.type==='text'&&text){
             placeholder(input)
          }
      }
  }
 });

</script>
</body>
</html>