<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>培训平台-机构管理</title>
		<%@include file="/commons/taglibs.jsp"%>
		
	</head>
<%@include file="/peixun_page/top.jsp"%>

		<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/fileUpload.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/autocomplete/jquery.bigautocomplete.js"></script> 
     	<link href="${ctx}/peixun_page/js/autocomplete/jquery.bigautocomplete.css" rel="stylesheet">
<body>
<!-- 查询条件 -->
<div class="center">

<form action="${ctx}/system/peixunOrglist.do?method=list" id="searchform" method="post">
	<div class="tiaojian" style="min-height:40px;">
	<input type="hidden" id="flag"  name="flag" value="<%=request.getAttribute("flag") %>"/>
		<p class="fl" style="margin:0 20px 20px 0">
			<span>机构名称：</span>
			<input type="text" name="model.name" value="${item.name}"/>
		</p>
		<p class="fl">
			<span style="text-align:right;">机构类型：</span>
		</p>
		<div class="fl select" style="margin-right:20px;">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display: none;">
			<select  name="model.type"  style="display:none;" value="${item.type}">
				<option value="">全部</option>
				<option value="4" <c:if test="${item.type==4}">selected</c:if>>医疗机构</option>
				<option value="5" <c:if test="${item.type==5}">selected</c:if>>医学院校</option>
				<option value="6" <c:if test="${item.type==6}">selected</c:if>>学/协会</option>
				<option value="7" <c:if test="${item.type==7}">selected</c:if>>出版社</option>
				<option value="8" <c:if test="${item.type==8}">selected</c:if>>社会单位</option>
			</select>
				<li>全部</li>
				<li>医疗机构</li>
				<li>医学院校</li>
				<li>学/协会</li>
				<li>出版社</li>
				<li>社会单位</li>
			</ul>
		</div>
		
		<p class="fl">
			<span style="width:5em;text-align:right;">地区：</span>
		</p>
		<div class="fl select" style="margin:0 10px 0 0">
			<div class="tik_position">
				<b class="ml5">省/直辖市</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" id="region1" style="display: none;">
				<select name="model.region1_Id" style="display:none;" value="${item.region1_Id}">
					<option value="0">省/直辖市</option>
					<c:forEach items="${region1list}" var="region1">
						<option value="${region1.prop_val_id}" <c:if test="${region1.prop_val_id==item.region1_Id}">selected="selected"</c:if>>${region1.name}</option>
					</c:forEach>
				</select>
				<li>省/直辖市</li>
				<c:forEach items="${region1list}" var="region1">
					<li>${region1.name}</li>
				</c:forEach>
			</ul>
		</div>
		<div class="fl select" style="margin:0 10px 0 0">
			<div class="tik_position">
				<b class="ml5" id="city">市</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" id="region2" style="display: none;">
				<select name="model.region2_Id" style="display:none;" value="${item.region2_Id}">
					<option value="0">市</option>
					
					<c:forEach items="${region2list}" var="region2">
						<option value="${region2.id}" <c:if test="${region2.id==item.region2_Id}">selected="selected"</c:if>>${region2.name}</option>
					</c:forEach>
				</select>
				<li>市</li>
				
				<c:forEach items="${region2list}" var="region1">
					<li>${region2.name}</li>
				</c:forEach>
			</ul>
		</div>
	
		<div class="clear"></div>
	
		<p class="fl" >
			<span style="width:5em;text-align:right;">状态：</span>
		</p>
		<div class="fl select" style="margin-right:20px;">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display:none" id="state">
				<select name="model.state" style="display:none;">
					<option value='0' >全部</option>
					<option value='1' <c:if test="${item.state==1}">selected</c:if>>正常</option>
					<option value='2' <c:if test="${item.state==2}">selected</c:if>>停用</option>
				</select>
				<li>全部</li>
				<li>正常</li>
				<li>停用</li>
			</ul>
		</div>
			
		<p class="fl cas_anniu">
			<a href="javascript:$('#searchform').submit();" class="fl" style="width:70px;margin-left:10px;">查询</a>
		</p>
		<p class="fr cas_anniu">
			<a  class="fr add_btn" id="add_btn" style="width:140px;">创建机构</a>
		</p>
	</div>
</form>
</div>
<div class="clear" style="height:20px"></div>
<div class="bjs"></div>

