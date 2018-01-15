<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/peixun_page/css/reset.css" />
	<link rel="stylesheet" href="${ctx}/peixun_page/css/index.css" />
	<link rel="stylesheet" href="${ctx}/peixun_page/css/pickadate/default.css" />
	<link rel="stylesheet" href="${ctx}/peixun_page/css/pickadate/default.date.css" />
	<link rel="stylesheet" href="${ctx}/peixun_page/css/fontawesome/css/font-awesome.css" />
	<script type="text/javascript" src="${ctx}/peixun_page/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/main.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/picker.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/picker.date.js"></script>
	<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/legacy.js"></script>
<title>培训后台</title>
</head>
<body>
<!-- 查询条件 -->
<div class="center">
	<form action="${ctx}/examManage/examStudentDetail.do?method=list&model.userType=2" method="post" id="fm">
	
	<div class="tiaojian" style="min-height:40px; margin-top:27px">		
		<p class="fl" style="margin:0 20px 10px 0">
			<span style="width:5em;text-align:right;">姓名：</span>
			<input type="text" name="model.realName" value="${item.realName}" />
		</p>
		<p class="fl" style="margin-left:60px">
			<span style="width:3em;text-align:right;">学科：</span>
			<input type="hidden" id="propIds" name="propIds" value="${propIds}"/>
			<input type="hidden" id="propNames" name="propNames" value="${propNames}"/>
			<div class="duouan" id="propNames0">${propNames}</div>
		</p>
		<p class="fl"  style="margin-left:60px">
			<span>账号：</span>
			<input type="text" name="model.accountName" value="${item.accountName}" />
		</p>
		<p class="fl" style="margin-left:40px">
			<span style="width:5em;text-align:right;">状态：</span>
		</p>
		<div class="fl select"  >
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
	</div>
	<div class="tiaojian" >		
		<div class="fl cas_anniu">
			<a href="javascript:$(fm).submit();" class="fl" style="width:70px;">查询</a>
		</div>
		<div class="fr cas_anniu">
			<a href="#" id = "addStudent-link1" class="fl exp1_tianjia01" style="width:80px;">添加监考人</a>
			<a href="javascript:window.close();" class="fl" style="width:80px;margin-left:10px;">返回</a>
		</div>
	</div>
	</form>
</div>
<div class="clear"></div>
<div class="bjs" style="margin-top:13px"></div>

<!-- 添加编辑 -->
<div id = "layercontent">
<div class="center">
	<form action="" id="fm1" name="fm1" method="post">
	<input type="hidden" id="userId" name="model.userId" value="0"/>
	<input type="hidden" name="model.userType" value="2"/>
			<div id="" class="layui-layer-content" style="height: 205px;">
				<div class="center">
					<div class="tiaojian" style="min-height:40px;">
						<p class="fl" style="margin-bottom:20px;">
							<span style="width:6em;text-align:right;">编辑姓名：</span>
							<input type="text" id="realName" name="model.realName" value="">
						</p>
						<p class="fl" style="margin-bottom:20px;">
							<span style="width:6em;text-align:right;">编辑账号：</span>
							<input type="text" id="accountName" name="model.accountName" value="">
						</p>
						<div class="clear"></div>
						<p class="fl" style="margin-bottom:20px;">
							<span style="width:6em;text-align:right;">学科：</span>
							<input type="text" class="subject_val" id="deptName" name="model.deptName" value="">
						</p>
						<p class="fl" style="margin-bottom:20px;">
							<span style="width:6em;text-align:right;">联系电话：</span>
							<input type="text" id="mobilPhone" name="model.mobilPhone" value="">
						</p>
					</div>
				</div>
			</div>
			<span class="layui-layer-setwin"></span>
	</form>
</div>
</div>
<!-- 内容 -->

<div class="center" style="">
	<div class="center01">
		<div class="clear"></div>
			<display:table requestURI="${ctx}/examManage/examStudentDetail.do?method=list"
							id="systemUser" partialList="true"
							excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}"  list="${page}"
							class="mt10 table"  decorator="com.hys.exam.displaytag.TableOverOutWrapper">
				<display:column
					title="<input type='checkbox' id='checkall' name='checkall' value='all' onclick='javascript:checkAll(this);' />"
					style="width:3%;text-align:center">
					 <input type="checkbox" name="ids" id="ids" 
					value="${systemUser.userId}+${systemUser.realName}_${systemUser.accountName}_${systemUser.workUnit}_${systemUser.deptName}_${systemUser.userType}_${systemUser.mobilPhone}"/>
				</display:column>
				<display:column title="序号" style="width:8%;text-align:center">${systemUser_rowNum}</display:column>
				<display:column property="realName" title="编辑姓名" style="width:12%;"></display:column>
				<display:column property="deptName" title="学科" style="width:20%;text-align:center"></display:column>
				<display:column property="mobilPhone" title="联系电话" style="width:15%;text-align:center"></display:column>
				<display:column property="accountName" title="账号" style="width:20%;"></display:column>
				<display:column title="账号状态" style="width:10%;">
					<c:if test="${systemUser.account_status==1}">正常</c:if>
					<c:if test="${systemUser.account_status==2}">停用</c:if>
				</display:column>
				<display:column title="操作" style="width:40%;text-align:center;">
					<a class="edit_btn" href="javascript:infoView(${systemUser.userId});" >详情</a> 
