<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理平台</title>
</head>

<%@include file="/new_page/top.jsp"%>
<body class="bjs">
	<!-- 题库内容 -->
	<form action="${ctx}/userManage/getUsers.do?method=search" method="post" name="searchFrm" id="searchFrm">
	<div class="center">
		<div class="tk_center01" style="min-height:120px;">
			<div class="tk_zuo">
				<div class="tik_xuanze">
					<div class="mt10 fl tk_tixing">
						<em class="fl">姓名：</em>
						<input type="text" name="realName" class="fl tik_select" value="${realName}"/>
					</div>
					<%-- <div class="mt10 ml5 fl tk_tixing">
						<span class="fl">用户类型：</span>
						<div class="fl tik_select" >
							<div class="tik_position_2">
								<b class="ml5" id="sel_userType">${userType}</b>
								<input type="hidden" name="userType" id="userType" value=""/>
								<p class="tik_position01"><i class="tk_bjt10"></i></p>
							</div>
							<ul class="fl tk_list_1 tk_list" style="display:none;">
								<select name="rolesId" id = "rolesId" style="display:none;">
									<option value="0">请选择</option>
									<c:forEach items="${roles}" var="role">
										<option value="${role.id}"<c:if test="${role.id==info.id}"> selected</c:if>>${role.nameDesc}</option>
									</c:forEach>
								</select>
								<li>请选择</li>
								<c:forEach items="${roles}" var="role">
									<li>${role.nameDesc}</li>
								</c:forEach>
							</ul>
						</div>
						
					</div> --%>
					<div class="mt10 ml5 fl tk_tixing">
								<p class="fl"><span class="fr">用户类型：</span></p>
								<div id="searchRoleNames" name="searchRoleNames" readonly class="fl tik_select01 searchRoles">${userType}</div>
								<input type = "hidden" id = "serchRoleIds" name = "serchRoleIds" >
								
								<input type="hidden" name="userType" id="userType" value=""/>
					</div>
					<div class="mt10 ml5 fl tk_tixing">
						<span class="fl">性别：</span>
						<div class="fl tik_select" >
							<div class="tik_position_2">
								<b class="ml5" id="sel_sex"><c:if test="${sex == 1}">男</c:if><c:if test="${sex == 2}">女</c:if></b>
								<input type="hidden" id="userSex" name="sex" value="${sex}"/>
								<p class="tik_position01"><i class="tk_bjt10"></i></p>
							</div>
							<ul class="fl tk_list_1 tk_list" style="display:none;">
								<li>请选择</li>
								<li>男</li>
								<li>女</li>
							</ul>
						</div>
					</div>
					<div class="mt10 ml10 fl tk_tixing">
						<span class="fl">单位：</span>
						<input type="text" name="workUnit" class="fl tik_select" value="${workUnit}"/>
					</div>
				</div>
				<div class="clear"></div>
				<div class="tik_xuanze">
				<div class="mt10 fl tk_tixing">
					<em class="fl">状态：</em>
					<div class="fl tik_select" >
						<div class="tik_position_2">
							<b class="ml5" id="sel_status"><c:if test="${status == 1}">启用</c:if><c:if test="${status == 2}">禁用</c:if></b>
							<input type="hidden" id="userStatus" name="status" value="${status}"/>
							<p class="tik_position01"><i class="tk_bjt10"></i></p>
						</div>
						<ul class="fl tk_list_1 tk_list" style="display:none;">
							<li>请选择</li>
							<li>启用</li>
							<li>禁用</li>
						</ul>
					</div>
				</div>
				<div class="mt10 ml5 fl tk_tixing">
					<span class="fl">账号：</span>
					<input type="text" name="loginName" class="fl tik_select" value="${loginName}"/>
				</div>
				<div class="mt10 fl tk_tixing">
					<span class="fl ml5">学科：</span>
						<div id="groupNames" class="fl tik_select01 searchProp" >${deptNames}</div>
						<input type="hidden" name="groupIds" id="groupIds" value=""/>
						<input type = "hidden" name = "deptNames" id = "deptNames" value = "">		
				</div>
	
				</div>
				<div class="clear"></div>
				
			</div>
			<div class="clear"></div>
			<div class="mt20 tk_you shiti" style="">
					<a href="javascript:searchSysUserInf();" class="fl tk_chaxun">查询</a>
					<a href="javascript:void(0);" class="fl ml30 tk_chaxun01">清空</a>
			</div>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
	</form>
	<div class="tkbjs"></div>
	<!--  -->
	<div class="center none" style="">
	<div class="tk_center01">
		<div class="mt10 kit1_tiaojia">
			<div class="fr cas_anniu">
				<a href="javascript:addSysUser();" class="fl exp1_tianjia01" style="width:95px;margin-right:15px;">添加用户</a><a href="${ctx}/new_page/systemUser/systemUserDownLoad.jsp" class="fl" style="width:95px;">批量导入</a>
			</div>
		</div>
		<div class="clear"></div>
		<form id="listfm" name="listfm" method="post" action="${ctx}/userManage/getUsers.do"  sort="list" defaultsort="1">
 		<display:table id="systemUser" cellpadding="0" cellspacing="0" partialList="true"  requestURI="${ctx}/userManage/getUsers.do"
 					size="${page.fullListSize}" pagesize="${page.objectsPerPage}" list="${page}"
					style="font-size:12px;width:100%;" class="mt10 cas_table" 
					  decorator="com.hys.exam.displaytag.TableOverOutWrapper" >
			<display:column
				title="<input type='checkbox' id='chkall' value='' class='chkall'>"
				style="width:3%;text-align:center">
				<input type="checkbox" class="chk" name="ids" value="${systemUser.id}" id="qcid" />
			</display:column>
			<display:column title="序号" class="td_mat1" style="width:3%">
				${systemUser_rowNum}
			</display:column>
			<display:column title="账号" sortable="true" class="td_mat1" style="width:16%" sortProperty="u.LOGIN_NAME">
				${systemUser.loginName}
				<em class="fr exp1_xiugai action" onclick = "javascript:editPass(${systemUser.id});" style="">修改密码</em>
			</display:column>
			<display:column title="用户姓名" sortable="true" property="realName" sortProperty="u.realName" style="width:7%;text-align:center" /> 
			<display:column title="性别" sortable="true" sortProperty="d.sex" style="width:7%;text-align:center">
				<c:if test="${systemUser.sex==1}">男</c:if>
				<c:if test="${systemUser.sex==2}">女</c:if>
			</display:column> 
			<display:column title="学科" property="deptName" style="width:10%;text-align:center" />
			<display:column title="用户类型" property="roleName" style="width:7%;text-align:center">
			</display:column>
			<display:column title="单位" sortable="true" property="workUnit" sortProperty="d.work_unit" style="width:10%;text-align:center">
			</display:column>
			<display:column title="手机号码" sortable="true" property="phoneNumber" sortProperty="d.phone_Number" style="width:12%;text-align:center">
			</display:column>
			<display:column title="状态" style="width:7%;text-align:center">
				<c:if test="${systemUser.enable == 1}">启用</c:if>
				<c:if test="${systemUser.enable == 2}">禁用</c:if>
			</display:column>
			<display:column title="操作" style="width:14%;text-align:center">
			 	<a href="javascript:sysUserModify('${systemUser.id}','${systemUser.loginName}','${systemUser.realName}','${systemUser.workUnit}','${systemUser.enable}',
			 			'${systemUser.roleName}','${systemUser.sex}','${systemUser.phoneNumber}','${systemUser.deptName}','${systemUser.roleIds}');" class="exp1_tianjia01">修改</a>
				<a href="javascript:offLine(${systemUser.id},${systemUser.enable});">
					<c:if test="${systemUser.enable == 2}">启用</c:if>
					<c:if test="${systemUser.enable == 1}">禁用</c:if>
				</a>
			</display:column>
		</display:table>
	    <input type="hidden" name="org.apache.struts.taglib.html.TOKEN"  value="${sessionScope['org.apache.struts.action.TOKEN']}" />
		</form>
		<div class="clear"></div> 
		<!-- 分页 -->
	</div>
	</div>
		<!-- 试题内容（结束） -->