<!-- 内容 -->
<div class="center" align="center">
 
	<div class="center01">
 		<display:table  name="page" id="peixunOrg" requestURI="${ctx}/system/peixunOrglist.do?method=list" 
				class="mt10 table" excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}"
				 keepStatus="true"  decorator="com.hys.exam.displaytag.TableOverOutWrapper">
				<display:column title="序号" style="width:4%;text-align:center">${peixunOrg_rowNum}</display:column>
				<display:column property="name" title="机构名称" style="width:12%;" sortable="true"></display:column>
				<display:column title="机构类型" style="width:8%;" sortable="true" sortProperty="type">
					<c:if test="${peixunOrg.type==4}">医疗机构</c:if>
					<c:if test="${peixunOrg.type==5}">医学院校</c:if>
					<c:if test="${peixunOrg.type==6}">学/协会</c:if>
					<c:if test="${peixunOrg.type==7}">出版社</c:if>
					<c:if test="${peixunOrg.type==8}">社会单位</c:if>
				</display:column>
				<%--<display:column title="管理单位" style="width:8%;" sortable="true" property = "hospital_name">--%>
				<%--</display:column>--%>
				<display:column title="地区" style="width:10%;" sortable="true" sortProperty = "region1_Id">
					<c:forEach items="${region1list}" var="region1">
						<c:if test="${region1.prop_val_id==peixunOrg.region1_Id}">${region1.name}</c:if>
					</c:forEach>/
					<c:forEach items="${region2list}" var="region2list">
						<c:if test="${region2list.id==peixunOrg.region2_Id}">${region2list.name}</c:if>
					</c:forEach>
					<%-- ${peixunOrg.region1_Id} / ${peixunOrg.region2_Id} --%>
				</display:column>
				<display:column property="contact" title="联系人" style="width:10%;" sortable="true"></display:column>
				<display:column property="phone_Number" title="联系电话" style="width:8%;" sortable="true"></display:column>
				<display:column  title="状态" style="width:8%;" sortable="true" sortProperty = "state">
					<c:if test="${peixunOrg.state==1}">正常</c:if>
					<c:if test="${peixunOrg.state==2}">停用</c:if>
				</display:column>
				<display:column  title="账号" style="width:10%;" sortable="true" sortProperty="account_name" property = "accountName">
					<%-- <c:forEach items="${accountarry}" var="acc">
						<c:if test="${acc.accountId == peixunOrg.user_Id}">${acc.accountName} </c:if>
					</c:forEach> --%>
					
				</display:column>
				<display:column title="账号状态" style="width:10%;" sortable="true" sortProperty = "account_status">
					<c:if test="${peixunOrg.account_status==1}">正常</c:if>
					<c:if test="${peixunOrg.account_status==2}">停用</c:if>
				</display:column>
				<display:column title="操作" style="width:12%;text-align:center;">
					<a class="edit_btn" href="javascript:infoView(${peixunOrg.id});">编辑</a> 
					<c:if test="${peixunOrg.state==1}"><a href="javascript:updateState(${peixunOrg.id},${peixunOrg.state})">停用</a></c:if>
					<c:if test="${peixunOrg.state==2}"><a href="javascript:updateState(${peixunOrg.id},${peixunOrg.state})">正常</a></c:if>
				</display:column>
		</display:table> 
		
		<div class="clear"></div> 
	</div>
</div>



