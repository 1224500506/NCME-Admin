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
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/default090909.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/lay.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">

<link rel="stylesheet" href="${ctx}/js/Validform_v5.3/css/valid.css"
	type="text/css" media="all" />
<script type="text/javascript"
	src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/Validform_v5.3/js/Validform_v5.3.js"></script>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<style type="text/css">
	.row_tip2 {
		width: 20%;
		background: #CCCCCC;
		font-weight: bold;
	}
</style>
</head>
<body>
	<form class="fm1" id="fm1" name="fm1"
		action="${ctx}/system/SystemBugLog.do?method=save" method="post"
		enctype="multipart/form-data">
		<table border="0" cellspacing="1" cellpadding="0" width="90%"
			class="gridtable">
			<tr>
				<td align="center" colspan="3" class="row_tip2"  >
					BUG/日志信息</td>
			</tr>
			<tr>
				<td class="row_tip2"   align="right">类型：</td>
				<td width="50%"><select id="type" name="type">
						<option value="1"
							<c:if test="${log.bugLevel== 1 || type == 1}">selected</c:if>>BUG
						
						<option value="2"
							<c:if test="${log.bugLevel== 2 || type == 2}">selected</c:if>>日志
						
				</select></td>
				<td width="30%">
					<div class="Validform_checktip"></div>
				</td>
			</tr>
			<tr>
				<td class="row_tip2"   align="right"><font
					class="red_star">*</font>BUG/日志标题：</td>
				<td width="50%"><input type="text" id="title" class="editInput"
					name="title" maxlength="50" size="50" datatype="*"
					nullmsg="请输入BUG/日志名称！" errormsg="BUG/日志名称最多50个字符！"
					value="${log.title}" /></td>
				<td width="30%">
					<div class="Validform_checktip"></div>
				</td>
			</tr>
			<tr>
				<td class="row_tip2"   align="right"><font
					class="red_star">*</font>BUG/日志内容：</td>
				<td align="left" width="50%"><textarea rows="10" cols="150" 
						name="content" id="content" maxlength="1000" 
						size="25" datatype="*" nullmsg="请输入BUG/日志内容！"
						errormsg="BUG/日志内容最多1000个字符！">${log.content}</textarea></td>
				<td width="30%">
					<div class="Validform_checktip"></div>
				</td>
			</tr>
			<c:if test="${log.type == 1 || type == 1}">
				<tr>
					<td class="row_tip2"   align="right">BUG级别：</td>
					<td width="50%"><select id="bugLevel" name="bugLevel">
							<option value="1"
								<c:if test="${log.bugLevel== 1 }">selected</c:if>>一级
							<option value="2"
								<c:if test="${log.bugLevel== 2 }">selected</c:if>>二级
							<option value="3"
								<c:if test="${log.bugLevel== 3 }">selected</c:if>>三级
					</select></td>
					<td width="30%">
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip2"   align="right"><font
						class="red_star">*</font>BUG修改状态：</td>
					<td width="50%"><select id="bugStatus" name="bugStatus">
							<option value="0"
								<c:if test="${log.bugStatus== 0 }">selected</c:if>>未修改
							<option value="1"
								<c:if test="${log.bugStatus== 1 }">selected</c:if>>已修改
							<option value="2"
								<c:if test="${log.bugStatus== 2 }">selected</c:if>>不需修改
							<option value="3"
								<c:if test="${log.bugStatus== 3 }">selected</c:if>>暂无法修改
							<option value="4"
								<c:if test="${log.bugStatus== 4 }">selected</c:if>>不是问题，打回
					</select></td>
					<td width="30%">
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip2"   align="right">BUG状态：</td>
					<td width="50%"><select id="status" name="status">
							<option value="1" <c:if test="${log.status== 1 }">selected</c:if>>正常


							
							<option value="-1"
								<c:if test="${log.status== -1 }">selected</c:if>>删除
					</select></td>
					<td width="30%">
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<c:if test="${log.id>0 }">
					<tr>
						<td class="row_tip2"   align="right">回复：</td>
						<td width="50%"><textarea rows="5" cols="100" name="reply"
								id="reply" maxlength="1000" class="editInput" size="25"
								errormsg="回复最多1000个字符！">${log.reply}</textarea></td>
						<td width="30%">
							<div class="Validform_checktip"></div>
						</td>
					</tr>
				</c:if>
			</c:if>

			<tr>
				<td class="row_tip2"   align="right" >
					BUG/日志创建者：</td>
				<td width="50%"><input type="text" id="creator"
					class="editInput" name="creator" maxlength="10" size="25"
					value="${log.creator}"
					<c:if test="${log.id > 0}">readonly="readonly"</c:if> /></td>
				<td width="30%">
					<div class="Validform_checktip"></div>
				</td>
			</tr>
			<c:if test="${log.id >0 }">
				<tr>
					<td class="row_tip2"   align="right">BUG/日志修改者：</td>
					<td width="50%"><input type="text" id="finisher" class="editInput"
						name="finisher" maxlength="10" size="25" value="${log.finisher}" />
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
			</c:if>
			<tr>
				<td class="row_tip2"   align="right">BUG/日志创建时间：</td>
				<td width="50%"><c:choose>
						<c:when test="${log.id >0}">
							<input type="text" name="createDate" id="createDate" class="editInput"
								value="<fmt:formatDate value="${log.createDate}" pattern="yyyy-MM-dd"/>" />
						</c:when>
						<c:otherwise>
							<input type="text" name="createDate" id="createDate" class="editInput"
								onClick="WdatePicker()"
								value="<fmt:formatDate value="${log.createDate}" pattern="yyyy-MM-dd"/>" />
						</c:otherwise>
					</c:choose></td>
				<td width="30%">
					<div class="Validform_checktip"></div>
				</td>
			</tr>
			<c:if test="${log.id >0 }">
				<tr>
					<td class="row_tip2"   align="right">BUG/日志修改时间：</td>
					<td width="50%"><input type="text" name="updateDate" id="updateDate" class="editInput"
						value="<fmt:formatDate value="${log.updateDate}" pattern="yyyy-MM-dd"/>"
						onClick="WdatePicker()" /></td>
					<td >
						<div class="Validform_checktip"></div>
					</td>
				</tr>
			</c:if>
			<tr>
				<td class="row_tip2"   align="right">附件1：</td>
				<td width="50%"><input type="file" name="file1" id="filePath1" /><font
					color="red">*请用jpg格式的图片</font></td>
				<td width="30%">
					<div class="Validform_checktip"></div>
				</td>
			</tr>
			<tr>
				<td class="row_tip2"   align="right">附件2：</td>
				<td width="50%"><input type="file" name="file2" id="filePath2" /><font
					color="red">*请用jpg格式的图片</font></td>
				<td width="30%">
					<div class="Validform_checktip"></div>
				</td>
			</tr>
			<tr>
				<td class="row_tip2"   align="right">附件3：</td>
				<td width="50%"><input type="file" name="file3" id="filePath3" /><font
					color="red">*请用jpg格式的图片</font></td>
				<td width="30%">
					<div class="Validform_checktip"></div>
				</td>
			</tr>

			<td colspan="6" align="center" class="row_tip2"><input
				type="submit" class="btn_03s" value="保 存" /> <input type="button"
				class="btn_03s" value="返 回" onclick="window.location.href='${ctx}/system/SystemBugLog.do?method=list&type=${log.type }'" />
			</td>
			</tr>
		</table>
		<input type="hidden" id="id" name="id" value="${log.id}" /> <input
			type="hidden" name="org.apache.struts.taglib.html.TOKEN"
			value="${sessionScope['org.apache.struts.action.TOKEN']}" />
	</form>
	<script type="text/javascript">
		$(".fm1").Validform({
			datatype : {//自定义datatype类型 浮点类型
				"float" : function(gets, obj, curform, datatype) {
					var reg = new RegExp("^\\d+(\\.\\d+)?$");
					if (!reg.exec(gets)) {
						return false;
					}
					return true;
				}
			}
		});		
	</script>
</body>
</html>