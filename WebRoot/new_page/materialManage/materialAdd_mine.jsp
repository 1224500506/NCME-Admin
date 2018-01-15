<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理平台</title>
</head>

<%@include file="/new_page/top.jsp"%>
<script>
	var filefmtArray = [[],["MP4"],["JPG","PNG"],["PPT"],["DOC","PDF"]];
</script>
<body class="bjs">
<div>
	<!-- 素材管理（开始） -->
	<div class="center">
		<div class="tk_tk_xuanxiag" style="">
			<div class="xingzeng_k">
				<i></i><h2 class="fl">新增素材</h2>
			</div>
			<form id="frm" action="${ctx}/material/MaterialManage.do?mode=save" method="POST" enctype="multipart/form-data">
			<div class="thi_shitineirong" style="">
				<ul>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><em>*</em><span class="fr">新增素材类型：</span></p>
							<div class="fl tik1_select tik1_select01">
								<div class="tik1_position">
									<b class="ml5">请选择</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul id="type" class="fl tk1_list" style="display:none;">
								<select name="type" style="display:none;">
									<option value="">请选择</option>
									<option value="1">视频</option>
									<option value="2">图片</option>
									<option value="3">PPT</option>
									<option value="4">文本</option>
								</select>
									<li>请选择</li>
									<li>视频</li>
									<li>图片</li>
									<li>PPT</li>
									<li>文本</li>
								</ul>
							</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><em>*</em><span class="fr">素材名称：</span></p>
							 
							<div class="fl tik_select" style="width:258px;height:30px">
								<div class="tik1_position">
									<input type="text" name="name" id="name" class="fl tki_bianji" style="border:none; width:258px;">
									<p class="mate_position" style="width:40px;">上传</p>
								</div>
								<input type="file" name="matFile" style="position:absolute;opacity:0;overflow:hidden;left:220px;right:0px;padding:0px;width:41px;height:30px"/>
							</div>
							 
						</div>
						<div class="clear"></div>
					</li>
					
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><em>*</em><span class="fr">素材格式：</span></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">请选择</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul id="format" class="fl tk1_list" style="display:none;">
								<select name="format" style="display:none;">
									<option value="">请选择</option>
								</select>
									<li>请选择</li>
								</ul>
							</div>
						</div>
						<div class="fl shitin_xinzeng att_movie">
							<p class="fl ml30"><em>*</em><span class="fr">视频时长：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="duration" value=""/>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><em>*</em><span class="fr">储存路径：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="savePath" value=""/>
						</div>
						<div class="fl shitin_xinzeng att_movie" >
							<p class="fl ml30"><em>*</em><span class="fr">码流（fps）：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="fps" value=""/>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><em>*</em><span class="fr">分辨率：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="resolution" value=""/>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><em>*</em><span class="fr">上传国家库：</span></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">请选择</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list tk_list" style="display:none;">
									<select name="national_state" style="display:none;">
										<option value="1">是</option>
										<option value="0">否</option>
									</select>
										<li>是</li>
										<li>否</li>
								</ul>
							</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><em>*</em><span class="fr">学科：</span></p>
							<input type="hidden"  id="propIds" name="course" value="${course}"/>
							<input type="hidden"  id="propNames01" name="courseStr" value="${courseStr}"/>
							<div class="fl tki_bianji takuang_xk" id="propNames">${courseStr}</div>
						</div>

						<div class="mll8 fl tk_tixing">
							<span class="fl">ICD10：</span>
							<!-- <input type="hidden"  name="ICD" id="ICD" value=""> -->
							<div class="fl tki_bianji" id="ICD">${ICD}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr"><em>*</em>主题：</span></p>
							<input type="hidden"  id="zutiIds" name="subject" value="${subject}"/>
							<input type="hidden"  id="zutiNames01" name="subjectStr" value="${subjectStr}"/>
							<div class="fl tki_bianji takuang_xk01" id="zutiNames" style="width:680px">${subjectStr}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr"><em>*</em>素材简介：</span></p>
							<textarea name="summary" cols="" rows="" onKeyup="javascript:view_count(this);"  placeholder="最大500字"  id="summary" class="fl tki_bianji takuang_xk" onclick="compare;"  style="width:680px;height:60px" >${info.summary}</textarea>
						</div>
						<div  class="shitin_xinzeng01">
							<p class="fl" style="margin-left:100px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">500</span></p>
							<p class="fl" style="width:0px">字</p>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr"><em>*</em>职称：</span></p>
							<input type="hidden" class="fl tki_bianji takuang_xk" id="dutyIds" name="sector" value="${sector}"/>
							<input type="hidden" class="fl tki_bianji takuang_xk" id="dutyNames01" name="sectorStr" value="${sectorStr}"/>
							<div class="fl tki_bianji takuang_xk" id="dutyNames">${sectorStr}</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">认知水平：</span></p>
							<input type="hidden" id="cognizeIds" name="cognizeIds" value=""/>
							<input type="hidden" id="cognizeNames" name="cognizeNames" value=""/>
							<div class="fl tki_bianji takuang_xk" id="cognizeNames01"></div>
