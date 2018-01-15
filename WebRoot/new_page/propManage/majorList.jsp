<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/new_page/top.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理平台</title>
</head>

<!--[if IE]>
	<script type="text/javascript">var ieFlag = true ;</script>                 
<![endif]-->

<body class="bjs" onkeydown="entersearch();">
<div>
	<!-- 题库内容 -->
	<div class="center">
		<div class="tk_center01" style="min-height:40px;">
			<div class="tk_zuo">
			<form id="sfrm" action="${ctx}/propManage/majorList.do" method="POST">
				<div class="tik_xuanze">
					<div class="mt10 fl tk_tixing">
						<em class="fl">专业名称：
						</em>
						<input type="text"  class="fl tik_select" name="smajor" value="${smajor}"/>
					</div>
					<div class="mt10 fl tk_tixing">
						<span class="fl">医院名称：</span>
						<input type="text"  class="fl tik_select" name="sname" value="${sname}"/>
					</div>
					<div class="mt10 fl tk_tixing">
						<span class="fl">专业级别：</span>
						<div class="fl tik_select">
							<div class="tik_position_1">
								<b class="ml5">请选择</b>
								<p class="tik_position01"><i class="tk_bjt10"></i></p>
							</div>
							<ul class="fl tk_list_1 tk_list" style="display:none;">
							<select name="sclassid" style="display:none;">
								<option value="0">请选择</option>
								<option value="1"<c:if test="${sclassid==1}"> selected</c:if>>1级</option>
								<option value="2"<c:if test="${sclassid==2}"> selected</c:if>>2级</option>
								<option value="3"<c:if test="${sclassid==3}"> selected</c:if>>3级</option>
								<option value="4"<c:if test="${sclassid==4}"> selected</c:if>>4级</option>
								<option value="5"<c:if test="${sclassid==5}"> selected</c:if>>5级</option>
							</select>
								<li>请选择</li>
								<li>1级</li>
								<li>2级</li>
								<li>3级</li>
								<li>4级</li>
								<li>5级</li>
							</ul>
						</div>
					</div>
					<div class="mt10 fl mll8 tk_tixing">
						<a href="javascript:void(0);" class="fl tk_chaxun">查询</a>
					</div>
				</div>
			</form>
			</div>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
	<div class="tkbjs"></div>
	<!--  -->
	<div class="center none" style="">
		<div class="tk_center01">
			<div class="mt10 kit1_tiaojia">
				<div class="fr cas_anniu">
<!-- 					<a href="javascript:void(0);" class="fl att_tianjia01" style="width:95px;margin-right:15px">专业排名设置</a>
 -->					<a href="javascript:void(0);" class="fl att_tianjia" style="width:95px;margin-right:15px">添加专业排名</a>
					<a href="javascript:void(0);" class="fl att_tianjia03" style="width:95px;">批量删除</a>
				</div>
			</div>
			<div class="clear"></div>
			<display:table name="propList" excludedParams="type" requestURI="${ctx}/propManage/majorList.do" id="p" class="mt10 cas_table" size="${totalCount}" pagesize="20"  sort="list" defaultsort="1">
				<display:column title="<input type='checkbox' class='chkall'/>" style="width:5%;"><input type='checkbox' class='chk' value="${p.id}"/></display:column>
				<display:column title="序号" style="width:5%;" property="id"></display:column>
				<display:column title="专业名称" sortable="true" style="width:15%;" property="major"></display:column>
 				<display:column title="医院名称" sortable="true" style="width:20%;" property="hospital"></display:column>
<%-- 				<display:column title="排名" style="width:10%;" property="orderName"></display:column>
 --%>				<display:column title="专业级别" sortable="true" style="width:10%;">
					<c:if test="${p.classId==1}">1级</c:if>
					<c:if test="${p.classId==2}">2级</c:if>
					<c:if test="${p.classId==3}">3级</c:if>
					<c:if test="${p.classId==4}">4级</c:if>
					<c:if test="${p.classId==5}">5级</c:if>
				</display:column>
 				<display:column title="操作" style="width:15%;">
					<a href="javascript:editPropShow('${p.id}','${p.major}','${p.hospital}','${p.hospitalId}','${p.orderName}','${p.classId}');" >修改</a>
					<a href="javascript:doDelProp(${p.id});" >删除</a>
 				</display:column>
			</display:table>

		</div>
	</div>
		<!-- 试题内容（结束） -->

