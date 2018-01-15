<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
String type = request.getParameter("type") == null ? "0" : request.getParameter("type");
String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>试题导入</title>
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
<form action="" name="fm1" id="fm1" method="post" enctype="multipart/form-data">
<input type="hidden" name="type" value="<%=type%>">
<input type="hidden" name="id" value="<%=id%>">
<table>
  <tr>
  	<td><span id="red">&nbsp;*</span>&nbsp;选择文件：&nbsp;<input type="file" name="uploadfile" id="uploadfile" value="" /><span id="red"></span></td>
  </tr>
  <tr>
  	<td>
  		<input type="button" value="导入属性" onclick="submitForm();"/>
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
	if(window.confirm("您确定提交吗？")){
		document.fm1.action="${ctx}/propManage/importOlemProp.do"
		document.fm1.submit();
	}
}
</script>
</body>
</html>
