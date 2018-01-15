<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/taglibs.jsp"%>
<title>资源管理平台</title>
</head>

<%@include file="/new_page/top.jsp"%>
<body class="bjs">
<div>
	<!-- 题库内容 -->
	<div class="center">
		<div class="tk_center01" style="min-height:40px;">
			<div class="tk_zuo">
			<c:if test="${type==20}">
				<form id="sfrm" action="${ctx}/propManage/regionList.do?type=${type}" method="POST">
			</c:if>
			<c:if test="${type==21 || type==22}">
				<form id="sfrm" action="${ctx}/propManage/regionList.do?id=${prop_val1}&type=${type}" method="POST">
			</c:if>
				<input name="type" value="${type }" type="hidden">
				<input name="id" value="${prop_val1}" type="hidden">
				<div class="tik_xuanze">
					<div class="mt10 fl tk_tixing">
						<em class="fl">
						<c:if test="${type==20}">省/直辖市：</c:if>
						<c:if test="${type==21}">市 ：</c:if>
						<c:if test="${type==22}">区县 ：</c:if>
						</em>
						<input type="text"  class="fl tik_select" name="sname" value="${sname}"/>
					</div>
					<div class="mt10 fl mll8 tk_tixing">
						<a href="javascript:void(0);" class="fl tk_chaxun">查询</a>
					</div>
				</div>
				</form>
			</div>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
	<div class="tkbjs"></div>
	<!--  -->
	<div class="center none" style="">
		<div class="tk_center01">
			<div class="mt10 kit1_tiaojia">
				<div class="mt10 fl tk_tixing">
					<c:if test="${parents.get(0).type != null}"><em>${parents.get(0).name}</em></c:if>
					<c:if test="${parents.get(1).type != null}"><em>>${parents.get(1).name}</em></c:if>
				</div>
				<div class="fr cas_anniu">
					<a href="javascript:void(0);" class="fl att_tianjia" style="width:95px;margin-right:15px;">
						<c:if test="${type==20}">添加省</c:if>
						<c:if test="${type==21}">添加市</c:if>
						<c:if test="${type==22}">添加区县</c:if>
					</a>
					<a href="javascript:void(0);" class="fl att_tianjia03" style="width:95px;margin-right:15px;">批量删除</a>
					<c:if test="${type!=20}"><a href="${ctx}/propManage/regionList.do?id=${parentid}&type=${type-1}" class="fl" style="width:95px;">返回</a></c:if>
				</div>
			</div>
			<div class="clear"></div>
			<display:table name="propList" excludedParams="type" requestURI="${ctx}/propManage/regionList.do?type=${type}" id="p" class="mt10 cas_table" size="${totalCount}" pagesize="20"  sort="list" defaultsort="1">
				<display:column title="<input type='checkbox' class='chkall'/>" style="width:5%;"><input type='checkbox' class='chk' value="${p.id}"/></display:column>
				<display:column title="序号" style="width:15%;" property="id"></display:column>
				<display:column title="名称" sortable="true" style="width:30%;" property="name"></display:column>
 				<display:column title="地区级别" sortable="true" style="width:15%;" property="c_type_name"></display:column>
				<display:column title="显示序号" sortable="true" style="width:20%;" property="code" sortProperty="codenum"></display:column>
 				<display:column title="操作" style="width:15%;">
					<a href="javascript:editPropShow('${p.id}','${p.name}','${p.code}', '${p.c_type}', '${p.type}');" >修改</a>
					<a href="javascript:doDelProp(${p.id},${p.prop_val_id});" >删除</a>
					<c:choose>
						<c:when test="${type==20}"><a href="${ctx}/propManage/regionList.do?id=${p.prop_val_id}&type=21" >市</a></c:when>
						<c:when test="${type==21}"><a href="${ctx}/propManage/regionList.do?id=${p.prop_val_id}&type=22" >县</a></c:when>
					</c:choose>
 				</display:column>
			</display:table>

		</div>
	</div>
		<!-- 试题内容（结束） -->

