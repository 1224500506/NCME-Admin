<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.hys.exam.utils.MD5Util" %>
<%@ page import="com.hys.auth.model.HysUsers" %>
<%@ page import="com.hys.exam.constants.Constants" %>
<%
   	HysUsers user = (HysUsers)request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
	String verify = "";
	String name = "";
   	if(null != user){
		  name = user.getLoginName();
		  String pass = user.getPassword();
		  verify = MD5Util.string2MD5(name + "_" + pass);
   	}
   	
%>
<!DOCTYPE HTML>
<html>
<head>
<title>menu</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<META HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Window-target" CONTENT="_top">
<META NAME="robots" CONTENT="all">
<%@include file="/commons/taglibs.jsp"%>
<style type="text/css">

<!--

body {
	margin: 0px;
	padding:0px;
	font:"宋体";
	font-size:12px;
	text-decoration:none
}


/*初始，所有下拉隐藏*/
#xiala1, #xiala2, #xiala3, #xiala4, #xiala5{
	display:none
}

#xiala1 a,#xiala2 a,#xiala3 a,#xiala4 a,#xiala5 a{
	color:#fff; text-decoration:none; padding-left:20px; padding-top:8px; float:left;
}

/*下拉文字鼠标hover样式，有下划线*/
#xiala1 a:hover,#xiala2 a:hover,#xiala3 a:hover,#xiala4 a:hover,#xiala5 a:hover{
	text-decoration:underline;
}	

