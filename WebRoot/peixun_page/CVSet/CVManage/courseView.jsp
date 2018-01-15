<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/commons/taglibs.jsp"%>
		<link href="${ctx}/peixun_page/css/global.css" rel="stylesheet">
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
		
		<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
	</head>
<body >
<div class="center"  style="">
	<div class="center01" id ="addwindow">
	<form id="sfrm" name="cvForm" method="POST">
	    <input type=hidden name="id" value="${info.id}">
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
				<input type="hidden" name="propIds" id="propIds" value="${propIds}" readonly/>
				<input type="hidden" name="propNames" id="propNames" value="${propNames}" readonly/>
				<div class="duouan" id="propNames01">${propNames}</div> 
			<div class="clear"></div>
			
			<div class="clear">
				<p class="fl" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 课程类型：</span>
				</p>
				<div class="fl select" style="margin:0 20px 20px 0;">
					<div class="tik_position">
						<b class="ml5">${info.getCvTypeName()}</b>
						<p class="position01"><i class="bjt10"></i></p>
					</div>
					<ul class="fl list" style="display: none;">
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
			<input type="checkbox" name="brand_c" value="1" disabled="disabled"  <c:if test="${fn:contains(info.brand, 1)}">checked="checked"</c:if>/> 病例
			<input type="checkbox" name="brand_c" value="3" disabled="disabled"  <c:if test="${fn:contains(info.brand, 3)}">checked="checked"</c:if>/> VR
			<input type="checkbox" name="brand_c" value="4" disabled="disabled"  <c:if test="${fn:contains(info.brand, 4)}">checked="checked"</c:if>/> 名师课程
			<input type="checkbox" name="brand_c" value="5" disabled="disabled"  <c:if test="${fn:contains(info.brand, 5)}">checked="checked"</c:if>/> 三维动画
			<input type="checkbox" name="brand_c" value="6" disabled="disabled"  <c:if test="${fn:contains(info.brand, 6)}">checked="checked"</c:if>/> 其他 
			</p>
<%-- 			<div class="fl select" style="margin:0 20px 20px 0;">
				<div class="tik_position">
					<b class="ml5">请选择</b>
					<p class="position01"><i class="bjt10"></i></p>
				</div>
				<ul class="fl list" style="display: none;">
					<select name = "brand" id="brand" style="display:none" readonly>
						<option value="">请选择</option>
						<option value="1" <c:if test="${info.brand == 1}">selected="selected"</c:if>>病例</option>						
						<option value="3" <c:if test="${info.brand == 3}">selected="selected"</c:if>>VR</option>
						<option value="4" <c:if test="${info.brand == 4}">selected="selected"</c:if>>名师课程</option>
						<option value="5" <c:if test="${info.brand == 5}">selected="selected"</c:if>>三维动画</option>
						<option value="6" <c:if test="${info.brand == 6}">selected="selected"</c:if>>其他</option>
						
					</select>
					<li>请选择</li>
					<li>病例</li>					
					<li>VR</li>
					<li>名师课程</li>
					<li>三维动画</li>
					<li>其他</li>
				</ul>
			</div> --%>
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
				<input type="text" name="otherTeacher" class="teacher_2" value="${otherTeacher}" readonly/>
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
			<div id="zhibo">
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> SDK  ID：</span>
					<input type="text" name="class_no" class="class_no" value="${live.class_no}"/>
				</p>
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 开始时间：</span>
					<input type="text" name="start_date" id="start_date" class="editInput"
				datatype="*" nullmsg="请选择授权开始时间！"  value="<fmt:formatDate value="${live.start_date}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
				</p>
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 失效时间：</span>
					<input type="text" name="invalid_date" id="invalid_date" class="editInput"
				datatype="*" nullmsg="请选择授权开始时间！"  value="<fmt:formatDate value="${live.invalid_date}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
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
				<input type="hidden" id="wareval" value="${isExistWare}" />
				<div id="kejian" style="display:none;">
					<p class="clear" style="margin-bottom:20px;">
						<span style="width:9em;text-align:right;"> 直播回放信息：</span>
						<c:if test="${isExistWare == 0 }">
							<input type="button" onclick="createCourseware();" style="color:#4778c7;cursor:pointer;" value="一键生成回放信息">
						</c:if>
						<c:if test="${isExistWare == 1 }">
							<input type="button" style="color:#4778c7;cursor:pointer;" value="查看回放课件信息" class="QueryView">
						</c:if>
					</p>
				</div>
			</div>
			<div id="two">
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:10em;text-align:right;"><em class="red_start">*</em> 是否设置点播课程：</span>
					<input type="radio" name="course_make_type" value="2" style="margin-top:7px;float:left;" <c:if test="${live.course_make_type eq 2}">checked="checked"</c:if> disabled><span style="color:#333;">是</span>
					<input type="radio" name="course_make_type" value="1" style="margin-top:7px;float:left;margin-left:15px;" <c:if test="${live.course_make_type != 2}">checked="checked"</c:if> disabled><span style="color:#333;">否</span>
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
			<!-- 直播参数部分end -->
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 课程简介：</span>
				<textarea  name="introduction"style="width:400px;" rows="5" >${info.introduction}</textarea>
			</p>
			<p class="fl" style="margin-bottom:20px;width:526px">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 课程封面：</span>
				<img src="${info.file_path}" class="file_path" style="width:200px;height:200px;"/>
				<input type="file" name="matFile" id="matFile" style="margin-left:330px;margin-top:-40px;">
				<input type="hidden" name="file_path" id="file_path" value="${info.file_path}">
			</p>
			<div class="clear"></div>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"> 课程声明：</span>
				<textarea  name="announcement" style="width:400px;" rows="5">${info.announcement}</textarea>
			</p>
			<div class="clear"></div>
			<p class="fl cas_anniu" style="margin-left:9.1em;">
				<a href="javascript:void(0);" class="fl next_step" style="width:140px;margin-left:10px;" onclick="showNextView();">下一步</a>
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
	<%--
	<span class="add_course btn_no"><a href="javascript:void(0);">添加单元</a></span>
	<span class="btn_no"><a href="javascript:deleteUnit();">删除单元</a></span>
	<span class="btn_no"><a href="javascript:to_up();">上移</a></span>
	<span class="btn_no"><a href="javascript:to_down();">下移</a></span>
	 --%>
	<!--<span class="btn_blue next_btn" style="display:none;"><a href="${ctx}/CVSet/CVUnitAdd.do?mode=unionEdit&id=${info.id}">编辑</a></span> -->
	<span class="btn_blue next_btn" style="display:none;"><a href="javascript:void(0);" onclick="edit();">查看</a></span>
	
