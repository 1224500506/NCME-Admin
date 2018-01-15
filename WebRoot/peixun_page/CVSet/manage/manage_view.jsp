<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<%@include file="/commons/taglibs.jsp"%>
</head>

<%@include file="/peixun_page/top.jsp"%>

<body>
<div class="clear"></div>
<!-- 查询条件 -->
<div class="center">
	<div class="tiaojian" style="min-height:40px;">
	<form id="main_view" action="${ctx}/CVSetManage.do" method="post">
	
		<p class="fl">
			<span>学科：</span>			
			<input type="hidden" id="propIds" name="propIds" value="${propIds}"/>
			<input type="hidden" id="propNames" name="propNames" value="${propNames}"/>
			<div class="duouan" style="margin-right:20px" id="propNames01">${propNames}</div>
		</p>
		<p class="fl" style="margin-right:20px;">
			<span>项目名称：</span>
			<input type="text" name="CVSetName" value="${sname}"/>
		</p>
		<p class="fl" style="margin-right:20px;">
			<span>项目负责人：</span>
			<input type="text" name="creater" value="${creater}"/>
		</p>
		<p class="fl" style="margin-right:20px;">
			<span >授课形式：</span>
		</p>
		<div class="fl select">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display: none;">
				<select name="forma" id="lessonType" style="display:none;">
					<option value="0">全部</option>
					<option value="1" <c:if test="${forma == 1}">selected</c:if>>远程</option>
					<option value="3" <c:if test="${forma == 3}">selected</c:if>>面授</option>
				</select>
				<li>全部</li>
				<li>远程</li>
				<li>面授</li>
			</ul>
		</div>
		<p class="fl" style="margin-right:20px;">
			<span>状态：</span>
		</p>
		<div class="fl select">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display: none;">
				<select name="CVSetStatus" id="CVSetStatus" style="display:none;">
				<option value="0">全部</option>
				<option value="1" <c:if test="${status == 1}">selected</c:if>>未提交</option>
				<option value="2" <c:if test="${status == 2}">selected</c:if>>审核中</option>
				<option value="3" <c:if test="${status == 3}">selected</c:if>>审核合格</option>
				<option value="4" <c:if test="${status == 4}">selected</c:if>>审核不合格</option>
				<option value="5" <c:if test="${status == 5}">selected</c:if>>已发布</option>
				<option value="6" <c:if test="${status == 6}">selected</c:if>>已下线</option>
			</select>
				<li>全部</li>
				<li>未提交</li>
				<li>审核中</li>
				<li>审核合格</li>
				<li>审核不合格</li>
				<li>已发布</li>
				<li>已下线</li>
			</ul>
		</div>
		<div class="clear"></div>
		<div class="fr cas_anniu" style="margin:20px 0;">
			<a href="${ctx}/peixun_page/CVSet/manage/CVS_add.jsp?projectfrom=2" class="fl" style="width:140px;margin-left:10px;">创建项目</a>
		</div>
		<div class="fl cas_anniu" style="margin:20px 0;">
			<a href="javascript:search();" class="fl" style="width:70px;margin-left:10px;">查询</a>
		</div>
	</form>
	</div>
</div>

