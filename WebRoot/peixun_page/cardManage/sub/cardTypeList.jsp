<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<%@include file="/commons/taglibs.jsp"%>
</head>
<%@include file="/peixun_page/top.jsp"%>
<!-- 卡类型浮层 -->
<body>

<div class="none1">
	<!-- 查询条件 -->
	<div class="center">
	<form name="queryForm" id="cardType" action="${ctx}/cardManage/SystemCard.do" method="post">
	<input type="hidden" id="method" name="method" value="cardTypeList" />
		<div class="tiaojian" style="min-height:40px;">
			<p class="fl">
				<span>卡类型：</span>				
				<input name="cardTypeName" id="cardTypeName" value="">
			</p>
			<div class="fl cas_anniu">
				<a href="javascript:query();" class="fl" style="width:70px;margin-left:70px;">查询</a>
				<!-- <button type="button" class="btn_03s" onclick="javascript:query();">搜索</button> -->
			</div>
		</div>
	</form>
	</div>
	<div class="clear"></div>
	<div class="bjs"></div>
	<!-- 内容 -->
	<div class="center" style="">
		<div class="center01">
			<div class="mt20 kit1_tiaojia">
				<div class="fr cas_anniu">
					<a href="javascript:doAllot();" class="fl lca_queren" style="width:80px;">确认</a>
					<a href="javascript:window.close();" class="fl lca_queren" style="width:80px;margin-left:10px;">取消</a>
					
				</div>
			</div>
			<div class="clear"></div>
			<display:table requestURI="${ctx}/cardManage/SystemCard.do?method=cardTypeList"
				 id="isystemCardType" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				  class="mt10 table" keepStatus="true"
				  decorator="com.hys.exam.displaytag.TableOverOutWrapper" >
					<display:caption><span style="color:red">${meg}</span></display:caption>
					<display:column title='选择' style="text-align:center">
					<input type="radio" name="cardTypeIds" value="${isystemCardType.id }"/>
					</display:column>
					<display:column title="序号" style="text-align:center">
						${isystemCardType_rowNum}
					</display:column>
					<display:column property="id" title="ID" style="text-align:center"/>
					<display:column property="cardTypeName" title="卡类型名称" style="text-align:center"/>
					<display:column property="startDate" title="有效时间" style="text-align:center"/>
					<display:column property="endDate" title="失效时间" style="text-align:center"/>
					<display:column property="creditSum" title="总学时" style="text-align:center" />
					<display:column property="price" title="产品价格" style="text-align:center" />
					
				</display:table>
		<%-- <form action="${ctx}/cardManage/SystemCard.do?method=allotList" id="item" method="post" >
			<input type="hidden" name="items" value=""/>
		</form> --%>
		</div>
	</div>
</div>

<script type="text/javascript">
//[查询] 
function query() {
		$('#cardType').submit();
}

//选择
function doAllot(){
	var retdata = 0;
	var cardTypeIds = $("input[type='radio'][name='cardTypeIds']:checked");
	if(cardTypeIds.length == 0){
		alert("请选择卡类型!");
	    return;
	}
	var cardTypeId = cardTypeIds.val();
	
	var cardNumber = '${cardNumber}';
	var cardNumberEnd = '${cardNumberEnd}';
	
	var beforeTypeId = '${beforeTypeId}';
	var cardIds = '${cardIds}';
	var allotNum = '${allotNum}';
	if(cardIds == "" && allotNum != "-1"){		//YHQ，2017-03-26
		retdata = $.ajax({url: "${ctx }/cardManage/SystemCard.do?method=allotCardTypeByNum&beforeTypeId="+
				beforeTypeId+"&allotNum="+allotNum+"&afterTypeId="+cardTypeId+
				"&cardNumber="+cardNumber+"&cardNumberEnd="+cardNumberEnd, async: false}).responseText;
	}else{		
		retdata = $.ajax({url: "${ctx }/cardManage/SystemCard.do?method=allotCardType&cardIds="+
				cardIds+"&cardTypeId="+cardTypeId, async: false}).responseText;
	}
	
	var items = new Array();
	items[0]= retdata;
	//window.returnValue=items;
	window.opener.document.getElementById('items').value=items;
	if(items[0]>0){
		alert("卡类型绑定成功！");
	}else if(items[0] == -1){
		alert("已失效的卡类型不容许分配卡！");
	}else{
		alert("学习卡绑定不成功，卡不足或卡的余额不为零，请稍候再试!");
	}
	window.close();
	window.opener.location.reload(true);
}
</script>
</body>
</html>

