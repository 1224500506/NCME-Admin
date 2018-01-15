<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/commons/taglibs.jsp"%>
		<link href="${ctx}/peixun_page/css/global.css" rel="stylesheet">
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
		<title>培训后台</title>
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
		<%@include file="/commons/taglibs.jsp"%>
		<%@include file="/peixun_page/top.jsp"%>
		<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>		
		<script type="text/javascript" src="${ctx}/peixun_page/js/utils.js"></script>
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/fileUpload.js"></script>
	</head>
<body >
<div class="center"  style="">
	<div class="center01" id ="addwindow">
	<form id="sfrm" name="cvForm" method="POST">
	    <input type=hidden name="id" value="${info.id}">
		<div class="tiaojian" style="min-height:40px;">
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 课程名称：</span>
				<input type="text" name="name" id="name" value="${info.name}" />
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 课程编号：</span>
				<input type="text" name="serial_number" id="serial_number" value="${info.serial_number}" readonly/>
			</p>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 学科：</span>
			</p>
				<input type="hidden" name="propIds" id="propIds" value="${propIds}"/>
				<input type="hidden" name="propNames" id="propNames" value="${propNames}"/>
				<div class="duouan" id="propNames01">${propNames}</div> 
			<div class="clear"></div>
			
			<div class="clear">
				<p class="fl" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 授课形式：</span>
				</p>
				<div class="fl select" style="margin:0 20px 20px 0;">
					<div class="tik_position">
						<b class="ml5" id="selectval">${info.getCvTypeName()}</b>
						<p class="position01"><i class="bjt10"></i></p>
					</div>
					<ul class="fl list" id="_cvType" style="display: none;">
						<select name = "cv_type" id="cv_type" style="display:none">
							
							<option value="0" <c:if test="${info.cv_type == 0}">selected="selected"</c:if>>点播</option>
							<option value="1"<c:if test="${info.cv_type == 1}">selected="selected"</c:if>>面授</option>
							<option value="2" <c:if test="${info.cv_type == 2}">selected="selected"</c:if>>直播</option>							
							
						</select>
						
						<li>点播</li>
						<li>面授</li>
						<li>直播</li>	
					</ul>
				</div>
			</div>
			<div id="db_label" style="display:block;">
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 课程标签：</span>

			<input type="checkbox" name="brand_c" value="1"  <c:if test="${fn:contains(info.brand, 1)}">checked="checked"</c:if>/> 病例
			<input type="checkbox" name="brand_c" value="3"  <c:if test="${fn:contains(info.brand, 3)}">checked="checked"</c:if>/> VR
			<input type="checkbox" name="brand_c" value="4"  <c:if test="${fn:contains(info.brand, 4)}">checked="checked"</c:if>/> 名师课程
			<input type="checkbox" name="brand_c" value="5"  <c:if test="${fn:contains(info.brand, 5)}">checked="checked"</c:if>/> 三维动画
			<input type="checkbox" name="brand_c" value="6"  <c:if test="${fn:contains(info.brand, 6)}">checked="checked"</c:if>/> 其他
			<input type="hidden" id="brand" name="brand"/>
			</p>

			</div>
			<%--面授--%>
			<div id="ms_label" style="display:none;">
				<p class="fl" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 课程标签：</span>
					<span style="text-align:left;">
						<input type="checkbox" name="brand_c" value="7"  <c:if test="${fn:contains(info.brand, 7)}">checked="checked"</c:if>/> 理论
						<input type="checkbox" name="brand_c" value="8"  <c:if test="${fn:contains(info.brand, 8)}">checked="checked"</c:if>/> 操作演示
						<input type="checkbox" name="brand_c" value="9"  <c:if test="${fn:contains(info.brand, 9)}">checked="checked"</c:if>/> 讨论
						<%--<input type="hidden" id="brand" name="brand"/>--%>
					</span>
				</p>
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;">授课地点：</span>
					<input type="text" name="cv_address" id="cv_address"  value="${info.cv_address}" />
				</p>
			</div>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 授课教师：</span>
				<input type="text" name="teacher" class="teacher_1" id="teacher" value="${teacher}"<%-- value="${list.teahcer}" --%> />
				<input type="hidden" name="teacherIds" id="teacherIds" value="${teacherIds}"<%-- value="${list.teahcerIds}" --%>/>

			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"> 示教教师：</span>
				<input type="text" name="otherTeacher" class="teacher_2" value="${otherTeacher}" />
				<input type="hidden" name="otherTeacherIds" id="otherTeacherIds" value="${otherTeacherIds}"<%-- value="${list.otherTeacherIds}" --%>/>
			</p>
			<div id="db_out_chain" style="display:block;">
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em>添加外部链接：</span>
					<span style="width:9em;text-align:left;">
						<input  name="is_add_out_chain" type="radio" value="0" <c:if test="${fn:contains(info.is_add_out_chain, 0)}">checked="checked"</c:if>/>否
						<input name="is_add_out_chain" type="radio" value="1" <c:if test="${fn:contains(info.is_add_out_chain, 1)}">checked="checked"</c:if>/>是
					</span>
				</p>
				<p id="p_cv_url" class="clear" style="margin-bottom:20px;display: none">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em>课程网址：</span>
					<input type="text" name="cv_url" id="cv_url"  value="${info.cv_url}" />
				</p>
			</div>
			<!-- 直播参数部分start -->
			<div style="display:none" id="zhibo">
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 直播SDK  ID：</span>
					<input type="text" name="class_no" id="class_no" class="class_no" value="${live.class_no}"/>
				</p>
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 开始时间：</span>
					<input type="text" name="start_date" id="start_date" class="editInput"
				datatype="*" nullmsg="请选择授权开始时间！" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'invalid_date\')}'})"  value="<fmt:formatDate value="${live.start_date}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
				</p>
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 失效时间：</span>
					<input type="text" name="invalid_date" id="invalid_date" class="editInput"
				datatype="*" nullmsg="请选择授权开始时间！" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'start_date\')}'})" value="<fmt:formatDate value="${live.invalid_date}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</p>
				<%-- <p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 教师加入URL：</span>
					<input type="text" name="teacher_url" class="teacher_url"  value="${live.teacher_join_url}" />
				</p> --%>
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 学生加入URL：</span>
					<input type="text" name="student_url" class="student_url" value="${live.student_join_url}"/>
				</p>
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 是否需要报名：</span>
					<span style="text-align:left;">
						<input  name="is_need_apply" type="radio" value="0" checked/>否
						<input name="is_need_apply" type="radio" value="1" />是
					</span>
					<input type="text" name="apply_num" id="apply_num" disabled="disabled" style="width:40px;"/>
				</p>
				<%-- <p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 老师口令：</span>
					<input type="text" name="teacher_token" class="teacher_token" value="${live.teacher_token}"/>
				</p>
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 助教口令：</span>
					<input type="text" name="assistant_token" class="assistant_token" value="${live.assistant_token}"/>
				</p> --%>
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 学生口令：</span>
					<input type="text" name="student_token" class="student_token" value="${live.student_token}"/>
				</p>
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"> 直播回放信息：</span>
					<c:if test="${isExistWare == 0 }">
						<input type="button" onclick="createCourseware();" style="color:#4778c7;cursor:pointer;" value="一键生成回放信息">
					</c:if>
					<c:if test="${isExistWare == 1 }">
						<input type="button" style="color:#4778c7;cursor:pointer;" value="查看回放课件信息" class="QueryView">
					</c:if>
				</p>

				<input type="hidden" id="makeType" name="makeType" value="${live.course_make_type}">
				<div id="one">
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:10em;text-align:right;"><em class="red_start">*</em> 是否设置点播课程：</span>
					<input type="radio" name="course_make_type" value="2" <c:if test="${live.course_make_type == 2}">checked="checked"</c:if> style="margin-top:7px;float:left;" onClick="radiocheck();" disabled><span style="color:#333;">是</span>

					<c:if test="${live.course_make_type == 2}">
						<input type="radio" name="course_make_type" value="0" style="margin-top:7px;float:left;margin-left:15px;" onClick="radiocheck();" disabled><span style="color:#333;">否</span>
					</c:if>
					<%-- <c:if test="${live.course_make_type != 2}">
						<c:if test="${live.course_make_type == 1}">
						<input type="radio" name="course_make_type" value="1" style="margin-top:7px;float:left;margin-left:15px;" onClick="radiocheck();" checked="checked"><span style="color:#333;">否</span>
						</c:if>
						<c:if test="${live.course_make_type == 0}">
						<input type="radio" name="course_make_type" value="0" style="margin-top:7px;float:left;margin-left:15px;" onClick="radiocheck();" checked="checked"><span style="color:#333;">否</span>
						</c:if>
					</c:if> --%>
				</p>
				</div>
				<div id="two">
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:10em;text-align:right;"><em class="red_start">*</em> 是否设置点播课程：</span>
					<input type="radio" name="course_make_type" value="2" style="margin-top:7px;float:left;" onClick="radiocheck();"><span style="color:#333;">是</span>

					<c:if test="${live.course_make_type != 2}">
						<c:if test="${live.course_make_type == 1}">
						<input type="radio" name="course_make_type" value="1" style="margin-top:7px;float:left;margin-left:15px;" onClick="radiocheck();" checked="checked"><span style="color:#333;">否</span>
						</c:if>
						<c:if test="${live.course_make_type == 0}">
						<input type="radio" name="course_make_type" value="0" style="margin-top:7px;float:left;margin-left:15px;" onClick="radiocheck();" checked="checked"><span style="color:#333;">否</span>
						</c:if>
					</c:if>
				</p>
				</div>
			</div>
			<!-- 直播参数部分end -->
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 课程简介：</span>
				<textarea  name="introduction" id="introduction" style="width:400px;" rows="5" >${info.introduction}</textarea>
			</p>
			<p class="fl" style="margin-bottom:20px;width:526px">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 课程封面：</span>
				<img src="${info.file_path}" class="file_path" style="width:200px;height:200px;"/>
				<input type="file" name="matFile" id="matFile" style="margin-left:330px;margin-top:-40px;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp">
				<input type="hidden" name="file_path" id="file_path" value="${info.file_path}">
			</p>
			<div class="clear"></div>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;">课程声明：</span>
				<textarea  name="announcement" id="announcement" style="width:400px;" rows="5">${info.announcement}</textarea>
			</p>
			<div class="clear"></div>
			<p class="fl cas_anniu" style="margin-left:9.1em;">
				<a href="javascript:void(0);" class="fl next_step" id="next_step" style="width:140px;margin-left:10px;" onclick="showNextView();">下一步</a>
			</p>
			<p class="fl cas_anniu">
				<a href="javascript:window.history.go(-1);" class="fr" style="width:70px;margin-left:10px;">返回</a>
			</p>
		</div>
	</form>
		<div class="clear"></div>
	</div>


