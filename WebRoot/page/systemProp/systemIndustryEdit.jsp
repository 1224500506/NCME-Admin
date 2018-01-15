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
		<link rel="stylesheet" type="text/css" href="${ctx}/css/tanchu.css">
		<script type="text/javascript" src="${ctx}/js/UI/jquery-1.8.0.min.js"></script>
	</head>
	<body>
		
		<div >
				<!--栏目标题-->
				<div class="tanchu_title">
					<!--左标题-->
					<div style="text-align: center;">
						<c:if test="${level eq 2 }">二</c:if>
						<c:if test="${level ne 2}">一</c:if>级行业 编辑
					</div>
				</div>
				<br/>
				<!--栏目标题结束-->
				<form action="${ctx}/system/SystemIndustry.do?method=save" target="_parent" method="post" id="fm1" name="fm1" onsubmit="return check();">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="tanchu_table">
						<c:if test="${parentId>0 }">
							<tr>
								<td align="right">
									上级行业：
								</td>
								<td>
									${parentName }
								</td>
							</tr>
						</c:if>
						<tr>
							<td align="right">
								行业名称：
							</td>
							<td>
								<input name="industryName" type="text" class="input_d" id="industryName" value="${industryName }" size="40" maxlength="100"/>
							</td>
						</tr>
						 
						<tr>
							<td align="right">
								排序：
							</td>
							<td>
								<input name="seq" type="text" class="input_d" id="seq" value="${seq }" 
									onkeyup="this.value = this.value.replace(/[^\d]/g, '');" size="10" maxlength="100"/>
							</td>
						</tr>
						<tr> <td>&nbsp;</td> </tr>
						<tr> <td>&nbsp;</td> </tr>
						<tr>
							<td>
								&nbsp;
							</td>
							<td class="tanchu_btn" >
								<button target='_self' style="cursor:pointer" type="submit"   border='0'><span class='tablebtn_bluelong'>保 存</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
								<button target='_self' style="cursor:pointer" type="button"   border='0' onclick="parent.dialogClose()"><span class='tablebtn_bluelong'>取 消</span></button>
								
							</td>
						</tr>
					</table>
					<input type="hidden" id="id" name="id" value="${id }" />
					<input type="hidden" id="parentId" name="parentId" value="${parentId }" />
				</form>
			</div>
		
	</body>
</html>
<script>
	function check(){
	  if($('#industryName').val() == ''){
	    alert('请输入名称!');
	    $('#industryName').focus();
	    return false;
	  }
	  return true;
	}
	
</script>