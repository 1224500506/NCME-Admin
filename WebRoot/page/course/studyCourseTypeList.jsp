<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>课程管理</title>
		<%@include file="/commons/taglibs.jsp"%>
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/button.css" rel="stylesheet" type="text/css" />
		<style media="all" type="text/css">
			@import url("${ctx}/css/displaytag/site.css");
			@import url("${ctx}/css/displaytag/screen.css");
		</style>
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/bootstrap/easyui.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/icon.css">

		<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/locale/easyui-lang-zh_CN.js"></script>        
	</head>
	<body>
		<div style="padding-left:12px;width:99%;">
			<form action="" method="post" id="fmx" name="fmx">
			<input type="hidden" id="curTypeId" name="curTypeId" value="${param.curTypeId}" />
			<input type="hidden" id="moveId" name="moveId" value="" />
			<input type="hidden" id="moveFlag" name="moveFlag" value="" />
			<input type="hidden" name="org.apache.struts.taglib.html.TOKEN"  value="${sessionScope['org.apache.struts.action.TOKEN']}" />
			<table class="gridtable" cellpadding="1" cellspacing="0" border="0" width="100%">
				<tr>
					<td class="row">课程分类名称:
						<input type="text" id="queryStudyCourseTypeName" name="queryStudyCourseTypeName" maxlength="100" value="${param.queryStudyCourseTypeName}" />
					</td>
					<td class="row">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td class="row">
						&nbsp;
					</td>
					<td class="row">
						<input type="submit" class="btn_03s" value="查 询"/>
						<input type="button" class="btn_03s" value="添加课程分类" onclick="javascript:window.location='${ctx}/course/studyCourseTypeAdd.do?curTypeId=${param.curTypeId}'" />
						<input type="button" class="btn_03s" value="删除课程分类" onclick="javascript:deleteStudyCourseType();" />
					</td>
				</tr>
			</table>
			</form>
			<br />
		</div>
		<div>
			<center>
				<form id="listfm" name="listfm" method="post" action="${ctx}/course/studyCourseTypeDelete.do">
				<display:table id="row" name="${StudyCourseTypeList}" requestURI="${ctx}/course/studyCourseTypeList.do" style="font-size:12px;width:98%;" class="ITS">
					<display:caption>查询结果</display:caption>
					<display:column title="<input type='checkbox' name='chkall' id='chkall' value='on' onclick='checkAll(this);'>" style="width:2%;text-align:center">
			            <input type="checkbox" name="ids" value="${row.id}" id="qcid"/>
		            </display:column>

					<display:column property="courseTypeName" title="课程分类名称" style="text-align:center" />
					<display:column property="studyCourse" title="课程分类课程数" style="text-align:center" />
					<display:column property="allClassHours" title="总学时" style="text-align:center" />
					<display:column title="类型" style="text-align:center" >
					  <c:choose>
					    <c:when test="${row.type eq 1}">培训课程级</c:when>
					    <c:when test="${row.type eq 2}">分类级</c:when>
					  </c:choose>
					</display:column>
					<display:column  title="创建时间" style="text-align:center" >
					  <fmt:formatDate value="${row.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</display:column>
					<display:column title="操作" style="text-align:center;width:18%;">
						<input type="button" class="btn_04s" value="查看" onclick="javascript:window.location='${ctx}/course/studyCourseTypeView.do?curTypeId=${param.curTypeId}&id=${row.id}'"/>
						<input type="button" class="btn_04s" value="修改" onclick="javascript:window.location='${ctx}/course/studyCourseTypeUpdate.do?curTypeId=${param.curTypeId}&id=${row.id}'"/>
						<input type="button" class="btn_04s" value="上移" onclick="upMove('${row.seq}','${row.upOrDownSeq}','${row.id}');" />
						<input type="button" class="btn_04s" value="下移" onclick="downMove('${row.seq}','${row.upOrDownSeq}','${row.id}');" />
						<!-- <input type="button" class="btn_03s" value="关联课程" onclick="javascript:dialog(${row.id});"/> -->
						<input type="button" class="btn_03s" value="已授权课程" <c:if test="${row.id == 1 }">disabled</c:if> onclick="viewCreditCourse('${row.id}')"/>
					</display:column>
				</display:table>
				<input type="hidden" name="curTypeId" value="${param.curTypeId}" />
                <input type="hidden" name="org.apache.struts.taglib.html.TOKEN"  value="${sessionScope['org.apache.struts.action.TOKEN']}" />
				</form>
			</center>
		</div>
	    <div id="dlg" class="easyui-dialog" title="关联课程" closed="true">
	        <iframe src="" width="1000" height="500" id="dialogFrame" name="dialogFrame"></iframe>
		</div>
		<script type="text/javascript">
			<c:if test="${MESSAGE != null && MESSAGE != ''}">
		        alert('${MESSAGE}');
		        <c:set var="MESSAGE" scope="session" value=""></c:set>
			</c:if>
			<c:if test="${IS_RELOAD_ADD != null}">
			    parent.reloadAdd();
			</c:if>
			<c:if test="${IS_RELOAD_DELETE != null}">
			    parent.reloadDelete();
			</c:if>	
		</script>
	</body>
