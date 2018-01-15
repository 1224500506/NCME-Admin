<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/commons/taglibs.jsp"%>
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
		<title>培训后台</title>
		<link rel="stylesheet" href="${ctx}/peixun_page/css/reset.css" />
		<link rel="stylesheet" href="${ctx}/peixun_page/css/index.css" />
		<link rel="stylesheet" href="${ctx}/peixun_page/css/pickadate/default.css" />
		<link rel="stylesheet" href="${ctx}/peixun_page/css/pickadate/default.date.css" />
		<link rel="stylesheet" href="${ctx}/peixun_page/css/fontawesome/css/font-awesome.css" />
		<link rel="stylesheet" href="${ctx}/peixun_page/css/ueditor.min.css" />
		<script type="text/javascript" src="${ctx}/peixun_page/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/drawWnd.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/layer/layer.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/picker.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/picker.date.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/legacy.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/ueditor/ueditor.config.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/ueditor/ueditor.all.min.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/ueditor/lang/zh-cn/zh-cn.js"></script>
		<style>
			input[type=text]{padding:0 5px;}
			input:disabled{background:#f0f0f0;color:#ccc;}
			.center{margin:15px auto 0;}
		</style>
	</head>
	<%@include file="/peixun_page/top2.jsp"%>
<body>
<!-- 查询条件 -->
<div class="center">
	<form action="${ctx}/talk/topicDiscussionManage.do" id="fm" name="fm" method="post">
	<!--<div class="tiaojian" style="min-height:40px;">
		<p class="fl">
			<span>选择目录：</span>
			 	<input type="hidden" id="curTypeId" name ="type_Ids" value="${type_Ids}"/>
				<input type="hidden" id="typeNames" name="typeName" value="${typeNames}")/>
				<div  id="typeNames01" class="duouan">${typeNames}</div>
		 </p>
		<p class="fl" style="margin-left:60px;">
			<span>试卷名称：</span>
			<input type="text" name="name" id="name" value="${name}" />
		</p>
		<p class="fl" style="margin-left:60px;">
			<span>组卷策略：</span>
			<div class="fl select" >
				<div class="tik_position">
					<b class="ml5">请选择</b>
					<p class="position01"><i class="bjt10"></i></p>
				</div>
				<ul class="fl list" style="display:none;">
					<select name="paperMode" id="paperMode" style="display:none;">
						<option value="">请选择</option>
						<option value="1"<c:if test="${paperMode==1}"> selected</c:if>>快捷策略</option>						
						<option value="2"<c:if test="${paperMode==2}"> selected</c:if>>精细策略</option>
						<option value="4"<c:if test="${paperMode==4}"> selected</c:if>>卷中卷</option>
						<option value="3"<c:if test="${paperMode==3}"> selected</c:if>>手工组卷</option>
					</select>
					<li>请选择</li>
					<li>快捷策略</li>
					<li>精细策略</li>
					<li>卷中卷</li>
					<li>手工组卷</li>
				</ul>
			</div>
		</p>
	</div>
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl" style="">
			<span>创建时间：</span>
			<input type="text" name="createDateFrom" id="createDateFrom" value="${createDateFrom}" onClick="javascript:formatDate('form')" style="width:85px;"/>
			<span style="padding:0px 7px 0px 7px;">至</span>
			<input type="text" name="createDateTo" id="createDateTo" value="${createDateTo}" onClick="javascript:formatDate('to');" style="width:85px;"/>
		</p>
		<div class="fl cas_anniu">
			<a href="javascript:$(fm).submit();" class="fl" style="width:70px;margin-left:130px;">查询</a>
		</div>
	</div>-->
	</form>
</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
		<!--<div class="mt5 kit1_tiaojia">
			<div class="fr cas_anniu">
				<a href="#" class="fl" id = "addPaper-link1" style="width:80px;">添加</a>
				<a href="javascript:window.close();" class="fl" style="width:80px;margin-left:10px;">返回</a>
			</div>
		</div>
		<div class="clear"></div>-->	
		<form action="" id="formB"  method="post">
			<display:table id="item" list="${list}" requestURI="${ctx}/talk/topicDiscussionManage.do" size="300" pagesize="300" style="width:100%;" decorator="com.hys.exam.displaytag.OverOutWrapper" class="mt10 table" excludedParams="method">
				<display:column property="id" title="ID" style="width:10%;text-align:center"></display:column>
				<display:column property="talkContent" title="讨论标题" style="width:20%;text-align:center"></display:column>
				<display:column property="talkAnsy" title="讨论解析" style="width:20%;text-align:center"></display:column>
				<display:column  property="createDate" title="创建时间" style="width:10%;text-align:center"></display:column>
				<display:column title="操作" style="width:40%;text-align:center;">
					<a href="javascript:void(0);" onclick="select('${item.id}');" class="">选择</a>
				</display:column>
			</display:table>
		</form>
		<div class="clear"></div> 
	</div>
</div>
<script type="text/javascript">
	$(function(){
		
		
	});
	
	/*
	 * @author 张建国
	 * @time   2017-02-23
	 * @param  id
	 * 说     明： 选中讨论点
	 */
	 function select(talkId){
		if(talkId!='' && talkId!=null){
			 var html = "<img src=\"${ctxAll}/images/talk.jpg\" width=\"100\" height=\"100\" _url='"+talkId+"' alt=\"talk\" onclick=\"preview("+talkId+",'talk');\">";
		     window.parent.ue.execCommand( 'inserthtml', html );
		     var dialog = window.parent.ue.getDialog("gmap");
		     //判断该单元任务点是否选中
		     if(window.parent.isRWD=='true'){
				//调用父页面的视频完成指标函数
				window.parent.comVideo();
			 }
		     dialog.close();
		}
		
	}
</script>
</body>
</html>