<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/taglibs.jsp"%>
	
<title>培训后台</title>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
</head>

<%@include file="/peixun_page/top.jsp"%>

<style>
	.picker_holder{overflow-y:hidden;}
	.pro_title{color:#075387 !important;cursor:pointer;}
	button.btn_blue{padding:3px 5px;color:#fff;background-color:#0B92E8;border:0 none;border-radius: 5px;outline:none;}
</style>

<body>
<div class="clear"></div>
<form name="queryForm" id = "queryForm" action="${ctx}/contentManage/editionManage.do?mode=view&id=${id}" method="post">
<!-- 查询条件 -->
<div class="center">
	<h2 style="font-size:16px; font-weight:600;">查看【首页 &gt; 推荐项目 &gt; 项目】绑定内容</h2>
	<div class="tiaojian" style="margin-top:30px;min-height:40px;">
		
		<p class="fl">
			<span>项目名称：</span>
			<input type="text" name="cvsetName" id="cvsetName" value="${cvsetName}"/>
		</p>

		
	</div>
	<div class="fl cas_anniu">
			<a href="javascript:$(queryForm).submit();" class="fl" style="margin-bottom:10px;width:70px;margin-left:0px;">查询</a>
			<a href="${ctx}/contentManage/editionManage.do?mode=list" class="fl" style="width:70px;">返回</a>
		</div>
</div>
<div class="clear"></div>
<div class="bjs"></div>

<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
		<div class="fr cas_anniu">
			<a href="javascript:void(0)" onclick="unbind('${id}')" class="fl" style="width:180px;">解除绑定</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<div class="clear"></div>
		<display:table name="list" requestURI="${ctx}/contentManage/editionManage.do?mode=view" id="p" class="mt10 table" pagesize="15">			
			<display:caption><button type="button" class="btn_blue" style="margin-left:3.5%;" name="list_filter" id="list_filter">保存显示顺序</button></display:caption>
			<display:column title="序号" style="width:3%;">${p_rowNum}</display:column>
			<display:column title="排序"  style="width:5%;"  sortProperty="sort">
				<input type="number" class="att_sort" style="width:4em;text-align:center;" id="${p.id}" value="${p.sort}" maxlength="1" oninput="if(value.length>1)value=value.slice(0,1)"/>
			</display:column>
			<display:column title="项目名称" class="pro_title" style="width:15%;">
				<a href="javascript:select(${p.id})" class="pro_title">${p.name}</a></display:column>
			<display:column title="项目编号" style="width:10%;">${p.code}</display:column>
			<display:column title="项目级别" style="width:6%;">
				<c:if test="${p.level==1}">国家I类</c:if>
				<c:if test="${p.level==2}">省级I类</c:if>
				<c:if test="${p.level==3}">市级I类</c:if>
				<c:if test="${p.level==4}">省级II类</c:if>
				<c:if test="${p.level==5}">市级II类</c:if>
				<c:if test="${p.level==6}">无学分</c:if>
			</display:column>	
			<display:column title="学分" style="width:5%;">${p.score}</display:column>
			<display:column title="项目起止时间" style="width:10%;">
				<fmt:formatDate value="${p.start_date}" pattern="yyyy-MM-dd"/>至
				<fmt:formatDate value="${p.end_date}" pattern="yyyy-MM-dd"/>		
			</display:column>
			<display:column title="项目负责人" style="width:8%;">
				<c:forEach	items="${p.managerList}" var="manager">
					${manager.name}&nbsp;
				</c:forEach>
			</display:column>
			<display:column title="创建时间" style="width:10%;">
			
			<fmt:formatDate value="${p.create_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</display:column>
			<display:column title="状态" style="width:5%;">
				<c:if test="${p.status==1}">未提交</c:if>
				<c:if test="${p.status==2}">审核中</c:if>
				<c:if test="${p.status==3}">审核合格</c:if>
				<c:if test="${p.status==4}">审核不合格</c:if>
				<c:if test="${p.status==5}">已发布</c:if>
				<c:if test="${p.status==6}">已下线</c:if>
			</display:column>
			<display:column title="标签" style="width:5%;">${p.sign}</display:column>		
			<display:column title="解除绑定" style="width:5%;">
				<input type="checkbox" name="project_id" value="${p.id}"/>
			</display:column>				
		</display:table>	
		<div class="clear"></div> 		
	</div>
</div>


<!-- 新学科弹出框-一级 -->
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
</form>
<script type="text/javascript">

var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
var submenu = "lc_left0" + menuindex;
$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
$('.'+submenu+'').show();
$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");

var basePath = "${ctx}";
$(function(){
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
	
	$('#groupNames01').click(function(){
		initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#groupIds'), $('#groupNames'));
		$('.att_make01').show();
	});
		
	$('.queren').click(function(){
		$('.make02').hide();		
	});
	
	$('#groupNames01').click(function(){
		initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#groupIds'), $('#groupNames'));
		$('.att_make01').show();
	});
	
	$('#list_filter').click(function(){
		setorderNum();
	});
	
});

	function setorderNum(){
		var url = "${ctx}/contentManage/editionManage.do?mode=setorder";
		var params = "orderstr=";
		var isblank = false;
		$('.att_sort').each(function(){
			var id = $(this).prop('id');
			var val = $(this).val();
			params+= id+"_"+val+";";
			if(val == ""){
				isblank = true;
			}
		});
		if(isblank){
			alert("排序号中有空值，请检查排序号！");
			return false;
		}
		if(flag){
			$.ajax({
			    url:url,
			    type: 'POST',
			    data: params,
			    dataType: 'text',
			    success: function(result){
				   if(result == 'success'){
					   	alert("修改成功！");
					   	document.location.href = document.location.href.split("#")[0];
				   }else{
					   alert("修改不成功！");
				   }
			    }
			});
		}
		
	}
	
	/////// select xueke window proc
	var kuangcode;
	var kuang;
	
	var initsubmenu;
	
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
		
		/* if(_titile == "职称"){
			$('.xs_biaoti .xs_er .attr_xueke01').text('职称');
		} */
		
		if (_title == "主题"){
			$('.xs_biaoti .xs_er .attr_xueke01').text('主题');
			initsubmenu="主题";
		}
		else if(_title == "学科"){
			$('.xs_biaoti .xs_er .attr_xueke01').text('一级学科');
			
		}
		else if (_title == "来源"){
			$('.xs_biaoti .xs_er .attr_xueke01').text('来源');
			initsubmenu="来源";
		}
		else if(_title == "职称"){
			$('.xs_biaoti .xs_er .attr_xueke01').text('职称');
			initsubmenu="职称";
		}
		
		 /* */

		$('.xs_kuangcode').text($(_kuangcode).val());
		$('.xs_kuang').text($(_kuang).val());
		
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
		/*if (_initType < 0) 
			url = ajaxurl + "&id="+ _initId;
		else */
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

			str += '<p class="fl attr_xueke04"' + ' id="'+ value.prop_val_id +'"' + '><em class="fl">' + value.name + '</em>';
			if (select > value.type){
				str += '<i class="fl ml10 kti_bjt2"></i>';
			}
			str += "</div><div class='clear'></div></li>";
		});
		
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
	
	function selectProp(){	alert();
		$(kuangcode).val($('.xs_kuangcode').text());
		$(kuang).val($('.xs_kuang').text());
		$(groupNames01).text($('.xs_kuang').text());
	}
	

	function selectProp(){
		$(kuangcode).val($('.xs_kuangcode').text());
		$(kuang).val($('.xs_kuang').text());
		$(groupNames01).text($('.xs_kuang').text());
	}
	
	//解除绑定
	function unbind(id){
		var cvsetId = $("input[type='checkbox']:checked");
		if(cvsetId.length == 0){
			alert("请先选择要解绑项目!");
		    return;
		}
		
		if(confirm("确定解除绑定?")) {
			var selectedIds = '';
			if(cvsetId != '' && cvsetId.length >0 ){
				for(var i=0; i<cvsetId.length; i++){
					selectedIds += cvsetId[i].value + ',';
				}
			}
			var p = {'cvsetIds':selectedIds,'id':id};
			
			$.post("${ctx}/contentManage/editionManage.do?mode=unbind", p,
					function(data){
						if(data >0){
							alert("解绑成功!");
						}
						else{
							alert("由于网络原因,更改不成功,请稍候再试!");
						}
						document.location.href = document.location.href.split("#")[0];
				});
			
		}
	}
	
	var flag = true;
	$(".att_sort").on("blur", function(e){
		var value = e.target.value;
		var count=0;
		if(value > 4){
			alert("只能输入1、2、3、4！");
			$(this).val('');
			return ;
		}
		if(value != ""){
			var bre = true;
			$('.att_sort').each(function(){
				if(bre){
					var val = $(this).val();
					if(val == value){
						count++;
					}
					if(count >= 2){
						bre = false;
						flag = false;
						alert("该序号已存在，请删除已有序号再进行排序！");
						$(this).val('');
					}else{
						flag = true;
					}
				}
			});
		}
		
	});

</script>
</body>
</html>