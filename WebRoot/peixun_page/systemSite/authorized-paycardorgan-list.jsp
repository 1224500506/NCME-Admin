<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<%@include file="/commons/taglibs.jsp"%>
</head>

<%@include file="/peixun_page/top.jsp"%>

<body>
<div class="clear"></div>
<div class="center">
	<div class="lec_zhandian">
		<dl class="fl">
			<dt>已绑定的地区</dt>
			
			<c:forEach items="${bindList}" var="organ" varStatus="status">
			<dd>
						<span class="fl">${status.index + 1 }</span><p>${organ.name }</p>
						<a href="javascript:void(0)" class="fl" style="margin-left:60px;" onclick="unbindPaycardOrgan('${organ.organId }','${typeId}')">解绑</a><br/><br/>
			</dd>
			</c:forEach>
						
		</dl>
		<dl class="fr">
			<dt>未绑定的地区</dt>
			
			<c:forEach items="${unBindList}" var="org" varStatus="st_status">
			<dd>
						<span class="fl">${st_status.index + 1 }</span><p>${org.name }</p>
						<c:if test="${org.districtId == 10 }">
							<a href="javascript:void(0)" class="fl" style="margin-left:60px;" onclick="bindPaycardOrgan('${org.organId }','${typeId}', 1)">绑定</a><br/><br/>
						</c:if>
						<c:if test="${org.districtId != 10 }">
							<a href="javascript:void(0)" class="fl" style="margin-left:60px;" onclick="bindPaycardOrgan('${org.organId }','${typeId}', 0)">绑定</a><br/><br/>
						</c:if>
			</dd>
			</c:forEach>
			
			<!-- <dd>
				<span class="fl">1</span>
				<p>北京市西城区安监局</p>
				<a href="#" class="fl" style="margin-left:60px;">绑定</a>
			</dd>
			<dd>
				<span class="fl">2</span>
				<p>北京市西城区安监局</p>
				<a href="#" class="fl" style="margin-left:60px;">绑定</a>
			</dd>
			<dd>
				<span class="fl">3</span>
				<p>北京市西城区安监局</p>
				<a href="#" class="fl" style="margin-left:60px;">绑定</a>
			</dd>
			<dd>
				<span class="fl">4</span>
				<p>北京市西城区安监局</p>
				<a href="#" class="fl" style="margin-left:60px;">绑定</a>
			</dd>
			<dd>
				<span class="fl">5</span>
				<p>北京市西城区安监局</p>
				<a href="#" class="fl" style="margin-left:60px;">绑定</a>
			</dd> -->
		</dl>
	</div>
	<div class="clear"></div>
	<div class="cas_anniu" style="width:70px;margin:40px auto;">
		<a href="${ctx}/cardManage/SystemCardType.do?method=list" class="fl queren" style="width:70px;">返回</a>
	</div>


</div>


<!-- 修改密码 -->
<div class="toumingdu make01" style="display:none;">
	
</div>

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
</body>
</html>