<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
</style>
 <script type="text/javascript">
	 //[查询] 
	 function query() {
	 		document.forms[0].submit();
	 }
	 
	 //预览
	 function view(id){
		 alert("暂无预览");
	 }
	 
	 //返回
	 function gotoback(){
		 var url = "${ctx}/system/SystemIndustryAbility.do?method=courseList&onePage=1&industryId=${industryId}&abilityId=${ability.id}";
		 window.location.href = url;
	 }
	 
	 //选择课程
	 function selectCourses(){
			var courseIds = $("input[type='checkbox'][name='courseIds']:checked");
			if(courseIds.length == 0){
				alert("请先选择课程!");
			    return;
			}
			var abilityId = $("#abilityId").val();
			var selectedIds = '';
			if(courseIds != '' && courseIds.length >0 ){
				for(var i=0; i<courseIds.length; i++){
					selectedIds += courseIds[i].value + ',';
				}
			}
			var p = {'selectedIds':selectedIds,'abilityId':abilityId};
			$.post("${ctx }/system/SystemIndustryAbility.do?method=saveAbilityCourses", p,
	  			function(data){
	  				if(data >0){
	  					alert("课程关联成功!");
	  				}
	  				else{
	  					alert("由于网络原因,课程关联不成功,请稍候再试!");
	  				}
	  				window.location.reload(true);
	  		});
	 }
</script>
	</head>
	<body bgcolor="#E7E7E7">
		<div style="margin: auto; text-align: right;">
		<center>
		
		<form name="queryForm" id="queryForm" action="${ctx}/system/SystemIndustryAbility.do" method="post">
		<input type="hidden" id="method" name="method" value="otherCourseList" />
		<input type="hidden" id="abilityId" name="abilityId" value="${ability.id }" />
		<input type="hidden" id="industryId" name="industryId" value="${industryId }" />
		<table style="font-size:12px;width:95%;text-align: center;" class="ITS" >
		<tr>
			<td>&nbsp;</td><td>&nbsp;</td>
			<td><button type="button" class="btn_03s" onclick="selectCourses()" >进行授权</button></td>
			<td><button type="button" class="btn_03s" onclick="gotoback()" >返回</button></td></td> 
		</tr>
		<tr>
			<td>课程名称&nbsp;<input name="courseName" id="courseName" value="${courseName }"/></td>
			<td>授课老师&nbsp;<input name="teacher" id="teacher" value="${teacher }"/></td>
			<!-- <td>来源&nbsp;<input name="teacher" id="teacher" /></td>  -->
			<td>
			<button type="button" class="btn_03s" onclick="javascript:query();" >搜索</button>
			</td>
		</tr>
		</table>
		
		<br/>
			
				<display:table requestURI="${ctx}/system/SystemIndustryAbility.do?method=otherCourseList"
				 id="iStudyCourse" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:95%;" class="ITS" keepStatus="true">
					<display:caption>能力[${ability.abilityName }]&nbsp;  未关联的课程  <span style="color:red">${meg}</span></display:caption>
					<display:column title="序号" style="text-align:center">
						${iStudyCourse_rowNum}
					</display:column>
					<display:column property="id" title="ID" style="text-align:center" sortable="true"/>
					<display:column property="studyCourseName" title="课程名称" style="text-align:center"/>
					<display:column property="summary" title="摘要" style="text-align:center"/>
					<display:column property="teacher" title="主讲人" style="text-align:center"/>
					<display:column title='<input type="checkbox" id="courseIds" value="checkbox" onclick="select_all(this)" />全选' style="text-align:center">
						<input type="checkbox" name="courseIds" value="${iStudyCourse.id }_${iStudyCourse.creditType }_${iStudyCourse.creditNum }"/>
					</display:column>
					
				</display:table>
			</center>
		</div>
		</form>
	 		
	</body>
</html>
