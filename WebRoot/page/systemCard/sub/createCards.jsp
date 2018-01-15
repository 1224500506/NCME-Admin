<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生成卡号</title>
<%@ include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>

<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
</style>
 <script type="text/javascript">

	function check(){
		var num = $("#num").val();
		if(num == ''){
			alert("请输入要生成的学习卡的数量!");
			$("#num").focus();
			return false;;
		}
		/* var usefulDate = $("#usefulDate").val();
		if(usefulDate == ''){
			alert("请选择有效期!");
			$("#usefulDate").focus();
			return false;
		} */
		return true;
	}
</script>
	</head>
	<body bgcolor="#E7E7E7">
		
		<div style="margin: auto; text-align: right;">
		<center>
		
		<form name="queryForm" action="${ctx}/system/SystemCard.do?method=doCreateCards" target="_parent" method="post" onsubmit="return check();">
		<table style="font-size:12px;width:95%;text-align: center;" class="ITS" >
		<tr>
			<td>&nbsp;</td>
			<td>
				&nbsp;
			</td>
		</tr>
		<tr>
			<td>卡类型</td>
			<td align="left">
				<select name="cardTypeId">
					<c:forEach items="${typeList}" var="type" varStatus="status">
						<option value="${type.id }">${type.cardTypeName }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>卡号编码(北京默认为01)</td>
			<td align="left"><input name="code" id="code" value="01" readonly="readonly" /></td>
		</tr>
		<tr>
			<td><font class="red_star">*</font>销售方式</td>
			<td align="left">
				<select id="sellStyle" name="sellStyle">
					<option value="1">网上销售</option>
					<option value="2">地面销售</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>学习卡数量</td>
			<td align="left"><input name="num" id="num" value="" maxlength="5" onkeyup="this.value = this.value.replace(/[^\d]/g, '');" /></td>
		</tr>
		<!-- 学习卡与卡类型绑定,使用卡类型的起始时间即可 -->
		<!-- 
		<tr>
			<td>学习卡有效期 </td>
			<td align="left"><input name="usefulDate" id="usefulDate" onClick="WdatePicker()" readonly /></td>
		</tr>
		 -->
		<!-- 
		<tr>
			<td>面值 </td>
			<td><input name="faceValue" id="faceValue" value="" onkeyup="this.value = this.value.replace(/[^\d]/g, '');"/></td>
		</tr>
		<tr>
			<td>余额</td>
			<td> <input name=balance id="balance" value="" onkeyup="this.value = this.value.replace(/[^\d]/g, '');" /></td>
		</tr>
		 -->
		
		<tr>
			<td>状态</td>
			<td align="left">
				<select name="status">
					<option value="0">待生效</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>描述</td>
			<td align="left">
				<input id="description" name="description" maxlength="100" />
			</td>
		</tr>
		<tr>
			<td colspan="2"><button type="submit" class="btn_03s" >确认生成</button>&nbsp;
				<button type="button" class="btn_03s" onclick="parent.dialogClose();" >关闭</button>
			</td>
		</tr>
		</table>
		
		<br/>
				
			</center>
		</div>
		</form>
	 		
	</body>
</html>
