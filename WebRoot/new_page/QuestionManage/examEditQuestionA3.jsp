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
		<i></i><h2 class="fl">修改试题</h2>
	</div>
	<div class="thi_shitineirong">
	<form id="fm1" name="fm1" action="${ctx}/questionManage/updateQuestion.do" method="post">
		<input type = "hidden" name = "id" value = "${quest.id}"/>
		<input type="hidden" name="state" value="${quest.state}"/>
		<ul>
			<li>
				<div class="fl shitin_xinzeng">
					<p class="fl"><span class="fr">试题类型：</span><em class="fr">*</em></p>
					<input type="hidden" name="question_label_id" id="question_label_id" value="${questionLabel}">
					<div class="fl tik1_select">
						<div class="tik1_position">
							<b class="ml5" id="SEL_KindOf">${labelName}</b>
							<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
						</div>
					</div>
				</div>
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

					<input type="hidden" class="fl tki_bianji takuang_xk" id="propIds" name="course" value="${unit_ids}"/>
					<input type="hidden" class="fl tki_bianji takuang_xk" id="propNames01" name="courseStr" value="${unit_names}"/>
					<div class="fl tki_bianji takuang_xk" id="propNames">${unit_names}</div>
				</div>
				<div class="fl shitin_xinzeng01">
					<p class="fl ml30"><span class="fr">ICD10：</span></p>
					<input type="text"  id="ICD" name="ICD" class="fl tki_bianji" readonly value="${ICD10_names}"/>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">主题：</span><em class="fr">*</em></p>
					<input type="hidden" class="fl tki_bianji takuang_xk" id="zutiIds" name="subject" value="${subject_ids}"/>
					<input type="hidden" class="fl tki_bianji takuang_xk" id="zutiNames01" name="subjectStr" value="${subject_names}"/>
					<div class="fl tki_bianji takuang_xk" id="zutiNames">${subject_names}</div>
				</div>
				<div class="fl shitin_xinzeng01">
					<p class="fl ml30"><span class="fr">来源：</span></p>
					<input type="hidden" class="fl tki_bianji takuang_xk" id="laiIds" name="src" value="${source_ids}"/>
					<input type="hidden" class="fl tki_bianji takuang_xk" id="laiNames01" name="srcStr" value="${source_names}"/>
					<div class="fl tki_bianji takuang_xk" id="laiNames">${source_names}</div>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">其他来源：</span></p>
					<textarea name="source" cols="" rows="" class="fl tki_bianji">${quest.source}</textarea>
				</div>
				<div class="fl shitin_xinzeng01">
					<p class="fl ml30"><span class="fr">篇名：</span></p>
					<textarea name="surname" cols="" rows="" class="fl tki_bianji">${quest.surname}</textarea>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">职称：</span><em class="fr">*</em></p>
					<input type="hidden" class="fl tki_bianji takuang_xk" id="dutyIds" name="sector" value="${sector_ids}"/>
					<input type="hidden" class="fl tki_bianji takuang_xk" id="dutyNames01" name="sectorNames" value="${sector_names}"/>
					<div class="fl tki_bianji takuang_xk" id="dutyNames">${sector_names}</div>
				</div>
				<div class="fl shitin_xinzeng">
					<p class="fl ml30"><span class="fr">认知水平：</span></p>
					<input type="hidden" class="fl tki_bianji takuang_xk" id="cognizeIds" name="cognizeIds" value="${cognize_ids}"/>
					<input type="hidden" class="fl tki_bianji takuang_xk" id="cognizeNames" name="cognizeNames" value="${cognize_names}"/>
					<div class="fl tki_bianji takuang_xk" id="cognizeNames01">${cognize_names}</div>
<%--					<input type="hidden" name="cognize" id="cognize">	
					<div class="fl tik1_select">
						<div class="tik1_position">
							<b class="ml5" id="SEL_cognize">${cognize_names}</b>
							<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
						</div>
						<ul class="fl tk1_list" style="display:none;">
							<c:forEach items="${cognizeList}" var="label">
								<li>${label.name}</li>
							</c:forEach>
						</ul>
					</div>
