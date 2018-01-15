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
	<div class="center none" style="">
		<div class="tk_center01">
			<ul class="cas1_xuanxing">
				<li class="action">来源</li>
				<li id="laiyuan" >来源类型</li>
			</ul>
			<div class="clear"></div>
			<div class="att1_leixing">
				<!-- 来源 -->
				<div class="att1_zhuti">
					<div class="mt10 cas_chaxun" style="height:40px;">
						<form id="sfrm" action="${ctx}/propManage/sourceList.do" method="POST">
						<div class="tik_xuanze">
							<div class="mt10 fl tk_tixing" >
								<em class="fl">来源类型：</em>
								<div class="fl tik_select">
									<div class="tik_position_1">
										<b class="ml5">请选择</b>
										<p class="tik_position01"><i class="tk_bjt10"></i></p>
									</div>
									<ul class="fl tk_list1 tk_list list_group" style="display:none;">
									<select name="stype" style="display:none;">
										<option value = "-1">请选择</option>
										<c:forEach items="${typeList}" var="group">
											<option value="${group.id}"<c:if test="${group.id==stype}"> selected</c:if>>${group.type_name}</option>
										</c:forEach>
									</select>
										<li>请选择</li>
										<c:forEach items="${typeList}" var="group">
											<li>${group.type_name}</li>
										</c:forEach>
									</ul>
								</div>
<%-- 								<input type="text"  class="fl tik_select" name="stype" value='${stype}'/>
 --%>								
							</div>
							<div class="mt10 ml30 fl tk_tixing">
								<span class="fl">学科：</span>
								<input type="hidden" class="fl tik_select" id="groupIds" name="propIds" value="${propIds}"/>
								<input type="hidden" class="fl tik_select" id="propNames" name="propNames" value="${propNames}"/>
								<div class="fl tik_select01 takuang_xk" id="groupNames">${propNames}</div>
							</div>
							<div class="mt10 mll8 fl tk_tixing">
								<span class="fl">来源名称：</span>
								<input type="text"  class="fl tik_select" name="sname" value='${sname}'/>
							</div>
						</div>
						</form>
					<div class="mt10 mll8 fl tk_tixing">
						<a href="javascript:void(0);" class="fl tk_chaxun">查询</a>
					</div>
						<div class="clear"></div>
					</div>
					<div class="fr cas_anniu">
						<a href="javascript:void(0);" class="fl att_tianjia" style="margin-right:15px;">添加来源</a>
						<a href="javascript:void(0);" class="fl att_tianjia03">合并来源</a>
					</div>
					<div class="clear"></div>
			<display:table name="propList" excludedParams="type" requestURI="${ctx}/propManage/sourceList.do" id="p" class="mt10 cas_table" size="${totalCount}" pagesize="20"  sort="list" defaultsort="1">
				<display:column title="<input type='checkbox' class='chkall'/>" style="width:3%;"><input type='checkbox' class='chk' value="${p.id}"/></display:column>
				<display:column title="序号" style="width:7%;" property="id"></display:column>
				<display:column title="来源类型" sortable="true" style="width:7%;" property="typeName"></display:column>
				<display:column title="来源名称" sortable="true" style="width:14%;" property="name"></display:column>
				<display:column title="来源编码" sortable="true" style="width:7%;" property="code"></display:column>
				<display:column title="来源出处" sortable="true" style="width:7%;" property="source"></display:column>
				<display:column title="出版年限" sortable="true" style="width:8%;" property="old"></display:column>
				<display:column title="关联单元" style="width:10%;" property="propNames"></display:column>
				<display:column title="主题" style="width:10%;" property="zhutiNames"></display:column>
				<display:column title="状态" style="width:7%;">
					<c:if test="${p.state==0}">未审核</c:if>
					<c:if test="${p.state==1}">已审核</c:if>
					<c:if test="${p.state==2}">禁用</c:if>
				</display:column>
				<%-- <display:column title="作者" style="width:5%;" property="author"></display:column>
				<display:column title="网址" style="width:5%;" property="website"></display:column> --%>
				<display:column title="操作" style="width:20%;">
				<c:if test="${p.state==0}">
					<a href="javascript:editPropShow('${p.id}','${p.name}','${p.code}', '${p.type}', '${p.source}', '${p.old}', '${p.zhutiIds}', '${p.zhutiNames}', '${p.author}', '${p.website}');" >修改</a>
				</c:if>
					<a href="javascript:doDelProp(${p.id});" >删除</a>
					<a href="javascript:showRel(${p.id});" >关联单元</a>
					<c:if test="${p.state!=1}">
					<a href="javascript:shenhePropShow(${p.id});" class="">审核</a>
					</c:if>
 				</display:column>
			</display:table>

					<div class="clear"></div>
				</div>
				<!-- end -->
			</div>
		</div>
	</div>
	<!-- 试题内容（结束） -->
