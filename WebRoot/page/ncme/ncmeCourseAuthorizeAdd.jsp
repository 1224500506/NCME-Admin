<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="now" class="java.util.Date" />   
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/default090909.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/lay.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">

<link rel="stylesheet" href="${ctx}/js/Validform_v5.3/css/valid.css" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/bootstrap/easyui.css">
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/Validform_v5.3.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/icon.css">
<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/UI/locale/easyui-lang-zh_CN.js"></script>   
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>



</head>
<body>
<form class="fm1" id="fm1" name="fm1" action="${ctx}/ncme/ncmeCourseAuthorizeSave.do" method="post">
<input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="${sessionScope['org.apache.struts.action.TOKEN']}" />
<table border="0" cellspacing="1" cellpadding="0" width="100%" class="gridtable">
	<tr>
		<td align="center" colspan="3" class="row_tip" >
			课程授权
		</td>
	</tr>
	<tr>
		<td class="row_tip"   align="right" width="20%">
			<font class="red_star">*</font>课程：
		</td>
		<td width="60%">
		  <div name="courseName" id="courseName" style="display:inline-block;float:left;">
          </div>
		  <div style="display:inline-block;float:right;">
		    <button onclick="javascript:dialog()" class="btn_03s">选择课程</button>
		    <button onclick="javascript:clearCourse()" class="btn_03s" >清除</button>	
          </div>
          <input type="hidden" id="courseIds" name="courseIds" datatype="*" nullmsg="请选择课程！"/>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>	
	</tr>
	
	<tr>
		<td class="row_tip"  align="right">
			<font class="red_star">*</font>授权年度：
		</td>
		<td width="50%">
	      <input type="text" name="creditYear" class="editInput" id="creditYear" onclick="setCreditYear()" maxlength="4" datatype="n4-4" nullmsg="请输入年度！" errormsg="请输入数字类型的4位年度！" value="<fmt:formatDate value="${now}" pattern="yyyy"/>" >
		</td>
        <td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip"  align="right">
			<font class="red_star">&nbsp;</font>项目编号：
		</td>
		<td>
		<input type="text" id="courseSerial" class="editInput" name="courseSerial" maxlength="50" size="25" value="" />
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip"  align="right">
			<font class="red_star">*</font>授权类别：
		</td>
		<td>
		
		    <input name="courseTypeName" id="courseTypeName" size="70" class="editInput" readonly datatype="*" nullmsg="请选择授权类别！"/>
		    <input type="hidden" id="courseTypeIds" name="courseTypeIds" />
		    <button onclick="javascript:dialogCourseType()" class="btn_03s">选择授权类别</button>&nbsp;
		    <button onclick="javascript:clearCourseType()" class="btn_03s" >清除</button>	
          	
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip"  align="right">
			<font class="red_star">*</font>授权级别：
		</td>
		<td align="left">
		<select id="creditType" name="creditType" datatype="*" nullmsg="请选择授权级别！" errormsg="请选择授权级别！" >
		      <c:forEach items="${ncmeCreditTypeList}" var="list">
		        <option value="${list.creditType}" <c:if test="${param.creditType eq  list.creditType}">selected</c:if> >${list.creditName}
		      </c:forEach>
		    </select>
		</td>
	    <td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip"  align="right">
			<font class="red_star">*</font>授予学时：
		</td>
		<td>
		<input type="text" id="creditNum" class="editInput" name="creditNum" maxlength="2" size="25" 
			datatype="*" nullmsg="请输入授权学时！" errormsg="请输入授权学时！" />
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>	
	</tr>
	
	<tr>
		<td class="row_tip"  align="right">
			<font class="red_star">*</font>授权开始时间：
		</td>
		<td align="left">
			<input type="text" name="startDate" id="startDate" class="editInput"
				datatype="*" nullmsg="请选择授权开始时间！" onClick="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\');}'})" />
		</td>
	    <td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	
	<tr>
		<td class="row_tip"  align="right">
			<font class="red_star">*</font>授权结束时间：
		</td>
		<td align="left">
			<input type="text" name="endDate" id="endDate" class="editInput"
				datatype="*" nullmsg="请选择授权结束时间！" onClick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\');}'})" />
		</td>
	    <td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<!-- 
	<tr>
		<td class="row_tip"  align="right">
			<font class="red_star">*</font>授予学时：
		</td>
		<td>
		<input type="text" id="creditHours" class="editInput" name="creditHours" maxlength="3" size="25" datatype="n" nullmsg="请输入授予学时！" errormsg="请输入数字类型的授予学时！" value=""/>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>	
	</tr>
	 -->
    
    <!-- 
	<tr>	
	<td class="row_tip"  align="right">
			<font class="red_star">*</font>机构：
		</td>
		<td>
		<div name="paperName" id="paperName" style="display:inline-block;float:left;">
			<select multiple="multiple" size="15" id="organIdLeft" name="organIdLeft" ondblclick="LeftToRight('organIdLeft','organId');" class="stcx_form2">
				<c:forEach items="${ncmeAdminOrganList}" var="list">
				  <option value="${list.organId}">${list.name}
				</c:forEach>
			</select>
			<a href="#" onclick="LeftToRight('organIdLeft','organId');">添加</a><br/>
		    <a href="#" onclick="RightToLeft('organIdLeft','organId');">删除</a><br/>
			<a href="#" onclick="LeftAllRight('organIdLeft','organId');">全部添加</a><br/>
			<a href="#" onclick="RightAllLeft('organIdLeft','organId');">全部删除</a>
		</div>
		<select multiple="multiple" size="15" id="organId" name="organId" ondblclick="RightToLeft('organIdLeft','organId');" class="stcx_form2" datatype="org" nullmsg="请选择机构！"></select>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>	
	</tr>
	-->
	<!-- 站点 begin-->
	<tr>	
		<td class="row_tip"  align="right">
			<font class="red_star">*</font>站点：
		</td>
		<td>
		<div  style="display:inline-block;float:left;width:420px" >
			<select multiple="multiple" size="15" id="siteIdLeft" name="siteIdLeft" ondblclick="LeftToRight('siteIdLeft','siteId');" class="stcx_form2">
				<c:forEach items="${systemSiteList}" var="site">
				  <option value="${site.id}">&nbsp;&nbsp;${site.domainName}(${site.aliasName })
				</c:forEach>
			</select>
			<span>
			<a href="#" onclick="LeftToRight('siteIdLeft','siteId');">&nbsp;&nbsp;添加</a><br/><br/>
		    <a href="#" onclick="RightToLeft('siteIdLeft','siteId');">&nbsp;&nbsp;删除</a><br/><br/>
			<a href="#" onclick="LeftAllRight('siteIdLeft','siteId');">&nbsp;&nbsp;全部添加</a><br/><br/>
			<a href="#" onclick="RightAllLeft('siteIdLeft','siteId');">&nbsp;&nbsp;全部删除</a><br/>
			</span>
		</div>
		<select multiple="multiple" size="15" id="siteId" name="siteId" ondblclick="RightToLeft('siteIdLeft','siteId');" class="stcx_form2" datatype="org" nullmsg="请至少选择一个站点！"></select>
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>	
	</tr>
	<!-- 站点 end-->
	<tr>
		<td class="row_tip"  align="right">
			重学标志：
		</td>
		<td>
		<select id="reStudyFlag" name="reStudyFlag" >
		  <option value="1" >不同年份可以重复学习
		  <option value="0" >不同年份不能重复学习
		</select>
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>	
	</tr>
	<tr>
		<td colspan="6" align="center" class="row_tip">
		    <input type="submit" class="btn_03s" value="保 存" />
		    <input type="button" class="btn_03s" value="返 回" onclick="javascript:history.back();"/>
		</td>
	</tr>
