<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML>
<html>
	<head>
		<link href="${ctx}/css/auth.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/lay.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
		<%@ include file="/commons/taglibs.jsp"%>
		<script type="text/javascript" src="${ctx}/js/prototype.js"></script>

		<link rel="stylesheet" href="${ctx}/js/Validform_v5.3/css/valid.css"
			type="text/css" media="all" />
		<script type="text/javascript"
			src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/Validform_v5.3/js/Validform_v5.3.js"></script>
			
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/bootstrap/easyui.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/icon.css">
		
		<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/locale/easyui-lang-zh_CN.js"></script>
		<title>更新</title>
		<style>
html {
	height: 100%;
}

#budget_div {
	width: 100%;
	height: 100%;
	padding: 8px;
	overflow: auto;
}

#list_div {
	width: 95%;
	height: 95%;
	padding: 8px;
	overflow: auto;
}

#bgdiv {
	position: absolute;
	top: 0px;
	left: 0px;
	width: 100%;
	height: 100%;
	background-color: #AAAAAA;
	z-index: 10;
	filter: alpha(opacity =     80);
	opacity: 0.8;
}

.main_table {
	border: 1px solid #c9c9c9;
}

table {
	margin: 5px 0 5px 0 !important;
}

body {
	width: 100%;
	height: 100%;
	margin: auto;
	padding: 0px;
	font-size: 12px;
	color: #000000;
	text-align: center;
}

.q4all {
	width: 100%;
	height: 94%;
	font-size: 12px;
	background-color: #FFFFFF;
	margin-bottom: 10px;
}

.subnav {
	width: 100%;
	height: 20px;
	background-image: url(../images/XYZY_04.jpg);
	background-repeat: repeat-x;
	padding-top: 4px;
	text-align: center;
}
</style>
		<!--[if IE]>
<style type="text/css">
.q4all { height: 90%; }
</style>
<![endif]-->
		<!--[if gte IE 7]>
<style type="text/css">
.q4all { height: 90%; }
</style>
<![endif]-->
	</head>
	<body
		style="border: 0px solid blue; margin: 0px; padding: 0px; overflow: hidden; float: left;">
		<div class="q4all">
			<div class="subnav">
				&nbsp;站点更新
			</div>
			<table class="main_table" border=0
				style="width: 100%; height: 100%; overflow: hidden;">
				<tr>
					<td width="*" valign="top">
						<div
							style="border: 0px solid black; * padding-bottom: 0px; overflow: hidden;">
							<form class="fm1" id="fm1" name="fm1"
								action="${ctx}/system/systemSite.do?method=update"
								method="post">
						<input type="hidden" id="id" name="model.id" value="${item.id}" />


						<div id="main" style="width: 100%; display: block">
							<table border=0
								style="width: 100%; border: 0px; height: 20px; line-height: 25px; margin-bottom: 0px;"
								cellpadding="3" cellspacing="1">
								<tr valign="top">
									<td style="width: 90%;" align="center">
										<table border=0>
											<tr>
												<td>
													站点域名:
												</td>
												<td>
													<input type="text" style="width: 200;"  id="domainName" name="model.domainName"
														value="${item.domainName}" maxlength="250" size="25"
														 datatype="s1-250" errormsg="请输入站点域名！" /><span style="color:red">*</span>
												</td>
												<td>
													<div class="Validform_checktip"></div>
												</td>
											</tr>
											 <tr>
												<td>
													别名:
												</td>
												<td>
													<input type="text" style="width: 200;"  id="aliasName" name="model.aliasName"
														value="${item.aliasName}" maxlength="250" size="25"
														 datatype="s1-250" errormsg="请输入站点别名！" /><span style="color:red">*</span>
												</td>
												<td>
													<div class="Validform_checktip"></div>
												</td>
											</tr>
											<tr>
												<td>
													备注:
												</td>
												<td>
													<input type="text" style="width: 200;"  id="remark"
														name="model.remark" value="${item.remark}"
														maxlength="25" size="25" ignore="ignore" datatype="*"
														errormsg="请输入备注！" />
												</td>
												<td>
													<div class="Validform_checktip"></div>
												</td>
												 
											</tr>
										</table>
									</td>
								</tr>

								<tr>
									<td style="text-align: center; border-top: 1px solid #c9c9c9;">
										<input type="submit" class="btn_03s" value="保存" />
										<input type="button" class="btn_03s" class="btn_02s" value="返回"
											onclick="window.location='${ctx}/system/systemSite.do?method=list'" />
									</td>
								</tr>
							</table>

						</div>

						</form>
						<div style="clear: both"></div>
						</div>
						<div
							style="border: 0px solid red; padding-top: 0px; width: 100%; height: 100%;">
						</div>
					</td>
				</tr>
			</table>

    <div id="dlg" class="easyui-dialog" title="查找课程分类" closed="true">
		<div>
			<div style="display: inline-block;width:280px;float:left;">
				<div style="font-size:14px;font-weight:bolder;padding: 0 0 5px 10px;">
				  &nbsp;
				</div>
				<ul id="menuTree" class="easyui-tree" style="float:left;width:97%;margin-left:5px;">	
			</div>
		</div>
	</div>
 	  <script type="text/javascript">
		$(function() {
			
	       //弹出层
	       $( "#dlg" ).dialog({
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
							$('#dlg').dialog( "close" );
						} 
						}, { 
						text: '取消', 
						iconCls: 'icon-cancel', 
						handler: function() { 
							$("#dlg").dialog( "close" ); 
						} 
						}] ,
		      close: function() {
		        allFields.val( "" ).removeClass( "ui-state-error" );
		      }
		    });
		    
		    //验证
	       $(".fm1").Validform();

	    })
	    
	    
		 //select 选中已选项
		  $("#clientId ").val(${item.clientId});   
		  $("#applicationId ").val(${item.applicationId});   
		  $("#roleId ").val(${item.roleId}); 
		  
		  //树形
		  $('#menuTree').tree({
		  	checkbox:true,
		  	cascadeCheck:false,//定义是否支持级联选择。
		  	onlyLeafCheck:false,//定义是否只在叶子节点之前显示复选框。
   			url:'${ctx}/course/studyCourseMenu.do',
   			onClick:function(node){
   				/*
				$('#courseType').val(node.id);
				$('#courseTypeHtml').val(node.text);
				$('#dlg').dialog( "close" );*/
   			},
   			onBeforeLoad : function(node, param) {
				if (node) {
					$(this).tree('options').url = '${ctx}/course/studyCourseMenu.do?parentId=' + node.id;
				} else {
					$(this).tree('options').url = '${ctx}/course/studyCourseMenu.do';
				}
			}
   		});  
   		
   		//取得选中IDs
   		function getChecked(){
			var nodes = $('#menuTree').tree('getChecked');
			var s = '';
			var c = '';
			for(var i=0; i<nodes.length; i++){
				if (c != '') c += ' | ';
				if (s != '') s += ',';
				s += nodes[i].id;
				c += nodes[i].text;
			}
			
			$('#courseType').val(s);
			$('#courseTypeHtml').val(c);
		}
   		
	  </script> 
	</body>
</html>
