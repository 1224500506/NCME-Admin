<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<%@include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
</head>

<%@include file="/peixun_page/top.jsp"%>

<body>
<div class="clear"></div>
<form name="queryForm" id = "queryForm" action="${ctx}/orderManage/OrderManage.do?method=list" method="post">
<!-- 查询条件 -->
<div class="center">
	
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl">
			<span>订单号：</span>
			<input  type = "text" name="orderNumber" id="orderNumber" value = "${serchInfo.orderNumber}"/>
		</p>
		
		<p class="fl" style="margin-left:60px;">
			<span>类型：</span>
			
			<div class="fl select" >
				<div class="tik_position">
					<b class="ml5">请选择</b>
					<p class="position01"><i class="bjt10"></i></p>
				</div>
				 <ul class="fl list" style="display:none;">
				 	<select name="cardTypeId" id="cardTypeId" style="display:none;">
					<option value = "-1">请选择</option>
					<c:forEach items="${cardTypeList}" var="cardType">
						<option value="${cardType.id}"<c:if test="${cardType.id==serchInfo.cardTypeId}"> selected</c:if>>${cardType.cardTypeName}</option>
					</c:forEach>
					</select>
					<li>请选择</li>
					<c:forEach items="${cardTypeList}" var="cardType">
						<li>${cardType.cardTypeName}</li>
					</c:forEach>
				</ul>
			</div> 
			<!-- 
			<input name="cardTypeName" id="cardTypeName" value = "${serchInfo.cardTypeName}"/>
			 -->
		<p class="fl" style="margin-left:60px;">
			<span>订单日期：</span>
			<input type="text" name="orderDateStart" value="${serchInfo.orderDateStart}" onClick="WdatePicker()" style = "width:185px;"/>
			<span style="padding:0px 7px 0px 7px;">至</span>
			<input type="text" name="orderDateEnd" value="${serchInfo.orderDateEnd}" onClick="WdatePicker()" style = "width:185px;"/>
		</p>
	</div>
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl">
			<span>用户名：</span>
			<input name="accountName" id="accountName" class="form-control" value="${serchInfo.accountName}"/>
		</p>
		<p class="fl" style="margin-left:60px;">
			<span>姓名：</span>
			<input name="realName" id="realName" class="form-control" value="${serchInfo.userName}"/>
		</p>
		<div class="fl cas_anniu">
			<a href="javascript:query();" class="fl" style="width:70px;margin-left:130px;">查询</a>
		</div>
	</div>
</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
		<display:table requestURI="${ctx}/orderManage/OrderManage.do?method=list"
				 id="systemCardOrder" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;" class="mt10 table" keepStatus="true">
					<display:caption media="html"><span style="color:red">${meg}</span></display:caption>
					<display:column title='<input type="checkbox" id="orderIds" onclick="select_all(this)" />全选' style="text-align:center;width:5%;" media="html">
					<input type="checkbox" name="orderIds" value="${systemCardOrder.id }"/>
					</display:column>
					<display:column title="序号" style="text-align:center;width:5%;">
						${systemCardOrder_rowNum}
					</display:column>
					<display:column property="id" title="ID" style="text-align:center;width:5%"/>
					<display:column property="orderNumber" title="流水号" style="text-align:center;width:10%"/>
					<display:column property="cardTypeName" title="类型" style="text-align:center;width:10%"/>
					<display:column property="userName" title="学员名称" style="text-align:center;width:10%"/>
					<display:column property="orderDate" title="订单日期" style="text-align:center;width:10%"/>
					<display:column property="amount" title="总价格" style="text-align:center;width:10%"/>
					<display:column property="payDate" title="付款日期" style="text-align:center;width:10%"/>
					<display:column style="text-align:center; width:10%" title="订单状态">
						<c:if test="${systemCardOrder.status == 1 }">正常</c:if>
						<c:if test="${systemCardOrder.status == -1 }">删除</c:if>
						<c:if test="${systemCardOrder.status == 2}">未付款</c:if>
						<c:if test="${systemCardOrder.status == 3}">已付款</c:if>
						<c:if test="${systemCardOrder.status == 4}">订单完成</c:if>
						<c:if test="${systemCardOrder.status == 5}">退款</c:if>
					</display:column>
					<display:column title="操作" media="html" style="text-align:center;width:20%">
						<a href="javascript:updateCardOrder('${systemCardOrder.id }')" >修改订单</a>&nbsp;
						<c:if test="${systemCardOrder.status == 3 || systemCardOrder.status == 4 }">
							<a href="javascript:viewCardByOrder('${systemCardOrder.id }')" >查看卡信息</a>
						</c:if>
					</display:column>
			</display:table>
		<div class="clear"></div> 
	</div>
