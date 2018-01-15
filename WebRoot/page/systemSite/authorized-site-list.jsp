<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>绑定站点</title>
<%@ include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
 <script type="text/javascript">

	function bindSite(typeId, siteId){
		var p = {'typeId':typeId,'siteId':siteId};
		$.post("${ctx }/system/SystemCardTypeSiteOrg.do?method=saveSite", p,
  			function(data){
  				window.location.reload(true);
  		});
	}
	
	function unbindSite(typeId, siteId){
		var p = {'typeId':typeId,'siteId':siteId};
		$.post("${ctx }/system/SystemCardTypeSiteOrg.do?method=deleteSite", p,
  			function(data){
  				window.location.reload(true);
  		});
	}
	
	function bindOrgan(siteId){
		window.location.href = "${ctx }/system/SystemCardTypeSiteOrg.do?method=getSystemOrganList&typeId=${typeId}&siteId="+siteId;
	}
</script>
	</head>
	<body bgcolor="#E7E7E7">
		
		<div id="Container">
			<div style="text-align:right"><button onclick="window.location.href='${ctx}/system/SystemCardType.do?method=list'">返回卡类型</button></div>
		    <div id="Left">
		    	<div style="margin-left:40px">
		    		已绑定的站点<br/>
		    	</div>
				<div id="Left_Content">
					<c:forEach items="${bindList}" var="site" varStatus="status">
						${status.index + 1 }&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0)" onclick="bindOrgan('${site.id}')">${site.domainName }</a>&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0)" onclick="unbindSite('${typeId }','${site.id}')">解绑</a><br/><br/>
					</c:forEach>
				</div>
			</div>
			<div id="Right">
				<div style="margin-left:40px">
					未绑定的站点<br/>
				</div>
				<div id="Right_Content">
					<c:forEach items="${unBindList}" var="st" varStatus="st_status">
						${st_status.index + 1 }&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0)" onclick="bindOrgan('${st.id}')">${st.domainName }</a>&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0)" onclick="bindSite('${typeId }','${st.id}')">绑定</a><br/><br/>
					</c:forEach>
				</div>
			</div>
			
		</div>
	 		
	</body>
</html>
