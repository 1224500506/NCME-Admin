<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>卡类型授权</title>
<%@ include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
</style>
 <script type="text/javascript">
//[查询] 
 function query() {
 	var cardTypeId = $("#cardTypeId").val();
 	if(cardTypeId == ''){
 		alert("请先选择卡类型!");
 		return;
 	}
 		
 	document.forms[0].submit();
 }


//分配卡类型
function allotCardType(){
	var cardNumber = $("#cardNumber").val();
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
		if(allotNum > allotSum){
			alert("该卡类型下只有"+allotSum+"张，达不到需要的"+allotNum+"张，请先制卡~");
			$("#allotNum").val('');
			$("#allotNum").focus();
			return;
		}
		var dataSourceInfos=window.showModalDialog('${ctx}/system/SystemCard.do?method=cardTypeList&beforeTypeId='+
					cardTypeId+'&allotNum='+allotNum+'&cardNumber='+cardNumber+'&cardNumberEnd='+cardNumberEnd,
		    	null,'scrollbars=yes;resizable=no;help=no;status=no;dialogTop=100;dialogLeft=200;dialogHeight=520px;dialogWidth=750px');
	    if(dataSourceInfos){
			var ret = dataSourceInfos[0];
			if(ret>0){
				alert("卡类型绑定成功！");
			}else{
				alert("由于网络原因,学习卡绑定不成功,请稍候再试!");
			}
			window.location.reload(true);
			////$("#queryForm").submit();
		}
	    
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
		var dataSourceInfos=window.showModalDialog('${ctx}/system/SystemCard.do?method=cardTypeList&cardIds='+selectedIds,
		    	null,'scrollbars=yes;resizable=no;help=no;status=no;dialogTop=100;dialogLeft=200;dialogHeight=520px;dialogWidth=750px');
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

//选择卡类型
function selectCardType(){
	var dataSourceInfos=window.showModalDialog('${ctx}/system/SystemCard.do?method=selectCardType',
	    	null,'scrollbars=yes;resizable=no;help=no;status=no;dialogTop=100;dialogLeft=200;dialogHeight=520px;dialogWidth=750px');
	    if(dataSourceInfos){
			queryForm.cardTypeName.value=dataSourceInfos[1];
			queryForm.cardTypeId.value=dataSourceInfos[0];
		}
}

function setAllot(type){
	if(type == 2){
		$("#allotNum").attr("disabled","disabled");
	}else if(type == 1){
		$("#allotNum").removeAttr("disabled");
	}
}

</script>
	</head>
	<body bgcolor="#E7E7E7">
		<jsp:include page="/page/tableColor.jsp"></jsp:include>
		<div style="margin: auto; text-align: right;">
		<center>
		
		<form name="queryForm" id="queryForm" action="${ctx}/system/SystemCard.do?method=allotList" method="post">
		<table style="font-size:12px;width:95%;text-align: center;" class="ITS" >
		<tr>
			<td>卡类型分配管理 &nbsp;---<font color="red">有效-且被使用过的的不能再分配</font></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td> 
		</tr>
		<tr>
			<td>
				开始卡号&nbsp;<font color="red">*</font><input name="cardNumber" id="cardNumber" value="${cardNumber }"/>
				结束卡号&nbsp;<font color="red">*</font><input name="cardNumberEnd" id="cardNumberEnd" value="${cardNumberEnd }"/>
			</td>
			<td>卡类型&nbsp;<font color="red">*</font><input name="cardTypeName" id="cardTypeName" value="${cardTypeName }" onclick="selectCardType()"/>
				<input type="hidden" id="cardTypeId" name="cardTypeId" value="${cardTypeId }" />
			</td>
			<td>
				<button type="button" class="btn_03s" onclick="javascript:query();" >搜索</button>&nbsp;
			</td>
			<td>
				<font class="red_star">*</font><input type="radio" name="selectAllot" value="1" id="selectAllot1" onclick="setAllot('1')">批量分配
					<input type="text" id="allotNum" value="" size="5" onkeyup="this.value = this.value.replace(/[^\d\.\-]/g, '');" />&nbsp;张&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;
				<font class="red_star">*</font><input type="radio" name="selectAllot" value="2" id="selectAllot2" onclick="setAllot('2')" >手动选择&nbsp;&nbsp;
				<button type="button" class="btn_03s" onclick="javascript:allotCardType();" >分配卡类型</button>&nbsp;
			</td>
		</tr>
		</table>
		<strong><span style="color:red">${meg}</span></strong>
		<br/>
			
				<display:table requestURI="${ctx}/system/SystemCard.do?method=allotList"
				 id="systemCard" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:95%;" class="ITS" keepStatus="true" decorator="com.hys.exam.displaytag.TableOverOutWrapper">
					<display:caption><span style="color:red">${meg}</span></display:caption>
					<display:column title='<input type="checkbox" id="cardIds" onclick="select_all(this)" />全选' style="text-align:center">
					<input type="checkbox" name="cardIds" value="${systemCard.id }"/>
					</display:column>
					<display:column title="序号" style="text-align:center">
						${systemCard_rowNum}
					</display:column>
					<display:column property="id" title="ID" style="text-align:center"/>
					<display:column property="cardNumber" title="卡号" style="text-align:center" sortable="true"/>
					<display:column property="cardPassword" title="卡密码" style="text-align:center" sortable="true"/>
					<display:column property="cardTypeName" title="卡类型名称" style="text-align:left" sortable="true" />
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
					<display:column property="balance" title="学时余额" style="text-align:left" sortable="true" />
					<display:column title="有效期" style="text-align:center" sortable="true">
						<fmt:formatDate value="${systemCard.usefulDate }" pattern="yyyy-MM-dd"/>
					</display:column>
					
				</display:table>
			</center>
		</div>
		</form>
	 		
	</body>
</html>