</div>
<!-- 主题弹出框 -->
<div class="toumingdu att_xueke" style="display:none;">
	<div class="TJ_cueke">
		<div class="fl shitin_xinzeng01" style="margin-top:60px;margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">地域名称：</span><em class="fr">*</em></p>
			<input type="text" class="fl att_bianji01" id="rsName" />
			<input type="hidden" id="rsType"  value="${type}" />
			<input type="hidden" id="mode"  />
		</div>
		<div class="clear"></div>
<!-- 		<div class="fl mt30 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">地区级别：</span><em class="fr">*</em></p>
			<select class="fl att_bianji01" id="rsC_type">
 -->
 				<c:if test="${type==20}"><input type="hidden" id="rsC_type" value="3"/></c:if>
				<c:if test="${type==21}"><input type="hidden" id="rsC_type" value="4"/></c:if>
				<c:if test="${type==22}"><input type="hidden" id="rsC_type" value="5"/></c:if>
<!-- 				<option value="5">区县级</option>
			</select>
		</div>
		<div class="clear"></div>
 -->		<div class="fl mt30 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">显示序号：</span><em class="fr">*</em></p>
			<input type="text" class="fl att_bianji01" id="rsCode" onkeyup="this.value=this.value.replace(/[^\d]/g, '');" />
			<input type="hidden" id="rsProp_val1" value="${prop_val1}" />
			<input type="hidden" id="rsId" />
		</div>
		<div class="clear"></div>
		<div class="ca1_anniu" style="width:200px;">
			<a href="javascript:void(0);" class="fl anniu att_baocun">保存</a>
			<a href="javascript:void(0);" class="fl ml30 anniu att_fanhui">返回</a>
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
		var submenu = "mar_left0" + menuindex;
		$('.tk_mainmenu>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
	

	//下拉框
		$('.tik_select').click(function(){
			$(".tk_list").css("display","none");
			$(this).find('ul').show();
			return false;
		});
		
		$('.tk_list li').click(function(){
			var str=$(this).text();
			$(this).parent().parent().find('div').find('b').text(str);
			$(this).parent().find('option').prop('selected', '');
			$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
			$('.tk_list').slideUp(50);
		});
	//查询条件选择
		$('.tk_list option:selected').each(function(){
			var str=$(this).text();
			$(this).parent().parent().parent().find('b').text(str);
		});

		$(document).click(function(){
			$('.tk_list').hide('fast');
		});

	//查询学科
		$('.tk_chaxun').click(function(){
			$('#sfrm').submit();
			
		});	
	//添加学科
		$('.att_tianjia').click(function(){
			$('.att_xueke').show();
			$('#rsName').val('');
			$('#rsCode').val('');
//			$('#rsC_type').val('');
			$('#rsId').val('');
			$('#mode').val('add');
		});
	//保存属性
		$('.att_baocun').click(function(){
			if (!$('#rsName').val()){
				alert ("请输入名称！");
				return;
			} 
			if (!$('#rsCode').val()){
				alert("请输入显示序号！");
				return;
			}
			if ($('#rsName').val().indexOf(" ")>-1){
				alert("名称不能为空格！");
				return;
			}
 			if ($('#rsCode').val().indexOf(" ")>-1){
				alert("序号不能为空格！");
				return;
			}
			if ($('#rsName').val().length>50){
				alert("名称长度不能大于50！");
				return;
			}
 			if ($('#rsCode').val().length>20){
				alert("序号长度不能大于20！");
				return;
			}
			
			//添加
			if ($('#mode').val() == 'add'){
				var url = '${ctx}/propManage/addProp.do';
				var params = 'propName=' + $('#rsName').val();
				params += '&code='+($('#rsCode').val()/1);
				params += '&type='+$('#rsType').val();
				params += '&prop_val1='+$('#rsProp_val1').val();
				params += '&c_type='+$('#rsC_type').val();
				
				$.ajax({
					url: url,
					type: 'POST',
					data: params,
					dataType: 'text',
					success: function(result){
						if(result == 'success'){
						   alert('添加成功！');
						   $('.att_xueke').hide();
						   document.location.href = document.location.href.split("#")[0];
						}else{
							alert('显示序号/地域名称已存在，请重新设置显示序号/名称!');
							return false;
						};
					}
				});
			}
			//修改
			else if ($('#mode').val() == 'edit'){
				var url = '${ctx}/propManage/editProp.do';
				var params = 'propName=' + $('#rsName').val();
				params += '&code='+($('#rsCode').val()/1);
				params += '&id='+$('#rsId').val();
				params += '&type='+$('#rsType').val();
				params += '&c_type='+$('#rsC_type').val();
				
				$.ajax({
					url: url,
					type: 'POST',
					data: params,
					dataType: 'text',
					success: function(result){
						if(result == 'success'){
						   alert('修改成功！');
						   $('.att_xueke').hide();
						   document.location.href = document.location.href.split("#")[0];
						}else{
							alert('显示序号/地域名称已存在，请重新设置显示序号/名称!');
							return false;
						};
					}
				});
			}
			
			//$('.att_xueke').hide();
		});
	//返回
		$('.att_fanhui').click(function(){
			$('.att_xueke').hide();
		});
	//check all
		$('.chkall').click(function(){
			if ($(this).attr('checked') == 'checked')
				$('.chk').attr('checked' , 'checked');
			else
				$('.chk').removeAttr('checked');
		});
		
		$('.att_tianjia03').click(function(){
			if ($('.chk:checked').length<=0){
				alert ("没有选择项目。");
				return;
			}
			
			if(!confirm("您确定要删除吗？"))return;
			var url = '${ctx}/propManage/delProp.do?mode=batchdel';

			var array = new Array();var dels=0;
			$('.chk:checked').each(function(key, val){
				array.push($(val).val());
				dels++;
			});
			var params = 'ids='+array.toString();
			
			$.ajax({
				url: url,
				type: 'POST',
				data: params,
				dataType: 'text',
				success: function(result){
					if(result != '0'){
						   if(eval(dels-result)!=0){
							alert(dels+'个地域中'+result+'个地域删除成功，其他'+ eval(dels - result)+'个地域因为在别的地方使用无法删除！');
							document.location.href = document.location.href.split("#")[0];
							}
						    else{
						    alert(result+'个地域删除成功!');
							document.location.href = document.location.href.split("#")[0];
						}
				    }else{
						alert('操作失败！');
					};
				}
			});
			
		});
				
});