<!-- common layer -->
<div class="center" id="layerContent">
<form action="${ctx}/system/peixunOrglist.do?method=add" id="fms" name="fms" method="post" enctype="multipart/form-data">
	<center>选择医疗机构时，请输入至少四个文字，来提升检索速度。</center>
	<div class="tiaojian" style="min-height:40px;margin-left:20px;margin-right:20px;margin-top:10px;">
		<p class="fl">
			<span style="width:6em;text-align:right;">机构类型：</span>
		</p>		
		<div class="fl select" style="margin:0 10px 0 0">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display:none;" id="type">
			<select  name="model.type" id="type" style="display:none;">
				<option value="">全部</option>
				<option value="4">医疗机构</option>
				<option value="5">医学院校</option>
				<option value="6">学/协会</option>
				<option value="7">出版社</option>
				<option value="8">社会单位</option>
			</select>
				<li>全部</li>
				<li>医疗机构</li>
				<li>医学院校</li>
				<li>学/协会</li>
				<li>出版社</li>
				<li>社会单位</li>
			</ul>
		</div>
		<p class="fl">
			<span style="width:6em;text-align:right;"><font color="red">*</font>机构全称：</span>
			<input type="text" name="model.name" value="" id="name" autocomplete="off" oninput="OnInput(event)" onpropertychange="OnPropChanged(event)" />
		</p>

		<div class="fl">	
			<p class="fl" style="margin-left:5px;">
				<span style="width:6em;text-align:right;"><font color="red">*</font>机构账号：</span>
				<input type="text"  name="model.accountName" id="accountName"/>
				<input type="hidden"  name="model.accountName" id="oldAccountName"/>
				<input type="hidden" name="model.accountId" id="accountId"/>
				<input type="hidden" name="model.userId" id="userId"/>
			</p>
		</div>
				
 		<div class="clear"></div> 
 		<div class="fr course_img" style="width:30%;">
			<p><span style="width:4em;text-align:right;">Logo：</span><img class="fl" style="margin-right:30px" src="" id="img" width="140px"></p>
			<div class="clear" style="height:20px"></div>
			<p><span style="width:4em;text-align:right;">图片：</span><img class="fl" style="margin-right:30px" src="" id="photo" width="140px"></p>
		</div>
		<div class="fl" style="margin-top:20px;">
			<div class="fl region">
				<p class="fl">
					<span style="width:6em;text-align:right;">地区：</span>
				</p>
				<div class="fl select" >
					<div class="tik_position">
						<b class="ml5">省/直辖市</b>
						<p class="position01"><i class="bjt10"></i></p>
					</div>
					<ul class="fl list"  style="display: none;" id="region1_edit">
						<select style="display:none;" name="model.region1_Id"  value="">
							<option value="0">省/直辖市</option>
							<c:forEach items="${region1list}" var="region1">
								<option value="${region1.prop_val_id}" <c:if test="${region1.prop_val_id==item.id}">selected</c:if>>${region1.name}</option>
							</c:forEach>
						</select>
						<li>省/直辖市</li>
						<c:forEach items="${region1list}" var="region1">
							<li>${region1.name}</li>
						</c:forEach>
					</ul>
				</div>
				<div class="fl select" style="margin:0 10px 0 10px">
					<div class="tik_position">
						<b class="ml5">市</b>
						<p class="position01"><i class="bjt10"></i></p>
					</div>
					<ul class="fl list" id="region2_edit" style="display: none;">
					<select style="display:none"  name="model.region2_Id">
						<option value=0>市</option>
					</select>
						<li>市</li>
					</ul>
				</div>	
			</div>
			<div class="clear"></div>
			<div id="citySpan">
				<p class="fl">
					<span style="width:6em;text-align:right;">地区：</span>
				</p>
				<input id="input_dq" value="地区" disabled="disabled"/>
				<input id="input_city" value="市" disabled="disabled"/>
			</div>
			<div class="clear"></div>
			 <%--<div class="Manageunit" style=""> --%>
				<%--<p class="fl" >--%>
					<%--<span style="width:6em;text-align:right;"><font color="red">*</font>管理单位：</span>--%>
					<%--<input type="text" class="fl att_bianji01" id="rsHospital"  placeholder="请输入正确的医院名称">--%>

				<%----%>
					<%--<input type="hidden" id="hospital_Id" name="model.hospital_Id"/>--%>
				<%--</p>--%>
				<%-- <div class="fl" style="margin-right:10px;">
					<div class="tik_position">
						<b class="ml5">请选择</b>
						<p class="position01"><i class="bjt10"></i></p>
					</div>
					<ul class="fl list" id="hospital_id" >
						<select name="model.hospital_Id" id = "hospitalId" style="display:none;">
							<option value=0>请选择</option>
							<c:forEach items = "${hosArry}" var = "hos">
								<option value="${hos.id}">${hos.name}</option>
							</c:forEach>
						</select>
						<li>请选择</li>
						<c:forEach items = "${hosArry}" var = "hos">
								<li>${hos.name}</li>
						</c:forEach>
					</ul>
				</div>	 --%>			
			<%--</div>--%>
		</div>
	
		<div class="fl" style="width:70%;margin-top:20px">
			<div class="fl" style="width:45%">
				<p class="fl" style="width:100%">
					<span style="width:6em;text-align:right;"><font color="red">*</font>联系人：</span>
					<input type="text"  name="model.contact" id="contact"/>
				</p>
			</div>
			<div class="fl" style="width:55%">
				<p class="fl" style="margin-left:4px;margin-bottom:20px;">
					<span style="width:6em;text-align:right;">联系电话：</span>
					<input onkeyup="this.value= this.value.replace(/[^\d]/g, '');" type="text" value="" name="model.phone_Number" id="phone_Number"/>
				</p>
			</div>
		</div>
		<div class="fl" style="margin-top:4px;width:70%;">
			<p class="fl"><span style="width:6em;text-align:right;">机构简介：</span>
				<textarea name="model.description" id="description" style="width:500px;" rows="5"></textarea></p> 
			<input type="hidden" name="model.id" id="id" value="">
			
			<div class="clear" style="height:20px"></div>
			<p class="fl" style="width:100%">
				<span style="width:6em;text-align:right;"><font color="red">*</font>Logo：</span>
				<input type="file" name="matFile" id="matFile" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp">
				<input type="hidden" name="logoPath" id="logoPath">
				
			</p>
		</div>
		<div class="clear" style="height:20px"></div>
		<div class="fl" style="margin-top:4px;width:70%;">
			<p class="fl" style="width:100%">
				<span style="width:6em;text-align:right;"><font color="red">*</font>机构图片：</span>
				<input type="file" name="matFile1" id="matFile1" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp">
				<input type="hidden" name="orgPath" id="orgPath">
			</p>
		</div>

	</div>
