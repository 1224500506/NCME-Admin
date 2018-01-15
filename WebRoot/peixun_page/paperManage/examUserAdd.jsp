<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/peixun_page/css/reset.css" />
	<link rel="stylesheet" href="${ctx}/peixun_page/css/index.css" />
	<link rel="stylesheet" href="${ctx}/peixun_page/css/pickadate/default.css" />
	<link rel="stylesheet" href="${ctx}/peixun_page/css/pickadate/default.date.css" />
	<link rel="stylesheet" href="${ctx}/peixun_page/css/fontawesome/css/font-awesome.css" />
	<script type="text/javascript" src="${ctx}/peixun_page/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/main.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/picker.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/picker.date.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/legacy.js"></script>
<title>培训后台</title>
</head>
<body>
<!-- 查询条件 -->
<div class="center">
<form action="${ctx}/examManage/examUserDetail.do?method=list&model.userType=1" method="post" id="queryForm">
 	<div class="tiaojian" style="min-height:40px;">
		<p class="fl" style="margin:0 20px 10px 0">
			<span style="width:5em;text-align:right;">姓名：</span>
			<input type="text" name="model.realName" value="${item.realName}" />
		</p>
		<p class="fl" style="margin:0 20px 10px 0">
			<span style="width:5em;text-align:right;">单位名称：</span>
			<input type="text" name="model.workUnit" value="${item.workUnit}" />
			<%--<input type="hidden" name="searchUnitIds" id="searchUnitIds" value="${searchUnitIds}"/>
			<div class="duouan" id="searchUnitIds01">${searchUnitNames}</div>
			<input type = "hidden" name = "searchUnitNames" id = "searchUnitNames" value = "${searchUnitNames}"/> --%>
		</p>
<!--		<p class="fl" style="margin:0 0 10px 0;">
			<span style="width:5em;text-align:right;">单位级别：</span>
		</p>
 		<div class="fl select"  style="margin:0 20px 10px 0">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display: none;">
			<select name="" style="display:none;">
				<option value="">全部</option>
				<option value="三甲">三甲</option>
				<option value="三乙">三乙</option>
				<option value="二甲">二甲</option>
				<option value="二乙">二乙</option>
			</select>
				<li>全部</li>
				<li>三甲</li>
				<li>三乙</li>
				<li>二甲</li>
				<li>二乙</li>
			</ul>
		</div>
 -->		<p class="fl">
			<span style="width:5em;text-align:right;">科室：</span>
			<input type="hidden" id="propIds" name="propIds" value="${propIds}"/>
			<input type="hidden" id="propNames" name="propNames" value="${propNames}"/>
			<div class="duouan" id="propNames01">${propNames}</div>
			<%-- <input type="text" name="model.deptName" value="${item.deptName}" /> --%>
		</p>
		<div class="clear"></div>
		<p class="fl">
