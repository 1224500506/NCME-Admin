<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<%@include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
</head>

<%@include file="/peixun_page/top.jsp"%>

<body>
<div class="clear"></div>
<!-- 查询条件 -->
<div class="center">
<form id ="fm1" name = "fm1" action = "${ctx}/systemManage/getAccounts.do?method=search">
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl" style="margin:0 20px 10px 0">
			<span style="width:5em;text-align:right;">姓名：</span>
			<input type="text" id = "userName" name = "userName" value = "${item.realName}"/>
		</p>
		<p class="fl" style="margin:0 20px 10px 0">
			<span style="width:5em;text-align:right;">账号：</span>
			<input type="text" id = "accountName" name = "accountName" value ="${item.accountName}"/>
		</p>
		<p class="fl" style="margin:0 20px 10px 0">
			<span style="width:5em;text-align:right;">科室：</span>
			<input type="hidden" id="propIds" name="propIds" value="${propIds}"/>
			<input type="hidden" id="propNames" name="propNames" value="${propNames}"/>
			<div class="duouan" id="propNames01">${propNames}</div>
		</p>
		<p class="fl" style="margin:0 20px 10px 0">
			<span style="width:5em;text-align:right;">手机号：</span>
			<input type="text" name="mobilPhone" value="${item.mobilPhone}" />
		</p>
		<div class="clear"></div>
		
		<p class="fl" style="margin:0 0 10px 0;">
			<span style="width:5em;text-align:right;">账号类型：</span>
		</p>
		<div class="fl select"  style="margin:0 20px 10px 0">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
				<ul class="fl list" style="display:none;">
					<select name="typeId" id = "typeId" style="display:none;">
						<option value = "-1">全部</option>
						<c:forEach items="${typeList}" var="type">
							<option value="${type.id}"<c:if test="${type.id==typeId}"> selected</c:if>>${type.user_type_name}</option>
						</c:forEach>
					</select>
					<li>全部</li>
					<c:forEach items="${typeList}" var="type">
						<li>${type.user_type_name}</li>
					</c:forEach>
				</ul>	
		</div>
		<p class="fl" style="margin:0 0 20px 0">
			<span style="width:5em;text-align:right;">角色：</span>
		</p>
		<div class="fl select" style="margin:0 10px 0 0">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display:none;">
					<select name="roleId" id = "roleId" style="display:none;">
						<option value = "-1">全部</option>
						<c:forEach items="${roleList}" var="role">
							<option value="${role.roleId}"<c:if test="${role.roleId==roleId}"> selected</c:if>>${role.roleNameDesc}</option>
						</c:forEach>
					</select>
					<li>全部</li>
					<c:forEach items="${roleList}" var="role">
						<li>${role.roleNameDesc}</li>
					</c:forEach>
			</ul>	
		</div>
		<p class="fl" style="margin:0 0 20px 0">
			<span style="width:5em;text-align:right;">归属机构：</span>
			<input type="text" name="mobilPhone" value="" />
		</p>
		<!-- 模型中不显示此字段,先注掉
		<p class="fl" style="margin:0 20px 10px 0">
			<span style="width:5em;text-align:right;">单位名称：</span>
			<input type="text" id ="workUnit" name = "workUnit" value = "${workUnit}"/>
		</p>
		<div class="clear"></div>
		 -->
		<p class="fl" style="margin:0 0 20px 0">
			<span style="width:5em;text-align:right;">状态：</span>
		</p>
		<div class="fl select"  style="margin:0 20px 0 0">
			<div class="tik_position">
				<b class="ml5">
					<c:if test = "${state == null}">全部</c:if>
					<c:if test = "${state == 1}">正常</c:if>
					<c:if test = "${state == 2}">停用</c:if>
				</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display: none;">
				<select id="state" name="state" style="display:none;">
					<option value ="">全部</option>
					<option value="1"<c:if test="${state==1}"> selected</c:if>>正常</option>
					<option value="2"<c:if test="${state==2}"> selected</c:if>>停用</option>
				</select>
				<li>全部</li>
				<li>正常</li>
				<li>停用</li>
			</ul>
		</div>
		

		<p class="fl cas_anniu">
			<a href="javascript:search();" class="fl chaxun" style="width:70px;margin:0 0 20px 20px;">查询</a>
		</p>
	</div>
	</form>
