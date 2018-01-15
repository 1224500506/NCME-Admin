<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/taglibs.jsp"%>
<title>培训后台</title>
</head>
<%@include file="/peixun_page/top.jsp"%>

<body>
<div class="none"  style="">
<form action="${ctx}/system/SystemPostHistory.do?method=list" method="post" name="queryForm">
	<!-- 查询条件 -->
	<div class="center">
		<input type="hidden" id="method" name="method" value="list" />
		<div class="tiaojian" style="min-height:40px;">
			<p class="fl">
				<span>手机号：</span>
				<input type="text" name="mobilePhone" id="mobilePhone" value="${postHistory.mobilePhone}">
			</p>
			<p class="fl jianju">
				<span>姓名：</span>
				<input type="text" name="userName" id="userName" value="${postHistory.userName}">
			</p>
			<p class="fl jianju">
				<span>证书名：</span>
				<input type="text" name="certificateName" id="certificateNo" value="${postHistory.certificateName}">
			</p>
			<div class="fl cas_anniu">
				<a href="javascript:$(queryForm).submit();" class="fl cas_anniu" style="width:70px;margin-left:70px;">查询</a>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<div class="bjs"></div>
<div class="clear"></div>
	<div class="center" style="">
		<div class="center01">
		
		<display:table requestURI="${ctx}/system/SystemPostHistory.do?method=list"
				 id="item" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" 
				 pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;" class="mt10 table" 
				 keepStatus="true">
		 
			<display:column title="序号" style="width:80px; height:36px; text-align:center">
				${item_rowNum}
			</display:column>
			<display:column title="姓名" property="userName" style="width:168px;text-align:center" />
			<display:column title="手机号" property="mobilePhone" style="width:256px;text-align:center" />
			<display:column title="地址" property="address" style="width:432px;text-align:center" />
			<display:column title="证书名" property="certificateName" style="width:255px;text-align:center" />
			<display:column title="创建时间" style="width:344px;text-align:center">
				<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</display:column>
			<display:column title="操作" style="width:167px;text-align:center">
				<a href="javascript:editDlg(${item.id});" class="xuexike"title="修改">修改</a> 
			</display:column>
		</display:table>
		<div class="clear"></div>
		</div>
	</div>	
</form>	
</div>
<!-- 编辑 -->
<div class="toumingdu make02" style="display:none;">
	<div class="lec_center" style="height:420px;">
		<div style="padding-top:20px;">
		<form class="fm1" id="fm1" name="fm1" action="${ctx}/system/SystemPostHistory.do?method=update" method="post">
			<input type="hidden" id="id" name="id" value="" />
			<div class="tiaojian mt10 lc_shengcheng">
				<span>姓名：</span>
				<input type="text" name="userName" id="user_name">
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>手机号：</span>
				<input type="text" name="mobilePhone" id="mobile_phone" >
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>地址：</span>
				<input type="text" name="address" id="address">
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>证书名：</span>
				<input type="text" name="certificateName" id="certificateName">
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>快递编号：</span>
				<input type="text" id="expressNo" name="expressNo" maxlength="250" size="25" datatype="s1-20" errormsg="请输入快递编号！">
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>快递列表：</span>
				<select id="expressId" name="expressId" datatype="n" errormsg="请选择快递！" >
					<option value="-1">----请选择---</option>
					<c:forEach var="item" items="${expressList}" varStatus="stat" >
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>快递详情：</span>
				<input type="text" id="description" name="description">
			</div>
			<div class="clear"></div>
			<div class="cas_anniu" style="margin-top:40px;margin-left:170px;">
				<a href="javascript:submit()" class="fl queren" style="width:70px;">确认</a>
				<a href="#" id="quxiao" class="fl queren" style="width:70px;margin-left:40px;">取消</a>
			</div>
		</form>
		</div>
		<div class="clear"></div>
	</div>
</div>

<script type="text/JavaScript">

function submit(){
	if($.trim($("#user_name").val()) == ""){
		alert("姓名不能为空");
		return false;
	}
	if($.trim($("#mobile_phone").val()) == ""){
		alert("手机号不能为空");
		return false;
	}
	if($.trim($("#address").val()) == ""){
		alert("地址不能为空");
		return false;
	}
	if($.trim($("#certificateName").val()) == ""){
		alert("证书名称不能为空");
		return false;
	}
	$("#fm1").submit();
	$('.make02').hide();
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
		
		//修改	
			/* 			
			$('.xuexike').click(function(){			 	
				$('.make02').show();
			});
 			*/			
		/* 	$('.queren').click(function(){
				$('.make02').hide();
			}); */
			$('#quxiao').click(function(){
				$('.make02').hide();
			}); 
});

function editDlg(id) {
	var url = "${ctx}/system/SystemPostHistory.do";
	var params = "method=updateJson&id=" + id;
	
	$.ajax({
		url: url,
		type: 'POST',
		data: params,
		dataType: 'json',
		success: function(result){
			if(result != ""){				
			
				$('#id').val(result.item.id);
				$('#user_name').val(result.item.userName);				
				$('#mobile_phone').val(result.item.mobilePhone);
				$('#address').val(result.item.address);
				$('#certificateName').val(result.item.certificateName);
				$('#expressNo').val(result.item.expressNo);
				$('#expressId').val(result.item.expressId);
				$('#description').val(result.item.description);
				$('.make02').show();
			}
		}
	});	
}
</script>  		
</body>
</html>
