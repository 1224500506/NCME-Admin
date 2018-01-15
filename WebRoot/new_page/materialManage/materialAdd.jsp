<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.bokecc.config.Config"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.bokecc.util.DemoUtil"%>
<%@page import="com.bokecc.util.APIServiceFunction"%>
<%@page import="org.dom4j.*"%>
<%@page import="java.util.Map" %>
<%@page import="java.util.HashMap" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/taglibs.jsp"%>
<title>资源管理平台</title>
<style type="text/css">
.fla_btn {
	position: relative;
}

.fla_btn embed {
	position: absolute;
	z-index: 1;
}
#swfDiv{*position:absolute; z-index:2;}
</style>
	<%@include file="/new_page/top.jsp"%>
	<script type="text/javascript" src="${ctx}/new_page/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/new_page/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/new_page/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/js/swfobject.js"></script>
<script type="text/javascript" src="${ctx}/new_page/js/fileUpload.js"></script>
</head>
<body>
<!-- 素材管理（开始） -->
	<div class="center">
		<div class="tk_tk_xuanxiag" style="">
			<div class="xingzeng_k">
				<i></i><h2 class="fl"><c:choose><c:when test="${info.id!=null}">修改</c:when><c:otherwise>新增</c:otherwise></c:choose>素材</h2>
			</div>
			<form id="frm" action="${ctx}/materialn/MaterialnManage.do?mode=save" method="POST" enctype="multipart/form-data">
			<div class="thi_shitineirong" style="">
				<ul>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr"><em>*</em>素材类型：</span></p>
								<select name="type" id="type" style="width:260px;height:30px;border:1px solid #dddddd;" onchange="selectType();">
									<option value="">请选择</option>
									<option value="1"<c:if test="${info.type==1}"> selected</c:if>>视频</option>
									<option value="2"<c:if test="${info.type==2}"> selected</c:if>>图片</option>
									<option value="3"<c:if test="${info.type==3}"> selected</c:if>>PPT</option>
									<option value="4"<c:if test="${info.type==4}"> selected</c:if>>文本</option>
									<option value="5"<c:if test="${info.type==5}"> selected</c:if>>压缩包</option>
								</select>
			   			</div>
						<div class="fl shitin_xinzeng">
						   <input name="id" type="hidden" value="${info.id}"/>
						   <div class="fla_btn" id="videoFile" <c:if test="${info.type!=1}">style="display:none;"</c:if>>
						     <span style="float:left;width:130px;text-align:right;font-size:14px;height:30px;line-height:30px;margin-left:30px;color:#333;"><span style="color:red;"><em>*</em></span>素材名称：</span>
						   	 <input id="videofile" name="fileName" type="text" value="${info.name}" style="width:258px;height:30px;border:1px solid #dddddd;float:left;"/>
						   	 <div id="swfDiv" style="float:left;width:40px;height:30px;margin-left:-40px;opacity:0;"></div>
						   	 <input type="button" value="上传" id="btn_width" style="width: 40px; height:32px;margin-left:-40px;background:#feaf27;color:#ffffff;font-size:12px;border:none;z-index:999;float:left;"/>
						   </div>
						   <div id="otherFile" <c:if test="${info.type==1}">style="display:none;"</c:if>>
							<p class="fl ml30"><span class="fr"><em>*</em>素材名称：</span></p>
							<div class="fl tik_select" style="width:258px;height:26px">
								<div class="tik1_position">
									<%-- <span>${info.name}</span> --%>
									<input type="text" id="otherfile" value="${info.name}" style="width:258px;height:28px;border:1px solid #f2f2f2;float:left;" disabled="disabled"/>
									<p class="mate_position" style="width:40px;">上传</p>
								</div>
								<input type="file" id="matFile" name="matFile" onchange="fileChange(this);" style="filter:alpha(opacity=0);opacity:0;position:absolute;overflow:hidden;top:0px;right:0px;padding:0px;width:30px;height:30px;background:#feaf27;color:#fff;border:none;"/>
							  </div>
						    </div>
						</div>
						<div class="clear"></div>
					</li>
					<li class="test">
						<div class="fl shitin_xinzeng att_movie">
							<p class="fl"><span class="fr"><em>*</em>原文件格式：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="format" value="${info.format}" id="format" readonly="readonly"/>
						</div>
						<div class="fl shitin_xinzeng att_movie">
							<p class="fl ml30"><span class="fr"><em>*</em>上传国家库：</span></p>
							<select id = "national_state" name="national_state" style="width:260px;height:30px;border:1px solid #dddddd;">
								<option value="1"<c:if test="${info.national_state==1}"> selected</c:if>>是</option>
								<option value="0"<c:if test="${info.national_state==0}"> selected</c:if>>否</option>
							</select>
						</div>
						<div class="clear"></div>
						<div class="fl shitin_xinzeng att_movie" style="display:none;">
							<p class="fl ml30"><span class="fr">视频时长：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="duration" value="${info.duration}" id="duration"/>
						</div>
						<div class="clear"></div>
					</li>
					<li class="test" style="display:none;">
						<div class="fl shitin_xinzeng att_movie" style="display:none;">
							<p class="fl"><span class="fr">分辨率：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="resolution" value="${info.resolution}" id="resolution"/>
						</div>
						<div class="fl shitin_xinzeng att_movie" id="ml" style="display:none;">
							<p class="fl ml30"><span class="fr">码流（fps）：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="fps" value="${info.fps}" id="fps"/>
						</div>
						<div class="clear"></div>
					</li>
					<li style="display:none;">
						<div class="fl shitin_xinzeng att_movie">
							<p class="fl"><span class="fr"><em>*</em>储存路径：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="savePath" id="savePath" value="${info.savePath}"/>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr"><em>*</em>学科：</span></p>
							<input type="hidden"  id="propIds" name="course" value="${unit_ids}"/>
							<input type="hidden"  id="propNames01" name="courseStr" value="${unit_names}"/>
							<div class="fl tki_bianji takuang_xk" id="propNames">${unit_names}</div>
						</div>

						<div class="fl mt10 mll8 tk_tixing">
							<p class="fl"><span class="fl" style="width:88px;">ICD10：</span></p>
							<input type="text" class="fl tki_bianji" readonly name="ICD" id="ICD" value="${ICD10_names}">
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">主题：</span></p>
							<input type="hidden"  id="zutiIds" name="subject" value="${subject_ids}"/>
							<input type="hidden"  id="zutiNames01" name="subjectStr" value="${subject_names}"/>
							<div class="fl tki_bianji takuang_xk01" id="zutiNames" style="width:680px">${subject_names}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">素材简介：</span></p>
							<textarea name="summary" cols="" rows="" onKeyup="javascript:view_count(this);" placeholder="最大500字" id="summary" class="fl tki_bianji takuang_xk" onclick="compare;" style="width:680px;height:60px" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" >${info.summary}</textarea>
						</div>
						<div  class="shitin_xinzeng01">
							<p class="fl" style="margin-left:100px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">500</span></p>
							<p class="fl" style="width:0px">字</p>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">职称：</span></p>
							<input type="hidden" class="fl tki_bianji takuang_xk" id="dutyIds" name="sector" value="${sector_ids}"/>
							<input type="hidden" class="fl tki_bianji takuang_xk" id="dutyNames01" name="sectorStr" value="${sector_names}"/>
							<div class="fl tki_bianji takuang_xk" id="dutyNames">${sector_names}</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">认知水平：</span></p>
							<input type="hidden" id="cognizeIds" name="cognizeIds" value="${cognize_ids}"/>
							<input type="hidden" id="cognizeNames" name="cognizeNames" value="${cognize_names}"/>
							<div class="fl tki_bianji takuang_xk" id="cognizeNames01">${cognize_names}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">来源：</span></p>
							<input type="hidden" class="fl tki_bianji takuang_xk" id="laiIds" name="src" value="${source_ids}"/>
							<input type="hidden" class="fl tki_bianji takuang_xk" id="laiNames01" name="srcStr" value="${source_names}"/>
							<div class="fl tki_bianji takuang_xk" id="laiNames">${source_names}</div>
						</div>
						<div class="fl shitin_xinzeng01">
							<p class="fl ml30"><span class="fr">其他来源：</span></p>
							<textarea name="otherSource" cols="" rows="" class="fl tki_bianji">${info.otherSource}</textarea>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">篇名：</span></p>
							<input type="text" name="surname" value="${info.surname}" class="fl tki_bianji" />
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr" id="display_expert_type">专家：</span></p>
							<c:choose>
		                        <c:when test="${info.expertName!= null}">
		                            <input type="text" class="fl tki_bianji" style="width:258px;" name="expertName" id="expertName" value="${info.expertName}" />
		                        </c:when>
		                        <c:otherwise>
									<input type="text" class="fl tki_bianji" style="width:258px;" name="expertName" id="expertName" value="<%= request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString() %>" />
		                        </c:otherwise>
		                    </c:choose>
							<input name="manager" id="manager" type="hidden" value="" />
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="ca1_anniu" style="width:200px;">

							<a href="#" class="fl anniu att_baocun" id="save"><c:choose><c:when test="${info.id!=null}">更新</c:when><c:otherwise>添加</c:otherwise></c:choose>素材</a>

							<a href="#" class="fl ml30 anniu fanhui">返回</a>

						</div>
						<div class="clear"></div>
					</li>
				</ul>
			</div>
			<c:if test="${info.id==null}">
				<div class="mt10 mat1_jinduo" style="width:94%;margin:0 auto;">
					<p class="fl mt15">上传进度：<span id="vjd">0</span>/<span id="vcount">0</span></p>
					<div class="cas_cha mat1_xiangqing">
						<p class="fl cas_query" style="padding-left:30px;"><a href="#" class="anniu" style="">查看详情</a></p>
					</div>
					<div class="clear"></div>
				</div>
			</c:if>
			</form>
		</div>
		<!-- 查看详情 -->
		<div class="shiti" style="width:94%;margin:0 auto;display:none;">
			<table class="mt20 cas_table" id="xq">
				<tr>
					<th width="5%">序号</th>
					<th width="10%">名称</th>
					<th width="10%">素材类型</th>
					<th width="10%">素材格式</th>
					<th width="10%">状态</th>
					<th width="40%">进度</th>
					<th  width="15%">操作</th>
				</tr>
				<tr id="row1">
					<td colspan="7" style="text-align:center;height:30px;">暂无素材附件</td>
				</tr>
			</table>
			<div class="clear"></div>

		</div>
		<div style="height:20px;"></div>
	</div>
	<!-- 试题内容（结束） -->

