<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/commons/taglibs.jsp"%>
		<link href="${ctx}/css/auth.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<title>Insert title here</title>
<style type="text/css">
<!--
.nav {
	MARGIN: 1px 0; 
	WIDTH: 100%; 
	FONT-FAMILY: verdana; 
	HEIGHT: 21px; 
	BACKGROUND-COLOR: #006699;
	font-family:Arial, Helvetica, sans-serif;
    font-size:12px;
}
.nav UL {
	PADDING: 0px; 
	DISPLAY: block; 
	MARGIN: 0px; 
	LIST-STYLE-TYPE: none; 
	HEIGHT: 21px; 
	BACKGROUND-COLOR: #006699;
	COLOR: #ffffff; 
}
.nav LI {
	BORDER-RIGHT: #ffffff 1px solid; 
	DISPLAY: block; 
	FLOAT: left; 
	HEIGHT: 21px;
	font-family:Arial, Helvetica, sans-serif;
    font-size:12px;
}
.nav LI A {
	PADDING:1px 15px 0; 
	DISPLAY: block; 
	FONT-WEIGHT: none; 
	COLOR: #ffffff; 
	LINE-HEIGHT: 20px; 
	TEXT-DECORATION: none;
}
.nav LI A:hover {
	COLOR:#FFFF00; 
	BACKGROUND-COLOR: #0099FF; 
	TEXT-DECORATION: none;
}
.current{
  color:#ffffff;
  background:#0099FF;
}
.nav li#date{
  color:#ffffff;
  PADDING:2px 15px 0; 
}
-->
</style>
<script type="text/javascript">
function questManage() {
	parent.mainframe.location = "${ctx}/questionManage/questionManage.do?type=clear";
}

function authManage() {
	parent.mainframe.location = '${ctx}/page/auth_Main.jsp?t=' + Math.random();
}

function logout(){
	window.location.href = '${ctx}/logout.do';
}

function EditPassword(){
	parent.mainframe.location = '${ctx}/page/auth_EditPwd.jsp?t=' + Math.random();
}

function importQues(){
	parent.mainframe.location = '${ctx}/page/examImportQuestion.jsp?t=' + Math.random();
}

function searchNotQues(){
	parent.mainframe.location = '${ctx}/questionManage/SearchNotPropQuestion.do';
}

function propManage(){
	parent.mainframe.location = '${ctx}/page/propManage/propManage.jsp';
}

function systemClientManage(){
	parent.mainframe.location = '${ctx}/system/systemClient.do?method=list';
}

function systemSiteManage(){
	parent.mainframe.location = '${ctx}/system/systemSite.do?method=list';
}

function systemUserManage(){
	parent.mainframe.location = '${ctx}/system/systemUser.do?method=list';
}

function searchCourseManage(){
	parent.mainframe.location = '${ctx}/course/curIndex.do';
}

function studyCoursewareManage(){
	parent.mainframe.location = '${ctx}/course/coursewareList.do';
}

function searchCourseTypeManage(){
	parent.mainframe.location = '${ctx}/course/studyCourseTypeIndex.do';//
}

function paperManage(){
	parent.mainframe.location = '${ctx}/exam/paper/paperIndex.do';//
}

function examManage(){
	parent.mainframe.location = '${ctx}/exam/examIndex.do';//
}

function paperTypeManage(){
	parent.mainframe.location = '${ctx}/exam/paper/examPaperTypeIndex.do';//
}

function examTypeManage(){
	parent.mainframe.location = '${ctx}/exam/category/examCategoryIndex.do';//
}

function courseAuthorize(){
	parent.mainframe.location = '${ctx}/ncme/ncmeCourseAuthorizeList.do';//
}

	var $c = function(array) {
		var nArray = [];
		for ( var i = 0; i < array.length; i++)
			nArray.push(array[i]);
		return nArray;
	};
	Array.prototype.each = function(func) {
		for ( var i = 0, l = this.length; i < l; i++) {
			func(this[i], i);
		}
		;
	};
	document.getElementsByClassName = function(cn) {
		var hasClass = function(w, Name) {
			var hasClass = false;
			w.className.split(' ').each(function(s) {
				if (s == Name)
					hasClass = true;
			});
			return hasClass;
		};
		var elems = document.getElementsByTagName("*") || document.all;
		var elemList = [];
		$c(elems).each(function(e) {
			if (hasClass(e, cn)) {
				elemList.push(e);
			}
		})
		return $c(elemList);
	};
	function change_bg(obj) {
		var a = document.getElementsByClassName("nav")[0]
				.getElementsByTagName("a");
		for ( var i = 0; i < a.length; i++) {
			a[i].className = "";
		}
		obj.className = "current";
	}
</script>
	</HEAD>
	<BODY>
<DIV class="nav">
  <UL>
    <LI><A href="javascript:questManage();" onclick="change_bg(this)">题库管理</A></LI>
    <LI><A href="javascript:paperTypeManage();" onclick="change_bg(this)">试卷分类管理</A> </LI>
    <LI><a href="javascript:paperManage();" onclick="change_bg(this)">试卷管理</a> </LI>
    <LI><A href="javascript:examTypeManage();" onclick="change_bg(this)">考试分类管理</A> </LI>
    <LI><A href="javascript:examManage();" onclick="change_bg(this)">考试管理</A> </LI>
    <LI><A href="javascript:searchCourseTypeManage();" onclick="change_bg(this)">课程分类管理</A> </LI>
    <LI><A href="javascript:searchCourseManage();" onclick="change_bg(this)">课程管理</A> </LI>
    <LI><A href="javascript:courseAuthorize();" onclick="change_bg(this)">课程授权</A> </LI>
    <LI><A href="javascript:studyCoursewareManage();" onclick="change_bg(this)">课件管理</A> </LI>
    <LI><A href="javascript:systemClientManage();" onclick="change_bg(this)">客户管理</A> </LI>
    <LI><A href="javascript:systemSiteManage();" onclick="change_bg(this)">站点管理</A> </LI>
    <LI><A href="javascript:systemUserManage();" onclick="change_bg(this)">帐户管理</A> </LI>
    <LI><A href="javascript:propManage();" onclick="change_bg(this)">属性维护</A> </LI>
    <LI><A href="javascript:authManage();" onclick="change_bg(this)">系统管理</A> </LI>
    <LI><A href="javascript:EditPassword();" onclick="change_bg(this)">修改密码</A> </LI>
    <LI><A href="javascript:logout();" onclick="change_bg(this)">退出系统</A> </LI>
    
  </UL>
</DIV>
	</BODY>
</HTML>