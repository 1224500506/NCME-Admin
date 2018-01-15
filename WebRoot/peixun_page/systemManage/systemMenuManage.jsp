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
<!-- 查询条件 -->
<div class="center">
	<form id = "fm1" name = "fm1" method = "post" action = "${ctx}/systemManage/getMenu.do?method=search">
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl" style="margin:0 20px 10px 0">
			<span style="width:5em;text-align:right;">资源名称：</span>
			<input type="text" name = "menuName" id = "menuName" value = "${menuName}"/>
		</p>
		<p class="fl" style="margin:0 0 10px 0">
			<span style="width:5em;text-align:right;">系统名称：</span>
		</p>
		<div class="fl select"  style="margin:0 20px 0 0">
			<div class="tik_position">
				<b class="ml5" id="systemName"><c:if test = "${system_type == null}">全部</c:if><c:if test = "${system_type != null}">${system_type}</c:if></b>
				<input type = "hidden" id = "system_type" name = "system_type" value = "${system_type}"/>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display: none;">
				<li>全部</li>
				<li>资源管理系统</li>
				<li>后台管理系统</li>
			</ul>
		</div>
		<p class="fl" style="margin:0 20px 10px 0">
			<span style="width:5em;text-align:right;">模块名称：</span>
			<input type="text" name = "menu_type" id = "menu_type" value = "${menu_type}"/>
		</p>
		<p class="fl" style="margin:0 0 10px 0">
			<span style="width:5em;text-align:right;">状态：</span>
		</p>
		<div class="fl select"  style="margin:0 20px 0 0">
			<div class="tik_position">
				<b class="ml5">
				<c:if test = "${state == null}">全部</c:if>
				<c:if test = "${state == 1}">正常</c:if>
				<c:if test = "${state == 2}">停用</c:if>
				</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display: none;">
				<select id="state" name="state" style="display:none;">
					<option value ="">全部</option>
					<option value="1"<c:if test="${state==1}"> selected</c:if>>正常</option>
					<option value="2"<c:if test="${state==2}"> selected</c:if>>停用</option>
				</select>
				<li>全部</li>
				<li>正常</li>
				<li>停用</li>
			</ul>
		</div>
		<div class="clear"></div>
		<p class="fl cas_anniu">
			<a href="javascript:search();" style="width:70px;margin:0 0 20px 20px;">查询</a>
		</p>
		<p class="fr cas_anniu">
			<a href="javascript:void(0);" class="add_btn" style="width:140px;margin:0 0 20px 20px;">添加菜单</a>
		</p>
	</div>
	</form>
