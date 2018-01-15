<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>绑定继教地区</title>
<%@ include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
 <script type="text/javascript">

	function bindPaycardOrgan(organId, typeId, orgType){
		var p = {'organId':organId,'typeId':typeId,'orgType':orgType};
		$.post("${ctx }/system/SystemCardTypeSiteOrg.do?method=savePaycardOrgan", p,
  			function(data){
  				window.location.reload(true);
  		});
	}
	
	function unbindPaycardOrgan(organId, typeId){
		var p = {'organId':organId,'typeId':typeId};
		$.post("${ctx }/system/SystemCardTypeSiteOrg.do?method=deletePaycardOrgan", p,
  			function(data){
  				window.location.reload(true);
  		});
	}
	
</script>
	</head>
	<body bgcolor="#E7E7E7">
		
		<div id="Container">
			<div style="text-align:right"><button onclick="window.location.href='${ctx}/system/SystemCardType.do?method=list'">返回卡类型</button></div>
		    <div id="Left">
		    	<div style="margin-left:40px">
		    		已绑定的地区<br/>
		    	</div>
				<div id="Left_Content">
					<c:forEach items="${bindList}" var="organ" varStatus="status">
						${status.index + 1 }&nbsp;&nbsp;&nbsp;${organ.name }&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0)" onclick="unbindPaycardOrgan('${organ.organId }','${typeId}')">解绑</a><br/><br/>
					</c:forEach>
				</div>
			</div>
			<div id="Right">
				<div style="margin-left:40px">
					未绑定的地区<br/>
				</div>
				<div id="Right_Content">
					<c:forEach items="${unBindList}" var="org" varStatus="st_status">
						${st_status.index + 1 }&nbsp;&nbsp;&nbsp;${org.name }&nbsp;&nbsp;&nbsp;
						<c:if test="${org.districtId == 10 }">
							<a href="javascript:void(0)" onclick="bindPaycardOrgan('${org.organId }','${typeId}', 1)">绑定</a><br/><br/>
						</c:if>
						<c:if test="${org.districtId != 10 }">
							<a href="javascript:void(0)" onclick="bindPaycardOrgan('${org.organId }','${typeId}', 0)">绑定</a><br/><br/>
						</c:if>
					</c:forEach>
				</div>
			</div>
			
		</div>
	 		
	</body>
</html>
