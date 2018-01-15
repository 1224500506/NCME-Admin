<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<%@include file="/commons/taglibs.jsp"%>
<style>
		.left_cont_v{width:320px;float:left;background:#ebebeb;padding:0 10px;}
		.left_cont_v .control_bar{margin:10px 0;}
		.left_cont_v .control_bar span{display:inline-block;float:left;margin-right:5px;border:1px solid #ccc;background-color:#dbdbdb;font-size:12px;padding:5px;box-sizing:border-box;border-radius:3px;}
		.left_cont_v .control_bar span.btn_blue{margin-right:0;background-color: #0B92E8;color:#fff;border:0 none;}
		.left_cont_v .control_bar span.btn_blue a{text-decoration:none;color:#fff;}
		.left_cont_v h2{font-size:14px;font-weight:600;margin:20px 0;}
		.left_cont_v ul{list-style:none;}
		.left_cont_v ul li{line-height:25px;font-size:14px;}
		.right_cont_v{margin-left:340px;}
		.left_cont{width:45%;float:left;margin-right:5%;border:1px solid #ccc;min-height:450px;}
		.left_cont ul{list-style:none;padding:0 20px;}
		.left_cont ul li{line-height:25px;font-size:14px;}
		.left_cont ul li i{float:right;color:#0000cc;}
		.right_cont{float:left;width:45%;border:1px solid #ccc;min-height:450px;}
		.right_cont h3{font-size:18px;margin-bottom:20px;}
		.right_cont h4{font-size:14px;border-bottom:1px solid #ccc;margin-bottom:20px;padding:5px 0;}
		.right_cont .ability_area{padding:10px;}
		.right_cont .ability_area i{font-size:18px;color:#0B92E8;margin:3.5px 20px 0 10px;}
		.right_cont .ability_area .tiaojian p{width:45%;line-height:30px;font-size:12px;cursor:pointer;}
		.right_cont .ability_area .tiaojian p input[type=checkbox]{float:left;margin:6px 5px 0 0;}
		.right_cont .ability_area .tiaojian p span{font-size:12px;}
		.left_cont h2,.right_cont h2{background:#ebebeb;border-bottom:1px solid #ccc;font-size:16px;font-weight:600;padding:5px 10px;line-height:35px;}
		.left_cont h2 span{background-color: #0B92E8;color:#fff;border:1px solid #0B92E8;float:right;padding:5px 10px;border-radius:6px;line-height:20px;}
		.left_cont h2 span a{text-decoration: none;color:#fff;}
		.left_cont h3{font-size:14px;font-weight:600;margin:20px;}
		
	</style>
	<style>
		dl{margin:10px 0;}
		dl dt{margin:10px 0 10px 20px;font-weight:600;}
		dl dd{margin:10px 0 10px 40px;}
	</style>
	
</head>

<%@include file="/peixun_page/top.jsp"%>

<body>
<!-- 内容 -->
<div class="center" id="mainPage">
	<form id="frm_add" action="${ctx}/CVSetManage.do?mode=edit&id=${View[0].id}" method="post" enctype="multipart/form-data">
	<input type="hidden" name="id" value="${View[0].id}"/>
	<div class="center01">
		<div class="tiaojian" style="min-height:40px;">
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目名称：</span>
				<input type="text" value="${View[0].name}" name="name" id="name" disabled/>
			</p>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目授课形式：</span>				
				<input type="text" disabled value="<c:if test="${View[0].forma == 1}">远程</c:if><c:if test="${View[0].forma == 2}">远程+面授</c:if><c:if test="${View[0].forma == 3}">面授</c:if>" />
				<input type="hidden" value="${View[0].forma}" name="forma"/>
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"> 人物画像：</span>
				<input type="text" class="portraits_input" value="${userImageNames}" disabled />
				<input type="hidden" name="userImage" value="${userImageIds}"/>
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"> 项目序号：</span>
				<input type="text" name="code" value="${View[0].code}" disabled />
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"> 项目负责人：</span>
				<input type="text" value="${manager}" class="person_btn" id="manager" disabled/>
				<input name="manager" type="hidden" value="${manager_id}"  />
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"> 授课教师：</span>
				<input  type="text" value="${teacher}" class="person_btn" id="teacher" disabled/>
				<input name="lessonTeacher" type="hidden" value="${teacher_id}"  />
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;">示教教师：</span>
				<input type="text" value="${otherTeacher}" class="person_btn" id="teacherOther" disabled/>
				<input name="generalTeacher" type="hidden" value="${otherTeacher_id}" />
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"> 项目归属机构：</span>				
				<input type="text" value="${orgNames}" class="org_btn" id="proOrg" disabled/>
				<input type="hidden" value="${orgIds}" id="organization" name="organization"/>
			</p>

			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目简介：</span>
				<textarea name="introduction" style="width:400px;" rows="5" id="introduction" disabled>${View[0].introduction}</textarea>
			</p>
			<p class="fl" style="margin-bottom:20px;width:526px">
				<span style="width:9em;text-align:right;">项目封面：</span>
				<img id="file_path" class="course_cover" src="${View[0].file_path}" style="width:200px;height:200px;"/>
				<input type="file" id="matFile" name="matFile" style="margin-left:330px;margin-top:-25px;" disabled>
				<input type="hidden" id="cover" name="cover" value="${View[0].file_path}">
			</p>
			<div class="clear"></div>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目声明：</span>
				<textarea name="announcement" id="announcement" style="width:400px;" rows="5" disabled>${View[0].announcement}</textarea>
			</p>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 学习须知：</span>
				<textarea name="knowledge_needed" style="width:400px;" rows="5" id="needed" disabled>${View[0].knowledge_needed}</textarea>
			</p>

			<div class="clear"></div>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;">书籍推荐：</span>
				<textarea name="reference" style="width:400px;" rows="5" disabled>${View[0].reference}</textarea>
			</p>			
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;">指南共识：</span>
				<textarea name="knowledge_base" style="width:400px;" rows="5" disabled> ${View[0].knowledge_base}</textarea>
			</p>
			<div class="clear"></div>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;">最新文献：</span>
				<textarea name="reference_lately" style="width:400px;" rows="5" disabled>${View[0].reference_lately}</textarea>
			</p>
			<div class="clear"></div>
			<p class="fl cas_anniu" style="margin-left:9.1em;">
				<a href="javascript:show_div();" class="fl next_step" style="width:140px;margin-left:15px;margin-bottom:15px">下一步</a>
			</p>
			<p class="fl cas_anniu">
				<a href="javascript:window.history.go(-1);" class="fr" style="width:70px;margin-left:15px;margin-bottom:15px">返回</a>
			</p>
			<input type="hidden" name="cvIds" id="cvIds" value="${cvschedule_id}" /><input type="hidden" name="cvscheduleIds" value="${cvschedule_scheduleId}"/>
		</div>

		<div class="clear"></div>
	</div>
	<input type="hidden" name="report" id="report" />
	</form>
</div>
<div id="container"></div>

<div id="course_create_1" style="display:none">
	<div class="top_cont">	
		<h2>${View[0].name}</h2>
	</div>
	<%-- 
	<div class="sub_nav">
		<!--<span class="btn_blue fr"><a href="javascript:save();">保存</a></span>-->
		<span class="btn_blue fr next_btn"><a href="javascript:bagaoOpen()" class="report_link">查看项目报告</a></span>
		<span class="add_course"><a href="javascript:getCourse();">添加课程</a></span>
		<span><a href="javascript:remove_cv()">删除课程</a></span>
		<span><a href="javascript:to_up();">上移</a></span>
		<span><a href="javascript:to_down();">下移</a></span>
		<span class="btn_blue edit_project" id="qualify_btn"><a href="javascript:void();">审核状态</a></span>
	</div>
	 --%>
	<div class="center" style="">
		<div class="center01">
			<div class="left_cont" id="left_cont">
								
			</div>	
			<div class="right_cont" style="display:none;">
					<h2>关联能力模型</h2> 
					<%--
					<button class="btn_blue fr" style="margin-right:10px; margin-top:10px;" onClick="javascript:save_unit_guide(this);" type="button" id="save_unit_guide_btn">保存</button>
					 --%>
					<div style="display:none;" class="ability_area ability_area_2">
						<h3>二级能力</h3>
						<h4></h4>
						<div class="tiaojian level_p">
							<p class="fl" style="margin-right:20px;">
								<input type="checkbox" class="fl" checked /> <span>精通临床操作技能</span> <i class="fa fa-caret-right"></i>
							</p>
						</div>
					</div>
					<div style="display:none;" class="ability_area ability_area_3">
						<h3>三级能力</h3>
						<h4></h4>
						<div class="tiaojian level_p">
						
							<div class="clear"></div>
							<p class="clear cas_anniu center" style="margin-top:30px;">
								<a href="javascript:void(0);" class="btn_back" style="width:70px;">返回</a>
							</p>
						</div>
					</div>
					<div style="display:none;" class="ability_area ability_area_4">
						<h3>四级能力</h3>
						<h4></h4>
						<div class="tiaojian">
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
</div>
<div id="bagao" style="display:none">
<div class="top_cont">	
	<h2>${View[0].name}</h2>
</div>
<%--
<div class="sub_nav">
	<span class="btn_blue fr"><a href="javascript:save();">保存</a></span>
	<span class="btn_blue btn_project_view"><a href="javascript:previous();">项目预览</a></span>
	<span class="btn_blue"><a href="javascript:viewDetail();">项目详细</a></span>
	<span class="btn_blue"><a href="javascript:openBagao();">项目报告</a></span>
	<span class="fr btn_blue"><a href="javascript:propose();">提交审核</a></span>
</div>  --%>                           <%-- ${ctx}/CVSetManage.do?mode=myXiangMu&CVSetStatus=2 --%>

<div class="center" style="">
	<div class="report_cont">
	
		<h2 class="title" style="font-size:20px;margin-bottom:30px;">项目报告</h2>
		<div class="cont">
			<textarea rows="" name="report1" id="report1" cols="" style="width:540px;height:300px">${View[0].report}</textarea>			
		</div>

	</div>
</div>
</div>
<div id="union" style="display:none">
	<div class="clear"></div>
	<div class="bjs"></div>
	
	
	<div class="left_cont_v">	
		<div class="control_bar clear">
			<span class="btn_blue" style="margin-left:230px"><a href="javascript:window.history.go(-1);">返回</a></span>				
			<span class="btn_blue btn_save" style="margin-left:10px"><a href="#">保存</a></span>		
		</div>
	</div>
	
	<div class="right_cont_v">
		<script id="editor" name="content" type="text/plain">

	</script>
	</div>
	
	<div class="clear"></div>
</div>
<div class="tiaojian" style="min-height:40px;">
<div class="verify_list pass_result">
		<div class="v_cont no_pass" style="display:none">
			<p class="clear">
				<span style="width:7em;text-align:right;">审核人：</span>
				<span>韩珞，张文远，李天</span>
			</p>
			<p class="clear">
				<span style="width:7em;text-align:right;">审核结果：</span>
				<input type="text" value="" class="notice_input" disabled>
			</p>
			<p class="clear">
				<span style="width:7em;text-align:right;">未通过原因：</span>
				<textarea name="reason" style="width:300px;" rows="5" disabled></textarea>				
			</p>
		</div>
</div>
</div>
<script type="text/javascript">
$(function(){
	//select menu
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "lc_left0" + menuindex;
		$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
	//选择目录弹出框
	
	$(".left_cont_v,.right_cont_v").height($(window).height() - 223);
	$(".right_cont_v").width($(window).width() - 340);
	$("#editor").css({"width":$(window).width() - 350 + "px",height:$(window).height() - 310 + "px"});

	window.ue = UE.getEditor('editor');
	ue.addListener("ready",function(){
		ue.setContent();
	});
	//下拉框
		$('.fl select').click(function(){
			$('.list').css("display","none");
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
		
		$(document).click(function(){
			$('.list').hide('fast');
		}); 
		$(".add_course").click(function(){
		window.localStorage.setItem("subject_clone","2");
		});
		if (window.localStorage.getItem("add_course") != null && window.localStorage.getItem("add_course") == 1){
			$(".table thead, .table tbody").show();
			$(".edit_project").show();
		}else{
			$(".table thead, .table tbody").hide();
			$(".right_cont,.ability_area_2").hide();
			$(".edit_project").hide();
		}
	
		/* if (window.localStorage.getItem("my_course") != null && window.localStorage.getItem("my_course") == 1){
			$(".report_link").text("查看项目报告");
		}else{
			$(".report_link").text("生成项目报告");
		} */
	
		$(".table tbody tr").click(function(){
			window.localStorage.setItem("add_ability","1");
			$(".right_cont,.ability_area_2").show();
			$(".next_btn").show();
		});
		/* if (window.localStorage.getItem("add_ability") != null && window.localStorage.getItem("add_ability") == 1){
			$(".right_cont,.ability_area_2").show();
			$(".next_btn").show();
		}else{
			$(".right_cont,.ability_area_2").hide();
			$(".next_btn").hide();
		} */
		$(".level_p p span,.level_p p i").click(function () {
			$(this).parent().parent().parent().hide().next(".ability_area").show();
		});
	
		$(".btn_back").click(function () {
			$(this).parent().parent().parent().hide().prev(".ability_area").show();
		}); 

	var win_w = parseInt($(window).width() / 3) + "px";
	var win_h = parseInt($(window).height() / 1.5) + "px";
	var teacher_cont = '<div class="tiaojian" style="min-height:40px;padding:20px;">' +
			//'<p class="fl" style="width:70%;margin:0 20px 20px 0;"><textarea style="width:100%;"  rows="5" class="fl teacher_name" name=""></textarea></p>' +
			'<div class="clear"></div>' +
			'<div class="clear teacher_name" style="margin:0 20px 20px 0; border: 1px solid #cfcfcf;min-height:80px;padding:10px;margin-top:10px;line-height:25px;">' +			
			'</div>' +	
					
			'<div class="clear"></div>' +
			'<p class="fl" style="margin-bottom:10px;"><input type="hidden" id="managerId" />' +
			'<input type="text" placeholder="请输入教师名称搜索" id="teacherSearch">' +			
			'</p>' +
			'<p class="fl cas_anniu" style="margin-left:20px" id="add_teacher"><a href="javascript:void(0);" class="fl" style="width:70px;">添加</a></p>' +
			'<div class="clear"></div>' +
			'<div class="clear" id="teacherName" style="border: 1px solid #cfcfcf;min-height:100px;padding:10px;margin-top:10px;line-height:25px;">' +
			
			'</div>' +
			'</div>';
	var org_cont = '<div class="tiaojian" style="min-height:40px;padding:20px;">' +
		
			'<div class="clear"></div>' +
			'<div class="clear teacher_Name" style="margin:0 20px 20px 0; border: 1px solid #cfcfcf;min-height:80px;padding:10px;margin-top:10px;line-height:25px;">' +			
			'</div>' +	
					
			'<div class="clear"></div>' +
			'<p class="fl" style="margin-bottom:10px;"><input type="hidden" id="managerId" />' +
			'<input type="text" placeholder="请输入机构名称搜索" id="orgSearch">' +			
			'</p>' +
			'<p class="fl cas_anniu" style="margin-left:20px" id="add_org"><a href="javascript:void(0);" class="fl" style="width:70px;">添加</a></p>' +
			
			'<div class="clear"></div>' +
			'<div class="clear" id="orgList" style="border: 1px solid #cfcfcf;min-height:100px;padding:10px;margin-top:10px;line-height:25px;">' +
			
			'</div>' +
			'</div>';
	$(".org_btn").click(function () {
	var this_ = $(".org_btn");
		var thisId = $('#organization');	
		layer.open({
			type: 1,
			title: "请选择项目归属机构",
			skin: 'layui-layer-rim', //加上边框
			area: ["600px", "400px"], //宽高
			content: org_cont,
			closeBtn: 0,
			btn: ['确定', '取消'],
			yes:function (index, layero) {
				
					this_.val($('.teacher_Name').text().slice(0, -1));
					thisId.val($('#managerId').val());
					layer.close(index);							
			},
			success: function(layerb, index){				
				$('#add_org').click(function(){					
					var str = ''; str2 = '';
					$('#chk_org:checked').each(function(){
						str += '<em onClick="javascript:delem1(this);" class="delem">' + $(this).prop("name") + '</em>'+ ","; 
						str2 += $(this).val()+",";						
					});		
					$('.teacher_Name').html($('.teacher_Name').html() + str);	
					$('#managerId').val($('#managerId').val()+str2);
					$('#chk_org:checked').each(function(){
						$(this).prop("checked", false);
					});
				});
				$(".btn1").click(function () {
					
				});
				$('#orgSearch').keyup(function(){
					var orgList = '';
					$.ajax({
						type:'post',
						url:'${ctx}/system/peixunOrglist.do',
						data:'method=getListByAjax&model.name='+$('#orgSearch').val(),
						dataType:'JSON',
						success:function(org){				
							for(var i=0; i<org.item.length; i++){					
									orgList += '<input type="checkbox" id="chk_org" name="' + org.item[i].name + '" value="'+org.item[i].id+'"/>' + org.item[i].name + '<br>';				
							}
							$('#orgList').html(orgList);
						}
					});
				});
			}
		});
		
	});
	$(".person_btn").click(function () {
		var _this = $(this);
		if(_this.prop("id") =='manager'){
			var _this_id = $('input[name="manager"]');
		} else if(_this.prop("id") == 'teacher') {
			var _this_id = $('input[name="lessonTeacher"]');
		}else if(_this.prop("id") =='teacherOther') {
			var _this_id = $('input[name="generalTeacher"]');
		}
		layer.open({
			type: 1,
			title: "请选择",
			skin: 'layui-layer-rim', //加上边框
			area: ["600px", "400px"], //宽高
			content: teacher_cont,
			closeBtn: 0,
			btn: ['确定', '取消'],
			yes: function (index, layero) {	
			    //alert($('.teacher_Name').text());				
				_this.val($('.teacher_Name').text());
				_this_id.val($('#managerId').val());
				layer.close(index);
				//_this_id.val(str_teach_ids);
			},
			success: function(layerb, index){
				/* $(".btn1").click(function () {
					var str_teach_dis = ''; var str_teach_ids = '';
					$('#chk_teacher:checked').each(function(){
						str_teach_dis += $(this).prop("name")+",";
						str_teach_ids += $(this).val()+",";
					});
					layer.close(index);
					_this.val(str_teach_dis.substring(0,str_teach_dis.length-1));
					_this_id.val(str_teach_ids);
				}); */
				
				$('#add_teacher').click(function(){
					var str = ''; str2 = '';
					$('#chk_teacher:checked').each(function(){
						str += '<em onClick="javascript:delem1(this);" class="delem">' + $(this).prop("name") + '</em>'+ ","; 
						str2 += $(this).val()+",";						
					});		
					$('.teacher_Name').html($('.teacher_Name').html() + str);	
					$('#managerId').val($('#managerId').val()+str2);
					$('#chk_teacher:checked').each(function(){
						$(this).prop("checked", false);
					});
				});
				$('#teacherSearch').keyup(function(){
					$.ajax({
						type:'post',
						url:'${ctx}/expert/ExpertManage.do?mode=search&name='+$('#teacherSearch').val(),
						dataType:'json',
						success:function(result){
							var strTeacher = '';					
							for(var i=0;i<result.name.length;i++){
								strTeacher += '<input type="checkbox" id="chk_teacher" value=" ' + result.name[i].id+ '" name="'+result.name[i].name+'" />'+result.name[i].name+'<br>';
							}							
							$('#teacherName').html(strTeacher);
						}	
					});
				});
			}
		});
	});
});

	var win_w = parseInt($(window).width() / 2.5) + "px";
	var win_h = parseInt($(window).height() / 1.8) + "px";
	
	/* $(".portraits_input").click(function(){
	
		var _this = $(this);
		var _next = $("input[name='code']");	
		var _this_id = $("input[name='userImage']");
		var option_maker = '<select id="sel_userLeixing" style="display:none;">'+"\n", li_maker ='';		
		$.ajax({			
			type:'post',
			url:'${ctx}/quality/userImageManage.do',
			data : 'mode=guide',
			dataType:'json',
			success:function(result){

			   var li_maker_temp = '';			  
			   for(var i=0; i<result.userLeixing.length; i++){
				   option_maker += '<option value='+result.userLeixing[i].type.id+'>'+result.userLeixing[i].type.name+'</option>';
				   li_maker_temp += '<li>'+result.userLeixing[i].type.name+'</li>'+"\n";			   
			   }
			   li_maker = option_maker + '</select>' + li_maker_temp;
		
			   var sub_cont = '<div class="toumingdu att_make01" style="display:none;">'+
					'<div class="tk_center09" style="margin:7% auto;">'+
						'<div class="tik_biaoti">'+
							'<span class="fl tit_biaoti" style="margin-left:290px;color:#fff;"></span>'+
							'<i class="fr bjt_kt"></i>'+
						'</div>'+
						'<div class="clear"></div>'+
						'<div class="xianshikuang">'+
							'<div class="mt15 xs_xuangze">'+
								'<div class="fl xs_kuangcode" style="display:none;"></div>'+
								'<div class="fl xs_currentid" style="display:none;"></div>'+
								'<div class="fl xs_selectlvl" style="display:none;"></div>'+
								'<div class="fl xs_checklvl" style="display:none;"></div>'+
								'<div class="fl xs_kuang"></div>'+
								'<div class="fr cas_anniu_4" style="margin-top:25px;">'+
									'<a href="javascript:selectProp();" class="fr">确定</a>'+
								'</div>'+
								'<div class="clear"></div>'+
							'</div>'+
							'<div class="xs_biaoti">'+
								'<div class="fl xs_er">'+
									'<p class="fl attr_xueke01"></p>'+
									'<i class="fl xs_bjt01"></i>'+
									'<em class="fl">></em>'+
								'</div>'+
							'</div>'+
							'<div class="clear"></div>'+
							'<ul class="xs_ul">'+
							'</ul>'+
							'<div class="clear"></div>'+
						
						'</div>'+
					'</div>'+
				'</div>'+
			   '<div class="tiaojian" style="min-height:40px;padding:20px;">' +
				'<p class="fl" style="width:70%;margin:0 20px 20px 0;"><textarea style="width:100%;" rows="5" class="fl" name="" id="xueke"></textarea></p>' +
				
				'<div class="clear"></div>' +	
							
				'<p class="fl" style="margin-bottom:10px;">' +
				'<span style="width:5em;text-align:right;">人物类型：</span>'+
				'<div class="fl select">' +
				'<div class="tik_position">' +
				'<b class="ml5">请选择人物画像类型</b>' +
				'<p class="position01"><i class="bjt10"></i></p>' +
				'</div>' +
				'<ul class="list" style="display:none;">' +
					li_maker+
				'</ul>' +
				'</div>' +
				'</p>' +
				
				'<p class="fl" style="margin-bottom:10px;">' +
					'<span style="width:5em;text-align:right;">学科：</span>'+				
					'<input type="hidden" class="fl tik_select" id="propIds" name="propIds" value="${propIds}"/>' +
					'<input type="hidden" class="fl tik_select" id="propNames" name="propNames" value="${propNames}"/>' +
					'<div class="duouan subject_input" id="propNames01"></div>' +'<input type="hidden" value="" id="prop_editId"/>' +
				'</p>' +				
				'<p class="fl cas_anniu" style="margin-left:20px"><a href="javascript:void(0);" class="fl btn1" style="width:70px;">添加</a></p>' +
				
				'<div class="clear"></div>' +
			    '<p class="fl" style="margin-bottom:10px;">' +				
				'<span style="width:5em;text-align:right;">人物画像：</span>' +
				'<div class="clear" id="str_user_image" style="border: 1px solid #cfcfcf;min-height:100px;padding:10px;margin-top:10px;line-height:25px;">' +
				'</p>' + 			
				'</div>';
		layer.open({
			type: 1,
			title: "人物画像",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, win_h], //宽高
			content: sub_cont,
			closeBtn: 0,
			btn: ['确定', '取消'],
			yes: function (index, layero) {
				var strName = '', strId = '';
					$('#chk:checked').each(function(){
						strId += $(this).prop("value")+",";
						strName += $(this).next().text()+",";						
					});
					
					var num_code = '', code_='', year = '', month = '', day = '', randNum = '';				
						$.ajax({
							type:'post',
							url:'${ctx}/CVSetManage.do',
							data:'mode=pinyin&hanyuStr='+$('#propNames01').text(),
							dataType:'text',
							success:function(Result){
								code_ = Result;
								for(var i=0; i<5; i++){
									randNum += Math.round(Math.random()*9);
								}
								date = new Date();					
								year = date.getFullYear();
								month = (date.getMonth()+1).toString();
								day = date.getDate().toString();					
								if(day.length == 1) day= "0" + day;	
								if(month.length == 1) month= "0" + month;	
								if(code_ == "") {
									alert("没有关联学科!");
									return;
								}	 
								num_code = code_ + '-' + year.toString() + month.toString() + day.toString() + '-' + randNum.toString();
								layer.close(index);
								_this.val(strName);	
								_next.val(num_code);	
								_this_id.val(strId);
							}
						});
			},
			success: function(layerb, index){				
				$('.select').click(function(){
					$('.list').css('display","none');
					$(this).find('ul').show();
				});
				$('.list li').click(function(){
					var str=$(this).text();
					$(this).parent().parent().find('div').find('b').text(str);
					$(this).parent().find('option').prop('selected', '');
					$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
					$('.list').slideUp(50);
					var str_user_image = '';var str_user_image_id = '<select id="userImage_id" style="display:none;">';
					var param = 'mode=guide_userImage&id='+$('#sel_userLeixing').val();
					$.ajax({		    		    
					    type: 'POST',
					    url:"${ctx}/quality/userImageManage.do",
					   	data:param,
					    dataType: 'JSON',
					    success: function(B){	
					    	var str_user_image_temp = '';				    	
					    	for(var i=0; i<B.result.length;i++){
					    		//str_user_image += '<input type="radio" id="chk" value="'+B.result[i].id+'"'+' name="'+B.result[i].name+'"/>'+B.result[i].name+'<br>';
					    		str_user_image += '<input type="checkbox" id="chk" value="'+B.result[i].id+'"'+' name="chk" onClick="javascript:chk_click(this);"/><span>'+B.result[i].name+'</span><br>';					    		
					    	}	
					    	$('#str_user_image').html(str_user_image);
					    				    
					    	
					    	
						}
				    });
					
				});				
				$('.select').click(function(e){
					return false;
				});
				$(document).click(function(){
					$('.list').hide('fast');
				});
				//选择目录弹出框
				$('#propNames01').click(function(){
					initPropList("学科", "${ctx}/propManage/getPropListByDirectAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
					$('.att_make01').show();
				});
				$('.cas_anniu_4,.bjt_kt').click(function(){
					var str_user_image = '';
					selectProp();
					$.ajax({
						type:'post',
						url: '${ctx}/quality/userImageManage.do',
						data:'mode=guide_userImage&id='+$('#sel_userLeixing').val()+'&xuekes='+$('#propIds').val(),
						dataType:'JSON',
						success:function(xuekel){
							var str_user_image_temp = '';				    	
					    	for(var i=0; i<xuekel.result.length;i++){
					    		//str_user_image += '<input type="radio" id="chk" value="'+B.result[i].id+'"'+' name="'+B.result[i].name+'"/>'+B.result[i].name+'<br>';
					    		str_user_image += '<input type="checkbox" onClick="javascript:chk_click(this);" id="chk" value="'+xuekel.result[i].id+'"'+' name="chk" /><span>'+xuekel.result[i].name+'</span><br>';					    		
					    	}	
					    	$('#str_user_image').html(str_user_image);
						}
					});
					
					$('.att_make01').hide();
				});
				$(".btn1").click(function(){
						
						
											
				});
			}
		});
			}
		});
		
	}); */
/*
 *@auth ZJG
 *@time  2016-12-27
 *方法说明   点击下一步 并通过AJAX方式修改表单信息
 */
function show_div(){
	showNext();
}

function showNext(){
	$('#course_create_1').show();
	$('.next_btn').show();
	$('.edit_project').show();
	$('#mainPage').hide();	
	var str = '<h2 id="the_top">目录</h2>';
	$.ajax({
		type:'post',
		url:'${ctx}/CVSetManage.do',
		data:'mode=getByAjax&id='+"${View[0].id}",
		dataType:'json',
		success:function(result){
			var obj = (null==result.list[0])?[]:result.list[0].CVScheduleList;
			for(var j=0; j<obj.length; j++){
					str += '<table id="table_'+ obj[j].id+'" class="mt10 table">'+
					'<tr align="center" valign="middle" id = "' + obj[j].id+'">' +
						'<th colspan="5">' +
							'<input type="radio" id="' + obj[j].id+'" name="cv" value="'+obj[j].id+'" disabled/> 课程名称:' + obj[j].name + 				         		 			
				    		'</th></tr>'+
					'<tr class="tr"><th width="10%">序号</th><th width="40%">课题</th><th width="20%">类别</th><th width="15%">任务点</th><th width="15%">完成状态</th></tr>';
				for(var i=0; i<obj[j].unitList.length;i++){
					str += '<tr align="center"><td width="10%">' + obj[j].unitList[i].id +
						'</td><td width="40%"  onclick="javascript:show_quality_area(this);">' + obj[j].unitList[i].name+'</td><td width="20%">';
							if(obj[j].unitList[i].type == 1) str+='理论讲解'; 
							if(obj[j].unitList[i].type == 2) str+='主题讨论'; 
							if(obj[j].unitList[i].type == 3) str+='随堂考试';
							if(obj[j].unitList[i].type == 4) str+='操作演示'; 
							if(obj[j].unitList[i].type == 5) str+='扩展阅读'; 
							if(obj[j].unitList[i].type == 6) str+='病例分享'; 
					str +='</td><td width="15%"><input disabled type="checkbox" name="point" onClick="javascript:testPoint(this, "' + obj[j].unitList[i].id+'");"';
							if(obj[j].unitList[i].point == 1) str+=' checked' +'/>'; 
						 str += '</td><td width="15%"><input disabled type="checkbox" name="state" onClick="javascript:testState(this, "'+obj[j].unitList[i].id+'");"';
							if(obj[j].unitList[i].state == 1) str+=' checked'+ '/>';
						str += '</td></tr>';
				}
				str += '</table>';
				$('.left_cont').html(str);
			}
			
		}
	});
}
	
function getCourse(){
	var proId = '${proId}';
	window.open("${ctx}/getCourseList.do?method=getCourseList&proId="+proId);	
//$('.no_pass').hide();
//$('.left_cont').show();
	//window.open("${ctx}/getCourseList.do?method=list");
//
}

var cv_count = 0;
function getCount() {
	return cv_count + 1;
}

function increCount() {
	cv_count ++;
}

function decreCount() {
	cv_count --;
}

function refresh_left_cont(str) {
	$('.left_cont').html($('.left_cont').html() + str);
	$('#edit_project').show();
	
	$('input[name="cv"]').each(function(){
		$(this).next().text($(this).prop("id"));
	});
	
}

function to_up() {
	

	var obj = $('input[name="cv"]:checked');
	var id_ = obj.prop("id");
	if(id_ == undefined){
		alert("请选择课程！");
		return;
		
	}
	var tbl_obj = $('#table_'+id_);
	var preTbl_obj = tbl_obj.prev();
	if(preTbl_obj.prop("id") == "the_top"){		
		return false;
	}
	$('#table_'+id_).remove();
	tbl_obj.insertBefore(preTbl_obj);
	
}

function to_down() {	
	var obj = $('input[name="cv"]:checked');
	var id_ = obj.prop("id");
	if(id_ == undefined){
		alert("请选择课程！");
		return;
		
	}
	var tbl_obj = $('#table_'+id_);
	var nextTbl_obj = tbl_obj.next();
	if(nextTbl_obj.length == 0){		
		return false;
	}
	$('#table_'+id_).remove();
	tbl_obj.insertAfter(nextTbl_obj);
}

function remove_cv() {

	var obj = $('input[name="cv"]:checked');	
	var id_ = "#table_" + obj.prop("id");
	if(id_ == '#table_undefined'){
		alert("请选择要删除的课程！");
		return;
	}
	$(id_).remove();	
	
}

function detail() {
	var cv_id = $('input[name="cv"]:checked').val();
	
}
function save(){	
	$('#report').val($('#report1').val());
	var ids = '';
	
	$('input[name="cv"]').each(function(){
		ids += $(this).val() + ",";
	});
	
	$('input[name="cvIds"]').val(ids);

	$(frm_add).submit();
}

function clone(){	
	window.location.href = '${ctx}/CVSet/CVManage.do?mode=edit&id=' + $('.clone_btn').val();
}
function bagaoOpen(){
	$('#bagao').show();
	$('#course_create_1').hide();
	$('.no_pass').hide();
}
function previous(){
	$('#bagao').hide();
	$('#course_create_1').show();
	$('#union').hide();
	$('.left_cont').show();
	$('.no_pass').hide();
}
function openBagao(){
	$('.report_cont').show();
	$('.no_pass, #union').hide();
}
function viewDetail(){
	$('#union').show();
	$('.left_cont, .right_cont').hide();
	$('.v_cont, .report_cont').hide();
	$('.no_pass').hide();
	var left_cont_v = '<div class="control_bar clear">' +
							'<span class="btn_blue" id="huiban" style="margin-left:230px"><a href="javascript:huiban();">返回</a></span>	' +			
							'<span class="btn_blue btn_save" style="margin-left:10px"><a href="javascript:saveUnion();">保存</a></span>	' +	
						'</div>';
	
	$.ajax({
		type:'post',
		url:'${ctx}/CVSet/CVUnitAdd.do',
		data:'mode=getUnitForCV&id='+ $('#cvIds').val(),
		dataType:'JSON',
		success:function(list){
			for(var i=0; i<list.result.length; i++){				
				left_cont_v += '<h2>课程'+(i+1)+'：'+list.result[i].name+'</h2><ul>';   
				for(var j=0; j<list.result[i].unitList.length; j++){
					left_cont_v += '<li onClick="javascript:itemClick('+list.result[i].unitList[j].id+')">'+(i+1)+'.'+(j+1)+':'+list.result[i].unitList[j].name+'</li>';
				}
				left_cont_v += '</ul>';
			}			
			$('.left_cont_v').html(left_cont_v);
		}
	});
}

function chk_click(obj) {
	var str = '';
	$.ajax({
		type:'post',
		url:'${ctx}/quality/userImageManage.do',
		data:'mode=xiangmu&id='+$(obj).val(),
		dataType:'JSON',
		success:function(xueke){
			for(i=0; i<xueke.result[0].departmentPropList.length; i++){
				str += xueke.result[0].departmentPropList[i].name + ",";
			}
			if($(obj).prop("checked") == true){
				$('#xueke').val($('#xueke').val()+str);
			}else{
				del_ = replaceString(str,"",$('#xueke').val());
				$('#xueke').val(del_);
			}
		}
	});
}
function replaceString(oldS,newS,fullS) {
// Replaces oldS with newS in the string fullS
   for (var i=0; i<fullS.length; i++) {
      if (fullS.substring(i,i+oldS.length) == oldS) {
         fullS = fullS.substring(0,i)+newS+fullS.substring(i+oldS.length,fullS.length);
      }
   }
   return fullS;
} 
function delem1(obj){
		var i = $(obj).index();
		//delete text
		var selstr = $(obj).parent().html();
		var selarray = selstr.split(",");
		var newnarray = new Array();
		$(selarray).each(function(key, val){
			if (key != i) newnarray.push(val);
		});
		$('.teacher_Name').html(newnarray.toString());
		
		//delete code
		var deletecode = "";
		selstr = $('.xs_kuangcode').text();
		selarray = selstr.split(",");
		newarray = new Array();
		$(selarray).each(function(key, val){
			if (key != i) newarray.push(val);
			else
				deletecode = val;
		});
		$('.xs_kuangcode').text(newarray.toString());

		//delete check
		var checklist = $('.attr_xueke04');
		$(checklist).each(function(key, val){
			if(deletecode == $(val).prop('id'))
				$(val).siblings().eq(0).prop("checked", "");
		});

		$('.delem1').off('click');
		$('.delem1').click(function(){
			delem1($(this));
		});
		
}
function delem(obj){
		var i = $(obj).index();
		//delete text
		var selstr = $(obj).parent().html();
		var selarray = selstr.split(",");
		var newnarray = new Array();
		$(selarray).each(function(key, val){
			if (key != i) newnarray.push(val);
		});
		$('.xs_kuang').html(newnarray.toString());
		
		//delete code
		var deletecode = "";
		selstr = $('.xs_kuangcode').text();
		selarray = selstr.split(",");
		newarray = new Array();
		$(selarray).each(function(key, val){
			if (key != i) newarray.push(val);
			else
				deletecode = val;
		});
		$('.xs_kuangcode').text(newarray.toString());

		//delete check
		var checklist = $('.attr_xueke04');
		$(checklist).each(function(key, val){
			if(deletecode == $(val).prop('id'))
				$(val).siblings().eq(0).prop("checked", "");
		});

		$('.delem').off('click');
		$('.delem').click(function(){
			delem($(this));
		});
		
}
function initPropList(_title,_ajaxurl, _initType, _initId, _selectLvl, _checkLvl, _kuangcode, _kuang){
		$('.tit_biaoti').text(_title);
		$('.xs_selectlvl').text(_selectLvl);
		$('.xs_checklvl').text(_checkLvl);
		$('.xs_currentid').text(_initId);

		$('.xs_er').each(function(){ $(this).eq(0).removeClass('xs_er').addClass('xs_san');});
		$('.xs_er').each(function(){ $(this).remove();});
		$('.xs_san').each(function(){ $(this).eq(0).removeClass('xs_san').addClass('xs_er');});
		$('.xs_er i').each(function(){ $(this).show();});
		$('.xs_er em').each(function(){ $(this).hide();});
		
		kuangcode = _kuangcode;
		kuang = _kuang;
		ajaxurl = _ajaxurl;
		
		if(_title == "学科"){
			$('.xs_biaoti .xs_er .attr_xueke01').each(function () {
				$(this).text('一级学科');
			});
			initsubmenu="一级学科";
		}
		$('.xs_kuangcode').text($(_kuangcode).val());
		//$('.xs_kuang').text($(_kuang).text());
		
		var selstr = $(_kuang).text();
		selarray = selstr.split(",");
		var newnarray = new Array();
		$(selarray).each(function(key, val){
			if (val != "") newnarray.push('<em class="delem">' + val + '</em>');
		});
		$('.xs_kuang').html(newnarray.toString());		

		$('.delem').click(function(){
			delem($(this));
		});

		var url;
		/*if (_initType < 0) 
			url = ajaxurl + "&id="+ _initId;
		else */
			url = ajaxurl + "?type="+ _initType +"&id="+ _initId + "&mode=getListByType";
			
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
			   		updatePropList(result);
			   };
		    }
		});		
	}
function initPropWndProp(){
	
	$('.attr_xueke04').off('click');
	$('.attr_xueke04').click(function(){
		var curid = eval($('.xs_currentid').text());
		var selid = $(this).prop('id');
		var selname = $(this).text();
		if (selid < 1) return false;
		var a = $(this).find('i').length;
		if (!a) return false;
		
		$('.xs_currentid').text(selid);
		var ms = $('.xs_biaoti .attr_xueke01').length-1;
		$('.xs_er i').hide();
		$('.xs_er em').show();
	//	$('.xs_er').eq(ms).find('i').show();
	//	$('.xs_er').eq(ms).find('em').hide();
		
		var str = '<div class="fl xs_er"><p class="fl attr_xueke01" id=' + curid + '>' + selname + '</p><i class="fl xs_bjt01"></i><em class="fl" style="display:none;">></em></div>';
		$('.xs_er').eq(ms).after(str);
		if(curid == 0)	$('.xs_er').eq(0).remove();
		var url = ajaxurl + "?id=" +selid + "&mode=getListByType";
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
			   		updatePropList(result);
			   };
		    }
		});
	});
	
	$('.attr_xueke01').off('click');
	$('.attr_xueke01').click(function(){
		var curid = eval($('.xs_currentid').text());
		var selid = $(this).prop('id');
		var selname = $(this).text();
		if (selid == '') return false;
		
		$('.xs_currentid').text(selid);
		var inx = $(this).parent().index();
		if (inx<0)return;
		$('.xs_er').each(function(key, val){
			if (key >= inx)
				$(val).remove();
			if (key == inx-1){
				$(val).find('i').show();
				$(val).find('em').hide();
			}
				
		});

		if (selid == 0) $('.xs_biaoti').html('<div class="fl xs_er"><p class="fl attr_xueke01">'+initsubmenu+'</p><i class="fl xs_bjt01"></i><em class="fl" style="display:none;">></em></div>');

		var url = ajaxurl + "?id=" +selid + "&mode=getListByType";
		
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
			   		updatePropList(result);
			   };
		    }
		});
	});
	
	$('.xs_ul input[type="checkbox"]').off('click');
	$('.xs_ul input[type="checkbox"]').click(function(){
		var p = $(this).parent().find('.attr_xueke04').eq(0);
		var id = $(p).prop('id');
		var propname = '<em class="delem">' + $(p).find('em').text() + '</em>';
		
		if ($(this).prop('checked')){
			var selstr = $('.xs_kuangcode').text();
			var selarray = selstr.split(",");
			var newarray = new Array();
			$(selarray).each(function(key, val){
				if (val != "") newarray.push(val);
			});
			newarray.push(id);
			$('.xs_kuangcode').text(newarray.toString());

			selstr = $('.xs_kuang').html();
			selarray = selstr.split(",");
			var newnarray = new Array();
			$(selarray).each(function(key, val){
				if (val != "") newnarray.push(val);
			});
			newnarray.push(propname);
			$('.xs_kuang').html(newnarray.toString());
		}
		else{
			var selstr = $('.xs_kuangcode').text();
			var selarray = selstr.split(",");
			var idx = selarray.indexOf(id);
			var newarray = new Array();
			$(selarray).each(function(key, val){
				if (key != idx) newarray.push(val);
			});
			$('.xs_kuangcode').text(newarray.toString());

			selstr = $('.xs_kuang').html();
			selarray = selstr.split(",");
			var newnarray = new Array();
			$(selarray).each(function(key, val){
				if (key != idx) newnarray.push(val);
			});
			$('.xs_kuang').html(newnarray.toString());
		}
		
		$('.delem').off('click');
		$('.delem').click(function(){
			delem($(this));
		});
	
	});
	
	//selected item mark checked.
	$('.xs_ul input[type="checkbox"]').each(function(key, val){
		var p = $(this).parent().find('.attr_xueke04').eq(0);
		var id = $(p).prop('id');
		var selstr = $('.xs_kuangcode').text();
		var selarray = selstr.split(",");
		var idx = selarray.indexOf(id);
		
		if (idx>=0) $(this).prop("checked", true);
	});
}
function updatePropList(result){
		
		var str = "";
		var check = eval($('.xs_checklvl').text());
		var select = eval($('.xs_selectlvl').text());
		$(result.list).each(function(key, value){
			str += "<li><div class=''>";
			if (check <= value.type)
				str += '<input class="fl" style="margin-top:5px;" type="checkbox">';

			str += '<p class="fl attr_xueke04"' + ' id="'+ value.prop_val_id +'"' + '><em class="fl">' + value.name + '</em>';
			if (select > value.type){
				str += '<i class="fl ml10 kti_bjt2"></i>';
			}
			str += "</div><div class='clear'></div></li>";
		});
		
		$('.xs_ul').html(str);
		initPropWndProp();
}


function selectProp(){
		$(kuangcode).val($('.xs_kuangcode').text());
		$(kuang).text($('.xs_kuang').text());
		
}
$('#qualify_btn').click(function(){
	$.ajax({
		type:'post',
		url:'${ctx}/CVSetManage.do',
		data:'mode=getByAjax&id='+"${View[0].id}",
		dataType:'json',
		success:function(result){
			var strReasons = '',str = '';
			var strReasonsRaw = new Array('内容不完整', '内容与所选胜任力指标体系不符', '内容有错误','课程形式设置有误', '格式不符合要求', '课程起止时间有误', '授课教师与实际不符', '商业倾向过于明显', '其他原因 ');
			
			$('.notice_input').val(result.list[0].opinion);
			strReasons = result.list[0].opinionType.split("^");	
			
			for(var i=0; i<strReasons.length-1; i++){
				str += (i+1)+"."+strReasonsRaw[strReasons[i]-1]+"\n";				
			}
			$('textarea[name="reason"]').val(str);
			
		}
	});
	
	$('.no_pass').show();
	$('.left_cont, .right_cont').hide();
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
function saveUnion(){
	str_content = ue.getContent();
			//window.location.href = "/peixun/CVSet/CVUnitAdd.do?mode=updateUnion";
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
		
}
var unitId ='';
var unitName ='';
var unitType='';
var unitPoint ='';
var unitState='';
function show_unit(obj)
{
	 
		var url = '${ctx}/CVSet/CVManage.do';
		var params = 'mode=getUnit&id='+$(obj).val();
		$.ajax({
			url		:url,
			data	:params,
			type	:'post',
			dataType:'json',
			success:function(result){
				unitId='';
				unitName='';
				unitType='';
				unitPoint='';
				unitState ='';
			 if(result != ''){
				for(var i=0;i<result.result.length;i++){
					unitId += result.result[i].id+",";
					unitName += result.result[i].name +",";
					unitType += result.result[i].type + ",";
					unitPoint += result.result[i].point + ",";
					unitState += result.result[i].state + ",";
				}
			}
			/* $(obj).next().next().after(unitId); 
			$(obj).next().next().after(unitName); */
			//$(obj).next().next().after(unitType);
			//$(obj).next().next().after(unitPoint);
			//$(obj).next().next().after(unitState);
			$(".unitlist").each(function(key,val){
				
				var id = $(this).prop("id");
				id = id.substring(id.indexOf('_',0)+1,id.length);
				if(id != $(obj).val())
				{
					$(this).find(".unitTr").remove();
					$(this).find(".initTr").remove();
					var initHtml = [];
					initHtml.push('<tr align="center" valign="middle" class = "initTr" id = "initTr_'+ id + '">'+
			                		'<td colspan="5" id="unit_td">--请选择课题--</td>'+
			             		  '</tr>');
					$(this).find("tr").last().after(initHtml.join(""));
				}	
			});			
			unitIds = unitId.split(",");
			unitNames = unitName.split(",");
			unitTypes = unitType.split(",");
			unitPoints = unitPoint.split(",");
			unitStates = unitState.split(",");
			var initTr = "#initTr_" + $(obj).val();
			var unitList = "#unitList_" + $(obj).val();
			$(initTr).remove();
    		var html = [];
    		var lastTr = $(unitList).find("tr").last();
    //var index = $("#unitList").find("tr").length;
			for(var i =0;i < unitIds.length-1;i++){
		        html.push("<tr align='center' class = 'unitTr'>");
		        html.push("<td width='10%'> ");
		        html.push("<input type='radio' id='");
		        html.push(unitIds[i]);
		        html.push("' name='cv' value='");
		        html.push(unitIds[i]);
		        html.push("'>&nbsp;");
		        html.push(unitIds[i]);
		        html.push("</td>");
		        html.push("<td width='40%'>");
		        html.push(unitNames[i]);
		        html.push("</td>");
		        html.push("<td width='20%'>");
		        if(unitTypes[i] == 1)
		        {
		        	html.push("理论讲解");
		        }
		        if(unitTypes[i] == 2)
		        {
		        	html.push("主题讨论");
		        }
		       if(unitTypes[i] == 3)
		        {
		        	html.push("随堂考试");
		        }
		       if(unitTypes[i] == 4)
		        {
		        	html.push("操作演示");
		        }
		       if(unitTypes[i] == 5)
		        {
		        	html.push("扩展阅读");
		        }
		       if(unitTypes[i] == 6)
		        {
		        	html.push("病例分享");
		        }        
		        html.push("</td>");
		        html.push("<td width='15%'>");
		        html.push("<input type='checkbox' name='point' ");
		        
		        
		        if(unitPoints[i] == 1)
		        {
		        	html.push(" checked");
		        }
		        html.push(">");
		        html.push("</td>");
		        html.push("<td width = '15%'>");
		        html.push("<input type='checkbox' name='state' ");
		        
		        
		        if(unitStates[i] == 1)
		        {
		        	html.push(" checked");
		        }
		        html.push(">");
		        html.push("</td>");
		        html.push("</tr>");
		        //index++;
		        }
			
	    		lastTr.after(html.join(""));
			}
		});
	 

}
function huiban(){
	$('#union').hide();
	$('.left_cont, .right_cont').show();
	//$('.v_cont, #bagaoshu').hide();
}
function display_union(obj){
	$('#edit_project').show();
	var str ='';
	
	str += '<table id="table_'+ obj.id+'" class="mt10 table">'+
	'<tr align="center" valign="middle" id = "' + obj.id+'">' +
		'<th colspan="5">' +
			'<input type="radio" id="' + obj.id+'" name="cv" value="'+obj.id+'" /> 课程名称:' + obj.name + 
         		'<button class="clone_btn tbn_blue fr" onClick="javascript:clone();" value="' + obj.id+'" type="button">课程信息</button>' +   			
    		'</th></tr>'+
	'<tr class="tr"><th width="10%">序号</th><th width="40%">课题</th><th width="20%">类别</th><th width="15%">任务点</th><th width="15%">完成状态</th></tr>';
for(var i=0; i<obj.unitList.length;i++){
	str += '<tr align="center"><td width="10%">' + obj.unitList[i].id +
		'</td><td width="40%"  onclick="javascript:show_quality_area(this);">' + obj.unitList[i].name+'</td><td width="20%">';
			if(obj.unitList[i].type == 1) str+='理论讲解'; 
			if(obj.unitList[i].type == 2) str+='主题讨论'; 
			if(obj.unitList[i].type == 3) str+='随堂考试';
			if(obj.unitList[i].type == 4) str+='操作演示'; 
			if(obj.unitList[i].type == 5) str+='扩展阅读'; 
			if(obj.unitList[i].type == 6) str+='病例分享'; 
	str +='</td><td width="15%"><input type="checkbox" name="point" onClick="javascript:testPoint(this, "' + obj.unitList[i].id+'");"';
			if(obj.unitList[i].point == 1) str+=' checked' +'/>'; 
		 str += '</td><td width="15%"><input type="checkbox" name="state" onClick="javascript:testState(this, "'+obj.unitList[i].id+'");"';
			if(obj.unitList[i].state == 1) str+=' checked'+ '/>';
		str += '</td></tr>';
}
str += '</table>';	

$('.left_cont').html($('.left_cont').html()+str);
    	

}


var stock = new Array();

function show_quality_area(obj) {

	stock = new Array();

	var unit_id = $(obj).prev().text();
	var image_ids = $('input[name="userImage"]').val();
	
	$('#save_unit_guide_btn').val(unit_id);
	
	var url = '${ctx}/CVSetManage.do';
	var params = 'mode=getDeepQuality&unit_id=' + unit_id + '&image_ids=' + image_ids;
	
	var list = '';
	$.ajax({
		type		: 'POST',
		url			: url,
		data		: params,
		dataType	: 'JSON',
		success:function(result){
			var _stock;
			if (result != '' && result != null) {
				list = result.list;
				if (result.stock != '')
					_stock = result.stock;
				else
					_stock = list;
				
				for (it in _stock) {
					stock.push(_stock[it].id + '_' + _stock[it].level);
				}
			}
			right_area_update(2, list, "二级能力");
		},
		error:function(){
			right_area_update(2, list, "二级能力");
		}
	});
	
}

function update_stock(obj) {
	
	if ($(obj).prop("checked") == true) {
		stock.push($(obj).val());
	} else {
		for (it in stock) 
			if (stock[it] == $(obj).val()) stock.splice(it, 1);
	}
}

function right_area_update(index, list, title) {

	var new_html = '';
	
	if (list != '') {
		for (var i=0; i<list.length; i++) {
			
			var v = list[i].id + '_' + list[i].level;
			
			var checked = '';
			for (it in stock)
				if (stock[it].split("_")[0] == list[i].id) {checked=" checked "; break;}
			
			new_html += '<p class="fl" style="margin-right:20px;">';
			new_html += 	'<input name="chk_guide" onchange="javascript:update_stock(this);" type="checkbox"' + checked + 'class="fl" value="' + v + '"/> <span onclick="javascript:next_index(this);">' + list[i].name + '</span>'; 
			
			if (index < 4)
				new_html += '<i onclick="javascript:next_index($(this).prev());" class="fa fa-caret-right"></i>';
			
			new_html += '</p>';
		}
	} else {
		new_html += '<p>Empty!</p>';	
	}

	if (index > 2) {
		new_html += '<div class="clear"></div>';
		new_html += '<p class="clear cas_anniu center"><a href="#" class="btn_back" style="width:70px;">返回</a></p>';
	}
		
	$(".right_cont .ability_area_"+ index +" .tiaojian").html(new_html);
	$(".right_cont .ability_area").each(function(){$(this).hide();});
	$(".right_cont,.ability_area_" + index).show();
	
	$(".right_cont .ability_area_"+ index +" .tiaojian").prev().text(title);
	
	$(".btn_back").click(function () {
		$(this).parent().parent().parent().hide().prev(".ability_area").show();
	});
}

function next_index(obj) {
	
	var id_level = $(obj).prev().val();
	var tmp = id_level.split("_");
	
	var id = tmp[0];
	var level = tmp[1];
	
	if (level > 3) return;
	
	var url = '${ctx}/qualityManage/guide.do';
	var params = 'handle=getNextStudyGuide&id=' + id + '&level=' + level;
	
	$.ajax({
		type		: 'POST',
		url			: url,
		data		: params,
		dataType	: 'JSON',
		success:function(result){
			var list;
			if (result != '' && result != null) {
				list = result.list;
			}
			
			var title = $(obj).parent().parent().prev().text() + " > " + $(obj).text();
			
			right_area_update(++level, list, title);
		}
	});
	
}

function save_unit_guide(obj) {
	
	var params = '';
	
	$('input[name="chk_guide"]:checked').each(function(){
		params += $(this).val() + ",";
	});
	
	if (params == '') {
		alert('请选择能力模型！');
		return;
	}
	
	var url = '${ctx}/CVSetManage.do';
	params = 'mode=save_guide_ref&id=' + $(obj).val() + '&id_levels=' + params;
	
	
	$.ajax({
		type		: 'POST',
		url			: url,
		data		: params,
		dataType	: 'TEXT',
		success:function(result){
			if (result == 'SUCCESS')
				alert('成功');
		}
	});
}
function propose(){
	$.ajax({
		type:'post',
		url:'${ctx}/CVSetManage.do?mode=updateState&state=2&id=${View[0].id}',		
		dataType:'text',
		success:function(result){
			if(result == 'success'){
				window.location.href = "${ctx}/CVSetManage.do?mode=myXiangMu";
			}else{
				alert("提交失败!");
			}
		}
	});
}

</script>
</body>
</html>