<div id="next_page" style="display:none">
<div class="top_cont">
	<h2>${info.name}</h2>
</div>
<div class="sub_nav">
	<!--<span class="btn_blue fr btn_no btn_save"><a href="javascript:saveCourse()">保存</a></span>-->
	<span class="btn_blue fr" id="showAddwindow" ><a href="#"  >返回</a></span>
	<span class="btn_blue fr" ><a href="#" onclick="save();" id="">保存</a></span>
	<span class="add_course btn_no"><a href="javascript:void(0);">添加单元</a></span>
	<span class="btn_no"><a href="javascript:deleteUnit();">删除单元</a></span>
	<span class="btn_no"><a href="javascript:to_up();">上移</a></span>
	<span class="btn_no"><a href="javascript:to_down();">下移</a></span>
	<!--<span class="btn_blue next_btn" style="display:none;"><a href="${ctx}/CVSet/CVUnitAdd.do?mode=unionEdit&id=${info.id}">编辑</a></span> -->
	<span class="btn_blue next_btn" style="display:none;"><a href="javascript:void(0);" onclick="edit();">编辑</a></span>

</div>
<div class="center" id="addcontent">
	<div class="center01">
		<c:if test="${info.cv_type != 2}">
			<button class="btn_blue clone_course btn_no" type="button" >克隆课程</button>
		</c:if>
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
						<select name ="type"  id = "sel_SearchDutyName" style = "display:none;">
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
			<div id="old">
			<%--
			<display:table name="unionList" id="p" class="mt10 table">
						<display:column title="序号" style="width:10%"><input type="radio" id="${p.id}" name="cv" value="${p.id}">&nbsp;${p.id}</display:column>
						<display:column title="课题" style="width:40%">${p.name}</display:column>
						<display:column title="类别" style="width:20%">
							<c:if test="${p.type==1}">理论讲解</c:if>
							<c:if test="${p.type==2}">主题讨论</c:if>
							<c:if test="${p.type==3}">随堂考试</c:if>
							<c:if test="${p.type==4}">操作演示</c:if>
							<c:if test="${p.type==5}">扩展阅读</c:if>
							<c:if test="${p.type==6}">病例分享</c:if>
						</display:column>
						<display:column title="任务点" style="width:15%">
							<input type="checkbox" name="point" id="point_${p.id}" onClick="javascript:testPoint(this,${p.id});"  <c:if test="${p.point==1}">checked="checked"</c:if>>
						</display:column>
						<display:column title="完成状态" style="width:15%">
							<input type="checkbox" name="state" id="state" onClick="javascript:testState(this,${p.id});" <c:if test="${p.state==1}">checked="checked"</c:if>>
						</display:column>
			</display:table>
			 --%>
			</div>
			<div id="new">
				<table id="unitList" class="mt10 table">
				<tr class="tr" id="title_tr">
					<th width="10%">序号</th>
					<th width="40%">课题</th>
					<th width="20%">类别</th>
					<th width="7.5%">任务点</th>
					<th width="10%">达标指标</th>
					<th width="10%">完成状态</th>

				</tr>
				<c:forEach items="${unionList}" varStatus="status" var="p">
				<tr class="tr" id="title_tr">
					<th>
						<input type="radio" id="${p.id}" name="cv" value="${p.id}">&nbsp;${p.id}
					</th>
					<th>

					<input type="text" id="name_${p.id}" value = "${p.name}" onblur="javascript:updateName(this,${p.id})">

					</th>
					<th>
