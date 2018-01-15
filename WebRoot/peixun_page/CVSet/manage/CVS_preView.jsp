<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/taglibs.jsp"%>
<title>培训后台</title>
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
		.ulli_1{color: #4778c7}
		.ulli_2{color: #000000}
		#chechengul li{cursor:pointer;} 

		dl{margin:10px 0;}
		dl dt{margin:10px 0 10px 20px;font-weight:600;}
		dl dd{margin:10px 0 10px 40px;}
		.inp{ width: 325px;
		             height: 30px;
		             border-left:0px;
		             border-right:0px;
		             border-top:0px;
		             border-bottom: 1px solid rgb(207,207,207);
		             
		        } 
        .inpnewInp{ width: 325px;
		             height: 30px;
		             border-left:0px;
		             border-right:0px;
		             border-top:0px;
		             border: 1px solid rgb(207,207,207);
		             
		        }  
      .inpspan{
    
      }		        
		        
		        
	</style>
	<link rel="stylesheet" href="${ctx}/peixun_page/css/preview.css" />
	<%@include file="/peixun_page/top.jsp"%>
        <script type="text/javascript" src="${ctx}/peixun_page/js/utils.js"></script>
        <script type="text/javascript" src="${ctx}/js/jedate/jedate.js"></script>
        <script type="text/javascript" src="${ctx}/js/jedate/skin/jedate.css"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/fileUpload.js"></script>
		
</head>
<body>
<!-- 内容 -->
<div class="center" id="mainPage">
	<ul id="nav_bar" class="kaoshi_ul" style="padding:2px;">
		<li><i class="action">1</i><p class="fl action">项目基本信息</p><span class="action"></span></li>
		<li><i class="ml30">2</i><p class="fl">课程单元信息</p><span></span></li>
		<li id="span_ms"><i class="ml30">3</i><p class="fl">期数设置</p></li>
	</ul>
	<form id="frm_add" action="${ctx}/CVSetManage.do?mode=edit&id=${View[0].id}" method="post" enctype="multipart/form-data">
	<input type="hidden" id="projectfrom" name="projectfrom" value="2">
	<input type="hidden" name="id" value="${View[0].id}"/>
	<input type="hidden" name="cVSetStatus" value="${View[0].status}"/>
	<input type="hidden" id="cv_set_type" name="cv_set_type" value="${View[0].cv_set_type}"/>
	<div class="center01">
		<div class="tiaojian" style="min-height:40px;">
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目名称：</span>
				<input type="text" value="${View[0].name}" name="name" id="name"/>
			</p>

			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目授课形式：</span>
			</p>						
			
			<div class="fl select" style="margin:0 20px 20px 0;">
				<div class="tik_position">
					<b class="ml5"><c:if test="${View[0].forma == 1}">远程</c:if><c:if test="${View[0].forma == 2}">远程+面授</c:if><c:if test="${View[0].forma == 3}">面授</c:if></b>
					<p class="position01"><i class="bjt10"></i></p>
				</div>
				<ul class="fl list" style="display: none;">
				<select name="forma" id="lessonType" style="display:none" >
					<option value="1" <c:if test="${View[0].forma == 1}">selected="selected"</c:if>>远程</option>
					<option value="3" <c:if test="${View[0].forma == 3}">selected="selected"</c:if>>面授</option>
				</select>
					<li>远程</li>
					<li>面授</li>
				</ul>
			</div> 
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 人物画像：</span>
				<c:if test="${View[0].status==1||View[0].status==6}">
					<input type="text" id="renWuImage" class="portraits_input" value="${userImageNames}"/></c:if>
				<c:if test="${View[0].status!=1&&View[0].status!=6}">
					<input type="text" id="renWuImage"  value="${userImageNames}"/></c:if>				
				<input type="hidden" name="userImage" value="${userImageIds}"/>
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"> <em class="red_start">*</em>项目序号：</span>
				<input type="text" name="code" value="${View[0].code}" readonly />
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目负责人：</span>
				<input type="text" value="${manager}" class="person_btn" id="manager"/>
				<input name="manager" type="hidden" value="${manager_id}" id="xmmanager" />
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 授课教师：</span>
				<input  type="text" value="${teacher}" class="person_btn" id="teacher" readonly/>
				<input name="lessonTeacher" type="hidden" value="${teacher_id}"  />
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;">示教教师：</span>
				<input type="text" value="${otherTeacher}" class="person_btn" id="teacherOther" readonly/>
				<input name="generalTeacher" type="hidden" value="${otherTeacher_id}" />
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"> <em class="red_start">*</em>项目归属机构：</span>
				<input type="text" value="${orgNames}" class="org_btn" id="proOrg"/>
				<input type="hidden" value="${orgIds}" id="organization" name="organization"/>
			</p>

			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目简介：</span>
				<textarea name="introduction" style="width:400px;" rows="5" id="introduction">${View[0].introduction}</textarea>
			</p>
			<p class="fl" style="margin-bottom:20px;width:526px">
				<span style="width:9em;text-align:right;">项目封面：</span>
				<img id="file_path" class="course_cover" src="${View[0].file_path}" style="width:200px;height:200px;"/>
				<input type="file" id="matFile" name="matFile" style="margin-left:330px;margin-top:-25px; " accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp">
				<input type="hidden" id="cover" name="cover" value="${View[0].file_path}">
			</p>
			<div class="clear"></div>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;">项目声明：</span>
				<textarea name="announcement" id="announcement" style="width:400px;" rows="5">${View[0].announcement}</textarea>
			</p>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 学习须知：</span>
				<textarea name="knowledge_needed" style="width:400px;" rows="5" id="needed">${View[0].knowledge_needed}</textarea>
			</p>		
			
			<div class="clear"></div>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;display:block">书籍推荐：</span>
				<div id="bookDiv" style="min-height:35px;height:auto;margin-left:125px;">
				    <c:if test="${refereeBookList != null && fn:length(refereeBookList) > 0}">	
				       <c:forEach items="${refereeBookList}" var="rbList" varStatus="status">
				           <span style="display:block;">
						         <span style="display:inline-block;margin-left:20px;">${status.index+1}.来源名称 :${rbList.name} <input type="hidden" value="${rbList.name}" name="name" class="inpnewInp" style="margin-left:10px;"/></span>
							     <span style="display:inline-block;margin-left:20px;"> 来源出处: ${rbList.source}</span><input type="hidden" value="${rbList.source}" name="source" class="inpnewInp" style="margin-left:15px;"/>
                                 <span style="display:inline-block;margin-left:20px;"> 作者: ${rbList.author}</span><input type="hidden" value="${rbList.author}" name="author" class="inpnewInp" style="margin-left:15px;"/>
                                 <span style="display:inline-block;margin-left:20px;"> 出版年限:${rbList.old} </span><input type="hidden" value="${rbList.old}" name="old" class="inpnewInp" style="margin-left:15px;"/>
						   </span>
					   </c:forEach>
					</c:if>
					<%--<c:if test="${refereeBookList == null || fn:length(refereeBookList) == 0}">				--%>
						<%--<span style=" height: 35px; display:block;">				  --%>
						  <%--<span style="display:inline-block;">	<span class="bookNumer">1.</span> 书籍名称 :</span><input type="text"  name="book_name" class="inpnewInp" style="width: 322px;margin-left:10px;"/>--%>
						  <%--<span style="display:inline-block;margin-left:80px;"> 网址: </span><input type="text"  name="book_url" class="inpnewInp" style="width: 322px;margin-left:15px;"/>--%>
						  <%--<span class="bookImgDel" style="width: 32px;height: 32px;margin-left: 5px;"><img style="width:20px;height:20px;" src="./peixun_page/images/round_remove.png"/></span>--%>
						<%--</span>	--%>
					<%--</c:if>																--%>
				</div> 
				<%--<i style="display:block;margin-left:120px;width: 32px;height: 32px;" class="bookImgAdd"><img style="width:20px;height:20px;" src="./peixun_page/images/plus_circle.png"/></i>--%>
			</p>
			
            <div class="clear"></div>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;display:block">指南共识：</span>
				<div id="knowledgeBaseDiv" style="min-height:35px;height:auto;margin-left:125px;">	
				    <c:if test="${knowledgeBaseList != null && fn:length(knowledgeBaseList) > 0}">	
				       <c:forEach items="${knowledgeBaseList}" var="rbList" varStatus="status">
				           <span style="display:block;">
						         <span style="display:inline-block;margin-left:20px;">${status.index+1}.来源名称 :${rbList.name} <input type="hidden" value="${rbList.name}" name="name" class="inpnewInp" style="margin-left:10px;"/></span>
							     <span style="display:inline-block;margin-left:20px;"> 来源出处: ${rbList.source}</span><input type="hidden" value="${rbList.source}" name="source" class="inpnewInp" style="margin-left:15px;"/>
                                 <span style="display:inline-block;margin-left:20px;"> 作者: ${rbList.author}</span><input type="hidden" value="${rbList.author}" name="author" class="inpnewInp" style="margin-left:15px;"/>
                                 <span style="display:inline-block;margin-left:20px;"> 出版年限:${rbList.old} </span><input type="hidden" value="${rbList.old}" name="old" class="inpnewInp" style="margin-left:15px;"/>
						   </span>
					   </c:forEach>
					</c:if> 
					<%--<c:if test="${knowledgeBaseList == null || fn:length(knowledgeBaseList) == 0}">	--%>
					    <%--<span style=" height: 35px; display:block;">				  --%>
						  <%--<span style="display:inline-block;">	<span class="knowledgeBaseNumer">1.</span> 指南名称 :</span><input type="text"  name="knowledgebase_name" class="inpnewInp" style="width: 322px;margin-left:10px;"/>--%>
						  <%--<span style="display:inline-block;margin-left:80px;"> 网址: </span><input type="text"  name="knowledgebase_url" class="inpnewInp" style="width: 322px;margin-left:15px;"/>--%>
						  <%--<span class="knowledgeBaseImgDel" style="width: 32px;height: 32px;margin-left: 5px;"><img style="width:20px;height:20px;" src="./peixun_page/images/round_remove.png"/></span>--%>
						<%--</span>	--%>
					<%--</c:if>  			--%>
														
				</div> 
				<%--<i style="display:block;margin-left:120px;width: 32px;height: 32px;" class="knowledgeBaseImgAdd"><img style="width:20px;height:20px;" src="./peixun_page/images/plus_circle.png"/></i>--%>
			</p>
			
			<div class="clear"></div>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;display:block">最新文献：</span>
				<div id="referenceDiv" style="min-height:35px;height:auto;margin-left:125px;">
				    <c:if test="${referenceLatelyList != null && fn:length(referenceLatelyList) > 0}">	
				       <c:forEach items="${referenceLatelyList}" var="rbList" varStatus="status">
				           <span style="display:block;">
						         <span style="display:inline-block;margin-left:20px;">${status.index+1}.来源名称 :${rbList.name} <input type="hidden" value="${rbList.name}" name="name" class="inpnewInp" style="margin-left:10px;"/></span>
							     <span style="display:inline-block;margin-left:20px;"> 来源出处: ${rbList.source}</span><input type="hidden" value="${rbList.source}" name="source" class="inpnewInp" style="margin-left:15px;"/>
                                 <span style="display:inline-block;margin-left:20px;"> 作者: ${rbList.author}</span><input type="hidden" value="${rbList.author}" name="author" class="inpnewInp" style="margin-left:15px;"/>
                                 <span style="display:inline-block;margin-left:20px;"> 出版年限:${rbList.old} </span><input type="hidden" value="${rbList.old}" name="old" class="inpnewInp" style="margin-left:15px;"/>
						   </span>
				       </c:forEach>				        
					</c:if> 
					<%--<c:if test="${referenceLatelyList == null || fn:length(referenceLatelyList) == 0}">	--%>
					    <%--<span style=" height: 35px; display:block;">				  --%>
						  <%--<span style="display:inline-block;">	<span class="referenceNumer">1.</span> 文献名称 :</span><input type="text"  name="reference_name" class="inpnewInp" style="width: 322px;margin-left:10px;"/>--%>
						  <%--<span style="display:inline-block;margin-left:80px;"> 网址: </span><input type="text"  name="reference_url" class="inpnewInp" style="width: 322px;margin-left:15px;"/>--%>
						  <%--<span class="referenceImgDel" style="width: 32px;height: 32px;margin-left: 5px;"><img style="width:20px;height:20px;" src="./peixun_page/images/round_remove.png"/></span>--%>
						<%--</span>	--%>
					<%--</c:if>												--%>
				</div> 
			</p>
						
			<div class="clear"></div>
			<p class="fl cas_anniu" style="margin-left:9.1em;">
				<a href="javascript:void(0);" class="fl next_step" style="width:140px;margin-left:15px;margin-bottom:15px" onclick="show_div();">下一步</a>
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
<%--2：课程单元信息--%>
<div id="cvs_step2" style="display:none">
	<ul id="nav_bar2" class="kaoshi_ul" style="padding:2px">
		<li><i class="ml30">1</i><p class="fl ">项目基本信息</p><span></span></li>
		<li><i class="ml30 action">2</i><p class="fl action">课程单元信息</p><span class="action"></span></li>
		<li id="span_ms2"><i class="ml30">3</i><p class="fl">期数设置</p></li>
	</ul>
	<div class="top_cont">	
		<h2>${View[0].name}</h2>
	</div>
	<div class="sub_nav">
		<span class="btn_blue fr" id="step2_submitAudit"><a href="javascript:cvsSubmitAudit();">提交审核</a></span>
		<span class="btn_blue fr" id="step2_save"><a href="javascript:cvsSave();">保存</a></span>
		<span class="btn_blue fr " id="step2_next"><a href="javascript:cvsSchedule();" class="step2_next">下一步</a></span>
		<span class="btn_blue fr " id="PreViewProject"><a href="javascript:preView();" class="step2_next">下一步</a></span>
		<span class="btn_blue fr"><a href="javascript:history.go(-1);">上一步</a></span>
		<span class="add_course" id="tianjiakecheng"><a href="javascript:getCourse();">添加课程</a></span>
		<span id="shanchukecheng"><a href="javascript:remove_cv()">删除课程</a></span>
		<span id="shangyi"><a href="javascript:to_up();">上移</a></span>
		<span id="xiayi"><a href="javascript:to_down();">下移</a></span>
	</div>		
	
	<div class="center" style="">
		<div class="center01">
			<div class="left_cont" id="left_cont">
								
			</div>	
			<div class="right_cont" style="display:none;">
					<h2>关联能力模型</h2> 
					<button class="btn_blue fr" style="margin-right:10px; margin-top:10px;" onClick="javascript:save_unit_guide(this);" type="button" id="save_unit_guide_btn">保存</button>
					
					<div style="display:none;" class="ability_area ability_area_1">
						<h3></h3>
						<h4></h4>
						<div class="tiaojian level_p">
							<p class="fl" style="margin-right:20px;">
								<input type="checkbox" class="fl" checked /> <span>精通临床操作技能</span> <i class="fa fa-caret-right"></i>
							</p>
						</div>
					</div>
					
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
<!-- 项目预览 -->
<div id="cvs_preView" style="display:none">
</div>
<%--3：期数设置--%>
<div id="cvs_step3" style="display:none">
	<ul class="kaoshi_ul" style="padding:2px">
		<li><i class="ml30">1</i><p class="fl ">项目基本信息</p><span></span></li>
		<li><i class="ml30">2</i><p class="fl">课程单元信息</p><span></span></li>
		<li><i class="ml30 action">3</i><p class="fl action">期数设置</p></li>
	</ul>
	<div class="top_cont">
		<h2>项目名称：${View[0].name}</h2>
	</div>
<div class="sub_nav">

	<span id="banciAddId" ><a href="javascript:banciAdd();">添加期数</a></span>
	<span id="banciDelId" style="display: none"><a href="javascript:banciDel();">删除期数</a></span>
	<!-- SCP下线项目不用再次提交 -->
	<c:if test="${View[0].status!=6}">
		<span class="fr btn_blue" id="tijiaoshenhe"><a href="javascript:cvsSubmitAudit();">提交审核</a></span>
	</c:if>
	<span class="fr btn_blue" id="saveAll"><a href="javascript:banciFinish();">保存</a></span>
	<span class="btn_blue btn_project_view"><a href="javascript:previous();">项目预览</a></span>
</div>                            <%-- ${ctx}/CVSetManage.do?mode=myXiangMu&CVSetStatus=2 --%>
<div class="center" >
	<div id="baociDiv" class="center" >
		<form id="frm_banci">
			<table id="baociTable" class="mt10 table">
			<thead style="">
			<tr>
				<th>全选&nbsp;&nbsp;<input type="checkbox" id="baociAllCHK" onclick="banciChkAll();" /></th>
				<th>期数</th>
				<th>招生人数（人）</th>
				<th>培训地点</th>
				<th>培训起止时间</th>
				<th>上课时间</th>
				<th>乘车路线</th>
				<th>联系方式</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody style="">
			<c:if test="${cvSetScheduleTeachList != null && fn:length(cvSetScheduleTeachList) > 0}">
				<c:forEach items="${cvSetScheduleTeachList}" var="cv" varStatus="status">
					<tr id='{"class_name":"${cv.class_name}","people_number":"${cv.people_number}","train_starttime":"${cv.train_starttime}","train_endtime":"${cv.train_endtime}","lession_starttime":"${cv.lession_starttime}","lession_endtime":"${cv.lession_endtime}","train_place":"${cv.train_place}","contact_way":"${cv.contact_way}","route_way":"${cv.route_way}","id":"${cv.sequenceNum}"}'>
						<td><input type="checkbox" id="baociCHK" /></td>
						<td> ${cv.class_name}</td>
						<td> ${cv.people_number}<input type="hidden" name="class_name" value="${cv.class_name}" /><input type="hidden" name="people_number" value="${cv.people_number}" /></td>
						<td> ${cv.train_place}<input type="hidden" name="train_place" value="${cv.train_place}" /></td>
						<td> <fmt:formatDate value="${cv.train_starttime}" type="both" dateStyle="long" pattern="yyyy-MM-dd"/>-
							<fmt:formatDate value="${cv.train_endtime}" type="both" dateStyle="long" pattern="yyyy-MM-dd"/>
							<input type="hidden" name="train_starttime" value="${cv.train_starttime}" />
							<input type="hidden" name="train_endtime" value="${cv.train_endtime}" />
						</td>
						<td> ${cv.lession_starttime}- ${cv.lession_endtime}
							<input type="hidden" name="lession_starttime" value="${cv.lession_starttime}" />
							<input type="hidden" name="lession_endtime" value="${cv.lession_endtime}" />
						</td>
						<td> ${cv.route_way}<input type="hidden" name="route_way" value="${cv.route_way}" /></td>
						<td> ${cv.contact_way}<input type="hidden" name="contact_way" value="${cv.contact_way}" /></td>
						<td>
								<a href="javascript:modifyBanci(${cv.sequenceNum});">编辑</a>
								<a href="javascript:delBanci(${cv.sequenceNum});">删除</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			</tbody>
		</table>
		</form>
	</div>
	<%--弹出的添加窗口--%>
	<div id="cvsScheduleAdd" class="center" style="display:none">
		<table id="cvsScheduleAddTable" class="mt10 table">
			<tr>
				<td style="text-align:right;white-space: nowrap;"><em class="red_start">*</em>期数：</td><td style="text-align:left" colspan="3">
				<span id="scheduleNum"></span>
				<%--<input type="text" value="" id="baociNameAdd"/>--%>
			</td>
			</tr>
			<tr>
				<td style="text-align:right;white-space: nowrap;"><em class="red_start">*</em>招生人数：</td><td style="text-align:left" colspan="3"><input type="text" value="" id="baociManAdd" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" /></td>
			</tr>
			<tr>
				<td style="text-align:right;white-space: nowrap;"><em class="red_start">*</em>起止时间：</td>
				<td style="text-align:left">
					<input type="text" class="datainp" id="scheduleStartTimeAdd" readonly value="" onclick="WdatePicker({minDate:'%y-%M-{%d+1}'})"/>
					~<input type="text" class="datainp" id="scheduleEndTimeAdd"  readonly value="" onclick="WdatePicker({minDate:'%y-%M-{%d+1}'})"/>
				</td>
				<td style="text-align:right;white-space: nowrap;"><em class="red_start">*</em>上课时间：</td>
				<td style="text-align:left">
					<input type="text" class="datainp" id="lessonStartTimeAdd"readonly value=""onclick="WdatePicker({dateFmt:'H:mm',minDate:'8:00',maxDate:'22:30'})"/>
					~<input type="text" class="datainp" id="lessonEndTimeAdd" readonly value="" onclick="WdatePicker({dateFmt:'H:mm',minDate:'8:00',maxDate:'22:30'})"/>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;white-space: nowrap;"><em class="red_start">*</em>培训地点：</td>
				<td style="text-align:left"><textarea name="announcement" id="baociDdAdd" style="width:400px;" rows="5"></textarea></td>

				<td style="text-align:right;white-space: nowrap;"><em class="red_start">*</em>联系方式：</td>
				<td style="text-align:left"><textarea name="announcement" id="baociContactWayAdd" style="width:400px;" rows="5"></textarea></td>
			</tr>
			<tr>
				<td style="text-align:right;white-space: nowrap;"><em class="red_start">*</em>乘车路线：</td>
				<td style="text-align:left"><textarea name="announcement" id="routeWayAdd" style="width:400px;" rows="5"></textarea></td>
				<td colspan="2"></td>
			</tr>
		</table>
	</div>
</div>
</div>
<div id="union" style="display:none">
	<div class="clear"></div>
	<div class="bjs"></div>
	<div class="left_cont_v">	
		<div class="control_bar clear">
			<span class="btn_blue" style="float: right;"><a href="javascript:window.history.go(-1);">返回</a></span>				
			<span class="btn_blue btn_save" style="margin-left:10px"><a href="#">保存</a></span>		
		</div>
	</div>
	
	<div class="right_cont_v">
		<script id="editor" name="content" type="text/plain">

	</script>
	</div>
	
	<div class="clear"></div>
</div>
<script type="text/javascript">
    //new create
    $(document).ready(function(){
        var selectValue=$("#lessonType").val();
        if(selectValue==1){
            $("#span_ms").hide();
        }else{
            $("#cv_set_type").val(1);
            $("#span_ms").show();
        }
    });
</script>
<script type="text/javascript">
var cvSetStatus = '${View[0].status}' ;
var sequenceFlag = 0 ;
$('.left_cont').bind('DOMNodeInserted', function(e) { 
   var unTrHtmlTmp = $(e.target).html() ;
   if (sequenceFlag != 0 && unTrHtmlTmp.indexOf('<tbody>') != -1) {
      var allUnitIdObj = $('input[name="cv"]') ;
	  if (allUnitIdObj != undefined) {
         var allUid = "" ;
		 for (var i = 0 ; i < allUnitIdObj.length ;i++) {
            if (allUid == "") allUid = allUnitIdObj[i].value ;
            else allUid = allUid + ',' + allUnitIdObj[i].value ;
         }
         if (allUid != "") {
            //alert(allUid) ;
            var refUrl = "${ctx}/CVSetManage.do?mode=saveScheduleSequence" ;
            var refParams = "cvSetId=${View[0].id}&scheduleIds=" + allUid ;
            $.ajax({
				url		:refUrl,
				data	:refParams,
				type	:'post',
				dataType:'json',
				async:false,
				success:function(data1){					
                    if (data1.result != 'success') {
						alert('修改课程顺序失败!');
					}
				},
                error:function(data2){                            
 	               alert('修改课程顺序失败：' + data2);
                }
			 });
         }
      }      
      //alert(unTrHtmlTmp) ;
   }   
});   

$('.select').click(function(){
		$('.list').css('display","none');
		if(cvSetStatus==2 || cvSetStatus==3  || cvSetStatus==5){
			$(this).find('ul').hide();
		}else{
			$(this).find('ul').show();
		}
		
		return false;
});
$('.list li').click(function(){		
	var str=$(this).text();
	$(this).parent().parent().find('div').find('b').text(str);
	$(this).parent().find('option').prop('selected', '');
	$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
	$('.list').slideUp(50);
	$('.list').hide('fast');
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
			
$(function(){    					
    //YHQ，2017-05-15，begin        
    $("#bookDiv").on("click",".bookImgDel",function(){
       if ($(".bookImgDel").length > 1) {
          this.parentElement.remove() ;
          var bnObj = $(".bookNumer") ;
          for (var itemXi = 0 ;itemXi < bnObj.length ; itemXi++) {
             bnObj[itemXi].innerHTML = (itemXi+1) + '.' ;
          }
       }       
    });    
    $(".bookImgAdd").click(function () {
       var addNewStr = '<span style=" height: 35px; display:block;"> '				  
					 + '<span style="display:inline-block;">	<span class="bookNumer">1.</span> 书籍名称 :</span><input type="text"  name="book_name" class="inpnewInp" style="width: 322px;margin-left:10px;"/> '
					 + '<span style="display:inline-block;margin-left:80px;"> 网址: </span><input type="text"  name="book_url" class="inpnewInp" style="width: 322px;margin-left:15px;"/>'
					 + '<span class="bookImgDel" style="width: 32px;height: 32px;margin-left: 5px;"><img style="width:20px;height:20px;" src="./peixun_page/images/round_remove.png"/></span>'
					 + '</span>' ;
	   $("#bookDiv").append(addNewStr) ;	     
	   var bnObj = $(".bookNumer") ;
       for (var itemXi = 0 ;itemXi < bnObj.length ; itemXi++) {
          bnObj[itemXi].innerHTML = (itemXi+1) + '.' ;
       }     
    });
    
    $("#knowledgeBaseDiv").on("click",".knowledgeBaseImgDel",function(){
       if ($(".knowledgeBaseImgDel").length > 1) {
          this.parentElement.remove() ;
          var bnObj = $(".knowledgeBaseNumer") ;
          for (var itemXi = 0 ;itemXi < bnObj.length ; itemXi++) {
             bnObj[itemXi].innerHTML = (itemXi+1) + '.' ;
          }
       }       
    });    
    $(".knowledgeBaseImgAdd").click(function () {
       var addNewStr = '<span style=" height: 35px; display:block;"> '				  
					 + '<span style="display:inline-block;">	<span class="knowledgeBaseNumer">1.</span> 指南名称 :</span><input type="text"  name="knowledgebase_name" class="inpnewInp" style="width: 322px;margin-left:10px;"/> '
					 + '<span style="display:inline-block;margin-left:80px;"> 网址: </span><input type="text"  name="knowledgebase_url" class="inpnewInp" style="width: 322px;margin-left:15px;"/>'
					 + '<span class="knowledgeBaseImgDel" style="width: 32px;height: 32px;margin-left: 5px;"><img style="width:20px;height:20px;" src="./peixun_page/images/round_remove.png"/></span>'
					 + '</span>' ;
	   $("#knowledgeBaseDiv").append(addNewStr) ;	     
	   var bnObj = $(".knowledgeBaseNumer") ;
       for (var itemXi = 0 ;itemXi < bnObj.length ; itemXi++) {
          bnObj[itemXi].innerHTML = (itemXi+1) + '.' ;
       }     
    });
    
    $("#referenceDiv").on("click",".referenceImgDel",function(){
       if ($(".referenceImgDel").length > 1) {
          this.parentElement.remove() ;
          var bnObj = $(".referenceNumer") ;
          for (var itemXi = 0 ;itemXi < bnObj.length ; itemXi++) {
             bnObj[itemXi].innerHTML = (itemXi+1) + '.' ;
          }
       }       
    });    
    $(".referenceImgAdd").click(function () {
       var addNewStr = '<span style=" height: 35px; display:block;"> '				  
					 + '<span style="display:inline-block;">	<span class="referenceNumer">1.</span> 文献名称 :</span><input type="text"  name="reference_name" class="inpnewInp" style="width: 322px;margin-left:10px;"/> '
					 + '<span style="display:inline-block;margin-left:80px;"> 网址: </span><input type="text"  name="reference_url" class="inpnewInp" style="width: 322px;margin-left:15px;"/>'
					 + '<span class="referenceImgDel" style="width: 32px;height: 32px;margin-left: 5px;"><img style="width:20px;height:20px;" src="./peixun_page/images/round_remove.png"/></span>'
					 + '</span>' ;
	   $("#referenceDiv").append(addNewStr) ;	     
	   var bnObj = $(".referenceNumer") ;
       for (var itemXi = 0 ;itemXi < bnObj.length ; itemXi++) {
          bnObj[itemXi].innerHTML = (itemXi+1) + '.' ;
       }     
    });    
    //YHQ，2017-05-15，over
    
    
	if("${View[0].id}"==1){
		$('#saveAll').show();
	}
	if("${View[0].status}"==2 || "${View[0].status}"==3 || "${View[0].status}"==5){
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
	}
	
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
	
		$(".add_course").click(function(){
		window.localStorage.setItem("subject_clone","2");
		});
		if (window.localStorage.getItem("add_course") != null && window.localStorage.getItem("add_course") == 1){
			$(".table thead, .table tbody").show();
			$(".edit_project").show();
		}else{
		    // 1:add wh 显示期数
			// $(".table thead, .table tbody").hide();
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

	//获取人员方法，并给予默认值,
	function get_teacher_cont(content){
		var new_content="";
		var strs= new Array(); //定义一数组 
		strs=content.split(","); //字符分割
		for (i=0;i<strs.length ;i++ ) 
		{ 
			new_content+='<em onClick="javascript:delem1(this);" class="delem">'+strs[i]+'</em>'+ ",";
		} 
	
		var x = '<div class="tiaojian" style="min-height:40px;padding:20px;">' +
		'<div class="clear"></div>' +
		'<div id="new_1987" class="clear teacher_Name" style="margin:0 20px 20px 0; border: 1px solid #cfcfcf;min-height:80px;padding:10px;margin-top:10px;line-height:25px;">' +	
		new_content
		+
		'</div>' +
				
		'<div class="clear"></div>' +
		'<p class="fl" style="margin-bottom:10px;"><input type="hidden" id="managerId" />' +
		'<input type="text" placeholder="请输入教师名称搜索" id="teacherSearch">' +			
		'</p>' +
		'<p class="fl cas_anniu" style="margin-left:20px" id="add_teacher"><a href="javascript:void(0);" class="fl" style="width:70px;">添加</a></p>' +
		'<div class="clear"></div>' +
		'<div class="clear" id="teacherName" style="border: 1px solid #cfcfcf;min-height:100px;padding:10px;margin-top:10px;line-height:25px;position:absolute;height:80px;width:530px;overflow:auto;">' +
		
		'</div>' +
		'</div>';
		return x;
	}
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
	
	
	//YHQ，2017-05-12
	function StringHaveRepeat(strVal) {
	   var repeatFlag = false ;
	   var ary = strVal.split(',') ; 
	   var nary=ary.sort(); 		
	   for(var i=0;i<ary.length;i++) {
	      if (nary[i] != "" && nary[i+1] != "" && nary[i] == nary[i+1]){
	          repeatFlag = true ;
	          break ;
		  } 
	   }
	   return repeatFlag ;
	}	
	
	$(".person_btn").click(function () {
		var _this = $(this);
		var _this_id ;
		var _this_name=$(".person_btn");
		var layerTitle = "请选择" ;
		var content="";
		if(_this.prop("id") =='manager'){
			_this_id = $('input[name="manager"]');
			content=$("#manager").val();
			layerTitle = "项目负责人" ;
		} else if(_this.prop("id") == 'teacher') {
			_this_id = $('input[name="lessonTeacher"]');
			content=$("#teacher").val();
			layerTitle = "授课教师" ;
		}else if(_this.prop("id") =='teacherOther') {
			_this_id = $('input[name="generalTeacher"]');
			content=$("#teacherOther").val();
			layerTitle = "示教教师" ;
		}		
		
		var currValTmp = '' ;
		
		layer.open({
			type: 1,
			title: layerTitle,
			skin: 'layui-layer-rim', //加上边框
			area: ["600px", "400px"], //宽高
			content: get_teacher_cont(content),
			closeBtn: 0,
			btn: ['确定', '取消'],
			yes: function (index, layero) {				    
			    if (StringHaveRepeat($('#managerId').val())) {
			          alert('已选人员有重复，请重新选择！') ;
			          return ;
			    }			
				//_this.val($('.teacher_Name').text());
				_this.val($('#new_1987').text());
				_this_id.val($('#managerId').val());
				// document.getElementById('bgManager').innerHTML = _this.val() ;
				layer.close(index);				
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
				
				currValTmp = _this_id.val() ;
				if (currValTmp != "") { //加载
				    var strA = currValTmp.split(',') ; 
		            var strB = _this.val().split(',') ;
		            var s1 = "" ;
		            var s2= "" ;
		            		           
		            for (var xI in strB) {
		               if (strB[xI] != "") {
		                  var delNameR = "yhqName" + Math.floor(Math.random() * 10000 + 1) ;
			               s1 += '<em onClick="javascript:delem1Add(\'' + delNameR + '\');" class="delem" id="' + delNameR + '">' + strB[xI] + "," + '</em>'; 
						   s2 += strA[xI] + ",";
		               }		               	
		            }
		            
		            $('.teacher_Name').html(s1);	
					$('#managerId').val(s2);
				}
				
				$('#add_teacher').click(function(){
					var str = ''; str2 = '';
					$('.chk_teacher:checked').each(function(){
						str += '<em onClick="javascript:delem1(this);" class="delem">' + $(this).prop("name") + "," + '</em>'; 
						str2 += $(this).val()+",";						
					});		

					$("#new_1987").html($("#new_1987").html() + str);	
					$('#managerId').val($('#managerId').val()+str2);
					//$('#managerId').val($('#managerId').val()+str2);
					//$('.teacher_Name').html($('.teacher_Name').html() + str);	
					$('.chk_teacher:checked').each(function(){
						$(this).prop("checked", false);
					});
				});
				$('#teacherSearch').keyup(function(e){
					var keyword = $(this).val().trim();
					if (keyword == "") return false;
					switch(e.keyCode){
					case 37:
					case 38:
					case 39:
					case 40:
					case 13:
					case 9:
						return false;
					}
					var url='${ctx}/expert/ExpertManage.do';
					$.ajax({
						type:'post',
						url:url,
						data:"mode=search&name="+$('#teacherSearch').val()+"&lockState=1",
						dataType:'json',
						success:function(result){
							var strTeacher = '';					
							for(var i=0;i<result.name.length;i++){
								strTeacher += '<input type="radio" id="chk_teacher_' + result.name[i].id+'"  class="chk_teacher"  value="'+result.name[i].id+'" name="'+result.name[i].name+'" />'+result.name[i].name+'<br>';
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
	
	$(".portraits_input").click(function(){
		
		var _this = $(this);
		var _next = $("input[name='code']");	
		var _thisId = $("input[name='userImage']");
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
		
			   var sub_cont = 
			   '<div class="tiaojian" style="min-height:40px;padding:20px;">' +
				'<div class="clear"></div>' +
				'<div class="clear teacher_Name" style="width:500;margin:0 20px 20px 0; border: 1px solid #cfcfcf;min-height:77px;line-height:25px;">' +			
				'</div>' +
				'<input type="hidden" id="managerId"/>'+
				'<input type="hidden" id="xueke"/>'+
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
				
				'<div class="clear"  style="border: 1px solid #cfcfcf;height:100px;padding:10px;margin-top:10px;line-height:25px; overflow: auto;">' +
				
				'<div id="str_user_image"></div>'+
				'</p>' + 			
				'</div>';
			
		layer.open({
			
			type: 1,
			title: "人物画像",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, 466], //宽高
			content: sub_cont,
			closeBtn: 0,
			btn: ['确定', '取消'],
			yes: function (index, layero) {
				var strName = '', strId = '';				
					var k='';
					if($('#xueke').val() != ""){
						k=$('#xueke').val().split(",");
					}
					var num_code = '', code_='', year = '', month = '', day = '', randNum = '';	
					// 
						$.ajax({
							type:'post',
							url:'${ctx}/CVSetManage.do',
							data:'mode=pinyin&hanyuStr='+k[0],
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
								
								_this.val($('.teacher_Name').text().slice(0, -1));
								_thisId.val($('#managerId').val());						
								_next.val(num_code);	
								layer.close(index);
								
							
							}
						});
			},
			success: function(layerb, index){				
				$('.select').click(function(){
					$('.list').css('display","none');
					$(this).find('ul').show();
				});
				$('.list li').click(function(){
					$('#propNames01').text('');
					$('#propIds').val('');
					var str=$(this).text();
					$(this).parent().parent().find('div').find('b').text(str);
					$(this).parent().find('option').prop('selected', '');
					$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
					$('.list').slideUp(50);
					var str_user_image = '';var str_user_image_id = '<select id="userImage_id" style="display:none;">';
					var param = 'mode=guide_userImage&id='+$('#sel_userLeixing').val();
					// 选择人物类型时加载人物画像中的内容
					$.ajax({		    		    
					    type: 'POST',
					    url:"${ctx}/quality/userImageManage.do",
					   	data:param,
					    dataType: 'JSON',
					    success: function(B){	
					    	var str_user_image_temp = '';				    	
					    	for(var i=0; i<B.result.length;i++){
					    		//str_user_image += '<input type="radio" id="chk" value="'+B.result[i].id+'"'+' name="'+B.result[i].name+'"/>'+B.result[i].name+'<br>';
					    		str_user_image += '<input type="checkbox" id="chk_ui" onClick="javascript:chk_click(this);" value="'+B.result[i].id+'"'+' name="'+B.result[i].name+'" /><span>'+B.result[i].name+'</span><br>';					    		
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
					initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
					$('.att_make01').show();
				});
				$('.cas_anniu_4,.bjt_kt').click(function(){
					
				// 执行确定按钮将内容添加到人物画像框中
				
				// 获取选定学科里面的checkbox里的内容
				var arr=new Array()
				$(".xs_ul").find("li").each(function(){
					var $thi = $(this).find("div").find("input[type='checkbox']")
					if($thi.prop("checked") == true){
						arr.push(1)
					}
				})
				if(arr.length>1){
					alert("只能选择一个");
					return false;
				}
					var str_user_image = '';
					selectProp();
					$.ajax({
						type:'post',
						url: '${ctx}/quality/userImageManage.do',
						data:'mode=guide_userImage&id='+$('#sel_userLeixing').val()+'&xuekes='+$('#propIds').val()+'&propIds='+$('#propIds').val(),
						dataType:'JSON',
						success:function(xuekel){
							var str_user_image_temp = '';				    	
					    	for(var i=0; i<xuekel.result.length;i++){
					    		//str_user_image += '<input type="radio" id="chk" value="'+B.result[i].id+'"'+' name="'+B.result[i].name+'"/>'+B.result[i].name+'<br>';
					    		str_user_image += '<input type="checkbox" id="chk_ui" onClick="javascript:chk_click(this);" value="'+xuekel.result[i].id+'"'+' name="'+xuekel.result[i].name+'" /><span>'+xuekel.result[i].name+'</span><br>';					    		
					    	}	
					    	$('#str_user_image').html(str_user_image);
					    	
						}
					});
					
					$('.att_make01').hide();
				});
				$(".btn1").click(function(){		
					
					var str = ''; str2 = '';
					$('#chk_ui:checked').each(function(){
						str += '<em onClick="javascript:delem1(this);" class="delem">' + $(this).prop("name") + '</em>'+ ","; 
						str2 += $(this).val()+",";						
					});		
					$('.teacher_Name').html($('.teacher_Name').html() + str);	
					$('#managerId').val($('#managerId').val()+str2);
					$('#chk_ui:checked').each(function(){
						$(this).prop("checked", false);
					});
				});
				
			
		
			
				
				
			}
		});
			}
		});
		
	});
	
/*
 *@auth ZJG
 *@time  2016-12-27
 *方法说明   点击下一步 并通过AJAX方式修改表单信息
 */
function show_div(){
	//进行表单验证
	if($('#name').val() == "" ){
		alert("请输入项目名称！");
		$('#name').focus();
		return ;
	}
	if($('.portraits_input').val() == ""){
		alert("请输入人物画像！");				
		return ;
	}
	if($('#manager').val() == ""){
		alert("请选择项目负责人！");
		return ;
	}
	if($('#teacher').val() == ""){
		alert("请选择授课教师！");
		return ;
	}
	<%--
	if($('#proOrg').val() == ""){
		alert("请选择项目归属机构！");
		return ;
	}
	--%>
	if($('#file_path').attr("src") == "" && $("#matFile").val() == ""){
		alert("请选择项目封面！");
		return ;
	}

	if($('#introduction').val() == ""){
		alert("请输入项目简介！");
		$('#introduction').focus();
		return ;	
	}	
	if($('#needed').val() == ""){
		alert("请输入学习须知！");
		$('#needed').focus();
		return ;
	}
	
	//YHQ，2017-05-15
	var bookNameListObj = $("input[name='book_name']") ;			
	var bookUrlListObj  = $("input[name='book_url']") ;
	var kbNameListObj   = $("input[name='knowledgebase_name']") ;			
	var kbUrlListObj    = $("input[name='knowledgebase_url']") ;
	var rcNameListObj   = $("input[name='reference_name']") ;			
	var rcUrlListObj    = $("input[name='reference_url']") ;

	//长度校验：
	if($('#name').val().allLength() > 240 ){
		alert("项目名称最多字数120字，您已经超过字数，请修改！");
		$('#name').focus();
		return ;
	}
	if($('#introduction').val().allLength() > 4000){
		alert("项目简介最多字数2000字，您已经超过字数，请修改！");
		$('#introduction').focus();
		return ;	
	}
	if($('#announcement').val().allLength() > 4000){
		alert("项目声明最多字数2000字，您已经超过字数，请修改！");
		$('#announcement').focus();
		return ;	
	}
	if($('#needed').val().allLength() > 4000){
		alert("学习须知最多字数2000字，您已经超过字数，请修改！");
		$('#needed').focus();
		return ;	
	}
	
	for (var i = 0 ; i < bookNameListObj.length ; i++) {
	   if ($.trim(bookNameListObj[i].value) != '' && $.trim(bookUrlListObj[i].value) != '') {
	      if(bookNameListObj[i].value.allLength() > 250){
			  alert('第 ' + (i+1) + ' 行，推荐书籍名称最多字数250字，您已经超过字数，请修改！');			  
			  return ;	
		  }
		  if(bookUrlListObj[i].value.allLength() > 250){
			  alert('第 ' + (i+1) + ' 行，推荐书籍的网址最多字数250字，您已经超过字数，请修改！');			  
			  return ;	
		  }	      
	   } 
	}
	
	for (var i = 0 ; i < kbNameListObj.length ; i++) {
	   if ($.trim(kbNameListObj[i].value) != '' && $.trim(kbUrlListObj[i].value) != '') {
	      if(kbNameListObj[i].value.allLength() > 250){
			  alert('第 ' + (i+1) + ' 行，指南名称最多字数250字，您已经超过字数，请修改！');			  
			  return ;	
		  }
		  if(kbUrlListObj[i].value.allLength() > 250){
			  alert('第 ' + (i+1) + ' 行，指南共识的网址最多字数250字，您已经超过字数，请修改！');			  
			  return ;	
		  }	      
	   } 
	}
	
	for (var i = 0 ; i < rcNameListObj.length ; i++) {
	   if ($.trim(rcNameListObj[i].value) != '' && $.trim(rcUrlListObj[i].value) != '') {
	      if(rcNameListObj[i].value.allLength() > 250){
			  alert('第 ' + (i+1) + ' 行，文献名称最多字数250字，您已经超过字数，请修改！');			  
			  return ;	
		  }
		  if(rcUrlListObj[i].value.allLength() > 250){
			  alert('第 ' + (i+1) + ' 行，最新文献的网址最多字数250字，您已经超过字数，请修改！');			  
			  return ;	
		  }	      
	   } 
	}
	var status=${View[0].status};
	console.log("status="+status);
    //如果项目状态，不是审核通过，则显示审核按钮
    if(status!=1){
        $("#step2_submitAudit").hide();
    }
    $('#cvs_step2').show();
    $('#step2_next').show();
    $('#mainPage').hide();

    //1：远程：3：面授
    if($('#lessonType').val()==1){
        $("#span_ms2").hide();
        $("#step2_next").hide();
    }else{
        $("#span_ms2").show();
    }
	/*$('#report').val($('#report1').val());
	var ids = '';
	$('input[name="cv"]').each(function(){
		ids += $(this).val() + ",";
	});
	$('input[name="cvIds"]').val(ids);
	$(frm_add).submit();*/
	//判断是否需要上传图片
	if($("#matFile").val()!=""){
		if (!fileUploadValid("matFile",2)) {
			return ;
		}
	     $("#frm_add").ajaxSubmit({
					type: 'post',
					url: '${ctx}/file/fileUpload.do',
					success: function(data) {
						try{
							var v = JSON.parse(data).message;
							
							 if(v!="Fail"){
	            			    $('#cover').val(v);
	            				$.ajax({
	            					url		:"${ctx}/CVSetManage.do?mode=edit&id=${View[0].id}",
	            					data	:$("#frm_add").serialize(),
	            					type	:'post',
	            					dataType:'json',
	            					success:function(data){
	            						var result = eval(data);
	            						if(result.message='success'){
                                            showNext();
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
	            					}
	            				});	
	            		 }else{
	            			 alert("上传项目封面时出现问题,连接不上私有云,请稍后重试...");
	            		 }
			            	 
						}catch(e){
							alert("上传项目封面时出现问题,连接不上私有云,请稍后重试....." + e);
						}
					},
					error: function(data){
						alert("上传项目封面时出现问题,连接不上私有云,请稍后重试......");
					}
				});
	}else{
		//通过AJAX方式修改项目信息
		$.ajax({
			url		:"${ctx}/CVSetManage.do?mode=edit&id=${View[0].id}",
			data	:$("#frm_add").serialize(),
			type	:'post',
			dataType:'json',
			success:function(data){
				var result = eval(data);
				if(result.message='success'){
					// getXueKe();
					showNext();
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
			}
		});	
	}
}

function showNext(){
    var banciFlag = false ;
    var cvSetForma = $("#lessonType").val() ;
    if (cvSetForma == '2' || cvSetForma == '3') {//面授
       banciFlag = true ;
    }
    
	$('#cvs_step2').show();			
	$('.next_btn').show();
	$('.edit_project').show();
	$('#mainPage').hide();	
	
	if (banciFlag) $('#banci_btn').show();
	else $('#banci_btn').hide();
	
	var str = '<h2 id="the_top">目录</h2>';
	$.ajax({
		type:'post',
		url:'${ctx}/CVSetManage.do',
		data:'mode=getByAjax&id='+"${View[0].id}",
		dataType:'json',
		success:function(result){
			var obj = (null==result.list[0])?[]:result.list[0].CVScheduleList;
			if("${View[0].status}"==5){
				for(var j=0; j<obj.length; j++){
						str += '<table id="table_'+ obj[j].id+'" class="mt10 table">'+
						'<tr align="center" valign="middle" id = "' + obj[j].id+'">' +
							'<th colspan="6">' +
								'<input disabled type="radio" id="' + obj[j].id+'" name="cv" value="'+obj[j].id+'" /> 课程名称:' + obj[j].name +
								'<button class="clone_btn tbn_blue fr" onClick="javascript:clone(this);" value="' + obj[j].id+'" type="button">课程信息</button>' + 
					    		'</th></tr>'+
						'<tr class="tr"><th width="10%">序号</th><th width="40%">课题</th><th width="15%">类别</th><th width="10%">任务点</th><th width="13%">达标指标</th><th width="13%">完成状态</th></tr>';
					for(var i=0; i<obj[j].unitList.length;i++){
						str += '<tr align="center" id="trId_' + obj[j].id + i + '" class="xTrChangeColor0228"><td>' + obj[j].unitList[i].id +//序号
							'</td><td id="tdId_' +obj[j].id+ i + '" onclick="javascript:show_quality_area(this);">' + obj[j].unitList[i].name+'</td><td>';//课题/类别
								if(obj[j].unitList[i].type == 1) str+='理论讲解'; 
								if(obj[j].unitList[i].type == 2) str+='主题讨论'; 
								if(obj[j].unitList[i].type == 3) str+='随堂考试';
								if(obj[j].unitList[i].type == 4) str+='操作演示'; 
								if(obj[j].unitList[i].type == 5) str+='扩展阅读'; 
								if(obj[j].unitList[i].type == 6) str+='病例分享'; 
								
					
							 str += '</td><td><input disabled type="checkbox" name="point" onClick="javascript:testPoint(this,'+obj[j].unitList[i].id+');"';//任务点
								if(obj[j].unitList[i].point == 1) str+=' checked'+ '/></td>';
								else str+=' /></td>';
							str += '<td>'+obj[j].unitList[i].quota+'</td>';
							str +='</td><td><input disabled type="checkbox" name="state" onClick="javascript:testState(this,' + obj[j].unitList[i].id+');"';//状态
							if(obj[j].unitList[i].state == 1) str+=' checked' +'/></td>'; 
							else str+=' /></td>';
							str+='</tr>';
					}
					str += '</table>';
				}
				$('.left_cont').html(str);				
			}else{
				for(var j=0; j<obj.length; j++){
					str += '<table id="table_'+ obj[j].id+'" class="mt10 table">'+
					'<tr align="center" valign="middle" id = "' + obj[j].id+'">' +
						'<th colspan="6">' +
							'<input type="radio" id="' + obj[j].id+'" name="cv" value="'+obj[j].id+'" /> 课程名称:' + obj[j].name + 
							'<button class="clone_btn tbn_blue fr" onClick="javascript:clone(this);" value="' + obj[j].id+'" type="button">课程信息</button>' + 
				    		'</th></tr>'+
					'<tr class="tr"><th width="10%">序号</th><th width="40%">课题</th><th width="15%">类别</th><th width="10%">任务点</th><th width="13%">达标指标</th><th width="13%">完成状态</th></tr>';
				  for(var i=0; i<obj[j].unitList.length;i++){
					str += '<tr align="center" id="trId_' + obj[j].id + i + '" class="xTrChangeColor0228"><td>' + obj[j].unitList[i].id +
						'</td><td id="tdId_' +obj[j].id+ i + '" onclick="javascript:show_quality_area(this);">' + obj[j].unitList[i].name+'</td><td>';
							if(obj[j].unitList[i].type == 1) str+='理论讲解'; 
							if(obj[j].unitList[i].type == 2) str+='主题讨论'; 
							if(obj[j].unitList[i].type == 3) str+='随堂考试';
							if(obj[j].unitList[i].type == 4) str+='操作演示'; 
							if(obj[j].unitList[i].type == 5) str+='扩展阅读'; 
							if(obj[j].unitList[i].type == 6) str+='病例分享'; 
					/* str +='</td><td width="10%" style="background-color: green;"><input type="checkbox" name="state" onClick="javascript:testState(this, "' + obj[j].unitList[i].id+'");"';
							if(obj[j].unitList[i].state == 1) str+=' checked' +'/></td>'; 
							str += '<td >'+obj[j].unitList[i].quota+'</td>';
						 str += '<td width="10%"><input type="checkbox" name="point" onClick="javascript:testPoint(this, "'+obj[j].unitList[i].id+'");"';
							if(obj[j].unitList[i].point == 1) str+=' checked'+ '/></td></tr>'; */
							 str += '</td><td><input type="checkbox" name="point" onClick="javascript:testPoint(this,'+obj[j].unitList[i].id+');"';//任务点
								if(obj[j].unitList[i].point == 1) str+=' checked'+ '/></td>';
								else str+=' /></td>';
							str += '<td>'+obj[j].unitList[i].quota+'</td>';
							str +='</td><td><input type="checkbox" name="state" onClick="javascript:testState(this,' + obj[j].unitList[i].id+');"';//状态
							if(obj[j].unitList[i].state == 1) str+=' checked' +'/></td>'; 
							else str+=' /></td>';
							str+='</tr>';
				  }
				  str += '</table>';
				}
				$('.left_cont').html(str);
			}
		}
	});
}
	
function getCourse(){
	var proId = '${proId}';
	
	var proType = $("#lessonType").val() ;
	var cvType = "" ;
	if (proType == '1') cvType = '0,2' ;
	else if (proType == '2') cvType = '0,1,2' ;
	else cvType = '1' ;
	
	window.open("${ctx}/getCourseList.do?method=getCourseList&proId="+proId + "&cvSetType=" + $("#cv_set_type").val() + "&cvType=" + cvType);	

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
		return;
	}
	if (cvSetStatus == '1' || cvSetStatus == '6') {
	} else {
	   alert('项目使用中，不能改变课程顺序！') ;
	   return ;
	}
	sequenceFlag = 1 ;
	tbl_obj.prev().before(tbl_obj) ;	
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
		return;
	}
	if (cvSetStatus == '1' || cvSetStatus == '6') {
	} else {
	   alert('项目使用中，不能改变课程顺序！') ;
	   return ;
	}
	sequenceFlag = 2 ;
	tbl_obj.next().after(tbl_obj);	
}

function remove_cv() {
	var obj = $('input[name="cv"]:checked');	
	var id_ = "#table_" + obj.prop("id");
	if(id_ == '#table_undefined'){
		alert("请选择要删除的课程！");
		return;
	}		
	if (cvSetStatus == '1' || cvSetStatus == '6'|| cvSetStatus == '4') {
	} else {
	   alert('项目使用中，不能删除课程！') ;
	   return ;
	}
	
	if(confirm("确认删除课程？")){ 
	   $.ajax({
			type:'post',
			url:'${ctx}/CVSetManage.do',
			data:'mode=deleteCVfromCvset&cvId=' + obj.prop("id"),
			dataType:'JSON',
			success:function(data1){
				if (data1.result == 'success') {
					$(id_).remove();
			    } else {
			        alert('删除课程失败!');
			    }
			},
			error:function(data2){
			   alert('删除课程失败：' + data2);
			}
	   });
	}	
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

function clone(obj){	
// 	window.open('${ctx}/CVSet/CVManage.do?mode=edit&id=' + $(obj).val());
	window.location.href = '${ctx}/CVSet/CVManage.do?mode=edit&id=' + $(obj).val();
}
function bagaoOpen(){
	$('#cvs_step3').show();
	$('#cvs_step2').hide();
	$('.no_pass').hide();
}
function previous(){
	<%--
	$('#cvs_step3').hide();
	$('#cvs_step2').show();
	$('#union').hide();
	$('.left_cont').show();
	$('.no_pass').hide();
	window.open("http://www.ncme.org.cn/qiantai/projectView.do?id="+"${View[0].id}");
	--%>
	
	//window.open("${ctxQiantaiURL}/entityManage/entityView.do?type=play4View&paramType=project&id=${View[0].id}");
	  window.open("${ctxQiantaiURL}/projectView.do?id=${View[0].id}");
}
function openBagao(){
	$('.report_cont').show();
	$('.no_pass, #union').hide();
}
function viewDetail(){
	UE.getEditor('editor').setContent("");//清空编辑器
	$('#union').show();
	$('.left_cont, .right_cont').hide();
	$('.v_cont, .report_cont').hide();
	$('.no_pass').hide();
	var left_cont_v = '<div class="control_bar clear">' +
							'<span class="btn_blue" id="huiban" style="margin-left:20px"><a href="javascript:huiban();">返回</a></span>	' +			
							'<span class="btn_blue btn_save" style="margin-left:170px"><a href="javascript:saveUnion();">保存</a></span>	' +	
						'</div>';
	
	$.ajax({
		type:'post',
		url:'${ctx}/CVSet/CVUnitAdd.do',
		data:'mode=getUnitForCV&id='+ $('#cvIds').val(),
		dataType:'JSON',
		success:function(list){
			for(var i=0; i<list.result.length; i++){				
				left_cont_v += '<h2>课程'+(i+1)+'：'+list.result[i].name+'</h2><ul id="chechengul">';   
				for(var j=0; j<list.result[i].unitList.length; j++){
					left_cont_v += '<li id="'+list.result[i].unitList[j].id+'" onClick="javascript:itemClick('+list.result[i].unitList[j].id+')">'+(i+1)+'.'+(j+1)+':'+list.result[i].unitList[j].name+'</li>';
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

//YHQ，2017-05-12
function delem1Add(emIdVal) {
   var thisVal = $("#" + emIdVal).html() ;
   var tnObj = $('.teacher_Name') ;
   if (tnObj.length > 0) {
      var tNameObj = tnObj[0].children ;
      var strA = $('#managerId').val().split(',') ;
      for (var itemXi = 0 ; itemXi < tNameObj.length ; itemXi++) {
         if (tNameObj[itemXi].innerHTML ==  thisVal) {
            var thisIdVal = $('#managerId').val() ;
             thisIdVal = thisIdVal.replace(strA[itemXi] + "," , "") ;
             $('#managerId').val(thisIdVal); 
             $("#" + emIdVal).remove() ;
             break ;
         }
      }	      
   }	   	  
}

//YHQ，2017-05-12
function delem1(obj){
        var i ;
        var selstr ;
        var selarray ;
        var newnarray ;
        var deletecode = "";
        
        var delFromWhoObj ;	        
	    delFromWhoObj = $('.teacher_Name').html() ;
	    if (delFromWhoObj != undefined && $('#managerId').val() != undefined) {
	       i = $(obj).index();		
			selstr = $(obj).parent().html();
			selarray = selstr.split(",");
			newnarray = new Array();
			$(selarray).each(function(key, val){
				if (key != i) newnarray.push(val);
			});
			$('.teacher_Name').html(newnarray.toString());
			
			selstr = $('#managerId').val();
			selarray = selstr.split(",");
			newarray = new Array();
			$(selarray).each(function(key, val){
				if (key != i) newarray.push(val);
				else
					deletecode = val;
			});
			$('#managerId').val(newarray.toString());
	    }
		
		delFromWhoObj = $('.xs_kuangcode').text() ;
	    if (delFromWhoObj != undefined && delFromWhoObj != '') {
	       deletecode = "";
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
	    }
		

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
var username;
$('#qualify_btn').click(function(){
	$.ajax({
		type:'post',
		url:'${ctx}/CVSetManage.do',
		data:'mode=getByAjax&id='+"${View[0].id}",
		dataType:'json',
		async:false,
		success:function(result){
			var strReasons = '',str = '';
			var strReasonsRaw = new Array('内容不完整', '内容与所选胜任力指标体系不符', '内容有错误','课程形式设置有误', '格式不符合要求', '课程起止时间有误', '授课教师与实际不符', '商业倾向过于明显', '其他原因 ');
			username=(result.expertInfo==null)?"":result.expertInfo.name;
			$('.notice_input').val(result.list[0].opinion);
			strReasons = result.list[0].opinionType.split("^");	
			for(var i=0; i<strReasons.length-1; i++){
				str += (i+1)+"."+strReasonsRaw[strReasons[i]-1]+"\n";				
			}
			$('textarea[name="reason"]').val(str);
			
		}
	});
	if(username=="${manager}"){
		if("${View[0].status}"==2){
			$("#shenHeRen").hide();
			$("#shenHeJieGuo").hide();
			$('.no_pass').show();
			$('.left_cont, .right_cont').hide();
		}
	}else{
		$('.no_pass').show();
		$('.left_cont, .right_cont').hide();
	}
});
function itemClick(_id){
	id=_id;
	
	$('#chechengul li').removeClass("ulli_1");
	$('#chechengul li').addClass("ulli_2");
	$("#"+_id).removeClass("ulli_2");
	$("#"+_id).addClass("ulli_1");
	
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
	var flag = true;
	$("#chechengul li").each(function(index){
		if($(this).attr('class')=='ulli_1'){
			flag = false;
		}
	});
	if(flag){
		alert("请选择单元");
		return;
	}
	var params = 'mode=updateUnion&id='+id+'&content='+str_content;
	$.ajax({
		type :'POST',
		url : url,
		data :params,
		dataType : 'text',
		success:function(result){
			if(result == 'success')
				alert("成功");
			else if(result == 'contentIsNull')
				alert("内容为空");
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
	$('#cvs_step3').hide();
	$('#union').hide();
	
	$('.left_cont').show();
	
	$('#cvs_step2').show();
	$('.report_cont').show();
}


function display_union(obj){
	$('#edit_project').show();
	var str ='';
	
	str += '<table id="table_'+ obj.id+'" class="mt10 table">'+
	'<tr align="center" valign="middle" id = "' + obj.id+'">' +
		'<th colspan="6">' +
			'<input type="radio" id="' + obj.id+'" name="cv" value="'+obj.id+'" /> 课程名称:' + obj.name + 
         		'<button class="clone_btn tbn_blue fr" onClick="javascript:clone(this);" value="' + obj.id+'" type="button">课程信息</button>' +   			
    		'</th></tr>'+
	'<tr class="tr"><th width="10%">序号</th><th width="40%">课题</th><th width="15%">类别</th><th width="10%">任务点</th><th width="13%">达标指标</th><th width="13%">完成状态</th></tr>';
for(var i=0; i<obj.unitList.length;i++){
	str += '<tr align="center" id="trId_' + obj.id + i + '" class="xTrChangeColor0228"><td>' + obj.unitList[i].id +//序号
		'</td><td id="tdId_' +obj.id+ i + '" onclick="javascript:show_quality_area(this);">' + obj.unitList[i].name+'</td><td>';
			if(obj.unitList[i].type == 1) str+='理论讲解'; 
			if(obj.unitList[i].type == 2) str+='主题讨论'; 
			if(obj.unitList[i].type == 3) str+='随堂考试';
			if(obj.unitList[i].type == 4) str+='操作演示'; 
			if(obj.unitList[i].type == 5) str+='扩展阅读'; 
			if(obj.unitList[i].type == 6) str+='病例分享'; 
	str +='</td><td><input type="checkbox" name="state" onClick="javascript:testPoint(this,' + obj.unitList[i].id+');"';
			if(obj.unitList[i].point == 1) str+=' checked' +'/>'; 
			else str+=' />';
			str += '<td>'+obj.unitList[i].quota+'</td>';
		 str += '</td><td><input type="checkbox" name="point" onClick="javascript:testState(this,'+obj.unitList[i].id+');"';
			if(obj.unitList[i].state == 1) str+=' checked'+ '/>';
			else str+=' />';
		str += '</td></tr>';
}
str += '</table>';	

$('.left_cont').html($('.left_cont').html()+str);
   
}

function testPoint(obj,_id){
		var pointMode;
		if($(obj).prop("checked")) pointMode = 1;
		else pointMode = 0; 
			
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

function testState(obj,_id){
	var stateMode;
	if($(obj).prop("checked")) sateMode =1;
	else sateMode =0; 
	
	var url='${ctx}/CVSet/CVManage.do';
	var params = 'mode=updatePoint&stateMode='+sateMode+'&id='+_id;
	$.ajax({
		url		:url,
		data	:params,
		type	:'post',
		dataType:'text',
		success:function(result){
		
		}
	});
}


var stock = new Array();

var unit_id ;
var image_ids ;
var levelDz      = new Array();
var levelDzCheck = new Array();
var uncheckHT = new HashTable();

function show_quality_area(obj) {
    var tdId = obj.id ; 
    var tdNumStr = tdId.substring(tdId.indexOf("_"),tdId.length) ;       
    var trNumStr = "" ;
    $('.xTrChangeColor0228').each(function(index,domEle){
       trNumStr = domEle.id ;
       trNumStr = trNumStr.substring(trNumStr.indexOf("_"),trNumStr.length) ;
       if (trNumStr == tdNumStr) {
          domEle.bgColor = "#0B92E8" ;
       } else {
          domEle.bgColor = "" ;
       }
    });
    
	stock = new Array();
	
	levelDz      = new Array();
    levelDzCheck = new Array();
    uncheckHT = new HashTable();

	unit_id = $(obj).prev().text();
	image_ids = $('input[name="userImage"]').val();
	
	$('#save_unit_guide_btn').val(unit_id);
	
	var url = '${ctx}/CVSetManage.do';
	var params = 'mode=getDeepQuality&unit_id=' + unit_id + '&image_ids=' + image_ids + '&level=0';
	
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
				
				/*for (it in _stock) {
					stock.push(_stock[it].id + '_' + _stock[it].level);
				}*/
			}
			right_area_update(1, list, "","");
		},
		error:function(){
			right_area_update(1, list, "","");
		}
	});
	
}

function update_stock(obj) {	
	if ($(obj).prop("checked") == true) {
		stock.push($(obj).val());
		uncheckHT.remove($(obj).val()) ;
		while (levelDzCheck.length > 0) {
		   //$("#" + levelDzCheck[levelDzCheck.length-1]).attr("checked",'true');
		   var cId = levelDzCheck[levelDzCheck.length-1] ;
		   stock.push(cId) ;
		   document.getElementById(cId).checked = true;
		   levelDzCheck.pop() ;
		}		
		for (var i = 0 ; i < levelDz.length ; i++) {
		   levelDzCheck.push(levelDz[i]) ;
		}
	} else {
		for (it in stock) {		   
		   if (stock[it] == $(obj).val()) {
		      stock.splice(it, 1);
		      uncheckHT.add($(obj).val(), $(obj).val()) ;
		   }
		}
			
	}
}

function right_area_update(index, list, title, backId) {    
	var new_html = '';
	
	if (list != '') {
		for (var i=0; i<list.length; i++) {
			
			var v = list[i].id + '_' + index ; //list[i].level;
			
			var checked = '';
			for (it in stock)
				if (stock[it].split("_")[0] == list[i].id) {checked=" checked "; break;}
			
			if (list[i].level == 1) {
			   stock.push(v) ;
			   checked=" checked ";
			}
			
			new_html += '<p class="fl" style="margin-right:20px;">';
			new_html += 	'<input id="' + v + '" name="chk_guide" onchange="javascript:update_stock(this);" type="checkbox" ' + checked + 'class="fl" value="' + v + '"/> <span onclick="javascript:next_index(this);">' + list[i].name + '</span>'; 
			
			if (index < 4)
				new_html += '<i onclick="javascript:next_index($(this).prev());" class="fa fa-caret-right"></i>';
			
			new_html += '</p>';
		}
	} else {
		new_html += '<p>无数据!</p>';	
	}

	if (index >= 2) {
		new_html += '<div class="clear"></div>';
		new_html += '<p class="clear cas_anniu center"><a href="#" class="btn_back" style="width:70px;">返回</a></p>';
	}
		
	$(".right_cont .ability_area_"+ index +" .tiaojian").html(new_html);
	$(".right_cont .ability_area").each(function(){$(this).hide();});
	$(".right_cont,.ability_area_" + index).show();
	
	$(".right_cont .ability_area_"+ index +" .tiaojian").prev().text(title);
	
	$(".btn_back").click(function () {
	    if (backId != '') {
           levelDz.pop();
           levelDzCheck.pop();
        }
		$(this).parent().parent().parent().hide().prev(".ability_area").show();
	});
}

function next_index(obj){
	
	var id_level = $(obj).prev().val();
	var tmp = id_level.split("_");
	
	var id = tmp[0];
	var level = tmp[1];
	
	if (level >= 4) return;
	
	levelDz.push(id_level);
    levelDzCheck.push(id_level);
	
	//var url = '${ctx}/qualityManage/guide.do';
	//var params = 'handle=getNextStudyGuide&id=' + id + '&level=' + level;
	var url = '${ctx}/CVSetManage.do';
	var params = 'mode=getDeepQuality&level=' + level + '&id=' + id + '&unit_id=' + unit_id + "&image_ids=" + image_ids;
	
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
			
			right_area_update(++level, list, title, id_level);
		}
	});
	
}

function save_unit_guide(obj) {
	
	var params = '';
	
	/*
	$('input[name="chk_guide"]:checked').each(function(){
		params += $(this).val() + ",";
	});
	*/
	
	for (i in stock) {
	   params += stock[i] + "," ;
	}
	
	if (stock.length == 0) {
		alert('请选择能力模型！');
		return;
	}
	
	var uncheckParams = '';
	var uncheckArray = uncheckHT.getKeys() ;
	if (uncheckHT.getSize() > 0)
	  for (j in uncheckArray) {
	     uncheckParams += uncheckArray[j] + "," ;
	  }
	
	var url = '${ctx}/CVSetManage.do';
	params = 'mode=save_guide_ref&id=' + $(obj).val() + '&id_levels=' + params ;
	
	if (uncheckHT.getSize() > 0) params += "&uncheckId_levels=" + uncheckParams ;
	
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

function cvsSubmitAudit(){
    var projectfrom=$("#projectfrom").val();
	$.ajax({
		type:'post',
		url:'${ctx}/CVSetManage.do?mode=updateState&state=2&id=${View[0].id}&hisCvSetId=${cvsetQualityHistory[0].cvSetId}&code=${View[0].code}',		
		dataType:'text',
		success:function(result){
			if(result == 'success'){
                if(projectfrom==1){
                    window.location.href = "${ctx}/CVSetManage.do?mode=myXiangMu";
                }else {
                    window.location.href = "${ctx}/CVSetManage.do";
                }
			}else{
				alert("提交失败!");
			}
		}
	});
}

function saveAll(){
	alert("保存成功");
	//window.location.href = "${ctx}/CVSetManage.do"; //"${ctx}/CVSetManage.do?mode=myXiangMu";
	window.history.go(-1);
}



	function getXueKe(){
		var params = 'mode=getXueke&imageName='+$('#renWuImage').val();
		$.ajax({
			url		:"${ctx}/quality/userImageManage.do",
			data	:params,
			type	:'post',
			dataType:'json',
			success:function(data){
				var bgXueke=data.result[0].name;
				document.getElementById('bgXueke').innerHTML=bgXueke;
			}
		});
	}
var banciLayerObj ;
var layerPx ;
var layerPy ;
layerPx = $(window).height() / 3 ; // Math.random()*($(window).height()-300)
layerPy = $(window).width() / 3;

	
function banciFinish() {
   var refUrl = "${ctx}/CVSetManage.do?mode=saveCVSetScheduleFaceTeach&cvSetId=${View[0].id}" ;
   var refParams = $("#frm_banci").serialize() ;
   console.log("refParams="+refParams);
   $.ajax({
		url		:refUrl,
		data	:refParams,
		type	:'post',
		dataType:'json',
		async:false,
		success:function(data1){					
           if (data1.result == 'success') {
			   alert('保存班次安排成功!');
		   } else {
		       alert('保存班次安排失败！');
		   }
		},
        error:function(data2){                            
          alert('保存班次安排失败：' + data2);
        }
   });
   
   layer.close(banciLayerObj);  
}

/**
 *  编辑排期
 * @param trIdVal
 */
function modifyBanci(trIdVal) {
    var trObj ;
    var trAllObj ;
    $("#baociTable").find("tbody tr").each(function(){
        var trJsonObj = jQuery.parseJSON($(this)[0].id);
        if (trJsonObj.id == trIdVal) {
            trObj = trJsonObj ;
            trAllObj = $(this) ;
        }
    });

    if (trObj != undefined) {
        //期数
        $("#baociNameAdd").val(trObj.class_name) ;
        //招生人数
        $("#baociManAdd").val(trObj.people_number) ;


        //起止时间
        $("#baociStartTimeAdd").val(trObj.train_starttime) ;
        $("#baociEndTimeAdd").val(trObj.train_endtime) ;

        //上课时间
        $("#lessonStartTimeAdd").val(trObj.lession_starttime) ;
        $("#lessonEndTimeAdd").val(trObj.lession_endtime) ;
        //培训地点
        $("#baociDdAdd").val(trObj.train_place) ;
        //联系方式
        $("#baociContactWayAdd").val(trObj.contact_way) ;
        //乘车路线
        $("#routeWayAdd").val(trObj.route_way) ;

        layer.open({
            type: 1,
            title: '修改培训期数',
            skin: 'layui-layer-rim', //加上边框
            area: [900, 450], //宽高
            offset: [ //坐标
                layerPx,layerPy
            ],
            content: $('#cvsScheduleAdd'),
            btn: ['修改','取消'],
            yes: function(index, layero){
                //检查排期录入数据
                var errorNum=check_schedule(0);
                if(typeof errorNum=='undefined'||errorNum<=0){
                    var oldTrAllObj = trAllObj ;
                    var trStr = '' ;
                    //期数
                    var bcName = $("#baociNameAdd").val() ;
                    //招生人数
                    var bcMan = $("#baociManAdd").val() ;
                    //起止时间
                    var bcStartTime = $("#scheduleStartTimeAdd").val() ;
                    var bcEndTime = $("#scheduleEndTimeAdd").val() ;
                    var bcTime  = bcStartTime + '--' + bcEndTime ;
                    //上课时间
                    var skStartTime = $("#lessonStartTimeAdd").val() ;
                    var skEndTime = $("#lessonEndTimeAdd").val() ;
                    var skTime  = skStartTime + '--' + skEndTime ;
                    //培训地点
                    var bcDd = $("#baociDdAdd").val() ;
                    //联系方式
                    var bcContactWay = $("#baociContactWayAdd").val() ;
                    //乘车路线
                    var routeWayAdd = $("#routeWayAdd").val() ;

                    var trIdJson ='{"class_name":"' + bcName + '","people_number":"' + bcMan
                        + '","train_starttime":"' + bcStartTime + '","train_endtime":"' + bcEndTime
                        + '","lession_starttime":"' + skStartTime + '","lession_endtime":"' + skEndTime
                        + '","train_place":"' + bcDd+ '","contact_way":"' + bcContactWay+ '","route_way":"' + routeWayAdd + '","id":"' + trIdVal + '"}' ;

                    var newTrObj = oldTrAllObj[0] ;

                    newTrObj.id = trIdJson ;
//		     newTrObj.cells[2].innerHTML = bcName + '<input type="hidden" name="class_name" value="' + bcName + '"/>' ;
                    newTrObj.cells[2].innerHTML = bcMan + '<input type="hidden" name="people_number" value="' + bcMan + '"/>'+ '<input type="hidden" name="class_name" value="' + bcName + '"/>'  ;
                    newTrObj.cells[3].innerHTML = bcDd + '<input type="hidden" name="train_place" value="' + bcDd + '"/>' ;
                    newTrObj.cells[4].innerHTML = bcTime + '<input type="hidden" name="train_starttime" value="' + bcStartTime + '"/>' + '<input type="hidden" name="train_endtime" value="' + bcEndTime + '"/>' ;
                    newTrObj.cells[5].innerHTML = skTime + '<input type="hidden" name="lession_starttime" value="' + skStartTime + '"/>' + '<input type="hidden" name="lession_endtime" value="' + skEndTime + '"/>' ;
                    newTrObj.cells[6].innerHTML = routeWayAdd + '<input type="hidden" name="route_way" value="' + routeWayAdd + '"/>' ;
                    newTrObj.cells[7].innerHTML = bcContactWay + '<input type="hidden" name="contact_way" value="' + bcContactWay + '"/>' ;
                    layer.close(index);
                    $("#cvsScheduleAdd").hide();
                }
            },btn2: function(index, layero){
                layer.close(index)
                $("#cvsScheduleAdd").hide();
            }
        });
        $("#cvsScheduleAddTable").find("tbody").show() ;
    }
}

function banciDel() {
   var delNum = 0 ;
   $("#baociTable").find("tbody tr").each(function(){
      if ($(this)[0].cells[0].children[0].checked) {
         $(this)[0].remove() ;
         delNum++ ;
      }
   });
   if (delNum == 0) alert('请选择要删除的班次！') ;
   banciOrder();
}

function cvsSave(){
    alert("保存成功");
    var projectfrom=$("#projectfrom").val();
    if(projectfrom==1){
        window.location.href = "${ctx}/CVSetManage.do?mode=myXiangMu";
    }else {
        window.location.href = "${ctx}/CVSetManage.do";
    }
//		window.history.go(-1);
}
//课程排期，cvsSchedule
function cvsSchedule(){
    $('#cvs_step3').show();
    $('#cvs_step2').hide();
    $('.no_pass').hide();
}

function datainpClick(dataObj) {
   var thisObj = dataObj;
   var thisId = thisObj.id;	
   jeDate({
		dateCell:"#"+thisId,
		format:"YYYY-MM-DD",
		isinitVal:true,
		isTime:true,		
		okfun:function(val){
		    thisObj.value = val ;			
		},
		choosefun:function(val) {
			thisObj.value = val ;
		}
	});	
}
//添加期数
function banciAdd() {
    $("#baociNameAdd").val("") ;
    $("#baociManAdd").val("") ;
    $("#baociDdAdd").val("") ;
    $("#scheduleStartTimeAdd").val("") ;
    $("#scheduleEndTimeAdd").val("") ;
    $("#lessonStartTimeAdd").val("") ;
    $("#lessonEndTimeAdd").val("") ;
    $("#baociContactWayAdd").val("") ;
    $("#routeWayAdd").val("") ;

    var a = 1 ;
    $("#baociTable").find("tbody tr").each(function(){
        a++ ;
    });
    $("#scheduleNum").html('第'+a+'期') ;

    layer.open({
        type: 1,
        title: '添加培训期数1',
        skin: 'layui-layer-rim', //加上边框
        area: [800, 500], //宽高
        offset: [ //坐标
            100,200
        ],
        content: $('#cvsScheduleAdd'),
        btn: ['确定','取消'],
        yes: function(index, layero){
            var trStr = '' ;
            //期数
            var bcName = $("#baociNameAdd").val() ;
            //招生人数
            var bcMan = $("#baociManAdd").val() ;
            //起止时间
            var bcStartTime = $("#scheduleStartTimeAdd").val() ;
            var bcEndTime = $("#scheduleEndTimeAdd").val() ;
            var bcTime  = bcStartTime + '--' + bcEndTime ;
            //上课时间
            var skStartTime = $("#lessonStartTimeAdd").val() ;
            var skEndTime = $("#lessonEndTimeAdd").val() ;
            var skTime  = skStartTime + '--' + skEndTime ;
            //培训地点
            var bcDd = $("#baociDdAdd").val() ;
            //联系方式
            var bcContactWay = $("#baociContactWayAdd").val() ;
            //乘车路线
            var routeWayAdd = $("#routeWayAdd").val() ;
            if($('#baociManAdd').val() == "" ){
                alert("请输入招生人数！");
                $('#baociManAdd').focus();
                return false;
            }
//检查排期录入数据
            var errorNum=check_schedule(0);
            if(typeof errorNum=='undefined'||errorNum<=0){
                var randomNum = Math.floor(Math.random() * 10000 + 1 );
                var trIdJson = '{"class_name":"' + bcName + '","people_number":"' + bcMan
                    + '","train_starttime":"' + bcStartTime + '","train_endtime":"' + bcEndTime
                    + '","lession_starttime":"' + skStartTime + '","lession_endtime":"' + skEndTime
                    + '","train_place":"' + bcDd+ '","contact_way":"' + bcContactWay+ '","route_way":"' + routeWayAdd + '","id":"' + randomNum + '"}' ;

                trStr += '<tr id=\'' + trIdJson + '\'>'
                    + '<td><input type="checkbox" id="baociCHK"/></td>'
                    + '<td class="bcIdClass">1</td>'
                    //	           + '<td>' + bcName + '<input type="hidden" name="class_name" value="' + bcName + '"/></td>'
                    + '<td>' + bcMan + '<input type="hidden" name="class_name" value="' + bcName + '"/><input type="hidden" name="people_number" value="' + bcMan + '"/></td>'
                    + '<td>' + bcDd + '<input type="hidden" name="train_place" value="' + bcDd + '"/></td>'
                    + '<td>' + bcTime + '<input type="hidden" name="train_starttime" value="' + bcStartTime + '"/>'
                    + '<input type="hidden" name="train_endtime" value="' + bcEndTime + '"/>'
                    + '</td>'
                    + '<td>' + skTime + '<input type="hidden" name="lession_starttime" value="' + skStartTime + '"/>'
                    + '<input type="hidden" name="lession_endtime" value="' + skEndTime + '"/>'
                    + '</td>'
                    + '<td>' + routeWayAdd + '<input type="hidden" name="route_way" value="' + routeWayAdd + '"/></td>'
                    + '<td>' + bcContactWay + '<input type="hidden" name="contact_way" value="' + bcContactWay + '"/></td>'
                    + '<td><a href="javascript:modifyBanci(\'' + randomNum + '\');">编辑</a>  <a href="javascript:delBanci(\'' + randomNum + '\');">删除</a></td>'
                    + '</tr>'
                ;
                $("#baociTable").find("tbody").append(trStr) ;
                banciOrder() ;

                layer.close(index);
                $("#cvsScheduleAdd").hide();

            }

        },btn2: function(index, layero){
            layer.close(index);
            $("#cvsScheduleAdd").hide();
        }
    });
    $("#cvsScheduleAddTable").find("tbody").show() ;

}
/**
 *
 * 最上边排期删除
 * */
function banciDel() {
    var delNum = 0 ;
    $("#baociTable").find("tbody tr").each(function(){
        if ($(this)[0].cells[0].children[0].checked) {
            $(this)[0].remove() ;
            delNum++ ;
        }
    });
    if (delNum == 0) alert('请选择要删除的期数！') ;
    banciOrder();
}
/**
 *
 * 行排期删除
 * */
function delBanci(trIdVal) {
    $("#baociTable").find("tbody tr").each(function(){
        var trJsonObj = jQuery.parseJSON($(this)[0].id);
        if (trJsonObj.id == trIdVal) {
            $(this)[0].remove() ;
        }
    });
    banciOrder();
}
/**
 *
 * 排期全选
 * */
function banciChkAll() {
    var chkFlag = $("#baociAllCHK")[0].checked ;
    if (chkFlag) {
        $("#baociTable").find("tbody tr").each(function(){
            $(this)[0].cells[0].children[0].checked = true ;
        });
    } else {
        $("#baociTable").find("tbody tr").each(function(){
            $(this)[0].cells[0].children[0].checked = false ;
        });
    }
}
/**
 * 排期排序
 * */
function banciOrder() {
    var orderNum = 1 ;
    $("#baociTable").find("tbody tr").each(function(){
        $(this)[0].cells[1].innerHTML ='第'+orderNum+'期';
        orderNum++ ;
    });
}
/**
 *
 *
 * 检查排期数据
 * */
function check_schedule(errorNum) {

    if($('#baociManAdd').val() == "" ){
        alert("请输入招生人数！");
        ++errorNum;
        $('#baociManAdd').focus();
        return errorNum;
    }
    if($('#scheduleStartTimeAdd').val() == "" ){
        alert("请输入培训开始时间！");
        ++errorNum;
        $('#scheduleStartTimeAdd').focus();
        return errorNum;
    }
    if($('#scheduleEndTimeAdd').val() == "" ){
        alert("请输入培训结束时间！");
        ++errorNum;
        $('#scheduleEndTimeAdd').focus();
        return errorNum;
    }
    if($('#lessonStartTimeAdd').val() == "" ){
        alert("请输入上课开始时间！");
        ++errorNum;
        $('#lessonStartTimeAdd').focus();
        return errorNum;
    }
    if($('#lessonEndTimeAdd').val() == "" ){
        alert("请输入上课结束时间！");
        ++errorNum;
        $('#lessonEndTimeAdd').focus();
        return errorNum;
    }
    if($('#baociDdAdd').val() == "" )
    {
        alert("请输入培训地点！");
        ++errorNum;
        $('#baociDdAdd').focus();
        return errorNum;
    }
    if($('#baociContactWayAdd').val() == "" ){
        alert("请输入联系方式！");
        ++errorNum;
        $('#baociContactWayAdd').focus();
        return errorNum;
    }
    if($('#routeWayAdd').val() == "" ){
        alert("请输入乘车路线！");
        ++errorNum;
        $('#routeWayAdd').focus();
        return errorNum;
    }
}
//预览项目或课程
function preView(){
	$("#cvs_step2").hide();
	
	$("#cvs_preView").load("${ctxQiantaiURL}/entityManage/entityView.do?type=play4View&paramType=project&id=${View[0].id}",function(){
		$("#cvs_preView").show();
		$(".course_video").css("float","right");
		$(".course_list").css("width","100%");
		$(".course_list").removeAttr("background-color");
	});
}
</script>
</body>
</html>