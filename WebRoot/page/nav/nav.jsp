<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>menu</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/commons/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="css/nav.css">
<script type="text/javascript">
	var ctx = '${ctx}';

	var timeout = 500;
	var closetimer = 0;
	var ddmenuitem = 0;

	// open hidden layer
	function mopen(id) {
		// cancel close timer
		mcancelclosetime();

		// close old layer
		if (ddmenuitem)
			ddmenuitem.style.visibility = 'hidden';

		// get new layer and show it
		if(id != ''){
			ddmenuitem = document.getElementById(id);
			ddmenuitem.style.visibility = 'visible';
		}

	}
	// close showed layer
	function mclose() {
		if (ddmenuitem)
			ddmenuitem.style.visibility = 'hidden';
	}

	// go close timer
	function mclosetime() {
		closetimer = window.setTimeout(mclose, timeout);
	}

	// cancel close timer
	function mcancelclosetime() {
		if (closetimer) {
			window.clearTimeout(closetimer);
			closetimer = null;
		}
	}

	// close layer when click-out
	document.onclick = mclose;
	
	var ctx = '${ctx}';
	
	function toIndex(){
		parent.mainframe.location = ctx + '/page/welcome.jsp';
	}
	
	function questManage() {
		parent.mainframe.location = ctx + '/questionManage/questionManage.do?type=clear';
	}
	
	function authManage() {
		parent.mainframe.location = ctx + '/page/auth_Main.jsp?t=' + Math.random();
	}
	
	function logout(){
		window.location.href = ctx + '/logout.do';
	}
	
	function EditPassword(){
		parent.mainframe.location = ctx + '/page/auth_EditPwd.jsp?t=' + Math.random();
	}
	
	function importQues(){
		parent.mainframe.location = ctx + '/page/examImportQuestion.jsp?t=' + Math.random();
	}
	
	function searchNotQues(){
		parent.mainframe.location = ctx + '/questionManage/SearchNotPropQuestion.do';
	}
	
	function propManage(){
		parent.mainframe.location = ctx + '/page/propManage/propManage.jsp';
	}
	
	function systemClientManage(){
		parent.mainframe.location = ctx + '/system/systemClient.do?method=list';
	}
	
	function systemSiteManage(){
		parent.mainframe.location = ctx + '/system/systemSite.do?method=list';
	}
	
	function systemUserManage(){
		parent.mainframe.location = ctx + '/system/systemUser.do?method=list';
	}
	
	function searchCourseManage(){
		parent.mainframe.location = ctx + '/course/curIndex.do';
	}
	
	function studyCoursewareManage(){
		parent.mainframe.location = ctx + '/course/coursewareList.do';
	}
	
	function searchCourseTypeManage(){
		parent.mainframe.location = ctx + '/course/studyCourseTypeIndex.do';//
	}
	
	function paperManage(){
		parent.mainframe.location = ctx + '/exam/paper/paperIndex.do';//
	}
	
	function examManage(){
		parent.mainframe.location = ctx + '/exam/examIndex.do';//
	}
	
	function paperTypeManage(){
		parent.mainframe.location = ctx + '/exam/paper/examPaperTypeIndex.do';//
	}
	
	function examTypeManage(){
		parent.mainframe.location = ctx + '/exam/category/examCategoryIndex.do';//
	}
	
	function courseAuthorize(){
		parent.mainframe.location = ctx + '/ncme/ncmeCourseAuthorizeList.do';//
	}
	
	//行业岗位管理
	function systemPropManage(){
		parent.mainframe.location = ctx + '/system/SystemIndustry.do?method=list';
	}
	
	//学习卡管理--制卡管理
	function systemCardManage(){
		parent.mainframe.location = ctx + '/system/SystemCard.do?method=list';
	}
	
	//卡类型管理
	function systemCardTypeManage(){
		parent.mainframe.location = ctx + '/system/SystemCardType.do?method=list';
	}
	
	//卡类型分配管理
	function systemCardAllotManage(){
		parent.mainframe.location = ctx + '/system/SystemCard.do?method=allotList';
	}
	
	//卡类型状态管理
	function systemCardStatusManage(){
		parent.mainframe.location = ctx + '/system/SystemCardStatus.do?method=list';
	}
	
	//订单管理
	function systemOrderManage(){
		parent.mainframe.location = ctx + '/system/SystemCardOrder.do?method=list';
	}
	
	//CMS管理
	function systemCMSManage(){
		parent.mainframe.location = ctx + '/system/SystemCategory.do';
	}

