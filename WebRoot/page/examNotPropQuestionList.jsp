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
<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
</style>
<link media="print" type="text/css" href="${ctx}/css/displaytag/print.css" />

		<link rel="stylesheet" type="text/css" href="${ctx}/css/default090909.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/lay.css">
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/data_all.js"></script>
		<script type="text/javascript" src="${ctx}/js/popupselector5.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.bgiframe.js"></script>
</head>
<body>
<form action="${ctx}/questionManage/SearchNotPropQuestion.do" method="post" name="fmx">
<table class="gridtable" width="100%" cellpadding="0" cellspacing="1" border="0">
	<tr>
		<td class="row_tip" colspan="5" height="25">查询条件</td>
	</tr>
	<tr>
		<td>题型名称：
			<select name="question_label_id" id="question_label_id">
				<option value="-1">---请选择---</option>
				<c:forEach items="${labelList}" var="label">
					<option value="${label.id}" <c:if test="${questionLabelId eq label.id}">selected="selected"</c:if>>${label.name}</option>
				</c:forEach>
			</select>
		</td>
		<td>试题内容：
			<input type="text" name="content" id="content" value="${content}"/>
		</td>
		<td>导入时间:
			<input type="text" name="create_date" value="${createDate}" id="create_date" onClick="WdatePicker()" readonly="readonly">
		</td>				
		<td>创建者:
			<input type="text" name="author" id="author" value="${author}" />
		</td>			
		<td align="center">
			<input type="submit" value="查询试题" />
		</td>				
	</tr>
</table>
</form>
<form name="fm1" method="post" action="${ctx}/questionManage/batchAddQuestionProp.do">
<input name="question_label_id" type="hidden" value="${questionLabelId}"/>
<input name="content" type="hidden" value="${content}"/>
<input name="create_date" type="hidden" value="${createDate}"/>
<input name="author" type="hidden" value="${author}"/>
<table border="0" cellspacing="1" cellpadding="0" width="100%" class="gridtable">
	<tr>
		<td class="row_tip" colspan="8" height="25">试题属性选择</td>
	</tr>
	<tr>
		<td class="row_tip"><font class="red_star">*</font>关联属性</td>
		<td colspan="7">
			<textarea class="inp_txt inp_txtsel inp_wm inp_cue" id="txtLoc" name="txtLoc" style="width: 92%" rows="3" readonly="readonly">点击此输入框选择试题属性</textarea>
			<input id="renderSelIds" type="hidden" name="renderSelIds" value=""/>	
			<input id="selId" type="hidden" />	
		</td>	
	</tr>
	<tr>
		<td colspan="8" height="25" style="vertical-align:middle; text-align:left;">
			<input type="button" onclick="xaction(0);" value="保存关联属性">
			<input type="button" onclick="xaction(5);" value="保存关联属性并加入题库">
			<input type="button" onclick="exportQuestion();" value="导出试题ID">
			<input type="button" onclick="deleteQuestion();" value="删除所选试题" />
		</td>
	</tr>
</table>
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td>
		<display:table name="questionList" requestURI="${ctx}/questionManage/SearchNotPropQuestion.do" id="qt" style="width:100%;" class="its" decorator="com.hys.exam.displaytag.OverOutWrapper">
			<display:column title="<input type='checkbox' name='chkall' id='chkall' value='on' onclick='CheckAll(this.form);'>" style="width:2%;text-align:center">
				<input type="checkbox" name="ids" value="${qt.id}" id="qcid"/>
			</display:column>
			<display:column title="题型" style="width:120px;text-align:center">
			<c:choose>
				<c:when test="${qt.question_label_id eq 1}">单选题(A1)</c:when>
				<c:when test="${qt.question_label_id eq 2}">单选题(A2)</c:when>
				<c:when test="${qt.question_label_id eq 3}">单选题(A3/A4)</c:when>
				<c:when test="${qt.question_label_id eq 9}">单选题(B1)</c:when>
				<c:when test="${qt.question_label_id eq 11}">多选题(X)</c:when>
				<c:when test="${qt.question_label_id eq 12}">填空题</c:when>
				<c:when test="${qt.question_label_id eq 13}">判断题</c:when>
				<c:when test="${qt.question_label_id eq 14}">简答题</c:when>
				<c:when test="${qt.question_label_id eq 18}">名词解释</c:when>
				<c:when test="${qt.question_label_id eq 20}">案例分析题</c:when>
			</c:choose>
			</display:column>
			<display:column property="link2" title="内容" style="white-space:normal;word-break:break-all;overflow:hidden;text-align: left;">
			</display:column>
			<display:column title="导入时间" style="width:120px;text-align:center">${qt.create_date}</display:column>
			<display:column title="创建者" style="width:100px;text-align:center">${qt.author}
			</display:column>
			<display:column title="状态" style="width:90;text-align:center">
				<c:if test="${qt.state==1}">正常</c:if>
				<c:if test="${qt.state==2}">禁用</c:if>
				<c:if test="${qt.state==3}">已过期</c:if>
				<c:if test="${qt.state==4}">已审核</c:if>
				<c:if test="${qt.state==5}">未审核</c:if>
				<c:if test="${qt.state==6}">导入试题</c:if>
			</display:column>
			<display:column>
				<a href="${ctx}/questionManage/viewQuestion.do?id=${qt.id}">修改</a>
			</display:column>
		</display:table>
		</td>
	</tr>
