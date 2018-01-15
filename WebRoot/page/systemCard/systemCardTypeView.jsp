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
<title>卡类型查看</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/default090909.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/lay.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">

<link rel="stylesheet" href="${ctx}/js/Validform_v5.3/css/valid.css" type="text/css" media="all" />
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
</head>
<body>
<table border="0" cellspacing="1" cellpadding="0" width="90%" class="gridtable">
	<tr>
		<td align="center" colspan="3" class="row_tip" height="25">
			卡类型信息
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			卡类型名称：
		</td>
		<td>
		 ${type.cardTypeName} 
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			卡有效开始时间
		</td>
		<td align="left">
			 <fmt:formatDate value="${type.startDate}" pattern="yyyy-MM-dd"/> 
		</td>
	    <td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			卡有效期结束时间
		</td>
		<td>
		 <fmt:formatDate value="${type.endDate}" pattern="yyyy-MM-dd"/> 
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>	
	</tr>
	<%-- <tr>
		<td class="row_tip" height="25">
			付费学分范围:
		</td>
		<td>
		 ${type.creditScope} 
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr> --%>
	<tr>
		<td class="row_tip" height="25">
			总学时：
		</td>
		<td>
		 ${type.creditSum} 
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			产品价格：
		</td>
		<td>
		 ${type.price} 
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>
     </tr>
	<%-- <tr>
		<td class="row_tip" height="25">
			销售元值：
		</td>
		<td>
		 ${type.evpValue} 
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>	
	</tr> --%>
	<tr>
		<td class="row_tip" height="25">
			销售方式：
		</td>
		<td>
			 <c:if test="${type.isNetpay == 1 }">仅限网上销售</c:if>  
			 <c:if test="${type.isNetpay == 2 }">仅限地面销售（前台不需要销售）</c:if>  
			 <c:if test="${type.isNetpay == 3 }">地面网上销售 </c:if> 
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>		
	</tr>
	<tr>
		<td class="row_tip" height="25">
			授权类型：
		</td>
		<td>
			<c:forEach items="${allList}" var="credit" >
				<input type="checkbox" name="creditType" value="${credit.creditType }" <c:if test="${credit.checked eq 1 }">checked</c:if>  disabled="disabled">${credit.creditType}${credit.creditName }&nbsp;&nbsp;&nbsp;
			</c:forEach>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>		
	</tr>
	<tr>
		<td class="row_tip" height="25">
			学习卡使用情况:
		</td>
		<td>
		 	总数：${type.allNum }， 已使用：${type.userdNum }，剩余： ${type.remainNum }
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
 	<tr>
		<td colspan="6" align="center" class="row_tip">
		    <input type="button" class="btn_03s" value="返 回" onclick="javascript:history.back(-1);"/>
		</td>
	</tr>
	 
	</tr>
</table>

</body>
</html>