<!-- 			<span style="width:5em;text-align:right;">工作类型：</span>
		</p>
		<div class="fl select" style="margin:0 20px 10px 0;">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display: none;">
			<select name="" style="display:none;">
				<option value="">全部</option>
				<option value="医师">医师</option>
				<option value="护师">护师</option>
				<option value="药师">药师</option>
				<option value="技师">技师</option>
			</select>
				<li>全部</li>
				<li>医师</li>
				<li>护师</li>
				<li>药师</li>
				<li>技师</li>
			</ul>
		</div>
		<p class="fl" style="margin:0 0px 20px 0"> -->
			<span style="width:5em;text-align:right;">职称：</span>
			<input type="hidden" id="jobIds" name="jobIds" value="${jobIds}"/>
			<input type="hidden" id="jobNames" name="jobNames" value="${jobNames}"/>
			<div class="duouan" id="jobNames01">${jobNames}</div>
		</p>
		<%-- <div class="fl select"  style="margin:0 20px 10px 0">
			<div class="tik_position">
				<b class="ml5">职称</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display: none;">
			<select name="model.profTitle" style="display:none;">
				<option value="">全部</option>
				<c:forEach items="${joblist}" var="job">
					<option value="${job.name}" <c:if test="${job.name eq item.profTitle}">selected</c:if>>${job.name}</option>
				</c:forEach>
			</select>
				<li>全部</li>
				<c:forEach items="${joblist}" var="job">
					<li>${job.name}</li>
				</c:forEach>
			</ul>
		</div> --%>
		<p class="fl ml20" >
			<span style="width:5em;text-align:right;">状态：</span>
		</p>
		<div class="fl select"  style="margin:0 20px 10px 0">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display: none;">
			<select name="model.status" style="display:none;">
				<option value="">全部</option>
				<option value="1" <c:if test="${item.status==1}">selected</c:if>>正常</option>
				<option value="2" <c:if test="${item.status==2}">selected</c:if>>停用</option>
			</select>
				<li>全部</li>
				<li>正常</li>
				<li>停用</li>
			</ul>
		</div>
		<div class="clear"></div>
		<p class="fl" style="margin:0 0 10px 0">
			<span style="width:5em;text-align:right;">地区：</span>
		</p>
		<div class="fl select" style="margin:0 10px 0 0">
			<div class="tik_position">
				<b class="ml5">省/直辖市</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" id="region1" style="display: none;">
			<select name="model.userConfig.userProvinceId" style="display:none;">
				<option value="0">省/直辖市</option>
				<c:forEach items="${region1list}" var="region1">
					<option value="${region1.prop_val_id}" <c:if test="${region1.prop_val_id==item.userConfig.userProvinceId}">selected</c:if>>${region1.name}</option>
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
				<b class="ml5">市</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" id="region2" style="display: none;">
			<select name="model.userConfig.userCityId" style="display:none;">
			<option value="0">市</option>
				<c:forEach items="${region2List}" var="region2">
					<option value="${region2.prop_val_id}" <c:if test="${region2.prop_val_id==item.userConfig.userCityId}">selected</c:if>>${region2.name}</option>
				</c:forEach>			
			</select>
				<li>市</li>
				
				<c:forEach items="${region2List}" var="region1">
					<li>${region2.name}</li>
				</c:forEach>
			</ul>
		</div>
		<div class="fl select" style="margin:0 20px 10px 0">
			<div class="tik_position">
				<b class="ml5">区</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" id="region3" style="display: none;">
			<select name="model.userConfig.userCountiesId" style="display:none;">
				<option value="0">区</option>
					<c:forEach items="${region3List}" var="region3">
					<option value="${region3.prop_val_id}" <c:if test="${region3.prop_val_id==item.userConfig.userCountiesId}">selected</c:if>>${region3.name}</option>
				</c:forEach>
			</select>
				<li>区</li>
					<c:forEach items="${region3List}" var="region1">
					<li>${region3.name}</li>
				</c:forEach>
			</ul>
		</div>
		<p class="fl cas_anniu">
			<a href="#" class="fl chaxun" style="width:70px;margin:0 20px 10px 0;">查询</a>
		</p>
		<div class="fr cas_anniu">
			<a href="#" id = "addUser-link1" class="fl exp1_tianjia01" style="width:80px;">添加考生</a>
			<a href="javascript:window.close();" class="fl" style="width:80px;margin-left:10px;">返回</a>
		</div>
	</div>
</form>
</div>

<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
		<!-- <div class="mt5 kit1_tiaojia">
			<div class="fr cas_anniu">
				<a href="kaoshi01.html" class="fl" style="width:80px;">新增</a>
			</div>
		</div>
		<div class="clear"></div> -->

		<display:table requestURI="${ctx}/examManage/examUserDetail.do?method=list&model.userType=1" 
				 id="systemUser" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}"  name = "page"
				 class="mt10 table" keepStatus="true">
				 <display:column
					title="<input type='checkbox' id='checkall' name='checkall' value='all' onclick='checkAll();' />"
					style="width:3%;text-align:center">
					 <input type="checkbox" name="ids" id="ids" onclick="checkInfo(this)"
					value="${systemUser.userId}+${systemUser.realName}_${systemUser.accountName}_${systemUser.workUnit}_${systemUser.deptName}_${systemUser.userType}_${systemUser.mobilPhone}"/>
				</display:column>
			<display:column title="序号" style="width:5%;">
				${systemUser_rowNum}
			</display:column>
			<display:column  style="width:10%;" title="学员账号" property="accountName" /> 
			<display:column  style="width:8%;" title="学员姓名" property="realName" /> 
			<display:column  style="width:15%;" title="工作单位" property="workUnit" /> 
			<display:column  style="width:15%;" title="地区" >
				${systemUser.userConfig.userProvinceName}/${systemUser.userConfig.userCityName}/${systemUser.userConfig.userCountiesName}
			</display:column>
			<display:column  style="width:10%;" title="科室" property="deptName" /> 
			<display:column  style="width:10%;" title="职称" property="profTitle" /> 
			<display:column  style="width:10%;" title="联系电话" property="mobilPhone" /> 
			<display:column  style="width:5%;" title="账号状态" >
				<c:if test="${systemUser.account_status==1}">正常</c:if>
				<c:if test="${systemUser.account_status==2}">停用</c:if>
			</display:column> 
			<display:column  style="width:8%;" title="操作" >
				<a class="edit_btn" href="javascript:infoView(${systemUser.userId});" >详情</a> 
