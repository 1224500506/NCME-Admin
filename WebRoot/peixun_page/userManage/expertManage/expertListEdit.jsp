<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%-- <%@include file="/new_page/top.jsp"%> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<%-- <%@include file="/commons/taglibs.jsp"%> --%>
</head>
<%@include file="/peixun_page/top.jsp"%>


<link rel="stylesheet" href="${ctx}/new_page/css/reset.css" />
<link rel="stylesheet" href="${ctx}/new_page/css/index.css" />
<script type="text/javascript" src="${ctx}/new_page/js/jquery.js"></script>
<style type="text/css">
	#gotoPage{
		cursor: pointer;
	}
</style>
<body class="bjs" onkeydown="entersearch();">
<div>
	<!-- 题库内容 -->
	<div class="center">
		
			<h2 style="font-size:16px; font-weight:600;">设置【导航栏 &gt; 名师 】</h2>
		
		<div class="tk_center01" style="min-height:160px;">
		<form id="sfrm" action="${ctx}/expert/ExpertManage.do?mode=listEdit" method="POST">
			<div class="tk_zuo">
				<div class="mt10 fl tk_tixing">
					<span class="fl">专家姓名：</span>
					<input type="text"  class="fl tik_select" name="name" value="${info.name}"/>
				</div>
				<div class="mt10 mll8 fl tk_tixing">

					<span class="fl">加入专委会：</span>
					<input type="hidden" class="fl tik_select" id="newgroupIds" name="groupIds" value="${info.groupIds}"/>
					<input type="hidden" id="gIds" name="groupNames" value="${info.groupNames}">
					<div class="fl tik_select takuang_xk" id="newgroupNames">${info.groupNames}</div>
				</div>
		
 				<div class="mt10 mll8 fl tk_tixing">
					<span class="fl">担任职务：</span>
					<div class="fl tik_select">
						<div class="tik_position_2">
							<b class="ml5">请选择</b>
							<p class="tik_position01"><i class="tk_bjt10"></i></p>
						</div>
						<ul class="fl tk_list1 tk_list " style="display:none;">
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
				<div class="mt10 mll8 fl tk_tixing">
					<span class="fl">工作单位：</span>
					<input type="text"  class="fl tik_select" name="workUnit" value="${info.workUnit}"/>
				</div>
			</div>
			<div class="clear"></div>
			<div class="tk_zuo">
				<div class="mt10 fl tk_tixing">
					<span class="fl">学科：</span>
					<input type="hidden" class="fl tik_select" id="groupIds" name="propIds" value="${info.propIds}"/>
					<input type="hidden" name="propNames" id="gNames" value="${info.propNames}">
					<div class="fl tik_select takuang_xk" id="groupNames">${info.propNames}</div>
				</div>
				<div class="mt10 mll8 fl tk_tixing">
					<span class="fl">职称：</span>
					<input type="hidden" class="fl tik_select" id="job" name="job" value="${info.job}"/>
					<input type="hidden" name="jobName" id="jobName" value="${info.jobName}">
					<div id="jobNames" class="fl tik_select takuang_job">${info.jobName}</div>
				</div>
		
			</div>
			<div class="clear"></div>
			<div class="tk_zuo">
				
			</div>
			<div class="clear"></div>
			<div class="mt15 tk_you shiti" style="">
				<a href="javascript:void(0);" class="fl tk_chaxun">查询</a>
			<a href="${ctx}/contentManage/editionManage.do?mode=list" class="fl tk_chaxun">返回</a>
			</div>
			<div class="clear"></div>
			</form>
		</div>
		
	</div>
	
	<div class="tkbjs"></div>
	<!--  -->
	<div class="center none" style="">
	<div class="tk_center01">
		<div class="mt10 kit1_tiaojia">
			<div class="fr cas_anniu">
				<a href="javascript:void(0);" onclick="doBatchDelProp();" class="fl" style="width:95px;margin-right:15px;">批量绑定</a>
			</div>
		</div>
		<div class="clear"></div> 
			<display:table name="extList" requestURI="${ctx}/expert/ExpertManage.do?mode=listEdit" id="p" class="mt10 cas_table" size="${totalCount}" pagesize="20"  sort="list" defaultsort="1" defaultorder="descending">
				
				<display:column style="display:none;" property="id" sortable="true"></display:column>
				
				 <display:column title="<input type='checkbox' class='chkall' name='selectall'/>" style="width:4%;">
            		<input type='checkbox' class='chk' value="${p.id}" name='stuCheckBox'/>
            	</display:column>  
				<display:column title="序号" style="width:5%;">${p_rowNum}</display:column>
				<display:column title="专家姓名" sortable="true" style="width:8%;" property="name"></display:column>
				<display:column title="所属专委会" style="width:18%;" property="groupNames"></display:column>
				<display:column title="职务" sortable="true" property="officeNames" style="width:8%;"></display:column>
 				<display:column title="学科" style="width:9%;" property="propNames"></display:column>
 				<display:column title="单位" sortable="true" style="width:9%;" property="workUnit"></display:column>
 				<display:column title="单位职务" sortable="true" style="width:8%;" sortProperty="workUnit_office">
					<c:if test="${p.workUnit_office==1}">院长</c:if>
					<c:if test="${p.workUnit_office==2}">副院长</c:if>
					<c:if test="${p.workUnit_office==3}">主任</c:if>
					<c:if test="${p.workUnit_office==4}">副主任</c:if>
					<c:if test="${p.workUnit_office==5}">所长</c:if>
					<c:if test="${p.workUnit_office==6}">副所长</c:if>
					<c:if test="${p.workUnit_office==7}">护士长</c:if>
					<c:if test="${p.workUnit_office==8}">校长</c:if>
					<c:if test="${p.workUnit_office==9}">副校长</c:if>
					<c:if test="${p.workUnit_office==10}">书记</c:if>
					<c:if test="${p.workUnit_office==11}">副书记</c:if>
					<c:if test="${p.workUnit_office==12}">处长</c:if>
					<c:if test="${p.workUnit_office==13}">副处长</c:if>
					<c:if test="${p.workUnit_office==14}">科长</c:if>
					<c:if test="${p.workUnit_office==15}">副科长</c:if>
					<c:if test="${p.workUnit_office==16}">副护士长</c:if>
					<c:if test="${p.workUnit_office==17}">秘书</c:if>
 				</display:column>
				<display:column title="任职状态" style="width:5%;">
					<c:if test="${p.state==1}">在职</c:if>
					<c:if test="${p.state==2}">离职</c:if>
				</display:column>
 				<display:column title="手机号码" sortable="true" style="width:10%;" property="phone2"></display:column>
 				
 				<display:column title="操作" style="width:15%;">
					<input type="hidden" value="${p.lockState}" id="lockState">
					<a href="${ctx}/expert/ExpertManage.do?mode=detailEdit&id=${p.id}" >查看</a>
					<a href="javascript:doLock(${p.id});" >绑定</a>
 				</display:column>
			</display:table>


	</div>
	</div>
		<!-- 试题内容（结束） -->
		
