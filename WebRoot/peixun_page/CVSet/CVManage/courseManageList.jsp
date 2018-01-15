<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/commons/taglibs.jsp"%>
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
		<title>培训后台</title>
		<%@include file="/peixun_page/top.jsp"%>
	</head>

<body>
<div class="center">
	<form id="sfrm" name="cvForm" method="POST" action="${ctx}/CVSet/CVManage.do?method=list">
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl">
			<span>学科：</span>
		</p>
			<input type="hidden" id="propIds" name="propIds" value="${propIds}"/>
			<input type="hidden" id="propNames" name="propNames" value="${propNames}"/>
			<div class="duouan" id="propNames01"  style="margin-right:20px;">${propNames}</div>
		

		<p class="fl" style="margin-right:20px;">
			<span>课程名称：</span>
			<input type="text" name="name" value="${name}" />
		</p>
		<p class="fl" style="margin-right:20px;">
			<span>项目名称：</span>
			<input type="text" name="item" value="${item}" />
		</p>
		<p class="fl" style="margin-right:20px;">
			<span>创建人：</span>
			<input type="text" name="sname" value="${sname}"/>
		</p>
		<div class="clear" style="margin-bottom:10px;"></div>
		
			<p class="fl">
				<span style="text-align:right;">授课形式：</span>
			</p>
			<div class="fl select">
				<div class="tik_position">
					<b class="ml5">点播</b>
					<p class="position01"><i class="bjt10"></i></p>
				</div>
				<ul class="fl list" id="_cvType" style="display: none;">
					<select name = "cv_type" id="cv_type" style="display:none">						
						<option value="-1">全部</option>
						<option value="0" <c:if test="${cvType2 == 0}">selected</c:if>>点播</option>
						<option value="1" <c:if test="${cvType2 == 1}">selected</c:if>>面授</option>
						<option value="2" <c:if test="${cvType2 == 2}">selected</c:if>>直播</option>						
					</select>
					
					<li>全部</li>
					<li>点播</li>
					<li>面授</li>
					<li>直播</li>					
				</ul>
			</div>
			
			
			<div id="kcbq" style="display:block;">
			<p class="fl" style="margin-bottom:20px;">
				<span style="width:9em;text-align:right;">课程标签：</span>
			</p>
			<p id="db_label" class="fl" style="margin-bottom:20px;margin-top: 7px;">
				<input type="checkbox" name="brand_c" value="1"  <c:if test="${fn:contains(brand, 1)}">checked="checked"</c:if>/>病例
				<input type="checkbox" name="brand_c" value="3" style="margin-left: 10px;" <c:if test="${fn:contains(brand, 3)}">checked="checked"</c:if>/>VR
				<input type="checkbox" name="brand_c" value="4" style="margin-left: 10px;" <c:if test="${fn:contains(brand, 4)}">checked="checked"</c:if>/>名师课程
				<input type="checkbox" name="brand_c" value="5" style="margin-left: 10px;" <c:if test="${fn:contains(brand, 5)}">checked="checked"</c:if>/>三维动画
				<input type="checkbox" name="brand_c" value="6" style="margin-left: 10px;" <c:if test="${fn:contains(brand, 6)}">checked="checked"</c:if>/>其他
				<input type="hidden" id="brand" name="brand"/>
			</p>
				<p id="ms_label" class="fl" style="margin-bottom:20px;margin-top: 7px;display: none">
					<input type="checkbox" name="brand_c" value="7" style="margin-left: 10px;" <c:if test="${fn:contains(brand, 7)}">checked="checked"</c:if>/>理论
					<input type="checkbox" name="brand_c" value="8" style="margin-left: 10px;" <c:if test="${fn:contains(brand, 8)}">checked="checked"</c:if>/>操作演示
					<input type="checkbox" name="brand_c" value="9" style="margin-left: 10px;" <c:if test="${fn:contains(brand, 9)}">checked="checked"</c:if>/>讨论
				</p>
			</div>
		
		<div class="clear" style="margin-bottom:10px;"></div>
		
		<p class="fl cas_anniu" style="margin:5px 0;">
			<a href="javascript:search()" class="fl" style="width:70px;margin-left:10px;">查询</a>
		</p>
		<p class="fl cas_anniu" style="margin:5px 0;margin-left:75%;">
			<a href="${ctx}/CVSet/CVManage.do?mode=add" class="fl btn_add" style="width:140px;">创建课程 </a>
		</p>
		
	</div>
	</form>
