<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@include file="/commons/taglibs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>试题导入</title>
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
<form action="" name="fm1" id="fm1" method="post" enctype="multipart/form-data">
<table>
  <tr>
  	<td>&nbsp;</td>
  	<td><a href="template2.xls" target="_blank"><span id="red">导入模版(含关联属性)</span></a></td>
  </tr>
  <tr>
  	<td><span id="red">&nbsp;*</span>&nbsp;选择文件：&nbsp;<input type="file" name="uploadfile" id="uploadfile" value="" /><span id="red">&nbsp;(注：上传文件为xls格式，大小不能超过1MB,总行数不能超过1000!)</span></td>
  </tr>
  <tr>
  	<td><!-- 
  		<input type="button" value="导入试题" onclick="submitForm('a');"/>
  		 -->
  		<input type="button" value="导入试题(含关联属性)" onclick="submitForm('a2');"/>
  		<input type="button" value="试题比对" onclick="submitForm('c');"/>
  		<input type="button" value="导入试题关联属性" onclick="submitForm('cd');"/>
  	</td>
  </tr>
</table>
</form>
<script>
function submitForm(tmp){
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
	if(window.confirm("您确定提交吗？")){
		if(tmp=='a'){
			document.fm1.action="${ctx}/questionManage/importQuestion.do"
			document.fm1.submit();
		}
		if(tmp=='a2'){
			document.fm1.action="${ctx}/questionManage/importQuestion2.do"
			document.fm1.submit();
		}
		if(tmp=='c'){
			document.fm1.action="${ctx}/questionManage/compareQuestion.do?type=F"
			document.fm1.submit();
		}
		
		if(tmp=='cd'){
			document.fm1.action="${ctx}/questionManage/importQuestionProp.do"
			document.fm1.submit();
		}
		
	}
}
<c:if test="${not empty errMsg}">
alert("${errMsg}");
</c:if>
</script>
</body>
</html>
