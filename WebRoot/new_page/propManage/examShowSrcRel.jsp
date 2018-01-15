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
	<!-- 题库内容 -->
	<div class="center none" style="">
		<div class="tk_tk_xuanxiag" style="">
			<div class="xingzeng_k">
				<i></i><h2 class="fl">关联单元</h2>
			</div>
		<div class="clear"></div>
			<form id="frm" action="${ctx}/propManage/sourceList.do" method="POST">
			<input type="hidden" name="mode" value="addrel" />
			<input type="hidden" name="id" value="${selid}" />
			<div class="ca1_anniu" style="width:60%;">
				<p class="fl" style="margin-top:50px;"><span class="fr">关联单元：</span></p>
				<input type="hidden" id="groupIds" name="groupIds" value="${propList.propIds}"/>
				<textarea name="" cols="" rows="" class="fl tki_bianji takuang_xk" id="groupNames" readonly style="width:80%;min-height:150px;padding:5px;">${propList.propNames}</textarea>
			</div>
			<div class="clear"></div>
			<div style="height:140px;"></div>
			<div class="ca1_anniu" style="width:200px;">
				<a href="javascript:void(0);" class="fl anniu att_baochun">保存</a>
				<a href="${ctx}/propManage/sourceList.do" class="fl ml30 anniu">取消</a>
			</div>
			<div class="clear"></div>
		</form>

		</div>
		
		<div style="height:20px;"></div>
	</div>
		<!-- 试题内容（结束） -->

</div>


<script type="text/javascript">
$(function(){
	//select menu
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "mar_left0" + menuindex;
		$('.tk_mainmenu>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
	
		$('.att_baochun').click(function(){
			$('#frm').submit();
		});
		
		$('.takuang_xk').click(function(){
			initPropList("单元","${ctx}/propManage/getPropListAjax.do", 1, 0, 4, 4, $('#groupIds'), $('#groupNames'));
			$('.att_make01').show();
		});

	//get full name
			var url = "${ctx}/propManage/getPropListAjax.do?ids=${propList.propIds}&mode=getFullNames";
			$.ajax({
			    url:url,
			    type: 'POST',
			    dataType: 'text',
			    success: function(result){
				   if(result != ''){
						var selstr = result;
						$('#groupNames').text(selstr);
				   }
				   else{
				   		$('#groupNames').text('');
				   }
			    },
			    error: function (){
			    	$('#groupNames').text('');
			    }
			});	
		
});


</script>
</body>
</html> 