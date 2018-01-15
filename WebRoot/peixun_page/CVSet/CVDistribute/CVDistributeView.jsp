<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/commons/taglibs.jsp"%>
		<script type="text/javascript" src="${ctx}/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/jedate/jedateCustom.js"></script>
		<title>培训后台</title>
<style>
.clear1{ zoom:1;}
</style>
	</head>
<%@include file="/peixun_page/top.jsp"%>

<body>
<div class="center" style="">
<form id = "fm1" name = "fm1" method = "POST" action="${ctx}/CVSet/CVDistribute.do?method=saveAuthorization">
	<div class="center01">
		<div class="tiaojian">
			<p class="fl"  style="margin:0 20px 20px 0;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目授权站点：</span>
				<input type="text" class="auth_site" value='<c:forEach items="${View.siteList}" var="site">${site.domainName};&nbsp;</c:forEach>' readonly="readonly" />
			</p>
			<p class="fl" style="margin:0 20px 20px 0;">
				<span style="width:9em;text-align:right;">项目编号：</span>
				<input type="text" id = "serialNumber" name = "serialNumber" value="${View.cVSetAuthorization.cvSetSerialNumber}" placeholder="请输入" readonly="readonly" />
			</p>
			<div class="clear"></div>
			<p class="fl"  style="margin:0 20px 20px 0;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目学分：</span>
					<select name="level" id = "level" class="fl select" style="width:80px;" disabled="true" >
						<option value="-1">全部</option>
						<option value="1" <c:if test="${View.cVSetAuthorization.cvSetLevel==1}"> selected</c:if>>国家I类</option>						
						<option value="2" <c:if test="${View.cVSetAuthorization.cvSetLevel==2}"> selected</c:if>>省级I类</option>
						<option value="3" <c:if test="${View.cVSetAuthorization.cvSetLevel==3}"> selected</c:if>>市级I类</option>
						<option value="4" <c:if test="${View.cVSetAuthorization.cvSetLevel==4}"> selected</c:if>>省级II类</option>
						<option value="5" <c:if test="${View.cVSetAuthorization.cvSetLevel==5}"> selected</c:if>>市级II类</option>
						<option value="6" <c:if test="${View.cVSetAuthorization.cvSetLevel==6}"> selected</c:if>>无学分</option>						
					</select>
					<c:if test="${View.cVSetAuthorization.cvSetLevel==6}">
						<input type="text" id = "score" disabled="disabled" name = "score" onkeyup="this.value= this.value.replace(/[^\d.]/g, '');" value="${View.cVSetAuthorization.cvSetScore}" placeholder="请输入学分" style="margin-left:10px;width:110px;"  readonly="readonly" />
					</c:if>
					<c:if test="${View.cVSetAuthorization.cvSetLevel!=6}">
						<input type="text" id = "score" name = "score" onkeyup="this.value= this.value.replace(/[^\d.]/g, '');" value="${View.cVSetAuthorization.cvSetScore}"  style="margin-left:10px;width:110px;"  readonly="readonly" />
					</c:if>
			</p>
			<div class="clear"></div>
			<p class="fl" style="margin:0 20px 20px 0;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目价格：</span>
    			<input type="radio" id="costType" name="costType" value="0" <c:if test="${View.cVSetAuthorization.cvSetCostType== 0}">checked="checked"</c:if> style="margin-top:7px;float:left;"  disabled="true" ><span style="color:#333;">免费</span>
    			<input type="radio" id="costType" name="costType" value="1" <c:if test="${View.cVSetAuthorization.cvSetCostType== 1}">checked="checked"</c:if> style="margin-top:7px;float:left;margin-left:15px;"  disabled="true" ><span style="color:#333;">学习卡</span>
    			<input type="radio" id="costType" name="costType" value="2" <c:if test="${View.cVSetAuthorization.cvSetCostType== 2}">checked="checked"</c:if> style="margin-top:7px;float:left;margin-left:15px;"  disabled="true" ><span style="color:#333;">收费</span>
    			<span class="fr" style="margin:0 5px;color:#333;" id="costUnit">元</span>
    			<input type="text" id = "cost" name = "cost" onkeyup="this.value= this.value.replace(/[^\d]/g, '');" value="${View.cost }"  style="float:right;width:100px;height:25px;border:1px solid #ddddd;margin-left:15px;"  readonly="readonly"/>
    			
			</p>
			<div class="clear"></div>
			
			<p class="clear1" style="margin:0 20px 50px 0;">
				<span style="width:9em;text-align:right;">项目标签：</span>
				<span class="fl" style="margin-right:30px;color:#333;"><input type="checkbox" class = "sign" id = "sign1" value = "公需科目"  disabled="true"/> 公需科目</span>
				<span class="fl" style="margin-right:30px;color:#333;"><input type="checkbox" class = "sign" id="sign2" value = "指南解读"  disabled="true"/> 指南解读</span>
				<span class="fl" style="margin-right:30px;color:#333;"><input type="checkbox" class = "sign" id="sign4" value = "指南解读"  disabled="true"/> 乡医培训</span>
				<span class="fl" style="color:#333;"><input type="checkbox" class = "sign" id="sign3" value = "海外视野"  disabled="true" /> 海外视野</span>
			</p>
			<div class="clear"></div>
			<c:if test = "${View.forma == 3}">
				<p class="clear1" style="margin:0 20px 100px 0;">
					<span style="width:9em;text-align:right;">面授培训人数：</span>
					<input type="text" id = "faceCount" name = "faceCount" disabled="disabled" onkeyup="this.value= this.value.replace(/[^\d]/g, '');" value="${faceCount}" placeholder="面授人数" />
					<span class="" style="margin:0 5px;color:#333;">人</span>
				</p>
			</c:if>
			<p class="fl" style="margin:0 20px 50px 0;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目授权区域：</span>
                   <textarea class="fl tki_bianji" style="width:678px;height:80px"  readonly="readonly">${propNames}</textarea>
			</p>

			<div style="border-bottom:1px solid #666;clear:both;margin-bottom:20px;"></div>
			<p class="fl" style="margin:0 20px 20px 0;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 项目有效时间：</span>
				<input type="text" class="" id = "start_date" name = "start_date"  data-name="start_date_submit" value="<fmt:formatDate value="${View.cVSetAuthorization.cvSetStartDate}" type="both" pattern="yyyy-MM-dd"/>" placeholder="yyyy-MM-dd"  readonly="readonly" /> <span style="margin:0 5px;color:#333;">至</span>
				<input type="text" class="" id = "end_date" name = "end_date" data-name="end_date_submit" value="<fmt:formatDate value="${View.cVSetAuthorization.cvSetEndDate}" type="both" pattern="yyyy-MM-dd"/>" placeholder="yyyy-MM-dd"  readonly="readonly"/>
			</p>
			<p class="fl" style="margin:0 20px 20px 0;">
				<c:if test = "${View.forma == 1}">
					<span style="width:13em;text-align:right;"><em class="red_start">*</em> 项目课程的学习间隔时间：</span>
					<input type="text" id = "break_days" name = "break_days" value = "${View.break_days}"  placeholder=""  readonly="readonly"/> <span class="fl" style="margin:0 5px;color:#333;">天</span>
				</c:if>
				<c:if test = "${View.forma == 3}">
					<span style="width:13em;text-align:right;"><em class="red_start">*</em> 项目报名截止时间：</span>
					<input type="text" id = "cvSetRegistrationStop" name = "cvSetRegistrationStop" value = "0"  placeholder=""  readonly="readonly"/> <span class="fl" style="margin:0 5px;color:#333;">天</span>
				</c:if>
			</p>
			<div class="clear"></div>

			<p class="fl" style="margin:0 20px 20px 0;">
				<span style="width:9em;text-align:right;"><em class="red_start">*</em> 课程起止时间：</span>
			</p>
			<ul class="fl form_list">
				<jsp:useBean id="now" class="java.util.Date" /> 
				<c:forEach items = "${View.CVScheduleList}" var = "schedule">
					<li class="clear">
						<p>
							<span style="width:30em;display:inline-block;">课程 ${itemNo}：${schedule.name}</span>
							<input type="text" class="" id = "item_${schedule.id}_start" name = "item_${schedule.id}_start"  data-name="item_${schedule.id}_start_submit"   value="<fmt:formatDate value="${schedule.start_date}" pattern="yyyy-MM-dd"/>" placeholder="yyyy-MM-dd"  readonly="readonly"/> <span style="margin:0 5px;color:#333;">至</span>
							<input type="text" class="" id = "item_${schedule.id}_end" name = "item_${schedule.id}_end" data-name="item_${schedule.id}_end_submit"  value="<fmt:formatDate value="${schedule.end_date}" pattern="yyyy-MM-dd" />" placeholder="yyyy-MM-dd"  readonly="readonly"/> 
 							<span class="fl" style="margin:0 15px;color:#333;">
 								<c:if test="${schedule.cv_type == 0}">点播</c:if>
 								<c:if test="${schedule.cv_type == 1}">面授</c:if>
 								<c:if test="${schedule.cv_type == 2}">直播</c:if>
 							</span>
							
						</p>
					</li>
				</c:forEach>
			</ul>

			<div style="border-bottom:1px solid #666;clear:both;margin:20px 0;"></div>
			<!-- <p class="fl cas_anniu" style="margin-left:9.1em;">
				<a href="javascript:save();" class="fl next_btn" style="width:70px;margin-left:10px;">保存</a>
			</p> -->
			<p class="fl cas_anniu">
				<a href="${ctx}/CVSet/CVDistribute.do" class="fr" style="width:70px;margin-left:10px;">返回</a>
			</p>
		</div>
		
		<input type = "hidden" name = "cvSetId" id = "cvSetId" value = "${View.id}"/>
		<input type = "hidden" name = "authorizationId" id = "authorizationId" value = "${authorizationId}"/>
		<input type = "hidden" name = "province" id = "province" value = "${View.provinceId}">
		<input type = "hidden" name = "city" id = "city" value = "${View.cityId}">
		<input type = "hidden" name = "cvSetStatus" id = "cvSetStatus" value = "${View.status}">
		
		<div class="clear"></div>
	</div>
