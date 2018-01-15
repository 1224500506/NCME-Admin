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
<title>卡类型编辑</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/default090909.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/lay.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">

<link rel="stylesheet" href="${ctx}/js/Validform_v5.3/css/valid.css" type="text/css" media="all" />
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/Validform_v5.3.js"></script>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
</head>
<body>
<form class="fm1" id="fm1" name="fm1" action="${ctx}/system/SystemCardType.do?method=save" method="post">
<table border="0" cellspacing="1" cellpadding="0" width="90%" class="gridtable">
	<tr>
		<td align="center" colspan="3" class="row_tip" height="25">
			卡类型信息
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			<font class="red_star">*</font>卡类型名称：
		</td>
		<td>
		<input type="text" id="cardTypeName" class="editInput" name="cardTypeName" maxlength="100" 
			size="25" datatype="*" nullmsg="请输入卡类型名称！" errormsg="卡类型名称最多100个字符！" value="${type.cardTypeName}"/>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			<font class="red_star">*</font>卡有效开始时间
		</td>
		<td align="left">
			<input type="text" name="startDate" id="startDate"
				value="<fmt:formatDate value="${type.startDate}" pattern="yyyy-MM-dd"/>"
				datatype="*" nullmsg="请选择卡有效开始时间！" onClick="WdatePicker()" />
		</td>
	    <td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			<font class="red_star">*</font>卡有效期结束时间
		</td>
		<td>
		<input type="text" id="endDate" name="endDate" datatype="*" nullmsg="请输入卡有效期结束时间！"
			value="<fmt:formatDate value="${type.endDate}" pattern="yyyy-MM-dd"/>"
			onClick="WdatePicker()"  />
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>	
	</tr>
	<tr>
		<td class="row_tip" height="25">
			付费学分范围:
		</td>
		<td>
		<input type="text" id="creditScope" class="editInput" name="creditScope" 
			maxlength="8" size="25" ignore="ignore" datatype="float" errormsg="付费学分范围!" value="${type.creditScope}"/>
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			<font class="red_star">*</font>总学时：
		</td>
		<td>
		<input type="text" id="creditSum" class="editInput" name="creditSum" maxlength="200" size="25" 
			datatype="n" nullmsg="请输入总学时！" errormsg="请输入数字类型的总学时！" value="${type.creditSum}"/>
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
		<input type="text" id="price" class="editInput" name="price" maxlength="200" size="25" value="${type.price}"/>
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>
     </tr>
	<tr>
		<td class="row_tip" height="25">
			销售元值：
		</td>
		<td>
		<input type="text" id="evpValue" class="editInput" name="evpValue" maxlength="10" size="25" value="${type.evpValue}"/>
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>	
	</tr>
	<tr>
		<td class="row_tip" height="25">
			销售方式：
		</td>
		<td>
			<select id="isNetpay" name="isNetpay" >
				<option value="1" <c:if test="${type.isNetpay == 1 }">selected</c:if> >仅限网上销售</option>
				<option value="2" <c:if test="${type.isNetpay == 2 }">selected</c:if> >仅限地面销售（前台不需要销售）</option>
				<option value="3" <c:if test="${type.isNetpay == 3 }">selected</c:if> >地面网上销售</option>
			</select>
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
			<!-- <input type="text" id="creditType" readonly class="editInput" name="creditType" maxlength="25" size="25" value="G1"/> -->
			<c:forEach items="${allList}" var="credit" >
				<input type="checkbox" name="creditType" value="${credit.creditType }" <c:if test="${credit.checked eq 1 }">checked</c:if> >${credit.creditType}${credit.creditName }&nbsp;&nbsp;&nbsp;
			</c:forEach>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>		
	</tr>
	<!-- 
	<tr>
		<td class="row_tip" height="25">
			可学学分：
		</td>
		<td>
		<input type="text" id="balance" class="editInput" name="balance" maxlength="25" size="25" value="${type.balance}"/>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>		
	</tr>
 	-->
	
		<td colspan="6" align="center" class="row_tip">
		    <input type="submit" class="btn_03s" value="保 存" />
		    <input type="button" class="btn_03s" value="返 回" onclick="javascript:history.back(-1);"/>
		</td>
	</tr>
</table>
<input type="hidden" id="id" name="id" value="${type.id}"/>
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