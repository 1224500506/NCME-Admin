<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>培训后台</title>
		<%@include file="/commons/taglibs.jsp"%>
	</head>
<%@include file="/peixun_page/top.jsp"%>
<body>

<!-- 查询条件 -->
<div class="center">
<form action="${ctx}/contentManage/issueManage.do?method=list" method="post" id="queryForm">
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl">
			<span style="width:5em;text-align:right;">分类：</span>
		</p>
		<div class="fl select" style="margin-right:20px;">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display: none;">
			<select name="model.type" style="display:none;">
				<option value="">全部</option>
				<option value="1" <c:if test="${item.type==1}">selected</c:if>>通知</option>
				<option value="2" <c:if test="${item.type==2}">selected</c:if>>公告</option>
			</select>
				<li>全部</li>
				<li>通知</li>
				<li>公告</li>
			</ul>
		</div>
		<p class="fl" style="margin-right:20px;">
			<span>标题：</span>
			<input type="text" name="model.title" value="${item.title}"/>
		</p>
		<p class="fl" style="margin-right:20px;">
			<span>开始时间：</span>
			<input type="text" class="start_date datepicker" name="model.startDate" value="${item.startDate}"/>
		</p>
		<p class="fl">
			<span>结束时间：</span>
			<input type="text" class="end_date datepicker" name="model.endDate" value="${item.endDate}" />
		</p>
		<div class="clear"></div>
		<p class="fl" style="margin-top:10px;">
			<span style="width:5em;text-align:right;">授权站点：</span>
		</p>
		<div class="fl select" style="margin-top:10px;margin-right:30px">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display: none;">
			<select name="model.siteId" style="display:none;">
				<option value="">全部</option>
				<c:forEach items="${sitelist}" var="site">
					<option value="${site.id}" <c:if test="${site.id eq item.siteId}">selected</c:if>>${site.domainName}</option>
				</c:forEach>
			</select>
				<li>全部</li>
				<c:forEach items="${sitelist}" var="site">
					<li>${site.domainName}</li>
				</c:forEach>
			</ul>
		</div>
		<p class="fl ml30 cas_anniu" style="margin-top:10px;">
			<a href="javascript:void(0);" class="fl chaxun" style="width:70px;margin-bottom:10px;">查询</a>
		<p class="fr cas_anniu mt10">
			<a href="javascript:void(0);" class="fr add_btn" style="width:140px;margin-bottom:10px;">添加通知公告</a>
		</p>
	</div>

</form>
</div>

<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
		<!-- <div class="mt5 kit1_tiaojia">
			<div class="fr cas_anniu">
				<a href="kaoshi01.html" class="fl" style="width:80px;">新增</a>
			</div>
		</div>
		<div class="clear"></div> -->
<button type="button" class="btn_blue fr att_setorder" style="margin-right:15%;cursor:pointer;" name="list_filter">保存显示顺序</button>
<div class="clear"></div>
		<display:table requestURI="${ctx}/contentManage/issueManage.do?method=list" 
				 id="contentIssue" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}" name="page"
				 class="mt10 table" keepStatus="true" decorator="com.hys.exam.displaytag.OverOutWrapper">
			<display:column title="序号" style="width:5%;">
				${contentIssue_rowNum}
			</display:column>
			<display:column  style="width:25%;" title="标题" property="title" /> 
			<display:column  style="width:5%;" title="分类">
				<c:if test="${contentIssue.type==1}">通知</c:if>
				<c:if test="${contentIssue.type==2}">公告</c:if>
			</display:column> 
			<display:column  style="width:20%;" title="起止时间" >
				${contentIssue.startDate}至${contentIssue.endDate}
			</display:column> 
			<display:column  style="width:20%;" title="授权站点" >
				<c:forEach items="${sitelist}" var="site">
					<c:forEach items="${contentIssue.siteIds}" var="sid">
						<c:if test="${site.id==sid}">${site.domainName}<br/></c:if>
					</c:forEach>
				</c:forEach>
			</display:column>
			<display:column style="width:5%;" title="排序" >
				<input type="number" class="att_orderNum" style="width:3em;text-align:center;" id="${contentIssue.id}" value="${contentIssue.orderNum}" />
			</display:column>
			<display:column  style="width:15%;" title="操作" >
				<a class="edit_btn" href="javascript:edit(${contentIssue.id});" >编辑</a> 
				<a href="javascript:del(${contentIssue.id});" >删除</a> 
			</display:column>
		</display:table>
		
	</div>
