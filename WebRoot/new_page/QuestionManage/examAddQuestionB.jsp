<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/new_page/top.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理平台</title>
</head>

<script type="text/javascript" src="${ctx}/js/question.js"></script>

<body class="bjs">
<div class="center">
<div class="tk_xuanxiag">
	<div class="xingzeng_k">
		<i></i><h2 class="fl">新增试题</h2>
	</div>
	<div class="thi_shitineirong">
	<form id="fm1" name="fm1" action="${ctx}/questionManage/addQuestion.do" method="post">	
		<ul>
			<li>
				<div class="fl shitin_xinzeng">
					<p class="fl"><span class="fr">试题类型：</span><em class="fr">*</em></p>
					
						<div class="fl tik1_select">
							<div class="tik1_position">
								<b class="ml5">${labelList[0].name}</b>
								<p class="tik1_position01" style="width:25px;"><i class="tk_bjt10"></i></p>
							</div>
							<ul class="fl tk1_list tk_label_list" style="display:none;">
							<select name="question_label_id" id="question_label_id" style="display:none;">
								<c:forEach items="${labelList}" var="label">
									<option value="${label.id}"<c:if test="${label.id==questionLabel}"> selected</c:if>>${label.name}</option>
								</c:forEach>
							</select>
								<c:forEach items="${labelList}" var="label">
									<li>${label.name}</li>
								</c:forEach>
							</ul>
						</div>
					
					
<%-- 					
					<input type="hidden" name="question_label_id" id="question_label_id" value="1">
					<div class="fl tik1_select">
						<div class="tik1_position">
							<b class="ml5" id="SEL_KindOf">${labelList[0].name}</b>
							<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
						</div>
						<ul class="fl tk1_list" style="display:none;">
							<c:forEach items="${labelList}" var="label">
								<li>${label.name}</li>
							</c:forEach>
						</ul>
					</div>
 --%>				</div>
				<div class="fl shitin_xinzeng">
					<p class="fl ml30"><span class="fr">试题难度：</span><em class="fr">*</em></p>
					<input type="hidden" name="grade" id="grade">
					<div class="fl tik1_select">
						<div class="tik1_position">
							<b class="ml5" id="SEL_grade">初级</b>
							<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
						</div>
						<ul class="fl tk1_list" style="display:none;">
							<li>初级</li>
							<li>中级</li>
							<li>高级</li>
						</ul>
					</div>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">学科：</span><em class="fr">*</em></p>

					<input type="hidden" class="fl tki_bianji takuang_xk" id="propIds" name="course" value="${propIds}"/>
					<input type="hidden" class="fl tki_bianji takuang_xk" id="propNames01" name="courseStr" value="${propNames}"/>
					<div class="fl tki_bianji takuang_xk" id="propNames">${propNames}</div>
				</div>
				<div class="fl shitin_xinzeng01">
					<p class="fl ml30"><span class="fr">ICD10：</span></p>
					<input type="text"  id="ICD" name="ICD" class="fl tki_bianji" readonly value="${ICD}"/>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">主题：</span><em class="fr">*</em></p>
					<input type="hidden" class="fl tki_bianji takuang_xk" id="zutiIds" name="subject" value="${subject}"/>
					<input type="hidden" class="fl tki_bianji takuang_xk" id="zutiNames01" name="subjectStr" value="${subjectStr}"/>
					<div class="fl tki_bianji takuang_xk"  id="zutiNames">${subjectStr}</div>
				</div>
				<div class="fl shitin_xinzeng01">
					<p class="fl ml30"><span class="fr">来源：</span></p>
					<input type="hidden" class="fl tki_bianji takuang_xk" id="laiIds" name="src" value="${laiIds}"/>
					<input type="hidden" class="fl tki_bianji takuang_xk" id="laiNames01" name="srcStr" value="${laiNames}"/>
					<div class="fl tki_bianji takuang_xk" id="laiNames">${laiNames}</div>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">其他来源：</span></p>
					<textarea name="source" cols="" rows="" class="fl tki_bianji"></textarea>
				</div>
				<div class="fl shitin_xinzeng01">
					<p class="fl ml30"><span class="fr">篇名：</span></p>
					<textarea name="surname" cols="" rows="" class="fl tki_bianji"></textarea>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">职称：</span><em class="fr">*</em></p>
					<input type="hidden" class="fl tki_bianji takuang_xk" id="dutyIds" name="sector" value="${sector}"/>
					<input type="hidden" class="fl tki_bianji takuang_xk" id="dutyNames01" name="sectorNames" value="${sectorNames}"/>
					<div class="fl tki_bianji takuang_xk" id="dutyNames">${sectorNames}</div>
				</div>
				<div class="fl shitin_xinzeng01">
					<p class="fl ml30"><span class="fr">认知水平：</span></p>
					<input type="hidden" class="fl tki_bianji takuang_xk" id="cognizeIds" name="cognizeIds" value="${cognizeIds}"/>
					<input type="hidden" class="fl tki_bianji takuang_xk" id="cognizeNames" name="cognizeNames" value="${cognizeNames}"/>
					<div class="fl tki_bianji takuang_xk" id="cognizeNames01">${cognizeNames}</div>
