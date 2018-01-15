<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理平台</title>
</head>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<%@include file="/new_page/top2.jsp"%>
<body class="bjs">
<div>
	<!-- 题库内容 -->
	<div class="center" style="margin-top:-10px;">
		<div class="tk_center01" style="min-height:140px;">
		<form name="sfrm" id="sfrm" action="${ctx}/materialn/MaterialnManage.do?mode=wb" method="POST">
			<div class="tk_zuo">
				<div class="tik_xuanze">
					<div class="mt10 fl tk_tixing">
						<em class="fl">学科：</em>					
								<input type="hidden" class="fl tik_select" id="propIds" name="propIds" value="${propIds}"/>
								<input type="hidden" class="fl tik_select" id="propNames" name="propNames" value="${propNames}"/>
								<div class="fl tik_select" id="propNames01">${propNames}</div>				
					</div>
				</div>
					<div class="mt10 mll8 fl tk_tixing">
						<span class="fl">ICD10：</span>
						<input type="hidden" class="fl tik_select" id="icdIds" name="icdIds" value="${icdIds}"/>
						<input type="hidden" class="fl tik_select" id="icdNames" name="icdNames" value="${icdNames}"/>
						<div class="fl tik_select01 takuang_xk" id="icdNames01">${icdNames}</div>
						<!-- <span class="fl">ICD10：</span> -->
						<%-- <input type="text"  id="ICD" name="ICD" class="fl tik_select" readonly value="${ICD}"/> --%>
					</div>
					<div class="mt10 mll8 fl tk_tixing">
						<span class="fl">主题：</span>
					
							<input type="hidden" class="fl tik_select" id="zutiIds" name="zutiIds" value="${zutiIds}"/>
							<input type="hidden" class="fl tik_select" id="zutiNames" name="zutiNames" value="${zutiNames}"/>
							<div class="fl tik_select02 takuang_xk01" id="zutiNames01">${zutiNames}</div>

					</div>
				</div>
				<div class="clear"></div>
				<div class="tik_xuanze">
					<div class="mt10 fl tk_tixing">
						<em class="fl">类型：</em>
						<div class="fl tik_select">
							<div class="tik_position">
								<b class="ml5">请选择</b>
								<p class="tik_position01"><i class="tk_bjt10"></i></p>
							</div>
							
							<ul class="fl tk_list1 tk_list " style="display:none;">
								<select name="type" style="display:none;">
									<option value="">请选择</option>
									<option value="1"<c:if test="${info.type==1}"> selected</c:if>>视频</option>
									<option value="2"<c:if test="${info.type==2}"> selected</c:if>>图片</option>
									<option value="3"<c:if test="${info.type==3}"> selected</c:if>>PPT</option>
									<option value="4"<c:if test="${info.type==4}"> selected</c:if>>文本</option>
								</select>
								
								<li>请选择</li>
								<li>视频</li>
								<li>图片</li>
								<li>PPT</li>
								<li>文本</li>
						</ul>
						</div>
					</div>
					<div class="mt10 mll8 fl tk_tixing">
						<span class="fl">名称：</span>
						<input type="text"  class="fl tik_select" name="name" value="${info.name}"/>
					</div>					
					<div class="mt10 mll8 fl tk_tixing">
						<span class="fl">状态：</span>
						<div class="fl tik_select">
							<div class="tik_position_2">
								<b class="ml5">请选择</b>
								<p class="tik_position01"><i class="tk_bjt10"></i></p>
							</div>
							<ul class="fl tk_list1 tk_list " style="display:none;">
								<select name="state" style="display:none;">
									<option value="">请选择</option>
									<option value="1"<c:if test="${info.state==1}"> selected</c:if>>未上传</option>
									<option value="2"<c:if test="${info.state==2}"> selected</c:if>>未审核</option>
									<option value="3"<c:if test="${info.state==3}"> selected</c:if>>审核不通过</option>
									<option value="4"<c:if test="${info.state==4}"> selected</c:if>>审核通过</option>
									<option value="5"<c:if test="${info.state==5}"> selected</c:if>>禁用</option>
								</select>
								<li>请选择</li>
								<li>未上传</li>
								<li>未审核</li>
								<li>审核不通过</li>
								<li>审核通过</li>
								<li>禁用</li>								
						</ul>
						</div>
					</div>
					<div class="clear"></div>
					<div class="tik_xuanze">
						<div class="mt10 fl tk_tixing">
							<em class="fl">入库时间：</em>
							<input type="text"  class="fl tik_select" style="width:150px;" name="upload_date" value="${upload_date}" onClick="WdatePicker()" readonly="readonly"/>
							<em class="fl" style="padding:0px 3px 0px 3px;">至</em>
							<input type="text"  class="fl tik_select" style="width:150px;" name="upload_date1" value="${upload_date1}" onClick="WdatePicker()" readonly="readonly"/>
						</div>
						<div class="mt10 fl tk_tixing" style="margin-left:150px;">
							<span class="fl">审核时间：</span>
							<input type="text"  class="fl tik_select" style="width:150px;" name="deli_date" value="${deli_date}" onClick="WdatePicker()" readonly="readonly"/>
							<em class="fl" style="padding:0px 3px 0px 3px;">至</em>
							<input type="text"  class="fl tik_select" style="width:150px;" name="deli_date1" value="${deli_date1}" onClick="WdatePicker()" readonly="readonly"/>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<!-- 高级查询 -->
				<div class="tk_yingcang" style="display:none;">
					<div class="tik_xuanze">
						<div class="mt10 fl tk_tixing" style="">
							<em class="fl">格<i style="padding:0px 14px 0px 14px;"></i>式：</em>
							<div class="fl tik_select" style="width:323px;">
								<div class="tik_position_3" style="width:323px;">
									<b class="ml5">请选择</b>
									<p class="tik_position02"><i class="tk_bjt10"></i></p>
								</div>
								
								<ul class="fl tk_list1 tk_list " style="display:none;">
									<select name="format" style="display:none;">
										<option value="">请选择</option>
										<option value="MP4"<c:if test="${info.format eq 'MP4'}"> selected</c:if>>MP4</option>
										<option value="JPG"<c:if test="${info.format eq 'JPG'}"> selected</c:if>>JPG</option>
										<option value="PNG"<c:if test="${info.format eq 'PNG'}"> selected</c:if>>PNG</option>
										<option value="DOC"<c:if test="${info.format eq 'DOC'}"> selected</c:if>>DOC</option>
										<option value="PDF"<c:if test="${info.format eq 'PDF'}"> selected</c:if>>PDF</option>
										<option value="PPT"<c:if test="${info.format eq 'PPT'}"> selected</c:if>>PPT</option>
									</select>
										<li>请选择</li>
										<li>MP4</li>
										<li>JPG</li>
										<li>PNG</li>
										<li>DOC</li>
										<li>PDF</li>
										<li>PPT</li>
								</ul>
							</div>
						</div>
						<div class="mt10 fl tk_tixing" style="margin-left:159px;">
							<span class="fl">排序规则：</span>
							<div class="fl tik_select" style="width:323px;">
								<div class="tik_position_3" style="width:323px;">
									<b class="ml5">请选择</b>
									<p class="tik_position02"><i class="tk_bjt10"></i></p>
								</div>
								<ul class="fl tk_list1 tk_list " style="display:none;">
									<select name="orderItem" style="display:none;">
										<option value="">请选择</option>
										<option value="1"<c:if test="${orderItem == 1}"> selected</c:if>>入库时间</option>
										<option value="2"<c:if test="${orderItem == 2}"> selected</c:if>>审核时间</option>
									</select>
										<li>请选择</li>
										<li>入库时间</li>
										<li>审核时间</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="clear"></div>
					<div class="tik_xuanze">
						<div class="mt10 fl tk_tixing">
							<em class="fl">简<i style="padding:0px 14px 0px 14px;"></i>介：</em>
							<input type="text"  class="fl tik_select" style="width:898px;" name="summary" value=""/>
						</div>
					</div>
					<div class="clear"></div>
					<div class="tik_xuanze">
						<div class="mt10 fl tk_tixing">
							<em class="fl">职<i style="padding:0px 14px 0px 14px;"></i>称：</em>
							<input type="hidden" name="dutyIds" id="dutyIds"  value="${dutyIds}">
							<input type="hidden" name="dutyNames" id="dutyNames"  value="${dutyNames}">
							<div class="fl tik_select03 takuang_xk" id = "dutyNames01" >${dutysNames}</div>
						</div>
						<div class="mt10 fl tk_tixing" style="margin-left:159px;">
							<span class="fl">认知水平：</span>
							<div class="fl tik_select" style="width:323px;">
								<div class="tik_position_3" style="width:323px;">
									<b class="ml5"></b>
									<p class="tik_position02"><i class="tk_bjt10"></i></p>
								</div>
								<ul class="fl tk_list" style="display:none;width:323px;">
									<select name="cognize" style="display:none;">
										<option value="">请选择</option>
										<option value="1"<c:if test="${info.cognize==1}"> selected</c:if>>识记</option>
										<option value="2"<c:if test="${info.cognize==2}"> selected</c:if>>理解</option>
										<option value="3"<c:if test="${info.cognize==3}"> selected</c:if>>掌握</option>
										<option value="4"<c:if test="${info.cognize==4}"> selected</c:if>>应用</option>
									</select>
										<li>请选择</li>
										<li>识记</li>
										<li>理解</li>
										<li>掌握</li>
										<li>应用</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="clear"></div>
					<div class="tik_xuanze">
						<div class="mt10 fl tk_tixing">
							<em class="fl">来<i style="padding:0px 14px 0px 14px;"></i>源：</em>
							<input type="hidden" name="laiIds" id="laiIds" value="${laiIds}">
							<input type="hidden" name="laiNames" id="laiNames" value="${laiNames}">
							<div class="fl tik_select03 takuang_xk" id = "laiNames01">${laiNames}</div>
						</div>
						<div class="mt10 fl tk_tixing" style="margin-left:159px;">
							<span class="fl">其他来源：</span>
							<input type="text"  class="fl tik_select" style="width:323px;" name="otherSource" value=""/>
						</div>
					</div>
					<div class="clear"></div>
					<div class="mt15 tk_you">
						<a href="javascript:materialSearch();" class="fl tk_chaxun mat1_baochun">查询</a>
						<a href="#" class="fl ml30 tk_chaxun01 mat1_baochun">基本查询</a>
					</div>
					<div style="height:40px;"></div>
				</div>
			</div>
			<div class="clear"></div>
			<div class="mt15 tk_you shiti" style="margin-top:-5px;">
				<a href="javascript:materialSearch();" class="fl tk_chaxun">查询</a>
				<a href="#" class="fl ml30 tk_chaxun01 mat1_chaxun">高级查询</a>
			</div>
			<div class="clear"></div>
			</form>
		</div>
		<div class="clear"></div>
	</div>
	<div class = "tkbjs"></div>
	
	<!--  -->
	<div class="center none" style="">
	<div class="tk_center01">
	<form id = "listfrm" name = "listfrm" method = "POST">
		<div class="mt10 kit1_tiaojia">
		</div>
		<div class="clear"></div> 
			<display:table name="mtlList" requestURI="${ctx}/materialn/MaterialnManage.do?mode=wb" id="p" class="mt10 cas_table" size="${totalCount}" pagesize="300">
				<display:column title="素材编号 " style="width:4%;text-align:center">${p.id}</display:column>
				<display:column title="素材名称" style="width:10%;">					
					${p.name}
				</display:column>
				<display:column title="学科" style="width:8%;" >
					<c:forEach items="${p.prop_map.get('3')}" var="propxk">
						${propxk.prop_val_name},
					</c:forEach>					
					<c:forEach items="${p.prop_map.get('4')}" var="propxk">
						${propxk.prop_val_name},
					</c:forEach>					
					<c:forEach items="${p.prop_map.get('5')}" var="propxk">
						${propxk.prop_val_name},
					</c:forEach>
				</display:column>
 				<display:column title="ICD10" style="width:6%;" >
 					<c:forEach items="${p.prop_map.get('11')}" var="propxk">
						${propxk.prop_val_name},
					</c:forEach>
				</display:column>
 				<display:column title="素材格式" style="width:8%;" property="format" ></display:column>
 				<display:column title="入库时间" style="width:8%;" property="upload_date"></display:column>
 				<display:column title="审核时间" style="width:8%;" property="deli_date"></display:column>
 				<display:column title="素材类型" style="width:8%;">
 					<c:if test="${p.type == 1}">视频</c:if>
 					<c:if test="${p.type == 2}">图片</c:if>
 					<c:if test="${p.type == 3}">PPT</c:if>
 					<c:if test="${p.type == 4}">文本</c:if>
 				</display:column>
 				<display:column title="状态" style="width:6%;">
 					<c:if test="${p.state == 1}">未上传</c:if>
 					<c:if test="${p.state == 2}">未审核</c:if>
 					<c:if test="${p.state == 3}">审核不通过</c:if>
 					<c:if test="${p.state == 4}">审核通过</c:if>
 					<c:if test="${p.state == 5}">禁用</c:if>
 				</display:column>
 				<display:column title="审核意见" style="width:8%;" property="deli_opinion"></display:column>
 				<display:column title="审核人" style="width:6%;" property="deli_man"></display:column>
 				<display:column title="操作" style="width:13%;" >
					<a href="${ctx}/material/MaterialManage.do?mode=detail&id=${p.id}" >查看</a>
					<a href="javascript:void(0);" onclick="selkectResource('${p.savePath}','${p.name}');">选择</a>
 				</display:column>
			</display:table>
		

		<div class="clear"></div> 
		<!-- 分页 -->
		</form>
	</div>
	</div>
		<!-- 试题内容（结束） -->
