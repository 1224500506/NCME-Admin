<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/commons/taglibs.jsp"%>
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
		<title>培训后台</title>
	</head>
<%@include file="/peixun_page/top2.jsp"%>

<body>
<!-- 查询条件 -->
<div class="center">
	<form action="${ctx}/paperManage/paperList.do" id="fm" name="fm" method="post">
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl">
			<span>选择目录：</span>
			 	<input type="hidden" id="curTypeId" name ="typeId" value="${typeId}"/>
				<input type="hidden" id="typeNames" name="typeName" value="${typeName}"/>
				<div  id="typeNames01" class="duouan">${typeName}</div>
		 </p>
		<p class="fl" style="margin-left:60px;">
			<span>试卷名称：</span>
			<input type="text" name="name" id="name" value="${name}" />
		</p>
		<p class="fl" style="margin-left:60px;">
			<span>组卷策略：</span>
			<div class="fl select" >
				<div class="tik_position">
					<b class="ml5">请选择</b>
					<p class="position01"><i class="bjt10"></i></p>
				</div>
				<ul class="fl list" style="display:none;">
					<select name="paperMode" id="paperMode" style="display:none;">
						<option value="">请选择</option>
						<option value="1"<c:if test="${paperMode==1}"> selected</c:if>>快捷策略</option>						
						<option value="2"<c:if test="${paperMode==2}"> selected</c:if>>精细策略</option>
						<option value="4"<c:if test="${paperMode==4}"> selected</c:if>>卷中卷</option>
						<option value="3"<c:if test="${paperMode==3}"> selected</c:if>>手工组卷</option>
					</select>
					<li>请选择</li>
					<li>快捷策略</li>
					<li>精细策略</li>
					<li>卷中卷</li>
					<li>手工组卷</li>
				</ul>
			</div>
		</p>
	</div>
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl" style="">
			<span>创建时间：</span>
			<input type="text" name="createDateFrom" id="createDateFrom" value="${createDateFrom}" onClick="WdatePicker()" style="width:85px;"/>
			<span style="padding:0px 7px 0px 7px;">至</span>
			<input type="text" name="createDateTo" id="createDateTo" value="${createDateTo}" onClick="WdatePicker()" style="width:85px;"/>
		</p>
		<div class="fl cas_anniu">
			<a href="javascript:$(fm).submit();" class="fl" style="width:70px;margin-left:130px;">查询</a>
		</div>
	</div>
	</form>
</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
		<div class="clear"></div>
		<form action="" id="formB" name="formB" method="post">
			<display:table id="item" name="page" requestURI="${ctx}/paperManage/paperList.do" style="width:100%;" decorator="com.hys.exam.displaytag.OverOutWrapper" class="mt10 table" excludedParams="msg" size="${totalCount}" pagesize="300">		
				<display:column property="name" title="名称" style="width:20%;text-align:center"></display:column>
				<display:column title="组卷策略" style="width:8%;">
					<c:choose>
						<c:when test="${item.paper_mode eq 1}">
							快捷策略
						</c:when>
						<c:when test="${item.paper_mode eq 2}">
							精细策略
						</c:when>
						<c:when test="${item.paper_mode eq 3}">
							手工组卷
						</c:when>
						<c:when test="${item.paper_mode eq 4}">
							卷中卷
						</c:when>
						<c:otherwise>&nbsp;</c:otherwise>
					</c:choose>
				</display:column>
				<display:column property="paper_score" title="总分" style="width:5%;text-align:center"></display:column>
				<display:column property="question_num" title="试题数量" style="width:8%;text-align:center"></display:column>
				<display:column title="创建时间" style="width:8%;">
					<c:out value="${fn:substring(item.create_date,0,10)}" />
				</display:column>
				<display:column property="type_name" title="所属试卷目录" 	style="width:11%;text-align:center"></display:column>
				<display:column title="操作" style="width:20%;text-align:center;">
					<a href="javascript:void(0);" onclick="selectSJ('${item.id}');">选择</a>
					<a href="${ctx}/paperManage/paperView.do?handle=view&paperId=${item.id}" class="">查看</a>
				</display:column>
			</display:table>
		</form>

		<div class="clear"></div> 
	</div>
