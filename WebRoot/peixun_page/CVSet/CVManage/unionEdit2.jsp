<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>完整demo</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript" src="<%=path%>/udeitor/third-party/jquery-1.10.2.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/udeitor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/udeitor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/udeitor/lang/zh-cn/zh-cn.js"></script>
    <link rel="stylesheet" href="<%=path%>/js/ztree/zTreeStyle.css" type="text/css">
    
	<link rel="stylesheet" href="<%=path%>/peixun_page/css/index.css" />
    <script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.js"></script>
    <style type="text/css">
      
    </style>
</head>
<body>
<div class="fixed" style="z-index:1000;">
	<div class="header">
		<div class="fl ml30 mt5 lc_left">
			<img src="<%=path%>/peixun_page/images/logo.png" alt="" />
		</div>
		<div class="fl lc_center">
			<ul>
				<li>能力管理</li>
				<li>项目管理</li>
				<li>考试管理</li>
				<li>学习卡管理</li>
				<li>客户管理</li>
				<li>订单管理</li>
				<li>内容管理</li>
				<li>系统维护</li>
			</ul>
		</div>
		<div class="fr mr20 lc_right">
			<p><i class="fl lc_bjt07"></i><span class="fl"></span></p>
			<div class="clear"></div>
			<div class="guanliyuan"  style="display:none;">
				<a href="#" class="password">修改密码</a>
				<a href="javascript:logout();">退出系统</a>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<!-- 二级 -->
	<div class="lc_er">
		<div class="lc_float lc_left01"  style="display:none;">
			<a href="<%=path%>/quality/userImageManage.do">人物画像</a>
			<a href="<%=path%>/qualityManage/qualityManage.do?method=list">能力模型 </a>
			<a href="<%=path%>/qualityManage/guide.do">学习地图</a>
		</div>
		<div class="lc_float lc_left02"  style="display:none;">
			<a href="<%=path%>/CVSetManage.do?mode=myXiangMu">我的项目</a>
			<a href="<%=path%>/CVSetManage.do?mode=myXueKe">我的学科</a>
			<a href="<%=path%>/CVSetManage.do">项目管理 </a>
			<a href="<%=path%>/CVSetQualify.do?mode=qualify">项目审核 </a>
			<a href="<%=path%>/CVSet/CVDistribute.do?method=list">项目授权</a>
			<a href="<%=path%>/CVSet/CVManage.do?method=list">课程管理</a>
			<a href="<%=path%>/groupManage/groupClassInfoManage.do?type=list">组课管理</a>
		</div>
		<div class="lc_float lc_left03"  style="display:none;">
			<a href="<%=path%>/paperManage/paperList.do">试卷管理</a>
			<a href="<%=path%>/examManage/examList.do">考评管理</a>
		</div>
		<div class="lc_float lc_left04"  style="display:none;">
			<a href="<%=path%>/cardManage/SystemCard.do?method=createCardList">制卡管理</a>
			<a href="<%=path%>/cardManage/SystemCard.do?method=list&s=index">学习卡管理</a>
			<a href="<%=path%>/cardManage/SystemCardType.do?method=list">卡类型管理</a>
			<a href="<%=path%>/cardManage/SystemCard.do?method=allotList">卡类型分配管理</a>
			<a href="<%=path%>/cardManage/SystemCardStatus.do?method=list">卡状态管理</a>
		</div>
		<div class="lc_float lc_left05"  style="display:none;">
			<a href="<%=path%>/system/systemSite.do?method=list">站点设置</a>
			<a href="<%=path%>/system/systemAdminOrgan.do?method=list">继教地区管理</a>
			<a href="<%=path%>/system/systemUser.do?method=list">用户信息</a>
			<a href="<%=path%>/system/SystemPostHistory.do?method=list">快递寄送管理</a>
		</div>
		<div class="lc_float lc_left06"  style="display:none;">
			<a href="<%=path%>/orderManage/OrderManage.do?method=list">订单管理</a>
			
		</div>
		<div class="lc_float lc_left07"  style="display:none;">
			<a href="<%=path%>/contentManage/issueManage.do?method=list">通知公告管理</a>
			<a href="<%=path%>/contentManage/sentenceManage.do?method=list">文章管理</a>
			<a href="<%=path%>/contentManage/editionManage.do?method=list">页面管理</a>
		</div>
		<div class="lc_float lc_left08"  style="display:none;">
			<a href="<%=path%>/system/peixunOrglist.do?method=list">机构管理</a>
			<a href="<%=path%>/system/systemUserStudent.do?method=list&model.userType=1">学员管理</a>
			<a href="<%=path%>/system/editManage.do?method=list&model.userType=2">编辑管理</a>
			<a href="<%=path%>/systemManage/getAccounts.do">账号管理</a>
			<a href="<%=path%>/systemManage/RoleManage.do">角色管理</a>
			<a href="<%=path%>/systemManage/getMenu.do">菜单管理</a>
		</div>
	</div>
	<div class="lc_bjt"></div>