--%>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="fl shitin_xinzeng">
					<p class="fl"><span class="fr">是否上传国家库：</span><em class="fr">*</em></p>
					<input type="hidden" name="upLoad" id="upLoad">
					<div class="fl tik1_select">
						<div class="tik1_position">
							<b class="ml5" id="SEL_upLoad"><c:if test="${quest.state==0}">否</c:if><c:if test="${quest.state!=0}">是</c:if></b>
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
							<b class="ml5" id="SEL_isMedia">${isMedia}</b>
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
					<textarea onKeyup="javascript:view_count(this);" name="content" cols="" rows="" class="fl tki_bianji01" placeholder="最大1800字" style="width:580px;" id="content" maxlength="1800" onpropertychange="if(value.length>1800) value=value.substr(0,1800)">${quest.content}</textarea>
					<div class="fl kit_gaojo">
						<a href="javascript:dialogX('${ctxAll}/new_page/QuestionManage/adv_edit_kind.jsp',700,480,'content');" class="fl anniu">高级编辑</a>
					</div>
				</div>
				<div  class="shitin_xinzeng01">
					<p class="fl" style="margin-left:100px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">1800</span></p>
					<p class="fl" style="width:0px">字</p>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="mt10 shitin_xinzeng01">
					<p class="fl"><span class="fr">试题解析：</span></p>
					<textarea onKeyup="javascript:view_count3(this);" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" placeholder="最大500字" name="analyse" id="analyse" cols="" rows="" class="fl tki_bianji01">${quest.analyse}</textarea>
				</div>
				<div  class="shitin_xinzeng01">
					<p class="fl" style="margin-left:100px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">500</span></p>
					<p class="fl" style="width:0px">字</p>
				</div>
				<div class="clear"></div>
			</li>
			<li>				
				<div class="clear"></div>
				<div id="naw_zisiti">
				<c:forEach items = "${quest.childQuestionList}" var = "child">
				<script type="text/javascript">var i = 0;</script>
					<ul class="naw">
						<li class="zishiti" style="margin-top:10px;">
							<div class="shitin_xinzeng01">
								<p class="fl"><span class="fr">子试题类型：</span><em class="fr">*</em></p>
								<div class="fl tik1_select">
									<div class="tik1_position">
										<b class="ml5">单选题 (A3)/(A4)</b>
									</div>
								</div>
								<div class="fl kit_gaojo" style="margin-top:2px;">
									<a href="javascript:void(0);" onclick="javascript:delNaw(this);" class="anniu shangshu-naw">删除子试题</a>
								</div>
							</div>
							<div class="clear"></div>
						</li>
						<li class="zishiti">
							<div class="mt10 shitin_xinzeng01">
								<p class="fl"><span class="fr">子试题内容：</span><em class="fr">*</em></p>
								<textarea onKeyup="javascript:view_count(this);" name="childContent" cols="" rows="" class="fl tki_bianji01" placeholder="最大1800字" style="width:580px;" id="childContent" maxlength="1800" onpropertychange="if(value.length>1800) value=value.substr(0,1800)">${child.content}</textarea>
							</div>
							<div  class="shitin_xinzeng01">
								<p class="fl" style="margin-left:100px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">1800</span></p>
								<p class="fl" style="width:0px">字</p>
							</div>
							<div class="clear"></div>
						</li>
						<li>
							<div class="mt10 shitin_xinzeng01">
								<p class="fl"><span class="fr">候选项：</span><em class="fr">*</em></p>
								<div class="fl semiOr" id="key_a">
									<c:forEach items = "${child.questionKeyList}" var = "questionKey">
										<div class="tik_danxuan">
											<p class="fl">
												<input type="radio"  name="answer_key${child.id}" id="answer_key" <c:if test = "${questionKey.isnot_true eq 1}">checked</c:if> value = "1" class="fl awkey"/>
												<em class="fl">
													<script type="text/javascript">
														document.write(String.fromCharCode(i+65));
														i++;
													</script>
												</em>
											</p>
											<textarea maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" name="answer_content" onKeyup="javascript:view_count2(this);" id = "answer_content" cols="" rows="" class="fl tki_bianji02">${questionKey.content}</textarea>
											<a href="javascript:dialogX('${ctxAll}/new_page/QuestionManage/adv_edit_kind.jsp',700,480,'answer_content');" class="fl anniu">高级编辑</a>
											<div class="shitin_xinzeng01">
												<p class="fl" style="margin-left:40px;min-width:100px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">500</span></p>
												<p class="fl" style="width:0px">字</p>
											</div>
										</div>
									<div class="clear"></div>
									</c:forEach>
								</div>
							</div>
							<div class="clear"></div>
							<div class="kit_gaojo01" style="width:200px;margin-left:348px;">
								<a href="javascript:void(0);" onClick = "javascript:__addrowDX(this);" class="fl anniu">增加候选项</a>
								<a href="javascript:void(0);" onClick = "javascript:__deleteRowFI(this);" class="fl ml30 anniu">减少候选项</a>
							</div>
							<div class="clear"></div>
						</li>
						<li class="zishiti">
							<div class="mt10 shitin_xinzeng01">
								<p class="fl"><span class="fr">子试题分析：</span></p>
								<textarea onKeyup="javascript:view_count3(this);" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" id="childAnalyse" name = "childAnalyse" cols="" rows="" class="fl tki_bianji01">${child.analyse}</textarea>
							</div>
							<div  class="shitin_xinzeng01">
								<p class="fl" style="margin-left:100px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">500</span></p>
								<p class="fl" style="width:0px">字</p>
							</div>
							<div class="clear"></div>
							<input type = "hidden" name = "sub_questions" value="">
						</li>
					</ul>
					</c:forEach>
				</div>
				<div class="ca1_anniu" style="width:350px;">					
					<!-- <a href="" class="fl anniu">添加子试题</a>  -->
					<a href="javascript:void(0)" class="fl anniu naw-tianje">添加子试题</a>
					<a href="javascript:xaction();" class="fl ml30 anniu">保存</a>
					<a href="${ctx}/questionManage/questionManage.do" class="fl ml30 anniu">取消</a>
				</div>
				<div class="clear"></div>
			</li>
		</ul>
		<div id="naw_content" style="display:none;">
		<ul class="naw">
			<li class="zishiti" style="margin-top:10px;">
				<div class="shitin_xinzeng01">
					<p class="fl"><span class="fr">子试题类型：</span><em class="fr">*</em></p>
					<div class="fl tik1_select">
						<div class="tik1_position">
							<b class="ml5">单选题 (A3)/(A4)</b>
						</div>
					</div>
					<div class="fl kit_gaojo" style="margin-top:2px;">
						<a href="javascript:void(0);" onclick="javascript:delNaw(this);" class="anniu shangshu-naw">删除子试题</a>
					</div>
				</div>
				<div class="clear"></div>
			</li>
			<li class="zishiti">
				<div class="mt10 shitin_xinzeng01">
					<p class="fl"><span class="fr">子试题内容：</span><em class="fr">*</em></p>
					<textarea onKeyup="javascript:view_count(this);" name="childContent" cols="" rows="" class="fl tki_bianji01" placeholder="最大1800字" style="width:580px;" id="childContent" maxlength="1800" onpropertychange="if(value.length>1800) value=value.substr(0,1800)"></textarea>
				</div>
				<div  class="shitin_xinzeng01">
					<p class="fl" style="margin-left:100px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">1800</span></p>
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
								<input type="radio"  name="answer_key" id="answer_key" value = "1" class="fl awkey"/>
								<em class="fl">A</em>
							</p>
							<textarea maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" name="answer_content" onKeyup="javascript:view_count2(this);" id = "answer_content" cols="" rows="" class="fl tki_bianji02"></textarea>
							<a href="javascript:dialogX('${ctxAll}/new_page/QuestionManage/adv_edit_kind.jsp',700,480,'answer_content');" class="fl anniu">高级编辑</a>
							<div class="shitin_xinzeng01">
								<p class="fl" style="margin-left:40px;min-width:100px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">500</span></p>
								<p class="fl" style="width:0px">字</p>
							</div>
						</div>
						<div class="clear"></div>
						<div class="mt10 tik_danxuan">
							<p class="fl">
								<input type="radio" name="answer_key" id="answer_key" value = "1" class="fl awkey"/>
								<em class="fl">B</em>
							</p>
							<textarea maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" onKeyup="javascript:view_count2(this);" name="answer_content" id = "answer_content" cols="" class="fl tki_bianji02"></textarea>
							<a href="javascript:dialogX('${ctxAll}/new_page/QuestionManage/adv_edit_kind.jsp',700,480,'answer_content');" class="fl anniu">高级编辑</a>
							<div class="shitin_xinzeng01">
								<p class="fl" style="margin-left:40px;min-width:100px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">500</span></p>
								<p class="fl" style="width:0px">字</p>
							</div>
						</div>
						<div class="clear"></div>
					</div>
				</div>
				<div class="clear"></div>
				<div class="kit_gaojo01" style="width:200px;margin-left:348px;">
					<a href="javascript:void(0);" onClick = "javascript:__addrowDX(this);" class="fl anniu">增加候选项</a>
					<a href="javascript:void(0);" onClick = "javascript:__deleteRowFI(this);" class="fl ml30 anniu">减少候选项</a>
				</div>
				<div class="clear"></div>
			</li>
			<li class="zishiti">
				<div class="mt10 shitin_xinzeng01">
					<p class="fl"><span class="fr">子试题分析：</span></p>
					<textarea maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" onKeyup="javascript:view_count3(this);" id="childAnalyse" name = "childAnalyse" cols="" rows="" class="fl tki_bianji01"></textarea>
				</div>
				<div  class="shitin_xinzeng01">
					<p class="fl" style="margin-left:100px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">500</span></p>
					<p class="fl" style="width:0px">字</p>
				</div>
				<div class="clear"></div>
				<input type = "hidden" name = "sub_questions" value="">
			</li>
		</ul>
	</div>
	</form>