<%-- 				<c:if test="${systemUser.status==1}">
					<a href="javascript:setState(${systemUser.userId},2);" >停用</a> 
				</c:if>
				<c:if test="${systemUser.status==2}">
					<a href="javascript:setState(${systemUser.userId},1);" >正常</a> 
				</c:if>
 --%>			</display:column>
		</display:table>
		
	</div>
</div>


<div class="toumingdu att_make01" style="display:none;z-index:10000;">
	<div class="tk_center09" style="margin:7% auto;">
		<div class="tik_biaoti">
			<span class="fl tit_biaoti" style="margin-left:290px;color:#fff;"></span>
			<i class="fr bjt_kt"></i>
		</div>
		<div class="clear"></div>
		<div class="xianshikuang">
			<div class="mt15 xs_xuangze">
				<div class="fl xs_kuangcode" style="display:none;"></div>
				<div class="fl xs_currentid" style="display:none;"></div>
				<div class="fl xs_selectlvl" style="display:none;"></div>
				<div class="fl xs_checklvl" style="display:none;"></div>
				<div class="fl xs_kuang"></div>
				<div class="fr cas_anniu_4" style="margin-top:25px;">
					<a href="javascript:selectProp();" class="fr">确定</a>
				</div>
				<div class="clear"></div>
			</div>
			<div class="xs_biaoti">
				<div class="fl xs_er">
					<p class="fl attr_xueke01">一级学科</p>
					<i class="fl xs_bjt01"></i>
					<em class="fl">></em>
				</div>
			</div>
			<div class="fl xs_zhcombo" style="display:none;">
				<select id="zclx_combo" style="margin:8px 13px;height:25px;min-width:120px">
					<option id="" value="0">医师</option>
				</select>
			</div>
			<div class="clear"></div>
			<ul class="xs_ul">
			</ul>
			<div class="clear"></div>
		
		</div>
	</div>
</div>

