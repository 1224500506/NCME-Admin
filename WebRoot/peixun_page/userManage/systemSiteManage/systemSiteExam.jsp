<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML>
<html>
	<head>
		<link href="${ctx}/css/auth.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/lay.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
<%@ include file="/commons/taglibs.jsp"%>
		<link rel="stylesheet" href="${ctx}/js/Validform_v5.3/css/valid.css"
			type="text/css" media="all" />
		<script type="text/javascript"
			src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript"
			src="${ctx}/js/Validform_v5.3/js/Validform_v5.3.js"></script>
			
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/bootstrap/easyui.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/icon.css">
		
		<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/locale/easyui-lang-zh_CN.js"></script>
		<title>更新</title>
		<style>
html {
	height: 100%;
}

#budget_div {
	width: 100%;
	height: 100%;
	padding: 8px;
	overflow: auto;
}

#list_div {
	width: 95%;
	height: 95%;
	padding: 8px;
	overflow: auto;
}

#bgdiv {
	position: absolute;
	top: 0px;
	left: 0px;
	width: 100%;
	height: 100%;
	background-color: #AAAAAA;
	z-index: 10;
	filter: alpha(opacity =     80);
	opacity: 0.8;
}

.main_table {
	border: 1px solid #c9c9c9;
}

table {
	margin: 5px 0 5px 0 !important;
}

body {
	width: 100%;
	height: 100%;
	margin: auto;
	padding: 0px;
	font-size: 12px;
	color: #000000;
	text-align: center;
}

.q4all {
	width: 100%;
	height: 94%;
	font-size: 12px;
	background-color: #FFFFFF;
	margin-bottom: 10px;
}

.subnav {
	width: 100%;
	height: 20px;
	background-image: url(../images/XYZY_04.jpg);
	background-repeat: repeat-x;
	padding-top: 4px;
	text-align: center;
}
		@import url("${ctx}/css/displaytag/site.css");
		@import url("${ctx}/css/displaytag/screen.css");
</style>
		<!--[if IE]>
<style type="text/css">
.q4all { height: 90%; }
</style>
<![endif]-->
		<!--[if gte IE 7]>
<style type="text/css">
.q4all { height: 90%; }
</style>
<![endif]-->
	</head>
	<body
		style="border: 0px solid blue; margin: 0px; padding: 0px; overflow: hidden; float: left;">
		<div class="q4all">
			<div class="subnav">
				&nbsp;站点更新
			</div>
			<table class="main_table" border=0
				style="width: 100%; height: 100%; overflow: hidden;">
				<tr>
					<td width="*" valign="top">
						<div
							style="border: 0px solid black; * padding-bottom: 0px; overflow: hidden;">
							<form class="fm1" id="fm1" name="fm1"
								action="${ctx}/system/systemSite.do?method=update"
								method="post">
						<input type="hidden" id="id" name="model.id" value="${item.id}" />


						<div id="main" style="width: 100%; display: block">
									<table border=0
										style="width: 100%; border: 0px; height: 20px; line-height: 25px; margin-bottom: 0px;"
										cellpadding="3" cellspacing="1">
										<tr valign="top">
											<td style="width: 90%;" align="center">
												<table border=0>
													<tr>
														<td>
															站点域名:
														</td>
														<td>
															${item.domainName}
														</td>
														<td>
															<div class="Validform_checktip"></div>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<display:table id="row" name="${item.siteCourseTypes}"
													requestURI="${ctx}/course/studyCourseView.do"
													style="font-size:12px;width:98%;" class="ITS">
													<display:column property="courseTypeName" title="课程分类名称" style="text-align:center" />
													<display:column property="introduction" title="课程分类介绍" style="text-align:center" />
													<display:column title="考试名称" style="text-align:center" >
													  <c:forEach items="${row.examExaminationList}" var="list">
													    ${list.name} |
													  </c:forEach>
													</display:column>
													<display:column title="操作" style="text-align:center" >
													  <input type="button" class="btn_03s" value="选择考试" onclick="javascript:dialog(${row.siteId},${row.courseTypeId});"/>
													</display:column>
												</display:table>
											</td>
										</tr>
										<tr>
											<td
												style="text-align: center; border-top: 1px solid #c9c9c9;">
												<input type="button" class="btn_03s" value="返回"
													onclick="window.location='${ctx}/system/systemSite.do?method=list'" />
											</td>
										</tr>
									</table>

								</div>

						</form>
						<div style="clear: both"></div>
						</div>
						<div
							style="border: 0px solid red; padding-top: 0px; width: 100%; height: 100%;">
						</div>
					</td>
				</tr>
			</table>

    <div id="dlg" class="easyui-dialog" title="选择考试" closed="true">
        <iframe src="" width="1000" height="500" id="dialogFrame" name="dialogFrame"></iframe>
	</div>
 	  <script type="text/javascript">

        function dialog(siteId,courseTypeId){
          $("#dialogFrame").attr('src','${ctx}/system/systemSite.do?method=siteExam&siteId=' + siteId + '&courseTypeId='+courseTypeId);
          $('#dlg').dialog('open');
        }

        function closeDialog(){
          $('#dlg').dialog('close');
          window.location.reload();
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
		    //验证
	       $(".fm1").Validform();
	    })
	  </script>
	</body>
</html>
