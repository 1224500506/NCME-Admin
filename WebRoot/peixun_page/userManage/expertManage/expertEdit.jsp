<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/taglibs.jsp"%>
<title>资源管理平台</title>
</head>

<%@include file="/peixun_page/top.jsp"%>
<body class="bjs">
<div>
	<!-- 题库内容 -->
	<div class="center">
		<div class="tk_tk_xuanxiag" style="">
			<div class="xingzeng_k">
				<i></i><h2 class="fl">
							<c:if test="${info.id == null}">添加专家</c:if>
							<c:if test="${info.id != null}">修改专家</c:if>
						</h2>
			</div>
			<form id="frm" action="${ctx}/expert/ExpertManage.do?mode=save" method="POST" enctype="multipart/form-data">
			<div class="thi_shitineirong" style="">
				<ul>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">专家姓名：</span><em class="fr">*</em></p>
							<input type="hidden" name="id" value="${info.id}" />
							<input type="text" id="rsName" class="fl tki_bianji" style="width:258px;" name="name" value="${info.name}"/>
						</div>
						<div class="fl shitin_xinzeng01">
							<p class="fl ml30"><span class="fr">编码：</span><em class="fr">*</em></p>
							<input type="text" id="rsCode" class="fl tki_bianji" style="width:258px;" name="code" value="${info.code}" />
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">加入专委会：</span></p>
							<input type="hidden" class="fl tik_select" id="newgroupIds" name="groupIds" value="${info.groupIds}"/>
							<textarea name="" cols="" rows="" class="fl tki_bianji takuang_zy" id="newgroupNames" readonly>${info.groupNames}</textarea>


<%-- 							<p class="fl"><span class="fr">加入专委会：</span></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">请选择</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list list_group" style="display:none;">
								<select name="groupId" style="display:none;">
									<option value="0">请选择</option>
									<c:forEach items="${grouplist}" var="group">
										<option value="${group.id}"<c:if test="${group.id==info.groupId}"> selected</c:if>>${group.name}</option>
									</c:forEach>
								</select>
									<li>请选择</li>
									<c:forEach items="${grouplist}" var="group">
										<li>${group.name}</li>
									</c:forEach>
								</ul>
							</div>
 --%>						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">职务：</span></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">请选择</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list" style="display:none;">
								<select name="office" style="display:none;">
									<option value="">请选择</option>
									<option value="1"<c:if test="${info.office==1}"> selected</c:if>>主任委员</option>
									<option value="2"<c:if test="${info.office==2}"> selected</c:if>>副主任委员</option>
									<option value="3"<c:if test="${info.office==3}"> selected</c:if>>秘书长</option>
									<option value="4"<c:if test="${info.office==4}"> selected</c:if>>学组长</option>
									<option value="5"<c:if test="${info.office==5}"> selected</c:if>>委员</option>
								</select>
									<li>请选择</li>
									<li>主任委员</li>
									<li>副主任委员</li>
									<li>秘书长</li>
									<li>学组长</li>
									<li>委员</li>
								
								</ul>
							</div>
						</div>
						<div class="clear"></div>
					</li>