</div>
<div class="center" id="addcontent">
	<div class="center01">
		<%--
		<button class="btn_blue clone_course btn_no" type="button" >克隆课程</button>
		 --%>
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
						${p.name}
					</th>
					<th>
						<c:if test="${p.type==1}">理论讲解</c:if>
						<c:if test="${p.type==2}">主题讨论</c:if>
						<c:if test="${p.type==3}">随堂考试</c:if>
						<c:if test="${p.type==4}">操作演示</c:if>
						<c:if test="${p.type==5}">扩展阅读</c:if>
						<c:if test="${p.type==6}">病例分享</c:if>
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
                 <!-- <th>操作</th> -->
             </tr>
             </thead>
             <tbody id="tbodyX">
               <c:forEach items="${wareList}" var="ware" varStatus="SerialNumber" >
             	<tr>
                 <td><input type=text value="${ware.subject}" id="subject${SerialNumber.index}" style="border:0px;width:160px;" readonly="readonly"></td>
                 <td><input type=text value="${ware.courseware_url}" id="coursewareUrl${SerialNumber.index}" style="border:0px;width:160px;" readonly="readonly"></td>
                 <td><input type=text value="${ware.courseware_no}" id="coursewareNo${SerialNumber.index}" style="border:0px;width:160px;" readonly="readonly"></td>
                 <td><input type=text value="${ware.courseware_token}" id="coursewareToken${SerialNumber.index}" style="border:0px;width:160px;" readonly="readonly"></td>
                 <%-- <td>
                 	<a href="javascript:editWare('${SerialNumber.index}');" id="editWare${SerialNumber.index}" style="color:#4778c7;display:block;">编辑</a>
                 	<a href="javascript:saveWare('${SerialNumber.index}');" id="saveWare${SerialNumber.index}" style="color:red;display:none;">保存</a>
                 </td> --%>
             	</tr>
              </c:forEach>
             </tbody>
         </table>
	</div>
</form>
</div>
</div>
<script type="text/javascript">
$("input").attr("readonly", "true");
$("textarea").attr("readonly", "true");
$('#manager').attr('class', "person_btn_false");
$('#teacher').attr('class', "person_btn_false");
$('#teacherOther').attr('class', "person_btn_false");
$('#proOrg').attr('class', "org_btn_false");
$('#matFile').hide();
$('#tianjiakecheng').hide();
$('#shanchukecheng').hide();
$('#shangyi').hide();
$('#xiayi').hide();
$('#tijiaoshenhe').hide();
$(document).ready(function () {
    var select_add_out_chain =$("input[name='is_add_out_chain']:checked").val();
    console.log("select_add_out_chain="+select_add_out_chain);
    if(select_add_out_chain==1){
        $("#p_cv_url").show();
    }else{
        $("#p_cv_url").hide();
    }
})
//保存课程成功后的id
var classId = ""; 


