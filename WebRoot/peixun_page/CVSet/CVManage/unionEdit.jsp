<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/commons/taglibs.jsp"%>
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
		<title>培训后台</title>
	<link rel="stylesheet" href="${ctx}/peixun_page/css/ueditor.min.css" />
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/ueditor/lang/zh-cn/zh-cn.js"></script>
	<style>
		.left_cont{width:320px;float:left;background:#ebebeb;padding:0 10px;}
		.left_cont .control_bar{margin:10px 0;}
		.left_cont .control_bar span{display:inline-block;float:left;margin-right:3px;border:1px solid #ccc;background-color:#dbdbdb;font-size:12px;padding:5px;box-sizing:border-box;border-radius:3px;}
		.left_cont .control_bar span.btn_blue{margin-right:0;background-color: #0B92E8;color:#fff;border:0 none;}
		.left_cont .control_bar span.btn_blue a{text-decoration:none;color:#fff;}
		.left_cont h2{font-size:14px;font-weight:600;margin:20px 0;}
		.left_cont ul{list-style:none;}
		.left_cont ul li{line-height:25px;font-size:14px;}
		.right_cont{margin-left:340px;}
	</style>
		<%@include file="/peixun_page/top.jsp"%>
	</head>

<div class="left_cont">
	<div class="control_bar clear">
		<span class="btn_blue"><a href="javascript:window.history.go(-1);">返回</a></span>
		<span>+同级目录</span>
		<span>+子目录</span>
		<span>上移</span>
		<span>下移</span>
		<span>删除</span>
		<span class="btn_blue btn_save"><a href="#">保存</a></span>
		
	</div>

	<!--  <h2><a href="course_create_2.html">课程1：玻璃体切除术在复杂白内障手术中的应用</a></h2>-->
	
	<ul >
		<c:forEach items="${list}" var="p">
			<li class="ml30 li" onclick="javascript:itemClick('${p.id}');">${p.name}</li>
		</c:forEach>
	</ul>
	
</div>
<div class="right_cont">
	<script id="editor" name="content" type="text/plain">

	</script>
</div>

<div class="clear"></div>


<script type="text/javascript">
var str_content ="";
var id;
$(function(){
	var show_tbody = window.localStorage.getItem("subject_clone");
//	var add_course = window.localStorage.getItem("add_course");
	$(".btn_save").click(function () {
			str_content = ue.getContent();
			//window.location.href = "${ctx}/CVSet/CVUnitAdd.do?mode=updateUnion";
			var url='${ctx}/CVSet/CVUnitAdd.do';
			var params = 'mode=updateUnion&id='+id+'&content='+str_content;
			$.ajax({
				type :'POST',
				url : url,
				data :params,
				dataType : 'text',
				success:function(result){
					if(result == 'success')
						alert("成功");
					else
						alert("请选择单元");
				}
			});
			
			
	});
	
	var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
	var submenu = "lc_left0" + menuindex;
	$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
	$('.'+submenu+'').show();
	$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
	
	$('.li').click(function(){		
		$('.li').each(function(){											
			if ($(this).children().html() != null)				
				$(this).html($(this).children().html());												
		});
		
		var strHtml = '<a href="">';
		strHtml += $(this).text();
		strHtml += '</a>';
		$(this).html(strHtml);
		
		
		
	});
	
	var cnt = 0;
	$('.li').each(function(){
		cnt ++;
		$(this).text(cnt + " : " + $(this).text());
	});
	
	
//选择目录弹出框
	$('.duouan').click(function(){
		$('.att_make01').show();
	});
	$('.bjt_kt,.cas_anniu_4').click(function(){
		$('.att_make01').hide();
		$('.att_make02').hide();
		$('.att_make03').hide();
		$('.att_make04').hide();
	});
	//二级
	$('.attr_xueke').click(function(){
		$('.att_make01').hide();
		$('.att_make02').show();
	});
	$('.attri01').click(function(){
		$('.att_make02').hide();
		$('.att_make01').show();
		$('.att_make03').hide();
		$('.att_make04').hide();
		$('.att_make05').hide();
	});
	//三级
	$('.attr_xueke02').click(function(){
		$('.att_make02').hide();
		$('.att_make03').show();
	});
	$('.attri02').click(function(){
		$('.att_make02').show();
		$('.att_make03').hide();
		$('.att_make04').hide();
		$('.att_make05').hide();
	});
	//调整
	$('.tiaozheng').click(function(){
		$('.make02').show();
	});
	$('.queren').click(function(){
		$('.make02').hide();
	});
	$(".left_cont,.right_cont").height($(window).height() - 117);
	$(".right_cont").width($(window).width() - 340);
	$("#editor").css({"width" : $(window).width() - 350 + "px", "height" :$(window).height() - 200 + "px"});
	
	window.ue = UE.getEditor('editor');
		ue.addListener("ready",function(){
			/* ue.setContent('' +
					'<table class="table" style="width:100%;' + '">' +
					'<tr>' +
			'<td width="10%">重点难点</td>' +
			'<td width="60%"></td>' +
			'<td width="30%">' +
			'<span class="fr" style="float:right;">' +
							' 至 ' +
			'</span>' +
			'<span class="fl">' +
			' 任务点&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
			' 原位播放<br />' +
			' 限制快进&nbsp;&nbsp;' +
			' 发放' +
			'</span>' +
			'</td>' +
			'</tr>' +
			'<tr>' +
			'<td width="10%">教学准备</td>' +
			'<td width="60%"></td>' +
			'<td width="30%">' +
			'<span class="fr" style="float:right;">' +
			' 至 ' +
			'</span>' +
			'<span class="fl">' +
			' 任务点&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
			' 原位播放<br />' +
			' 限制快进&nbsp;&nbsp;' +
			' 发放' +
			'</span>' +
			'</td>' +
			'</tr>' +
			'<tr>' +
			'<td width="10%">课题引入</td>' +
			'<td width="60%"></td>' +
			'<td width="30%">' +
			'<span class="fr" style="float:right;">' +
			' 至 ' +
			'</span>' +
			'<span class="fl">' +
			' 任务点&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
			' 原位播放<br />' +
			' 限制快进&nbsp;&nbsp;' +
			' 发放' +
			'</span>' +
			'</td>' +
			'</tr>' +
			'<tr>' +
			'<td width="10%">课堂练习</td>' +
			'<td width="60%"></td>' +
			'<td width="30%">' +
			'<span class="fr" style="float:right;">' +
			' 至 ' +
			'</span>' +
			'<span class="fl">' +
			' 任务点&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
			' 原位播放<br />' +
			' 限制快进&nbsp;&nbsp;' +
			' 发放' +
			'</span>' +
			'</td>' +
			'</tr>' +
			'<tr>' +
			'<td width="10%">课堂小结</td>' +
			'<td width="60%"></td>' +
			'<td width="30%">' +
			'<span class="fr" style="float:right;">' +
			' 至 ' +
			'</span>' +
			'<span class="fl">' +
			' 任务点&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
			' 原位播放<br />' +
			' 限制快进&nbsp;&nbsp;' +
			' 发放' +
			'</span>' +
			'</td>' +
			'</tr>' +
			'<tr>' +
			'<td width="10%">本课作业</td>' +
			'<td width="60%"></td>' +
			'<td width="30%">' +
			'<span class="fr" style="float:right;">' +
			' 至 ' +
			'</span>' +
			'<span class="fl">' +
			' 任务点&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
			' 原位播放<br />' +
			' 限制快进&nbsp;&nbsp;' +
			' 发放' +
			'</span>' +
			'</td>' +
			'</tr>' +
			'<tr>' +
			'<td width="10%">附件资料</td>' +
			'<td width="60%"></td>' +
			'<td width="30%">' +
			'<span class="fr" style="float:right;">' +
			' 至 ' +
			'</span>' +
			'<span class="fl">' +
			' 任务点&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
			' 原位播放<br />' +
			' 限制快进&nbsp;&nbsp;' +
			' 发放' +
			'</span>' +
			'</td>' +
			'</tr>' +
			'</table>' +
		''); */
	});

});

function itemClick(_id){
	id=_id;
	$('#unitTable').show();
	$('.table').show();
	var url="${ctx}/CVSet/CVUnitAdd.do";
	 $.ajax({
		type:'post',
		url:url,
		data:'mode=unitContentEdit&id='+_id,
		dataType:'json',
		success:function(result){			
			var str = result.result[0].content;			
			ue.setContent(str);
			
		}
	}); 
}

</script>
</body>
</html>