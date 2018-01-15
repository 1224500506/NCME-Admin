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

<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<body>
<form name="Form" id="Form" action="${ctx}/contentManage/sentenceManage.do?method=list" method="post">

<div class="center">
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl" style="margin:0 0 10px 0">
			<span style="width:5em;text-align:right;">分类：</span>
		</p>
		<div class="fl select"  style="margin:0 20px 0 0">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display:none;">
			
			 	<select class="fl select"  style="display:none;" name="model.type" value="">
			 		<option value='0' <c:if test="${model.type == 0}">selected</c:if> >全部</option>
			 		<option value='1' <c:if test="${model.type == 1}">selected</c:if> >资讯公告</option>
					<option value='2' <c:if test="${model.type == 2}">selected</c:if> >政策法规</option>
					<option value='3' <c:if test="${model.type == 3}">selected</c:if> >乡医培训</option>
					<option value='4' <c:if test="${model.type == 4}">selected</c:if> >分类4</option>
			 	</select>
			 <li>全部</li>
			 <li>资讯公告</li>
			 <li>政策法规</li>
			 <li>乡医培训 </li>
			 <li>分类4</li></ul>
			 
		</div>
		
        <p class="fl" style="margin:0 20px 10px 0">
			<span style="width:5em;text-align:right;">标题：</span>
			<input type="text" id = "title" name = "model.title" value="${model.title}" />
		</p>
		
		<p class="fl" style="margin:0 20px 10px 0">
			<span style="width:5em;text-align:right;">发布时间：</span>
			<input type="text" name="start_date" id="start_date" class="editInput"
				datatype="*" nullmsg="请选择开始时间！" value="${model.start_date}"
				onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'start_date\')}'})"/>
			<span>--</span>
			<input type="text" name="end_date" id="end_date" class="editInput"
				datatype="*" nullmsg="请选择结束时间！"  value="${model.end_date}"
				onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'end_date\')}'})" />
			
		</p>
	 	<div class="clear"></div>
	    <p class="fl" style="margin:0 0 10px 0">
	 		<span style="width:5em;text-align:right;">状态：</span>
	 		</p>
	 	<div class="fl select"  style="margin:0 20px 0 0">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display:none;">
	 		<select class="fl select" style="display:none;" name="model.state" value="">
		 		<option value='0' <c:if test="${model.state == 0}">selected</c:if> >全部</option>
		 		<option value='1' <c:if test="${model.state == 1}">selected</c:if> >未发布</option>
				<option value='2' <c:if test="${model.state == 2}">selected</c:if> >已发布</option>
	 		</select>
	 		 <li>全部</li>
			 <li>未发布</li>
			 <li>已发布</li></ul>
	 	</div>	
		
		<p class="fl" style="margin:0 0 10px 0">
			<span style="width:5em;text-align:right;">授权站点：</span>
		</p>
		<div class="fl select">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display:none;">
			<select class="fl select" style="display:none;" name="site_id">
				<option value="0" <c:if test="${site_id == 0}">selected</c:if> >全部</option>
				<c:forEach items="${siteList}" var="site">
					 <option value="${site.id}" <c:if test="${site_id == site.id}">selected</c:if> >${site.domainName}</option>
				</c:forEach>	
				
			</select> 
				<li>全部</li>
			<c:forEach items="${siteList}" var="site">
				<li>${site.domainName}</li>
			</c:forEach>
			</ul>	
		</div>

		<p class="fl cas_anniu">
			<span style="width:5em;">&nbsp;</span>
			<a href="javascript:$('#Form').submit();" class="fl" style="width:70px;margin-bottom:10px;">查询</a>
		</p>
		<p class="fr cas_anniu">
			<a class="fr" style="width:140px;" href="${ctx}/contentManage/sentenceManage.do?method=add">添加文章</a>
		</p>
		
	</div>
</div>
</form>
<div class="clear"></div>
<div class="bjs"   ></div>
 
<!-- 内容 -->