$(function(){
		//获取课程类型
		var _qct = $("#cv_type").val();
		var _qctval = $("#cv_type option:selected").text();
		if(_qct == 2 || _qctval == "直播"){
			$('#db_label').hide();
			$('#zhibo').show();
			$('#two').show();
			if($('#wareval').val() == 1){
				$('#kejian').show();
				$('.next_step').show();
			}else{
				$('.next_step').hide();
			}
		}else if(_qct == 1 || _qctval == "面授"){
				$('.next_step').show();
				$('#db_label').hide();
           		$('#db_out_chain').hide();
				$('#zhibo').hide();
            	$('#ms_label').show();
				$('#two').hide();
				$('.next_step').html("下一步");
		}else{
			$('.next_step').show();
			$('#db_label').show();
            $('#db_out_chain').show();
			$('#zhibo').hide();
            $('#ms_label').hide();
			$('#two').hide();
			$('.next_step').html("下一步");
		}
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
		<%--
		$('#propNames01').click(function(){
			initPropList("学科", "${ctx}/propManage/getPropListByDirectAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
			$('.att_make01').show();
		});
		--%>
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
		if($("input[name='point']:checked")){
            $('#point').val(1);
        }else{
        	$('#point').val(0);
        } 

		$.ajax({
				url		:'${ctx}/CVSet/CVUnitAdd.do',
				data	:'mode=addUnionUpdate&point='+$('#point').val()+'&type='+$('#sel_SearchDutyName').val()+'&name='+$('#unitName').val()+'&id='+'${info.id}',
				type	:'post',
				dataType:'json',
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
							success:function(data){
								var result = eval(data);
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
	<%--
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
	--%>
	
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
					//location.reload();	
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
             			 alert("上传服务器异常,请稍后重试...");
             		 }
			            	 
						}catch(e){
							alert("上传服务器异常,请稍后重试...");
						}
					},
					error: function(data){
						alert("上传服务器异常,请稍后重试...");
					}
				});

}

function addUnion(){
	document.getElementById("sfrm").action = "${ctx}/CVSet/CVManage.do?mode=addUnion";
}

function select_init() {
	<%--
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
	--%>
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
	        if(value.type == 1){
	        	html.push("理论讲解");
	        }
	        if(value.type == 2){
	        	html.push("主题讨论");
	        }
	       if(value.type == 3){
	        	html.push("随堂考试");
	       }
	       if(value.type == 4){
	        	html.push("操作演示");
	       }
	       if(value.type == 5){
	        	html.push("扩展阅读");
	       }
	       if(value.type == 6){
	        	html.push("病例分享");
	        }        
	        html.push("</td>");
	        html.push("<td width='15%'>");
	        var id = "point_"+value.id;
	        html.push("<input type='checkbox' id='"+id+"' name='point' onClick='javascript:testPoint(this,");
	        html.push(value.id);
	        html.push(");'");
	        if(value.point == 1){
	        	html.push(" checked");
	        }
	        html.push(">");
	        html.push("</td>");
	        html.push("<td width = '15%'>");
	        html.push("<input type='checkbox' name='state' onClick='javascript:testState(this,");
	        html.push(value.id);
	        html.push(");'");
	        if(value.state == 1){
	        	html.push(" checked");
	        }
	        html.push(">");
	        html.push("</td>");
	        html.push("</tr>");
	        
		});
    	lastTr.after(html.join(""));
}

function deleteUnit() {
//	var obj = $('input[name="cv"]:checked');
//	if (obj.prop("id") == null) return;
//	var id = obj.val();
	
//	swapUnit(id, id);
	
	var obj = $('input[name="cv"]:checked');
	if(obj == undefined){
		alert("请选择要删除的单元！");
	}
	$(obj).parent().parent().remove();
	var id = obj.val();
	swapUnit(id, id);
	var ob = $('input[name="cv"]');
	if (ob.prop("id")==null) $(".table,.next_btn").hide();
}

function to_up() {

	var obj = $('input[name="cv"]:checked');
	if(obj == undefined){
		alert("请选择单元！");
	}
	var upObj = obj.parent().parent();
	var temp = upObj;
	var prevUpObj = upObj.prev();
	if(prevUpObj.length == 0){		
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
}

function showNextView(){	
	$('#addwindow').hide();	
	$('#next_page').show();	
}

function testState(obj,_id){
}

//编辑
function edit(){
	<%--	
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
    
    window.open("${ctx}/groupManage/groupClassInfoManage.do?type=unionEditView&ids=${info.id}&chekPointIds="+chekPointIds+"&pageType=class");
    --%>
    window.open("${ctxQiantaiURL}/entityManage/entityView.do?type=play4View&id="+"${info.id}"+ "&paramType=class");
}

</script>
</body>
</html>