<%--					<input type="hidden" name="cognize" id="cognize">	
					<div class="fl tik1_select">
						<div class="tik1_position">
							<b class="ml5" id="SEL_cognize">${cognizeList[0].name}</b>
							<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
						</div>
						<ul class="fl tk1_list" style="display:none;">
							<c:forEach items="${cognizeList}" var="label">
								<li>${label.name}</li>
							</c:forEach>
						</ul>
					</div>--%>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="fl shitin_xinzeng">
					<p class="fl"><span class="fr">是否上传国家库：</span><em class="fr">*</em></p>
					<input type="hidden" name="upLoad" id="upLoad">
					<div class="fl tik1_select">
						<div class="tik1_position">
							<b class="ml5" id="SEL_upLoad">是</b>
							<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
						</div>
						<ul class="fl tk1_list" style="display:none;">
							<li>是</li>
							<li>否</li>
						</ul>
					</div>
				</div>
				<div class="fl shitin_xinzeng">
					<p class="fl ml30"><span class="fr">是否多媒体试题：</span><em class="fr">*</em></p>
					<input type="hidden" name="isnot_multimedia" id="isnot_multimedia">
					<div class="fl tik1_select">
						<div class="tik1_position">
							<b class="ml5" id="SEL_isMedia">否</b>
							<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
						</div>
						<ul class="fl tk1_list" style="display:none;">
							<li>是</li>
							<li>否</li>
						</ul>
					</div>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="shitin_xinzeng01">
					<p class="fl"><span class="fr">试题内容：</span><em class="fr">*</em></p>
					<textarea name="content" onKeyup="javascript:view_count(this);" onblur="checkContent(this.value)" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" placeholder="最大500字" cols="" rows="" class="fl tki_bianji01"  style="width:580px;" id="content"></textarea>
					<div class="fl kit_gaojo">
						<a href="javascript:dialogX('${ctxAll}/new_page/QuestionManage/adv_edit_kind.jsp',700,480,'content');" class="anniu">高级编辑</a>
					</div>
				</div>
				<div  class="shitin_xinzeng01">
					<p class="fl" style="margin-left:100px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">500</span></p>
					<p class="fl" style="width:0px">字</p>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="mt10 shitin_xinzeng01">
					<p class="fl"><span class="fr">候选项：</span><em class="fr">*</em></p>
					<div class="fl semiOr" id="key_a">
						<div class="tik_danxuan">
							<p class="fl">
								<input type="checkbox" value="1" name="answer_key" id="answer_key" class="fl"/>
								<em class="fl">A</em>
							</p>
							<textarea name="answer_content" onKeyup="javascript:view_count2(this);" id="answer_content1" placeholder="最大500字" cols="" rows="" class="fl tki_bianji02" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)"></textarea>
							<a href="javascript:dialogX('${ctxAll}/new_page/QuestionManage/adv_edit_kind.jsp',600,430,'answer_content1');" class="fl anniu">高级编辑</a>
							<div class="shitin_xinzeng01">
								<p class="fl" style="margin-left:40px;min-width:100px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">500</span></p>
								<p class="fl" style="width:0px">字</p>
							</div>
						</div>
						<div class="clear"></div>
						<div class="mt10 tik_danxuan">
							<p class="fl">
								<input type="checkbox" value="2" name="answer_key" id="answer_key" class="fl"/>
								<em class="fl">B</em>
							</p>
							<textarea name="answer_content" placeholder="最大500字" onKeyup="javascript:view_count2(this);" id="answer_content2" cols="" rows="" class="fl tki_bianji02" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)"></textarea>
							<span></span>
							<a href="javascript:dialogX('${ctxAll}/new_page/QuestionManage/adv_edit_kind.jsp',600,430,'answer_content2');" class="fl anniu">高级编辑</a>
							<div class="shitin_xinzeng01">
								<p class="fl" style="margin-left:40px;min-width:100px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">500</span></p>
								<p class="fl" style="width:0px">字</p>
							</div>
						</div>
					</div>
				</div>
				<div class="clear"></div>
				<div class="kit_gaojo01" style="width:200px;">
					<a href="javascript:__addrowDX()" class="fl anniu">增加候选项</a>
					<a href="javascript:__deleteRowFI()" class="fl ml30 anniu">减少候选项</a>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="mt10 shitin_xinzeng01">
					<p class="fl"><span class="fr">试题解析：</span></p>
					<textarea name="analyse" placeholder="最大500字" onKeyup="javascript:view_count(this);" id="analyse" cols="" rows="" class="fl tki_bianji01" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)"></textarea>
				</div>
				<div  class="shitin_xinzeng01">
					<p class="fl" style="margin-left:100px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">500</span></p>
					<p class="fl" style="width:0px">字</p>
				</div>
				<div class="clear"></div>
				<div class="ca1_anniu" style="width:200px;">
					<a href="javascript:xaction();" class="fl anniu">保存</a>
					<a href="${ctx}/questionManage/questionManage.do" class="fl ml30 anniu">取消</a>
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
	