</form>
</div>
<div id="container"></div>

<script type="text/javascript">

/**
 * 2017-02-26 
       程宏业
  更改日期时间插件
 */
$(".datainp").click(function(){
	var $thi = $(this);
	var thid = $thi.attr("id");
	var dname =$thi.attr("data-name");
	var customDate = "";
	if(thid=="start_date"){
		customDate = "start_date";
	}else if(thid=="end_date"){
		customDate = "end_date";
	}
	jeDate({
		dateCell:"#"+thid,
		format:"YYYY-MM-DD",
		isinitVal:true,
		isTime:true, //isClear:false,
		customDate:customDate,
		minDate:"2014-09-19",
		okfun:function(val){
			if(thid=='start_date'){
				$('.kechengks').each(function(){
					$(this).val(val);
				});
			}else if(thid=='end_date'){
				$('.kechengjs').each(function(){
					$(this).val(val);
				});
			}
		},
		choosefun:function(val) {
			if(thid=='start_date'){
				$('.kechengks').each(function(){
					$(this).val(val);
				});
			}else if(thid=='end_date'){
				$('.kechengjs').each(function(){
					$(this).val(val);
				});
			}
		}
	});
	if(dname){
		$thi.attr("name",dname);
	}
	

	
});




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
		var code;
		if(CDCode != null && CDCode != "" && typeof(CDCode) != "undefined"){
			code = CDCode.split("-");
			var menuindex = code[0];
			var submenuindex = code[1];
			var submenu = "lc_left0" + menuindex;
			$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
			$('.'+submenu+'').show();
			$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
		}
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

	
	//初始化费用输入框
	var costTypeval = $("input[name='costType']:checked").val();
	if(costTypeval == 0){
		$("#cost").hide();
		$("#costUnit").hide();
	}else if(costTypeval == 1){
		$("#cost").hide();
		$("#costUnit").hide();
	}else if(costTypeval == 2){
		$("#cost").show();
		$("#costUnit").show();
	}
	
});
function radiocheck(){
	var costTypeval = $("input[name='costType']:checked").val();
	if(costTypeval == 0){
		$("#cost").hide();
		$("#costUnit").hide();
		$("#cost").val('0.0');
	}else if(costTypeval == 1){
		$("#cost").hide();
		$("#costUnit").hide();
		$("#cost").val('0.0');
	}else if(costTypeval == 2){
		$("#cost").show();
		$("#costUnit").show();
	}
}
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