</div>
<!-- 来源弹出框 -->
<div class="toumingdu att_xueke" style="display:none;">
	<div class="TJ_cueke" style="min-height:500px;margin:8% auto;">
		<div class="fl shitin_xinzeng01" style="margin-top:40px;margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">来源名称：</span><em class="fr">*</em></p>
			<input type="text" class="fl att_bianji01" id="rsName"/>
			<input type="hidden" id="mode" />
		</div>
		<div class="fl shitin_xinzeng01" style="margin-top:20px;margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">来源编码：</span><em class="fr">*</em></p>
			<input type="text" class="fl att_bianji01"  id="rsCode"/>
			<input type="hidden" id="rsId" />
		</div>
		<div class="fl shitin_xinzeng01" style="margin-top:20px;margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">来源类型：</span><em class="fr">*</em></p>
			<select class="fl" style="width:302px;height:30px;border:1px solid #dddddd;" id="rsProp_val1" name="prop_val1">
				<option value="">请选择</option>
				<c:forEach items="${typeList}" var="srcType">
					<option value="${srcType.id}">${srcType.type_name}</option>
				</c:forEach>							
			</select>
		</div>
		<div class="fl shitin_xinzeng01" style="margin-top:20px;margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">来源出处：</span></p>
			<input type="text" class="fl att_bianji01"  id="rsSource"/>
		</div>
		<div class="fl shitin_xinzeng01" style="margin-top:20px;margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">出版年限：</span></p>
			<input type="text" class="fl att_bianji01"  id="rsOld" maxlength=10/>
		</div>
		<div class="fl mt20 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">主题：</span></p>
			<input type="hidden" class="fl tik_select" id="newgroupIds" name="groupIds" value=""/>
			<textarea name="" cols="" rows="" placeholder="" class="fl takuang_duoxuan takuang_xk_edit" id="newgroupNames" style="width:302px;height:30px;" readonly></textarea>
		</div>
		<div class="fl shitin_xinzeng01" style="margin-top:20px;margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">作者：</span></p>
			<input type="text" class="fl att_bianji01"  id="author" maxlength=100/>
		</div>
		<div class="fl shitin_xinzeng01" style="margin-top:20px;margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">网址：</span></p>
			<input type="text" class="fl att_bianji01"  id="website" maxlength=1000/>
		</div>
		<div class="clear"></div>
		<div class="ca1_anniu" style="width:200px;">
			<a href="javascript:void(0);" class="fl anniu att_baocun">保存</a>
			<a href="javascript:void(0);" class="fl ml30 anniu att_fanhui">返回</a>
		</div>
	
	</div>
