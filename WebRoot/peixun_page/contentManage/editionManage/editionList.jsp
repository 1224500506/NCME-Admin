<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<%@include file="/commons/taglibs.jsp"%>
</head>
<%@include file="/peixun_page/top.jsp"%>

<body>
<div class="clear"></div>
<form name="queryForm" id = "queryForm" action="${ctx}/contentManage/editionManage.do?method=list" method="post">
<!-- 查询条件 -->
<div class="center">
	
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl" style="margin-left:60px;">
			<span>页面名称：</span>			
			<div class="fl select" >
				<div class="tik_position">
					<b class="ml5">全部</b>
					<p class="position01"><i class="bjt10"></i></p>
				</div>
				 <ul class="fl list" style="display:none;">
				 	<select name="bookName" id="cardTypeId" style="display:none;">
						<option value="0"<c:if test="${bName==null}"> selected</c:if>>全部</option>
						<option value="1"<c:if test="${bName==1}"> selected</c:if>>首页</option>						
						<option value="2"<c:if test="${bName==2}"> selected</c:if>>慕课</option>
						
						<option value="9"<c:if test="${bName==9}"> selected</c:if>>名师</option>
						<option value="10"<c:if test="${bName==10}"> selected</c:if>>专委会</option>
					</select>
					<li>全部</li>
					<li>首页</li>
					<li>慕课</li>
					
					<li>名师</li>
					<li>专委会</li>							
				</ul>
			</div> 
		</p>
		
		<p class="fl" style="margin-left:60px;">
			<span>栏目名称：</span>			
			<div class="fl select" >
				<div class="tik_position">
					<b class="ml5">全部</b>
					<p class="position01"><i class="bjt10"></i></p>
				</div>
				 <ul class="fl list" style="display:none;">
				 	<select class="fl select" name="kindName" id="cardTypeId" style="display:none;">
						<option value="0"<c:if test="${kName==null}"> selected</c:if>>全部</option>
						<option value="1"<c:if test="${kName==1}"> selected</c:if>>推荐项目</option>						
						<option value="2"<c:if test="${kName==2}"> selected</c:if>>推荐课程</option>
						<option value="3"<c:if test="${kName==3}"> selected</c:if>>优质慕课</option>
						<option value="4"<c:if test="${kName==4}"> selected</c:if>>名师课程</option>
						<option value="5"<c:if test="${kName==5}"> selected</c:if>>典型病例</option>
						<option value="6"<c:if test="${kName==6}"> selected</c:if>>精彩直播</option>
						<option value="7"<c:if test="${kName==7}"> selected</c:if>>指南解读</option>
						<option value="8"<c:if test="${kName==8}"> selected</c:if>>专项能力</option>
					</select>
					<li>全部</li>
					<li>推荐项目</li>
					<li>推荐课程</li>	
					<li>优质慕课</li>
					<li>名师课程</li>	
					<li>典型病例</li>
					<li>精彩直播</li>							
					<li>指南解读</li>	
					<li>专项能力</li>							
				</ul>
			</div> 
		</p>
		<p class="fl" style="margin-left:60px;">
			<span>按内容类别：</span>			
			<div class="fl select" >
				<div class="tik_position">
					<b class="ml5">全部</b>
					<p class="position01"><i class="bjt10"></i></p>
				</div>
				 <ul class="fl list" style="display:none;">
				 	<select name="takeKind" id="cardTypeId" style="display:none;">
						<option value ="0"<c:if test="${tKind==null}"> selected</c:if>>全部</option>
						<option value="1"<c:if test="${tKind==1}"> selected</c:if>>项目</option>						
						<option value="2"<c:if test="${tKind==2}"> selected</c:if>>课程</option>
						<option value="3"<c:if test="${tKind==3}"> selected</c:if>>文章</option>
						
						<option value="9"<c:if test="${tKind==9}"> selected</c:if>>名师</option>
						<option value="10"<c:if test="${tKind==10}"> selected</c:if>>专委会</option>
						<option value="8"<c:if test="${tKind==8}"> selected</c:if>>专项能力</option>
					</select>
					<li>全部</li>
					<li>项目</li>
					<li>课程</li>		
					<li>文章</li>	
					
					<li>名师</li>
					<li>专委会</li>
					<li>专项能力</li>					
				</ul>
			</div> 
		</p>
	</div>
	<div class="fl cas_anniu">
			<a href="javascript:$(queryForm).submit();" class="fl" style="margin-bottom:10px;width:70px;margin-left:130px;">查询</a>
		</div>
