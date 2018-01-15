<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/new_page/top.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理平台</title>
</head>

<body class="bjs">
<div>
	<!-- 条件 -->
	<div class="center">
		<div class="tk_center01" style="min-height:80px;">
			<form id="sfrm" action="${ctx}/expert/ExpertSubGroup.do?gid=${gid}" method="POST">
			<div class="tk_zuo">
				<div class="tik_xuanze">
					<div class="mt10 fl tk_tixing">
						<span class="fl">学组名称：</span>
						<input type="text"  class="fl tik_select" name="name" value="${info.name}"/>
					</div>
					<div class="mt10 fl mll8 tk_tixing">
						<em class="fl">联系人：</em>
						<input type="text"  class="fl tik_select" name="contact" value="${info.contact}"/>
					</div>
					<div class="mt10 fl tk_tixing">
						<span class="fl">状态：</span>
						<div class="fl tik_select">
							<div class="tik_position_1">
								<b class="ml5">请选择</b>
								<p class="tik_position01"><i class="tk_bjt10"></i></p>
							</div>
							<ul class="fl tk_list_1 tk_list" style="display:none;">
								<select name="state" style="display:none;">
									<option value="0">请选择</option>
									<option value="1"<c:if test="${info.lockState==1}"> selected</c:if>>启用</option>
									<option value="2"<c:if test="${info.lockState==2}"> selected</c:if>>禁用</option>
								</select>
									<li>请选择</li>
									<li>启用</li>
									<li>禁用</li>
								</ul>
						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
			<div class="mt15 tk_you shiti" style="">
				<a href="javascript:void(0);" class="fl tk_chaxun">查询</a>
				<a href="javascript:void(0);" class="fl ml30 tk_chaxun01">清空</a>
			</div>
			<div class="clear"></div>
			</form>
		</div>
		<div class="clear"></div>
	</div>
	<div class="tkbjs"></div>
	<!-- 题库内容 -->
	<div class="center">
		<div class="tk_center01">
			<div class="mt10 kit1_tiaojia">
				<div class="mt10 fl tk_tixing">
					<em class="fl">${parent.name}</em>
				</div>
				<div class="fr cas_anniu">
				<c:if test="${parent.lockState==1}">
					<a href="${ctx}/expert/ExpertSubGroup.do?gid=${gid}&mode=add"  class="fl" style="width:95px;margin-right:15px;">添加学组</a>
					<a href="javascript:void(0);" onclick="doBatchDelProp();" class="fl" style="width:95px;margin-right:15px;">批量删除</a>
				</c:if>
					<a href="${ctx}/expert/ExpertGroupManage.do" class="fl" style="width:95px;">返回</a>
				</div>
			</div>
			<div class="clear"></div>
			<display:table name="extList" requestURI="${ctx}/expert/ExpertSubGroup.do?gid=${gid}" id="p" class="mt10 cas_table" size="${totalCount}" pagesize="20"  sort="list" defaultsort="1" defaultorder="descending">
				<display:column title="<input type='checkbox' class='chkall'>" style="width:2%;text-align:center">
					<c:if test="${p.lockState==1}"><input type="checkbox" class='chkk' name="idArr" value="${p.id}" disabled="disabled" id="qcid"  /></c:if>
					<c:if test="${p.lockState==2}"><input type="checkbox" class='chk' name="idArr" value="${p.id}" id="qcid" /></c:if>
				</display:column>
				<display:column title="序号" style="width:10%;" property="id"></display:column>
				<display:column title="学组名称" style="width:15%;" property="name"></display:column>
				<display:column title="联系人" style="width:10%;" property="contact"></display:column>
 				<display:column title="手机号码" style="width:12%;" property="phone2"></display:column>
 				<display:column title="邮箱" style="width:13%;" property="email"></display:column>
 				<display:column title="状态" style="width:10%;">
					<c:if test="${p.lockState==1}">启用</c:if>
					<c:if test="${p.lockState!=1}">禁用</c:if>
 				</display:column>
 				<display:column title="操作" style="width:20%;">
 				<c:if test="${parent.lockState==1}">
					<a href="${ctx}/expert/ExpertSubGroup.do?gid=${gid}&mode=edit&id=${p.id}" >修改</a>
					<a href="javascript:doLock(${p.id},${p.lockState});" >
						<c:if test="${p.lockState!=1}">启用</c:if>
						<c:if test="${p.lockState==1}">禁用</c:if>
					</a>
					<c:if test="${p.lockState==2}"><a href="javascript:doDelProp(${p.id});" >删除</a></c:if>
				</c:if>
					<a href="${ctx}/expert/ExpertSubGroupMember.do?expertGroup=xz&gid=${gid}&sid=${p.id}" >学组成员</a>
 				</display:column>
			</display:table>


		</div>
	</div>
	
		<!-- 试题内容（结束） -->
	
