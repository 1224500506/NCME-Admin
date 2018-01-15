<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<%@include file="/commons/taglibs.jsp"%>
</head>
<%@include file="/peixun_page/top.jsp"%>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>

<body>
<div>
	<!-- 查询条件 -->
	<div class="center">
	<form id="queryForm" name="queryForm" action="${ctx}/cardManage/SystemCardType.do" method="post">
		<input type="hidden" id="method" name="method" value="list" />
		<div class="tiaojian" style="min-height:40px;">
			<p class="fl">
				<span>卡类型：</span>
				<input type="text" name="cardTypeName" id="cardTypeName" value=""/>
			</p>
			<p class="fl jianju">
				<span>有效时间：</span>
				<input type="text" class="" name="startDate" value="${startDate}" onClick="WdatePicker()"/>
			</p>
			<p class="fl jianju">
				<span>失效时间：</span>
				<input type="text" class="" name="endDate" value="${endDate}" onClick="WdatePicker()"/>
			</p>
			<div class="fl cas_anniu">
				<a href="javascript:query();" class="fl" style="width:70px;margin-left:70px;">查询</a>
			</div>
		</div>
	</form>
	</div>
	<div class="clear"></div>
	<div class="bjs"></div>
	<!-- 内容 -->
	<div class="center">
		<div class="center01">
			<div class="mt5 kit1_tiaojia">
				<div class="fr cas_anniu">
					<a href="#" class="fl xuexike" style="width:80px;">添加</a>
					<a href="javascript:delBatch();" class="fl" style="width:80px;margin-left:10px;">删除</a>
				</div>
			</div>
		</div>
		<div class="clear"></div>
			<display:table requestURI="${ctx}/cardManage/SystemCardType.do?method=list" id="p" class="mt10 table" 
				list="${page.list}" size="${page.count}" pagesize="20" partialList="true" 
				excludedParams="method"  keepStatus="true" decorator="com.hys.exam.displaytag.TableOverOutWrapper">
				<display:column title="<input type='checkbox' class='chkall'/>" style="width:4%;"><input type='checkbox' class='chk' value="${p.id}"/></display:column>
				<display:column title="序号" style="width:4%;">${p_rowNum}</display:column>
				<display:column title="ID" style="width:4%;" property="id"></display:column>
				<display:column title="卡类型名称" style="width:18%;" property="cardTypeName"></display:column>
				<display:column title="有效时间" style="width:8%;" ><fmt:formatDate value="${p.startDate }" pattern="yyyy-MM-dd"/></display:column>
 				<display:column title="失效时间" style="width:8%;" ><fmt:formatDate value="${p.endDate }" pattern="yyyy-MM-dd" /></display:column>
 				<display:column title="总学时" style="width:4%;" property="creditSum"></display:column>
 				<display:column title="产品总价" style="width:8%;" property="price"></display:column>
 				<display:column title="总数" style="width:4%;" property="allNum"></display:column>
 				<display:column title="已使用" style="width:4%;" property="userdNum"></display:column>
 				<display:column title="剩余" style="width:4%;" >
					<c:if test="${p.remainNum <100}"><font color='red'>${p.remainNum}</font></c:if>
					<c:if test="${p.remainNum >=100}">${p.remainNum}</c:if>
 				</display:column>
 				<display:column title="操作" style="width:30%;">
					<a href="javascript:updateType('${p.id }',0)" >查看卡类型</a>
					<a href="javascript:updateType('${p.id }',1)" >修改卡类型</a>
					<a href="javascript:authorizeSite('${p.id }')" >授权站点</a>
					<a href="javascript:authorizePaycardOrgan('${p.id }')" >授权地区</a>
					<a href="javascript:authorizeCourse('${p.id }')" >授权项目</a>
 				</display:column>
			</display:table>

	</div>
		<!-- 内容（结束） -->
		
</div>