</div>
<!-- 主题弹出框 -->
<div class="toumingdu att_xueke" style="display:none;">
	<div class="TJ_cueke">
		<div class="fl shitin_xinzeng01" style="margin-top:40px;margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">专业名称：</span><em class="fr">*</em></p>
			<input type="text" class="fl att_bianji01" id="rsMajor" />
			<input type="hidden" id="mode"  />
		</div>
		<div class="clear"></div>
		<div class="fl mt20 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">医院名称：</span><em class="fr">*</em></p>
			<%-- <select class="fl att_bianji01" id="rsHospital">
				<option value="">请选择</option>
				<c:forEach items="${hospList}" var="hospital">
					<option value="${hospital.name}">${hospital.name}</option>
				</c:forEach>
			</select> --%>
			<input type="text" class="fl att_bianji01" id="rsHospital" list="hospitalList">
			<datalist id="hospitalList">
				<!--[if IE]>
                   <select id="hospitalListSelectId" class="fl att_bianji01" style="display:none;margin-left:90px;"></select>                
				<![endif]-->								
			</datalist>
			<input type="hidden" id="rsHospitalId" />
			<input type="hidden" id="rsId" />
		</div>
		<div class="clear"></div>
		<div class="fl mt20 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">专业级别：</span><em class="fr">*</em></p>
			<select class="fl att_bianji01" id="rsClass">
				<option value="0">请选择</option>
				<option value="1"<c:if test="${sclassid==1}"> selected</c:if>>1级</option>
				<option value="2"<c:if test="${sclassid==2}"> selected</c:if>>2级</option>
				<option value="3"<c:if test="${sclassid==3}"> selected</c:if>>3级</option>
				<option value="4"<c:if test="${sclassid==4}"> selected</c:if>>4级</option>
				<option value="5"<c:if test="${sclassid==5}"> selected</c:if>>5级</option>
			</select>
		</div>
		<div class="clear"></div>
		<div class="fl mt20 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">专业排名：</span></p>
			<input type="text" class="fl att_bianji01" id="rsOrder" onkeyup="this.value=this.value.replace(/[^\d]/g, '');"/>
		</div>
		<div class="clear"></div>
		<div class="ca1_anniu" style="width:200px;">
			<a href="javascript:void(0);" class="fl anniu att_baocun">保存</a>
			<a href="javascript:void(0);" class="fl ml30 anniu att_fanhui">返回</a>
		</div>
	</div>
