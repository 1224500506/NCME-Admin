<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>考试分类信息</title>
		<%@include file="/commons/taglibs.jsp"%>
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/icon.css">
		
		<script type="text/javascript" src="${ctx}/js/UI/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/locale/easyui-lang-zh_CN.js"></script>
	</head>
	<body>
		<div>
			<div style="display: inline-block;width:280px;float:left;">
				<div style="font-size:14px;font-weight:bolder;padding: 0 0 5px 10px;">
				  &nbsp;
				</div>
				<ul id="examTree" class="easyui-tree" style="float:left;width:97%;margin-left:5px;">
			</div>
			<div style="display:inline-block;width:980px;float:left;text-align:left;height:600px;">
				<iframe id="mainFrame" name="mainFrame" frameborder="0" style="width:100%;height:100%;margin:0;padding:0;"></iframe>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		$('#examTree').tree({
   			onClick:function(node){
				$('#mainFrame').attr("src","${ctx}/exam/examList.do?curTypeId="+node.id);
   			},
   			onBeforeLoad : function(node, param) {
				if (node) {
					$(this).tree('options').url = '${ctx}/exam/category/examCategoryTree.do?parentId=' + node.id;
				} else {
					$(this).tree('options').url = '${ctx}/exam/category/examCategoryTree.do';
				}
			}
   		});
	</script>
</html>