<!-- 添加 -->
<div class="toumingdu make02" style="display:none;">
	<div class="lec_center" style="height:500px;">
	<form id="editForm" name="editForm" action="${ctx}/cardManage/SystemCardType.do" method="post">
		<input type="hidden" id="method" name="method" value="save" />
		<input type="hidden" id="eid" name="id" value="0" />
		<div style="padding-top:20px;">
			<div class="mt10 lc_shengcheng">
				<span>卡类型名称：</span>
				<input type="text" id="ecardTypeName" name="cardTypeName" maxlength="100"/>
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>有效时间：</span>
				<input type="text" name="startDate" id="estartDate" onClick="WdatePicker({maxDate:'#F{$dp.$D(\'eendDate\')}'})" />
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>失效时间：</span>
				<input type="text" id="eendDate" name="endDate" onClick="WdatePicker({minDate:'#F{$dp.$D(\'estartDate\')}'})" />
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>付费学分范围：</span>
				<input type="text" id="creditScope" name="creditScope" maxlength="10"/>
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>总学时：</span>
				<input type="text" id="creditSum" name="creditSum" maxlength="10"/>
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>产品价格：</span>
				<input type="text" id="price" name="price" maxlength="7"/>
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>销售元值：</span>
				<input type="text" id="evpValue" name="evpValue"maxlength="10"/>
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>销售方式：</span>
				<select class="lc_list" id="isNetpay" name="isNetpay">
					<option value="1" >仅限网上销售</option>
					<option value="2" >仅限地面销售（前台不需要销售）</option>
					<option value="3" >地面网上销售</option>
				</select>
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span>授权类型：</span>
				<span style="width:300px;height:50px;">
				<c:forEach items="${allList}" var="credit" >
					<span style="width:auto;"><input type="checkbox" style="border:none;width:20px;height:13px;margin-top:9px;margin-left:5px;" name="creditType" value="${credit.creditType }" />
					<em>${credit.creditType}${credit.creditName }</em></span>
				</c:forEach>
				</span>
			</div>
			<div class="clear"></div>
			<div class="cas_anniu" style="margin-top:20px;margin-left:170px;">
				<a href="#" class="fl queren" style="width:70px;">确认</a>
				<a href="#" class="fl quxiao" style="width:70px;margin-left:40px;">取消</a>
			</div>
		</div>
		<div class="clear"></div>
	</form>
	</div>
</div>

<script type="text/javascript">
$(function(){

	//select menu
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "lc_left0" + menuindex;
		$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
	
		//check all
		$('.chkall').click(function(){
			if ($(this).attr('checked') == 'checked')
				$('.chk').attr('checked' , 'checked');
			else
				$('.chk').removeAttr('checked');
		});

	//添加
		$('.xuexike').click(function(){
			$('.queren').show();
			$('.make02 input').removeProp('disabled');
			$('.make02 select').removeProp('disabled');

			$('#eid').val("0");
			$('#ecardTypeName').val("");
			$('#estartDate').val("");
			$('#eendDate').val("");
			$('#creditScope').val("");
			$('#creditSum').val("");
			$('#price').val("");
			$('#evpValue').val("");
			$('#isNetpay').val("");
			$('#ecardTypeName').val("");
			
			$('.make02 input[type="checkbox"]').each(function(){
				$(this).prop("checked","");
			});
			
			$('.make02').show();
		});
		
		$('.queren').click(function(){
			//添加创建卡校验
			if($('#ecardTypeName').val() == ""){
				alert("卡类型名称不能为空！");
				$('#ecardTypeName').focus();
				return false;
			}
			if($('#estartDate').val() == ""){
				alert("有效时间不能为空！");
				$('#estartDate').focus();
				return false;
			}
			if($('#eendDate').val() == ""){
				alert("失效时间不能为空！");
				$('#eendDate').focus();
				return false;
			}
			if($('#creditScope').val() == ""){
				alert("付费学分范围不能为空！");
				$('#creditScope').focus();
				return false;
			}
			if($('#creditSum').val() == ""){
				alert("总学时不能为空！");
				$('#creditSum').focus();
				return false;
			}
			if(!/^[1-9]\d*$/.test($('#creditSum').val())){
				alert("总学时范围为整数值！");
				$('#creditSum').focus();
				return false;
			}
			if($('#price').val() == ""){
				alert("产品价格不能为空！");
				$('#price').focus();
				return false;
			}
			if(!/^((?:-?0)|(?:-?[1-9]\d*))(?:\.\d{1,2})?$/.test($('#price').val())){
				alert("产品价格范围为正整数或两位小数！");
				$('#price').focus();
				return false;
			}
			if($('#evpValue').val() == ""){
				alert("销售元值不能为空！");
				$('#evpValue').focus();
				return false;
			}
			if($('#isNetpay').val() == ""){
				alert("销售方式不能为空！");
				$('#ecardTypeName').focus();
				return false;
			}
		
			if($('#ecardTypeName').val().length >= 100){
				alert("卡类型名称长度不能超过100个文字！");
				$('#ecardTypeName').focus();
				return false;
			}
			
			if($('#creditScope').val().length > 20){
				alert("输入10个文字！");
				$('#creditScope').focus();
				return false;
			}
                        
                        //YHQ，2017-03-17
                        var creditScopeVal = $('#creditScope').val() ;
                        var creditTypeVal = "" ;
                        $("[name='creditType']:checked").each(function(){ 
                           creditTypeVal += $(this).val();                         
                        }) ;
                        
                        if (creditScopeVal != "" ) {
                            if((/^(\+|-)?\d+$/.test(creditScopeVal)) && creditScopeVal > 0){                                
                            } else {
                                alert("付费学分范围为整数值！") ;
                                return ;
                            }
                            if (creditTypeVal == "") {
                                alert("请选择授权类型！") ;
                                return ;
                            }
                        } else {
                            if (creditTypeVal != "") {
                                alert("不输入付费学分范围时不能选择授权类型！") ;
                                return ;
                            }
                        }
                                                
			var url = '${ctx}/cardManage/SystemCardType.do?method=save';
			var params = $('#editForm').serialize();
			
			$.ajax({
				url: url,
				type: 'POST',
				data: params,
				dataType: 'text',
				success: function(result){
					if (result == "success"){
						alert ("保存成功！");
						document.location.href = document.location.href.split("#")[0];
					}else if (result == "nameIsSame") {
						alert ("卡类型名称重复，请重新输入！");
						return false;
					}
					else{
						alert ("网络原因，保存不成功,请稍后再试！");
						return false;
					}
				}
			});	
		});
		$('.make02').hide();
		$('.quxiao').click(function(){
			$('.make02').hide();
		});


});