</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
		<form id="listfrm" name = "cvForm" method="POST">
			<display:table name="list" requestURI="${ctx}/CVSet/CVManage.do?method=list" id="p"  excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}"  class="mt10 table">
					<display:column title="序号" style="width:3%">${p_rowNum}</display:column>
					<display:column title="学科" style="width:5%">
						<c:forEach items="${p.courseList}" varStatus="stauts" var="prop">
						${prop.name}&nbsp;
						</c:forEach>
					</display:column>
					<display:column title="课程名称" style="width:15%">${p.name}</display:column>
					<display:column title="课程编号" style="width:10%">${p.serial_number}</display:column>
					<display:column title="引用项目" style="width:6%">
						<c:forEach items = "${p.usingItem}" varStatus="status" var="usingItems">
							${usingItems.name}&nbsp;
						</c:forEach>
					</display:column>
					<display:column title="授课形式" style="width:5%">
						${p.getCvTypeName()}
					</display:column>
					<display:column title="课程标签" style="width:5%">						
							<c:forEach var="item" items="${p.brand}" varStatus="status"> 
								<c:if test="${item == 1}">病例</c:if>						
								<c:if test="${item == 3}">VR</c:if>
								<c:if test="${item == 4}">名师课程</c:if>
								<c:if test="${item == 5}">三维动画</c:if>
								<c:if test="${item == 6}">其他</c:if>							
								<c:if test="${item == 7}">理论</c:if>
								<c:if test="${item == 8}">操作演示</c:if>
								<c:if test="${item == 9}">讨论</c:if>
							</c:forEach>
					</display:column>
					<display:column title="授课教师" style="width:10%">
						<c:forEach items="${p.teacherList}" varStatus="status" var="teacher">
							${teacher.name}&nbsp;
						</c:forEach>
					</display:column>
					<display:column title="示教教师" style="width:8%">
						<c:forEach items="${p.otherTeacherList}" varStatus="status" var="other">
							${other.name}&nbsp;
						</c:forEach>
					</display:column>
					<display:column title="创建时间" style="width:10%"><fmt:formatDate value="${p.create_date}" pattern="yyyy-MM-dd HH:mm:ss"/></display:column>
					<display:column title="操作" style="width:10%">
							<a href="${ctx}/CVSet/CVManage.do?mode=view&id=${p.id}" class="">查看</a>
							<a href="${ctx}/CVSet/CVManage.do?mode=edit&id=${p.id}" class="">编辑</a>
							<a href="javascript:deleteCourse(${p.id});" class="">删除</a>
					</display:column>
			</display:table>
		</form>
		<div class="clear"></div> 
		<!-- 分页 -->
		
			
	</div>

</div>


<script type="text/javascript">
$(function(){
		
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "lc_left0" + menuindex;
		$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
		
//选择目录弹出框
		$('#propNames01').click(function(){
			initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
			$('.att_make01').show();
		});
		$('.cas_anniu_4,.bjt_kt').click(function(){
			$('.att_make01').hide();
		});
		
		select_init();
		//点击查询后保存原有查询条件
		var _ct = $("#cv_type").val();
		var _ctval = $("#cv_type option:selected").text();
		if(_ct == 2 || _ctval == "直播"){
			$('#kcbq').hide();
		}else if(_ct == 1 || _ctval == "面授"){
            $('#kcbq').show();
			$('#db_label').hide();
			$('#ms_label').show();
		}else{
            $('#kcbq').show();
			$('#db_label').show();
			$('#ms_label').hide();
		}
		//设置当选择直播课程时情况
 		$('#_cvType li').click(function(){
 			var _ct = $("#cv_type").val();
 			var _ctval = $("#cv_type option:selected").text();
            $('input:checkbox[name=brand_c]:checked').removeAttr("checked");
 			if(_ct == 2 || _ctval == "直播"){
 				$('#kcbq').hide();
 			}else if(_ct == 1 || _ctval == "面授"){
                $('#kcbq').show();
 				$('#db_label').hide();
 				$('#ms_label').show();
 			}else{
                $('#kcbq').show();
                $('#db_label').show();
                $('#ms_label').hide();
 			}
         });
		
});


/*控制下拉框方法*/
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
			
			var cvTypeObj = $(this).parent().find('#cv_type') ;
			if (cvTypeObj.length ==1) {
			   var cvTypeValue = cvTypeObj.val() ;
			}
			
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

    var spCodesTemp = "";
    $('input:checkbox[name=brand_c]:checked').each(function(i){
     if(0==i){
      spCodesTemp = $(this).val();
     }else{
      spCodesTemp += (","+$(this).val());
     }
    });
    $("#brand").val(spCodesTemp); 


	var name = $('#propNames01').text();
	$('#propNames').val($('#propNames01').text());
     
	$('#sfrm').submit();
}

function deleteCourse(_id){
	if(!confirm("您确定要删除?")) return;	
	var url='${ctx}/CVSet/CVManage.do?mode=delete&id='+_id;
	if(_id != ''){
	//var params='&id='+del_id;
	}
	
	$.ajax({
		type :'post',
		 url : url,
	  //params : params,
	  	dataType:'text',
	  success:function(b){
	  	if(b == 'success'){
	  		alert('删除成功！');
	  		//document.location.href = document.location.href.split("#")[0];
	  		search() ;
	  	}else
	  		alert('数据被引用无法删除！');
	  },
	  error:function(){
	  	alert('数据被引用无法删除！');
	  }
		
	});
}

</script>
</body>
</html>