<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/taglibs.jsp"%>
<link rel="stylesheet" href="css/reset.css" />
<link rel="stylesheet" href="css/index.css" />
<link rel="stylesheet" href="css/pickadate/default.css" />
<link rel="stylesheet" href="css/pickadate/default.date.css" />
	
<title>培训后台</title>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
</head>

<%@include file="/peixun_page/top.jsp"%>

<style>
	.picker__holder{overflow-y:hidden;}
	.pro_title{color:#075387 !important;text-decoration:underline;cursor:pointer;}
</style>
	
<body>
<div class="clear"></div>
<form name="queryForm" id = "queryForm" action="${ctx}/contentManage/editionManage.do?mode=editCV&id=${id}" method="post">
<!-- 查询条件 -->
<div class="center">
	<h2 style="font-size:16px; font-weight:600;">绑定【首页 &gt; 课程 &gt; 】</h2>
	<div class="tiaojian" style="margin-top:30px;min-height:40px;">
		
		<p class="fl">
			<span>课程名称：</span>
			<input type="text" name="cvName" id="cvName" value="${cvName}"/>
		</p>

		
	</div>
	<div class="fl cas_anniu">
			<a href="javascript:$(queryForm).submit();" class="fl" style="margin-bottom:10px;width:70px;margin-left:0px;">查询</a>
			<a href="${ctx}/contentManage/editionManage.do?mode=list" class="fl" style="width:70px;">返回</a>
		</div>
</div>
<div class="clear"></div>
<div class="bjs"></div>

<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
	<div class="clear"></div>
	<table class="table">
	<caption class="tiaojian">
		<div class="fr cas_anniu" style="width:98%;">			
			<a href="javascript:void(0)" onclick="bindCV('${id}')" class="fl" style="width:100px;margin-left:89%;">确认绑定</a>
			
		</div>
	</caption>
		<display:table name="list" requestURI="${ctx}/contentManage/editionManage.do?mode=editCV" id="p" class="mt10 table" pagesize="15">			
			<display:column title="序号" style="width:3%;">${p_rowNum}</display:column>
			<display:column title="课程ID" style="width:6%;">${p.id}</display:column>	
			<display:column title="课程名称" style="width:10%;">${p.name}</display:column>
			<display:column title="所属项目名称" style="width:10%;">${p.cvsetName}</display:column>
			<display:column title="课程起止时间" style="width:10%;">
				<fmt:formatDate value="${p.startDate}" pattern="yyyy-MM-dd"/>至
				<fmt:formatDate value="${p.endDate}" pattern="yyyy-MM-dd"/>		
			</display:column>
			
			<display:column title="创建时间" style="width:10%;">
				<fmt:formatDate value="${p.create_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</display:column>
			<%-- <display:column title="标签" style="width:5%;">${p.id}</display:column> --%>	
			<display:column title="标签" style="width:5%">						
				<c:forEach var="item" items="${p.brand}" varStatus="status"> 
					<c:if test="${item == 1}">病例</c:if>						
					<c:if test="${item == 3}">VR</c:if>
					<c:if test="${item == 4}">名师课程</c:if>
					<c:if test="${item == 5}">三维动画</c:if>
					<c:if test="${item == 6}">其他</c:if>							
				</c:forEach> 
			</display:column>	
			<display:column title="选择绑定" style="width:5%;">
				<input type="checkbox" name="project_id" value="${p.id}"/>
			</display:column>				
		</display:table>	
		<div class="clear"></div> 		
	</div>
	</table>
</div>
</form>
<script type="text/javascript">

var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
var submenu = "lc_left0" + menuindex;
$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
$('.'+submenu+'').show();
$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");

var basePath = "${ctx}";
$(function(){
	$('.select').click(function(){
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
	});
	
	$('.list option:selected').each(function(){
		var str=$(this).text();
		$(this).parent().parent().parent().find('b').text(str);
	});
	
	
	$('.queren').click(function(){
		$('.make02').hide();		
	});
});


//绑定
function bindCV(id){
	var cvsetId = $("input[type='checkbox']:checked");
	if(cvsetId.length == 0){
		alert("请先选择要绑定课程!");
	    return;
	}
	if(cvsetId.length > 4){
		alert("最多推荐4项内容！");
		return false;
	}
	$.ajax({
	    url: "${ctx}/contentManage/editionManage.do?mode=bindCount",
	    type: 'POST',
	    data: "&id="+id+"&flag=CV",
	    dataType: 'text',
	    success: function(result){
	    	var count = parseInt(result);
	    	if((count+cvsetId.length) > 4){
	    		alert("最多推荐4项内容！");
	    		return false;
	    	}
		   if(confirm("确定绑定?")) {
				var selectedIds = '';
				if(cvsetId != '' && cvsetId.length >0 ){
					for(var i=0; i<cvsetId.length; i++){
						selectedIds += cvsetId[i].value + ',';
					}
				}
			 
				var p = {'cvIds':selectedIds,'id':id};
				
				$.post("${ctx}/contentManage/editionManage.do?mode=bindCV", p,
						function(data){
							if(data >0){
								alert("绑定成功!");
							}
							else{
								alert("由于网络原因,更改不成功,请稍候再试!");
							}
							document.location.href = document.location.href.split("#")[0];
					});
				
			}
		   
	    }
	});
	
}
</script>
</body>
</html>