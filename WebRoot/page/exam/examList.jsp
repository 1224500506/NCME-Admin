<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>考试管理</title>
		<%@include file="/commons/taglibs.jsp"%>
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/button.css" rel="stylesheet" type="text/css" />
		
		<link href="${ctx}/js/UI/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/js/UI/themes/icon.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="${ctx}/js/UI/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
		
		<style media="all" type="text/css">
			@import url("${ctx}/css/displaytag/site.css");
			@import url("${ctx}/css/displaytag/screen.css");
		</style>
	</head>
	<body>
		<div style="padding-left:12px;width:99%;">
			<form action="" method="post" id="formA" name="formA">
			<input type="hidden" id="formAId" name="curTypeId" value="${param.curTypeId}" />
			<table class="gridtable" cellpadding="1" cellspacing="0" border="0" width="100%">
				<tr>
					<td class="row">考试名称:
						<input type="text" id="queryExamName" name="queryExamName" maxlength="50" value="${param['queryExamName']}" />
					</td>
					<td class="row">创建时间:
						<input type="text" id="queryCreateTime" name="queryCreateTime" maxlength="50" value="${param['queryCreateTime']}" />
					</td>
				</tr>
				<tr>
					<td class="row">
						<input type="button" class="btn_03s" value="查 询" onclick="selectCourse();" />
						<input type="button" class="btn_03s" value="添加考试" onclick="addCourse();" />
						<input type="button" class="btn_03s" value="删除考试" onclick="delCourse('');" />
						<input type="button" class="btn_03s" value="恢复考试" onclick="recoverCourse('');" />
					</td>
					<td class="row">
						
					</td>
				</tr>
			</table>
			</form>
			<br />
		</div>
		<div>
			<center>
				<form action="" id="formB" name="formB" method="post">
				<input type="hidden" id="courseId" name="courseId" />
				<input type="hidden" id="formBId" name="curTypeId" value="${param.curTypeId}" />
				<display:table id="row" name="ExamList" requestURI="${ctx}/exam/examList.do" style="font-size:12px;width:98%;" class="ITS">
					<display:caption>用户查询结果</display:caption>
					
					<display:column title="<input type='checkbox' id='chckAll' name='chckAll' onclick='checkAll(this);'>" style="text-align:center;width:2%">
						<input type="checkbox" id="curIds" name="curIds" value="${row.id}" />
					</display:column>
					<display:column property="name" title="考试名称" style="text-align:center" />
					<display:column title="考试时长(分钟)" style="text-align:center" >
					  <fmt:formatNumber value="${row.exam_times / 1000 }" pattern="0"/>
					</display:column>
					<display:column title="创建时间" style="text-align:center" >
					  ${fn:substring(row.create_time,0,19)}
					</display:column>
					<display:column title="开始时间" style="text-align:center" >
					  ${fn:substring(row.start_time,0,19)}
					</display:column>
					<display:column title="结束时间" style="text-align:center" >
					  ${fn:substring(row.end_time,0,19)}
					</display:column>
					<display:column title="状态">
						<c:if test="${row.state ==1 }"><font color="blue">正常</font></c:if>
						<c:if test="${row.state ==-1 }">删除</c:if>
						<c:if test="${row.state ==2 }">禁用</c:if>
					</display:column>

					<display:column title="操作" style="text-align:center;width:15%">
						<input type="button" class="btn_04s" value="查看" onclick="view('${row.id}');" />
						<input type="button" class="btn_04s" value="修改" onclick="detailCur('${row.id}','${param.curTypeId}');" />
					</display:column>
				</display:table>
				<input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="${sessionScope['org.apache.struts.action.TOKEN']}" />
				</form>
			</center>
		</div>
		
		<!-- 弹出层开始 -->
		<div id="elementInfo" class="easyui-window" title="" closed="true" iconCls="icon-save" style="padding:5px;">
			<div id="initHtml"></div>
		</div>
		<!-- 弹出层结束 -->
		
		<script type="text/javascript">

			function selectCourse(){
				document.formA.action = "${ctx}/exam/examList.do" ;
				document.formA.submit() ;
			}

			function addCourse(){
				document.formA.action = "${ctx}/exam/examAdd.do" ;
				document.formA.submit() ;
			}
            function view(id){
                window.location = "${ctx}/exam/examView.do?curTypeId=" + ${param.curTypeId} + "&id=" + id;
            }
			function delCourse(){
				var curIds = $("input[name='curIds']:checked") ;
				if(curIds.length == 0){
					alert("请您至少选择一项需要删除的数据!") ;
					return false ;
				}
				if(confirm("确认删除吗?")){
					$("#method").attr("value", '') ;
					document.formB.action = "${ctx}/exam/examDelete.do" ;
					document.formB.submit() ;
				}
			}
			
			//恢复考试
			function recoverCourse(){
				var curIds = $("input[name='curIds']:checked") ;
				if(curIds.length == 0){
					alert("请您至少选择一项需要恢复的数据!") ;
					return false ;
				}
				if(confirm("确认恢复吗?")){
					$("#method").attr("value", '') ;
					document.formB.action = "${ctx}/exam/examDelete.do?method=recover" ;
					document.formB.submit() ;
				}
			}
			function detailCur(id){
				document.formB.action = "${ctx}/exam/examUpdate.do?curTypeId=${param.curTypeId}&id=" + id ;
				document.formB.submit() ;
			}

			function checkAll(element){
				$("input[name='curIds']").each(function(){
					$(this).attr("checked", element.checked) ;
				});
			}
		</script>
	</body>
</html>