<%-- 						<c:if test="${p.type==1}">理论讲解</c:if>
						<c:if test="${p.type==2}">主题讨论</c:if>
						<c:if test="${p.type==3}">随堂考试</c:if>
						<c:if test="${p.type==4}">操作演示</c:if>
						<c:if test="${p.type==5}">扩展阅读</c:if>
						<c:if test="${p.type==6}">病例分享</c:if> --%>

						<select id="type_${p.id}" onchange="javascript:updateType( this.options[this.options.selectedIndex].value,${p.id})">
						  <option value =1  <c:if test="${p.type==1}">selected="selected"</c:if>>理论讲解</option>
						  <option value = 2 <c:if test="${p.type==2}">selected="selected"</c:if>>主题讨论</option>
						  <option value= 3 <c:if test="${p.type==3}">selected="selected"</c:if>>随堂考试</option>
						  <option value=4 <c:if test="${p.type==4}">selected="selected"</c:if>>操作演示</option>
						  <option value=5 <c:if test="${p.type==5}">selected="selected"</c:if>>扩展阅读</option>
						  <option value=6 <c:if test="${p.type==6}">selected="selected"</c:if>>病例分享</option>
						</select>

					</th>
					<th>
						<input type="checkbox" name="point" id="point_${p.id}" onClick="javascript:testPoint(this,${p.id});"  <c:if test="${p.point==1}">checked="checked"</c:if>>
					</th>
					<th>
					${ p.quota}
					</th>
					<th>
						<input type="checkbox" name="state" id="state" onClick="javascript:testState(this,${p.id});" <c:if test="${p.state==1}">checked="checked"</c:if>>
					</th>

				</tr>
				</c:forEach>
			</table>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>
</div>
<div id="container"></div>
<!-- layer内容 -->
<div id="layercontent" style="display:none;">
<div class="center">
<form name="editfrm"  method="post">
	<input type="hidden" id="userId" name="model.userId" value="0" />
	<div class="tiaojian" style="min-height:40px;">
		<table>
             <thead>
             <tr>
                 <th style="text-align: left;">课件名称</th>
                 <th style="text-align: left;">回放地址</th>
                 <th style="text-align: left;">SDK ID</th>
                 <th style="text-align: left;">口令</th>
                 <th>操作</th>
             </tr>
             </thead>
             <tbody id="tbodyX">
               <c:forEach items="${wareList}" var="ware" varStatus="SerialNumber" >
             	<tr>
                 <td><input type=text value="${ware.subject}" id="subject${SerialNumber.index}" style="border:0px;width:160px;" readonly="readonly"></td>
                 <td><input type=text value="${ware.courseware_url}" id="coursewareUrl${SerialNumber.index}" style="border:0px;width:160px;" readonly="readonly"></td>
                 <td><input type=text value="${ware.courseware_no}" id="coursewareNo${SerialNumber.index}" style="border:0px;width:160px;" readonly="readonly"></td>
                 <td><input type=text value="${ware.courseware_token}" id="coursewareToken${SerialNumber.index}" style="border:0px;width:160px;" readonly="readonly"></td>
                 <td>
                 	<a href="javascript:editWare('${SerialNumber.index}');" id="editWare${SerialNumber.index}" style="color:#4778c7;display:block;">编辑</a>
                 	<a href="javascript:saveWare('${SerialNumber.index}');" id="saveWare${SerialNumber.index}" style="color:red;display:none;">保存</a>
                 </td>
             	</tr>
              </c:forEach>
             </tbody>
         </table>
	</div>
</form>
</div>
</div>
<script type="text/javascript">
$('#unitList').bind('DOMNodeInserted', function(e) {
   var unTrHtmlTmp = $(e.target).html() ;
   if (unTrHtmlTmp.indexOf('cv') != -1 && unTrHtmlTmp.indexOf('point') != -1 && unTrHtmlTmp.indexOf('state') != -1) {
      //alert('element now contains: ' + unTrHtmlTmp);
      var allUnitIdObj = $('input[name="cv"]') ;
	  if (allUnitIdObj != undefined) {
         var allUid = "" ;
		 for (var i = 0 ; i < allUnitIdObj.length ;i++) {
            if (allUid == "") allUid = allUnitIdObj[i].value ;
            else allUid = allUid + ',' + allUnitIdObj[i].value ;
         }
         if (allUid != "") {
             var refUrl = "${ctx}/CVSet/CVManage.do";
			 var refParams = "mode=saveUnitSequence&unid=" + allUid;
			 $.ajax({
							url		:refUrl,
							data	:refParams,
							type	:'post',
							dataType:'json',
							async:false,
							success:function(data1){
								//var result1 = eval(data1);
                                if (data1.result != 'success') {
									alert('修改单元顺序失败!');
								}
							},
			                error:function(data2){
                               //var result2 = eval(data2);
			 	               alert('修改单元顺序失败：' + data2);
			                }
			 });
         }
      }
   }
});

//保存课程成功后的id
var classId = "";
$(document).ready(function () {
    var select_add_out_chain =$("input[name='is_add_out_chain']:checked").val();
    console.log("select_add_out_chain="+select_add_out_chain);
    if(select_add_out_chain==1){
        $("#p_cv_url").show();
    }else{
        $("#p_cv_url").hide();
    }
})

