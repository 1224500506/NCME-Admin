<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>试卷信息</title>
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
				<ul id="paperTree" class="easyui-tree" style="float:left;width:97%;margin-left:5px;">
			</div>
			<div style="display:inline-block;float:left;width:70%;text-align:left;height:700px;">
				<iframe id="mainFrame" name="mainFrame" frameborder="0" style="width:100%;height:100%;margin:0;padding:0;"></iframe>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		$('#paperTree').tree({
   			onClick:function(node){
   				if(node.id > 0){
   					$('#mainFrame').attr("src","${ctx}/course/studyExamQuestion.do?typeId="+node.id+"&curId=${param.curId}&examType=${param.examType}");
   				}
   			},
   			onBeforeLoad : function(node, param) {
				if (node) {
					$(this).tree('options').url = '${ctx}/exam/paper/paperTree.do?parentId=' + node.id;
				} else {
					$(this).tree('options').url = '${ctx}/exam/paper/paperTree.do';
				}
			}
   		});
	</script>
</html>