</div>

<!-- layer内容 -->
<div id="layercontent" style="display:none;">
<div class="center">
<form id="editfrm" name="editfrm" action="#" method="post">
	<input type="hidden" id="reId" name="model.id" value="0" />
	<div class="tiaojian">
		<p class="fl">
			<span style="width:6em;text-align:right;"><em style="color:red">*</em>类别：</span>
			<ul class="fl"><li>
			<input type="radio" name="model.type" value="1" class="reType" /> 通知
			<input type="radio" name="model.type" value="2" class="reType" /> 公告
			</li></ul>
		</p>
		<div class="clear" style="margin-bottom:20px;"></div> 
		<p class="clear" style="margin-bottom:20px;">
			<span style="width:6em;text-align:right;"><em style="color:red">*</em>标题：</span>
			<input type="text" id="reTitle" name="model.title" style="width:400px;" value="" />
		</p>
		<div class="clear"></div> 
		<p class="fl" style="margin-right:10px;">
			<span style="width:6em;text-align:right;"><em style="color:red">*</em>开始时间：</span>
			<input type="text" class="start_date datepicker" id="reStartDate" name="model.startDate" value="" />
		</p>
		<p class="fl">
			<span><em style="color:red">*</em>结束时间：</span>
			<input type="text" class="end_date datepicker" id="reEndDate" name="model.endDate" value="" />
		</p>
		<div class="clear" style="margin-bottom:20px;"></div> 
		<p class="fl">
			<span style="width:6em;text-align:right;">授权站点：</span>
			<ul class="fl" style="display:inline-block">
				<c:forEach items="${sitelist}" var="site">
					<li><input type="checkbox" class="reSiteid" name="model.siteIds" value="${site.id}" />${site.domainName}</li>
				</c:forEach>
			</ul>
		</p>
		<div class="clear" style="margin-bottom:20px;"></div>
			<p class="clear">
			<span style="width:6em;text-align:right;"><em style="color:red">*</em>内容：</span>
			<textarea name="model.content" id="reContent" rows="10" cols="60"></textarea>
		</p>
	</div>
