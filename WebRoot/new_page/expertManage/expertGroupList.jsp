<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%-- <%@include file="/new_page/top.jsp"%> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
</head>

<%@include file="/peixun_page/top.jsp"%>
<link rel="stylesheet" href="${ctx}/new_page/css/reset.css" />
<link rel="stylesheet" href="${ctx}/new_page/css/index.css" />
<script type="text/javascript" src="${ctx}/new_page/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<style>
	.picker_holder{overflow-y:hidden;}
	.pro_title{color:#075387 !important;text-decoration:underline;cursor:pointer;}
	button.btn_blue{padding:3px 5px;color:#fff;background-color:#0B92E8;border:0 none;border-radius: 5px;outline:none;}
</style>
<body class="bjs" onkeydown="entersearch();">
<div>
	<!-- 题库内容 -->
	<div class="center">
	<h2 style="font-size:16px; font-weight:600;">查看【导航栏  &gt; 专委会】</h2>
		<div class="tk_center01" style="min-height:100px;">
			<form id="sfrm" action="${ctx}/expert/ExpertGroupManage.do" method="POST">
			<div class="tk_zuo">
				<div class="tik_xuanze">
					<div class="mt10 fl tk_tixing">
						<span class="fl">专委会名称：</span>
						<input type="text"  class="fl tik_select" name="name" value="${info.name}"/>
					</div>
					<div class="mt10 fl tk_tixing">
						<span class="fl">学科：</span>
						<input type="hidden" id="groupIds" name="propIds" value="${info.propIds}" />
						<input type="hidden" name="propNames" id="pNames" value="${info.propNames}" />
						<div class="fl tik_select takuang_xk" id="groupNames">${info.propNames}</div>
					</div>
					<div class="mt10 fl tk_tixing">
						<span class="fl">届期：</span>
						<input type="text"  class="fl tik_select" style="width:150px;" name="startDate" value="${info.startDate}" onClick="WdatePicker()" readonly="readonly"/>
						<em class="fl" style="padding:0px 3px 0px 3px;">至</em>
						<input type="text"  class="fl tik_select" style="width:150px;" name="endDate" value="${info.endDate}" onClick="WdatePicker()" readonly="readonly"/>
					</div>
				</div>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
			<div class="cas_cha" style="width:200px;margin:20px auto;">
				<a href="javascript:void(0);" class="fl tk_chaxun">查询</a>
<!-- 				<a href="javascript:void(0);" class="fl tk_chaxun">返回</a> -->
<a href="${ctx}/contentManage/editionManage.do?mode=list" class="fl tk_chaxun">返回</a>
<!-- 				<a href="javascript:void(0);" class="fl ml30 tk_chaxun01">清空</a> -->
			</div>
			<div class="clear"></div>
			</form>
		</div>
		<div class="clear"></div>
	</div>
	<div class="tkbjs"></div>
	<!--  -->
	<div class="center none" style="">
	<div class="tk_center01">
		<div class="mt10 kit1_tiaojia">
			<div class="fr cas_anniu">
				<a href="${ctx}/expert/ExpertGroupManage.do?mode=add" class="fl" style="width:95px;margin-right:15px;">添加专委会</a>
				<a href="javascript:void(0);" onclick="doBatchDelProp();" class="fl" style="width:95px;margin-right:15px;">批量删除</a>
			</div>
		</div>
		<div class="clear"></div>
			<display:table name="extList" requestURI="${ctx}/expert/ExpertGroupManage.do" id="p" class="mt10 cas_table" size="${totalCount}" pagesize="20" sort="list" defaultsort="0">
				<display:column title="<input type='checkbox' class='chkall'>" style="width:2%;text-align:center">
					<c:if test="${p.lockState==1}"><input type="checkbox" class='chkk' name="idArr" value="${p.id}" disabled="disabled" id="qcid"  /></c:if>
					<c:if test="${p.lockState==2}"><input type="checkbox" class='chk' name="idArr" value="${p.id}" id="qcid" /></c:if>
				</display:column>
				<display:caption>
				、<button type="button" class="btn_blue fr att_setorder" style="margin-right:22%;cursor:pointer;" name="list_filter">保存显示顺序</button></display:caption>
				<display:column title="序号" style="width:5%;" property="id"></display:column>
				<display:column title="名称" sortable="true" style="width:8.5%;" property="name"></display:column>
				<display:column title="学科" style="width:12%;" property="propNames"></display:column>
				<display:column title="主委" style="width:5%;" property="master"></display:column>
 				<display:column title="学组数" style="width:6%;" property="numSubGroup"></display:column>
 				<display:column title="联系人" sortable="true" style="width:6%;" property="contact"></display:column>
 				<display:column title="手机" sortable="true" style="width:8.5%;" property="phone2"></display:column>
 				<display:column title="邮箱" sortable="true" style="width:8.5%;" property="email"></display:column>
 				<display:column title="届期" style="width:16.5%;" property="termStr"></display:column>
 				<display:column title="排序" style="width:10%">
			 </display:column>
 				<display:column title="操作" style="width:20%;">
					<a href="${ctx}/expert/ExpertGroupManage.do?mode=detail&id=${p.id}" >全貌</a>
					 <a href="javascript:doLock(${p.id},${p.locakstate});" >绑定</a>
 				</display:column>
			</display:table>
	</div>
	</div>
		<!-- 试题内容（结束） -->
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
	//查询条件选择
		$('.tk_list option:selected').each(function(){
			var str=$(this).text();
			$(this).parent().parent().parent().find('b').text(str);
		});

		$(document).click(function(){
			$('.tk_list').hide('fast');
		});

	//查询
		$('.tk_chaxun').click(function(){
			$('#pNames').val($('#groupNames').text());  
			$('#sfrm').submit();
		});
	//清空
		$('.tk_chaxun01').click(function(){
			$('#sfrm').find('input').val('');
			$('#sfrm').find('select').val('');
			$('#groupNames').html('');
			$('.tik_select').find('b').text("请选择");
		});

		$('.takuang_xk').click(function(){
			initPropList("学科","${ctx}/propManage/getPropListAjax.do", 1, 0, 3, 3, $('#groupIds'), $('#groupNames'));
			$('.att_make01').show();
		});
		


});


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

//绑定   
function doLock(id,lockstate){
	if(lockstate == 1)
	{
		if(!confirm("您确定要绑定吗？"))return;
	}
	else
	{
		if(!confirm("您确定要解绑吗？"))
			return;
	}
	var url = '${ctx}/expert/ExpertGroupManage.do?mode=lock';
	if(id!=''){
		var params = 'id=' +id;
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

function entersearch(){
   var event = window.event || arguments.callee.caller.arguments[0];
   if (event.keyCode == 13) {
	   $('#pNames').val($('#groupNames').text());  
		$('#sfrm').submit();
   }
}
</script>
</body>
</html>