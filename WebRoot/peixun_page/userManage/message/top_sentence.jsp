<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.hys.exam.model.SystemUser" %>
<%@ page import="com.hys.exam.common.util.LogicUtil" %>
<%
	SystemUser user = LogicUtil.getSystemUser(request);
	String username = "";
	if(null != user){
		if(null != user.getRealName()){
			username = user.getRealName();
		}
	}
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%@include file="/commons/taglibs.jsp"%>
	<link rel="stylesheet" href="${ctx}/peixun_page/css/reset.css" />
	<link rel="stylesheet" href="${ctx}/peixun_page/css/index.css" />
	<link rel="stylesheet" href="${ctx}/peixun_page/css/pickadate/default.css" />
	<link rel="stylesheet" href="${ctx}/peixun_page/css/pickadate/default.date.css" />
	<link rel="stylesheet" href="${ctx}/peixun_page/css/fontawesome/css/font-awesome.css" />
	<link rel="stylesheet" href="${ctx}/peixun_page/css/ueditor.min.css" />
	
	<script type="text/javascript" src="${ctx}/peixun_page/js/jquery.js"></script>
<%-- 	<script type="text/javascript" src="${ctx}/peixun_page/js/jquery.js"></script> --%>
	<script type="text/javascript" src="${ctx}/peixun_page/js/main.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/picker.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/picker.date.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/legacy.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/ueditor145/ueditor.config.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/ueditor145/ueditor.all.min.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/ueditor145/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
	
	<!-- /Admin/WebRoot/js/jquery.form.js -->
	<style>
		input[type=text]{padding:0 5px;}
		input:disabled{background:#f0f0f0;color:#ccc;}
		.center{margin:15px auto 0;}
	</style>
</head>
<body>
<span class="bjs">
<!-- 头部 -->
<div class="fixed" style="z-index:1000;">
	<div class="header">
		<div class="fl ml30 mt5 lc_left">
			<img src="${ctx}/peixun_page/images/logo.png" alt="" />
		</div>
		<div class="fl lc_center">
			<ul>
				<c:if test="${menu != null && menu.size() > 0}">
						<c:forEach items="${menu}" varStatus="stauts" var="menuOneLeve">							
							<li>${menuOneLeve.menu_type}</li>
						</c:forEach>
				</c:if>				
			</ul>
		</div>
		<div class="fr mr20 lc_right">
			<p><i class="fl lc_bjt07"></i><span class="fl"><%=username %></span></p>
			<div class="clear"></div>
			<div class="guanliyuan"  style="display:none;">
				<a href="#" class="password">修改密码</a>
				<a href="javascript:logout();">退出系统</a>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<!-- 二级 -->
	<div class="lc_er">
	    <c:if test="${menu != null && menu.size() > 0}">
			<c:forEach items="${menu}" varStatus="oneMenuStauts" var="menuOneLevel">
			    <div class="lc_float lc_left0${oneMenuStauts.index+1}"  style="display:none;">
                                        <c:if test="${menuOneLevel.systemMenuList != null && menuOneLevel.systemMenuList.size() > 0}">					
						<c:forEach items="${menuOneLevel.systemMenuList}" varStatus="twoMenuStauts" var="twoMenu">
						    <a href="${ctx}${twoMenu.url}">${twoMenu.name}</a>						   						  
						</c:forEach>
					</c:if>
				</div>
			</c:forEach>
		</c:if>	
		
	</div>
	<div class="lc_bjt"></div>
</div>
<div class="gaodu"></div>
<div class="clear"></div>
</span>
<!-- 修改密码 -->
<form method="post" action="${ctx}/editPwd.do" id="editpwd" name="editpwd">
<div class="toumingdu make01" style="display:none;">
	<div class="sy_conter">
		<div class="con">
			<div class="denglu_1">
				<a class="zhanghao" href="#">修改密码</a>
			</div>
			<div class="clear denglu_2">
				<div class="dl_shoujihao">
					<em class="fl"></em>
					<input type="password" placeholder="请输入原密码" class="fl telphoto" id="oldpwd" name="oldpwd"  value="" />
				</div>
				<p class="clear tishi_1"></p>
			</div>
			
			<div class="clear denglu_3">
				<div class="dl_shoujihao" style="position:relative;margin-top:-10px;">
					<em class="fl"></em>
					<input type="password" placeholder="请设置新登录密码" class="fl telphoto1" id="newpwd" name="newpwd" value="" />
				</div>
				<p class="clear tishi_3"></p>
			</div>
			<div class="clear denglu_3">
				<div class="dl_shoujihao" style="position:relative;margin-top:-10px;">
					<em class="fl"></em>
					<input type="password" placeholder="请在输入密码" class="fl telphoto1" id="repwd" name="repwd"  value="" />
				</div>
				<p class="clear tishi_3"></p>
			</div>
			
			<div class="clear denglu">
				<em href="#" style="margin-top:2px;margin-bottom:20px;" class="queding">提交</em>
			</div>
		</div>
	</div>
</div>
</form>

<!-- 2017.01.24 Change by IE -->
<!-- From Here -->
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
				<div class="checkAllDiv" style="width:60px;height:40px;line-height:40px;margin-left:10px;display:none;" >
					<label for="checkall">全选</label><input type="checkbox" class="fl" id="checkall" style="margin:15px 5px 0 0;" onclick ="checkAllDiv();"/>
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
<!--  To Here -->

<script type="text/javascript">
var currentThis = null;
var msg = "${msg}";
if(msg != null && msg != "")
{
	alert(msg);
}
$(function(){
	//导航切换
	$('.lc_center>ul>li').mousemove(function(){
	currentThis = this;
		$(this).addClass('action').siblings('.action').removeClass('action');
		var n=$(this).index();
		$('.lc_er div').eq(n).show().siblings().hide();
	});
	$('.lc_right p').mousemove(function(){
		$('.guanliyuan').show();
	});
	$('.lc_er').mouseleave(function(){
		$('.guanliyuan').hide();
	});
	$('.bjs').mouseleave(function(){
		$('.lc_er>div>a').each(function(key,val){
			if($(this).attr("class") == "action")
			{
				var n = $(this).parent().index();
				$('.lc_center>ul>li').removeClass('action');
			    $('.lc_center>ul>li').eq(n).addClass('action');
				$('.lc_er div').eq(n).show().siblings().hide();
			}
		});
	});
	
	$('.wrap').mousemove(function(){
		$(currentThis).removeClass('action').siblings('.action').removeClass('action');
		var n=$(currentThis).index();
						
		$('.lc_er div').eq(n).hide().siblings().hide();
	});
	// 修改密码
	$('.password').click(function(){
		$('.make01').show();
	});
	$('.denglu').click(function(){
		$('.make01').hide();
		var old = $('#oldpwd').val();
   		var nep = $('#newpwd').val();
   		var rep = $('#repwd').val();
   		if(old!="" && nep!="" && rep!=""){
   			if (nep == rep){
   				document.getElementById('editpwd').submit();
   			}
   			else
   				alert ("两次密码不一致");
   		}   		
  	});	
});

var ctx = '${ctx}';

function logout(){
	window.location.href = ctx + '/logout.do';
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
	
	var globalId=[];
	
	// 2017.01.24 Add By IE to display show the source
	/*
		2017.01.24 Change By IE
		To display show source
	*/
	
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
		
		
		
		if(_initType > 0 && _initType < 6){
			$('.xs_biaoti .xs_er .attr_xueke01').text('一级学科');
			initsubmenu="一级学科";
			viewpath = true;
		}else if(_initType==20){
			$('.xs_biaoti .xs_er .attr_xueke01').text('省/直辖市');
			$('.checkAllDiv').css('display','inline-block');
			initsubmenu="省/直辖市";
			viewpath = true;
		}
		else{
			$('.xs_biaoti .xs_er .attr_xueke01').text(_title);
			initsubmenu=_title;
			viewpath = false;
		}
		
		
		
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
		}else if(_initType==20){
			$('.xs_kuang').html("");
			var url = ajaxurl;
			var params = "ids="+ $(_kuangcode).val() +"&mode=region1list";
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
		else if (_initType < 0 && initsubmenu =="选择目录") 
			url = ajaxurl + "?parentId=" +_initType;
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
			   			$('.xscx_divtext').hide();
			   			updateZHLXCombo(result);
			   			updatePropList("");
			   		}
			   		else if (_initType == 10 || _initType == 7 || (_initType == -1 && _selectLvl !=5)){
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

	/*
		Changing Ended.
	*/

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
		var url = ajaxurl + "?type="+ initType +"&id="+ initId + "&mode=getListByType&sname=" +keyword;
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

	//End 

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
			var sti = '全选<input class="fl" style="margin-top:5px;" type="checkbox">';
			$('.xs_er').eq(ms).after(str);
			if(curid == 0)	$('.xs_er').eq(0).remove();
			var url = ajaxurl;
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
			$('.xs_ul input[type="checkbox"]').off('click');
			$('.xs_ul input[type="checkbox"]').click(function(){
				var p = $(this).parent().find('.attr_xueke04').eq(0);
				var id = $(p).prop('id');
				var propname = '<em class="delem">';
				if (viewpath) propname+= $('.xs_er').text().trim();
				propname+= $(p).find('em').text() + '</em><br>';
				
				if ($(this).prop('checked')){
					var selstr = $('.xs_kuangcode').text();
					var selarray = selstr.split(",");
					var newarray = new Array();
					$(selarray).each(function(key, val){
						if (val != "") newarray.push(val);
					});
					newarray.push(id);
					globalId=newarray;
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
			$(result.list).each(function(key, value) {
				//类型为20的地域
				if(value.type==20){
					//从1开始
					value.type=1;
				}
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
		
		}
		
		$('.xs_ul').html(str);
		initPropWndProp();
	}
	
	function updateZHLXCombo(result){
		$('.xs_zhcombo div b').text('请选择');
		var strli = '<option value=0>请选择</option>';
		$(result.list).each(function(key, value){
			strli += '<option value=' + value.code + '>'+value.name+'</option>';
		});
		$('#zclx_combo').html(strli);
		
		$('#zclx_combo li').click(function(){
		
			$('.xs_zhcombo div b').text($(this).text());
			
			var url = ajaxurl;
			var params = "zhtype=" +$(this).index()+ "&type=9";
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
			$('#zclx_combo').slideUp(50);
		});
	}
	
	function selectProp(){
		$(kuangcode).val($('.xs_kuangcode').text());
		var selPropHtml = $('.xs_kuang').html().replace(brexp,",");
		$('.xs_kuang').html(selPropHtml);
		var xs_kuangVal =$('.xs_kuang').text();
		if (xs_kuangVal.substring(xs_kuangVal.length-1,xs_kuangVal.length) == ','){
			xs_kuangVal = xs_kuangVal.slice(0, xs_kuangVal.length-1);
		}
		$(kuang).text(xs_kuangVal);
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
	
	//全选按钮
	var CheckBox=$('.xs_ul')[0].getElementsByTagName('input');
	var CheckBoxP=$('.xs_ul')[0].getElementsByTagName('p');
	var CheckBoxEm=$('.xs_ul')[0].getElementsByTagName('em');
	function checkAllDiv(){
		if($("#checkall").attr("checked")=="checked"){
			var checkAllArrId = [];
			checkAllArrId = globalId;
			var checkAllArrEm = [];
			//全选
			for(i=0;i<CheckBox.length;i++){
				
				
				if(CheckBox[i].checked==''){
					CheckBox[i].checked=true;
					checkAllArrId.push(CheckBoxP[i].id);
					var propname = '<em class="delem">省/直辖市 >';
					checkAllArrEm.push(CheckBoxEm[i].innerHTML);
					propname+= CheckBoxEm[i].innerHTML + '</em><br>';
					$('.xs_kuang').append(propname);
				}
				
			};
			$('.xs_kuangcode').text(checkAllArrId.toString());
		}else if($("#checkall").attr("checked")==null){
			for(i=0;i<CheckBox.length;i++){CheckBox[i].checked=false;};
			$('.xs_kuang').html('');
			$('.xs_kuangcode').text('');
		}
	}
	
	$('.xs_kuang').on('click','.delem',function(){
		delem(this);
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
	});
</script>
<script type="text/javascript" src="${ctx}/peixun_page/js/movediv.js"></script>

</body>
</html> 