<%-- 					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">学组：</span></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">请选择</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list list_subgroup" style="display:none;">
								<select id='subGroupId' name="subGroupId" style="display:none;">
									<option value="">请选择</option>
									<c:forEach items="${sublist}" var="group">
										<option value="${group.id}"<c:if test="${group.id==info.subGroupId}"> selected</c:if>>${group.name}</option>
									</c:forEach>
								</select>
									<li>请选择</li>
									<c:forEach items="${sublist}" var="group">
										<li>${group.name}</li>
									</c:forEach>
							</ul>
							</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">届期：</span></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">请选择</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list list_termlist" style="display:none;">
								<select name="term" style="display:none;">
									<option value="">请选择</option>
									<c:forEach items="${termlist}" var="group">
										<option value="${group.id}"<c:if test="${group.id==info.term}"> selected</c:if>>${group.startDateStr} - ${group.endDateStr}</option>
									</c:forEach>
								</select>
									<li>请选择</li>
									<c:forEach items="${termlist}" var="group">
										<li>${group.startDateStr} - ${group.endDateStr}</li>
									</c:forEach>
								</ul>
							</div>
						</div>
						<div class="clear"></div>
					</li>
 --%>					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">任职状态：</span></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">请选择</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list" style="display:none;">
								<select name="state" style="display:none;">
									<option value="0">请选择</option>
									<option value="1"<c:if test="${info.state==1}"> selected</c:if>>在职</option>
									<option value="2"<c:if test="${info.state==2}"> selected</c:if>>离职</option>
								</select>
									<li>请选择</li>
									<li>在职</li>
									<li>离职</li>
								</ul>
							</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">离职时间：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="breakDate" value="${info.breakDate}"/>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">职称：</span></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">请选择</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list" style="display:none;">
								<select name="job" style="display:none;">
									<option value="">请选择</option>
									<c:forEach items="${proplist}" var="prop">
										<option value="${prop.id}"<c:if test="${prop.id==info.job}"> selected</c:if>>${prop.name}</option>
									</c:forEach>
								</select>
									<li>请选择</li>
									<c:forEach items="${proplist}" var="prop">
										<li>${prop.name}</li>
									</c:forEach>
								</ul>
							</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">状态：</span></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">请选择</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list" style="display:none;">
								<select name="lockState" style="display:none;">
									<option value="0">请选择</option>
									<option value="1"<c:if test="${info.lockState==1}"> selected</c:if>>启用</option>
									<option value="2"<c:if test="${info.lockState==2}"> selected</c:if>>禁用</option>
								</select>
									<li>请选择</li>
									<li>启用</li>
									<li>禁用</li>
								</ul>
							</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">学科：</span></p>
							<input type="hidden" class="fl tik_select" id="groupIds" name="propIds" value="${info.propIds}"/>
							<textarea name="" cols="" rows="" class="fl tki_bianji takuang_xk" id="groupNames" readonly>${info.propNames}</textarea>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">工作单位：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="workUnit" value="${info.workUnit}"/>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">固定电话：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="phone1" value="${info.phone1}"/>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">手机号：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="phone2" value="${info.phone2}"/>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">邮箱：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="email" value="${info.email}"/>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">秘书姓名：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="clerkName" value="${info.clerkName}"/>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">秘书电话：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="clerkPhone" value="${info.clerkPhone}"/>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">国籍：</span></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">中国</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list" style="display:none;">
								<select name="isNation" style="display:none;">
									<option value="0"<c:if test="${info.isNation==0}"> selected</c:if>>中国</option>
									<option value="1"<c:if test="${info.isNation==1}"> selected</c:if>>外国</option>
								</select>
									<li>中国</li>
									<li>外国</li>
								</ul>
							</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">银行卡号：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="bankCard" value="${info.bankCard}"/>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">开户行：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="bank" value="${info.bank}"/>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
						<p class="fl"><span class="fr">身份证号：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="identityNum" value="${info.identityNum}"/>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">专家照片：</span></p>
							<div class="fl tik_select" style="width:258px;height:30px">
								<div class="tik1_position">
									<span>${info.photo}</span>
									<p class="mate_position" style="width:40px;">上传</p>
								</div>
								<input type="file" name="photo" onchange="javascript:$(this).siblings().find('span').text($(this).val());"
									style="position:absolute;opacity:0;overflow:hidden;left:220px;right:0px;padding:0px;width:41px;height:30px"/>
							</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">是否新增账号：</span></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">请选择</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list" style="display:none;">
								<select name="isAddAccount" style="display:none;">
									<option value="">请选择</option>
									<option value="0">是</option>
									<option value="1">否</option>
								</select>
									<li>请选择</li>
									<li>是</li>
									<li>否</li>
								</ul>
							</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">账号：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="userName" value="${info.userName}"/>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">简介：</span></p>
							<textarea name="summary" cols="" rows="" class="fl tki_bianji" style="width:680px;">${info.summary}</textarea>
						</div>
						<div class="clear"></div>
					</li>
					<li class="mt10">
						<div class="ca1_anniu" style="width:200px;">
							<a href="#" class="fl anniu att_baocun">保存</a>
							<a href="javascript:goBack();" class="fl ml30 anniu att_fanhui">返回</a>
						</div>
						<div class="clear"></div>
					</li>
					
				</ul>	
			</div>
			<input type = "hidden" id = "gid" name = "gid" value = "${groupId}">
			<input type = "hidden" id = "sid" name = "sid" value = "${studyId}">
			</form>
		</div>
		
	</div>
	
		<!-- 试题内容（结束） -->
			
</div>


