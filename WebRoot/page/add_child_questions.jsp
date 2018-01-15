<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/commons/meta.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
	<%@include file="/commons/taglibs.jsp"%>
		<title>添加子试题</title>
<script language="javascript" src="${ctx}/js/global.js"></script>
<style type="text/css">
#shiti{
	margin-top:15px;
	display:none;
	text-align: left;
}
body{
	margin: 0px;
	padding:0px;
}
</style>
	</head>
<body>
<script>
var winObj = window.opener;
window.onblur = function(){
	var ele = document.activeElement;
	if(!ele.type){
		window.focus();
	}
}
</script>
<div align="right">
<input name="button" type="button" value="提交" onclick="winObj.started();"/>&nbsp;
<input type="button" name="close" value="返回" onclick="winObj.win1.hide();winObj.resetChildWin();"/>
</div>
<table border="0" cellspacing="1" cellpadding="5" width="100%" class="gridtable">
  <tr>
  	<td class="row_tip"><span style='COLOR:#FF0000;FONT-SIZE:12px'>*</span>&nbsp;选项个数：</td>
	<td><input name="input_num" id="input_num" class="input1" type="text" size="5"/></td>
 	<td class="row_tip"><span style='COLOR:#FF0000;FONT-SIZE:12px'>*</span>&nbsp;试题类型：</td>
	<td><select name="status" id="status" class="sel1">
		  <option value="1">多选试题</option>
		  <option value="2">单选试题</option>
		</select>
	</td>
  </tr>
</table>
<div id="shiti">
<table border="0" cellspacing="1" cellpadding="5" width="100%" class="gridtable">
  <tr>
  	<td class="row_tip"><span style='COLOR:#FF0000;FONT-SIZE:12px'>*</span>&nbsp;试题内容：</td>
  	<td align="left"><textarea name="title" cols="40" rows="4" class="input1"></textarea></td>
  </tr>
  <tr>
  	<td class="row_tip">试题分析：</td>
  	<td align="left"><textarea name="jiexi" cols="40" rows="4" class="input1"></textarea></td>
  </tr>
  <tr>
  	<td class="row_tip"><span style='COLOR:#FF0000;FONT-SIZE:12px'>*</span>&nbsp;候选项：</td>
  	<td align="left"><div id="xuanxiang"></div></td>
  </tr>	
</table>
</div>				
</body>
</html>