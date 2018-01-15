<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>完整demo</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <%@include file="/commons/taglibs.jsp"%>
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
<table border=0 align=left style="background-color:#EBEBEB;height:90%;">
	<tr>
		<td width=360px align=left valign=top style="BORDER-RIGHT: #999999 1px dashed">
			<ul id="tree" class="ztree" style="width:360px; overflow:auto;"></ul>
		</td>
	</tr>
</table>
<div>
<div style="width:100%;height:50px;">
	<%--
	<a href="javascript:void(0);" class="" onclick="getValue();" style="width:75px;margin-right:15px;height:30px;line-height:30px;text-align:center;border:1px solid #4778c7;color:#4778c7;font-size:12px;display:block;-webkit-border-radius:5px;border-radius:5px;-moz-border-radius:5px;float:right;margin-top:12px;">保存</a>
	<a href="javascript:void(0);" class="" onclick="clearUeditor();" style="width:75px;margin-right:15px;height:30px;line-height:30px;text-align:center;border:1px solid #4778c7;color:#4778c7;font-size:12px;display:block;-webkit-border-radius:5px;border-radius:5px;-moz-border-radius:5px;float:right;margin-top:12px;">清空</a>
	 --%>
    <a href="javascript:void(0);" class="" onclick="returnHtml();" style="width:75px;margin-right:15px;height:30px;line-height:30px;text-align:center;border:1px solid #4778c7;color:#4778c7;font-size:12px;display:block;-webkit-border-radius:5px;border-radius:5px;-moz-border-radius:5px;float:right;margin-top:12px;">返回</a>
</div>
	<script id="editor" name="editor" type="text/plain" style="width:89%;height:500px;margin-left:378px;"></script>
    <!-- <textarea id="editor" name="editor" style="width:89%;height:85%;margin-left:378px;"></textarea> -->
