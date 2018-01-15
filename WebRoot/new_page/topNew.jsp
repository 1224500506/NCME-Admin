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

<link rel="stylesheet" href="${ctx}/new_page/css/reset.css" />
<link rel="stylesheet" href="${ctx}/new_page/css/index.css" />
<script type="text/javascript" src="${ctx}/new_page/js/jquery.js"></script>
<style type="text/css">
	#gotoPage{
		cursor: pointer;
	}
</style>

</head>
<body>

<div class="bjs">
	<!-- 头部 -->
	<div class="fixed" style="position:fixed;width:100%;margin-top:-15px auto;z-index:100;">
	<div class="header">
		<div style="margin:0 auto;width:92%;">
		<div class="fl mt5 tk_left">
			<img src="${ctx}/new_page/images/logo1.png" alt="" />
		</div>
		<div class="fl tk_center tk_mainmenu">
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
			
			<div class="lc_er guanliyuan"  style="display:none;">
				<a href="javascript:void(0);" class="password">修改密码</a>
				<a href="javascript:logout();">退出系统</a>
			</div>
		</div>
		</div>
	</div>
	<div class="clear"></div>
	<!-- 二级 -->
	<div class="tk_er">
	    
	    <!-- 循环生成二级栏目 -->	    
            <c:if test="${menu != null && menu.size() > 0}">
                <c:forEach items="${menu}" varStatus="oneMenuStauts" var="menuOneLevel">
                    <div class="tk_float mar_left0${oneMenuStauts.index+1}"  style="display:none;">
                                <c:if test="${menuOneLevel.systemMenuList != null && menuOneLevel.systemMenuList.size() > 0}">					
                                        <c:forEach items="${menuOneLevel.systemMenuList}" varStatus="twoMenuStauts" var="twoMenu">
                                            <a id="${oneMenuStauts.index+1}-${twoMenuStauts.index+1}" onclick="setCodeToHidden(this)" href="${ctx}${twoMenu.url}">${twoMenu.name}</a>						   						  
                                        </c:forEach>
                                </c:if>
                        </div>
                </c:forEach>
	    </c:if>
	</div>
	</div>
	<div class="clear" style="height:85px"></div>

</div>

<!-- 修改密码 -->
<form method="post" action="${ctx}/editPwd.do" id="editpwd" name="editpwd">
<div class="toumingdu make01" style="display:none;">
	<div class="sy_conter">
		<div class="con">
			<div class="denglu_1">
				<a class="zhanghao" href="javascript:void(0);">修改密码</a> <em class="msg" style="margin-left:20px;color:#f00;">${msg}</em>
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
					<input type="password" placeholder="请确认新密码" class="fl telphoto1" id="repwd" name="repwd"  value="" />
				</div>
				<p class="clear tishi_3"></p>
			</div>
			
			<div class="cas_anniu">
				<a href="javascript:void(0);" class="fl denglu_5" style="margin-top:2px;margin-left:45px;margin-right:20px">提交</a>
				<a href="javascript:void(0);" class="fl quxiao" style="margin-top:2px;margin-bottom:20px;">取消</a>
			</div>
		</div>
	</div>
</div>
</form>

<div class="toumingdu att_make01" style="display:none;z-index:10000;">
	<div class="tk_center09" style="margin:7% auto;">
		<div class="tik_biaoti">
			<span class="fl tit_biaoti" style="width:280px;text-align:center;position:relative;left:50%;margin-left:-140px;color:#fff;"></span>
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

<script type="text/javascript">
var currentThis = null;
		
$(function(){
	//导航切换
	$('.tk_center>ul>li').mousemove(function(){
		
		currentThis = this;
		$(this).addClass('action').siblings('.action').removeClass('action');
		var n=$(this).index();
		$('.tk_er div').eq(n).show().siblings().hide();
	});	

	$('.lc_right p').mousemove(function(){
		$('.guanliyuan').show();
	});
	$('.lc_er').mouseleave(function(){
		$('.guanliyuan').hide();
	});	
	
	$('.bjs').mouseleave(function(){
		$('.tk_er>div>a').each(function(key,val){
			if($(this).attr("class") == "action")
			{
				var n = $(this).parent().index();
				$('.tk_center>ul>li').removeClass('action');
			    $('.tk_center>ul>li').eq(n).addClass('action');
				$('.tk_er div').eq(n).show().siblings().hide();
			}
		});
	});
	
	$('.wrap').mousemove(function(){
		$(currentThis).removeClass('action').siblings('.action').removeClass('action');
		var n=$(currentThis).index();
						
		$('.tk_er div').eq(n).hide().siblings().hide();
	});
	
/* 	
	$(function(){
		$('.tik_select').click(function(){
			$(this).find('ul').show();
		});
		$('.tk_list li').click(function(){
			var str=$(this).text();
			$(this).parent().parent().find('div').find('b').text(str);
			//$(this).parent().hide();
			$('.tk_list').slideUp();
		});
		
		$('.tik1_select').click(function(){
			$(this).find('ul').show();
		});
		$('.tk1_list li').click(function(){
			var str=$(this).text();
			$(this).parent().parent().find('div').find('b').text(str);
			//$(this).parent().hide();
			$('.tk1_list').slideUp();
		});
		
	});
	 */
	// 修改密码
	$('.password').click(function(){
		$('.msg').text('');
		$('.make01').show();
	});
	$('.denglu_5').click(function(){
		var old = $('#oldpwd').val();
   		var nep = $('#newpwd').val();
   		var rep = $('#repwd').val();
   		
	if(old==""){alert("请输入原密码！"); return false;}
   	else {	
  		
   		if (nep!="" && rep!=""){

   			if (nep == rep){
   				document.getElementById('editpwd').submit();
   			}

   			else {alert("两次密码不一致"); return false;}

   		}

   		else  {alert("无法修改密码！");  return false;}		
   		
    	
  	}
  	});
  	
  	$('.quxiao').click(function (){
  		$('.make01').hide();
  	});
	
});

