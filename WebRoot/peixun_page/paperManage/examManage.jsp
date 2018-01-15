<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<%@include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
</head>

<%@include file="/peixun_page/top.jsp"%>

<body>
<div class="clear"></div>
<form name="queryForm" id = "queryForm" action="${ctx}/examManage/examList.do" method="post">
<!-- 查询条件 -->
<div class="center">
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl">
			<span>选择目录：</span>			
				<input type="hidden" id="curTypeId" name ="curTypeId" value="${curTypeId}"/>
				<input type="hidden" id="typeNames" name="typeNames" value="${typeNames}"/>
				<div  id="typeNames01" class="duouan">${typeNames}</div>			
		</p>
		<p class="fl" style="margin-left:60px;">
			<span>考试名称：</span>
			<input type="text" id = "queryExamName" name = "queryExamName" value = "${queryExamName}"/>
		</p>
		<p class="fl" style="margin-left:60px;">
			<span>考试类别：</span>
			<div class="fl select" >
				<div class="tik_position">
					<b class="ml5">请选择</b>
					<p class="position01"><i class="bjt10"></i></p>
				</div>
				<ul class="fl list" style="display:none;">
					<select name="examType" style="display:none;">
						<option value="-1">请选择</option>
						<option value="1"<c:if test="${examType==1}"> selected</c:if>>考试</option>						
						<option value="2"<c:if test="${examType==2}"> selected</c:if>>练习</option>
					</select>
					<li>请选择</li>
					<li>考试</li>
					<li>练习</li>
				</ul>
			</div>
		</p>
	</div>
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl">
			<span>考试状态：</span>
			<div class="fl select" >
				<div class="tik_position">
					<b class="ml5">全部</b>
					<p class="position01"><i class="bjt10"></i></p>
				</div>
				<ul class="fl list" style="display:none;">
					<select name="examState" style="display:none;">
						<option value="-1">全部</option>
						<option value="1"<c:if test="${examState==1}"> selected</c:if>>进行中</option>						
						<option value="2"<c:if test="${examState==2}"> selected</c:if>>未开始</option>
						<option value="3"<c:if test="${examState==3}"> selected</c:if>>已结束</option>
						<option value="0"<c:if test="${examState==0}"> selected</c:if>>禁用</option>
					</select>
					<li>全部</li>
					<li>进行中</li>
					<li>未开始</li>
					<li>已结束</li>
					<li>禁用</li>
				</ul>
			</div>
		</p>
		<div class="fl cas_anniu">
			<a href="javascript:search();" class="fl" style="width:70px;margin-left:130px;">查询</a>
		</div>
	</div>
</div>
</form>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
		<div class="mt5 kit1_tiaojia">
			<div class="fr cas_anniu">
				<a href="${ctx}/peixun_page/paperManage/examAdd.jsp" class="fl" style="width:80px;">新增考试</a>
				<a href="${ctx}/peixun_page/paperManage/exerciseAdd.jsp" class="fl" style="width:80px;margin-left:10px;">新增练习</a>
			</div>
		</div>
		<div class="clear"></div>
			    <display:table requestURI="${ctx}/examManage/examList.do"
				 id="item" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${ExamList.fullListSize}" pagesize="${ExamList.objectsPerPage}" list="${ExamList}"
				 style="font-size:12px;" class="mt10 table" keepStatus="true">
                    <display:column property = "name" title="名称" style="text-align:center;width:28%"></display:column>
                    <display:column title="考试方式" style="text-align:center;width:6%">
                        <c:if test="${item.exam_time == 1}">定时</c:if>
                        <c:if test="${item.exam_time == 2}">正常</c:if>
                    </display:column>
                    <display:column title="类别" style="text-align:center;width:5%">
                        <c:if test="${item.exam_type eq 1}">考试</c:if>
                        <c:if test="${item.exam_type eq 2}">练习</c:if>
                    </display:column>
                    <display:column title="考生人数" style="text-align:center;width:6%">
                   		${item.countUsers}
                    </display:column>
                    <display:column title="有效期" style="text-align:center;width:20%">
                        开始时间：${fn:substring(item.start_time,0,19)}<br/>结束时间：${fn:substring(item.end_time,0,19)}
                    </display:column>
                    <display:column title="创建时间" style="text-align:center;width:8%">
                        ${fn:substring(item.create_time,0,19)}
                    </display:column>
                    <display:column title="状态" style="text-align:center;width:8%">
                        <c:if test="${item.state eq 1}">进行中</c:if>
                        <c:if test="${item.state eq 2}">未开始</c:if>
                        <c:if test="${item.state eq 3}">已结束</c:if>
                        <c:if test="${item.state eq 0}">禁用</c:if>
                    </display:column>
                    <display:column title="操作" style="text-align:center;width:20%">
                      	<a href="javascript:delExam('${item.id}');" class="">删除</a>
					<a href="javascript:modifyExam('${item.id}');" class="">修改</a>
									<a href="javascript:show_tiaozhong('${item.id}', '${item.exam_type_name}',${item.exam_type_id});" class="tiaozheng">调整</a>
					<a href="${ctx}/examManage/examView.do?examId=${item.id}">查看</a>
                    </display:column>
                </display:table>
		<div class="clear"></div> 
	</div>
