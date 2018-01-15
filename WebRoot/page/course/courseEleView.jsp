<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>${sessionScope.site.siteName}</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<%@include file="/commons/taglibs.jsp"%>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/fxbg.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css" />
		
		<link href="${ctx}/js/UI/themes/gray/easyui.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/js/UI/themes/icon.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="${ctx}/js/UI/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
	</head>
	<body>
		<div id="container">

			<div id="content">
				<div class="warp">
					
					<div id="main_cont">
						<div style="text-align:center;font-size:14px;padding-bottom:10px;">
							本课件共含课件：&nbsp;<strong>${curEle.wareNumber}</strong>&nbsp;&nbsp;个，
							练习&nbsp;<strong>${curEle.quesNumber}</strong>&nbsp;&nbsp;套。
							<!-- 
								课件时长：&nbsp;<strong>6</strong>&nbsp;&nbsp;分钟。
							 -->
						</div>
						
						<c:set value="0" var="curIndex"></c:set>
						<div id="tab_ui" class="easyui-tabs">
							<c:forEach items="${curEle.eleList}" var="ele" varStatus="eleStatus">
								<c:choose>
									<c:when test="${ele.courseElementType >= 11}">
										<div id="tabs-${eleStatus.index+1}" 
											<c:choose>
												<c:when test="${ele.courseElementType eq 11}">
													title="课&nbsp;前&nbsp;练&nbsp;习&nbsp;"		
												</c:when>
												<c:when test="${ele.courseElementType eq 12}">
													title="课&nbsp;中&nbsp;练&nbsp;习&nbsp;"		
												</c:when>
												<c:when test="${ele.courseElementType eq 13}">
													title="课&nbsp;后&nbsp;练&nbsp;习&nbsp;"
												</c:when>
											</c:choose>	
										style="overflow:hidden;"></div>
									</c:when>
									<c:otherwise>
										<c:if test="${curIndex eq 0}">
											<div id="tabs-${eleStatus.index+1}" title="学&nbsp;习&nbsp;课&nbsp;件" style="overflow:hidden;"></div>
											<c:set value="${curIndex + 1}" var="curIndex"></c:set>										
										</c:if>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							
						</div>
						
						<div style="height:480px;width:640px;padding-left:50px;">
							${courseWare.path}
						</div>
						
						<div class="clearfix"></div>
						
						<div style="text-align:center;height:50px;padding-top:10px;z-index:1000;">
							<c:set var="allIndex" value="${curEle.wareNumber + curEle.quesNumber}"></c:set>
								
							<c:if test="${eleIndex > 1 && eleIndex <= allIndex}">
								<input type="button" class="btn_03s" value="上一步" onclick="selectType('${eleIndex-1}')" />&nbsp;&nbsp;&nbsp;	
							</c:if>
							<c:if test="${eleIndex > 0 && eleIndex < allIndex}">
								<input type="button" class="btn_03s" value="下一步" onclick="selectType('${eleIndex+1}')" />
							</c:if>
						</div>
					</div>
				</div>
			</div>

		</div>
		<form id="subForm" name="subForm" method="post">
			<input type="hidden" id="urlId" name="urlId" value="${param.urlId}" />
			<input type="hidden" id="curTypeId" name="curTypeId" value="${param.curTypeId}" />
			<input type="hidden" id="eleIndex" name="eleIndex" />
			<input type="hidden" id="id" name="id" value="${param.id}" />
		</form>
		<script type="text/JavaScript">
			function selectType(eleIndex){
				var temForm = document.subForm ; 
				temForm.eleIndex.value = eleIndex ;
				temForm.action = "${ctx}/course/studyCoursePreview.do" ;
				temForm.submit() ;
			}
			
			$(document).ready(function(){
				var tabs = $('#tab_ui').tabs('tabs');
				for(var i = 0; i < tabs.length; i++){
					tabs[i].panel('options').tab.unbind();
				}
				
				var eleIndex = '${param.eleIndex eq null ? 1 : param.eleIndex}' ;
				$("#tab_ui").tabs("select", eleIndex-1);
			});
		</script>
	</body>
</html>