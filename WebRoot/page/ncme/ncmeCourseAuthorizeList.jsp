<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>

<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
	#bgdiv {
		position: absolute;
		top: 0px;
		left: 0px;
		width: 100%;
		height: 100%;
		background-color: #AAAAAA;
		z-index: 10;
		filter: alpha(opacity = 80);
		opacity: 0.8;
	}
</style>
</head>
<jsp:include page="/page/tableColor.jsp"></jsp:include>
<body style="">
<form action="${ctx}/ncme/ncmeCourseAuthorizeList.do" method="post" name="fmx" id="fmx">
<table class="gridtable" width="100%" cellpadding="1" cellspacing="0" border="0">
	<tr>
		<td class="row">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度:
			<!-- <input type="text" name="creditYear" id="creditYear" maxlength="4" value="<c:if test="${param.creditYear != null}">${param.creditYear}</c:if><c:if test="${param.creditYear == null}"><fmt:formatDate value="${now}" pattern="yyyy"/></c:if>" > -->
			<select name="creditYear" id="creditYear">
				<option value="2013" <c:if test="${currentYear eq '2013' }">selected</c:if> >2013</option>
				<option value="2014" <c:if test="${currentYear eq '2014' }">selected</c:if> >2014</option>
				<option value="2015" <c:if test="${currentYear eq '2015' }">selected</c:if> >2015</option>
				<option value="2016" <c:if test="${currentYear eq '2016' }">selected</c:if> >2016</option>
				<option value="2017" <c:if test="${currentYear eq '2017' }">selected</c:if> >2017</option>
			</select>
		</td>
		
		<td class="row">授权级别:
		    <select id="creditType" name="creditType">
		      <option value="">全部
		      <c:forEach items="${ncmeCreditTypeList}" var="list">
		        <option value="${list.creditType}" <c:if test="${param.creditType eq  list.creditType}">selected</c:if> >${list.creditName}
		      </c:forEach>
		    </select>
		</td>
		<td class="row">
			<input type="button"   size="100px" value="课件播放时间设置" onclick="setPlayTime()"/>
		</td>	
		<td class="row">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="row">课程名称:
			<input type="text" name="studyCourseName" id="studyCourseName" maxlength="100" value="${param.studyCourseName}" size="40">
		</td>
		<!-- 
		<td class="row">机构名称:
			<select id="organId" name="organId">
			  <option value="">全部
		      <c:forEach items="${ncmeAdminOrganList}" var="list">
		        <option value="${list.organId}" <c:if test="${param.organId eq  list.organId}">selected</c:if> >${list.name}
		      </c:forEach>
			</select>
		</td>
		 -->
		 <td class="row">站点名称:
			<select id="siteId" name="siteId">
			  <option value="">全部
		      <c:forEach items="${systemSiteList}" var="list">
		        <option value="${list.id}" <c:if test="${param.siteId eq  list.id}">selected</c:if> >${list.domainName}
		      </c:forEach>
			</select>
		</td>
		<td class="row">授权日期:
		    从
			<input type="text" name="startDate" id="startDate" value="" onClick="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\');}'})" readonly="readonly" size="15">
	            到
			<input type="text" name="endDate" id="endDate" value=""  onClick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\');}'})" readonly="readonly" size="15"  />
		</td>
		<td class="row">
			&nbsp;
		</td>	
	</tr>	
	<tr>
		<td class="row">
		</td>
		<td class="row">
		</td>
		<td class="row">
		</td>
		<td class="row">
			<input type="submit" class="btn_03s" value="查询授权" />
			<input type="button" class="btn_03s" value="新增授权" onclick="javascript:window.location = '${ctx}/ncme/ncmeCourseAuthorizeAddTo.do'" />
			<input type="button" class="btn_03s" value="删除授权" onclick="javascript:deleteAuth();" />
		</td>	
	</tr>
</table>
</form>
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<form id="listfm" name="listfm" method="post" action="${ctx}/ncme/ncmeCourseAuthorizeDelete.do">
	<input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="${sessionScope['org.apache.struts.action.TOKEN']}" />
	<display:table name="${pageList}" requestURI="${ctx}/ncme/ncmeCourseAuthorizeList.do" id="qt" style="width:100%;" class="its"
	 decorator="com.hys.exam.displaytag.TableOverOutWrapper" >
		<display:column title="<input type='checkbox' name='chkall' id='chkall' value='' onclick='CheckAll(this.form);'>" style="width:2%;text-align:center">
			<input type="checkbox" name="ids" value="${qt.creditYear}_${qt.courseId}_${qt.siteId}" id="qcid"/>
		</display:column>
		<display:column title="授权年份" property="creditYear" style="width:30px;text-align:center"></display:column>
		<display:column title="站点" property="domainName" style="width:150px;text-align:center"></display:column>
		<display:column title="课程名称" property="studyCourseName" style="width:300px;text-align:center"></display:column>
		<display:column title="发布状态" style="width:50;text-align:center">
		  <c:if test="${qt.status eq 0}">未发布</c:if>
		  <c:if test="${qt.status eq 1}">已发布</c:if>
		  <c:if test="${qt.status eq 2}">已下线</c:if>
		  <c:if test="${qt.status eq 3}">已作废</c:if>
		</display:column>
		<display:column title="授权类别" property="courseTypeNames" style="width:200px;text-align:center"></display:column>
		<display:column title="授权级别" property="creditName" style="width:50px;text-align:center"></display:column>
		<display:column title="授权学时" property="creditNum" style="width:30px;text-align:center"></display:column>
		<display:column title="授权日期" style="width:130px;text-align:center">
		  	<fmt:formatDate value="${qt.creditDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</display:column>
		<display:column title="操作" media="html" style="text-align:center">
			<a href="javascript:updateCredit('${qt.creditYear}','${qt.courseId}','${qt.siteId}','${qt.domainName }')" >修改授权</a>&nbsp;&nbsp;
			<a href="javascript:editPropShow('${qt.creditYear}','${qt.courseId}','${qt.siteId}','${qt.courseType }','${qt.creditDate }')" >修改授权时间</a>&nbsp;&nbsp;
		</display:column>
	</display:table>
    <input type="hidden" name="org.apache.struts.taglib.html.TOKEN"  value="${sessionScope['org.apache.struts.action.TOKEN']}" />
	</form>
