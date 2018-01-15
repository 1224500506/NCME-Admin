<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/new_page/top.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理平台</title>
</head>

<script type="text/javascript" src="${ctx}/js/question.js"></script>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>

<body class="bjs" onkeydown="entersearch();">
<div>
	<div class="center">
	<div class="tk_center01" style="min-height:40px;">
		<div class="tk_zuo">
			<form action="${ctx}/questionManage/questionManage.do" method="post" name="fmx" id="fmx">			
				<div class="tik_xuanze">
					<div class="mt10 fl tk_tixing">
						<em class="fl">题型：</em>
						<input type="hidden" name="question_label_name" id="question_label_name" value="${question_label_name}">						
						<div class="fl tik_select">
							<div class="tik_position">
								<b class="ml5" id="SEL_KindOf">${question_label_name}</b>
								<p class="tik_position01"><i class="tk_bjt10"></i></p>
							</div>
							<ul class="fl tk_list" style="display:none;">
								<li>请选择</li>
								<c:forEach items="${labelList}" var="label">
									<li>${label.name}</li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<div class="mt10 mll8 fl tk_tixing">
						<span class="fl">状态：</span>
						<input type="hidden" name="state" id="state" value="${state}">
						<div class="fl tik_select">
							<div class="tik_position_1">
								<b class="ml5" id="SEL_State">${state}</b>
								<p class="tik_position01"><i class="tk_bjt10"></i></p>
							</div>
							<ul class="fl tk_list_1 tk_list" style="display:none;">
								<li>请选择</li>
								<li>未上传</li>
								<li>未审核</li>
								<li>合格</li>
								<li>不合格</li>
								<li>禁用</li>
							</ul>
						</div>
					</div>
					<div class="mt10 mll8 fl tk_tixing">
						<span class="fl">多媒体试题：</span>
						<input type="hidden" name="isMedia" id="isMedia" value="${isMedia}">
						<div class="fl tik_select">
							<div class="tik_position_2">
								<b class="ml5" id="SEL_Media">${isMedia}</b>
								<p class="tik_position01"><i class="tk_bjt10"></i></p>
							</div>
							<ul class="fl tk_list_2 tk_list" style="display:none;">
								<li>请选择</li>
								<li>是</li>
								<li>否</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="clear"></div>
				<div class="tik_xuanze">
					<div class="mt10 fl tk_tixing">
						<em class="fl">学科：</em>
						<input type="hidden" class="fl tik_select" id="propIds" name="propIds" value="${propIds}"/>
						<input type="hidden" class="fl tik_select" id="propNames" name="propNames" value="${propNames}"/>
						<div class="fl tik_select01 takuang_xk" id="propNames01">${propNames}</div>
					</div>
					<div class="mt10 mll8 fl tk_tixing">
						<span class="fl">ICD10：</span>
						<input type="hidden" class="fl tik_select" id="icdIds" name="icdIds" value="${icdIds}"/>
						<input type="hidden" class="fl tik_select" id="icdNames" name="icdNames" value="${icdNames}"/>
						<div class="fl tik_select01 takuang_xk" id="icdNames01">${icdNames}</div>