<div class="toumingdu att_make01" style="display:none;z-index:10000;">
	<div class="tk_center09" style="margin:7% auto;margin-left:30%">
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
			<div class="fl xscx_divtext" style="display:none;">
				<input type="text" id="xscx_text" style="margin:8px 13px;height:25px;min-width:120px"/>
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
		
</div>
<script type="text/javascript">
var filefmtArray = [[],["MP4","WMV"],["JPG","PNG"],["PPT"],["DOC","PDF"]];
	var ajaxurl;

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
	
		var type = "${info.type}";
		if (type == "") type = 0;
		else
			type = eval(type);
		var format = "${info.format}";
		str = '<select name="format" style="display:none;"><option value="">请选择</option>';
		for (i=0; i<filefmtArray[type].length; i++){
			if (filefmtArray[type][i] == format)
				str += '<option value="'+filefmtArray[type][i]+'" selected>'+filefmtArray[type][i]+'</option>';
			else 
				str += '<option value="'+filefmtArray[type][i]+'" >'+filefmtArray[type][i]+'</option>';
		}
		
		str+='</select><li>请选择</li>';
		for (i=0; i<filefmtArray[type].length; i++){
			str += '<li>'+filefmtArray[type][i]+'</li>';
		}
		
		$('#format').html(str);
	
	selectInit();
		
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
	
		$('.cas_anniu_4,.bjt_kt').click(function(){
			$('.att_make01').hide();
		});
			//更多查询
		$('.mat1_chaxun').click(function(){
			$('.tk_yingcang').show();
			$('.shiti').hide();
			//$('.none').hide();
		});
		//基本查询
		$('.mat1_baochun').click(function(){
			$('.tk_yingcang').hide();
			$('.shiti').show();
			//$('.none').show();
		});
		$('#propNames01').click(function(){
			initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
			$('.att_make01').show();
		});		
		$('#zutiNames01').click(function(){
			initPropList("主题","${ctx}/propManage/getPropListAjax.do", 7, 0, 1, 0, $('#zutiIds'), $('#zutiNames01'));
			$('.att_make01').show();
		});
		$('#icdNames01').click(function(){
			initPropList("ICD10","${ctx}/propManage/icdList.do" ,10, 0, 1, 0, $('#icdIds'), $('#icdNames01'));
			$('.att_make01').show();
		});	
		$('#laiNames01').click(function(){
			initPropList("来源","${ctx}/propManage/sourceList.do", -1, 0, 1, 0, $('#laiIds'), $('#laiNames01'));
			$('.att_make01').show();
		});
		$('#dutyNames01').click(function(){
			initPropList("职称","${ctx}/propManage/getPropListAjax.do", 24, 0, 1, 0, $('#dutyIds'), $('#dutyNames01'));
			$('.att_make01').show();
		});
		$('#cognizeNames01').click(function(){
			initPropList("认知水平","${ctx}/propManage/getPropListAjax.do", 8, 0, 1, 0, $('#cognizeIds'), $('#cognizeNames01'));
			$('.att_make01').show();
		});
		
		$('#type li').click(function(){
			var type = $(this).index()-1;
			
			str = '<select name="format" style="display:none;"><option value="">请选择</option>';
			for (i=0; i<filefmtArray[type].length; i++){
				str += '<option value="'+filefmtArray[type][i]+'">'+filefmtArray[type][i]+'</option>';
			}
			
			str+='</select><li>请选择</li>';
			for (i=0; i<filefmtArray[type].length; i++){
				str += '<li>'+filefmtArray[type][i]+'</li>';
			}
			
			$('#format').html(str);
			selectInit();
		});
});

