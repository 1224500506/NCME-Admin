<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>试卷分类</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<%@include file="/commons/taglibs.jsp"%>
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/button.css" rel="stylesheet" type="text/css" />
		
		<link href="${ctx}/js/UI/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/js/UI/themes/icon.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="${ctx}/js/UI/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/locale/easyui-lang-zh_CN.js"></script>
		
	</head>
	<body>
		<div>
	  		<table class="gridtable" cellpadding="1" cellspacing="0" border="0" width="100%">
				<tr>
					<td class="row" width="15%" align="right" style="padding-top:10px;">
						目录名称：
					</td>
					<td class="row">
						${param.rowName}
	    				<input type="hidden" name="oldCode" id="oldCode" value="${param.rowCode}" />
					</td>
				</tr>
				<tr>
					<td class="row" align="right">
						所属目录：
					</td>
					<td class="row">
						${param.parentName}&nbsp;
						<input type="hidden" id="pName" name="pName" value="${param.parentName}" />
					</td>
				</tr>
				<tr>
					<td class="row" align="right">
						调整目录：
					</td>
					<td class="row">
						<input type="text" id="adjustName" name="adjustName" readonly="readonly" />
					</td>
				</tr>
			</table>
			
			<div>
				<div style="font-size:14px;font-weight:bolder;padding: 0 0 5px 10px;">
				  &nbsp;
				</div>
				<ul id="paperTree" class="easyui-tree" style="float:left;width:97%;margin-left:5px;">
			</div>
			
			<div align="center">
				<br />
			  	<input type="button" class="btn_03s" value="保 存" id="addNode" />
			  	<input type="button" class="btn_03s" value="返 回" onclick="closeWin2();" />
			</div>
		</div>
		
		<script type="text/javascript">
	   		
	   		$(document).ready(function(){
	   			$('#paperTree').tree({
		   			onClick:function(node){
						$("#adjustName").attr("value", node.text) ;
		   			},
		   			onBeforeLoad : function(node, param) {
						if (node) {
							$(this).tree('options').url = '${ctx}/exam/paper/paperTree.do?parentId=' + node.id;
						} else {
							$(this).tree('options').url = '${ctx}/exam/paper/paperTree.do';
						}
					}
		   		});
		   		
		   		$("#addNode").click(function(){
		   			var pname = $("#pName").val() ;
		   			var aName = $("#adjustName").val() ;
		   			var _node = $('#paperTree').tree('getSelected');
		   			
		   			if(aName == ''){
		   				alert("调整目录为空，请您选择需调整到哪个目录下!") ;
		   				return false ;
		   			}
		   			
		   			if(pname == _node.text){
		   				alert("当前需要调整的目录已在此目录下，请重新选择!") ;
		   				return false ;
		   			}
		   			
		   			if(confirm("您确定将当前节点调整到本次选择目录吗？")){
		   				var url = "${ctx}/exam/paper/examPaperTypeMove.do" ;
		   				$.ajax({
							type:"post",
							url:url,
							data:"parentId="+_node.id+"&oldCode="+$("#oldCode").val(),
							dataType: 'text',
							success: function(msg) {
								if(msg == 'ajax.ok'){
									alert("调整成功！");
									parent.parent.reloadDelete() ;
									parent.closeWin();
								}else {
									alert("调整失败！");
								}
							},
							error: function(){
								alert("调整失败！");
							}
						});
		   			}
		   		}) ;
	   		}) ;
	   		
   			function closeWin2(){
	   			window.parent.$('#editInfo2').window("close") ;
	   		}
		</script>
	</body>
</html>