</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
		<display:table id="systemMenu" cellpadding="0" cellspacing="0" partialList="true"  requestURI="${ctx}/systemManage/getMenu.do"
 					size="${totalCount}" pagesize="20" list="${page}" style="font-size:12px;width:100%;" class="mt10 table" 
					  decorator="com.hys.exam.displaytag.TableOverOutWrapper" >
			<display:column title="序号" style="width:5%">
				${systemMenu_rowNum}
			</display:column>
			<display:column title="系统名称">
				${systemMenu.system_type}
			</display:column>
			<display:column title="模块" property="menu_type" style="text-align:center">
			</display:column>
			<display:column title="资源名称" property="name" style="text-align:center" /> 	
			<display:column title="资源地址" property="url" style="text-align:center">
			</display:column>
			<display:column title="状态" style="text-align:center">
				<c:if test="${systemMenu.state == 1}">正常</c:if>
				<c:if test="${systemMenu.state == 2}">停用</c:if>
			</display:column>
			<display:column title="操作" style="text-align:center">
			 		<a href="javascript:updateMenu(${systemMenu.id},'${systemMenu.name}','${systemMenu.system_type}','${systemMenu.menu_type}','${systemMenu.url}',${systemMenu.state});" class="edit_btn">修改</a>
					<a href="javascript:updateState(${systemMenu.id},${systemMenu.state});" class="">
					<c:if test="${systemMenu.state == 1}">停用</c:if>
					<c:if test="${systemMenu.state == 2}">正常</c:if>
					</a>
			</display:column>
		</display:table>
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
function search()
{
	$("#system_type").val($("#systemName").text());
	$('#fm1').submit();
}
function updateMenu(id,name,system_type,menu_type,url,state)
{
	$("#edit_id").val(id);
	$("#editSystem_type").text(system_type);
	$("#editmenuType").text(menu_type);
	$("#editMenu_name").val(name);
	$("#editMenu_url").val(url);
	$("#state").val(state);
}
function updateState(id,oldState)
{
	var url = "${ctx}/systemManage/getMenu.do?method=updateState";
	var param = "";
	param += "&id=" + id;
	param += "&oldState=" + oldState;
	$.ajax({
	    url:url,
	    type: 'POST',
	    dataType: 'text',
	    data : param,
	    success: function(result){
		   if(result == 'success'){
		   		alert("操作成功！");			   		   		
		   }
		   else
		   {
		   		alert("操作失败!");
		   }
		   document.location.href = "${ctx}/systemManage/getMenu.do?method=getNewMenus";
		   //document.location.href = document.location.href.split("#")[0];
    	}
	});		
}
	var win_w = "600px";
	var win_h = "400px";
	
	var adminMenuLi = "" ;
	<c:forEach items="${adminMenuList}" var = "adminMenu">
		adminMenuLi += '<li>${adminMenu.menu_type}</li>' ;
	</c:forEach>
	
	var peixunMenuLi = "" ;
	<c:forEach items="${peixunMenuList}" var = "peixunMenu">
		peixunMenuLi += '<li>${peixunMenu.menu_type}</li>' ;
	</c:forEach>
	
	var add_cont = '' +
			'<div class="center">' +
			'<div class="tiaojian" style="min-height:40px;">' +
			'<p class="fl">' +
			'<span style="width:5em;text-align:right;">系统名称：</span>' +
			'</p>' +
			'<div class="fl select" style="margin:0 20px 20px 0">' +
			'<div class="tik_position">' +
			'<b class="ml5" id = "newSystem_type">全部</b>' +
			'<p class="position01"><i class="bjt10"></i></p>' +
			'</div>' +
			'<ul class="fl list" id = "systemType" style="display: none;">' +
			'<li>资源管理系统</li>' +
			'<li>后台管理系统</li>' +
			'</ul>' +
			'</div>' +
			'<div class="clear"></div> ' +
			'<p class="fl">' +
			'<span style="width:5em;text-align:right;">模块名称：</span>' +
			'</p>' +
			'<div class="fl select" id = "menuType" style="margin:0 20px 20px 0">' +
			'<div class="tik_position">' +
			'<b class="ml5" id = "newMenu_type">全部</b>' +
			'<p class="position01"><i class="bjt10"></i></p>' +
			'</div>' +
			'<ul class="fl list" id = "type1" style="display: none;">' +
			adminMenuLi + 
			'</ul>' +
			'<ul class="fl list" id = "type2" style="display: none;">' +
			peixunMenuLi + 
			'</ul>' +
			'</div>' +
			'<div class="clear"></div> ' +
			'<p class="fl" style="margin:0 0 20px 0;">' +
			'<span style="width:5em;text-align:right;">资源名称：</span> ' +
			'<input type="text" id = "newMenu_name" name = "newMenu_name" value="" />' +
			'</p>' +
			'<div class="clear"></div> ' +
			'<p class="fl" style="margin:0 0 20px 0;">' +
			'<span style="width:5em;text-align:right;">资源地址：</span> ' +
			'<input type="text" value="" id = "newMenu_url" style="width:400px" />' +
			'</p>' +
			'</div>' +
			'</div>';
	var edit_cont = '' +
			'<div class="center">' +
			'<div class="tiaojian" style="min-height:40px;">' +
			'<p class="fl">' +
			'<span style="width:5em;text-align:right;">系统名称：</span>' +
			'</p>' +
			'<div class="fl select" style="margin:0 20px 20px 0">' +
			'<div class="tik_position">' +
			'<b class="ml5" id = "editSystem_type">全部</b>' +
			'<p class="position01"><i class="bjt10"></i></p>' +
			'</div>' +
			'<ul class="fl list" id = "systemType" style="display: none;">' +
			'<li>资源管理系统</li>' +
			'<li>后台管理系统</li>' +
			'</ul>' +
			'</div>' +
			'<div class="clear"></div> ' +
			'<p class="fl">' +
			'<span style="width:5em;text-align:right;">模块名称：</span>' +
			'</p>' +
			'<div class="fl select" id = "menuType" style="margin:0 20px 20px 0">' +
			'<div class="tik_position">' +
			'<b class="ml5" id = "editmenuType">全部</b>' +
			'<p class="position01"><i class="bjt10"></i></p>' +
			'</div>' +
			'<ul class="fl list" id = "type1" style="display: none;">' +
			adminMenuLi + 
			'</ul>' +
			'<ul class="fl list" id = "type2" style="display: none;">' +
			peixunMenuLi + 
			'</ul>' +
			'</div>' +
			'<div class="clear"></div> ' +
			'<p class="fl" style="margin:0 0 20px 0;">' +
			'<span style="width:5em;text-align:right;">资源名称：</span> ' +
			'<input type="text" id = "editMenu_name" name = "editMenu_name" value="" />' +
			'</p>' +
			'<div class="clear"></div> ' +
			'<p class="fl" style="margin:0 0 20px 0;">' +
			'<span style="width:5em;text-align:right;">资源地址：</span> ' +
			'<input type="text" value="" id = "editMenu_url" style="width:400px" />' +
			'<input type = "hidden" id = "edit_id" name = "edit_id" value="">' +
			'<input type = "hidden" id = "state" name = "state" value="">' +
			'</p>' +
			'</div>' +
			'</div>';
	$(".add_btn").click(function() {
		layer.open({
			type: 1,
			title: "添加菜单",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, win_h], //宽高
			content: add_cont,
			closeBtn: 0,
			btn: ['保存', '取消'],
			yes: function (index, layero) {
				var systemType = $("#newSystem_type").text();
				if(systemType =="全部")
				{
					alert("请选择系统名称");
					return false;
				}
				var  menuType = $("#newMenu_type").text();
				if(menuType =="全部")
				{
					alert("请选择模块名称");
					return false;
				}
				var  menuName = $("#newMenu_name").val();
				if(menuName =="")
				{
					alert("请输入资源名称");
					return false;
				}
				var  menuUrl = $("#newMenu_url").val();
				if(menuUrl =="")
				{
					alert("请输入资源地址");
					return false;
				}
				var url = "${ctx}/systemManage/getMenu.do?method=add";
				var param = "";
				param += "&systemType=" + systemType;
				param += "&menuType=" + menuType;
				param += "&menuName=" + menuName;
				param += "&menuUrl=" + menuUrl;
				$.ajax({
				    url:url,
				    type: 'POST',
				    dataType: 'text',
				    data : param,
				    success: function(result){
					   if(result == 'success'){
					   		alert("添加成功！");			   		   		
					   }else if(result == 'yes'){
					   		alert("资源名称已存在!");
					   		$("#newMenu_name").val('').focus();
					   		return;
					   } else {
					   		alert("添加失败!");
					   		return;
					   }
					   layer.close(index);
					   document.location.href = document.location.href.split("#")[0];
			    	}
				});		
				
			},
			btn2: function (index, layero) {
				layer.close(index);
			},
			success: function(layerb, index){
				$('.select').click(function(){
					$('.list').css('display","none');
					if($(this).prop("id") == "menuType")
					{
						if($("#newSystem_type").text() == "资源管理系统")
						{
							$("#type1").show();
							$("#type2").hide();
						}
						else if($("#newSystem_type").text() == "后台管理系统")
						{
							$("#type1").hide();
							$("#type2").show();
						}
						else
						{
							alert("请选择系统名称");
						}
					}
					else
					{
						$(this).find('ul').show();
						$("#newMenu_type").text("全部");
					}
					
				});
				$('.list li').click(function(){
					var str=$(this).text();
					$(this).parent().parent().find('div').find('b').text(str);
					$('.list').slideUp(50);
				});
				$('.select').click(function(e){
					return false;
				});
				$(document).click(function(){
					$('.list').hide('fast');
				});
				$(".btn1").click(function(){
					layer.close(index);
				});
			}
			});
		});
		$(".edit_btn").click(function() {
			layer.open({
				type: 1,
				title: "修改菜单",
				skin: 'layui-layer-rim', //加上边框
				area: [win_w, win_h], //宽高
				content: edit_cont,
				closeBtn: 0,
				btn: ['保存', '取消'],
				yes: function (index, layero) {
					var systemType = $("#editSystem_type").text();
					if(systemType =="全部")
					{
						alert("请选择系统名称");
						return false;
					}
					var  menuType = $("#editmenuType").text();
					if(menuType =="全部")
					{
						alert("请选择模块名称");
						return false;
					}
					var  menuName = $("#editMenu_name").val();
					if(menuName =="")
					{
						alert("请输入资源名称");
						return false;
					}
					var  menuUrl = $("#editMenu_url").val();
					if(menuUrl =="")
					{
						alert("请输入资源地址");
						return false;
					}
					var url = "${ctx}/systemManage/getMenu.do?method=update";
					var param = "";
					param += "&systemType=" + systemType;
					param += "&menuType=" + menuType;
					param += "&menuName=" + menuName;
					param += "&menuUrl=" + menuUrl;
					param += "&id=" + $("#edit_id").val();
					param += "&state=" + $("#state").val(); 
					$.ajax({
					    url:url,
					    type: 'POST',
					    dataType: 'text',
					    data : param,
					    success: function(result){
						   if(result == 'success'){
						   		alert("修改成功！");
						   }
						   else
						   {
						   		alert("修改失败!");
						   }
						   layer.close(index);
						   document.location.href = document.location.href.split("#")[0];
				    	}
					});		
					
				},
				btn2: function (index, layero) {
					layer.close(index);
				},
				success: function(layerb, index){
					$('.select').click(function(){
						$('.list').css('display","none');
						if($(this).prop("id") == "menuType")
						{
							if($("#editSystem_type").text() == "资源管理系统")
							{
								$("#type1").show();
								$("#type2").hide();
							}
							else if($("#editSystem_type").text() == "后台管理系统")
							{
								$("#type1").hide();
								$("#type2").show();
							}
							else
							{
								alert("请选择系统名称");
							}
						}
						else
						{
							$(this).find('ul').show();
							$("#editmenuType").text("全部");
						}
						
					});
					$('.list li').click(function(){
						var str=$(this).text();
						$(this).parent().parent().find('div').find('b').text(str);
						$('.list').slideUp(50);
					});
					$('.select').click(function(e){
						return false;
					});
					$(document).click(function(){
						$('.list').hide('fast');
					});
					$(".btn1").click(function(){
						layer.close(index);
					});
				}
				});
		});
		
</script>
</body>