//禁用
function doLock(id){
	var url = '${ctx}/material/MaterialManage.do?mode=lock';
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
		      alert('操作成功！');
		      document.location.href = document.location.href.split("#")[0];
		   }else{
		   		alert('操作失败!');
		   };
	    }
	});
}

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
				//check all
		$('.chkall').click(function(){
			if ($(this).attr('checked') == 'checked')
				$('.chk').attr('checked' , 'checked');
			else
				$('.chk').removeAttr('checked');
		});
}
	
function doDelMat(id, state){
	if (state == 2 || state == 5) {
			
		if(!confirm("您确定要删除吗？"))return;
		var url = '${ctx}/material/MaterialManage.do?mode=del';
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
			   		alert('删除失败！请检查内容!');
			   };
		    }
		});
	}else {
		alert('只能删除未审核或者禁用状态的素材！');
		return;
	}
}
/*
function doForb(id){
	var url = '${ctx}/material/MaterialManage.do';
	var params = 'mode=update';
	params += '&state=4';
	params += '&id=' + id;

	$.ajax({
		type: 'POST',
		url: url,
		data : params,
		dataType: 'text',
		success : function (b){
	      	alert('成功！');
	      	document.location.href = document.location.href.split("#")[0];
		},
		error: function(){
		   	  alert('失败.请检查属性的关联!');
		}
	});
}
*/
function materialSearch() {
	var kindOfQuestion = $('#SEL_KindOf').text();
	$('#question_label_name').val(kindOfQuestion);
	
	var stateOfQuestion = $('#SEL_State').text();
	$('#state').val(stateOfQuestion);
	
	var isMedia = $('#SEL_Media').text();
	$('#isMedia').val(isMedia);
	$('#propNames').val($('#propNames01').text());
	$('#zutiNames').val($('#zutiNames01').text());
	$('#dutyNames').val($('#dutyNames01').text());
	$('#cognizeNames').val($('#cognizeNames01').text());
	$('#laiNames').val($('#laiNames01').text());
	$('#icdNames').val($('#icdNames01').text());
	$('#sfrm').submit();
}