</div>
<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
  
  
    var classId = '';
    
    var className = '';
	
	var pIds = '';
	
	var pIdName = '';
	
	var templateType = '';
	
	//完成指标
	var compIndex='' ;
	
	//单元是否选中任务点
	var isRWD = '';
	
	if('${chekPointIds}'!='Null'){
		var chekPointIds = '${chekPointIds}';
	}else{
		var chekPointIds = '';
	}

	//获取编辑器信息
    function getValue(){
    	 var content = UE.getEditor('editor').getContent();
    	 //if(content!=''){
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
				data : {type:'save',classId:classId,className:className,content:content,pIds:pIds,pIdName:pIdName,templateType:templateType,compIndex:compIndex},
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
    	// }else{
    	//	 alert("请先编辑课程内容.");
    	// }
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
				//save();
				classId = treeNode.id;
				className = treeNode.name;
				if(treeNode.name=='1.2话题讨论'){
					classId = '1';
				}
				//通过AJAX获取课程模板信息
				$.ajax({
					async : false,
					type: 'POST',
					url: "<%=path%>/groupManage/groupClassInfoManage.do",
					data : {type:'queryGroup',classId:classId},
					dataType: 'json',
					success : function (data){	
			           //解析JSON
			           var result = eval(data);
			           if(data.message=='success' || data.message=='noData'){
			        	   if(data.content!=''){
			        		   ue.setEnabled([]);
			        		   //清空编辑器内容
							   UE.getEditor('editor').setContent('');
							   //在富文本编辑器中追加html
							   UE.getEditor('editor').execCommand('insertHtml', data.content);
							   //scp不可编辑
							   ue.setDisabled([]);
			        	   }else{
			        		   ue.setEnabled([]);
			        		 //清空编辑器内容
							   UE.getEditor('editor').setContent('');
							   //在富文本编辑器中追加html
							   UE.getEditor('editor').execCommand('insertHtml', data.content);
							   //scp不可编辑
							   ue.setDisabled([]);
			        	   }
			           }else if(data.message=='noData'){
			        	   ue.setEnabled([]);
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
				
				//判断该单元是否选中任务点
				   if(chekPointIds!=null && chekPointIds!=''){
					   if(chekPointIds==classId){
						   isRWD = "true";
					   }else{
						   var point = chekPointIds.split(";");
						   for(var i = 0; i <point.length;i++){
							   if(point[i]==classId){
								   isRWD = "true";
								   break;
							   }else{
								   isRWD = "false";
							   }
						   }
					   }
				   }else{
					   isRWD = "false";
				   } 
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

	$(document).ready(function(){
alert(123);
		var obj = [];
		
		//获取树结构数据
		$.ajax({
			async: false,
			type: 'POST',
			url: "<%=path%>/groupManage/groupClassInfoManage.do",
			data : {type:'getAJAX',classId:'${ids}'},
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
	    window.close();
		<%-- 
		var pageType = '${pageType}';
		if(pageType!='' && pageType=='class'){
			location.href="<%=path%>/CVSet/CVManage.do?mode=list";
		}else if(pageType!='' && pageType=='project'){
			location.href="<%=path%>/CVSetManage.do?mode=list";
		}  --%>
		//history.back(-1);
	}
	
	//视频完成指标
	function comVideo(){
		setTimeout(comVideoSetTimeout, 1000);
	}
	
	function comVideoSetTimeout(){
		compIndex = prompt("请输入视频的完成指标(数字).");
		if(compIndex==null || compIndex==''){
			alert("视频指标不允许为空,请重新输入");
			comVideo();
		}else{
			//监测是否为数字类型
			if(isNaN(compIndex)){
				alert("完成指标必须为数字,请重新输入");
				comVideo();
			}else if(compIndex==0){
				alert("完成指标必须大于0,请重新输入");
				comVideo();
			}
                        
                        //YHQ 2017-03-04，更新课程单元的评分
                        if(classId == null || classId ==''){
                            alert("请选择课程单元");
                            return  ;
                        }
                        $.ajax({
                                async : false,
                                type: 'POST',
                                url: "${ctx}/groupManage/groupClassInfoManage.do?type=saveUnitQuota&unitId=" + classId + "&quotaVal=" + compIndex,                                
                                dataType: 'json',
                                success : function (data){
			           var result = eval(data);
			           if(result.message =='success'){			        	   
			           }else {
                                       alert("保存视频指标失败,请重新输入") ;
                                       comVideo();
			           }
                                },
                                error: function(){
                                        alert('保存视频指标时出现异常,请重新输入');
                                        comVideo();
                                }
			});
		}
	}
	
	
	function comPaper(){
		setTimeout(comPaperSetTimeout, 1000);
	}
	
	function comPaperSetTimeout(){
		compIndex = prompt("请输入随堂测验的完成指标(数字).");
		if(compIndex==null || compIndex==''){
			alert("随堂测验指标不允许为空,请重新输入");
			comPaper();
		}else{
			//监测是否为数字类型
			if(isNaN(compIndex)){
				alert("完成指标必须为数字,请重新输入");
				comPaper();
			}else if(compIndex==0){
				alert("完成指标必须大于0,请重新输入");
				comPaper();
			}
                        
                        //YHQ 2017-03-04，更新课程单元的评分
                        if(classId == null || classId ==''){
                            alert("请选择课程单元");
                            return  ;
                        }
                        $.ajax({
                                async : false,
                                type: 'POST',
                                url: "${ctx}/groupManage/groupClassInfoManage.do?type=saveUnitQuota&unitId=" + classId + "&quotaVal=" + compIndex,                                
                                dataType: 'json',
                                success : function (data){
			           var result = eval(data);
			           if(result.message =='success'){			        	   
			           }else {
                                       alert("保存随堂测验指标失败,请重新输入") ;
                                       comPaper();
			           }
                                },
                                error: function(){
                                        alert('保存随堂测验指标时出现异常,请重新输入');
                                        comPaper();
                                }
			});
		}
	}
	
	function comBingli(){
		setTimeout(comBingliSetTimeout, 1000);
	}
	
	function comBingliSetTimeout(){
		compIndex = prompt("请输入病例讨论的完成指标(数字).");
		if(compIndex==null || compIndex==''){
			alert("病例讨论指标不允许为空,请重新输入");
			comBingli();
		}else{
			//监测是否为数字类型
			if(isNaN(compIndex)){
				alert("完成指标必须为数字,请重新输入");
				comBingli();
			}else if(compIndex==0){
				alert("完成指标必须大于0,请重新输入");
				comBingli();
			}
                        
                        //YHQ 2017-03-04，更新课程单元的评分
                        if(classId == null || classId ==''){
                            alert("请选择课程单元");
                            return  ;
                        }
                        $.ajax({
                                async : false,
                                type: 'POST',
                                url: "${ctx}/groupManage/groupClassInfoManage.do?type=saveUnitQuota&unitId=" + classId + "&quotaVal=" + compIndex,                                
                                dataType: 'json',
                                success : function (data){
			           var result = eval(data);
			           if(result.message =='success'){			        	   
			           }else {
                                       alert("保存病例讨论指标失败,请重新输入") ;
                                       comBingli();
			           }
                                },
                                error: function(){
                                        alert('保存病例讨论指标时出现异常,请重新输入');
                                        comBingli();
                                }
			});
		}
	}
	
	function comTaolun(){
		setTimeout(comTaolunSetTimeout, 1000);
	}
	
	function comTaolunSetTimeout(){
		compIndex = prompt("请输入主题讨论的完成指标(数字).");
		if(compIndex==null || compIndex==''){
			alert("主题讨论指标不允许为空,请重新输入");
			comTaolun();
		}else{
			//监测是否为数字类型
			if(isNaN(compIndex)){
				alert("完成指标必须为数字,请重新输入");
				comTaolun();
			}else if(compIndex==0){
				alert("完成指标必须大于0,请重新输入");
				comTaolun();
			}
                        
                        //YHQ 2017-03-04，更新课程单元的评分
                        if(classId == null || classId ==''){
                            alert("请选择课程单元");
                            return  ;
                        }
                        $.ajax({
                                async : false,
                                type: 'POST',
                                url: "${ctx}/groupManage/groupClassInfoManage.do?type=saveUnitQuota&unitId=" + classId + "&quotaVal=" + compIndex,                                
                                dataType: 'json',
                                success : function (data){
			           var result = eval(data);
			           if(result.message =='success'){			        	   
			           }else {
                                       alert("保存病例讨论指标失败,请重新输入") ;
                                       comBingli();
			           }
                                },
                                error: function(){
                                        alert('保存病例讨论指标时出现异常,请重新输入');
                                        comBingli();
                                }
			});
		}
	}
                
	function save(){
   	 var content = UE.getEditor('editor').getContent();
   	 //if(content!=''){
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
				data : {type:'save',classId:classId,className:className,content:content,pIds:pIds,pIdName:pIdName,templateType:templateType,compIndex:compIndex},
				dataType: 'json',
			});
   	// }else{
   	//	 alert("请先编辑课程内容.");
   	// }
   }

	
	window.onload =function(){
	
		 $("#editor").find("#edui185").click(function(){
			 
			$("#edui181").find("#edui183_state").hide();
			$("#edui184").find("#edui184_state").hide();
		 });
		
	};
	 
</script>
</body>
</html>