<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="${ctx}/js/global.js"></script>
<script type="text/javascript" src="${ctx}/js/prototype.js"></script>
<script type="text/javascript" src="${ctx}/js/question.js"></script>
<script language="javascript" src="${ctx}/js/dhtmlxWindows/dhtmlxcommon.js"></script>
<script type="text/javascript" src="${ctx}/js/dhtmlxWindows.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/default090909.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/lay.css">
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/data_all.js"></script>
<script type="text/javascript" src="${ctx}/js/popupselector5.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.bgiframe.js"></script>
</head>
<body onload="defaultsel(${label});">

<form id="fm1" name="fm1" action="${ctx}/questionManage/addQuestion.do" method="post">
<table border="0" cellspacing="1" cellpadding="0" width="100%" class="gridtable">
	<tr>
		<td align="center" colspan="6" class="row_tip" height="25">
			添加试题
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">
			试题题型：
		</td>
		<td>
			<select id="question_label_id" name="question_label_id" onchange="showType(this.value);">
				<c:forEach items="${labelList}" var="label">
				<option value="${label.id}">${label.name}</option>
				</c:forEach>
			</select>
		</td>
		<td class="row_tip" height="25">
			试题难度：
		</td>
		<td>
			<select id="grade" name="grade">
				<option value="0">
					---请选择---
				</option>
				<option value="1">
					1
				</option>
				<option value="2">
					2
				</option>
				<option value="3">
					3
				</option>
				<option value="4">
					4
				</option>
				<option value="5">
					5
				</option>
			</select>
		</td>	
		<td class="row_tip" height="25">
			多媒体试题：
		</td>
		<td>
			<select id="isnot_multimedia" name="isnot_multimedia">
				<option value="0" selected="selected">
					否
				</option>
				<option value="1">
					是
				</option>
			</select>
		</td>				
	</tr>
	<tr>
		<td class="row_tip" height="25">
			试题区分度：
		</td>
		<td>
			<select id="differ" name="differ">
				<option value="0">---请选择---</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select>
		</td>
		<td class="row_tip">
			试题来源：
		</td>
		<td colspan="3">
			<input type="text" id="source" class="editInput" name="source" />
		</td>
	</tr>
	<!-- <tr>
		<td class="row_tip">
			<font class="red_star">*</font>试题属性：
		</td>
		<td colspan="5">
				<textarea class="inp_txt inp_txtsel inp_wm inp_cue" id="txtLoc" name="txtLoc" style="width: 92%" rows="3" readonly="readonly">点击此输入框选择试题属性</textarea>
				<input id="renderSelIds" type="hidden" name="renderSelIds" value=""/><input id="selId" type="hidden" />
		</td>
	</tr>	 -->
	<tr>
		<td colspan="6">
			<table border="0" cellspacing="0" cellpadding="0" width="100%" class="gridtable">
				<tr>
					<td class="row_tip"><font class="red_star">*</font>被收录题库：</td>
					<td>
						<select multiple="multiple" size="4" id="_questType" ondblclick="LeftToRight('_questType','questType');" class="stcx_form">
							<c:forEach items="${questType}" var="questType">
								<option value="${questType.id}">${questType.name}</option>
							</c:forEach>							
						</select>							
					</td>
					<td>
						<a href="#" onclick="LeftToRight('_questType','questType');">添加</a><br/>
						<a href="#" onclick="RightToLeft('_questType','questType');">删除</a><br/>
						<a href="#" onclick="LeftAllRight('_questType','questType');">全部添加</a><br/>
						<a href="#" onclick="RightAllLeft('_questType','questType');">全部删除</a>					
					</td>
					<td>
						<select multiple="multiple" size="4" class="stcx_form" id="questType" name="questType" ondblclick="RightToLeft('_questType','questType');">
						</select>	
					</td>
					
					<td class="row_tip">行业：</td>
					<td>
						<select multiple="multiple" size="4" id="_questPoint2_7" ondblclick="LeftToRight('_questPoint2_7','questPoint2_7');" class="stcx_form">
							<c:forEach items="${questPropPoint2}" var="point2">
								<option value="${point2.id}">${point2.name}</option>
							</c:forEach>
						</select>						
					</td>
					<td>
						<a href="#" onclick="LeftToRight('_questPoint2_7','questPoint2_7');">添加</a><br/>
						<a href="#" onclick="RightToLeft('_questPoint2_7','questPoint2_7');">删除</a><br/>
						<a href="#" onclick="LeftAllRight('_questPoint2_7','questPoint2_7');">全部添加</a><br/>
						<a href="#" onclick="RightAllLeft('_questPoint2_7','questPoint2_7');">全部删除</a>								
					</td>
					<td>
						<select multiple="multiple" size="4"  id="questPoint2_7" name="questPoint2_7" ondblclick="RightToLeft('_questPoint2_7','questPoint2_7');" class="stcx_form">
						</select>						
					</td>						
				</tr>
				<tr>
					<td class="row_tip"><font class="red_star">*</font>知识分类：</td>
					<td>
						<select multiple="multiple" size="4" id="_questCognize_8" ondblclick="LeftToRight('_questCognize_8','questCognize_8');" class="stcx_form">
							<c:forEach items="${questPropCognize}" var="cognize">
								<option value="${cognize.id}">${cognize.name}</option>
							</c:forEach>						
						</select>						
					</td>
					<td>
						<a href="#" onclick="LeftToRight('_questCognize_8','questCognize_8');">添加</a><br/>
						<a href="#" onclick="RightToLeft('_questCognize_8','questCognize_8');">删除</a><br/>
						<a href="#" onclick="LeftAllRight('_questCognize_8','questCognize_8');">全部添加</a><br/>
						<a href="#" onclick="RightAllLeft('_questCognize_8','questCognize_8');">全部删除</a>						
					</td>
					<td>
						<select multiple="multiple" size="4" id="questCognize_8" name="questCognize_8" ondblclick="RightToLeft('_questCognize_8','questCognize_8');" class="stcx_form">
						</select>	
					</td>
					<td class="row_tip"><font class="red_star">*</font>适用对象：</td>
					<td>
						<select multiple="multiple" size="4" id="_questTitle_9" ondblclick="LeftToRight('_questTitle_9','questTitle_9');" class="stcx_form">
							<c:forEach items="${questPropTitle}" var="title">
								<option value="${title.id}">${title.name}</option>
							</c:forEach>						
						</select>						
					</td>
					<td>
						<a href="#" onclick="LeftToRight('_questTitle_9','questTitle_9');">添加</a><br/>
						<a href="#" onclick="RightToLeft('_questTitle_9','questTitle_9');">删除</a><br/>
						<a href="#" onclick="LeftAllRight('_questTitle_9','questTitle_9');">全部添加</a><br/>
						<a href="#" onclick="RightAllLeft('_questTitle_9','questTitle_9');">全部删除</a>						
					</td>
					<td>
						<select multiple="multiple" size="4" id="questTitle_9" name="questTitle_9" ondblclick="RightToLeft('_questTitle_9','questTitle_9');" class="stcx_form">
						</select>					
					</td>							
				</tr>				
			</table>
		</td>
	</tr>
	<tr>
		<td class="row_tip">
			<font class="red_star">*</font>试题内容：
		</td>
		<td colspan="5">
			<textarea id="content" class="editTextarea" name="content" style="width: 92%" rows="3" elname="试题内容" pattern="string" required="1"></textarea>
			<a href="javascript:dialog('adv_edit.jsp',600,430,'content');">高级编辑</a>
		</td>
	</tr>
	<tr>
		<td class="row_tip">
			试题分析：
		</td>
		<td colspan="5">
			<textarea id="analyse" name="analyse" style="width: 92%" class="editTextarea" rows="3" elname="试题分析" pattern="string"></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="6" align="center" class="row_tip">
			<img src="${ctx}/images/tjzst.gif" style="cursor: pointer;" onclick="win1.show()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img src="${ctx}/images/xjsj_bc.gif" onclick="xaction();" style="cursor: pointer;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img src="${ctx}/images/xjsj_fh.gif" onclick="window.location='${ctx}/questionManage/questionManage.do'" style="cursor: pointer;"/>&nbsp;&nbsp;
		</td>
	</tr>
