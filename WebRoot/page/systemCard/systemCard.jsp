<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/icon.css">
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/UI/locale/easyui-lang-zh_CN.js"></script>
<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
</style>
 <script type="text/javascript">


//[批量删除] 
function delBatch() {
	var cardIds = $("input[type='checkbox'][name='cardIds']:checked");
	if(cardIds.length == 0){
		alert("请先选择学习卡!");
	    return;
	}
	var selectedIds = '';
	if(cardIds != '' && cardIds.length >0 ){
		for(var i=0; i<cardIds.length; i++){
			selectedIds += cardIds[i].value + ',';
		}
	}
	if(confirm("确认删除吗?")){
		var p = {'cardIds':selectedIds};
		$.post("${ctx }/system/SystemCard.do?method=delBatch", p,
  			function(data){
  				if(data >0){
  					alert("学习卡删除成功!");
  				}
  				else{
  					alert("由于网络原因,学习卡删除不成功,请稍候再试!");
  				}
  				window.location.reload(true);
  		});
	}		
}

//[查询] 
function query() {
	var cardTypeId = $("#cardTypeId").val();
	if(cardTypeId == ''){
		alert("请先选择卡类型!");
		reutrn;
	}
		
	document.forms[0].submit();
}

//选择卡类型
function selectCardType(){
	window.open("${ctx}/system/SystemCard.do?method=selectCardType",
			null,'scrollbars=yes;resizable=no;help=no;status=no;dialogTop=100;dialogLeft=200;dialogHeight=520px;dialogWidth=750px');
}

//新增
function addCard(){
	var cardTypeId = $("#cardTypeId").val();
	if(cardTypeId == ''){
		alert("请先选择卡类型!");
		reutrn;
	}
	document.location.href = "${ctx}/system/SystemCard.do?method=edit&cardTypeId="+cardTypeId;
}

//修改
function updateCard(id){
	var cardTypeId = $("#cardTypeId").val();
	if(cardTypeId == ''){
		alert("请先选择卡类型!");
		reutrn;
	}
	document.location.href = "${ctx}/system/SystemCard.do?method=edit&id="+id+"&cardTypeId="+cardTypeId;
}

//更改卡状态
function updateStatus(status){
	var cardIds = $("input[type='checkbox'][name='cardIds']:checked");
	if(cardIds.length == 0){
		alert("请先选择学习卡!");
	    return;
	}
	var selectedIds = '';
	if(cardIds != '' && cardIds.length >0 ){
		for(var i=0; i<cardIds.length; i++){
			selectedIds += cardIds[i].value + ',';
		}
	}
	var p = {'cardIds':selectedIds,'status':status};
	
	$.post("${ctx }/system/SystemCardStatus.do?method=updateStatus", p,
			function(data){
				if(data >0){
					alert("更改成功!");
				}
				else{
					alert("由于网络原因,更改不成功,请稍候再试!");
				}
				location = "${ctx }/system/SystemCard.do?method=list";
		});
}

//重置余额
function resetBalance(){
	var cardIds = $("input[type='checkbox'][name='cardIds']:checked");
	if(cardIds.length == 0){
		alert("请先选择学习卡!");
	    return;
	}
	var selectedIds = '';
	if(cardIds != '' && cardIds.length >0 ){
		for(var i=0; i<cardIds.length; i++){
			selectedIds += cardIds[i].value + ',';
		}
	}
	var p = {'cardIds':selectedIds,'balance':0};
	$.post("${ctx }/system/SystemCardStatus.do?method=updateStatus", p,
			function(data){
				if(data >0){
					alert("重置余额成功!");
				}
				else{
					alert("由于网络原因,重置余额不成功,请稍候再试!");
				}
				location = "${ctx }/system/SystemCard.do?method=list";
		});
}

//更改销售状态
function updateSelled(status){
	var cardIds = $("input[type='checkbox'][name='cardIds']:checked");
	if(cardIds.length == 0){
		alert("请先选择学习卡!");
	    return;
	}
	var selectedIds = '';
	if(cardIds != '' && cardIds.length >0 ){
		for(var i=0; i<cardIds.length; i++){
			selectedIds += cardIds[i].value + ',';
		}
	}
	var p = {'cardIds':selectedIds,'status':status};
	
	$.post("${ctx }/system/SystemCardStatus.do?method=updateSelled", p,
			function(data){
				if(data >0){
					alert("更改成功!");
				}
				else{
					alert("由于网络原因,更改不成功,请稍候再试!");
				}
				location = "${ctx }/system/SystemCard.do?method=list";
		});
}

//更改销售方式
function updateSellStyle(status){
	var cardIds = $("input[type='checkbox'][name='cardIds']:checked");
	if(cardIds.length == 0){
		alert("请先选择学习卡!");
	    return;
	}
	var selectedIds = '';
	if(cardIds != '' && cardIds.length >0 ){
		for(var i=0; i<cardIds.length; i++){
			selectedIds += cardIds[i].value + ',';
		}
	}
	var p = {'cardIds':selectedIds,'status':status};
	
	$.post("${ctx }/system/SystemCardStatus.do?method=updateSellStyle", p,
			function(data){
				if(data >0){
					alert("更改成功!");
				}
				else{
					alert("由于网络原因,更改不成功,请稍候再试!");
				}
				location = "${ctx }/system/SystemCard.do?method=list";
		});
}

//绑定用户
function bindUser(cardId){
	var url = "${ctx }/system/SystemCardStatus.do?method=getBindUser&cardId="+cardId+"&isBind=0";
	window.location.href = url;
}

//解绑用户
function unBindUser(cardId){
	var url = "${ctx }/system/SystemCardStatus.do?method=getBindUser&cardId="+cardId+"&isBind=1";
	window.location.href = url;
}

