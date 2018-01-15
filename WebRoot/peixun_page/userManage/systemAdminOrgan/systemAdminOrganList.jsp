<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/taglibs.jsp"%>

<title>培训后台</title>

		<script type="text/javascript">


//[查询] 
function query() {
		document.getElementById('queryForm').value = 'list';
		document.getElementById('queryForm').submit();
		//document.forms[0].submit();
}

</script>
</head>
	<%@include file="/peixun_page/top.jsp"%>
	<body>

		<div class="none" style="">

		<%-- <form name="queryForm" action="${ctx}/peixun_page/userManage/systemAdminOrgan/systemAdminOrgan.do" method="post" id ="queryForm"> --%>
		<form name="queryForm" action="${ctx}/system/systemAdminOrgan.do" method="post" id ="queryForm">
		<input type="hidden" id="method" name="method" value="list" />
			<div class="center">
				<div class="tiaojian" style="min-height:40px;">
					<p class="fl">
						<span>名称：</span>
						<input name="name" id="name" value="${name }"/>
					</p>
					<div class="fl cas_anniu">
						<a href="javascript:query();" class="fl" style="width:70px;margin-left:70px;">查询</a>
					</div>
				</div>
			</div>
		</form>
		</div>
		
		
		<div class="bjs"></div>
		<div class="center none" style="">
		<div class="center01">
			
		<display:table requestURI="${ctx}/system/systemAdminOrgan.do?method=list"
		 id="systemAdminOrgan" cellpadding="0" cellspacing="0" partialList="true" 
		 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
		 style="font-size:12px;" class="mt10 table" keepStatus="true">
		<display:column title='<input type="checkbox" class="chkall" />全选' style="text-align:center;width:5%;"><input type="checkbox" class="chk" value="${systemAdminOrgan.organId}"/></display:column>
			<div class ="center">
			<display:column title="序号" style="text-align:center;width:10%">
			${systemAdminOrgan_rowNum}
			</display:column>
			<display:column property="name" title="ID" style="text-align:center;width:30%" />
			<display:column property="description" title="站点域名" style="text-align:center;width:30%"/>
			</div>
		</display:table>
		
		</div>
		</div>

		<script type="text/JavaScript">
	  
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
		
		//生成学习卡
			$('.xuexike').click(function(){
				$('.make02').show();
			});
			$('.queren').click(function(){
				$('.make02').hide();
			});
			$('.quxiao').click(function(){
				$('.make02').hide();
			});
			$('.chkall').click(function(){
					if ($(this).attr('checked') == 'checked')
						$('.chk').attr('checked' , 'checked');
					else
						$('.chk').removeAttr('checked');
			});
	
		});
	  
		 
	  </script>  
	</body>
</html>
