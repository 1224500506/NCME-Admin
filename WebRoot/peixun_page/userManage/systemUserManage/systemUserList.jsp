<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
	<%@include file="/commons/taglibs.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>培训后台</title>
	</head>
<%@include file="/peixun_page/top.jsp"%>
<body>

<div class="none"  style="">
	<form action="${ctx}/system/systemUser.do?method=list" method="post" name="queryForm">
	<!-- 查询条件 -->
	<div class="center">
		<div class="tiaojian" style="min-height:40px;">
			<p class="fl">
				<span>证件号：</span>
				<input type="text" name="model.certificateNo" id="certificateNo"  class="form-control" width="50%" maxlength="100" value="${systemUserForm.model.certificateNo}">
			</p>
			<p class="fl jianju">
				<span>姓名：</span>
				<input type="text" name="model.realName" id="realName"  class="form-control" maxlength="20" value="${systemUserForm.model.realName}">
			</p>
			<div class="fl cas_anniu">
				<a href="javascript:$(queryForm).submit();" class="fl" style="width:70px;margin-left:70px;">查询</a>
			</div>
		</div>
	</div>
	</form>
	<div class="clear"></div>
	<div class="bjs"></div>
	<!-- 内容 -->
	<div class="center none" style="">
		<div class="center01">
		<form id="listfm" name="listfm" method="post" action="${ctx}/system/systemUser.do?method=list">
		<display:table requestURI="${ctx}/system/systemUser.do?method=list"
				 id="systemUser" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}" name="page"
				 style="font-size:12px;width:100%;" class="mt10 table" keepStatus="true"
				  decorator="com.hys.exam.displaytag.TableOverOutWrapper" >		
			<display:column title="序号" style="width:5%;text-align:center">
				${systemUser_rowNum}
			</display:column>
			<display:column title="ID" property="userId" style="width:5%;text-align:center" /> 
			<display:column title="姓名" property="realName" style="width:10%;text-align:center" /> 
			<display:column title="证件号码" property="certificateNo" style="width:15%;text-align:center">
			</display:column>
			<display:column title="登录名" property="accountName" style="width:10%;text-align:center" />
			<display:column title="邮箱" property="email" style="width:160px;text-align:center" />
			
			<display:column title="手机" property="mobilPhone"  style="width:10%;text-align:center">
			</display:column>
			<display:column title="单位" property="workUnit"  style="width:10%;text-align:center">
			</display:column>
			<display:column title="注册时间" property="regDate"  style="width:10%;text-align:center">
			</display:column>
			<display:column title="操作" style="width:10%;text-align:center">
				<a href="javascript:infoView(${systemUser.userId});" title="查看详情">详情</a> 
			</display:column>
		</display:table>
   		<input type="hidden" name="org.apache.struts.taglib.html.TOKEN"  value="${sessionScope['org.apache.struts.action.TOKEN']}" />
		</form>
		<div class="clear"></div> 	
		</div>
	</div>
</div>
<!-- 修改密码 -->
<div class="toumingdu make01" style="display:none;">
	
</div>
<!-- 详情 -->
<div class="toumingdu make02" style="display:none;">
	<div class="kh_center" style="height:400px;">
		<div style="padding-top:40px;margin-left:40px;">
			<div class="fl mt10 kh_shengcheng">
				<span><i style="color:red;"></i>站点ID：</span>
				<p id="accountId"></p>
			</div>
			<div class="fl mt10 kh_shengcheng">
				<span><i style="color:red;"></i>站点域名：</span>
				<p id="accountName"></p>
			</div>
		</div>
		<div class="clear"></div>
		<div class="mt10" style="margin-left:40px;">
			<div class="fl mt10 kh_shengcheng">
				<span><i style="color:red;"></i>登录名：</span>
				<p id="loginName"></p>
			</div>
			<div class="fl mt10 kh_shengcheng">
				<span><i style="color:red;"></i>邮箱：</span>
				<p id="email"></p>
			</div>
		</div>
		<div class="clear"></div>
		<div class="mt10" style="margin-left:40px;">
			<div class="fl mt10 kh_shengcheng">
				<span><i style="color:red;"></i>性别：</span>
				<p id="sex"></p>
			</div>
			<div class="fl mt10 kh_shengcheng">
				<span><i style="color:red;"></i>出生年月：</span>
				<p id="birthday"></p>
			</div>
		</div>
		<div class="clear"></div>
		<div class="mt10" style="margin-left:40px;">
			<div class="fl mt10 kh_shengcheng">
				<span><i style="color:red;"></i>手机：</span>
				<p id="mobilPhone"></p>
			</div>
			<div class="fl mt10 kh_shengcheng">
				<span><i style="color:red;"></i>单位地址：</span>
				<p id="postCode"></p>
			</div>
		</div>
		<div class="clear"></div>
		<div class="mt10" style="margin-left:40px;">
			<div class="fl mt10 kh_shengcheng">
				<span><i style="color:red;"></i>单位名称：</span>
				<p id="workUnit"></p>
			</div>
			<div class="fl mt10 kh_shengcheng">
				<span><i style="color:red;"></i>行业岗位名称：</span>
				<p id="deptName"></p>
			</div>
		</div>
		<div class="clear"></div>
		<div class="mt10" style="margin-left:40px;">
			<div class="fl mt10 kh_shengcheng">
				<span><i style="color:red;"></i>注册日期：</span>
				<p id="regDate"></p>
			</div>
		</div>
		<div class="clear"></div>
		<div class="cas_anniu" style="margin-top:40px;margin-left:340px;">
			<a href="#" class="fl queren" style="width:70px;">返回</a>
		</div>
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
	
	$('.queren').click(function(){
		$('.make02').hide();		
	});
});

function infoView(id) {
	
	var url = "${ctx}/system/systemUser.do";
	var params = "method=info&id=" + id;
	
	$.ajax({
		url: url,
		type: 'POST',
		data: params,
		dataType: 'json',
		success: function(result){
			if(result != ""){				
				$('#accountId').text(result.item.accountId);
				$('#accountName').text(result.item.accountName);				
				$('#loginName').text(result.item.realName);
				$('#email').text(result.item.email);
				$('#sex').text(result.item.sex==1?'男':'女');
				$('#birthday').text(result.item.birthday);
				$('#mobilPhone').text(result.item.mobilPhone);
				$('#postCode').text(result.item.postCode);
				$('#workUnit').text(result.item.workUnit);
				$('#deptName').text(result.item.deptName);
				$('#regDate').text(result.regDate);
				$('.make02').show();
			}
		}
	});
}
</script>
</body>
</html>