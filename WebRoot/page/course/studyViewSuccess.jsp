<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<%@include file="/commons/taglibs.jsp"%>
		<script type="text/javascript" src="${ctx}/js/UI/jquery-1.8.0.min.js"></script>
	</head>
	<body>
		====${succFlag}====
		<script type="text/JavaScript">
			<c:choose>
				<c:when test="${succFlag eq 'add'}">
					parent.parent.reloadPage();	
				</c:when>
				<c:otherwise>
					parent.reloadPage();
				</c:otherwise>
			</c:choose>
		</script>
	</body>
</html>