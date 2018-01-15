<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.beans.factory.FactoryBean"%>
<%@page import="org.springframework.security.intercept.web.FilterSecurityInterceptor"%>
<%@page import="org.springframework.security.intercept.web.FilterInvocationDefinitionSource"%>
<%@ include file="/commons/taglibs.jsp"%>
<%
    ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(application);
    FactoryBean factoryBean = (FactoryBean) ctx.getBean("&customFilterInvocationDefinationSource");
    FilterInvocationDefinitionSource fids = (FilterInvocationDefinitionSource) factoryBean.getObject();
    FilterSecurityInterceptor filter = (FilterSecurityInterceptor) ctx.getBean("filterSecurityInterceptor");
    filter.setObjectDefinitionSource(fids);
%>
<script type="text/javascript">
<!--
	window.location.replace('${ctx}/page/userManage/auth_RefreshSuccess.jsp');
//-->
</script>
