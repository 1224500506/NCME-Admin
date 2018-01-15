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
<form class="fm1" id="fm1" name="fm1" action="${ctx}/course/coursewareSave.do" method="post">
<table border="0" cellspacing="1" cellpadding="0" width="100%" class="gridtable">
	<tr>
		<td align="center" colspan="2" class="row_tip" height="25">
			查看课件
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			课件代码：
		</td>
		<td width="25">
		    ${StudyCourseware.code}
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			课件名称：
		</td>
		<td>
		    ${StudyCourseware.name}
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			课件类型：
		</td>
		<td align="left">
		<c:if test="${StudyCourseware.type eq 1}"><c:out value="单屏课程" ></c:out></c:if>
		<c:if test="${StudyCourseware.type eq 2}"><c:out value="三屏课程" ></c:out></c:if>
		<c:if test="${StudyCourseware.type eq 3}"><c:out value="flash课程" ></c:out></c:if>
		<c:if test="${StudyCourseware.type eq 4}"><c:out value="静态页面" ></c:out></c:if>
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			时长(分钟)：
		</td>
		<td>
		  ${StudyCourseware.times}
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			学时：
		</td>
		<td>
		${StudyCourseware.classHours}
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			内容摘要：
		</td>
		<td>
		  ${StudyCourseware.summary}
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			关键字：
		</td>
		<td>
		  ${StudyCourseware.keyWord}
		</td>
     </tr>
	<tr>
		<td class="row_tip" height="25">
			授课老师姓名：
		</td>
		<td>
		  ${StudyCourseware.teacherName}
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			授课老师职称：
		</td>
		<td>
		  ${StudyCourseware.teacherTitle}
		</td>	
	</tr>
			<tr>
		<td class="row_tip" height="25">
			授课老师单位：
		</td>
		<td>
		  ${StudyCourseware.teacherUnit}
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			制作年份：
		</td>
		<td>
		  ${StudyCourseware.makeYear}
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			行业：
		</td>
		<td width="600">
			<c:forEach items="${StudyCourseware.industryList}" var="list">
				<label for="industryId_${list.id}">${list.name}</label>;
			</c:forEach>
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			适用对象：
		</td>
		<td width="600">
			<c:forEach items="${StudyCourseware.applicableList}" var="list">
				<label for="applicableId_${list.id}">${list.name}</label><br/>
			</c:forEach>
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			知识分类：
		</td>
		<td width="600">
			<c:forEach items="${StudyCourseware.knowList}" var="list">
				<label for="knowId_${list.id}">${list.name}</label><br/>
			</c:forEach>
		</td>
	</tr>	
	<tr>
		<td class="row_tip" height="25">
			课件注意事项：
		</td>
		<td>
		  ${StudyCourseware.attentions}
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			课件地址：
		</td>
		<td>
		  <textarea name="path" id="path" style="width:100%" rows="5"><c:out value="${StudyCourseware.path}" escapeXml="true"></c:out></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="6" align="center" class="row_tip">
		    <input type="button" class="btn_02s" onclick="window.location.href='${ctx}/course/coursewareList.do'"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>