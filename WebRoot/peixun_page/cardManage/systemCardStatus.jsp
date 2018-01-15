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

<body>
<!-- 学习卡管理 -->
<div class="none"  style="">
	<!-- 查询条件 -->
	<div class="center">
	  <form name="queryForm" id="queryForm" action="${ctx}/cardManage/SystemCardStatus.do?method=list" method="post">
		  <div class="tiaojian" style="min-height:40px;">
			<%-- <p class="fl">
				<span>卡号：</span>
				<input name="cardNumberStart" id="cardNumberStart" value="${cardNumberStart }"/>
				<span style="padding:0px 5px 0px 5px;">至</span>
				<input name="cardNumberEnd" id="cardNumberEnd" value="${cardNumberEnd }"/>
			</p> --%>	
			<p class="fl">
				<span>卡号：</span>
				<input type="text" name="cardNumberStart" id="cardNumberStart" value="${cardNumberStart }"
						onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
				<span style="padding:0px 5px 0px 5px;">至</span>
				<input type="text" name="cardNumberEnd" id="cardNumberEnd" value="${cardNumberEnd }"
						onkeyup="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
			</p>		
			<div class="fl cas_anniu">
				<a href="#" class="fl xuexike" style="width:70px;margin-left:70px;">查询</a>
			</div>
		  </div>
	  </form>
	</div>
	<div class="clear"></div>
	<div class="bjs"></div>
	<!-- 内容 -->
	<div class="center none" style="">
		<div class="center01">
			<display:table requestURI="${ctx}/cardManage/SystemCardStatus.do?method=list"
				 id="systemCard" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:95%;" class="mt10 table" keepStatus="true">
					<display:caption><span style="color:red">${meg}</span></display:caption>
					<display:column title='<input type="checkbox" id="cardIds" value="" class="chkall" />全选' style="width:10%;">
						<input type="checkbox" name="cardIds" class="chk" value="${systemCard.id }"/>
					</display:column>
					<display:column title="序号" style="width:5%;">
						${systemCard_rowNum}
					</display:column>
					<display:column property="id" title="ID" style="width:10%;"/>
					<display:column property="cardNumber" title="卡号" style="width:15%;"/>
					<display:column property="cardPassword" title="卡密码" style="width:10%;"/>
					<%-- <display:column title="导入时间" style="width:10%;">
						<fmt:formatDate value="${systemCard.importDate}" pattern="yyyy-MM-dd"/>
					</display:column> --%>
					<display:column title="有效期" style="width:10%;">
						<fmt:formatDate value="${systemCard.usefulDate}" pattern="yyyy-MM-dd"/>
					</display:column>
					<display:column property="balance" title="剩时余额" style="width:10%;"/>
					<display:column style="width:10%" title="卡状态">
						<c:if test="${systemCard.status == 1 }">有效</c:if>
						<c:if test="${systemCard.status == 0 }">待生效</c:if>
						<c:if test="${systemCard.status == -2}">禁用</c:if>
						<c:if test="${systemCard.status == -1}">无效/删除</c:if>
						<c:if test="${systemCard.status == 2}">已绑定</c:if>
					</display:column>
					<display:column title="操作" media="html" style="width:10%;">
						<a href="javascript:viewDetail('${systemCard.id }')" >查看详细</a>
					</display:column>
				</display:table>
			
			<div class="clear"></div> 			
		</div>
	</div>
</div>

<!-- 修改密码 -->
<div class="toumingdu make01" style="display:none;">
	
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
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
});

//查看
$('.xuexike').click(function(){
	$('#queryForm').submit();
});


//查看学习卡详细
function viewDetail(cardId){
	var url = "${ctx }/cardManage/SystemCardStatus.do?method=viewDetail&cardId="+cardId;
	window.location.href = url;
}

//导出卡记录
function exportCards(recordId){
	window.location.href = "${ctx}/cardManage/SystemCard.do?method=exportCards&recordId="+recordId;
}

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
	
		//check all
$('.chkall').click(function(){
	if ($(this).attr('checked') == 'checked')
		$('.chk').attr('checked' , 'checked');
	else
		$('.chk').removeAttr('checked');
});
</script>

</body>
</html>