<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>课程分类管理</title>
		<%@include file="/commons/taglibs.jsp"%>
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/icon.css">
		
		<script type="text/javascript" src="${ctx}/js/UI/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/locale/easyui-lang-zh_CN.js"></script>
	</head>
	<body>
		<div>
			<div style="display: inline-block;width:240px;float:left;">
				<div style="font-size:14px;font-weight:bolder;padding: 0 0 5px 10px;">
				  &nbsp;
				</div>
				<ul id="menuTree" class="easyui-tree" style="float:left;width:97%;margin-left:5px;height:200px">
			</div>
			<div style="display:inline-block;width:1050px;float:left;text-align:left;height:600px;">
				<iframe id="mainFrame" name="mainFrame" frameborder="0" style="width:100%;height:100%;margin:0;padding:0;"></iframe>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		$('#menuTree').tree({
   			url:'${ctx}/course/studyCourseMenu.do',
   			onClick:function(node){
				$('#mainFrame').attr("src","${ctx}/course/studyCourseTypeList.do?curTypeId="+node.id);
   			},
   			onBeforeLoad : function(node, param) {
				if (node) {
					$(this).tree('options').url = '${ctx}/course/studyCourseMenu.do?parentId=' + node.id;
				} else {
					$(this).tree('options').url = '${ctx}/course/studyCourseMenu.do';
				}
			}
   		});
        function reloadAdd(){
          //新增后刷新树
          $.ajaxSetup({async: false});
   		  var obj = $('#menuTree').tree('getSelected');
   		  var child = $('#menuTree').tree('getChildren',obj.target);
   		  
   		  if(child.length==0){
   		      //刷新父节点
   		      var parent = $('#menuTree').tree('find',obj.attributes.parentId);
   		      $('#menuTree').tree('reload',parent.target);
   		  }else{
   		      //刷新本节点
   		      $('#menuTree').tree('reload',obj.target);
   		  }

          //选中当前节点
   		  var current = $('#menuTree').tree('find',obj.id);
   		  $('#menuTree').tree('select',current.target);
   		  $('#menuTree').tree('expand',current.target);

   		  $.ajaxSetup({async: true});
        }

        function reloadDelete(){
          //删除后刷新树
          $.ajaxSetup({async: false});
   		  var obj = $('#menuTree').tree('getSelected');
   		  //刷新父节点
   		  var parent = $('#menuTree').tree('find',obj.attributes.parentId);
   		  $('#menuTree').tree('reload',parent.target);

          //选中当前节点
   		  var current = $('#menuTree').tree('find',obj.id);
   		  $('#menuTree').tree('select',current.target);
   		  $('#menuTree').tree('expand',current.target);

   		  $.ajaxSetup({async: true});
        }

	</script>
</html>