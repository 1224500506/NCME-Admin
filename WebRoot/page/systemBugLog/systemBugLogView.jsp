<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BUG日志编辑</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/default090909.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/lay.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">

<link rel="stylesheet" href="${ctx}/js/Validform_v5.3/css/valid.css" type="text/css" media="all" />
</head>
<body>
<table border="0" cellspacing="1" cellpadding="0" width="90%" class="gridtable">
	<tr>
		<td align="center" colspan="3" class="row_tip" height="25">
			BUG/日志信息
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			<font class="red_star">*</font>BUG/日志标题：
		</td>
		<td style="width:65%;" >${log.title}</td>
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			<font class="red_star">*</font>BUG/日志内容：
		</td>
		<td align="left" style="width:65%;"><textarea rows="10" cols="150">${log.content}</textarea></td>
	</tr>
	<c:if test="${log.type == 1 }">
		<tr>
			<td class="row_tip" height="25" align="right">
				BUG级别：
			</td>
			<td style="width:65%;">
				<c:if test="${log.bugLevel== 1 }">一级</c:if> 
				<c:if test="${log.bugLevel== 2 }">二级</c:if>
				<c:if test="${log.bugLevel== 3 }">三级</c:if> 
			</td>
		</tr>
		<tr>
			<td class="row_tip" height="25" align="right">
				<font class="red_star">*</font>BUG修改状态：
			</td>
			<td style="width:65%;">
				<c:if test="${log.bugStatus== 0 }">未修改</c:if> 
				<c:if test="${log.bugStatus== 1 }">已修改</c:if> 
				<c:if test="${log.bugStatus== 2 }">不需修改</c:if> 
				<c:if test="${log.bugStatus== 3 }">暂无法修改</c:if> 
				<c:if test="${log.bugStatus== 4 }">不是问题，打回</c:if> 
			</td>
		</tr>
		<tr>
			<td class="row_tip" height="25" align="right">
				BUG状态：
			</td>
			<td style="width:65%;">
				
				<c:if test="${log.status== 1 }">正常</c:if> 
				<c:if test="${log.status== -1 }">删除</c:if> 
			</td>
		</tr>
		<c:if test="${log.id>0 }">
			<tr>
				<td class="row_tip" height="25" align="right">
					回复：
				</td>
				<td style="width:65%;">${log.reply}</td>
		     </tr>
	     </c:if>
     </c:if>
	<tr>
		<td class="row_tip" height="25" align="right" align="right">
			BUG/日志创建者：
		</td>
		<td style="width:65%;">${log.creator}</td>		
	</tr>
	<c:if test="${log.id >0 }">
		<tr>
			<td class="row_tip" height="25" align="right">
				BUG/日志修改者：
			</td>
			<td style="width:65%;">${log.finisher}</td>	
		</tr>
	</c:if>
	<tr>
		<td class="row_tip" height="25" align="right">
			BUG/日志创建时间：
		</td>
		<td style="width:65%;">${log.createDate}</td>	
	</tr>
	<c:if test="${log.id >0 }">
	<tr>
		<td class="row_tip" height="25" align="right">
			BUG/日志修改时间：
		</td>
		<td style="width:65%;">${log.updateDate}</td>	
	</tr>
	</c:if>
	<tr>
		<td class="row_tip" height="25" align="right">
			附件1：
		</td>
		<td style="width:65%;" >
			<c:if test="${log.filePath != null }">
				<img alt="tupian" src="${ctx }/upload/content/systemBugLog/${log.filePath}" width="500px" height="400px"> 
			</c:if>
		</td>	
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			附件2：
		</td>
		<td style="width:65%;">
			<c:if test="${log.filePathTwo != null }">
				<img alt="tupian" src="${ctx }/upload/content/systemBugLog/${log.filePathTwo}">
			</c:if>
		</td>	
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			附件3：
		</td>
		<td style="width:65%;">
			<c:if test="${log.filePathThree != null }">
				<img alt="tupian" src="${ctx }/upload/content/systemBugLog/${log.filePathThree}">
			</c:if>
		</td>	
	</tr>
	<td colspan="6" align="center" class="row_tip">
		    <input type="button" class="btn_03s" value="返 回" onclick="javascript:history.back(-1);"/>
		</td>
	</tr>
</table>
<input type="hidden" id="id" name="id" value="${log.id}"/>
<input type="hidden" name="org.apache.struts.taglib.html.TOKEN"  value="${sessionScope['org.apache.struts.action.TOKEN']}" />
<script type="text/javascript">
		    $(".fm1").Validform({
		      datatype:{//自定义datatype类型 浮点类型
			    "float":function(gets,obj,curform,datatype){
						var reg = new RegExp("^\\d+(\\.\\d+)?$");
						if (!reg.exec(gets)){
						  return false;
						}
						return true;
					}
		      }
	        });
</script>
</body>
</html>