</div>
</form>

<!-- 调整 -->
<div class="toumingdu make02" style="display:none;">
	<div class="lec_center" style="height:300px;">
		<div style="padding-top:50px;">
			<div class="lc_shengcheng">
				<span>当前目录：</span>
				<input type="text" placeholder=" 中医"/>
			</div>
			<div class="clear"></div>
			<div class="mt30 lc_shengcheng">
				<span>调整目录：</span>
				<div class="duouan" style="width:260px;height:30px;">
			
				</div>
			</div>
			<div class="clear"></div>
			<div class="cas_anniu" style="margin-top:50px;margin-left:170px;">
				<a href="#" class="fl queren" style="width:70px;">确认</a>
				<a href="#" class="fl queren" style="width:70px;margin-left:40px;">取消</a>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>
<!-- 选择目录弹出框-一级 -->
<div class="toumingdu att_make01" style="display:none;">
	<div class="tk_center09" style="margin:7% auto;">
		<div class="tik_biaoti">
			<span class="fl" style="margin-left:290px;color:#fff;">选择目录</span>
			<i class="fr bjt_kt"></i>
		</div>
		<div class="clear"></div>
		<div class="xianshikuang">
			<div class="mt15 xs_xuangze">
				<div class="fl xs_kuang">
				
				</div>
				<div class="fr cas_anniu_4" style="margin-top:25px;">
					<a href="#" class="fr">确定</a>
				</div>
				<div class="clear"></div>
			</div>
			<div class="xs_er">
				<p class="fl">选择目录</p>
				<i class="fl xs_bjt01"></i>
			</div>
			<div class="clear"></div>
			<ul class="xs_ul">
				<li>
					<div class="attr_xueke">
						<div class="">
							<input type="checkbox" class="fl" style="margin-top:5px;"/> 
							<p class="fl attr_xueke"><em class="fl">医院题库</em><i class="fl ml10 kti_bjt2"></i></p>
						</div>
					</div>
					<div class="clear"></div>
				</li>
			</ul>
			<div class="clear"></div>
		
		</div>
	</div>
</div>
<!--选择目录弹出框-二级 -->
<div class="toumingdu att_make02" style="display:none;">
	<div class="tk_center09" style="margin:7% auto;">
		<div class="tik_biaoti" style="height:35px;line-height:40px;">
			<span class="fl" style="margin-left:290px;">选择目录</span>
			<i class="fr bjt_kt"></i>
		</div>
		<div class="clear"></div>
		<div class="xianshikuang">
			<div class="mt15 xs_xuangze">
				<div class="fl xs_kuang"></div>
				<div class="fr cas_anniu_4" style="margin-top:25px;">
					<a href="#" class="fr">确定</a>
				</div>
				<div class="clear"></div>
			</div>
			<div class="xs_er attri01">
				<p class="fl">医院题库</p>
				<i class="fl xs_bjt01"></i>
			</div>
			<div class="clear"></div>
			<ul class="xs_ul">
				<li>
					<div class="attr_xueke02">
						<input type="checkbox" class="fl" style="margin-top:5px;"/> 
						<p class="fl"><em class="fl">内科护理学高级教程</em><i class="fl ml10 kti_bjt2"></i></p>
					</div>
					<div class="clear"></div>
				</li>
				<li>
					<div class="attr_xueke02">
						<input type="checkbox" class="fl" style="margin-top:5px;"/> 
						<p class="fl"><em class="fl">2016年北京定考</em><i class="fl ml10 kti_bjt2"></i></p>
					</div>
					<div class="clear"></div>
				</li>
				<li>
					<div class="attr_xueke02">
						<input type="checkbox" class="fl" style="margin-top:5px;"/> 
						<p class="fl"><em class="fl">教师继续中心定期考核</em><i class="fl ml10 kti_bjt2"></i></p>
					</div>
					<div class="clear"></div>
				</li>
				
			</ul>
			<div class="clear"></div>
		
		</div>
	</div>
