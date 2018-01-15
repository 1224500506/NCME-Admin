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


//[批量删除] 
function delBatch() {
	var abilityId = '${ability.id }';
	var courseIds = $("input[type='checkbox'][name='courseIds']:checked");
	if(courseIds.length == 0){
		alert("请先选择课程!");
	    return;
	}
	if(confirm('确认取消关联吗?')){
		var p = {'abilityId':abilityId,'courseIds': courseIds};
		$.post("${ctx }/system/SystemIndustryAbility.do?method=deleteAbilityCourses", p,
  			function(data){
  				if(data >0){
  					alert("取消关联成功!");
  				}
  				else{
  					alert("由于网络原因,取消不成功,请稍候再试!");
  				}
  				location = location;
  		});
	}		
}

//[查询] 
function query() {
		document.forms[0].submit();
}
//[取消关联] 
function deleteCourse(abilityId, courseId) {
	if(confirm('确认取消关联吗?')){
		var p = {'abilityId':abilityId,'courseId': courseId};
		$.post("${ctx }/system/SystemIndustryAbility.do?method=deleteAbilityCourses", p,
  			function(data){
  				if(data >0){
  					alert("取消关联成功!");
  				}
  				else{
  					alert("由于网络原因,取消不成功,请稍候再试!");
  				}
  				location = location;
  		});
	}
	
}

//关联课程
function getCourse(industryId, abilityId){
	window.location.href = "${ctx}/system/SystemIndustryAbility.do?method=otherCourseList&onePage=1&abilityId="+abilityId+"&industryId="+industryId;
}


</script>
	</head>
	<body bgcolor="#E7E7E7">
		
		<div style="margin: auto; text-align: right;">
		<center>
		
		<form name="queryForm" action="${ctx}/system/SystemIndustryAbility.do" method="post">
		<input type="hidden" id="method" name="method" value="courseList" />
		<input type="hidden" id="abilityId" name="abilityId" value="${ability.id }" />
		<input type="hidden" id="industryId" name="industryId" value="${industryId }" />
		<table style="font-size:12px;width:95%;text-align: center;" class="ITS" >
		<tr>
			<td>&nbsp;</td><td>&nbsp;</td>
			<td><button type="button" class="btn_03s" onclick="getCourse('${industryId }','${ability.id }')" >选择课程</button></td>
			<td><button type="button" class="btn_03s" onclick="window.location.href='${ctx}/system/SystemIndustryAbility.do?method=list&industryId=${industryId }'" >返回</button></td></td> 
		</tr>
		<tr>
			<td>关键词&nbsp;<input name="courseName" id="courseName" value="${courseName }"/></td>
			<td>负责人&nbsp;<input name="teacher" id="teacher" value="${teacher }"/></td>
			<td>来源&nbsp;<input name="teacher" id="teacher" /></td> 
			<td>
			<button type="button" class="btn_03s" onclick="javascript:query();" >搜索</button>
			</td>
		</tr>
		</table>
		
		<br/>
			
				<display:table requestURI="${ctx}/system/SystemIndustryAbility.do?method=courseList"
				 id="abilityCourse" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:95%;" class="ITS" keepStatus="true">
					<display:caption><a href="${ctx }/system/SystemIndustry.do?method=list">回到行业岗位 </a>&nbsp;   能力[${ability.abilityName }]  已经关联的课程  <span style="color:red">${meg}</span></display:caption>
					<display:column title='<input type="checkbox" name="courseIds" id="courseIds" value="checkbox" onclick="select_all(this)" />全选' style="text-align:center">
					<input type="checkbox" name="courseIds" value="${abilityCourse.id }"/>
					</display:column>
					<display:column title="序号" style="text-align:center">
						${abilityCourse_rowNum}
					</display:column>
					<display:column property="id" title="ID" style="text-align:center"/>
					<display:column property="studyCourseName" title="课程名称" style="text-align:center"/>
					<display:column property="studyCourseName" title="来源" style="text-align:center"/>
					<display:column property="teacher" title="主讲人" style="text-align:center"/>
					<display:column title="操作" media="html" style="text-align:center">
						<a href="javascript:deleteCourse('${ability.id }','${abilityCourse.id }')" >取消关联</a>
					</display:column>
				</display:table>
			</center>
		</div>
		</form>
	 		
	</body>
</html>
