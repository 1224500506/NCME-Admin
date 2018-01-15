<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<%@include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
</head>

<%@include file="/peixun_page/top.jsp"%>

<body>
<!-- 内容 -->
<div class="center" style="">
	<form name = "fm1" id = "fm1" method="POST" action = "${ctx}/systemManage/getRole/method = list">
	<div class="tabs_main">
		<ul class="tab">
			<li id="tab1" class="active">资源管理系统</li>
			<li id="tab2">后台管理系统</li>
		</ul>
		<div class="tab1 cont">
			<c:forEach items="${adminMenuList}" var = "adminMenu">
				<table class="fl table" style="width:auto;margin:0 10px 10px 0;">
					<thead>
					<tr>
						<th colspan="2">${adminMenu.menu_type}</th>
					</tr>
					<tr>
						<th>功能名称</th>
						<th>操作权限</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${adminMenu.systemMenuList}" var = "adminTwomenu">						
							<tr>	
								<td>${adminTwomenu.name}</td>
								<td><input type="checkbox" class = "systemMenu1"  id = "systemMenu_${adminTwomenu.id}" value = "${adminTwomenu.id}" <c:if test="${adminTwomenu.state==2}">disabled</c:if> /></td>
							</tr>						
					</c:forEach>
					</tbody>
				</table>
			</c:forEach>										
			
			<div class="clear" style="margin-bottom:20px;"></div>
			<p class="fl cas_anniu">
				<a href="javascript:addMenu();" style="width:70px;margin:0 0 20px 20px;">保存</a>
			</p>
			<p class="fl cas_anniu">
				<a href="${ctx}/systemManage/RoleManage.do" class="add_btn" style="width:70px;margin:0 0 20px 20px;">返回</a>
			</p>
		</div>
			<div class="tab2 cont" style="display:none;">
			
			<c:forEach items="${peixunMenuList}" var = "peixunMenu">
				<table class="fl table" style="width:auto;margin:0 10px 10px 0;">
					<thead>
					<tr>
						<th colspan="2">${peixunMenu.menu_type}</th>
					</tr>
					<tr>
						<th>功能名称</th>
						<th>操作权限</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${peixunMenu.systemMenuList}" var = "peixunTwomenu">						
							<tr>	
								<td>${peixunTwomenu.name}</td>
								<td><input type="checkbox" class = "systemMenu1"  id = "systemMenu_${peixunTwomenu.id}" value = "${peixunTwomenu.id}" <c:if test="${peixunTwomenu.state==2}">disabled</c:if> /></td>
							</tr>						
					</c:forEach>
					</tbody>
				</table>
			</c:forEach>	
			
			<div class="clear" style="margin-bottom:20px;"></div>
			<p class="fl cas_anniu">
				<a href="javascript:addMenu();" style="width:70px;margin:0 0 20px 20px;">保存</a>
			</p>
			<p class="fl cas_anniu">
				<a href="${ctx}/systemManage/RoleManage.do" class="add_btn" style="width:70px;margin:0 0 20px 20px;">返回</a>
			</p>
		</div>
	<div class="center01"> </div>
	<input type = "hidden" id = "menuIds" name = "menuIds" value="${roleMenuList}"/>
	<input type = "hidden" id = "roleId" name = "roleId" value="${roleId}"/>
</div>
</form>
</div>

<script type="text/javascript">
$(function(){
	var roleMenuList = "${roleMenuList}";
	var roleList = roleMenuList.split(",");
	$(roleList).each(function(key,val){
		var roleId = "#systemMenu_" + val;
		$(roleId).prop("checked",true);
	});
	//	标签页
	$(".tabs_main ul.tab li:first").addClass("active");
	$(".tabs_main .cont.first").show().siblings(".cont").hide();
	$(".tabs_main ul.tab li").each(function(){
		$(this).click(function(){
			$(this).addClass("active").siblings().removeClass("active");
			var c_name = $(this).attr("id");
			$(".tabs_main div." + c_name).show().siblings("div").hide();
		});
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
function addMenu()
{
	var menuRole = "";
	$(".systemMenu1").each(function(key,val){
		if($(this).prop("checked"))
		{
			menuRole += $(this).val() + ",";
		}
	});
	$("#menuIds").val(menuRole);
	
	document.getElementById("fm1").action = "${ctx}/systemManage/getRole.do?method=add";
	document.getElementById("fm1").submit();
}
</script>

</body>

