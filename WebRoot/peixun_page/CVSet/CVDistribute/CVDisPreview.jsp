<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/commons/taglibs.jsp"%>
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/city.js"></script>
		<title>培训后台</title>
	</head>
<%@include file="/peixun_page/top.jsp"%>

<body>
<div class="center" style="">
<form id = "fm1" name = "fm1" method = "POST" action = "${ctx}/CVSet/CVDistribute.do?method=save">
	<div class="center01">
		<div class="tiaojian">
			<p class="fl"  style="margin:0 20px 20px 0;">
				<span style="width:9em;text-align:right;">项目授权站点：</span>
				<input type="text" class="auth_site" value='${sqName}'/>
				<input type = "hidden" id = "siteIds" name = "siteIds" value = '${sqIds}'/>
			</p>
			<p class="fl">
				<span style="width:9em;text-align:right;">项目授权区域：</span>
			</p>
			<div class="fl select" style="margin:0 10px 0 0">
				<div class="tik_position">
					<b class="ml5">省/直辖市</b>
					<p class="position01"><i class="bjt10"></i></p>
				</div>
				<ul class="fl list" id="region1" style="display: none;">
					<select name="provinceId" id = "provinceId" style="display:none;">
						<option value="0">省/直辖市</option>
						<c:forEach items="${region1list}" var="region1">
							<option value="${region1.prop_val_id}">${region1.name}</option>
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
				<select name="cityId" id = "cityId" style="display:none;">
					<option value="0">市</option>
				</select>
					<li>市</li>
				</ul>
			</div>
			<div class="clear"></div>
			<p class="fl" style="margin:0 20px 20px 0;">
				<span style="width:9em;text-align:right;">项目编号：</span> 
				<input type="text" id = "serialNumber" name = "serialNumber" value="${View.serial_number}" placeholder="请输入" />
			</p>
			<p class="fl">
				<span style="width:9em;text-align:right;">项目级别：</span>
			</p>
			<div class="fl select">
				<div class="tik_position">
					<b class="ml5">全部</b>
					<p class="position01"><i class="bjt10"></i></p>
				</div>
				<ul class="fl list" style="display: none;">
					<select name="level" id = "level" class="fl select" style="display:none;">
						<option value="-1">全部</option>
						<option value="1" <c:if test="${View.level==1}"> selected</c:if>>国家I类</option>						
						<option value="2" <c:if test="${View.level==2}"> selected</c:if>>省级I类</option>
						<option value="3" <c:if test="${View.level==3}"> selected</c:if>>市级I类</option>
						<option value="4" <c:if test="${View.level==4}"> selected</c:if>>省级II类</option>
						<option value="5" <c:if test="${View.level==5}"> selected</c:if>>市级II类</option>
					</select>
					<li>国家I类</li>
					<li>省级I类</li>
					<li>市级I类</li>
					<li>省级II类</li>
					<li>市级II类</li>
				</ul>
			</div>
			<div class="clear"></div>
			<p class="fl" style="margin:0 20px 20px 0;">
				<span style="width:9em;text-align:right;">项目学分：</span>
				<input type="text" id = "score" name = "score" value="${View.score}" placeholder="请输入学分" />
			</p>
			<p class="fl" style="margin:0 20px 20px 0;">
				<span style="width:9em;text-align:right;">项目价格：</span>
				<input type="text" id = "cost" name = "cost" value="${View.cost }" placeholder="请输入价格" /> <span class="fl" style="margin:0 5px;color:#333;">元</span>
			</p>
			<div class="clear"></div>
			<p class="clear" style="margin:0 20px 20px 0;">
				<span style="width:9em;text-align:right;">项目标签：</span>
				<span class="fl" style="margin-right:30px;color:#333;"><input type="checkbox" class = "sign" id = "sign1" value = "公需科目"/> 公需科目</span>
				<span class="fl" style="color:#333;"><input type="checkbox" class = "sign" value = "指南"/> 指南</span>
				<input type = "hidden" id = "signStr" name = "signStr" value = "${View.sign}"/>
			</p>

			<div style="border-bottom:1px solid #666;clear:both;margin-bottom:20px;"></div>
			<p class="fl" style="margin:0 20px 20px 0;">
				<span style="width:9em;text-align:right;">项目有效时间：</span>
				<input type="text" class="xiangmuks" id = "start_date" name = "start_date" value="<fmt:formatDate value="${View.start_date}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="yyyy-MM-dd HH:mm:ss" readonly="readonly" /> <span style="margin:0 5px;color:#333;">至</span>
				<input type="text" class="xiangmujs" id = "end_date" name = "end_date" value="<fmt:formatDate value="${View.end_date}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="yyyy-MM-dd HH:mm:ss" readonly="readonly" />
			</p>
			<p class="fl" style="margin:0 20px 20px 0;">
				<span style="width:13em;text-align:right;">项目课程的学习间隔时间：</span>
				<input type="text" id = "break_days" name = "break_days" value = "${View.break_days}" placeholder="" /> <span class="fl" style="margin:0 5px;color:#333;">天</span>
			</p>
			<div class="clear"></div>

			<p class="fl" style="margin:0 20px 20px 0;">
				<span style="width:9em;text-align:right;">课程发布起止时间：</span>
			</p>
			<ul class="fl form_list">
				<c:forEach items = "${View.CVScheduleList}" var = "schedule">
					<li class="clear">
						<p>
							<span style="width:30em;display:inline-block;">课程 ${itemNo}：${schedule.name}</span>
							<input type="text" class=" kechengks" id = "item_${schedule.id}_start" name = "item_${schedule.id}_start" value="<fmt:formatDate value="${schedule.start_date}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="yyyy-MM-dd HH:mm:ss" readonly="readonly" /> <span style="margin:0 5px;color:#333;">至</span>
							<input type="text" class=" kechengjs" id = "item_${schedule.id}_end" name = "item_${schedule.id}_end" value="<fmt:formatDate value="${schedule.end_date}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="yyyy-MM-dd HH:mm:ss" readonly="readonly" />
						</p>
					</li>
				</c:forEach>
			</ul>

			<div style="border-bottom:1px solid #666;clear:both;margin:20px 0;"></div>
			<p class="fl cas_anniu">
				<a href="${ctx}/CVSet/CVDistribute.do" class="fr" style="width:70px;margin-left:10px;">返回</a>
			</p>
		</div>
		<input type = "hidden" name = "cvId" id = "cvId" value = "${View.id}"/>
		<input type = "hidden" name = "province" id = "province" value = "${View.provinceId}">
		<input type = "hidden" name = "city" id = "city" value = "${View.cityId}">
		<div class="clear"></div>
	</div>
