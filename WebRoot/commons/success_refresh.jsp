<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>操作成功提示</title>
	<%@include file="/commons/taglibs.jsp"%>
	<script language="javascript">
	<!--
		function doAfterSubmit(){
			try{
				window.parent.doAfterSubmit();
			}catch(err){
				//just skip it
				
			}
		}
	//-->
	</script>
</head>

<body style="margin: 0; padding: 0; background: transparent url(${ctx}/images/background-blue.gif) top center repeat-x">
<div style="text-align: center; padding: 20px;">
	<table border="0" cellspacing="4" cellpadding="5">
	<tbody>
		<tr>
			<td rowspan="2"><img src="${ctx}/images/success.gif" border=0 alt="操作成功"></td>
			<td align="center" style="font-size: 14px; font-weight: bold">操作成功！</td>
		</tr>
		<tr>
			<td align="center"><button onclick="doAfterSubmit()" style="width: 120px;">返回列表页</button></td>
		</tr>
	</tbody>
	</table>
	

</div>
</body>
</html>