<div id="tips"></div>
<form id="addvform" name="addvform" action="" method="get">

<div style="clear:both;display:none;">视频标题：<input id="title" name="title" type="text" /></div>
<div style="display:none;">视频标签：<input id="tag" name="tag" type="text" /></div>
<div style="display:none;">视频简介：<textarea id="description" name="description" rows="5" cols="30"></textarea></div>
<input id="videoid" name="videoid" type="hidden" value="" />
<div style="display:none;">视频分类：

<select id="supercategory" onchange="show();">
   <c:forEach items="${oneCategoryList}" var="oneCategoryObj">
	   <option value="${oneCategoryObj.expert}">${oneCategoryObj.expertName}</option>
   </c:forEach>
</select>

<c:forEach items="${oneCategoryList}" var="oneCategoryObj">
	   <c:if test="${twoCategoryList.get(oneCategoryObj.expert) != null}">
	       <select id="sub_${oneCategoryObj.expert}"  name="sub_category">
	          <c:forEach items="${twoCategoryList.get(oneCategoryObj.expert)}" var="subCategoryObj">
	              <option value="${subCategoryObj.expert}">${subCategoryObj.expertName}</option>
	          </c:forEach>
	       </select>
	   </c:if>
</c:forEach>


</div>
</form>
<script type="text/javascript">
	$(function (){
		//select menu
		var menuindex = 2;
		var submenuindex = 3;
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
		'<input type="text" placeholder="请输入专家名称搜索" id="teacher" style="height:23px;">' +
		'</p>' +
		'<p class="fl cas_anniu" style="margin-left:20px;padding-top:0px;" id="add_teacher"><a href="javascript:void(0);" class="fl" style="width:70px;">添加</a></p>' +
		'<div class="clear"></div>' +
		'<div class="clear" id="teacherName" style="border: 1px solid #cfcfcf;min-height:100px;padding:10px;margin-top:10px;line-height:25px;position:absolute;height:80px;width:530px;overflow:auto;">' +

		'</div>' +
		'</div>';

		$("#expertName").click(function () {
			var _this = $(this);
			var layerTitle = "专家" ;
			_this_id = $('input[name="manager"]');
			var currValTmp = '' ;
			layer.open({
				type: 1,
				title: layerTitle,
				skin: 'layui-layer-rim', //加上边框
				area: ["600px", "420px"], //宽高
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
				    document.getElementById('expertName').innerHTML = _this.val() ;

					layer.close(index);
				},
				success: function(layerb, index){
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
						//var urll=encodeURI('${ctx}/expert/ExpertManage.do?mode=search&name='+$('#teacher').val()); xh-2017-08-23
						$.ajax({
							type:'post',
							url:'${ctx}/expert/ExpertManage.do',
							data:'mode=search&name=' + keyword ,
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
</script>
<script type="text/javascript">
    var ccMassage = "${ccMassage}"  ;
    if (ccMassage != "success") {
       alert("无法从CC获取目录数据，请重新访问！") ;
    }

    // 加载上传flash ------------- start
	var swfobj=new SWFObject('http://union.bokecc.com/flash/api/uploader.swf', 'uploadswf', '80', '25', '8');
	swfobj.addVariable( "progress_interval" , 1);	//	上传进度通知间隔时长（单位：s）
	swfobj.addVariable( "notify_url" , "<%= Config.notify_url%>");	//	上传视频后回调接口
	swfobj.addParam('allowFullscreen','true');
	swfobj.addParam('allowScriptAccess','always');
	swfobj.addParam('wmode','transparent');
	swfobj.write('swfDiv');

	var updResult = '';

	//上传总数
	var vcount = 0;

	//上传进度
	var vjd = 0;

	//ccFlash是否选中视频素材
	var isVoid = false;

// 加载上传flash ------------- end

//	-------------------
//	调用者：flash
//	功能：选中上传文件，获取文件名函数
//	时间：2010-12-22
//	说明：用户可以加入相应逻辑
//	-------------------
	function on_spark_selected_file(filename) {
		isVoid = true;
	    if(filename!="" && filename!="undefined"){
	    	//对文件名进行拆分
	    	var spi = filename.lastIndexOf(".");
	    	var len = filename.length;
	    	var str = "";
	    	if(spi>0){
				var suffix = filename.substring(spi+1,len);//后缀名
	    		$("#format").val(suffix);
	    	}else{
	    		alert("视频格式有误，所选文件无后缀名！");
	    	}
	    }
		document.getElementById("videofile").value = filename.lastIndexOf(".")>0?filename.substring(0,filename.lastIndexOf(".")):filename;
	}

//	-------------------
//	调用者：flash
//	功能：验证上传是否正常进行函数
//	时间：2010-12-22
//	说明：用户可以加入相应逻辑
//	-------------------
	function on_spark_upload_validated(status, videoid) {
		if (status == "OK") {
			//alert("上传正常,videoid:" + videoid);
			$("#savePath").val(videoid);
			$("#vid"+vcount).text(videoid);
			document.getElementById("videoid").value = videoid;
			//document.getElementById("videoidshow").innerHTML = videoid;
		} else if (status == "NETWORK_ERROR") {
			alert("CC上传出错：网络错误！");
			document.getElementById("up").innerHTML = document.getElementById("up").innerHTML + "  CC上传出错：网络错误！" ;
		} else if (status == "INVALID_REQUEST") {
			alert("CC上传出错：大于2G的素材不允许上传！");
			document.getElementById("up").innerHTML = document.getElementById("up").innerHTML + "  CC上传出错：大于2G的素材不允许上传！" ;
/* 			alert("CC上传出错：页面超时,请重新登录后再操作！");
			document.getElementById("up").innerHTML = document.getElementById("up").innerHTML + "  CC上传出错：页面超时,请重新登录后再操作！" ; */
		} else {
			alert("CC上传出错，api错误码：" + status);
			document.getElementById("up").innerHTML = document.getElementById("up").innerHTML + "  CC上传出错，api错误码：" + status;
		}
	}

	//切割文件上传输入框的name和后缀并展示
	function fileChange(obj) {
		$("#otherfile").removeAttr("disabled");
		var path = $(obj).val();
		var filename = fileNameSub(path);
		//对文件名进行拆分
		var index1 = filename.lastIndexOf(".");
		var index2 = filename.length;
		var suffix = filename.substring(index1+1,index2);//后缀名
		var name = filename.substring(0,index1);
		$(obj).siblings().find('input').val(name);
		$("#format").val(suffix);
	}

	function fileNameSub(path){
		var p1 = path.lastIndexOf("/"); //对路径进行截取
		var p2 = path.lastIndexOf("\\"); //对路径进行截取
		var p = Math.max(p1, p2);
		var filename;
		if (p < 0) {
			filename = path;
		} else {
			filename = path.substring(p + 1); //赋值文件名
		}
		return filename;
	}

	//	-------------------
	//	调用者：flash
	//	功能：通知上传进度函数
	//	时间：2010-12-22
	//	说明：用户可以加入相应逻辑
	//	-------------------
	function on_spark_upload_progress(progress) {
		var uploadProgress = document.getElementById("up");
		if (progress == -1) {
			//uploadProgress.innerHTML = "上传出错：" + progress;
			document.getElementById("up").innerHTML = document
					.getElementById("up").innerHTML
					+ "  上传出错：" + progress;
		} else if (progress == 100) {
			//ccFlash上传视频素材完成，重置状态
			isVoid = false;
			uploadProgress.innerHTML = "进度：100% ";
			vjd = vjd + 1;
			//表单提交 使用Ajax方式提交表单
			$.ajax({
				async : false,
				type : 'POST',
				url : "${ctx}/materialn/MaterialnManage.do?mode=save",
				data : $("#frm").serialize(),
				dataType : 'json',
				success : function(data) {
					if(data){

					}else{
						//对json进行解析
						$(".progress").html("已完成");
						$("#vjd").html(vjd);
						//清空表单
						qkForm();
					}
					//提示框
					alert("操作成功.");
					//document.location.href = "${ctx}/material/MaterialManage.do";
				},
				error : function(data) {
					alert('操作失败.');
				}
			});
		} else {
			uploadProgress.innerHTML = "进度：" + progress + "%";
		}
	}

	function positionUploadSWF() {
		document.getElementById("swfDiv").style.width = document
				.getElementById("btn_width").style.width;
		document.getElementById("swfDiv").style.height = document
				.getElementById("btn_width").style.height;
	}

	//清空表单
	function qkForm() {
		$("#matFile").val('');
		$("#matFile").siblings().find('span').text('');
		$("#videofile").val('');
		$("#savePath").val('');
		$("#expertName").val('');
		$("#type").val('');
		$("#format").val('');
		$("#courseStr").val('');
		$("#subjectStr").val('');
		$("#summary").val('');
		$("#cognize").val('');
		$("#srcStr").val('');
		$("#otherSource").val('');
		$("#expertName").val('');
	}

	//控制上传
	function submitvideo() {
		var title = encodeURIComponent(document.getElementById("title").value,
				"utf-8");
		var tag = encodeURIComponent(document.getElementById("tag").value,
				"utf-8");
		var description = encodeURIComponent(document
				.getElementById("description").value, "utf-8");
		var superCategory = encodeURIComponent(document
				.getElementById("supercategory").value, "utf-8");
		var subCategory = document.getElementById("sub_" + superCategory);
		if (document.getElementById("supercategory") != null
				&& subCategory == null) {
			document.getElementById("tips").innerHTML = "<div style='color:red;'>一级分类不能添加视频，请重新选择</div>";
			return;
		}
		//本地
		var url = "${ctx}/getuploadurl.jsp?title=" + title + "&tag=" + tag
				+ "&description=" + description;
		//服务器
		//var url = "http://123.56.233.125:8090/admin/getuploadurl.jsp?title=" + title + "&tag=" + tag + "&description=" + description;
		if (subCategory != null) {
			url = url + "&categoryid=" + subCategory.value;
		}
		var req = getAjax();
		req.open("GET", url, true);
		req.onreadystatechange = function() {
			if (req.readyState == 4) {
				if (req.status == 200) {
					var re = req.responseText;//获取返回的内容
					document.getElementById("uploadswf").start_upload(re); //	调用flash上传函数
					//document.getElementById("request_params").innerHTML = re;
				}
			}
		};
		req.send(null);
	}
	function getAjax() {
		var oHttpReq = null;

		if (window.XMLHttpRequest) {
			oHttpReq = new XMLHttpRequest;
			if (oHttpReq.overrideMimeType) {
				oHttpReq.overrideMimeType("text/xml");
			}
		} else if (window.ActiveXObject) {
			try {
				oHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				oHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			}
		} else if (window.createRequest) {
			oHttpReq = window.createRequest();
		} else {
			oHttpReq = new XMLHttpRequest();
		}

		return oHttpReq;
	}

	//控制视频分类显示
	showSub();
	function show() {
		subCategorys = document.getElementsByName("sub_category");
		for (var i = 0; i < document.getElementsByName("sub_category").length; i++) {
			subCategorys[i].style.display = 'none';
		}
		showSub();
	}

	function showSub() {
		var superCategory = document.getElementById("supercategory").value;
		var subCategory = document.getElementById("sub_" + superCategory);
		if (subCategory != null) {
			subCategory.style.display = 'inline';
		}
	}
</script>
<script type="text/javascript">
$(function(){
	$('.cas_query').click(function(){
		$('.shiti').toggle();

	});
	//select menu
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "mar_left0" + menuindex;
		$('.tk_mainmenu>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");

		$('.cas_anniu_4,.bjt_kt').click(function(){
			$('.att_make01').hide();
		});

	selectInit();

		$('.tk1_list li').click(function(){
			var str=$(this).text();
			$(this).parent().parent().find('div').find('b').text(str);
			$('.tk1_list').slideUp(50);
			/*if(str != "MP4"){
				$("input[name='duration']").attr("disabled","disabled");
				$("input[name='fps']").attr("disabled","disabled");
			}
			else
			{
				$("input[name='duration']").removeAttr("disabled");
				$("input[name='fps']").removeAttr("disabled");
			}*/
		});

		$(document).click(function(){
			$('.tk1_list').hide('fast');
		});

		$('.att_baocun').click(function(){
		    if (ccMassage != "success") {
		       alert("无法从CC获取目录数据，请重新访问！") ;
		       return ;
		    }
		    if($('#national_state').val() == "1"){
				//判断是否全部上传完毕
				if(vcount==vjd){
					var selectval = $("#type").val();
					if(selectval==""){
						if ($("select[name='type']").val() == '') {
							alert("请选择素材类型！");
							return;
						}
					} else if(selectval==1){
						if(!'${info.id}'){
							if ($("input[name='fileName']").val() == '') {
								alert("请选择素材！");
								return;
							}
						}
						if ($("#format").val() == '') {
							alert("请选择格式正确的文件！");
							return;
						} else if ($("input[name='course']").val() == '') {
							alert("请选择学科！");
							return;
						} else if($('#summary').val() !="" && $('#summary').val().length >500) {
							alert("素材简介不能多于500字！");
							$('#summary').focus();
							return;
						}else{
							if(!isVoid&&'${info.id}'){
								//表单提交 使用Ajax方式提交表单
								$.ajax({
									async : false,
									type : 'POST',
									url : "${ctx}/materialn/MaterialnManage.do?mode=save",
									data : $("#frm").serialize(),
									dataType : 'json',
									success : function(data) {
										//提示框
										alert("操作成功.");
										//document.location.href = "${ctx}/material/MaterialManage.do";
									},
									error : function(data) {
										alert('操作失败.');
									}
								});
							}else{
								//执行上传CC视频库
							    submitvideo();
							    //对上传视频进行计数
							    vcount = vcount + 1;
							    var vidS = "vid"+vcount;
							    $("#vcount").html(vcount);
							    //对详情表格追加一行
							    $("#row1").remove();
							    var str = "";
							    str = str + "<tr>";
							    str = str + "<td>"+vcount+"</td>";
							    str = str + "<td>"+$("#videofile").val()+"</td>";
							    str = str + "<td>"+$("#type").find("option:selected").text();+"</td>";
							    str = str + "<td>"+$("#format").val();+"</td>";
							    str = str + "<td class=\"progress\">上传中</td>";
							    $("#up").removeAttr("id");
							    str = str + "<td style=\"text-align:center;\"><div style=\"font-size:14px;color:#333;\"><span id=\"up\"></span></div></td>";
							    str = str + "<td><a onclick=\"deleteTr(this);\">删除 <span style=\"display:none;\" id=\"vid"+vcount+"\"></span></a></td>";
							    str = str + "</tr>";
							    $("#xq").append(str);
							}
						}
					} else if(selectval!="" && selectval!=1){
						if(!'${info.id}'){
							if ($("input[name='matFile']").val() == '') {
								alert("请选择素材！");
								return;
							}
						}
						//判断上传文件的格式和大小
						if($("input[name='matFile']").val() != ''){
							if (!fileUploadValid("matFile",selectval)) {
								return;
							}
						}

						if ($("#format").val() == '') {
							alert("请选择格式正确的文件！");
							return;
						} else if ($("input[name='course']").val() == '') {
							alert("请选择学科！");
							return;
						} else if($('#summary').val() !="" && $('#summary').val().length >500) {
							alert("素材简介不能多于500字！");
							$('#summary').focus();
							return;
						}else{
							//上传对象存储
							submitobject();
						}
					}
				}else{
					alert("请等待视频附件上传完毕...");
				}
			}else{
				//上传对象存储
				 $.ajax({
					async : false,
					type: 'POST',
					url: "${ctx}/materialn/MaterialnManage.do?mode=save",
					data : $("#frm").serialize(),
					dataType: 'json',
					success : function (data){

						alert("操作成功，素材未上传！")
					},
					error : function(data) {
						alert('操作失败，素材未上传！');
					}
				});

		   }
		});
		$('.tik1_select01 li').click(function(){
			var selectval = $(this).parent().find('option').eq($(this).index()-1).val();
			if (selectval ==1 || selectval == 0) {
				$('.att_movie').show();
			}else {
				$('.att_movie').hide();
				$('.none').show();
				$('.test').hide();
			}

		});

		$('#propNames').click(function(){
			initPropList("学科","${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames'));
			$('.att_make01').show();
		});
		$('#zutiNames').click(function(){
			initPropList("主题","${ctx}/propManage/getPropListAjax.do" ,7, 0, 1, 0, $('#zutiIds'), $('#zutiNames'));
			$('.att_make01').show();
		});


		$('#laiNames').click(function(){
			initPropList("来源","${ctx}/propManage/sourceList.do", -1, 0, 1, 0, $('#laiIds'), $('#laiNames'));
			$('.att_make01').show();
		});

		$('#dutyNames').click(function(){
			initPropList("职称","${ctx}/propManage/getPropListAjax.do", 24, 0, 1, 0, $('#dutyIds'), $('#dutyNames'));
			$('.att_make01').show();
		});

		$('#cognizeNames01').click(function(){
			initPropList("认知水平","${ctx}/propManage/getPropListAjax.do", 8, 0, 1, 0, $('#cognizeIds'), $('#cognizeNames01'));
			$('.att_make01').show();
		});
		$('.att_addExpert').click(function(){
		var url = "SelectExpert.do";
		var bHeight = $(window).height()-100;
		var bWidth = $(window).width()-200;
		window.open(url, "selectExpert", "top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width=" + bWidth + ",height=" + bHeight);
	});

	//init avilable input character num
		$('textarea').each(function(){
			view_count($(this));
		});
});

function selectInit(){
		$('.tik1_select').click(function(){
			$(".tk1_list").css("display","none");
			$(this).find('ul').show();
			return false;
		});
		$('.tk1_list li').click(function(){
			var str=$(this).text();
			$(this).parent().parent().find('div').find('b').text(str);
			$(this).parent().find('option').prop('selected', '');
			$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
			$('.tk1_list').slideUp(50);
		});

		$('.tk1_list option:selected').each(function(){
			var str=$(this).text();
			$(this).parent().parent().parent().find('b').text(str);
		});
}

function deleteTr(obj){
    var errTd = document.getElementById("up").innerHTML.indexOf("上传出错");
    if (errTd != -1) {
       //上传计数减1
	   vcount = 0;
	   //上传完成计数减1
	   vjd = 0;
	   $("#vcount").html(vcount);
	   $("#vjd").html(vjd);
       $(obj).parent().parent().remove();
       return ;
    }
	//判断素材是否上传成功
    var prevTd = $(obj).parent().prev().text().indexOf("100%");
    //var savePath = $(obj).children("span").text();
    var savePath = $(obj).children("input").val();
	if(prevTd>0){
		//使用ajax方式删除元素
		$.ajax({
				async : false,
				type: 'POST',
				url: "${ctx}/materialn/MaterialnManage.do?mode=delA",
				data : "savePath="+savePath,
				dataType: 'json',
				success : function (data){
				    if (data.message == "success") {
				       $(obj).parent().parent().remove();
						//上传计数减1
	        			vcount = vcount - 1;
	        			//上传完成计数减1
	        			vjd = vjd - 1;
	        			$("#vcount").html(vcount);
	        			$("#vjd").html(vjd);
				    } else {
				        alert("删除视频失败，请重试！") ;
				    }
				}
			});

	}else{
		alert("素材未上传完成,请耐心等待...")
	}
}

//选择附件格式时的显示隐藏
function selectType(){
   //清空素材格式
   $("#format").val("");
    var selType = $("#type").val();
   if(selType!="" && selType!=1){
	   //$(".test").css("display","none");
	   //视频上传控件隐藏
	   $("#videoFile").css("display","none");
	   //文件上传控件显示
	   $("#otherFile").css("display","block");

   }else{
	   //$(".test").css("display","block");
	   $("#videoFile").css("display","block");
	   $("#otherFile").css("display","none");
   }

   if(selType ==1 ){
	   $("#display_expert_type").html("讲者：");
   }if(selType == 4 || selType == 3){
	   $("#display_expert_type").html("作者：");
   }else if(selType == 2 || selType == 5){
	   $("#display_expert_type").html("专家：");
   }
}

//将资源上传到对象存储服务器
function submitobject(){
	 /* var form=document.getElementById("frm");
     var formData=new FormData(form);
     var oReq = new XMLHttpRequest();
     oReq.onreadystatechange=function(){
       if(oReq.readyState==4){
         if(oReq.status==200){
             var json=JSON.parse(oReq.responseText,function(k,v){
            	 if(!(v instanceof Object)){
            		 if(v!="Fail"){
            			 //为了方便表单提交
            			 var filename = fileNameSub($("#matFile").val());
						 //对文件名进行拆分
            			 $("#videofile").val(filename.substring(0,filename.lastIndexOf(".")));
            			 $("#savePath").val(v);
            			 //上传素材信息
            			 upMaterialn(v,filename.substring(0,filename.lastIndexOf(".")));
            		 }else{
            			 alert("上传服务器异常,请稍后重试...");
            		 }
            	 }
             });
         }
       }
     } */
     $("#videofile").val("");
     if($('#matFile').val()){
	   	 $("#frm").ajaxSubmit({
				type: 'post',
				url: '${ctx}/file/fileUpload.do',
				success: function(data) {
					try{
						var v = JSON.parse(data).message;
						if(v!="Fail"){
							//为了方便表单提交
			       			 //var filename = fileNameSub($("#matFile").val());//----taoliang remove
			       			 var filename = $("#otherfile").val();//----taoliang add
							 //对文件名进行拆分
			       			 //$("#videofile").val(filename.substring(0,filename.lastIndexOf(".")));//----taoliang remove
			       			 $("#videofile").val(filename);//----taoliang update
			       			 $("#savePath").val(v);
			       			 //上传素材信息
			       			 //upMaterialn(v,filename.substring(0,filename.lastIndexOf(".")));//----taoliang remove
			       			 upMaterialn(v,filename);//----taoliang update
						}else{
	            			 alert("上传素材时出现问题,连接不上私有云,请稍后重试...");
	            		 }
					}catch(e){
						alert("上传素材时出现问题,连接不上私有云,请稍后重试....." + e);
					}
				},
				error: function(data){
					alert("上传素材时出现问题,连接不上私有云,请稍后重试......");
				}
			});
	     //oReq.open("POST", "${ctx}/file/fileUpload.do");
    	 //oReq.send(formData);
     }else{
    	 upMaterialn();
     }
     //为了方便下次上传
     return true;
}

function upMaterialn(v,filename){
	$.ajax({
		async : false,
		type: 'POST',
		url: "${ctx}/materialn/MaterialnManage.do?mode=save",
		data : $("#frm").serialize(),
		dataType: 'json',
		success : function (data){
			if(data){

			}else{
				$(".progress").html("已完成");
    			//上传计数加1
    			vcount = vcount + 1;
    			//上传完成计数加1
    			vjd = vjd + 1;
    			$("#vcount").html(vcount);
    			$("#vjd").html(vjd);
    			$("#row1").remove();
    			var str = "";
    			str = str + "<tr>";
		 		str = str + "<td>"+vcount+"</td>";
		 		str = str + "<td>"+$("#matFile").val().substring(12)+"</td>";
		 		str = str + "<td>"+$("#type").find("option:selected").text();+"</td>";
		 		str = str + "<td>"+splitFile()+"</td>";
		 		str = str + "<td class=\"progress\">已完成</td>";
		 		str = str + "<td>进度：100%</td>";
		 		str = str + "<td><a onclick=\"deleteTr(this);\">删除 <span style=\"display:none;\" id=\"vid"+vcount+"\">"+v+"</span></a></td>";
		 		str = str + "</tr>";
		 		$("#xq").append(str);
		 		//清空表单
				qkForm();
			}
			//提示框
			alert("操作成功.");
			//document.location.href = "${ctx}/material/MaterialManage.do";

		},
		error: function(){
			alert('操作失败.');
		}
	});
}

//查找素材格式
function splitFile(){
	var f = $("#matFile").val();
	var index1 = f.lastIndexOf(".");
	var index2 = f.length;
	var suffix = f.substring(index1+1,index2);//后缀名
	return suffix;
}

function view_count(obj) {
	var next_obj = $(obj).next();
	var str = $(obj).val();
	var left_count = 500-str.length;
	var spell = $(obj).parent().parent().find('#spellNum');
	$(spell).text(left_count);
}

function selectProp() {

		$(kuangcode).val($('.xs_kuangcode').text());
		var selPropHtml = $('.xs_kuang').html().replace(brexp,",");
		$('.xs_kuang').html(selPropHtml);
		$(kuang).text($('.xs_kuang').text());

		var url = "${ctx}/propManage/getPropListAjax.do";
		var ids = $(kuangcode).val();

		if ($(kuangcode).prop('id') == "propIds"){
		$('#ICD').val("");
			if(ids != null && ids !=""){
				var params = "mode=getICD&ids="+ids;
				$.ajax({
					type: 'POST',
					url: url,
					data : params,
					dataType: 'text',
					success : function (newId){
						$('#ICD').val(newId);
					},
				});
			}
		}
}
$('.fanhui').click(function(){
	if (!confirm("您确定要返回列表页吗？"))
		return ;
	document.location.href = "${ctx}/material/MaterialManage.do";
});

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
</script>

</body>
</html>