</div>
<!-- 选择目录弹出框-三级 -->
<div class="toumingdu att_make03" style="display:none;">
	<div class="tk_center09" style="margin:7% auto;">
		<div class="tik_biaoti" style="height:35px;line-height:40px;">
			<span class="fl" style="margin-left:290px;">选择目录</span>
			<i class="fr bjt_kt"></i>
		</div>
		<div class="clear"></div>
		<div class="xianshikuang">
			<div class="mt15 xs_xuangze">
				<div class="fl xs_kuang"></div>
				<div class="fr cas_anniu_4" style="margin-top:25px;">
					<a href="#" class="fr">确定</a>
				</div>
				<div class="clear"></div>
			</div>
			<div class="xs_biaoti">
				<div class="fl xs_er attri01">
					<p class="fl">医院题库</p>
					<em class="fl"></em>
				</div>
				<div class="fl xs_er attri02">
					<p class="fl">内科护理学高级教程</p>
					<i class="fl xs_bjt01"></i>
				</div>
			</div>
			
			<div class="clear"></div>
			<ul class="xs_ul">
				<li>
					<div class="">
						<input type="checkbox" class="fl" style="margin-top:5px;"/> 
						<p class="fl attr_xueke03"><em class="fl">临床</em><i class="fl ml10 kti_bjt2"></i></p>
					</div>
					<div class="clear"></div>
				</li>
				<li>
					<div class="">
						<input type="checkbox" class="fl" style="margin-top:5px;"/> 
						<p class="fl attr_xueke03"><em class="fl">口腔</em><i class="fl ml10 kti_bjt2"></i></p>
					</div>
					<div class="clear"></div>
				</li>
				<li>
					<div class="">
						<input type="checkbox" class="fl" style="margin-top:5px;"/> 
						<p class="fl attr_xueke03"><em class="fl">中医</em><i class="fl ml10 kti_bjt2"></i></p>
					</div>
					<div class="clear"></div>
				</li>
				<li>
					<div class="">
						<input type="checkbox" class="fl" style="margin-top:5px;"/> 
						<p class="fl attr_xueke03"><em class="fl">公共卫生</em><i class="fl ml10 kti_bjt2"></i></p>
					</div>
					<div class="clear"></div>
				</li>
			</ul>
			<div class="clear"></div>
		
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
	$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
	
				$('.select').click(function(){
					 $('.list').css('display","none');
					$(this).find('ul').show();
				});
				$('.list li').click(function(){
						var str=$(this).text();
						$(this).parent().parent().find('div').find('b').text(str);
						$(this).parent().find('option').prop('selected', '');
						var op = $(this).parent().find('option').eq($(this).index()-1);
						$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
						$('.list').slideUp(50);
				});
				$('.list option:selected').each(function(){
					var str=$(this).text();
					$(this).parent().parent().parent().find('b').text(str);
				});
		
				$('.select').click(function(e){
					return false;
				});
				$(document).click(function(){
					$('.list').hide('fast');
				});
//选择目录弹出框
		$('.duouan').click(function(){
			$('.att_make01').show();
		});
		$('.bjt_kt,.cas_anniu_4').click(function(){
			$('.att_make01').hide();
			$('.att_make02').hide();
			$('.att_make03').hide();
			$('.att_make04').hide();
		});
		//二级
		$('.attr_xueke').click(function(){
			$('.att_make01').hide();
			$('.att_make02').show();
		});
		$('.attri01').click(function(){
			$('.att_make02').hide();
			$('.att_make01').show();
			$('.att_make03').hide();
			$('.att_make04').hide();
			$('.att_make05').hide();
		});
		//三级
		$('.attr_xueke02').click(function(){
			$('.att_make02').hide();
			$('.att_make03').show();
		});
		$('.attri02').click(function(){
			$('.att_make02').show();
			$('.att_make03').hide();
			$('.att_make04').hide();
			$('.att_make05').hide();
		});
		//调整
		$('.tiaozheng').click(function(){
			$('.make02').show();
		});
		$('.queren').click(function(){
			$('.make02').hide();
		});
});
//[查询] 
function query() {
		document.getElementById("queryForm").submit();
}
function viewCardByOrder(orderId){
	var url = "${ctx}/orderManage/OrderManage.do?method=getCardList&orderId="+orderId;
	document.getElementById("queryForm").action = url;
	document.getElementById("queryForm").submit();
}
//修改
function updateCardOrder(id){
	window.location.href="${ctx}/orderManage/OrderManage.do?method=edit&id="+id;
}

</script>
</body>

