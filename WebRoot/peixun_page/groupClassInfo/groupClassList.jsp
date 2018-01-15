<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
		<title>培训后台</title>
		<%@include file="/commons/taglibs.jsp"%>
	</head>
<%@include file="/peixun_page/top.jsp"%>
<body>
<div class="center">
	<form id="sfrm" name="cvForm" method="POST">
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl">
			<span>课程：</span>
			<input type="text" name="classParentName"/>
		</p>
		<p class="fl" style="margin-right:20px;">
			<span>单元：</span>
			<input type="text" name="className"/>
		</p>
		<div class="fl cas_anniu" style="margin-right:20px 0;">
			<a href="javascript:search()" class="fl" style="width:70px;margin-left:10px;">查询</a>
		</div>
		<div class="clear"></div>
	</div>
	</form>
</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
		<form id="listfrm" name = "cvForm" method="POST">
			<display:table name="list" requestURI="${ctx}/groupManage/groupClassInfoManage?type=list" id="p" pagesize="15" class="mt10 table">
					<display:column title="序号" style="width:5%">${p.id}</display:column>
					<display:column title="课程名称" style="width:15%">${p.classParentName}&nbsp;</display:column>
					<display:column title="单元名称" style="width:15%">${p.className}</display:column>
					<display:column title="启用状态" style="width:15%">
					  <c:if test="${p.state==1}">启用</c:if>
					  <c:if test="${p.state==0}">弃用</c:if>
					</display:column>
					<display:column title="创建日期" style="width:15%">${p.createDate}&nbsp;</display:column>
					<display:column title="操作" style="width:15%">
							<a href="javascript:void(0);" onclick="review('${p.classId}');" class="">预览</a>
							<a href="javascript:void(0);" onclick="edit('${p.classParentId}');" class="">编辑</a>
							<c:if test="${p.state==0}">
							  <a href="javascript:void(0);" class="">启用</a>
							</c:if>
							<a href="javascript:void(0);" class="">删除</a>
					</display:column>
			</display:table>
		</form>
		<div class="clear"></div> 	
	</div>
</div>
<script type="text/javascript">
	$(function(){
		
	});
	
	//预览课程内容
	function review(classId){
		location.href = "${ctx}/groupManage/groupClassInfoManage.do?type=review&reviewId="+classId;
	}
	
	//编辑课程
	function edit(classParentId){
		//跳转到编辑页面中
		$.ajax({
			async: false,
			type: 'POST',
			url: "${ctx}/groupManage/groupClassInfoManage.do",
			data : {type:'cz',ids:classParentId},
			dataType: 'json',
			success : function (data){
	           //解析JSON
	           var result = eval(data);
			}
		});										
	    //组课跳转
		location.href = "${ctx}/peixun_page/CVSet/CVManage/unionEdit2.jsp";	
	}
</script>
</body>
</html>