<!-- layer内容 -->
<div id="layercontent" style="display:none;">
<div class="center">
<form name="editfrm" action="${ctx}/system/systemUserStudent.do?method=save" method="post">
	<input type="hidden" id="userId" name="model.userId" value="0" />
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">证件类型：</span>
		</p>
		<select class="fl select" id="certificateType" name="model.certificateType">
			<option value="1">身份证</option>
			<option value="2">军官证</option>
			<option value="3">港澳台通行证</option>
		</select>

		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">证件号码：</span>
			<input type="text" id="certificateNo" name="model.certificateNo" value="" />
		</p>
		<div class="clear"></div>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">学员账号：</span>
			<input type="text" value="" />
		</p>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">学员姓名：</span>
			<input type="text" id="realName" name="model.realName" value="" />
		</p>
		<div class="clear"></div>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">性别：</span>
		</p>
		<select class="fl select" id="sex" name="model.sex">
			<option value="1">男</option>
			<option value="2">女</option>
		</select>

		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">学历：</span>
		</p>
		<select class="fl select" id="education" name="model.education">
			<option value="1">中专</option>
			<option value="2">大专</option>
			<option value="3">本科</option>
			<option value="4">硕士研究生</option>
			<option value="5">博士研究生女</option>
		</select>
		<div class="clear"></div>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">科室：</span>
		</p>
		<select class="fl select" id="deptName" name="model.deptName">
			<option value="1">中专</option>
			<option value="2">大专</option>
			<option value="3">本科</option>
			<option value="4">硕士研究生</option>
			<option value="5">博士研究生女</option>
		</select>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">感兴趣的学科：</span>
			<input type="text" id="profession" name="model.profession" value="" />
		</p>
		<div class="clear"></div> 
		<p class="fl">
			<span style="width:8em;text-align:right;">职称：</span>
		</p>
		<select class="fl select" id="profTitle" name="model.profTitle">
			<option value="1">中专</option>
			<option value="2">大专</option>
			<option value="3">本科</option>
			<option value="4">硕士研究生</option>
			<option value="5">博士研究生女</option>
		</select>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">手机号码：</span>
			<input type="text" id="mobilPhone" name="model.mobilPhone" value="" />
		</p>
		<div class="clear"></div>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">单位名称：</span>
			<input type="text" id="workUnit" name="model.workUnit"  readonly value="" style="width:640px;" />
			<input type = "hidden" id = "workUnitId" name = "model.work_unit_id" value = "" /> 
		</p>
		<div class="clear"></div><p class="fl" style="margin:0 0 20px 0">
			<span style="width:8em;text-align:right;">地区：</span>
		</p>
		
		<select class="fl select" id="userProvinceId" name="model.userConfig.userProvinceId">
			<option value="1">中专</option>
			<option value="2">大专</option>
			<option value="3">本科</option>
			<option value="4">硕士研究生</option>
			<option value="5">博士研究生女</option>
		</select>
		<select class="fl ml20 select" id="userCityId" name="model.userConfig.userCityId" >
			<option value="1">中专</option>
			<option value="2">大专</option>
			<option value="3">本科</option>
			<option value="4">硕士研究生</option>
			<option value="5">博士研究生女</option>
		</select>
		<select class="fl ml20 select" id="userCountiesId" name="model.userConfig.userCountiesId">
			<option value="1">中专</option>
			<option value="2">大专</option>
			<option value="3">本科</option>
			<option value="4">硕士研究生</option>
			<option value="5">博士研究生女</option>
		</select>
		<div class="clear"></div>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">单位详细地址：</span>
			<input type="text" id="address" name="model.userConfig.address" value="" style="width:640px;" />
		</p>
		<div class="clear" ></div>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">职业医师号</span>
			<input type="text" id="health" value="" style="width:640px;" readonly/>
		</p>	
	</div>
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
	
	$('.queren').click(function(){
		$('.make02').hide();		
	});
	
	$('#propNames01').click(function(){
		initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
		$('.att_make01').show();
	});

	$('#jobNames01').click(function(){
		initPropList("职称", "${ctx}/propManage/getPropListAjax.do", 24, 0, 3, 3, $('#jobIds'), $('#jobNames01'));
		$('.att_make01').show();
	});

	$('#searchUnitIds01').click(function(){
		initPropList("医院", "${ctx}/propManage/getPropListAjax.do", 23, 0, 5, 0, $('#searchUnitIds'),$('#searchUnitIds01'));
		$('.att_make01').show();
	});
	
	//select box
		selectInit();

		$('.chaxun').click(function(){
		
			$('#propNames').val($('#propNames01').text());
			$('#jobNames').val($('#jobNames01').text());
			$('#queryForm').submit();
		});


	var win_w = "1000px";
	var win_h = "700px";
	var add_cont = $('#layercontent').html();
	var host_cont = $('#layercontent').find('.att_make01').html();
	$('#layercontent').remove();
	$(".edit_btn").click(function() {
		layer.open({
			type: 1,
			title: "学员详情",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, win_h], //宽高
			content: add_cont,
			closeBtn: 0,
			btn: ['保存', '取消'],
			yes: function (index, layero) {
				//缩写保存功能
				layer.close(index);
			},
			btn2: function (index, layero) {
				layer.close(index);
			},
			success: function(layerb, index){
				$(".btn1").click(function(){
					layer.close(index);
				});
			}
		});
	});

});

