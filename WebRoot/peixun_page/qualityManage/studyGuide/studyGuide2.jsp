<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<%@include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
</head>

<%@include file="/peixun_page/top.jsp"%>

<body>
<!-- 查询条件 -->
<div class="center">
	<form id="fm" action="${ctx}/qualityManage/guide.do?pageNum=2&parentId=${parentId}&typeId=${typeId}" method="POST">
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl" style="margin-right:20px;">
			<span>学习地图一级：</span>
			<input type="text" name="name" value="${name}"/>
		</p>
		<div class="fl cas_anniu">
			<a href="javascript:$(fm).submit();" class="fl" style="width:70px;">查询</a>
		</div>
		<div class="clear"></div>
		<div class="fr cas_anniu">
			<a href="${ctx}/qualityManage/guide.do" class="fl" style="width:70px;">返回</a>
		</div>
		<div class="clear"></div>
	</div>
	</form>
</div>
<div class="clear"></div>
<div class="bjs"></div>

<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
	<!-- 内容 -->
	
		<display:table name="list" requestURI="${ctx}/qualityManage/guide.do" id ="p" class="mt10 table" excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}">

							
			<display:column title = "序号" style = "width:5%;" >${p_rowNum}</display:column>
			<display:column title = "学习地图一级" style = "width:75%;" >${p.name}</display:column>
			<display:column title = "操作" style ="width:20%;">
				<a href="###" onclick="goTwoLevel('${parentId}','${typeId}','${p.id}','${parentName}','${p.name}');">二级</a>
			</display:column>
		
		</display:table>
		
		<div class="clear"></div> 
	</div>
</div>

<script type="text/javascript">
function goTwoLevel(parentId,typeId,pId,parentName,pName) {
   var pAllName = "能力管理 > 学习地图 > " + parentName + " > "+ pName ;
   pAllName = encodeURI(pAllName) ;
   window.location.href = "${ctx}/qualityManage/guide.do?pageNum=3&parentId=" + parentId + "&typeId=" + typeId + "&qualityId=" + pId + "&parentName=" + pAllName ;
}
$(function(){
			
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "lc_left0" + menuindex;
		$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
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
	var win_w = parseInt($(window).width() / 1.5) + "px";
	var win_h = parseInt($(window).height() / 3) + "px";
	var add_portraits_cont = '' +
			'<div class="tiaojian center">' +

					'<p class="fl" style="margin-right:20px;min-height:40px;">' +
						'<span class="fl" style="width:8em;text-align:right;">人物类型：</span>' +
						'<div class="fl select">' +
							'<div class="tik_position">' +
								'<b class="ml5">请选择</b>' +
								'<p class="position01"><i class="bjt10"></i></p>' +
							'</div>' +
							'<ul class="fl list" style="display: none;">' +
								'<li>医师能力模型</li>' +
								'<li>护师能力模型</li>' +
								'<li>药师能力模型</li>' +
								'<li>医技能力模型</li>' +
								'<li>中医能力模型</li>' +
								'<li>公卫能力模型</li>' +
							'</ul>' +
						'</div>' +
					'</p>' +
					'<p class="fl" style="margin-right:20px;min-height:40px;">' +
						'<span class="fl" style="width:4em;text-align:right;">科室：</span>' +
						'<div class="fl select">' +
							'<div class="tik_position">' +
								'<b class="ml5">请选择</b>' +
								'<p class="position01"><i class="bjt10"></i></p>' +
							'</div>' +
							'<ul class="fl list" style="display:none;">' +
								'<li>心血管外科</li>' +
								'<li>呼吸内科</li>' +
								'<li>消化内科</li>' +
								'<li>肾内科</li>' +
							'</ul>' +
						'</div>' +
					'</p>' +
					'<p class="fl" style="min-height:40px;">' +
						'<span class="fl" style="width:8em;text-align:right;">人物画像：</span>' +
						'<div class="fl select">' +
						'<div class="tik_position">' +
						'<b class="ml5">请选择</b>' +
						'<p class="position01"><i class="bjt10"></i></p>' +
						'</div>' +
						'<ul class="fl list" style="display:none;">' +
						'<li>高级心血管外科主任</li>' +
						'<li>中级心血管外科主任</li>' +
						'<li>初级心血管外科主任</li>' +
						'<li>高级心血管外科主治</li>' +
						'<li>中级心血管外科主治</li>' +
						'<li>初级心血管外科主治</li>' +
						'</ul>' +
						'</div>' +
					'</p>' +

			'</div>';
	$(".add_btn,.edit_btn").click(function() {
		layer.open({
			type: 1,
			title: "添加能力模型",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, win_h], //宽高
			content: add_portraits_cont,
			closeBtn: 0,
			btn: ['保存', '取消'],
			yes: function (index, layero) {
				//编写保存功能
				layer.close(index);
			},
			btn2: function (index, layero) {
				layer.close(index);
			},
			success:function(index, layero){
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
			}
		});
	});
})
</script>
</body>
</html>
