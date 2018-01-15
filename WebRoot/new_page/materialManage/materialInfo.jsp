<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/new_page/top.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理平台</title>
</head>
<body class="bjs">
<div>
	<!-- 素材管理（开始） -->
	<div class="center">
		<div class="tk_tk_xuanxiag" style="">
			<div class="xingzeng_k">
				<i></i><h2 class="fl">查看素材</h2>
			</div>
			<form id="frm" action="${ctx}/material/MaterialManage.do?mode=update" method="POST" enctype="multipart/form-data">
			<div class="thi_shitineirong" style="">
				<ul>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">新增素材类型：</span></p>
							<div class="fl exp_zhuanjiak">
								<c:if test="${info.type == 1}">视频</c:if>
								<c:if test="${info.type == 2}">图片</c:if>
								<c:if test="${info.type == 3}">PPT</c:if>
								<c:if test="${info.type == 4}">文本</c:if>
								<c:if test="${info.type == 5}">压缩包</c:if>
							</div>
						</div>						
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">素材名称：</span></p>
							<div class="fl exp_zhuanjiak">
							<c:choose>
							   <c:when test="${info.format == 'MP4' or info.format == 'WMV' or info.format == 'RMVB' or info.format == 'MKV' or info.format == 'FLV' or 
		                			info.format == 'mp4' or info.format == 'wmv' or info.format == 'rmvb' or info.format == 'mkv' or info.format == 'flv'}">  
							        <a href="${ctx}/material/MaterialManage.do?mode=materialView&currUnitMediaIdVal=${info.savePath}&pmId=${info.id}" target="_Blank">${info.name}</a>       
							   </c:when>
							   <c:otherwise> 
							     	<a href="${info.savePath}"  target="_Blank">${info.name}</a> 
							   </c:otherwise>
							</c:choose>
							</div>
						</div>						
						<div class="clear"></div>
					</li>								
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">视频格式：</span></p>
							<div class="fl exp_zhuanjiak">${info.format}</div>
						</div>
						<div class="fl shitin_xinzeng att_movie" <c:if test="${info.type!=1}"> style="display:none;"</c:if>>
							<p class="fl ml30"><span class="fr">视频时长：</span></p>
							<div class="fl exp_zhuanjiak">${info.duration}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">储存路径：</span></p>
							<div class="fl exp_zhuanjiak">${info.savePath}</div>
						</div>
						<div class="fl shitin_xinzeng att_movie" <c:if test="${info.type!=1}"> style="display:none;"</c:if>>
							<p class="fl ml30"><span class="fr">码流（fps）：</span></p>
							<div class="fl exp_zhuanjiak">${info.fps}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">分辨率：</span></p>
							<div class="fl exp_zhuanjiak">${info.resolution}</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">上传国家库：</span></p>
							<div class="fl exp_zhuanjiak">
								<c:if test="${info.state==1}">否</c:if>
								<c:if test="${info.state!=1}">是</c:if>									
							</div>
							
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">学科：</span></p>
							<div class="fl exp_zhuanjiak">${unit_names}</div>							
						</div>
						<div class="mt10 mll8 fl tk_tixing">
							<span class="fl">ICD10：</span>
							<div class="fl exp_zhuanjiak">${ICD10_names}</div>														
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">主题：</span></p>
							<div class="fl exp_zhuanjiak">${subject_names}</div>							
						</div>
						<c:choose>
						   <c:when test="${info.format == 'MP4' or info.format == 'WMV' or info.format == 'RMVB' or info.format == 'MKV' or info.format == 'FLV' or 
	                			info.format == 'mp4' or info.format == 'wmv' or info.format == 'rmvb' or info.format == 'mkv' or info.format == 'flv'}">  
						        <div class="fl shitin_xinzeng">
									<p class="fl ml30"><span class="fr">分类目录：</span></p>
									<div class="fl exp_zhuanjiak">暂无</div>
								</div>       
						   </c:when>
						   <c:otherwise> 
						     	
						   </c:otherwise>
						</c:choose>
						<div class="clear"></div>
					</li>
					<li>												
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">素材简介：</span></p>
							<div class="fl exp_zhuanjiak">${info.summary}</div>							
						</div>						
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">职称：</span></p>
							<div class="fl exp_zhuanjiak">${sector_names}</div>							
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">认知水平：</span></p>
							<div class="fl exp_zhuanjiak">${cognize_names}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">来源：</span></p>
							<div class="fl exp_zhuanjiak">${source_names}</div>							
						</div>
						<div class="fl shitin_xinzeng01">
							<p class="fl ml30"><span class="fr">其他来源：</span></p>
							<div class="fl exp_zhuanjiak">${info.otherSource}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">篇名：</span></p>
							<div class="fl exp_zhuanjiak">${info.surname}</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">专家：</span></p>
							 <div class="fl exp_zhuanjiak">${info.expertName}</div>
							</div>
						<div class="clear"></div>
					</li>
					<li class="mt10">
						<div class="mt15 tk_you shiti" style="width:350px;">							
							<c:if test="${spec eq 'info' && info.state==2}">
							<a href="javascript:updatesucaiState(4,${info.id});" class="fl tk_chaxun">合格</a>
							<a href="javascript:updatesucaiState(3,${info.id});"  class="fl tk_chaxun"  style="margin-left:30px">不合格</a>
							</c:if>
							<a href="javascript:history.go(-1);" class="fl ml30 tk_chaxun01 mat1_chaxun">返回</a>
						</div>
						<div class="clear"></div>
					</li>
					
				</ul>	
			</div>
			
			</form>
		</div>
			<div class="clear"></div>
			
		</div>
		<div style="height:20px;"></div>
	</div>
	<!-- 试题内容（结束） -->
	<div class="toumingdu att_make01" style="display:none;">
	<div class="tk_center09" style="margin:7% auto;">
		<div class="tik_biaoti">
			<span class="fl" style="margin-left:290px;color:#fff;" id="propType"></span>
			<i class="fr bjt_kt"></i>
		</div>
		<div class="clear"></div>
		<div class="xianshikuang">
			<div class="mt15 xs_xuangze">
				<div class="fl xs_kuang">
				</div>
				<div class="fr cas_anniu_4" style="margin-top:25px;">
					<a href="javascript:selectProp();" class="fr">确定</a>
				</div>
				<div class="clear"></div>
			</div>
			<div class="xs_biaoti">
				<div class="xs_er" id="rootLevel" style="display:none;">
					<p class="fl" id="rootPropName"></p>
					<i class="fl xs_bjt01"></i>
				</div>
				<div class="fl xs_er attri01" id="firstLevel" style="display:none;" onclick="backLevel(1);">
					<p class="fl" id="first"></p>
					<div style="display:none;" id="firstId"></div>
					<div style="display:none;" id="firstName"></div>
					<em class="fl">></em>
				</div>
				<div class="fl xs_er attri02" id="secondLevel" style="display:none;" onclick="backLevel(2);">
					<p class="fl" id="second"></p>
					<div style="display:none;" id="secondId"></div>
					<div style="display:none;" id="secondName"></div>
					<em class="fl">></em>
				</div>
				<div class="fl xs_er attri03" id="thirdLevel" style="display:none;" onclick="backLevel(3);">
					<p class="fl" id="third"></p>
					<div style="display:none;" id="thirdId"></div>
					<div style="display:none;" id="thirdName"></div>
					<i class="fl xs_bjt01"></i>
				</div>
			</div>
			<div class="clear"></div>
			<ul class="xs_ul">
		
			</ul>
			<div class="clear"></div>
		
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
		var type = "${type}";
		if (type == "1") submenuindex = 1;
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
		if(str != "MP4"){
			$("input[name='duration']").attr("disabled","disabled");
			$("input[name='fps']").attr("disabled","disabled");
		}
		else
		{
			$("input[name='duration']").removeAttr("disabled");
			$("input[name='fps']").removeAttr("disabled");
		}
	});

		$(document).click(function(){
			$('.tk1_list').hide('fast');
		});

		$('.att_baocun').click(function(){
				
			if ($("select[name='type']").val() == '') {
				alert("请选择素材类型！");
				return;
			}
			if ($('#matFile').text() == '') {
				alert("请输入文件名！");
				return;
			}
			if ($("select[name='format']").val() == '') {
				alert("请选择文件格式");
				return;
			}
			if ($("input[name='duration']").val() == '') {
				if ($("select[name='type']").val() != '' && $("select[name='type']").val() == 1 && $("select[name='format']").val() !== '' && $("select[name='format']").val() == 'MP4') {
					alert("请输入视频时长！");
					return;
				}
			}
			if ($("input[name='savePath']").val() == '') {
				alert("请输入存储路径！");
				return;
			}
			if ($("input[name='fps']").val() == '') {
				if ($("select[name='type']").val() != '' && $("select[name='type']").val() == 1 && $("select[name='format']").val() !== '' && $("select[name='format']").val() == 'MP4') {
					alert("请输入码流！");
					return;
				}
			}
			if ($("input[name='resolution']").val() == '') {
				alert("请输入分辨率！");
				return;
			}
			if ($("input[name='course']").val() == '') {
				alert("请选择学科！");
				return;
			}

			if($('#summary').val() !="" && $('#summary').val().length >=50) {
				alert("素材简介不能多于50字！");
				$('#summary').focus();
				return;
			}
			
			$('#frm').submit();
		});
	
		$('.tik1_select01 li').click(function(){
			var selectval = $(this).parent().find('option').eq($(this).index()-1).val();
			if (selectval ==1 || selectval == 0) {
				$('.att_movie').show();		
			}else {
				$('.att_movie').hide();	
			}

		});	
		
});