//直接快速设置学习卡
function dirictProcessCard(id){
	var url = "${ctx }/system/SystemCard.do?method=dirictProcessCard&cardId="+id;
	window.location.href = url;
}

//导入学习卡
function importCards(){
	alert("导入学习卡!");
}

//关闭弹出层
function dialogClose(){
  $("#dlg").dialog( "close" );
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
		
		<form name="queryForm" action="${ctx}/system/SystemCard.do" method="post">
		<input type="hidden" id="method" name="method" value="list" />
		
		<table style="font-size:12px;width:95%;text-align: center;" class="ITS" >
		<tr>
			<td><font color="red">搜索，添加，修改之前 请先选择卡类型！</font></td>
			<td colspan="3" align="left">
				每页显示&nbsp;<input  id="pageSize" name="pageSize" value="${pageSize }" />条，最好<font color="red">不要超过1000条</font>
			</td>
			<!-- <td>
				
				<button type="button" class="btn_03s" onclick="javascript:importCards();" >导入学习卡</button>
				 
			</td> -->
		</tr>
		<tr>
			<td>
				开始卡号&nbsp;<input name="cardNumber" id="cardNumber" value="${cardNumber }"/>
				结束卡号&nbsp;<input name="cardNumberEnd" id="cardNumberEnd" value="${cardNumberEnd }"/>
			</td>
			<td>卡类型&nbsp;<input name="cardTypeName" id="cardTypeName" value="${cardTypeName }" onclick="selectCardType()"/>
				<input type="hidden" id="cardTypeId" name="cardTypeId" value="${cardTypeId }" />
			</td>
			<td>
				<button type="button" class="btn_03s" onclick="javascript:query();" >搜索</button>&nbsp;
			</td>
			<td>
				<button type="button" class="btn_03s" onclick="javascript:addCard();" >添加</button>&nbsp;
				<button type="button" class="btn_03s" onclick="javascript:updateStatus(1);" >有效</button>&nbsp;
				<button type="button" class="btn_03s" onclick="javascript:updateStatus(-2);" >禁用</button>&nbsp;
				<button type="button" class="btn_03s" onclick="javascript:updateSelled(1);" >设为已售</button>&nbsp;
				<button type="button" class="btn_03s" onclick="javascript:updateSellStyle(1);" >网上销售</button>&nbsp;
				<button type="button" class="btn_03s" onclick="javascript:updateSellStyle(2);" >地面销售</button>&nbsp;
				<!-- <button type="button" class="btn_03s" onclick="javascript:delBatch();" >删除</button>&nbsp;-->
				<!-- <button type="button" class="btn_03s" onclick="javascript:updateStatus(0);" >待生效</button> -->
				<!-- <button type="button" class="btn_03s" onclick="javascript:resetBalance();" >重置余额</button> -->
			</td>
		</tr>
		</table>
		
		<br/>
			
				<display:table requestURI="${ctx}/system/SystemCard.do?method=list"
				 id="systemCard" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:95%;" class="ITS" keepStatus="true" decorator="com.hys.exam.displaytag.TableOverOutWrapper">
					<display:caption><span style="color:red">${meg}</span></display:caption>
					<display:column title='<input type="checkbox" id="cardIds" value="checkbox" onclick="select_all(this)" />全选' style="text-align:center">
					<input type="checkbox" name="cardIds" value="${systemCard.id }"/>
					</display:column>
					<display:column title="序号" style="text-align:center">
						${systemCard_rowNum}
					</display:column>
					<display:column property="id" title="ID" style="text-align:center"/>
					<display:column property="cardNumber" title="卡号" style="text-align:center" sortable="true"/>
					<display:column property="cardPassword" title="卡密码" style="text-align:center" sortable="true"/>
					<display:column property="cardTypeName" title="卡类型名称" style="text-align:center" sortable="true"/>
					<display:column title="有效期" style="text-align:center" sortable="true">
						<fmt:formatDate value="${systemCard.usefulDate}" pattern="yyyy-MM-dd"/>
					</display:column>
					<display:column style="text-align:center; width:10%" title="卡状态">
						<c:if test="${systemCard.status == 1 }">
							有效-
							<c:if test="${systemCard.isnotBind==0 }">未绑定</c:if>
							<c:if test="${systemCard.isnotBind==1 }">已绑定</c:if>
						</c:if>
						<c:if test="${systemCard.status == 0 }">待生效</c:if>
						<c:if test="${systemCard.status == -2}">禁用</c:if>
					</display:column>
					<display:column style="text-align:center; width:5%" title="售出状态">
						<c:if test="${systemCard.isselled == 1 }">已售出</c:if>
						<c:if test="${systemCard.isselled == 0}">未售出</c:if>
					</display:column>
					<display:column style="text-align:center; width:5%" title="售卡方式">
						<c:if test="${systemCard.sellStyle == 1 }">网上销售</c:if>
						<c:if test="${systemCard.sellStyle == 2}">地面销售</c:if>
					</display:column>
					<display:column property="faceValue" title="总学时" style="text-align:left"  />
					<display:column property="balance" title="余额" style="text-align:left" sortable="true" />
					<display:column title="操作" media="html" style="text-align:center">
						<a href="javascript:updateCard('${systemCard.id }')" >修改</a>
						<a href="javascript:bindUser('${systemCard.id }')" >绑定用户</a>
						<a href="javascript:unBindUser('${systemCard.id }')" >解绑用户</a>
						<%-- <a href="javascript:dirictProcessCard('${systemCard.id }')" >快速设置</a> --%>
					</display:column>
				</display:table>
			</center>
		</form>
	 	</div>
	</body>
</html>
