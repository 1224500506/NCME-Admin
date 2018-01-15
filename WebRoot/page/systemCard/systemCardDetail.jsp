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
<title>学习卡详细</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/default090909.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/lay.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">

<link rel="stylesheet" href="${ctx}/js/Validform_v5.3/css/valid.css" type="text/css" media="all" />
<script type="text/javascript">

</script>
</head>
<body>
<table border="0" cellspacing="1" cellpadding="0" width="90%" class="gridtable">
	<tr>
		<td align="center" colspan="4" class="row_tip" height="25">
			学习卡信息
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">学习卡卡号：</td>
		<td>${card.cardNumber}</td>
		<td class="row_tip">学习卡密码</td>
        <td>${card.cardPassword }</td>
	</tr>
	<tr>
		<td class="row_tip" height="25"> 卡余额 </td>
		<td align="left"> ${card.balance} </td>
	    <td class="row_tip"> 学习卡状态 </td>
        <td>
        	<c:if test="${card.status == 1 }">有效</c:if>
        	<c:if test="${card.status == -1 }">无效</c:if>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25"> 父卡 </td>
		<td align="left">&nbsp;</td>
	    <td class="row_tip">订单号</td>
        <td>&nbsp;</td>
	</tr>
	<!-- --------------------------------绑定的用户信息----------------------------------- -->
	<tr>
		<td align="center" colspan="4" class="row_tip" height="25">
			用户信息
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">用户名：</td>
		<td>${userInfo.accountName}</td>
		<td class="row_tip">真实姓名</td>
        <td>${userInfo.realName }</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">绑定时间：</td>
		<td>${userInfo.bindDate}</td>
		<td class="row_tip">销售渠道</td>
        <td>
        	<c:if test="${cardType.isNetpay == 1 }">仅限网上销售</c:if>
        	<c:if test="${cardType.isNetpay == 2 }">仅限地面销售（前台不需要销售）</c:if>
        	<c:if test="${cardType.isNetpay == 3 }">地面网上销售</c:if>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25">所在医院</td>
		<td colspan="3">&nbsp;</td>
	</tr>
	<!-- --------------------------------卡类型信息----------------------------------- -->
	<tr>
		<td align="center" colspan="4" class="row_tip" height="25">
			卡类型信息
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">卡类型（英文）：</td>
		<td>${cardType.id}</td>
		<td class="row_tip">卡类型（中文）</td>
        <td>${cardType.cardTypeName }</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">生效时间：</td>
		<td>${cardType.startDate}</td>
		<td class="row_tip">失效时间</td>
        <td>${cardType.endDate }</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">总学分：</td>
		<td>${cardType.creditSum}</td>
		<td class="row_tip">学分范围</td>
        <td>${cardType.balance }</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">付费类型</td>
		<td colspan="3">${cardType.creditScope }</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">课程列表</td>
		<td colspan="3">
			<select name="choices_c" size="5" style="width: 100%" multiple="multiple" >
                 <c:forEach items="${course_list }" var="c" varStatus="status">
                 		<option value="${c.id }">
                			${status.index + 1 }：${c.studyCourseName }--${c.summary }
                		</option>
                 </c:forEach>
             </select>
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">使用地区</td>
		<td colspan="3">
			<select name="choices_o" size="5" style="width: 100%" multiple="multiple" >
                 <c:forEach items="${org_list }" var="c" varStatus="status_o">
                 		<option value="${c.organId }">
                			${status_o.index + 1 }：${c.name }
                		</option>
                 </c:forEach>
             </select>
		</td>
	</tr>
</table>
<table border="0" cellspacing="1" cellpadding="0" width="90%" class="gridtable">
	<tr>
		<td colspan="6" align="center" class="row_tip">
		    <input type="button" class="btn_03s" value="返 回" onclick="javascript:history.back(-1);"/>
		</td>
	</tr>
</table>

</body>
</html>