</html>
<script type="text/javascript">
	function checkAll(element){
		$("input[name='ids']").each(function(){
			$(this).attr("checked", element.checked) ;
		});
	}
	
	function deleteStudyCourseType(){
	  if($("input[name='ids']:checked").length == 0){
	    alert('请选择需要删除的课程分类!');
	    return ;
	  }
	
	  if(confirm("确认删除!")){
	    $("#listfm").submit();
	  }
	}
	
	function dialog(id){
	  $( "#dialogFrame" ).attr('src','${ctx}/course/studyCourseTypeCourseList.do?curTypeId=' + id);
	  $('#dlg').dialog('open');
	}
	
	function upMove(index, newIndex, typeId){
		if(index == '' || newIndex <= 1){
			alert("当前数据不能进行上移操作!") ;
			return false ;
		}
		$("#moveId").val(typeId) ;
		$("#moveFlag").val('') ;
		document.fmx.action = "${ctx}/course/studyCourseTypeMove.do" ;
		document.fmx.submit()
	}
	
	function downMove(index, newIndex, typeId){
		var maxSeq = '${maxSeq}' ;
		 maxSeq = (maxSeq == '' || maxSeq == null) ? 1 : parseInt(maxSeq) ;   
		if(index >= maxSeq){
			alert("当前数据不能进行下移操作!") ;
			return false ;
		}
		$("#moveId").val(typeId) ;
		$("#moveFlag").val(typeId) ;
		document.fmx.action = "${ctx}/course/studyCourseTypeMove.do" ;
		document.fmx.submit()
	}
	
	//查看授权的课程
	function viewCreditCourse(id){
		window.location='${ctx}/course/studyCourseView.do?method=viewCreditCourse&typeId='+id;
	}
	
	$(function() {
		//弹出层
	    $( "#dlg" ).dialog({
		autoOpen: false,
	    height: 500,
	    width: 1000,
	    modal: true,//true遮蔽
	    iconCls:'icon-search', //左上角图标,icon文件夹下图标
	    close: function() {
	    	allFields.val( "" ).removeClass( "ui-state-error" );
	    }
	    });
	    
	    $("input[name='isFree'][type='checkbox']").click(function(){
	    	var priId = $(this).val() ;
	    	var checkFlag = document.getElementById("isFree_"+priId).checked ;
			var boolFlag = checkFlag ? false : true ;
			$(this).attr("disabled", true) ;
	    	$.ajax({
				type:"post",
				url:'${ctx}/course/studyCourseTypeIsFree.do',
				data:"primaryId="+priId+"&checkFlag="+checkFlag,
				dataType: 'text',
				success: function(msg) {
					if(msg == 'ajax.ok'){
					
					}else {
						alert("操作失败！");
						$("#isFree_"+priId).attr("checked", boolFlag) ;
					}
					$("#isFree_"+priId).attr("disabled", false) ;
				},
				error: function(){
					alert("操作失败！");
					$("#isFree_"+priId).attr("checked", boolFlag) ;
				}
			});
	    });
	 })
</script>