</table>
<hr/>
<table width="100%" border="0" cellpadding="0" cellspacing="0" id="child_questions" class="dataintable">
	<tr>
		<th colspan="2">子试题信息</th>
	</tr>
	<tr>
		<td class="tdTitle" width="80%"><b>试题内容</b></td>
		<td class="tdTitle" width="20%"><b>操作</b></td>
	</tr>
</table>
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
		
<script type="text/javascript">

var win1 = new ChildWin("${label}");

function xaction(){
	var question_content = document.fm1.content.value.getBytes();
	var question_analyse = document.fm1.analyse.value.getBytes();
	var question_source = document.fm1.source.value.getBytes();
	
		var questType_ = document.getElementById('questType');
		var questCognize_ = document.getElementById('questCognize_8');
		var questPoint2_ = document.getElementById('questPoint2_7');
		var questTitle_ = document.getElementById('questTitle_9');
		
		if(questType_.options.length==0){
			alert('请选择被收入题库!');
			return;
		}
		if(questCognize_.options.length==0){
			alert('请选择知识分类!');
			return;
		}
		if(questPoint2_.options.length==0){
			alert('请选择行业!');
		}
		if(questTitle_.options.length==0){
			alert('请选择适用对象!');
			return;
		}
		
		seled(questTitle_);
		seled(questType_);
		seled(questCognize_);
		seled(questPoint2_);
	
	/* var renderSelIds = document.getElementById("renderSelIds").value;
	if(''==renderSelIds) {
		alert('请选择试题属性!');
		return;
	} */
	
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
	
	if(question_source>100){
		alert('试题来源不能大于100个字符或者50个汉字!');
		$("source").focus();
		return;
	}
	
	if(document.getElementById('child_content')==null){
		alert('请添加子试题!');
		return;
	}
	
	if(confirm('您确定保存吗？')){
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
</script>
</body>
</html>