</form>
</div>
<script type="text/javascript">
$(function(){
    $("#citySpan").hide() ;
//select menu
	var CDCode = window.localStorage.getItem("CDCode");
	var code = CDCode.split("-");
	var menuindex = code[0];
	var submenuindex = code[1];
	var submenu = "lc_left0" + menuindex;
	$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
	$('.'+submenu+'').show();
	$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
//select region
//	$('.Manageunit').hide();
	selectInit();
	var win_w = "1000px";
	var win_h = "580px";
	var cont = $('#layerContent').html();
	$('#layerContent').remove();
	//添加机构
	$('#add_btn').click(function() {
		var flagP = true;
		layer.open({
			type: 1,
			title: "添加机构",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, win_h], //宽高
			content: cont,
			closeBtn: 1,
			btn: ['保存', '取消'],
			yes: function (index, layero){
				if(flagP){
					flagP = false;		
					var formvalue = $('#fms').serialize();
				 	if($('#name').val() == ""){
						alert("请输入机构名称！");
						return;
					}else if($('#accountName').val() == ""){
						alert("请输入机构账号！");
						return;
					}else if($('#contact').val() == ""){
						alert("请输入联系人！");
						return;
					}else if($('#matFile').val() == ""){
						alert("请选择Logo！");
						return;
					}else if($('#matFile1').val() == ""){
						alert("请选择机构图片！");
						return;
					}else if($('#matFile').val()==$('#matFile1').val()){
						alert("Logo与机构图片名称相同！");
						return;
					}
	//				else if ($('#type option:selected').text() == '医疗机构' && $("#rsHospital").val().trim() == '') {
	//					//如果机构类型是医疗机构时候，那么管理单位是必填项
	//					alert("请输入管理单位");
	//					return;
	//				}
					else{
					    duplicateTest($('#id').val(),$('#name').val(),$('#accountName').val(),0);	//0表示添加				   
			        }
		        }
	 		},
			success: function(layerb, index){
				flagP = true;
				selectInit();
				selectChange();
//				getHospitalNamesList();
			},
			btn2: function (index, layero) {
				$("#rsHospital").val("");
				layer.close(index);
			},
		});
	});
	 //编辑机构
	$(".edit_btn").click(function() {
		layer.open({
			type: 1,
			title: "编辑机构",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, win_h], //宽高
			content: cont,
			closeBtn: 1,
			btn: ['保存', '取消'],
			yes: function (index, layero) {
				var formvalue = $('#fms').serialize();
				console.log("formvalue="+formvalue);
				var oldAccountName=$("#oldAccountName").val();
				console.log("oldAccountName="+oldAccountName);

			 	if($('#name').val() == ""){
					alert("请输入机构名称！");
					return;
				}else if($('#accountName').val() == ""){
					alert("请输入机构账号！");
					return;
				}else if($('#accountName').val()!=oldAccountName){
                    alert("机构账号不允许修改！");
                    $('#accountName').val(oldAccountName)
                    return;
                }else if($('#contact').val() == ""){
					alert("请输入联系人！");
					return;
				}else if(($('#matFile').val() != "" && $('#matFile1').val() == "") ||($('#matFile').val() == "" && $('#matFile1').val() != "")){
					alert("请选择Logo、机构图片！");
					return;
				}
//				else if ($('#type option:selected').text() == '医疗机构' && $("#rsHospital").val().trim() == '') {
//					//如果机构类型是医疗机构时候，那么管理单位是必填项
//					alert("请输入管理单位");
//					return;
//				}
				else{
				    duplicateTest($('#id').val(),$('#name').val(),$('#accountName').val(),1);	//1表示修改			   
		        }
			},
			success: function(layerb, index){
				selectInit();
				selectChange();
//				getHospitalNamesList();
			},
			btn2: function (index, layero) {
				$("#rsHospital").val("");
				layer.close(index);
			},
		});
	});
});

