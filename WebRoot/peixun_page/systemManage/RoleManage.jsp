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
<form name="queryForm" id="queryForm" action="${ctx}/systemManage/RoleManage.do" method="post">
<div class="center">
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl" style="margin:0 20px 10px 0">
			<span style="width:5em;text-align:right;">名称：</span>
			<input type="text" id = "roleName" name = "roleName" value = "${roleName}" />
		</p>
		<p class="fl" style="margin:0 0 10px 0">
			<span style="width:5em;text-align:right;">状态：</span>
		</p>
		<div class="fl select"  style="margin:0 20px 0 0">
			<div class="tik_position">
				<b class="ml5">
				<c:if test = "${state == null}">全部</c:if>
				<c:if test = "${state == 1}">正常</c:if>
				<c:if test = "${state == 0}">停用</c:if>
				</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display: none;">
				<select id="state" name="state" style="display:none;">
					<option value ="">全部</option>
					<option value="1"<c:if test="${state==1}"> selected</c:if>>正常</option>
					<option value="0"<c:if test="${state==0}"> selected</c:if>>停用</option>
				</select>
				<li>全部</li>
				<li>正常</li>
				<li>停用</li>
			</ul>
		</div>

		<div class="clear"></div>
		<p class="fl cas_anniu">
			<a href="javascript:query();" style="width:70px;margin:0 0 20px 20px;">查询</a>
		</p>
		<p class="fr cas_anniu">
			<a href="javascript:void(0);" class="add_btn" style="width:140px;margin:0 0 20px 20px;">添加角色</a>
		</p>
	</div>
</div>
</form>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->

<div class="center none" style="">
	<div class="tk_center01">		
		<display:table name="roles" id="element" requestURI="${ctx}/systemManage/RoleManage.do?roleName=${roleName }&state=${state }"
		size="${page.count}" pagesize="${page.pageSize}" list="${page.list}"
		style="font-size:12px;width: 100%;top:0px; margin:0px;padding:0px;"  class="mt10 table">
			<display:column
				title="序号"
				style="width:5%;text-align:center">
				${element_rowNum}
			</display:column>			
			<display:column property="roleName" title="名称" style="width:30%;text-align:center"></display:column>			
			<display:column property="roleNameDesc" title="权限" style="width:30%;text-align:center"></display:column>
			<display:column title="状态" style="width:15%;text-align:center">
				<c:if test="${element.status eq 1}">正常</c:if>
				<c:if test="${element.status eq 0}">停用</c:if>
			</display:column>
			<display:column title="操作" style="width:20%;text-align:center">				
				<a href="javascript:updateState(${element.roleId},${element.status});"> 
					<c:if test="${element.status eq 1}">停用</c:if>
					<c:if test="${element.status eq 0}">正常</c:if>
				</a>
				<a href="${ctx}/systemManage/getRole.do?method=list&roleId=${element.roleId}" class="exp1_sty exp1_chakan05">授予资源</a>
			</display:column>				
			
		</display:table>
		<div class="clear"></div>
	</div>
</div>

<script type="text/javascript">
var msg = "${msg}";

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
				
	var win_w = "500px";
	var win_h = "225px";
	var role_cont = '' +
			'<div class="center">' +
			'<div class="tiaojian" style="min-height:40px;">' +
			'<p class="fl" style="margin:0 0 20px 0;">' +
			'<span style="width:6em;text-align:right;"><font color="red">*</font>角色名称：</span> ' +
			'<input type="text" id="role_add" value="" />' +
			'<span style="color:red;font-size:10px;">&nbsp;以"ROLE_"开头,大小写字母或者数字结尾</span></p>' +			
			'<p class="fl" style="margin:0 0 20px 0;">' +
			'<span style="width:6em;text-align:right;"><font color="red">*</font>权限：</span> ' +
			'<input type="text" id="roleDesc_add" value="" />' +
			'</p>' +			
			'</div>' +
			'</div>';
	var isSaveRole = true;
	$(".add_btn").click(function() {
		layer.open({
			type: 1,
			title: "添加角色名称",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, win_h], //宽高
			content: role_cont,
			closeBtn: 0,
			btn: ['确定并授权', '取消'],
			yes: function (index, layero) {
				//缩写保存功能
				var url = "${ctx}/systemManage/saveRole.do?method=success";
				var param = "";
				
				//校验role_add必须以ROLE_开头，不然系统启动的时候会报错,并且只能以大小写字母或数字结尾
				var role_add = $('#role_add').val().trim();
				var roleReg = /ROLE_[0-9a-zA-Z]+$/;
				if (!roleReg.test(role_add)) {
					alert('角色名称必须以"ROLE_"开头,并且后边只能是大写字母，小写字母或者数字');
					return;
				}
				//校验角色标识字段不能为空，不然会异常
				if ($('#roleDesc_add').val().trim() == '') {
					alert("请填写角色标识！");
					return ;
				}
				param += "&name=" + $('#role_add').val().trim() + "&nameDesc=" + $('#roleDesc_add').val().trim();	
				if(isSaveRole){
					isSaveRole = false;
					$.ajax({
					    url:url,
					    type: 'POST',
					    dataType: 'json',
					    data : param,
					    success: function(result){
					        //result=null;
					        isSaveRole = false;
					        if(null!=result&&"undefined"!=result){
					        	/** 刷新资源管理系统的内存 **/
					        	var url2 = "${ctxAdminURL}/system/reloadrole.do";
					        	$.ajax({
								    url:url2,
								    type: 'POST',
								    dataType: 'json',
								    success: function(d){
							    	}
								});
					        	/** 刷新资源管理系统的内存 **/
	                            alert(result.msg);
	                            layer.close(index);
	                            var roleId = result.roleId;
	                            if(roleId != null)
	                            {
	                                document.location.href = "${ctx}/systemManage/getRole.do?method=list&roleId=" + roleId;
	                            }
	                            else
	                            {
	                                document.location.href = document.location.href.split("#")[0];
	                            }
							} else {
	                            document.location.href = document.location.href.split("#")[0];
							}
				    	}
					});
				}else{
					alert("请勿重复提交保存！");
				}
			},
			btn2: function (index, layero) {
				layer.close(index);
			}
		});
	});

})
function query()
{
	document.getElementById("queryForm").submit();
}
function updateState(id,status)
{	
	if(status == 1){
		if(confirm("确定停用该角色？停用后，拥有该角色的用户权限将被解除！"))
		{
			var url = "${ctx}/systemManage/deleteRole.do?&rid=" + id;
			$.ajax({
				url:url,
				dataType:"text",
				type: 'POST',
				success: function(result){
					if(result == "success")
					{
						alert("操作成功");
					}
					else
					{
						alert("操作失败");
					}
					document.location.href = document.location.href.split("#")[0];
				}				
			});
		}
	}
	else {
		var url = "${ctx}/systemManage/deleteRole.do?&rid=" + id;
			$.ajax({
				url:url,
				dataType:"text",
				type: 'POST',
				success: function(result){
					if(result == "success")
					{
						alert("操作成功");
					}
					else
					{
						alert("操作失败");
					}
					document.location.href = document.location.href.split("#")[0];
				}				
			});
		}
}
</script>
</body>

