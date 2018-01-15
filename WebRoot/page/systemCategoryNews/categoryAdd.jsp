<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>添加栏目</title>
		<%@ include file="/commons/taglibs.jsp"%>
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
		<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
		<style media="all" type="text/css">
			@import url("${ctx}/css/displaytag/site.css");
			@import url("${ctx}/css/displaytag/screen.css");
		</style>
	</head>
	<body
		style="border: 0px solid blue; margin: 0px; padding: 0px; overflow: hidden; float: left;">
			<div class="subnav" style="text-align:center">
				&nbsp;添加栏目
			</div>
			<table class="main_table" border=0
				style="width: 100%; height: 100%; overflow: hidden;">
				<tr>
					<td width="*" valign="top">
						<div
							style="border: 0px solid black; * padding-bottom: 0px; overflow: hidden;">
							<form class="fm1" id="fm1" name="fm1"
								action="${ctx}/system/SystemCategory.do?method=add"
								method="post">

						<div id="main" style="width: 100%; display: block">
							<table border=0
								style="width: 50%; border: 0px; height: 20px; line-height: 25px; margin-bottom: 0px;"
								cellpadding="3" cellspacing="1" align="center">
								<tr valign="top">
									<td style="width: 90%;" align="center">
										<table border=0>
											<tr>
												<td>
													站点:
												</td>
												<td>
													<select name="applicationId" id="applicationId">
														<c:forEach items="${siteList }" var="site">
															<option value="${site.id }" ${category.applicationId == site.id ? 'selected' : ''}>${site.domainName }</option>
														</c:forEach>
													</select>
												</td>
												<td>
													栏目标题:
												</td>
												<td>
													<input type="text" name="title" value="${category.title }"/>
												</td>
											</tr>
										</table>
									</td>
								</tr>

								<tr>
									<td style="text-align: center; border-top: 1px solid #c9c9c9;">
										<input type="submit" class="btn_03s" value="保存" />
										<input type="button" class="btn_03s" class="btn_02s" value="返回"
											onclick="window.location='${ctx}/system/SystemCategory.do?method=list'" />
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

	</body>
</html>