function selectInit(){
// select view
	$('.select').click(function(){
		$(".list").css("display","none");
		$(this).find('ul').show();
		return false;
	});
	$('.list li').click(function(){
		var str=$(this).text();
		$(this).parent().parent().find('div').find('b').text(str);
		$(this).parent().find('option').prop('selected', '');
		$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
		$('.list').slideUp(50);
	});
	
	$('.list option:selected').each(function(){
		var str=$(this).text();
		$(this).parent().parent().parent().find('b').text(str);
	});
	$(document).click(function(){
		$('.list').hide('fast');
	});
	
	$('#region1 li').click(function(){
		//alert("test");
		var regionid = $(this).parent().find('option').eq($(this).index()-1).val();
		//alert(regionid);
		if(regionid==0){
			updateSelList($('#region2_edit'), "市", null);
			return;
		}
		var url = "${ctx}/propManage/getPropListAjax.do?id=" + regionid;
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
			   		updateSelList($('#region2'), "市", result);
			   };
		    }
		});
	});
}
//机构类型选择
function selectChange(){
/////edit
//	if($('#type').find('select').val() == 1)
//		$('.Manageunit').show();
//	else $('.Manageunit').hide();
/////add	
	$('#type li').click(function(){
		if($(this).eq(0).text() == "医疗机构")
		{
//			$('.Manageunit').show();
			$('.region').hide();
            $("#citySpan").show() ;
			$('#region1_edit').val(0);
			$('#region2_edit').val(0);
		}
		else
		{
//			$('.Manageunit').hide();
            $("#citySpan").hide() ;
			$('#rsHospital').val('');
			$('.region').show();
		}
	});
	$('#region1_edit li').click(function(){
		//alert("test");
		var regionid = $(this).parent().find('option').eq($(this).index()-1).val();
		//alert(regionid);
		if(regionid==0){
			updateSelList($('#region2_edit'), "市", null);
			return;
		}
		var url = "${ctx}/propManage/getPropListAjax.do?id=" + regionid;
		
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
			   		updateSelList($('#region2_edit'), "市", result);
			   };
		    }
		});
	});
}

function getCity(regionid,id){
	if(regionid==0){
		updateSelList($('#region2_edit'), "市", null);
		return;
	}
		var url = "${ctx}/propManage/getPropListAjax.do?id=" + regionid;
		
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
			   		updateSelList2($('#region2_edit'), "市", result,id);
			   };
		    }
		});
	}