<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">	
		<display:table name="CVSet" requestURI="${ctx}/CVSetManage.do" id="CVS" class="mt10 table" excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}">			
					<display:column title="序号" style="width:3%;">${CVS.id}</display:column>
					<display:column title="学科"  style="width:5%;">
						<%-- <%-- ${CVS.userImageList.departmentPropList.name} --%>
						<c:forEach	items="${CVS.userImageList}" varStatus="status" var="image">
							<c:forEach	items="${image.departmentPropList}" varStatus="status" var="prop">
								${prop.name}&nbsp;
							</c:forEach>
						</c:forEach> 
					</display:column>
					<display:column title="项目名称" style="width:15%;">${CVS.name}</display:column>
					<display:column title="项目编号" style="width:10%;">${CVS.code}</display:column>
					<display:column title="项目学分" style="width:4%;">
						<c:if test="${CVS.level == 1}">国家I类</c:if>
						<c:if test="${CVS.level == 2}">省级I类</c:if>
						<c:if test="${CVS.level == 3}">市级I类</c:if>
						<c:if test="${CVS.level == 4}">省级II类</c:if>
						<c:if test="${CVS.level == 5}">市级II类</c:if>${CVS.score}
					</display:column>
					<display:column title="授课形式" style="width:4%;">
						<c:if test="${CVS.forma == 1}">远程</c:if>
						<c:if test="${CVS.forma == 3}">面授</c:if>
					</display:column>
					<display:column title="项目负责人" style="width:6%;">
						<c:forEach	items="${CVS.managerList}" var="manager">
							${manager.name}&nbsp;
						</c:forEach>
					</display:column>
					<display:column title="审核人" style="width:6%;">${CVS.deli_man}</display:column>
					<%--<display:column title="学分" style="width:5%;">${CVS.score}</display:column>--%>
					<display:column title="项目有效时间" style="width:10%;">
						<fmt:formatDate value="${CVS.start_date}" pattern="yyyy-MM-dd"/>至
						<fmt:formatDate value="${CVS.end_date}" pattern="yyyy-MM-dd"/>		
					</display:column>
					
					<display:column title="创建时间" style="width:10%;">
						<fmt:formatDate value="${CVS.create_date}" pattern="yyyy-MM-dd hh:mm:ss"/>
					</display:column>
					<display:column title="项目状态" style="width:4%;">
						<c:if test="${CVS.status==1}">未提交</c:if>
						<c:if test="${CVS.status==2}">审核中</c:if>
						<c:if test="${CVS.status==3}">审核合格</c:if>
						<c:if test="${CVS.status==4}">审核不合格</c:if>
						<c:if test="${CVS.status==5}">已发布</c:if>
						<c:if test="${CVS.status==6}">已下线</c:if>
					</display:column>	
                    <%--<display:column title="项目类型" style="width:5%;">${CVS.getCv_set_type_name()}</display:column>--%>
					<display:column title="标签" style="width:5%;">${CVS.sign}</display:column>				
					<display:column title="操作" style="width:10%;">
						<c:if test="${(CVS.status==1 || CVS.status==4 || CVS.status==6) && CVS.maker == user.accountName}">
							<a href="javascript:update('${CVS.id}');" class="edit_btn">编辑</a> 
							<a href="javascript:Del(${CVS.id});">删除</a>		
						</c:if>	
						<%-- <c:if test="${CVS.status==2 || CVS.status==3 || CVS.status==5 || CVS.maker != user.accountName}"> --%>
							<a href="javascript:update('${CVS.id}');" class="edit_btn">查看</a>
						<%-- </c:if>	 --%>										 
					</display:column>
		</display:table>		
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
	//下拉框
		select_init();
		/* $('.fl select').click(function(){
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
			alert(('#CVSetStatus').val());		
		});		
		$(document).click(function(){
			$('.list').hide('fast');
		}); */
	//选择目录弹出框
		$('#propNames01').click(function(){
			initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
			$('.att_make01').show();
		});
		$('.cas_anniu_4,.bjt_kt').click(function(){
			$('.att_make01').hide();
		});
		$(document).click(function(){
			$('.list').hide('fast');
		});	 
});
function update(id_){	
	var url = "${ctx}/CVSetManage.do?mode=get_CVS&id="+id_;
	window.location.href = url;
}

function Del(id_){
	if (!confirm("确定删除?"))
	{
		return;
	}
	$.ajax({
		type:'post',
		url: '${ctx}/CVSetManage.do',
		data:'mode=del&id='+id_,
		dataType:'text',
		success:function(result){
			if(result == 'success'){
				alert('删除成功！');
				document.location.href = document.location.href.split("#")[0];
			}else if(result == 'bangDing'){
				alert('此项目已被卡类型绑定，不能删除！');
			}
			else{
				alert('此项目正在被使用，不能删除！');
			}
		},
		error:function(){
			alert('删除失败!');
		}
	});	
}
function select_init() {
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
	
function search(){
	$('#propNames').val($('#propNames01').text());
	$(main_view).submit();
}

</script>

</body>
</html>