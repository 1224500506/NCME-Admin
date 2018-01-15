<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="now" class="java.util.Date" />   
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
<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/bootstrap/easyui.css">
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/Validform_v5.3.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/icon.css">
<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/UI/locale/easyui-lang-zh_CN.js"></script>   
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>



</head>
<body>
<form class="fm1" id="fm1" name="fm1" action="${ctx}/ncme/ncmeCourseAuthorizeUpdate.do?method=update" method="post">
<input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="${sessionScope['org.apache.struts.action.TOKEN']}" />
<table border="0" cellspacing="1" cellpadding="0" width="90%" class="gridtable">
	<tr>
		<td align="center" colspan="3" class="row_tip" height="25">
			课程授权
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25"  align="right">
			课程：
		</td>
		<td>
		  ${credit.studyCourseName }
          <input type="hidden" id="courseIds" name="courseIds" value="${credit.courseId }"/>
		</td>
		<td>
          &nbsp;
        </td>	
	</tr>
	<!-- 
	<tr>
		<td class="row_tip" height="25"  align="right">
			课程学时：
		</td>
		<td>
		  ${credit.classHours }
		</td>
		<td>
          &nbsp;
        </td>	
	</tr>
	 -->
	<tr>
		<td class="row_tip" height="25" align="right">
			授权年度：
		</td>
		<td width="50%">
	      ${credit.creditYear }
	      <input type="hidden" id="creditYear" name="creditYear" value="${credit.creditYear }"/>
		</td>
        <td>
          &nbsp;
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			<font class="red_star">&nbsp;</font>项目编号：
		</td>
		<td>
		<input type="text" id="courseSerial" class="editInput" name="courseSerial" maxlength="50" size="25" value="${credit.courseSerial }" />
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			授权类别：
		</td>
		<td>
			${credit.courseTypeNames }
			<input type="hidden" id="courseTypeIds" name="courseTypeIds" value="${credit.courseTypeIds }"/>
		</td>
		<td>
          &nbsp;
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			<font class="red_star">*</font>授权级别：
		</td>
		<td align="left">
		<select id="creditType" name="creditType" datatype="*" nullmsg="请选择授权级别！" errormsg="请选择授权级别！" >
		      <c:forEach items="${ncmeCreditTypeList}" var="list">
		        <option value="${list.creditType}" <c:if test="${credit.creditType eq  list.creditType}">selected</c:if> >${list.creditName}
		      </c:forEach>
		    </select>
		</td>
	    <td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			<font class="red_star">*</font>授予学时：
		</td>
		<td>
		<input type="text" id="creditNum" class="editInput" name="creditNum" maxlength="8" size="25"  value="${credit.creditNum }"
		 	datatype="*" nullmsg="请输入授权学时！" errormsg="请输入授权学时！"/>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>	
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			<font class="red_star">*</font>授权开始时间：
		</td>
		<td align="left">
			<input type="text" name="startDate" id="startDate" value="<fmt:formatDate value="${credit.startDate}" pattern="yyyy-MM-dd"/>"
				datatype="*" nullmsg="请选择授权开始时间！" onClick="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\');}'})" />
		</td>
	    <td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			<font class="red_star">*</font>授权结束时间：
		</td>
		<td align="left">
			<input type="text" name="endDate" id="endDate" value="<fmt:formatDate value="${credit.endDate}" pattern="yyyy-MM-dd"/>"
				datatype="*" nullmsg="请选择授权结束时间！" onClick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\');}'})" />
		</td>
	    <td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<!-- 
	<tr>
		<td class="row_tip" height="25" align="right">
			<font class="red_star">*</font>授予学时：
		</td>
		<td>
		<input type="text" id="creditHours" class="editInput" name="creditHours" maxlength="8" size="25" 
			datatype="n" nullmsg="请输入授予学时！" errormsg="请输入数字类型的授予学时！" value="${credit.creditHours }"/>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>	
	</tr>
	 -->
    <!-- 
	<tr>	
		<td class="row_tip" height="25" align="right">
			机构：
		</td>
		<td>
			北京市安监局
			<input type="hidden" id="organId" name="organId" value="${credit.organId }"/>
		</td>
		<td>
          &nbsp;
        </td>	
	</tr>
	 -->
	<tr>	
		<td class="row_tip" height="25" align="right">
			站点：
		</td>
		<td>
			${domainName }
			<input type="hidden" id="siteId" name="siteId" value="${credit.siteId }"/>
		</td>
		<td>
          &nbsp;
        </td>	
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			重学标志：
		</td>
		<td>
		<select id="reStudyFlag" name="reStudyFlag" >
		  <option value="1" <c:if test="${credit.reStudyFlag == 1 }">selected</c:if> >不同年份可以重复学习
		  <option value="0" <c:if test="${credit.reStudyFlag == 0 }">selected</c:if> >不同年份不能重复学习
		</select>
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>	
	</tr>
	<tr>
		<td colspan="6" align="center" class="row_tip">
		    <input type="submit" class="btn_03s" value="保 存" />
		    <input type="button" class="btn_03s" value="返 回" onclick="javascript:history.back();"/>
		</td>
	</tr>
</table>


</form>
<script type="text/javascript">
	 $(".fm1").Validform({
      datatype:{//自定义datatype类型
	    "org":function(gets,obj,curform,datatype){
	      if(obj[0].options.length > 0){
	        $(obj).children().each(function(){
                this.selected = true;
              });
	        return ture;
	      }
	      return false;
		}
      }
    });

</script>
</body>
</html>