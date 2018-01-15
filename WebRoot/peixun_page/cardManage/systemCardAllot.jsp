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
<div class="clear"></div>
<!-- 学习卡管理 -->
<div class="none"  style="">
	<!-- 查询条件 -->
	<div class="center">
	<form name="queryForm" id="queryForm" action="${ctx}/cardManage/SystemCard.do?method=allotList" method="post">
		<div class="tiaojian" style="min-height:40px;">
			<%-- <p class="fl">
				<span><font color="red">*</font>卡号：</span>
				<input name="cardNumber" id="cardNumber" value="${cardNumber }"/>
				<span style="padding:0px 5px 0px 5px;">至</span>
				<input name="cardNumberEnd" id="cardNumberEnd" value="${cardNumberEnd }"/>
			</p> --%>
			<p class="fl">
				<span><font color="red"></font>卡号：起</span>
				<input type="text" name="cardNumber" id="cardNumber" value="${cardNumber }"/>
				<span style="padding:0px 5px 0px 5px;">至</span>
				<input type="text" name="cardNumberEnd" id="cardNumberEnd" value="${cardNumberEnd }"/>
			</p> 
			<p class="fl jianju">
				<span><i style="color:red;"></i>卡类型：</span>				
				<input name="cardTypeName" id="cardTypeName" class="ka_click" value="${cardTypeName }"/>
				<input type="hidden" id="cardTypeId" name="cardTypeId" value="${cardTypeId }" />
			</p>
			<div class="fl cas_anniu">
				<a href="javascript:query();" class="fl xuexike" style="width:70px;margin-left:70px;">查询</a>
			</div>
		</div>
	</form>
	</div>
	<div class="clear"></div>
	<div class="bjs"></div>
	<!-- 内容 -->
	<div class="center none" style="">
		<div class="center01">
			<div class="mt20 kit1_tiaojia">
				<div class="fl cl_piliang">
					<i class="fl" style="color:red;line-height:28px;">*</i>					
					<input type="radio" checked="true" name="selectAllot" class="fl cl_input01" value="1" id="selectAllot1" onclick="setAllot('1')">
					<span class="fl">批量分配(张)</span>
					<!-- <input type="text"  class="fl cl_input02"/> -->
					<input type="text" id="allotNum" class="fl cl_input02" value="" size="5" onkeyup="this.value = this.value.replace(/[^\d\.\-]/g, '');" />
				</div>
				<div class="fl ml30 cl_piliang">
					<i class="fl" style="color:red;line-height:28px;">*</i>
					<!-- <input type="radio" name="dan" class="fl cl_input01"/> -->
					<input type="radio" name="selectAllot" class="fl cl_input01" value="2" id="selectAllot2" onclick="setAllot('2')" >
					<span class="fl">手动选择</span>
				</div>
				<div class="fr cas_anniu">
					<a href="javascript:allotCardType();" class="fl xuexike" style="width:80px;">分配卡类型</a>
				</div>
			</div>
<!-- Draw Table -->
			<div class="clear"></div>
			<display:table requestURI="${ctx}/cardManage/SystemCard.do?method=allotList"
				 id="systemCard" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 class="mt10 table" keepStatus="true" decorator="com.hys.exam.displaytag.TableOverOutWrapper">
					<display:caption><span style="color:red">${meg}</span></display:caption>
					<display:column title='<input type="checkbox" id="cardIds" class="chkall" disabled="disabled"/>全选' style="width:5%;">
					<input type="checkbox" name="cardIds" class="chk" value="${systemCard.id }" disabled="disabled"/>
					</display:column>
					<display:column title="序号" style="width:5%;">
						${systemCard_rowNum}
					</display:column>
					<display:column property="id" title="ID" style="width:6%;"/>
					<display:column property="cardNumber" title="卡号" style="width:16%;"/>
					<display:column property="cardPassword" title="卡密码" style="width:10%;"/>
					<display:column property="cardTypeName" title="卡类型名称" style="width:10%;"  />
					<display:column style="width:11%" title="卡状态">
						<c:if test="${systemCard.status == 1 }">
							有效-
							<c:if test="${systemCard.isnotBind==0 }">未绑定</c:if>
							<c:if test="${systemCard.isnotBind==1 }">已绑定</c:if>
						</c:if>
						<c:if test="${systemCard.status == 0 }">待生效</c:if>
						<c:if test="${systemCard.status == -2}">禁用</c:if>
					</display:column>
					<display:column style="width:10%" title="售出状态">
						<c:if test="${systemCard.isselled == 1 }">已售出</c:if>
						<c:if test="${systemCard.isselled == 0}">未售出</c:if>
					</display:column>
					<display:column style="text-align:center; width:10%" title="售卡方式">
						<c:if test="${systemCard.sellStyle == 1 }">网上销售</c:if>
						<c:if test="${systemCard.sellStyle == 2}">地面销售</c:if>
					</display:column>
					<display:column property="faceValue" title="总学时" style="width:5%;"/>
					<display:column property="balance" title="剩时余额" style="width:5%;" />
					<display:column title="有效期" style="width:18%;">
						<fmt:formatDate value="${systemCard.usefulDate }" pattern="yyyy-MM-dd"/>
					</display:column>					
				</display:table>
				
			<input type="hidden" id="items"/>
			<div class="clear"></div> 
		</div>
	</div>