<div class="toumingdu att_make01" style="display:none;">
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
			<div class="clear"></div>
			<ul class="xs_ul">
			</ul>
			<div class="clear"></div>
		
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
	
	selectInit();
	
	//when select a group at form, get subgroups and terms of the group and redraw combo boxs.
		$('.list_group li').click(function(){
			var selectval = $(this).parent().find('option').eq($(this).index()-1).val();
			var url = '${ctx}/expert/ExpertManage.do?mode=getsub&gid=' + selectval;
			$.ajax({
			    url:url,
			    type: 'GET',
			    dataType: 'json',
			    success: function(result){
				   if(result != ''){
				   		var subgroup = result.sublist;
				   		var termlist = result.termlist;
				   		
				   		$('.list_subgroup select option').remove();
				   		$('.list_subgroup li').remove();
				   		var newoptions = "<option value=''>请选择</option>";
				   		var newlis = "<li>请选择</li>";
				   		$.each(subgroup, function(key, val){
				   			newoptions += "<option value='" + val.id + "'>" + val.name  + "</option>";
				   			newlis += "<li>" + val.name  + "</li>";
				   		});
				   		$('.list_subgroup select').html(newoptions);
				   		$('.list_subgroup select').after(newlis);
				   		
				   		////////////////////////////////
				   		$('.list_termlist select option').remove();
				   		$('.list_termlist li').remove();
				   		newoptions = "<option value=''>请选择</option>";
				   		newlis = "<li>请选择</li>";
				   		$.each(termlist, function(key, val){
				   			newoptions += "<option value='" + val.id + "'>" + val.startDateStr + " - " + val.endDateStr + "</option>";
				   			newlis += "<li>" + val.startDateStr + " - " + val.endDateStr + "</li>";
				   		});
				   		$('.list_termlist select').html(newoptions);
				   		$('.list_termlist select').after(newlis);
				   		
				   		selectInit();
				   }else{
				   		alert('失败!');
				   };
			    }
			    });

		});

		$(document).click(function(){
			$('.tk1_list').hide('fast');
		});

		$('.att_baocun').click(function(){
			if(!$('#rsName').val() || !$('#rsCode').val()){
				alert("名称和编码不能为空");
				return;
			}
				
			if ($('#rsmode').val() == 'add'){
				var url = '${ctx}/expert/ExpertManage.do?mode=add';
			}
			else if ($('#rsmode').val() == 'edit'){
				var url = '${ctx}/expert/ExpertManage.do?mode=edit';
				if (!$('#rsId').val())
					return;
			}
			
			 $('#frm').submit();
				
		});
		
	//window control code
		$('.cas_anniu_4,.bjt_kt').click(function(){
			$('.att_make01').hide();
		});
		
		$('.takuang_xk').click(function(){
			initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#groupIds'), $('#groupNames'));
			$('.att_make01').show();
		});
		
		$('.takuang_zy').click(function(){
			initPropList("专委会", "${ctx}/expert/ExpertGroupListAjax.do", 1, 0, 2, 1, $('#newgroupIds'), $('#newgroupNames'));
			$('.att_make01').show();
		});
		
});

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



/////// select xueke window proc
	var kuangcode;
	var kuang;
	var ajaxurl;
	var initsubmenu;

	function initPropList(_title, _ajaxurl, _initType, _initId, _selectLvl, _checkLvl, _kuangcode, _kuang){
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
		
		if (_title == "专委会"){
			initsubmenu = '专委会';
			$('.xs_biaoti .xs_er .attr_xueke01').text('专委会');
		}
		else{
			initsubmenu = '一级学科';
			$('.xs_biaoti .xs_er .attr_xueke01').text('一级学科');
		}

		$('.xs_kuangcode').text($(_kuangcode).val());
	//	$('.xs_kuang').text($(_kuang).text());
		
		var selstr = $(_kuang).text();
		selarray = selstr.split(",");
		var newnarray = new Array();
		$(selarray).each(function(key, val){
			if (val != "") newnarray.push('<em class="delem">' + val + '</em>');
		});
		$('.xs_kuang').html(newnarray.toString());		

		$('.delem').click(function(){
			delem($(this));
		});

		var url = ajaxurl + "?type="+ _initType +"&id="+ _initId;
		
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
			
			var url = ajaxurl+"?id=" + selid;
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

			var url = ajaxurl+"?id=" + selid;
			
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
		
		$('.xs_ul input[type="checkbox"]').off('click');
		$('.xs_ul input[type="checkbox"]').click(function(){
			var p = $(this).parent().find('.attr_xueke04').eq(0);
			var id = $(p).prop('id');
			var propname = '<em class="delem">' + $(p).find('em').text() + '</em>';
			
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
				selarray = selstr.split(",");
				var newnarray = new Array();
				$(selarray).each(function(key, val){
					if (val != "") newnarray.push(val);
				});
				newnarray.push(propname);
				$('.xs_kuang').html(newnarray.toString());
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
				selarray = selstr.split(",");
				var newnarray = new Array();
				$(selarray).each(function(key, val){
					if (key != idx) newnarray.push(val);
				});
				$('.xs_kuang').html(newnarray.toString());
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

	function updatePropList(result){
		
		var str = "";
		var check = eval($('.xs_checklvl').text());
		var select = eval($('.xs_selectlvl').text());
		$(result.list).each(function(key, value){
			str += "<li><div class=''>";
			if (check <= value.type)
				str += '<input class="fl" style="margin-top:5px;" type="checkbox">';

			str += '<p class="fl attr_xueke04"' + ' id="'+ value.prop_val_id +'"' + '><em class="fl">' + value.name + '</em>';
			if (select > value.type){
				str += '<i class="fl ml10 kti_bjt2"></i>';
			}
			str += "</div><div class='clear'></div></li>";
		});
		
		$('.xs_ul').html(str);
		initPropWndProp();
	}
	
	function selectProp(){
		$(kuangcode).val($('.xs_kuangcode').text());
		$(kuang).text($('.xs_kuang').text());
	}
	
	function delem(obj){
		var i = $(obj).index();
		//delete text
		var selstr = $(obj).parent().html();
		var selarray = selstr.split(",");
		var newnarray = new Array();
		$(selarray).each(function(key, val){
			if (key != i) newnarray.push(val);
		});
		$('.xs_kuang').html(newnarray.toString());
		
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
function goBack() {
		window.history.back();
}
</script>
</body>
</html>