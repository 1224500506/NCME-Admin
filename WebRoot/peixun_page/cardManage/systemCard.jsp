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
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<body>
<div>
	<!-- 查询条件 -->
	<div class="center">
		<form id="queryForm" name="queryForm" action="${ctx}/cardManage/SystemCard.do?method=list" method="post">
		<div class="tiaojian" style="min-height:40px;">
			<p class="fl">
				<span>卡号：</span>
				<input type="text" name="cardNumber" id="cardNumber" value="${cardNumber }"/>
				<span style="padding:0px 5px 0px 5px;">至</span>
				<input type="text" name="cardNumberEnd" id="s_cardNumberEnd" value="${cardNumberEnd }"/>
			</p>
			<p class="fl jianju">
				<span><i style="color:red;">*</i>卡类型：</span>
				<input type="text" class="ka_click" readonly name="cardTypeName" id="cardTypeName" value="${cardTypeName }" onclick="selectCardType()"/>
				<input type="hidden" id="cardTypeId" name="cardTypeId" value="${cardTypeId }" />
			</p>
			<div class="fl cas_anniu">
				<a href="javascript:query();" class="fl" style="width:70px;margin-left:70px;">查询</a>
			</div>
		</div>
		</form>
	</div>
	<div class="clear"></div>
	<div class="bjs"></div>
	<!-- 内容 -->
	<div class="center">
		<div class="center01">
			<div class="mt5 kit1_tiaojia">
				<div class="fr cas_anniu">
					<!-- <a href="#" class="fl xuexike" style="width:80px;">添加</a>  -->
					<a href="javascript:updateStatus(1);" class="fl" style="width:80px;margin-left:10px;">有效</a>
					<a href="javascript:updateStatus(-2);" class="fl" style="width:80px;margin-left:10px;">禁用</a>
					<a href="javascript:updateSelled(1);" class="fl" style="width:80px;margin-left:10px;">设为已售</a>
					<a href="javascript:updateSellStyle(1);" class="fl" style="width:80px;margin-left:10px;">网上销售</a>
					<a href="javascript:updateSellStyle(2);" class="fl" style="width:80px;margin-left:10px;">地面销售</a>
				</div>
			</div>
		</div>
		<div class="clear"></div>
			<display:table requestURI="${ctx}/cardManage/SystemCard.do?method=list" id="p" class="mt10 table" 
				list="${page.list}" size="${page.count}" pagesize="20" partialList="true" 
				excludedParams="method"  keepStatus="true" decorator="com.hys.exam.displaytag.TableOverOutWrapper">
				<display:caption><span style="color:red">${meg}</span></display:caption>
				<display:column title="<input type='checkbox' class='chkall'/>" style="width:2%;"><input type='checkbox' class='chk' value="${p.id}"/></display:column>
				<display:column title="序号" style="width:3%;">${p_rowNum}</display:column>
				<display:column title="ID" style="width:3%;" property="id"></display:column>
				<display:column title="卡号" style="width:8%;" property="cardNumber"></display:column>
				<display:column title="卡密码" style="width:5%;" property="cardPassword"></display:column>
				<display:column title="卡类型名称" style="width:8%;" property="cardTypeName"></display:column>
 				<display:column title="有效期" style="width:6%;">
 					<fmt:formatDate value="${p.usefulDate}" pattern="yyyy-MM-dd"/>
 				</display:column>
 				<display:column title="卡状态" style="width:6%;">
					<c:if test="${p.status == 1 }">
						有效-
						 <c:if test="${p.isnotBind==0 }">未绑定</c:if>
						<c:if test="${p.isnotBind==1 }">已绑定</c:if> 
					</c:if>
					
					<c:if test="${p.status == 2 }">
						有效-已绑定
					</c:if>
					<c:if test="${p.status == 0 }">待生效</c:if>
					<c:if test="${p.status == -2}">禁用</c:if>
 				</display:column>
 				<display:column title="售出状态" style="width:6%;">
					<c:if test="${p.isselled == 1 }">已售出</c:if>
					<c:if test="${p.isselled == 0}">未售出</c:if>
 				</display:column>
 				<display:column title="售卡方式" style="width:6%;">
					<c:if test="${p.sellStyle == 1 }">网上销售</c:if>
					<c:if test="${p.sellStyle == 2}">地面销售</c:if>
 				</display:column>
 				<display:column title="总学时" style="width:5%;" property="faceValue"></display:column>
 				<display:column title="剩余学时" style="width:5%;" property="balance"></display:column>
 				<display:column title="操作" style="width:10%;">
					<a href="javascript:updateCard('${p.id }','${p.cardNumber}','${p.cardPassword}','<fmt:formatDate value="${p.usefulDate}" pattern="yyyy-MM-dd"/>','${p.faceValue}','${p.balance}','${p.sellStyle}','${p.status}')" >修改</a>
					<a href="javascript:bindUser('${p.id }')" >绑定用户</a>
					<a href="javascript:unBindUser('${p.id }')" >解绑用户</a>
 				</display:column>
			</display:table>

	</div>
		<!-- 内容（结束） -->
		