</div>
<!-- 调整 -->
<div class="toumingdu make02" style="display:none;">
	<div class="lec_center" style="height:300px;">
		<div style="padding-top:50px;">
			<div class="lc_shengcheng">
				<span>当前目录：</span>
				<input type="text" id="now" placeholder=" 中医" value="${typeName}"/>
			</div>
			<div class="clear"></div>
			<div class="mt30 lc_shengcheng">
				<span>调整目录：</span>
				<input type="hidden" id="curTypeId1" name ="typeId1" value="${typeId1}" />
				 <input type="hidden" id="paperId1" name ="paperId1" value=" " />
					<input type="hidden" id="typeNames1" name="typeName1" value="${typeName1}")/>
					<div  id="typeNames02" class="duouan" style="width:260px;height:30px;">${typeName1}</div>
			 	</div>
			</div>
			<div class="clear"></div>
			<div class="cas_anniu" style="margin-top:50px;margin-left:170px;">
				<a href="javascript:void(0);" class="fl queren" style="width:70px;">确认</a>
				<a href="javascript:void(0);" class="fl queren" style="width:70px;margin-left:40px;">取消</a>
			</div>
		</div>
		<div class="clear"></div>
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
		
		$('.select').click(function(){
			$('.list').css("display","none");
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
	    $('#typeNames01').click(function(){
			initPropList("选择目录","${ctx}/paperManage/getPaperListType.do",-1,0,5,0,$('#curTypeId'),$('#typeNames'),$('#typeNames01'));
			$('.att_make01').show();
		});
		 $('#typeNames02').click(function(){
			initPropList("选择目录","${ctx}/paperManage/getPaperListType.do",-1,0,5,0,$('#curTypeId1'),$('#typeNames1'),$('#typeNames02'));
			//initPropList("选择目录","${ctx}/exam/paper/paperTree.do",1,0,5,0,$('#typeId'),$('#typeName01'));
			$('.att_make01').show();
		});
		$('.queren').click(function(){
			$('.make02').hide();		
		});
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
				var url = "${ctx}/paperManage/paperList.do?method=update&paperId1="+$('#paperId1').val()+"&typeId1="+$('#curTypeId1').val();
				
				$.ajax({
				    url:url,
				    type: 'POST',
				    dataType: 'text',
				    success: function(result){
					       if(result=="success"){
					       
				            alert('调整成功！');
				            document.location.href = document.location.href.split("#")[0];
						   }
						   else
						   {
						   		alert('调整没有！。');
						   }
						 
						 
				    }
				});
	 
		});
		
		
});
var kuangcode;
	var kuang;
	var initType;
	var initsubmenu;
	var kuangView;