</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
		<form id="listfm" name="listfm" method="post" action="${ctx}/systemManage/getAccounts.do">
 		<display:table id="systemUser" cellpadding="0" cellspacing="0" partialList="true"  requestURI="${ctx}/systemManage/getAccounts.do"
 					size="${page.fullListSize}" pagesize="${page.objectsPerPage}" list="${page}"
					style="font-size:12px;width:100%;" class="mt10 table" 
					  decorator="com.hys.exam.displaytag.TableOverOutWrapper" >
			<display:column title="序号" style="width:3%">
				${systemUser_rowNum}
			</display:column>
			<display:column title="账号" style="width:8%">
				${systemUser.accountName}
			</display:column>
			<display:column title="姓名" property="realName" style="width:7%;text-align:center" />
			<display:column  style="width:10%;" title="科室" property="deptName" />  
			<display:column  style="width:7%;" title="手机号" property="mobilPhone" /> 	
			<display:column  style="width:10%;" title="证件号" property="certificateNo" /> 
			<display:column  style="width:10%;;text-align:center;" title="归属机构">
				<c:if test="${systemUser.userType == 3}">中国继续医学教育网</c:if>
				<c:if test="${systemUser.userType != 3}">${systemUser.workUnit}</c:if>
			</display:column>
			<display:column title="账号类型" style="width:7%;text-align:center">
				<c:if test="${systemUser.userType == 1}">学员</c:if>
				<c:if test="${systemUser.userType == 2}">编辑</c:if>
				<c:if test="${systemUser.userType == 3}">专家</c:if>
				<c:if test="${systemUser.userType == 4}">医疗机构</c:if>
				<c:if test="${systemUser.userType == 5}">高等院校</c:if>
				<c:if test="${systemUser.userType == 6}">学/协会</c:if>
				<c:if test="${systemUser.userType == 7}">出版社</c:if>
				<c:if test="${systemUser.userType == 8}">企业</c:if>
			</display:column>
			<display:column title="角色" style="width:7%;text-align:center" >
				<c:forEach items="${systemUser.roleList}" var="role">
					${role.roleNameDesc};
				</c:forEach>
				<input type = "hidden" id = "account_role_${systemUser.accountId}" value='<c:forEach items="${systemUser.roleList}" var="role">${role.roleId},</c:forEach>' />
			</display:column>
			<display:column title="状态" style="width:7%;text-align:center">
				<c:if test="${systemUser.account_status == 1}">正常</c:if>
				<c:if test="${systemUser.account_status == 2}">停用</c:if>
			</display:column>
			<display:column title="操作" style="width:14%;text-align:center">
				<a href="${ctx}/system/systemUserStudent.do?method=view&rtype=1&id=${systemUser.userId}&type=3">查看</a>
				<a href="${ctx}/system/systemUserStudent.do?method=view&rtype=1&id=${systemUser.userId}&type=4">编辑</a> 
			 		<!--<a href="javascript:updateState(${systemUser.accountId},${systemUser.account_status});" class="">
						<c:if test="${systemUser.account_status == 1}">停用</c:if>
						<c:if test="${systemUser.account_status == 2}">正常</c:if>
					</a> -->
		 			<c:if test="${systemUser.account_status==1}">
						<a href="javascript:updateState(${systemUser.accountId},2);" >停用</a> 
					</c:if>
					<c:if test="${systemUser.account_status==2}">
						<a href="javascript:updateState(${systemUser.accountId},1);" >启用</a> 
					</c:if>
			 		<a href="javascript:resetPass('${systemUser.accountName}');" class="pwd_btn">重置密码</a>
					<a href="javascript:addRole(${systemUser.accountId},'${systemUser.accountName}');" class="role_btn">授予角色</a>
			</display:column>
		</display:table>
	    <input type="hidden" name="org.apache.struts.taglib.html.TOKEN"  value="${sessionScope['org.apache.struts.action.TOKEN']}" />
		</form>
		<div class="clear"></div> 
		<!-- 分页 -->
	</div>
</div>

