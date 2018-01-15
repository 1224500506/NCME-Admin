<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>培训后台</title>
		<%@include file="/commons/taglibs.jsp"%>
	</head>
<body>
<%@include file="/peixun_page/top.jsp"%>
<!-- 查询条件 -->
<div class="center">
<form action="${ctx}/system/systemUserStudent.do?method=list&model.userType=1" method="post" id="queryForm">
 	<div class="tiaojian" style="min-height:40px;">
		<p class="fl" style="margin:0 20px 10px 0">
			<span style="width:5em;text-align:right;">姓名：</span>
			<input type="text" name="model.realName" value="${item.realName}" />
		</p>
		<p class="fl" style="margin:0 20px 10px 0">
			<span style="width:5em;text-align:right;">工作单位：</span>
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
		<div class="clear"></div>
		<p class="fl">
			<span style="width:5em;text-align:right;">手机号：</span>
			<input type="text" name="model.mobilPhone" value="${item.mobilPhone}" />
		</p>
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
					<option value="${region1.prop_val_id}" >${region1.name}</option>
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
			</select>
				<li>市</li>
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
			</select>
				<li>区</li>
			</ul>
		</div>
		<p class="fl cas_anniu">
			<a href="#" class="fl chaxun" style="width:70px;margin:0 20px 10px 0;">查询</a>
		</p>
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

		<display:table requestURI="${ctx}/system/systemUserStudent.do?method=list" 
				 id="systemUser" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}" name="page"
				 class="mt10 table" keepStatus="true" decorator="com.hys.exam.displaytag.TableOverOutWrapper">
			<display:column title="序号" style="width:3%;">
				${systemUser_rowNum}
			</display:column>
			<display:column  style="width:7%;" title="学员账号" property="accountName" /> 
			<display:column  style="width:7%;" title="学员姓名" property="realName" /> 
			<display:column  style="width:6%;" title="地区" >
				${systemUser.userConfig.userProvinceName}
				<c:if test="${systemUser.userConfig.userCityName eq not ''}">/${systemUser.userConfig.userCityName}</c:if>
				<c:if test="${systemUser.userConfig.userCountiesName eq not ''}">/${systemUser.userConfig.userCountiesName}</c:if>
			</display:column>
			<display:column  style="width:15%;" title="工作单位" property="workUnit" /> 
			<display:column  style="width:10%;" title="科室" property="deptName" /> 
			<display:column  style="width:6%;" title="职称类型">
				<c:if test="${systemUser.job_Id eq '1'}"> 医疗人员</c:if>  
				<c:if test="${systemUser.job_Id eq '2'}"> 护理人员</c:if>  
				<c:if test="${systemUser.job_Id eq '3'}"> 药学人员</c:if>  
				<c:if test="${systemUser.job_Id eq '4'}"> 公共卫生人员</c:if>  
				<c:if test="${systemUser.job_Id eq '5'}"> 技术人员</c:if>  
				<c:if test="${systemUser.job_Id eq '6'}"> 中医人员</c:if>  
				<c:if test="${systemUser.job_Id eq '7'}"> 教师</c:if>  
				<c:if test="${systemUser.job_Id eq '8'}"> 研究人员</c:if>  
				<c:if test="${systemUser.job_Id eq '9'}"> 其他</c:if>  
			</display:column> 
			<display:column  style="width:8%;" title="职称" property="profTitle" /> 
			<display:column  style="width:10%;" title="证件号" property="certificateNo" /> 
			<display:column  style="width:7%;" title="手机号" property="mobilPhone" /> 
			<display:column  style="width:5%;" title="状态" >
				<c:if test="${systemUser.account_status==1}">正常</c:if>
				<c:if test="${systemUser.account_status==2}">停用</c:if>
			</display:column> 
			<display:column  style="width:12%;" title="操作" >
			<!-- 
				<a class="edit_btn" href="javascript:infoView(${systemUser.userId});" >查看</a> 
			 -->
				<a href="${ctx}/system/systemUserStudent.do?method=view&rtype=1&id=${systemUser.userId}&type=1">查看</a>
				<a href="${ctx}/system/systemUserStudent.do?method=view&rtype=1&id=${systemUser.userId}&type=2">编辑</a> 
	 			<c:if test="${systemUser.status==1}">
					<a href="javascript:setState(${systemUser.userId},2);" >停用</a> 
				</c:if>
				<c:if test="${systemUser.status==2}">
					<a href="javascript:setState(${systemUser.userId},1);" >启用</a> 
				</c:if>
			 	<a href="javascript:resetPass('${systemUser.accountName}');" class="pwd_btn">重置密码</a>
			</display:column>
		</display:table>
		
	</div>
</div>
<!-- <div class="toumingdu att_make01" style="display:none;">
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
					<p class="fl attr_xueke01"></p>
					<i class="fl xs_bjt01"></i>
					<em class="fl">></em>
				</div>
			</div>
			<div class="clear"></div>
			<ul class="xs_ul">
			</ul>
			<div class="clear"></div>
		
		</div>
	</div>
