<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/functions" prefix= "fn"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="jodd" uri="http://www.springside.org.cn/jodd_form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.newworld.com/tags/examTag" prefix="exam" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxAll" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>

<c:set var="ctxAdminURL"   value="${ctxPropertiesMap.adminURL}"   />
<c:set var="ctxPeixunURL"  value="${ctxPropertiesMap.peixunURL}"  />
<c:set var="ctxQiantaiURL" value="${ctxPropertiesMap.qiantaiURL}" />

<script type="text/javascript">
	var ctxJS = "${pageContext.request.contextPath}";
	String.prototype.getBytes = function() {
	    var cArr = this.match(/[^\x00-\xff]/ig);
	    return this.length + (cArr == null ? 0 : cArr.length);
	};
</script> 