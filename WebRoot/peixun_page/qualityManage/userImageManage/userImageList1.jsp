<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/commons/taglibs.jsp"%>
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
		<title>培训后台</title>
	</head>
<%@include file="/peixun_page/top.jsp"%>
<body>
<!-- 头部 -->
<!-- 查询条件 -->
<div class="center">
<form id="sfrm" action="${ctx}/quality/userImageManage.do" method="POST">
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl">
			<span>人物类型：</span>
			<input type="text" id="sname" name="sname" value="${sname}"/>
		</p>

		<p class="fl cas_anniu">
			<a href="javascript:$(sfrm).submit();" class="fl" style="width:70px;margin-left:10px;">查询</a>
		</p>
	</div>
</form>
</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
	<form id = "listfrm" name = "listfrm" method = "POST">
		<display:table name="list" requestURI="${ctx}/quality/userImageManage.do?method=list1" id ="p"  class="mt10 table" excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}">
			<display:caption>能力管理 > 人物画像</display:caption>
				<display:column title = "序号" style = "width:5%;" >${p_rowNum}</display:column>
				<display:column title ="人物类型" style = "width:75%;" >${p.type.name}</display:column>
				<display:column title = "操作" style ="width:20%;">
					
					<a href="${ctx}/quality/userImageManage.do?mode=list2&id=${p.type.id}" >人物画像</a>
				</display:column>
			
			</display:table>

		<div class="clear"></div>
	</form> 
	</div>
</div>


<script type="text/javascript">
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
		


});
	var win_w = parseInt($(window).width() / 2) + "px";
	var win_h = parseInt($(window).height() / 4) + "px";
	var add_portraits_cont = '' +
			'<div class="center">' +
				'<div class="tiaojian" style="min-height:40px;">' +
					'<p class="clear">' +
						'<span>人物画像类型：</span>' +
						'<input type="text" />' +
					'</p>' +
				'</div>' +
			'</div>';
	var edit_portraits_cont = '' +
			'<div class="center">' +
			'<div class="tiaojian" style="min-height:40px;">' +
			'<p class="clear">' +
			'<span>人物画像类型：</span>' +
			'<input type="text" value="" id="edit1"/>' +
			'</p>' +
			'</div>' +
			'</div>';
	$(".add_portraits_btn").click(function() {
		
		layer.open({
			type: 1,
			title: "添加人物画像类型",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, win_h], //宽高
			content: add_portraits_cont,
			closeBtn: 0,
			btn: ['保存', '取消'],
			yes: function (index, layero) {
				//缩写保存功能
			},
			btn2: function (index, layero) {
				layer.close(index);
			}
		});
	});
	$(".edit_portraits_btn").click(function() {
		layer.open({
			type: 1,
			title: "修改人物画像类型",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, win_h], //宽高
			content: edit_portraits_cont,
			closeBtn: 0,
			btn: ['保存', '取消'],
			yes: function (index, layero) {
				//缩写保存功能
			},
			btn2: function (index, layero) {
				layer.close(index);
			}
		});
	});
function updateUser(id,userTypeIDs){
	
	$('#edit1').val(userTypeIDs+"人物类型");
	
} 




</script>
</body>
</html>