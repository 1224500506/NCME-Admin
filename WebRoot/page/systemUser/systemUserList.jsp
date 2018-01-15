<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
</style>
</head>
<body>
<jsp:include page="/page/tableColor.jsp"></jsp:include>
<form action="${ctx}/system/systemUser.do?method=list" method="post" name="queryForm">
<table class="its" width="90%" cellpadding="1" cellspacing="0" border="0">
	<tr>
		<td width="300px">证件号码:
			<input type="text" name="model.certificateNo" id="certificateNo"  class="form-control" width="50%" maxlength="100" value="${systemUserForm.model.certificateNo}">
		</td>
		<td width="300px">姓名:
			<input type="text" name="model.realName" id="realName"  class="form-control" maxlength="20" value="${systemUserForm.model.realName}">
		</td>
		<td colspan="2" align="center">
			<input type="submit" class="btn btn-primary" value="查询" />&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="btn btn-primary" onclick="clearForm('queryForm');return false;" title="清空所有查询条件">清空</button>
		</td>	
	</tr>
</table>
</form>
<br/>
	<form id="listfm" name="listfm" method="post" action="${ctx}/system/systemUser.do?method=list">
	<display:table requestURI="${ctx}/system/systemUser.do?method=list"
				 id="systemUser" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:100%;" class="ITS" keepStatus="true"
				  decorator="com.hys.exam.displaytag.TableOverOutWrapper" >
		<display:caption>查询结果  <span style="color:red">${meg}</span></display:caption>
		<display:column title="序号" style="width:12px;text-align:center">
			${systemUser_rowNum}
		</display:column>
		<display:column title="ID" property="userId" style="width:20px;text-align:center" /> 
		<display:column title="姓名" property="realName" style="width:150px;text-align:center" /> 
		<display:column title="证件号码" property="certificateNo" style="width:120px;text-align:center">
		</display:column>
		<display:column title="登录名" property="accountName" style="width:120px;text-align:center" />
		<display:column title="邮箱" property="email" style="width:160px;text-align:center" />
		
		<display:column title="手机" property="mobilPhone"  style="width:100px;text-align:center">
		</display:column>
		<display:column title="单位" property="workUnit"  style="width:300px;text-align:center">
		</display:column>
		<display:column title="注册时间" property="regDate"  style="width:200px;text-align:center">
		</display:column>
		<display:column title="操作" style="width:80px;text-align:center">
			<a href="${ctx}/system/systemUser.do?method=view&id=${systemUser.userId}" title="查看详情">详情</a> 
		</display:column>
	</display:table>
    <input type="hidden" name="org.apache.struts.taglib.html.TOKEN"  value="${sessionScope['org.apache.struts.action.TOKEN']}" />
	</form>
	<script type="text/javascript">
		<c:if test="${MESSAGE != null && MESSAGE != ''}">
	        alert('${MESSAGE}');
	        <c:set var="MESSAGE" scope="session" value=""></c:set>
		</c:if>
	</script>
</body>
</html>
<script type="text/javascript">
	function deleteStudyCourseware(){
	  if($("input[name='ids']:checked").length == 0){
	    alert('请选择需要删除的帐户!');
	    return ;
	  }
	
	  if(confirm("确认删除!")){
	    $("#listfm").submit();
	  }
	}
</script>
