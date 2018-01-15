<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/commons/taglibs.jsp"%>
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
		<style>
			.left_cont{width:45%;float:left;margin-right:5%;border:1px solid #ccc;min-height:450px;}
			.left_cont ul{list-style:none;padding:0 20px;}
			.left_cont ul li{line-height:25px;font-size:14px;}
			.left_cont ul li i{float:right;color:#0000cc;}
			.right_cont{float:left;width:45%;border:1px solid #ccc;min-height:450px;}
			.right_cont h3{font-size:18px;margin-bottom:20px;}
			.right_cont h4{font-size:14px;border-bottom:1px solid #ccc;margin-bottom:20px;padding:5px 0;}
			.right_cont .ability_area{padding:10px;}
			.right_cont .ability_area i{font-size:18px;color:#0B92E8;margin:-5px 20px 0 10px;}
			.right_cont .ability_area .tiaojian p{width:45%;line-height:30px;font-size:12px;cursor:pointer;}
			.right_cont .ability_area .tiaojian p input[type=checkbox]{float:left;margin:6px 5px 0 0;}
			.right_cont .ability_area .tiaojian p span{font-size:12px;}
			.left_cont h2,.right_cont h2{background:#ebebeb;border-bottom:1px solid #ccc;font-size:16px;font-weight:600;padding:5px 10px;line-height:35px;}
			.left_cont h2 span{background-color: #0B92E8;color:#fff;border:1px solid #0B92E8;float:right;padding:5px 10px;border-radius:6px;line-height:20px;}
			.left_cont h2 span a{text-decoration: none;color:#fff;}
			.left_cont h3{font-size:14px;font-weight:600;margin:20px;}
		</style>
		<title>培训后台</title>
		<%@include file="/peixun_page/top.jsp"%>
	</head>
<body >
<div class="center"  style="">
	
	<div class="center01" id ="addwindow">
	<form id="sfrm" name="cvForm" method="POST">
		<div class="tiaojian" style="min-height:40px;">
		
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 课程名称：</span>
				<input type="text" name="name" value="${info.name}" />
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 课程编号：</span>
				<input type="text" name="serial_number" value="${info.serial_number }" />
			</p>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 学科：</span>
			</p>
				<input type="hidden" name="propIds" id="propIds" value="${propIds}"/>
				<input type="hidden" name="propNames" id="propNames" value="${propNames}"/>
				<div class="duouan" id="propNames01" style="background:#f0f0f0">${propNames}</div> 
			<div class="clear"></div>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;">课程标签：</span>
			</p>
			<div class="fl select" style="margin:0 20px 20px 0;">
				<div class="tik_position" style="background:#f0f0f0">
					<b class="ml5"> <c:if test="${info.brand == 1}">病例</c:if>
						<c:if test="${info.brand == 2}">直播</c:if>
						<c:if test="${info.brand == 3}">VR</c:if></b>
						<c:if test="${info.brand == 4}">名师课程</c:if></b>
						<c:if test="${info.brand == 5}">三维动画</c:if></b>
						<c:if test="${info.brand == 6}">其他</c:if></b>
				</div>
				
			</div>

			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 授课教师：</span>
				<input type="text" name="teacher" class="teacher_1" id="teacher1" value="${teacher}"<%-- value="${list.teahcer}" --%> />
				<input type="hidden" name="teacherIds" id="teacherIds" value="${teacherIds}"<%-- value="${list.teahcerIds}" --%>/>
				
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 示教教师：</span>
				<input type="text" name="otherTeacher" class="teacher_2" value="${otherTeacher}" />
				<input type="hidden" name="otherTeacherIds" id="otherTeacherIds" value="${otherTeacherIds}"<%-- value="${list.otherTeacherIds}" --%>/>
			</p>

			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 课程简介：</span>
				<textarea  name="introduction"style="width:400px;" rows="5" >${info.introduction}</textarea>
			</p>
			<p class="fl" style="margin-bottom:20px;width:526px">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 课程封面：</span>
				<%-- <input type="file" name="file_path" value="${info.file_path }"> --%>
				<img src="${ctx}${imgFile}" style="width:300px; height:230px"/>
			</p>
			<div class="clear"></div>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 课程声明：</span>
				<textarea  name="announcement" style="width:400px;" rows="5">${info.announcement}</textarea>
			</p>
			<div class="clear"></div>
			<p class="fl cas_anniu" style="margin-left:9.1em;">
				<a href="javascript:void(0);" class="fl next_step" style="width:140px;margin-left:10px;">下一步</a>
			</p>
			<p class="fl cas_anniu">
				<a href="javascript:closeWindow();" class="fr" style="width:70px;margin-left:10px;">返回</a>
			</p>
		</div>
	</form>
		<div class="clear"></div>
	</div>
	
	
<div id="next_page" style="display:none">
<div class="top_cont">
	
	<h2>${info.name}</h2>
</div>
<div class="sub_nav" style="height:25px">
	<span class="btn_blue fr" id="showAddwindow" ><a href="#"  >返回</a></span>	
	<span class="btn_blue next_btn" style="display:none;"><a href="${ctx}/CVSet/CVUnitAdd.do?mode=unionEdit&id=${info.id}">编辑</a></span>
</div>
<div class="center" id="addcontent">
	<div class="center01">
		<button class="btn_blue clone_course btn_no" type="button" >克隆课程</button>
		<div class="clear" style="margin-bottom:20px;"></div>
		<div class="left_cont">
			<h2 class="catalog">目录</h2>
			<form id="createUnionForm" name="cvUnitForm" method="POST">
			<div class="add_union" style="display:none;padding:20px;">
				
				<div class="tiaojian">
				
					<p class="clear" style="margin-bottom:20px;">
						<span class="fl" style="width:6em;text-align:right;">单元名称：</span>
						<input type="text" name="name" id="unitName"/>
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
						<select name ="type"  id = "sel_point" style = "display:none;">
							
							<option value ="1" <c:if test="${type==1}">selected="selected"</c:if>>理论讲解 </option>
							<option value ="2" <c:if test="${type==2}">selected="selected"</c:if>>主题讨论</option>
							<option value ="3" <c:if test="${type==3}">selected="selected"</c:if>>随堂考试 </option>
							<option value ="4" <c:if test="${type==4}">selected="selected"</c:if>>操作演示</option>
							<option value ="5" <c:if test="${type==5}">selected="selected"</c:if>>扩展阅读</option>
							<option value ="6" <c:if test="${type==6}">selected="selected"</c:if>>病例分享</option>
						</select>
							<li>理论讲解</li>
							<li>主题讨论</li>
							<li>随堂考试</li>
							<li>操作演示</li>
							<li>扩展阅读</li>
							<li>病例分享</li>
						</ul>
					</div>
					<div class="clear"></div>
					<p><span style="width:6em;">&nbsp;</span><input type="checkbox" name="point" id="point" /> 任务点 </p>
					<p class="clear">
						<span style="width:6em;">&nbsp;</span>
						<input type="button" name="btn_confirm" class="btn_blue btn_confirm" value="确定" />
						<input type="button" name="btn_reset" class="btn_reset" value="取消" />
					</p>
					
				</div>
			
			</div>
			</form>
			
			<table id="unitList" class="mt10 table">
				<tr class="tr" id="title_tr">
					<th width="10%">序号</th>
					<th width="40%">课题</th>
					<th width="20%">类别</th>
					<th width="15%">任务点</th>
					<th width="15%">完成状态</th>
				</tr>
				<tr align="center" valign="middle" id = "initTr">
	                    <td colspan="5" id="unit_td">--请添加单元--</td>
	             </tr>
			</table>
		</div>
		

		<div class="clear"></div>
	</div>
</div>

</div>
<div id="container"></div>
<script type="text/javascript">
$('input').each(function(){
	$(this).attr('disabled', true);
});
$('textarea').each(function(){
	$(this).attr('disabled', true);
});
$(function(){

		$.ajax({
			url:'${ctx}/CVSet/CVUnitAdd.do',
			data:'mode=getUnit&id='+'${info.id}',
			dataType:'json',
			success:function(result){
				if(result != ""){
					list = result.result;
					refresh_left_cont(list);
				}
			}
		});

		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "lc_left0" + menuindex;
		$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
		
		/* if("${view == 2}") showNextView();  */
			//else show_first();
		$(".next_step").click(function () {
			$('#addwindow').hide();
			$('#next_page').show();
		});
		
		select_init();
		
		$('#showAddwindow').click(function(){
			show_first();
		});
		//changed by Ma
		/* $('#propNames01').click(function(){
			initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
			$('.att_make01').show();
		}); */
		$('.cas_anniu_4,.bjt_kt').click(function(){
			$('$propNames').val($('.propNames01').text());
			$('.att_make01').hide();
		});
		
		var obj = $('input[name="cv"]');
		if (obj.prop("id")!=null) $(".table,.next_btn").show();
		
		
		var show_tbody = window.localStorage.getItem("subject_clone");
	if (show_tbody != null && (show_tbody == 1 || show_tbody == 2 || show_tbody == 3)){
		$(".table").show();
	}else{
		//$(".table").hide();
	}

	var add_course = window.localStorage.getItem("add_course");
	if (add_course != null && add_course == 1){
		$(".table,.next_btn").show();
	}else{
		//$(".table,.next_btn").hide();
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
		//$(".table").hide();
		$(".right_cont").hide();
	});
	$(".btn_confirm").click(function(){
		$('.add_union').hide();
		$(".table").show();
		$(".next_btn").show();
		if($("input[name='point']:checked")){                    
                    $('#point').val(1);
        }else $('#point').val(0);
		//window.localStorage.setItem("add_course","1");
		
		//document.getElementById("createUnionForm").action = "${ctx}/CVSet/CVUnitAdd.do?mode=addUnionUpdate&id="+'${info.id}';
		//document.getElementById("createUnionForm").submit(); */
		
		var url = '${ctx}/CVSet/CVUnitAdd.do';
		var params = 'mode=addUnion&point='+$('#point').val()+'&type='+$('#sel_point').val()+'&name='+$('#unitName').val();
		$.ajax({
			url		:url,
			data	:params,
			type	:'post',
			dataType:'json',
			success:function(result){
				if(result != "")
				{
					list = result.list;
					refresh_left_cont(list);
				}				
			}
			
		});
	
		$('#unitName').val("");
		$('#point').prop("checked",false);
		$('#sel_point').parent().parent().find("b").text("理论讲解 ");
		$('#sel_point').val("1");
		$('.add_union').hide();	
		
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
		window.open('${ctx}/CVSet/CVUnitAdd.do?mode=clone');
	});
	
	
	var win_w = parseInt($(window).width() / 3) + "px";
	var win_h = parseInt($(window).height() / 1.5) + "px";
	
	
	
	
	
//选择目录弹出框
		
	 $.extend( $.fn.pickadate.defaults, {
		monthsFull: [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
		monthsShort: [ '一', '二', '三', '四', '五', '六', '七', '八', '九', '十', '十一', '十二' ],
		weekdaysFull: [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
		weekdaysShort: [ '日', '一', '二', '三', '四', '五', '六' ],
		today: '今日',
		clear: '删',
		firstDay: 1,
		format: 'yyyy-mm-dd',
		formatSubmit: 'yyyy-mm-dd'
	}); 

	// 日期选择控件
	 var input = $(".datepicker").pickadate({
		monthSelector: true,
//		yearSelector: true,
		today:false,
		clear:false,
		dateMax : true,
		dateMin : [2010, 1, 1],
		yearSelector: 100
	});
	var calendar = input.data( 'pickadate' ); 


	var teacher_cont = '<div class="tiaojian" style="min-height:40px;padding:20px;">' +
			
			'<div class="clear"></div>' +
			'<div class="clear teacher_Name" style="margin:0 20px 20px 0; border: 1px solid #cfcfcf;min-height:80px;padding:10px;margin-top:10px;line-height:25px;">' +			
			'</div>' +	
					
			'<div class="clear"></div>' +
			'<p class="fl" style="margin-bottom:10px;"><input type="hidden" id="managerId" />' +
			'<input type="text" placeholder="请输入教师名称搜索" id="teacher">' +			
			'</p>' +
			'<p class="fl cas_anniu" style="margin-left:20px" id="add_teacher"><a href="javascript:void(0);" class="fl" style="width:70px;">添加</a></p>' +
			'<div class="clear"></div>' +
			'<div class="clear" id="teacherName"  style="height:122px;overflow:auto;border: 1px solid #cfcfcf;min-height:100px;padding:10px;margin-top:10px;line-height:25px;">' +
			
			'</div>' +
			'</div>';
	$(".teacher_1").click(function(){
		
		tmpManageName = "";
		tmpManageIds = "";
		var _this = $(this);
		var _this_id = $('#teacherIds');
		var loadNamesStr = '', loadIdsStr = '';
		var loadNames = $(this).val().slice(0, -1).split(",");
		if(loadNames != ""){
			for(var i=0; i<loadNames.length; i++){
				loadNamesStr += '<em onClick="javascript:delem1(this);" class="delem">' +
							   loadNames[i]+
							   '</em>'+",";
			}
		}
		loadIdsStr = _this_id.val();
		layer.open({
			type: 1,
			title: "专家",
			skin: 'layui-layer-rim', //加上边框
			area: ["600px", "460px"], //宽高
			content: teacher_cont,
			closeBtn: 0,
			btn: ['确定', '取消'],
			yes: function (index, layero) {						
				_this.val($('.teacher_Name').text().slice(0, -1));
				_this_id.val($('#managerId').val());
				layer.close(index);
				//_this_id.val(str_teach_ids);
			},
			success: function(layerb, index){
				initListShow();
				
				$('.teacher_Name').html(loadNamesStr);
				$('#managerId').val(loadIdsStr);				
				
				$('#add_teacher').click(function(){	
					var str = ''; str2 = '';
					var isChecked = 0;
					$('#chk_teacher:checked').each(function(){
						isChecked ++;
						str += '<em onClick="javascript:delem1(this);" class="delem">' + $(this).prop("name") + '</em>'+ ","; 
						str2 += $(this).val()+",";						
					});	
					if(isChecked == 0) 
						return false;
						
					if(tmpManageName != "") {
						$('.teacher_Name').html("");
						$('#managerId').val("");
						$('.teacher_Name').html(tmpManageName + str);
						$('#managerId').val(tmpManageIds+str2);
						tmpManageName ="";
						tmpManageIds ="";
					} else {						
						$('.teacher_Name').html($('.teacher_Name').html() + str);
						$('#managerId').val($('#managerId').val()+str2);
					}
					$('#chk_teacher:checked').each(function(){
						$(this).prop("checked", false);
					});
				});
				$('#teacher').keyup(function(){
					tmpManageName = $('.teacher_Name').html();
					tmpManageIds = $('#managerId').val();
					
					$.ajax({
						type:'post',
						url:'${ctx}/expert/ExpertManage.do?mode=search&name='+$('#teacher').val(),
						dataType:'json',
						success:function(result){
							var strTeacher = '';
							//var idsStr = loadIdsStr.slice(0,-1);
							//var idList = loadIdsStr.split(",");			
							for(var i=0;i<result.name.length;i++){
								strTeacher += '<input type="checkbox" id="chk_teacher" value=" ' + result.name[i].id+ '" name="'+result.name[i].name+'" onclick="javascript:chk_validate(this, '+result.name[i].id+')"/>'+result.name[i].name+'<br>';
							}							
							$('#teacherName').html(strTeacher);
						}	
					});
				});
			}
		});
	});
		
	
	
	$(".teacher_2").click(function(){
		tmpManageName = "";
		tmpManageIds = "";
		var _this = $(this);
		var _this_id = $('#otherTeacherIds');
		var loadNamesStr = '', loadIdsStr = '';
		var loadNames = $(this).val().slice(0, -1).split(",");
		if(loadNames != ""){
			for(var i=0; i<loadNames.length; i++){
				loadNamesStr += '<em onClick="javascript:delem1(this);" class="delem">' +
							   loadNames[i]+
							   '</em>'+",";
			}
		}
		loadIdsStr = _this_id.val();
		layer.open({
			type: 1,
			title: "专家",
			skin: 'layui-layer-rim', //加上边框
			area: ["600px", "460px"], //宽高
			content: teacher_cont,
			closeBtn: 0,
			btn: ['确定', '取消'],
			yes: function (index, layero) {						
				_this.val($('.teacher_Name').text().slice(0, -1));
				_this_id.val($('#managerId').val());
				layer.close(index);
				//_this_id.val(str_teach_ids);
			},
			success: function(layerb, index){
				initListShow();
				
				$('.teacher_Name').html(loadNamesStr);
				$('#managerId').val(loadIdsStr);				
				
				$('#add_teacher').click(function(){	
					var str = ''; str2 = '';
					var isChecked = 0;
					$('#chk_teacher:checked').each(function(){
						isChecked ++;
						str += '<em onClick="javascript:delem1(this);" class="delem">' + $(this).prop("name") + '</em>'+ ","; 
						str2 += $(this).val()+",";						
					});	
					if(isChecked == 0) 
						return false;
						
					if(tmpManageName != "") {
						$('.teacher_Name').html("");
						$('#managerId').val("");
						$('.teacher_Name').html(tmpManageName + str);
						$('#managerId').val(tmpManageIds+str2);
						tmpManageName ="";
						tmpManageIds ="";
					} else {						
						$('.teacher_Name').html($('.teacher_Name').html() + str);
						$('#managerId').val($('#managerId').val()+str2);
					}
					$('#chk_teacher:checked').each(function(){
						$(this).prop("checked", false);
					});
				});
				$('#teacher').keyup(function(){
					tmpManageName = $('.teacher_Name').html();
					tmpManageIds = $('#managerId').val();
					
					$.ajax({
						type:'post',
						url:'${ctx}/expert/ExpertManage.do?mode=search&name='+$('#teacher').val(),
						dataType:'json',
						success:function(result){
							var strTeacher = '';
							//var idsStr = loadIdsStr.slice(0,-1);
							//var idList = loadIdsStr.split(",");			
							for(var i=0;i<result.name.length;i++){
								strTeacher += '<input type="checkbox" id="chk_teacher" value=" ' + result.name[i].id+ '" name="'+result.name[i].name+'" onclick="javascript:chk_validate(this, '+result.name[i].id+')"/>'+result.name[i].name+'<br>';
							}							
							$('#teacherName').html(strTeacher);
						}	
					});
				});
			}
		});
	});
});	


	
		
function saveCourse(){
		if($("input[name='point']:checked")){
                    
                    $('#point').val(1);
        }else $('#point').val(0);
	
		if($("input[name='state']:checked")){
                    
                    $('#state').val(1);
        }else $('#state').val(0);
	 document.getElementById("sfrm").action = "${ctx}/CVSet/CVManage.do?mode=updateSave&id="+'${info.id}';
    
     document.getElementById("sfrm").submit();
}

function addUnion(){
	document.getElementById("sfrm").action = "${ctx}/CVSet/CVManage.do?mode=addUnion";
}

function select_init() {
	$('.select').click(function(){
		$(".list").css("display","none");
		$(this).find('ul').show();
		return false;
	});
	
	$('.list li').click(function(){
		var str=$(this).text();
		$(this).parent().parent().find('div').find('b').text(str);
		$(this).parent().find('option').prop('selected', '');
		$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
		$('.list').slideUp(50);
	});
	$('.list option:selected').each(function(){
		var str=$(this).text();
		$(this).parent().parent().parent().find('b').text(str);
	});

	$(document).click(function(){
		$('.list').hide('fast');
	});	
}

/* function next_view() {
	
	$('#addwindow').hide();
	$('.top_cont').show();
	$('.sub_nav').show();
	$('#addcontent').show();	
} */

function show_first(){	
	$('#addwindow').show();	
	$('#next_page').hide();
}


function refresh_left_cont(unitList) {
	//window.location.reload(true);
	
	$("#initTr").remove();
    var html = [];

    var lastTr = $("#unitList").find("tr").last();
    //var index = $("#unitList").find("tr").length;
	$(unitList).each(function(key, value){
	        html.push("<tr align='center'>");
	        html.push("<td width='10%'> ");
	        html.push("<input type='radio' id='");
	        html.push(value.id);
	        html.push("' name='cv' value='");
	        html.push(value.id);
	        html.push("'>&nbsp;");
	        html.push(value.id);
	        html.push("</td>");
	        html.push("<td width='40%'>");
	        html.push(value.name);
	        html.push("</td>");
	        html.push("<td width='20%'>");
	        if(value.type == 1)
	        {
	        	html.push("理论讲解");
	        }
	        if(value.type == 2)
	        {
	        	html.push("主题讨论");
	        }
	       if(value.type == 3)
	        {
	        	html.push("随堂考试");
	        }
	       if(value.type == 4)
	        {
	        	html.push("操作演示");
	        }
	       if(value.type == 5)
	        {
	        	html.push("扩展阅读");
	        }
	       if(value.type == 6)
	        {
	        	html.push("病例分享");
	        }        
	        html.push("</td>");
	        html.push("<td width='15%'>");
	        html.push("<input type='checkbox' name='point' disabled onClick='javascript:testPoint(this,");
	        html.push(value.id);
	        html.push(");'");
	        if(value.point == 1)
	        {
	        	html.push(" checked");
	        }
	        html.push(">");
	        html.push("</td>");
	        html.push("<td width = '15%'>");
	        html.push("<input type='checkbox' name='state' disabled onClick='javascript:testState(this,");
	        html.push(value.id);
	        html.push(");'");
	        if(value.state == 1)
	        {
	        	html.push(" checked");
	        }
	        html.push(">");
	        html.push("</td>");
	        html.push("</tr>");
	        
		});
    	lastTr.after(html.join(""));
}

function deleteUnit() {
	
	var obj = $('input[name="cv"]:checked');
	
	if(obj == undefined){
		alert("请选择要删除的单元！");
	}
	
 	var id = obj.val();
	
	swapUnit(id, id);
}

function to_up() {

	var obj = $('input[name="cv"]:checked');
	if(obj == undefined){
		alert("请选择单元！");
	}
	var upObj = obj.parent().parent();
	var temp = upObj;
	var prevUpObj = upObj.prev();
		if(prevUpObj.prop("id") == "title_tr"){	
		return false;
	}	
	upObj.remove();
	temp.insertBefore(prevUpObj);
}

function to_down() {
	
	var obj = $('input[name="cv"]:checked');
	if(obj == undefined){
		alert("请选择单元！");
	}
	var upObj = obj.parent().parent()
	var temp = upObj;
	var prevUpObj = upObj.next();
	if(prevUpObj.length == 0){		
		return false;
	}
	upObj.remove();
	temp.insertAfter(prevUpObj);
}

function swapUnit(src_id, target_id, rowNum) {
	
	var url = '${ctx}/CVSet/CVManage.do';
	var params = 'mode=swapUnit&src_id='+src_id +'&target_id=' + target_id;
	
	$.ajax({
		url 	: url,
		data 	: params,
		type	: 'post',
		dataType: 'text',
		success : function(result) {
			if (result == 'success')
				//refresh_left_cont();
				
				var obj = $('input[name="cv"]:checked');
	
				if(obj == undefined){
					alert("请选择要删除的单元！");
				}
				$(obj).parent().parent().remove();
				document.cookie = "radio=" + escape(rowNum);				
		}
	});
}

function testPoint(obj,_id){
	var pointMode;
	if($(obj).prop("checked")) pointMode =1;
	else pointMode =0; 
		
		var url='${ctx}/CVSet/CVManage.do';
		var params = "mode=updatePoint&pointMode="+pointMode+"&id="+_id;
		$.ajax({
			url		:url,
			data	:params,
			type	:'post',
			dataType:'text',
			success	:function(result){
				
			}
		});
		
}

/* function showNextView(){
	$('#addwindow').hide();
	$('#next_page').show();
} */
function testState(obj,_id){
	var stateMode;
	if($(obj).prop("checked")) sateMode =1;
	else sateMode =0; 
		var url='${ctx}/CVSet/CVManage.do';
		var params = 'mode=updatePoint&stateMode='+1+'&id='+_id;
		$.ajax({
			url		:url,
			data	:params,
			type	:'post',
			dataType:'text',
			success:function(result){
			
			}
		});
	
}
function closeWindow(){
	window.history.go(-1);
	window.close();
}


</script>
</body>
</html>