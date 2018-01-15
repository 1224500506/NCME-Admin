<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>课程管理</title>
		<%@include file="/commons/taglibs.jsp"%>
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/button.css" rel="stylesheet" type="text/css" />
		
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
	<jsp:include page="/page/tableColor.jsp"></jsp:include>
	<body>
		<div style="padding-left:12px;width:99%;">
			<form action="" method="post" id="formA" name="formA">
			<input type="hidden" id="formAId" name="curTypeId" value="${param.curTypeId}" />
			<table class="gridtable" cellpadding="1" cellspacing="0" border="0" width="100%">
				<tr>
					<td class="row">课程名称:
						<input type="text" id="studyCourseName" name="course.studyCourseName" maxlength="50" value="${param['course.studyCourseName']}" />
					</td>
					<td class="row" colspan="2" >主讲老师:
						<input type="text" id="teacher" name="course.teacher" maxlength="50" value="${param['course.teacher']}" />
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
						<input type="button" class="btn_03s" value="添加课程" onclick="addCourse();" />&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<%--
				<tr>
					<td class="row">学科及知识点:
						<input type="text" name="content" id="course.content" maxlength="50" />
					</td>
					<td class="row">
						 
							历史应用:
							<select id="siteId" name="course.siteId">
								<c:forEach items="${siteList}" var="site" varStatus="siteStatus">
									<option value="${site.id}">${site.domainName}</option>	
								</c:forEach>
							</select>
						
						<span style="padding-left:100px;">
							
						</span>
					</td>
				</tr>
				 --%>
			</table>
			</form>
			<br />
		</div>
		<div>
			<center>
				<form action="" id="formB" name="formB" method="post">
				<input type="hidden" id="method" name="method" />
				<input type="hidden" id="courseId" name="courseId" />
				<input type="hidden" id="formBId" name="curTypeId" value="${param.curTypeId}" />
				<display:table id="row" name="page" requestURI="${ctx}/ncme/ncmeStudyCourseView.do" style="font-size:12px;width:98%;" class="ITS"
				 decorator="com.hys.exam.displaytag.TableOverOutWrapper" >
					<display:caption>用户查询结果</display:caption>
					
					<display:column title="<input type='checkbox' id='chckAll'  onclick='checkAll(this);'>" style="text-align:center;width:2%">
						<input type="checkbox" id="curIds" name="curIds" value="${row.id}" courseName="${row.studyCourseName}" courseNumber="${row.courseNumber}"/>
					</display:column>
					<display:column property="courseNumber" title="课程编号" style="text-align:center" />
					<display:column property="studyCourseName" title="课程名称" style="text-align:center" />
					<display:column title="添加时间" style="text-align:center" >
					  <fmt:formatDate value="${row.createDate}" pattern="yyyy-MM-dd"/>
					</display:column>
					<display:column title="发布时间" style="text-align:center" >
					  <fmt:formatDate value="${row.pubDate}" pattern="yyyy-MM-dd"/>
					</display:column>
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
		
		<!-- 弹出层开始 -->
		<div id="elementInfo" class="easyui-window" title="" closed="true" iconCls="icon-save" style="padding:5px;">
			<div id="initHtml"></div>
		</div>
		<!-- 弹出层结束 -->
		
		<script type="text/javascript">
			
			var iframe = "<iframe id='eleInfo' name='eleInfo' src='' width='100%' height='100%' frameborder='0' scrolling='scroll'></iframe>" ;
		
			function selectCourse(){
				//document.formA.action = "${ctx}/course/studyCourseView.do" ;
				document.formA.submit() ;
			}

			/**
			function addCourse(){
				document.formA.action = "${ctx}/course/studyCourseAddTo.do" ;
				document.formA.submit() ;
			}
			**/
			function delCourse(){
				var curIds = $("input[name='curIds']:checked") ;
				if(curIds.length == 0){
					alert("请您至少选择一项需要删除的数据!") ;
					return false ;
				}
				if(confirm("确认删除吗?")){
					$("#method").attr("value", '') ;
					document.formB.action = "${ctx}/course/studyCourseDel.do" ;
					document.formB.submit() ;
				}
			}

			function delCourse2(met){
				if(confirm("确认删除吗?")){
					$("#method").attr("value", met) ;
					$("#courseId").attr("value", met) ;
					document.formB.action = "${ctx}/course/studyCourseDel.do" ;
					document.formB.submit() ;
				}
			}
			
			function detailCur(met){
				$("#courseId").attr("value", met) ;
				document.formB.action = "${ctx}/course/studyCourseDetail.do" ;
				document.formB.submit() ;
			}

			function curWareView(met){
				$("#courseId").attr("value", met) ;
				var curTypeId = $("#formBId").val() ;
				//document.formB.action = "${ctx}/course/studyCurWareView.do ;
				//document.formB.submit() ;
				window.location.href = "${ctx}/course/studyCurWareView.do?courseId="+met+"&curTypeId="+curTypeId ;
			}
			
			function checkAll(element){
				$("input[name='curIds']").each(function(){
					$(this).attr("checked", element.checked) ;
				});
			}
			
			function previewCur(id){
				$("#initHtml").html("") ;
				$("#initHtml").html(iframe) ;
				
				var url = "${ctx}/course/studyCoursePreview.do?id=" + id ;
				openwindow(url,"videoWindow",1200,700);
			}
			
			function openwindow(url,name,iWidth,iHeight){
		    	var url;     //转向网页的地址;
		    	var name;    //网页名称，可为空;
		    	var iWidth;  //弹出窗口的宽度;
		    	var iHeight; //弹出窗口的高度;
		    	var iTop = (window.screen.availHeight-30-iHeight)/2;       //获得窗口的垂直位置;
		    	var iLeft = (window.screen.availWidth-10-iWidth)/2;        //获得窗口的水平位置;
		    	window.open(url,name,'height='+iHeight+',innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=yes,resizeable=no,location=no,status=no');
		    }
		    
		    function addCourse(){
		    	var curIds = $("input[name='curIds']:checked") ;
				if(curIds.length == 0){
					alert("请您至少选择一项需要添加的课程!") ;
					return false ;
				}
		    	$("input[name='curIds']:checked").each(function(){
		    	    ///parent.parent.setCourse($(this).attr("courseNumber"),$(this).attr("courseName"));
		    		parent.setCourse($(this).val(),$(this).attr("courseName"));
				});
				
				alert('添加成功!');
		    }
		    
		    
		</script>
	</body>
</html>