</table>

<div id="dlg" class="easyui-dialog" title="关联课程" closed="true">
  <iframe src="" width="1200" height="500" id="dialogFrame" name="dialogFrame"></iframe>
</div>

<div id="dlg-coures-type" class="easyui-dialog" title="选择授权类别" closed="true">
	<iframe src="" width="450" height="300" id="dialogFrame-coures-type" name="dialogFrame-coures-type" frameborder="0"></iframe>
</div>

</form>
<script type="text/javascript">
	 $(".fm1").Validform({
      datatype:{//自定义datatype类型
	    "org":function(gets,obj,curform,datatype){
	      if(obj[0].options.length > 0){
	        $(obj).children().each(function(){
                this.selected = true;
              });
	        return ture;
	      }
	      return false;
		}
      }
    });

function LeftToRight(_lid,_rid){
	var lid = document.getElementById(_lid);
	var rid = document.getElementById(_rid);
	var len_left = lid.options.length;
	for(i=0;i<len_left;i++){
		if(lid.options[i].selected==true){
			var len_right = rid.options.length;
			rid.options[len_right]=new Option(lid.options[i].text,lid.options[i].value);
			lid.remove(lid.selectedIndex);
			break;
		}
	}
}

function RightToLeft(_lid,_rid){
	var lid = document.getElementById(_lid);
	var rid = document.getElementById(_rid);
	var len_right = rid.options.length;
	for(i=0;i<len_right;i++){
		if(rid.options[i].selected==true){
			var len_left = lid.options.length;
			lid.options[len_left]=new Option(rid.options[i].text,rid.options[i].value);
			rid.remove(rid.selectedIndex);
			break;
		}
	}
}
function LeftAllRight(_lid,_rid){
	var lid = document.getElementById(_lid);
	var rid = document.getElementById(_rid);
	var len_left = lid.options.length;
	var len_right = rid.options.length;
	for(i=0;i<len_left;i++){
		rid.options[len_right++]=new Option(lid.options[i].text,lid.options[i].value);
	}
	lid.options.length=0;
}