//修改
function editPropShow(_id,_name,_code,_ctype, _type){
	$('#rsName').val(_name);
	$('#rsCode').val(_code);
//	$('#rsC_type').val(_ctype);
	$('#rsId').val(_id);
	$('#rsType').val(_type);
	$('#mode').val('edit');
	$('.att_xueke').show();
	
		//查询条件选择
		$('.tk_list option:selected').off(each);
		$('.tk_list option:selected').each(function(){
			var str=$(this).text();
			$(this).parent().parent().parent().find('b').text(str);
		});
	
};

//删除
var force_del_url;
var force_del_params;
function doDelProp(id,prop_val_id){

	if(!confirm("您确定要删除吗？"))return;
	var url = '${ctx}/propManage/delProp.do';
	if(id!='' && prop_val_id!=''){
		var params = 'id=' +id;
		params +='&prop_val_id='+prop_val_id;
	}
    force_del_url = url;
	force_del_params = params;
	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'text',
	    success: function(result){
		   if(result == 'success'){
		      alert('删除成功！');
		      document.location.href = document.location.href.split("#")[0];
		   }else{
			   alert("该属性已被使用，无法删除!");
			   /* 
		   		if(confirm('该属性已被使用，若删除，则使用该属性的地方均被删除，确定删除!')){
		   			
					var url = force_del_url;
					var params = force_del_params + "&type=-100";
				
					$.ajax({
					    url:url,
					    type: 'POST',
					    data: params,
					    dataType: 'text',
					    success: function(result){
						   if(result == 'success'){
						      alert('删除成功！');
						      document.location.href = document.location.href.split("#")[0];
						   }else{
						      alert('删除失败！');
						   };
					    }
					 });
		   		} */
		   };
	    }
	    });
}


</script>
</body>
</html> 