function popUpDlg(type, id, title) {

	var url = '${ctx}/material/MaterialManage.do';
	var params = 'handle=jump';
	params += '&type=' + type;
	params += '&id=' + id;

	if (type == 1) {
		$('.xs_ul').empty();
		$('.xs_kuang').empty();
		$('#rootPropName').text("一级学科");
		$('#rootLevel').show();
		$('#firstLevel').hide();
		$('#secondLevel').hide();
		$('#thirdLevel').hide();		
	} else if (type == 2) {
		$('#rootLevel').hide();
		
		$('#first').text(title);
		$('#firstId').text(id);
		$('#firstName').text(title);
		
		$('#firstLevel').show();
		$('#secondLevel').hide();
		$('#thirdLevel').hide();	
	} else if (type == 3) {
		$('#rootLevel').hide();
		$('#firstLevel').show();
		
		$('#second').text(title);
		$('#secondId').text(id);
		$('#secondName').text(title);
		
		$('#secondLevel').show();
		$('#thirdLevel').hide();	
	} else if (type == 4) {
		$('#rootLevel').hide();
		$('#firstLevel').show();
		$('#secondLevel').show();
		
		$('#third').text(title);
		$('#thirdId').text(id);
		$('#thirdName').text(title);
		
		$('#thirdLevel').show();	
	} else if (type == 7){
		$('.xs_ul').empty();
		$('.xs_kuang').empty();
		$('#rootPropName').text("主题");
		$('#rootLevel').show();
		$('#firstLevel').hide();
		$('#secondLevel').hide();
		$('#thirdLevel').hide();
	} else if (type == 9) {
		$('.xs_ul').empty();
		$('.xs_kuang').empty();
		$('#rootPropName').text("职称");
		$('#rootLevel').show();
		$('#firstLevel').hide();
		$('#secondLevel').hide();
		$('#thirdLevel').hide();
	} else if (type == 10) {
		$('.xs_ul').empty();
		$('.xs_kuang').empty();
		$('#rootPropName').text("来源");
		$('#rootLevel').show();
		$('#firstLevel').hide();
		$('#secondLevel').hide();
		$('#thirdLevel').hide();
	} else if (type == 11) {
		$('.xs_ul').empty();
		$('.xs_kuang').empty();
		$('#rootPropName').text("ICD10");
		$('#rootLevel').show();
		$('#firstLevel').hide();
		$('#secondLevel').hide();
		$('#thirdLevel').hide();
	} else {
	
	}
	
	$.ajax({
		type: 'POST',
		url: url,
		data : params,
		dataType: 'json',
		success : function (data){
			if (type < 5) {
				var course = data;
				var str = '';
				for (var i=0; i<course.length; i++) {
					str += '<li><div class="attr_xueke">';
					if (type > 2) str += '<input type="checkbox" class="fl" style="margin-top:5px;" name="unit" onclick="subView2(4);" value="' + course[i].prop_val_id + '" title="' + course[i].name + '"/>';
					str += '<p class="fl"><em class="fl"';
					if (type < 4) {
						str += 'onclick="popUpDlg(';
						str += (course[i].type+1) + ',' + course[i].prop_val_id + ',\'' + course[i].name + '\'';
						str += ');"';
					}
					str += '>';
					str += course[i].name;
					str += '</em>';
					if (type < 4)	str += '<i class="fl ml10 kti_bjt2"></i>';
					str += '</p></div><div class="clear"></div></li>';
				}
				$('.xs_ul').empty();
				$('.xs_ul').append(str);
				$('#propType').text("学科");
			   	$('.att_make01').show();
		   	} else if (type == 7) {
		   		
		   		var subject = data;
				var str = '';
				for (var i=0; i<subject.length; i++) {
					str += '<li><div class="attr_xueke">';
					str += '<input type="checkbox" class="fl" style="margin-top:5px;" name="unit" onclick="subView2(7);" value="' + subject[i].refId + '" title="' + subject[i].name + '"/>';
					str += '<p class="fl"><em class="fl"';
					str += '>';
					str += subject[i].name;
					str += '</em>';
					str += '</p></div><div class="clear"></div></li>';
				}
				
		   		$('.xs_ul').empty();
				$('.xs_ul').append(str);
		   		$('#propType').text("主题");
		   		$('.att_make01').show();
		   	} else if (type == 9) {
		   		
		   		var sector = data;
				var str = '';
				for (var i=0; i<sector.length; i++) {
					str += '<li><div class="attr_xueke">';
					str += '<input type="checkbox" class="fl" style="margin-top:5px;" name="unit" onclick="subView2(9);" value="' + sector[i].refId + '" title="' + sector[i].name + '"/>';
					str += '<p class="fl"><em class="fl"';
					str += '>';
					str += sector[i].name;
					str += '</em>';
					str += '</p></div><div class="clear"></div></li>';
				}
				
		   		$('.xs_ul').empty();
				$('.xs_ul').append(str);
		   		$('#propType').text("职称");
		   		$('.att_make01').show();
		   	} else if (type == 10) {
		   		
		   		var src = data;
				var str = '';
				for (var i=0; i<src.length; i++) {
					str += '<li><div class="attr_xueke">';
					str += '<input type="checkbox" class="fl" style="margin-top:5px;" name="unit" onclick="subView2(10);" value="' + src[i].id + '" title="' + src[i].name + '"/>';
					str += '<p class="fl"><em class="fl"';
					str += '>';
					str += src[i].name;
					str += '</em>';
					str += '</p></div><div class="clear"></div></li>';
				}
				
		   		$('.xs_ul').empty();
				$('.xs_ul').append(str);
		   		$('#propType').text("来源");
		   		$('.att_make01').show();
		   	} else if (type == 11) {
		   		
		   		var ICD = data;
				var str = '';
				for (var i=0; i<ICD.length; i++) {
					str += '<li><div class="attr_xueke">';
					str += '<input type="checkbox" class="fl" style="margin-top:5px;" name="unit" onclick="subView2(11);" value="' + ICD[i].id + '" title="' + ICD[i].name + '"/>';
					str += '<p class="fl"><em class="fl"';
					str += '>';
					str += ICD[i].name;
					str += '</em>';
					str += '</p></div><div class="clear"></div></li>';
				}
				
		   		$('.xs_ul').empty();
				$('.xs_ul').append(str);
		   		$('#propType').text("ICD10");
		   		$('.att_make01').show();
		   	} else {
		   	
		   	}
		},
		error: function(){
			alert('error');
		}
	});	
}
function backLevel(type) {
	var id, title;
	if (type == 1) {
		
	} else if (type == 2) {
		id 		= $('#firstId').text();
		title 	= $('#firstName').text();
	} else if (type == 3) {
		id 		= $('#secondId').text();
		title 	= $('#secondName').text();
	}
	popUpDlg(type, id, title);
}
function subView2(type){
	var propName = '';
	var propIds = '';
	$("input[name='unit']").each(function () {
	    if ($(this).is(':checked')) {
			if (propIds == '') 		propIds += this.value;
			else					propIds += ', ' + this.value;
			
			if (propName == '')		propName += this.title;
			else					propName += ', ' + this.title;
	    }
 	});
 	
 	$('.xs_kuang').text(propName);
 	if (type < 5) 			$('#course').val(propIds);
 	else if (type == 7)		$('#subject').val(propIds);
	else if (type == 9)		$('#sector').val(propIds);
	else if (type == 10)	$('#src').val(propIds);
	else if (type == 11)	$('#ICD').val(propIds);
	else {}
}
function selectProp() {
	if ($('#propType').text() == "学科") {
		
		subView2(4);
		
		$('#courseStr').text($('.xs_kuang').text());
			
		var unitIds = $('#course').val();
		if (unitIds == '') {
			$('#ICDStr').text("");
			$('#ICDStr').next().val("");
			return;
		}
		
		var url = '${ctx}/material/MaterialManage.do';
		var params = 'handle=jump';
		params += '&type=4';
		params += '&id=' + unitIds;
	
		$.ajax({
				type: 'POST',
				url: url,
				data : params,
				dataType: 'json',
				success : function (data){
					var icdStr = "";
					if (data != null && data != '') {
						for (var i=0; i<data.length; i++) {
							var icdList = data[i].icdList;
							for (var k=0; k<icdList.length; k++) {
								icdStr += icdList[k].name + " ";
							}
						}
					}
					$('#ICDStr').text(icdStr);
					$('#ICDStr').next().val(icdStr);
					$('#ICDStr').parent().css('background-color', 'white');
				},
				error: function(){
					alert("error");
				}
			});
	}
	else if ($('#propType').text() == "主题") {		$('#subjectStr').text($('.xs_kuang').text()); if ($('#subjectStr').text() == '') $('#subject').val('');}
	else if ($('#propType').text() == "职称") {		$('#sectorStr').text($('.xs_kuang').text()); if ($('#sectorStr').text() == '') $('#sector').val('');}
	else if ($('#propType').text() == "来源") {		$('#srcStr').text($('.xs_kuang').text()); if ($('#srcStr').text() == '') $('#src').val('');}
	else if ($('#propType').text() == "ICD10") {	$('#ICDStr').text($('.xs_kuang').text()); $('#ICDStr').next().val($('.xs_kuang').text());}
	else {}	
}

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
function updatesucaiState(state,id) {
     var opinion;
     if (state == 3) {
     	opinion = prompt("请添加审核意见!","");
     	if (opinion == null) return ;
     }
     if (opinion == null) opinion = "";
	 if(state != 3 || opinion != ""){
		
		var url = '${ctx}/material/MaterialManage.do?mode=save&';
		var params = 'state=' + state;
		params += '&opinion=' + opinion;
		params += '&id=' + id;
		
		$.ajax({
			type: 'POST',
			url: url,
			data : params,
			dataType: 'text',
			success : function (b){
				if (b == "noPermission")
				{
					alert('不能审核自己上传的素材！');
			      	document.location.href = document.location.href.split("#")[0];
				}
				else{
					alert('成功！');
			      	document.location.href = '${ctx}/material/MaterialVerify.do?handle=main2';
			    }
		      	
			},
			error: function(){
			   	  alert('失败！请检查属性的关联!');
			}
		});
	}
}

</script>
</body>
</html>