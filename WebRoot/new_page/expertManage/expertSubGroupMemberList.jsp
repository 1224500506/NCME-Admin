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
			<form id="sfrm" action="${ctx}/expert/ExpertSubGroupMember.do?expertGroup=xz&gid=${gid}&sid=${sid}" method="POST">
			<div class="tk_zuo">
				<div class="tik_xuanze">
					<div class="mt10 fl tk_tixing">
						<span class="fl">专家名称：</span>
						<input type="text"  class="fl tik_select" name="name" value="${info.name}"/>
					</div>
					<div class="mt10 fl tk_tixing">
						<span class="fl">届期：</span>
						<div class="fl tik_select">
							<div class="tik_position_1">
								<b class="ml5">请选择</b>
								<p class="tik_position01"><i class="tk_bjt10"></i></p>
							</div>
							<ul class="fl tk_list_1 tk_list" style="display:none;">
								<select name="term" style="display:none;">
									<option value="">请选择</option>
									<c:forEach items="${termlist}" var="group">
										<option value="${group.id}"<c:if test="${group.id==info.term}"> selected</c:if>>${group.startDateStr} - ${group.endDateStr}</option>
									</c:forEach>
								</select>
									<li>请选择</li>
									<c:forEach items="${termlist}" var="group">
										<li>${group.startDateStr} - ${group.endDateStr}</li>
									</c:forEach>
								</ul>
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
					<em class="fl">${groupName}</em>
				</div>
				<div class="fr cas_anniu">
				<c:if test="${islocked==1}">
					<a href="javascript:void(0);"  class="fl att_addExpert" style="width:95px;margin-right:15px;">添加成员</a>
				</c:if>
					<a href="javascript:void(0);"  class="fl att_selsDel" style="width:95px;margin-right:15px;">批量移除</a>
					<a href="${ctx}/expert/ExpertSubGroup.do?gid=${parentid}" class="fl" style="width:95px;">返回</a>
				</div>
			</div>
			<div class="clear"></div>
			<display:table name="extList" requestURI="${ctx}/expert/ExpertSubGroupMember.do?expertGroup=xz&gid=${gid}&sid=${sid}" id="p" class="mt10 cas_table" size="${totalCount}" pagesize="20"  sort="list" defaultsort="1" defaultorder="descending">
				<display:column title="<input type='checkbox' class='chkall'/>" style="width:3%;"><input type='checkbox' name="ids" class='chk' value="${p.id}"/></display:column>
				<display:column title="序号" style="width:5%;" property="id"></display:column>
				<display:column title="专家名称" style="width:10%;" property="name"></display:column>
				<display:column title="担任职务" style="width:13%;">
					<c:choose>
						<c:when test="${fn:contains(p.jobName,',')}">${fn:substring(p.jobName,"0",fn:length(p.jobName)-3)}</c:when>
						<c:otherwise>${p.jobName}</c:otherwise>
					</c:choose>
			   <%-- <c:if test="${p.office==1}">主任委员</c:if>
					<c:if test="${p.office==2}">副主任委员</c:if>
					<c:if test="${p.office==3}">秘书长</c:if>
					<c:if test="${p.office==4}">学组长</c:if>
					<c:if test="${p.office==5}">委员</c:if> --%>
				</display:column>
 				<display:column title="届期" style="width:13%;" property="termStr"></display:column>
 				<display:column title="手机号码" style="width:13%;" property="phone2"></display:column>
 				<display:column title="邮箱" style="width:13%;" property="email"></display:column>
 				<display:column title="工作单位" style="width:13%;" property="workUnit"></display:column>
 				<display:column title="操作" style="width:15%;">
 				<c:if test="${islocked==1}">
					<a href="${ctx}/expert/ExpertSubGroupMember.do?expertGroup=xz&gid=${gid}&sid=${sid}&mode=edit&id=${p.id}" >修改</a>
				</c:if>
					<a href="javascript:doDelProp(${p.id});" >移除</a>
 				</display:column>
			</display:table>

		</div>
	</div>
	
		<!-- 试题内容（结束） -->

<div class="clear"></div>
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
	
		if (document.location.href.indexOf('error') != -1){
				alert ("编码重复，请输入正确的编码！");
		        document.location.href = "${ctx}/expert/ExpertSubGroupMember.do?expertGroup=xz&gid=${gid}&sid=${sid}";
		    }
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
	//批量移除
		$('.att_selsDel').click(function(){
			var checked = false;
			var ids = "";
	        $("input[name='ids']").each(function () {
	            if ($(this).is(':checked')) {
	                checked = true;
	                ids += $(this).val() + ","; 
	                //return false;
	            }
	        });
	        if (!checked){
	        	alert("请选择要导出的设备!");
            	return false;
	        }
	        if (!confirm("确定移除?"))
	        	return false;
	        	
	        var url = '${ctx}/expert/ExpertSubGroupMember.do?expertGroup=xz&sid=${sid}&mode=batchdel';
			if(ids!=''){
				var params = 'ids=' +ids;
			}
			$.ajax({
			    url:url,
			    type: 'POST',
			    data: params,
			    dataType: 'text',
			    success: function(result){
				   if(result == 'success'){
				      alert('移除成功！');
				      document.location.href = document.location.href.split("#")[0];
				   }else{
				   		alert('移除失败请检查内容!');
				   };
			    }
			});
			
		});
	
	//check all
		$('.chkall').click(function(){
			if ($(this).attr('checked') == 'checked')
				$('.chk').attr('checked' , 'checked');
			else
				$('.chk').removeAttr('checked');
		});

	$('.att_addExpert').click(function(){	
		var url = "ExpertManage.do?mode=select&gid=${sid}";
		var bHeight = $(window).height()-100;
		var bWidth = $(window).width()-200;
		window.open(url, "selectExpert", "top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width=" + bWidth + ",height=" + bHeight);
	});
});

//移除
function doDelProp(id){
	var url = '${ctx}/expert/ExpertSubGroupMember.do?expertGroup=xz&sid=${sid}&mode=del';
	if(id!=''){
		var params = 'id=' +id;
	}
	if(!confirm("您确定要移除吗?")){
		return false;
	}else{
		$.ajax({
		    url:url,
		    type: 'POST',
		    data: params,
		    dataType: 'text',
		    success: function(result){
			   if(result == 'success'){
			      alert('移除成功！');
			      document.location.href = document.location.href.split("#")[0];
			   }else{
			   		alert('移除失败请检查内容!');
			   };
		    }
		});
	}
		
}

</script>
</body>
</html>