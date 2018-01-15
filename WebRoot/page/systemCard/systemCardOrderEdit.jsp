<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="com.hys.auth.model.HysUsers" %>
<%@ page import="com.hys.exam.constants.Constants" %>
<%
   	HysUsers user = (HysUsers)request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
	String name = "";
   	if(null != user){
		  name = user.getLoginName();
   	}
   	
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>卡订单编辑</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/default090909.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/lay.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">

<link rel="stylesheet" href="${ctx}/js/Validform_v5.3/css/valid.css" type="text/css" media="all" />
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/Validform_v5.3.js"></script>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript">
	function changeAmount(value){
		var quantity = $("#quantity").val();
		var price = $("#price").val();
		if(quantity !='' && price != ''){
			var amount = quantity*price;
			$("#amount").val(amount);
		}
	}

</script>
</head>
<body>
<form class="fm1" id="fm1" name="fm1" action="${ctx}/system/SystemCardOrder.do?method=update" method="post">
<table border="0" cellspacing="1" cellpadding="0" width="90%" class="gridtable">
	<tr>
		<td align="center" colspan="3" class="row_tip" height="25">
			卡订单信息
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25" width="20%" align="right">
			卡类型名称：
		</td>
		<td width="30%">
			${order.cardTypeName}
			<input type="hidden" id="cardTypeId" name="cardTypeId" value="${order.cardTypeId}" class="form-control"> 
		</td width="20%">
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			用户：
		</td>
		<td>
			${order.userName}
			<input type="hidden" name="userId" name="userId" value="${order.userId}" class="form-control">
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			<font class="red_star">*</font>订单生成日期：
		</td>
		<td align="left">
			<input type="text" name="orderDate" id="orderDate"
				value="<fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd"/>";
				datatype="*" nullmsg="请选择订单生成时间！" onClick="WdatePicker()"  class="form-control"/>
		</td>
	    <td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	
	<tr>
		<td class="row_tip" height="25" align="right">
			订单数量：
		</td>
		<td>
		<input type="text" id="quantity"   name="quantity"  class="form-control"
			onkeyup="this.value = this.value.replace(/[^\d\-]/g, ''); changeAmount(this.value);"
			maxlength="8" size="25" datatype="n" nullmsg="请输入订单数量！" errormsg="请输入数字!" value="${order.quantity}"/>
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			单价：
		</td>
		<td>
		<input type="text" id="price" datatype="*" nullmsg="请输入单价！" errormsg="请输入数字!"  class="form-control"
			onkeyup="this.value = this.value.replace(/[^\d\.\-]/g, ''); changeAmount(this.value);"
		   name="price" maxlength="200" size="25" value="${order.price}"/>
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			总价格：
		</td>
		<td>
		<input type="text" id="amount"   name="amount" maxlength="200" size="25" value="${order.amount}"  class="form-control" readonly/>
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>
     </tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			付款时间：
		</td>
		<td align="left">
			<input type="text" name="payDate" id="payDate" value="${order.payDate}" 
				nullmsg="请选择付款时间！" onClick="WdatePicker()"  class="form-control"/>
		</td>
	    <td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			支付方式：
		</td>
		<td>
		<input type="text" id="paymodeCode"  class="form-control"   name="paymodeCode" maxlength="25" size="25" value="${order.paymodeCode}"/>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>		
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			发票状态：
		</td>
		<td>
			<select name="invoiceStatus" datatype="*" nullmsg="请选择发票状态！" style = "display:none;">
				<option value="" <c:if test="${order.invoiceStatus eq ''}">selected</c:if> >请选择</option>
				<option value="0" <c:if test="${order.invoiceStatus == 0}">selected</c:if> >不需要发票</option>
				<option value="1" <c:if test="${order.invoiceStatus == 1}">selected</c:if> >需要发票</option>
				<option value="2" <c:if test="${order.invoiceStatus == 2}">selected</c:if> >已开发票</option>
			</select>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>		
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			发票抬头：
		</td>
		<td>
		<input type="text" id="invoiceTitle"   name="invoiceTitle" class="form-control" maxlength="25" size="25" value="${order.invoiceTitle}"/>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>		
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			收件人：
		</td>
		<td>
		<input type="text" id="recipient"   name="recipient" class="form-control" maxlength="25" size="25" value="${order.recipient}"/>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>		
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			收件人联系电话：
		</td>
		<td>
		<input type="text" id="tel"   name="tel" class="form-control" maxlength="25" size="25" value="${order.tel}"/>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>		
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			收件人联系地址：
		</td>
		<td>
		<input type="text" id="address"   name="address" class="form-control" maxlength="25" size="25" value="${order.address}"/>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>		
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			订单处理人：
		</td>
		<td>
		<input type="text" id="orderOper"   name="orderOper" class="form-control" maxlength="25" size="25" value="${order.orderOper}"/>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>		
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			订单处理时间：
		</td>
		<td align="left">
			<input type="text" name="payDate" id="payDate" value="${order.payDate}" 
				nullmsg="请选择付款时间！" onClick="WdatePicker()" class="form-control" />
		</td>
	    <td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			备注：
		</td>
		<td>
			<textarea rows="5" cols="100" id="remark"   name="remark" maxlength="25" size="25" >${order.remark}</textarea>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>		
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			邮编：
		</td>
		<td>
		<input type="text" id="postCode" datatype="p" errormsg="请输入正确邮编!" class="form-control"   name="postCode" maxlength="25" size="25" value="${order.postCode}"/>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>		
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			订单号：
		</td>
		<td>
			${order.orderNumber}
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>		
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			订单状态：
		</td>
		<td>
			<select name="status" datatype="*" nullmsg="请选择状态！">
				<option value="" <c:if test="${order.status eq '' }">selected</c:if> >请选择</option>
				<option value="-1" <c:if test="${order.status ==-1 }">selected</c:if> >删除</option>
				<option value="1" <c:if test="${order.status == 1 }">selected</c:if> >正常</option>
				<option value="2" <c:if test="${order.status ==2 }">selected</c:if> >未付款</option>
				<option value="3" <c:if test="${order.status ==3 }">selected</c:if> >已付款</option>
				<option value="4" <c:if test="${order.status ==4 }">selected</c:if> >订单完成</option>
				<option value="5" <c:if test="${order.status ==5 }">selected</c:if> >退款</option>
			</select>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>		
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			订单是否绑定学习卡：
		</td>
		<td colspan="2">
			<c:if test="${empty cardList }">
				此订单暂时没绑定学习卡。 目前可以绑定的数量是${noBindCount }张。
				<%
					if(name.equals("admin")){
				%>
				<input type="button" class="btn_03s" size="20px" onclick="createCard('${order.id}')" value="立即绑定" />
				<%} %>
			</c:if>
			<c:if test="${not empty cardList }">
				<c:forEach items="${cardList }" varStatus="stauts" var="card">
					学习卡：${card.cardNumber }，密码：${card.cardPassword }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</c:forEach>
			</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="3" align="center" class="row_tip">
		<%
			if(name.equals("admin")){
		%>
		    <input type="submit" class="btn btn-primary" value="保 存" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<%} %>
		    <input type="button" class="btn btn-primary" value="返 回" onclick="javascript:history.back(-1);"/>
		</td>
	</tr>
</table>
<input type="hidden" id="id" name="id" value="${order.id}"/>
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
    
    function createCard(orderId){
    	var status = '${order.status}';
    	//if(status <3){
    	//	alert("此订单尚未支付，不能绑卡~");
    	//	return;
    	//}
    	var quantity = '${order.quantity}';
    	var noBindCount = '${noBindCount}';
    	var cardTypeId = $("#cardTypeId").val();
    	if(quantity<=0){
    		alert("订单数量至少为1个，请先修改订单数量，保存后再来生成学习卡~");
    		return;
    	}
    	if(quantity>noBindCount){
    		alert("订单数量大于剩余空白卡数量,请先进行绑定或者制卡~");
    		return ;
    	}
    	
    	var p = {'orderId':orderId,'cardTypeId':cardTypeId,'quantity':quantity};
		$.post("${ctx }/system/SystemCardOrder.do?method=createCard", p,
  			function(data){
  				if(data >0){
  					alert("生成学习卡成功!");
  				}
  				else{
  					alert("由于网络原因,学习卡生成不成功,请稍候再试!");
  				}
  				window.location.reload(true);
  		});
    }
</script>
</body>
</html>