</div>



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
			return false;
		});
		
		$('.tk_list li').click(function(){
			var str=$(this).text();
			$(this).parent().parent().find('div').find('b').text(str);
			$(this).parent().find('option').prop('selected', '');
			$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
			$('.tk_list').slideUp(50);
		});
	//查询条件选择
		$('.tk_list option:selected').each(function(){
			var str=$(this).text();
			$(this).parent().parent().parent().find('b').text(str);
		});

		$(document).click(function(){
			$('.tk_list').hide('fast');
		});

	//查询学科
		$('.tk_chaxun').click(function(){
			$('#sfrm').submit();
			
		});	
	//添加学科
		$('.att_tianjia').click(function(){
			$('.att_xueke').show();
			$('#rsMajor').val('');
			$('#rsHospital').val('');
			$('#rsHospitalId').val('');
			$('#rsOrder').val('');
			$('#rsClass').val('');
			$('#rsId').val('');
			$('#mode').val('add');
		});
	//保存属性
		$('.att_baocun').click(function(){
			if (!$('#rsMajor').val()){
				alert ("请输入专业名称！");
				$('#rsMajor').focus();
				return;
			}
			if (!$('#rsHospital').val()){
				alert("请输入医院名称!");
				$('#rsHospital').focus();
				return;
			}
			if ($('#rsMajor').val().indexOf(" ")>-1){
				alert("专业名称不能为空格！");
				return;
			}
 			if ($('#rsHospital').val().indexOf(" ")>-1){
				alert("医院名称不能为空格！");
				return;
			}
			if ($('#rsMajor').val().length>50){
				alert("专业名称长度不能大于50！");
				$('#rsMajor').focus();
				return;
			}
 			if ($('#rsOrder').val().length>20){
				alert("专业排名长度不能大于20！");
				$('#rsOrder').focus();
				return;
			}
 			if ($('#rsOrder').val().indexOf(" ")>-1){
				alert("专业排名不能为空格！");
				return;
			}
			
	    	var selStr = $('#rsHospital').val();
			var selId = null;
			for(var i=0;i<$('#hospitalList option').length;i++){
				if($('#hospitalList option').eq(i).val()===selStr){
					selId=$('#hospitalList option').eq(i).attr('id');
					$('#rsHospitalId').val(selId);
					break;
				}				
			}
			
			if ($('#rsHospitalId').val()==''){
				alert("请输入正确的医院名称！");
				return;
			}
	    	

/* 			if (!$('#rsOrder').val()){
				alert("专业排名不能为空！");
				$('#rsOrder').focus();
				return;
			}
 */			if ($('#rsClass').val() == "0"){
				alert("请选择专业级别！");
				$('#rsClass').focus();
				return;
			}
			
			//添加
			if ($('#mode').val() == 'add'){
				var url = '${ctx}/propManage/majorList.do?mode=save';
				var params = 'major=' + $('#rsMajor').val();
				params += '&hospital='+$('#rsHospital').val();
				params += '&hospitalid='+$('#rsHospitalId').val();
				params += '&order='+$('#rsOrder').val();
				params += '&cls='+$('#rsClass').val();
				
				$.ajax({
					url: url,
					type: 'POST',
					data: params,
					dataType: 'text',
					success: function(result){
						if(result == 'success'){
						   alert('专业排名添加成功！');
						   $('.att_xueke').hide();
						   document.location.href = document.location.href.split("#")[0];
						}else{
							alert('专业与医院重复，无法保存！');
							return false;
						};
					}
				});
			}
			//修改
			else if ($('#mode').val() == 'edit'){
				var url = '${ctx}/propManage/majorList.do?mode=save';
				var params = 'major=' + $('#rsMajor').val();
				params += '&hospital='+$('#rsHospital').val();
				params += '&hospitalid='+$('#rsHospitalId').val();
				params += '&order='+$('#rsOrder').val();
				params += '&cls='+$('#rsClass').val();
				params += '&id='+$('#rsId').val();
				
				$.ajax({
					url: url,
					type: 'POST',
					data: params,
					dataType: 'text',
					success: function(result){
						if(result == 'success'){
						   alert('专业排名修改成功！');
						   $('.att_xueke').hide();
						   document.location.href = document.location.href.split("#")[0];
						}else{
							alert('专业与医院重复，无法保存！');
							return false;
						};
					}
				});
			}
			
			//$('.att_xueke').hide();
		});
	//返回
		$('.att_fanhui').click(function(){
			$('.att_xueke').hide();
		});
	//check all
		$('.chkall').click(function(){
			if ($(this).attr('checked') == 'checked')
				$('.chk').attr('checked' , 'checked');
			else
				$('.chk').removeAttr('checked');
		});
		
		$('.att_tianjia03').click(function(){
			if ($('.chk:checked').length<=0){
				alert ("没有选择项目。");
				return;
			}
			
			if(!confirm("您确定要删除吗？"))return;
			var url = '${ctx}/propManage/majorList.do?mode=batchdel';

			var array = new Array();var dels=0;
			$('.chk:checked').each(function(key, val){
				array.push($(val).val());dels++;
			});
			var params = 'ids='+array.toString();
			
			$.ajax({
				url: url,
				type: 'POST',
				data: params,
				dataType: 'text',
				success: function(result){
					if(result != '0'){
					 if(eval(dels-result)!=0){
						alert(dels+'个专业排名中'+result+'个专业排名删除成功，其他'+ eval(dels - result)+'个专业排名因为在别的地方使用无法删除！');
						document.location.href = document.location.href.split("#")[0];
						}
					    else{
					    alert(result+'个专业排名删除成功!');
						document.location.href = document.location.href.split("#")[0];
						}
					}else{
						alert('操作失败！');
					};
				}
			});
			
		});									
		
		
		$("#hospitalListSelectId").change(function(){
		   $('#rsHospital').val($(this).val()) ;
		});
		
		/*
		$("#hospitalListSelectId").blur(function(){
		  $('#rsHospital').val($(this).val()) ;
		});
		
		$("#rsHospital").blur(function(){
		   var firstHosVal = "" ;
		   var firstHosFlag = false ;
		   for(var i = 0; i< $('#hospitalList option').length; i++){
		       if (i == 0) firstHosVal = $('#hospitalList option').eq(i).val() ;
				if($('#hospitalList option').eq(i).val() == $("#rsHospital").val()){					
					firstHosFlag = true ;
					break;
				}				
			}
			if (!firstHosFlag) $("#rsHospital").val(firstHosVal) ;
		});
		*/
		
		$('#rsHospital').keyup(function(e){
			var keyword = $(this).val().trim();
			
			if (keyword == "") return false;
			
			switch(e.keyCode){
				case 37:
				case 38:
				case 39:
				case 40:
				case 13:
				case 9:
					return false;
			}
			
			try {							   
			   if (ieFlag) {//IE9及以下
			      $("#hospitalListSelectId").show();
			   }
			} catch(e){//IE10及以上、Chrome、Firefox、Safari			   
			}
							
			$('#rsHospitalId').val('');
			
			var url = "${ctx}/propManage/getHospitalListAjax.do";
			var params = "sname="+keyword;
			$.ajax({
				url: url,
				type: 'POST',
				data: params,
				dataType: 'json',
				success: function(result){
					if(result != ''){
						namelist = result.list;
						if (namelist!=undefined){							
							var datalist = "";
							var firstVal = ""
							$(namelist).each(function(key, value){
							    if (key == 0) firstVal = value.name ;
								datalist += "<option value='" + value.name + "' id='" + value.id + "'>" + value.name + "</option>";								
							});														
							
							try {							   
							   if (ieFlag) {//IE9及以下
							      $("#hospitalListSelectId option").remove();
							      $("#hospitalListSelectId").append(datalist);
							      //$('#rsHospital').val(firstVal) ;
							   }
							} catch(e){//IE10及以上、Chrome、Firefox、Safari
							   $('#hospitalList').html("");							
							   $('#hospitalList').html(datalist);
							}													
						}
					}else{
						//alert('操作失败！');
					};
				}
			});			
		});
		
		
		
				

		/* 以下代码会造成页面死循环
		$('#rsHospital').blur(function(e){
			if($(e.relatedTarget).prop('class') == "fl ml30 anniu att_fanhui")
				return;
			
			if($('#rsHospitalId').val() != '')
				return;
				
			var selStr = $(this).val();
			var selId = null;
			$('#hospitalList').find('option').each(function(){
				if ($(this).val() === selStr){
					selId = $(this).prop('id');
				}
			});
			
			if (selId == null){
				alert ("请输入正确的医院名称！");
				//$('#rsHospital').val('');
				$('#rsHospitalId').val('');
				return;
			}
			else
				$('#rsHospitalId').val(selId);
				return;
		});*/
});

