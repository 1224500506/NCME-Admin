<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课件导入</title>
<%@include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/prototype.js"></script>
<style type="text/css">
td {
	font-size: 12px;
}
#red {
	COLOR: #FF0000;
	FONT-SIZE: 12px;
}
</style>
</head>
<body>
<br/>

==============================================以下是上传新的课件=======================================================
<form action="" name="fm1" id="fm1" method="post" enctype="multipart/form-data">
<table>
	<!-- 
  <tr>
  	<td>&nbsp;</td>
  	<td><a href="template2.xls" target="_blank"><span id="red">导入模版(含关联属性)</span></a></td>
  </tr> -->
  <tr>
  	<td><span id="red">&nbsp;*</span>&nbsp;选择文件：&nbsp;<input type="file" name="uploadfile" id="uploadfile" value="" />
  		<span id="red">&nbsp;(注：上传文件为xls格式，大小不能超过1MB,总行数不能超过1000!)</span>
  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		<input type="button" value="返回课件列表" onclick="window.location.href='${ctx}/course/coursewareList.do'" />   </td>
  </tr>
  <tr>
  	<td>
  		<input type="button" value="导入课件(含关联属性)" onclick="submitForm();"/>
  	</td>
  </tr>
</table>
</form>

<br/>
<br/>
<br/>
<br/>
==============================================以下是修改课件的链接=======================================================
==============================================Excel里只保留课件id,课件名,课件新的链接,以及备用链接4列====================================
<br/>
<form action="" name="fm2" id="fm2" method="post" enctype="multipart/form-data">
<table>
	
  <tr>
  	<td><span id="red">&nbsp;*</span>&nbsp;选择文件：&nbsp;<input type="file" name="updatefile" id="updatefile" value="" />
  		<span id="red">&nbsp;(注：上传文件为xls格式，大小不能超过1MB,总行数不能超过1000!)</span>
  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		<input type="button" value="返回课件列表" onclick="window.location.href='${ctx}/course/coursewareList.do'" />   </td>
  </tr>
  <tr>
  	<td>
  		<input type="button" value="导入课件(只修改视频链接)" onclick="updateForm();"/>
  	</td>
  </tr>
</table>
</form>

<script>
function submitForm(){
	if($F("uploadfile")==""){
		alert("请选择要导入的文件！");
		$("uploadfile").focus();
		return;
	}
	if($F("uploadfile").length>4){
		if($F("uploadfile").substring($F("uploadfile").length-3,$F("uploadfile").length)!= "xls" && $F("uploadfile").substring($F("uploadfile").length-4,$F("uploadfile").length)!= "xlsx"){
			alert("只能上传.xls和.xlsx的文件");
			return;
		}
	}
	if(window.confirm("您确定导入吗？")){
		
			document.fm1.action="${ctx}/course/importCourseware.do"
			document.fm1.submit();
		
	}
}

function updateForm(){
	if($F("updatefile")==""){
		alert("请选择要导入的文件！");
		$("updatefile").focus();
		return;
	}
	if($F("updatefile").length>4){
		if($F("updatefile").substring($F("updatefile").length-3,$F("updatefile").length)!= "xls" && $F("updatefile").substring($F("updatefile").length-4,$F("updatefile").length)!= "xlsx"){
			alert("只能上传.xls和.xlsx的文件");
			return;
		}
	}
	if(window.confirm("您确定更新新的链接吗？")){
		
			document.fm2.action="${ctx}/course/importCourseware.do?method=update"
			document.fm2.submit();
		
	}
}

<c:if test="${not empty errMsg}">
	alert("${errMsg}");
</c:if>
</script>
</body>
</html>
