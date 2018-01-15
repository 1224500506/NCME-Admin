<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML>
<html>
	<head>
	<%@ include file="/commons/taglibs.jsp"%>
		<script type="text/javascript" src="${ctx}/js/prototype.js"></script>
		<title>更新用户</title>
		<style>
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

.main_table {
	border: 1px solid #c9c9c9;
}

table {
	margin: 5px 0 5px 0 !important;
}

body {
	width: 100%;
	height: 100%;
	margin: 0px;
	padding: 0px;
	font-size: 12px;
	color: #000000;
	text-align: center;
	background-image: url(../images/06.gif);
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
	text-align: left;
}
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
<script>
function findUser(){
	parent.mainframe.location = '${ctx}/userManage/getUsers.do?t=' + Math.random();
}
function addUser(){
	parent.mainframe.location = '${ctx}/userManage/preSaveUser.do?t=' + Math.random();
}

</script>
	</head>
	<body
		style="border: 0px solid blue; margin: 0px; padding: 0px; overflow: hidden; float: left;">
		<div class="q4all">
			<div class="subnav">
				&nbsp;当前位置：首页 > 更新成功
			</div>
			<table class="main_table" border=0
				style="width: 100%; height: 100%; overflow: hidden;">
				<tr>
					<td width="*" valign="top">
						<div
							style="text-align:center;border: 0px solid black; * padding-bottom: 0px; overflow: hidden;">
								<div id="main" style="display:block">
								<c:if test="${empty exception}">
									用户更新成功！<br/><br/><a href="javascript:findUser();">查询用户</a>
								</c:if>
								<c:if test="${!empty exception}">
									${exception}<br/><br/><a href="javascript:findUser();">查询用户</a>
								</c:if>
								</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>

<SCRIPT type="text/javascript">
Event.observe(window, 'resize', adjustListViewDim);
//检索结果显示列表的自动适应
function adjustListViewDim(){
  var nPageHeight = Element.getDimensions(document.body)['height'];
  var nPageWidth = Element.getDimensions(document.body)['width'];
  //alert(nPageHeight)
  //太扁平的话，不需要重新设置高度了
  if(nPageHeight <= 200 || nPageWidth < 300){
    return;
  }
  //else
  var nSearchItemHeight = Element.getDimensions('form_div')['height'] || 70;
  var nSearchItemWidth = Element.getDimensions('budget_div')['width'] || 200;
  $('listframe').style.height = nPageHeight - nSearchItemHeight - 50;
  $('listframe').style.width = nPageWidth - nSearchItemWidth - 40;
  $('budget_div').style.height = nPageHeight;
}
</SCRIPT>