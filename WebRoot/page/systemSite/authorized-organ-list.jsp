<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>绑定继教地区</title>
<%@ include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
 <script type="text/javascript">

	function bindOrgan(organId, siteId){
		var p = {'organId':organId,'siteId':siteId};
		$.post("${ctx }/system/SystemCardTypeSiteOrg.do?method=saveOrgan", p,
  			function(data){
  				window.location.reload(true);
  		});
	}
	
	function unbindOrgan(organId, siteId){
		var p = {'organId':organId,'siteId':siteId};
		$.post("${ctx }/system/SystemCardTypeSiteOrg.do?method=deleteOrgan", p,
  			function(data){
  				window.location.reload(true);
  		});
	}
	
</script>
	</head>
	<body bgcolor="#E7E7E7">
		
		<div id="Container">
			<div style="text-align:right"><button onclick="window.location.href='${ctx}/system/SystemCardTypeSiteOrg.do?method=getSystemSiteList&typeId=${typeId }'">返回站点</button></div>
		    <div id="Left">
		    	<div style="margin-left:40px">
		    		已绑定的地区<br/>
		    	</div>
				<div id="Left_Content">
					<c:forEach items="${bindList}" var="organ" varStatus="status">
						${status.index + 1 }&nbsp;&nbsp;&nbsp;
						${organ.name }&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0)" onclick="unbindOrgan('${organ.organId }','${siteId}')">解绑</a><br/><br/>
					</c:forEach>
				</div>
			</div>
			<div id="Right">
				<div style="margin-left:40px">
					未绑定的地区<br/>
				</div>
				<div id="Right_Content">
					<c:forEach items="${unBindList}" var="org" varStatus="st_status">
						${st_status.index + 1 }&nbsp;&nbsp;&nbsp;
						${org.name }&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0)" onclick="bindOrgan('${org.organId }','${siteId}')">绑定</a><br/><br/>
					</c:forEach>
				</div>
			</div>
			
		</div>
	 		
	</body>
</html>