</div>

<!-- 添加 -->
<div class="toumingdu make02" style="display:none;">
	<div class="lec_center" style="height:420px;">
		<div style="padding-top:20px;">
			<input type="hidden" id="id" name="id" value="0"/>
			<!-- <div class="mt10 lc_shengcheng">
				<span>卡号：</span>
				<input type="text" id="cardNumber" name="cardNumber" value="" readonly/>
			</div>
			<div class="clear"></div> -->
			<div class="mt10 lc_shengcheng">
				<span>密码：</span>
				<input type="text" name="cardPassword" id="cardPassword" value=""/>
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>卡有效期：</span>
				<input type="text" id="usefulDate" name="usefulDate" value="" onClick="WdatePicker()" />
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>是否绑定：</span>
				<select class="lc_list" id="isnotBind">
					<option value="1" >是</option> 
					<option value="0" >否</option> 
				</select>
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>总学时：</span>
				<input type="text" id="faceValue" name="faceValue" value="" 
				onkeyup="this.value = this.value.replace(/[^\d\.\-]/g, '');"/>
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>剩余学时：</span>
				<input type="text" id="balance" name="balance" value=""
				onkeyup="this.value = this.value.replace(/[^\d\.\-]/g, '');"/>
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>销售方式：</span>
				<select class="lc_list" id="sellStyle" name="sellStyle">
					<option value="1">网上销售</option>
					<option value="2">地面销售</option>
				</select>
			</div>
			<div class="clear"></div>
			<div class="cas_anniu" style="margin-top:40px;margin-left:170px;">
				<a href="#" class="fl queren" style="width:70px;">确认</a>
				<a href="#" class="fl quxiao" style="width:70px;margin-left:40px;">取消</a>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	//用户选中的卡类型进行回显操作
	/* var carUrlStr = decodeURI(document.location.href.split("cardTypeName=")[1]);
	if(carUrlStr != "undefined"){
		var cartid = carUrlStr.split("&")[1];//.split("cardTypeId=");
		$("#cardTypeName").val(carUrlStr.split("&")[0]);
		$("#cardTypeId").val(cartid.split("=")[1]);
	} */
	
	//select menu
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "lc_left0" + menuindex;
		$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
	
	//check all
		$('.chkall').click(function(){
			if ($(this).attr('checked') == 'checked')
				$('.chk').attr('checked' , 'checked');
			else
				$('.chk').removeAttr('checked');
		});

	//添加
		$('.xuexike').click(function(){

			var obj= $('.empty');
			if(obj != null && obj.length > 0){
				alert("请先选择卡类型!");
				return ;
			}
			$('.make02').show();
		});
		$('.quxiao').click(function(){
			$('.make02').hide();
		});

		$('.queren').click(function(){
			if(parseInt($('#faceValue').val()) < parseInt($('#balance').val())){
				alert("剩余学时不能大于总学时！");
				return;
			}
			
			document.location.href.split("&")[0];
			$('.make02').hide();

			id = $('#id').val();
			cardNumber = $('#cardNumber').val();
			cardPassword = $('#cardPassword').val();
			usefulDate = $('#usefulDate').val();
			faceValue = $('#faceValue').val();
			balance = $('#balance').val();
			sellStyle = $('#sellStyle').val();
			cardTypeName = $('#cardTypeName').val();
			cardTypeId = $('#cardTypeId').val();

			var url = '${ctx}/cardManage/SystemCard.do?method=save';
			var params = 'id='+id;
			params += '&cardNumber=' + cardNumber;
			params += '&cardPassword=' + cardPassword;
			params += '&usefulDate=' + usefulDate;
			params += '&faceValue=' + faceValue;
			params += '&balance=' + balance;
			params += '&sellStyle=' + sellStyle;
			params += '&cardTypeName=' + cardTypeName;
			params += '&cardTypeId=' + cardTypeId;
			
			$.ajax({
				url: url,
				type: 'POST',
				data: params,
				dataType: 'text',
				success: function(result){
					//document.location.href = document.location.href.split("#")[0]+"&cardTypeName="+$('#cardTypeName').val()+"&cardTypeId="+$('#cardTypeId').val();
					document.location.href = document.location.href.split("#")[0]+"&cardTypeId="+$('#cardTypeId').val();
				//encodeURI()
				}
			});	

		});

});

