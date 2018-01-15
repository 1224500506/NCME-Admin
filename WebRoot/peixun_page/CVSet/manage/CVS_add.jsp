<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
%>
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
		
    
      	        
p span label input {
	
	float: none;
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
<%--1：项目基本信息--%>
<div class="center" id="mainPage">
	<ul id="nav_bar" class="kaoshi_ul" style="padding:2px;">
		<li><i class="action">1</i><p class="fl action">项目基本信息</p><span class="action"></span></li>
		<li><i class="ml30">2</i><p class="fl">课程单元信息</p><span></span></li>
		<li id="span_ms"><i class="ml30">3</i><p class="fl">期数设置</p></li>
	</ul>
	<form id="frm_add" action="${ctx}/CVSetManage.do?mode=CVS_add" method="POST" enctype="multipart/form-data">
	    <input type="hidden" id="projectfrom" name="projectfrom" value="${param.projectfrom}">
	    <input type="hidden" id="cv_set_type" name="cv_set_type" value="0"/>
		<div class="center01">
		<div class="tiaojian" style="min-height:40px;">
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目名称：</span>
				<input type="text" value="" name="name" id="name" autocomplete="off"/>
			</p>

			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目授课形式：</span>
			</p>
			<select name="forma" id="lessonType" >
				<option value="0">全选</option>
				<option value="1">远程</option>
				<option value="3">面授</option>
			</select>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 人物画像：</span>
				<input type="text" class="portraits_input" value="" id="renWuImage"/>
				<input type="hidden" name="userImage"  value="" />
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目序号：</span>
				<input type="text" id="code" name="code" value="" readonly />
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目负责人：</span>
				<input type="text" id="1" value="" class="person_btn" />
				<input name="manager" id="manager" type="hidden" value="" />
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 授课教师：</span>
				<input  type="text" id="2" value="" class="person_btn" />
				<input name="lessonTeacher" type="hidden" value="" />
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;">示教教师：</span>
				<input  type="text" id="3" value="" class="person_btn" />
				<input name="generalTeacher" type="hidden" value=""  />
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"> <em class="red_start">*</em>项目归属机构：</span>
				<input type="text" value="" id="xiangmuOrg" class="org_btn" />
				<input type="hidden" value="" name="organization" id="organization" />
			</p>

			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目简介：</span>
				<textarea name="introduction" id="introduction" style="width:400px;" rows="5"></textarea>
			</p>
			<p class="fl" style="margin-bottom:20px;width:526px">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目封面：</span>
				<input type="file" id="matFile" name="matFile" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp">
				<input type="hidden" id="cover" name="cover" >
				<span>支持jpg，png，jpeg，bmp，gif格式，不超过10M</span>
			</p>
			<div class="clear"></div>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;">项目声明：</span>
				<textarea name="announcement" id="announcement" style="width:400px;" rows="5"></textarea>
			</p>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 学习须知：</span>
				<textarea name="knowledge_needed" id="needed" style="width:400px;color:#887ca6; " rows="5">1.项目学习过程中，设置有任务点，请认真完成各项任务点；2.为保证学习质量，请按照课程顺序学习，不可跳过、快进。
不超过500字</textarea>
			</p>
			<div class="clear"></div>
			
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;display:block">书籍推荐：</span>

			<%--<input id="addBook" type="button" value="添加书籍"/>--%>
				<button class="btn_blue fr" style="margin-right:10px; margin-top:10px;" onClick="javascript:addSource(1);"  type="button" >选择文件</button>
			<input id="chooseSourseBookID" type="hidden" value=""/>
				<div id="bookDiv" style="min-height:35px;height:auto;margin-left:125px;">
						<%--<span style=" height: 35px; display:block;">				  --%>
						  <%--<span style="display:inline-block;">	<span class="bookNumer">1.</span> 书籍名称 :</span><input type="text"  name="book_name" class="inpnewInp" style="width: 322px;margin-left:10px;"/>--%>
						  <%--<span style="display:inline-block;margin-left:80px;"> 网址: </span><input type="text"  name="book_url" class="inpnewInp" style="width: 322px;margin-left:15px;"/>--%>
						  <%--<span class="bookImgDel" style="width: 32px;height: 32px;margin-left: 5px;"><img style="width:20px;height:20px;" src="${ctx}/peixun_page/images/round_remove.png"/></span>--%>
						<%--</span>						--%>
				</div> 
				<%--<i style="display:block;margin-left:120px;width: 32px;height: 32px;" class="bookImgAdd"><img style="width:20px;height:20px;" src="${ctx}/peixun_page/images/plus_circle.png"/></i>--%>
			</p>
			
            <div class="clear"></div>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;display:block">指南共识：</span>
				<input id="chooseSourseKnowledgeID" type="hidden" value=""/>
				<button class="btn_blue fr" style="margin-right:10px; margin-top:10px;" onClick="javascript:addSource(2);"  type="button" >选择文件</button>
				<div id="knowledgeBaseDiv" style="min-height:35px;height:auto;margin-left:125px;">					   
					    <%--<span style=" height: 35px; display:block;">				  --%>
						  <%--<span style="display:inline-block;">	<span class="knowledgeBaseNumer">1.</span> 指南名称 :</span><input type="text"  name="knowledgebase_name" class="inpnewInp" style="width: 322px;margin-left:10px;"/>--%>
						  <%--<span style="display:inline-block;margin-left:80px;"> 网址: </span><input type="text"  name="knowledgebase_url" class="inpnewInp" style="width: 322px;margin-left:15px;"/>--%>
						  <%--<span class="knowledgeBaseImgDel" style="width: 32px;height: 32px;margin-left: 5px;"><img style="width:20px;height:20px;" src="${ctx}/peixun_page/images/round_remove.png"/></span>--%>
						<%--</span>						--%>
				</div> 
				<%--<i style="display:block;margin-left:120px;width: 32px;height: 32px;" class="knowledgeBaseImgAdd"><img style="width:20px;height:20px;" src="${ctx}/peixun_page/images/plus_circle.png"/></i>--%>
			</p>
			
			<div class="clear"></div>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;display:block">最新文献：</span>
				<input id="chooseSourseReferenceID" type="hidden" value=""/>
				<button class="btn_blue fr" style="margin-right:10px; margin-top:10px;" onClick="javascript:addSource(3);"  type="button" >选择文件</button>
				<div id="referenceDiv" style="min-height:35px;height:auto;margin-left:125px;">				    
					    <%--<span style=" height: 35px; display:block;">				  --%>
						  <%--<span style="display:inline-block;">	<span class="referenceNumer">1.</span> 文献名称 :</span><input type="text"  name="reference_name" class="inpnewInp" style="width: 322px;margin-left:10px;"/>--%>
						  <%--<span style="display:inline-block;margin-left:80px;"> 网址: </span><input type="text"  name="reference_url" class="inpnewInp" style="width: 322px;margin-left:15px;"/>--%>
						  <%--<span class="referenceImgDel" style="width: 32px;height: 32px;margin-left: 5px;"><img style="width:20px;height:20px;" src="${ctx}/peixun_page/images/round_remove.png"/></span>--%>
						<%--</span>																		--%>
				</div> 
				<%--<i style="display:block;margin-left:120px;width: 32px;height: 32px;" class="referenceImgAdd"><img style="width:20px;height:20px;" src="${ctx}/peixun_page/images/plus_circle.png"/></i>--%>
			</p>												
			
			<div class="clear"></div>			
			<p class="fl cas_anniu" style="margin-left:9.1em;">
				<a href="javascript:show_div();" class="fl next_step" style="width:140px;margin-left:10px;">下一步</a>
			</p>
			<p class="fl cas_anniu">
				<a href="javascript:window.history.go(-1);" class="fr" style="width:70px;margin-left:10px;">返回</a>
			</p>
			<input type="hidden" name="cvIds" />
		</div>

		<div class="clear"></div>
	</div>
	<input type="hidden" name="report" id="report"/>
	</form>
</div>
<div id="container"></div>
<%--2：课程单元信息--%>
<div id="cvs_step2" style="position:relative; display:none;" class="fixed">
	<ul id="nav_bar2" class="kaoshi_ul" style="padding:2px">
		<li><i class="ml30">1</i><p class="fl ">项目基本信息</p><span></span></li>
		<li><i class="ml30 action">2</i><p class="fl action">课程单元信息</p><span class="action"></span></li>
		<li id="span_ms2"><i class="ml30">3</i><p class="fl">期数设置</p></li>
	</ul>
	<div class="top_cont">
		<h2>项目名称：<input  id="xiagnmuName" style="border-style:none;" readonly/></h2>
	</div>
	<div class="sub_nav">
		<!--<span class="btn_blue fr"><a href="javascript:save();">保存</a></span>-->
		<span class="btn_blue fr" id="step2_submitAudit"><a href="javascript:cvsSubmitAudit();">提交审核</a></span>
		<span class="btn_blue fr" id="step2_save"><a href="javascript:cvsSave();">保存</a></span>
		<span class="btn_blue fr " id="step2_next"><a href="javascript:cvsSchedule();" class="step2_next">下一步</a></span>
		<span class="btn_blue fr"><a href="javascript:history.go(-1);">上一步</a></span>
		<span class="add_course"><a href="javascript:getCourse();">添加课程</a></span>
		<span><a href="javascript:remove_cv()">删除课程</a></span>
		<span><a href="javascript:to_up();">上移</a></span>
		<span><a href="javascript:to_down();">下移</a></span>
	</div>
	<div class="center" style="">
		<div class="center01">
			<div style="height:550px; width:650px;overflow-y:auto; ">
				<div class="left_cont" id="left_cont" style="width:100%;">
					<h2 id="the_top">目录</h2>				
				</div>	
			</div>
			<div class="right_cont" style="display:none;position: absolute; left: 750px; top: 162px;">
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
<%--3：期数设置--%>
<div id="cvs_step3" style="display:none">
	<ul class="kaoshi_ul" style="padding:2px">
		<li><i class="ml30">1</i><p class="fl ">项目基本信息</p><span></span></li>
		<li><i class="ml30">2</i><p class="fl">课程单元信息</p><span></span></li>
		<li><i class="ml30 action">3</i><p class="fl action">期数设置</p></li>
	</ul>
<div class="top_cont">	
	<h2>项目名称：<input  id="xiagnmuName2" style="border-style:none;" readonly/></h2>
</div>
<div class="sub_nav">
	<span id="banciAddId"><a href="javascript:banciAdd();">添加期数</a></span>
	<span id="banciDelId"><a href="javascript:banciDel();">删除期数</a></span>
	<span class="btn_blue fr"><a href="javascript:cvsSubmitAudit();">提交审核</a></span>
	<span class="btn_blue fr"><a href="javascript:banciFinish();">保存</a></span>
	<span class="btn_blue fr"><a href="javascript:previous();">项目预览</a></span>	
</div>

<div class="center" style="">
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

<div id="union" style="display:none">

	<div class="clear"></div>
	<div class="bjs"></div>

	<table border=0 align=left style="background-color:#EBEBEB;height:98%;">
		<tr>
			<td width=360px align=left valign=top style="BORDER-RIGHT: #999999 1px dashed">
				<ul id="tree" class="ztree" style="width:360px; overflow:auto;"></ul>
			</td>
		</tr>
	</table>
	<div>
	<div style="width:100%;height:50px;">
		<a href="javascript:void(0);" class="fl" onclick="getValue();" style="width:75px;margin-right:15px;height:30px;line-height:30px;texte-algin:center;border:1px solid #4778c7;color:#4778c7;font-size:12px;display:block;-webkit-border-radius:5px;border-radius:5px;-moz-border-radius:5px;float:right;">保存</a>
		<a href="javascript:void(0);" class="fl" onclick="clearUeditor();" style="width:75px;margin-right:15px;height:30px;line-height:30px;texte-algin:center;border:1px solid #4778c7;color:#4778c7;font-size:12px;display:block;-webkit-border-radius:5px;border-radius:5px;-moz-border-radius:5px;float:right;">清空</a>
	</div>
	<div class="clear"></div>
</div>
	<script type="text/javascript">
        //new create
        $(document).ready(function(){
            $('#lessonType').change(function(){
                var selectValue=$("#lessonType").val();
                if(selectValue==1){
                    $("#span_ms").hide();
                }else{
                    $("#cv_set_type").val(1);
                    $("#span_ms").show();
                }
            });
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
         if (allUid != "" && proId != '') {
            //alert(allUid) ;
            var refUrl = "${ctx}/CVSetManage.do?mode=saveScheduleSequence" ;
            var refParams = "cvSetId=" + proId + "&scheduleIds=" + allUid ;
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

    //YHQ，2017-05-15，begin        
    $("#bookDiv").on("click",".bookImgDel",function(){
       if ($(".bookImgDel").length >= 1) {
          $(this).parent().remove() ;
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
					 + '<span class="bookImgDel" style="width: 32px;height: 32px;margin-left: 5px;"><img style="width:20px;height:20px;" src="${ctx}/peixun_page/images/round_remove.png"/></span>'
					 + '</span>' ;
	   $("#bookDiv").append(addNewStr) ;	     
	   var bnObj = $(".bookNumer") ;
       for (var itemXi = 0 ;itemXi < bnObj.length ; itemXi++) {
          bnObj[itemXi].innerHTML = (itemXi+1) + '.' ;
       }     
    });
    
    $("#knowledgeBaseDiv").on("click",".knowledgeBaseImgDel",function(){
       if ($(".knowledgeBaseImgDel").length >= 1) {
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
					 + '<span class="knowledgeBaseImgDel" style="width: 32px;height: 32px;margin-left: 5px;"><img style="width:20px;height:20px;" src="${ctx}/peixun_page/images/round_remove.png"/></span>'
					 + '</span>' ;
	   $("#knowledgeBaseDiv").append(addNewStr) ;	     
	   var bnObj = $(".knowledgeBaseNumer") ;
       for (var itemXi = 0 ;itemXi < bnObj.length ; itemXi++) {
          bnObj[itemXi].innerHTML = (itemXi+1) + '.' ;
       }     
    });
    
    $("#referenceDiv").on("click",".referenceImgDel",function(){
       if ($(".referenceImgDel").length >= 1) {
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
					 + '<span class="referenceImgDel" style="width: 32px;height: 32px;margin-left: 5px;"><img style="width:20px;height:20px;" src="${ctx}/peixun_page/images/round_remove.png"/></span>'
					 + '</span>' ;
	   $("#referenceDiv").append(addNewStr) ;	     
	   var bnObj = $(".referenceNumer") ;
       for (var itemXi = 0 ;itemXi < bnObj.length ; itemXi++) {
          bnObj[itemXi].innerHTML = (itemXi+1) + '.' ;
       }     
    });    
    //YHQ，2017-05-15，over


//以下是ZJG自定义变量

var proId = "";

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
	//
		$(".next_step").click(function(){
			window.localStorage.removeItem("add_course");
			window.localStorage.removeItem("add_ability");
			window.localStorage.removeItem("my_course");
		});
	//选择目录弹出框
		$('#propNames01').click(function(){
			initPropList("学科", "${ctx}/propManage/getPropListByDirectAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
			$('.att_make01').show();
		});
		$('.cas_anniu_4,.bjt_kt').click(function(){
			$('.att_make01').hide();
		});	
	
	
		
	//下拉框
		select_init(); 
		

	//
		$(".table tbody tr").click(function(){
			window.localStorage.setItem("add_ability","1");
			$(".right_cont,.ability_area_2").show();
			$(".next_btn").show();
		});
		//选择目录弹出框
		
		$(".left_cont_v,.right_cont_v").height($(window).height() - 223);
		$(".right_cont_v").width($(window).width() - 340);
		$("#editor").css({"width":$(window).width() - 350 + "px",height:$(window).height() - 310 + "px"});


		$(".level_p p span,.level_p p i").click(function () {
			$(this).parent().parent().parent().hide().next(".ability_area").show();
		});
	
		$(".btn_back").click(function () {
			$(this).parent().parent().parent().hide().prev(".ability_area").show();
		}); 

	var win_w = parseInt($(window).width() / 3) + "px";
	var win_h = parseInt($(window).height() / 1.5) + "px";
	<%--
	增加滚动条
	position:absolute;height:80px;width:530px;overflow:auto;
	--%>
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
			'<div class="clear" id="teacherName" style="border: 1px solid #cfcfcf;min-height:100px;padding:10px;margin-top:10px;line-height:25px;position:absolute;height:80px;width:530px;overflow:auto;">' +
			
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
				//处理机构弹出框数据回显问题
				{
					var selstr = $('.org_btn').val();
					selarray = selstr.split(",");
					var newnarray = new Array();
					$(selarray).each(function(key, val){
						if (val != "") newnarray.push('<em onClick="javascript:delem1(this);" class="delem">' + val + '</em>');
					});
					if(newnarray.length>0)
						$('.teacher_Name').html(newnarray.toString()+",");
					else
						$('.teacher_Name').html("");
				}
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
		var layerTitle = "专家" ;
		var _this_id ;
		if(_this.prop("id") ==1){
			_this_id = $('input[name="manager"]');
			layerTitle = "项目负责人" ;
		} else if(_this.prop("id") ==2) {
			_this_id = $('input[name="lessonTeacher"]');
			layerTitle = "授课教师" ;
		}else if(_this.prop("id") ==3) {
			_this_id = $('input[name="generalTeacher"]');
			layerTitle = "示教教师" ;
		}
		
		var currValTmp = '' ;		
		
		layer.open({
			type: 1,
			title: layerTitle,
			skin: 'layui-layer-rim', //加上边框
			area: ["600px", "400px"], //宽高
			content: teacher_cont,
			closeBtn: 0,
			btn: ['确定', '取消'],
			yes: function (index, layero) {			    
			    if (StringHaveRepeat($('#managerId').val())) {
			          alert('已选人员有重复，请重新选择！') ;
			          return ;
			    }
			    _this_id.val($('#managerId').val());
			    _this.val($('.teacher_Name').text().slice(0, -1));
			    // document.getElementById('bgManager').innerHTML = _this.val() ;
								
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
		
					/**
					程宏业
					2017-02-16
					*/
					// 更改下拉列表框的为单选按钮
					var str = ''; str2 = '';
					$('.chk_teacher:checked').each(function(){
						str += '<em onClick="javascript:delem1(this);" class="delem">' + $(this).prop("name") + "," + '</em>'; 
						str2 += $(this).val()+",";						
					});		
					$('.teacher_Name').html($('.teacher_Name').html() + str);	
					$('#managerId').val($('#managerId').val()+str2);
					$('.chk_teacher:checked').each(function(){
						$(this).prop("checked", false);
					});
				});
				$('#teacher').keyup(function(e){
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
					
					//var urll=encodeURI('${ctx}/expert/ExpertManage.do?mode=search&name='+$('#teacher').val()); YHQ-2017-08-23
					$.ajax({
						type:'post',
						url:'${ctx}/expert/ExpertManage.do',
						data:'mode=search&name=' + keyword+'&lockState=1' ,
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

// 	var win_w = parseInt($(window).width() / 2.4) + "px";
// 	var win_h = parseInt($(window).height() / 1.8) + "px";
	var win_w = "60%";
	var win_h = "60%";
	
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
				'<span style="width:5em;text-align:right;white-space: nowrap;">人物类型：</span>'+
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
				'<span style="width:5em;text-align:right;white-space: nowrap;">人物画像：</span>' +
				
				'<div class="clear"  style="border: 1px solid #cfcfcf;height:100px;padding:10px;margin-top:10px;line-height:25px; overflow: auto;">' +
				
				'<div id="str_user_image"></div>'+
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
				var _teacherNameStr = 	$('.teacher_Name').html();//回显的内容			
					var k='';
					if($('#xueke').val() != ""){
						k=$('#xueke').val().split(",");
					}else if(_teacherNameStr != ""){//如果用户没有重新选择则进入回显内容判断
						k=_teacherNameStr.split(",");
						var newnarray = new Array();
						$(k).each(function(key, val){
							if (val != ""){
							 	var ts = val.split(">")[1].split("<")[0];
							 	newnarray.push(ts.substring(0, 5));
							 }
						});
						k = newnarray;	
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
				var dotexp = /,/g;
				//处理弹出框数据回显问题
				{
					var selstr = $('.portraits_input').val();
					selarray = selstr.split(",");
					var newnarray = new Array();
					$(selarray).each(function(key, val){
						if (val != "") newnarray.push('<em onClick="javascript:delem1(this);" class="delem">' + val + '</em>');
					});
					if(newnarray.length>0)
						$('.teacher_Name').html(newnarray.toString()+",");
					else
						$('.teacher_Name').html("");
				}
							
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
	/***
	 * 步骤一数据校验
	 */ 
	function step1_check(){
        //进行表单验证
        if($('#name').val() == "" ){
            alert("请输入项目名称！");
            $('#name').focus();
            return ;
        }
        if($("#lessonType").val() == 0){
            alert("请输入项目授课形式！");
            $('#name').focus();
            return ;
        }
        if($('.portraits_input').val() == ""){
            alert("请输入人物画像！");
            $('.portraits_input').focus();
            return ;
        }
        if($('#1').val() == ""){
            alert("请选择项目负责人！");
            $('#1').focus();
            return ;
        }
        if($('#2').val() == ""){
            alert("请选择授课教师！");
            $('#2').focus();
            return ;
        }
        
        if($('.org_btn').val() == ""){
            alert("请选择项目归属机构！");
            $('.org_btn').focus();
            return ;
        }
        if($('#matFile').val() == ""){
            alert("请选择项目封面！");
            $('#matFile').focus();
            return ;
        }
        if($('#needed').val() == ""){
            alert("请输入学习须知！");
            $('#needed').focus();
            return ;
        }
        //判断上传文件
        if (!fileUploadValid("matFile",2)) {
            return ;
        }
        if($('#introduction').val() == ""){
            alert("请输入项目简介！");
            $('#introduction').focus();
            return ;
        }



        //YHQ，2017-05-16
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
	}
	
	/*
	 *@auth ZJG
	 *@time  2016-12-27
	 *方法说明   点击下一步 并通过AJAX方式保存表单信息
	 */
	function show_div(){

        if($('#name').val() == "" ){
            alert("请输入项目名称！");
            $('#name').focus();
            return ;
        }
        if($("#lessonType").val() == 0){
            alert("请输入项目授课形式！");
            $('#name').focus();
            return ;
        }
        if($('.portraits_input').val() == ""){
            alert("请输入人物画像！");
            $('.portraits_input').focus();
            return ;
        }
        if($('#1').val() == ""){
            alert("请选择项目负责人！");
            $('#1').focus();
            return ;
        }
        if($('#2').val() == ""){
            alert("请选择授课教师！");
            $('#2').focus();
            return ;
        }

        if($('.org_btn').val() == ""){
            alert("请选择项目归属机构！");
            $('.org_btn').focus();
            return ;
        }

        if($('#introduction').val() == ""){
            alert("请输入项目简介！");
            $('#introduction').focus();
            return ;
        }
        if($('#matFile').val() == ""){
            alert("请选择项目封面！");
            $('#matFile').focus();
            return ;
        }
        if($('#needed').val() == ""){
            alert("请输入学习须知！");
            $('#needed').focus();
            return ;
        }
        //判断上传文件
        if (!fileUploadValid("matFile",2)) {
            return ;
        }


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
        //YHQ，2017-05-16
        var bookNameListObj = $("input[name='book_name']") ;
        var bookUrlListObj  = $("input[name='book_url']") ;
        var kbNameListObj   = $("input[name='knowledgebase_name']") ;
        var kbUrlListObj    = $("input[name='knowledgebase_url']") ;
        var rcNameListObj   = $("input[name='reference_name']") ;
        var rcUrlListObj    = $("input[name='reference_url']") ;
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

        //a-----
        //判断是否提交过了
                    if(isSubmited("frm_add")){
                        alert("已经提交了，请勿重复提交！");
                        return ;
                    }
        var chooseSourseBookID=$("#chooseSourseBookID").val();
        var chooseSourseKnowledgeID=$("#chooseSourseKnowledgeID").val();
        var chooseSourseReferenceID=$("#chooseSourseReferenceID").val();

        console.log("chooseSourseBookID="+chooseSourseBookID);
        console.log("chooseSourseKnowledgeID="+chooseSourseKnowledgeID);
        console.log("chooseSourseReferenceID="+chooseSourseReferenceID);
                    //设置已经提交
                    setFormSubmitedById("frm_add");

                    $("#frm_add").ajaxSubmit({
                        type: 'post',
                        url: '${ctx}/file/fileUpload.do',
                        success: function(data) {
                            try{
                                var v = JSON.parse(data).message;

                                if(v!="Fail"){
                                    $('#cover').val(v);

                                    $.ajax({
                                        url		:"${ctx}/CVSetManage.do?mode=CVS_add&chooseSourseBookID="+chooseSourseBookID+"&chooseSourseKnowledgeID="+chooseSourseKnowledgeID+"&chooseSourseReferenceID="+chooseSourseReferenceID,
                                        data	:$("#frm_add").serialize(),
                                        type	:'post',
                                        dataType:'json',
                                        success:function(data){
                                            var result = eval(data);
                                            if(result.message=='success'){
                                                proId = result.proId;
                                                $('#cvs_step2').show();
                                                $('#step2_next').show();
                                                $('#mainPage').hide();

                                                //1：远程：3：面授
                                                if($('#lessonType').val()==1){
                                                    $("#span_ms2").hide();
                                                    $("#step2_next").hide();
                                                }else{
                                                    $("#step2_submitAudit").hide();
                                                    $("#span_ms2").show();
                                                }


                                                var xiagnmuName=$('#name').val();
                                                $('#xiagnmuName').val(xiagnmuName);
                                                $('#xiagnmuName2').val(xiagnmuName);
                                                var shouKeXs=$('#lessonType').val();
                                                if(shouKeXs==1){
                                                    document.getElementById('bgForma').innerHTML="远程";
                                                }else if(shouKeXs==2){
                                                    document.getElementById('bgForma').innerHTML="远程+面授";
                                                }else if(shouKeXs==3){
                                                    document.getElementById('bgForma').innerHTML="面授";
                                                }
                                                var introduction=$('#introduction').val();
                                                document.getElementById('bgIntroduction').innerHTML=introduction;
                                                var renWuImage=$('#renWuImage').val();
                                                document.getElementById('bgImage').innerHTML=renWuImage;
                                                var renWuManager=$('#1').val();

                                                getXueKe();
                                                // getZhuanJia();

                                                var banciFlag = false ;
                                                var cvSetForma = $("#lessonType").val() ;
                                                if (cvSetForma == '2' || cvSetForma == '3') {//面授
                                                    banciFlag = true ;
                                                }
                                                if (banciFlag) $('#banci_btn').show();
                                                else $('#banci_btn').hide();

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

                                            }else{
                                                removeSubmited("frm_add");
                                                alert("项目保存失败.");
                                            }
                                        }
                                    });
                                }else{
                                    removeSubmited("frm_add");
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
//				}
	}
	
	function getCourse(){
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
		//$('#edit_project').show();
		
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
		var ids = '';	
		$('input[name="cv"]').each(function(){
			ids += $(this).val() + ",";
		});	
		
		//location.href = "${ctx}/groupManage/groupClassInfoManage.do?type=unionEdit2&ids="+ids+"&chekPointIds=''&pageType=project";
		window.open("${ctx}/groupManage/groupClassInfoManage.do?type=unionEdit2&ids="+ids+"&chekPointIds=''&pageType=project") ;
		
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
		//window.location.href = '${ctx}/CVSet/CVManage.do?mode=edit&id=' + $('.clone_btn').val();
		window.open('${ctx}/CVSet/CVManage.do?mode=edit&id=' + $('.clone_btn').val());
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
	        
	        delFromWhoObj = $('#xueke').val() ;
	        if (delFromWhoObj != undefined) {
	            i = $(obj).index();
	            deletecode = "";			
			    selstr = $('#xueke').val();			
			    selarray = selstr.split(",");
				newarray = new Array();
				$(selarray).each(function(key, val){
					if (key != i) newarray.push(val);
					else
						deletecode = val;
				});
				$('#xueke').val(newarray.toString());
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
	 //课程排期，cvsSchedule
	function cvsSchedule(){
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
		window.open("http://www.ncme.org.cn/qiantai/projectView.do?id=" + proId);
		--%>
		
		//window.open("${ctxQiantaiURL}/entityManage/entityView.do?type=play4View&paramType=project&id=" + proId);
		  window.open("${ctxQiantaiURL}/projectView.do?id=" + proId);
	}
	
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
	
	function display_union(obj){
		//$('#edit_project').show();
		var str ='';
		str += '<table id="table_'+ obj.id+'" class="mt10 table">'+
		'<tr align="center" valign="middle" id = "' + obj.id+'">' +
			'<th colspan="6">' +
				'<input type="radio" id="' + obj.id+'" name="cv" value="'+obj.id+'" /> 课程名称:' + obj.name + 
	         		'<button class="clone_btn tbn_blue fr" onClick="javascript:clone();" value="' + obj.id+'" type="button">课程信息</button>' +   			
	    		'</th></tr>'+
		'<tr class="tr"><th width="10%">序号</th><th width="40%">课题</th><th width="15%">类别</th><th width="10%">任务点</th><th width="13%">达标指标</th><th width="13%">完成状态</th></tr>';
		for(var i=0; i<obj.unitList.length;i++){
			str += '<tr align="center"  id="trId_' + obj.id + i  + '" class="xTrChangeColor0228"><td>' + obj.unitList[i].id +
				'</td><td id="tdId_'+ obj.id + i   + '"  onclick="javascript:show_quality_area(this);">' + obj.unitList[i].name+'</td><td>';
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
	
	function huiban(){
		$('#union').hide();
		$('#cvs_step2').show(); 
		$('.left_cont, .right_cont').show();
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
	    $('.xTrChangeColor0228').each(function (index,domEle){
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
			for (it in stock) 
				if (stock[it] == $(obj).val()) {
				   stock.splice(it, 1);
				   uncheckHT.add($(obj).val(), $(obj).val()) ;
				}
		}
	}
	
	function right_area_update(index, list, title, backId) {
		var new_html = '';
		if (list != '') {
			for (var i=0; i<list.length; i++) {
				var v = list[i].id + '_' + index ;//list[i].level;
				var checked = '';
				for (it in stock)
					if (stock[it].split("_")[0] == list[i].id) {checked=" checked "; break;}
				if (list[i].level == 1) {
				   stock.push(v) ;
				   checked=" checked ";
				}
				new_html += '<p class="fl" style="margin-right:20px;">';
				new_html += 	'<input id="' + v + '" name="chk_guide" onchange="javascript:update_stock(this);" type="checkbox"' + checked + 'class="fl" value="' + v + '"/> <span onclick="javascript:next_index(this);">' + list[i].name + '</span>'; 
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
	
	function next_index(obj) {
		var id_level = $(obj).prev().val();
		var tmp = id_level.split("_");
		var id = tmp[0];
		var level = tmp[1];
		if (level >= 4){
			return;
		} 
		
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
		params = 'mode=save_guide_ref&id=' + $(obj).val() + '&id_levels=' + params;
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
/***
 *
 * 提交项目审核
 * */
	function cvsSubmitAudit(){
	    var type=$('#lessonType').val();   //1：远程：3：面授
		var projectfrom=$("#projectfrom").val();
		var code=$('#code').val();
		var url = '${ctx}/CVSetManage.do';
		var params = 'mode=updateState&state=2&code=' + code;
		if(type==1){
            $.ajax({
                type:'post',
                url: url,
                data: params,
                dataType:'text',
                success:function(result){
                    if(result == 'success'){
                        alert('提交项目审核成功!');
                        if(projectfrom==1){
                            window.location.href = "${ctx}/CVSetManage.do?mode=myXiangMu";
                        }else {
                            window.location.href = "${ctx}/CVSetManage.do";
                        }
                    }else{
                        alert("提交项目审核失败!");
                    }
                }
            });
		}else{
			//1:先保存期数
            var errorNum=check_schedule(0);
            if(typeof errorNum=='undefined'||errorNum<=0){
                var projectfrom=$("#projectfrom").val();
                var refUrl = "${ctx}/CVSetManage.do?mode=saveCVSetScheduleFaceTeach&cvSetId=" + proId ;
                var refParams = $("#frm_banci").serialize() ;
                $.ajax({
                    url		:refUrl,
                    data	:refParams,
                    type	:'post',
                    dataType:'json',
                    async:false,
                    success:function(data1){
                        if (data1.result == 'success') {
                            alert('提交项目审核成功!');
                            $.ajax({
                                type:'post',
                                url: url,
                                data: params,
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
                        } else {
                            alert('提交项目审核失败！');
                        }
                    },
                    error:function(data2){
                        alert('保存期数安排失败：' + data2);
                    }
                });
                layer.close(banciLayerObj);
            }
		}

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
	
	<%--function getZhuanJia(){--%>
	    <%--var exIdList = $('#manager').val().split(",");--%>
	    <%--var bgUnitStrTmp = "";--%>
	    <%--var bgJobNameStrTmp = "";--%>
	    <%--var bgOfficeStrTmp = "";--%>
	    <%--var bgPropNamesStrTmp = "";--%>

	    <%--for (var itemXexId in exIdList) {--%>
	       <%--if ($.trim(exIdList[itemXexId]) != "") {--%>
	          <%--var params = 'mode=getZhuanJia&managerId=' + exIdList[itemXexId];//$('#xmmanager').val();--%>
				<%--$.ajax({--%>
					<%--url		:"${ctx}/expert/GetExpert.do",--%>
					<%--data	:params,--%>
					<%--type	:'post',--%>
					<%--dataType:'json',--%>
					<%--async:false,--%>
					<%--success:function(data){--%>
						<%--var bgUnit=data.result.workUnit;--%>
						<%--if (bgUnitStrTmp =="") bgUnitStrTmp = bgUnit ;--%>
						<%--else bgUnitStrTmp = bgUnitStrTmp + "，" + bgUnit ;--%>
						<%--//document.getElementById('bgUnit').innerHTML=bgUnit;--%>
						<%--var bgJobName=data.result.jobName;--%>
						<%--if (bgJobNameStrTmp =="") bgJobNameStrTmp = bgJobName ;--%>
						<%--else bgJobNameStrTmp = bgJobNameStrTmp + "，" + bgJobName ;--%>
						<%--//document.getElementById('bgJobName').innerHTML=bgJobName;--%>
						<%--var bgOffice=data.result.office;--%>
						<%--var bgoTmpVal = "" ;--%>
						<%--if(bgOffice==1){--%>
							<%--//document.getElementById('bgOffice').innerHTML="主任委员";--%>
							<%--bgoTmpVal ="主任委员";--%>
						<%--}else if(bgOffice==2){--%>
							<%--//document.getElementById('bgOffice').innerHTML="副主任委员";--%>
							<%--bgoTmpVal ="副主任委员";--%>
						<%--}else if(bgOffice==3){--%>
							<%--//document.getElementById('bgOffice').innerHTML="秘书长";--%>
							<%--bgoTmpVal ="秘书长";--%>
						<%--}else if(bgOffice==4){--%>
							<%--//document.getElementById('bgOffice').innerHTML="学组长";--%>
							<%--bgoTmpVal ="学组长";--%>
						<%--}else if(bgOffice==5){--%>
							<%--//document.getElementById('bgOffice').innerHTML="委员";--%>
							<%--bgoTmpVal ="委员";--%>
						<%--}--%>

						<%--if (bgOfficeStrTmp == "") bgOfficeStrTmp = bgoTmpVal ;--%>
						<%--else bgOfficeStrTmp = bgOfficeStrTmp + "，" + bgoTmpVal ;--%>

						<%--var bgPropNames=data.result.propNames;--%>
						<%--if (bgPropNamesStrTmp == "") bgPropNamesStrTmp = bgPropNames ;--%>
						<%--else bgPropNamesStrTmp = bgPropNamesStrTmp + "，" + bgPropNames ;--%>
						<%--//document.getElementById('bgPropNames').innerHTML=bgPropNames;--%>
					<%--}--%>
				<%--});--%>
	       <%--}--%>
	    <%--}--%>

	    <%--if (bgUnitStrTmp != "") document.getElementById('bgUnit').innerHTML = bgUnitStrTmp;--%>
		<%--if (bgJobNameStrTmp != "") document.getElementById('bgJobName').innerHTML = bgJobNameStrTmp;--%>
		<%--if (bgOfficeStrTmp != "") document.getElementById('bgOffice').innerHTML = bgOfficeStrTmp;--%>
		<%--if (bgPropNamesStrTmp != "") document.getElementById('bgPropNames').innerHTML = bgPropNamesStrTmp;--%>
	<%--}--%>


	function setFormSubmitedById(id){
		$("#"+id).attr("submited","true");
	}
	function isSubmited(id){
		return $("#"+id).attr("submited")=="true";
	}
	function removeSubmited(id){
		$("#"+id).removeAttr("submited");
	}
	




//来自：CVS_update
var banciLayerObj ;
var layerPx ;
var layerPy ;
function baociBTNclick() {
   $("#baociTable tbody").html("");
   
   var refUrl = "${ctx}/CVSetManage.do?mode=queryCVSetScheduleFaceTeachByCVsetId" ;
   var refParams = "cvSetId=" + proId ;
   $.ajax({
		url		:refUrl,
		data	:refParams,
		type	:'post',
		dataType:'json',
		async:false,
		success:function(data1){					
           if (data1.result.length != 0) {
				for (var bci = 0 ; bci < data1.result.length ; bci++) {
				     var trStr = '' ;
				     var bcObj = data1.result[bci] ;
				     var bcName = bcObj.class_name ;
				     var bcMan = bcObj.people_number ;
				     var bcDd = bcObj.train_place ;
				     var bcStartTime = bcObj.trainStartTime ;
				     var bcEndTime = bcObj.trainEndTime ;
				     var bcContactWay = bcObj.contact_way ;
				     var bcTime = '' ;
				     bcTime = bcStartTime + '--' + bcEndTime ;
				     
				     var randomNum = Math.floor(Math.random() * 10000 + 1 );
				     
				     var trIdJson = '{"class_name":"' + bcName + '","people_number":"' + bcMan + '","train_place":"' + bcDd + '","train_starttime":"' + bcStartTime 
				           + '","train_endtime":"' + bcEndTime + '","contact_way":"' + bcContactWay + '","id":"' + randomNum + '"}' ;
				     
				     trStr += '<tr id=\'' + trIdJson + '\'>'
				           + '<td><input type="checkbox" id="baociCHK"/></td>' 
				           + '<td class="bcIdClass">1</td>'
						   + '<td>' + bcMan + '<input type="hidden" name="people_number" value="' + bcMan + '"/></td>'
						   + '<td>' + bcDd + '<input type="hidden" name="train_place" value="' + bcDd + '"/></td>'
						   + '<td>' + bcName + '<input type="hidden" name="class_name" value="' + bcName + '"/></td>'
				           + '<td>' + bcTime + '<input type="hidden" name="train_starttime" value="' + bcStartTime + '"/>'
				           + '<input type="hidden" name="train_endtime" value="' + bcEndTime + '"/>'
				           + '</td>'
				           + '<td>' + bcContactWay + '<input type="hidden" name="contact_way" value="' + bcContactWay + '"/></td>'
				           + '<td><a href="javascript:modifyBanci(\'' + randomNum + '\');">编辑</a>  <a href="javascript:delBanci(\'' + randomNum + '\');">删除</a></td>'
				           + '</tr>'	            
				           ;
				     $("#baociTable").find("tbody").append(trStr) ;
	     
				}
				banciOrder() ;
		   }
		},
        error:function(data2){                            
           alert('加载期数安排失败：' + data2);
        }
   });
   
   layerPx = $(window).height() / 3 ; // Math.random()*($(window).height()-300)
   layerPy = $(window).width() / 3 ;
   
    
   banciLayerObj = layer.open({
	  type: 1,
	  title: "期数安排",
	  skin: 'layui-layer-rim', //加上边框
	  area: [win_w, 466], //宽高	
	  offset: [ //坐标
	    layerPx,layerPy
	  ],
	  closeBtn: 0,  
	  content: $('#baociDiv')
	});
	
	$("#baociTable").find("thead").show() ;
    $("#baociTable").find("tbody").show() ;   
}
	
function banciFinish() {
//检查排期录入数据
    var errorNum=check_schedule(0);
    if(typeof errorNum=='undefined'||errorNum<=0){
       var projectfrom=$("#projectfrom").val();
	   var refUrl = "${ctx}/CVSetManage.do?mode=saveCVSetScheduleFaceTeach&cvSetId=" + proId ;
	   var refParams = $("#frm_banci").serialize() ;
	   $.ajax({
			url		:refUrl,
			data	:refParams,
			type	:'post',
			dataType:'json',
			async:false,
			success:function(data1){
			   if (data1.result == 'success') {
				   alert('保存期数安排成功!');
				   if(projectfrom==1){
					   window.location.href = "${ctx}/CVSetManage.do?mode=myXiangMu";
				   }else {
					   window.location.href = "${ctx}/CVSetManage.do";
				   }
			   } else {
				   alert('保存期数安排失败！');
			   }
			},
			error:function(data2){
			  alert('保存期数安排失败：' + data2);
			}
	   });
	   layer.close(banciLayerObj);
	}
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
	  title: '添加培训期数',
	  skin: 'layui-layer-rim', //加上边框
	  area: [win_w, 500], //宽高
	  offset: [ //坐标
	    layerPx,layerPy
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
		  area: [win_w, 400], //宽高
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
/**
 * 选择来源
 * */
function addSource(soure_type){
    console.log("soure_type="+soure_type);
    var chooseSourseBookID=$("#chooseSourseBookID").val();
    var chooseSourseKnowledgeID=$("#chooseSourseKnowledgeID").val();
    var chooseSourseReferenceID=$("#chooseSourseReferenceID").val();
    layer.open({
        type: 2,
        title: "选择来源",
        skin: 'layui-layer-rim', //加上边框
        area: ['70%', '90%'], //宽高
        content: "${ctx}/propManage/sourceList.do?chooseSourseBookID="+chooseSourseBookID
		+"&chooseSourseKnowledgeID="+chooseSourseKnowledgeID
		+"&chooseSourseReferenceID="+chooseSourseReferenceID
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
</script>
<script type="text/javascript" src="${ctx}/js/movediv.js"></script>
</body>
</html>