<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>添加权限资源链接</title>
		<%@ include file="/commons/taglibs.jsp"%>
		<script src="${ctx}/js/prototype.js"></script>
		<style>
body {
	margin: auto;
}
</style>
		<script type="text/javascript">
    	function $(id) {
    		return document.getElementById(id);
    	}
    	function doSave(){
    		if($('value').value==''){alert('资源链接未填写');return false;}
    		var url = '${ctx}/security/addResource.do';
    		var params = 'value='+$('value').value;
    		params+='&type='+$('type').value;
    		params+='&name='+$('name').value;
			var doAjax = new Ajax.Request(
	   		url,
	   		{
	      		method : 'POST',
	      		parameters : params,
	      		onComplete : result
	   		}
	   		);
    		
    	}
    	
		function result(originalRequest){
			if(originalRequest.responseText=='success'){
				$('value').value = '';
				$('type').value = '';
				$('name').value = '';
				alert('资源链接保存成功！');
			}else{
				alert(originalRequest.responseText);
			}
		}
    </script>
	</head>
	<body>
		<div style="position: relative; text-align: center; top: 30%;">
			<table border=0 style="border: #035 solid;">
				<thead>
					<tr>
						<th colspan=2>
							增加资源
						</th>
						<th>
						</th>
					</tr>
				</thead>
				<tr>
					<td>
						资源链接：
					</td>
					<td>
						<input type="text" id="value" name="value" size="50"/><span style="color: red; padding-left: 5px; font-size: 12px;">*</span>
					</td>
				</tr>
				<tr>
					<td>
						资源级别：
					</td>
					<td>
						<input type="text" id="type" name="type" vlaue="1"/><span style="color: red; padding-left: 5px; font-size: 12px;">1:url 2：菜单</span>
					</td>
				</tr>
				<tr>
					<td>
						资源名称：
					</td>
					<td>
						<input type="text" id="name" name="name" />
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<input type="button" id="save" name="save" value="保存" onclick="doSave()" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: right;">
						<a href="${ctx}/security/findResources.do">&gt;&gt;&gt;资源关联</a>
					</td>
					<td></td>
				</tr>
			</table>
		</div>
	</body>
</html>
