<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理平台</title>
<%@include file="/commons/taglibs.jsp"%>
</head>
<frameset id="FRM_WHOLE" rows="30,60,*,30" frameborder="no" border="0" framespacing="0">
	<frame id="FRM_TOP" src="${ctx }/page/top.jsp" marginheight="0" marginwidth="0" noresize="noresize" scrolling="NO">
	<frame id="FRM_TITLE" src="${ctx }/page/nav/nav2.jsp" marginheight="0" marginwidth="0" noresize="noresize" scrolling="NO">
	<frame id="mainframe" name="mainframe" src="${ctx }/page/welcome.jsp" marginheight="0" marginwidth="0" noresize="noresize" scrolling="yes">
	<frame id="FRM_DOWN" src="${ctx }/page/down.jsp" marginheight="0" marginwidth="0" noresize="noresize" scrolling="NO">
</frameset>
<noframes>系统提示：对不起，您的浏览器不支持框架！</noframes>
</html>