<script type="text/javascript">
$(function(){
	
	$('#propNames01').click(function(){
		initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
		$('.att_make01').show();
	});
	
	$('.chaxun').click(function(){
		$('#propNames').val($('#propNames01').text());
	});
	
	//select menu
	var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
	var submenu = "lc_left0" + menuindex;
	$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
	$('.'+submenu+'').show();
	$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
	
				$('.select').click(function(){
					 $('.list').css('display","none');
					$(this).find('ul').show();
				});
				$('.list li').click(function(){
						var str=$(this).text();
						$(this).parent().parent().find('div').find('b').text(str);
						$(this).parent().find('option').prop('selected', '');
						var op = $(this).parent().find('option').eq($(this).index()-1);
						$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
						$('.list').slideUp(50);
				});
				$('.list option:selected').each(function(){
					var str=$(this).text();
					$(this).parent().parent().parent().find('b').text(str);
				});
		
				$('.select').click(function(e){
					return false;
				});
				$(document).click(function(){
					$('.list').hide('fast');
				});
//选择目录弹出框
		$('.duouan').click(function(){
			$('.att_make01').show();
		});
		$('.bjt_kt,.cas_anniu_4').click(function(){
			$('.att_make01').hide();
			$('.att_make02').hide();
			$('.att_make03').hide();
			$('.att_make04').hide();
		});
		//二级
		$('.attr_xueke').click(function(){
			$('.att_make01').hide();
			$('.att_make02').show();
		});
		$('.attri01').click(function(){
			$('.att_make02').hide();
			$('.att_make01').show();
			$('.att_make03').hide();
			$('.att_make04').hide();
			$('.att_make05').hide();
		});
		//三级
		$('.attr_xueke02').click(function(){
			$('.att_make02').hide();
			$('.att_make03').show();
		});
		$('.attri02').click(function(){
			$('.att_make02').show();
			$('.att_make03').hide();
			$('.att_make04').hide();
			$('.att_make05').hide();
		});
		//调整
		$('.tiaozheng').click(function(){
			$('.make02').show();
		});
		$('.queren').click(function(){
			$('.make02').hide();
		});
});
$(".pwd_btn").click(function(){
		layer.alert(
				"确认将账号 <span id = 'aName'></span>重置密码吗？<br />重置密码后，账号密码为：123456",{
					title: "重置密码",
					closeBtn:false,
					btn:['确定','取消'],
					yes: function (index, layero) {
						var url = "${ctx}/systemManage/getAccounts.do?method=resetPass";
						if($("#aName").text() == "")
						{
							alert("请选择账号!");
							layer.close(index);
							return;
						}
						var param = "";
						param += "&accountName=" + $("#aName").text();
						$.ajax({
					    url:url,
					    type: 'POST',
					    dataType: 'text',
					    data : param,
					    success: function(result){
						    if(result == 'success'){
						   		alert("重置密码成功！");
						   }
						   else
						   {
						   		alert("重置密码失败!");
						   }
						   layer.close(index);
				    	}
					});
						//缩写保存功能
						layer.close(index);
					},
					btn2: function (index, layero) {
						layer.close(index);
					}
				});
	});
	var win_w = "600px";
	var win_h = "400px";
	var role_cont = '' +
			'<div class="center">' +
			'<div class="tiaojian" style="min-height:40px;">' +
			'<p class="fl" style="margin:0 0 20px 0;">' +
			'<span style="width:5em;text-align:right;">账号：</span> ' +
			'<input type="text" id = "roleAccountName" value="" readonly />' +
			'</p>' +
			'<div class="clear"></div> ' +
			'<p class="fl" style="margin:0 0 10px 0;line-height:25px;">' +
			'<span style="width:5em;text-align:right;">角色名称：</span>' +
			'</p>' +
			'<div class="fl" style="margin:0;width:400px;font-size:12px;">' +
			'<c:forEach items="${roleList}" var="role">' +
			'<p class="fl" style="margin:0 20px 10px 0;line-height:25px;width:40%;">' +
			'<input type="checkbox" class="chk" id = "chk_${role.roleId}" style = "width:25px;height:25px;" value="${role.roleId}" /> ${role.roleNameDesc}' +
			'</p>' +
			'</c:forEach></div>' +
			'<input type = "hidden" id = "roleAccountId" name = "roleAccountId" value=""></div>' +
			'</div>';
	$(".role_btn").click(function() {
		layer.open({
			type: 1,
			title: "授予角色",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, win_h], //宽高
			content: role_cont,
			closeBtn: 0,
			btn: ['保存', '取消'],
			yes: function (index, layero) {
						var url = "${ctx}/systemManage/getAccounts.do?method=editRole";
						var newRole = "";
						$(".chk").each(function(key,val){
							if($(this).prop("checked"))
							{
								newRole += $(this).val() + ",";
							}
						});
						if(newRole == "")
						{
							alert("请选择角色!");
							return false;
						}
						var param = "";
						param += "&accountId=" + $("#roleAccountId").val();
						param += "&newRole=" + newRole;
						$.ajax({
						    url:url,
						    type: 'POST',
						    dataType: 'text',
						    data : param,
						    success: function(result){
							    if(result == 'success'){
							   		alert("授予角色成功！");
							   }
							   else
							   {
							   		alert("授予角色失败!");
							   }
								layer.close(index);
								document.location.href = document.location.href.split("#")[0];	   
					    	}
					    });
					},
			btn2: function (index, layero) {
				layer.close(index);
			},
			success: function(layerb, index){
				$('.select').click(function(){
					$('.list').css('display","none');
					$(this).find('ul').show();
				});
				$('.list li').click(function(){
					var str=$(this).text();
					$(this).parent().parent().find('div').find('b').text(str);
					$('.list').slideUp(50);
				});
				$('.select').click(function(e){
					return false;
				});
				$(document).click(function(){
					$('.list').hide('fast');
				});
				$(".btn1").click(function(){
					layer.close(index);
				});
			}
		});
	});