var ctx = '${ctx}';

function logout(){
	window.location.href = ctx + '/logout.do';
}

function setCodeToHidden(code){
		 window.localStorage.removeItem("CDCode");
		 window.localStorage.setItem("CDCode",code.id);
// 		 window.localStorage.getItem("CDCode");
	}

</script>

<script>
/////// select xueke window proc
	var kuangcode;
	var kuang;
	var ajaxurl;
	var initType;
	var initId;
	var initsubmenu;
	var viewpath;
	var dotexp = /,/g;
	var brexp = /<br>/g;
	
	var currTitle = "" ;
	
	function initPropList(_title,_ajaxurl, _initType, _initId, _selectLvl, _checkLvl, _kuangcode, _kuang){
		currTitle = _title ;
		
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
			var propname = '<em class="delem">';
			if (viewpath) propname+= $('.xs_biaoti').text().trim();
			
			if (currTitle == "来源") {
				propname+= $(p).text() + '</em><br>';
			}else {
				propname+= $(p).find('em').text() + '</em><br>';
			}			
					
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
		
		    if (currTitle == "来源") {
		    	str += "<li>";
				if (check <= value.type)
					str += '<input class="" style="margin-top:2px;float:left;" type="checkbox">';
	
				if ($(kuangcode).prop('id') == "icdIds")
					str += '<em class="attr_xueke04"' + ' id="'+ value.id +'"' + '>' + value.name + '</em>';
				else
					str += '<em class="attr_xueke04"' + ' id="'+ value.prop_val_id +'"' + '>' + value.name + '</em>';
				
				if (select > value.type){
					str += '<i class="fl ml10 kti_bjt2 style="margin-top:4px;"></i>';
				}
				str += "</li>";
		    } else {
		    	str += "<li>";
				if (check <= value.type)
					str += '<input class="" style="margin-top:2px;float:left;" type="checkbox">';
	
				if ($(kuangcode).prop('id') == "icdIds")
					str += '<p style="float:left;" class="attr_xueke04"' + ' id="'+ value.id +'"' + '><em>' + value.name + '</em>';
				else
					str += '<p style="float:left;" class="attr_xueke04"' + ' id="'+ value.prop_val_id +'"' + '><em>' + value.name + '</em>';
				
				if (select > value.type){
					str += '<i class="fl ml10 kti_bjt2 style="margin-top:4px;"></i>';
				}
				str += "</li>";
		    }
			
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
	
	
	//chenlb add
	function gotoPage_displaytag(pageFlag) {

	    var T = /^[1-9]\d*$/;
	    var pageNum = document.getElementById("gotoPageNumber_displaytag").value;
	    if (!T.test(pageNum)) {
	    	alert("请输入数字！");
	    	document.getElementById("gotoPageNumber_displaytag").value="";
	        return;
	    }
	    
	    var pageCount = document.getElementById("pageCount").value;
	    
	    if(pageCount != ''){
	    	pageCount=pageCount.replace(/,/, "");
		    if(parseInt(pageNum)>parseInt(pageCount)){
		    	alert("您输入的页数不能大于最大页数！");
		    	//document.getElementById("pageCount").value = "";
		    	return;
		    }
	    }
	    
	    if (pageFlag == 0) {
	        // paging.banner.full
	        // paging.banner.first
	        url = document.getElementById("lastPageUrl_displaytag").value;
	    } else {
	        // pageFlag == 1
	        // paging.banner.last
	        url = document.getElementById("prevPageUrl_displaytag").value;
	    }
		
	    if(url.indexOf("page=")==-1){
	    	url = url + "page=1";
	    }
	    var url1 = url.substring(0,url.indexOf("page=")+5);
	    var params = "";
	    if(url.indexOf("&")>-1){
	    	params = url.substring(url.indexOf("&"));
	    }
	    window.location = url1+pageNum+params;
	}
</script>
<script type="text/javascript" src="${ctx}/new_page/js/movediv.js"></script>