</div>


<script type="text/javascript">
$(function(){
	
	$('.chkall').click(function(){
		if ($(this).attr('checked') == 'checked')
			$('.chk').attr('checked' , 'checked');
		else
			$('.chk').removeAttr('checked');
	});
	
	//select menu
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "mar_left0" + menuindex;
		$('.tk_mainmenu>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
		
		$('table thead tr th').eq(0).hide();
	
	//下拉框
	selectInit();
		
	//when select a group at form, get subgroups and terms of the group.
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
			$('.tk_list').hide('fast');
		});
	
	//查询
		$('.tk_chaxun').click(function(){
			$('#gIds').val($('#newgroupNames').text());
			$('#gNames').val($('#groupNames').text());
			$('#jobName').val($('#jobNames').text());
			$('#sfrm').submit();
		});
	//清空
		$('.tk_chaxun01').click(function(){
			$('#sfrm').find('input').val('');
			$('#sfrm').find('select').val('');
			$('#newgroupNames').text('');
			$('#groupNames').text('');
			$('#job').text('');
			$('#jobNames').text('');
			$('.tik_select').find('b').text("请选择");
		});
		
		
		$('#groupNames').click(function(){
			initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#groupIds'), $('#groupNames'));
			$('.att_make01').show();
		});
		
		$('#newgroupNames').click(function(){
			initPropList("专委会", "${ctx}/expert/ExpertGroupListAjax.do", 0, 0, 2, 1, $('#newgroupIds'), $('#newgroupNames'));
			$('.att_make01').show();
		});
		
		$('.takuang_job').click(function(){
			initPropList("职称", "${ctx}/propManage/getPropListAjax.do", 24, 0, 1, 1, $('#job'), $('#jobNames'));
			$('.att_make01').show();
		});
		
		if ("${param.error}" == "1"){
			alert ("编码重复，请输入正确的编码！");
	        //document.location.href = "${ctx}/expert/ExpertManage.do";
	    }
		if ("${param.error}" == "2"){
			alert ("账号重复，请输入别的账号！");
	        //document.location.href = "${ctx}/expert/ExpertManage.do?";
	    }
		
});

	//下拉框
	function selectInit(){

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
		
		$('.tk_list option:selected').each(function(){
			var str=$(this).text();
			$(this).parent().parent().parent().find('b').text(str);
		});
	}