<div class="clear"></div>

<!-- 添加用户 -->
<div class="toumingdu  exp_tang03" style="display:none;">
	<form action="${ctx}/userManage/updateUser.do" method="post" name="editFrm" id="editFrm">
	<div class="exper_tangkuang01" style="height:400px;margin:160px auto;border-radius:8px;-moz-border-radius:8px;-webkit-border-radius:8px;-khtml-border-radius:8px;background:#fff;width:700px;">
		<div class="exr_mingchengz">
			<div class="thi_shitineirong" style="width:700px;padding-top:50px;">
					<ul>
						<li class="mt15">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr">账号：</span><i style="color:red;"><em>*</em></i></p>
								<input type="hidden" id="sysId" name="id"/>
								<input type="hidden" id="roleIds" name="roleIds"/>
								<input class="fl tki_bianji011" id="userId" name="userId" maxlength = "20"/>
							</div>
							<div class="fl ml10 shitin_xinzeng4">
								<p class="fl"><span class="fr">用户类型：</span><i style="color:red;"><em>*</em></i></p>
								<textarea cols="" placeholder="" rows="" id="curRolesNames" name="curRolesNames" readonly class="fl tki_bianji011 takuang_xk editRole"></textarea>
								<input type = "hidden" id = "curRolesIds" name = "curRolesIds" >
							</div>
							<div class="clear"></div>
						</li>
						<li class="mt15">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr">姓名：</span><i style="color:red;"><em>*</em></i></p>
								<input class="fl tki_bianji011" id="realName" name="realName" maxlength = "50"/>
							</div>
							<div class="fl ml10 shitin_xinzeng4">
								<p class="fl"><span class="fr">性别：</span><i style="color:red;"><em>*</em></i></p>
								<select class="fl tki_list01" id="sex" name="sex">
									<option value="-1">---请选择---</option>
									<option value="1">男</option>
									<option value="2">女</option>
								</select>
							</div>
							<div class="clear"></div>
						</li>
						<li class="mt15">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr">单位：</span></p>
								<input class="fl tki_bianji011" id="workUnit" name="workUnit"/>
							</div>
							<div class="fl ml10 shitin_xinzeng4">
								<p class="fl"><span class="fr">手机号：</span></p>
								<input class="fl tki_bianji011" id="mobilPhone" name="mobilPhone"/>
							</div>
							<div class="clear"></div>
						</li>
						<li class="mt15">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr">状态：</span><i style="color:red;"><em>*</em></i></p>
								<select class="fl tki_list01" style="width:202px;" id="curStatus" name="curStatus">
									<option value="-1">---请选择---</option>
									<option value="1">启用</option>
									<option value="2">禁用</option>
								</select>
							</div>
							<div class="fl ml10 shitin_xinzeng4">
								<p class="fl"><span class="fr">学科：</span></p>
								<textarea cols="" placeholder="" rows="" id="curPropNames" name="curPropNames" readonly class="fl tki_bianji011 takuang_xk editProp"></textarea>
								<input type = "hidden" id = "curPropIds" name = "curPropIds" >
								
							</div>
							<div class="clear"></div>
						</li>

						<li>
							<div class="ca1_anniu" style="width:200px;">
								<a href="javascript:modifySysUser();" class="fl hide" style="margin-right:30px">保存</a>
								<a href="javascript:void(0);" class="fl hide01" >返回</a>
							</div>
							<div class="clear"></div>
						</li>
					</ul>
					<div class="clear"></div>
				</div>
		</div>

	</div>
	</form>
