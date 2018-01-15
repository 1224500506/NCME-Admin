<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/prototype.js"></script>
<script type="text/javascript" src="${ctx}/js/question.js"></script>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
</style>
</head>
<body>
<table width="100%" cellpadding="0" cellspacing="2" border="0" class="gtable">
	<tr>
		<td style="color: #FFFFFF;text-align: left">
			修改试题状态
			<select name="quest_status" id="quest_status" onchange="setAuthor(this.value);">
				<option value="S" selected="selected">－请选择－</option>
				<option value="Y">审核通过</option>
				<option value="N">未审核</option>
				<option value="D">禁用试题</option>
				<option value="I">导入试题</option>
			</select>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" onclick="deleteQuestion();" value="删除所选试题" />
		</td>	
	</tr>
</table>
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<form name="listfm" method="post">
	<display:table name="questionList" requestURI="${ctx}/questionManage/adSearchQuestion.do?handle=search" id="qt" style="width:100%;" class="its" decorator="com.hys.exam.displaytag.OverOutWrapper">
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
		<display:column title="状态" style="width:50;text-align:center">
			<c:if test="${qt.state==1}">正常</c:if>
			<c:if test="${qt.state==2}">禁用</c:if>
			<c:if test="${qt.state==3}">已过期</c:if>
			<c:if test="${qt.state==4}">已审核</c:if>
			<c:if test="${qt.state==5}">未审核</c:if>
		</display:column>
		<display:column title="操作" style="width:120;text-align:center">
			<a href="${ctx}/questionManage/viewQuestion.do?id=${qt.id}&handle=ads_v">查看</a> | 
			<a href="${ctx}/questionManage/viewQuestion.do?id=${qt.id}&handle=ads_e">修改</a>
		</display:column>
	</display:table>
	<div id="bgdiv" style="display: none;"></div>
	
	<div id="setAuthor" style="border: #036 solid; padding-top: 30px; width: 500px; height: 200px; position: absolute; left: 250px; top: 150px; background-color: #FFFFFF; display: none; z-index: 20;">
	<ul>
		<li>
			创建者名称：
			<input type="text" id="author" size="40" name="author"/>
			<span style="color: red; padding-left: 5px;">*</span>
		</li>
	</ul>
	<center>
		<input type="button" name="saverr" value="保存" onclick="updateQuestStatus();" />
		<input type="button" name="saverr" value="取消" onclick="hiddenShow();" />
	</center>
	</div>
	</form>
</table>
<div style="display:none;border:1px solid #000000;background-color:#FFFFCC;font-size:12px;position:absolute;padding:2;" id=altlayer></div>
<script type="text/javascript">
function updateQuestStatus(){
	var s = document.getElementById("quest_status").value;
	var author = $('author').value.getBytes();
	if(author>100 || author==0){
		alert('请填写创建者！');
		return;
	}
	
	var flag;
	if(document.listfm.qcid!=null){
		var length = document.listfm.qcid.length;
		if(!document.listfm.qcid.length){
			if(document.listfm.qcid.checked){
				flag=true;
			}else{
				flag=false;
			}
		}else{
			for(var i=0;i<length;i++){
				if(document.listfm.qcid[i].checked==true){
					flag=true;
					break; 
				}
			}
		}
		
		if(!flag){
			if(s=='D'){
				alert('请选择要禁用的试题!');
			}
			if(s=='Y'){
				alert('请选择要过通过审核的试题!');
			}
			if(s=='N'){
				alert('请选择未通过审核的试题!');
			}
			if(s=='I'){
				alert('请选择试题!');
			}
		}else{
			if(confirm('您确定要进行操作吗？')){
				document.listfm.action="${ctx}/questionManage/updateQuestionState.do?type="+s;
				document.listfm.submit();
			}
		}
	}
}

function deleteQuestion(){
	var id = '';
	if(document.listfm.qcid!=null){
		var length = document.listfm.qcid.length;
		if(!document.listfm.qcid.length){
			if(document.listfm.qcid.checked==true){
				id = document.listfm.qcid.value;
			}
		}else{
			for(var i=0;i<length;i++){
				if(document.listfm.qcid[i].checked==true){
					id+=document.listfm.qcid[i].value+',';
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
      window.location = '${ctx}/questionManage/adSearchQuestion.do?handle=list';
   }else{
   		alert('删除失败请检查试题关联!');
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

function setAuthor(s){
	if(s!='S'){
		$('author').value='';
		document.getElementById('setAuthor').style.display='block';
		document.getElementById('bgdiv').style.display='block';
	}else{
		document.getElementById('setAuthor').style.display='none';
		document.getElementById('bgdiv').style.display='none';
		$('author').value='';
	}

}
function hiddenShow(){
	document.getElementById('setAuthor').style.display='none';
	document.getElementById('bgdiv').style.display='none';
	$('author').value='';
}
</script>
</body>
</html>