<!-- 							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5"></b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list" style="display:none;">
								<select name="cognize" style="display:none;">
									<option value="">请选择</option>
									<option value="1">识记</option>
									<option value="2">理解</option>
									<option value="3">掌握</option>
									<option value="4">应用</option>
								</select>
									<li>请选择</li>
									<li>识记</li>
									<li>理解</li>
									<li>掌握</li>
									<li>应用</li>
								</ul>
							</div> -->
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr"><em>*</em>来源：</span></p>
							<input type="hidden" class="fl tki_bianji takuang_xk" id="laiIds" name="src" value="${src}"/>
							<input type="hidden" class="fl tki_bianji takuang_xk" id="laiNames01" name="srcStr" value="${srcStr}"/>
							<div class="fl tki_bianji takuang_xk" id="laiNames">${srcStr}</div>
						</div>
						<div class="fl shitin_xinzeng01">
							<p class="fl ml30"><span class="fr">其他来源：</span></p>
							<textarea name="otherSource" cols="" rows="" class="fl tki_bianji"></textarea>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">篇名：</span></p>
							<input type="text" name="surname" value="${info.surname}" class="fl tki_bianji" />
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">专家：</span></p>
							<div class="fl tik_select" style="width:258px;height:30px">
								<div class="tik1_position">
									<input  name="expert" id="expert" value = "${info.expert}" class="fl tki_bianji" style="border:none; width:258px;"/>
									<input type = "hidden" name ="expertId" id = "expertId" value="${info.expertId}"/>
									<p class="mate_position att_addExpert" style="margin-left:-20px;width:60px;">选择专家</p>
								</div>
							</div>
						</div>
						<div class="clear"></div>
					</li>
					<li class="mt10">
						<div class="ca1_anniu" style="width:200px;">
							<a href="javascript:void(0);" class="fl anniu att_baocun" id="save">添加素材</a>
							<a href="${ctx}/material/MaterialManage.do" class="fl ml30 anniu">返回</a>
						</div>
						<div class="clear"></div>
					</li>					
				</ul>	
			</div>
<!-- 			<div class="mt10 mat1_jinduo" style="width:94%;margin:0 auto;">
				<p class="fl mt15">上传进度：1/4</p>
				<div class="cas_cha mat1_xiangqing">
					<p class="fl cas_query" style="padding-left:30px;"><a href="javascript:void(0);" class="anniu" style="">查看详情</a></p>
				</div>
				<div class="clear"></div>
				
			</div> -->
			</form>
		</div>		
		<div style="height:20px;"></div>
	</div>
	<!-- 试题内容（结束） -->


