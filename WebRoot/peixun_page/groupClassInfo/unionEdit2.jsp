<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>课程修改--单元编辑</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    
    <%@include file="/commons/taglibs.jsp"%>
    
    <script type="text/javascript">
       var ctxAdminURL  = '${ctxAdminURL}'  ;
       var ctxPeixunURL = '${ctxPeixunURL}' ; 
       var ctxAllJs = '${ctxAll}' ;
             
    </script>

    <script type="text/javascript" src="<%=path%>/udeitor/third-party/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="<%=path%>/peixun_page/js/utils.js"></script>
	<script type="text/javascript" src="<%=path%>/peixun_page/js/lib/layer/layer.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/udeitor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/udeitor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/udeitor/lang/zh-cn/zh-cn.js"></script>
    <link rel="stylesheet" href="<%=path%>/js/ztree/zTreeStyle.css" type="text/css">
    
	<link rel="stylesheet" href="<%=path%>/peixun_page/css/index.css" />
    <script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.js"></script>
 	<style type="text/css">
 	 <!--强制对应弹框的大小--> 
      #edui171_body{
         width:1200px!important;
         height:500px!important;
      }
      #edui171_content{
         width:1200px!important;
         height:500px!important;
      }
      <!--视频-->
      #edui161_body{        
         width:1200px!important;
         height:500px!important;
      }
      #edui161_content{
         width:1200px!important;
         height:500px!important;

      }
	 <!--多图上传-->
	 #edui149_body{
			 width:1200px!important;
			 height:500px!important;
		 }
	 #edui149_content{
		 width:1200px!important;
		 height:500px!important;

	 }
	 <!--病例-->
	 #edui176_body{
			 width:1200px!important;
			 height:500px!important;
		 }
	 #edui176_content{
		 width:1200px!important;
		 height:500px!important;
	 }

	 #sourceBaseDiv{width:600px;height:400px;position:relative}
	 #sourceBaseDiv #target{width:200px;height:200px;position:absolute;right:0;bottom:0}
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

<div style="width:78%">
	<div style="width:100%;height:50px;margin-left:378px;">
		<a href="javascript:void(0);" id="btn_save" class="mainContent" onclick="getValue();" style="display:none;width:75px;margin-right:15px;height:30px;line-height:30px;text-align:center;border:1px solid #4778c7;color:#4778c7;font-size:12px;-webkit-border-radius:5px;border-radius:5px;-moz-border-radius:5px;float:right;margin-top:12px;">保存</a>
		<a href="javascript:void(0);" id="btn_clear"  class="mainContent" onclick="clearUeditor();" style="display:none;width:75px;margin-right:15px;height:30px;line-height:30px;text-align:center;border:1px solid #4778c7;color:#4778c7;font-size:12px;-webkit-border-radius:5px;border-radius:5px;-moz-border-radius:5px;float:right;margin-top:12px;">清空</a>
	    <a href="javascript:void(0);" class="" onclick="returnHtml();" style="width:75px;margin-right:15px;height:30px;line-height:30px;text-align:center;border:1px solid #4778c7;color:#4778c7;font-size:12px;display:block;-webkit-border-radius:5px;border-radius:5px;-moz-border-radius:5px;float:right;margin-top:12px;">返回</a>
	</div>

	<div id="div_add"  class="tiaojian" style="display:none;width:89%;height:85%;margin-left:378px;">
		<span style="width:9em;text-align:right;margin-bottom: 20px"><em class="red_start">*</em> 扩展阅读：</span>
		<hr  style="margin: 20px">
		<p class="clear" style="margin-bottom:20px;">
			<span style="width:9em;text-align:right;"><em class="red_start">*</em> 阅读说明：</span>
			<textarea type="text" value="" name="extend_read" id="extend_read" placeholder="阅读目的、阅读范围、阅读要求、作业要求说明等，不超过500字"  style="width: 80%;height: 200px;"></textarea>
		</p>
		<p class="clear" style="margin-bottom:20px;">
			<span style="width:9em;text-align:right;"><em class="red_start">*</em>阅读材料：</span>

			<button class="btn_blue"  onClick="javascript:addSource(4);"  type="button" >选择</button>
		</p>
		<input id="chooseSourseIDs" type="hidden" value=""/>
		<input id="cv_unit_Id" type="hidden" value=""/>
		<input id="save_sign" type="hidden" value=""/>
		<div id="sourceBaseDiv" style="min-height:35px;height:auto;margin-left:125px;">

		</div>
		<div id="target">
			<span style="width:9em;text-align:right;margin-bottom: 20px"><em class="red_start">*</em>  达标要求设置：</span>
			<hr  style="margin: 20px">
			<%--<p class="clear" style="margin-bottom:20px;">--%>
				<%--<span style="width:19em;text-align:left;">请选择扩展阅读的完成指标：</span>--%>
			<%--</p>--%>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em>字数不少于：</span>
				<input  name="key_nums" id="key_nums" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();"/> 字
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;">关键字设置：</span>
				<textarea type="text" value="" name="key_words" id="key_words" placeholder="多个关键字直接用;隔开"  style="width: 70%"></textarea>
			</p>
		</div>
	</div>

	<script id="editor" name="editor" class="mainContent" type="text/plain" style="display:none;width:100%;height:500px;margin-left:378px;"></script>
    <!-- <textarea id="editor" name="editor" class="mainContent" style="display:none;width:89%;height:85%;margin-left:378px;"></textarea> -->

