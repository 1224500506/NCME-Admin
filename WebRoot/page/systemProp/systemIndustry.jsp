<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>资源管理系统</title>
		<%@include file="/commons/taglibs.jsp"%>
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/public.css">
		<script type="text/javascript" src="${ctx}/js/UI/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/locale/easyui-lang-zh_CN.js"></script>
	</head>
	<body>
		<!--main-->
		<div class="main">
			<!--栏目标题-->
			<div class="title">
				<!--左标题-->
				<div class="title_left">
					<ul>
						
						<li>
							<a href="${ctx }/system/SystemIndustry.do?method=list">系统属性</a> &gt; 行业岗位
						</li>
					</ul>
				</div>
			</div>
			<div><strong><span style="color:red">${meg}</span></strong></div>
			<!--功能选项-->
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="gltop_btn">
				<tr>
					<td align="right">
						<!--右按钮-->
						<table border="0" cellspacing="5" cellpadding="0"
							class="gltop_btn_right">
							<tr>
								<td>
									<button target="_self" onClick="add()" style="cursor:pointer">
										<span class="tabletitlebtn_bluelongtwo">添加一级行业</span>
									</button>
								</td>
								
							</tr>
						</table>
						<!--右按钮结束-->
					</td>
				</tr>
			</table>

			<table id="industry" name="industry" width="90%"></table>
		</div>
		<div id="dlg" class="easyui-dialog" title="系统属性" closed="true">
			<iframe src="" width="450" height="300" id="dialogFrame" name="dialogFrame" frameborder="0"></iframe>
		</div>
		<!--main结束-->
		<br/>
		<br/>
		<br/>
		<br/>
		
	</body>
</html>
<script>

	$(function() {
		$('#industry').treegrid({
			title : '行业岗位',
			nowrap : true,
			animate : true,
			collapsible : true,
			fitColumns: true,
			idField : 'id',
			treeField : 'industryName',
			pagination : false,
			columns : [ [{
				field : 'id',
				align:'left',
				title : 'ID',
				width : 50
			}, {
			    field : 'industryName',
				align:'left',
				title : '岗位名称',
				width : 250
			}, {
				field : 'xx',
				align:'left',
				title : '操作',
				width : 415,
				align:'center',
				formatter:function(value, row, index){
					if(row.level == 1){
						return "<button target='_self' style=\"cursor:pointer\" onClick='addSub("+row.id+")' width='400' height='232' border='0'><span class='tablebtn_bluelong'>添加二级行业</span></button>&nbsp;&nbsp;"+
								"<button target='_self' style=\"cursor:pointer\" onClick='update("+row.id+","+row.level+")' width='400' height='232' border='0'><span class='tablebtn_yellow'>修改</span></button>&nbsp;&nbsp;"+
								"<button style=\"cursor:pointer\" onclick='deletePorp(\""+row.id+"\")'><span class='tablebtn_red'>删除</span></button>";
					}else if(row.level == 2){
						return "<button target='_self' style=\"cursor:pointer\" onClick='viewAbility("+row.id+")' width='400' height='232' border='0'><span class='tablebtn_bluelong'>查看行业能力</span></button>&nbsp;&nbsp;"+
								"<button target='_self' style=\"cursor:pointer\" onClick='update("+row.id+","+row.level+")' width='400' height='232' border='0'><span class='tablebtn_yellow'>修改</span></button>&nbsp;&nbsp;"+
								"<button style=\"cursor:pointer\" onclick='deletePorp(\""+row.id+"\")'><span class='tablebtn_red'>删除</span></button>";
					}
					
				}
			} ] ],
            onBeforeLoad:function(row,param){
                if (row){
                    $(this).treegrid('options').url = '${ctx}/system/SystemIndustry.do?method=subList&parentId='+row.id;
                } else {
                    $(this).treegrid('options').url = '${ctx}/system/SystemIndustry.do?method=subList';
                }
            }
		});
	});

	//删除行业
	//var retdata = $.ajax({url: "${ctx}/course/docType/getChild?id=" + treeNode.id, async: false}).responseText;
	function deletePorp(id) {
    	var childSize = 0;
	    $.ajaxSetup({async: false});
	    $.ajax({
			url: "${ctx}/system/SystemIndustry.do?method=getSubCount&parentId="+id,
			type:'get',
			dataType:'text',
			success:function(data){
			  childSize = data;
			}
	    });
	    $.ajaxSetup({async: true});

		if(childSize > 0){
		  alert('请先删除子节点后再删除此节点!');
		  return;
		}

	    if (confirm("确认删除!")) {
		  window.location = "${ctx}/system/SystemIndustry.do?method=delete&id=" + id;
		}
	}

	//添加一级行业
	function add(){
	  $( "#dialogFrame" ).attr("src","${ctx}/system/SystemIndustry.do?method=add&level=1");

 	  var top = $(window).scrollTop() ;
	  var innerWidth = $(window).innerWidth() / 2 ;
	  		//弹出层
	    $( "#dlg" ).dialog({
		  autoOpen: false,
	      height: 340,
	      width: 480,
	      modal: true,//true遮蔽
	      top:top + 70,
	      left:innerWidth - 140,
	      iconCls:'icon-search', //左上角图标,icon文件夹下图标
	      close: function() {
	        allFields.val( "" ).removeClass( "ui-state-error" );
	      }
	    });

	  $("#dlg").dialog("open");
	}

	//添加二级行业
	function addSub(id){
	  $( "#dialogFrame" ).attr("src","${ctx}/system/SystemIndustry.do?method=add&level=2&parentId=" + id);
 	  var top = $(window).scrollTop() ;
	  var innerWidth = $(window).innerWidth() / 2 ;
	  		//弹出层
	    $( "#dlg" ).dialog({
		  autoOpen: false,
	      height: 340,
	      width: 480,
	      modal: true,//true遮蔽
	      top:top + 70,
	      left:innerWidth - 140,
	      iconCls:'icon-search', //左上角图标,icon文件夹下图标
	      close: function() {
	        allFields.val( "" ).removeClass( "ui-state-error" );
	      }
	    });
	  $("#dlg").dialog("open");
	}

	//关闭弹出层
	function dialogClose(){
	  $("#dlg").dialog( "close" );
	}

	//修改行业
	function update(id,level){
	  $( "#dialogFrame" ).attr("src","${ctx}/system/SystemIndustry.do?method=update&id=" + id+"&level="+level);

 	  var top = $(window).scrollTop() ;
	  var innerWidth = $(window).innerWidth() / 2 ;
	  		//弹出层
	    $( "#dlg" ).dialog({
		  autoOpen: false,
	      height: 340,
	      width: 480,
	      modal: true,//true遮蔽
	      top:top + 70,
	      left:innerWidth - 140,
	      iconCls:'icon-search', //左上角图标,icon文件夹下图标
	      close: function() {
	        allFields.val( "" ).removeClass( "ui-state-error" );
	      }
	    });

	  $("#dlg").dialog("open");
	}
	
	//查看行业下的能力
	function viewAbility(id){
		var url = "${ctx}/system/SystemIndustryAbility.do?method=list&industryId="+id;
		////window.open(url);
		window.location.href=url;
	}
</script>