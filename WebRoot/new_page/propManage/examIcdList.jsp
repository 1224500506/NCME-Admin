<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/new_page/top.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理平台</title>
</head>

<body class="bjs" onkeydown="entersearch();">
<div>
	<!-- 题库内容 -->
	<div class="center">
		<div class="tk_center01" style="min-height:40px;">
			<div class="tk_zuo">
			<form id="sfrm" action="${ctx }/propManage/icdList.do?type=${type}" method="POST">
				<input type="hidden" id="type" name="type" value="${type }" />
				<div class="tik_xuanze">
					<div class="mt10 fl tk_tixing">
						<em class="fl">
						<c:if test="${type==9}">ICD9名称 ：</c:if>
						<c:if test="${type==10}">ICD10名称 ：</c:if>
						</em>
						<input type="text"  class="fl tik_select" name="sname" id="sname" value="${sname}"/>
						<em class="fl" style="margin-left:20px;">
						<c:if test="${type==9}">ICD9编号 ：</c:if>
						<c:if test="${type==10}">ICD10编号 ：</c:if>
						</em>
						<input type="text"  class="fl tik_select" name="scode" id="scode" value="${scode}"/>
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
					<a href="javascript:void(0);" class="fl att_tianjia" style="width:95px;margin-right:20px">
						<c:if test="${type==9}">添加ICD9</c:if>
						<c:if test="${type==10}">添加ICD10</c:if>
					</a>
					<a href="javascript:void(0);" class="fl att_tianjia03">批量删除</a>
				</div>
			</div>
			<div class="clear"></div>
			<display:table name="propList" excludedParams="type" requestURI="${ctx}/propManage/icdList.do?type=${type}" id="p" class="mt10 cas_table" pagesize="20"  sort="list" defaultsort="1">
				<display:column title="<input type='checkbox' class='chkall'/>" style="width:5%;"><input type='checkbox' class='chk' value="${p.id}"/></display:column>
				<display:column title="序号" style="width:10%;" property="id"></display:column>
				<display:column title="ICD${type}编号" style="width:10%;" sortable="true" property="code"></display:column>
				<display:column title="疾病名称" style="width:20%;" sortable="true" property="name"></display:column>
				<display:column title="英文名称" style="width:15%;" sortable="true" sortProperty="name_en" property="nameEn"></display:column>
 				<display:column title="助记码" style="width:10%;" sortable="true" property="hint"></display:column>
 				<display:column title="已关联的知识点" style="width:10%;" property="propNames"></display:column>
 				<display:column title="操作" style="width:20%;">
					<a href="javascript:editPropShow('${p.id}','${p.name}','${p.code}','${p.nameEn}','${p.hint}','${p.propIds}','${p.type}');" >修改</a>
					<a href="javascript:doDelProp(${p.id});" >删除</a>
					<a href="${ctx}/propManage/showIcdRel.do?type=${type}&id=${p.id}" >知识点</a>
 				</display:column>
			</display:table>

		</div>
	</div>
		<!-- 试题内容（结束） -->

