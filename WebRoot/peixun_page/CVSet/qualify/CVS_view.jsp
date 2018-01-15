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
		.right_cont .ability_area i{font-size:18px;color:#0B92E8;margin:-5px 20px 0 10px;}
		.right_cont .ability_area .tiaojian p{width:45%;line-height:30px;font-size:12px;cursor:pointer;}
		.right_cont .ability_area .tiaojian p input[type=checkbox]{float:left;margin:6px 5px 0 0;}
		.right_cont .ability_area .tiaojian p span{font-size:12px;}
		.left_cont h2,.right_cont h2{background:#ebebeb;border-bottom:1px solid #ccc;font-size:16px;font-weight:600;padding:5px 10px;line-height:35px;}
		.left_cont h2 span{background-color: #0B92E8;color:#fff;border:1px solid #0B92E8;float:right;padding:5px 10px;border-radius:6px;line-height:20px;}
		.left_cont h2 span a{text-decoration: none;color:#fff;}
		.left_cont h3{font-size:14px;font-weight:600;margin:20px;}
		input{background-color:#f0f0f0;}
	</style>
	
	<link rel="stylesheet" href="${ctx}/peixun_page/css/preview.css" />
</head>

<%@include file="/peixun_page/top.jsp"%>

<body>
<!-- 内容 -->
<div class="center" id="mainPage">
	<form id="frm_add" action="${ctx}/CVSetManage.do?mode=CVS_add" method="post" enctype="multipart/form-data">
	<div class="center01">
		<div class="tiaojian" style="min-height:40px;">
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目名称：</span>
				<input type="text" value="${View[0].name}" name="name" readonly/>
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目授课形式：</span>
				<input type="text" value="<c:if test="${View[0].forma == 1}">远程</c:if><c:if test="${View[0].forma == 2}">远程+面授</c:if><c:if test="${View[0].forma == 3}">面授</c:if>" readonly />
			</p>
			
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"> 人物画像：</span>
				<input type="text" id="renWuImage" class="portraits_input" value="${userImageNames}" readonly />
				<input type="hidden" name="userImage" value="${userImageIds}"/>
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"> 项目序号：</span>
				<input type="text" id="code" name="code" value="${View[0].code}" readonly />
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"> 项目负责人：</span>
				<input type="text" value="${manager}" class="person_btn" readonly/>
				<input name="manager" type="hidden" value="${manager_id}" id="xmmanager" />
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"> 授课教师：</span>
				<input  type="text" value="${teacher}" class="person_btn" readonly/>
				<input name="lessonTeacher" type="hidden" value="${teacher_id}"  />
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;">示教教师：</span>
				<input type="text" value="${otherTeacher}" class="person_btn" readonly/>
				<input name="generalTeacher" type="hidden" value="${otherTeacher_id}" />
			</p>
			
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"> 项目归属机构：</span>				
				<input type="text" value="<c:forEach items="${orgList}" var="org">${org.name}&nbsp;&nbsp;</c:forEach>" class="org_btn" />
				<input type="hidden" value="<c:forEach items="${orgList}" var="org">${org.id}&nbsp;&nbsp;</c:forEach>" id="organization" />
			</p>

			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目简介：</span>
				<textarea name="introduction" style="width:400px;background:#f0f0f0" rows="5">${View[0].introduction}</textarea>
			</p>
			<p class="fl" style="margin-bottom:20px;width:526px">
				<span style="width:9em;text-align:right;">项目封面：</span>
				<img id="course_cover" class="course_cover" src="${imgFile}" width=200px />
			</p>
			<div class="clear"></div>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;">项目声明：</span>
				<textarea name="announcement" id="announcement" style="width:400px;background:#f0f0f0" rows="5" >${View[0].announcement}</textarea>
			</p>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 学习须知：</span>
				<textarea name="knowledge_needed" style="width:400px;background:#f0f0f0" rows="5">${View[0].knowledge_needed}</textarea>
			</p>

			<div class="clear"></div>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;display:block">书籍推荐：</span>
				<%--<div id="bookDiv" style="min-height:35px;height:auto;margin-left:125px;">--%>
				    <%--<c:if test="${refereeBookList != null && fn:length(refereeBookList) > 0}">--%>
				       <%--<c:forEach items="${refereeBookList}" var="rbList" varStatus="status">--%>
				          <%--<span style=" height: 35px; display:block;">--%>
							  <%--<span style="display:inline-block;">	<span class="bookNumer">${status.index+1}.</span> 书籍名称 :</span><input type="text" value="${rbList.key1}" name="book_name" class="inpnewInp" style="width: 322px;margin-left:10px;" readonly="readonly"/>--%>
							  <%--<span style="display:inline-block;margin-left:80px;"> 网址: </span><input type="text" value="${rbList.value1}" name="book_url" class="inpnewInp" style="width: 322px;margin-left:15px;" readonly="readonly"/>--%>
						  <%--</span>--%>
				       <%--</c:forEach>--%>
					<%--</c:if>--%>
				<%--</div>--%>
			</p>
			
            <div class="clear"></div>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;display:block">指南共识：</span>
				<%--<div id="knowledgeBaseDiv" style="min-height:35px;height:auto;margin-left:125px;">--%>
				    <%--<c:if test="${knowledgeBaseList != null && fn:length(knowledgeBaseList) > 0}">--%>
				       <%--<c:forEach items="${knowledgeBaseList}" var="rbList" varStatus="status">--%>
				           <%--<span style=" height: 35px; display:block;">--%>
							  <%--<span style="display:inline-block;">	<span class="knowledgeBaseNumer">${status.index+1}.</span> 指南名称 :</span><input type="text" value="${rbList.key1}" name="knowledgebase_name" class="inpnewInp" style="width: 322px;margin-left:10px;" readonly="readonly"/>--%>
							  <%--<span style="display:inline-block;margin-left:80px;"> 网址: </span><input type="text" value="${rbList.value1}" name="knowledgebase_url" class="inpnewInp" style="width: 322px;margin-left:15px;" readonly="readonly"/>--%>
						   <%--</span>--%>
				       <%--</c:forEach>--%>
					<%--</c:if>--%>
					<%--<c:if test="${knowledgeBaseList == null || fn:length(knowledgeBaseList) == 0}">--%>
					    <%--<span style=" height: 35px; display:block;">--%>
						  <%--<span style="display:inline-block;">	<span class="knowledgeBaseNumer">1.</span> 指南名称 :</span><input type="text"  name="knowledgebase_name" class="inpnewInp" style="width: 322px;margin-left:10px;" readonly="readonly"/>--%>
						  <%--<span style="display:inline-block;margin-left:80px;"> 网址: </span><input type="text"  name="knowledgebase_url" class="inpnewInp" style="width: 322px;margin-left:15px;" readonly="readonly"/>--%>
						<%--</span>--%>
					<%--</c:if>--%>
				<%--</div>--%>
			</p>
			
			<div class="clear"></div>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;display:block">最新文献：</span>
				<%--<div id="referenceDiv" style="min-height:35px;height:auto;margin-left:125px;">--%>
				    <%--<c:if test="${referenceLatelyList != null && fn:length(referenceLatelyList) > 0}">--%>
				       <%--<c:forEach items="${referenceLatelyList}" var="rbList" varStatus="status">--%>
				           <%--<span style=" height: 35px; display:block;">--%>
							  <%--<span style="display:inline-block;">	<span class="referenceNumer">${status.index+1}.</span> 文献名称 :</span><input type="text" value="${rbList.key1}" name="reference_name" class="inpnewInp" style="width: 322px;margin-left:10px;" readonly="readonly"/>--%>
							  <%--<span style="display:inline-block;margin-left:80px;"> 网址: </span><input type="text" value="${rbList.value1}" name="reference_url" class="inpnewInp" style="width: 322px;margin-left:15px;" readonly="readonly"/>--%>
						   <%--</span>--%>
				       <%--</c:forEach>--%>
					<%--</c:if>--%>
				<%--</div>--%>
			</p>
			
			<div class="clear"></div>
			<p class="fl cas_anniu" style="margin-left:9.1em;">
				<a href="javascript:show_div();" class="fl next_step" style="width:140px;margin-left:15px;margin-bottom:15px">下一步</a>
			</p>
			<p class="fl cas_anniu">
				<a href="javascript:window.history.go(-1);" class="fr" style="width:70px;margin-left:15px;margin-bottom:15px">返回</a>
			</p>
		</div>

		<div class="clear"></div>
	</div>
	<input type="hidden" id="cvIds" name="cvscheduleIds" value="${cvschedule_id}"/>
	</form>
</div>
<div id="container"></div>

<div id="course_create_1" style="display:none">
	<div class="top_cont">	
		<h2>${View[0].name}</h2>
	</div>
	<div class="sub_nav">
		<span id="preView" class="btn_blue"><a href="javascript:previewCVS();">项目预览</a></span>
		<span id="viewDetail" class="btn_blue"><a href="javascript:viewDetail();">项目详细</a></span>
		<span id="bagao_btn" class="btn_blue"><a href="#">审核须知</a></span>
		<!-- 
		<span id="qualify_btn" class="btn_blue btn_v_note" style="display:none"><a href="#" >审核意见</a></span>
		 -->
		<span id="qualify_btn" class="btn_blue btn_v_note" ><a href="#" >审核意见</a></span>	
		<input type="hidden" id="optionType" value="${optionType}"/>
		<c:choose>
			<c:when test="${View[0].status == 2 }">
				<c:if test="${empty historyOwn }">
					<span class="fr btn_v_no"><a href="javascript:void(0);">审核不通过</a></span>
					<span class="fr btn_v_ok btn_blue"><a href="javascript:void(0);">审核通过</a></span>
				</c:if>
				<c:if test="${not empty historyOwn }">
					<span class="fr btn_blue">您的审核结果：
						<c:if test="${historyOwn.qualifyStatus == 3 }">通过</c:if>
						<c:if test="${historyOwn.qualifyStatus == 4 }">不通过</c:if>
					</span>
				</c:if>
			</c:when>
			<c:when test="${View[0].status == 3 }">
				<span class="fr btn_blue"><a href="javascript:void(0);">项目已审核通过</a></span>
			</c:when>
			<c:when test="${View[0].status == 4 }">
				<span class="fr btn_blue"><a href="javascript:void(0);">项目未审核通过</a></span>
			</c:when>
			<c:when test="${View[0].status == 5 }">
				<span class="fr btn_blue"><a href="javascript:void(0);">项目已发布</a></span>
			</c:when>
			<c:otherwise>
				
			</c:otherwise>
		</c:choose>
		
		
		
		
		
	</div>
	<div class="verify_list pass_result">
		<div class="v_cont no_pass" style="width:700px;height:400px;display:none;background:#FFF;border:solid 1px #c7daf9;">
			<p class="clear">
				<span style="width:7em;text-align:right;">审核人：</span>
				<c:forEach items="${cvsetQualityPersion}" var="per">${per.name}&nbsp;&nbsp;</c:forEach>
			</p>
			<p class="clear">
				<span style="width:7em;text-align:right;">审核结果：</span>
				<c:forEach items="${cvsetQualityHistory}" var="his">
					<c:if test="${his.qualifyStatus == 3}">通过&nbsp;&nbsp;</c:if>
					<c:if test="${his.qualifyStatus == 4}">未通过&nbsp;&nbsp;</c:if>
				</c:forEach>
				 <%--
				<input type="text" value="${cvsetQualityHistory.qualifyStatus }" class="notice_input" disabled="">
				 --%>
			</p>
			<p class="clear">
				<span style="width:7em;text-align:right;">未通过原因：</span>
				<div id="errorReason"></div>
				
				<%-- 
				<textarea name="reason" style="width:300px;" rows="5" disabled="">
				
				<textarea style="width:600px;height:300px">
				--%>
				
				<!-- 
					<c:forEach items="${cvsetQualityHistory}" var="his">
						<c:if test="${his.opinionType == '1^'}">内容不完整&nbsp;&nbsp;</c:if>
						<c:if test="${his.opinionType == '2^'}">内容与所选胜任力指标体系不符&nbsp;&nbsp;</c:if>
						<c:if test="${his.opinionType == '3^'}">内容有错误&nbsp;&nbsp;</c:if>
						<c:if test="${his.opinionType == '4^'}">课程形式设置有误&nbsp;&nbsp;</c:if>
						<c:if test="${his.opinionType == '5^'}">格式不符合要求&nbsp;&nbsp;</c:if>
						<c:if test="${his.opinionType == '6^'}">课程起止时间有误&nbsp;&nbsp;</c:if>
						<c:if test="${his.opinionType == '7^'}">授课教师与实际不符&nbsp;&nbsp;</c:if>
						<c:if test="${his.opinionType == '8^'}">商业倾向过于明显&nbsp;&nbsp;</c:if>
						<c:if test="${his.opinionType == '9^'}">其他原因:${his.opinion}</c:if>
					</c:forEach>
				 -->
					
					<%--
				</textarea>
				 --%>				
			</p>
		</div>
	</div>
	<div class="center" id="bagaoshu" style="display:none">
	<%--
	<div class="report_cont">
		<h2 class="title" style="font-size:20px;margin-bottom:30px;">项目报告</h2>
		<div class="cont">
			<textarea rows="" name="report1" id="report1" cols="" style="width:540px;height:300px">${View[0].report}</textarea>
		</div>
	</div>
	 --%>
	<div class="per-centen">
		<h1 class="per-centen-h1">项目审核报告</h1>
		<div class="per-centen-cont">
			<h2>尊敬的教授：</h2>
			<p> 您好！</p>
			<p> 感谢您在百忙之中参与到国家级继续医学教育示范项目的评审工作，请您仔细审阅项目信息及教学内容，并根据项目审核标准判定本项目是否通过审核。谢谢！</p>
		</div>
		<div class="per-xinxi">
		<h2>项目信息：</h2>
		<ul class="per-xinxi-ul">
			<li>
				<span>名称：</span>
				<p>${View[0].name}</p>
				<div style="clear:both;"></div>
			</li>
			<li>
				<span>所属机构：</span>
				<p><c:forEach items = "${orgList}" var="org">${org.name}&nbsp;&nbsp;</c:forEach></p>
				<div style="clear:both;"></div>
			</li>
			<li>
				<span>授课形式：</span>
				<p><c:if test="${View[0].forma == 1}">远程</c:if><c:if test="${View[0].forma == 2}">远程+面授</c:if><c:if test="${View[0].forma == 3}">面授</c:if></p>
				<div style="clear:both;"></div>
			</li>
			<li>
				<span>所属学科：</span>
				<p id="bgXueke"></p>
				<div style="clear:both;"></div>
			</li>
			<li>
				<span style="width:600px;">培训对象：${userImageNames}</span>
				<div style="clear:both;"></div>
			</li>
		</ul>
	</div>
	<div class="per-xinxi">
		<h2>项目负责人信息：</h2>
		<ul class="per-xinxi-ul">
			<li>
				<span>姓名：</span>
				<p>${manager}</p>
				<div style="clear:both;"></div>
			</li>
			<li>
				<span>工作单位：</span>
				<p id="bgUnit"></p>
				<div style="clear:both;"></div>
			</li>
			<li>
				<span>职称：</span>
				<p id="bgJobName"></p>
				<div style="clear:both;"></div>
			</li>
			<li>
				<span>职务：</span>
				<p id="bgOffice"></p>
				<div style="clear:both;"></div>
			</li>
			<li>
				<span>所属学科：</span>
				<p id="bgPropNames"></p>
				<div style="clear:both;"></div>
			</li>
		</ul>
	</div>
	<div class="per-xinxi">
		<h2>项目简介：</h2>
		<p style="line-height:25px;font-size:14px;color:#666;text-indent:2em;font-weight:100;">${View[0].report}</p>
	</div>
	<div class="per-xinxi">
		<h2>项目审核标准：</h2>
		<ul class="per-xinxi-ul color">
			<li>
				<p>教学目标合理、清晰，符合教学对象需求；</p>
				<div style="clear:both;"></div>
			</li>
			<li>
				
				<p>教学大纲完整、详尽；</p>
				<div style="clear:both;"></div>
			</li>
			<li>
				
				<p>教学内容科学、准确；</p>
				<div style="clear:both;"></div>
			</li>
			<li>
				
				<p>教学内容客观、公正，无商业化倾向；</p>
				<div style="clear:both;"></div>
			</li>
			<li>
				
				<p>教学内容符合项目针对性、实用性和先进性；</p>
				<div style="clear:both;"></div>
			</li>
			<li>
				
				<p>教学内容符合相关人员岗位胜任力模型指标</p>
				<div style="clear:both;"></div>
			</li>
			<li>
				
				<p>教师仪表端庄，举止适宜，示范规范、熟练；</p>
				<div style="clear:both;"></div>
			</li>
			<li>
				
				<p>考核内容覆盖教学大纲主要内容，科学合理。</p>
				<div style="clear:both;"></div>
			</li>
		</ul>
	</div>
	<div class="per-centen-cont">
		<p style="font-weight:600;font-size:18px;color:#333;line-height:40px;width:100%;text-align:center;">再次感谢您对继续医学教育项目评审工作的大力支持和帮助！</p>
	</div>
	</div>
</div>
	
	<div class="center" style="">
	<div class="center01">
		<div class="left_cont">
			<h2>目录</h2>

			
		</div>
		 <div class="right_cont" style="display:none;">
			<div class="tiaojian cont_ok" style="min-height:40px;display:none;padding:20px 0;display:none;">
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;">审核结果：</span>
					<input type="text" value="通过" disabled />
				</p>				
				<p class="clear">
					<span style="width:9em;text-align:right;">&nbsp;</span>
					<input type="button" name="v_ok" value="确定" class="btn_blue" />
					<input type="button" name="v_reset" value="取消" />
				</p>
			</div>
			<div class="tiaojian cont_no" style="min-height:40px;display:none;padding:20px 0;display:none;">
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;">审核结果：</span>
					<input type="text" value="未通过" disabled />
				</p>
				<div class="clear" style="margin-bottom:20px;">
					<p class="fl">
						<span style="width:9em;text-align:right;">项目未通过原因：</span>
					</p>
					<ul class="fl reason_list">
						<li><input type="checkbox" name="chk" id="1" value="内容不完整" /> 内容不完整</li>
						<li><input type="checkbox" name="chk" id="2" value="内容与所选胜任力指标体系不符" /> 内容与所选胜任力指标体系不符</li>
						<li><input type="checkbox" name="chk" id="3" value="内容有错误" /> 内容有错误</li>
						<li><input type="checkbox" name="chk" id="4" value="课程形式设置有误" /> 课程形式设置有误</li>
						<li><input type="checkbox" name="chk" id="5" value="格式不符合要求" /> 格式不符合要求</li>
						<li><input type="checkbox" name="chk" id="6" value="课程起止时间有误" /> 课程起止时间有误</li>
						<li><input type="checkbox" name="chk" id="7" value="授课教师与实际不符" /> 授课教师与实际不符</li>
						<li><input type="checkbox" name="chk" id="8" value="商业倾向过于明显" /> 商业倾向过于明显</li>
						<li><input type="checkbox" name="chk" id="9" value="9" class="other_reason" /> 其他原因<input type="text" name="other_reason" id="other_reason"/></li>
					</ul>
				</div>

				<p class="clear">
					<span style="width:9em;text-align:right;">&nbsp;</span>
					<input type="button" name="v_no" value="确定" class="btn_blue" />
					<input type="button" name="v_reset" value="取消" />
				</p>
			</div>
			<!-- <h2 class="ability_area_title">关联能力模型</h2>
			<div class="ability_area ability_area_2">
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
				<h4>1级心血管外科主任 > 专业技能 > 精通临床操作技能 ></h4>
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
			</div> -->
		</div>

		<div class="clear"></div>
	</div>
	
</div>
</div>

<div id="union" style="display:none">
	<div class="clear"></div>
	<div class="bjs"></div>
	
	
	<div class="left_cont_v">	
		<div class="control_bar clear">
			<span class="btn_blue" style="margin-left:230px"><a href="javascript:window.history.go(-1);">返回</a></span>				
				
		</div>
	</div>
	
	<div class="right_cont_v">
		<script id="editor" name="content" type="text/plain">
		</script>
	</div>
	
	<div class="clear"></div>
</div>
<script type="text/javascript">
$("textarea").each(function(){
	$(this).attr("readonly", "readonly");
});

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
		
	/* if ((window.localStorage.getItem("verify_ok") != null && window.localStorage.getItem("verify_ok") == 1 ) || (window.localStorage.getItem("verify_no") != null && window.localStorage.getItem("verify_no") == 1 )){
		$(".control_bar").hide();
		$(".btn_v_note").show();
	}else{
		$(".control_bar").show();
		$(".btn_v_note").hide();
	} */
//选择目录弹出框
	
	$(".left_cont_v,.right_cont_v").height($(window).height() - 223);
	$(".right_cont_v").width($(window).width() - 340);
	$("#editor").css({"width":$(window).width() - 350 + "px",height:$(window).height() - 310 + "px"});

	window.ue = UE.getEditor('editor');
	ue.addListener("ready",function(){
		ue.setContent();
	});

	$(".btn_v_ok").click(function(){
		$('.left_cont').hide();
		$('#union').hide();
		$('#bagaoshu').hide();
		$('.v_cont').hide();
		$(".right_cont,.cont_ok").show().next(".cont_no").hide();
		$(".ability_area,.ability_area_title").hide();
	});
	$(".btn_v_no").click(function(){
		$('.left_cont').hide();
		$('#union').hide();
		$('#bagaoshu').hide();
		$('.v_cont').hide();
		$(".right_cont,.cont_no").show().prev(".cont_ok").hide();
		$(".ability_area,.ability_area_title").hide();
		
	});

	$(".table tbody tr").click(function () {
		$(".ability_area_2,.ability_area_title").show();
		$(".cont_no,.cont_ok").hide();
	});

	$(".level_p p span,.level_p p i").click(function () {
		$(this).parent().parent().parent().hide().next(".ability_area").show();
	});

	$(".btn_back").click(function () {
		$(this).parent().parent().parent().hide().prev(".ability_area").show();
	});

	$("input[name=v_ok]").click(function(){	
		layer.msg('审核通过！一旦提交，则不可更改，确定提交？', {
			time: 0 //不自动关闭
			, btn: ['确定', '取消']
			, yes: function (index) {
				var code=$('#code').val();
				$.ajax({
					type:'post',
					url:'${ctx}/CVSetQualify.do',
					data:'mode=updateState&state=3&opinion=通过&id='+"${View[0].id}"+'&code='+code+'&hisCvSetId='+"${cvsetQualityHistory[0].cvSetId}",
					dataType:'text',
					success: function(Res){
						if(Res == 'success'){
							alert("成功");
						}else{
							alert("失败");
						}
						window.location.href = '${ctx}/CVSetQualify.do?mode=qualify';
					}
				});			
				
				layer.close(index);				
				//JinHO window.location.href = 'project_auth_list.html';
				////window.location.href = '${ctx}/CVSet/CVDistribute.do?method=list';
				
			}
		});
		
	});
	$("input[name=v_no]").click(function(){		
		if ($(".reason_list li input[type=checkbox]:checked").length > 0 ){
			layer.msg('审核未通过！一旦提交，则不可更改，确定提交？', {
				time: 0 //不自动关闭
				, btn: ['确定', '取消']
				, yes: function (index) {
				    var opinionType = '';
					$("input[name='chk']:checked").each(function(){
						opinionType += $(this).prop("id") + "^";
					});		
					var code=$('#code').val();
					$.ajax({
					type:'post',
					url:'${ctx}/CVSetQualify.do',
					data:'mode=updateState&state=4&id='+"${View[0].id}"+'&opinionType='+opinionType+'&opinion='+$("input[name=other_reason]").val()+'&code='+code+'&hisCvSetId='+"${cvsetQualityHistory[0].cvSetId}",
					dataType:'text',
					success: function(Res){
						if(Res == 'success'){
							alert("操作成功!");
						}else{
							alert("操作失败!");
						}
						window.location.href = '${ctx}/CVSetQualify.do?mode=qualify';
					}
				});
					layer.close(index);
					$(".cont_no").hide();
					$(".tags_cont").show();
				}
			});
		}else{
			layer.alert("请选择至少一项未通过原因！")
		}
		if ($("input.other_reason").prop("checked") == true && $("input[name=other_reason]").val() == ""){
			layer.alert("请填写其它原因！");
		}
		$("input.other_reason").click(function(){
			$("input[name=other_reason]").attr("");
		});
		
	});



	$("input[name=v_reset]").click(function(){
		$(this).parent().parent().hide();
	});

	$(".table tbody tr").click(function(){
		$(".tags_cont").show();
		$(".cont_no,.cont_ok").hide();
	});	
});

$('#bagao_btn').click(function(){
	//$('#qualify_btn, #bagaoshu').show();
	$('#bagaoshu').show();
	$('.left_cont').hide();
	$('.right_cont').hide();
	$('.v_cont').hide();
	$('#union').hide();
});

$('#qualify_btn').click(function(){
	var optionTypeValue = $("#optionType").val();
	var strReasons = '',str = '';
	var strReasonsRaw = new Array('内容不完整', '内容与所选胜任力指标体系不符', '内容有错误','课程形式设置有误', '格式不符合要求', '课程起止时间有误', '授课教师与实际不符', '商业倾向过于明显', '其他原因 ');
	

	if(optionTypeValue!=null&&optionTypeValue!=''){
		if(optionTypeValue.substr(optionTypeValue.length-1,1)=="^"){
			optionTypeValue=optionTypeValue.substr(0,optionTypeValue.length-1)
		}
		strReasons = optionTypeValue.split("^");
	}	
	
	for(var i=0;i<strReasons.length;i++){
		str += (i+1)+"."+strReasonsRaw[strReasons[i]-1]+"<br/>";
	}
	$('#errorReason').html(str);
	
	/*
	$.ajax({
		type:'post',
		url:'${ctx}/CVSetManage.do',
		data:'mode=getByAjax&id='+"${View[0].id}",
		dataType:'json',
		success:function(result){
			var strReasons = '',str = '';
			var strReasonsRaw = new Array('内容不完整', '内容与所选胜任力指标体系不符', '内容有错误','课程形式设置有误', '格式不符合要求', '课程起止时间有误', '授课教师与实际不符', '商业倾向过于明显', '其他原因 ');
			
			var aaa = result.list[0].opinionType;
			strReasons = aaa.split("^");
			//strReasons = result.list[0].opinionType.split("^");
			if(strReasons == ""){
				$('.notice_input').val(result.list[0].opinion);
			}else{
				$('.notice_input').val("不通过");
			}
			
			for(var i=0; i<strReasons.length-1; i++){
				alert("i="+i);
				if(strReasons[i] == 9){
					str += (i+1)+"."+'其他原因:'+result.list[0].opinion;
				}else{
					str += (i+1)+"."+strReasonsRaw[strReasons[i]-1]+"\n";
				}	
			}
			$('textarea[name="reason"]').val(str);
		}
	});*/
	$('.right_cont,.cont_ok').hide();
	$('.right_cont,.cont_no').hide();
	$('.left_cont').hide();
	$('.v_cont').show();
	$('#bagaoshu').hide();
	$('#union').hide();
});

function show_div(){
	$('#course_create_1').show();
	$('#mainPage').hide();
	$('.right_cont').hide();
	//$('#union').hide();	
	var str = '<h2>目录</h2>';
	/*var str_cvschedule = '', str_cvschedule_id;
	str_cvschedule = "${cvschedule}".split(",");
	str_cvschedule_id = "${cvschedule_id}".split(",");
	
	for(i=0; i<str_cvschedule.length -1; i++){	
		str += '<h3><input type="radio" id="' + i + '" name="cv" value="' + str_cvschedule_id[i] + '">&nbsp;&nbsp;课程名称<span></span> : '; 
		str += 	str_cvschedule[i];
		//str +=	'<button class="clone_btn btn_blue fr" value="str_cvschedule_id[i]" type="button">课程信息</button>';
		str += '</h3>';	
	} */
	
	//$('.left_cont').html(str);
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
							'<input type="radio" id="' + obj[j].id+'" name="cv" value="'+obj[j].id+'" /> 课程名称:' + obj[j].name + 				         		 			
				    		'</th></tr>'+
					'<tr class="tr"><th width="10%">序号</th><th width="40%">课题</th><th width="20%">类别</th><th width="15%">任务点</th><th width="15%">完成状态</th></tr>';
				for(var i=0; i<obj[j].unitList.length;i++){
					str += '<tr align="center"><td width="10%">' + obj[j].unitList[i].id +
						'</td><td width="40%">' + obj[j].unitList[i].name+'</td><td width="20%">';
							if(obj[j].unitList[i].type == 1) str+='理论讲解'; 
							if(obj[j].unitList[i].type == 2) str+='主题讨论'; 
							if(obj[j].unitList[i].type == 3) str+='随堂考试';
							if(obj[j].unitList[i].type == 4) str+='操作演示'; 
							if(obj[j].unitList[i].type == 5) str+='扩展阅读'; 
							if(obj[j].unitList[i].type == 6) str+='病例分享'; 
					str +='</td><td width="15%"><input type="checkbox" name="point" onClick="javascript:testPoint(this, "' + obj[j].unitList[i].id+'");"';
							if(obj[j].unitList[i].point == 1) str+=' checked' +' disabled="disabled" />'; 
						 str += '</td><td width="15%"><input type="checkbox" name="state" onClick="javascript:testState(this, "'+obj[j].unitList[i].id+'");"';
							if(obj[j].unitList[i].state == 1) str+=' checked'+ ' disabled="disabled" />';
						str += '</td></tr>';
				}
				str += '</table>';
				$('.left_cont').html(str);
			}
			getXueKe();
			getZhuanJia();
		}
	});
}
function previewCVS(){
	<%--
	$('.v_cont, #bagaoshu').hide();
	$('.right_cont,.cont_no').hide();
	$('.right_cont,.cont_ok').hide();
	$('.left_cont').show();
	$('#union').hide();
	window.open("http://www.ncme.org.cn/qiantai/projectView.do?id="+"${View[0].id}");
	--%>
	window.open("${ctxQiantaiURL}/projectView.do?id="+"${View[0].id}");
}
function viewDetail(){
	$('#union').show();
	$('.left_cont, .right_cont').hide();
	$('.v_cont, #bagaoshu').hide();
	
	var left_cont_v = '<div class="control_bar clear">' +
							'<span class="btn_blue" id="huiban" style="margin-left:280px"><a href="javascript:huiban();">返回</a></span>	' +						
					  '</div>';
	
	$.ajax({
		type:'post',
		url:'${ctx}/CVSet/CVUnitAdd.do',
		data:'mode=getUnitForCV&id='+ $('#cvIds').val() ,
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
function itemClick(_id){
	id=_id;
	$('#unitTable').show();
	$('.table').show();
	var url="${ctx}/groupManage/groupClassInfoManage.do";
	 $.ajax({
		type:'post',
		url:url,
		data:{type:'queryGroup',classId:_id},
		dataType:'json',
		success:function(data){
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
	        		   var dialog = UE.getEditor('editor').getDialog("template");
					   dialog.render();
					   dialog.open();
	        	   }
	           }else if(data.message=='noData'){
	        	   //调用模板弹出层
     		   UE.getEditor('editor').setContent('');
     		   var dialog = UE.getEditor('editor').getDialog("template");
				   dialog.render();
				   dialog.open();
	           }
			/* var str = result.content;			
			ue.setContent(str); */
		
	}
}); 
} 
/*
 * 
 获取课程信息，以前代码，可能不用了。20171116李飞
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
} */
function huiban(){
	$('#union').hide();
	$('.left_cont').show();
	//$('.v_cont, #bagaoshu').hide();
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

function getZhuanJia(){
	var params = 'mode=getZhuanJia&managerId='+$('#xmmanager').val();
	$.ajax({
		url		:"${ctx}/expert/GetExpert.do",
		data	:params,
		type	:'post',
		dataType:'json',
		success:function(data){
			var bgUnit=data.result.workUnit;
			document.getElementById('bgUnit').innerHTML=bgUnit;
			var bgJobName=data.result.jobName;
			document.getElementById('bgJobName').innerHTML=bgJobName;
			var bgOffice=data.result.office;
			if(bgOffice==1){
				document.getElementById('bgOffice').innerHTML="主任委员";
			}else if(bgOffice==2){
				document.getElementById('bgOffice').innerHTML="副主任委员";
			}else if(bgOffice==3){
				document.getElementById('bgOffice').innerHTML="秘书长";
			}else if(bgOffice==4){
				document.getElementById('bgOffice').innerHTML="学组长";
			}else if(bgOffice==5){
				document.getElementById('bgOffice').innerHTML="委员";
			}
			var bgPropNames=data.result.propNames;
			document.getElementById('bgPropNames').innerHTML=bgPropNames;
		}
	});
}
</script>
</body>
</html>