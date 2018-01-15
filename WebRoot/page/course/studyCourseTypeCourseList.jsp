<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>课程管理</title>
		<%@include file="/commons/taglibs.jsp"%>
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/button.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="${ctx}/js/UI/jquery-1.8.0.min.js"></script>
		
		<style media="all" type="text/css">
			@import url("${ctx}/css/displaytag/site.css");
			@import url("${ctx}/css/displaytag/screen.css");
		</style>
	</head>
	<body>
		<div style="padding-left:12px;width:99%;">
			<form action="" method="post" id="formA" name="formA">
			<input type="hidden" id="formAId" name="curTypeId" value="${param.curTypeId}" />
			<table class="gridtable" cellpadding="1" cellspacing="0" border="0" width="100%">
				<tr>
					<td class="row">课程名称:
						<input type="text" id="studyCourseName" name="studyCourseName" maxlength="50" value="${param['studyCourseName']}" />
					</td>
					<td class="row">主讲老师:
						<input type="text" id="teacher" name="teacher" maxlength="50" value="${param['teacher']}" />
					</td>
				</tr>
				<tr>
					<td class="row">关键字:
						<input type="text" id="keyWord" name="keyWord" maxlength="50" value="${param['keyWord']}" />
					</td>
					<td class="row">类型:
						<select id="studyCourseType" name="studyCourseType">
						    <option value="" >请选择</option>
							<option value="1" <c:if test="${param['studyCourseType'] eq 1}">selected</c:if>>单屏课程</option>
							<option value="2" <c:if test="${param['studyCourseType'] eq 2}">selected</c:if>>三屏课程</option>
							<option value="3" <c:if test="${param['studyCourseType'] eq 3}">selected</c:if>>flash</option>
							<option value="4" <c:if test="${param['studyCourseType'] eq 4}">selected</c:if>>文档</option>
							<option value="5" <c:if test="${param['studyCourseType'] eq 5}">selected</c:if>>混合</option>
						</select>
						<input type="button" class="btn_03s" value="查 询" onclick="selectCourse();" />
						<input type="button" class="btn_03s" value="添加到分类" onclick="addTo();" />
					</td>
				</tr>
			</table>
			</form>
			<br />
		</div>
		<div>
			<center>
				<form action="" id="formB" name="formB" method="post">
				<input type="hidden" id="method" name="method" />
				<input type="hidden" id="courseId" name="courseId" />
				<input type="hidden" id="formBId" name="curTypeId" value="${param.curTypeId}" />
				<display:table id="row" name="page" requestURI="${ctx}/course/studyCourseTypeCourseList.do" style="font-size:12px;width:98%;" class="ITS">
					<display:caption>用户查询结果</display:caption>

					<display:column title="<input type='checkbox' id='chckAll' name='chckAll' onclick='checkAll(this);'>" style="text-align:center;width:2%">
						<input type="checkbox" id="curIds" name="curIds" value="${row.id}" />
					</display:column>
					<display:column property="studyCourseName" title="课程名称" style="text-align:center" />
					<display:column property="coursePractice" title="练习（套）" style="text-align:center" />
					<display:column property="classHours" title="学时" style="text-align:center" />
					<display:column title="类型" style="text-align:center">
						<c:choose>
							<c:when test="${row.studyCourseType eq 1}">
								单屏课程
							</c:when>
							<c:when test="${row.studyCourseType eq 2}">
								三屏课程
							</c:when>
							<c:when test="${row.studyCourseType eq 3}">
								flash
							</c:when>
							<c:when test="${row.studyCourseType eq 4}">
								文档课程
							</c:when>
							<c:when test="${row.studyCourseType eq 5}">
								混合课程
							</c:when>
							<c:otherwise>
								&nbsp;
							</c:otherwise>
						</c:choose>
					</display:column>
					<display:column property="summary" title="摘要" style="text-align:center" />
					<display:column title="操作" style="text-align:center;width:11%">
						<input type="button" class="btn_04s" value="预览" />
					</display:column>
				</display:table>
                <input type="hidden" name="org.apache.struts.taglib.html.TOKEN"  value="${sessionScope['org.apache.struts.action.TOKEN']}" />
				</form>
			</center>
		</div>
<script type="text/javascript">
	<c:if test="${MESSAGE != null && MESSAGE != ''}">
        alert('${MESSAGE}');
        <c:set var="MESSAGE" scope="session" value=""></c:set>
	</c:if>
</script>
		<script type="text/javascript">
			function selectCourse(){
				document.formA.action = "${ctx}/course/studyCourseTypeCourseList.do" ;
				document.formA.submit() ;
			}

			function addTo(){
			    if($("input[name='curIds']:checked").length == 0){
                    alert('请选择需要添加的课程!');
                    return ;
                }

		        document.formB.action = "${ctx}/course/studyCourseTypeCourseSave.do" ;
				document.formB.submit() ;
			}

			function checkAll(element){
				$("input[name='curIds']").each(function(){
					$(this).attr("checked", element.checked) ;
				});
			}
		</script>
	</body>
</html>