</div>
</div>
</div>

		
<script type="text/javascript">
	
	var answerkeyIndex = 1;
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
	var src = $('#naw_content').html();
	$('#naw_content').remove();
	$('.naw-tianje').click(function(){
		var nKey = "answer_key"+answerkeyIndex;
		var temsrc = src.replaceAll("answer_key",nKey);
		answerkeyIndex++;
		$('#naw_zisiti').append(temsrc);
	});
	function delNaw(obj)
	{
			var ulobj = $(obj).parent().parent().parent().parent();
			$(ulobj).remove();
	}
	$('#cognizeNames01').click(function(){
		initPropList("认知水平","${ctx}/propManage/getPropListAjax.do", 8, 0, 1, 0, $('#cognizeIds'), $('#cognizeNames01'));
		$('.att_make01').show();
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

var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
var menu = "tk_bjt0" + menuindex;
var submenu = "mar_left0" + menuindex;
$('.'+menu+'').parent().addClass("action");
$('.'+submenu+'').show();
$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
	
	function xaction(){
	
		var questCognize;
		var questSrc_ = document.getElementById('laiIds').value;

		var questType_ = document.getElementById('propIds').value;
		var questPoint2_ = document.getElementById('zutiIds').value;
 
		var questTitle_ = document.getElementById('dutyIds').value;
		var questICD_ = document.getElementById('ICD').value;
		debugger;
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
			var inputValFlag = false;
			var subQuesNum = 0;
			var isAnswerEmpty = false;
			var isNotAnswerKeyEmpty = false;
			$('.naw').each(function(key,val){
				var strQuestionInfo = "";
				strQuestionInfo += "3|";
				var question_content = $(this).find("[name='childContent']").val();
				if(question_content>1800 || question_content==""){
					alert('试题内容不能为空且不能大于1800个字符或者900个汉字!');
					$(this).find("[name='content']").focus();
					inputValFlag = true;
					return;
				}
				strQuestionInfo += question_content + "|";			
				$(this).find("[name='answer_content']").each(function(){
					if($(this).val() == ""){
						isAnswerEmpty = true;
						return;
					}
				});
				if(isAnswerEmpty)
				{
					inputValFlag = true;
					alert ("请输入候选项!");
					return;
				}
				
				$(this).find(".awkey").each(function(){
					if($(this).prop("checked")){
						isNotAnswerKeyEmpty = true;
						return;
					}
				});
				if(!isNotAnswerKeyEmpty)
				{
					inputValFlag = true;
					alert('请选取答案!');
					return; 
				}
				var childkey = "";
				$(this).find('.awkey').each(function(){
					var content = $(this).parent().parent().find("[name='answer_content']").val();
					var isNot;
					if($(this).prop('checked'))
					{
						isNot = 1;
					}
					else
					{
						isNot = 0;
					}
					childkey += content + "//" + isNot;
					childkey += "|";
				});
				strQuestionInfo += childkey;
				var question_analyse = $(this).find("[name='childAnalyse']").val();
				strQuestionInfo += "|" + question_analyse;
				$(this).find("[name='sub_questions']").val(strQuestionInfo);
				subQuesNum ++;
			});
			if(isAnswerEmpty ==false && isNotAnswerKeyEmpty && subQuesNum == 0 )
			{
				alert("请添加子试题！");
				return;
			}
		}
		
		if(!inputValFlag)
		{
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

	
function __addrowDX(obj) {
	var keyName = $(obj).parent().parent().find(".awkey").prop("name");
	var p = $(obj).parent().parent().find(".shitin_xinzeng01 .semiOr");
	var s = $(p).find("input");
	if (s.length<6){	
		var str = '';
		str += '<div class="clear"></div><div class="mt10 tik_danxuan"><p class="fl"><input type="radio" value="2" name="'+ keyName +'" id="answer_key" class="fl awkey"/><em class="fl">';
		str += String.fromCharCode(s.length+65);
		str += '</em></p><textarea name="answer_content" onKeyup="javascript:view_count2(this);"; id="answer_content';
		str += s.length + 1;
		Id = s.length + 1;
		str += '" cols="" rows="" class="fl tki_bianji02" placeholder="最大500字" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)"></textarea><span></span>';
		str +='\n';
		str +='<a href="javascript:dialogX(\'${ctxAll}/new_page/QuestionManage/adv_edit_kind.jsp\',600,430,\'answer_content2\');" class="fl anniu">高级编辑</a>';
		str +='\n';	
		str += '<div class="shitin_xinzeng01"><p class="fl" style="margin-left:40px;min-width:100px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">500</span></p><p class="fl" style="width:0px">字</p></div>';	
		$(p).append(str);
	}
}

function __deleteRowFI(obj) {
	var p = $(obj).parent().parent().find(".shitin_xinzeng01 .semiOr");
	var s = $(p).find(".tik_danxuan");
	if (s.length>2){	
		$(s).last().remove();
	}
}
function updateState(state) {
		
	var opinion;
	if (state == 3) {
		opinion = prompt("请添加子试题!","");
    		if (opinion == null) return false;
	}
	if (opinion == null) opinion = "";
	$('#analyse').val(opinion);
}
function view_count(obj) {
	var next_obj = $(obj).next();
	var str = $(obj).val();
	var left_count = 1800-str.length;
	var spell = $(obj).parent().parent().find('#spellNum');
	$(spell).text(left_count);
}

function view_count3(obj) {
	var next_obj = $(obj).next();
	var str = $(obj).val();
	var left_count = 500-str.length;
	var spell = $(obj).parent().parent().find('#spellNum');
	$(spell).text(left_count);
}

function view_count2(obj) {
	
	var next_obj = $(obj).next();
	var str = $(obj).val();
	var left_count = 500-str.length;
	var spell = $(obj).parent().find('#spellNum');
	$(spell).text(left_count);
}

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
		});
function selectProp() {

	$(kuangcode).val($('.xs_kuangcode').text());
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
$('textarea').each(function(){
	view_count($(this));
});

$('.thi_shitineirong').find("[name='analyse']").each(function(){
	view_count3($(this));
});
$('.thi_shitineirong').find("[name='childAnalyse']").each(function(){
	view_count3($(this));
});

$('.naw').find("[name='answer_content']").each(function(){
	view_count2($(this));
});
$('.naw').find("[name='answer_analyse']").each(function(){
	view_count2($(this));
});
</script>		
</body>
</html>