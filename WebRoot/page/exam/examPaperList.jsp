<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>资源与管理系统</title>
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
				</div>
				<div style="width: 30%; float: left; text-align: right;">
					<input type="button" class="btn_03s" value="选择试卷" onclick="javascript:selectPaper();" />
					<input type="button" class="btn_03s" value="关闭" onclick="javascript:parent.window.close();" />
				</div>
			</div>

			<display:table id="item" name="page" requestURI="${ctx}/exam/examPaperList.do"
							style="width:100%;" decorator="com.hys.exam.displaytag.OverOutWrapper" class="its" excludedParams="msg">
							
				<display:column title="<input type='checkbox' id='checkAll' name='checkAll' onclick='checkAll(this);'>" style="width:5%;text-align:center">
					<input type="checkbox" id="paperId" name="paperId" value="${item.id}" paperName="${item.name}" />
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
			</display:table>
		</div>

		<script type="text/javascript">
			function checkAll(element){
				$("input[name='paperId']").each(function(){
					$(this).attr("checked", element.checked) ;
				});
			}
			function search(){
				document.fm.action = '${ctx}/exam/examPaperList.do';
				document.fm.submit();
			}
			function selectPaper(){
			  if($("input[name='paperId']:checked").length == 0){
                alert('请选择需要添加的试卷!');
                return ;
              }

              $("input[name='paperId']:checked").each(function(){
	                if($(this).attr("disabled") != "disabled"){
						window.parent.opener.addPaper($(this).val(),$(this).attr("paperName"));

	                    $(this).hide();
						$(this).attr("disabled","disabled");
                    }
				});
			  alert("添加成功!");
			}
			$(document).ready(function(){
			  var paperIds = window.parent.opener.getPaperIds();
			  if(paperIds != ""){
			    $("input[name='paperId']").each(function(){
			      if(paperIds.indexOf($(this).val()) >= 0 ){
			        $(this).hide();
					$(this).attr("disabled","disabled");
			      }
			    });
			  }
			});
		</script>
	</body>
</html>