</script>
</head>
<body  style="text-align:center">
	<ul id="sddm" style="z-index:100;">
		<li>
			<a href="javascript:toIndex()" onmouseover="mopen('')" onmouseout="mclosetime()">首页</a>
			<div> &nbsp; </div>
		</li>
		<li>
			<a href="javascript:propManage()" onmouseover="mopen('')" onmouseout="mclosetime()">属性管理</a>
			<div> &nbsp; </div>
		</li>
		<li>
			<a href="javascript:systemPropManage()" onmouseover="mopen('')" onmouseout="mclosetime()">行业岗位管理</a>
			<div> &nbsp; </div>
		</li>
		<li><a href="#" onmouseover="mopen('m1')" onmouseout="mclosetime()">题库管理</a>
			<div id="m1" onmouseover="mcancelclosetime()" onmouseout="mclosetime()">
				<a href="javascript:questManage();">试题管理</a> 
				<a href="javascript:paperManage();">试卷管理</a> 
				<a href="javascript:paperTypeManage();">试卷目录</a>
			</div>
		</li>
		<li><a href="#" onmouseover="mopen('m2')" onmouseout="mclosetime()">考试管理</a>
			<div id="m2" onmouseover="mcancelclosetime()" onmouseout="mclosetime()">
				<a href="javascript:examManage();" onclick="change_bg(this)">考试设置</a>
				<a href="javascript:examTypeManage();" onclick="change_bg(this)">考试目录</a>
			</div>
		</li>
		<li><a href="#" onmouseover="mopen('m3')" onmouseout="mclosetime()">课程管理</a>
			<div id="m3" onmouseover="mcancelclosetime()" onmouseout="mclosetime()">
				<a href="javascript:studyCoursewareManage();" onclick="change_bg(this)">课件管理</a>
				<a href="javascript:searchCourseManage();" onclick="change_bg(this)">课程设置</a>
				<a href="javascript:courseAuthorize();" onclick="change_bg(this)">课程授权</a>
				<a href="javascript:searchCourseTypeManage();" onclick="change_bg(this)">课程目录</a>
			</div>
		</li>
		<li><a href="#" onmouseover="mopen('m4')" onmouseout="mclosetime()">客户管理</a>
			<div id="m4" onmouseover="mcancelclosetime()" onmouseout="mclosetime()">
				<a href="javascript:systemSiteManage();" onclick="change_bg(this)">站点设置</a>
				<a href="javascript:systemClientManage();" onclick="change_bg(this)">客户信息</a>
				<a href="javascript:systemUserManage();" onclick="change_bg(this)">账户信息</a>
			</div>
		</li>
		<li><a href="#" onmouseover="mopen('m5')" onmouseout="mclosetime()">学习卡管理</a>
			<div id="m5" onmouseover="mcancelclosetime()" onmouseout="mclosetime()">
				<a href="javascript:systemCardManage();" onclick="change_bg(this)">制卡管理</a>
				<a href="javascript:systemCardTypeManage();" onclick="change_bg(this)">卡类型管理</a>
				<a href="javascript:systemCardAllotManage();" onclick="change_bg(this)">卡类型分配管理</a>
				<a href="javascript:systemCardStatusManage();" onclick="change_bg(this)">卡状态管理</a>
			</div>
		</li>
		<li><a href="javascript:systemOrderManage();" onmouseover="mopen('')" onmouseout="mclosetime()">订单管理</a>
			<div> </div>
		</li>
		<li><a href="javascript:systemCMSManage();" onmouseover="mopen('')" onmouseout="mclosetime()">内容管理</a>
			<div> </div>
		</li>
		<li><a href="javascript:authManage();" onmouseover="mopen('')" onmouseout="mclosetime()">系统维护</a>
			<div> </div>
		</li>
		<li><a href="javascript:EditPassword();" onmouseover="mopen('')" onmouseout="mclosetime()">修改密码</a>
			<div> </div>
		</li>
		<li><a href="javascript:logout();" onmouseover="mopen('')" onmouseout="mclosetime()">退出系统</a>
			<div> </div>
		</li>
	</ul>
	<div style="clear:both"></div>
</body>
</html>
