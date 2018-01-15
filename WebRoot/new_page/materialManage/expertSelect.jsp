<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理平台</title>
<link rel="stylesheet" href="${ctx}/new_page/css/reset.css" />
<link rel="stylesheet" href="${ctx}/new_page/css/index.css" />
<script type="text/javascript" src="${ctx}/new_page/js/jquery.js"></script>
</head>

<body class="bjs">
<div>
	<!-- 题库内容 -->
	<div class="center">
		<div class="tk_center01" style="min-height:120px;">
		<form id="sfrm" action="${ctx}/material/SelectExpert.do" method="POST">
			<div class="tk_zuo">
				<div class="mt10 fl tk_tixing">
					<em class="fl">专家姓名：</em>
					<input type="text"  class="fl tik_select" name="name" value="${info.name}"/>
				</div>
				<div class="mt10 mll8 fl tk_tixing">
					<span class="fl">职称：</span>
					<div class="fl tik_select">
						<div class="tik_position_1">
							<b class="ml5">请选择</b>
							<p class="tik_position01"><i class="tk_bjt10"></i></p>
						</div>
						<ul class="fl tk_list1 tk_list " style="display:none;">
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
			</div>
			<div class="clear"></div>
			<div class="tk_zuo">
				<div class="mt10 fl tk_tixing">
					<em class="fl">工作单位：</em>
					<input type="text"  class="fl tik_select" name="workUnit" value="${info.workUnit}"/>
				</div>
				<div class="mt10 mll8 fl tk_tixing">
					<span class="fl">状态：</span>
					<div class="fl tik_select">
						<div class="tik_position_1">
							<b class="ml5">请选择</b>
							<p class="tik_position01"><i class="tk_bjt10"></i></p>
						</div>
						<ul class="fl tk_list1 tk_list " style="display:none;">
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
				<div class="mt10 mll8 fl tk_tixing">
					<span class="fl">学科：</span>
					<input type="hidden" class="fl tik_select" id="groupIds" name="propIds" value="${info.propIds}"/>
					<input type="text" class="fl tik_select takuang_xk" id="groupNames" readonly name="propNames" value="${info.propNames}" />
				</div>
			</div>
			<div class="clear"></div>
			<div class="mt15 tk_you shiti" style="">
				<a href="javascript:void(0);" class="fl tk_chaxun">查询</a>
				<a href="javascript:void(0);" class="fl ml30 tk_chaxun01">清空</a>
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
				<a href="javascript:void(0);" class="fl tianjia" style="width:80px;">添加专家</a>
				<a href="javascript:window.close();" class="fl ml30" style="width:80px;">关闭</a>
			</div>
		</div>
		<div class="clear"></div> 
			<display:table name="extList" requestURI="${ctx}/expert/ExpertManage.do" id="p" class="mt10 cas_table" size="${totalCount}" pagesize="20">
				<display:column title="" style="width:5%;text-align:center">
					<input type="radio" class='chk' name="eid" value="${p.id}" id="ecid"/>
					<input type="hidden" value="${p.groupIds}">
				</display:column>
				<display:column title="专家姓名" style="width:8%;"><span id = "ex_name_${p.id}">${p.name}</span></display:column>
				<display:column title="职务" style="width:8%;">
					<c:if test="${p.office==1}">主任委员</c:if>
					<c:if test="${p.office==2}">副主任委员</c:if>
					<c:if test="${p.office==3}">秘书长</c:if>
					<c:if test="${p.office==4}">学组长</c:if>
					<c:if test="${p.office==5}">委员</c:if>
				</display:column>
 				<display:column title="学科" style="width:9%;" property="propNames"></display:column>
 				<display:column title="单位" style="width:9%;" property="workUnit"></display:column>
 				<display:column title="单位职务" style="width:8%;" property="jobName"></display:column>
 				<display:column title="手机号码" style="width:10%;" property="phone1"></display:column>
 				<display:column title="状态" style="width:5%;">
					<c:if test="${p.lockState==1}">启用</c:if>
					<c:if test="${p.lockState==2}">禁用</c:if>
				</display:column>
			</display:table>


	</div>
	</div>
		<!-- 试题内容（结束） -->
		
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

<script type="text/javascript">
var winObj = parent.opener;
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
	selectInit();
	
	//check all
		$('.chkall').click(function(){
			if ($(this).attr('checked') == 'checked')
				$('.chk').attr('checked' , 'checked');
			else
				$('.chk').removeAttr('checked');
		});
		
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
			$('#sfrm').submit();
		});
	//清空
		$('.tk_chaxun01').click(function(){
			$('#sfrm').find('input').val('');
			$('#sfrm').find('select').val('');
			$('.tik_select').find('b').text("请选择");
		});
		
	//init checkbox
		$('.chk').each(function(){
			var ids = $(this).siblings(0).val();
			var b = ids.search("${gid}");
			if (b > 0)
				$(this).remove();
		});
	//add expert
		$('.tianjia').click(function(){
			var selIds = "";
			$('.chk:checked').each(function(){
				selIds = $(this).val();
			});
			
			if(selIds == ""){
				alert("请选择专家！");
				return;
			}
			var name = "#ex_name_"+selIds;
			winObj.addExpert(selIds,$(name).text());
			parent.window.close();
		});
		
	
		$('.takuang_xk').click(function(){
			initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#groupIds'), $('#groupNames'));
			$('.att_make01').show();
		});
		
		$('.takuang_zy').click(function(){
			initPropList("专委会", "${ctx}/expert/ExpertGroupListAjax.do", 0, 0, 2, 1, $('#newgroupIds'), $('#newgroupNames'));
			$('.att_make01').show();
		});
		
		if (document.location.href.indexOf('error') != -1){
			alert ("编码重复，请输入正确的编码！");
	        document.location.href = "${ctx}/expert/ExpertManage.do?";
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

//删除
function doDelProp(id){
	if(!confirm("确定？")) return;
	var url = '${ctx}/expert/ExpertManage.do?mode=del';
	if(id!=''){
		var params = 'id=' +id;
	}

	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'text',
	    success: function(result){
		   if(result == 'success'){
		      alert('删除成功！');
		      document.location.href = document.location.href.split("#")[0];
		   }else{
		   		alert('删除失败请检查内容!');
		   };
	    }
	});
}


</script>
<script>
/////// select xueke window proc
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
			var url = ajaxurl + "?ids="+ $(_kuangcode).val() +"&mode=getFullNames";
			$.ajax({
			    url:url,
			    type: 'POST',
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
		
		var url;
		if (_initType == 24) 
			url = ajaxurl + "?type="+ _initType +"&id="+ _initId + "&sort=t.id";
		else 
			url = ajaxurl + "?type="+ _initType +"&id="+ _initId + "&mode=getListByType";
			
		$.ajax({
		    url:url,
		    type: 'POST',
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
			var url = ajaxurl + "?id=" +selid + "&mode=getListByType";
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

			var url = ajaxurl + "?id=" +selid + "&mode=getListByType";
			
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

//window control code
	$('.cas_anniu_4,.bjt_kt').click(function(){
		$('.att_make01').hide();
	});
	
	
</script>
<script type="text/javascript" src="${ctx}/new_page/js/movediv.js"></script>

</body>
</html>