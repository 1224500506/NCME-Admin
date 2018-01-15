<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/commons/taglibs.jsp"%>
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
		<title>培训后台</title>
		<link rel="stylesheet" href="${ctx}/peixun_page/css/reset.css" />
		<link rel="stylesheet" href="${ctx}/peixun_page/css/index.css" />
		<link rel="stylesheet" href="${ctx}/peixun_page/css/pickadate/default.css" />
		<link rel="stylesheet" href="${ctx}/peixun_page/css/pickadate/default.date.css" />
		<link rel="stylesheet" href="${ctx}/peixun_page/css/fontawesome/css/font-awesome.css" />
		<link rel="stylesheet" href="${ctx}/peixun_page/css/ueditor.min.css" />
		<script type="text/javascript" src="${ctx}/peixun_page/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/drawWnd.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/layer/layer.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/picker.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/picker.date.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/legacy.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/ueditor/ueditor.config.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/ueditor/ueditor.all.min.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/ueditor/lang/zh-cn/zh-cn.js"></script>
		<style>
			input[type=text]{padding:0 5px;}
			input:disabled{background:#f0f0f0;color:#ccc;}
			.center{margin:15px auto 0;}
		</style>
	</head>
<body>
<!-- 查询条件 -->
<div class="center">
	<form action="${ctx}/examManage/examPaperDetail2.do" id="fm" name="fm" method="post">
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl">
			<span>选择目录：</span>
			 	<input type="hidden" id="curTypeId" name ="type_Ids" value="${type_Ids}"/>
				<input type="hidden" id="typeNames" name="typeName" value="${typeNames}")/>
				<div  id="typeNames01" class="duouan">${typeNames}</div>
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
			<input type="text" name="createDateFrom" id="createDateFrom" value="${createDateFrom}" onClick="javascript:formatDate('form')" style="width:85px;"/>
			<span style="padding:0px 7px 0px 7px;">至</span>
			<input type="text" name="createDateTo" id="createDateTo" value="${createDateTo}" onClick="javascript:formatDate('to');" style="width:85px;"/>
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
		<div class="mt5 kit1_tiaojia">
			<div class="fr cas_anniu">
				<a href="#" class="fl" id = "addPaper-link1" style="width:80px;">添加</a>
				<a href="javascript:window.close();" class="fl" style="width:80px;margin-left:10px;">返回</a>
			</div>
		</div>
		<div class="clear"></div>		
		<form action="" id="formB"  method="post">
			<display:table id="item" list="${List}" requestURI="${ctx}/examManage/examPaperDetail2.do"  size="${list.fullListSize}" pagesize="${list.objectsPerPage}"
							style="width:100%;" decorator="com.hys.exam.displaytag.OverOutWrapper" class="mt10 table" excludedParams="method">
				<display:column title="<input type='checkbox' class='chkall' onclick='CheckAll(this.form);'>" style="width:10%;text-align:center">
					<input type="checkbox" class='chk' name="ids" value="${item.id}+${item.name}/${item.paper_mode}/${item.paper_score}/${item.question_num}/${fn:substring(item.create_date,0,10)}" />
				</display:column>	
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
				<display:column title="操作" style="width:40%;text-align:center;">
					<a href="${ctx}/paperManage/paperView.do?handle=view&paperId=${item.id}" class="">查看</a>
				</display:column>
			</display:table>
		</form>
		

		<div class="clear"></div> 
	</div>
</div>
<!-- 调整 -->
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
			<div class="fl select xs_zhcombo" style="margin:8px 0 0 16px;display:none;">
				<div class="tik_position">
					<b class="ml5">请选择</b>
					<p class="position01"><i class="bjt10"></i></p>
				</div>
				<ul class="fl list" style="display: none;" id="zclx_combo">
					<li>请选择</li>
				</ul>
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
		var winObj = parent.opener;
	    var paperNamesId = winObj.paperNamesId;
	    var examType = $("#eexamType", winObj.document).val();//默认为考试状态
	    var examType1 = $("#eexamType1",winObj.document).val();
	   
	   
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
		
		$('.bjt_kt,.attr_xueke').click(function(){
			$('.att_make01').hide();
		});
			
		//check all
		$('.chkall').click(function(){
			if ($(this).attr('checked') == 'checked')
				$('.chk').attr('checked' , 'checked');
			else
				$('.chk').removeAttr('checked');
		});
		$('#typeNames01').click(function(){
			initPropList("选择目录","${ctx}/paperManage/getPaperListType.do",-1,0,5,0,$('#curTypeId'),$('#typeNames'),$('#typeNames01'));
			$('.att_make01').show();
		});
		
	    $(function () {
	        
	       $("input[name='ids']").each(function () {
	            var str = this.value;
	            var tempArray = str.split("+");
	            var temp1 = tempArray[0];
	            for (var i in paperNamesId) {
	                if (temp1 == paperNamesId[i]) {
	                    this.disabled = "true";
	                    this.style.display = 'none';
	                }
	            }
	            if (temp1 == $("#paerId", winObj.document).val()) {
	            	this.disabled = "true";
	                this.style.display = 'none';
	            }
	        });
	        
			$("#addPaper-link1").click(function () {
	            
	            var flag = false;
	            var seleNum = 0;
	            $("input[name='ids']").each(function (i) {
	                if (this.checked && this.disabled == false) {
	                    seleNum = seleNum + 1;
	                }
	            });
	
	            $("input[name='ids']").each(function () {
	
	                if (this.checked && this.disabled == false) {
	
	                    var str = this.value;
	                    var tempArray = str.split("+");
	                    var temp1 = tempArray[0];
	                    var temp2 = tempArray[1].split("/");
	                    winObj.paperNamesId.push(temp1);
	                    winObj.paperNamesInfos.push(temp2);
	                    winObj.paperNamesId1.push(temp1);
	                    flag = true;
	                    this.disabled = "true";
	                    this.style.display = 'none';
	                }
	
	            });	
				
				
	            if (flag) {
	                winObj.setPaper();
	                alert("添加试卷成功！");
	                parent.window.close();
	            } else {
	                alert("请选择试卷！");
	            }
				
	        });
	    });
	    
});

function formatDate(time) // get selected time
{ 
	if (time != null && time == "to") // First, select startTime
	{	//WdatePicker()
		if ($("#createDateFrom").val() != "") // when startTime selected already 
		{
			WdatePicker({dateFmt:'yyyy-MM-dd', minDate:$("#createDateFrom").val(), errDealMode:'-1'});
		}
		else WdatePicker();
	}
	if (time != null && time == "form") // First, select endTime
	{	
		if ($("#createDateTo").val() != "") // when endTime selected already
		{	
			WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'createDateTo\',{d:0});}',errDealMode:'-1'});
		}
		else WdatePicker();
	}
}

var kuangcode;
	var kuang;
	var initType;
	var initsubmenu;
	var kuangView;

function initPropList(_title,_ajaxurl, _initType, _initId, _selectLvl, _checkLvl, _kuangcode, _kuang, _kuangView){
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
		initsubtitle = _title;	  
		  
		
		 if (_title == "选择目录"){
			$('.xs_biaoti .xs_er .attr_xueke01').text('选择目录');
			initsubmenu="选择目录";
		}
		$('.xs_kuangcode').text($(kuangcode).val());
		$('.xs_kuang').text($(kuang).val());
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
				//	alert(selstr);
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
</script>
</body>
</html>