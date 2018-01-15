<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<%@include file="/commons/taglibs.jsp"%>
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/public.css">
		<script type="text/javascript" src="${ctx}/js/UI/jquery-1.8.0.min.js"></script>
	</head>
	<body>
		
		<div class="tanchu_k">
				<!--栏目标题-->
				<div class="tanchu_title">
					<!--左标题-->
					<div style="text-align:center">
						添加/修改
					</div>
				</div>
				<br/>
				<!--栏目标题结束-->
				<form action="${ctx}/system/SystemIndustryAbility.do?method=save" target="_parent" method="post" id="fm1" name="fm1" onsubmit="return check();">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="tanchu_table">
						<c:if test="${not empty industry}">
							<tr>
								<td align="right">
									上级行业：
								</td>
								<td>
									${industry.industryName }
								</td>
							</tr>
						</c:if>
						<tr>
							<td> &nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td align="right">
								能力名称：
							</td>
							<td>
								<input name="abilityName" type="text" class="input_d" id="abilityName" value="${ability.abilityName }" size="40" maxlength="100"/>
							</td>
						</tr>
						<tr> <td>&nbsp;</td> </tr>
						<tr> <td>&nbsp;</td> </tr>
						<tr> <td>&nbsp;</td> </tr>
						<tr> <td>&nbsp;</td> </tr>
						<tr>
							<td>
								&nbsp;
							</td>
							<td class="tanchu_btn">
								<button target='_self' style="cursor:pointer" type="submit" width='400' height='232' border='0'>
									<span class='tablebtn_bluelong'>保 存</span>
								</button>&nbsp;&nbsp;&nbsp;&nbsp;
								<button target='_self' style="cursor:pointer" type="button" width='400' height='232' border='0' onclick="parent.dialogClose()">
									<span class='tablebtn_bluelong'>取 消</span>
								</button>
							</td>
						</tr>
					</table>
					<input type="hidden" id="id" name="id" value="${ability.id }" />
					<input type="hidden" id="industryId" name="industryId" value="${industry.id }" />
				</form>
			</div>
		
	</body>
</html>
<script>
	function check(){
	  if($('#abilityName').val() == ''){
	    alert('请输入能力名称!');
	    $('#abilityName').focus();
	    return false;
	  }
	  return true;
	}
	
</script>