</div>
<input type=hidden id="dataSourceInfos" name="dataSourceinfos"/>

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

$('.ka_click').click(function(){
	//window.location.href="${ctx}/cardManage/SystemCard.do?method=selectCardType";
//	window.location.href = "${ctx}/cardManage/SystemCard.do?method=selectCardType";	
	window.open("${ctx}/cardManage/SystemCard.do?method=selectCardType");

});

 function query() {
 	var cardTypeId = $("#cardTypeId").val();
 	var cardNumberEndVal = $("#cardNumberEnd").val();
 	var cardNumberVal = $("#cardNumber").val();
 	
 	
 	/* if(cardTypeId == '' && cardNumberVal == '' && cardNumberEndVal == ''){
 		alert("请先选择卡类型或输入卡号!");
 		return;
 	} */
 		
 	$('#queryForm').submit();
 }
 
 function setAllot(type){
	if(type == 2){
	    $("#allotNum").val("") ;
		$("#allotNum").attr("disabled","disabled");
		$(".chk").removeAttr("disabled");
		$(".chkall").removeAttr("disabled");
	}else if(type == 1){
		$("#allotNum").removeAttr("disabled");
		$('.chk').removeAttr('checked');
		$(".chk").attr("disabled","disabled");
		$(".chkall").attr("disabled","disabled");		
	}
}

//分配卡类型
function allotCardType(){
	var cardNumber = $("#cardNumber").val();
	var cardNumberAll = ${page.otherResVal.get("blankCardNum")} ; 
	var cardNumberEnd = $("#cardNumberEnd").val(); 
	//两种方式,1：直接输入数量,系统分配。2：手动选择分配。
	var val = $('input:radio[name="selectAllot"]:checked').val();
	var url = "";
	if(val == null){
		alert("请选择批量或者手动选择~");
		return;
	}else if(val == 1){
		var allotNum = $("#allotNum").val();
		var allotSum = ${page.count};
		var cardTypeId = $("#cardTypeId").val();
		if(allotNum == '' || allotNum == 0){
			alert("请输入分配的学习卡数量");
			$("#allotNum").focus();
			return;
		}
		if(allotNum > cardNumberAll){
			alert("该卡类型下只有"+cardNumberAll+"张，达不到需要的"+allotNum+"张，请先制卡~");
			$("#allotNum").val('');
			$("#allotNum").focus();
			return;
		}
		window.open("${ctx}/cardManage/SystemCard.do?method=cardTypeList&&beforeTypeId="+
					cardTypeId+"&allotNum="+allotNum+"&cardNumber="+cardNumber+"&cardNumberEnd="+cardNumberEnd);
		
		var dataSourceInfos=document.getElementById('items').value;

	    if(dataSourceInfos){
			var ret = dataSourceInfos[0];
			if(ret>0){
				alert("卡类型绑定成功！");
			}else{
				alert("由于网络原因,学习卡绑定不成功,请稍候再试!");
			}
			window.location.reload(true);
		};
		/* var dataSourceInfos=window.showModalDialog('${ctx}/cardManage/SystemCard.do?method=cardTypeList&beforeTypeId='+
					cardTypeId+'&allotNum='+allotNum+'&cardNumber='+cardNumber+'&cardNumberEnd='+cardNumberEnd,
		    	null,'scrollbars=yes;resizable=no;help=no;status=no;dialogTop=100;dialogLeft=200;dialogHeight=520px;dialogWidth=750px');
		//window.open("${ctx}/cardManage/SystemCard.do?method=selectCardType");
	    if(dataSourceInfos){
			var ret = dataSourceInfos[0];
			if(ret>0){
				alert("卡类型绑定成功！");
			}else{
				alert("由于网络原因,学习卡绑定不成功,请稍候再试!");
			}
			window.location.reload(true);
			////$("#queryForm").submit();
		}; */
	    
	}else if(val == 2){
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
		/* var dataSourceInfos=window.showModalDialog('${ctx}/cardManage/SystemCard.do?method=cardTypeList&cardIds='+selectedIds,
		    	null,'scrollbars=yes;resizable=no;help=no;status=no;dialogTop=100;dialogLeft=200;dialogHeight=520px;dialogWidth=750px'); */
		window.open("${ctx}/cardManage/SystemCard.do?method=cardTypeList&cardIds="+selectedIds);
		
		var dataSourceInfos=document.getElementById('items').value;

	    if(dataSourceInfos){
			var ret = dataSourceInfos[0];
			if(ret>0){
				alert("卡类型绑定成功！");
			}else{
				alert("由于网络原因,学习卡绑定不成功,请稍候再试!");
			}
			window.location.reload(true);
		}
	}
    
}
	$('.chkall').click(function(){
	if ($(this).attr('checked') == 'checked')
		$('.chk').attr('checked' , 'checked');
	else
		$('.chk').removeAttr('checked');
	});

</script>
</body>
</html>