</div>
<div class="gaodu"></div>
<div class="clear"></div>
<div style="width:20%">
	<table border=0 align=left style="background-color:#EBEBEB;height:90%;">
		<tr>
			<td width=360px align=left valign=top style="BORDER-RIGHT: #999999 1px dashed">
				<ul id="tree" class="ztree" style="width:360px; overflow:auto;"></ul>
			</td>
		</tr>
	</table>
</div>
<div style="width:80%">
	<div style="width:100%;height:50px;margin-left:378px;">
		<a href="javascript:void(0);" class="" onclick="getValue();" style="width:75px;margin-right:15px;height:30px;line-height:30px;text-align:center;border:1px solid #4778c7;color:#4778c7;font-size:12px;display:block;-webkit-border-radius:5px;border-radius:5px;-moz-border-radius:5px;float:right;margin-top:12px;">保存</a>
		<a href="javascript:void(0);" class="" onclick="clearUeditor();" style="width:75px;margin-right:15px;height:30px;line-height:30px;text-align:center;border:1px solid #4778c7;color:#4778c7;font-size:12px;display:block;-webkit-border-radius:5px;border-radius:5px;-moz-border-radius:5px;float:right;margin-top:12px;">清空</a>
	    <a href="javascript:void(0);" class="" onclick="returnHtml();" style="width:75px;margin-right:15px;height:30px;line-height:30px;text-align:center;border:1px solid #4778c7;color:#4778c7;font-size:12px;display:block;-webkit-border-radius:5px;border-radius:5px;-moz-border-radius:5px;float:right;margin-top:12px;">返回</a>
	</div>
	<script id="editor" name="editor" type="text/plain" style="width:100%;height:500px;margin-left:378px;"></script>
    <!-- <textarea id="editor" name="editor" style="width:89%;height:85%;margin-left:378px;"></textarea> -->
