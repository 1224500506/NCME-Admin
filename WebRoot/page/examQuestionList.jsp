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
<form action="${ctx}/questionManage/questionManage.do" method="post" name="fmx">
<table class="gridtable" width="100%" cellpadding="1" cellspacing="0" border="0">
	<tr>
		<td class="row">题型名称:
			<select name="question_label_id" id="question_label_id">
				<option value="-1" <c:if test="${query.question_label_ids == null}">selected="selected"</c:if>>---请选择---</option>
				<c:forEach items="${labelList}" var="label">
					<option value="${label.id}" <c:if test="${query.question_label_ids eq label.id}">selected="selected"</c:if>>${label.name}</option>
				</c:forEach>
			</select>
		</td>
		<td class="row">试题状态:
			<select id="state" name="state">
				<option value="-1" <c:if test="${query.state eq -1}">selected="selected"</c:if>>---请选择---</option>
				<option value="1" <c:if test="${query.state eq 1}">selected="selected"</c:if>>正常</option>
				<option value="2" <c:if test="${query.state eq 2}">selected="selected"</c:if>>禁用</option>
				<option value="3" <c:if test="${query.state eq 3}">selected="selected"</c:if>>已过期</option>
				<option value="4" <c:if test="${query.state eq 4}">selected="selected"</c:if>>已审核</option>
				<option value="5" <c:if test="${query.state eq 5}">selected="selected"</c:if>>未审核</option>
			</select>
		</td>
		<td class="row">创建时间:
			<input type="text" name="create_date" id="create_date" onClick="WdatePicker()" readonly="readonly">
		</td>
		<td class="row">创建者:
			<input type="text" name="author" id="author" value="${query.author}" />
		</td>	
	</tr>
	<tr>
		<td class="row">试题内容:
			<input type="text" name="content" id="content" value="${query.content}"/>
		</td>
		<td class="row">排序规则:
			<input type="checkbox" value="1" name="orderItem"/>题型
			<input type="checkbox" value="2" name="orderItem"/>题干
			<input type="checkbox" value="3" name="orderItem"/>创建时间
			<input type="checkbox" value="4" name="orderItem"/>审核时间
		</td>
		<td class="row">是否为多媒体试题:
			<select id="isnot_multimedia" name="isnot_multimedia">
				<option value="-1" <c:if test="${query.isnot_multimedia == null}">selected="selected"</c:if>>---请选择---</option>
				<option value="0" <c:if test="${query.isnot_multimedia eq 0}">selected="selected"</c:if>>否</option>
				<option value="1" <c:if test="${query.isnot_multimedia eq 1}">selected="selected"</c:if>>是</option>
			</select>
		</td>	
		<td class="row">
			<input type="submit" value="查询试题" />
			<input type="button" onclick="adSearch();" value="高级查询">
		</td>	
	</tr>
</table>
</form>
<table width="100%" cellpadding="0" cellspacing="2" border="0" class="gtable">
	<tr>
		<td><a href="${ctx}/questionManage/swapQuestionLabel.do?questionLabel=1">直接添加试题</a></td>
		<td><a href="${ctx}/page/examImportQuestion.jsp">批量导入试题</a></td>
		<!-- <td><a href="#" onclick="viewQuestion();">导入试题属性添加</a></td> -->
	</tr>
</table>
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<form name="listfm" method="post">
	<display:table name="questionList" requestURI="${ctx}/questionManage/questionManage.do" id="qt" style="width:100%;" class="its" decorator="com.hys.exam.displaytag.OverOutWrapper">
		<display:column title="<input type='checkbox' id='chkall' value='' onclick='CheckAll(this.form);'>" style="width:2%;text-align:center">
			<input type="checkbox" name="ids" value="${qt.id}" id="qcid"/>
		</display:column>
		<display:column title="序号" style="text-align:center">
			${qt_rowNum}
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
		<display:column property="author" title="创建者">
		</display:column>
		<display:column title="状态" style="width:50;text-align:center">
			<c:if test="${qt.state==1}">正常</c:if>
			<c:if test="${qt.state==2}">禁用</c:if>
			<c:if test="${qt.state==3}">已过期</c:if>
			<c:if test="${qt.state==4}">已审核</c:if>
			<c:if test="${qt.state==5}">未审核</c:if>
		</display:column>
		<display:column title="操作" style="width:120;text-align:center">
			<a href="${ctx}/questionManage/viewQuestion.do?id=${qt.id}&handle=v">查看</a> | 
			<a href="${ctx}/questionManage/viewQuestion.do?id=${qt.id}&handle=e">修改</a>
		</display:column>
	</display:table>
	</form>
</table>
<div style="display:none;border:1px solid #000000;background-color:#FFFFCC;font-size:12px;position:absolute;padding:2;" id=altlayer></div>
<script type="text/javascript">

function viewQuestion(){
	window.location.href="${ctx}/questionManage/SearchNotPropQuestion.do";
}

function adSearch(){
	window.location.href="${ctx}/questionManage/adSearchQuestion.do?handle=jump";
}

function updateQuestStatus(){
		var s = document.getElementById("quest_status").value;
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
					
			}else{
				if(confirm('您确定要进行操作吗？')){
					document.listfm.action="${ctx}/questionManage/updateQuestionState.do?type="+s;
					document.listfm.submit();
				}
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
			tempalt += "<br/>行业:"+tmp[5];
		}
		if (tmp[6]!="null" && tmp[6]!="undefined") {
			tempalt += "<br/>知识分类:"+tmp[6];
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
</script>
</body>
</html>