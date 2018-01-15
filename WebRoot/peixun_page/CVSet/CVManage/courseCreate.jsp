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
		<script type="text/javascript" src="${ctx}/peixun_page/js/utils.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/fileUpload.js"></script>
	</head>
<body >
<div class="center"  style="">
	
	<div class="center01" id ="addwindow">
	<form id="sfrm" name="cvForm" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="id" id="newCourseId"/>
		<div class="tiaojian" style="min-height:40px;">
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 课程名称：</span>
				<input type="text" name="name" id="name" value="" />
			</p>
			<div class="clear">
				<p class="fl" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 授课形式：</span>
				</p>
				<div class="fl select" style="margin:0 20px 20px 0;">
					<div class="tik_position">
						<b class="ml5">点播</b>
						<p class="position01"><i class="bjt10"></i></p>
					</div>
					<ul class="fl list" id="_cvType" style="display: none;">
						<select name = "cv_type" id="cv_type" style="display:none">
							<option value="0" selected="selected">点播</option>
							<option value="1">面授</option>
							<option value="2">直播</option>
						</select>

						<li>点播</li>
						<li>面授</li>
						<li>直播</li>
					</ul>
				</div>
			</div>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 学科：</span>
			</p>
				<input type="hidden" name="propIds" id="propIds" value="${propIds}"/>
				<input type="hidden" name="propNames" id="propNames" value="${propNames}"/>
				<div class="duouan" id="propNames01">${propNames}</div> 
			<div class="clear"></div>

			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 课程编号：</span>
				<input type="text" name="serial_number" id="serial_number" value="${serial_number}" readonly/>
			</p>
			<div id="db_label" style="display:block;">
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 课程标签：</span>
				<span style="text-align:left;">
					<input type="checkbox" name="brand_c" value="1"  <c:if test="${fn:contains(info.brand, 1)}">checked="checked"</c:if>/> 病例
					<input type="checkbox" name="brand_c" value="3"  <c:if test="${fn:contains(info.brand, 3)}">checked="checked"</c:if>/> VR
					<input type="checkbox" name="brand_c" value="4"  <c:if test="${fn:contains(info.brand, 4)}">checked="checked"</c:if>/> 名师课程
					<input type="checkbox" name="brand_c" value="5"  <c:if test="${fn:contains(info.brand, 5)}">checked="checked"</c:if>/> 三维动画
					<input type="checkbox" name="brand_c" value="6"  <c:if test="${fn:contains(info.brand, 6)}">checked="checked"</c:if>/> 其他
					<input type="hidden" id="brand" name="brand"/>
				</span>
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
					<input type="text" name="cv_address" id="cv_address"  value="" />
				</p>
			</div>

			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 授课教师：</span>
				<input type="text" name="teacher" id="teacher" class="teacher_1" value="${teahcer}" /> 
				<input type="hidden" name="teacherIds" id="teacherIds" value=""/>
			</p>
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"> 示教教师：</span>
				<input type="text" name="otherTeacher" class="teacher_2" value="" />
				<input type="hidden" name="otherTeacherIds" id="otherTeacherIds" value=""/>
			</p>
			<div id="db_out_chain" style="display:block;">
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em>添加外部链接：</span>
					<span style="width:9em;text-align:left;">
						<input  name="is_add_out_chain" type="radio" value="0" checked/>否
						<input name="is_add_out_chain" type="radio" value="1" />是
					</span>
				</p>
				<p id="p_cv_url" class="clear" style="margin-bottom:20px;display: none">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em>课程网址：</span>
					<input type="text" name="cv_url" id="cv_url"  value="" />
				</p>
			</div>
			<!-- 直播参数部分start -->
			<div style="display:none" id="zhibo">
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> SDK  ID：</span>
					<input type="text" name="class_no" id="class_no" class="class_no" />
				</p>
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 开始时间：</span>
					<input type="text" name="start_date" id="start_date" class="editInput"
				datatype="*" nullmsg="请选择授权开始时间！" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'invalid_date\')}'})"  />
				</p>
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 失效时间：</span>
					<input type="text" name="invalid_date" id="invalid_date" class="editInput"
				datatype="*" nullmsg="请选择授权开始时间！" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'start_date\')}'})" />
				</p>
				<!-- <p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 教师加入URL：</span>
					<input type="text" name="teacher_url" class="teacher_url"  />
				</p> -->
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 学生加入URL：</span>
					<input type="text" name="student_url" id="student_url" class="student_url" />
				</p>
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 是否需要报名：</span>
					<span style="text-align:left;">
						<input  name="is_need_apply" type="radio" value="0" checked/>否
						<input name="is_need_apply" type="radio" value="1" />是
					</span>
						<input type="text" name="apply_num" id="apply_num" disabled="disabled" style="width:40px;"/>
				</p>
				<!-- <p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 老师口令：</span>
					<input type="text" name="teacher_token" class="teacher_token" />
				</p>
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 助教口令：</span>
					<input type="text" name="assistant_token" class="assistant_token" />
				</p> -->
				<p class="clear" style="margin-bottom:20px;">
					<span style="width:9em;text-align:right;"><em class="red_start">*</em> 学生口令：</span>
					<input type="text" name="student_token" id="student_token" class="student_token" />
				</p>
			</div>
			<!-- 直播参数部分end -->
			<p class="clear" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 课程简介：</span>
				<textarea  name="introduction" id="introduction" style="width:400px;" rows="5"></textarea>
			</p>
			<p class="fl" style="margin-bottom:20px;width:526px">
			<%-- 
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 课程封面：</span>
			--%>
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 课程封面：</span>
				<input type="file" name="matFile" id="matFile" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp">
				<input type="hidden" name="file_path" id="file_path">
				<span>支持jpg，png，jpeg，bmp，gif格式，不超过10M</span>
			</p>
			<div class="clear"></div>
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;">课程声明：</span>
				<textarea  name="announcement" id="announcement" style="width:400px;" rows="5"></textarea>
			</p>
			<div class="clear"></div>
			<p class="fl cas_anniu" style="margin-left:9.1em;">
				<a href="#" class="fl next_step" style="width:140px;margin-left:10px;">下一步</a>
			</p>
			<p class="fl cas_anniu">
				<a href="javascript:window.history.go(-1);" class="fr" style="width:70px;margin-left:10px;">返回</a>
			</p>
		</div>
	  </form>
	  <div class="clear"></div>
	</div>
