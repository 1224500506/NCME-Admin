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
	<div class="lec_zhandian">
		<dl class="fl">
			<dt>已绑定的站点</dt>
			<dd>
			    <c:forEach items="${bindList}" var="organ" varStatus="status">
			    <dd>
						<span class="fl">${status.index + 1 }</span>
						<p>${organ.name }</p>
						<a href="javascript:void(0)" class="fl" style="margin-left:60px;" onclick="unbindOrgan('${organ.organId }','${siteId}')">解绑</a><br/><br/>
				</dd>
				</c:forEach>
			
			<!-- <dd>
				<span class="fl">1</span>
				<a href="www.anquan100.com" class="fl ml10" style="min-width:300px">www.anquan100.com</a>
				<a href="#" class="fl" style="margin-left:60px;">解绑</a>
			</dd>
			<dd>
				<span class="fl">1</span>
				<a href="www.anquan100.com" class="fl ml10" style="min-width:300px">www.anquan100.com</a>
				<a href="#" class="fl" style="margin-left:60px;">解绑</a>
			</dd>
			<dd>
				<span class="fl">1</span>
				<a href="www.anquan100.com" class="fl ml10" style="min-width:300px">www.anquan100.com</a>
				<a href="#" class="fl" style="margin-left:60px;">解绑</a>
			</dd>
			<dd>
				<span class="fl">1</span>
				<a href="www.anquan100.com" class="fl ml10" style="min-width:300px">www.anquan100.com</a>
				<a href="#" class="fl" style="margin-left:60px;">解绑</a>
			</dd>
			<dd>
				<span class="fl">1</span>
				<a href="www.anquan100.com" class="fl ml10" style="min-width:300px">www.anquan100.com</a>
				<a href="#" class="fl" style="margin-left:60px;">解绑</a>
			</dd> -->
		</dl>
		<dl class="fr">
			<dt>未绑定的站点</dt>
			<c:forEach items="${unBindList}" var="org" varStatus="st_status">
			<dd>
				<span class="fl">${st_status.index + 1 }</span>
				<p>${org.name }</p>
				<a href="javascript:void(0)" class="fl" style="margin-left:60px;" onclick="bindOrgan('${org.organId }','${siteId}')">绑定</a><br/><br/>
			</dd>			
			</c:forEach>
			<!-- <dd>
				<span class="fl">1</span>
				<a href="www.anquan100.com" class="fl ml10" style="min-width:300px">www.anquan100.com</a>
				<a href="#" class="fl" style="margin-left:60px;">绑定</a>
			</dd>
			<dd>
				<span class="fl">1</span>
				<a href="www.anquan100.com" class="fl ml10" style="min-width:300px">www.anquan100.com</a>
				<a href="#" class="fl" style="margin-left:60px;">绑定</a>
			</dd>
			<dd>
				<span class="fl">1</span>
				<a href="www.anquan100.com" class="fl ml10" style="min-width:300px">www.anquan100.com</a>
				<a href="#" class="fl" style="margin-left:60px;">绑定</a>
			</dd>
			<dd>
				<span class="fl">1</span>
				<a href="www.anquan100.com" class="fl ml10" style="min-width:300px">www.anquan100.com</a>
				<a href="#" class="fl" style="margin-left:60px;">绑定</a>
			</dd>
			<dd>
				<span class="fl">1</span>
				<a href="www.anquan100.com" class="fl ml10" style="min-width:300px">www.anquan100.com</a>
				<a href="#" class="fl" style="margin-left:60px;">绑定</a>
			</dd> -->
		</dl>
	</div>
	<div class="clear"></div>
	<div class="cas_anniu" style="width:70px;margin:40px auto;">
		<a href="${ctx}/cardManage/SystemCardTypeSiteOrg.do?method=getSystemSiteList&typeId"=${typeId} style="width:70px;">返回</a>
	</div>
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
</body>
</html>
