<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/question.js"></script>
<script type="text/javascript" src="${ctx}/js/prototype.js"></script>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/default090909.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/lay.css">
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/data_all.js"></script>
		<script type="text/javascript" src="${ctx}/js/popupcheckbox.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.bgiframe.js"></script>
</head>
<body>
<form action="${ctx}/questionManage/adSearchQuestion.do" method="post" name="fmx">
<input type="hidden" name="handle" value="search">
<table class="gridtable" width="100%" cellpadding="0" cellspacing="1" border="0">
	<tr>
		<td class="row_tip" colspan="4" height="25">基础属性</td>
	</tr>
	<tr>
		<td class="row_tip" height="25">题型名称：</td>
		<td>
			<select name="question_label_id" id="question_label_id">
				<option value="-1">---请选择---</option>
				<c:forEach items="${labelList}" var="label">
					<option value="${label.id}" <c:if test="${questionLabelId eq label.id}">selected="selected"</c:if>>${label.name}</option>
				</c:forEach>
			</select>		
		</td>
		<td class="row_tip" height="25">试题状态：</td>
		<td>
			<select id="state" name="state">
				<option value="-1">---请选择---</option>
				<option value="1" <c:if test="${state eq 1}">selected="selected"</c:if>>正常</option>
				<option value="2" <c:if test="${state eq 2}">selected="selected"</c:if>>禁用</option>
				<option value="3" <c:if test="${state eq 3}">selected="selected"</c:if>>已过期</option>
				<option value="4" <c:if test="${state eq 4}">selected="selected"</c:if>>已审核</option>
				<option value="5" <c:if test="${state eq 5}">selected="selected"</c:if>>未审核</option>
			</select>		
		</td>		
	</tr>
	<tr>
		<td class="row_tip" height="25">创建时间：</td>
		<td><input type="text" name="create_date" id="create_date" onClick="WdatePicker()" readonly="readonly"></td>
		<td class="row_tip">审核时间：</td>
		<td><input type="text" name="online_date" id="online_date" onClick="WdatePicker()" readonly="readonly"></td>
	</tr>
	<tr>
		<td class="row_tip" height="25">试题来源：</td>
		<td><input type="text" name="source" id="source"/></td>
		<td class="row_tip">创建者：</td>
		<td><input type="text" name="author" id="author"/></td>			
	</tr>
	<tr>
		<td class="row_tip" height="25">试题内容：</td>
		<td><input type="text" name="content" id="content"/></td>
		<td class="row_tip" height="25">排序规则：</td>
		<td>
			<input type="checkbox" value="1" name="orderItem"/>&nbsp;题型
			<input type="checkbox" value="2" name="orderItem"/>&nbsp;题干
			<input type="checkbox" value="3" name="orderItem"/>&nbsp;创建时间
			<input type="checkbox" value="4" name="orderItem"/>&nbsp;审核时间
		</td>
	</tr>
</table>
<hr/>
<table border="0" cellspacing="1" cellpadding="0" width="100%" class="gridtable">
	<tr>
		<td class="row_tip" colspan="8" height="25">专业属性</td>
	</tr>
	<!-- 
	<tr>
		<td class="row_tip">关联属性</td>	
		<td colspan="7">
			<textarea class="inp_txt inp_txtsel inp_wm inp_cue" id="txtLoc" name="txtLoc" style="width: 92%" rows="3" readonly="readonly">点击此输入框选择试题属性</textarea>
			<input id="renderSelIds" type="hidden" name="renderSelIds" value=""/>	
			<input id="selId" type="hidden" />	
		</td>	
	</tr>
	 -->	
	<tr>
		<td class="row_tip">被收录题库</td>
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
		<td class="row_tip">行业</td>
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
		<td class="row_tip">认知水平</td>
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
		<td class="row_tip">职称</td>
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
	<tr>
		<td colspan="8" height="25" style="vertical-align:middle; text-align:center;">
			<input type="button" onclick="adSearch();" value="查 询">&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="javascript:void(0)" onclick="javascript:history.go(-1)">返回普通查询</a>
		</td>
	</tr>
	<tr>
		<td colspan="8" height="25" style="vertical-align:middle; text-align:center;">
			<font color="red" size="6">注：需审核试题请点击查询按钮~</font>
		</td>
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
					style="position: absolute; z-index: 1778; top: 0pt; left: 554px; display: none;">
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
					style="display: none; position: absolute; z-index: 1779;">
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
					style="display: none; position: absolute; z-index: 1780;">
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
					style="display: none; position: absolute; z-index: 1781;">
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

	function adSearch(){

		var questType_ = document.getElementById('questType');
		var questCognize_ = document.getElementById('questCognize_8');
		var questPoint2_ = document.getElementById('questPoint2_7');
		var questTitle_ = document.getElementById('questTitle_9');
		seled(questTitle_);
		seled(questType_);
		seled(questCognize_);
		seled(questPoint2_);
		
		document.fmx.submit();
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