//禁用or启用
function doLock(id){
	var lll = "";
	if(id>0){
		lll = "绑定";
	}else{
		lll = "解绑";
	}
	if(!confirm("确定" + lll + "？")) return;
	var url = '${ctx}/expert/ExpertManage.do?mode=lockEdit';
	if(id!=''){
		var params = '&id=' + id;
	}

	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'text',
	    success: function(result){
		   if(result == 'success'){
		      alert('操作成功！');
		      document.location.href = document.location.href.split("#")[0];
		   }else if(result == 'isUse'){
		   		alert('该专家已被使用，无法绑定！');
		   }else{
		   		alert('操作失败!');
		   }
	    }
	});
}

//[批量绑定] 
function doBatchDelProp() {
	var cardTypeIds = $(".chk[type='checkbox']:checked");
	if(cardTypeIds.length == 0){
		alert("请先选择绑定的选项!");
	    return;
	}
	var selectedIds = '';
	if(cardTypeIds != '' && cardTypeIds.length >0 ){
		for(var i=0; i<cardTypeIds.length; i++){
			selectedIds += cardTypeIds[i].value + ',';
		}
	}
	if(confirm("确认绑定吗?")){
		var p = {'typeIds':selectedIds};
		$.post("${ctx}/expert/ExpertManage.do?mode=lockEditAll", p,
  			function(data){
  				if(data != "fail"){
  					alert("所选项绑定成功!");
  				}
  				else{
  					alert(data);
  					alert("该选项已被绑定，不能重复绑定！");
  				}
  				document.location.href = document.location.href.split("#")[0];
  		});
	}
}

function checkMaterialIds() {
    var checked = false;
    $("input[name='idArr']").each(function () {
        if ($(this).is(':checked')) {
            checked = true;
            return false;
        }
    });
    return checked;
}


function updatePropList(result){
	var kuangcode_id = $(kuangcode).prop('id');
	if (kuangcode_id == 'job'){
		
		var str = "";
		var check = eval($('.xs_checklvl').text());
		var select = eval($('.xs_selectlvl').text());
		$(result.list).each(function(key, value){
			str += "<li><div class=''>";
			if (check <= value.type)
				str += '<input class="fl" style="margin-top:5px;" type="radio" name="_selradio">';

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
		initPropWndRadio();
	}
	else
	{
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
}
	
function initPropWndRadio(){
	$('.xs_ul input[type="radio"]').off('click');
	$('.xs_ul input[type="radio"]').click(function(){
		var p = $(this).parent().find('.attr_xueke04').eq(0);
		var id = $(p).prop('id');
		var propname = '<em class="delem">';
		if (viewpath) propname+= $('.xs_biaoti').text().trim();
		propname+= $(p).find('em').text() + '</em><br>';
		
		if ($(this).prop('checked')){
			$('.xs_kuangcode').text(id.toString());

			$('.xs_kuang').html(propname.toString().replace(dotexp,"<br>"));
		}
		else{
			$('.xs_kuangcode').text('');
			$('.xs_kuang').html('');
		}
		
		$('.delem').off('click');
		$('.delem').click(function(){
			delem($(this));
		});
	
	});
	
	//selected item mark checked.
	$('.xs_ul input[type="radio"]').each(function(key, val){
		var p = $(this).parent().find('.attr_xueke04').eq(0);
		var id = $(p).prop('id');
		var selstr = $('.xs_kuangcode').text();
		var selarray = selstr.split(",");
		var idx = selarray.indexOf(id);
		
		if (idx>=0) $(this).prop("checked", true);
	});
}

function selectProp(){
	var kuangcode_id = $(kuangcode).prop('id');
	if (kuangcode_id == 'job'){
		$(kuangcode).val($('.xs_kuangcode').text());
		var selPropHtml = $('.xs_kuang').html().replace(brexp,"");
		$('.xs_kuang').html(selPropHtml);
		$(kuang).text($('.xs_kuang').text());
	}
	else{	
		$(kuangcode).val($('.xs_kuangcode').text());
		var selPropHtml = $('.xs_kuang').html().replace(brexp,",");
		$('.xs_kuang').html(selPropHtml);
		$(kuang).text($('.xs_kuang').text());
	}
}

function entersearch(){
   var event = window.event || arguments.callee.caller.arguments[0];
   if (event.keyCode == 13) {
	    $('#gIds').val($('#newgroupNames').text());
		$('#gNames').val($('#groupNames').text());
		$('#jobName').val($('#jobNames').text());
		$('#sfrm').submit();
   }
}
$('input[name="selectall"]').click(function(){  
    //alert(this.checked);  
    if($(this).is(':checked')){  
        $('input[name="stuCheckBox"]').each(function(){  
            //此处如果用attr，会出现第三次失效的情况  
            $(this).prop("checked",true);  
        });  
}else{  
    $('input[name="stuCheckBox"]').each(function(){  
            $(this).removeAttr("checked",false);  
        });  
        //$(this).removeAttr("checked");  
}  
      
}); 
</script>
</body>
</html>