<div id="next_page" style="display:none;">
<div class="top_cont">	
	<h2><input  id="keChengName" style="border-style:none;" readonly/></h2>
</div>
<div class="sub_nav">
	<!--<span class="btn_blue fr btn_no btn_save"><a href="javascript:saveCourse()">保存</a></span>-->
	<span class="btn_blue fr" ><a href="javascript:void(0)"  id="showAddwindow">返回</a></span>
	<span class="btn_blue fr" ><a href="#" onclick="save();" id="">保存</a></span>
	<span class="add_course btn_no"><a href="javascript:void(0);">添加单元</a></span>
	<span class="btn_no"><a href="javascript:deleteUnit();">删除单元</a></span>
	<span class="btn_no"><a href="javascript:to_up();">上移</a></span>
	<span class="btn_no"><a href="javascript:to_down();">下移</a></span>
	<span class="btn_blue next_btn" ><a href="javascript:void(0);" onclick="edit();">编辑</a></span>
</div>
<div class="center" id="addcontent">
	<div class="center01">
		<button class="btn_blue clone_course btn_no" type="button" >克隆课程</button>
		<div class="clear" style="margin-bottom:20px;"></div>
		<div class="left_cont">
			<h2 class="catalog">目录</h2>
			<form id="createUnionForm" name="cvUnitForm" method="POST">
			<div class="add_union" style="display:none;padding:20px;">
				<div class="tiaojian" >
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
					<p><span style="width:6em;">&nbsp;</span><input type="checkbox" name="point" id="point" />任务点 </p>
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
					<th width="8%">任务点</th>
					<th width="11%">达标指标</th>
					<th width="11%">完成状态</th>
				</tr>
				<tr align="center" valign="middle" id = "initTr">
	                <td colspan="6" id="unit_td">--请添加单元--</td>
	            </tr>
			</table>	
	    </div>
		<div class="clear"></div>
	</div>
