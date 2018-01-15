<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<%@include file="/commons/taglibs.jsp"%>
</head>
<%@include file="/peixun_page/top.jsp"%>

<body>
<div class="clear"></div>
<!-- 查询条件 -->
<div class="center">	
	<form id="main_view" action="${ctx}/cardManage/SystemCardType.do" method="post">
	   <div class="tiaojian" style="min-height:40px;">	
			<p class="fl">
				<span>学科：</span>			
				<input type="hidden" id="propIds" name="propIds" value="${propIds}"/>
				<input type="hidden" id="propNames" name="propNames" value="${propNames}"/>
				<input type="hidden" name="method" value="selectCvBindCardType"/>
				<input type="hidden" id="cardTypeId" name="cardTypeId" value="${cardTypeId}"/>
				<div class="duouan" style="margin-right:20px" id="propNames01">${propNames}</div>
			</p>
			<p class="fl" style="margin-right:20px;">
				<span>创建人：</span>
				<input type="text" name="creater" value="${creater}"/>
			</p>
			<p class="fl" style="margin-right:20px;">
				<span>项目名称：</span>
				<input type="text" name="CVSetName" value="${sname}"/>			
			</p>
		
			<div class="fl cas_anniu">
				<a href="javascript:search();" class="fl" style="width:50px;margin-left:50px;">查询</a>
			</div>
			<div class="fl cas_anniu">
				<a href="javascript:bindCardType();" class="fl" style="width:50px;margin-left:50px;">绑定</a>
			</div>
			
	  </div>
	</form>
	</div>
</div>

<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">	
		<display:table name="CVSet" requestURI="${ctx}/cardManage/SystemCardType.do?method=selectCvBindCardType" id="CVS" class="mt10 table" pagesize="15">			
					<display:column title="<input type='checkbox' class='chkall'/>" style="width:4%;"><input type='checkbox' name='pidChk' class='chk' value="${CVS.id}"/></display:column>
					<display:column title="序号" style="width:3%;">${CVS.id}</display:column>
					<display:column title="学科"  style="width:5%;">
						<%-- <%-- ${CVS.userImageList.departmentPropList.name} --%>
						<c:forEach	items="${CVS.userImageList}" varStatus="status" var="image">
							<c:forEach	items="${image.departmentPropList}" varStatus="status" var="prop">
								${prop.name}&nbsp;
							</c:forEach>
						</c:forEach> 
					</display:column>
					<display:column title="项目名称" style="width:15%;">${CVS.name}</display:column>
					<display:column title="项目编号" style="width:10%;">${CVS.code}</display:column>
					<display:column title="项目级别" style="width:6%;">
						<c:if test="${CVS.level == 1}">国家I类</c:if>
						<c:if test="${CVS.level == 2}">省级I类</c:if>
						<c:if test="${CVS.level == 3}">市级I类</c:if>
						<c:if test="${CVS.level == 4}">省级II类</c:if>
						<c:if test="${CVS.level == 5}">市级II类</c:if>
					</display:column>
					<display:column title="学分" style="width:5%;">${CVS.score}</display:column>
					<display:column title="项目起止时间" style="width:10%;">
						<fmt:formatDate value="${CVS.start_date}" pattern="yyyy-MM-dd"/>至
						<fmt:formatDate value="${CVS.end_date}" pattern="yyyy-MM-dd"/>		
					</display:column>
					<display:column title="项目负责人" style="width:8%;">
						<c:forEach	items="${CVS.managerList}" var="manager">
							${manager.name}&nbsp;
						</c:forEach>
					</display:column>
					<display:column title="审核人" style="width:8%;">${CVS.deli_man}</display:column>
					<display:column title="创建时间" style="width:10%;">
						<fmt:formatDate value="${CVS.create_date}" pattern="yyyy-MM-dd"/>
					</display:column>					
					<display:column title="标签" style="width:5%;">${CVS.sign}</display:column>
		</display:table>		
		<div class="clear"></div> 		
	</div>
</div>

<script type="text/javascript">
$(function(){
        //check all
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
		var submenu = "lc_left0" + menuindex;
		$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
	//下拉框
		select_init();
		/* $('.fl select').click(function(){
			$('.list').css("display","none");
			$(this).find('ul').show();
			return false;
		});
		$('.list li').click(function(){
			var str=$(this).text();
			$(this).parent().parent().find('div').find('b').text(str);
			$(this).parent().find('option').prop('selected', '');
			$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
			$('.list').slideUp(50);	
			alert(('#CVSetStatus').val());		
		});		
		$(document).click(function(){
			$('.list').hide('fast');
		}); */
	//选择目录弹出框
		$('#propNames01').click(function(){
			initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
			$('.att_make01').show();
		});
		$('.cas_anniu_4,.bjt_kt').click(function(){
			$('.att_make01').hide();
		});
		$(document).click(function(){
			$('.list').hide('fast');
		});	 
});

function bindCardType(){
    var cvSetIdVal = "" ;
    $("input[name^='pidChk'][type='checkbox']").each(function(){
		if ($(this).attr("checked") == "checked") {
		   cvSetIdVal += $(this).val() + ",";
		} 
	});
	if (cvSetIdVal == "") {
	   alert("请选择要绑定的项目！") ;
	   return ;
	}
		
	var url = "${ctx}/cardManage/SystemCardType.do?method=bindCvSet&typeId=${cardTypeId}&cvSetId=" + cvSetIdVal ;
	$.ajax({
		url: url,
		type: 'POST',		
		dataType: 'json',
		async: false,
		success: function(result){
		   if (result.message == "success")	{
		      alert("绑定项目成功！") ;
		      //window.location.href = "${ctx}/CVSetManage.do?mode=systemCardTypeCVlist&typeId=" + typeIdVal ;
		      window.opener.reloadMe();
		      window.close() ;		      
		   } else {
		      alert("绑定项目失败，请重试！") ;
		   }		
		}
	});		
}

function select_init() {
	$('.select').click(function(){
		$(".list").css("display","none");
		$(this).find('ul').show();
		return false;
	});
	
	$('.list li').click(function(){
		var str=$(this).text();
		$(this).parent().parent().find('div').find('b').text(str);
		$(this).parent().find('option').prop('selected', '');
		$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');		
		$('.list').slideUp(50);		
	});
	$('.list option:selected').each(function(){
		var str=$(this).text();
		$(this).parent().parent().parent().find('b').text(str);
	});

	$(document).click(function(){
		$('.list').hide('fast');
	});	
}
	
function search(){
	$('#propNames').val($('#propNames01').text());
	$(main_view).submit();
}

</script>

</body>
</html>