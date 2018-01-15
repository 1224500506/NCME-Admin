<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>编辑学员</title>
		<%@include file="/commons/taglibs.jsp"%>
    	<script src="${ctx}/peixun_page/js/validate.js"></script>   
	</head>
<%@include file="/peixun_page/top.jsp"%>
<style>
	.sel_btn{
		height: 25px;
		line-height: 25px;
		padding: 0 15px;
		background: #02bafa;
		border: 1px #26bbdb solid;
		border-radius: 3px;
		/*color: #fff;*/
		display: inline-block;
		text-decoration: none;
		font-size: 14px;
		outline: none;
	}
	.ch_cls{
	    background: #e4e4e4;
	}
</style>
<form method="post" action="${ctx}/system/systemUserStudent.do?method=updateAccount" id="modForm" name="modForm" enctype="multipart/form-data">
<div>
	<div class="center">
		<div class="tk_tk_xuanxiag" style="">
			<div class="thi_shitineirong" style="">
				<div class="tiaojian" style="min-height:40px;">
					<p class="fl" style="margin-bottom:20px;"></p>
					<p>个人信息</p>
					<div class="clear"></div>
				    <!-- 第一行 帐号类型  -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>帐号类型：</span>
					</p>
                    <select class="fl select" id="userType" name="model.userType" >
                        <c:forEach items="${typeList}" var="typeList">   
                        	<c:if test="${typeList.id != 1}">
	                       		<option value="${typeList.id}" <c:if test="${typeList.id == item.userType}">selected</c:if>>${typeList.user_type_name}</option>
                        	</c:if>
                        </c:forEach>
                    </select>
					<div class="clear"></div>
					
					<!-- 第二行 学员账号 学员姓名 -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>账号：</span>
						<input type="text" id="accountName" name="model.accountName" value="${item.accountName}" maxlength="18" onblur="checkthi();"/>
					</p>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>姓名：</span>
						<input type="text" id="realName" name="model.realName" value="${item.realName}" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')"/>
					</p>
					<div class="clear"></div>
					
				    <!-- 第三行 证件类型  证件号码 -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em></em>证件类型：</span>
					</p>
					<select class="fl select" id="certificateType" name="model.certificateType" >
						<option value="" <c:if test="${item.certificateType == ''}"> selected="selected"</c:if>>请选择</option>
						<option value="1" <c:if test="${item.certificateType == 1}"> selected="selected"</c:if>>身份证</option>
						<option value="2" <c:if test="${item.certificateType == 2}"> selected="selected"</c:if>>军官证</option>
						<option value="3" <c:if test="${item.certificateType == 3}"> selected="selected"</c:if>>港澳台通行证</option>
						<option value="4" <c:if test="${item.certificateType == 4}"> selected="selected"</c:if>>护照</option>
					</select>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em></em>证件号码：</span>
						<input type="text" id="certificateNo" name="model.certificateNo" value="${item.certificateNo}" onblur="checkIdCard();"/>
					</p>
					<div class="clear"></div>
					
					<!-- 第四行 学科  性别 -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>学科：</span>
						<input type="hidden" id="propIds" name="model.propIds" value="${item.propIds}"/>
						<input type="hidden" id="propNames" name="propNames" value="${item.deptName}"/>
						<a class="duouan" id="propNames01">${item.deptName}</a>
					</p>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">性别：</span>
					</p>
					<select class="fl select" id="sex" name="model.sex" >
						<option value="1" <c:if test="${item.sex == 1}"> selected="selected"</c:if>>男</option>
						<option value="2" <c:if test="${item.sex == 2}"> selected="selected"</c:if>>女</option>
					</select>
					<div class="clear"></div>
					
					<!-- 第五行 手机号 所属机构   -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>手机号码：</span>
						<input type="text" id="mobilPhone" name="model.mobilPhone" value="${item.mobilPhone}" maxlength="11" onblur="checkPhone();"/>
					</p>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>所属机构：</span>
						<c:if test="${item.userType == 3}">
							<input type="text" value="中国继续医学教育网" readonly />
						</c:if>
						<c:if test="${item.userType != 3}">
							<input type="text" id="workUnit" name="model.workUnit" value="${item.workUnit}" readonly />
							<input type = "hidden" id = "workUnitId" name = "model.work_unit_id" value = "${item.work_unit_id}" /> 
						</c:if>
					</p>
					<div class="clear"></div>
				</div>
				<div class="tiaojian" style="min-height:40px;">
					<p class="fl" style="margin-bottom:20px;"></p>
					<p style="width:5em;text-align:right;">角色信息</p>	
					<div class="clear"></div>	
					<!-- 第六行  角色信息 -->
					<p class="fl" style="margin:0 0 10px 0;line-height:25px;">
						<span style="width:5em;text-align:right;"></span>
					</p>
					<div class="fl" style="margin:0;width:600px;font-size:12px;">
						<c:forEach items="${roleList}" var="role">
							<p class="fl" style="margin:0 20px 10px 0;line-height:25px;width:30%;">
								<input type="checkbox" class="chk" id = "chk_${role.roleId}" style = "width:20px;height:20px;" value="${role.roleId}" /> ${role.roleNameDesc}
							</p>  
						</c:forEach>
						<input type = "hidden" id = "account_role_${item.accountId}" value='<c:forEach items="${item.roleList}" var="role">${role.roleId},</c:forEach>' />
						<input type = "hidden" id = "userId" name="model.userId" value="${item.accountId}"/>
						<input type = "hidden" id = "roleIds" name="model.roleIds" />
					</div>
					<div class="clear"></div>
				</div>
				
				<div class="tiaojian" style="min-height:40px;">
					<p class="fl" style="margin-bottom:20px;"></p>
					<p>创建时间信息</p>			
					<div class="clear"></div>	
					<!-- 第七行  创建时间  更新时间   -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">创建时间：</span>
						<input type="text" id="regDate" value="${item.regDatee}" readonly />
					</p>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">更新时间：</span>
						<input type="text" id="lastUpdateDate" value="${item.lastUpdateDatee}" readonly />
					</p>
					<div class="clear"></div>
				</div>
				
				<div class="tiaojian" style="min-height:40px;">	
					<p class="fl" style="margin-bottom:20px;"></p>
					<p>其它信息</p>		
					<div class="clear"></div>	
					<!-- 第八行  备注说明  备注时间   -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">备注说明：</span>
						<input type="text" id="reason" value="${item.reason}" readonly />
					</p>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">备注时间：</span>
						<input type="text" id="reasondate" value="${item.reasondate}" readonly />
					</p>
					<div class="clear"></div>
					
					<!-- 第九行  保存 返回   -->
					<p class="fl" style="margin-bottom:25px; width:75%">
						<span style="width:100%;text-align:center;display:block;">
							<a href="javascript:Change();" class="sel_btn ch_cls">保存</a>
							<a href="javascript:history.back(-1)" class="sel_btn ch_cls">取消</a>
						</span>
					</p>
				</div>
			</div>
		</div>	
	</div>			
