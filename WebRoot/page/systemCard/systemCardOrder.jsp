<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
</style>
 <script type="text/javascript">


//[批量删除] 
function delBatch() {
	var orderIds = $("input[type='checkbox'][name='orderIds']:checked");
	if(orderIds.length == 0){
		alert("请先选择学习卡订单!");
	    return;
	}
	var selectedIds = '';
	if(orderIds != '' && orderIds.length >0 ){
		for(var i=0; i<orderIds.length; i++){
			selectedIds += orderIds[i].value + ',';
		}
	}
	if(confirm("确认删除吗?")){
		var p = {'orderIds':selectedIds};
		$.post("${ctx }/system/SystemCardOrder.do?method=delBatch", p,
  			function(data){
  				if(data >0){
  					alert("学习卡订单删除成功!");
  				}
  				else{
  					alert("由于网络原因,学习卡订单删除不成功,请稍候再试!");
  				}
  				window.location.reload(true);
  		});
	}
}

//[查询] 
function query() {
		document.forms[0].submit();
}

//修改
function updateCardOrder(id){
	window.location.href="${ctx}/system/SystemCardOrder.do?method=edit&id="+id;
}

function viewCardByOrder(orderId){
	var url = "${ctx}/system/SystemCardOrder.do?method=getCardList&orderId="+orderId;
	window.open(url);
}

function doRefresh(){
	var meg = '${meg}';
	if(meg != null && meg !=''){
		alert(meg);
		document.forms[0].submit();
	}
}

</script>
	</head>
	<jsp:include page="/page/tableColor.jsp"></jsp:include>
	<body bgcolor="#E7E7E7" onload="doRefresh()">
		
		<div style="margin: auto; text-align: right;">
		<center>
		
		<form name="queryForm" action="${ctx}/system/SystemCardOrder.do" method="post">
		<input type="hidden" id="method" name="method" value="list" />
		<table style="font-size:12px;width:95%;text-align: center;" class="ITS" >
		<tr>
			<td align="left">卡订单管理&nbsp;</td><td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<!-- <tr>
			<td align="left">支付方式
				<select name="paymodeCode">
					<option value="">全部</option>
					<option value="P1">网上银行</option>
					<option value="P2">支付宝</option>
					<option value="P3">信用卡</option>
				</select>
			</td>
			<td align="left">发票状态
				<select name="invoiceStatus">
					<option value="">全部</option>
					<option value="0">不需要发票</option>
					<option value="1">需要发票</option>
					<option value="2">已开发票</option>
				</select>
			</td>
			<td align="left">订单类别
				<select name="orderType">
					<option value="">全部</option>
					<option value="1">CME订单</option>
					<option value="2">全科订单</option>
				</select>
			</td>
			<td align="left">订单状态
				<select name="status">
					<option value="">全部</option>
					<option value="2">未付款</option>
					<option value="3">已付款</option>
					<option value="4">订单完成</option>
					<option value="5">退款</option>
				</select>
			</td>
			
		</tr> -->
		<tr>
			<td align="left" width="20%">订单号
				<input name="orderNumber" id="orderNumber"  class="form-control" value=""  />
			</td>
			<td align="left" width="20%">卡类型名称&nbsp;
				<input name="cardTypeName" id="cardTypeName" class="form-control" value=""/>
			</td>
			<td align="left" width="20%">订单日期&nbsp;
				从<input type="text" name="orderDateStart" value="" onClick="WdatePicker()" class="form-control"/>
			</td>
			<td align="left" width="20%">
				到<input type="text" name="orderDateEnd" value="" onClick="WdatePicker()" class="form-control"/>
			</td>
			
			
		</tr>
		
		<tr>
			<td align="left">用户名&nbsp;
				<input name="accountName" id="accountName" class="form-control" value=""/>
			</td>
			<td align="left">
				姓名&nbsp;
				<input name="realName" id="realName" value="" class="form-control"/>
			</td>
			<td align="center" colspan="2">
				<button type="button"  class="btn btn-primary" onclick="javascript:query();" >搜索</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<!-- <button type="button"  class="btn btn-primary" onclick="javascript:delBatch();" >删除</button> -->
			</td>
		</tr>
		</table>
		
		<br/>
			
				<display:table requestURI="${ctx}/system/SystemCardOrder.do?method=list"
				 id="systemCardOrder" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:95%;" class="ITS" keepStatus="true" export="true" decorator="com.hys.exam.displaytag.TableOverOutWrapper">
					<display:caption media="html"><span style="color:red">${meg}</span></display:caption>
					<display:column title='<input type="checkbox" id="orderIds" onclick="select_all(this)" />全选' style="text-align:center" media="html">
					<input type="checkbox" name="orderIds" value="${systemCardOrder.id }"/>
					</display:column>
					<display:column title="序号" style="text-align:center">
						${systemCardOrder_rowNum}
					</display:column>
					<display:column property="id" title="ID" style="text-align:center"/>
					<display:column property="orderNumber" title="流水号" style="text-align:center" sortable="true"/>
					<display:column property="cardTypeName" title="卡类型名称" style="text-align:center" sortable="true"/>
					<display:column property="userName" title="学员名称" style="text-align:center" sortable="true"/>
					<display:column property="orderDate" title="订单日期" style="text-align:center" sortable="true"/>
					<display:column property="amount" title="总价格" style="text-align:center" sortable="true"/>
					<display:column property="payDate" title="付款日期" style="text-align:center" sortable="true"/>
					<display:column style="text-align:center; width:10%" title="订单状态">
						<c:if test="${systemCardOrder.status == 1 }">正常</c:if>
						<c:if test="${systemCardOrder.status == -1 }">删除</c:if>
						<c:if test="${systemCardOrder.status == 2}">未付款</c:if>
						<c:if test="${systemCardOrder.status == 3}">已付款</c:if>
						<c:if test="${systemCardOrder.status == 4}">订单完成</c:if>
						<c:if test="${systemCardOrder.status == 5}">退款</c:if>
					</display:column>
					<display:column title="操作" media="html" style="text-align:center">
						<a href="javascript:updateCardOrder('${systemCardOrder.id }')" >修改订单</a>&nbsp;
						<c:if test="${systemCardOrder.status == 3 || systemCardOrder.status == 4 }">
							<a href="javascript:viewCardByOrder('${systemCardOrder.id }')" >查看卡信息</a>
						</c:if>
					</display:column>
				</display:table>
			</center>
		</div>
		</form>
	 		
	</body>
</html>