.menux{ background:#349FBE;height:35px;}	/*整体menu背景*/#0000FF
.menu{margin:0px auto; padding-left:43px;}	/*ul，可修改padding-left的数值*/
.menu li{ list-style:none; float:left;}		/*不需要修改*/
.menu li a{ 
	display:block; height:35px; width:80px;border-left:1px solid #fff; 
	line-height:35px;  font-size:14px; font-weight:bold; text-align:center; 
	color:#fff; text-decoration:none;
}	

.menu li a:hover{ background:#06DCF7}		/*鼠标经过menu文字时的样式*/

.menuxiala{width:100%; background:#8C7E7E; height:40px; clear:both}	/*下拉菜单上级div，可根据修要修改。*/

-->

</style>
<script type="text/javascript">
	var ctx = '${ctx}';
	
	var study_ctx = 'http://www.anquan100.com';
	////var study_ctx = 'http://www.ncme.org.cn';

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
	
	//继教地区表
	function systemAdminOrganManage(){
		parent.mainframe.location = ctx + '/system/systemAdminOrgan.do?method=list';
	}
	
	//组织机构表
	function systemOrgManage(){
		parent.mainframe.location = ctx + '/system/systemOrg.do?method=list';
	}
	
	function systemUserManage(){
		parent.mainframe.location = ctx + '/system/systemUser.do?method=list';
	}
	
	function expertsManage(){
		window.open(study_ctx + '/ajpx/experts/list?sign=<%=verify%>');
	}
	
	function systemUserStat(){
		window.open(study_ctx + '/ajpx/special/user/stat/userList?sign=<%=verify%>');
	}
	
	//快递管理
	function systemPostHistoryManage(){
		parent.mainframe.location = ctx + '/system/SystemPostHistory.do?method=list';
	}
	
	function searchCourseManage(){
		parent.mainframe.location = ctx + '/course/studyCourseView.do';	//no use the tree
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
	
	//学习卡管理
	function systemCardManage(){
		parent.mainframe.location = ctx + '/system/SystemCard.do?method=list&s=index&pageSize=500';
	}
	
	//学习卡管理---制卡管理
	function systemCardCreate(){
		parent.mainframe.location = ctx + '/system/SystemCard.do?method=createCardList';
	}
	
	//卡类型管理
	function systemCardTypeManage(){
		parent.mainframe.location = ctx + '/system/SystemCardType.do?method=list';
	}
	
	//卡类型分配管理
	function systemCardAllotManage(){
		parent.mainframe.location = ctx + '/system/SystemCard.do?method=allotList&s=index';
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
	
	//BUG管理
	function systemBugLog(){
		parent.mainframe.location = ctx + '/system/SystemBugLog.do?method=list&type=1';
	}

	function dis(id){
		var spans=document.getElementsByTagName("span");
		for ( var int = 0; int < spans.length; int++) {
			if(int!=id){
				spans[int].style.display="none";
			}
		}
	}
</script>
</head>
<body>
<div class="menux">

	<ul class="menu">
	
		<li><a href="javascript:toIndex();" onmousemove="javascript:dis();">网站首页</a></li>
		
		<%
			if(name.equals("admin")){
		%>
		
		<li><a href="javascript:propManage();" onmousemove="javascript:dis();">属性管理</a></li>
		
		<li><a href="javascript:systemPropManage();" onmousemove="javascript:dis();">行业岗位</a></li>
		
		<li><a href="#" onmousemove="xiala1.style.display='block';javascript:dis(0);">题库管理↓</a></li>
		
		<li><a href="#" onmousemove="xiala2.style.display='block';javascript:dis(1);">考试管理↓</a></li>
		
		<li><a href="#" onmousemove="xiala3.style.display='block';javascript:dis(2);">课程管理↓</a></li>
		
		<li><a href="#" onmousemove="xiala4.style.display='block';javascript:dis(3);">客户管理↓</a></li>
		
		<li><a href="#" onmousemove="xiala5.style.display='block';javascript:dis(4);">学习卡管理</a></li>
		
		<li><a href="javascript:systemOrderManage();" onmousemove="javascript:dis();">订单管理</a></li>
		
		<li><a href="javascript:systemCMSManage();" onmousemove="javascript:dis();">内容管理</a></li>
		
		<li><a href="javascript:authManage();" onmousemove="javascript:dis();">系统维护</a></li>
		
		<li><a href="javascript:EditPassword();" onmousemove="javascript:dis();">修改密码</a></li>
		
		<li><a href="javascript:systemBugLog();" onmousemove="javascript:dis();">BUG管理</a></li>
		
		<%}else if(name.equals("market")){ %>
			
			<li><a href="javascript:systemOrderManage();" >订单管理</a></li>
			<li><a href="javascript:systemUserManage();" >用户管理</a></li>
			
		<%} %>
		
		<li><a href="javascript:logout();" onmousemove="javascript:dis();">退出</a></li>
	</ul>

</div>

<div class="menuxiala">

	<span id="xiala1" style="padding-left:274px;" onmousemove="this.style.display='block'" >
		<a href="javascript:questManage();">试题管理</a>
		<a href="javascript:paperManage();">试卷管理</a>
		<a href="javascript:paperTypeManage();">试卷目录</a>
	</span>
	
	<span id="xiala2" style="padding-left:346px;" onmousemove="this.style.display='block'" >
		<a href="javascript:examManage();">考试设置</a>
		<a href="javascript:examTypeManage();">考试目录</a>
	</span>
	
	<span id="xiala3" style="padding-left:434px;" onmousemove="this.style.display='block'" >
		<a href="javascript:studyCoursewareManage();">课件管理</a>
		<a href="javascript:searchCourseManage();">课程设置</a>
		<a href="javascript:courseAuthorize();">课程授权</a>
		<a href="javascript:searchCourseTypeManage();">课程目录</a>
	</span>
	
	<span id="xiala4" style="padding-left:510px;" onmousemove="this.style.display='block'" >
		<a href="javascript:systemSiteManage();">站点设置</a>
		<a href="javascript:systemAdminOrganManage();">继教地区管理</a>
		<!-- <a href="javascript:systemClientManage();">客户信息</a> -->
		<a href="javascript:systemUserManage();">用户信息</a>
		<a href="javascript:expertsManage();">专家管理</a>
		<a href="javascript:systemPostHistoryManage();">快递寄送管理</a>
		<!-- <a href="javascript:systemUserStat();">用户统计</a> -->
	</span>
	
	<span id="xiala5" style="padding-left:590px;" onmousemove="this.style.display='block'" >
		<a href="javascript:systemCardCreate();">制卡管理</a>
		<a href="javascript:systemCardManage();">学习卡管理</a>
		<a href="javascript:systemCardTypeManage();">卡类型管理</a>
		<a href="javascript:systemCardAllotManage();">卡类型分配管理</a>
		<a href="javascript:systemCardStatusManage();">卡状态管理</a>
	</span>

</div>

</body>
</html>