function search()
{
	$("#fm1").submit();
}

//此处比照学员管理模块中的停用/启用处理
function updateState(id, status){
	var opinion = "";
	if(status==2){
		if (!confirm("确认停用学员账号吗？ \n停用后，学员将无法使用该账号登录中国继续医学教育网？")) {
			return;
		}
	   	opinion = prompt("请输入账号停用原因!","");
	   	if (opinion == null || opinion == "") {
	   		return;
	   	} else {
			if(opinion != '' && !/^[^\<>~!`@#$%&*(),.?{};'"+/|]*$/.test(opinion)){  
				alert("停用原因只支持汉字、英文字母和数字，请输入正确的内容！");
				return;
			} 
	   	}
	}else{
		if(!confirm("确认启用学员帐号吗?")){
			return;
		}
	}
	var url = "${ctx}/system/systemUserStudent.do?method=setState";
	
	var params = "&id="+id;
	params+= "&state="+status;
	params += '&opinion=' + opinion;
	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'text',
	    success: function(result){
			if (result =="success"){
				alert("操作成功！");
				window.location.reload(true);
			}
			else{
				alert("操作失败！");
			}
	    }
	});
}
function updateState11(id,oldState){
	if(oldState==1){
		if (!confirm("您确定要停用吗?")) {
			return false;
		} 
	}else{
		if(!confirm("您确定要启用吗?")){
			return false;
		}
	}
	var url = "${ctx}/systemManage/getAccounts.do?method=updateState";
	var param = "";
	param += "&id=" + id;
	param += "&oldState=" + oldState;
	$.ajax({
	    url:url,
	    type: 'POST',
	    dataType: 'text',
	    data : param,
	    success: function(result){
		   if(result == 'success'){
		   		alert("操作成功！");			   		   		
		   }
		   else
		   {
		   		alert("操作失败!");
		   }
		   
		   document.location.href = document.location.href.split("#")[0];
    	}
	});		
}

function resetPass(accountName)
{
	$("#aName").text(accountName);
}
function addRole(id,accountName)
{
	$(".chk").each(function(key,val){
		$(this).prop("checked",false);
	});
	$("#roleAccountName").val(accountName);
	$("#roleAccountId").val(id);
	
	var account = "#account_role_" + id;
	var account_role = $(account).val();
	var roleArray = account_role.split(",");
	$(roleArray).each(function(key, val){
		$("#chk_" + val).prop("checked",true);
	});
}
</script>
</body>