</div>
<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
    
    var classId = '';
    
    var className = '';
	
	var pIds = "";
	
	var pIdName = "";
	
	var templateType = "";
    
    //获取编辑器信息
    function getValue(){
    	 var content = UE.getEditor('editor').getContent();
    	 if(content!=''){
    	    if(content.indexOf("template_video")>0){
    	    	templateType = "video";
    	    }else if(content.indexOf("template_image")>0){
    	    	templateType = "image";
    	    }else{
    	    	templateType = "other";
    	    }	 
    	    $.ajax({
				type: 'POST',
				url: "<%=path%>/groupManage/groupClassInfoManage.do",
				data : {type:'save',classId:classId,className:className,content:content,pIds:pIds,pIdName:pIdName,templateType:templateType},
				dataType: 'json',
				success : function (data){
		           //解析JSON
		           var result = eval(data);
		           if(result.message=='success'){
		        	   alert("保存成功.");
		           }else{
		        	   alert("保存失败.");
		           }
				},
				error: function(){
					alert('数据获取失败.');
				}
			});
    	 }else{
    		 alert("请先编辑课程内容.");
    	 }
    }
    
    //清空编辑器
    function clearUeditor(){
    	UE.getEditor('editor').setContent("");
    }
    
	var zTree;
	var demoIframe;

	var setting = {
		view: {
			dblClickExpand: false,
			showLine: true,
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: ""
			}
		},
		callback: {
			onClick: function(event, treeId, treeNode){
				classId = treeNode.id;
				className = treeNode.name;
				if(treeNode.name=='1.2话题讨论'){
					classId = '1';
				}
				//通过AJAX获取课程模板信息
				$.ajax({
					type: 'POST',
					url: "<%=path%>/groupManage/groupClassInfoManage.do",
					data : {type:'queryGroup',classId:classId},
					dataType: 'json',
					success : function (data){
			           //解析JSON
			           var result = eval(data);
			           if(data.message=='success'){
			        	   if(data.content!=''){
			        		   //清空编辑器内容
							   UE.getEditor('editor').setContent('');
							   //在富文本编辑器中追加html
							   UE.getEditor('editor').execCommand('insertHtml', data.content);
			        	   }else{
			        		   //调用模板弹出层
			        		   UE.getEditor('editor').setContent('');
			        		   var dialog = UE.getEditor('editor').getDialog("template");
							   dialog.render();
							   dialog.open();
			        	   }
			           }else if(data.message=='noData'){
			        	   //调用模板弹出层
		        		   UE.getEditor('editor').setContent('');
		        		   var dialog = UE.getEditor('editor').getDialog("template");
						   dialog.render();
						   dialog.open();
			           }
					},
					error: function(){
						alert('数据获取失败.');
					}
				});	
			},
			beforeClick: function(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("tree");
				if (treeNode.isParent) {
					zTree.expandNode(treeNode);
					return false;
				} else {
					demoIframe.attr("src",treeNode.file + ".html");
					return true;
				}
			}
		}
	};

	var zNodes =[
		{id:100, pId:0, name:"课程1：玻璃体切除术在复杂白内障手术中的应用", open:true},
		{id:101, pId:1, name:"1.1 玻璃体切除术的概述", file:"core/standardData"},
		{id:102, pId:1, name:"1.2话题讨论", file:"core/simpleData"},
		{id:103, pId:1, name:"1.3随堂测试", file:"core/noline"},
		{id:104, pId:1, name:"1.4术中后囊膜破裂", file:"core/noicon"},
		{id:105, pId:1, name:"1.5书中后囊膜破裂前部切除手术演示", file:"core/custom_icon"},
		{id:106, pId:1, name:"1.6晶状体半脱位手术演示", file:"core/custom_iconSkin"}

		/*{id:2, pId:0, name:"[excheck] 复/单选框功能 演示", open:false},
		{id:201, pId:2, name:"Checkbox 勾选操作", file:"excheck/checkbox"},
		{id:206, pId:2, name:"Checkbox nocheck 演示", file:"excheck/checkbox_nocheck"},
		{id:207, pId:2, name:"Checkbox chkDisabled 演示", file:"excheck/checkbox_chkDisabled"},
		{id:208, pId:2, name:"Checkbox halfCheck 演示", file:"excheck/checkbox_halfCheck"},
		{id:202, pId:2, name:"Checkbox 勾选统计", file:"excheck/checkbox_count"},
		{id:203, pId:2, name:"用 zTree 方法 勾选 Checkbox", file:"excheck/checkbox_fun"},
		{id:204, pId:2, name:"Radio 勾选操作", file:"excheck/radio"},
		{id:209, pId:2, name:"Radio nocheck 演示", file:"excheck/radio_nocheck"},
		{id:210, pId:2, name:"Radio chkDisabled 演示", file:"excheck/radio_chkDisabled"},
		{id:211, pId:2, name:"Radio halfCheck 演示", file:"excheck/radio_halfCheck"},
		{id:205, pId:2, name:"用 zTree 方法 勾选 Radio", file:"excheck/radio_fun"},

		{id:3, pId:0, name:"[exedit] 编辑功能 演示", open:false},
		{id:301, pId:3, name:"拖拽 节点 基本控制", file:"exedit/drag"},
		{id:302, pId:3, name:"拖拽 节点 高级控制", file:"exedit/drag_super"},
		{id:303, pId:3, name:"用 zTree 方法 移动 / 复制 节点", file:"exedit/drag_fun"},
		{id:304, pId:3, name:"基本 增 / 删 / 改 节点", file:"exedit/edit"},
		{id:305, pId:3, name:"高级 增 / 删 / 改 节点", file:"exedit/edit_super"},
		{id:306, pId:3, name:"用 zTree 方法 增 / 删 / 改 节点", file:"exedit/edit_fun"},
		{id:307, pId:3, name:"异步加载 & 编辑功能 共存", file:"exedit/async_edit"},
		{id:308, pId:3, name:"多棵树之间 的 数据交互", file:"exedit/multiTree"},

		{id:4, pId:0, name:"大数据量 演示", open:false},
		{id:401, pId:4, name:"一次性加载大数据量", file:"bigdata/common"},
		{id:402, pId:4, name:"分批异步加载大数据量", file:"bigdata/diy_async"},
		{id:403, pId:4, name:"分批异步加载大数据量", file:"bigdata/page"},

		{id:5, pId:0, name:"组合功能 演示", open:false},
		{id:501, pId:5, name:"冻结根节点", file:"super/oneroot"},
		{id:502, pId:5, name:"单击展开/折叠节点", file:"super/oneclick"},
		{id:503, pId:5, name:"保持展开单一路径", file:"super/singlepath"},
		{id:504, pId:5, name:"添加 自定义控件", file:"super/diydom"},
		{id:505, pId:5, name:"checkbox / radio 共存", file:"super/checkbox_radio"},
		{id:506, pId:5, name:"左侧菜单", file:"super/left_menu"},
		{id:513, pId:5, name:"OutLook 风格", file:"super/left_menuForOutLook"},
		{id:515, pId:5, name:"Awesome 风格", file:"super/awesome"},
		{id:514, pId:5, name:"Metro 风格", file:"super/metro"},
		{id:507, pId:5, name:"下拉菜单", file:"super/select_menu"},
		{id:509, pId:5, name:"带 checkbox 的多选下拉菜单", file:"super/select_menu_checkbox"},
		{id:510, pId:5, name:"带 radio 的单选下拉菜单", file:"super/select_menu_radio"},
		{id:508, pId:5, name:"右键菜单 的 实现", file:"super/rightClickMenu"},
		{id:511, pId:5, name:"与其他 DOM 拖拽互动", file:"super/dragWithOther"},
		{id:512, pId:5, name:"异步加载模式下全部展开", file:"super/asyncForAll"},

		{id:6, pId:0, name:"其他扩展功能 演示", open:false},
		{id:601, pId:6, name:"隐藏普通节点", file:"exhide/common"},
		{id:602, pId:6, name:"配合 checkbox 的隐藏", file:"exhide/checkbox"},
		{id:603, pId:6, name:"配合 radio 的隐藏", file:"exhide/radio"}*/
	];	
	

	$(document).ready(function(){

		var obj = [];

		//获取树结构数据
		$.ajax({
			async: false,
			type: 'POST',
			url: "<%=path%>/groupManage/groupClassInfoManage.do",
			data : {type:'getAJAX',classId:classId},
			dataType: 'json',
			success : function (data){
	           //解析JSON
	           var result = eval(data);
	           if(data.message=='success'){
	        	   for(var i=0;i<data.tree.length;i++){
	        		   var tree = {};
	        		   tree.id = data.tree[i].id;
	        		   tree.name = data.tree[i].name;
	        		   tree.pId = data.tree[i].pId;
	        		   if(data.tree[i].pId==0){
	        			   pIds = data.tree[i].id;
	        			   pIdName = data.tree[i].name;
	        			   tree.pId = data.tree[i].pId;
	        			   tree.open = true; 
	        		   }else{  
	        			   tree.pId = pIds;
	        			   tree.file = 'file:"core/standardData"';
	        		   }
	        		   obj.push(tree);
	        	   }
	           }else if(data.message=='noData'){
	        	   alert('暂无课程数据.');
	           }
			},
			error: function(){
				alert('树结构数据获取失败.');
			}
		});	
		var t = $("#tree");
		t = $.fn.zTree.init(t, setting, obj);
		demoIframe = $("#testIframe");
		demoIframe.bind("load", loadReady);
		var zTree = $.fn.zTree.getZTreeObj("tree");
		zTree.selectNode(zTree.getNodeByParam("id", pIds));

		$('.lc_center>ul>li').mousemove(function(){
			currentThis = this;
				$(this).addClass('action').siblings('.action').removeClass('action');
				var n=$(this).index();
				$('.lc_er div').eq(n).show().siblings().hide();
			});
			$('.lc_right p').mousemove(function(){
				$('.guanliyuan').show();
			});
			$('.lc_er').mouseleave(function(){
				$('.guanliyuan').hide();
			});
			$('.bjs').mouseleave(function(){
				$('.lc_er>div>a').each(function(key,val){
					if($(this).attr("class") == "action")
					{
						var n = $(this).parent().index();
						$('.lc_center>ul>li').removeClass('action');
					    $('.lc_center>ul>li').eq(n).addClass('action');
						$('.lc_er div').eq(n).show().siblings().hide();
					}
				});
			});
			
			$('.wrap').mousemove(function(){
				$(currentThis).removeClass('action').siblings('.action').removeClass('action');
				var n=$(currentThis).index();
								
				$('.lc_er div').eq(n).hide().siblings().hide();
			});
		
	});

	function loadReady() {
		var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
		htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
		maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
		h = demoIframe.height() >= maxH ? minH:maxH ;
		if (h < 530) h = 530;
		demoIframe.height(h);
	}
	
	//返回
	function returnHtml(){
		history.back(-1);
	}

</script>
</body>
</html>