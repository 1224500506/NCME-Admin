<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/commons/taglibs.jsp"%>
		<title>培训后台</title>
		<script type="text/javascript" src="${ctx}/js/xuekePopup.js"></script>
	</head>
<%@include file="/peixun_page/top.jsp"%>

<body>
<!-- 查询条件 -->
<div class="center">	
	<div class="tiaojian" style="min-height:40px;">
	<form id = "level1_form" action="${ctx}/qualityManage/qualityManage.do" method="post">
		<p class="fl" style="margin-right:20px;">
			<span>能力模型类型：</span>
			<input type="text" name="sname" id="level0_search" value="${sname}"/>
		</p>
		<div class="fl cas_anniu">
			<a href="javascript:$(level1_form).submit();" class="fl" style="width:70px;">查询</a>
		</div>
		
		<div class="clear" style="margin-top:20px;"></div>		
		<!-- <div class="mt10 kit1_tiaojia">
				<div class="fr cas_anniu">
					<a href="#" class="fl add_btn" style="width:140px;margin-right:15px;">添加能力模型类型</a> 					
				</div>
		</div>  -->
		<div class="clear"></div>
	</form>
	</div>
</div> 

<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
		<display:table name="QM_list" requestURI="${ctx}/qualityManage/qualityManage.do" id="p" class="mt10 table" excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}">
		<display:caption>能力管理 > 能力模型</display:caption>
				<display:column title="序号" style="width:10%;">${p_rowNum}</display:column>
				<display:column title="能力模型类型"  style="width:70%;">${p.type.name}能力模型</display:column>				
				<display:column title="操作" style="width:20%;">
					<%-- <a href="javascript:update('${p.type.id}','${p.type.name}');" class="edit_btn">修改</a> 
					<a href="javascript:doDelProp(${p.type.id});">删除</a> --%>					
					<a href="###" onclick="goOneLevel('${p.type.id}','${p.type.name}');">一级</a>				 
				</display:column>
			</display:table>
		<div class="clear"></div> 		
	</div>
</div>

<script type="text/javascript">	
function goOneLevel(pId,pName) {
   var pAllName = "能力管理 > 能力模型> " + pName + "能力模型" ;
   pAllName = encodeURI(pAllName) ;
   window.location.href = "${ctx}/qualityManage/qualityManage.do?typeID=" + pId + "&pageNum=2&captionName=" + pAllName ;
}
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
	
	var win_w = parseInt($(window).width() / 2) + "px";
	var win_h = parseInt($(window).height() / 4) + "px";
	var add_cont = '' +
			'<div class="center">' +
			'<div class="tiaojian clear" style="min-height:40px;">' +
			'<p class="clear" style="margin-bottom:10px;">' +
			'<span class="fl" style="width:8em;text-align:right;display:inline-block;">能力模型类型：</span>' +
			'<input type="text" />' +
			'</p>' +
			'</div>' +
			'</div>';
	var edit_cont = '' +
			'<div class="center">' +
			'<div class="tiaojian clear" style="min-height:40px;">' +
			'<p class="clear" style="margin-bottom:10px;">' +
			'<span class="fl" style="width:8em;text-align:right;display:inline-block;">能力模型类型：</span>' +
			'<input type="text" value="" id="edit1" />' +
			'</p>' +
			'</div>' +
			'</div>';

	$(".add_btn").click(function() {
		layer.open({
			type: 1,
			title: "添加能力模型类型",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, win_h], //宽高
			content: add_cont,
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

			}
		});
	});
	$(".edit_btn").click(function(name_) {
		layer.open({
			type: 1,
			title: "修改能力模型类型",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, win_h], //宽高
			content: edit_cont,
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

			}
		});
	});

});

function update(id_, name_){
	$('#edit').val(name_+'能力模型');
}




</script>
</body>
</html>