if ($('#SEL_KindOf').text() == '') $('#SEL_KindOf').text("请选择");
if ($('#SEL_State').text() == '') $('#SEL_State').text("请选择");
if ($('#SEL_Media').text() == '') $('#SEL_Media').text("请选择");
if ($('#SEL_OrderBy').text() == '') $('#SEL_OrderBy').text("请选择");




</script>

<script>

	var kuangcode;
	var kuang;
	var ajaxurl;
	var initType;
	var initId;
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
		initId = _initId;
		/* if(_titile == "职称"){
			$('.xs_biaoti .xs_er .attr_xueke01').text('职称');
		} */
		
		if(_initType > 0 && _initType < 6){
			$('.xs_biaoti .xs_er .attr_xueke01').text('一级学科');
			initsubmenu="一级学科";
			viewpath = true;
		}else{
			$('.xs_biaoti .xs_er .attr_xueke01').text(_title);
			initsubmenu=_title;
			viewpath = false;
		}
		

		$('.xs_kuangcode').text($(_kuangcode).val());

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
			   /* if(result != ''){
			   		updatePropList(result);
			   }; */
			   debugger;
		    	 if(result != ''){
				   		//职称
				   		if (_initType == 24){
				   			$('.xs_zhcombo').show();
				   			$('.xscx_divtext').hide();
				   			updateZHLXCombo(result);
				   			updatePropList("");
				   		}
				   		else if (_initType == 10 || _initType == 7 || _initType == -1 ){
				   			$('.xscx_divtext').show();
				   			$('.xs_zhcombo').hide();
				   			$('#xscx_text').val('');
				   			updatePropList("");
				   		}
				   		else{
				   			$('.xscx_divtext').hide();
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

	$('#xscx_text').keyup(function(e){
		var keyword = $(this).val().trim();		
		if (keyword == ""){
			updatePropList("");
			return false;
		}
		
		switch(e.keyCode){
			case 37:
			case 38:
			case 39:
			case 40:
			case 13:
			case 9:
				return false;
		}		
		var url = ajaxurl + "?type="+ initType +"&id="+ initId + "&mode=getListByType" ;
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    data: 'sname=' + keyword ,
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

$('.cas_anniu_4,.bjt_kt').click(function(){
	$('.att_make01').hide();
});

function selectProp(){
		$(kuangcode).val($('.xs_kuangcode').text());
		$(kuang).text($('.xs_kuang').text());
		var url = "${ctx}/propManage/getPropListAjax.do";
		var ids = $(kuangcode).val();
		
		if ($(kuangcode).prop('id') == "propIds"){
		$('#ICD').val("");
		if(ids != null && ids !=""){
			var params = "mode=getICD&ids="+ids;  
			$.ajax({
				type: 'POST',
				url: url,
				data : params,
				dataType: 'text',
				success : function (newId){
					$('#ICD').val(newId);
				},
			});
		}
		}
	}
	
function exportMaterial(){
	 if (!checkMaterialIds()) {
            alert("请选择要导出的素材!");
            return false;
        }
        document.getElementById("listfrm").action = "${ctx}/materialManage/downLoadMaterialListFile.do";
        document.getElementById("listfrm").submit();
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
function downloadFile(id) {
	var url = "${ctx}/material/MaterialManage.do?mode=downloadFile&sel_id=" + id;
	window.open(url, '_blank');
}

function selkectResource(obj,objName){
	var wbType = "${wbType}";
	//判断是视频还是图片
	if(wbType=="vio"){
		if(obj==null || obj==''){
			obj = '034282A1F1B06B299C33DC5901307461';
		}
		var videoAttr1 = {
			      //视频地址
			      url: obj,
			      //视频宽高值， 单位px
			      width: 200,
			      height: 100,
			      title: objName
	    }
		//ue 是编辑器实例
		//该方法将会向编辑器内插入两个视频
		window.parent.ue.execCommand( 'insertvideo', [ videoAttr1] );
		var dialog = window.parent.ue.getDialog("insertvideo");
		//判断该单元任务点是否选中
		if(window.parent.isRWD=='true'){
			//调用父页面的视频完成指标函数
			window.parent.comVideo();
		}
		dialog.close();
	}else if(wbType=="img"){
		if(obj==null || obj==''){
			obj = "http://file02.16sucai.com/d/file/2015/0410/9d79a9a9581f5e70dbbe2b88e86fab21.jpg";
		} 
	    var imageAttr1 = {
	    		src:obj,
	    	    width:'100',
	    	    height:'100',
	    	    title: objName
	    }
	    window.parent.ue.execCommand( 'insertimage', [ imageAttr1] );
	    var dialog = window.parent.ue.getDialog("insertimage");
	    //判断该单元任务点是否选中
	    if(window.parent.isRWD=='true'){
			//调用父页面的视频完成指标函数
			window.parent.comVideo();
		}
	    dialog.close();
	} 
}

</script>
</body>
</html>