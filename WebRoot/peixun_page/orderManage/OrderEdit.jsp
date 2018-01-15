<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/default090909.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/lay.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">

<link rel="stylesheet" href="${ctx}/js/Validform_v5.3/css/valid.css" type="text/css" media="all" />
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/Validform_v5.3.js"></script>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<title>培训后台</title>
</head>

<%@include file="/peixun_page/top.jsp"%>
<%@ page import="com.hys.auth.model.HysUsers" %>
<%@ page import="com.hys.exam.constants.Constants" %>
<%
   	//HysUsers user = (HysUsers)request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
	String name = "";
   	if(null != user){
		  name = user.getAccountName();
   	}
   	
%>
<body>

<div class="clear"></div>
<div class="center" style="">
	<div class="thi_shitineirong ca1_jichu" style="width:1040px;margin:0 auto;">
	<form class="fm1" id="fm1" name="fm1" action="${ctx}/orderManage/OrderManage.do?method=update" method="post">		
		<ul style="margin-top:30px;">
			<li style="margin-top:10px;">
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr"><i style="color:red;"></i>用户：</span></p>
					<input type="text" class="fl tki_bianji" value = "${order.userName}" style="border:none" readonly/>
					<input type="hidden" name="userId" name="userId" value="${order.userId}" class="form-control">
				</div>
				<div class="fl shitin_xinzeng01" style="margin-left:100px;">
					<p class="fl ml30"><span class="fr"><i style="color:red;">*</i>订单生成日期：</span></p>
					<input type="text" name="orderDate" id="orderDate"
				value="<fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd"/>";
				datatype="*" nullmsg="请选择订单生成时间！" onClick="WdatePicker()"  class="fl tki_bianji"/>
				</div>
				<div class="clear"></div>
			</li>
			<li style="margin-top:10px;">
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr"><i style="color:red;"></i>订单数量：</span></p>
					<input type="text" id="quantity"   name="quantity"  class="fl tki_bianji"
						onkeyup="this.value = this.value.replace(/[^\d\-]/g, ''); changeAmount(this.value);"
						maxlength="8" size="25" datatype="n" nullmsg="请输入订单数量！" errormsg="请输入数字!" value="${order.quantity}"/>
				</div>
				<div class="fl shitin_xinzeng01" style="margin-left:100px;">
					<p class="fl ml30"><span class="fr"><i style="color:red;"></i>单价：</span></p>
					<input type="text" id="price" datatype="*" nullmsg="请输入单价！" errormsg="请输入数字!"  class="fl tki_bianji"
						onkeyup="this.value = this.value.replace(/[^\d\.\-]/g, ''); changeAmount(this.value);"
					   name="price" maxlength="200" size="25" value="${order.price}"/>
				</div>
				<div class="clear"></div>
			</li>
			<li style="margin-top:10px;">
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr"><i style="color:red;"></i>总价：</span></p>
					<input type="text" id="amount"   name="amount" maxlength="200" size="25" value="${order.amount}"  class="fl tki_bianji" readonly/>
				</div>
				<div class="fl shitin_xinzeng01" style="margin-left:100px;">
					<p class="fl ml30"><span class="fr"><i style="color:red;"></i>付款时间：</span></p>
					<input type="text" name="payDate" id="payDate" value="${order.payDate}" 
						nullmsg="请选择付款时间！" onClick="WdatePicker()"  class="fl tki_bianji"/>
				</div>
				<div class="clear"></div>
			</li>
			<li style="margin-top:10px;">
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr"><i style="color:red;"></i>支付方式：</span></p>
					<input type="text" id="paymodeCode" class="fl tki_bianji"  name="paymodeCode" maxlength="25" size="25" value="${order.paymodeCode}"/>
				</div>
				<div class="fl shitin_xinzeng01" style="margin-left:100px;">
					<p class="fl ml30"><span class="fr"><i style="color:red;"></i>发票状态：</span></p>
					<div class="fl select" style="width:300px;height:30px" >
						<div class="tik_position" style="width:300px;height:30px;" >
							<b class="ml5">请选择</b>
							<p class="position01" style="width:30px;"><i class="bjt10" style="margin-left:12px;margin-top:12px;"></i></p>
						</div>
						<ul class="fl list" style="display:none;width:300px;">
							<select name="invoiceStatus" datatype="*" nullmsg="请选择发票状态！" style="display:none;">
								<option value="" <c:if test="${order.invoiceStatus eq ''}">selected</c:if> >请选择</option>
								<option value="0" <c:if test="${order.invoiceStatus == 0}">selected</c:if> >不需要发票</option>
								<option value="1" <c:if test="${order.invoiceStatus == 1}">selected</c:if> >需要发票</option>
								<option value="2" <c:if test="${order.invoiceStatus == 2}">selected</c:if> >已开发票</option>
							</select>
							<li>请选择</li>
							<li>需要发票</li>
							<li>不需要发票</li>
							<li>已开发票</li>
						</ul>
					</div>
				</div>
				<div class="clear"></div>
			</li>
			<li style="margin-top:10px;">
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr"><i style="color:red;"></i>发票抬头：</span></p>
					<input type="text" id="invoiceTitle"   name="invoiceTitle" class="fl tki_bianji" maxlength="25" size="25" value="${order.invoiceTitle}"/>
				</div>
				<div class="fl shitin_xinzeng01" style="margin-left:100px;">
					<p class="fl ml30"><span class="fr"><i style="color:red;"></i>收件人：</span></p>
					<input type="text" id="recipient"   name="recipient" class="fl tki_bianji" maxlength="25" size="25" value="${order.recipient}"/>
				</div>
				<div class="clear"></div>
			</li>
			<li style="margin-top:10px;">
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr"><i style="color:red;"></i>收件人联系电话：</span></p>
					<input type="text" id="tel"   name="tel" class="fl tki_bianji" maxlength="25" size="25" value="${order.tel}"/>
				</div>
				<div class="fl shitin_xinzeng01" style="margin-left:100px;">
					<p class="fl ml30"><span class="fr"><i style="color:red;"></i>收件人联系地址：</span></p>
					<input type="text" id="address"   name="address" class="fl tki_bianji" maxlength="25" size="25" value="${order.address}"/>
				</div>
				<div class="clear"></div>
			</li>
			<li style="margin-top:10px;">
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr"><i style="color:red;"></i>订单处理人：</span></p>
					<input type="text" id="orderOper"   name="orderOper" class="fl tki_bianji" maxlength="25" size="25" value="${order.orderOper}"/>
				</div>
				<div class="fl shitin_xinzeng01" style="margin-left:100px;">
					<p class="fl ml30"><span class="fr"><i style="color:red;"></i>订单处理时间：</span></p>
				<input type="text" name="payDate" id="payDate" value="${order.payDate}" 
				nullmsg="请选择付款时间！" onClick="WdatePicker()"  class="fl tki_bianji" />
				</div>
				<div class="clear"></div>
			</li>
			<li style="margin-top:5px;">
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">备注：</span></p>
					<textarea id="remark"   name="remark" cols="" rows="" class="fl" style="width:865px;height:80px;border:1px solid #dddddd;" >${order.remark}</textarea>
				</div>
				<div class="clear"></div>
			</li>
			<li style="margin-top:10px;">
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr"><i style="color:red;"></i>邮编：</span></p>
					<input type="text" id="postCode" datatype="p" errormsg="请输入正确邮编!"  class="fl tki_bianji"  name="postCode" maxlength="25" size="25" value="${order.postCode}"/>
				</div>
				<div class="fl shitin_xinzeng01" style="margin-left:100px;">
					<p class="fl ml30"><span class="fr"><i style="color:red;"></i>订单号：</span></p>
					<input type="text" class="fl tki_bianji" id="orderNumber" name = "orederNumber" value="${order.orderNumber}" style="border:none" readonly/>
				</div>
				<div class="clear"></div>
			</li>
			<li style="margin-top:10px;">
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr"><i style="color:red;"></i>订单状态：</span></p>
					<div class="fl select" style="width:300px;height:30px" >
						<div class="tik_position" style="width:300px;height:30px;" >
							<b class="ml5">请选择</b>
							<p class="position01" style="width:30px;"><i class="bjt10" style="margin-left:12px;margin-top:12px;"></i></p>
						</div>
						<ul class="fl list" style="display:none;width:300px;">
							<select name="status" datatype="*" nullmsg="请选择状态！" style="display:none;">
								<option value="" <c:if test="${order.status eq '' }">selected</c:if> >请选择</option>
								<option value="1" <c:if test="${order.status == 1 }">selected</c:if> >正常</option>
								<option value="-1" <c:if test="${order.status ==-1 }">selected</c:if> >删除</option>
								<option value="2" <c:if test="${order.status ==2 }">selected</c:if> >未付款</option>
								<option value="3" <c:if test="${order.status ==3 }">selected</c:if> >已付款</option>
								<option value="4" <c:if test="${order.status ==4 }">selected</c:if> >订单完成</option>
								<option value="5" <c:if test="${order.status ==5 }">selected</c:if> >退款</option>
							</select>
							<li>请选择</li>
							<li>正常</li>
							<li>删除</li>
							<li>未付款</li>
							<li>已付款</li>
							<li>订单完成</li>
							<li>退款</li>
						</ul>
					</div>
				</div>
				<div class="clear"></div>
			</li>
			<li style="margin-top:10px;">
				<div class="fl shitin_xinzeng01" style="">
					<p class="fl"><span class="fr"><i style="color:red;"></i>是否绑定学习卡：</span></p>
					<div style="font-size:12px;color:#333;width:410px;line-height:30px;float:left;">
					<c:if test="${empty cardList }">
						此订单暂时没绑定学习卡。 目前可以绑定的数量是${noBindCount }张。
					</c:if>
					<c:if test="${not empty cardList }">
						<c:forEach items="${cardList }" varStatus="stauts" var="card">
							学习卡：${card.cardNumber }，密码：${card.cardPassword }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</c:forEach>
					</c:if>
					</div>
						<%
							if(name.equals("admin")){
						%>
						<a href="javascript:createCard('${order.id}');" class="fl did_liji">立即绑定</a>
						<%} %>
				</div>
				
				<div class="clear"></div>
			</li>
			<li style="margin-top:15px;">
				<div class="cas_anniu" style="width:170px;margin:40px auto;">
				<%
					if(name.equals("admin")){
				%>
		    		<a href="javascript:save();" class="fl" style="width:70px;">保存</a>
				<%} %>
					<a href="${ctx}/orderManage/OrderManage.do?method=list" class="fl" style="width:70px;margin-left:20px;">返回</a>
				</div>
				<div class="clear"></div>
			</li>
		</ul>
		<input type="hidden" id="id" name="id" value="${order.id}"/>
		<input type="hidden" name="org.apache.struts.taglib.html.TOKEN"  value="${sessionScope['org.apache.struts.action.TOKEN']}" />
		</form>
		<div class="clear"></div>
	</div>
