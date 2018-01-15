<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/taglibs.jsp"%>
<title></title>
	<link rel="stylesheet" href="${ctx}/peixun_page/css/reset.css" />
	<link rel="stylesheet" href="${ctx}/peixun_page/css/index.css" />
	<link rel="stylesheet" href="${ctx}/peixun_page/css/pickadate/default.css" />
	<link rel="stylesheet" href="${ctx}/peixun_page/css/pickadate/default.date.css" />
	<link rel="stylesheet" href="${ctx}/peixun_page/css/fontawesome/css/font-awesome.css" />
	<link rel="stylesheet" href="${ctx}/peixun_page/css/ueditor.min.css" />
	
	<script type="text/javascript" src="${ctx}/peixun_page/js/jquery.js"></script>
	<%-- <script type="text/javascript" src="${ctx}/peixun_page/js/main.js"></script> --%>
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/picker.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/picker.date.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/legacy.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/ueditor/lang/zh-cn/zh-cn.js"></script>
	<style>
		input[type=text]{padding:0 5px;}
		input:disabled{background:#f0f0f0;color:#ccc;}
		.center{margin:15px auto 0;}
	</style>
</head>

<body>
<div >
<div class="center">
<div class="tk_xuanxiag">
	<div class="xingzeng_k ks_biaoti">
		<h4 class="fl ml20">查看试题</h4>
	</div>
	<div class="thi_shitineirong">
	<form id="fm1" name="fm1" action="${ctx}/questionManage/updateQuestion.do" method="post">	
		<input type="hidden" name="id" value="${id}">
		<ul>
			<li>
				<div class="fl shitin_xinzeng">
					<p class="fl"><span class="fr">试题类型：</span><em class="fr">*</em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${labelName}</div>
				</div>
				<div class="fl shitin_xinzeng">
					<p class="fl ml30"><span class="fr">试题难度：</span><em class="fr">*</em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">
						<c:if test="${quest.grade==1}">初级</c:if>
						<c:if test="${quest.grade==2}">中级</c:if>
						<c:if test="${quest.grade==3}">高级</c:if>					
					</div>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">学科：</span><em class="fr">*</em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${unit_names}</div>					
				</div>
				<div class="fl shitin_xinzeng01">
					<p class="fl ml30"><span class="fr">ICD10：</span><em class="fr">*</em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${ICD10_names}</div>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">主题：</span><em class="fr">*</em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${subject_names}</div>
				</div>
				<div class="fl shitin_xinzeng01">
					<p class="fl ml30"><span class="fr">来源：</span><em class="fr">*</em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${source_names}</div>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">其他来源：</span><em class="fr">*</em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${otherSource_names}</div>
				</div>
				<div class="fl shitin_xinzeng01">
					<p class="fl ml30"><span class="fr">篇名：</span><em class="fr">*</em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${otherSource_names}</div>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">职称：</span><em class="fr">*</em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${sector_names}</div>
				</div>
				<div class="fl shitin_xinzeng">
					<p class="fl ml30"><span class="fr">认知水平：</span><em class="fr">*</em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${cognize_names}</div>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="fl shitin_xinzeng">
					<p class="fl"><span class="fr">是否上传国家库：</span><em class="fr">*</em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">			
					</div>
				</div>
				<div class="fl shitin_xinzeng">
					<p class="fl ml30"><span class="fr">是否多媒体试题：</span><em class="fr">*</em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${isMedia}</div>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="shitin_xinzeng01">
					<p class="fl"><span class="fr">试题内容：</span><em class="fr">*</em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${quest.content}</div>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="mt10 shitin_xinzeng01">
					<p class="fl"><span class="fr">候选项：</span><em class="fr">*</em></p>
					<div class="fl semiOr" id="key_a">
						<script type="text/javascript">var i=65</script>
						<c:forEach var="qk" items="${quest.questionKeyList}" varStatus="i">	
							<div class="tik_danxuan">
								<p class="fl">
									<input type="checkbox" <c:if test="${qk.isnot_true eq 1}">checked</c:if> value="${i.index+1}" name="answer_key" id="answer_key" disabled class="fl"/>
									<em class="fl"><script type="text/javascript">document.write(String.fromCharCode(i++));</script></em>
								</p>
								<textarea name="answer_content" id="answer_content1" cols="" rows="" class="fl tki_bianji02"></textarea>
							</div>
							<div class="clear"></div>
						</c:forEach>
					</div>
				</div>	
				<div class="clear"></div>
			</li>
			<li>
				<div class="mt10 shitin_xinzeng01">
					<p class="fl"><span class="fr">试题分析：</span></p>
					<textarea name="analyse" id="analyse" cols="" rows="" readonly="readonly" class="fl tki_bianji01">${quest.analyse}</textarea>
				</div>
				<div class="clear"></div>
			</li>	
		</ul>
	</form>
	

	<div id="mask"
			style="top: 0pt; left: 0pt; position: absolute; z-index: 1000; height: 1188px; display: none;"
			class="bg62">
		</div>
		<div class="sech indx">
			<div class="sechbar">
				<div id="popupSelector"
					style="position: absolute; z-index: 1777; top: 0pt; left: 238px; display: none;">
					<div id="pslayer" class="alert_lay sech_lay lm lay_wl">
						<!--背景圆角上-->
						<div class="alert_t">
						</div>
						<div class="box">
							<h1>
								<span id="psHeader"></span><a href="javascript:void(0);"
									class="butn3" id="imgClose"> </a>
							</h1>
							<div class="blk">
								<div style="display: none;" id="divSelecting" class="sech_layt">
									<h3>
										<span id="selectingHeader"></span><b class="btn_fst"> <input
												id="lnkOK" name="" value="确定" class="btn_fst" type="button">
											<input id="lnkEmpty" name="" value="清空" class="butdef"
												type="button"> </b>
									</h3>
									<ul id="selecting"></ul>
								</div>
								<div style="display: none;" id="noSelectedLoc"
									class="sech_layt btn_fst">
									<h3>
										<span>提示：</span><b><input id="btnOkLoc" name=""
												class="fst" value="确定" type="button"> <input name=""
												disabled="disabled" value="清空" class="butdef_n"
												type="button"> </b>
									</h3>
									<p>
										请您通过＂
										<img src="${ctx}/images/ico1.gif" alt="">
										＂至少选择一个学科知识点
									</p>
								</div>
								<div class="sech_layb">
									<h2 style="display: none;" id="subHeader1">
										<span></span>
									</h2>
									<ol id="allItems1">
									</ol>
								</div>
							</div>
						</div>
						<!--背景圆角下-->
						<div class="alert_b">
							<img src="ttt/laybj_br.gif" alt="">
						</div>
					</div>
				</div>
				<div id="subItems" class="alert_lay sech_lay2 lay_wl2"
					style="position: absolute; z-index: 1777; top: 0pt; left: 554px; display: none;">
					<!--背景圆角上-->
					<div class="alert_t">
					</div>
					<div id="subBox" class="box">
					</div>
					<!--背景圆角下-->
					<div class="alert_b">
						<img src="ttt/laybj_br.gif" alt="">
					</div>
				</div>
				<div id="thirdItems" class="alert_lay sech_lay2 lay_wl2"
					style="display: none; position: absolute; z-index: 1778;">
					<!--背景圆角上-->
					<div class="alert_t">
					</div>
					<div id="thirdBox" class="box">
					</div>
					<!--背景圆角下-->
					<div class="alert_b">
					</div>
				</div>
				<div id="fourthItems" class="alert_lay sech_lay2 lay_wl2"
					style="display: none; position: absolute; z-index: 1779;">
					<!--背景圆角上-->
					<div class="alert_t">
					</div>
					<div id="fourthBox" class="box">
					</div>
					<!--背景圆角下-->
					<div class="alert_b">
					</div>
				</div>
				<div id="fifthItems" class="alert_lay sech_lay2 lay_wl2"
					style="display: none; position: absolute; z-index: 1779;">
					<!--背景圆角上-->
					<div class="alert_t">
					</div>
					<div id="fifthBox" class="box">
					</div>
					<!--背景圆角下-->
					<div class="alert_b">
					</div>
				</div>
			</div>
		</div>



	</div>
	
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
					<em class="fl"></em>
				</div>
				<div class="fl xs_er attri02" id="secondLevel" style="display:none;" onclick="backLevel(2);">
					<p class="fl" id="second"></p>
					<div style="display:none;" id="secondId"></div>
					<div style="display:none;" id="secondName"></div>
					<em class="fl"></em>
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
</div>
</div>
</div>

		
<script type="text/javascript">
	
	$('.cas_anniu_4,.bjt_kt').click(function(){
		$('.att_make01').hide();
	});

var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
var type = "${type}";
if (type == "1") submenuindex = 1;
var menu = "tk_bjt0" + menuindex;
var submenu = "mar_left0" + menuindex;
$('.'+menu+'').parent().addClass("action");
$('.'+submenu+'').show();
$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");

	var isFolded = 0;
	$('.tk1_list').click(function(){
		isFolded = 0;
		return false;
	});
	$('.tik1_select').click(function(){
		isFolded = !isFolded;
		if (isFolded) return false;
	});
	$(document).click(function(){
		isFolded = 0;
		$('.tk1_list').hide('fast');
	});

	function xaction(){
	
		var question_content = document.fm1.content.value.getBytes();
		var question_analyse = document.fm1.analyse.value.getBytes();
		
		var questCognize;
		var questSrc_ = document.getElementById('src').value;
		var questType_ = document.getElementById('course').value;
		var questPoint2_ = document.getElementById('subject').value;
		var questTitle_ = document.getElementById('sector').value;
		
		if(questType_==''){
			alert('请选择试题分类!');
			return;
		}
		if(questPoint2_==''){
			alert('请选择行业!');
			return;
		}
		if(questTitle_==''){
			alert('请选择适用对象!');
			return;
		}
		if(questSrc_==''){
			alert('请选择来源!');
			return;
		}
		if($('#laiNames').text()=='其他' && $("[name='source']").val() == ''){
			alert('请输入其他来源！');
			return;
		}
		if($("[name='surname']").val() == ''){
			alert('请输入篇名！');
			return;
		}		
		//seled(questTitle_);
		//seled(questType_);
		//seled(questCognize_);
		//seled(questPoint2_);
		
		if(question_content>1800 || question_content==0){
			alert('试题内容不能为空且不能大于1800个字符或者900个汉字!');
			$("content").focus();
			return;
		}	

			
		if(question_analyse>1800){
			alert('试题分析不能大于1800个字符或者900个汉字!');
			$("analyse").focus();
			return;
		}
/*
		if(checkkeyAnswer()!=true){
			alert('试题选项内容不能为空且不能大于1800个字符或者900个汉字!');
			return;
		}
		if(checkkey()!=true){
			alert('请选取答案!');
			return;
		}
*/
		var isChecked = false;
		$("input:radio").each(function(){
		 	if ($(this).is(':checked')) {
           		 isChecked = true;
           		 return;
           }
		});
		if (!isChecked) {
			alert('check!');
			return;
		}

		if(confirm('您确定保存吗？')){
		
			var cognize = $('#SEL_cognize').text();
			$('#cognize').val(cognize);
			
			var grade = $('#SEL_grade').text();
			if(grade == "高级") $('#grade').val('3');
			else if (grade == "中级") $('#grade').val('2');
			else $('#grade').val('1');
			
			var isMedia = $('#SEL_isMedia').text();
			if (isMedia == '不') 		$('#isnot_multimedia').val(0);
			else if (isMedia == '是')	$('#isnot_multimedia').val(1);
			else {}
			
			var upLoad = $('#SEL_upLoad').text();
			$('#upLoad').val(upLoad);
	
			document.fm1.submit();
		}
	}
	
	function seled(id){
		var len = id.options.length;
		if(len>0){
			for(var i=0;i<len;i++){
				id.options[i].selected='selected';
			}
		}
	}

if ($('#SEL_KindOf').text() == '') $('#SEL_KindOf').text("请选择");
if ($('#SEL_State').text() == '') $('#SEL_State').text("请选择");
if ($('#SEL_Media').text() == '') $('#SEL_Media').text("请选择");
if ($('#SEL_OrderBy').text() == '') $('#SEL_OrderBy').text("请选择");
if ($('#SEL_cognize').text() == '') $('#SEL_cognize').text("请选择");


function updateState(state) {
	
		
	var opinion;
	if (state == 3) {
		opinion = prompt("请添加审核意见!","");
    		if (opinion == null) return false;
	}
	if (opinion == null) opinion = "";
	
	if(state != 3 || opinion != ""){
	
		var url = '${ctx}/questionManage/updateQuestionState.do';
		var params = 'state=' + state;
		params += '&opinion=' + opinion;
		params += '&id=' + "${id}";
   		
		$.ajax({
			type: 'POST',
			url: url,
			data : params,
			dataType: 'text',
			success : function (b){
		      	alert('成功！');
		      	document.location.href = document.location.href.split("#")[0];
			},
			error: function(){
			   	  alert('失败.请检查属性的关联!');
			}
		});
	}

}


	
function popUpDlg(type, id, title) {

	var url = '${ctx}/questionManage/questionManage.do';
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
					str += '<input type="checkbox" class="fl" style="margin-top:5px;" name="unit" onclick="subView2(7);" value="' + subject[i].id + '" title="' + subject[i].name + '"/>';
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
					str += '<input type="checkbox" class="fl" style="margin-top:5px;" name="unit" onclick="subView2(9);" value="' + sector[i].id + '" title="' + sector[i].name + '"/>';
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
		
		var url = '${ctx}/questionManage/questionManage.do';
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
							};
						};
					}
					$('#ICDStr').text(icdStr);
					$('#ICDStr').next().val(icdStr);
					$('#ICDStr').css('background-color', 'white');
				},
				error: function(){
					alert("error");
				}
			});
	}
	else if ($('#propType').text() == "主题") {		$('#subjectStr').text($('.xs_kuang').text()); if ($('#subjectStr').text() == '') $('#subject').val('');  $('#subjectStr').next().val($('.xs_kuang').text());}
	else if ($('#propType').text() == "职称") {		$('#sectorStr').text($('.xs_kuang').text()); if ($('#sectorStr').text() == '') $('#sector').val('');}
	else if ($('#propType').text() == "来源") {		$('#srcStr').text($('.xs_kuang').text()); if ($('#srcStr').text() == '') $('#src').val('');}
	else if ($('#propType').text() == "ICD10") {	$('#ICDStr').text($('.xs_kuang').text()); $('#ICDStr').next().val($('.xs_kuang').text());}
	else {}	
}

function __addrowDX() {
	var s = document.fm1.answer_key;
	if (s.length<6){	
		var str = '';
		str += '<div class="clear"></div><div class="mt10 tik_danxuan"><p class="fl"><input type="radio" value="2" name="answer_key" id="answer_key" class="fl"/><em class="fl">';
		str += String.fromCharCode(s.length+65);
		str += '</em></p><textarea name="answer_content" id="answer_content';
		str += s.length + 1;
		str += '" cols="" rows="" class="fl tki_bianji02"></textarea><a href="javascript:dialog(\'../new_page/QuestionManage/adv_edit_kind.jsp\',600,430,\'answer_content2\');" class="fl anniu">高级编辑</a></div>';
		$('#key_a').append(str);
	}
}

function __deleteRowFI() {
	var s = document.fm1.answer_key;
	if (s.length>2){	
		$('.tik_danxuan').last().remove();
	}
}

</script>		
	</body>
</html>