</table>

	<div id="editProp" style="border: #036 solid; padding-top: 30px; width: 500px; height: 200px; position: absolute; left: 450px; top: 150px; background-color: #FFFFFF; display: none; z-index: 20;">
		<ul>
			
			<li>
				原时间：<input type="text" id="old_credit_date" size="40" readonly="readonly"/><br/><br/>
				新时间：<input type="text" id="new_credit_date" size="40" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" /><br/><br/>
				<input type="hidden" id="old_creditYear" name="old_creditYear" />
				<input type="hidden" id="old_courseId" name="old_courseId" />
				<input type="hidden" id="old_siteId" name="old_siteId" />
				<input type="hidden" id="old_courseType" name="old_courseType" />
			</li>
		</ul>
		<center>
			<input type="button" name="saverr" value="保存" onclick="doEditCreditDate();" />&nbsp;&nbsp;&nbsp;
			<input type="button" name="saverr" value="取消" onclick="hiddenPropShow();" />
		</center>
	</div>	
	<div id="bgdiv" style="display: none;"></div>
	
<script type="text/javascript">
	<c:if test="${MESSAGE != null && MESSAGE != ''}">
        alert('${MESSAGE}');
        <c:set var="MESSAGE" scope="session" value=""></c:set>
	</c:if>
</script>
</body>
</html>
<script type="text/javascript">
function deleteAuth(){
  if($("input[name='ids']:checked").length == 0){
    alert('请选择需要删除的授权!');
    return ;
  }

  if(confirm("确认删除!")){
    $("#listfm").submit();
  }
}

function checkAll(element){
	$("input[name='ids']").each(function(){
		$(this).attr("checked", element.checked) ;
	});
}

function setStartTime(){
	if($("input[name='endTime']").val() == ''){
		WdatePicker({dateFmt:'yyyy-MM-dd'})
	}else{
		WdatePicker({
			dateFmt:'yyyy-MM-dd',
			maxDate:'#F{$dp.$D(\'endTime\');}'
		});
	}
}
function setEndTime(){
	if($("input[name='startTime']").val() == ''){
	    WdatePicker({dateFmt:'yyyy-MM-dd'})
	}else{
	    WdatePicker({
			dateFmt:'yyyy-MM-dd',
			minDate:'#F{$dp.$D(\'startTime\');}'
		});
	}
  }

function updateCredit(creditYear, courseId, siteId, domainName){
	window.location.href="${ctx}/ncme/ncmeCourseAuthorizeUpdate.do?method=toUpdate&creditYear="
			+ creditYear+"&courseId="+courseId+"&siteId="+siteId+"&domainName="+domainName;
}

//设置课件播放时间
function setPlayTime(){
	window.location.href="${ctx}/ncme/ncmeCourseSetting.do?method=edit";
}

function editPropShow(creditYear,courseId,siteId,courseType,credit_date){
	document.getElementById('editProp').style.display='block';
	document.getElementById('bgdiv').style.display='block';
	$("#old_credit_date").val(credit_date);
	$("#new_credit_date").val(credit_date);
	
	$("#old_creditYear").val(creditYear);
	$("#old_courseId").val(courseId);
	$("#old_siteId").val(siteId);
	$("#old_courseType").val(courseType);
}

function hiddenPropShow(){
	document.getElementById('editProp').style.display='none';
	document.getElementById('bgdiv').style.display='none';
	$("#credit_date").val();
}

function doEditCreditDate(){
	var new_credit_date = $("#new_credit_date").val();
	var creditYear = $("#old_creditYear").val();
	var courseId = $("#old_courseId").val();
	var siteId = $("#old_siteId").val();
	var courseType = $("#old_courseType").val();
	
	if(new_credit_date == ''){
		alert("新的授权时间不能为空!");
		return;
	}
	
	var p = {'creditYear':creditYear,'courseId':courseId,'siteId':siteId,'courseType':courseType,'new_credit_date':new_credit_date};
	$.post("${ctx}/ncme/ncmeCourseAuthorizeUpdate.do?method=updateDate", p,
			function(data){
				if(data >0){
					alert("更新成功!");
				}
				else{
					alert("由于网络原因,更新不成功,请稍候再试!");
				}
				$("#fmx").submit();
		});
}

</script>