<%--
$("#provinceId").change(function(){
	
	var url = "${ctx}/propManage/getPropListAjax.do?id=" +$(this).val();
	
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
	
	
})




function updatePropList(obj, defval, result){
	
	var str = "<option value='0'>"+defval+"</option>";
	/* var str1 = "<li>"+defval+"</li>"; */
	var city = $("#city").val();
	if (result!=null)
	$(result.list).each(function(key, value){
		var sel = "";
		if(value.prop_val_id==${View.cityId}){
			sel="selected";
		}
		str += "<option value='"+value.prop_val_id+"' "+sel+">"+value.name+"</option>";
		/* str1+= "<li>"+value.name+"</li>"; */
	});
	$("#cityId").html(str);
	/* $(obj).find('li').remove();
	$(obj).find('select').after(str1); */
	/* if(defval!="市"){
		selectInit();
	} */
	
}
--%>
 	if( $('#signStr').val()!=null){
		var signStr = $('#signStr').val();
		if( signStr.indexOf('公需科目') >= 0)
			$('#sign1').prop('checked',true);
		if( signStr.indexOf('指南') >= 0)
			$('#sign2').prop('checked',true);
		if( signStr.indexOf('海外视野') >= 0)
			$('#sign3').prop('checked',true);
		if( signStr.indexOf('乡医') >= 0)
			$('#sign4').prop('checked',true);	
	}
	
	$("#level").change(function(){
		if($("#level").val()==6){
			$("#score").val(0);
			$("#score").attr("disabled","disabled");
		}else{
			$("#score").val("2.0");
			$("#score").removeAttr("disabled");
		}
	});
 function save()
{
	var siteIds = $("#siteIds").val();
	if(siteIds == ''){
		alert("请选择项目授权站点~");
		return;
	}
	
	if($('#propNames01').text() == ""){
		alert("请选择项目授权区域~");
		return ;
	}
	
	var serialNumber = $("#serialNumber").val();
	if(serialNumber.length > 100){
		alert("项目编号不可超过100字~");
		return;
	} 
	
	var level = $("#level").val();
	if(level < 1){
		alert("请选择项目级别~");
		return;
	}
	
	var score = $("#score").val();
	if(score == ''){
		alert("请输入项目学分~");
		return;
	}
	
	var signStr="";
	$(".sign").each(function(key,val){
		if($(this).prop("checked") == true)
		{
			signStr += $(this).val() + ",";
		}
		
	});
	$("#signStr").val(signStr);
	<%--
	if(signStr == ""){
		alert("请选择项目标签~");
		return;
	}
	--%>
	
	var dXiangmuks = $(".xiangmuks").val();
	var dXiangmujs = $(".xiangmujs").val();
	if(dXiangmuks == ''){
		alert("请选择项目开始时间~");
		return;
	}
	
	if(dXiangmujs == ''){
		alert("请选择项目结束时间~");
		return;
	}
	
	if(dXiangmujs <= dXiangmuks){
		alert("项目结束时间不能早于项目开始时间~");
		$(".xiangmujs").val("");
		return;
	}
	
	var break_days = $("#break_days").val();
	if(break_days == ''){
		alert("请输入项目课程的学习间隔时间~");
		return;
	}
	
	var k = 0;
	var i = 0;
	$('.kechengks').each(function(){
		
		var dKechengks = $(this).val();
		
		if(dKechengks == ''){
			 i++;
		}
	});
	
	if(i>0){
		alert("请选择课程发布开始时间~");
		return;
	}
	
	var j=0;
	$('.kechengjs').each(function(){
		var dKechengjs = $(this).val();
		if(dKechengjs == ''){
			 j++;
		}
		
		/**
		var dKechengks = $(this).prev().prev().val();
		
		if(dKechengjs == ''){
			 alert("请选择课程发布结束时间~");
			 return;
		}
		
		if (!((dKechengks > dXiangmuks) && (dKechengjs > dKechengks) && (dXiangmujs > dKechengjs))){
			if ((dKechengks != 0) && (dKechengjs != 0)){
				k = 1;
				return;
			}
		}
		
		**/
	});
	
	if(j>0){
		alert("请选择课程发布结束时间~");
		return;
	}
	
	$('.kechengjs').each(function(){
		var dKechengjs = $(this).val();
		var dKechengks = $(this).prev().prev().val();
		 
		if (dKechengks < dXiangmuks || dKechengjs < dKechengks || dKechengjs > dXiangmujs ){	 
			k = 1;
		}
		 
	});
	
	if (k == 1){
		alert("请选择正确的课程发布起止时间！");
		return;
	} 
	
	/**
	2017-04-04
	程宏业 标签名称
	*/
	var sign ="";
	if($('#sign1').prop('checked')){
		sign+=$("#sign1").val();
	}
	if($('#sign2').prop('checked')){
		sign+=$("#sign2").val();
	}
	if($('#sign3').prop('checked')){
		sign+=$("#sign3").val();
	}
	$("input[name='signStr']").val(sign);
	
	//#################taoliang####################
	var end_date = $('#end_date').val();
	var endDate = new Date((end_date).replace(/-/g,"/"));
	var date = new Date();
	if(endDate < date){
		var layerStr = '<div class="tiaojian" style="min-height:40px;"><p class="fl" style="margin-bottom:20px;margin-left:20px;"><span style="margin-top:40px;font-size:large;color:red;">当前项目授权的有效时间已过期</span></p></div>';
	    var win_w = "350px";
		var win_h = "200px";
		layer.open({
			type: 1,
			title: "项目过期提醒",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, win_h], //宽高
			content: layerStr,
			closeBtn: 0,
			btn: ['修改日期', '保存授权'],
			yes: function (index, layero) {
				layer.close(index);
			},
			btn2: function (index, layero) {
				$("#fm1").submit();
			},
			success: function(layerb, index){
				
			}
		});
	}else{
		$("#fm1").submit();
	}
   //#####################################

	//$("#fm1").submit();
}

	var win_w = parseInt($(window).width() / 2.5) + "px";
	var win_h = parseInt($(window).height() / 1.8) + "px";
	
	$("#propNames01").click(function(){
		var nowstatus = $("#cvSetStatus").val();
		initPropList("区域", "${ctx}/propManage/getPropListAjax.do?cvSetId="+$("#cvSetId").val(), 20, 0, 1, 1, $('#propIds'), $('#propNames01'), nowstatus);
		//initPropList("科室", "${ctx}/CVSet/CVDistribute.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
		$('.att_make01').show(); 
	});
	
	$(".portraits_").click(function(){
		var _this = $(this);
		var _next = $("input[name='code']");	
		var _thisId = $("input[name='userImage']");
		var option_maker = '<select id="sel_region1list" style="display:none;">'+"\n", li_maker ='';
		$.ajax({			
			type:'post',
			url:'${ctx}/CVSet/CVDistribute.do',
			data : 'method=region1list',
			dataType:'json',
			success:function(result){
				
			   var li_maker_temp = '';			  
			   for(var i=0; i<result.region1list.length; i++){
				   option_maker += '<option value='+result.region1list[i].prop_val_id+'>'+result.region1list[i].name+'</option>';
				   li_maker_temp += '<li>'+result.region1list[i].name+'</li>'+"\n";			   
			   }
			   li_maker = option_maker + '</select>' + li_maker_temp;
		
			   var sub_cont = 
			   '<div class="tiaojian" style="min-height:40px;padding:20px;">' +
				'<div class="clear"></div>' +
				'<div class="clear teacher_Name" style="width:500;margin:0 20px 20px 0; border: 1px solid #cfcfcf;min-height:77px;line-height:25px;">' +			
				'</div>' +
				'<input type="hidden" id="managerId"/>'+
				'<input type="hidden" id="xueke"/>'+
				'<div class="clear"></div>' +	
							
				'<p class="fl" style="margin-bottom:10px;">' +
				'<span style="width:5em;text-align:right;">省/市：</span>'+
				'<div class="fl select">' +
				'<div class="tik_position">' +
				'<b class="ml5">请选择省/市</b>' +
				'<p class="position01"><i class="bjt10"></i></p>' +
				'</div>' +
				'<ul class="list" style="display:none;">' +
					li_maker+
				'</ul>' +
				'</div>' +
				'</p>' +
				
							
				'<p class="fl cas_anniu" style="margin-left:20px"><a href="javascript:void(0);" class="fl btn1" style="width:70px;">添加</a></p>' +
				
				'<div class="clear"></div>' +
			    '<p class="fl" style="margin-bottom:10px;">' +				
				'<span style="width:5em;text-align:right;">区/县：</span>' +
				
				'<div class="clear"  style="border: 1px solid #cfcfcf;height:100px;padding:10px;margin-top:10px;line-height:25px; overflow: auto;">' +
				
				'<div id="str_user_image"></div>'+
				'</p>' + 			
				'</div>';
			
		layer.open({
			
			type: 1,
			title: "授权区域",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, 466], //宽高
			content: sub_cont,
			closeBtn: 0,
			btn: ['确定', '取消'],
			yes: function (index, layero) {
				var strName = '', strId = '';				
					var k='';
					if($('#xueke').val() != ""){
						k=$('#xueke').val().split(",");
					}
					var num_code = '', code_='', year = '', month = '', day = '', randNum = '';	
					// 
						$.ajax({
							type:'post',
							url:'${ctx}/CVSetManage.do',
							data:'mode=pinyin&hanyuStr='+k[0],
							dataType:'text',
							success:function(Result){
								code_ = Result;
								for(var i=0; i<5; i++){
									randNum += Math.round(Math.random()*9);
								}
								date = new Date();					
								year = date.getFullYear();
								month = (date.getMonth()+1).toString();
								day = date.getDate().toString();					
								if(day.length == 1) day= "0" + day;	
								if(month.length == 1) month= "0" + month;	
								if(code_ == "") {
									alert("没有关联区域!");
									return;
								}		
								num_code = code_ + '-' + year.toString() + month.toString() + day.toString() + '-' + randNum.toString();
								
								_this.val($('.teacher_Name').text().slice(0, -1));
								_thisId.val($('#managerId').val());						
								_next.val(num_code);	
								layer.close(index);
								
							
							}
						});
			},
			success: function(layerb, index){				
				$('.select').click(function(){
					$('.list').css('display","none');
					$(this).find('ul').show();
				});
				$('.list li').click(function(){
					var str=$(this).text();
					$(this).parent().parent().find('div').find('b').text(str);
					$(this).parent().find('option').prop('selected', '');
					$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
					$('.list').slideUp(50);
					var str_user_image = '';var str_user_image_id = '<select id="userImage_id" style="display:none;">';
					var param = 'id='+$('#sel_region1list').val();
					// 选择省市时加载区县
					$.ajax({		    		    
					    type: 'POST',
					    url:"${ctx}/propManage/getPropListAjax.do",
					   	data:param,
					    dataType: 'JSON',
					    success: function(B){	
					    	var str_user_image_temp = '';				    	
					    	for(var i=0; i<B.list.length;i++){
					    		//str_user_image += '<input type="radio" id="chk" value="'+B.list[i].id+'"'+' name="'+B.list[i].name+'"/>'+B.list[i].name+'<br>';
					    		str_user_image += '<input type="checkbox" id="chk_ui" value="'+B.list[i].id+'"'+' name="'+B.list[i].name+'" /><span>'+B.list[i].name+'</span><br>';					    		
					    	}	
					    	$('#str_user_image').html(str_user_image);
					    					    	
						}
				    });
					
				});			
			
				
				$('.select').click(function(e){
					return false;
				});
				$(document).click(function(){
					$('.list').hide('fast');
				});
				$('.cas_anniu_4,.bjt_kt').click(function(){
				
				// 执行确定按钮将内容添加到人物画像框中
				
				// 获取选定学科里面的checkbox里的内容
				var arr=new Array();
				$(".xs_ul").find("li").each(function(){
					var $thi = $(this).find("div").find("input[type='checkbox']");
					if($thi.prop("checked") == true){
						arr.push(1);
					};
				});
				if(arr.length>1){
					alert("只能选择一个");
					return false;
				}
					var str_user_image = '';
					selectProp();
					$.ajax({
						type:'post',
						url: '${ctx}/quality/userImageManage.do',
						data:'mode=guide_userImage&id='+$('#sel_region1list').val()+'&xuekes='+$('#propIds').val()+'&propIds='+$('#propIds').val(),
						dataType:'JSON',
						success:function(xuekel){
							var str_user_image_temp = '';				    	
					    	for(var i=0; i<xuekel.result.length;i++){
					    		//str_user_image += '<input type="radio" id="chk" value="'+B.result[i].id+'"'+' name="'+B.result[i].name+'"/>'+B.result[i].name+'<br>';
					    		str_user_image += '<input type="checkbox" id="chk_ui" onClick="javascript:chk_click(this);" value="'+xuekel.result[i].id+'"'+' name="'+xuekel.result[i].name+'" /><span>'+xuekel.result[i].name+'</span><br>';					    		
					    	}	
					    	$('#str_user_image').html(str_user_image);
					    	
						}
					});
					
					$('.att_make01').hide();
				});
				$(".btn1").click(function(){		
					
					var str = ''; str2 = '';
					$('#chk_ui:checked').each(function(){
						str += '<em onClick="javascript:delem1(this);" class="delem">' + $(this).prop("name") + '</em>'+ ","; 
						str2 += $(this).val()+",";						
					});		
					$('.teacher_Name').html($('.teacher_Name').html() + str);	
					$('#managerId').val($('#managerId').val()+str2);
					$('#chk_ui:checked').each(function(){
						$(this).prop("checked", false);
					});
				});
				
			
		
			
				
				
			}
		});
			}
		});
		
	});
	
	function chk_click(obj) {
		var str = '';
		$.ajax({
			type:'post',
			url:'${ctx}/quality/userImageManage.do',
			data:'mode=xiangmu&id='+$(obj).val(),
			dataType:'JSON',
			success:function(xueke){
				for(i=0; i<xueke.result[0].departmentPropList.length; i++){
					str += xueke.result[0].departmentPropList[i].name + ",";
				}
				if($(obj).prop("checked") == true){
			
					$('#xueke').val($('#xueke').val()+str);
					
				}else{
					del_ = replaceString(str,"",$('#xueke').val());
					$('#xueke').val(del_);
				}
			}
		});
	}
	
	function delem1(obj){
		var i = $(obj).index();
		var selstr = $(obj).parent().html();
		var selarray = selstr.split(",");
		var newnarray = new Array();
		$(selarray).each(function(key, val){
			if (key != i) newnarray.push(val);
		});
		$('.teacher_Name').html(newnarray.toString());
		var deletecode = "";
		selstr = $('#managerId').val();
		selarray = selstr.split(",");
		newarray = new Array();
		$(selarray).each(function(key, val){
			if (key != i) newarray.push(val);
			else
				deletecode = val;
		});
		$('#managerId').val(newarray.toString());
		var deletecode = "";
		selstr = $('#xueke').val();
		selarray = selstr.split(",");
		newarray = new Array();
		$(selarray).each(function(key, val){
			if (key != i) newarray.push(val);
			else
				deletecode = val;
		});
		$('#xueke').val(newarray.toString());
		var checklist = $('.attr_xueke04');
		$(checklist).each(function(key, val){
			if(deletecode == $(val).prop('id'))
				$(val).siblings().eq(0).prop("checked", "");
		});
		$('.delem1').off('click');
		$('.delem1').click(function(){
			delem1($(this));
		});	
}
 
</script>
</body>
</html>