</div>
<div class="clear"></div>
<div class="bjs"></div>

<!-- 内容 -->
<div class="center" style="">
	<div class="center01">	
		<display:table name="Edition" requestURI="${ctx}/contentManage/editionManage.do?mode=list" id="p" class="mt10 table" pagesize="15">			
			<display:column title="序号" style="width:5%;">${p_rowNum}</display:column>
			<display:column title="页面名称"  style="width:20%;">${p.name}</display:column>
			<display:column title="栏目名称" style="width:25%;">${p.title}</display:column>
			<display:column title="内容类别" style="width:15%;">
				<c:if test="${p.type==1}">项目</c:if>
				<c:if test="${p.type==2}">课程</c:if>
				<c:if test="${p.type==3}">文章</c:if>
				<c:if test="${p.type==9}">名师</c:if>
				<c:if test="${p.type==10}">专委会</c:if>
				<c:if test="${p.type==8}">专项能力</c:if>
			</display:column>
			<display:column title="内容数量" style="width:10%;">${p.n_ContentSize}</display:column>
			<display:column title="操作" style="width:30%;">
				<c:if test="${p.type==1}">
					<a href="${ctx}/contentManage/editionManage.do?mode=view&id=${p.id}" style="margin-right:20px">查看绑定内容</a>
					<a href="${ctx}/contentManage/editionManage.do?mode=edit&id=${p.id}">绑定详细内容</a> 
				</c:if>
				<c:if test="${p.type==2}">
					<a href="${ctx}/contentManage/editionManage.do?mode=viewCV&id=${p.id}" style="margin-right:20px">查看绑定内容</a>
					<a href="${ctx}/contentManage/editionManage.do?mode=editCV&id=${p.id}">绑定详细内容</a>
				</c:if>
				<!-- 名师 -->
				<c:if test="${p.type==9}">
					<a href="${ctx}/expert/ExpertManage.do?mode=listView&id=${p.id}" style="margin-right:20px">查看绑定内容</a>
					<a href="${ctx}/expert/ExpertManage.do?mode=listEdit&id=${p.id}">绑定详细内容</a>
				</c:if>
				<!-- 专委会 -->
				<c:if test="${p.type==10}">
					<a href="${ctx}/expert/ExpertGroupManage.do?mode=viewZWH&id=${p.id}" style="margin-right:20px">查看绑定内容</a>
					<a href="${ctx}/expert/ExpertGroupManage.do?mode=editZWH&id=${p.id}">绑定详细内容</a>
				</c:if>
				<!-- 专项能力 -->
				<c:if test="${p.type==8}">
					<a href="${ctx}/contentManage/editionManage.do?mode=viewZXNL&id=${p.id}" style="margin-right:20px">查看绑定内容</a>
					<a href="${ctx}/contentManage/editionManage.do?mode=editZXNL&id=${p.id}">绑定详细内容</a>
				</c:if>
			</display:column>				
		</display:table>	
		<div class="clear"></div> 		
	</div>
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
	
	//当点击页面其他地方时关闭下拉框--taoliang
	$(document).click(function(){
		$('.list').hide('fast');
	});
	
	/* $('.queren').click(function(){
		$('.make02').hide();		
	}); */
});

</script>
</body>
</html>