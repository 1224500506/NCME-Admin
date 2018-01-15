<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>已授权课程</title>
		<%@include file="/commons/taglibs.jsp"%>
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/button.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/icon.css">
		
		<link href="${ctx}/js/UI/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/js/UI/themes/icon.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="${ctx}/js/UI/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.bgiframe.js"></script>
		
		<style media="all" type="text/css">
			@import url("${ctx}/css/displaytag/site.css");
			@import url("${ctx}/css/displaytag/screen.css");
		</style>
		<style type="text/css"> 
			body{font-size:12px;}
			.selectItemcont{padding:8px;}
			#selectItem{background:#FFF;position:absolute;top:0px;left:center;border:1px solid #000;overflow:hidden;width:380px;z-index:1000;}
			.selectItemtit{line-height:20px;height:20px;margin:1px;padding-left:12px;}
			.bgc_ccc{background:#E88E22;}
			.selectItemleft{float:left;margin:0px;padding:0px;font-size:12px;font-weight:bold;color:#fff;}
			.selectItemright{float:right;cursor:pointer;color:#fff;}
			.selectItemcls{clear:both;font-size:0px;height:0px;overflow:hidden;}
			.selectItemhidden{display:none;}
		</style>
		<script type="text/javascript">
		
	    jQuery.fn.selectCourseType = function (targetId) {
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
	        
	        //清空
	        targetId.find("#selectItemClear").click(function () {
	            $("#CourseTypes").val('');
	            $("#CourseTypeIds").val('');
	            temp_value = '';
	            temp_id = '';
	            targetId.find(":checkbox").attr("checked", false);
	        });
	
	        targetId.find("#selectSub :checkbox").click(function () {
	            targetId.find(":checkbox").attr("checked", false);
	            $(this).attr("checked", true);
	            var value = $(this).val();
	            if(temp_value.indexOf(value) == -1){
	            	temp_value = temp_value + value.substring(value.indexOf("-")+1) + ',';
	            	temp_id = temp_id + value.substring(0,1) + ',';
	            }
	            $("#CourseTypeIds").val(temp_id);
	            _seft.val(temp_value);
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
	        $("#CourseTypes").selectCourseType("#selectItem");
	    });
	 </script>
	</head>
	<body>
		<div style="padding-left:12px;width:99%;">
			<form action="" method="post" id="formA" name="formA">
			<table class="gridtable" cellpadding="1" cellspacing="0" border="0" width="100%">
				<tr>
					<td class="row">课程名称:
						<input type="text" id="studyCourseName" name="course.studyCourseName" maxlength="50" value="${param['course.studyCourseName']}" />
					</td>
					<td class="row">主讲老师:
						<input type="text" id="teacher" name="course.teacher" maxlength="50" value="${param['course.teacher']}" />
					</td>
					<td class="row">状态:
						<select id="status" name="course.status">
							<option value="-1">全部</option>
							<option value="0" <c:if test="${param['course.status'] eq 0}">selected</c:if>>未发布</option>
							<option value="1" <c:if test="${param['course.status'] eq 1}">selected</c:if>>已发布</option>
							<option value="2" <c:if test="${param['course.status'] eq 2}">selected</c:if>>已下线</option>
							<option value="3" <c:if test="${param['course.status'] eq 3}">selected</c:if>>已作废</option>
						</select>					
					</td>					
				</tr>
				<tr>
					<td class="row">关键字:&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text" id="keyWord" name="course.keyWord" maxlength="50" value="${param['course.keyWord']}" />
					</td>
					<td class="row">类型:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						
						<input type="text" name="CourseTypes" id="CourseTypes" value="${CourseTypes }" size="50" readonly>
						<input type="hidden" name="CourseTypeIds" id="CourseTypeIds" value="${CourseTypeIds }" size="50" >
						<div id="selectItem" class="selectItemhidden"> 
						 <div id="selectItemAd" class="selectItemtit bgc_ccc"> 
						  <h2 id="selectItemTitle" class="selectItemleft">课程类型选择</h2>
						  
						  <div id="selectItemClose" class="selectItemright">确定&nbsp;&nbsp;&nbsp;</div>
						  <div id="selectItemClear" class="selectItemright">清空&nbsp;&nbsp;&nbsp;</div>
						 </div> 
						 <div id="selectItemCount" class="selectItemcont"> 
							  <div id="selectSub"> 
								   <input type="checkbox"  id="cr01" value="1-单屏课程"/><label for="cr01">1-单屏课程</label>
								   <input type="checkbox"  id="cr02" value="2-三屏课程"/><label for="cr02">2-三屏课程</label>
								   <input type="checkbox"  id="cr03" value="3-flash"/><label for="cr03">3-flash</label>
								   <input type="checkbox"  id="cr09" value="4-文档"/><label for="cr09">4-文档</label>
								   <input type="checkbox"  id="cr08" value="5-混合"/><label for="cr09">5-混合</label>
							  </div> 
						 </div> 
						</div> 
						 
						
					</td>
					<td class="row">
						<input type="button" class="btn_03s" value="查 询" onclick="selectCourse();" />
						<input type="button" class="btn_03s" value="返 回" onclick="history.go(-1)" />
					</td>
				</tr>
				
			</table>
			</form>
			<br />
		</div>
		<div>
			<center>
				<form action="" id="formB" name="formB" method="post">
				<input type="hidden" id="method" name="method" />
				<input type="hidden" id="courseId" name="courseId" />
				<display:table id="row" name="page" requestURI="${ctx}/course/studyCourseView.do?method=viewCreditCourse&typeId=${typeId }" style="font-size:12px;width:98%;" class="ITS">
					<display:caption>用户查询结果</display:caption>
					
					<display:column property="studyCourseName" title="课程名称" style="text-align:center" />
					<display:column property="summary" title="课程摘要" style="text-align:center" />
					<display:column property="teacher" title="主讲老师" style="text-align:center" />
					<display:column title="添加时间" style="text-align:center" >
					  <fmt:formatDate value="${row.createDate}" pattern="yyyy-MM-dd"/>
					</display:column>
					<display:column title="发布时间" style="text-align:center" >
					  <fmt:formatDate value="${row.pubDate}" pattern="yyyy-MM-dd"/>
					</display:column>

					<display:column title="课件数" style="text-align:center" />
					<display:column property="classHours" title="学时" style="text-align:center" />
					<display:column property="coursePractice" title="练习（套）" style="text-align:center" />

					<display:column title="类型" style="text-align:center">
						<c:choose>
							<c:when test="${row.studyCourseType eq 1}">
								单屏课程
							</c:when>
							<c:when test="${row.studyCourseType eq 2}">
								三屏课程
							</c:when>
							<c:when test="${row.studyCourseType eq 3}">
								flash
							</c:when>
							<c:when test="${row.studyCourseType eq 4}">
								文档课程
							</c:when>
							<c:when test="${row.studyCourseType eq 5}">
								混合课程
							</c:when>
							<c:otherwise>
								&nbsp;
							</c:otherwise>
						</c:choose>
					</display:column>
					<display:column title="状态" style="text-align:center">
					  <c:if test="${row.status eq 0 }">未发布</c:if>
					  <c:if test="${row.status eq 1 }">已发布</c:if>
					  <c:if test="${row.status eq 2 }">已下线</c:if>
					  <c:if test="${row.status eq 3 }">已作废</c:if>
					</display:column>
					
				</display:table>
				</form>
			</center>
		</div>
		
		<script type="text/javascript">
			
			var iframe = "<iframe id='eleInfo' name='eleInfo' src='' width='100%' height='100%' frameborder='0' scrolling='scroll'></iframe>" ;
		
			function selectCourse(){
				var CourseTypes = $("#CourseTypes").val();
				var CourseTypeIds = $("#CourseTypeIds").val();
				$("#studyCourseTypes").val(CourseTypes);
				document.formA.action = "${ctx}/course/studyCourseView.do?method=viewCreditCourse&typeId=${typeId }" ;
				document.formA.submit() ;
			}

		</script>
	</body>
</html>