function initPropList(_title,_ajaxurl, _initType, _initId, _selectLvl, _checkLvl, _kuangcode, _kuang,_kuangView){
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
		kuangView = _kuangView;
		initType = _initType;
		ajaxurl = _ajaxurl;
	 	 if (_title == "选择目录"){
			$('.xs_biaoti .xs_er .attr_xueke01').text('选择目录');
			initsubmenu="选择目录";
		}
		
		var selstr = $(_kuang).val();
		selarray = selstr.split(",");
		var newnarray = new Array();
		$(selarray).each(function(key, val){
			if (val != "") newnarray.push('<em class="delem">' + val + '</em>');
		});
		$('.xs_kuang').html(newnarray.toString());		

		$('.delem').click(function(){
			delem($(this));
		});

		var url;
		if (_initType < 0 && initsubmenu =="选择目录") 
			url = ajaxurl + "?parentId=" +_initType;
		else
			url = ajaxurl + "?type="+ _initType +"&id="+ _initId + "&mode=getListByType";
		
		 
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
			//if (selid < 1) return false;
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
			if (initType < 0 && initsubmenu =="选择目录")
			{
				var url = ajaxurl + "?parentId="+selid;
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
			else
			{
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
			}
			
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
			
			if (initType < 0 && initsubmenu =="选择目录")
			{
				var url;
				if(selid == 0)
					url = ajaxurl + "?parentId=-1";
				else
					url = ajaxurl + "?parentId=" +selid;
				
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
			else
			{
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
			}
		});
		if (initType < 0 && initsubmenu =="选择目录")
		{
			$('.xs_ul input[type="radio"]').off('click');
				$('.xs_ul input[type="radio"]').click(function(){
					var p = $(this).parent().find('.attr_xueke04').eq(0);
					var id = $(p).prop('id');
					var propname = '<em class="delem">' + $(p).find('em').text() + '</em>';
					
					if ($(this).prop('checked')){
						var newarray = new Array();
						$('.xs_kuangcode').text("");
						$('.xs_kuang').text("");
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
					$('.delem').off('click');
					$('.delem').click(function(){
						delem($(this));
					});
				
				});
				
				//selected item mark checked.
				$('.xs_ul input[type="radio"]').each(function(key, val){
					var p = $(this).parent().find('p').eq(0);
					var id = $(p).prop('id');
					var selstr = $('.xs_kuangcode').text();
					var selarray = selstr.split(",");
					var idx = selarray.indexOf(id);
					
					if (idx>=0) $(this).prop("checked", true);
				});
		}
		else
		{
			$('.xs_ul input[type="radio"]').off('click');
				$('.xs_ul input[type="radio"]').click(function(){
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
				$('.xs_ul input[type="radio"]').each(function(key, val){
					var p = $(this).parent().find('.attr_xueke04').eq(0);
					var id = $(p).prop('id');
					var selstr = $('.xs_kuangcode').text();
					var selarray = selstr.split(",");
					var idx = selarray.indexOf(id);
					
					if (idx>=0) $(this).prop("checked", true);
				});
		}
	
	}	

	function updatePropList(result){
		
		var str = "";
		var check = eval($('.xs_checklvl').text());
		var select = eval($('.xs_selectlvl').text());
		if (initType < 0 && initsubmenu =="选择目录")
		{
			$(result).each(function(key, value){
				str += "<li><div class=''>";
				if(value.state != "closed")
					str += '<input class="fl" style="margin-top:5px;" type="radio" name = "examtype" value ="'+value.id+'">';
	
				str += '<p class="fl attr_xueke04"' + ' id="'+ value.id +'"' + '><em class="fl">' + value.text + '</em>';
				if(value.state == "closed")
					str += '<i class="fl ml10 kti_bjt2"></i>';
				str += "</div><div class='clear'></div></li>";
			});
		}
		else
		{
			$(result.list).each(function(key, value){
				str += "<li><div class=''>";
				if (check <= value.type)
					str += '<input class="fl" style="margin-top:5px;" type="radio">';
	
				str += '<p class="fl attr_xueke04"' + ' id="'+ value.prop_val_id +'"' + '><em class="fl">' + value.name + '</em>';
				if (select > value.type){
					str += '<i class="fl ml10 kti_bjt2"></i>';
				}
				str += "</div><div class='clear'></div></li>";
			});
		}
				
		$('.xs_ul').html(str);
		initPropWndProp();
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

function selectProp(){
		
		$(kuangcode).val($('.xs_kuangcode').text());
		$(kuang).val($('.xs_kuang').text());
		$(kuangView).text($('.xs_kuang').text());
		//$('#typeNames').val($('.xs_kuang').text());
		$('.att_make01').hide();
}
function contral(nam,id){
  $('#now').val(nam);
  $('#typeNames02').text(nam);
  $('#paperId1').val(id);
}

function deletePaper(id) {

	if (!confirm("确定删除!")) return;
	
	var url = '${ctx}/paperManage/paperDelete.do';
	var params = 'id=' + id;
	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'text',
	    success: function(result){
			if(result == 'success'){
		   		alert('删除成功！');
		   		document.location.href = "${ctx}/paperManage/paperList.do";	
		   	}else{
		   		alert('删除失败！请检查内容!');
		   	};
	    }
	});	
}

/**
 * @author 张建国
 * @time   2017-01-18
 * @param  试卷Id
 * 说     明： 插入试卷信息
 */
function selectSJ(obj){
	var html = "<p>"
	           +"<img src=\"${ctx}/images/sj.jpg\" alt='"+obj+"' width=\"100\" height=\"100\"/>"
	    	   +"</p>";  	   
    window.parent.ue.execCommand( 'inserthtml', html );
    var dialog = window.parent.ue.getDialog("attachment");
	dialog.close();
}
</script>
</body>
</html>