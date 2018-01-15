<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
		<title>课件预览</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<%@include file="/commons/taglibs.jsp"%>
	</head>
<body>
		<c:choose>
			<c:when test="${StudyCourseware.type eq 3}">
			  <c:if test="${fn:startsWith(StudyCourseware.path,'http')}">
			      <center>
					<iframe height="768px" width="1024px" src="${StudyCourseware.path}"></iframe>
				  </center>
				</c:if>
			  <c:if test="${fn:startsWith(StudyCourseware.path,'<script')}">
			      ${StudyCourseware.path}
			  </c:if>
			</c:when>
			<c:when test="${StudyCourseware.type eq 1}">
				<c:if test="${fn:startsWith(StudyCourseware.path,'<iframe') || fn:startsWith(StudyCourseware.path,'<script')}">
			      <center>
					${StudyCourseware.path}
				  </center>
				</c:if>
				<c:if test="${empty StudyCourseware.path  }">
					<center>该课件暂无视频！<button onclick="window.close();">关闭</button></center>
					
				</c:if>
			</c:when>
		</c:choose>
	</body>
</html>