</div>
<script type="text/javascript">
$(function(){

	
	//select menu
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "mar_left0" + menuindex;
		$('.tk_mainmenu>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
	
		$('.att_addExpert').click(function(){	
				var url = "SelectExpert.do";
				var bHeight = $(window).height()-100;
				var bWidth = $(window).width()-200;
				window.open(url, "selectExpert", "top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width=" + bWidth + ",height=" + bHeight);
			});
	selectInit();
		
		/* $('#summary').keyup(function(){
			var zi_length = $('#summary').val();
			$('#summarycount').text(500-zi_length.length);
		}); */
		
		$(document).click(function(){
			$('.tk1_list').hide('fast');
		});

		$('.att_baocun').click(function(){
				
			if ($("select[name='type']").val() == '') {
				alert("请选择素材类型！");
				$("select[name='type']").focus();
				return;
			}
			if ($("input[name='name']").val() == '') {
				alert("请输入素材名！");
				return;
			}
/* 			if ($("input[name='matFile']").val() == '') {
				alert("请输入文件！");
				return;
			}
 */			if ($("select[name='format']").val() == '') {
				alert("请选择文件格式");
				$("select[name='format']").focus();
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
				$("input[name='savePath']").focus();
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
				$("input[name='resolution']").focus();
				return;
			}
			if ($("input[name='propIds']").val() == '') {
				alert("请选择学科！");
				$("input[name='propIds']").focus();
				return;
			}
			if ($("input[name='subject']").val() == '') {
				alert("请选择主题！");
				$("input[name='subject']").focus();
				return;
			}
 			if($('#summary').val()== ""){
				alert("请输入素材简介！");
				$('#summary').focus();
				return;
			}
			if($('#summary').val().length >500) {			
				alert("素材简介不能多于500字！");
				$('#summary').focus();
				return;
			}
			if ($("input[name='sector']").val() == '') {
				alert("请选择职称！");
				$("input[name='sector']").focus();
				return;
			}
			if ($("input[name='src']").val() == '') {
				alert("请选择来源！");
				$("input[name='src']").focus();
				return;
			}
			
			$.ajax({
				type: 'POST',
				url: '${ctx}/material/MaterialManage.do',
				data : 'handle=pick&name=' + $("input[name='name']").val(),
				dataType: 'text',
				success : function (data){						
					if (data == 'good') {
						$('#frm').submit();
					} else {
						alert("素材名称不能重复!");
					}
				},
				error: function(){
					return;
				}
			});
		});
	
		$('.tik1_select01 li').click(function(){
			var selectval = $(this).parent().find('option').eq($(this).index()-1).val();
			if (selectval ==1 || selectval == 0) {
				$('.att_movie').show();		
			}else {
				$('.att_movie').hide();	
			}

		});	
		
		$('#propNames').click(function(){
			initPropList("学科","${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 5, $('#propIds'), $('#propNames'));
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
		
	//
		$('#type li').click(function(){
			var type = $(this).index()-1;
			
			str = '<select name="format" style="display:none;"><option value="">请选择</option>';
			for (i=0; i<filefmtArray[type].length; i++){
				str += '<option value="'+filefmtArray[type][i]+'">'+filefmtArray[type][i]+'</option>';
			}
			
			str+='</select><li>请选择</li>';
			for (i=0; i<filefmtArray[type].length; i++){
				str += '<li>'+filefmtArray[type][i]+'</li>';
			}
			
			$('#format').html(str);
			selectInit();
		});
});

function selectInit(){
//		$('.tik1_select').off('click');
		$('.tik1_select').click(function(){
			$(".tk1_list").css("display","none");
			$(this).find('ul').show();
			return false;
		});
//		$('.tk1_list li').off('click');
		$('.tk1_list li').click(function(){
			var str=$(this).text();
			$(this).parent().parent().find('div').find('b').text(str);
			$(this).parent().find('option').prop('selected', '');
			$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
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
		
		$('.tk1_list option:selected').each(function(){
			var str=$(this).text();
			$(this).parent().parent().parent().find('b').text(str);
		});
}	

function view_count(obj) {
	var next_obj = $(obj).next();
	var str = $(obj).val();
	var left_count = 500-str.length;
	var spell = $(obj).parent().parent().find('#spellNum');
	$(spell).text(left_count);
}
function addExpert(id,name)
{
	$("#expertId").val(id);
	$("#expert").val(name);
}
function selectProp() {

		$(kuangcode).val($('.xs_kuangcode').text());
		var selPropHtml = $('.xs_kuang').html().replace(brexp,",");
		$('.xs_kuang').html(selPropHtml);
		$(kuang).text($('.xs_kuang').text());

		var url = "/admin/propManage/getPropListAjax.do";
		var ids = $(kuangcode).val();
		
		if ($(kuangcode).prop('id') == "propIds"){
		$('#ICD').text("");
		if(ids != null && ids !=""){
			var params = "mode=getICD&ids="+ids;  
			$.ajax({
				type: 'POST',
				url: url,
				data : params,
				dataType: 'text',
				success : function (newId){
					$('#ICD').text(newId);
				},
			});
		}
		} 
}

</script>
</body>
</html>