</div>
</div>
</div>
<div id="container"></div>
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

    //以下是ZJG自定义变量

	//保存课程成功后的id
	var classId = ""; 

	$(function(){

			var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
			var submenu = "lc_left0" + menuindex;
			$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
			$('.'+submenu+'').show();
			$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
			
			
			/*
			 *@auth    ZJG
			 *@time    2016-12-27
			 *方法说明   点击下一步 通过AJAX方式提交课程信息并编辑单元
			 */
			$('.next_step').click(function () {
                var _ct = $("#cv_type").val();
                var _ctval = $("#cv_type option:selected").text();
				var spCodesTemp = "";
			    $('input:checkbox[name=brand_c]:checked').each(function(i){
			     if(0==i){
			      spCodesTemp = $(this).val();
			     }else{
			      spCodesTemp += (","+$(this).val());
			     }
			    });
			    $("#brand").val(spCodesTemp);
				//表单验证
				/* */
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
                if($('#brand').val() == "" && !(_ct == 2 || _ctval == "直播")){
                    alert("请选择课程标签！");
                    return ;
                }
                if($('#teacher').val() == ""){
					alert("请选择授课教师！");
					return ;
				}
				console.log("ADDD==="+$("input[name='is_add_out_chain']:checked").val());

                if($("input[name='is_add_out_chain']:checked").val()==1&&$("#cv_url").val()==''){
                    alert("请输入课程网址！");
                    $('#cv_url').focus();
                    return ;
                }
				//添加直播时信息校验

				if(_ct == 2 || _ctval == "直播"){
					if($('#class_no').val() == ""){
						alert("请输入课堂SDK ID！");
                        $('#class_no').focus();
						return ;
					}
					if($('#start_date').val() == ""){
						alert("请输入开始时间！");
                        $('#start_date').focus();
						return ;
					}
					if($('#invalid_date').val() == ""){
						alert("请输入结束时间！");
                        $('#invalid_date').focus();
						return ;
					}
					if($('#student_url').val() == ""){
						alert("请输入学生URL！");
                        $('#student_url').focus();
						return ;
					}
                    if($("input[name='is_need_apply']:checked").val()==1&&$("#apply_num").val()==''){
                        alert("请输入报名人数！");
                        $('#apply_num').focus();
                        return ;
                    }
					if($('#student_token').val() == ""){
						alert("请输入学生口令！");
                        $('#student_token').focus();
						return ;
					}
					 $("#brand").val(6);

				}
				if($('#introduction').val() == ""){
					alert("请输入课程简介！");
					$('#introduction').focus();
					return ;	
				}				
				if($('#matFile').val() == ""){
					alert("请选择课程封面！");
					$('#matFile').focus();
					return ;
				}
				//长度校验：
				if($('#name').val().allLength() > 100 ){
					alert("课程名称最多字数50字，您已经超过字数，请修改！");
					$('#name').focus();
					return ;
				}
				if($('#introduction').val().allLength() > 6000 ){
					alert("课程简介最多字数3000字，您已经超过字数，请修改！");
					$('#introduction').focus();
					return ;
				}
				if($('#announcement').val().allLength() > 6000 ){
					alert("课程声明最多字数3000字，您已经超过字数，请修改！");
					$('#announcement').focus();
					return ;
				}
				
				//通过AJAX方式提交课程信息
				 if($("input[name='point']:checked")){
					 $('#point').val(1);
				 }else{
					 $('#point').val(0);
				 }
				 if($("input[name='state']:checked")){
					 $('#state').val(1);
				 }else{
					 $('#state').val(0);
				 } 
		        //判断上传文件
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
			            			    if(classId){
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
														var select_add_out_chain =$("input[name='is_add_out_chain']:checked").val();
														//0 点播 1 面授 2 直播
														if(_ct == 1 || _ctval == "面授"){
															saveZhibo();
														}else if((_ct == 0 || _ctval == "点播")&&(select_add_out_chain==1)){
                                                            saveZhibo();
														}else{
					            							//控制显示隐藏
					            							$('#addwindow').hide();	
					            							$('#next_page').show();	
					            							//返回已经保存的课程id
					            							var keChengName=$('#name').val();
					            							$('#keChengName').val(keChengName);
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
				            							
				            						}else if(result.message = "errortime"){
				            							alert("请正确录入直播时间！");
				            							return;
				            						}else if(result.message = "errorforzhiboadd"){
				            							alert("直播信息操作异常，请重新添加课程！");
				            							return;
				            						}				
				            					 }
				            				});
			            			    }else{
			            			    	$.ajax({
				            					url 	: "${ctx}/CVSet/CVManage.do?mode=save",
				            					data 	: $("#sfrm").serialize(),
				            					type	: 'post',
				            					dataType: 'json',
				            					success : function(data) {
				            						var result = eval(data);
				            						if(result.message = "success"){
				            							//如果为直播课程则直接保存，不需要下一步添加单元等信息
				            							var _ct = $("#cv_type").val();
														var _ctval = $("#cv_type option:selected").text();
                                                        var select_add_out_chain =$("input[name='is_add_out_chain']:checked").val();
														if(_ct == 2 || _ctval == "直播"){
															saveZhibo();
														}else if((_ct == 0 || _ctval == "点播")&&(select_add_out_chain==1)){
                                                            saveZhibo();
                                                        }else{
					            							//控制显示隐藏
					            							$('#addwindow').hide();	
					            							$('#next_page').show();	
					            							//返回已经保存的课程id
					            							classId = result.id;
					            							$("#newCourseId").val(classId);
					            							var keChengName=$('#name').val();
					            							$('#keChengName').val(keChengName);
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
				            						else if(result.message = "errortime"){
				            							alert("请正确录入直播时间！");
				            							return;
				            						}else if(result.message = "errorforzhiboadd"){
				            							alert("直播信息操作异常，请重新添加课程！");
				            							return;
				            						}			
				            					 }
				            				});
			            			    }
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
			     
			});
			
			select_init();
			
			$('#showAddwindow').click(function(){
				location.href="${ctx}/CVSet/CVManage.do?mode=list";
				//$('#next_page').hide();
				//$('#addwindow').show();
			});
			
			$('#propNames01').click(function(){
				initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
				$('.att_make01').show();
			});
			
			$('.cas_anniu_4,.bjt_kt').click(function(){
				$('.att_make01').hide();
			});
			var obj = $('input[name="cv"]');
			if (obj.prop("id")!=null){
				$(".table,.next_btn").show();
			}
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
			$(".right_cont").hide();
		});
		
		/*
		 *@auth   ZJG
		 *@time   2016-12-27
		 *方法说明  保存单个添加的单元信息
	     */
		$(".btn_confirm").click(function(){	
			if($('#point')[0].checked){
	            $('#point').val(1);
	        }else{
	        	$('#point').val(0);
	        } 
			$.ajax({
				url		:'${ctx}/CVSet/CVUnitAdd.do',
				data	:'mode=addUnion&point='+$('#point').val()+'&type='+$('#sel_point').val()+'&name='+$('#unitName').val(),
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
			today:false,
			clear:false,
			dateMax : true,
			dateMin : [2010, 1, 1],
			yearSelector: 100
		});
		var calendar = input.data( 'pickadate' ); */ 
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
        //0 点播 1 面授 2 直播
		//设置当选择直播课程时情况
		$('#_cvType li').click(function(){
			var _ct = $("#cv_type").val();
			var _ctval = $("#cv_type option:selected").text();
			if(_ct == 2 || _ctval == "直播"){
				$('#db_label').hide();
				$('#db_out_chain').hide();
				$('#ms_label').hide();
				$('#zhibo').show();
				$('.next_step').html("保存");
			}else if(_ct == 1 || _ctval == "面授"){
				$('#db_label').hide();
				$('#db_out_chain').hide();
				$('#zhibo').hide();
				$('#ms_label').show();
				$('.next_step').html("保存");
			}else{
				$('#db_label').show();
				$('#db_out_chain').show();
				$('#zhibo').hide();
				$('#ms_label').hide();
                $('.next_step').html("下一步");
			}
        })
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

	/*
	 *@auth    ZJG
	 *@time    2016-12-27
	 *方法说明    保存课程信息
	 */
    function saveCourse(){
		 if($("input[name='point']:checked")){
			 $('#point').val(1);
		 }else{ 
	     	 $('#point').val(0);
	     }
		 if($("input[name='state']:checked")){
			 $('#state').val(1);
		 }else{
	     		$('#state').val(0); 
	     }
		 document.getElementById("sfrm").action = "${ctx}/CVSet/CVManage.do?mode=save";
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

	function refresh_left_cont(unitList) {
		$("#initTr").remove();
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
		        
		        //达标指标 
		        html.push("<td width = '7.5%'>"+value.quota+"</td>");
		        
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
	  if(confirm("确认删除单元？")){
		var obj = $('input[name="cv"]:checked');
		if(obj == undefined  || obj.val() == undefined){
			alert("请选择要删除的单元！");
			return ;
		}
		var unitId = obj.val();
		if (classId != '') {
		   var refUrl2 = "${ctx}/CVSet/CVManage.do" ;
		   var refParams2 = "mode=delCvUnit&cvId=" + classId + "&unitId=" + unitId ;
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
		}
	  }			
	}
	
	function to_up() {
		var obj = $('input[name="cv"]:checked');
		if(obj == undefined){
			alert("请选择单元！");
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
		}
		var upObj = obj.parent().parent();
		if (upObj.index() != $('input[name="cv"]').length) {
	        upObj.next().after(upObj);
	    }	    
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
					document.cookie = "radio=" + escape(rowNum);				
			}
		});
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
				
				},
			});
	}
	
	/*
	 *@auth    ZJG
	 *@time    2016-12-27
	 *方法说明   点击编辑跳转组课页面
	 */
	function edit(){
		//原来编辑内容
		//${ctx}/CVSet/CVUnitAdd.do?mode=unionEdit
		//判断是否保存
			if(classId!=''){
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
			    //location.href = "${ctx}/groupManage/groupClassInfoManage.do?type=unionEdit2&ids="+classId+"&chekPointIds="+chekPointIds+"&pageType=class";
			    window.open("${ctx}/groupManage/groupClassInfoManage.do?type=unionEdit2&ids="+classId+"&chekPointIds="+chekPointIds+"&pageType=class");
			}
	}
	
	function save(){
		alert("保存成功");
	};
	
	//直播课程成功处理
	function saveZhibo(){
		alert("保存成功");
		window.location.href = "${ctx}/CVSet/CVManage.do?method=list";
	};
</script>
<script type="text/javascript" src="${ctx}/js/movediv.js"></script>
</body>
</html>