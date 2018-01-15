<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学习卡编辑</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/default090909.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/lay.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">

<link rel="stylesheet" href="${ctx}/js/Validform_v5.3/css/valid.css" type="text/css" media="all" />
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/Validform_v5.3.js"></script>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<script type="text/javascript">

function doSubmit(){
	var cardNumber = $("#cardNumber").val();
	if(cardNumber == ""){
		alert("请输入卡号！");
		return;
	}
	var cardPassword = $("#cardPassword").val();
	if(cardPassword == ""){
		alert("请输入卡密码！");
		return;
	}
	$("#fm1").submit();
	
}

</script>
</head>
<body>
<form class="fm1" id="fm1" name="fm1" action="${ctx}/system/SystemCard.do?method=save" method="post">
<input type="hidden" name="cardType" id="cardType" value="${cardTypeId }" />
<table border="0" cellspacing="1" cellpadding="0" width="90%" class="gridtable">
	<tr>
		<td align="center" colspan="3" class="row_tip" height="35">
			学习卡信息
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="35">
			卡号：
		</td>
		<td width="60%">
		<input type="text" id="cardNumber" class="editInput" name="cardNumber" maxlength="100" 
			size="25" datatype="*" nullmsg="请输入卡号！" errormsg="卡号最多100个字符！" value="${cardNumber}" readonly/>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="35">
			<font class="red_star">*</font>密码
		</td>
		<td align="left">
			<input type="text" name="cardPassword" id="cardPassword" class="editInput"  value="${card.cardPassword}" 
				datatype="*" nullmsg="请输入密码！" />
		</td>
	    <td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	 
	<tr>
		<td class="row_tip" height="35">
			<font class="red_star">*</font>卡有效期
		</td>
		<td>
		<input type="text" id="usefulDate" name="usefulDate" class="editInput" datatype="*" nullmsg="请输入卡有效期！"
			onClick="WdatePicker()" value="<fmt:formatDate value="${card.usefulDate}" pattern="yyyy-MM-dd"/>"/>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>	
	</tr>
	 
	<tr>
		<td class="row_tip" height="35">
			是否绑定:
		</td>
		<td>
			<c:if test="${card.isnotBind == 1 }">是</c:if> 
			<c:if test="${card.isnotBind == 0 }">否</c:if>  
			
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	 
	<tr>
		<td class="row_tip" height="35">
			<font class="red_star">*</font>总学时：
		</td>
		<td>
			<input type="text" id="faceValue" class="editInput" name="faceValue" 
				onkeyup="this.value = this.value.replace(/[^\d\.\-]/g, '');"
				maxlength="200" size="2" datatype="n" nullmsg="请输入总学时！" errormsg="请填写数字！" value="${card.faceValue}"/>
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="35">
			<font class="red_star">*</font>剩余学时：
		</td>
		<td>
			<input type="text" id="balance" class="editInput" name="balance" 
				onkeyup="this.value = this.value.replace(/[^\d\.\-]/g, '');"
				maxlength="200" size="35" datatype="n" nullmsg="请输入总学时！" errormsg="请填写数字！" value="${card.balance}"/>
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="35">
			<font class="red_star">*</font>销售方式：
		</td>
		<td>
			<select id="sellStyle" name="sellStyle">
				<option value="1">网上销售</option>
				<option value="2">地面销售</option>
			</select>
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td colspan="6" align="center" class="row_tip">
		    <input type="button" onclick="doSubmit()" class="btn_03s" value="保 存" />
		    <input type="button" class="btn_03s" value="返 回" onclick="javascript:history.back(-1);"/>
		</td>
	</tr>
</table>
<input type="hidden" id="id" name="id" value="${card.id}"/>
<input type="hidden" name="org.apache.struts.taglib.html.TOKEN"  value="${sessionScope['org.apache.struts.action.TOKEN']}" />
</form>
<script type="text/javascript">
		    $(".fm1").Validform({
		      datatype:{//自定义datatype类型 浮点类型
			    "float":function(gets,obj,curform,datatype){
						var reg = new RegExp("^\\d+(\\.\\d+)?$");
						if (!reg.exec(gets)){
						  return false;
						}
						return true;
					}
		      }
	        });
</script>
</body>
</html>