function infoView(id){
	var url = "${ctx}/system/systemUserStudent.do?method=view&rtype=1";
	
	var params = "&id="+id;

	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'json',
	    success: function(result){
		   if(result != ''){
		      var item = result.item;
		      var config = result.config;
		      $('#certificateType').val(item.certificateType);
		      $('#certificateNo').val(item.certificateNo);
		      $('#realName').val(item.realName);
		      $('#sex').val(item.sex);
		      $('#education').val(item.education);
		      $('#deptName').val(item.deptName);
		      $('#profession').val(item.profession);
		      $('#profTitle').val(item.profTitle);
		      $('#mobilPhone').val(item.mobilPhone);
		      $('#workUnit').val(item.workUnit);
		      $('#workUnitId').val(item.work_unit_id);
		      if(config != null){
			      $('#userProvinceId').val(config.userProvinceId);
			      $('#userCityId').val(config.userCityId);
			      $('#userCountiesId').val(config.userCountiesId);
			      $('#address').val(config.address);
		      } 
		      $.ajax({
		      	url:'${ctx}/system/systemUserStudent.do?method=health',
		      	type:'post',
		      	data:'id='+id,
		      	dataType:'json',
		      	success:function(result){
		      		if(result != '')
		      			if(result.result.id == 0) $('#health').val("没有");
		      			else $('#health').val(result.result.id);
		      	}
		      });
		   }else{
		   		alert('检查内容失败!');
		   }
	    }
	});
	
}

function selectInit(){
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
		var regionid = $(this).parent().find('option').eq($(this).index()-1).val();
		
		var url = "${ctx}/propManage/getPropListAjax.do?id=" + regionid;
		
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
			   		updateSelList($('#region2'), "市", result);
			   		updateSelList($('#region3'), "区", null);
			   };
		    }
		});
	});

	$('#region2 li').click(function(){
		var regionid = $(this).parent().find('option').eq($(this).index()-1).val();
		
		var url = "${ctx}/propManage/getPropListAjax.do?id=" + regionid;
		
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
			   		updateSelList($('#region3'), "区", result);
			   };
		    }
		});
	});

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

