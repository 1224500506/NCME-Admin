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
	</head>
	<body>
		<div id="container">

			<div id="content">
				<div class="warp">
					
					<div id="main_cont">
						
						<div style="height:450px;width:600px;padding-left:50px;">
							<c:choose>
								<c:when test="${fn:startsWith(curWare.path, 'http')}">
									<iframe width="1000" height="1000" src="${curWare.path}" scrolling="no" frameborder="1"></iframe>
								</c:when>
								<c:otherwise>
									${curWare.path}		
								</c:otherwise>
							</c:choose>
						</div>
						
						<div class="clearfix"></div>
						
					</div>
				</div>
			</div>

		</div>
	</body>
</html>