function RightAllLeft(_lid,_rid){
	var lid = document.getElementById(_lid);
	var rid = document.getElementById(_rid);
	var len_left = lid.options.length;
	var len_right = rid.options.length;
	for(i=0;i<len_right;i++){
		lid.options[len_left++]=new Option(rid.options[i].text,rid.options[i].value);
	}
	rid.options.length=0;	
}
function dialog(){
	  //$( "#dialogFrame" ).attr('src','${ctx}/page/ncme/ncmeCourseIndex.jsp');
	  $( "#dialogFrame" ).attr('src','${ctx}/ncme/ncmeStudyCourseView.do?status=1');
	  $('#dlg').dialog('open');
	}

//课程类型弹出框
function dialogCourseType(){
	  $("#dialogFrame-coures-type" ).attr('src','${ctx}/course/studyCourseTypeAdd.do?method=selectCourseType');
		//授权类别弹出层
	    $("#dlg-coures-type" ).dialog({
			autoOpen: false,
		    height: 350,
		    width: 500,
		    modal: true,//true遮蔽
		    iconCls:'icon-search', //左上角图标,icon文件夹下图标
		    close: function() {
		    	allFields.val( "" ).removeClass( "ui-state-error" );
		    }
		 });
	  $("#dlg-coures-type").dialog('open');
}

//关闭
function dialogClose(){
	$("#dlg-coures-type").dialog( "close" );
}

$(function() {
		//弹出层
	    $( "#dlg" ).dialog({
		autoOpen: false,
	    height: 500,
	    width: 1200,
	    modal: true,//true遮蔽
	    iconCls:'icon-search', //左上角图标,icon文件夹下图标
	    close: function() {
	    	allFields.val( "" ).removeClass( "ui-state-error" );
	    }
	    });
		
});

function setCourse(id,name){
  var courseIds = $( "#courseIds" ).val();
  var courseArray = courseIds.split(',');

  for(i=0;i<courseArray.length;++i){
    if(courseArray[i] == id){
      return ;
    }
  }

  if($( "#courseName" ).html() == ""){
    $( "#courseName" ).html(name);
  }else{
    $( "#courseName" ).html($( "#courseName" ).html() + ',' + name);
  }

  if($( "#courseIds" ).val() == ""){
    $( "#courseIds" ).val( id )
  }else{
    $( "#courseIds" ).val($( "#courseIds" ).val() + ',' + id)
  }
}

function clearCourse(){
  $( "#courseName" ).html("");
  $( "#courseIds" ).val("");
}

function clearCourseType(){
	$("#courseTypeName").val("");
	$("#courseTypeIds").val("");
}


function setCreditYear(){
	WdatePicker({
	    onpicked:function(){
			$('#creditYear').val();
	    },
	    minDate:'%y',		//不能早于当前年
	    dateFmt:'yyyy'
	});
}
</script>
</body>
</html>