</table>
<div style="display:none;border:1px solid #000000;background-color:#FFFFCC;font-size:12px;position:absolute;padding:2;" id="altlayer"></div>
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
	function xaction(s){		
		var renderSelIds = document.getElementById("renderSelIds").value;
		if((''==renderSelIds)){
			alert('请选择试题属性!');
			return;
		}
		
		var flag;
		
		if(document.fm1.ids!=null){
			var length = document.fm1.ids.length;
			if(!document.fm1.ids.length){
				if(document.fm1.ids.checked){
					flag=true;
				}else{
					flag=false;
				}
			}else{
				for(var i=0;i<length;i++){
					if(document.fm1.ids[i].checked==true){
						flag=true;
						break; 
					}
				}
			}
			if(!flag){
				alert('请选择试题!');
			}else{
				if(confirm('您确定保存吗？')){
					document.fm1.action="${ctx}/questionManage/batchAddQuestionProp.do?state="+s;
					document.fm1.submit();
				}
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
	

document.body.onmousemove=quickalt;
document.body.onmouseover=getalt;
document.body.onmouseout=restorealt;
var tempalt='';
var temp='';

function getalt(e){
	e=window.event||e;   
	var srcElement = e.srcElement||e.target;   
	if(srcElement.title && (srcElement.title!='' || (srcElement.title=='' && temp!=''))){
		
		var mousePos = mousePosition(e);
		altlayer.style.left=mousePos.x;
		altlayer.style.top=mousePos.y+20;
		
		altlayer.style.display='';
		temp = srcElement.title;
		var tmp =srcElement.title.split('_');
		srcElement.title = '';
		if (tmp[0]!="null" && tmp[0]!="undefined") {
			tempalt = "一级学科:"+tmp[0];
		}
		if (tmp[1]!="null" && tmp[1]!="undefined") {
			tempalt += "<br/>二级学科:"+tmp[1];
		}
		if (tmp[2]!="null" && tmp[2]!="undefined") {
			tempalt += "<br/>三级学科:"+tmp[2];
		}
		if (tmp[3]!="null" && tmp[3]!="undefined") {
			tempalt += "<br/>单元:"+tmp[3];
		}
		if (tmp[4]!="null" && tmp[4]!="undefined") {
			tempalt += "<br/>知识点:"+tmp[4];
		}
		if (tmp[5]!="null" && tmp[5]!="undefined") {
			tempalt += "<br/>副知识点:"+tmp[5];
		}
		if (tmp[6]!="null" && tmp[6]!="undefined") {
			tempalt += "<br/>认知水平:"+tmp[6];
		}
		if (tmp[7]!="null" && tmp[7]!="undefined") {
			tempalt += "<br/>试题分类:"+tmp[7];
		}
		altlayer.innerHTML=tempalt;
	}
}
function quickalt(e){
	e=window.event||e;   
	if(altlayer.style.display==''){
		var mousePos = mousePosition(e);
		altlayer.style.left=mousePos.x;
		altlayer.style.top=mousePos.y+10;
	}
}
function restorealt(e){
	e=window.event||e;   
	var srcElement = e.srcElement||e.target;   
	srcElement.title=temp;
	temp = '';
	tempalt='';
	altlayer.style.display='none';
}

function mousePosition(ev){//返回一个类
    if(ev.pageX || ev.pageY){
        return {x:ev.pageX, y:ev.pageY};
    }
    return {
        x:ev.x + document.body.scrollLeft - document.body.clientLeft,
        y:ev.clientY + document.body.scrollTop - document.body.clientTop
    }
}

function exportQuestion(){
	var flag;
	
	if(document.fm1.ids!=null){
		var length = document.fm1.ids.length;
		if(!document.fm1.ids.length){
			if(document.fm1.ids.checked){
				flag=true;
			}else{
				flag=false;
			}
		}else{
			for(var i=0;i<length;i++){
				if(document.fm1.ids[i].checked==true){
					flag=true;
					break; 
				}
			}
		}
		if(!flag){
			alert('请选择试题!');
		}else{
			if(confirm('您确定导出选中试题吗？')){
				document.fm1.action="${ctx}/questionManage/exportQuestion.do";
				document.fm1.submit();
			}
		}
	}
}

function deleteQuestion(){
	var id = '';
	if(document.fm1.qcid!=null){
		var length = document.fm1.qcid.length;
		if(!document.fm1.qcid.length){
			if(document.fm1.qcid.checked==true){
				id = document.fm1.qcid.value;
			}
		}else{
			for(var i=0;i<length;i++){
				if(document.fm1.qcid[i].checked==true){
					id+=document.fm1.qcid[i].value+',';
				}
			}
		}
	}
	
	if(id==''){
		alert('请选择要删除的试题!');
	}else{
		if(confirm('您确定要进行操作吗？')){
			var url = '${ctx}/questionManage/deleteQuestion.do';
			var params = 'idArr=' +id;
			var doAjax = new Ajax.Request(
		    url,
		    {
		      method : 'POST',
		      parameters : params,
		      onComplete : doDelResourceResult
		    }
		    );
		}
	}
}
function doDelResourceResult(originalRequest){
	var result = originalRequest.responseText;
   if(result == 'success'){
      alert('删除成功！');
      window.location = window.location;
   }else{
   		alert('删除失败请检查试题关联!');
   }
}
</script>	
</body>
</html>