<%-- 					<c:if test="${systemUser.status==1}">
						<a href="javascript:setState(${systemUser.userId},2);" >停用</a> 
					</c:if>
					<c:if test="${systemUser.status==2}">
						<a href="javascript:setState(${systemUser.userId},1);" >正常</a> 
					</c:if> --%>
				</display:column>
			</display:table>
		<div class="clear"></div> 
	</div>
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
		
		$('#propNames0').click(function(){
			initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames0'));
			$('.att_make01').show();
		});
		
		//添加编辑
		$('.add_btn').click(function(){
			$('.make02').show();
			$('#layer_Name').text('添加编辑');
			$('#userId').val('0');
			$('#realName').val('');
			$('#accountName').val('');
		    $('#deptName').val('');
		    $('#mobilPhone').val('');
		});
		$('.layui-layer-btn1').click(function(){
			$('.make02').hide();
		});
		
		$('.layui-layer-btn1').click(function(){
			$('.make02').hide();
		});
		
		selectInit();
});
	var win_w = "600px";
	var win_h = "300px";
	var add_cont = $('#layercontent').html();
	$('#layercontent').remove();
	$(".edit_btn").click(function() {
		layer.open({
			type: 1,
			title: "编辑详情",
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
//查看详情
function infoView(id){
	var url = "${ctx}/system/editManage.do?method=view&rtype=1";
	
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
		      $('#userId').val(item.userId);
		      $('#realName').val(item.realName);
		      $('#accountName').val(item.accountName);
		      $('#deptName').val(item.deptName);
		      $('#mobilPhone').val(item.mobilPhone);
		   }else{
		   		alert('检查内容失败!');
		   }
	    }
	});
}

function setState(id, status){

	if (!confirm("确认？"))return;
	var url = "${ctx}/system/editManage.do?method=setState";
	
	var params = "&id="+id;
	params+= "&state="+status;

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

function saveEdit(){
	var url = "${ctx}/system/editManage.do?method=insert";
	if ($('#userId').val() != 0)
		url = "${ctx}/system/editManage.do?method=update";
	
	var params = "model.userId="+$('#userId').val();
 	params += "&model.realName="+$('#realName').val();
 	params += "&model.userType=2";
	params += "&model.accountName="+$('#accountName').val();
	params += "&model.deptName="+$('#deptName').val();
	params += "&model.mobilPhone="+$('#mobilPhone').val();
	
	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'text',
	    success: function(result){
			if (result !=""){
				alert(result);
				window.location.reload(true);
			}
	    }
	});
}
function checkAll(obj){

	$("input[type='checkbox']").each(function(){
		if ($(obj).prop("checked") == true)
			$(this).attr('checked' , "checked");
		else
			$(this).removeAttr('checked' , "checked");
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
	
}
	  var winObj = parent.opener;
      var stuNamesId = winObj.stuNamesId;
        $(function () {
            $("input[name='ids']").each(function () {
                var str = this.value;
                var tempArray = str.split("+");
                var temp1 = tempArray[0];
                for (var i in stuNamesId) {
                    if (temp1 == stuNamesId[i]) {
                        this.disabled = "true";
                        this.style.display = 'none';
                    }
                }
            });
            $("#addStudent-link1").click(function () {
                var flag = false;
                $("input[name='ids']").each(function (i) {
                    if (this.checked && this.disabled == false) {
                        var str = this.value;
                        var tempArray = str.split("+");
                        var temp1 = tempArray[0];
                        var temp2 = tempArray[1].split("_");
                        winObj.stuNamesId1.push(temp1);
                        winObj.stuNamesId.push(temp1);
                        winObj.stuNamesInfo.push(temp2);
                        flag = true;
                    }
                });
                //for(n in winObj.stuNames){
                //alert(winObj.stuNames[n]+" ");
                //}
                if (flag) {

                    winObj.setStu();
                    alert("添加监考人成功！");
                    parent.window.close();
                } else {
                    alert("请选择监考人！");
                }
            });
        });
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
		$("#propNames").val($('.xs_kuang').text());
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
</body>
</html>
