	<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/js/question.js"></script>
<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.bgiframe.js"></script>

<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
</style>
<style type="text/css"> 
		body{font-size:12px;}
		.selectItemcont{padding:8px;}
		#selectItem{background:#FFF;position:absolute;top:0px;left:center;border:1px solid #000;overflow:hidden;width:680px;z-index:1000;}
		.selectItemtit{line-height:20px;height:20px;margin:1px;padding-left:12px;}
		.bgc_ccc{background:#E88E22;}
		.selectItemleft{float:left;margin:0px;padding:0px;font-size:12px;font-weight:bold;color:#fff;}
		.selectItemright{float:right;cursor:pointer;color:#fff;}
		.selectItemcls{clear:both;font-size:0px;height:0px;overflow:hidden;}
		.selectItemhidden{display:none;}
		
		
		.selectItemcont_applicable{padding:8px;}
		#selectItem_applicable{background:#FFF;position:absolute;top:0px;left:center;border:1px solid #000;overflow:hidden;width:680px;z-index:1000;}
		.selectItemtit_applicable{line-height:20px;height:20px;margin:1px;padding-left:12px;}
		.bgc_ccc_applicable{background:#E88E22;}
		.selectItemleft_applicable{float:left;margin:0px;padding:0px;font-size:12px;font-weight:bold;color:#fff;}
		.selectItemright_applicable{float:right;cursor:pointer;color:#fff;}
		.selectItemcls_applicable{clear:both;font-size:0px;height:0px;overflow:hidden;}
		.selectItemhidden_applicable{display:none;}
		
		.selectItemcont_know{padding:8px;}
		#selectItem_know{background:#FFF;position:absolute;top:0px;left:center;border:1px solid #000;overflow:hidden;width:680px;z-index:1000;}
		.selectItemtit_know{line-height:20px;height:20px;margin:1px;padding-left:12px;}
		.bgc_ccc_know{background:#E88E22;}
		.selectItemleft_know{float:left;margin:0px;padding:0px;font-size:12px;font-weight:bold;color:#fff;}
		.selectItemright_know{float:right;cursor:pointer;color:#fff;}
		.selectItemcls_know{clear:both;font-size:0px;height:0px;overflow:hidden;}
		.selectItemhidden_know{display:none;}
		
	</style>
	
	<script type="text/javascript">
		
	    jQuery.fn.selectCourseProp = function (targetId) {
	        var _seft = this;
	        var temp_value = '';
	        var temp_id = '';
	        var targetId = $(targetId);
	        this.click(function () {
	            var A_top = $(this).offset().top + $(this).outerHeight(true);  //  1
	            var A_left = $(this).offset().left;
	            targetId.bgiframe();
	            targetId.show().css({ "position": "absolute", "top": A_top + "px", "left": A_left + "px" });
	        });
	        
	
	        targetId.find("#selectItemClose").click(function () {
	            targetId.hide();
	        });
	        
	        targetId.find("#selectItemClose_applicable").click(function () {
	            targetId.hide();
	        });
	        
	        targetId.find("#selectItemClose_know").click(function () {
	            targetId.hide();
	        });
	        
	        
	        //清空
	        targetId.find("#selectItemClear").click(function () {
	            $("#obj_industry").val('');
	            $("#industryIds").val('');
	            temp_value = '';
	            temp_id = '';
	            targetId.find(":checkbox").attr("checked", false);
	        });
	        
	        targetId.find("#selectItemClear_applicable").click(function () {
	            $("#obj_applicable").val('');
	            $("#applicableIds").val('');
	            temp_value = '';
	            temp_id = '';
	            targetId.find(":checkbox").attr("checked", false);
	        });
	        
	        targetId.find("#selectItemClear_know").click(function () {
	            $("#obj_know").val('');
	            $("#knowIds").val('');
	            temp_value = '';
	            temp_id = '';
	            targetId.find(":checkbox").attr("checked", false);
	        });
	        
	
	        targetId.find("#selectSub :checkbox").click(function () {
	            targetId.find(":checkbox").attr("checked", false);
	            $(this).attr("checked", true);
	            var value = $(this).val();
	            var name = value.substring(value.indexOf("-")+1);
	            if(temp_value.indexOf(name) == -1){
	            	temp_value = temp_value + name + ',';
	            	temp_id = temp_id + value.substring(0,value.indexOf("-")) + ',';
	            }
	            _seft.val(temp_value);
	            $("#industryIds").val(temp_id);
	            //targetId.hide();
	        });
	        
	        targetId.find("#selectSub_applicable :checkbox").click(function () {
	            targetId.find(":checkbox").attr("checked", false);
	            $(this).attr("checked", true);
	            var value = $(this).val();
	            var name = value.substring(value.indexOf("-")+1);
	            if(temp_value.indexOf(name) == -1){
	            	temp_value = temp_value + name + ',';
	            	temp_id = temp_id + value.substring(0,value.indexOf("-")) + ',';
	            }
	            _seft.val(temp_value);
	            $("#applicableIds").val(temp_id);
	            //targetId.hide();
	        });
	        
	        targetId.find("#selectSub_know :checkbox").click(function () {
	            targetId.find(":checkbox").attr("checked", false);
	            $(this).attr("checked", true);
	            var value = $(this).val();
	            var name = value.substring(value.indexOf("-")+1);
	            if(temp_value.indexOf(name) == -1){
	            	temp_value = temp_value + name + ',';
	            	temp_id = temp_id + value.substring(0,value.indexOf("-")) + ',';
	            }
	            _seft.val(temp_value);
	            $("#knowIds").val(temp_id);
	            //targetId.hide();
	        });
	        
	
	        $(document).click(function (event) {
	            if (event.target.id != _seft.selector.substring(1)) {
	                targetId.hide();
	            }
	        });
	
	        targetId.click(function (e) {
	            e.stopPropagation(); //  2
	        });
	
	        return this;
	    }
	
	    $(function () {
	        //行业
	        $("#obj_industry").selectCourseProp("#selectItem");
	        //适用对象
	        $("#obj_applicable").selectCourseProp("#selectItem_applicable");
	        //知识分类
	        $("#obj_know").selectCourseProp("#selectItem_know");
	    });
	    </script>
</head>
<body>
<form action="" method="post" id="fmx" name="fmx">

<table class="gridtable" width="100%" cellpadding="1" cellspacing="0" border="0">
	<tr>
		<td class="row">课件名称:
			<input type="text" name="queryName" id="queryName" maxlength="100" value="${param.queryName}">
		</td>
		<td class="row">授课老师:
			<input type="text" name="queryTeacherName" id="queryTeacherName" maxlength="20" value="${param.queryTeacherName}">
		</td>
		<td class="row">关键字:
			<input type="text" name="queryKeyWord" id="queryKeyWord" maxlength="250" value="${param.queryKeyWord}" />
		</td>
		<td class="row">制作年份:
			<input type="checkbox" name="queryYear" id="queryYear" value="2014" <c:if test="${param.queryYear eq '2014'}">checked</c:if> /><label for="queryYear">2014</label>
		</td>	
	</tr>
	<tr>
		<td class="row" colspan="4">行业:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="text" name="obj_industry" id="obj_industry" readonly size="150" value="${obj_industry }"/>
		<input type="hidden" id="industryIds" name="industryIds" value="${industryIds }"/>
		 <div id="selectItem" class="selectItemhidden"> 
		 <div id="selectItemAd" class="selectItemtit bgc_ccc"> 
		  <h2 id="selectItemTitle" class="selectItemleft">行业选择</h2>
		  
		  <div id="selectItemClose" class="selectItemright">确定&nbsp;&nbsp;&nbsp;</div>
		  <div id="selectItemClear" class="selectItemright">清空&nbsp;&nbsp;&nbsp;</div>
		 </div> 
		 <div id="selectItemCount" class="selectItemcont"> 
			  <div id="selectSub"> 
				   <c:forEach items="${industryList}" var="ind" varStatus="status">
						<input type="checkbox" id="industryId_${ind.id}" value="${ind.id}-${ind.name}" />
						<label for="industryId_${ind.id}">${ind.name}</label>
						<c:if test="${status.index %8 == 7 }"><br/></c:if>
					</c:forEach>
			  </div> 
		 </div> 
		</div> 
		</td>
	</tr>
	<tr>
		<td class="row" colspan="4">适用对象:
		<input type="text" name="obj_applicable" id="obj_applicable" readonly size="150" value="${obj_applicable }"/>
		<input type="hidden" id="applicableIds" name="applicableIds" value="${applicableIds }" />
		 <div id="selectItem_applicable" class="selectItemhidden_applicable"> 
		 <div id="selectItemAd_applicable" class="selectItemtit_applicable bgc_ccc_applicable"> 
		  <h2 id="selectItemTitle_applicable" class="selectItemleft_applicable">适用对象选择</h2>
		  
		  <div id="selectItemClose_applicable" class="selectItemright_applicable">确定&nbsp;&nbsp;&nbsp;</div>
		  <div id="selectItemClear_applicable" class="selectItemright_applicable">清空&nbsp;&nbsp;&nbsp;</div>
		 </div> 
		 <div id="selectItemCount_applicable" class="selectItemcont_applicable"> 
			  <div id="selectSub_applicable"> 
				   <c:forEach items="${applicableList}" var="app" varStatus="st">
						<input type="checkbox" id="applicableIds_${app.id}" value="${app.id}-${app.name}" />
						<label for="applicableIds_${app.id}">${app.name}</label>
						<c:if test="${st.index >0 && st.index %3 == 2 }"><br/></c:if>
					</c:forEach>
			  </div> 
		 </div> 
		</div> 
		 
		</td>
	</tr>
	<tr>
		<td class="row" colspan="4">知识分类:
		<input type="text" name="obj_know" id="obj_know" readonly size="150" value="${obj_know }"/>
		<input type="hidden" id="knowIds" name="knowIds" value="${knowIds }" />
		 <div id="selectItem_know" class="selectItemhidden_know"> 
		 <div id="selectItemAd_know" class="selectItemtit_know bgc_ccc_know"> 
		  <h2 id="selectItemTitle_know" class="selectItemleft_know">知识分类选择</h2>
		  
		  <div id="selectItemClose_know" class="selectItemright_know">确定&nbsp;&nbsp;&nbsp;</div>
		  <div id="selectItemClear_know" class="selectItemright_know">清空&nbsp;&nbsp;&nbsp;</div>
		 </div> 
		 <div id="selectItemCount_know" class="selectItemcont_know"> 
			  <div id="selectSub_know"> 
				   <c:forEach items="${knowList}" var="know" varStatus="stat">
						<input type="checkbox" id="knowIds_${know.id}" value="${know.id}-${know.name}" />
						<label for="knowIds_${know.id}">${know.name}</label>
						<c:if test="${stat.index >0 && stat.index %5 == 4 }"><br/></c:if>
					</c:forEach>
			  </div> 
		 </div> 
		</div> 
		 
		</td>
	</tr>
	<tr>
		<td class="row">
		</td>
		<td class="row">
		</td>
		<td class="row">
		</td>
		<td class="row">
			<input type="button" onclick="query();" class="btn_03s" value="查询课件" />
			<input type="button" class="btn_03s" value="添加课件" onclick="javascript:window.location='${ctx}/course/coursewareAdd.do'" />
			<input type="button" class="btn_03s" value="删除课件" onclick="javascript:deleteStudyCourseware();" />
			<input type="button" class="btn_03s" value="导入课件" onclick="window.location.href='${ctx}/page/course/coursewareImport.jsp'" />
		</td>	
	</tr>
</table>
</form>
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<form id="listfm" name="listfm" method="post" action="${ctx}/course/coursewareDelete.do">
	<display:table name="${StudyCoursewareList}" requestURI="${ctx}/course/coursewareList.do" id="qt" style="width:100%;" class="its" decorator="com.hys.exam.displaytag.StudyCoursewareDecorator">
		<display:column title="<input type='checkbox' id='chkall' value='' onclick='CheckAll(this.form);'>" style="width:2%;text-align:center">
			<input type="checkbox" name="ids" value="${qt.id}" id="qcid"/>
		</display:column>
		<display:column title="序号" style="width:2%;text-align:center">
			${qt_rowNum}
		</display:column>
		<display:column title="ID" property="id" style="width:5%;text-align:center" />
		<display:column title="课件名称" property="name" style="width:15%;">
		</display:column>
		<!-- 
		<display:column property="decoratorTitle" title="行业" style="width:10%; white-space:normal;word-break:break-all;overflow:hidden;text-align: left;">
		</display:column> -->
		<display:column title="知识分类" style="width:10%;text-align:center">
			<c:forEach items="${qt.knowList}" var="list">
				${list.name}&nbsp;&nbsp;
			</c:forEach>
		</display:column>
		<display:column title="授课老师" property="teacherName" style="width:5%;text-align:center">
		</display:column>
		<display:column title="时长" property="times" style="width:2%;text-align:center">
		</display:column>		
		<display:column title="类型" style="width:5%;text-align:center">
		<c:if test="${qt.type eq 1}">单屏课程</c:if>
		<c:if test="${qt.type eq 2}">三屏课程</c:if>
		<c:if test="${qt.type eq 3}">flash课程</c:if>
		<c:if test="${qt.type eq 4}">静态页面</c:if>
		</display:column>
		<display:column title="制作年份" property="makeYear" style="width:5%;text-align:center">
		</display:column>
		<display:column title="操作" style="width:10%;text-align:center">
			<a href="${ctx}/course/coursewareView.do?id=${qt.id}">查看</a> | 
			<a href="${ctx}/course/coursewareUpdate.do?id=${qt.id}">修改</a>
			<a href="${ctx}/course/coursewarePreview.do?id=${qt.id}" target="_blank">预览</a>
		</display:column>
	</display:table>
    <input type="hidden" name="org.apache.struts.taglib.html.TOKEN"  value="${sessionScope['org.apache.struts.action.TOKEN']}" />
	</form>
</table>
<div style="display:none;border:1px solid #000000;background-color:#FFFFCC;font-size:12px;position:absolute;padding:2;" id=altlayer></div>
<script type="text/javascript">
	<c:if test="${MESSAGE !=null && MESSAGE != ''}">
        alert('${MESSAGE}');
        <c:set var="MESSAGE" scope="session" value=""></c:set>
	</c:if>
</script>
</body>
</html>
<script type="text/javascript">
function deleteStudyCourseware(){
  if($("input[name='ids']:checked").length == 0){
    alert('请选择需要删除的课件!');
    return ;
  }

  if(confirm("确认删除!")){
    $("#listfm").submit();
  }
}

function query(){
	document.fmx.action = "${ctx}/course/coursewareList.do" ;
	document.fmx.submit() ;
}

function checkAll(element){
	$("input[name='ids']").each(function(){
		$(this).attr("checked", element.checked) ;
	});
}

document.body.onmousemove=quickalt;
document.body.onmouseover=getalt;
document.body.onmouseout=restorealt;
var tempalt='';
var temp='';

function getalt(e){
	e=window.event||e;   
	var srcElement = e.srcElement||e.target;   
	if(srcElement.title && srcElement.title!=''){
		var mousePos = mousePosition(e);
		altlayer.style.left=mousePos.x;
		altlayer.style.top=mousePos.y+20;
		altlayer.style.display='';
		temp = srcElement.title;
		srcElement.title = '';
		tempalt = "行业:"+ temp;
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

function mousePosition(ev){
    if(ev.pageX || ev.pageY){
        return {x:ev.pageX, y:ev.pageY};
    }
    return {
        x:ev.x + document.body.scrollLeft - document.body.clientLeft,
        y:ev.clientY + document.body.scrollTop - document.body.clientTop
    }
}
</script>
