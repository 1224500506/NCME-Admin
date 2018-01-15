<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
		<title>培训后台</title>
		<%@include file="/commons/taglibs.jsp"%>
		<%@include file="/peixun_page/top.jsp"%>
	</head>

<div class="center">									
	<form id="sfrm" name="cvForm" method="POST" action="${ctx}/CVSet/CVUnitAdd.do?mode=clone&classId=${classId}">
		<div class="tiaojian" style="min-height:40px;">
			<p class="fl" style="margin-right:20px;">
				<span>学科：</span>	
				<input type="hidden" id="propIds" name="propIds" value="${propIds}"/>
				<input type="hidden" id="propNames" name="propNames" value="${propNames}"/>
				<div class="duouan" id="propNames01">${propNames}</div>
			</p>
			<p class="fl" style="margin-right:20px;">
				<span>创建人：</span>
				<input type="text" name="sname" value="${sname}"/>
			</p>
			<p class="fl" style="margin-right:20px;">
				<span>课程名称：</span>
				<input type="text" name="name" value="${name}" />
			</p>
			<!-- 
			<p class="fl" style="margin-right:20px;">
				<span>状态：</span>
			</p>
			<div class="fl select">
				<div class="tik_position">
					<b class="ml5">全部</b>
					<p class="position01"><i class="bjt10"></i></p>
				</div>
				<ul class="fl list" style="display: none;">
					<li>待提交</li>
					<li>待审核</li>
					<li>审核通过</li>
					<li>审核未通过</li>
					<li>已发布</li>
					<li>已下线</li>
					<li>已过期</li>
				</ul>
			</div>
			 -->
			<div class="fl cas_anniu" style="margin-right:20px 0;">
				<a href="javascript:search()" class="fl" style="width:70px;margin-left:10px;">查询</a>
			</div>
			<div class="clear"></div>
			<div class="fr cas_anniu" style="margin:20px 0;">
				<a href="${ctx}/CVSet/CVManage.do?mode=add" class="fl course_create" style="width:140px;margin-left:10px;">创建课程</a>
			</div>
		</div>
	</form>
</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
		<!-- <div class="mt5 kit1_tiaojia">
			<div class="fr cas_anniu">
				<a href="kaoshi01.html" class="fl" style="width:80px;">新增</a>
			</div>
		</div>
		<div class="clear"></div> -->
		<display:table name="clone" requestURI="${ctx}/CVSet/CVUnitAdd.do?mode=clone&classId=${classId}" id="p" class="mt10 table" 
		               cellpadding="0" cellspacing="0" partialList="true" keepStatus="true"
		               excludedParams="method" size="${clone.fullListSize}" pagesize="${clone.objectsPerPage}" >
					<display:column title="序号" style="width:3%">${p.id}</display:column>
					<display:column title="学科" style="width:5%">
						<c:forEach items="${p.courseList}" varStatus="stauts" var="prop">
						${prop.name}&nbsp;
						</c:forEach>
					</display:column>
					<display:column title="课程名称" style="width:15%">${p.name}</display:column>
					<display:column title="课程编号" style="width:10%">${p.serial_number}</display:column>
					<display:column title="引用项目" style="width:6%"></display:column>
					<display:column title="课程标签" style="width:5%">${p.brand}</display:column>
					<display:column title="授课教师" style="width:10%">
						<c:forEach items="${p.teacherList}" varStatus="status" var="teacher">
							${teacher.name}&nbsp;
						</c:forEach>
					</display:column>
					<display:column title="示教教师" style="width:8%">
						<c:forEach items="${p.otherTeacherList}" varStatus="status" var="other">
							${other.name}&nbsp;
						</c:forEach>
					</display:column>
					<display:column title="创建时间" style="width:10%">${p.create_date}</display:column>
					<display:column title="操作" style="width:10%">
							<button class="clone_btn btn_blue" type="button" onclick="javascript:cloneCopy('${p.id }','${classId}');">克隆</button>
					</display:column>
			</display:table>
		<div class="clear"></div> 
		<!-- 分页 -->
		
	</div>
</div>

<script type="text/javascript">
function search(){
	var name = $('#propNames01').text();
	$('#propNames').val($('#propNames01').text());
     
	$('#sfrm').submit();
}
$('#propNames01').click(function(){
	initPropList("学科","${ctx}/propManage/getPropListAjax.do",1,0,5,3,$('#propIds'),$('#propNames01'));
	$('.att_make01').show();
});
$(function(){
	var clone_href = window.localStorage.getItem("subject_clone");
	$(".course_create").click(function(){
		window.localStorage.setItem("project_course","1");
	});

	/*  $(".clone_btn").click(function(){
		window.localStorage.setItem("add_course","1");
		window.localStorage.removeItem("subject_clone");
		if (clone_href != null && clone_href == 1){
			window.location.href = "${ctx}/CVSet/CVManage.do?mode=add";
		}else{
			cloneCopy(this.id);
			
		}
	}); */ 
//选择目录弹出框
		$('.duouan').click(function(){
			$('.att_make01').show();
		});
		$('.bjt_kt,.cas_anniu_4').click(function(){
			$('.att_make01').hide();
			$('.att_make02').hide();
			$('.att_make03').hide();
			$('.att_make04').hide();
		});
		//二级
		$('.attr_xueke').click(function(){
			$('.att_make01').hide();
			$('.att_make02').show();
		});
		$('.attri01').click(function(){
			$('.att_make02').hide();
			$('.att_make01').show();
			$('.att_make03').hide();
			$('.att_make04').hide();
			$('.att_make05').hide();
		});
		//三级
		$('.attr_xueke02').click(function(){
			$('.att_make02').hide();
			$('.att_make03').show();
		});
		$('.attri02').click(function(){
			$('.att_make02').show();
			$('.att_make03').hide();
			$('.att_make04').hide();
			$('.att_make05').hide();
		});
		//调整
		$('.tiaozheng').click(function(){
			$('.make02').show();
		});
		$('.queren').click(function(){
			$('.make02').hide();
		});
});

function cloneCopy(_id,_classId){
	$.ajax({
		type:'POST',
		url:'${ctx}/CVSet/CVUnitAdd.do',
		data:'mode=cloneUnitList&id=' + _id+"&classId="+_classId,
		dataType:'json',
		success:function(result){
			if (result != '') {
				window.opener.refresh_left_cont(result);
				window.close(); 				
			}
		}
		
	});
		
}




</script>
</body>
</html>