//修改
function editPropShow(_id,_major,_hospital, _hospitalid,_order,_classid){
	$('#rsMajor').val(_major);
	$('#rsHospital').val(_hospital);
	$('#rsHospitalId').val(_hospitalid);
	$('#rsOrder').val(_order);
	$('#rsClass').val(_classid);
	$('#rsId').val(_id);
	$('#mode').val('edit');
	$('.att_xueke').show();
};

//删除
var force_del_url;
var force_del_params;
function doDelProp(id){
	if(!confirm("您确定要删除吗？"))return;
	var url = '${ctx}/propManage/majorList.do?mode=del';
	if(id!=''){
		var params = 'id=' +id;
	}
    force_del_url = url;
	force_del_params = params;
	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'text',
	    success: function(result){
		   if(result == 'success'){
		      alert('专业排名删除成功！');
		      document.location.href = document.location.href.split("#")[0];
		   }else{
			   alert("该属性已被使用，无法删除!");
			   /* 
		   		if(confirm('该属性已被使用，若删除，则使用该属性的地方均被删除，确定删除!')){
		   			
					var url = force_del_url;
					var params = force_del_params + "&type=-100";
				
					$.ajax({
					    url:url,
					    type: 'POST',
					    data: params,
					    dataType: 'text',
					    success: function(result){
						   if(result == 'success'){
						      alert('专业排名删除成功！');
						      document.location.href = document.location.href.split("#")[0];
						   }else{
						      alert('专业排名删除失败！');
						   };
					    }
					 });
		   		} */
		   };
	    }
	    });
}

function entersearch(){
   var event = window.event || arguments.callee.caller.arguments[0];
   if (event.keyCode == 13) {
	   $('#sfrm').submit();
   }
}
</script>
</body>
</html> 