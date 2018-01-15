<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>资源管理系统</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<%@ include file="/commons/taglibs.jsp"%>
		<link href="${ctx}/css/style1.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="${ctx}/js/UI/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>

		<style media="all" type="text/css">
			@import url("${ctx}/css/displaytag/site.css");
			@import url("${ctx}/css/displaytag/screen.css");
		</style>
		
		<link href="${ctx}/js/UI/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/js/UI/themes/icon.css" rel="stylesheet" type="text/css" />
		
		<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css" />
	</head>
	<body style="overflow:hidden;">
		<div>
			<div>
				<form id="fm" name="fm" method="post">
				<input type="hidden" name="typeId" value="${paperTypeId}" />
				<table width="100%" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td width="5%">
							试卷名称：
						</td>
						<td width="5%">
							<input type="text" name="name" id="name" value="${name}" />
						</td>
						<td width="5%">
							创建时间：
						</td>
						<td width="5%">
							<input type="text" name="createDate" id="createDate" value="${createDate}" onClick="WdatePicker()" />
						</td>
						<td>
							<input type="button" class="btn_03s" value="查 询" onclick="search();" />
							<input type="button" class="btn_03s" value="重 置" onclick="javascript:document.forms['fm'].reset(); return false;" />
						</td>
					</tr>
				</table>
				</form>
			</div>
			<div style="width: 100%; margin-top: 10px;padding-bottom:5px;">
				<div style="width: 70%; float: left">
					<input type="button" class="btn_03s" value="新增试卷" typeId="${paperTypeId}" id="addPaper" />
					<input type="button" class="btn_03s" value="删除试卷" id="delPaper" />
				</div>
				<div style="width: 30%; float: left; text-align: right;">
					<input type="button" class="btn_03s" value="目录管理" />
				</div>
			</div>

			<form action="" id="formB" name="formB" method="post">
			<display:table id="item" name="page" requestURI="${ctx}/exam/paper/paperList.do"
							style="width:100%;" decorator="com.hys.exam.displaytag.OverOutWrapper" class="its" excludedParams="msg">
							
				<display:column title="<input type='checkbox' id='cardIds' onclick='checkAll(this);'>" style="width:5%;text-align:center">
					<input type="checkbox" id="paperId" name="paperId" value="${item.id}" />
				</display:column>
				<display:column property="name" title="名称" style="text-align:left"></display:column>
				<display:column title="组卷策略">
					<c:choose>
						<c:when test="${item.paper_mode eq 1}">
							快捷策略1
						</c:when>
						<c:when test="${item.paper_mode eq 5}">
							快捷策略2
						</c:when>
						<c:when test="${item.paper_mode eq 3}">
							手工组卷
						</c:when>
						<c:when test="${item.paper_mode eq 4}">
							卷中卷
						</c:when>
						<c:otherwise>&nbsp;</c:otherwise>
					</c:choose>
				</display:column>
				<display:column property="paper_score" title="总分数" style="text-align:center"></display:column>
				<display:column property="question_num" title="试题数量" style="text-align:center"></display:column>
				<display:column title="创建时间">
					<c:out value="${fn:substring(item.create_date,0,10)}" />
				</display:column>
				<display:column property="type_name" title="所属试卷目录" 	style="text-align:left"></display:column>
				<display:column title="操作" style="width:16%;text-align:center;">
					<a href="JavaScript:void(0)" paperId="${item.id}" name="viewPaper">查看试卷</a>
					<a href="JavaScript:void(0)" paperId="${item.id}" name="outPaper">导出试卷</a>
					<a href="JavaScript:void(0)" paperId="${item.id}" name="answerPaper">导出试卷(答案)</a>
				</display:column>
			</display:table>
			</form>
		</div>
		
		<!-- 弹出层开始 -->
		<div id="paperInfo" class="easyui-window" title="" closed="true" iconCls="icon-save" style="padding:5px;">
			<div id="initHtml"></div>
		</div>
		<!-- 弹出层结束 -->

		<script type="text/javascript">
			
			var iframe = "<iframe id='paperFrame' name='paperFrame' src='' width='100%' height='100%' frameborder='0' scrolling='scroll'></iframe>" ;
			
			function search(){
				document.fm.action = '${ctx}/exam/paper/paperList.do';
				document.fm.submit();
			}
			
			$(document).ready(function(){
				$("a[name='viewPaper']").click(function(){
					var paperId = $(this).attr("paperId") ;
					
					$("#initHtml").html("") ;
					$("#initHtml").html(iframe) ;
					
					$('#paperInfo').window({
						title: '&nbsp;试题信息',
						width: 940,
						height: 520,
						modal: true,
						shadow: false,
						closed: false,
						minimizable:false,
						collapsible:false,
						maximizable:false,
						top:40,
						left:40
					});
					
					var url = "${ctx}/exam/paper/paperView.do?paperId=" + paperId ;
					$("#paperFrame").attr("src", url) ;
				});
				
				$("a[name='outPaper']").click(function(){
					var paperId = $(this).attr("paperId") ;
					window.location.href = "${ctx}/exam/paper/paperOut.do?paperId=" + paperId ;
				}) ;

				$("a[name='answerPaper']").click(function(){
					var paperId = $(this).attr("paperId") ;
					window.location.href = "${ctx}/exam/paper/paperAnswer.do?paperId=" + paperId ;
				}) ;
				
				$("#addPaper").click(function(){
					var typeId = $(this).attr("typeId") ;
					window.location.href = "${ctx}/exam/paper/paperAddTo.do?typeId=" + typeId ;
				});
				
				$("#delPaper").click(function(){
					var paperIds = $("input[name='paperId'][type='checkbox']:checked") ;
					if(paperIds.lengh == 0){
						alert("请您至少选择一个需要删除的数据信息!") ;
						return false ;
					}
					if(confirm("确认删除当前所选的数据信息吗?")){
						document.formB.action='${ctx}/exam/paper/paperDelete.do?typeId=${paperTypeId}';
						document.formB.submit();
					}
				});
			}) ;
			
			function closeWin(){
				$("#paperInfo").window('close') ;
			}
			
			function checkAll(element){
				$("input[name='paperId'][type='checkbox']").each(function(){
					$(this).attr("checked", element.checked) ;
				});
			}
		</script>
	</body>
</html>