</form>
</div>
<div id="container"></div>
<script type="text/javascript">

function compare_date() {

	var date01 = $("#datepicker01").val();
	var date02 = $("#datepicker02").val();
	var date03 = $("#datepicker03").val();
	var date04 = $("#datepicker04").val();
	var date05 = $("#datepicker05").val();
	var date06 = $("#datepicker06").val();
	
	if(date01 > date02 && date02 != "")
	{
		alert("Error: ????");
	}
	if(date03 > date04 && date04 != "")
	{
		alert("Error: ????");
	}
	if(date05 > date06 && date06 != "")
	{
		alert("Error: ????");
	}
	
	var restDay = $("#restdateVal").val();
	
	if(isNaN(restDay))
		alert();
	if(restDay > 0) {
		var timeVal01 = new Date(date04);
		var timeVal02 = new Date(date05);
		var compareTime01 = timeVal01.getTime();
		var compareTime02 = timeVal02.getTime();
		
		if(date04 == "") {
			alert("Error: ???");
		}
		if(compareTime02 < (compareTime01 + 259200000)) {
			alert("dfdfdf");
		}
	}
}

function chk_String() {
	var restDay = $("#restdateVal").val();
	
	if(isNaN(restDay)) {
		alert();
	}
}

$(function(){
//	$(".next_btn").click(function(){
//
//	});
//选择目录弹出框

		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "lc_left0" + menuindex;
		$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");

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
		$.extend( $.fn.pickadate.defaults, {
			monthsFull: [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
			monthsShort: [ '一', '二', '三', '四', '五', '六', '七', '八', '九', '十', '十一', '十二' ],
			weekdaysFull: [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
			weekdaysShort: [ '日', '一', '二', '三', '四', '五', '六' ],
			today: '今日',
			clear: '删',
			firstDay: 1,
			format: 'yyyy-mm-dd',
			formatSubmit: 'yyyy-mm-dd'
		});
		
	// 日期选择控件
	var input = $(".datepicker").pickadate({
		monthSelector: true,
//		yearSelector: true,
		today:false,
		clear:false,
		dateMax : true,
		dateMin : [2010, 1, 1],
		yearSelector: 100
	});
	var calendar = input.data( 'pickadate' );
//	calendar.show( 1, 1985 );
//	calendar.setDate(
//			1975,
//			10,
//			13 );

	var win_w = parseInt($(window).width() / 3) + "px";
	var win_h = parseInt($(window).height() / 3) + "px";
	$(".auth_site").focus(function(){
		var sub_cont = '<div class="tiaojian" style="min-height:40px;padding:20px;">' +
				'<ul class="fl" style="width:70%;margin:0 20px 20px 0;">' +
					'<c:forEach items = "${siteList}" var="site">' +
					'<li><input type="checkbox" name="auth_site_list" id = "auth_site_list_${site.id}" value="${site.id}" / ><span>${site.domainName}</span></li>' +
					'</c:forEach>' +
				'</ul>' +
				'</div>';
		layer.open({
			type: 1,
			title: "授权站点",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, win_h], //宽高
			content: sub_cont,
			closeBtn: 0,
			btn: ['确定', '取消'],
			yes: function (index, layero) {
				//编写保存功能
/*
				if ($("input[name=auth_site_list]").prop("checked") == true){
					$(".auth_site").val($("input[name=auth_site_list]").val());
				}*/
				var siteVal = "";
				var siteStr = "";
				$("input[name=auth_site_list]").each(function(key,val){

					if($(this).prop("checked") == true)
					{
						siteVal += $(this).val() + ",";
						siteStr +=$(this).parent().find("span").text();
					}
				});
				$('.auth_site').val(siteStr);
				$("#siteIds").val(siteVal);
				
				layer.close(index);
			},
			btn2: function (index, layero) {
				layer.close(index);
			},
			success:function(index, layero){

			}
		});
		var site = $("#siteIds").val();
		var siteArray = site.split(",");
		$(siteArray).each(function(key, val){
			var siteObj = "#auth_site_list_" + val;
			$(siteObj).prop("checked",true);
		});
	});
});
selectInit();
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
	if($("#province").val() != 0)
	{
		var url = "${ctx}/propManage/getPropListAjax.do?id=" + $("#province").val();
		
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
			   		updatePropList($('#region2'), "市", result);
			   };
		    }
		});
	
	}
	$('#region1 li').click(function(){
		var regionid = $(this).parent().find('option').eq($(this).index()-1).val();
		
		var url = "${ctx}/propManage/getPropListAjax.do?id=" + regionid;
		
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
			   		updatePropList($('#region2'), "市", result);
			   };
		    }
		});
	});
}

function updatePropList(obj, defval, result){
	
	var str = "<option value='0'>"+defval+"</option>";
	var str1 = "<li>"+defval+"</li>";
	var city = $("#city").val();
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
function save()
{
	var signStr="";
	var dXiangmuks = $(".xiangmuks").val();
	var dXiangmujs = $(".xiangmujs").val();
	var k = 0;
		
	$('.kechengjs').each(function(){
		var dKechengjs = $(this).val();
		var dKechengks = $(this).prev().prev().val();
		if (!((dKechengks > dXiangmuks) && (dKechengjs > dKechengks) && (dXiangmujs > dKechengjs))){
			if ((dKechengks != 0) && (dKechengjs != 0)){
				k = 1;
				return;
			}
		}
	});
	if (k == 1){
		alert("请选择正确的课程发布起止时间！");
		return;
	}
	
	$(".sign").each(function(key,val){
		if($(this).prop("checked") == true)
		{
			signStr += $(this).val() + ",";
		}
		
	});
	$("#signStr").val(signStr);
	$("#fm1").submit();
}
</script>
</body>
</html>