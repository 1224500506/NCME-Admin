<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/commons/taglibs.jsp"%>
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
	
		<title>培训后台</title>
		<%@include file="/peixun_page/top.jsp"%>
	</head>
<body>
<!-- 头部 -->

<div class="clear"></div>
<div class="bjs"></div>

<div class="top_cont">
	
	<h2>玻璃体切除术在复杂白内障手术中的应用</h2>
</div>
<div class="sub_nav" >
	<span class="btn_blue fr btn_no btn_save"><a href="javascript:void(0);">保存</a></span>
	<span class="btn_blue fr"><a href="${ctx}/CVSet/CVManage.do?mode=edit" >返回</a></span>

	<span class="add_course btn_no"><a href="javascript:void(0);">添加单元</a></span>
	<span class="btn_no"><a href="javascript:void(0);">删除单元</a></span>
	<span class="btn_no"><a href="javascript:void(0);">上移</a></span>
	<span class="btn_no"><a href="javascript:void(0);">下移</a></span>
	<span class="btn_blue next_btn" style="display:none;"><a href="course_edit_project.html">编辑</a></span>
</div>
<div class="center" style="">
	<div class="center01">
		<button class="btn_blue clone_course btn_no" type="button" >克隆课程</button>
		<div class="clear" style="margin-bottom:20px;"></div>
		<div class="left_cont">
			<h2 class="catalog">目录</h2>

			<table class="mt10 table" style="display:none;">
				<thead>
					<tr>
						<th width="10%">序号</th>
						<th width="40%">课题</th>
						<th width="20%">类别</th>
						<th width="15%">任务点</th>
						<th width="15%">完成状态</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1.1</td>
						<td style="text-align:left;">玻璃体切除术的概述</td>
						<td>理论讲解</td>
						<td><input class="task_point" type="checkbox" checked /></td>
						<td><input class="task_complete" type="checkbox" checked /></td>
					</tr>
					<tr>
						<td>1.2</td>
						<td style="text-align:left;">临床实践中，您认为有哪些措施能够更好预防或者处理白内障手术并发症？</td>
						<td>主题讨论</td>
						<td><input class="task_point" type="checkbox" /></td>
						<td><input class="task_complete" type="checkbox" /></td>
					</tr>
					<tr>
						<td>1.3</td>
						<td style="text-align:left;">随堂考试</td>
						<td>随堂考试</td>
						<td><input class="task_point" type="checkbox" /></td>
						<td><input class="task_complete" type="checkbox" /></td>
					</tr>
					<tr>
						<td>1.4</td>
						<td style="text-align:left;">术中后囊膜破裂</td>
						<td>理论讲解</td>
						<td><input class="task_point" type="checkbox" /></td>
						<td><input class="task_complete" type="checkbox" /></td>
					</tr>
					<tr>
						<td>1.5</td>
						<td style="text-align:left;">术中后囊膜破裂前部玻切手术演示</td>
						<td>操作演示</td>
						<td><input class="task_point" type="checkbox" /></td>
						<td><input class="task_complete" type="checkbox" /></td>
					</tr>
					<tr>
						<td>1.6</td>
						<td style="text-align:left;">结合阅读书籍以及临床实践论述玻切手术的要点及注意事项</td>
						<td>扩展阅读</td>
						<td><input class="task_point" type="checkbox" /></td>
						<td><input class="task_complete" type="checkbox" /></td>
					</tr>
					<tr>
						<td>1.7</td>
						<td style="text-align:left;">玻璃体切除术实践分享</td>
						<td>病例分析</td>
						<td><input class="task_point" type="checkbox" /></td>
						<td><input class="task_complete" type="checkbox" /></td>
					</tr>
				</tbody>
			</table>
			<div class="add_union" style="display:none;padding:20px;">
				<div class="tiaojian">
					<p class="clear" style="margin-bottom:20px;">
						<span class="fl" style="width:6em;text-align:right;">单元名称：</span>
						<input type="text" name="union_name"/>
					</p>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:6em;text-align:right;">教学类型：</span>
					</p>
					<div class="fl select" style="margin:0 0 20px 0;">
						<div class="tik_position">
							<b class="ml5">请选择</b>
							<p class="position01"><i class="bjt10"></i></p>
						</div>
						<ul class="fl list" style="display: none;">
							<li>理论讲解</li>
							<li>主题讨论</li>
							<li>随堂考试</li>
							<li>操作演示</li>
							<li>扩展阅读</li>
							<li>病例分享</li>
						</ul>
					</div>
					<div class="clear"></div>
					<p><span style="width:6em;">&nbsp;</span><input type="checkbox" name="task_point" /> 任务点 </p>
					<p class="clear">
						<span style="width:6em;">&nbsp;</span>
						<input type="button" name="btn_confirm" class="btn_blue btn_confirm" value="确定" />
						<input type="button" name="btn_reset" class="btn_reset" value="取消" />
					</p>
				</div>
			</div>
		</div>
		<div class="right_cont" style="display:none;">
			<h2>关联能力模型</h2>
			<div style="display:none;" class="ability_area ability_area_2">
				<h3>二级能力</h3>
				<h4>1级心血管外科主任 > 专业技能 > </h4>
				<div class="tiaojian level_p">
					<p class="fl" style="margin-right:20px;">
						<input type="checkbox" class="fl" checked /> <span>精通临床操作技能</span> <i class="fa fa-caret-right"></i>
					</p>
					<p class="fl">
						<input type="checkbox" class="fl" checked /> <span>诊断能力和治疗步骤</span> <i class="fa fa-caret-right"></i>
					</p>
				</div>
			</div>
			<div style="display:none;" class="ability_area ability_area_3">
				<h3>三级能力</h3>
				<h4>1级心血管外科主任 > 专业技能 > 精通临床操作技能</h4>
				<div class="tiaojian level_p">
					<p class="fl" style="margin-right:20px;">
						<input type="checkbox" class="fl" checked /> <span>临床电生理性检查</span> <i class="fa fa-caret-right"></i>
					</p>
					<p class="fl">
						<input type="checkbox" class="fl" checked /> <span>动态血压监测</span> <i class="fa fa-caret-right"></i>
					</p>
					<p class="fl" style="margin-right:20px;">
						<input type="checkbox" class="fl" checked /> <span>心脏病的影响学检查</span> <i class="fa fa-caret-right"></i>
					</p>
					<p class="fl">
						<input type="checkbox" class="fl" checked /> <span>超声心动图</span> <i class="fa fa-caret-right"></i>
					</p>
					<p class="fl" style="margin-right:20px;">
						<input type="checkbox" class="fl" checked /> <span>有创性检查、治疗</span> <i class="fa fa-caret-right"></i>
					</p>
					<p class="fl">
						<input type="checkbox" class="fl" checked /> <span>完成手术切口及并发症的管理</span> <i class="fa fa-caret-right"></i>
					</p>
					<div class="clear"></div>
					<p class="clear cas_anniu center" style="margin-top:30px;">
						<a href="javascript:void(0);" class="btn_back" style="width:70px;">返回</a>
					</p>
				</div>
			</div>
			<div style="display:none;" class="ability_area ability_area_4">
				<h3>四级能力</h3>
				<h4>1级心血管外科主任 > 专业技能 > 精通临床操作技能 > 临床电生理性检查</h4>
				<div class="tiaojian">
					<p class="fl" style="margin-right:20px;">
						<input type="checkbox" class="fl"  /> <span>掌握心电图检查的各导联位置</span>
					</p>
					<p class="fl">
						<input type="checkbox" class="fl"  /> <span>完成12导心电图描记</span>
					</p>
					<p class="fl" style="margin-right:20px;">
						<input type="checkbox" class="fl"  /> <span>动态心电监测</span>
					</p>
					<p class="fl">
						<input type="checkbox" class="fl"  /> <span>运动试验</span>
					</p>
					<p class="fl">
						<input type="checkbox" class="fl"  /> <span>典型心电图的诊断</span>
					</p>
					<div class="clear"></div>
					<p class="clear cas_anniu center">
						<a href="javascript:void(0);" class="btn_back" style="width:70px;">返回</a>
					</p>
				</div>
			</div>
		</div>

		<div class="clear"></div>
	</div>
