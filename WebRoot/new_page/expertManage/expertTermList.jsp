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
	<div class="center">
		<div class="tk_center01" style="min-height:40px;">
		<form id="sfrm" action="" method="POST">
			<div class="tk_zuo">
				<div class="tik_xuanze">
					<div class="mt10 fl tk_tixing">
						<em class="fl">专委会：</em>
						<input type="text"  class="fl tik_select" name="sname" value="${sname}"/>
					</div>
					<div class="mt10 fl mll8 tk_tixing">
						<a href="javascript:void(0);" class="fl tk_chaxun">查询</a>
					</div>
				</div>
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
			<display:table name="termList" requestURI="${ctx}/expert/ExpertTerm.do" id="p" class="mt10 cas_table" size="${totalCount}" pagesize="20"  sort="list" defaultsort="1">
				<display:column title="序号" style="width:5%;" property="id"></display:column>
				<display:column title="专委会" sortable="true" style="width:20%;" property="groupName"></display:column>
				<display:column title="届期" sortable="true" style="width:60%;" sortProperty="startDate">
				${p.startDateStr} 至 ${p.endDateStr}
				</display:column>
 				<display:column title="操作" style="width:15%;">
					<a href="${ctx}/expert/ExpertGroupTerm.do?gid=${p.groupId}" >详情</a>
 				</display:column>
			</display:table>

	</div>
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
	
	
		$('.tk_chaxun').click(function(){
			$('#sfrm').submit();
			
		});	

});
</script>
</body>
</html>