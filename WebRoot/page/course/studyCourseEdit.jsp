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
		<title>Insert title here</title>
		<%@include file="/commons/taglibs.jsp"%>
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/default090909.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/lay.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">

		<link rel="stylesheet" href="${ctx}/js/Validform_v5.3/css/valid.css" type="text/css" media="all" />
		<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/Validform_v5.3.js"></script>
	</head>
	<body>
		<form class="fm1" id="fm1" name="fm1" action="${ctx}/course/studyCourseEdit.do" method="post" enctype="multipart/form-data">
			<!-- <input type="hidden" id="curTypeId" name="curTypeId" value="${param.curTypeId}" /> -->
			<input type="hidden" id="id" name="course.id" value="${param.courseId}" />
			<input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="${sessionScope['org.apache.struts.action.TOKEN']}" />
			<table border="0" cellspacing="1" cellpadding="0" width="100%" class="gridtable">
				<tr>
					<td align="center" colspan="3" class="row_tip" height="25">
						修改课程
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>课程编号：
					</td>
					<td width="20%">
						${course.courseNumber}
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>课程名称：
					</td>
					<td width="20%">
						<input type="text" id="studyCourseName" name="course.studyCourseName" class="editInput" maxlength="60" size="25" value="${course.studyCourseName}" ignore="ignore" datatype="*0-50" nullmsg="请输入课程名称!" />
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						主讲人：
					</td>
					<td>
						<input type="text" id="teacher" name="course.teacher" class="editInput" maxlength="25" size="25" value="${course.teacher}"/>
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						关键字：
					</td>
					<td>
						<input type="text" id="keyWord" name="course.keyWord" class="editInput" maxlength="200" size="25" value="${course.keyWord}" />
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						学时：
					</td>
					<td>
						<input type="text" id="classHours" name="course.classHours" class="editInput" maxlength="8" size="25" value="${course.classHours}" ignore="ignore" datatype="float" errormsg="请输入数字类型的学时！" />
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<!-- 
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>类型：
					</td>
					<td align="left">
						<select id="studyCourseType" name="course.studyCourseType" datatype="*" nullmsg="请选择课程类型！" errormsg="请选择课程类型！">
							<option value="">请选择</option>
							<option value="1" <c:if test="${course.studyCourseType eq 1}">selected</c:if>>单屏课程</option>
							<option value="2" <c:if test="${course.studyCourseType eq 2}">selected</c:if>>三屏课程</option>
							<option value="3" <c:if test="${course.studyCourseType eq 3}">selected</c:if>>flash课程</option>
							<option value="4" <c:if test="${course.studyCourseType eq 4}">selected</c:if>>静态页面</option>
							<option value="5" <c:if test="${course.studyCourseType eq 5}">selected</c:if>>混合</option>
						</select>
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				 -->
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>状态：
					</td>
					<td align="left">
						<select id="status" name="course.status" datatype="*" nullmsg="请选择状态！" errormsg="请选择课程类型！">
							<option value="0" <c:if test="${course.status eq 0}">selected</c:if>>未发布</option>
							<option value="1" <c:if test="${course.status eq 1}">selected</c:if>>已发布</option>
							<option value="2" <c:if test="${course.status eq 2}">selected</c:if>>已下线</option>
							<option value="3" <c:if test="${course.status eq 3}">selected</c:if>>已作废</option>
						</select>
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>难度：
					</td>
					<td align="left">
						<select id="difficulty" name="course.difficulty" datatype="*" nullmsg="请选择状态！" errormsg="请选择课程类型！">
							<option value="1" <c:if test="${course.difficulty eq 1}">selected</c:if>>初</option>
							<option value="2" <c:if test="${course.difficulty eq 2}">selected</c:if>>中</option>
							<option value="3" <c:if test="${course.difficulty eq 3}">selected</c:if>>高</option>
						</select>
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						课程内容（必填）：
					</td>
					<td>
						<textarea rows="5" cols="150" id="description" name="course.description" ignore="ignore" datatype="*1-250" errormsg="简述最多250个字符！">${course.description}</textarea>
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>

				<tr>
					<td class="row_tip" height="25">
						教学目标（必填）：
					</td>
					<td>
						<textarea rows="5" cols="150" id="review" name="course.review" ignore="ignore" datatype="*1-250" errormsg="导读最多250个字符！">${course.review}</textarea>
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				
				<tr>
					<td class="row_tip" height="25">
						图片上传：
					</td>
					<td>
						<input type="file" name="file" id="file"/><font color="red">*请用jpg格式的图片</font>
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				
				<tr>
					<td colspan="6" align="center" class="row_tip">
						<input type="button" onclick="validateFile();" class="btn_03s" value="保 存" />
						<input type="button" class="btn_03s" value="返 回" onclick="history.go(-1)" />
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">
			function validateFile(){
				/**
				var fvalue = document.getElementById("file").value;
				var regexp =/^jpg$/;
				if(!regexp.test(fvalue.substring(fvalue.lastIndexOf(".")+1))){
					alert('提示，请您上传后缀名为.jpg的文件.');
					return;
				}
				//校验文件的大小
				var fileSize = getFileSize(fvalue);
				if(fileSize == 0){
					alert('提示，所上传的文件不能为空。');
					return;
				}
				else if(fileSize>2048000){
					alert('提示，所上传的文件不能大于2M！');
					return;
				}else if(fileSize==-1){
					alert("跳出此消息框，是由于你的activex控件没有设置好,\n"+
					"设置步骤：\n"+
					"1、工具->internet选项->\"安全\"选项卡->可信站点->站点\n"+
					"->添加（注意：添加之前把\"对该区域中所有站点要求服务器验证\"前面的复选框去掉）->关闭\n"+
					"2、可信站点—>自定义级别,\n"+
					"打开\"安全设置\"对话框，把\"对未标记为安全的\n"+
					"ActiveX控件进行初始化和脚本运行\"，改为\"启动\",然后重启IE即可");
					return;
				}
				**/
				$("#fm1").submit();
			}
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
	        $(document).ready(function(){
		      parent.scrollToTop();
		    });
		    
		    function getFileSize (fileName) {
			if (document.layers) {
				try{
			  		if (navigator.javaEnabled()) {
			   			var file = new java.io.File(fileName);
			    		if (location.protocol.toLowerCase() != 'file:')
			     			netscape.security.PrivilegeManager.enablePrivilege('UniversalFileRead' );
			   				return file.length(); 
			  			}else 
			   				return -1; 
			   	}catch(e){
		  			return -1;
		  		}	
		 	}else if (document.all) {
		 			try{
			  			window.oldOnError = window.onerror;
			  			window.onerror = function (err) {
			   				if (err.indexOf('utomation') != -1) {
			   					document.getElementById("isOrNot").value='not';
			    				// alert('file access not possible');
			   				}else 
			    				return false; 
			  			};
			  			
			  		 var fso = new ActiveXObject('Scripting.FileSystemObject');
			  		 var file = fso.GetFile(fileName);
			 		 window.onerror = window.oldOnError;
			  		return file.Size; 
		  		}catch(e){
		  			return -1;
		  		}
		 	}
		}
		</script>
	</body>
</html>