<div class="clear"></div>
</div>


<script type="text/javascript">
$(function(){

	$('.chkall').click(function(){
		if ($(this).attr('checked') == 'checked')
			$('.chk').attr('checked' , 'checked');
		else
			$('.chk').removeAttr('checked');
	});
	
	//select menu
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "mar_left0" + menuindex;
		$('.tk_mainmenu>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
	
	//下拉框
		$('.tik_select').click(function(){
			$(".tk_list").css("display","none");
			$(this).find('ul').show();
			return false;
		});
		
		$('.tk_list li').click(function(){
			var str=$(this).text();
			$(this).parent().parent().find('div').find('b').text(str);
			$(this).parent().find('option').prop('selected', '');
			$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
			$('.tk_list').slideUp(50);
		});

		$('.tk_list option:selected').each(function(){
			var str=$(this).text();
			$(this).parent().parent().parent().find('b').text(str);
		});

		$(document).click(function(){
			$('.tk_list').hide('fast');
		});
	//查询
		$('.tk_chaxun').click(function(){
			$('#sfrm').submit();
		});
	//清空	
		$('.tk_chaxun01').click(function(){
			$('#sfrm').find('input').val('');
			$('#sfrm').find('select').val('');
			$('.tik_select').find('b').text("请选择");
		});



});

//删除
function doDelProp(id){
	if(!confirm("确定删除？")) return;
	
	var url = '${ctx}/expert/ExpertManage.do?mode=batchSubGourpDel&gourpType=xz&id=' + id;
	$.ajax({
	    url:url,
	    type: 'POST',
	    dataType: 'text',
	    success: function(result){
		   if(result == 'success'){
		      alert('删除成功！');
		      document.location.href = document.location.href.split("#")[0];
		   }else if(result != ""){
			  alert(result);
		      document.location.href = document.location.href.split("#")[0];
		   }else{
		      alert('删除失败！');
		   }
	    },
		error: function(){
		   	  alert('失败.请检查关联!');
		}
	});
}

//批量删除
function doBatchDelProp(){
	
	if (!checkMaterialIds()) {
		alert("请选择学组!");
		return;
	}

	if(!confirm("确定批量删除？")) return;
	
	var params = "id=";
	$("input[name='idArr']").each(function () {
   		if ($(this).is(':checked')) {
       		params += $(this).val() + ",";
   		}
	});
	
	var url = '${ctx}/expert/ExpertManage.do?mode=batchSubGourpDel&gourpType=xz';

	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'text',
	    success: function(result){
		   if(result == 'success'){
		      alert('删除成功！');
		      document.location.href = document.location.href.split("#")[0];
		   }else if(result != ""){
			  alert(result);
		      document.location.href = document.location.href.split("#")[0];
		   }else{
		      alert('删除失败！');
		   }
	    },
		error: function(){
		   	  alert('失败.请检查关联!');
		}
	});
}

function checkMaterialIds() {
    var checked = false;
    $("input[name='idArr']").each(function () {
        if ($(this).is(':checked')) {
            checked = true;
            return false;
        }
    });
    return checked;
}

function doLock(id,lockState){
	var url = '${ctx}/expert/ExpertSubGroup.do?gid=${gid}&mode=lock';
	if(id!=''){
		var params = 'id=' +id;
	}
	if(lockState==1){
		if(!confirm("您确定要禁用吗?")){
			return false;
		}
	}else{
		if(!confirm("您确定要启用吗?")){
			return false;
		}
	}
	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'text',
	    success: function(result){
		   if(result == 'success'){
		      alert('操作成功！');
		      document.location.href = document.location.href.split("#")[0];
		   }else{
		   		alert('操作失败!');
		   };
	    }
	});
		
}
</script>
</body>
</html>