</div>
<!-- Add Window -->
<div class="toumingdu  exp_tang04" style="display:none;">
	<form action="${ctx}/userManage/addUsers.do" method="post" name="addFrm" id="addFrm">
	<div class="exper_tangkuang01" style="height:400px;margin:160px auto;border-radius:8px;-moz-border-radius:8px;-webkit-border-radius:8px;-khtml-border-radius:8px;background:#fff;width:700px;">
		<div class="exr_mingchengz">
			<div class="thi_shitineirong" style="width:700px;padding-top:50px;">
					<ul>
						<li class="mt15">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr"><i style="color:red;"><em>*</em></i>账号：</span></p>
								<input class="fl tki_bianji011" id="newuserId" name="loginName"/>
							</div>
							<div class="fl shitin_xinzeng04">
								<p class="fl ml10"><span class="fr">用户类型：</span><i style="color:red;"><em>*</em></i></p>
								<textarea cols="" placeholder="" rows="" id="newRoleNames" name="newRoleNames" readonly class="fl tki_bianji011  addRoles"></textarea>
								<input type = "hidden" id = "newRoleIds" name = "newRoleIds" >
							</div>
							<div class="clear"></div>
						</li>
						<li class="mt15">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr"><i style="color:red;"><em>*</em></i>姓名：</span></p>
								<input class="fl tki_bianji011" id="newrealName"  name="realName"/>
							</div>
							<div class="fl ml10 shitin_xinzeng4">
								<p class="fl"><span class="fr">性别：</span><i style="color:red;"><em>*</em></i></p>
								<select class="fl tki_list01" name="sex" id = "addUserSex">
									<option value="-1">---请选择---</option>
									<option value="1">男</option>
									<option value="2">女</option>
								</select>
							</div>
							<div class="clear"></div>
						</li>
						<li class="mt15">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr">单位：</span></p>
								<input class="fl tki_bianji011" name="addworkUnit" id = "addworkUnit"/>
							</div>
							<div class="fl ml10 shitin_xinzeng4">
								<p class="fl"><span class="fr">手机号：</span></p>
								<input class="fl tki_bianji011" name="addmobilPhone" id = "addmobilPhone"/>
							</div>
							<div class="clear"></div>
						</li>
						<li class="mt15">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr">状态：</span><i style="color:red;"><em>*</em></i></p>
								<select class="fl tki_list01" style="width:202px;" name="status" id = "addStatus">
									<option value="-1">---请选择---</option>
									<option value="1">启用</option>
									<option value="2">禁用</option>
								</select>
							</div>
							<div class="fl ml10 shitin_xinzeng4">
								<p class="fl"><span class="fr">学科：</span></p>
								<textarea cols="" placeholder="" rows="" id="newgroupNames" name="newPropName" readonly class="fl tki_bianji011 takuang_xk addProp"></textarea>
								<input type = "hidden" id = "newgroupIds" name = "newPropIds" >
							</div>
							<div class="clear"></div>
						</li>

						<li>
							<div class="ca1_anniu" style="width:200px;">
								<a href="javascript:addSysUserInf();" class="fl hide" style="margin-right:30px">保存</a>
								<a href="javascript:void(0);" class="fl hide01" >返回</a>
							</div>
							<div class="clear"></div>
						</li>
					</ul>
					<div class="clear"></div>
				</div>
		</div>

	</div>
	</form>