<div class="center none" style="">
	<div class="tk_center01">
			
		<display:table name="list" requestURI="${ctx}/contentManage/sentenceManage.do?method=list" id="p" excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}" 
				 class="mt10 table"   >
			<display:caption><button type="button" class="btn_blue fr att_setorder" style="margin-right:22%;cursor:pointer;" name="list_filter">保存显示顺序</button></display:caption>
			<display:column
				title="序号"
				style="width:5%;text-align:center">
				${p_rowNum}
			</display:column>			
			<display:column property="title" title="标题" style="width:25% "></display:column>			
			<display:column  title="分类" style="width:5% ">
			    <c:if test="${p.type == 1}">资讯公告</c:if> 
				<c:if test="${p.type == 2}">政策法规</c:if>
				<c:if test="${p.type == 3}">乡医培训</c:if> 
				<c:if test="${p.type == 4}">分类4</c:if></display:column>
			
			<display:column property="deli_date" title="发布时间"  style="width:10%" > </display:column>
		   
			<display:column title="授权站点" style="width:20%">
				<c:forEach items="${p.siteList}" var="site">
					${site.domainName}<br>
				</c:forEach>
			</display:column>
			<display:column title="状态" style="width:5%">
				<c:if test="${p.state == 1}">未发布</c:if> 
				<c:if test="${p.state == 2}">已发布</c:if>
			</display:column>
			<display:column title="排序" style="width:10%">
				<c:if test="${p.ordernum==999999999}">
					<input type="number" class="att_orderNum" style="width:3em;text-align:center;" id="${p.id}" value="" data-category="${p.type }"/>
	 			</c:if>
				<c:if test="${p.ordernum!=999999999}">
					<input type="number" class="att_orderNum" style="width:3em;text-align:center;" id="${p.id}" value="${p.ordernum}" data-category="${p.type }"/>
	 			</c:if>
			 </display:column>
			<display:column title="操作" style="width:25%">				
				<a href="javascript:edit(${p.id},${p.state});">编辑</a>
				<a href="javascript:deleteSentence(${p.id},${p.state});">删除</a>
				<c:if test="${p.state == 1}"><a href="javascript:updateState(${p.id},2);" class="post_article" ><button class="btn_blue" style="width:70px">发布</button></a></c:if>
				<c:if test="${p.state == 2}"><a href="javascript:updateState(${p.id},1);" class="post_article_cancel" ><button class="btn_red" style="width:70px">取消发布</button></a></c:if>
				<%-- <c:if test="${p.state == 1}"><button class="btn_blue" style="width:70px"><a href="javascript:updateState(${p.id},2);" class="post_article" >发布</a></button></c:if>
				<c:if test="${p.state == 2}"><button class="btn_red" style="width:70px"><a href="javascript:updateState(${p.id},1);" class="post_article_cancel" >取消发布</a></button></c:if> --%>
			</display:column>				
			
		</display:table>
		<div class="clear"></div>
	</div>
</div>

<script type="text/javascript">
var msg = "${msg}";
if(msg != "")
{
	alert(msg);
}
$(function(){	
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "lc_left0" + menuindex;
		$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");	
		
	selectInit();
		$('.att_setorder').click(function(){
		setorderNum();
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
	var input = $(".datepicker").pickadate({
		monthSelector: true,
		today:false,
		clear:'清空',
		close:'关闭',
		dateMax : true,
		dateMin : [2010, 1, 1],
		yearSelector: 100
	});
		
		$(document).click(function(){
			$('.list').hide('fast');
		});		
});
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
function updateState(id, state)
{
	var url = "${ctx}/contentManage/sentenceManage.do";
	var params = "method=updateState&id=" + id;
	params += "&state=" + state;
	
	$.ajax({
		url	:	url,
		data :	params,
		dataType:"text",
		type: 'POST',
		success: function(result){
			if(result == "success")
			{
				window.location.reload(true);
			}
			else
			{
				alert("发布失败");
			}
		}		
	});	
}
	

 
function deleteSentence(id,state) {
	if(state == 2){
		alert("不能删除已发布的文章！");
		return;
	}
	if(confirm("删除?")){

		var url = "${ctx}/contentManage/sentenceManage.do";
		var params = "method=delete&id=" + id;
		
		$.ajax({
			url	:	url,
			data :	params,
			dataType:"text",
			type: 'POST',
			success: function(result){
				if(result == "success")
				{
					alert("删除成功!");
					window.location.reload(true);
				}
				else
				{
					alert("删除失败!");
				}
			}		
		});	
	}
	
}
 function setorderNum(){
	var url = "${ctx}/contentManage/sentenceManage.do?method=setorder";
	var params = "orderstr=";
	var k = 0;
	var obj={
			category1:[],
			category2:[],
			category3:[],
			category4:[]
	}
	$('.att_orderNum').each(function(){
		var id = $(this).prop('id');
		var val = $(this).val();
		params+= id+"_"+val+";";
		var cate = $(this).data("category");
		if(val!=''){
			obj['category'+cate].push(val);
		}
	});
	for(item in obj){
		var value=obj[item];
		var flag=mm(value);		
		if(flag && item=="category1"){
			alert("资讯公告中有重复的序号，请删除已有序号再进行排序！");
			return;
		}
		if(flag && item=="category2"){
			alert("政策法规中有重复的序号，请删除已有序号再进行排序！");
			return;
		}
		if(flag && item=="category3"){
			alert("乡医培训中有重复的序号，请删除已有序号再进行排序！");
			return;
		}
		if(flag && item=="category4"){
			alert("分类4中有重复的序号，请删除已有序号再进行排序！");
			return;
		}
	}

	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'text',
	    success: function(result){
		   if(result == 'success'){
		   	//	alert('删除成功！');
		   		window.location.reload(true);
		   }else{
		   	//	alert('删除失败!');
		   }
	    }
	});
	
}
//判断数组里是否存在重复元素，有重复返回true；否则返回false
function mm(a){
   return /(\x0f[^\x0f]+)\x0f[\s\S]*\1/.test("\x0f"+a.join("\x0f\x0f") +"\x0f");
}

function edit(id, state){
	if(state == 2){
		alert("不能编辑已发布的文章！");
		return;
	}
	document.location.href = "${ctx}/contentManage/sentenceManage.do?method=update&id="+id;
}
</script>
</body>

