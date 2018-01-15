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
<table border="0" cellspacing="1" cellpadding="0" width="90%" class="gridtable">
	<tr>
		<td align="center" colspan="3" class="row_tip" height="25">
			课件信息
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25" nowrap="nowrap" >
			<font class="red_star">*</font>课件名称：
		</td>
		<td>
		<input type="text" id="name" class="editInput" name="name" maxlength="100" size="25" datatype="*1-100" nullmsg="请输入课件名称！" errormsg="课件名称最多100个字符！" value="${StudyCourseware.name}"/>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			<font class="red_star">*</font>课件类型：
		</td>
		<td align="left">
		<select name="type" id="type" datatype="*" nullmsg="请选择课件类型！" errormsg="请选择课件类型！">
		    <option value="">请选择
			<option value="1" <c:if test="${StudyCourseware.type eq 1}"><c:out value="selected" escapeXml="false"></c:out></c:if>>单屏课程
			<option value="2" <c:if test="${StudyCourseware.type eq 2}"><c:out value="selected" escapeXml="false"></c:out></c:if>>三屏课程
			<option value="3" <c:if test="${StudyCourseware.type eq 3}"><c:out value="selected" escapeXml="false"></c:out></c:if>>flash课程
			<option value="4" <c:if test="${StudyCourseware.type eq 4}"><c:out value="selected" escapeXml="false"></c:out></c:if>>静态页面
		</select>
		</td>
	    <td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25" nowrap="nowrap">
			时长(分钟)：
		</td>
		<td>
		<input type="text" id="times" class="editInput" name="times" maxlength="8" size="25" ignore="ignore" datatype="n" errormsg="请输入数字类型的时长！" value="${StudyCourseware.times}"/>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>	
	</tr>
	<!-- 
	<tr>
		<td class="row_tip" height="25">
			学时：
		</td>
		<td>
		<input type="text" id="classHours" class="editInput" name="classHours" maxlength="8" size="25" ignore="ignore" datatype="float" errormsg="请输入数字类型的时长！" value="${StudyCourseware.classHours}"/>
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	 -->
	<tr>
		<td class="row_tip" height="25">
			内容摘要：
		</td>
		<td>
		<input type="text" id="summary" class="editInput" name="summary" maxlength="200" size="25" value="${StudyCourseware.summary}"/>
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
		<input type="text" id="keyWord" class="editInput" name="keyWord" maxlength="200" size="25" value="${StudyCourseware.keyWord}"/>
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>
     </tr>
	<tr>
		<td class="row_tip" height="25" nowrap="nowrap">
			授课老师姓名：
		</td>
		<td>
		<input type="text" id="teacherName" class="editInput" name="teacherName" maxlength="25" size="25" value="${StudyCourseware.teacherName}"/>
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>	
	</tr>
	<tr>
		<td class="row_tip" height="25" nowrap="nowrap">
			授课老师职称：
		</td>
		<td>
		<input type="text" id="teacherTitle" class="editInput" name="teacherTitle" maxlength="25" size="25" value="${StudyCourseware.teacherTitle}"/>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>		
	</tr>
			<tr>
		<td class="row_tip" height="25">
			授课老师单位：
		</td>
		<td>
		<input type="text" id="teacherUnit" class="editInput" name="teacherUnit" maxlength="25" size="25" value="${StudyCourseware.teacherUnit}"/>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>		
	</tr>
	<tr>
		<td class="row_tip" height="25">
			<font class="red_star">*</font>制作年份：
		</td>
		<td>
		<c:if test="${StudyCourseware eq null}">
			<c:set var="makeYear" value="2014"></c:set>
		</c:if>
		<c:if test="${StudyCourseware.makeYear != null}">
			<c:set var="makeYear" value="${StudyCourseware.makeYear}"></c:set>
		</c:if>
		<input type="text" id="makeYear" class="editInput" name="makeYear" maxlength="4" size="25" value="${makeYear}" datatype="n" nullmsg="请填写制作年份！" errormsg="请填写课件类型！"/>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			<font class="red_star">*</font>行业：
		</td>
		<td width="600">
			<c:forEach items="${industryList}" var="list">
				<input type="checkbox" name="industryId" id="industryId_${list.id}" value="${list.id}" datatype="*" nullmsg="请选择行业！" errormsg="请选择行业！" 
				<c:forEach items="${StudyCourseware.industryList}" var="studyCoursewareList">
					<c:if test="${list.id eq studyCoursewareList.id }">checked</c:if>
				</c:forEach>
				/>
				<label for="industryId_${list.id}">${list.name}</label><br/>
			</c:forEach>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>		
	</tr>
	<tr>
		<td class="row_tip" height="25">
			<font class="red_star">*</font>适用对象：
		</td>
		<td width="600">
			<c:forEach items="${applicableList}" var="list">
				<input type="checkbox" name="applicableId" id="applicableId_${list.id}" value="${list.id}" datatype="*" nullmsg="请选择适用对象！" errormsg="请选择适用对象！"
				<c:forEach items="${StudyCourseware.applicableList}" var="studyCoursewareList">
					<c:if test="${list.id eq studyCoursewareList.id }">checked</c:if>
				</c:forEach>
				/>
				<label for="applicableId_${list.id}">${list.name}</label><br/>
			</c:forEach>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			<font class="red_star">*</font>知识分类：
		</td>
		<td width="600">
			<c:forEach items="${knowList}" var="list">
				<input type="checkbox" name="knowId" id="knowId_${list.id}" value="${list.id}" datatype="*" nullmsg="请选择知识分类！" errormsg="请选择知识分类！"
				<c:forEach items="${StudyCourseware.knowList}" var="studyCoursewareList">
					<c:if test="${list.id eq studyCoursewareList.id }">checked</c:if>
				</c:forEach>
				/>
				<label for="knowId_${list.id}">${list.name}</label><br/>
			</c:forEach>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>		
	</tr>
	<tr>
		<td class="row_tip" height="25">
			课件注意事项：
		</td>
		<td>
		<input type="text" id="attentions" class="editInput" name="attentions" maxlength="100" size="25" value="${StudyCourseware.attentions}"/>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			课件地址：
		</td>
		<td>
		    <%-- <input type="text" id="path" size="100px" class="editInput" name="path" maxlength="1000" size="100" value="<c:out value="${StudyCourseware.path}" escapeXml="true"></c:out>"/> --%>
		 
			<textarea name="path" id="path" style="width:100%" rows="8" cols="30"><c:out value="${StudyCourseware.path}" escapeXml="true"></c:out></textarea> 
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>	
	</tr>
	<tr>
		<td colspan="6" align="center" class="row_tip">
		    <input type="submit" class="btn_03s" value="保 存" />
		    <input type="button" class="btn_03s" value="返 回" onclick="window.location.href='${ctx}/course/coursewareDelete.do'"/>
		</td>
	</tr>
</table>
<input type="hidden" id="id" name="id" value="${StudyCourseware.id}"/>
<input type="hidden" name="org.apache.struts.taglib.html.TOKEN"  value="${sessionScope['org.apache.struts.action.TOKEN']}" />
</form>
<script type="text/javascript">
		    $(".fm1").Validform({
		      datatype:{//自定义datatype类型 浮点类型
			    "float":function(gets,obj,curform,datatype){
						var reg = new RegExp("^\\d+(\\.\\d+)?$");
						if (!reg.exec(gets)){
						  return false;
						}
						return true;
					}
		      }
	        });
</script>
</body>
</html>