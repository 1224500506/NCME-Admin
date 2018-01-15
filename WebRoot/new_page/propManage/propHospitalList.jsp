<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/new_page/top.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理平台</title>
</head>

<body class="bjs">
<div>
	<!-- 题库内容 -->
	<div class="center">
		<div class="tk_center01" style="min-height:40px;">
			<ul class="cas1_xuanxing">
				<li  id="yiyuan">医院</li>
				<li class="action" >医院级别</li>
			</ul>
			<div class="tk_zuo">
			<form id="sfrm" action="${ctx}/propManage/propHospitalList.do?type=${type}" method="POST">
				<div class="tik_xuanze">
					<div class="mt10 fl tk_tixing" style="margin-left:-180px;">
						<em class="fl">医院级别名称：
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
				<div class="fr cas_anniu">
					<a href="javascript:void(0);" class="fl att_tianjia" style="width:95px;margin-right:15px;">添加医院级别</a>
					<a href="javascript:void(0);" class="fl att_tianjia03" style="width:95px;">批量删除</a>
				</div>
			</div>
			<div class="clear"></div>
			<display:table name="propList" excludedParams="type" requestURI="${ctx}/propManage/propHospitalList.do?type=${type}" id="p" class="mt10 cas_table" size="${totalCount}" pagesize="20"  sort="list" defaultsort="1">
				<display:column title="<input type='checkbox' class='chkall'/>" style="width:5%;"><input type='checkbox' class='chk' value="${p.id}"/></display:column>
				<display:column title="序号" style="width:10%;" property="id"></display:column>
				<display:column title="医院级别" sortable="true" style="width:25%;" property="name"></display:column>
				<display:column title="医院数" style="width:10%;" property="ext_type"></display:column>
<%-- 				<display:column title="类型" style="width:20%;" property="typeName"></display:column>
 --%>				<display:column title="显示序号" sortable="true" style="width:15%;" property="code" sortProperty="codenum"></display:column>
 				<display:column title="操作" style="width:20%;">
					<a href="javascript:editPropShow('${p.id}','${p.name}','${p.code}', '${p.type}');" >修改</a>
					<a href="javascript:doDelProp(${p.id},${p.prop_val_id});" >删除</a>
					<%-- <a href="${ctx}/propManage/hospitalList.do?type=${p.id}" >医院</a> --%>
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
			<p class="fl" style="width:90px;"><span class="fr">医院级别：</span><em class="fr">*</em></p>
			<input type="text" class="fl att_bianji01" id="rsName" />
			<input type="hidden" id="rsType"  value="${type}" />
			<input type="hidden" id="mode"  />
		</div>
		<div class="clear"></div>
		<div class="fl mt30 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">显示序号：</span><em class="fr">*</em></p>
			<input type="text" class="fl att_bianji01" id="rsCode" onkeyup="this.value=this.value.replace(/[^\d]/g, '');"/>
			<input type="hidden" id="rsProp_val1" value="0" />
			<input type="hidden" id="rsId" />
		</div>
		<div class="clear"></div>
		<div style="height:20px;"></div>
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
	
	
		$('#yiyuan').click(function(){
			document.location.href = "${ctx}/propManage/hospitalList.do";
		});
		
		$('input[name="sname"]').blur(function(){
			var reg = new RegExp("^[\u4e00-\u9fa5_a-zA-Z0-9]+$");//匹配汉子、数字、字母及下划线
			var sname = $('input[name="sname"]').val();
			if(sname != ""){
				if(!reg.test(sname)){
					alert("医院级别名称仅支持汉字、字母和数字！");
					$('input[name="sname"]').val("");
					return;
				}
			}
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
			$('#rsId').val('');
			$('#mode').val('add');
		});
	//保存属性

			$('.att_baocun').click(function(){
			if (!$('#rsName').val()){
				alert("请输入医院级别！");
				return;
			}
			if(!$('#rsCode').val()){
				alert("请输入显示序号！");
				return;
			}
			if ($('#rsName').val().indexOf(" ")>-1){
				alert("医院级别不能为空格！");
				return;
			}
 			if ($('#rsCode').val().indexOf(" ")>-1){
				alert("显示序号不能为空格！");
				return;
			}
			if ($('#rsName').val().length>50){
				alert("医院级别名称长度不能大于50！");
				return;
			}
 			if ($('#rsCode').val().length>20){
				alert("显示序号长度不能大于20！");
				return;
			}
			
			//添加
			if ($('#mode').val() == 'add'){
				var url = '${ctx}/propManage/addProp.do';
				var params = 'propName=' + $('#rsName').val();
				params += '&code='+$('#rsCode').val();
				params += '&type='+$('#rsType').val();
				params += '&prop_val1='+$('#rsProp_val1').val();
				
				$.ajax({
					url: url,
					type: 'POST',
					data: params,
					dataType: 'text',
					success: function(result){
						if(result == 'success'){
						   alert('医院级别添加成功！');
						   $('.att_xueke').hide();
						   document.location.href = document.location.href.split("#")[0];
						}else{
							alert('医院级别或显示序号重复且不能保存!');
							return false;
						};
					}
				});
			}
			//修改
			else if ($('#mode').val() == 'edit'){
				var url = '${ctx}/propManage/editProp.do';
				var params = 'propName=' + $('#rsName').val();
				params += '&code='+$('#rsCode').val();
				params += '&type='+$('#rsType').val();
				params += '&id='+$('#rsId').val();
				
				$.ajax({
					url: url,
					type: 'POST',
					data: params,
					dataType: 'text',
					success: function(result){
						if(result == 'success'){
						   alert('医院级别修改成功！');
						   $('.att_xueke').hide();
						   document.location.href = document.location.href.split("#")[0];
						}else{
							alert('医院级别或显示序号重复且不能保存!');
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
				alert ("没有选择医院级别。");
				return;
			}
			
			if(!confirm("您确定要删除吗？"))return;
			var url = '${ctx}/propManage/delProp.do?mode=batchdel';

			var array = new Array();var dels=0;
			$('.chk:checked').each(function(key, val){
				array.push($(val).val());dels++;
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
						alert(dels+'个医院级别中'+result+'个医院级别删除成功，其他'+ eval(dels - result)+'个医院级别因为在别的地方使用无法删除！');
						document.location.href = document.location.href.split("#")[0];
					    }
					    else{
					   	 alert(result+'个医院级别删除成功!');
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
function editPropShow(_id,_name,_code, _type){
	$('#rsName').val(_name);
	$('#rsCode').val(_code);
	$('#rsId').val(_id);
	$('#rsType').val(_type);
	$('#mode').val('edit');
	$('.att_xueke').show();
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
		      alert('医院级别删除成功！');
		      document.location.href = document.location.href.split("#")[0];
		   }else{
		   		alert('该医院等级下已创建医院，不可删除!');
		   	}
	  	}
	    });
}

</script>
</body>
</html> 