//[查询] 
function query() {
	$('#queryForm').submit();
}

//[批量删除] 
function delBatch() {
//	alert("暂不允许删除卡类型！");
//	return;
	var cardTypeIds = $(".chk[type='checkbox']:checked");
	if(cardTypeIds.length == 0){
		alert("请先选择学习卡类型!");
	    return;
	}
	var selectedIds = '';
	if(cardTypeIds != '' && cardTypeIds.length >0 ){
		for(var i=0; i<cardTypeIds.length; i++){
			selectedIds += cardTypeIds[i].value + ',';
		}
	}
	if(confirm("确认删除吗?")){
		var p = {'typeIds':selectedIds};
		$.post("${ctx}/cardManage/SystemCardType.do?method=delete", p,
  			function(data){
  				if(data >0){
  					alert("卡类型删除成功!");
  				}
  				else{
  					alert("该卡类型已被学习卡绑定，不能删除！");
  				}
  				document.location.href = document.location.href.split("#")[0];
  		});
	}
}

//授权/非授权的项目
function authorizeCourse(typeId){
	//window.location.href = "${ctx}/cardManage/SystemCardType.do?method=getStudyCourse&isAuthorized=1&typeId="+typeId;
	window.location.href = "${ctx}/cardManage/SystemCardType.do?method=systemCardTypeCVlist&cardTypeId="+typeId;
}

//授权站点
function authorizeSite(typeId){
	window.location.href = "${ctx}/cardManage/SystemCardTypeSiteOrg.do?method=getSystemSiteList&typeId="+typeId;
}

//授权
function authorizePaycardOrgan(typeId){
	window.location.href = "${ctx}/cardManage/SystemCardTypeSiteOrg.do?method=getSystemPaycardOrganList&typeId="+typeId;
}

//修改
function updateType(id, mode){
	if (mode == 1){
		$('.queren').show();
	/* 	$('.make02 input').removeProp('disabled');
		$('.make02 select').removeProp('disabled'); */
		$('.make02 input[type="text"]').attr('style', 'border:display');		
		$('.make02 input').removeProp('readonly');
		
		$('.make02 input[type="checkbox"]').removeAttr('disabled');
		$('.make02 select').removeAttr('disabled');
	}
	else{
		$('.queren').hide();		
		$('.make02 input[type="text"]').attr('style', 'border:none');		
		$('.make02 input').prop('readonly', 'readonly');
		$('.make02 input').removeProp('onclick');
		$('.make02 input[type="checkbox"]').attr('disabled', 'disabled');		
		$('.make02 select').attr('disabled', 'disabled');
	}
	
	var url = '${ctx}/cardManage/SystemCardType.do?method=get';
	var params = 'id='+id;
	
	$.ajax({
		url: url,
		type: 'POST',
		data: params,
		dataType: 'json',
		success: function(result){
			$('#eid').val(id);
			$('#ecardTypeName').val(result.type.cardTypeName);
			$('#estartDate').val(result.startDate);
			$('#eendDate').val(result.endDate);
			$('#creditScope').val(result.type.creditScope);
			$('#creditSum').val(result.type.creditSum);
			$('#price').val(result.type.price);
			$('#evpValue').val(result.type.evpValue);
			$('#isNetpay').val(result.type.isNetpay);
			$('#ecardTypeName').val(result.type.cardTypeName);
			
			$('.make02 input[type="checkbox"]').each(function(){
				var i = $(this).parent().index();
				$(this).prop("checked",(result.allList[i].checked == "1")?"checked":"");
			});
			
			$('.make02').show();
		}
	});	
}
</script>
</body>
</html>