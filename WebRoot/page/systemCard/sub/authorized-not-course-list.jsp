<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base target="_self" />
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

//授权
function addSelect(){
	var cardTypeId = '${cardTypeId}';
	var courseIds = $("input[type='checkbox'][name='courseIds']:checked");
	if(courseIds =='' || courseIds.length == 0){
		alert("请选择要授权的课程!");
	    return;
	}
	
	if(confirm("本次只授权您所选中的课程！\r\n未选中的将不予授权！")){
	
		var selectedIds = '';
		for(var i=0; i<courseIds.length; i++){
			selectedIds += courseIds[i].value + ',';
		}
		var retdata = 1;
		var retdata = $.ajax({url: "${ctx }/system/SystemCardType.do?method=saveSystemPaycardCourse&cardTypeId="
			+ cardTypeId+"&courseIds="+selectedIds, async: false}).responseText;
	
		if(retdata>0){
			alert("卡类型与课程授权成功！");
		}else{
			alert("由于网络原因,卡类型与课程授权不成功,请稍候再试!");
		}
		window.location.reload(true);
	}
}

//授权所有
function addAll(){
	if(confirm("您确认授权该分类下所有课程？\r\n不用亲自选择，直接全部授权！")){
		//var cardTypeId = '${cardTypeId}';
		//var courseType = '${courseType}';
		//var creditYear = $("#creditYear").val();
		//var data = 1;
		//var retdata = $.ajax({url: "${ctx }/system/SystemCardType.do?method=saveSystemPaycardCourse&cardTypeId="
		//		+ cardTypeId+"&courseType="+courseType+"&creditYear="+creditYear, async: false}).responseText;
		 $.post("${ctx }/system/SystemCardType.do?method=saveSystemPaycardCourseByCourseType", $("#queryForm").serialize(),function(data) {
			if(data>0){
				alert("卡类型与课程授权成功！");
			}else{
				alert("由于网络原因,卡类型与课程授权不成功,请稍候再试!");
			}
			window.location.reload(true);
		});
	}
}

</script>
	</head>
	<body bgcolor="#E7E7E7">
		
		<div style="margin: auto; text-align: right;">
		<center>
		
		<form name="queryForm" id="queryForm" action="${ctx}/system/SystemCardType.do?method=getUnCreditCourseList" method="post">
		<input type="hidden" id="courseType" name="courseType" value="${courseType }" />
		<input type="hidden" id="cardTypeId" name="cardTypeId" value="${cardTypeId }" />
		<table style="font-size:12px;width:95%;text-align: center;" class="ITS" >
		<tr>
			<td colspan="3">
				
			</td>
		</tr>
		<tr>
			<td align="left" colspan="3">授权时间
				<select id="creditYear" name="creditYear">
					<option value="2013" <c:if test="${creditYear eq '2013' }">selected</c:if> >2013</option>
					<option value="2014" <c:if test="${creditYear eq '2014' }">selected</c:if> >2014</option>
					<option value="2015" <c:if test="${creditYear eq '2015' }">selected</c:if> >2015</option>
					<option value="2016" <c:if test="${creditYear eq '2016' }">selected</c:if> >2016</option>
					<option value="2017" <c:if test="${creditYear eq '2017' }">selected</c:if> >2017</option>
				</select>
				<!-- &nbsp;&nbsp;&nbsp;&nbsp;授权地区:
				<select id="organId" name="organId">
					<option value="2">北京安监局</option>
				</select> -->
			</td>
		</tr>
		<tr>
			<td align="left">课程名称&nbsp;<input name="studyCourseName" id="studyCourseName" size="25" value="${studyCourseName }"/></td>
			<td align="left"><button type="button" class="btn_03s" <c:if test="${fn:length(page.list) ==0 }">disabled title="暂无数据"</c:if> onclick="javascript:query();" >搜索</button></td>
			<td>
				&nbsp;<button type="button" class="btn_03s" <c:if test="${fn:length(page.list) ==0 }">disabled title="暂无数据"</c:if> onclick="javascript:addSelect();" >授权所选</button>&nbsp;&nbsp;&nbsp;&nbsp;
				<%-- <button type="button" class="btn_03s" <c:if test="${fn:length(page.list) ==0 }">disabled title="暂无数据"</c:if> onclick="javascript:addAll();" >授权所有</button> --%>
			</td>
		</tr>
		</table>
		
		<br/>
				<display:table requestURI="${ctx}/system/SystemCardType.do?method=getUnCreditCourseList"
				 id="creditStudyCourse" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:95%;" class="ITS" keepStatus="true">
					<display:column title='<input type="checkbox" id="courseIds" value="checkbox" onclick="select_all(this)" />全选' style="text-align:center">
					<input type="checkbox" name="courseIds" value="${creditStudyCourse.id }"/>
					</display:column>
					<display:column title="序号" style="text-align:center">
						${creditStudyCourse_rowNum}
					</display:column>
					<display:column property="id" title="ID" style="text-align:center"/>
					<display:column property="studyCourseName" title="课程名称" style="text-align:center" sortable="true"/>
					<display:column property="summary" title="摘要" style="text-align:center" sortable="true"/>
					<display:column property="teacher" title="主讲老师" style="text-align:center" sortable="true"/>
					<display:column property="createDate" title="创建时间" style="text-align:center" sortable="true"/>
					<display:column property="courseTypeName" title="课程目录" style="text-align:center" sortable="true"/>
					
				</display:table>
			</center>
		</div>
		</form>
	 		
	</body>
</html>