</div>
<!-- 修改密码 -->
<div class="toumingdu make01" style="display:none;">
	
</div>
<!-- 调整 -->
<div class="toumingdu make02" style="display:none;">
	<div class="lec_center" style="height:300px;">
		<div style="padding-top:50px;">
			<div class="lc_shengcheng">
				<span>当前目录：</span>
				<input type="text" id="ex_typename" disabled="disabled"/>
			</div>
			<div class="clear"></div>
			<div class="mt30 lc_shengcheng">
				<span>调整目录：</span>
				<input type="hidden" id="curTypeId1" name ="curTypeId1" value="${curTypeId1}"/>
				<input type="hidden" id="typeNames1" name="typeNames1" value="${typeNames1}"/>
				<div id="typename_tiaozhong" class="duouan" style="width:260px;height:30px;">${typeNames1} </div>
			</div>
			<div class="clear"></div>
			<div class="cas_anniu" style="margin-top:50px;margin-left:170px;">
				<input type="hidden" id="tiaozhongID" name="tiaozhongName" value=""/>
				<a href="javascript:tiaozhongExam();" class="fl queren" style="width:70px;">确认</a>
				<a href="#" class="fl queren" style="width:70px;margin-left:40px;">取消</a>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>
<script type="text/javascript">

	$('.list li').click(function(){
		var str=$(this).text();
		$(this).parent().parent().find('div').find('b').text(str);
		$(this).parent().find('option').prop('selected', '');
		$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
		$('.list').slideUp(50);
	});
	$(document).click(function(){
		$('.list').hide('fast');
	});
	

	var ajaxurl;
	var controlStyle = 2;
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
		
		
		$('.queren').click(function(){
			$('.make02').hide();
			controlStyle = 2;
		});
		$('#typeNames01').click(function(){
			initPropList("选择目录","${ctx}/examManage/getExamListType.do",-1,0,5,0,$('#curTypeId'),$('#typeNames'),$('#typeNames01'));
			$('.att_make01').show();
		});
		//调整
		$('#typename_tiaozhong').click(function(){
			initPropList("选择目录","${ctx}/examManage/getExamListType.do",-1,0,5,0,$('#curTypeId1'),$('#typeNames1'),$('#typename_tiaozhong'));
			$('.att_make01').show();
		});	
		 $('.bjt_kt').click(function(){
			$('.att_make01').hide();
		});
});

	var kuangcode;
	var kuang;
	var kuangview;
	var initsubmenu;
	var initsubtitle;
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
		ajaxurl = _ajaxurl;
		initsubtitle = _title;	  
		
		 if (_title == "选择目录"){
			$('.xs_biaoti .xs_er .attr_xueke01').text('选择目录');
			initsubmenu="选择目录";
		}

		$('.xs_kuangcode').text($(_kuangcode).val());
		$('.xs_kuang').text($(_kuang).val());
		$('.attr_xueke01').text(initsubtitle);
		$('.attr_xueke01').removeAttr("id");
		
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
		var url = ajaxurl + "?parentId=" +_initType;
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
			var url = ajaxurl + "?parentId=" +selid;
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

			if (selid == 0)
			{
				$('.xs_biaoti').html('<div class="fl xs_er"><p class="fl attr_xueke01">'+initsubmenu+'</p><i class="fl xs_bjt01"></i><em class="fl" style="display:none;">></em></div>');
				selid = -1;
			}

			var url = ajaxurl + "?parentId=" +selid;
			
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
			var propname = '<em class="delem">' + $(p).find('em').text() + '</em><br>';
			
			if ($(this).prop('checked')){
				var selstr = $('.xs_kuangcode').text();
				var selarray = selstr.split(",");
				var newarray = new Array();
				$(selarray).each(function(key, val){
					if (val != "") newarray.push(val);
				});
				newarray.push(id+",");
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
		
		
		//selected item mark checked(checkbox).
		$('.xs_ul input[type="checkbox"]').each(function(key, val){
			var p = $(this).parent().find('.attr_xueke04').eq(0);
			var id = $(p).prop('id');
			var selstr = $('.xs_kuangcode').text();
			var selarray = selstr.split(",");
			var idx = selarray.indexOf(id);
			
			if (idx>=0) $(this).prop("checked", true);
		});
				//selected item mark checked(checkbox).
		$('.xs_ul input[type="radio"]').each(function(key, val){
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
		$(result).each(function(key, value){
			str += "<li><div class=''>";
			if(value.state != "closed"){
				if(controlStyle==2){
					str += '<input class="fl" style="margin-top:5px;" type="checkbox">';
				}else if(controlStyle == 1){
					str += '<input class="fl" style="margin-top:5px;" type="radio" name="control">';
				} 
			}
			str += '<p class="fl attr_xueke04"' + ' id="'+ value.id +'"' + '><em class="fl">' + value.text + '</em>';
			if(value.state == "closed")
				str += '<i class="fl ml10 kti_bjt2"></i>';
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

function selectProp(){
		$(kuangcode).val($('.xs_kuangcode').text());
		$(kuang).val($('.xs_kuang').text());
		$(kuangView).text($('.xs_kuang').text());
		var url = "${ctx}/examManage/getExamListType.do";
		var ids = $(kuangcode).val();
		
		
		$('.att_make01').hide();
}
function search()
{
	document.getElementById("queryForm").submit();
}
function delExam(selid)
{
	if(confirm("您确定要删除吗?")){
		var url = "${ctx}/examManage/examDelete.do" + "?curIds=" +selid;
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'text',
		    success: function(result){
			   if(result == "success"){
					alert('操作成功！');
			   }else if(result == "usingExam"){
					alert('考试已被使用，不能删除！');
			   }else{
			   		alert('添加失败');
			   }
			   document.location.reload(true);
		    }
		});	
	}
	else return false;	
}

//YHQ，2017-05-17
function modifyExam(selid){	
	var url = "${ctx}/examManage/examList.do?method=usingExam&examId="  +selid;
	$.ajax({
	    url:url,
	    type: 'POST',
	    dataType: 'text',
	    async:false,
	    success: function(result){
		   if(result == "noUsingExam"){
				document.location.href = "${ctx}/examManage/examEdit.do?examId=" + selid ;
		   }else if(result == "usingExam"){
			   alert('考试已被使用，不能修改！');
		   }
	    }
	});			
}

function tiaozhongExam()
{
	var url = "${ctx}/examManage/examTiaozhong.do" + "?examId="+$('#tiaozhongID').val()+"&typeid="+$('#curTypeId1').val();
	$.ajax({
	    url:url,
	    type: 'POST',
	    dataType: 'text',
	    success: function(result){
		   if(result == "success"){
				alert('操作成功！');
		   }
		   else
		   {
		   		alert('添加失败');
		   }
		   document.getElementById("queryForm").submit();
	    }
	});
	controlStyle = 2;
}

function show_tiaozhong(examid, typename,typeid) 
{
	controlStyle = 1;
	$('#tiaozhongID').val(examid);
	$('#ex_typename').val(typename);
	$('#curTypeId1').val(typeid);
	$('#typeNames1').val(typename);
	$('#typename_tiaozhong').text(typename);
	
	$('.make02').show();
}
</script>
</body>