</div>
</form>

<script type="text/javascript">
	//验证用户帐号是否可用
	function checkthi(){
		var accountName = $("#accountName").val();
		var filter = /^[a-zA-Z0-9]{4,18}$/;
		if(accountName=='' || accountName == null){
			alert("*用户帐号不能为空");
		}else {
			if(!filter.test(accountName)){
				alert("*用户帐号格式不正确");
			}else{
				// 检测用户是否可用
				$.ajax({
					type:'POST',
					url:'${ctx}/system/systemUserStudent.do?method=checkUserName&accountName='+accountName+"&userId=" + $("#userId").val(),
					dataType:'json',
					async: false,
					success:function(data){
						var result = eval(data);
						if(result.message!='success'){
							alert("*用户帐号已经存在");
							$("#accountName").val("");
							return;
						}else{
							return;
						}
					}
				});
			}
		}
	}
</script>
<script type="text/javascript">
	// 说     明：检查手机号码是否重复
	function checkPhone(){
		var phone = $("#mobilPhone").val();
		//通过ajax检测手机号码是否为空
		if(phone!=null && phone!=''){
			var regTel1 = /^1(3|4|5|7|8)\d{9}$/.test(phone);
			if (!regTel1) { 
				alert("手机号输入有误！");
				$('#mobilPhone').val("");
     			$('#mobilPhone').focus();
				return false;
		  	}
			
			$.ajax({
				type:'POST',
				url:'${ctx}/system/systemUserStudent.do?method=checkMobile&mobilPhone='+phone+"&userId=" + $("#userId").val(),
				dataType:'json',
				async: false,
				success:function(data){
					var result = eval(data);
					if(result.message!='success'){
						alert("*该手机号已经注册不可用");
						$('#mobilPhone').val("");
		     			$('#mobilPhone').focus();
					  	return false;
					}else{
						return true;
					}
				}
			});
		}
	}