</div>

<script type="text/javascript">
$(function(){

	var show_tbody = window.localStorage.getItem("subject_clone");
	if (show_tbody != null && (show_tbody == 1 || show_tbody == 2 || show_tbody == 3)){
		$(".table").show();
	}else{
		$(".table").hide();
	}

	var add_course = window.localStorage.getItem("add_course");
	if (add_course != null && add_course == 1){
		$(".table,.next_btn").show();
	}else{
		$(".table,.next_btn").hide();
	}

	var p_course = window.localStorage.getItem("project_course");
	/* $(".btn_save").click(function () {
		if (p_course != null && p_course == 1){
			window.location.href = "course_clone.html";
		}else{
			window.location.href = "subject_list.html";
		}
	}); */

	$(".table tbody tr").click(function(){
		window.localStorage.setItem("add_ability","1");
		$(".right_cont,.ability_area_2").show();
		$(".ability_area_3,.ability_area_4").hide();
	});
	$(".level_p p span,.level_p p i").click(function () {
		$(this).parent().parent().parent().hide().next(".ability_area").show();
	});

	$(".btn_back").click(function () {
		$(this).parent().parent().parent().hide().prev(".ability_area").show();
	});


	$(".add_course").click(function(){
		$(".add_union").show();
		$(".table").hide();
		$(".right_cont").hide();
	});
	$(".btn_confirm").click(function(){
		$('.add_union').hide();
		$(".table").show();
		$(".next_btn").show();
		window.localStorage.setItem("add_course","1");
	});
	$(".btn_reset").click(function(){
		$('.add_union').hide();
	});
	$(".table tbody tr").click(function(){
		$(".ability_area_2").show();
		$(".next_btn").show();
	});

	$(".next_btn").click(function(){
		window.localStorage.setItem("subject_clone","2");
	});
	$(".clone_course").click(function () {
		window.localStorage.setItem("subject_clone","1");
		window.location.href="course_clone.html";
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


	var win_w = parseInt($(window).width() / 3) + "px";
	var win_h = parseInt($(window).height() / 1.5) + "px";
	$(".portraits_input").focus(function(){
		var sub_cont = '<div class="tiaojian" style="min-height:40px;padding:20px;">' +
				'<p class="fl" style="width:70%;margin:0 20px 20px 0;"><textarea style="width:100%;" rows="5" class="fl" name=""></textarea></p>' +
				'<p class="fl cas_anniu"><a href="javascript:void(0);" class="fl btn1" style="width:70px;">确定</a></p>' +
				'<p class=clear" style="margin-bottom:10px;">' +
				'<div class="fl select">' +
				'<div class="tik_position">' +
				'<b class="ml5">请选择学科</b>' +
				'<p class="position01"><i class="bjt10"></i></p>' +
				'</div>' +
				'<ul class="list" style="display:none;">' +
				'<li>心血管病学</li>' +
				'<li>骨外科学</li>' +
				'<li>泌尿外科学</li>' +
				'<li>神经外科学</li>' +
				'<li>烧伤外科学</li>' +
				'<li>心胸外科学</li>' +
				'</ul>' +
				'</div>' +
				'</p>' +
				'<div class="clear"></div>' +
				'<div class="clear" style="border: 1px solid #cfcfcf;min-height:100px;padding:10px;margin-top:10px;line-height:25px;">' +
				'<input type="checkbox" />1级心血管主任<br />' +
				'<input type="checkbox" />2级心血管主任<br />' +
				'<input type="checkbox" />3级心血管主任<br /><br />' +
				'<input type="checkbox" />1级心血管副主任<br />' +
				'<input type="checkbox" />1级心血管副主任<br />' +
				'<input type="checkbox" />1级心血管副主任<br />' +
				'</div>' +
				'</div>';
		layer.open({
			type: 1,
			title: "学科",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, win_h], //宽高
			content: sub_cont,
			closeBtn: 0,
			btn: ['取消'],
					yes: function (index, layero) {
					 
						layer.close(index);
					},
			success: function(layerb, index){
				$('.select').click(function(){
					$('.list').css('display","none');
					$(this).find('ul').show();
				});
				$('.list li').click(function(){
					var str=$(this).text();
					$(this).parent().parent().find('div').find('b').text(str);
					$('.list').slideUp(50);
				});
				$('.select').click(function(e){
					return false
				});
				$(document).click(function(){
					$('.list').hide('fast');
				});
				$(".btn1").click(function(){
					layer.close(index);
				});
			}
		});
	});

})
</script>
</body>
</html>