//修改
function updateCard(id,cardNumber,cardPassword,usefulDate,faceValue,balance,sellStyle,status){
	var cardTypeId = $("#cardTypeId").val();
	if(cardTypeId == ''){
		alert("请先选择卡类型!");
		reutrn;
	}

	$('#id').val(id);
	$('#cardNumber').val(cardNumber);
	$('#cardPassword').val(cardPassword);
	$('#usefulDate').val(usefulDate);
	$('#faceValue').val(faceValue);
	$('#balance').val(balance);
	$('#sellStyle').val(sellStyle);
	if(status == 2 || status == "2"){
		$('#isnotBind').val(1);
	}else{
		$('#isnotBind').val(0);
	}

	$('.make02').show();
}

//绑定用户
function bindUser(cardId){
	var url = "${ctx}/cardManage/SystemCardStatus.do?method=getBindUser&cardId="+cardId+"&isBind=0&cardTypeName="+$('#cardTypeName').val()+"&cardTypeId="+$('#cardTypeId').val();
	window.location.href = encodeURI(encodeURI(url));
}

//解绑用户
function unBindUser(cardId){
	var url = "${ctx}/cardManage/SystemCardStatus.do?method=getBindUser&cardId="+cardId+"&isBind=1&cardTypeName="+$('#cardTypeName').val()+"&cardTypeId="+$('#cardTypeId').val();
	window.location.href = encodeURI(encodeURI(url));
}

//选择卡类型
function selectCardType(){
	window.open("${ctx}/cardManage/SystemCard.do?method=selectCardType");
}

//[查询] 
function query() {
	var cardTypeId = $("#cardTypeId").val();
	if(cardTypeId == ''){
		alert("请先选择卡类型!");
		reutrn;
	}
	
	
	$('#queryForm').submit();
}

//更改卡状态
function updateStatus(status){
	var cardIds = $("input[class='chk']:checked");
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
	
	$.post("${ctx}/cardManage/SystemCardStatus.do?method=updateStatus", p,
			function(data){
			var result=eval("("+data+")");//转换为json对象
				if(result.msg == 'success'){
					if(status == -2 && result.disCard != ""){
						alert("操作完成，其中学习卡"+result.disCard+"已绑定用户，不能被禁用！");
					}else{
						alert("更改成功！");
					}
				}else if(result.msg == 'fail'){
					alert("更新失败，请重新尝试！");
				}else{
					alert("由于网络原因,更改不成功,请稍候再试！");
				}
				document.location.href = document.location.href.split("#")[0]+"&cardTypeId="+$('#cardTypeId').val();
		});
}

//更改销售状态
function updateSelled(status){
	var cardIds = $("input[type='checkbox']:checked");
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
	
	$.post("${ctx }/cardManage/SystemCardStatus.do?method=updateSelled", p,
			function(data){
				if(data >0){
					alert("更改成功!");
				}
				else{
					alert("由于网络原因,更改不成功,请稍候再试!");
				}
				document.location.href = document.location.href.split("#")[0]+"&cardTypeId="+$('#cardTypeId').val();
		});
}

//更改销售方式
function updateSellStyle(status){
	var cardIds = $("input[type='checkbox']:checked");
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
	
	$.post("${ctx }/cardManage/SystemCardStatus.do?method=updateSellStyle", p,
			function(data){
				if(data == 0 || data == 1){
					alert("更改成功!");
				}
				else{
					alert("由于网络原因,更改不成功,请稍候再试!");
				}
				document.location.href = document.location.href.split("#")[0]+"&cardTypeId="+$('#cardTypeId').val();
		});
}

</script>
</body>
</html>