</script>
<script type="text/javascript">
	/**
	 * 证件类型改变时,证件码号清空
	 */
	$("#certificateType").change(function(){
		$("#certificateNo").val("");
	});
	
	/**
	 * 检查证件号码是否重复以及是否合法
	 */
	function checkIdCard(){
		var idCard = $("#certificateNo").val();
		var cardType = $("#certificateType").val();
		var filter_jg = /^[a-zA-Z0-9]{7,21}$/;
		var filter_ga = /^[a-zA-Z0-9]{5,21}$/;
		var filter_hz =  /^[a-zA-Z0-9]{3,21}$/;
		var res;
		//通过ajax检测证件号码是否为空
		if(idCard!=null && idCard!=''){
			$.ajax({
				type:'POST',
				url:'${ctx}/system/systemUserStudent.do?method=checkIdCard&idCard='+idCard+"&userId=" + $("#userId").val(),
				dataType:'json',
				async: false,
				success:function(data){
					var result = eval(data);
					if(result.message!='success'){
						alert("*该证件号码已被注册");
						$("#certificateNo").val("");
						return false;
						//$("#certificateNo").focus();
					}
					if($("#certificateType").val()==1 && idCard.length !=18){
						alert("*身份证号必须为18位的");
						$("#certificateNo").val("");
						return false;
					}
					if($("#certificateType").val() ==1  && IdentityCodeValidRegister($("#certificateNo").val())==false){
						//校验身份证号码合法性
						alert("*身份证号不正确");
						$("#certificateNo").val("");
						return false;
					}
					if(cardType == 2 && !filter_jg.test(idCard)){//校验军官证号码合法性
						alert("*军官证号码不正确");
						$("#certificateNo").val("");
						return false;
					}
					if(cardType == 3 && !filter_ga.test(idCard)){//校验港澳/台通行证号码合法性
						alert("*港澳/台通行证号码不正确");
						$("#certificateNo").val("");
						return false;
					}
					if(cardType == 4 && !filter_hz.test(idCard)){//校验护照号码合法性
						alert("*护照号码不正确");
						$("#certificateNo").val("");
						return false;
					}
				}
			});
		}else{
			$("#certificateNo").val("");
			return false;
		}
	}
</script>

<script type="text/javascript">
	function isDuplicateAccount(){
		var url = "${ctx}/system/systemUserStudent.do?method=isDuplicate&userId=" + $("#userId").val() + "&accountName="+$("#accountName").val();
		$.ajax({
			url:url,
			type:'POST',
			dataType:'text',
			success:function(result){
				if (result != null && result == "exist") {
					alert("用户帐号重复，请重新输入！");
					$("#accountName").focus();
					return true;
				} else {
					var newRole = "";
					$(".chk").each(function(key,val) {
						if ($(this).prop("checked")) {
							newRole += $(this).val() + ",";
						}
					});
					if (newRole == "") {
						alert("请选择角色!");
						return false;
					}
					$("#roleIds").val(newRole);
					$(modForm).submit();
					return false;
				}
			},
		});
	}
	
	function Change(){
		if (isFullInputData()) {
			isDuplicateAccount();
		} else {
			return;
		}
	}
	
	function isFullInputData() {
		if($("#accountName").val() == "") {
			alert("请输入学员账号！");
			return false;
		}
		
		if($("#realName").val() == "") {
			alert("请输入学员姓名！");
			return false;
		}
		
		if($("#mobilPhone").val() == "") {
			alert("请输入手机号码！");
			return false;
		}
		
		return true;
	}
</script>

<script type="text/javascript">
	$(function(){
		//选择目录弹出框
		$('#propNames01').click(function() {
			initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
			$('.att_make01').show();
		});
		
	});
	
	//显示之前选中的角色
	$(".chk").each(function(key,val){
		$(this).prop("checked",false);
	});
	var id = $("#userId").val();
	var account = "#account_role_" + id;
	var account_role = $(account).val();
	var roleArray = account_role.split(",");
	$(roleArray).each(function(key, val){
		$("#chk_" + val).prop("checked",true);
	});	
	

</script>