</div>
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
function changeAmount(value){
		var quantity = $("#quantity").val();
		var price = $("#price").val();
		if(quantity !='' && price != ''){
			var amount = quantity*price;
			$("#amount").val(amount);
		}
}
function save(){
	document.getElementById("fm1").submit();
}
  $(".fm1").Validform({
      	datatype:{//自定义datatype类型 浮点类型
	    "float":function(gets,obj,curform,datatype){
				var reg = new RegExp("^\\d+(\\.\\d+)?$");
				if (!reg.exec(gets)){
				  return false;
				}
				return true;
			}
      	}
    });
    
    function createCard(orderId){
    	var status = '${order.status}';
    	//if(status <3){
    	//	alert("此订单尚未支付，不能绑卡~");
    	//	return;
    	//}
    	var quantity = '${order.quantity}';
    	var noBindCount = '${noBindCount}';
    	var cardTypeId = $("#cardTypeId").val();
    	if(quantity<=0){
    		alert("订单数量至少为1个，请先修改订单数量，保存后再来生成学习卡~");
    		return;
    	}
    	if(quantity>noBindCount){
    		alert("订单数量大于剩余空白卡数量,请先进行绑定或者制卡~");
    		return ;
    	}
    	
    	var p = {'orderId':orderId,'cardTypeId':cardTypeId,'quantity':quantity};
		$.post("${ctx }/system/SystemCardOrder.do?method=createCard", p,
  			function(data){
  				if(data >0){
  					alert("生成学习卡成功!");
  				}
  				else{
  					alert("由于网络原因,学习卡生成不成功,请稍候再试!");
  				}
  				window.location.reload(true);
  		});
    }
</script>
</body>

