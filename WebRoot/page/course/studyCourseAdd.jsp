<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<%@include file="/commons/taglibs.jsp"%>
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/default090909.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/lay.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">

		<link rel="stylesheet" href="${ctx}/js/Validform_v5.3/css/valid.css" type="text/css" media="all" />
		<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/Validform_v5.3.js"></script>
	</head>
	<body>
		<form class="fm1" id="fm1" name="fm1" action="${ctx}/course/studyCourseAdd.do" method="post">
			<!-- <input type="hidden" id="curTypeId" name="curTypeId" value="${param.curTypeId}" /> -->
			<input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="${sessionScope['org.apache.struts.action.TOKEN']}" />
			<table border="0" cellspacing="1" cellpadding="0" width="100%" class="gridtable">
				<tr>
					<td align="center" colspan="3" class="row_tip" height="25">
						添加课程
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>课程名称：
					</td>
					<td width="20%">
						<input type="text" id="studyCourseName" name="course.studyCourseName" class="editInput" maxlength="60" size="25" datatype="*1-50" nullmsg="请输入课程名称!" />
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						主讲人：
					</td>
					<td>
						<input type="text" id="teacher" name="course.teacher" class="editInput" maxlength="25" size="25" />
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						关键字：
					</td>
					<td>
						<input type="text" id="keyWord" name="course.keyWord" class="editInput" maxlength="200" size="25" />
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						学时：
					</td>
					<td>
						<input type="text" id="classHours" name="course.classHours" class="editInput" ignore="ignore" maxlength="2" size="25" nullmsg="请输入学时！" datatype="n" errormsg="请输入数字类型的时长！" />
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<!-- 
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>类型：
					</td>
					<td align="left">
						<select id="studyCourseType" name="course.studyCourseType" datatype="*" nullmsg="请选择课程类型！" errormsg="请选择课程类型！">
							<option value="">请选择</option>
							<option value="1">单屏课程</option>
							<option value="2">三屏课程</option>
							<option value="3">flash课程</option>
							<option value="4">静态页面</option>
							<option value="5">混合</option>
						</select>
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				 -->
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>状态：
					</td>
					<td align="left">
						<select id="status" name="course.status" datatype="*" nullmsg="请选择状态！" errormsg="请选择课程类型！">
							<option value="0">未发布</option>
							<option value="1">已发布</option>
							<option value="2">已下线</option>
							<option value="3">已作废</option>
						</select>
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>难度：
					</td>
					<td align="left">
						<select id="difficulty" name="course.difficulty" datatype="*" nullmsg="请选择状态！" errormsg="请选择课程类型！">
							<option value="1">初</option>
							<option value="2">中</option>
							<option value="3">高</option>
						</select>
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>				
				<tr>
					<td class="row_tip" height="25">
						课程内容（必填）：
					</td>
					<td>
						<textarea rows="5" cols="50" id="description" name="course.description" ignore="ignore" datatype="*1-1000" nullmsg="简述最多1000个字符！" errormsg="简述最多1000个字符！"></textarea>
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>

				<tr>
					<td class="row_tip" height="25">
						教学目标（必填）：
					</td>
					<td>
						<textarea rows="5" cols="50" id="review" name="course.review" ignore="ignore" datatype="*1-250" nullmsg="导读最多250个字符！" errormsg="导读最多250个字符！"></textarea>
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td colspan="6" align="center" class="row_tip">
						<input type="submit" class="btn_03s" value="保 存" />
						<input type="button" class="btn_03s" value="返 回" onclick="history.go(-1)" />
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">
			$(function() {
		       $(".fm1").Validform();
		    })
		</script>
	</body>
</html>