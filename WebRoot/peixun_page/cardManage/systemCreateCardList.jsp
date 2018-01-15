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
<div>
	<!-- 内容 -->
	<div class="center">
		<div class="center01">
			<div class="mt5 kit1_tiaojia">
				<div class="fr cas_anniu">
					<a href="#" class="fl xuexike" style="width:100px;">生成学习卡</a>
				</div>
			</div>
		</div>
		<div class="clear"></div>
			<display:table requestURI="${ctx}/cardManage/SystemCard.do?method=createCardList"
				 id="cardRecord" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 class="mt10 table" keepStatus="true">
					<display:column title="序号" style="text-align:center">
						${cardRecord_rowNum}
					</display:column>
					<display:column property="id" title="ID" style="text-align:center"/>
					<display:column property="cardStartInt" title="开始卡号" style="text-align:center"/>
					<display:column property="cardEndInt" title="结束卡号" style="text-align:center"/>
					<display:column property="cardSum" title="卡数量" style="text-align:center"/>
					<display:column property="cardUserdSum" title="已使用数量" style="text-align:center"/>
					<display:column property="createDateStr" title="生成日期" style="text-align:center" />
					<display:column property="description" title="描述" style="text-align:center" />
					<display:column title="操作" media="html" style="text-align:center">
						<a href="javascript:exportCards('${cardRecord.id }')" >导出卡号密码</a>
					</display:column>
				</display:table>

	</div>
		<!-- 内容（结束） -->
		
</div>


<!-- 生成学习卡 -->
<div class="toumingdu make02" style="display:none;">
	<form id="queryForm" name="queryForm" action="${ctx}/cardManage/SystemCard.do?method=doCreateCards" method="post" >
	<div class="lec_center">
		<div style="padding-top:50px;">
			<input type="hidden" name="cardTypeId" value="21">
			<%--
			<div class="lc_shengcheng">
				
				<span>卡号编码：</span>
				<input type="text" name="code" value="01" readonly/>
			</div>
			 --%>
			<div class="clear"></div>
			<div class="mt20 lc_shengcheng">
				<span>销售方式：</span>
				<select class="lc_list" name="sellStyle">
<!-- 					<option value="0">请选择</option>
 -->					<option value="1">网上销售</option>
					<option value="2">地面销售</option>
				</select>
			</div>
			<div class="clear"></div>
			<div class="mt20 lc_shengcheng">
				<span>学习卡数量：</span>
				<input type="text" id="num" name="num" onkeyup="this.value = this.value.replace(/[^\d]/g, '');"/>
			</div>
			<div class="clear"></div>
			<div class="mt20 lc_shengcheng">
				<span>描述：</span>
				<input type="hidden" name="status" value="0">
				<input type="text" name="description" />
			</div>
			<div class="clear"></div>
			<div class="mt20 lc_shengcheng">
				<span>成本价：</span>
				<input type="text" name="cost" />
			</div>
			<div class="clear"></div>
			<div class="cas_anniu" style="margin-top:40px;margin-left:170px;">
				<a href="#" class="fl queren" style="width:70px;">确认</a>
				<a href="#" class="fl quxiao" style="width:70px;margin-left:40px;">取消</a>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	</form>
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
	
	//生成学习卡
		$('.xuexike').click(function(){
			$('.make02').show();
		});
		$('.queren').click(function(){
			if($('#num').val() == null || $('#num').val() == ''){
				alert("请输入要生成的学习卡的数量!");
				$("#num").focus();
				return ;
			}else{
				if(Number($('#num').val()) <= 1){
					alert("学习卡的数量要大于1!");
					$("#num").focus();
					return ;
				}
			 	$('#queryForm').submit();
				$('.make02').hide(); 
			}
		});
		$('.quxiao').click(function(){
			$('.make02').hide();
		});


});

//导出卡记录
function exportCards(recordId){
	window.location.href = "${ctx}/cardManage/SystemCard.do?method=exportCards&recordId="+recordId;
}

/* function check(){
	var num = $("#num").val();
	if(num == ''){
		alert("请输入要生成的学习卡的数量!");
		$("#num").focus();
		return ;
	}
	/* var usefulDate = $("#usefulDate").val();
	if(usefulDate == ''){
		alert("请选择有效期!");
		$("#usefulDate").focus();
		return false;
	} 
	else return true;
} */

</script>
</body>
</html>