function infoView(id){
	var reg=new RegExp("amp;","g"); //准备替换(amp;)
	var url = "${ctx}/system/peixunOrglist.do?method=view";	
	var params = "&id="+id;
	$('#id').val(id);
	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'json',
	    success: function(result){
		   if(result != ''){
		   	  // 2017/01/12, Change by lee
		   	  //viewHospital(result.hosarry, result.count);
		   	var item = result.item;
		      $('#name').val(item.name);
		      $('#type').find('select').val(item.type);
		      //$('#level').find('select').val(item.level);
		      $('#region1_edit').find('select').val(item.region1_Id);
		      getCity(item.region1_Id,item.region2_Id);
// 		      $('#region2_edit').find('select').val(item.region2_Id);
		      $('#contact').val(item.contact);
		      $('#accountName').val(item.accountName).attr("disabled","disabled");
		      $('#oldAccountName').val(item.accountName);
		      /* $('#accountName').prop("readonly", "readonly"); */
		      $('#accountId').val(item.accountId);
		      $('#hospital_id').find('select').val(item.hospital_Id);
		      $('#phone_Number').val(item.phone_Number);
		      $('#description').val(item.description);
		      $('#modelpath').val(item.filePath);
		      $('#logoPath').val(item.filePath);
		      $('#orgPath').val(item.photoPath);
		      //var path = $('#img').attr("src");
		      //path +='${ctx}'+'/'+ item.filePath;
		      $('#img').attr("src", item.filePath.replace(reg,""));
		      $('#photopath').val(item.photoPath);
		      //path = $('#photo').attr("src");
		      //path += '${ctx}'+'/'+item.photoPath;
		     
		      $('#photo').attr("src", item.photoPath.replace(reg,""));
		      //$('#photo').attr("src", path);
		   	  // 2017/01/12, Add by lee
		   	  // Show Hospital Name if type is 医疗机构
		   	  if(item.type == 4) {
		   	  	var hospitalItem = result.hospital;
//		   	  	$('#rsHospital').val(hospitalItem.name);
//		   	  	$('#hospital_Id').val(hospitalItem.id);
//		   	  	$('.Manageunit').show();
//		   	  	$('.region').hide();
		   	  }
			  selectInit();
		   }else{ alert('检查内容失败!');}
	    },
	});
 }
 function duplicateTest(id,param,accountName,type){
	var typee = type;
	var url = "${ctx}/system/peixunOrglist.do?method=test&type="+typee;	
	var params = "&param="+param+"&accountName="+accountName+"&id="+id;
 	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'text',
	    success: function(result){
	       if(result=="nameDuplicate"){
	             alert("机构名称重复！");
	             return;
	       }
	       else if(result=="accountNameDuplicate"){
	        	 alert("机构账号重复！");
	        	 return;
	       }
	       else if(result=="updatestr"){
	    	   	 logoUpload(typee);
	       }
	       else{
	    	     //执行表单提交
	    	     logoUpload(typee);
	    	     
	 	   	     //$(fms).submit();
				 //alert("添加成功!");
	 	   	 }
	 	 	   
		  }
	});
 }
 
 //封装上传Logo图片方法
 function logoUpload(type){
	if(type==1 && $('#matFile').val() == "" && $('#matFile1').val() == ""){
		var url = "${ctx}/system/peixunOrglist.do?method=update";
		 $.ajax({
				url:url,
				data	:$("#fms").serialize(),
				type	:'post',
				dataType:'json',
				success: function(data){
					var result = eval(data);
					if(result.message=='success'){
						if(type==0){
							alert("保存成功！");	
						}else{
							alert("修改成功！");
						}	
						location.href="${ctx}/system/peixunOrglist.do?method=list";
					}else{
						alert("保存失败!");
					}
				}
		});	
		return;
	}
	 
	var uploadResult = ''; 
	//上传logo图片
	/* var form=document.getElementById("fms");
    var formData=new FormData(form);
    var oReq = new XMLHttpRequest();
    oReq.onreadystatechange=function(){
       if(oReq.readyState==4){
         if(oReq.status==200){
             var json=JSON.parse(oReq.responseText,function(k,v){
            	 if(!(v instanceof Object)){
            		 if(v!="Fail"){
            			 //设置logo路径
            			 $("#logoPath").val(v);
            			 $("#logo").attr("src",v);
            			//上传封面图片
            			 var form1=document.getElementById("fms");
            			 var formData1=new FormData(form1);
            			 var oReq1 = new XMLHttpRequest();
            			    oReq1.onreadystatechange=function(){
            			       if(oReq1.readyState==4){
            			         if(oReq1.status==200){
            			             var json=JSON.parse(oReq1.responseText,function(k,v){
            			            	 if(!(v instanceof Object)){
            			            		 if(v!="Fail"){
            			            			 //设置封面路径
            			            			 $("#orgPath").val(v);
            			            			 $("#img").attr("src",v);
            			            			 var urll="";
            			            			 if(type==0){
            			            				 urll='${ctx}/system/peixunOrglist.do?method=add';
            			            			 }else{
            			            				 urll='${ctx}/system/peixunOrglist.do?method=update';
            			            			 }
            			            			 $.ajax({
            			         					url:urll,
            			         					data	:$("#fms").serialize(),
            			         					type	:'post',
            			         					dataType:'json',
            			         					success: function(data){
            			         						var result = eval(data);
            			         						if(result.message=='success'){
            			         							if(type==0){
	            			         							alert("保存成功！");	
            			         							}else{
            			         								alert("修改成功！");
            			         							}	
            			         							location.href="${ctx}/system/peixunOrglist.do?method=list";
            			         						}else{
            			         							alert("保存失败!");
            			         						}
            			         					}
            			         				});	
            			            		 }
            			            	 }
            			             });
            			         }
            			       }
            			     }
            			     oReq1.open("POST", "${ctx}/file/fileUpload2.do");
            			     oReq1.send(formData1); 
            			    
            		 }
            	 }
             });
         }
       }
     }
     oReq.open("POST", "${ctx}/file/fileUpload.do");
     oReq.send(formData); */
     if ($('#matFile').val() != "" && !fileUploadValid("matFile",2)) {
    	 return ;
     }
     if ($('#matFile1').val() != "" && !fileUploadValid("matFile1",2)) {
    	 return ;
     }
     $("#fms").ajaxSubmit({
					type: 'post',
					url: '${ctx}/file/fileUpload.do',
					success: function(data) {
						try{
							var v = JSON.parse(data).message;
							
							 if(v!="Fail"){
		            			 //设置logo路径
		            			 $("#logoPath").val(v);
		            			 $("#logo").attr("src",v);
		            			//上传封面图片
		            			 $("#fms").ajaxSubmit({
									type: 'post',
									url: '${ctx}/file/fileUpload2.do',
									success: function(data1) {
										try{
											var v1 = JSON.parse(data1).message;
											
											 if(v1!="Fail"){
            			            			 //设置封面路径
            			            			 $("#orgPath").val(v1);
            			            			 $("#img").attr("src",v1);
            			            			 var urll="";
            			            			 if(type==0){
            			            				 urll='${ctx}/system/peixunOrglist.do?method=add';
            			            			 }else{
            			            				 urll='${ctx}/system/peixunOrglist.do?method=update';
            			            			 }
            			            			 $.ajax({
            			         					url:urll,
            			         					data	:$("#fms").serialize(),
            			         					type	:'post',
            			         					dataType:'json',
            			         					success: function(data){
            			         						var result = eval(data);
            			         						if(result.message=='success'){
            			         							if(type==0){
	            			         							alert("保存成功！");	
            			         							}else{
            			         								alert("修改成功！");
            			         							}	
            			         							location.href="${ctx}/system/peixunOrglist.do?method=list";
            			         						}else{
            			         							alert("保存失败!");
            			         						}
            			         					}
            			         				});	
            			            		 }
							            	 
										}catch(e){
											alert("上传服务器异常,请稍后重试...");
										}
									},
									error: function(data){
										alert("上传服务器异常,请稍后重试...");
									}
								});
		            			    
		            		 }
			            	 
						}catch(e){
							alert("上传服务器异常,请稍后重试...");
						}
					},
					error: function(data){
						alert("上传服务器异常,请稍后重试...");
					}
				});
     
     return uploadResult;
 }
 
 //封装上传封面图片方法
 function imgUpload(){
	var uploadResult = ''; 
	//上传封面图片
	/*var form=document.getElementById("fms");
    var formData=new FormData(form);
    var oReq = new XMLHttpRequest();
    oReq.onreadystatechange=function(){
       if(oReq.readyState==4){
         if(oReq.status==200){
             var json=JSON.parse(oReq.responseText,function(k,v){
            	 if(!(v instanceof Object)){
            		 if(v!="Fail"){
            			 //设置封面路径
            			 $("#orgPath").val(v);
            			 $("#img").attr("src",v);
            			 uploadResult = 'success';
            			    
            		 }else{
            			 uploadResult = 'fail';
            		 }
            	 }
             });
         }
       }
     }
     oReq.open("POST", "${ctx}/file/fileUpload2.do");
     oReq.send(formData); */
     
     $("#fms").ajaxSubmit({
					type: 'post',
					url: '${ctx}/file/fileUpload.do',
					success: function(data) {
						try{
							var v = JSON.parse(data).message;
							
							 if(v!="Fail"){
		            			 //设置封面路径
		            			 $("#orgPath").val(v);
		            			 $("#img").attr("src",v);
		            			 uploadResult = 'success';
		            			    
		            		 }else{
		            			 uploadResult = 'fail';
		            		 }
			            	 
						}catch(e){
							alert("上传服务器异常,请稍后重试...");
						}
					},
					error: function(data){
						alert("上传服务器异常,请稍后重试...");
					}
				});
     
     return uploadResult;
 }