<%-- 						<input type="text"  id="ICD" name="ICD" class="fl tik_select" readonly value="${ICD}"/>
 --%>					</div>
					<div class="mt10 mll8 fl tk_tixing">
					<span class="fl">主题：</span>
						<input type="hidden" class="fl tik_select" id="zutiIds" name="zutiIds" value="${zutiIds}"/>
						<input type="hidden" class="fl tik_select" id="zutiNames" name="zutiNames" value="${zutiNames}"/>
						<div class="fl tik_select02 takuang_xk01" id="zutiNames01">${zutiNames}</div>
						</div>
					</div>
				</div>
				<input type="hidden" name="orderBy" id="orderBy" value="${orderBy}">
				<div class="clear"></div>
				<!-- 高级查询 -->
				<div class="tk_yingcang" style="display:none;">
					<div class="tik_xuanze">
						<div class="mt10 fl tk_tixing">
							<em class="fl">入库时间：</em>
							<input type="text"  class="fl tik_select" style="width:150px;" name="create_date1" id="create_date1" onClick="WdatePicker()" readonly="readonly" value = "${qInfo.create_date}" />
							<em class="fl" style="padding:0px 3px 0px 3px;">至</em>
							<input type="text"  class="fl tik_select" style="width:150px;" name="create_date3" id="create_date3" onClick="WdatePicker()" readonly="readonly" value = "${qInfo.create_date2}"/>
						</div>
						<div class="mt10 fl tk_tixing" style="margin-left:159px;">
							<span class="fl">审核时间：</span>
							<input type="text"  class="fl tik_select" style="width:150px;" name="online_date1" id="online_date1" onClick="WdatePicker()" readonly="readonly" value = "${qInfo.online_date}"/>
							<em class="fl" style="padding:0px 3px 0px 3px;">至</em>
							<input type="text"  class="fl tik_select" style="width:150px;" name="online_date2" id="online_date2" onClick="WdatePicker()" readonly="readonly" value = "${qInfo.online_date2}"/>
						</div>
					</div>
					<div class="clear"></div>
					<div class="tik_xuanze">
						<div class="mt10 fl tk_tixing">
							<em class="fl">试题内容：</em>
							<input type="text"  class="fl tik_select" style="width:323px;" name="contentParam" id = "contentParam" value = "${qInfo.content}"/>
						</div>
						<div class="mt10 fl tk_tixing" style="margin-left:159px;">
							<span class="fl">创建者：</span>
							<input type="text"  class="fl tik_select" style="width:323px;" name="authorParam" id = "authorParam" value = "${qInfo.author}"/>
						</div>
					</div>
					<div class="clear"></div>
					<div class="tik_xuanze">
						<div class="mt10 fl tk_tixing" style="">
							<em class="fl">认知水平：</em>
							<input type="hidden" class="fl tki_bianji takuang_xk" id="cognizeIds" name="cognizeIds" value="${cognizeIds}"/>
							<input type="hidden" class="fl tki_bianji takuang_xk" id="cognizeNames" name="cognizeNames" value="${cognizeNames}"/>
							<div class="fl tki_bianji takuang_xk" style="width:323px;" id="cognizeNames01">${cognizeNames}</div>
							
<%-- 							<em class="fl">认知水平：</em>
							<input type="hidden" name="cognize" id="cognize" value="${cognize}">	
							<div class="fl tik_select" style="width:323px;">
								<div class="tik_position_3" style="width:323px;">
									<b class="ml5" id="SEL_cognize">${cognize}</b>
									<p class="tik_position02"><i class="tk_bjt10"></i></p>
								</div>
								<ul class="fl tk_list" style="display:none;width:323px;">
									<li>请选择</li>
									<c:forEach items="${cognizeList}" var="label">
										<li>${label.name}</li>
									</c:forEach>
								</ul>
							</div> --%>
						</div>
						<div class="mt10 fl tk_tixing" style="margin-left:159px;">
							<span class="fl">职称：</span>
							<input type="hidden" class="fl tki_bianji takuang_xk" id="dutyIds" name="dutyIds" value="${dutyIds}"/>
							<input type="hidden" class="fl tki_bianji takuang_xk" id="dutyNames" name="dutyNames" value="${dutyNames}"/>
							<div class="fl tki_bianji takuang_xk" id="dutyNames01">${dutyNames}</div>
						</div>
					</div>
					<div class="clear"></div>
					<div class="tik_xuanze">
						<div class="mt10 fl tk_tixing">
							<em class="fl">来源：</em>
							<input type="hidden" class="fl tki_bianji takuang_xk" id="laiIds" name="laiIds" value="${laiIds}"/>
							<input type="hidden" class="fl tki_bianji takuang_xk" id="laiNames" name="laiNames" value="${laiNames}"/>
							<div class="fl tki_bianji takuang_xk" id="laiNames01">${laiNames}</div>
							
						</div>
					</div>
					<div class="clear"></div>
					<div class="mt15 tk_you">
						<a href="javascript:questionSearch();" class="fl tk_chaxun">查询</a>
						<a href="javascript:void(0);" class="fl ml30 tk_chaxun01 mat1_baochun">基本查询</a>
					</div>
					<div style="height:40px;"></div>
				</div>
				<input type = "hidden" id = "searchMode" name = "searchMode" value = "${searchMode}" />
			</form>
				<div class="clear"></div>
				<div class="mt15 tk_you shiti" style="">
					<a href="javascript:questionSearch();" class="fl tk_chaxun">查询</a>
					<a href="javascript:void(0);" class="fl ml30 tk_chaxun01 mat1_chaxun">高级查询</a>
				</div>
				<div style="height:40px;"></div>
			
			</div>
		<div class="clear"></div>
	</div>
	<div class="clear"></div>
	</div>
	<div class="tkbjs"></div>
	
	<div class="center none" style="">
	<div class="tk_center01">
		<div class="mt10 kit1_tiaojia">
			<div class="mt10 fl tk_tixing">
