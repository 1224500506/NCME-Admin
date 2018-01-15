<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>考试分类信息</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/icon.css">
		<%@include file="/commons/taglibs.jsp"%>
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
			<div style="display:inline-block;float:left;text-align:left;height:600px;width:75%;">
				<iframe id="mainFrame" name="mainFrame" frameborder="0" style="width:100%;height:100%;margin:0;padding:0;"></iframe>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		$('#examTree').tree({
   			onClick:function(node){
				$('#mainFrame').attr("src","${ctx}/exam/category/examTypeList.do?curTypeId="+node.id);
   			},
   			onBeforeLoad : function(node, param) {
				if (node) {
					$(this).tree('options').url = '${ctx}/exam/category/examCategoryTree.do?parentId=' + node.id;
				} else {
					$(this).tree('options').url = '${ctx}/exam/category/examCategoryTree.do';
				}
			}
   		});
        function reloadAdd(){
          //新增后刷新树
          $.ajaxSetup({async: false});
   		  var obj = $('#examTree').tree('getSelected');
   		  var child = $('#examTree').tree('getChildren',obj.target);
   		  
   		  if(child.length==0){
   		      //刷新父节点
   		      var parent = $('#examTree').tree('find',obj.attributes.parentId);
   		      $('#examTree').tree('reload',parent.target);
   		  }else{
   		      //刷新本节点
   		      $('#examTree').tree('reload',obj.target);
   		  }

          //选中当前节点
   		  var current = $('#examTree').tree('find',obj.id);
   		  $('#examTree').tree('select',current.target);
   		  $('#examTree').tree('expand',current.target);

   		  $.ajaxSetup({async: true});
        }

        function reloadDelete(){
          //删除后刷新树
          $.ajaxSetup({async: false});
   		  var obj = $('#examTree').tree('getSelected');
   		  //刷新父节点
   		  var parent = $('#examTree').tree('find',obj.attributes.parentId);
   		  $('#examTree').tree('reload',parent.target);

          //选中当前节点
   		  var current = $('#examTree').tree('find',obj.id);
   		  $('#examTree').tree('select',current.target);
   		  $('#examTree').tree('expand',current.target);

   		  $.ajaxSetup({async: true});
        }

	</script>
</html>