</div>
<!-- 合并来弹源出框 -->
<div class="toumingdu att_xueke03" style="display:none;">
	<div class="TJ_cueke">
		<p style="padding-top:60px;margin-left:40px;font-size:16px;color:red;">您确认将选择的来源合并为：</p>
		<div class="fl shitin_xinzeng01" style="margin-top:30px;margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">来源类型：</span><em class="fr">*</em></p>
			<select class="fl srcleixing" style="width:300px;height:30px;border:1px solid #dddddd;">
				<option value="">请选择</option>
				<c:forEach items="${typeList}" var="srcType">
					<option value="${srcType.id}">${srcType.type_name}</option>
				</c:forEach>							
			</select>
		</div>
		<div class="fl shitin_xinzeng01" style="margin-top:30px;margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">来源名称：</span><em class="fr">*</em></p>
			<select class="fl laiyuan" id="targetSrc" name="targetSrc" style="width:300px;height:30px;border:1px solid #dddddd;">
				<option value="">请选择</option>
			</select>
		</div>
		<div class="clear"></div>
		<div class="ca1_anniu" style="width:200px;">
			<a href="javascript:void(0);" class="fl anniu att_hebing">保存</a>
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
	
	
		$('#laiyuan').click(function(){
			document.location.href = "${ctx}/propManage/sourceTypeList.do";
		});


	//查询学科
		$('.tk_chaxun').click(function(){
			$('#propNames').val($('#groupNames').text());
			$('#sfrm').submit();
			
		});	
	//添加学科
		$('.att_tianjia').click(function(){
			$('.att_xueke').show();
			$('#rsName').val('');
			$('#rsCode').val('');
			$('#rsId').val('');
			$('#rsOld').val('');
			$('#rsSource').val('');
			$('#rsProp_val1').val('0');
			$('#newgroupIds').val('');
			$('#newgroupNames').text('');
			$('#mode').val('add');
		});
		
		$('.att_tianjia03').click(function(){
			if ($('.chk:checked').length<=1){
				alert ("请选择两个以上来源。");
				return;
			}
			$('.srcleixing').val('');
			$('#targetSrc').html('<option value="">请选择</option>');
			$('.att_xueke03').show();
		});
		
		$('.srcleixing').change(function(){
			var url = '${ctx}/propManage/sourceList.do?mode=getListByType';
			var params = 'typeid=' + $(this).val();
			params+= '&state=-1';
			
			$.ajax({
				url: url,
				type: 'POST',
				data: params,
				dataType: 'json',
				success: function(result){
					if(result != ""){
						var str = '<option value="">请选择</option>';
						
						$(result.list).each(function(key, value){
							str+='<option value="'+value.id+'">'+value.name+'</option>';
						});
						
						$('.laiyuan').html(str);
					};
				}
			});
		});
		
	//保存合并来源	
		$('.att_hebing').click(function(){
			if ($('#targetSrc').val() == ""){
				alert("请选择来源名称。");
				return;
			}
			$('.att_xueke03').hide();

			var url = '${ctx}/propManage/sourceList.do?mode=merge';

			var array = new Array();
			$('.chk:checked').each(function(key, val){
				array.push($(val).val());
			});
			var params = 'target=' + $('#targetSrc').val();
			params += '&code='+array.toString();
			
			$.ajax({
				url: url,
				type: 'POST',
				data: params,
				dataType: 'text',
				success: function(result){
					if(result == 'success'){
						alert('合并成功！');
						document.location.href = document.location.href.split("#")[0];
					}else{
						alert('合并失败！');
					};
				}
			});
			$('.att_xueke').hide();
		});
	//保存	
		$('.att_baocun').click(function(){
			if (!$('#rsName').val()){
				alert ("请输入来源名称！");
				$('#rsName').focus();
				return;
			}
 			if (!$('#rsCode').val()){
				alert ("请输入来源编码！");
				$('#rsCode').focus();
				return;
			}
 			if ($('#rsName').val().indexOf(" ")>-1){
				alert("来源名称不能为空格！");
				return;
			}
 			if ($('#rsCode').val().indexOf(" ")>-1){
				alert("来源编码不能为空格！");
				return;
			}
 			if ($('#rsName').val().length>50){
				alert ("来源名称长度不能大于50！");
				$('#rsName').focus();
				return;
			}
 			if ($('#rsCode').val().length>20){
				alert ("来源编码长度不能大于20！");
				$('#rsCode').focus();
				return;
			}
			if ($('#rsProp_val1').val() == ""){
				alert("请选择来源类型！");
				return;
			}
			if ($('#author').val().length > 100){
				alert("作者框输入长度不能超过100！");
				return;
			}
			//添加
			if ($('#mode').val() == 'add'){
				var url = '${ctx}/propManage/sourceList.do?mode=add';
				var params = 'propName=' + $('#rsName').val();
				params += '&code='+$('#rsCode').val();
				params += '&type='+$('#rsType').val();
				params += '&source='+$('#rsSource').val();
				params += '&old='+$('#rsOld').val();
				params += '&prop_val1='+$('#rsProp_val1').val();
				params += '&hint='+$('#newgroupIds').val();
				params += '&author='+$('#author').val();
				params += '&website='+$('#website').val();
				
				$.ajax({
					url: url,
					type: 'POST',
					data: params,
					dataType: 'text',
					success: function(result){
						if(result == 'success'){
						   alert('来源添加成功！');
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
				var url = '${ctx}/propManage/sourceList.do?mode=edit';
				var params = 'propName=' + $('#rsName').val();
				params += '&code='+$('#rsCode').val();
				params += '&id='+$('#rsId').val();
				params += '&source='+$('#rsSource').val();
				params += '&old='+$('#rsOld').val();
				params += '&prop_val1='+$('#rsProp_val1').val();
				params += '&hint='+$('#newgroupIds').val();
				params += '&author='+$('#author').val();
				params += '&website='+$('#website').val();
				
				$.ajax({
					url: url,
					type: 'POST',
					data: params,
					dataType: 'text',
					success: function(result){
						if(result == 'success'){
						   alert('来源修改成功！');
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
			$('.att_xueke03').hide();
		});
	//check all
		$('.chkall').click(function(){
			if ($(this).attr('checked') == 'checked')
				$('.chk').attr('checked' , 'checked');
			else
				$('.chk').removeAttr('checked');
		});
		
	
		$('.takuang_xk').click(function(){
			initPropList("学科","${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#groupIds'), $('#groupNames'));
			$('.att_make01').show();
		});		
		$('.takuang_xk_edit').click(function(){
			initPropList("主题","${ctx}/propManage/getPropListAjax.do" ,7, 0, 1, 0, $('#newgroupIds'), $('#newgroupNames'));
			$('.att_make01').show();
		});		
	
	//	
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
		
		$('.tk_list option:selected').each(function(){
			var str=$(this).text();
			$(this).parent().parent().parent().find('b').text(str);
		});
		
		$(document).click(function(){
			$('.tk_list').hide('fast');
		});
	
		
		
});

//表示修改页面
function editPropShow(_id,_name,_code,_type,_source,_old,_propIds,_propNames,_author,_website){
	$('#rsName').val(_name);
	$('#rsCode').val(_code);
	$('#rsId').val(_id);
	$('#rsProp_val1').val(_type);
	$('#rsSource').val(_source);
	$('#rsOld').val(_old);
	$('#mode').val('edit');
	$('#newgroupIds').val(_propIds);
	$('#newgroupNames').text(_propNames);
	$('#author').val(_author);
	$('#website').val(_website);
	$('.att_xueke').show();
};

//表示审核页面
function shenhePropShow(_id){
	if(!confirm("您确定要审核吗？"))return;
		var url = '${ctx}/propManage/sourceList.do?mode=updateState';
		var params = '&id='+_id;
		params += '&state='+1;
			
		$.ajax({
			url: url,
			type: 'POST',
			data: params,
			dataType: 'text',
			success: function(result){
				if(result == 'success'){
				   alert('来源审核成功！');
				   document.location.href = document.location.href.split("#")[0];
				}else{
					alert('名称或编码重复,不能保存!');
				};
			}
		});
			
};

//删除
function doDelProp(id,prop_val_id){
	if(!confirm("您确定要删除吗？"))return;
	var url = '${ctx}/propManage/sourceList.do?mode=del';
	if(id!='' && prop_val_id!=''){
		var params = 'id=' +id;
		params +='&prop_val_id='+prop_val_id;
	}

	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'text',
	    success: function(result){
		   if(result == 'success'){
		      alert('来源删除成功！');
		      document.location.href = document.location.href.split("#")[0];
		   }else{
		   		alert('来源删除失败请检查属性的关联!');
		   };
	    }
	    });
}

function showRel(id)
{	
	document.location.href = '${ctx}/propManage/sourceList.do?mode=showrel&id='+id;
}

function entersearch(){
   var event = window.event || arguments.callee.caller.arguments[0];
   if (event.keyCode == 13) {
	   $('#propNames').val($('#groupNames').text());
		$('#sfrm').submit();
   }
}

</script>
</body>
</html>