function viewHospital(objList, objCount){
	  var hospital = objList;
	  var str = "<option value='0'>全部</option>";
	  var str1 = "<li>全部</li>";
  	  for(i=0; i< objCount;i++)
  	  {
  		  if (hospital[i] != null){
		  str += "<option value='"+hospital[i].id+"'>"+hospital[i].name+"</option>";
		  str1+= "<li>"+hospital[i].name+"</li>";
  	     }
  	  }
  	  $('#hospital_id').find('select').html(str);
	  $('#hospital_id').find('li').remove();
	  $('#hospital_id').find('select').after(str1);
	  
	  selectInit();
}
function updateSelList(obj, defval, result){
	
	var str = "<option value=''>"+defval+"</option>";
	var str1 = "<li>"+defval+"</li>";
	if (result!=null)
	$(result.list).each(function(key, value){
		str += "<option value='"+value.prop_val_id+"'>"+value.name+"</option>";
		str1+= "<li>"+value.name+"</li>";
	});
	$(obj).find('select').html(str);
	$(obj).find('li').remove();
	$(obj).find('select').after(str1);
	selectInit();                                   
}
function updateSelList2(obj, defval, result,id){
	
	var str = "<option value=''>"+defval+"</option>";
	var str1 = "<li>"+defval+"</li>";
	if (result!=null)
	$(result.list).each(function(key, value){
		if(value.prop_val_id==id){
			str += "<option value='"+value.prop_val_id+"' selected='selected'>"+value.name+"</option>";
		}else{
			str += "<option value='"+value.prop_val_id+"' >"+value.name+"</option>";
		}
		str1+= "<li>"+value.name+"</li>";
	});
	$(obj).find('select').html(str);
	$(obj).find('li').remove();
	$(obj).find('select').after(str1);
	selectInit();                                   
}
 