</div>
<script type="text/javascript">
        $("#sourceBaseDiv").on("click",".referenceImgDel",function(){
            if ($(".referenceImgDel").length >= 1) {
                this.parentElement.remove() ;
                var bnObj = $(".referenceNumer") ;
                for (var itemXi = 0 ;itemXi < bnObj.length ; itemXi++) {
                    bnObj[itemXi].innerHTML = (itemXi+1) + '.' ;
                }
            }
        });
    // 添加扩展阅读
		function saveExtendRead() {
            var save_sign=$("#save_sign").val();
            if(save_sign==1){
                alert('您已经提交扩展阅读，请勿重复提交！');
                return false;
			}else{
                var cv_unit_Id=$("#cv_unit_Id").val();
                var extend_read=$("#extend_read").val();
                var chooseSourseIDs=$("#chooseSourseIDs").val();
                var key_nums=$("#key_nums").val();
                var key_words=$("#key_words").val();
                if(extend_read==null||extend_read==''){
                    alert('请输入阅读说明！');
                    $("#extend_read").focus();
                    return false;
				}
				if(extend_read.length>500){
                    alert('阅读说明字数超过500！');
                    return false;
				}
                if(chooseSourseIDs==null||chooseSourseIDs==''){
                    alert('请选择阅读材料！');
                    return false;
                }
                if(key_nums==null||key_nums==''){
                    alert('请输入字数！');
                    $("#key_nums").focus();
                    return false;
                }
                $.ajax({
                    type: 'POST',
                    url: "<%=path%>/CVSet/CVManage.do",
                    data : {mode:'addUnionRefSource',cv_unit_Id:cv_unit_Id,extend_read:extend_read,chooseSourseIDs:chooseSourseIDs,key_nums:key_nums,key_words:key_words},
                    dataType: 'json',
                    success : function (data){
                        //解析JSON
                        var result = eval(data);
                        if(result.message=='success'){
                            //设置为防重复提交
                            $("#save_sign").val("1");
                            alert("扩展阅读保存成功.");
                        }else{
                            alert("扩展阅读保存失败.");
                        }
                    },
                    error: function(){
                        alert('数据获取失败.');
                    }
                });
			}
        }
		// 添加扩展阅读
		function clearExtendRead() {
			$("#extend_read").val("");
			$("#chooseSourseIDs").val("");
			$("#key_nums").val("");
			$("#key_words").val("");
            $("#sourceBaseDiv").html("");
            $("#chooseSourseIDs").val("");
		}

        function addSource(soure_type){
            var chooseSourseIDs=$("#chooseSourseIDs").val();
            var chooseSourseBookID=$("#chooseSourseBookID").val();
            var chooseSourseKnowledgeID=$("#chooseSourseKnowledgeID").val();
            var chooseSourseReferenceID=$("#chooseSourseReferenceID").val();
            layer.open({
                type: 2,
                title: "选择来源",
                skin: 'layui-layer-rim', //加上边框
                area: ['60%', '70%'], //宽高
                content: "${ctx}/propManage/sourceList.do?chooseSourseBookID="+chooseSourseBookID
                +"&chooseSourseKnowledgeID="+chooseSourseKnowledgeID
                +"&chooseSourseReferenceID="+chooseSourseReferenceID
                +"&chooseSourseIDs="+chooseSourseIDs
                +"&soure_type="+soure_type
                ,
                // closeBtn: 0,
                btn: ['返回'],
                yes: function (index, layero) {
                    layer.close(index);
                },
                success: function(layerb, index){
                }
            });
        }

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
         if (classId == '') {
            alert('请点击选择左边的课程单元编辑后保存！') ;//YHQ，2017-05-18
            return ;
         }
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
				if(treeNode.parentTId==null){//根结点
					$(".mainContent").hidden();
				}else{
					$(".mainContent").show();
				}
				save();
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
			        		   //清空编辑器内容
							   UE.getEditor('editor').setContent('');
							   //在富文本编辑器中追加html
							   UE.getEditor('editor').execCommand('insertHtml', data.content);
			        	   }else{
			        		   //调用模板弹出层
			        		   UE.getEditor('editor').setContent('');
                               // var dialog = UE.getEditor('editor').getDialog("template");
							   // dialog.render();
							   // dialog.open();
			        	   }
			           }else if(data.message=='noData'){
			        	   //调用模板弹出层
		        		   UE.getEditor('editor').setContent('');
                           // var dialog = UE.getEditor('editor').getDialog("template");
						   // dialog.render();
						   // dialog.open();
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
                    // console.log("treeNode="+JSON.stringify(treeNode));
                    if(treeNode.type=="5"){
                        $("#btn_save").attr("onclick","saveExtendRead();");
                        $("#btn_clear").attr("onclick","clearExtendRead();");
						//
                        $("#cv_unit_Id").val(treeNode.id);
                        $(".tiaojian").show();
                        $(".mainContent").hide();
                        UE.getEditor('editor').setHide();
                        var cv_unit_Id=$("#cv_unit_Id").val();
                        // AJAX加载数据
                        $.ajax({
                            type: 'POST',
                            url: "<%=path%>/CVSet/CVManage.do",
                            data : {mode:'getTargetUnit',cv_unit_Id:cv_unit_Id},
                            dataType: 'json',
                            success : function (data){
                                var result = eval(data);   //解析JSON
                                if(result.message=='success'){
                                    var data=eval(result.sourceBaseList);
                                    var str='';
                                    for(var o=0; o<data.length; o++) {
                                         str+='<span style="display:block;">'
                                            + '<span style="display:inline-block;margin-left:20px;">'+(o+1)+'.来源名称 :'+data[o].name+' <input type="hidden" value="'+data[o].name+'" name="name" class="inpnewInp" style="margin-left:10px;"/></span>'
                                            + '<span style="display:inline-block;margin-left:20px;"> 来源出处:'+ data[o].source+'</span><input type="hidden" value="'+data[o].source+'" name="source" class="inpnewInp" style="margin-left:15px;"/>'
                                            + '<span style="display:inline-block;margin-left:20px;"> 作者: '+data[o].author+'</span><input type="hidden" value="'+data[o].author+'" name="author" class="inpnewInp" style="margin-left:15px;"/>'
                                            + '<span style="display:inline-block;margin-left:20px;"> 出版年限:'+data[o].old+' </span><input type="hidden" value="'+data[o].old+'" name="old" class="inpnewInp" style="margin-left:15px;"/>'
                                            + '</span>';
                                    }
                                    //回写来源选择
                                    $("#sourceBaseDiv").html(str);
                                    //设置为防重复提交
                                    $("#chooseSourseIDs").val(result.chooseSourseIDs);
                                    $("#extend_read").val(result.extend_read);
                                    $("#key_nums").val(result.key_nums);
                                    $("#key_words").val(result.key_words);
                                }
                            },
                            error: function(){
                                alert('数据获取失败.');
                            }
                        });
					}else{
                        $(".mainContent").show();
                        $("#div_add").hide();
                        UE.getEditor('editor').show();
                        demoIframe.attr("src",treeNode.file + ".html");
					}

					return true;
				}
			}
		}
	};

	$(document).ready(function(){

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
                       tree.type = data.tree[i].type;
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

		setCookie("courseEdit_20170817",1,1);

	   
	   	//opener.location.reload();
	   	window.close() ;
	    
		<%-- 
		var pageType = '${pageType}';
		if(pageType!='' && pageType=='class'){
			location.href="<%=path%>/CVSet/CVManage.do?mode=list";
		}else if(pageType!='' && pageType=='project'){
			location.href="<%=path%>/CVSetManage.do?mode=list";
		}  --%>
		//history.back(-1);
	}
	function setCookie(name,value,time){

		var d = new Date();
    	d.setHours(d.getHours() + time);
    	var expires = "expires="+d.toUTCString();
   
    	document.cookie = name + "="+ escape(value) +";"+expires +";path=/";

};
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
				dataType: 'json'
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