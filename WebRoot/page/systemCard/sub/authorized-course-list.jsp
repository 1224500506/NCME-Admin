<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base target="_self" />
<title>卡类型与课程授权</title>
<%@ include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/icon.css">
<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/UI/locale/easyui-lang-zh_CN.js"></script>   
<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
</style>
 <script type="text/javascript">
 	$(function() {
	     //弹出层
	     $( "#dlgCourseType" ).dialog({
			  autoOpen: false,
		      height: 300,
		      width: 320,
		      modal: true,//true遮蔽
		      iconCls:'icon-search', //左上角图标,icon文件夹下图标
		      buttons: [{ 
						text: '提交', 
						iconCls: 'icon-ok', 
						handler: function() { 
							getChecked(); //选中
							//$('#dlgCourseType').dialog( "close" );
						} 
						}, { 
						text: '取消', 
						iconCls: 'icon-cancel', 
						handler: function() { 
							$("#dlgCourseType").dialog( "close" ); 
						} 
						}] ,
		      close: function() {
		        allFields.val( "" ).removeClass( "ui-state-error" );
		      }
		    });
	     
		   	//树形
		 	$('#menuTree').tree({
		 		checkbox:true,
		 		cascadeCheck:false,//定义是否支持级联选择。
		 		onlyLeafCheck:true,//定义是否只在叶子节点之前显示复选框。
		 			url:'${ctx}/course/studyCourseMenu.do',
		 			onClick:function(node){
		 				
		 			},
		 			onBeforeLoad : function(node, param) {
		 			if (node) {
		 				$(this).tree('options').url = '${ctx}/course/studyCourseMenu.do?parentId=' + node.id;
		 			} else {
		 				$(this).tree('options').url = '${ctx}/course/studyCourseMenu.do';
		 			}
		 		}
		 	}); 
	  })

	//[查询] 
	function query() {
		document.forms[0].submit();
	}
	
	//选择课程分类
	function add(typeId){
		
		//$( "#dialogFrame" ).attr('src','${ctx}/page/systemCard/sub/authorize-course-index.jsp?cardTypeId='+typeId);
		//$('#dlg').dialog('open');
		
		$('#dlgCourseType').dialog('open')
	}
	
	//选择课程
	function addCourse(typeId){
		$( "#dialogFrame" ).attr('src','${ctx}/page/systemCard/sub/authorize-course-index.jsp?cardTypeId='+typeId);
		$('#dlg').dialog('open');
	}

	//解除授权
	function deleteCourseAuthorized(typeId, courseId){
		if(confirm("确认解除吗?")){
			var p = {'typeId':typeId,'courseId':courseId};
			$.post("${ctx }/system/SystemCardType.do?method=deleteCourseAuthorized", p,
	  			function(data){
	  				if(data >0){
	  					alert("解除成功!");
	  				}
	  				else{
	  					alert("由于网络原因,解除不成功,请稍候再试!");
	  				}
	  				window.location.reload(true);
	  		});
		}
	}

	
	//取得选中IDs
	function getChecked(){
		var nodes = $('#menuTree').tree('getChecked');
		var s = '';
		for(var i=0; i<nodes.length; i++){
			if (s != '') s += ',';
			s += nodes[i].id;
		}
		if(s == ''){
			alert("亲，你还没选择课程分类呢！");
			return;
		}
		//$('#dlgCourseType').dialog( "close" );
		
		//授权
		var typeId = '${typeId}';
		var p = {'cardTypeId':typeId,'courseTypeIds':s};
		
		$.post("${ctx }/system/SystemCardType.do?method=batchCourseAuthorized", p,
  			function(data){
  				if(data >0){
  					alert("授权成功!");
  				}
  				else if(data == 0){
  					alert("由于网络原因,授权不成功,请稍候再试!");
  				}else {
  					alert("该分类下没有要授权的课程!");
  				}
  				window.location.reload(true);
  		});

	}

function doRefresh(){
	window.location.reload(true);
}

</script>
	</head>
	<body bgcolor="#E7E7E7">
		
		<div style="margin: auto; text-align: right;">
		<center>
		
		<form name="queryForm" action="${ctx}/system/SystemCardType.do?method=getStudyCourse" method="post">
		<input type="hidden" id="isAuthorized" name="isAuthorized" value="1" />
		<input type="hidden" id="typeId" name="typeId" value="${typeId }" />
		<table style="font-size:12px;width:95%;text-align: center;" class="ITS" >
		<tr>
			<td>&nbsp;</td><td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>课程名称&nbsp;<input name="courseName" id="courseName" value=""/></td>
			<td><button type="button" class="btn_03s" onclick="javascript:query();" >搜索</button></td>
			<td>&nbsp;</td>
			<td>
				&nbsp;<button type="button" class="btn_03s" onclick="javascript:add('${typeId}');" >课程分类</button>
				&nbsp;<button type="button" class="btn_03s" onclick="javascript:addCourse('${typeId}');" >课程列表</button>
				&nbsp;<button type="button" class="btn_03s" onclick="javascript:doRefresh();" >刷新本页</button>
				&nbsp;<button type="button" class="btn_03s" onClick="window.location.href='${ctx}/system/SystemCardType.do?method=list'" >返回</button>
			</td>
		</tr>
		</table>
		
		<br/>
				<display:table requestURI="${ctx}/system/SystemCardType.do?method=getStudyCourse&isAuthorized=${isAuthorized }&type=${typeId }"
				 id="isStudyCourse" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:95%;" class="ITS" keepStatus="true">
					<display:caption><span style="color:red">${meg}</span></display:caption>
					<display:column title="序号" style="text-align:center">
						${isStudyCourse_rowNum}
					</display:column>
					<display:column property="id" title="ID" style="text-align:center"/>
					<display:column property="studyCourseName" title="课程名称" style="text-align:center" sortable="true"/>
					<display:column property="summary" title="摘要" style="text-align:center" sortable="true"/>
					<display:column property="teacher" title="主讲老师" style="text-align:center" sortable="true"/>
					<display:column property="createDate" title="创建时间" style="text-align:center" sortable="true"/>
					<display:column property="pubDate" title="发布时间" style="text-align:center" sortable="true"/>
					<display:column title="操作" media="html" style="text-align:center">
						<a href="javascript:deleteCourseAuthorized('${typeId }','${isStudyCourse.id }')" >解除授权</a>&nbsp;&nbsp;
					</display:column>
				</display:table>
			</center>
		</div>
		</form>
	
	 <div id="dlg" class="easyui-dialog" style="width:1150px;height:480px" title="授权课程" closed="true">
	  	<iframe src="" width="1100" height="850" id="dialogFrame" name="dialogFrame"></iframe>
	</div>	
	
	 
	<div id="dlgCourseType" class="easyui-dialog" title="查找课程分类" closed="true">
		<div>
			<div style="display: inline-block;width:280px;float:left;">
				<div style="font-size:14px;font-weight:bolder;padding: 0 0 5px 10px;">
				  &nbsp;
				</div>
				<ul id="menuTree" class="easyui-tree" style="float:left;width:97%;margin-left:5px;">	
			</div>
		</div>
	</div>
		
	</body>
</html>