</div>
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
		$('.tik_select').click(function(){
			$(".tk_list").css("display","none");
			$(this).find('ul').show();
			return false;
		});
		$('.tk_list li').click(function(){
			var str=$(this).text();
			$(this).parent().parent().find('div').find('b').text(str);
			$(this).parent().find('option').prop('selected', '');
			$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
			$('.tk_list').slideUp(50);
		});
		
		$('.tk_list option:selected').each(function(){
			var str=$(this).text();
			$(this).parent().parent().parent().find('b').text(str);
		});
		
		$(document).click(function(){
			$('.tk_list').hide('fast');
		});
			
});


	//下拉框
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
	//查询条件选择
		$('.tk1_list option:selected').each(function(){
			var str=$(this).text();
			$(this).parent().parent().parent().find('b').text(str);
		});

		$(document).click(function(){
			$('.tk1_list').hide('fast');
		});

		$('.tk_label_list li').click(function(){
			document.location.href = "${ctx}/questionManage/swapQuestionLabel.do?questionLabel="+$(this).parent().find('option').eq($(this).index()-1).val();
//			alert($(this).parent().find('option').eq($(this).index()-1).val());
		});

function xaction(){
	
		var question_content = document.fm1.content.value.getBytes();
		var question_analyse = document.fm1.analyse.value.getBytes();
		
		var questCognize;
		var questSrc_ = document.getElementById('laiIds').value;

		var questType_ = document.getElementById('propIds').value;
		var questPoint2_ = document.getElementById('zutiIds').value;

		var questTitle_ = document.getElementById('dutyIds').value;
		var questICD_ = document.getElementById('ICD').value;
		
		var upLoad = $('#SEL_upLoad').text();  //获取是否上传国家库的值 1：是   0:否   xh 2017.09.05
		if (upLoad == '是'){					   //是否上传国家库的值=是,则对页面中必选项进行验证,否则不做验证
			if(questType_==''){
				alert('请选择学科!');
				return;
			}
			if(questPoint2_==''){
				alert('请选择主题!');
				return;
			}
			if(questTitle_==''){
				alert('请选择职称!');
				return;
			}
			if($('#laiNames').text()=='其他' && $("[name='source']").val() == ''){
				alert('请输入其他来源！');
				return;
			}
			if(question_content>1800 || question_content==0){
				alert('试题内容不能为空且不能大于1800个字符或者900个汉字!');
				$('#content').focus();
				return;
			}	
			if(question_analyse>1800){
				alert('试题分析不能大于1800个字符或者900个汉字!');
				$('#analyse').focus();
				return;
			}
			var isChecked = false;
			$("input[name=answer_key]").each(function(){
			 	if ($(this).is(':checked')) {
	           		 isChecked = true;
	           		 return;
	           }
			});
			if (!isChecked) {
				alert('请选中候选项，将其作为正确答案！');
				return;
			}
			var isAnswerEmpty = false;
			$("[name='answer_content']").each(function(){
				if($(this).val() == ""){
					isAnswerEmpty = true;
					return;
				}
			});
		
			if (isAnswerEmpty){
				alert ("请输入候选项!");
				return;
			}
		}
		
		if(confirm('您确定保存吗？')){
		
			var cognize = $('#SEL_cognize').text();
			$('#cognize').val(cognize);
			
			var grade = $('#SEL_grade').text();
			if(grade == "高级") $('#grade').val('3');
			else if (grade == "中级") $('#grade').val('2');
			else $('#grade').val('1');
			
			
			var isMedia = $('#SEL_isMedia').text();
			if (isMedia == '否') 		$('#isnot_multimedia').val(0);
			else if (isMedia == '是')	$('#isnot_multimedia').val(1);
			else {}
			
			if (upLoad == '是') upLoad = 1;
			else upLoad = 0;
			$('#upLoad').val(upLoad);
	
			document.fm1.submit();
		}
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

function __addrowDX() {
	var s = document.fm1.answer_key;
	if (s.length<6){
	var add_value = s.length+1;
	var str = '';
		str += '<div class="clear"></div><div class="mt10 tik_danxuan"><p class="fl"><input type="checkbox" value="'+ add_value +'" name="answer_key" id="answer_key" class="fl"/><em class="fl">';
		str += String.fromCharCode(s.length+65);
		str += '</em></p><textarea name="answer_content" onKeyup="javascript:view_count2(this);"; id="answer_content';
		str += s.length + 1;
		Id = s.length + 1;
		str += '" cols="" rows="" class="fl tki_bianji02" placeholder="最大500字" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)"></textarea><span></span>';
		str +='\n';
		str +='<a href="javascript:dialogX(\'${ctxAll}/new_page/QuestionManage/adv_edit_kind.jsp\',600,430,\'answer_content2\');" class="fl anniu">高级编辑</a>';
		str +='\n';	
		str += '<div class="shitin_xinzeng01"><p class="fl" style="margin-left:40px;min-width:100px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">500</span></p><p class="fl" style="width:0px">字</p></div>';
		$('#key_a').append(str);
	}
	
}

function __deleteRowFI() {
	var s = document.fm1.answer_key;
	if (s.length>2){	
		$('.tik_danxuan').last().remove();
	}
}

function view_count(obj) {
	var next_obj = $(obj).next();
	var str = $(obj).val();
	var left_count = 500-str.length;
	var spell = $(obj).parent().parent().find('#spellNum');
	$(spell).text(left_count);
}

//检测试题内容
function checkContent(value){
	var questionLabelId = '${questionLabel}';
	var p = {'questionLabelId':questionLabelId,'content':value};
	$.post("${ctx}/questionManage/addQuestion.do?method=checkQuestion", p,
  		function(data){
			if(data >0){
				alert("该课题内容已存在，请换其他内容!");
				//$("#content").focus();
				return false;
			}
  				
  		});
}

function view_count2(obj) {
	
	var next_obj = $(obj).next();
	var str = $(obj).val();
	var left_count = 500-str.length;
	var spell = $(obj).parent().find('#spellNum');
	$(spell).text(left_count);
}
</script>		
</body>
</html>