</div>
<!-- 新学科弹出框-一级 -->


<!-- 修改密码 -->
<form method = "post" name = "passFrm" id = "passFrm" action = "${ctx}/userManage/getUsers.do?method=setPass">
<div class="toumingdu  exp_tang02" style="display:none;">
	<div class="exper_tangkuang01" style="width:450px;height:300px;margin:200px auto;background:#fff;border:1px solid #dddddd;border-radius:5px;-moz-border-radius:5px;-webkit-border-radius:5px;-khtml-border-radius:5px;">
		<div class="exr_mingchengz">
			<div class="thi_shitineirong" style="padding-top:50px;padding-left:50px;">
					<ul>
						<li class="mt15">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr">新密码：</span></p>
								<input class="fl tki_bianji011 newPassPanel" id = "newPassPanel" style = "width:200px;"/>
							</div>
							<div class="clear"></div>
						</li>
						<li class="mt15">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr">确认密码：</span></p>
								<input class="fl tki_bianji011 conPassPanel" id = "conPassPanel" style = "width:200px;"/>
							</div>
							<div class="clear"></div>
						</li>
						<li class="mt30">
							<div class="cas_cha" style="margin-left:70px;">
								<p class="fl ml30" style="margin-left:10px;"><a href="javascript:setPass();" >保存</a></p>
								<p class="fl ml30"><a href="javascript:void(0);" class="hide01" >返回</a></p>
							</div>
							<div class="clear"></div>
						</li>
						
					</ul>	
					<div class="clear"></div>
				</div>
		</div>
	
	</div>
</div>
<input type = "hidden" id = "userId" name = "userId" value = "0">
</form>
<script type="text/javascript">

