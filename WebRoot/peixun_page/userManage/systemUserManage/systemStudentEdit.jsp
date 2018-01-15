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
<form method="post" action="${ctx}/system/systemUserStudent.do?method=updatenew" id="modForm" name="modForm" enctype="multipart/form-data">
<div>
	<div class="center">
		<div class="tk_tk_xuanxiag" style="">
			<div class="thi_shitineirong" style="">
				<div class="tiaojian" style="min-height:40px;">
					<input id="userId" name="model.userId" type="hidden" value="${item.userId}"/>
				    <!-- 第一行 证件类型  证件号码 -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>证件类型：</span>
					</p>
					<select class="fl select" id="certificateType" name="model.certificateType">
						<option value="1" <c:if test="${item.certificateType == 1}"> selected="selected"</c:if>>身份证</option>
						<option value="2" <c:if test="${item.certificateType == 2}"> selected="selected"</c:if>>军官证</option>
						<option value="3" <c:if test="${item.certificateType == 3}"> selected="selected"</c:if>>港澳台通行证</option>
						<option value="4" <c:if test="${item.certificateType == 4}"> selected="selected"</c:if>>护照</option>
					</select>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>证件号码：</span>
						<input type="text" id="certificateNo" name="model.certificateNo" value="${item.certificateNo}" onblur="checkIdCard();"/>
					</p>
					<div class="clear"></div>
					
					<!-- 第二行 学员账号 学员姓名 -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>学员账号：</span>
						<input type="text" id="accountName" name="model.accountName" value="${item.accountName}" maxlength="18" onblur="checkthi();"/>
					</p>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>学员姓名：</span>
						<input type="text" id="realName" name="model.realName" value="${item.realName}" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')"/>
					</p>
					<div class="clear"></div>
					
					<!-- 第三行 性别  学历 -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">性别：</span>
					</p>
					<select class="fl select" id="sex" name="model.sex">
						<option value="1" <c:if test="${item.sex == 1}"> selected="selected"</c:if>>男</option>
						<option value="2" <c:if test="${item.sex == 2}"> selected="selected"</c:if>>女</option>
					</select>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">学历：</span>
					</p>
					<select class="fl select" id="education" name="model.education">
						<option value="0">请选择</option>
						<option value="1" <c:if test="${item.education == 1}"> selected="selected"</c:if>>高中</option>
						<option value="2" <c:if test="${item.education == 2}"> selected="selected"</c:if>>中专</option>
						<option value="3" <c:if test="${item.education == 3}"> selected="selected"</c:if>>大专</option>
						<option value="4" <c:if test="${item.education == 4}"> selected="selected"</c:if>>本科</option>
						<option value="5" <c:if test="${item.education == 5}"> selected="selected"</c:if>>硕士</option>
						<option value="6" <c:if test="${item.education == 6}"> selected="selected"</c:if>>博士</option>
						<option value="7" <c:if test="${item.education == 7}"> selected="selected"</c:if>>博士后</option>
						<option value="8" <c:if test="${item.education == 8}"> selected="selected"</c:if>>其它</option>
					</select>
					<div class="clear"></div>
					
					<!-- 第四行  单位名称  科室 -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>单位名称：</span>
						<input type="text" id="workUnit" name="model.workUnit" value="${item.workUnit}"/>
						<input type = "hidden" id = "workUnitId" name = "model.work_unit_id" value = "${item.work_unit_id}" /> 
					</p>
					<div class="clear"></div>
					
					<!-- 第五行  职称类型  职称 -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>职称类别：</span>
						<!-- 
						<input type="text" id="workExtTypeName" name="workExtTypeName" value="" disabled/>
						 -->
                        <select name="model.workExtType" id="workExtType" style="width: 200px;height: 25px;">
                            <c:forEach items="${jobList}" var="job">   
                        		<option value="${job.code}" <c:if test="${job.code == workExtType}">  selected</c:if>>${job.name}</option>
                            </c:forEach>
                        </select>
					</p>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>职称：</span>
						<!-- 
						<input type="text" id="profTitle" name="profTitle" value="" disabled/>
						 -->
                        <select name="model.profTitle" id="profTitle" style="width: 200px;height: 25px;">
                        	<option value="-1">其它</option>
                            <c:forEach items="${myJobList}" var="myjob">   
                        		<option value="${myjob.id}" <c:if test="${myjob.id == userJobId}">  selected</c:if>>${myjob.name}</option>
                            </c:forEach>  
                        </select>
					</p>
					<div class="clear"></div>
					
					<!-- 单独的科室 -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>科室：</span>
						<!-- 
						<input type="text" id="deptName" name="deptName" value="" disabled/>
						 -->
                        <select name="model.xueke1" id="xueke1" style="width: 168px;height: 25px;">
                           	<c:forEach items="${xueke1}" var="xue">   
                           		<c:if test="${xue.id == levelOne}"><option value="${xue.id}" selected>${xue.name}</option></c:if>
                               </c:forEach>                                
                        </select>
                        <select name="model.xueke2" id="xueke2" style="width: 168px;height: 25px;">
                            <c:forEach items="${xueke2}" var="xue2">   
                           		<option value="${xue2.id}" <c:if test="${xue2.id == levelTwo}">selected</c:if>>${xue2.name}</option>
                               </c:forEach>
                        </select>
                        <select name="model.xueke3" id="xueke3" style="width: 170px;height: 25px;">
                            <c:forEach items="${xueke3}" var="xue3">   
                           		<option value="${xue3.id}" <c:if test="${xue3.id == item.propIds}">selected</c:if>>${xue3.name}</option>
                               </c:forEach>
                        </select>
					</p>
					<div class="clear"></div>
					
					<!-- 第六行 手机号  邮箱   -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>手机号码：</span>
						<input type="text" id="mobilPhone" name="model.mobilPhone" value="${item.mobilPhone}" onblur="checkPhone();" maxlength="11"/>
					</p>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">邮箱：</span>
						<input type="text" id="email" name="model.email" value="${item.email}" onblur="checkEmail();"/>
					</p>
					<div class="clear"></div>
					
					<!-- 第七行  来自基层  执业医师号-->
						<!-- 
						<div class="clear"></div><p class="fl" style="margin:0 0 20px 0">
							<span style="width:8em;text-align:right;">地区：</span>
						</p>
						<p class="fl" style="margin-bottom:20px;">
							<input type="text" id="userProvinceName" name="model.userConfig.userProvinceName" value="" disabled/>
						</p>
						<p class="fl" style="margin-bottom:20px;">
							<input type="text" id="userCityName" name="model.userConfig.userCityName" value="" disabled/>
						</p>
						<p class="fl" style="margin-bottom:20px;">
							<input type="text" id="userCountiesName" name="model.userConfig.userCountiesName" value="" disabled/>
						</p>
						<div class="clear" ></div>
						 -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>来自基层：</span>
					</p>
					<select class="fl select" id="grassroot" name="model.grassroot">
						<option value="0" <c:if test="${item.grassroot == 0}"> selected="selected"</c:if>>否</option>
						<option value="1" <c:if test="${item.grassroot == 1}"> selected="selected"</c:if>>是</option>
					</select>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">执业医师号：</span>
						<input type="text" id="health" name="model.health_certificate" value="${item.health_certificate}" onblur="checkcode();"/>
					</p>	
					<div class="clear"></div>
					
					<!-- 第八行 单位地址-->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;"><em>*</em>单位地址：</span>
						<!-- 
						<input type="text" id="address" name="userConfig.address" value="${item.userConfig.address}" disabled/>
						 -->
                        <c:if test="${hosAddress != null}">
                        	<c:if test="${hosAddress != ''}">
                        		<input type="text" name="model.hospitalAddress" id="address"  placeholder="街道/门牌号" size="50" value=" ${hosAddress}"/>
                        	</c:if>
                        </c:if>
                        <c:if test="${hosAddress == null || hosAddress == ''}">
                        	<c:if test="${hospitalAddress != null && hospitalAddress != ''}">
                        		<input type="text" name="model.hospitalAddress" id="address"  placeholder="街道/门牌号" size="50" value=" ${hospitalAddress}"/>
                        	</c:if>
                        </c:if>
                        <c:if test="${hosAddress == null and hospitalAddress == null}">
                        	<input type="text" name="model.hospitalAddress" id="address"  placeholder="街道/门牌号" size="50" value=" ${hospitalAddress}"/>
                        </c:if>
					</p>
					<div class="clear"></div>
					
					<!-- 第九行  注册时间  更新时间   -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">注册时间：</span>
						<input type="text" id="regDate" value="${item.regDatee}" readonly/>
					</p>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">更新时间：</span>
						<input type="text" id="lastUpdateDate" value="${item.lastUpdateDatee}" readonly/>
					</p>
					<div class="clear"></div>
					
					<!-- 第十行  备注说明  备注时间   -->
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">备注说明：</span>
						<input type="text" id="reason" value="${item.reason}" readonly/>
					</p>
					<p class="fl" style="margin-bottom:20px;">
						<span style="width:8em;text-align:right;">备注时间：</span>
						<input type="text" id="reasondate" value="${item.reasondate}" readonly/>
					</p>
					<div class="clear"></div>
					
					<!-- 第十一行  保存 返回   -->
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
	//验证用户名是否可用
	function checkthi(){
		var accountName = $("#accountName").val();
		var filter = /^[a-zA-Z0-9]{4,18}$/;
		if(accountName=='' || accountName == null){
			alert("*学员帐号不能为空");
		}else {
			if(!filter.test(accountName)){
				alert("*学员帐号格式不正确");
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
							alert("*学员帐号已经存在");
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
	//验证执业医师号不能重复
	function checkcode(){
		var code = $('#health').val();
		var check = /^[0-9]{15}$/.test(code); 
		if (code==null || code=="") {
			return false;
		} 
		if (code!="" && !check){
			alert("执业医师号为15位数字！");
			$('#health').val("");
			$('#health').focus();
		  	return false;
		} 
		$.ajax({
			type:'POST',
			url:'${ctx}/system/systemUserStudent.do?method=code&code='+code+"&userId=" + $("#userId").val(),
			dataType:'json',
			success:function(data){
				var result = eval(data);
				if(result.message!='yes'){
					alert("执业医师号已存在！");
					$('#health').val("");
	     			$('#health').focus();
				  	return false;
				}
			}
		});
	}
</script>
<script type="text/javascript">
	//验证邮箱是否已经存在
	function checkEmail(){
		var email = $("#email").val();
		if($("#email").val()!=""){
			var re = /^([a-zA-Z0-9_-])+@([A-Za-z0-9]+[-.])+[A-Za-zd]{2,5}$/;
			if ($('#email').val() !="" && !re.test($('#email').val())){
				alert("邮箱格式不对！");
				$("#email").val("");
				$("#email").focus();
				return false;
			} else {
				//通过ajax检测邮箱是否已经存在
				$.ajax({
					type:'POST',
					url:'${ctx}/system/systemUserStudent.do?method=checkIsUnique&email='+email+"&userId=" + $("#userId").val(),
					dataType:'json',
					async: false,
					success:function(data){
						var result = eval(data);
						if(result.message!='success'){
							alert("该邮箱已经存在，请重新输入！");
							$("#email").val("");
							$("#email").focus();
							return false;
						}
					}
				});
			}
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
			alert("*证件号码不可以为空");
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
					alert("学员姓名重复，请重新输入！");
					$("#accountName").focus();
					return true;
				} else {
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
		if($("#certificateType").val() == 0) {
			alert("请选择证件类型！");
			return false;
		}
		
		if($("#certificateNo").val() == "") {
			alert("请输入证件号码！");
			return false;
		}

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
		
		if($("#workExtType").val() == -1 || $("#workExtType").val()=="" || $("#workExtType").val()== null) {
			alert("请选择职称类型！");
			return false;
		}
		if($("#profTitle").val() == -1 || $("#profTitle").val()=="" || $("#profTitle").val()== null) {
			alert("请选择您的职称！");
			return false;
		}
		if($("#xueke1").val() == -1 || $("#xueke1").val()=="" || $("#xueke1").val()== null) {
			alert("请选择一级学科！");
			return false;
		}
		if($("#xueke2").val() == -1 || $("#xueke2").val()=="" || $("#xueke2").val()== null) {
			alert("请选择二级学科！");
			return false;
		}
		if($("#xueke3").val() == -1 || $("#xueke3").val()=="" || $("#xueke3").val()== null) {
			alert("请选择三级学科！");
			return false;
		}
		
		if($("#address").val() == "") {
			alert("请输入学员单位地址！");
			return false;
		}
		
		return true;
	}
</script>

<script type="text/javascript">

	function initXueke(idd) {
		var dd=idd;
		var url = "${ctx}/propManage/getPropListAjax.do?idd="+dd;
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){   
			   if(result != ''){ 
			   		updateXueke($('#xueke1'), result);
			   		//修改联动一级学科
			   		var img_type=$("#workExtType").val();
					$("#xueke1 option").attr("selected",false);
					$("#xueke1 option[img_type="+img_type+"]").attr("selected",true)
					
					$("#xueke2").val(0);
					$("#xueke3").val(0);
					var url = "${ctx}/propManage/getPropListAjax.do?id="+$("#xueke1").val();
					$.ajax({
					    url:url,
					    type: 'POST',
					    dataType: 'json',
					    success: function(result){   
						   if(result != ''){ 
						   		updateXueke($('#xueke2'), result);
						   		updateXueke($('#xueke3'), null);
						   };
					    }
					});	
			   };
		    }
		});	
	}

	$("#xueke1").change(function(){
		$("#xueke2").val(0);
		$("#xueke3").val(0);
		var url = "${ctx}/propManage/getPropListAjax.do?id="+$("#xueke1").val();
		
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){   
			   if(result != ''){ 
			   		updateXueke($('#xueke2'), result);
			   		updateXueke($('#xueke3'), null);
			   };
		    }
		});	
	});
	
	$("#xueke2").change(function(){
		$("#xueke3").val(0);
		var url = "${ctx}/propManage/getPropListAjax.do?id="+$("#xueke2").val();
		
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){   
			   if(result != ''){ 
			   		updateXueke($('#xueke3'), result);
			   };
		    }
		});	
	});
	
	$("#workExtType").change(function(){
		
		//work_type改变后，先给xueke1赋值
		initXueke($("#workExtType").val());
		
		$("#profTitle").val(0);
		var url = "${ctx}/propManage/propWorkUnit.do?ext_type="+$("#workExtType").val();
		
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){   
			   if(result != ''){ 
			   		updateWorkUnit($('#profTitle'), result);
			   };
		    }
		});	
	});

	function updateWorkUnit(obj, result, _default){
		var str="<option value='-1' selected>请选择</option>";
		if (result!=null)
		$(result.list).each(function(key, value){
			str += "<option value='"+value.id+"'>"+value.name+"</option>";
		});
		$(obj).html(str);
	}
	
	function updatePropList(obj, result, _default){
		var str = "<option value='-1' selected>请选择</option>";
		if (result!=null)
		$(result.list).each(function(key, value){
			str += "<option value='"+value.prop_val_id+"'>"+value.name+"</option>";
		});
		$(obj).html(str);
	}

	function updateXueke(obj, result, _default) {
		var str = "<option value='-1' selected>请选择</option>";
		
		if (result!=null)
		$(result.list).each(function(key, value){
			str += "<option value='"+value.id+"'>"+value.name+"</option>";
		});
		
		$(obj).html(str);
	}

</script>