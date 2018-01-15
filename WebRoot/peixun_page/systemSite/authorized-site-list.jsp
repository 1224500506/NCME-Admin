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
			<dt>已绑定的站点</dt>
			<dd>
			<c:forEach items="${bindList}" var="site" varStatus="status">
			<dd>
						<span class="fl">${status.index + 1 }.</span>
						<%-- <a href="javascript:void(0)" onclick="bindOrgan('${site.id}')" class="fl ml10" style="min-width:300px">${site.domainName }</a>&nbsp;&nbsp;&nbsp; --%>
						<span class="fl ml10" style="min-width:300px">${site.domainName }</span>&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0)" onclick="unbindSite('${typeId }','${site.id}')" class="fl" style="margin-left:60px;">解绑</a><br/><br/>
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
			
			<c:forEach items="${unBindList}" var="st" varStatus="st_status">
			<dd>
						<span class="fl">${st_status.index + 1 }.</span>
						<%-- <a href="javascript:void(0)" class="fl ml10" style="min-width:300px" onclick="bindOrgan('${st.id}')">${st.domainName }</a>&nbsp;&nbsp;&nbsp;\ --%>
						<span class="fl ml10" style="min-width:300px">${st.domainName }</span>&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0)" class="fl" style="margin-left:60px;" onclick="bindSite('${typeId }','${st.id}')">绑定</a><br/><br/>
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
		<a href="${ctx}/cardManage/SystemCardType.do?method=list" class="fl queren" style="width:70px;">返回</a>
	</div>


</div>


<!-- 修改密码 -->
<div class="toumingdu make01" style="display:none;">
	
</div>

<script type="text/javascript">
function bindSite(typeId, siteId){
		var p = {'typeId':typeId,'siteId':siteId};
		$.post("${ctx }/cardManage/SystemCardTypeSiteOrg.do?method=saveSite", p,
  			function(data){
  				window.location.reload(true);
  		});
	}
	
function unbindSite(typeId, siteId){
		var p = {'typeId':typeId,'siteId':siteId};
		$.post("${ctx }/cardManage/SystemCardTypeSiteOrg.do?method=deleteSite", p,
  			function(data){
  				window.location.reload(true);
  		});
	}
	
function bindOrgan(siteId){
		window.location.href = "${ctx }/cardManage/SystemCardTypeSiteOrg.do?method=getSystemOrganList&typeId=${typeId}&siteId="+siteId;
	}
</script>

</body>
</html>