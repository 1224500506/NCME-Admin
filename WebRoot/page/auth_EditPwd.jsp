<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/commons/taglibs.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>修改密码</title>
		<link href="${ctx}/css/auth.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
<!--
body{
	margin: 0 !important;
}
.STYLE1 {
	font-size: 14px;
	color: #FFFFFF;
	font-weight: bold;
}
-->
</style>
		<script type="text/javascript">
		var check = false;
		function $(id){
			return document.getElementById(id);
		}
		function cp(){
			var oldpwd = $("oldpwd").value;
			if(oldpwd ==""){document.getElementById("ap").innerHTML = "原密码不能为空"; return false;}
			else{document.getElementById("ap").innerHTML = "";}
			var newpwd = $("newpwd").value;
			if(newpwd ==""){document.getElementById("bp").innerHTML = "新密码不能为空"; return false;}
			else{document.getElementById("bp").innerHTML = "";}
    		var repwd = $("repwd").value;
			
    		if(newpwd != repwd){
			document.getElementById("cp").innerHTML = "两次密码不一致";
			check = false;
			}else{
			document.getElementById("cp").innerHTML = "";
			check = true;
			}
		}
    	function checkpwd(){
    		var old = $("oldpwd").value;
    		var nep = $("newpwd").value;
    		var rep = $("repwd").value;
    		if(check){
	    		if(old!="" && nep!="" && rep!=""){
	    			document.getElementById('editpwd').submit();
	    			return true;
	    		}
    		}
    		return false;
    	}
    </script>
	</head>
	<center>
		<body bgcolor="#E7E7E7">
			<div class="q4all">
				<div class="subnav">
					&nbsp;&nbsp;当前位置：&gt; 密码维护
				</div>
				<div class="q3_1"></div>
				<div class="q3_2">
					<form method="post" action="${ctx}/editPwd.do" id="editpwd" name="editpwd">
						<table width="50%" border="0" cellspacing="1" cellpadding="1">
							<tr>
								<td height="33">
									&nbsp;
								</td>
								<td height="33">
									<span>${msg}</span>
								</td>
								<td height="33" align="center">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td width="29%" height="33" align="right">
									<img src="${ctx}/images/XYZY21_07.jpg" width="21" height="22" />
								</td>
								<td width="21%" height="33" align="right">
									<span class="STYLE1">原密码：</span>
								</td>
								<td width="50%" height="33" align="left">
									<input type="password" maxlength="20" size="20" id="oldpwd" name="oldpwd" onkeyup="cp()" />
									<span id="ap"></span>
								</td>
							</tr>
							<tr>
								<td height="33" align="right">
									<img src="${ctx}/images/XYZY21_10.jpg" width="21" height="23" />
								</td>
								<td height="33" align="right" class="STYLE1">
									新密码：
								</td>
								<td height="33" align="left">
									<input type="password" maxlength="20" size="20" id="newpwd" name="newpwd" onkeyup="cp()" />
									<span id="bp"></span>
								</td>
							</tr>
							<tr>
								<td height="33" align="right">
									<img src="${ctx}/images/XYZY21_12.jpg" width="21" height="23" />
								</td>
								<td height="33" align="right" class="STYLE1">
									密码确认：
								</td>
								<td height="33" align="left">
									<input type="password" maxlength="20" size="20" id="repwd" name="repwd" onkeyup="cp()" />
									<span id="cp"></span>
								</td>
							</tr>
							<tr>
								<td height="66">
									&nbsp;
								</td>
								<td height="66">
									&nbsp;
								</td>
								<td height="66" align="center">
									<a href="#" onclick="return checkpwd();"><img src="${ctx}/images/XYZY21_15.jpg" width="97" height="25" border="0" />
									</a>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div class="q3_3"></div>
			</div>
		</body>
	</center>
	<script language="javascript" for="document" event="onkeydown">
<!--
  if(event.keyCode==13){
     return checkpwd();
    }
-->
</script>
</html>