function setState(id, status){

	if (!confirm("确认？"))return;
	var url = "${ctx}/system/systemUserStudent.do?method=setState";
	
	var params = "&id="+id;
	params+= "&state="+status;

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

	function updatePropList(result){
		
		var str = "";
		var check = eval($('.xs_checklvl').text());
		var select = eval($('.xs_selectlvl').text());
		$(result.list).each(function(key, value){
			str += "<li><div class=''>";
			if (check <= value.type)
				str += '<input class="fl" style="margin-top:5px;" type="checkbox">';

			if ($(kuangcode).prop('id') == "icdIds")
				str += '<p class="fl attr_xueke04"' + ' id="'+ value.id +'"' + '><em class="fl">' + value.name + '</em>';
			else
				str += '<p class="fl attr_xueke04"' + ' id="'+ value.prop_val_id +'"' + '><em class="fl">' + value.name + '</em>';
			
			if (select > value.type){
				str += '<i class="fl ml10 kti_bjt2"></i>';
			}
			str += "</div><div class='clear'></div></li>";
		});
		
		$('.xs_ul').html(str);
		initPropWndProp();
	}
	
	function initPropWndProp(){
		
		$('.attr_xueke04').off('click');
		$('.attr_xueke04').click(function(){
			var curid = eval($('.xs_currentid').text());
			var selid = $(this).prop('id');
			var selname = $(this).text();
			if (selid < 1) return false;
			var a = $(this).find('i').length;
			if (!a) return false;
			
			$('.xs_currentid').text(selid);
			var ms = $('.xs_biaoti .attr_xueke01').length-1;
			$('.xs_er i').hide();
			$('.xs_er em').show();
		//	$('.xs_er').eq(ms).find('i').show();
		//	$('.xs_er').eq(ms).find('em').hide();
			
			var str = '<div class="fl xs_er"><p class="fl attr_xueke01" id=' + curid + '>' + selname + '</p><i class="fl xs_bjt01"></i><em class="fl" style="display:none;">></em></div>';
			$('.xs_er').eq(ms).after(str);
			if(curid == 0)	$('.xs_er').eq(0).remove();
			var url = ajaxurl;
			var params = "id=" +selid + "&mode=getListByType";
			$.ajax({
			    url:url,
			    type: 'POST',
			    data: params,
			    dataType: 'json',
			    success: function(result){
				   if(result != ''){
				   		updatePropList(result);
				   };
			    }
			});
		});
		
		$('.attr_xueke01').off('click');
		$('.attr_xueke01').click(function(){
			var curid = eval($('.xs_currentid').text());
			var selid = $(this).prop('id');
			var selname = $(this).text();
			if (selid == '') return false;
			
			$('.xs_currentid').text(selid);
			var inx = $(this).parent().index();
			if (inx<0)return;
			$('.xs_er').each(function(key, val){
				if (key >= inx)
					$(val).remove();
				if (key == inx-1){
					$(val).find('i').show();
					$(val).find('em').hide();
				}
					
			});

			if (selid == 0) $('.xs_biaoti').html('<div class="fl xs_er"><p class="fl attr_xueke01">'+initsubmenu+'</p><i class="fl xs_bjt01"></i><em class="fl" style="display:none;">></em></div>');

			var url = ajaxurl;
			var params = "id=" +selid + "&mode=getListByType";
			
			$.ajax({
			    url:url,
			    type: 'POST',
			    data: params,
			    dataType: 'json',
			    success: function(result){
				   if(result != ''){
				   		updatePropList(result);
				   };
			    }
			});
		});
		
		$('.xs_ul input[type="checkbox"]').off('click');
		$('.xs_ul input[type="checkbox"]').click(function(){
			var p = $(this).parent().find('.attr_xueke04').eq(0);
			var id = $(p).prop('id');
			var propname = '<em class="delem">';
			if (viewpath) propname+= $('.xs_biaoti').text().trim();
			propname+= $(p).find('em').text() + '</em><br>';
			
			if ($(this).prop('checked')){
				var selstr = $('.xs_kuangcode').text();
				var selarray = selstr.split(",");
				var newarray = new Array();
				$(selarray).each(function(key, val){
					if (val != "") newarray.push(val);
				});
				newarray.push(id);
				$('.xs_kuangcode').text(newarray.toString());

				selstr = $('.xs_kuang').html();
				selarray = selstr.split("<br>");
				var newnarray = new Array();
				$(selarray).each(function(key, val){
					if (val != "") newnarray.push(val);
				});
				newnarray.push(propname);
				$('.xs_kuang').html(newnarray.toString().replace(dotexp,"<br>"));
			}
			else{
				var selstr = $('.xs_kuangcode').text();
				var selarray = selstr.split(",");
				var idx = selarray.indexOf(id);
				var newarray = new Array();
				$(selarray).each(function(key, val){
					if (key != idx) newarray.push(val);
				});
				$('.xs_kuangcode').text(newarray.toString());

				selstr = $('.xs_kuang').html();
				selarray = selstr.split("<br>");
				var newnarray = new Array();
				$(selarray).each(function(key, val){
					if (key != idx) newnarray.push(val);
				});
				$('.xs_kuang').html(newnarray.toString().replace(dotexp,"<br>"));
			}
			
			$('.delem').off('click');
			$('.delem').click(function(){
				delem($(this));
			});
		
		});
		
		//selected item mark checked.
		$('.xs_ul input[type="checkbox"]').each(function(key, val){
			var p = $(this).parent().find('.attr_xueke04').eq(0);
			var id = $(p).prop('id');
			var selstr = $('.xs_kuangcode').text();
			var selarray = selstr.split(",");
			var idx = selarray.indexOf(id);
			
			if (idx>=0) $(this).prop("checked", true);
		});
	}
       	var winObj = parent.opener;
        var userNamesId = winObj.userNamesId;
        var myUserNamesId = winObj.myUserNamesId;
        var myUserNamesId = winObj.myUserNamesId;
       
        function checkInfo(obj){
        	var str = obj.value;
        	 if (obj.checked && obj.disabled == false) {
                 winObj.myUserNamesId.push(str);
             }else{
            	 for (var i = 0; i < myUserNamesId.length; i++) {
     	            if (obj.value == myUserNamesId[i]) {            	
     	                for (var j=i; j<myUserNamesId.length-1; j++){
     	                	myUserNamesId[j] = myUserNamesId[j+1];
     	            	}
     	               myUserNamesId.pop();
     	                break;
     	            }
     	        }
             }
    	}
        $(function () {
            $("input[name='ids']").each(function () {
                var str = this.value;
                var tempArray = str.split("+");
                var temp1 = tempArray[0];
                for (var i in userNamesId) {
                    if (temp1 == myUserNamesId[i]) {
                        this.disabled = "true";
                        this.style.display = 'none';
                    }
                }
            });
          
            var checks = document.getElementsByName("ids");
            for (var i=0; i<checks.length; i++){
    			for(var j in myUserNamesId){
 					if(checks[i].value==myUserNamesId[j]){
 						checks[i].checked = true;
 					}   				
    			}
    		}
    		 $("input[name='ids']").each(function () {
                var str = this.value;
                var tempArray = str.split("+");
                var temp1 = tempArray[0];
                for (var i in userNamesId) {
                    if (temp1 == userNamesId[i]) {
                        this.disabled = "true";
                        this.style.display = 'none';
                    }
                }
            });
           $("#addUser-link1").click(function () {
                 var flag = false;
             //   $("input[name='ids']").each(function (i) {
            	 winObj.userNamesInfo=[];
            	 myUserNamesId=getArray(myUserNamesId)
            	 for(var i in myUserNamesId){
            		 var str = myUserNamesId[i];
            		 var tempArray = str.split("+");
                     var temp1 = tempArray[0];
                     var temp2 = tempArray[1].split("_");
                     temp2.push(temp1);
						//放进所有选择的数组中
                     winObj.userNamesId.push(temp1);
                     winObj.userNamesId1.push(temp1);
                     winObj.userNamesId_back.push(temp1);
                     winObj.userNamesInfo.push(temp2);
                     flag = true;
            	 }
					/* //如果被选中并且选择框没有消失
                    if (this.checked && this.disabled == false) {

                        var str = this.value;
                        var tempArray = str.split("+");
                        var temp1 = tempArray[0];
                        var temp2 = tempArray[1].split("_");
						//放进所有选择的数组中
                        winObj.userNamesId.push(temp1);
                        winObj.userNamesId1.push(temp1);
                        winObj.userNamesId_back.push(temp1);
                        winObj.userNamesInfo.push(temp2);
                        flag = true;
                    } */

            //    });

                if (flag) {
                    winObj.setUser();
                    alert("添加考生成功！");
                    parent.window.close();
                } else {
                    alert("请选择考生！");
                }
            });
        });
        function getArray(a) {
        	 var hash = {},
        	     len = a.length,
        	     result = [];

        	 for (var i = 0; i < len; i++){
        	     if (!hash[a[i]]){
        	         hash[a[i]] = true;
        	         result.push(a[i]);
        	     } 
        	 }
        	 return result;
        }
        function checkAll() {
            var ids = document.getElementsByName("ids");
            var all = document.getElementById("checkall");
            var len = ids.length;
            if (len > 0) {
                var i = 0;
                for (i = 0; i < len; i++){
                	if(ids[i].checked){
                		for (var s = 0; s < myUserNamesId.length; s++) {
             	            if (ids[i].value == myUserNamesId[s]) {            	
             	                for (var j=s; j<myUserNamesId.length-1; j++){
             	                	myUserNamesId[j] = myUserNamesId[j+1];
             	            	}
             	               myUserNamesId.pop();
             	                break;
             	            }
             	        }
                	}
                	ids[i].checked = all.checked;
                	checkInfo(ids[i]);
                }
            }
        }
        ////// select xueke window proc
	/* var kuangcode;
	var kuang;
	var initsubtitle;
	var kuangView; */
	
	var kuangcode;
	var kuang;
	var ajaxurl;
	var initType;
	var initsubmenu;
	var viewpath;
	var dotexp = /,/g;
	var brexp = /<br>/g;
	
	function initPropList(_title,_ajaxurl, _initType, _initId, _selectLvl, _checkLvl, _kuangcode, _kuang){
		$('.tit_biaoti').text(_title);
		$('.xs_selectlvl').text(_selectLvl);
		$('.xs_checklvl').text(_checkLvl);
		$('.xs_currentid').text(_initId);

		$('.xs_er').eq(0).removeClass('xs_er').addClass('xs_san');
		$('.xs_er').remove();
		$('.xs_san').eq(0).removeClass('xs_san').addClass('xs_er');
		$('.xs_er i').show();
		$('.xs_er em').hide();
		
		kuangcode = _kuangcode;
		kuang = _kuang;
		ajaxurl = _ajaxurl;
		initType = _initType;
		
		/* if(_titile == "职称"){
			$('.xs_biaoti .xs_er .attr_xueke01').text('职称');
		} */
		
		if(_initType > 0 && _initType < 6){
			$('.xs_biaoti .xs_er .attr_xueke01').text('一级学科');
			initsubmenu="一级学科";
			viewpath = true;
		}
		else{
			$('.xs_biaoti .xs_er .attr_xueke01').text(_title);
			initsubmenu=_title;
			viewpath = false;
		}
		
		 /* */

		$('.xs_kuangcode').text($(_kuangcode).val());
	//	$('.xs_kuang').text($(_kuang).text());
		
		// 学科
		if(_initType > 0 && _initType < 6){
			$('.xs_kuang').html("");
			var url = ajaxurl;
			var params = "ids="+ $(_kuangcode).val() +"&mode=getFullNames";
			$.ajax({
			    url:url,
			    type: 'POST',
			    data: params,
			    dataType: 'text',
			    success: function(result){
				   if(result != ''){
						var selstr = result;
						selarray = selstr.split(", ");
						var newnarray = new Array();
						$(selarray).each(function(key, val){
							if (val != "") newnarray.push('<em class="delem">' + val + '</em>');
						});
						$('.xs_kuang').html(newnarray.toString().replace(dotexp, "<br>"));
						
						$('.delem').click(function(){
							delem($(this));
						});
				   }
				   else{
				   		$('.xs_kuang').html("");
				   }
			    },
			    error: function (){
			    	$('.xs_kuang').html("");
			    }
			});	
		}
		else{
			var selstr = $(_kuang).text();
			selarray = selstr.split(",");
			var newnarray = new Array();
			$(selarray).each(function(key, val){
				if (val != "") newnarray.push('<em class="delem">' + val + '</em>');
			});
			$('.xs_kuang').html(newnarray.toString().replace(dotexp, "<br>"));		

			$('.delem').click(function(){
				delem($(this));
			});

		}
		
		var url = ajaxurl;
		if (_initType == 24) 
			params = "type="+ _initType +"&id="+ _initId + "&sort=t.id";
		else 
			params = "type="+ _initType +"&id="+ _initId + "&mode=getListByType";
			
		$.ajax({
		    url:url,
		    type: 'POST',
		    data: params,
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
			   		//职称
			   		if (_initType == 24){
			   			$('.xs_zhcombo').show();
			   			updateZHLXCombo(result);
			   			updatePropList("");
			   		}
			   		else{
			   			$('.xs_zhcombo').hide();
			   			updatePropList(result);
			   		}
			   };
		    }
		});		
	}
	
	function delem(obj){
		var i = $(obj).index()/2;
		//var b = $(obj).text();
		//delete text
		var selstr = $(obj).parent().html();
		var selarray = selstr.split("<br>");
		var newnarray = new Array();
		$(selarray).each(function(key, val){
		//	var a = $(val).text();
		//	if (a != b) newnarray.push(val);
		//	else i=key;
			if (key != i) newnarray.push(val);
		});
		$('.xs_kuang').html(newnarray.toString().replace(dotexp, "<br>"));
		
		//delete code
		var deletecode = "";
		selstr = $('.xs_kuangcode').text();
		selarray = selstr.split(",");
		newarray = new Array();
		$(selarray).each(function(key, val){
			if (key != i) newarray.push(val);
			else
				deletecode = val;
		});
		$('.xs_kuangcode').text(newarray.toString());

		//delete check
		var checklist = $('.attr_xueke04');
		$(checklist).each(function(key, val){
			if(deletecode == $(val).prop('id'))
				$(val).siblings().eq(0).prop("checked", "");
		});

		$('.delem').off('click');
		$('.delem').click(function(){
			delem($(this));
		});
		
	}
	
	$('#zclx_combo').change(function(){
		var url = ajaxurl + "?zhtype=" +$(this).val()+ "&type=9";
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
			   		updatePropList(result);
			   };
		    }
		});
	});
	
	function updateZHLXCombo(result){
		var str = '<option value="0">请选择</option>';
		$(result.list).each(function(key, value){
			str += '<option value="'+value.code+'">'+value.name+'</option>';
		});
		$('#zclx_combo').html(str);
	}
	
	function selectProp(){
		$(kuangcode).val($('.xs_kuangcode').text());
		var selPropHtml = $('.xs_kuang').html().replace(brexp,",");
		$('.xs_kuang').html(selPropHtml);
		$(kuang).text($('.xs_kuang').text());
	}
	$('.cas_anniu_4,.bjt_kt').click(function(){
		$('.att_make01').hide();
	});
</script>
</body>
</html>