$(function(){
	//select menu
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "mar_left0" + menuindex;
		$('.tk_mainmenu>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
	
	//下拉框
		$('.tik_select').click(function(){
		    $(".tk_list").css("display","none");
			$(this).find('ul').show();
		});
		$('.tk_list li').click(function(){
			var str=$(this).text();
			$(this).parent().parent().find('div').find('b').text(str);
			$('.tk_list').slideUp(50);
		});
		$('.tik_select').click(function(e){
			return false
		});
		$(document).click(function(){
			$('.tk_list').hide('fast');
		});


	//清空
		$('.tk_chaxun01').click(function(){
			$('#searchFrm').find('input').val('');
			$('#searchFrm').find('select').val('');
			//$('.tik_select').find('b').text("请选择");
			$('#searchFrm').find('textarea').val('');
			//$('#searchRoleNames').val("");
			$('#groupNames').text("");
		});

		$('.cas_anniu_4,.bjt_kt,.make02').click(function(){
			$('.att_make02').hide();

		});
		$('.hide01').click(function(){
			$('.exp_tang02').hide();
			$('.exp_tang03').hide();
			$('.exp_tang04').hide();
		});
		$('.chkall').click(function(){

			if ($(this).attr('checked') == true)
			{
				$('.chk').attr('checked' , 'true');
			}
			else
				$('.chk').removeAttr('checked');
		});
		$('.exp1_xiugai').click(function(){
			$('.exp_tang02').show();
		});
});

	//if ($('#sel_userType').text() == '') $('#sel_userType').text("请选择");
	if ($('#sel_sex').text() == '') $('#sel_sex').text("请选择");
	if ($('#sel_status').text() == '') $('#sel_status').text("请选择");
	function searchSysUserInf(){
		var selUserType = $('#searchRoleNames').val();
		var selSex = $('#sel_sex').text();
		var selStatus = $('#sel_status').text();
		
		var selUserId;
		var selSexId;
		var selStatusId;
		
		
		if (selSex == "男") 		 selSexId = 1;
		else if (selSex == "女")  selSexId = 2;
		
		if (selStatus == "启用") 		  selStatusId = 1;
		else if (selStatus == "禁用")  selStatusId = 2;
		
		
		$('#serchRoleIds').val(selUserType);
		$('#userSex').val(selSexId);
		$('#userStatus').val(selStatusId);
		$('#deptNames').val($('#groupNames').text());
		$('#searchFrm').submit();
	}
	
	function setPass() {
		var newPass = $('#newPassPanel').val();
		var conPass = $('#conPassPanel').val();
		
		if (newPass == ""){
			alert("请输入新密码!");
			return;
		}
		if (conPass == ""){
			alert("请确认新密码!");
			return;
		}
		if (newPass != conPass){
			alert("输入密码错误!");
			return;
		}
		var url = "${ctx}/userManage/getUsers.do?method=setPass&newPass=" + newPass +"&userId="+ $('#userId').val();  
			$.ajax({
					type: 'POST',
					url: url,
					dataType: 'text',
					success : function (b){
						if(b == "success")
						{
							$('.exp_tang02').hide();
							alert('添加成功！');
							
						}
						else{
							alert('添加失败!');
						}
					},
		});
	}
	
	function sysUserModify(userId,loginName,realName,workUnit,status,roleName,sex,mobilPhone,deptName,roleIds){
		if(status == 2) {
			alert("禁用的用户不能修改！");
			return false;
		}
			$('#sysId').val(userId);
			$('#userId').val(loginName);
			$('#realName').val(realName);
			$('#workUnit').val(workUnit);
			$('#curStatus').val(status);
			$('#curRolesNames').text(roleName);
			$('#sex').val(sex);
			$('#mobilPhone').val(mobilPhone);
			$('#curRolesIds').val(roleIds);
			$('#curPropNames').text(deptName);
			$('.exp_tang03').show();
	}
	function modifySysUser(){
		var userid = $('#userId').val();
		var realName = $('#realName').val();
		var newRoleNames = $('#curRolesNames').val();
		var sex = $('#sex').val();
		var curStatus = $('#curStatus').val();
		
 		if(userid == ""){
 			alert ("帐号不能为空!");
 			return false;
 		}else if(realName == ""){
 			alert ("姓名不能为空!");
 			return false;
 		}else if(curRolesNames == ""){
 			alert ("用户类型不能为空!");
 			return false;
 		}else if(sex == ""){
 			alert ("性别不能为空!");
 			return false;
 		}else if(curStatus == ""){
 			alert ("状态不能为空!");
 			return false;
 		}
 		
 		/* else if($('#workUnit').val() == ""){
 			alert ("单位不能为空！");
 			return false;
 		} */
 		
			$('#editFrm').submit();
			$('.exp_tang03').hide();
	}
	function addSysUser(){
			$('.exp_tang04').show();
	}
	function addSysUserInf(){
 		var userid = $('#newuserId').val();
		var realName = $('#newrealName').val();
		var addworkUnit = $('#addworkUnit').val();
		var newRoleNames=$('#newRoleNames').val();
		var addUserSex=$('#addUserSex').val();
		var addStatus=$('#addStatus').val();
 		if(userid == ""){
 			alert ("帐号不能为空!");
 			return false;
 		}else if(realName == ""){
 			alert ("姓名不能为空!");
 			return false;
 		}else if(newRoleNames == ""){
 			alert("用户类型不能为空!");
 			return false;
 		}else if(addUserSex == "-1"){ 			
 			alert("请选择性别!");
 			return false;
 		}else if(addStatus == "-1"){
 			alert("请选择状态!");
 			return false;
 		}
 		
		var url ="${ctx}/userManage/addUsers.do";
		var params = "loginName=" + userid;
		params += "&newRolesIds="+ $('#newRoleIds').val();
		params += "&realName="+ $('#newrealName').val();
		params += "&deptName="+ $('#newgroupNames').val();
		params += "&sex=" + $('#addUserSex').val();
		params += "&workUnit="+ $('#addworkUnit').val();
		params += "&mobilPhone="+ $('#addmobilPhone').val();
		params += "&status="+ $('#addStatus').val();
		$.ajax({
					type: 'POST',
					url: url,
					data : params,
					dataType: 'text',
					success : function (b){
						if(b == "success")
						{
							alert('添加成功！');
							document.location.href = document.location.href.split("#")[0];
						}
						else{
							alert('账号不能重复!');
						}

					},
					error: function(){
					   	alert('添加失败');
					}
		});
		$('.exp_tang04').hide();
	}
	function goBack() {
		window.history.back();
	}
	//下拉框
	function selectInit(){
		$('.tik1_select').click(function(){
			$(".tk1_list").css("display","none");
			$(this).find('ul').show();
			return false;
		});
		$('.tk1_list li').click(function(){
			var str=$(this).text();
			$(this).parent().parent().find('div').find('b').text(str);
			$(this).parent().find('option').prop('selected', '');
			$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
			$('.tk1_list').slideUp(50);
		});
		
		$('.tk1_list option:selected').each(function(){
			var str=$(this).text();
			$(this).parent().parent().parent().find('b').text(str);
		});
	}

	function selSubRoles(id){
		var rolesName = '';
		var rolesIds = '';
		$("input[name='rolesunit']").each(function () {
		    if ($(this).is(':checked')) {
				if (rolesIds == '') 		rolesIds += this.value;
				else					rolesIds += ',' + this.value;
				
				if (rolesName == '')		rolesName += this.title;
				else					rolesName += ', ' + this.title;
		    }
	 	});
	 	$('#rolesNamePanel').text(rolesName);
	}
	function offLine(selId,state) {
		if(state == 1)
		{
			if(!confirm("您确定要禁用此用户吗?"))
			{
				return false;
			}
		}
		else
		{
			if(!confirm("您确定要启用此用户吗?"))
			{
				return false;
			}
		}
			var url = "${ctx}/userManage/deleteUser.do";
			var params = "id=" + selId;
			$.ajax({
						type: 'POST',
						url: url,
						data : params,
						dataType: 'text',
						success : function (b){
							alert('添加成功！');
							searchSysUserInf();
						},
						error: function(){
						   	alert('添加失败');
						}
			});
	}
	function editPass(selId){
		$('#userId').val(selId);
	}
	
	$('.searchProp').click(function(){
		initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#groupIds'), $('#groupNames'));
		$('.att_make01').show();
	});
	$('.editProp').click(function(){
		initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#curPropIds'), $('#curPropNames'));
		$('.att_make01').show();
	});
	$('.addProp').click(function(){
		initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#newgroupIds'), $('#newgroupNames'));
		$('.att_make01').show();
	});
	$('.editRole').click(function(){
		initPropList("用户类型", "${ctx}/userManage/getUserRoles.do", 1, 0, 2, 1, $('#curRolesIds'), $('#curRolesNames'));
		$('.att_make01').show();
	});
	$('.addRoles').click(function(){
		initPropList("用户类型", "${ctx}/userManage/getUserRoles.do", 1, 0, 2, 1, $('#newRoleIds'), $('#newRoleNames'));
		$('.att_make01').show();
	});
	$('.searchRoles').click(function(){
		initPropList("用户类型", "${ctx}/userManage/getUserRoles.do", 1, 0, 2, 1, $('#serchRoleIds'), $('#searchRoleNames'));
		$('.att_make01').show();
	});
	
</script>
</body>
</html>