<%-- 				<em class="fl">排序规则：</em>
				<div class="fl tik_select">
					<div class="tik_position_4">
						<b class="ml5" id="SEL_OrderBy">
							<c:if test="${orderBy eq '3'}">入库时间</c:if>
							<c:if test="${orderBy eq '4'}">审核时间</c:if>
						</b>
						<p class="tik_position01"><i class="tk_bjt10"></i></p>
					</div>
					<ul class="fl tk_list" style="display:none;">
						<li onclick="questionSearch(3);">入库时间</li>
						<li onclick="questionSearch(4);">审核时间</li>
					</ul>
				</div> --%>
			</div>
			<div class="fr cas_anniu">
				<a href="${ctx}/questionManage/swapQuestionLabel.do?questionLabel=1" class="fl" style="width:95px;margin-right:15px;">新增试题</a>
				<a href="${ctx}/questionManage/importQuestion2.do" class="fl" style="width:95px;margin-right:15px;">批量导入试题</a>
				<a href="javascript:void(0);" onclick="exportQuestions();" class="fl" style="width:95px;">导出试题</a>
			</div>
		</div>
		<div class="clear"></div>
		
		<form action="" name="listfm" id="listfm" method="post">
		<table class="mt10 cas_table">
			
			<display:table name="questionList" id="qt" style="width:100%;" class="mt10 cas_table" requestURI="${ctx}/questionManage/questionManage.do" decorator="com.hys.exam.displaytag.OverOutWrapper" >
				<display:column title="<input type='checkbox' id='chkall' value='' onclick='CheckAll(this.form);'>" style="width:3%;text-align:center">
					<input type="checkbox" name="idArr" value="${qt.id}" id="qcid"/>
				</display:column>
				<display:column title="题型" sortable="true" sortProperty="question_label_id" style="width:8%;text-align:center">
				<c:choose>
					<c:when test='${qt.question_label_id eq 1}'>单选题(A1)</c:when>
					<c:when test='${qt.question_label_id eq 2}'>单选题(A2)</c:when>
					<c:when test='${qt.question_label_id eq 3}'>单选题(A3/A4)</c:when>
					<c:when test='${qt.question_label_id eq 4}'>单选题(A3-1/A4-1)</c:when>
					<c:when test='${qt.question_label_id eq 9}'>单选题(B1)</c:when>
					<c:when test='${qt.question_label_id eq 11}'>多选题(X)</c:when>
					<c:when test='${qt.question_label_id eq 12}'>填空题</c:when>
					<c:when test='${qt.question_label_id eq 13}'>判断题</c:when>
					<c:when test='${qt.question_label_id eq 14}'>简答题</c:when>
					<c:when test='${qt.question_label_id eq 18}'>名词解释</c:when>
					<c:when test='${qt.question_label_id eq 20}'>案例分析题</c:when>
				</c:choose>
				</display:column>
				<display:column property="link2" title="内容" style="width:28%;white-space:normal;word-break:break-all;overflow:hidden;text-align: left;">
				</display:column>
				<display:column title="入库时间" sortable="true" property="create_date" style="width:8%;white-space:normal;word-break:break-all;overflow:hidden;">
				</display:column>
				<display:column title="审核时间" sortable="true" property="online_date" style="width:8%;white-space:normal;word-break:break-all;overflow:hidden;">
				</display:column>
				<display:column title="状态" style="width:8%;text-align:center">
					<c:if test="${qt.state==0}">未上传</c:if>
					<c:if test="${qt.state==1}">未审核</c:if>
					<c:if test="${qt.state==2}">合格</c:if>
					<c:if test="${qt.state==3}">不合格</c:if>
					<c:if test="${qt.state==4}">禁用</c:if>
				</display:column>
				<display:column title="审核意见" style="width:15%;">
					<c:if test="${qt.state==3 || qt.state==4}">${qt.opinion}</c:if>
				</display:column>
				<display:column property="checker" title="审核人" style="width:7%;">
				</display:column>
				<display:column title="操作" style="width:120px;text-align:center">
					<a href="${ctx}/questionManage/viewQuestion.do?id=${qt.id}&handle=v&type=1">查看</a>
					<c:if test="${qt.state==0 or qt.state==1 or qt.state==3}">
						<a href="${ctx}/questionManage/viewQuestion.do?id=${qt.id}&handle=e">修改</a>
					</c:if>
					<c:if test="${qt.state!=4}">
						<a href="javascript:updateState(${qt.id},4,'${qt.create_date }');">禁用</a>
						<a href="javascript:deleteQuestion(${qt.id});">删除</a>
					</c:if>
					<c:if test="${qt.state==4}">
						<a href="javascript:updateState(${qt.id},0,'${qt.create_date }');">启用</a>
					</c:if>
				</display:column>
			</display:table>
		</table>
	</form>
	</div>
