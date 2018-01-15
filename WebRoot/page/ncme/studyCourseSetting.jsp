<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程设置</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
</style>
</head>
<body style="">
<form action="${ctx}/ncme/ncmeCourseAuthorizeList.do" method="post" name="fmx">
<table class="gridtable" width="100%" cellpadding="1" cellspacing="0" border="0">
	
	<tr>
		<td class="row">&nbsp;
		</td>
		<td class="row">&nbsp;
		</td>
		<td class="row">&nbsp;
		</td>
		<td class="row" align="center">
			<input type="button" class="btn_03s" value="返回授权" onclick="window.location.href='${ctx}/ncme/ncmeCourseAuthorizeList.do'"/>
		</td>	
	</tr>
</table>
</form>
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<form id="fm1" name="fm1" method="post" action="">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;批量设置课件播放时间：
		<input type="text" id="time" value="${setting.time }" onkeyup="this.value = this.value.replace(/[^\d\.]/g, '');">分钟
		<input type="button" class="btn_03s" value="重新设置" onclick="doSetting()"/>
		<input type="hidden" id="id" value="${setting.id }">
	</form>
</table>
 
</body>
</html>
<script type="text/javascript">

//设置课件播放时间
function doSetting(){
	var time = $("#time").val();
	if(time == '' || time <0){
		alert("请设置一下时间~不能为空或者负数哦~");
		return;
	}
	var id = $("#id").val();
	if(confirm("确认设置吗?")){
		var p = {'time':time,'id':id};
		$.post("${ctx }/ncme/ncmeCourseSetting.do?method=update", p,
  			function(data){
  				if(data >0){
  					alert("设置成功!");
  				}
  				else{
  					alert("由于网络原因,设置不成功,请稍候再试!");
  				}
  				window.location.reload(true);
  		});
	}
}


</script>