$(function(){
		//获取授课形式
		var _qct = $("#cv_type").val();
		var _qctval = $("#cv_type option:selected").text();
		var typeForCourse = $("input[name='course_make_type']:checked").val();
		if(_qct == 2 || _qctval == "直播"){
			$('#db_label').hide();
			$('#zhibo').show();
			if(typeForCourse == 0 || typeForCourse == 1){
				$("#two").show();
				$("#one").hide();
				$('.next_step').html("保存");
			}else if(typeForCourse == 2){
				$("#one").show();
				$("#two").hide();
				$('.next_step').html("下一步");
			}

		}else if(_qct == 1 || _qctval == "面授"){
				$('#db_label').hide();
				$('#zhibo').hide();
				$('.next_step').html("下一步");
		}else{
			$('#db_label').show();
			$('#zhibo').hide();
			$('.next_step').html("下一步");
		}
	    //


		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "lc_left0" + menuindex;
		$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");

		/* if("${view == 2}") showNextView();
			//else show_first();
		$(".next_step").click(function () {
			showNextView();
		});*/

		select_init();

		$('#showAddwindow').click(function(){
			location.href="${ctx}/CVSet/CVManage.do?mode=list";
			//show_first();
		});

		$('#propNames01').click(function(){
			initPropList("学科", "${ctx}/propManage/getPropListByDirectAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
			$('.att_make01').show();
		});
		$('.cas_anniu_4,.bjt_kt').click(function(){
			$('.att_make01').hide();
		});

		var obj = $('input[name="cv"]');
		if (obj.prop("id")!=null) $(".table,.next_btn").show();
		if (obj.prop("id")==null) $(".table,.next_btn").hide();


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
	$(".btn_save").click(function () {
		if (p_course != null && p_course == 1){
			window.location.href = "course_clone.html";
		}else{
			window.location.href = "subject_list.html";
		}
	});

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

	/*
	 * @auth  张建国
	 * @time  2017-01-05
	 * 说   明：通过AJAX保存单元信息
	 */
	$(".btn_confirm").click(function(){
		$('.add_union').hide();
		$(".table").show();
		$(".next_btn").show();
		if($('#point')[0].checked){
            $('#point').val(1);
        }else{
        	$('#point').val(0);
        }
		//window.localStorage.setItem("add_course","1");
		//document.getElementById("createUnionForm").action = "${ctx}/CVSet/CVUnitAdd.do?mode=addUnionUpdate&id="+'${info.id}';
		//document.getElementById("createUnionForm").submit();
		$.ajax({
				url		:'${ctx}/CVSet/CVUnitAdd.do',
				data	:'mode=addUnionUpdate&point='+$('#point').val()+'&type='+$('#sel_SearchDutyName').val()+'&name='+$('#unitName').val()+'&id='+'${info.id}',
				type	:'post',
				dataType:'json',
				async:false,
				success:function(data){
					var result = eval(data);
					if(result.result.message=='success'){
						//保存课程与单元的关联关系
						var refUrl = "${ctx}/CVSet/CVUnitAdd.do";
						var refParams = "mode=addUnionRef&cvId="+classId+"&unitId="+result.result.unitId;
						$.ajax({
							url		:refUrl,
							data	:refParams,
							type	:'post',
							dataType:'json',
							async:false,
							success:function(data2){
								var result2 = eval(data2);
							}
						});
						if(data!=''){
							refresh_left_cont(data);
						}
					}
				}
			});
		$('#unitName').val("");
		$('#point').prop("checked",false);
		$('#sel_SearchDutyName').parent().parent().find("b").text("理论讲解 ");
		$('#sel_SearchDutyName').val("1");
		$('.add_union').hide();
		/*$.ajax({
			type:'post',
			url:'${ctx}/CVSet/CVUnitAdd.do?mode=addUnionUpdate&id=${info.id}',
			data:$("#createUnionForm").serialize(),
			type	: 'POST',
			dataType: 'JSON',
			success:function(data){
				alert(data);
				refresh_left_cont();
				//刷新页面
				//location.href="${ctx}/CVSet/CVManage.do?mode=edit&id=${info.id}";
				//if(data=='success'){
					//alert("保存成功!");
				//}
			}
		});*/
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
		window.open('${ctx}/CVSet/CVUnitAdd.do?mode=clone&classId='+classId);
	});


	var win_w = parseInt($(window).width() / 3) + "px";
	var win_h = parseInt($(window).height() / 1.5) + "px";



//选择目录弹出框

	/* $.extend( $.fn.pickadate.defaults, {
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
	var calendar = input.data( 'pickadate' );  */
//	calendar.show( 1, 1985 );
//	calendar.setDate(
//			1975,
//			10,
//			13 );

	var win_w = parseInt($(window).width() / 2) + "px";
	var win_h = parseInt($(window).height() / 1.5) + "px";


	/**
	 * @time  2017-01-04
	 * @auth  ZJG
	 * 说   明 : 选择授课教师
	 **/
	$(".teacher_1").click(function(){
		$.ajax({
			type: 'POST',
			url: "${ctx}/CVSet/CVManage.do",
			data : "mode=teacher&teahcerId="+$('#teacherIds').val(),
			dataType: 'json',
			success:function(b){
				var sub_cont = '<div class="tiaojian" style="min-height:40px;padding:20px;">' +
				'<p class="fl" style="width:70%;margin:0 20px 20px 0;"><textarea style="width:100%;" rows="5" class="fl teacher_name" name="" readonly="readonly"></textarea></p>' +
				'<p class="fl cas_anniu"><a href="javascript:void(0);" id="add_teacher1" class="fl btn1" style="width:70px;">添加</a></p>' +
				'<div class="clear"></div>' +
				'<p class=clear" style="margin-bottom:10px;">' +
				'<input type="text" id="teacher1" placeholder="请输入教师名称搜索">' +
				'</p>' +
				'<div class="clear"></div>' +
				'<div class="clear" id="teacherList" style="border: 1px solid #cfcfcf;min-height:100px;padding:10px;margin-top:10px;line-height:25px;">' +
				'</div>' +
				'</div>';
			 	layer.open({
					type: 1,
					title: "选择授课教师",
					skin: 'layui-layer-rim', //加上边框
					area: [win_w, win_h], //宽高
					content: sub_cont,
					closeBtn: 0,
					btn: ['取消'],
					yes: function (index, layero) {
						layer.close(index);
					},
					success: function(layerb, index){
						$('#add_teacher1').click(function(){
							var str = '';
							var ids='';
							$(".tea_name:checked").each(function(){
								str+=$(this).attr("name")+",";
								ids+= $(this).val()+",";
							});
							$(".teacher_1").val(str);
							$(".teacher_name").val(str);
							$("#teacherIds").val(ids);
							layer.close(index);
						});
						$("#teacher1").keyup(function(){
							var teacherList = '';
							$.ajax({
								type:'post',
								url:'${ctx}/CVSet/CVManage.do',
								data:'mode=teacherSearch&name='+$('#teacher1').val(),
								dataType:'JSON',
								success:function(tea){
									for(var i=0; i<tea.result.length; i++){
										teacherList += '<input type="checkbox" class="tea_name" name="' + tea.result[i].name + '" value="'+tea.result[i].id+'"/>' + tea.result[i].name + '<br>';
									}
									$('#teacherList').html(teacherList);
								}
							});
						});
					}
				});
			 },
			 error:function(){
			 	alert('修改失败!');
			 }
		});

	});

	$(".teacher_2").click(function(){
		$.ajax({
			type: 'POST',
			url: "${ctx}/CVSet/CVManage.do",
			data : "mode=teacher&teahcerId="+$('#otherTeacherIds').val(),
			dataType: 'json',
			success:function(b){
				var sub_cont = '<div class="tiaojian" style="min-height:40px;padding:20px;">' +
				'<p class="fl" style="width:70%;margin:0 20px 20px 0;"><textarea style="width:100%;" rows="5" class="fl other_teacher_name" name="" readonly="readonly"></textarea></p>' +
				'<p class="fl cas_anniu"><a href="javascript:void(0);" id="add_teacher2" class="fl btn1" style="width:70px;">添加</a></p>' +
				'<div class="clear"></div>' +
				'<p class=clear" style="margin-bottom:10px;">' +
				'<input type="text" id="teacher2" placeholder="请输入教师名称搜索">' +
				'</p>' +
				'<div class="clear"></div>' +
				'<div class="clear" id="teacherList2" style="border: 1px solid #cfcfcf;min-height:100px;padding:10px;margin-top:10px;line-height:25px;">' +
				'</div>' +
				'</div>';
			 	layer.open({
					type: 1,
					title: "选择示教教师",
					skin: 'layui-layer-rim', //加上边框
					area: [win_w, win_h], //宽高
					content: sub_cont,
					closeBtn: 0,
					btn: ['取消'],
					yes: function (index, layero) {
						layer.close(index);
					},
					success: function(layerb, index){
						$('#add_teacher2').click(function(){
							var str = '';
							var ids='';
							$(".tea_name_o:checked").each(function(){
								str+=$(this).attr("name")+",";
								ids+= $(this).val()+",";
							});
							$(".teacher_2").val(str);
							$(".other_teacher_name").val(str);
							$("#otherTeacherIds").val(ids);
							layer.close(index);
						});
						$("#teacher2").keyup(function(){
							var teacherList2 = '';
							$.ajax({
								type:'post',
								url:'${ctx}/CVSet/CVManage.do',
								data:'mode=teacherSearch&name='+$('#teacher2').val(),
								dataType:'JSON',
								success:function(tea){
									for(var i=0; i<tea.result.length; i++){
										teacherList2 += '<input type="checkbox" class="tea_name_o" name="' + tea.result[i].name + '" value="'+tea.result[i].id+'"/>' + tea.result[i].name + '<br>';
									}
									$('#teacherList2').html(teacherList2);
								}
							});
						});
					}
				});
			 },
			 error:function(){
			 	alert('修改失败!');
			 }
		});
	});

	//当选择直播课程时,处理为其授课形式不可修改
	$('#_cvType li').click(function(){
		if(_qct == 2 || _qctval == "直播"){
			var _ct = $("#cv_type").val();
			var _ctval = $("#cv_type option:selected").text();
			if(_ct != 2 || _ctval != "直播"){
				$("#cv_type").find("option").eq(_qct).attr("selected","selected");
				$('#selectval').html(_qctval);
				alert("直播类型的课程，不可修改为其他类型！");
			}
		}else{
			var _ct = $("#cv_type").val();
			var _ctval = $("#cv_type option:selected").text();
			if(_ct == 2 || _ctval == "直播"){
				$("#cv_type").find("option").eq(_qct).attr("selected","selected");
				$('#selectval').html(_qctval);
				alert("该授课形式不能修改为直播类型！");
			}else if(_ct == 0 || _ctval == "点播"){
				$('#db_label').show();
                $('#db_out_chain').show();
                $('#zhibo').hide();
                $('#ms_label').hide();
                $('.next_step').html("下一步");
			}else if(_ct == 1 || _ctval == "面授"){
				$('#db_label').hide();
                $('#db_out_chain').hide();
                $('#zhibo').hide();
                $('#ms_label').show();
                $('.next_step').html("保存");
			}
		}
    });


    //#####################################
        var win_w = "800px";
		var win_h = "500px";
		var add_cont = $('#layercontent').html();
		$('#layercontent').remove();
		$(".QueryView").click(function(){
			layer.open({
				type: 1,
				title: "直播回放课件详细信息",
				skin: 'layui-layer-rim', //加上边框
				area: [win_w, win_h], //宽高
				content: add_cont,
				closeBtn: 0,
				btn: ['返回'],
				yes: function (index, layero) {
					//缩写保存功能
					layer.close(index);
					location.reload();
				},
				btn2: function (index, layero) {
					//layer.close(index);
				},
				success: function(layerb, index){
					/* $(".btn1").click(function(){
						layer.close(index);
					}); */
				}
			});
		});
     //#####################################
    //是否添加外链

    $("input:radio[name='is_add_out_chain']").change(function (){
        if( $(this).val()==1){
            $("#p_cv_url").show();
            $('.next_step').html("保存");
        }else {
            $("#p_cv_url").hide();
            $('.next_step').html("下一步");
        }
    });
    //直播报名人数
    $("input:radio[name='is_need_apply']").change(function (){
        if( $(this).val()==1){
            $("#apply_num").removeAttr("disabled");;
        }else {
            $("#apply_num").attr("disabled","disabled");
        }
    });

});


	var kuangcode;
	var kuang;

	var initsubmenu;

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


function selectProp(){
		$(kuangcode).val($('.xs_kuangcode').text());
		$(kuang).text($('.xs_kuang').text());
		$('#propNames').val($('.xs_kuang').text());
}

function saveCourse(){
		if($("input[name='point']:checked")){
           $('#point').val(1);
        }else $('#point').val(0);
		if($("input[name='state']:checked")){
           $('#state').val(1);
        }else $('#state').val(0);
	 //document.getElementById("sfrm").action = "${ctx}/CVSet/CVManage.do?mode=updateSave&id="+'${info.id}';
     //document.getElementById("sfrm").submit();
     //执行修改
 	//上传封面图片
 	/*var form=document.getElementById("sfrm");
     var formData=new FormData(form);
     var oReq = new XMLHttpRequest();
     oReq.onreadystatechange=function(){
        if(oReq.readyState==4){
          if(oReq.status==200){
              var json=JSON.parse(oReq.responseText,function(k,v){
             	 if(!(v instanceof Object)){
             		 if(v!="Fail"){
             			    $('#file_path').val(v);
             			    $.ajax({
             					url 	: "${ctx}/CVSet/CVManage.do?mode=updateSave",
             					data 	: $("#sfrm").serialize(),
             					type	: 'post',
             					dataType: 'json',
             					success : function(data) {
             						var result = eval(data);
             						if(result.message = "success"){
             							alert("课程保存成功!");
             							//控制显示隐藏
            							$('#addwindow').hide();
            							$('#next_page').show();
            							//返回已经保存的课程id
            							classId = result.id;
             						}else{
             							alert("课程保存失败!")
             						}
             					 }
             				});
             		 }else{
             			 alert("上传服务器异常,请稍后重试...");
             		 }
             	 }
              });
          }
        }
      }
      oReq.open("POST", "${ctx}/file/fileUpload.do");
      oReq.send(formData); */

	$("#sfrm").ajaxSubmit({
					type: 'post',
					url: '${ctx}/file/fileUpload.do',
					success: function(data) {
						try{
							var v = JSON.parse(data).message;

							 if(v!="Fail"){
	             			    $('#file_path').val(v);
	             			    $.ajax({
	             					url 	: "${ctx}/CVSet/CVManage.do?mode=updateSave",
	             					data 	: $("#sfrm").serialize(),
	             					type	: 'post',
	             					dataType: 'json',
	             					success : function(data) {
	             						var result = eval(data);
	             						if(result.message = "success"){
	             							alert("课程保存成功!");
	             							//控制显示隐藏
	            							$('#addwindow').hide();
	            							$('#next_page').show();
	            							//返回已经保存的课程id
	            							classId = result.id;
	             						}else{
	             							alert("课程保存失败!")
	             						}
	             					 }
	             				});
	             		 }else{
	             			 alert("上传课程封面时出现问题,连接不上私有云,请稍后重试...");
	             		 }

						}catch(e){
							alert("上传课程封面时出现问题,连接不上私有云,请稍后重试....." + e);
						}
					},
					error: function(data){
						alert("上传课程封面时出现问题,连接不上私有云,请稍后重试......");
					}
				});

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

		var cvTypeObj = $(this).parent().find('#cv_type') ;
		if (cvTypeObj.length ==1) {
			var cvTypeValue = cvTypeObj.val() ;
		}

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
	$('.top_cont').hide();
	$('.sub_nav').hide();
	$('#addcontent').hide();
	$('#addwindow').show();
}


function refresh_left_cont(unitList) {
	//window.location.reload(true);
	$("#initTr").remove();
	document.getElementById("new").style.display="";
	document.getElementById("old").style.display="none";
    var html = [];
    var lastTr = $("#unitList").find("tr").last();
	$(unitList.list).each(function(key, value){
	        html.push("<tr class='tr' id='title_tr'>");
	        html.push("<th> ");
	        html.push("<input type='radio' id='");
	        html.push(value.id);
	        html.push("' name='cv' value='");
	        html.push(value.id);
	        html.push("'>&nbsp;");
	        html.push(value.id);
	        html.push("</th>");
	        html.push("<th>");
	        html.push("<input type='text' id='name_");
	        html.push(value.id);
	        html.push("' value='");
	        html.push(value.name);
	        html.push("' onblur='javascript:updateName(this,");
	        html.push(value.id);
	        html.push(")'>");
	        html.push("</th>");
	        html.push("<th>");
	        html.push("<select id='type_");
	        html.push(value.id);
	        html.push("' onchange='javascript:updateType( this.options[this.options.selectedIndex].value,");
	        html.push(value.id);
	        html.push(")'>");
	        if(value.type == 1){
	        	html.push("<option value='1' selected='selected'>理论讲解</option>");
	        }else{
	        	html.push("<option value='1'>理论讲解</option>");
	        }
	        if(value.type == 2){
	        	html.push("<option value='2' selected='selected'>主题讨论</option>");
	        }else{
	        	html.push("<option value='2'>主题讨论</option>");
	        }
	       if(value.type == 3){
	        	html.push("<option value='3' selected='selected'>随堂考试</option>");
	       }else{
	        	html.push("<option value='3'>随堂考试</option>");
	       }
	       if(value.type == 4){
	        	html.push("<option value='4' selected='selected'>操作演示</option>");
	       }else{
	        	html.push("<option value='4'>操作演示</option>");
	       }
	       if(value.type == 5){
	        	html.push("<option value='5' selected='selected'>扩展阅读</option>");
	       }else{
	        	html.push("<option value='5'>扩展阅读</option>");
	       }
	       if(value.type == 6){
	        	html.push("<option value='6' selected='selected'>病例分享</option>");
	        }  else{
	        	html.push("<option value='6'>病例分享</option>");
	        }
	        html.push("</select>");
	        html.push("</th>");
	        html.push("<th>");
	        var id = "point_"+value.id;
	        html.push("<input type='checkbox' id='"+id+"' name='point' onClick='javascript:testPoint(this,");
	        html.push(value.id);
	        html.push(");'");
	        if(value.point == 1){
	        	html.push(" checked");
	        }
	        html.push(">");
	        html.push("</th>");

			html.push("<th>");
			html.push(value.quota);
			html.push("</th>");

	        html.push("<th>");
	        html.push("<input type='checkbox' name='state' id='state' onClick='javascript:testState(this,");
	        html.push(value.id);
	        html.push(");'");
	        if(value.state == 1){
	        	html.push(" checked");
	        }
	        html.push(">");
	        html.push("</th>");
	        html.push("</tr>");

		});
    	lastTr.after(html.join(""));
}

function deleteUnit() {
  if(confirm("确认删除单元？")){
	var obj = $('input[name="cv"]:checked');
	if(obj == undefined || obj.val() == undefined){
		alert("请选择要删除的单元！");
        return ;
	}
    var cvId = '${info.id}' ;
    var unitId = obj.val();

	var refUrl = "${ctx}/CVSet/CVManage.do";
	var refParams = "mode=cvDelFlag&cvId=" + cvId;
	$.ajax({
			url		:refUrl,
			data	:refParams,
			type	:'post',
			dataType:'json',
			async:false,
			success:function(data1){
                if (data1.result == 'yes') {
					var refUrl2 = "${ctx}/CVSet/CVManage.do" ;
					var refParams2 = "mode=delCvUnit&cvId=" + cvId + "&unitId=" + unitId ;
					$.ajax({
							url		:refUrl2,
							data	:refParams2,
							type	:'post',
							dataType:'json',
							async:false,
							success:function(data3){
                                if (data3.result == 'success') {
									$(obj).parent().parent().remove();
								} else {
									alert('删除单元失败!');
								}
							},
			                error:function(data4){
                               //var result2 = eval(data2);
			 	               alert('删除单元失败：' + data4);
			                }
			 		});
				} else if (data1.result == 'no') {
                    alert('课程被使用不能被删除!');
                }
			},
			error:function(data2){
			   alert('判断单元是否可以删除失败：' + data2);
			}
	});
  }
}

function to_up() {
	var obj = $('input[name="cv"]:checked');
	if(obj == undefined){
		alert("请选择单元！");
        return ;
	}
	var upObj = obj.parent().parent();
    if (upObj.index() != 1) {
       upObj.prev().before(upObj) ;
    }
}

function to_down() {
	var obj = $('input[name="cv"]:checked');
	if(obj == undefined){
		alert("请选择单元！");
        return ;
	}
	var upObj = obj.parent().parent() ;
    if (upObj.index() != $('input[name="cv"]').length) {
        upObj.next().after(upObj);
    }
}

function swapUnit(src_id, target_id) {
//function swapUnit(src_id, target_id, rowNum) {
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

//返回字符串长度，包含中英文 String.prototype.allLength = function(){return this.replace(/[^\x00-\xff]/g,"01").length;}

function showNextView(){
    var spCodesTemp = "";
    $('input:checkbox[name=brand_c]:checked').each(function(i){
     if(0==i){
      spCodesTemp = $(this).val();
     }else{
      spCodesTemp += (","+$(this).val());
     }
    });
    $("#brand").val(spCodesTemp);
  	// 表单验证


	if($('#name').val() == "" ){
		alert("请输入课程名称！");
		$('#name').focus();
		return ;
	}
	if($('#serial_number').val() == ""){
		alert("请输入课程编号！");
		$('#serial_number').focus();
		return ;
	}
	if($('#propNames01').text() == ""){
		alert("请选择学科！");
		return ;
	}
	if($('#teacher').val() == ""){
		alert("请选择授课教师！");
		return ;
	}
	var _ct = $("#cv_type").val();
	var _ctval = $("#cv_type option:selected").text();
	if(_ct == 2 || _ctval == "直播"){
		if($('#class_no').val() == ""){
			alert("请输入课堂SDK ID！");
			return ;
		}
		if($('#start_date').val() == ""){
			alert("请输入开始时间！");
			return ;
		}
		if($('#invalid_date').val() == ""){
			alert("请输入结束时间！");
			return ;
		}
		if($('#student_url').val() == ""){
			alert("请输入学生URL！");
			return ;
		}
		if($('#student_token').val() == ""){
			alert("请输入学生口令！");
			return ;
		}
		$("#brand").val(6);
	}
	 if($('#brand').val() == "" ){
			alert("请选择课程标签！");
			return ;
		} 
	if($('#introduction').val() == ""){
		alert("请输入课程简介！");
		$('#introduction').focus();
		return ;	
	}	
	if($("#file_path").val() == "" && $("#matFile").val()== ""){
		alert("请选择课程封面！");
		$('#matFile').focus();
		return ;
	}

    //长度校验：
	var nameTmpVal = $('#name').val() + "" ;			
    if(nameTmpVal.allLength() > 100 ){
					alert("课程名称最多字数50字，您已经超过字数，请修改！");
					$('#name').focus();
					return ;
	}
    var introductionTmpVal = $('#introduction').val() + "" ;
	if(introductionTmpVal.allLength() > 6000 ){
					alert("课程简介最多字数3000字，您已经超过字数，请修改！");
					$('#introduction').focus();
					return ;
	}
    var announcementTmpVal = $('#announcement').val() + "" ;
	if(announcementTmpVal.allLength() > 6000 ){
					alert("课程声明最多字数3000字，您已经超过字数，请修改！");
					$('#announcement').focus();
					return ;
	}

	//通过AJAX方式修改信息
	//上传封面图片
	//首选in判断是否需要上传封面
	if($("#matFile").val()!=""){
		if (!fileUploadValid("matFile",2)) {
			return ;
		}
	     $("#sfrm").ajaxSubmit({
					type: 'post',
					url: '${ctx}/file/fileUpload.do',
					success: function(data) {
						try{
							var v = JSON.parse(data).message;
							
							if(v!="Fail"){
	            			    $('#file_path').val(v);
	            			    $.ajax({
	            					url 	: "${ctx}/CVSet/CVManage.do?mode=updateSave",
	            					data 	: $("#sfrm").serialize(),
	            					type	: 'post',
	            					dataType: 'json',
	            					success : function(data) {
	            						var result = eval(data);
	            						if(result.message = "success"){
	            							//如果为直播课程则直接保存，不需要下一步添加单元等信息
	            							var _ct = $("#cv_type").val();
											var _ctval = $("#cv_type option:selected").text();
											if(_ct == 2 || _ctval == "直播"){
												saveZhibo();
											}else{
		            							//控制显示隐藏
	                                            $('.top_cont').html('<h2>' + nameTmpVal + '</h2>');
		            							$('#addwindow').hide();	
		            							$('#next_page').show();	
		            							//返回已经保存的课程id
		            							classId = result.id;
	            							}
	            							
	            							//添加项目时重新生成学科导航页面
													$.ajax({
										                //接口地址
										                url:'${ctxQiantaiURL}/courseManage/courseList.do?logIndex=false&eh=626',
										                //请求方式
										                type:'post',
										                //返回数据类型
										                dataType:'json',
										                //请求成功时处理方式
										                success:function(result){
										                },
										            });
	            						}			
	            					 },
		                             error:function(data){
                                        //			                         
		                            }
	            				});
	            		 }else{
	            			 alert("上传课程封面时出现问题,连接不上私有云,请稍后重试...");
	            		 }
			            	 
						}catch(e){
							alert("上传课程封面时出现问题,连接不上私有云,请稍后重试....." + e);
						}
					},
					error: function(data){
						alert("上传课程封面时出现问题,连接不上私有云,请稍后重试......");
					}
				});
	     
	}else{
		//console.log($("#sfrm").serialize());
		$.ajax({
			url 	: "${ctx}/CVSet/CVManage.do?mode=updateSave",
			data 	: $("#sfrm").serialize(),
			type	: 'post',
			dataType: 'json',
			success : function(data) {
				var result = eval(data);
				if(result.message = "success"){
					//如果为直播课程则直接保存，不需要下一步添加单元等信息
       				var _ct = $("#cv_type").val();
					var _ctval = $("#cv_type option:selected").text();
					var _typeForCourse = $("input[name='course_make_type']:checked").val();
					if(_ct == 2 || _ctval == "直播"){
						if(_typeForCourse == 0 || _typeForCourse == 1){
							saveZhibo();
						}else if(_typeForCourse == 2){
							//控制显示隐藏
                            $('.top_cont').html('<h2>' + nameTmpVal + '</h2>');
 							$('#addwindow').hide();	
 							$('#next_page').show();	
 							//返回已经保存的课程id
 							classId = result.id;
						}
					}else{
						//控制显示隐藏
	                    $('.top_cont').html('<h2>' + nameTmpVal + '</h2>');
						$('#addwindow').hide();	
						$('#next_page').show();	
						//返回已经保存的课程id
						classId = result.id;
					}
					//添加项目时重新生成学科导航页面
													$.ajax({
										                //接口地址
										                url:'${ctxQiantaiURL}/courseManage/courseList.do?logIndex=false&eh=626',
										                //请求方式
										                type:'post',
										                //返回数据类型
										                dataType:'json',
										                //请求成功时处理方式
										                success:function(result){
										                },
										            });
				}		
			 },
		     error:function(data){
               //			                         
             }
		});
	}
	//$('#addwindow').hide();
	//$('#next_page').show();
}

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

//编辑
function edit(){
	//被选中的任务点
	var chekPointIds =''; 
	//组课跳转
    //检测单元是否选中了任务点
    $("input[id^='point_']").each(function(){
	  //获取被选中任务点的单元id
	  if($(this).is(':checked')){
		  var sId = $(this).attr("id");
		  chekPointIds = chekPointIds + sId.substr(sId.indexOf('_')+1,sId.length)+";";
	  }
    });
    /*if(chekPointIds!=null && chekPointIds!='' && chekPointIds.indexOf(';')>0){
	   //通过AJAX讲选中的知识点保存到Session中
	   $.ajax({
		    type: 'POST',
			url: "${ctx}/groupManage/groupClassInfoManage.do",
			data : {type:'saveCheckPoint',chekPointIds:chekPointIds},
			dataType: 'json',
			success:function(data){
				var result = eval(data);
			}
	   });
    }*/
    //location.href = "${ctx}/groupManage/groupClassInfoManage.do?type=unionEdit2&ids=${info.id}&chekPointIds="+chekPointIds+"&pageType=class";
    window.open("${ctx}/groupManage/groupClassInfoManage.do?type=unionEdit2&ids=${info.id}&chekPointIds="+chekPointIds+"&pageType=class");
}

	function save(){

		alert("保存成功");
	};
	
	//直播课程成功处理
	function saveZhibo(){
		alert("保存成功");
		window.location.href = "${ctx}/CVSet/CVManage.do?method=list";
	};
	
	//获取直播课件
	function createCourseware(){
		var roomId = $("#class_no").val();
		var cvId = $("input[name='id']").val();
		$.ajax({
				type: 'POST',
				url: "${ctx}/cvLive/coursewareList.do?mode=getCourseware&roomId="+roomId+"&id="+cvId,
				dataType: 'JSON',
				success : function(data){
	            	alert(data.msg);
	            	location.reload();	  
	            },
				error:function(data2){
					alert("生成回放信息异常！");
				}	  
	    });  
	}
	
	function radiocheck(){
		var makeTypeval = $("input[name='course_make_type']:checked").val();
		if(makeTypeval == 0 || makeTypeval == 1){
			$('.next_step').html("保存");
		}else if(makeTypeval == 2){
			$('.next_step').html("下一步");
		}
	}	
	function editWare(index){
		$("#editWare"+index).hide();
		$("#saveWare"+index).show();
		$("#tbodyX input").each(function(i){
			$("#subject"+index).removeAttr("readonly").attr("style","border:1;width:160px;");	
			$("#coursewareUrl"+index).removeAttr("readonly").attr("style","border:1;width:160px;");
			$("#coursewareNo"+index).removeAttr("readonly").attr("style","border:1;width:160px;");
			$("#coursewareToken"+index).removeAttr("readonly").attr("style","border:1;width:160px;");
		});
	}
	function saveWare(index){
		var coursewareNo = $("#coursewareNo"+index).val();
		var subject = $("#subject"+index).val();
		var coursewareUrl = $("#coursewareUrl"+index).val();
		var coursewareToken = $("#coursewareToken"+index).val();
		//var url = "${ctx}/cvLive/zhibo.do?mode=updateCourseware&coursewareNo="+coursewareNo+"&subject="+encodeURI(encodeURI(subject))+"&coursewareUrl="+coursewareUrl+"&coursewareToken="+coursewareToken;
		$.ajax({
				type: 'POST',
				url: "${ctx}/cvLive/zhibo.do",
				dataType: 'JSON',
				data:mode="mode=updateCourseware&coursewareNo="+coursewareNo+"&subject="+subject+"&coursewareUrl="+coursewareUrl+"&coursewareToken="+coursewareToken,
				success : function(data){
	            	alert(data.msg);
	            },
				error:function(data2){
					alert("修改保存课件信息异常！");
				}	  
	    });  
	}

	
	
	function updateName(o,id){
		
		$.ajax({
			type: 'POST',
			url: "${ctx}/CVSet/CVManage.do?mode=updateName",
			dataType: 'JSON',
			data:{"id":id,"name":o.value},
			success : function(data){
            }
    });  
	
	}
	
	
	
	function updateType(o,id){
		
		$.ajax({
			type: 'POST',
			url: "${ctx}/CVSet/CVManage.do?mode=updateType",
			dataType: 'JSON',
			data:{"id":id,"type":o},
			success : function(data){
            }
    });  
	
	}
	
	
	$(function(){
	
		//获取cookie

       var value = getCookie("courseEdit_20170817");
       
       if(value != "" && value != "undefined" && value ==1){
       
       		$("#next_step").trigger("click");
       
       		delCookie("courseEdit_20170817");
       }

		
		
	
	});
	
	//获取cookie
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
    }
    return "";
}



	function delCookie(name){

		setCookie(name,"",-1);

	}


function setCookie(name,value,time){

		var d = new Date();
    	d.setHours(d.getHours() + time);
    	var expires = "expires="+d.toUTCString();
   
    	document.cookie = name + "="+ escape(value) +";"+expires +";path=/";

}

</script>
</body>
</html>