</form>
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
	
	$('.queren').click(function(){
		$('.make02').hide();		
	});

	//select box
		selectInit();

		$('.chaxun').click(function(){
			$('#queryForm').submit();
		});

	$.extend( $.fn.pickadate.defaults, {
		monthsFull: [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
		monthsShort: [ '一', '二', '三', '四', '五', '六', '七', '八', '九', '十', '十一', '十二' ],
		weekdaysFull: [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
		weekdaysShort: [ '日', '一', '二', '三', '四', '五', '六' ],
		today: '今日',
		clear: '删',
		firstDay: 1,
		format: 'yyyy-mm-dd',
		formatSubmit: 'yyyy-mm-dd'
	});

	// 日期选择控件
	$(".datepicker").pickadate({
		monthSelector: true,
//		yearSelector: true,
		today:false,
		clear:false,
		dateMax : true,
		dateMin : [2010, 1, 1],
		yearSelector: 100
	});

	var win_w = "800px";
	var win_h = "600px";
	var add_cont = $('#layercontent').html();
	$('#layercontent').remove();
	
	$(".add_btn").click(function() {
		document.location.href="${ctx}/contentManage/issueManage.do?method=add";
		return;
		layer.open({
			type: 1,
			title: "添加通知公告",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, win_h], //宽高
			content: add_cont,
			closeBtn: 0,
			btn: ['保存', '取消'],
			yes: function (index, layero) {
				//缩写保存功能
				var startDate=$('#reStartDate').val();
				var endDate=$('#reEndDate').val();
				if(endDate!="" && startDate>endDate)
				 {
				 	alert("通知公告时间不能交叉！")
				 	return;
				 }
		
				if (saveIssue()){
					var url = "${ctx}/contentManage/issueManage.do?method=save";
					var params = $('#editfrm').serialize();
				
					$.ajax({
					    url:url,
					    type: 'POST',
					    data: params,
					    dataType: 'text',
					    success: function(result){
						   if(result == 'success'){
						   		alert('保存成功！');
						   		window.location.reload(true);
						   }else{
						   		alert('无法保存，名称不能重复!');
						   }
					    }
					});
				}
				//	layer.close(index);
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
		$(".datepicker").pickadate({
			monthSelector: true,
	//		yearSelector: true,
			today:false,
			clear:false,
			dateMax : true,
			dateMin : [2010, 1, 1],
			yearSelector: 100
		});
		$('.layui-layer').css('overflow',"hidden");
		
	});
	
	$(".edit_btn").click(function() {
		return;
		layer.open({
			type: 1,
			title: "编辑通知公告",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, win_h], //宽高
			content: add_cont,
			closeBtn: 0,
			btn: ['保存', '取消'],
			yes: function (index, layero) {
				//缩写保存功能
				var startDate=$('#reStartDate').val();
				var endDate=$('#reEndDate').val();
				if(endDate!="" && startDate>endDate)
				 {
				 	alert("通知公告时间不能交叉！")
				 	return;
				 }
				
				if (saveIssue()){
					var url = "${ctx}/contentManage/issueManage.do?method=save";
					var params = $('#editfrm').serialize();
				
					$.ajax({
					    url:url,
					    type: 'POST',
					    data: params,
					    dataType: 'text',
					    success: function(result){
						   if(result == 'success'){
						   		alert('保存成功！');
						   		window.location.reload(true);
						   }else{
						   		alert('无法保存，名称不能重复!');
						   }
					    }
					});
				}
				//	layer.close(index);
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
		$(".datepicker").pickadate({
			monthSelector: true,
	//		yearSelector: true,
			today:false,
			clear:false,
			dateMax : true,
			dateMin : [2010, 1, 1],
			yearSelector: 100
		});
		$('.layui-layer').css('overflow',"hidden");
	});

	$('.att_setorder').click(function(){
		setorderNum();
	});
});

function edit(id){
	document.location.href="${ctx}/contentManage/issueManage.do?method=edit&id="+id;
	return;
	var url = "${ctx}/contentManage/issueManage.do?method=detail";
	var params = "id="+id;

	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'json',
	    success: function(result){
		   if(result != ''){
				var issue = result.issue;
				$('#reId').val(issue.id);
				$('.reType').prop('checked', '');
		   		$('.reType').each(function(){
		   			if ($(this).val() == issue.type)
		   				$(this).prop('checked', 'checked');
		   		});
		   		$('#reTitle').val(issue.title);
		   		$('#reStartDate').val(issue.startDate);
		   		$('#reEndDate').val(issue.endDate);
		   		$('.reSiteid').prop('checked', '');
		   		$('.reSiteid').each(function(){		   			
		   			if (issue.siteIds.indexOf(eval($(this).val())) >= 0){
		   				$(this).prop('checked', 'checked');
		   			}
		   		});
		   		$('#reContent').val(issue.content);
		   }else{
		   		alert('检查内容失败!');
		   }
	    }
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

function saveIssue()
{
	var typeval = $('.reType:checked').val();
	if (typeval == undefined){
		alert ("请选择类别！");
		return false;
	}
	if ($('#reTitle').val().trim() == ""){
		alert ("请输入标题！");
		return false;
	}
	if ($('#reStartDate').val() == ""){
		alert ("请输入开始时间！");
		return false; 
 	}
	if( $('#reEndDate').val() == ""){
		alert ("请输入结束时间！");
		return false; 
	}
	if ($('#reContent').val().trim() == ""){
		alert ("请输入内容！");
		return false;
	}
	

	return true;
}

function del(id)
{
	if (!confirm("您确定要删除吗？"))return;
	var url = "${ctx}/contentManage/issueManage.do?method=del";
	var params = "id=" + id;

	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'text',
	    success: function(result){
		   if(result == 'success'){
		   		alert('删除成功！');
		   		window.location.reload(true);
		   }else{
		   		alert('删除失败!');
		   }
	    }
	});
	
}

function setorderNum(){
	var url = "${ctx}/contentManage/issueManage.do?method=setorder";
	var params = "orderstr=";
	var k = 0;
	
	
	$('.att_orderNum').each(function(){
		var id = $(this).prop('id');
		var val = $(this).val();
		params+= id+"_"+val+";";
		var j = 0;
		
		$('.att_orderNum').each(function(){
			var vals = $(this).val();
			if (vals == val) j++;
		});
		/* if (j>1) {
			k=1; 
			return false;			
		} */
		
	});
	/* if (k>0){
		alert("排序编号不能重复！");
		window.location.reload(true);
		return;
	} */
	
	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'text',
	    success: function(result){
		   if(result == 'success'){
		   		window.location.reload(true);
		   }else{
		   	//	alert('删除失败!');
		   }
	    }
	});
	return true;
}
</script>
</body>
</html>