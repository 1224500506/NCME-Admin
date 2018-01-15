<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
		<title>培训后台</title>
		<%@include file="/commons/taglibs.jsp"%>
	</head>
<%@include file="/peixun_page/top.jsp"%>

<body>
<!-- 查询条件 -->
<div class="center">
	<form action="${ctx}/system/editManage.do?method=list&model.userType=2" method="post" id="fm">
	
	<div class="tiaojian" style="min-height:40px; margin-top:27px">		
		<p class="fl" style="margin:0 0 0 0">
			<span style="width:5em;text-align:right;">姓名：</span>
			<input type="text" name="model.realName" value="${item.realName}" />
		</p>
		<p class="fl" style="margin-left:25px">
			<span style="width:3em;text-align:right;">学科：</span>
			<input type="hidden" id="propIds2" name="propIds" value="${propIds}"/>
			<input type="hidden" id="propNames2" name="propNames" value="${propNames}"/>
			<div class="duouan" id="propNames02">${propNames}</div>
		</p>
		<p class="fl"  style="margin-left:25px">
			<span>账号：</span>
			<input type="text" name="model.accountName" value="${item.accountName}" />
		</p>
		<p class="fl" style="margin-left:25px">
			<span>状态：</span>
		</p>
		<div class="fl select"  style="margin-left:0px">
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
		<div class="fr cas_anniu">
			<a href="javascript:void(0);" class="add_btn" style="width:140px;margin-left:500px">添加编辑</a>
		</div>
		<div class="fl cas_anniu" style = "margin-left:20px;">
			<a href="javascript:searchProp();" class="fl" style="width:70px;">查询</a>
		</div>
	</div>
	</form>
</div>
<div class="clear"></div>
<div class="bjs" style="margin-top:13px"></div>

<!-- 添加编辑 -->
<div class="toumingdu make02" style="display:none;">
	<form action="" id="fm1" name="fm1" method="post">
	<input type="hidden" id="userId" name="model.userId" value="0"/>
	<input type="hidden" name="model.userType" value="2"/>
	<div class="layui-layer layui-layer-page layui-layer-rim layer-anim" id="layui-layer1" style="z-index: 19891015; width: 600px; height: 300px; top: 95.5px; left: 654px;margin-top:150px;margin-right:290px">
		<div class="layui-layer-title"><span  id="layer_Name" ></span></div>
			<div id="" class="layui-layer-content" style="height: 205px;">
				<div class="center">
					<div class="tiaojian" style="min-height:40px;">
						<p class="fl" style="margin-bottom:20px;">
							<span style="width:6em;text-align:right;"><i style="color:red;">*</i>编辑姓名：</span>
							<input type="text" id="realName" name="model.realName" value="">
						</p>
						<p class="fl" style="margin-bottom:20px;">
							<span style="width:6em;text-align:right;"><i style="color:red;">*</i>编辑账号：</span>
							<input type="text" id="accountName" name="model.accountName" value="">
						</p>
						<div class="clear"></div>
						<p class="fl" style="margin-bottom:20px;">
							<span style="width:6em;text-align:right;"><i style="color:red;">*</i>学科：</span>
							<input type="hidden" id="propIds" name="model.propIds" value=""/>
							<input type="hidden" id="propNames" name="propNames" value=""/>
							<a class="duouan" id="propNames01"></a>
						</p>
						<p class="fl" style="margin-bottom:20px;">
							<span style="width:6em;text-align:right;"><i style="color:red;">*</i>联系电话：</span>
							<input type="text" id="mobilPhone" name="model.mobilPhone" value="">
						</p>
					</div>
				</div>
			</div>
			<span class="layui-layer-setwin"></span>
			<div class="layui-layer-btn">
				<a href="javascript:saveEdit();" class="layui-layer-btn0">保存</a>
				<a class="layui-layer-btn1">返回</a>
			</div>
		</div>
	</form>
</div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
		<div class="clear"></div>
			<display:table requestURI="${ctx}/system/editManage.do?method=list"
							id="systemUser" partialList="true"
							excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}" name="page"
							class="mt10 table" keepStatus="true" decorator="com.hys.exam.displaytag.TableOverOutWrapper">
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
 					<c:if test="${systemUser.status==1}">
						<a href="javascript:setState(${systemUser.userId},2);" >停用</a> 
					</c:if>
					<c:if test="${systemUser.status==2}">
						<a href="javascript:setState(${systemUser.userId},1);" >正常</a> 
					</c:if> 
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
		
		//添加编辑
		$('.add_btn').click(function(){
			$('.make02').show();
			$('#layer_Name').text('添加编辑');
			$('#userId').val('0');
			$('#realName').val('');
			$('#accountName').val('');
		    $('#propNames01').text('');
		    $('#propIds').val('');
		    $('#propNames').val('');
		    $('#mobilPhone').val('');
		});
		$('.layui-layer-btn1').click(function(){
			$('.make02').hide();
		});
		
		//选择目录弹出框
		$('#propNames01').click(function(){
			initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
			$('.att_make01').show();
		});
		$('#propNames02').click(function(){
			initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds2'), $('#propNames02'));
			$('.att_make01').show();
		});
		$('.bjt_kt,.cas_anniu_4').click(function(){
			$('.att_make01').hide();
		});
		//编辑详情
		$(".edit_btn").click(function() {
			$('.make02').show();
			$('#layer_Name').text('编辑详情');
		});
		$('.layui-layer-btn1').click(function(){
			$('.make02').hide();
		});
		
		selectInit();
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
		      $('#propNames01').text(item.deptName);
		      $('#propNames').val(item.deptName);
		      $('#propIds').val(item.propIds);
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

	if ($('#realName').val().trim() == ""){
		alert ("请输入编辑姓名！");
		return;
	}
	if ($('#accountName').val().trim() == ""){
		alert ("请输入编辑账号！");
		return;
	}
	if ($('#propNames01').text().trim() == ""){
		alert ("请输入学科！");
		return;
	}
	if ($('#mobilPhone').val().trim() == ""){
		alert ("请输入联系电话号码！");
		return;
	} else {
		 var reg = /^((\d{3,4}-)|\d{3.4}-)?\d{7,8}$/;	
		 var regsj = /^1[3|4|5|8][0-9]\d{4,8}$/;
         var gets = $('#mobilPhone').val();
         if(!reg.test(gets)&&!regsj.test(gets)){	
         	alert("请输入正确手机号或按照如下格式输入联系电话号码： '123-1234567','123-12345678','1234-1234567','1234-12345678','1234567','12345678'");
         	return;
         }
     }

	var url = "${ctx}/system/editManage.do?method=insert";
	if ($('#userId').val() != 0)
		url = "${ctx}/system/editManage.do?method=update";
	
	var params = "model.userId="+$('#userId').val();
 	params += "&model.realName="+$('#realName').val();
 	params += "&model.userType=2";
	params += "&model.accountName="+$('#accountName').val();
	params += "&model.propIds="+$('#propIds').val();
	params += "&model.mobilPhone="+$('#mobilPhone').val();
	
	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'json',
	    success: function(result){
			if (result !=""){
				if(result.result == "success")
				{
					alert(result.errorMsg);
					window.location.reload(true);
				}
				else
				{
					alert(result.errorMsg);
				}
				
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

function searchProp()
{
	$('#propNames2').val($('#propNames02').text());
	$(fm).submit();
}
</script>
</body>
</html>