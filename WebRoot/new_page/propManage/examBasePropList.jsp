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
			<form id="sfrm" action="${ctx}/propManage/propBaseList.do?type=${type}" method="POST">
				<div class="tik_xuanze">
					<div class="mt10 fl tk_tixing">
						<em class="fl">
						<c:if test="${type==7}">名称 ：</c:if>
						<c:if test="${type==8}">认知水平 ：</c:if>
						<c:if test="${type==9}">名称 ：</c:if>
						</em>
						<input type="text"  class="fl tik_select" name="sname" value="${sname}"/>
						<em class="fl" style="margin-left:20px;">
						编码 ：
						</em>
						<input type="text"  class="fl tik_select" name="scode" value="${scode}"/>
						<c:if test="${type==9}">
							<em class="fl" style="margin-left:20px;">
							职称类型：
							</em>
							<select class="fl tik_select" name="sclass">
								<option value="">请选择</option>
								<c:forEach items="${classList}" var="job">
									<c:if test="${job.code == sclass}">
										<option value="${job.code}" selected="selected">${job.name}</option>
									</c:if>
									<c:if test="${job.code != sclass}">
										<option value="${job.code}">${job.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</c:if>
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
						<c:if test="${type==7}">添加主题</c:if>
						<c:if test="${type==8}">添加认知水平</c:if>
						<c:if test="${type==9}">添加职称</c:if>
					</a>
					<a href="javascript:void(0);" class="fl att_tianjia03">批量删除</a>
				</div>
			</div>
			<div class="clear"></div>
			<display:table name="propList" excludedParams="type" requestURI="${ctx}/propManage/propBaseList.do?type=${type}" id="p" class="mt10 cas_table" size="${totalCount}" pagesize="20"  sort="list" defaultsort="1">
				<display:setProperty name="sort.amount" value="total" />
				<display:column title="<input type='checkbox' class='chkall'/>" style="width:5%;"><input type='checkbox' class='chk' value="${p.id}"/></display:column>
				<display:column title="序号" style="width:18%;" property="id"></display:column>
				<c:if test="${type==9}">
					<display:column title="职称类型" style="width:15%;" sortable="true">
						<c:forEach items="${classList}" var="job">
							<c:if test="${job.code == p.ext_type}">${job.name}</c:if>
						</c:forEach>
					</display:column>
				</c:if>
				<display:column title="名称" sortable="true" style="width:30%;" property="name"></display:column>
<%-- 				<display:column title="类型" style="width:20%;" property="typeName"></display:column>
 --%>				<display:column title="编码" sortable="true" style="width:18%;" property="code"></display:column>
 				<display:column title="操作" style="width:15%;">
					<a href="javascript:editPropShow('${p.id}','${p.reAll(p.name)}','${p.reAll(p.code)}','${p.ext_type}');" >修改</a>
					<a href="javascript:doDelProp(${p.id},${p.prop_val_id});" >删除</a>
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
		<c:if test="${type==9}">
			<p class="fl" style="width:110px;"><span class="fr">职称类型：</span><em class="fr">*</em></p>
				<select class="fl att_bianji01" id="rsExt_type">
					<c:forEach items="${classList}" var="job">
						<option value="${job.code}">${job.name}</option>
					</c:forEach>
				</select>
		</div>
		<div class="clear"></div>
		<div class="fl mt30 shitin_xinzeng01" style="margin-left:40px;">
		</c:if>
			<p class="fl" style="width:110px;"><span class="fr">
				<c:if test="${type==7}">主题名称：</c:if>
				<c:if test="${type==8}">认知水平名称：</c:if>
				<c:if test="${type==9}">职称名称：</c:if>
			</span><em class="fr">*</em></p>
			<input type="text" class="fl att_bianji01" id="rsName" />
			<input type="hidden" id="rsType"  value="${type}" />
			<input type="hidden" id="mode"  />
		</div>
		<div class="clear"></div>
		<div class="fl mt30 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:110px;"><span class="fr">
				<c:if test="${type==7}">主题编码：</c:if>
				<c:if test="${type==8}">认知水平编码：</c:if>
				<c:if test="${type==9}">职称编码：</c:if>
			</span><em class="fr">*</em></p>
			<input type="text" class="fl att_bianji01" id="rsCode"/>
			<input type="hidden" id="rsProp_val1" value="0" />
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
		var type = ${type};
		var submenuindex;
		if (type == 7) submenuindex = 4;
		else if (type == 8) submenuindex = 6;
		else if (type == 9) submenuindex = 7;
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
			$('#rsExt_type').val('');
			$('#mode').val('add');
		});
	//保存属性
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
			//添加
			if ($('#mode').val() == 'add'){
				var url = '${ctx}/propManage/addProp.do';
				var params = 'propName=' + $('#rsName').val();
				params += '&code='+$('#rsCode').val();
				params += '&type='+$('#rsType').val();
				params += '&prop_val1='+$('#rsProp_val1').val();
				if ($('#rsType').val() == "9")
					params += '&ext_type='+$('#rsExt_type').val();
				
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
							alert('名称或编码重复且不能保存!');
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
				if ($('#rsType').val() == "9")
					params += '&ext_type='+$('#rsExt_type').val();
				params += '&id='+$('#rsId').val();
				
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
							alert('名称或编码重复且不能保存!');
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
					alert(dels+'个项目中'+result+'个项目删除成功，其他'+ eval(dels - result)+'个项目因为在别的地方使用无法删除！');
						document.location.href = document.location.href.split("#")[0];
					}else{
						alert('操作失败！');
					};
				}
			});
			
		});
		
});

//修改
function editPropShow(_id,_name,_code,_ext_type){
	_name = _name.replace(/&quos;/g,"'");
	_name = _name.replace(/&quot;/g,"\"");
	_code = _code.replace(/&quos;/g,"'");
	_code = _code.replace(/&quot;/g,"\"");
	$('#rsName').val(_name);
	$('#rsCode').val(_code);
	$('#rsExt_type').val(_ext_type);
	$('#rsId').val(_id);
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
		      alert('删除成功！');
		      document.location.href = document.location.href.split("#")[0];
		   }else{
			   alert("该属性已被使用，无法删除!");
	/* 	   		if(confirm('该属性已被使用，若删除，则使用该属性的地方均被删除，确定删除!')){
		   			
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

function entersearch(){
   var event = window.event || arguments.callee.caller.arguments[0];
   if (event.keyCode == 13) {
	   $('#sfrm').submit();
   }
}
</script>
</body>
</html> 