</div>
<!-- 主题弹出框 -->
<div class="toumingdu att_xueke" style="display:none;">
	<div class="TJ_cueke">
		<div class="fl mt30 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:100px;"><span class="fr">
				<c:if test="${type==9}">ICD9编码：</c:if>
				<c:if test="${type==10}">ICD10编码：</c:if>	
				</span><em class="fr">*</em></p>
			<input type="text" class="fl att_bianji01" id="rsCode"/>
			<input type="hidden" id="rsId" />
		</div>
		<div class="clear"></div>
		<div class="fl mt20 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:100px;"><span class="fr">疾病名称：</span><em class="fr">*</em></p>
			<input type="text" class="fl att_bianji01" id="rsName" />
			<input type="hidden" id="rsType"  value="${type}" />
			<input type="hidden" id="mode"  />
		</div>
		<div class="clear"></div>
		<div class="fl mt20 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:100px;"><span class="fr">英文名称：</span></p>
			<input type="text" class="fl att_bianji01" id="rsNameEn" 
			onkeyup="this.value=this.value.replace(/[^a-zA-Z]+/g ,'')" />
		</div>
		<div class="clear"></div>
		<div class="fl mt20 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:100px;"><span class="fr">助记码：</span></p>
			<input type="text" class="fl att_bianji01" id="rsHint" />
		</div>
		<input type = "hidden" id = "propIds" name = "propIds" />
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
		var type = ${type};
		var submenuindex;
		if (type == 9) submenuindex = 2;
		else if (type == 10) submenuindex = 3;
		var submenu = "mar_left0" + menuindex;
		$('.tk_mainmenu>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
	

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
			$('#rsNameEn').val('');
			$('#rsHint').val('');
			$('#mode').val('add');
		});
	//保存ICD
		$('.att_baocun').click(function(){
			if (!$('#rsName').val()){
				alert("请输入名称！");
				return;
			}
 			if (!$('#rsCode').val()){
				alert("请输入编码！");
				return;
			}
 			if ($('#rsName').val().indexOf(" ")>-1){
				alert("名称不能为空格！");
				return;
			}
 			if ($('#rsCode').val().indexOf(" ")>-1){
				alert("编码不能为空格！");
				return;
			}
 			if ($('#rsName').val().length>50){
				alert("名称长度不能大于50！");
				return;
			}
 			if ($('#rsCode').val().length>20){
				alert("编码长度不能大于20！");
				return;
			}
 			if ($('#mode').val() == 'add'){
				var url = '${ctx}/propManage/addIcd.do';
				var params = 'propName=' + $('#rsName').val();
				params += '&code='+$('#rsCode').val();
				params += '&type='+$('#rsType').val();
				params += '&nameEn='+$('#rsNameEn').val();
				params += '&hint='+$('#rsHint').val();
				
				$.ajax({
					url: url,
					type: 'POST',
					data: params,
					dataType: 'text',
					success: function(result){
						if(result == 'success'){
						   alert('ICD添加成功！');
						   $('.att_xueke').hide();
						   document.location.href = document.location.href.split("#")[0];
						}else{
							alert('名称或编码重复且不能保存!');
							return false;
						};
					}
				});
			}
			//修改
			else if ($('#mode').val() == 'edit'){
				var url = '${ctx}/propManage/editIcd.do';
				var params = 'propName=' + $('#rsName').val();
				params += '&code='+$('#rsCode').val();
				params += '&id='+$('#rsId').val();
				params += '&nameEn='+$('#rsNameEn').val();
				params += '&hint='+$('#rsHint').val();
				params += '&type='+$('#rsType').val();
				params += '&propIds='+$('#propIds').val();
				$.ajax({
					url: url,
					type: 'POST',
					data: params,
					dataType: 'text',
					success: function(result){
						if(result == 'success'){
						   alert('ICD修改成功！');
						   $('.att_xueke').hide();
						   document.location.href = document.location.href.split("#")[0];
						}else{
							alert('名称或编码重复且不能保存!');
							return false;
						};
					}
				});
			}
			
			//$('.att_xueke').hide();
		});
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
			var url = '${ctx}/propManage/delIcd.do?mode=batchdel';

			var array = new Array();var dels=0;
			$('.chk:checked').each(function(key, val){
				  dels++;
				array.push($(val).val());
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
							alert(dels+'个ICD中'+result+'个ICD删除成功，其他'+ eval(dels - result)+'个ICD因为在别的地方使用无法删除！');
							document.location.href = document.location.href.split("#")[0];
							}
						 	else{
						    alert(  result+'个ICD删除成功!');
							document.location.href = document.location.href.split("#")[0];
							}
					}else{
						alert('操作失败！');
					};
				}
			});
			
		});
		
		
});

//表示修改页面
function editPropShow(_id,_name,_code,_nameen,_hint,_propIds, _type){
	$('#rsName').val(_name);
	$('#rsCode').val(_code);
	$('#rsId').val(_id);
	$('#rsNameEn').val(_nameen);
	$('#rsHint').val(_hint);
	$('#rsType').val(_type);
	$('#propIds').val(_propIds);
	$('#mode').val('edit');
	$('.att_xueke').show();
};

//删除
var force_del_url;
var force_del_params;
 
function doDelProp(id){
	if(!confirm("您确定要删除吗？"))return;
	var url = '${ctx}/propManage/delIcd.do';
	if(id!=''){
		var params = 'id=' +id;
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
		      alert('ICD删除成功！');
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
						      alert('ICD删除成功！');
						      document.location.href = document.location.href.split("#")[0];
						   }else{
						      alert('ICD删除失败！');
						   };
					    }
					 });
		   		} */
		   };
	    }
	    });
}


function entersearch(){
   var event = window.event || arguments.callee.caller.arguments[0];
   if (event.keyCode == 13) {
	   $('#sfrm').submit();
   }
}
</script>
</body>
</html> 