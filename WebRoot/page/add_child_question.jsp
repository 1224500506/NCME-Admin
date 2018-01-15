<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/commons/meta.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
	<%@include file="/commons/taglibs.jsp"%>
		<title>子试题信息</title>
		<script language="javascript" src="${ctx}/js/global.js"></script>
<style>
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
<table border="0" cellspacing="1" cellpadding="5" width="100%" class="gridtable">
  <tr>
  	<td class="row_tip"><span style='COLOR:#FF0000;FONT-SIZE:12px'>*</span>&nbsp;试题内容：</td>
  	<td align="left"><textarea id="vchild_content" name="vchild_content" style="width:92%;" rows="4"></textarea></td>
  </tr>
  <tr>
  	<td class="row_tip"><span style='COLOR:#FF0000;FONT-SIZE:12px'>*</span>&nbsp;标准答案：</td>
  	<td align="left"><textarea id="vchild_answer" name="vchild_answer" style="width:92%;" rows="4"></textarea></td>
  </tr>  
  <tr>
  	<td class="row_tip">试题分析：</td>
  	<td align="left"><textarea id="vchild_analyse" name="vchild_analyse" style="width:92%;" rows="4"></textarea></td>
  </tr>
  <tr>
  	<td class="row_tip" colspan="2" align="center">
  	<img id="vchild_save" src="${ctx}/images/xjsj_bc.gif" onclick="winObj.addchildquestion(-1);" style="cursor: pointer;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<img name="close" src="${ctx}/images/xjsj_fh.gif" onclick="winObj.switchfun(1);winObj.win1.hide();" style="cursor: pointer;"/>
  	</td>
  </tr>
</table>					
</body>
</html>