function updateState(id,state){
	if(state == 1){
		if(confirm("您确定停用该机构账号？停用后，机构无法使用该账号登录系统!"))
		{
			var url = "${ctx}/system/peixunOrglist.do?method=setState";
			$.ajax({
				url:url,
				data:"&id="+id,
				type:'POST',
				dataType:'text',
				success: function(result){
					if(result != ""){
						alert("操作成功！");	
						window.location.reload(true);
					}
					else{ alert("操作失败!");}
					window.location.reload(true);
				}
			});	
		}
	}
	else{
		var url = "${ctx}/system/peixunOrglist.do?method=setState";
			$.ajax({
				url:url,
				data:"&id="+id,
				type:'POST',
				dataType:'text',
				success: function(result){
					if(result != ""){
						alert("操作成功！");	
						window.location.reload(true);
					}
					else{ alert("操作失败!");}
					window.location.reload(true);
				}
			});
	}	
}
//监控Input输入框变化
// Firefox, Google Chrome, Opera, Safari, Internet Explorer from version 9
function OnInput (event) {
    var han = /^[\u4e00-\u9fa5]+$/;
    var inputvalue=event.target.value;
    if (han.test(inputvalue)) {
        getHospitalList(inputvalue);
        return false;
    };

}
// Internet Explorer
function OnPropChanged (event) {
    if (event.propertyName.toLowerCase () == "value") {
        var han = /^[\u4e00-\u9fa5]+$/;
        var inputvalue=event.target.value;
        if (han.test(inputvalue)) {
            getHospitalList(inputvalue);
            return false;
        };
    }
}

/**
 * 具体的执行方法
 * @param inputvalue
 */
function getHospitalList(inputvalue) {
    console.log("inputvalue="+inputvalue);
    var name=inputvalue;
    //只有是医疗机构才提供
    if ($('#type option:selected').text() == '医疗机构') {
        var  result;
        if(name.length>=4){
            var url = "${ctx}/propManage/getHospitalAjax.do";
            $.ajax({
                url:url,
                type: 'POST',
                data:{organName:name},
                dataType: 'json',
                success: function(result){
                    $("#name").bigAutocomplete({
                        width:198,
                        data:result,
                        callback:function(data){
                            $('.region').hide();
                            $("#input_dq").val(data.provName);
                            $("#input_city").val(data.cityName);
                            $("#region1_edit").find('select').val(data.provID);
//                            $("#region2_edit").find('select').val(data.cityID);
                            $("#citySpan").show() ;
//                            console.log("ID"+data.id+", title="+data.title+", provID="+data.provID+", provName="+data.provName+", cityID="+data.cityID+", cityName="+data.cityName);
                        }
                    });
                }
            });
        }
    }

}
// 2017/01/12, Add by lee
// For show list of hospital Names when change text of input tag
function getHospitalNamesList() {
    var name=$("#name").val();
    console.log("getHospitalNamesList="+name+",length="+name.length+",Type="+$('#type option:selected').text());
	//只有是医疗机构才提供
    if ($('#type option:selected').text() == '医疗机构') {
        var  result;
        if(name.length>=2){
            var url = "${ctx}/propManage/getHospitalAjax.do";
            $.ajax({
                url:url,
                type: 'POST',
                data:{organName:name},
                dataType: 'json',
                success: function(result){
                    $("#name").bigAutocomplete({
                        width:198,
                        data:result,
                        callback:function(data){
                            $('.region').hide();
                            $("#input_dq").val(data.provName);
                            $("#input_city").val(data.cityName);
                            $("#citySpan").show() ;
                            console.log("ID"+data.id+", title="+data.title+", provName="+data.provName+", cityName="+data.cityName);
                        }
                    });
                }
            });
        }
    }

}
//点击查询后，如果查询条件有省份，那么市就不能选择了；所以如果省不为空，设置一次市区
function initCity(){
	//该页面有两个地方是一模一样的省市设置，本次修改的是第一个
	var selectProvince = $("select[name='model.region1_Id']");
	var regionid = $(selectProvince[0]).attr("value");
	if(regionid==0){
		updateSelList($('#region2_edit'), "市", null);
		return;
	}
	var url = "${ctx}/propManage/getPropListAjax.do?id=" + regionid;
	var cityId = '${item.region2_Id}';
	$.ajax({
	    url:url,
	    type: 'POST',
	    dataType: 'json',
	    success: function(result){
		   if(result != ''){
		   	updateSelList($('#region2'), "市", result);
		  	//select填充后，需要选中以前的值
			$(result.list).each(function(key, value){
				if(cityId == value.prop_val_id){
					$("#city").html(value.name);
				}
				
			});
		   }
	    }
	});
}
initCity();
</script>
</body>
</html>