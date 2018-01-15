<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.baidu.ueditor.ActionEnter,com.baidu.ueditor.WeehoActionEnter"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");	
	String rootPath = application.getRealPath( "/" );
	
	String weehoMode = request.getParameter("weehoMode"); //YHQ，2017-03-15，自定义上传
	if (weehoMode != null) {
	   out.write( new WeehoActionEnter( request, rootPath ).exec());
	} else {
	   out.write( new ActionEnter( request, rootPath ).exec());
	}
	
	
%>