<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<title>培训后台</title>
<%@include file="/commons/taglibs.jsp"%>
</head>

<%@include file="/peixun_page/top.jsp"%>
<body>

<div class="clear"></div>
<div class="center" style="">
	<div class="center01">
		 <div class="mt5 kit1_tiaojia">
			<div class="fr cas_anniu">
				<a href="${ctx}/orderManage/OrderManage.do?method=list" class="fl" style="width:80px;">返回</a>
			</div>
		</div>
		<div class="clear"></div>
		<display:table requestURI="${ctx}/orderManage/OrderManage.do?method=getCardList"
			 id="systemOrderCard" cellpadding="0" cellspacing="0" partialList="true" 
			 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
			 style="font-size:12px;width:95%;" class="mt10 table" keepStatus="true">
				<display:caption><span style="color:red">${meg}</span></display:caption>
				
				<display:column title="序号" style="text-align:center">
					${systemOrderCard_rowNum}
				</display:column>
				<display:column property="id" title="ID" style="text-align:center"/>
				<display:column property="cardNumber" title="卡号" style="text-align:center" />
				<display:column property="cardPassword" title="卡密码" style="text-align:center" />
				<display:column property="importDate" title="导入时间" style="text-align:center" />
				<display:column property="usefulDate" title="有效期" style="text-align:center"/>
				<display:column style="text-align:center; width:10%" title="卡状态">
					<c:if test="${systemOrderCard.status == 1 }">有效</c:if>
					<c:if test="${systemOrderCard.status == 0 }">待生效</c:if>
					<c:if test="${systemOrderCard.status == -2}">禁用</c:if>
					<c:if test="${systemOrderCard.status == -1}">无效/删除</c:if>
				</display:column>
				
			</display:table>
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
					$('.list').slideUp(50);
				});
				$('.select').click(function(e){
					return false
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
		alert($('#cardType').val());
		document.getElementById("queryForm").submit();
}

</script>
</body>