</div> -->
<!-- layer内容 -->
<div id="layercontent" style="display:none;">
<div class="center">
<form name="editfrm" action="${ctx}/system/systemUserStudent.do?method=save" method="post">
	<input type="hidden" id="userId" name="model.userId" value="0" />
	<div class="tiaojian" style="min-height:40px;">
	    <!-- 第一行 证件类型  证件号码 -->
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">证件类型：</span>
		</p>
		<select class="fl select" id="certificateType" name="model.certificateType" disabled>
			<option value="1">身份证</option>
			<option value="2">军官证</option>
			<option value="3">港澳台通行证</option>
			<option value="4">护照</option>
		</select>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">证件号码：</span>
			<input type="text" id="certificateNo" name="model.certificateNo" value="" disabled/>
		</p>
		<div class="clear"></div>
		
		<!-- 第二行 学员账号 学员姓名 -->
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">学员账号：</span>
			<input type="text" id="accountName" name="model.accountName" value="" disabled/>
		</p>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">学员姓名：</span>
			<input type="text" id="realName" name="model.realName" value="" disabled/>
		</p>
		<div class="clear"></div>
		
		<!-- 第三行 性别  学历 -->
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">性别：</span>
		</p>
		<select class="fl select" id="sex" name="model.sex" disabled>
			<option value="1">男</option>
			<option value="2">女</option>
		</select>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">学历：</span>
		</p>
		<select class="fl select" id="education" name="model.education" disabled>
			<option value="0">请选择</option>
			<option value="1">高中</option>
			<option value="2">中专</option>
			<option value="3">大专</option>
			<option value="4">本科</option>
			<option value="5">硕士</option>
			<option value="6">博士</option>
			<option value="7">博士后</option>
			<option value="8">其它</option>
		</select>
		<div class="clear"></div>
		
		<!-- 第四行  单位名称  科室 -->
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">单位名称：</span>
			<input type="text" id="workUnit" name="model.workUnit" value="" disabled/>
			<input type = "hidden" id = "workUnitId" name = "model.work_unit_id" value = "" /> 
		</p>
		<div class="clear"></div>
		
		<!-- 第五行  职称类型  职称 -->
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">职称类别：</span>
			<input type="text" id="workExtTypeName" name="model.workExtTypeName" value="" disabled/>
		</p>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">职称：</span>
			<input type="text" id="profTitle" name="model.profTitle" value="" disabled/>
		</p>
		<div class="clear"></div>
		
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">科室：</span>
			<input type="text" id="deptName" name="model.deptName" value="" disabled/>
		</p>
		
		<!-- 第六行 手机号  邮箱   -->
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">手机号码：</span>
			<input type="text" id="mobilPhone" name="model.mobilPhone" value="" disabled/>
		</p>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">邮箱：</span>
			<input type="text" id="email" name="model.email" value="" disabled/>
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
			<span style="width:8em;text-align:right;">来自基层：</span>
		</p>
		<select class="fl select" id="grassroot" name="model.grassroot" disabled>
			<option value="0">否</option>
			<option value="1">是</option>
		</select>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">执业医师号：</span>
			<input type="text" id="health" value="" disabled/>
		</p>	
		<div class="clear"></div>
		
		<!-- 第八行 单位地址-->
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">单位地址：</span>
			<input type="text" id="address" name="model.userConfig.address" value="" disabled/>
		</p>
		<div class="clear"></div>
		
		<!-- 第九行  注册时间  更新时间   -->
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">注册时间：</span>
			<input type="text" id="regDate" name="model.regDate" value="" disabled/>
		</p>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">更新时间：</span>
			<input type="text" id="lastUpdateDate" name="model.lastUpdateDate" value="" disabled/>
		</p>
		<div class="clear"></div>
		
		<!-- 第十行  备注说明  备注时间   -->
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">备注说明：</span>
			<input type="text" id="reason" name="model.reason" value="" disabled/>
		</p>
		<p class="fl" style="margin-bottom:20px;">
			<span style="width:8em;text-align:right;">备注时间：</span>
			<input type="text" id="reasondate" name="model.reasondate" value="" disabled/>
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


	var win_w = "860px";
	var win_h = "538px";
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
			btn: ['返回'],
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
	var url = "${ctx}/system/systemUserStudent.do?method=view&rtype=1&type";
	
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
		      var workExtTypeName = result.workExtTypeName;
		      $('#workExtTypeName').val(workExtTypeName);
		      $('#certificateType').val(item.certificateType);
		      $('#certificateNo').val(item.certificateNo);
		      $('#accountName').val(item.accountName);
		      $('#realName').val(item.realName);
		      $('#sex').val(item.sex);
		      $('#education').val(item.education);
		      $('#deptName').val(item.deptName);
		      $('#profession').val(item.profession);
		      $('#profTitle').val(item.profTitle);
		      $('#mobilPhone').val(item.mobilPhone);
		      $('#email').val(item.email);
		      $('#workUnit').val(item.workUnit);
		      $('#workUnitId').val(item.work_unit_id);
		      if(config != null){
			      $('#userProvinceName').val(config.userProvinceName);
			      $('#userCityName').val(config.userCityName);
			      $('#userCountiesName').val(config.userCountiesName);
		      } 
		      $('#address').val(item.userConfig.address);
		      $('#regDate').val(item.regDatee);
		      $('#lastUpdateDate').val(item.lastUpdateDatee);
		      $('#reason').val(item.reason);
		      $('#reasondate').val(item.reasondate);
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

function setState(id, status){
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

function resetPass(accountName) {
	$("#aName").text(accountName);
}

$(".pwd_btn").click(function(){
	layer.alert(
		"确认将账号 <span id = 'aName'></span>重置密码吗？<br />重置密码后，账号密码为：123456",{
			title: "重置密码",
			closeBtn:false,
			btn:['确定','取消'],
			yes: function (index, layero) {
				var url = "${ctx}/systemManage/getAccounts.do?method=resetPass";
				if($("#aName").text() == "") {
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
			    success: function(result) {
				   if(result == 'success') {
				   		alert("重置密码成功！");
				   } else {
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
</script>
</body>
</html>