</div>

<script type="text/javascript">

init();
function init()
{
	var searchMode = $("#searchMode").val();
	if(searchMode == "2")
	{
		$('.tk_yingcang').show();
		$('.shiti').hide();
	}
}

if ($('#SEL_KindOf').text()=='') $('#SEL_KindOf').text('请选择');
if ($('#SEL_State').text()=='') $('#SEL_State').text('请选择');
if ($('#SEL_Media').text()=='') $('#SEL_Media').text('请选择');

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
	
	
		$('#laiyuan').click(function(){
			document.location.href = "${ctx}/questionManage/questionManage.do";
		});

	//添加学科
		$('.att_tianjia').click(function(){
			$('.att_xueke').show();
			$('#rsName').val('');
			$('#rsCode').val('');
			$('#rsId').val('');
			$('#newgroupIds').val('');
			$('#newgroupNames').text('');
			$('#mode').val('add');
		});
		
		$('.att_tianjia03').click(function(){
			if ($('.chk:checked').length<=1){
				alert ("请选择来源两个以上。");
				return;
			}
			$('.srcleixing').val('');
			$('#targetSrc').html('<option value="">请选择</option>');
			$('.att_xueke03').show();
		});
		
		$('.srcleixing').change(function(){
			var url = '${ctx}/propManage/sourceList.do?mode=getListByType';
			var params = 'typeid=' + $(this).val();
			
			$.ajax({
				url: url,
				type: 'POST',
				data: params,
				dataType: 'json',
				success: function(result){
					if(result != ""){
						var str = '<option value="">请选择</option>';
						
						$(result.list).each(function(key, value){
							str+='<option value="'+value.id+'">'+value.name+'</option>';
						});
						
						$('.laiyuan').html(str);
					};
				}
			});
		});
		
	//保存合并来源	
		$('.att_hebing').click(function(){
			if ($('#targetSrc').val() == ""){
				alert("请选择来源名称。");
				return;
			}
			$('.att_xueke03').hide();

			var url = '${ctx}/propManage/sourceList.do?mode=merge';

			var array = new Array();
			$('.chk:checked').each(function(key, val){
				array.push($(val).val());
			});
			var params = 'target=' + $('#targetSrc').val();
			params += '&code='+array.toString();
			
			$.ajax({
				url: url,
				type: 'POST',
				data: params,
				dataType: 'text',
				success: function(result){
					if(result == 'success'){
						alert('合并成功！');
						document.location.href = document.location.href.split("#")[0];
					}else{
						alert('合并失败！');
					};
				}
			});
			$('.att_xueke').hide();
		});
	//保存	
		$('.att_baocun').click(function(){
			if (!$('#rsName').val()){
				alert ("请输入来源名称！");
				$('#rsName').focus();
				return;
			}
			if (!$('#rsCode').val()){
				alert ("请输入来源编码！");
				$('#rsCode').focus();
				return;
			}
			if ($('#rsProp_val1').val() == ""){
				alert("请选择来源类型！");
				return;
			}
			//添加
			if ($('#mode').val() == 'add'){
				var url = '${ctx}/propManage/sourceList.do?mode=add';
				var params = 'propName=' + $('#rsName').val();
				params += '&code='+$('#rsCode').val();
				params += '&type='+$('#rsType').val();
				params += '&source='+$('#rsSource').val();
				params += '&old='+$('#rsOld').val();
				params += '&prop_val1='+$('#rsProp_val1').val();
				params += '&hint='+$('#newgroupIds').val();
				
				$.ajax({
					url: url,
					type: 'POST',
					data: params,
					dataType: 'text',
					success: function(result){
						if(result == 'success'){
						   alert('试题添加成功！');
						   document.location.href = document.location.href.split("#")[0];
						}else{
							alert('名称或编码重复且不能保存!');
						};
					}
				});
			}
			//修改
			else if ($('#mode').val() == 'edit'){
				var url = '${ctx}/propManage/sourceList.do?mode=edit';
				var params = 'propName=' + $('#rsName').val();
				params += '&code='+$('#rsCode').val();
				params += '&id='+$('#rsId').val();
				params += '&source='+$('#rsSource').val();
				params += '&old='+$('#rsOld').val();
				params += '&hint='+$('#newgroupIds').val();
				
				$.ajax({
					url: url,
					type: 'POST',
					data: params,
					dataType: 'text',
					success: function(result){
						if(result == 'success'){
						   alert('试题修改成功！');
						   document.location.href = document.location.href.split("#")[0];
						}else{
							alert('名称或编码重复且不能保存!');
						};
					}
				});
			}
			
			$('.att_xueke').hide();
		});
		$('.att_fanhui').click(function(){
			$('.att_xueke').hide();
			$('.att_xueke03').hide();
		});
	//check all
		$('.chkall').click(function(){
			if ($(this).attr('checked') == 'checked')
				$('.chk').attr('checked' , 'checked');
			else
				$('.chk').removeAttr('checked');
		});
		
		//更多查询
		$('.mat1_chaxun').click(function(){
			$('.tk_yingcang').show();
			$("#searchMode").val("2");
			$('.shiti').hide();			
			//$('.none').hide();
		});
		//基本查询
		$('.mat1_baochun').click(function(){
			$('.tk_yingcang').hide();
			$('.shiti').show();
			initGaoJiProp();
			$("#searchMode").val("1");
			//$('.none').show();
		});
		
		$('#propNames01').click(function(){
			initPropList("学科","${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
			$('.att_make01').show();
		});	
		$('#icdNames01').click(function(){
			initPropList("ICD10","${ctx}/propManage/icdList.do" ,10, 0, 1, 0, $('#icdIds'), $('#icdNames01'));
			$('.att_make01').show();
		});		
		
		$('#zutiNames01').click(function(){
			initPropList("主题","${ctx}/propManage/getPropListAjax.do" ,7, 0, 1, 0, $('#zutiIds'), $('#zutiNames01'));
			$('.att_make01').show();
		});		
		
		$('#laiNames01').click(function(){
			initPropList("来源","${ctx}/propManage/sourceList.do", -1, 0, 1, 0, $('#laiIds'), $('#laiNames01'));
			$('.att_make01').show();
		});		
		
		$('#cognizeNames01').click(function(){
			initPropList("认知水平","${ctx}/propManage/getPropListAjax.do", 8, 0, 1, 0, $('#cognizeIds'), $('#cognizeNames01'));
			$('.att_make01').show();
		});	
		$('#dutyNames01').click(function(){
			initPropList("职称","${ctx}/propManage/getPropListAjax.do", 24, 0, 1, 0, $('#dutyIds'), $('#dutyNames01'));
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
		
		$('.tik_select').click(function(){
			$(".tk_list").css("display","none");
			$(this).find('ul').show();
			return false;
		});

		$('.tk_list option:selected').each(function(){
			var str=$(this).text();
			$(this).parent().parent().parent().find('b').text(str);
		});

		$('.tk_list li').click(function(){
			var str=$(this).text();
			$(this).parent().parent().find('div').find('b').text(str);
			$(this).parent().find('option').prop('selected', '');
			$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
			$('.tk_list').slideUp(50);
		});


		$(document).click(function(){
			$('.tk_list').hide('fast');
		});

});

//表示修改页面
function editPropShow(_id,_name,_code,_type,_source,_old,_propIds,_propNames){
	$('#rsName').val(_name);
	$('#rsCode').val(_code);
	$('#rsId').val(_id);
	$('#rsProp_val1').val(_type);
	$('#rsSource').val(_source);
	$('#rsOld').val(_old);
	$('#mode').val('edit');
	$('#newgroupIds').val(_propIds);
	$('#newgroupNames').text(_propNames);
	$('.att_xueke').show();
};



function showRel(id)
{	
	document.location.href = '${ctx}/propManage/sourceList.do?mode=showrel&id='+id;
}



function updateState(id, state, createDate){
	//if (confirm("确定!")) 
	{
		//禁用:4      启用:0
		var url = '${ctx}/questionManage/updateQuestionState.do';
		var params = 'id=' + id;
		params+= '&state=' + state;
		params+= '&createDate=' + createDate;
		if(state==4){
			if (!confirm("您确定要禁用吗?")) {
				return false;
			} 
		}else{
			if(!confirm("您确定要启用吗?")){
				return false;
			}
		}
		$.ajax({
				type: 'POST',
				url: url,
				data : params,
				dataType: 'text',
				success : function (b){
				      if (b == '1'){
				      	alert('操作成功！');
				      	questionSearch();
				      }	else if(b == '2'){
				    	 alert('该试题已经被用于组卷，不可禁用！若要禁用，请先将该试题从试卷中删除！');
				      } else {
				      	alert('操作失败!');
				      }	
				},
				error: function(){
				   	  alert('操作失败!');
				}
			});
      }
}
	
function deleteQuestion(QuestionId){
	
	if (confirm("确定删除!")) {

		var url = '${ctx}/questionManage/deleteQuestion.do';
		var params = 'idArr=' + QuestionId;
	
		$.ajax({
				type: 'POST',
				url: url,
				data : params,
				dataType: 'text',
				success : function (b){
				      if (b == 'success'){
				      	alert('试题删除成功！');
				      	questionSearch();
				      }	else if(b == '2'){
				      	alert('该试题已经被用于组卷，不可删除！若要删除，请先将该试题从试卷中删除！');
				      } else {
				      	alert('试题删除失败.请检查属性的关联!');
				      }
				},
				error: function(){
				   	  alert('试题删除失败.请检查属性的关联!');
				}
			});
      }
}

function deleteAllQuestions(){
      if (!checkItemidArr()) {
           alert("请选择要删除的设备!");
           return false;
       }
       if (confirm("确定删除!")) {
			var url = '${ctx}/questionManage/deleteQuestion.do';
			var params = 'idArr=';
			 
			$("input[name='idArr']").each(function () {
           		if ($(this).is(':checked')) {
               		params += $(this).val() + ",";
           		}
       		});
			$.ajax({
					type: 'POST',
					url: url,
					data : params,
					dataType: 'text',
					success : function (b){
					      if (b == 'success'){
					      	alert('试题删除成功！');
					      	questionSearch();
					      }	
					      else
					      	alert('试题删除失败.请检查属性的关联!');
					},
					error: function(){
					   	  alert('试题删除失败.请检查属性的关联!');
					}
				});
		}
}
	
function onChange(orderBy) {
	window.location.href = "${ctx}/questionManage/questionManage.do?orderBy=" + orderBy;
}

function questionSearch(orderBy) {
	var kindOfQuestion = $('#SEL_KindOf').text();
	$('#question_label_name').val(kindOfQuestion);
	
	var stateOfQuestion = $('#SEL_State').text();
	$('#state').val(stateOfQuestion);
	
	var isMedia = $('#SEL_Media').text();
	$('#isMedia').val(isMedia);
	
/* 	var cognize = $('#SEL_cognize').text();
	$('#cognize').val(cognize);
 */	
	$('#propNames').val($('#propNames01').text());
	$('#zutiNames').val($('#zutiNames01').text());
	$('#dutyNames').val($('#dutyNames01').text());
	$('#cognizeNames').val($('#cognizeNames01').text());
	$('#laiNames').val($('#laiNames01').text());
	$('#icdNames').val($('#icdNames01').text());
	
	if (orderBy != null) {
		$('#orderBy').val(orderBy);
	}	
	
	
	
	$('#fmx').submit();
}

function checkItemidArr() {
       var checked = false;
       $("input[name='idArr']").each(function () {
           if ($(this).is(':checked')) {
               checked = true;
               return false;
           }
       });
       return checked;
}
var force_del_url;
var force_del_params;
function doDelProp(id,prop_val_id){
	if(!confirm("您确定要删除吗？"))return;
	var url = '${ctx}/propManage/sourceList.do?mode=del';
	if(id!='' && prop_val_id!=''){
		var params = 'id=' +id;
		params +='&prop_val_id='+prop_val_id;
	}
    force_del_url = url;
	force_del_params = params;
	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'text',
	    success: function(result){
		   if(result == 'success'){
		      alert('试题删除成功！');
		      document.location.href = document.location.href.split("#")[0];
		   }else{
			   alert("该属性已被使用，无法删除!");
			   /* 
		   		if(confirm('该属性已被使用，若删除，则使用该属性的地方均被删除，确定删除!')){
		   			
					var url = force_del_url;
					var params = force_del_params + "&type=-100";
				
					$.ajax({
					    url:url,
					    type: 'POST',
					    data: params,
					    dataType: 'text',
					    success: function(result){
						   if(result == 'success'){
						      alert('试题删除成功！');
						      document.location.href = document.location.href.split("#")[0];
						   }else{
						      alert('试题删除失败！');
						   };
					    }
					 });
		   		} */
		   }
	    }
	   });
}
function exportQuestions() {
	if (!checkItemidArr()) {
         alert("请选择要导出的试题！");
         return false;
     }
     if (confirm("确定?")) {
      	document.getElementById("listfm").action = "${ctx}/questionManage/exportQuestionAndProp.do";
        document.getElementById("listfm").submit();
	}
}
function initGaoJiProp()
{
	$("#create_date1").val("");
	$("#create_date3").val("");
	$("#online_date1").val("");
	$("#online_date2").val("");
	$("#contentParam").val("");
	$("#authorParam").val("");
	$("#cognizeIds").val("");
	$("#cognizeNames").val("");
	$("#cognizeNames01").text("");
	$("#laiIds").val("");
	$("#laiNames").val("");
	$("#laiNames01").text("");
	$("#dutyIds").val("");
	$("#dutyNames").val("");
	$("#dutyNames01").text("");
}

function entersearch(){
   var event = window.event || arguments.callee.caller.arguments[0];
   if (event.keyCode == 13) {
	   questionSearch();
   }
}
</script>
</body>
</html>