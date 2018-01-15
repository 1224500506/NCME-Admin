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
	<form name="queryForm" id="selectTypeCard" action="${ctx}/cardManage/SystemCard.do?method=selectCardType" method="post">
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
					<a href="javascript:doSelect();" class="fl lca_queren" style="width:80px;">确认</a>
					<a href="javascript:window.close();" class="fl lca_queren" style="width:80px;margin-left:10px;">取消</a>
					
				</div>
			</div>
			<div class="clear"></div>
			<display:table requestURI="${ctx}/cardManage/SystemCard.do?method=selectCardType"
				 id="isystemCardType" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 class="mt10 table" keepStatus="true" decorator="com.hys.exam.displaytag.TableOverOutWrapper">
					<display:caption><span style="color:red">${meg}</span></display:caption>
					<display:column title='选择' style="text-align:center">
					<input type="radio" name="cardTypeIds" value="${isystemCardType.id }_${isystemCardType.cardTypeName }"/>
					</display:column>
					<display:column title="序号" style="text-align:center">
						${isystemCardType_rowNum}
					</display:column>
					<display:column property="id" title="ID" style="text-align:center"/>
					<display:column property="cardTypeName" title="卡类型名称" style="text-align:center" />
					<display:column title="有效时间" style="text-align:center" >
						<fmt:formatDate value="${isystemCardType.startDate}" pattern="yyyy-MM-dd"/>
					</display:column>
					<display:column title="失效时间" style="text-align:center" >
						<fmt:formatDate value="${isystemCardType.endDate}" pattern="yyyy-MM-dd"/>
					</display:column>
					<display:column property="creditSum" title="总学分" style="text-align:center" />
					<display:column property="price" title="产品价格" style="text-align:center" />
					
				</display:table>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function(){

	//select menu
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "lc_left0" + menuindex;
		$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		//$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");

});

function query() {
		$('#selectTypeCard').submit();
}

//选择
function doSelect(){
	var cardIds = $("input[type='radio'][name='cardTypeIds']:checked");
	if(cardIds.length == 0){
		alert("请选择卡类型!");
	    return;
	}
	var params = cardIds.val();
	var arr = params.split("_");
	/* var items = new Array();
	items[0]=arr[0];
	items[1]=arr[1];
	window.returnValue=items; */
	window.opener.document.getElementById("cardTypeName").value=arr[1];
	window.opener.document.getElementById("cardTypeId").value=arr[0];
	window.close();
	//window.location.href="${ctx}/cardManage/SystemCard.do?method=allotList&cardTypeId="+arr[0]+"&cardTypeName="+arr[1];
}
</script>
</body>
</html>

