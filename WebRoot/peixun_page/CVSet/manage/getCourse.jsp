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

<form id="form1" action="${ctx}/getCourseList.do?method=getCourseList" method="post" >
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl">
			<span>学科：</span>
		</p>
			<input type="hidden" id="propIds" name="propIds" value="${propIds}"/>
			<input type="hidden" id="propNames" name="propNames" value="${propNames}"/>
			<input type="hidden" id="proId" name="proId" value="${proId}"/>
			<div class="duouan" id="propNames01"  style="margin-right:20px;">${propNames}</div>
			<c:if test="${cvType != null}">
			   <input type="hidden" id="cvType" name="cvType" value="${cvType}"/>
            </c:if>
            <%--<c:if test="${cvSetType != null}">--%>
			   <%--<input type="hidden" id="cvSetType" name="cvSetType" value="${cvSetType}"/>--%>
            <%--</c:if>--%>
			
		<p class="fl" style="margin-right:20px;">
			<span>创建人：</span>
			<input type="text" name="sname" value="${sname}"/>
		</p>
		<%--<p class="fl" style="margin-right:20px;">--%>
			<%--<span>项目名称：</span>--%>
			<%--<input type="text" name="item" value="${item}" />--%>
		<%--</p>--%>
		<p class="fl" style="margin-right:20px;">
			<span>课程名称</span>
			<input type="text" name="name" value="${name}" />
		</p>
		<p class="fl" style="margin-right:20px;">
			<span >课程类型：</span>
			<select name="cvSetType" id="cvSetType" class="fl select"  style="outline:none;">
				<option value="">全部</option>
				<option value="0" <c:if test="${cvSetType == 0}">selected</c:if>>点播</option>
				<option value="1" <c:if test="${cvSetType == 1}">selected</c:if>>面授</option>
				<option value="2" <c:if test="${cvSetType == 2}">selected</c:if>>直播</option>
			</select>
		</p>
		<p class="fl" style="margin-right:20px;">
			<span>状态：</span>
			<select name="CVSetStatus" id="CVSetStatus" class="fl select" style="outline: none;">
				<option value="0">全部</option>
				<option value="1" >未提交</option>
				<option value="2" >审核中</option>
				<option value="3" >审核合格</option>
				<option value="4" >审核不合格</option>
				<option value="5" >已发布</option>
				<option value="6" >已下线</option>
			</select>
		</p>
		<div class="clear"></div>
		<div class="fr cas_anniu" style="margin:20px 0;">
			<a href="${ctx}/CVSet/CVManage.do?mode=add" class="fl course_create" style="width:140px;margin-left:10px;">创建课程</a>
		</div>
		<div class="fl cas_anniu" style="margin:20px 0;">
			<a href="javascript:void(0)" onclick="SubmitFrom();" class="fl" style="width:70px;margin-left:10px;">查询</a>
		</div>
	</div>
	</form>
</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
		
		<display:table name="list" requestURI="${ctx}/getCourseList.do?method=getCourseList" id="p" pagesize="300" class="mt10 table">
					<display:column title="序号" style="width:3%">${p.id}</display:column>
					<display:column title="学科" style="width:5%">
						<c:forEach items="${p.courseList}" varStatus="stauts" var="prop">
						${prop.name}&nbsp;
						</c:forEach>
					</display:column>
					<display:column title="课程名称" style="width:15%">${p.name}</display:column>
					<display:column title="课程编号" style="width:10%">${p.serial_number}</display:column>
					<display:column title="引用项目" style="width:6%">${p.cvsetName}</display:column>
					<display:column title="课程类型" style="width:5%">
						<c:if test="${p.cv_type == '0'}">点播</c:if>
						<c:if test="${p.cv_type == '1'}">面授</c:if>
						<c:if test="${p.cv_type == '2'}">直播</c:if>
					</display:column>
			        <display:column title="课程标签" style="width:5%">
						<c:if test="${p.brand == '1'}">病例</c:if>
						<c:if test="${p.brand == '3'}">VR</c:if>
						<c:if test="${p.brand == '4'}">名师课程</c:if>
						<c:if test="${p.brand == '5'}">三维动画</c:if>
						<c:if test="${p.brand == '6'}">其他</c:if>
					</display:column>
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
					<display:column title="创建时间" style="width:10%"></display:column>
					<display:column title="操作" style="width:10%">
							<button class="clone_btn btn_blue" type="button" onclick="clone('${p.id}','${proId }');">克隆</button>
					</display:column>
			</display:table>
		<div class="clear"></div> 		
	</div>
</div>

<script type="text/javascript">
/* $(function(){
	//select menu
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "lc_left0" + menuindex;
		$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");

	var clone_href = window.localStorage.getItem("subject_clone");
	$(".course_create").click(function(){
		window.localStorage.setItem("project_course","1");
	});

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
		
		
		
		
}); */

/*
 *@auth   ZJG
 *@time   2016-12-27
 *方法说明 课程克隆
 */
function clone(id,proId){
	var url = "${ctx}/CVSet/CVUnitAdd.do";
	var param = "mode=getCVInfoByAjax&id="+id+"&proId="+proId;
	$.ajax({
		type:'Post',
		url:url,
		data:param,
		dataType:'json',
		success:function(result){
			var obj = result.info;
			window.opener.display_union(obj);
			window.opener.increCount();			
			window.close();			
		}			
	});
}


/**
 * 2017-02-28
   学科弹出框样式加载
 
 
 */
$(function(){
	var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
	var submenu = "lc_left0" + menuindex;
	$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
	$('.'+submenu+'').show();
	$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
	
//选择目录弹出框
	$('#propNames01').click(function(){
		initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
		$('.att_make01').show();
	});
	$('.cas_anniu_4,.bjt_kt').click(function(){
		$('.att_make01').hide();
	});
	
});

function SubmitFrom(){
	var name = $('#propNames01').text();
	$('#propNames').val($('#propNames01').text());
     
	$('#form1').submit();
}
</script>
</body>
</html>