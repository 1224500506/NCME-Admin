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
	<div class="center none" style="">
		<div class="tk_center01">
			<ul class="cas1_xuanxing">
				<li id="laiyuan" >来源</li>
				<li  class="action">来源类型</li>
			</ul>
			<div class="clear"></div>
			<div class="att1_leixing">
				<!-- 来源类型 -->
				<div class="att1_zhuti">
					<div class="tk_center01" style="min-height:30px;">
						<div class="tk_zuo">
						<form id="sfrm" action="${ctx}/propManage/sourceTypeList.do" method="POST">
							<div class="tik_xuanze">
								<div class="mt20 fl tk_tixing">
									<em class="fl">来源类型：</em>
									<input type="text"  class="fl tik_select" name="sname" value="${sname}"/>
								</div>
								<div class="mt20 fl mll8 tk_tixing">
									<a href="javascript:void(0);" class="fl tk_chaxun">查询</a>
								</div>
							</div>
						</form>
						</div>
						<div class="clear"></div>
					</div>
					<div class="clear"></div>
					<div class="tk_center01">
						<div class="kit1_tiaojia">
							<div class="fr cas_anniu">
								<a href="javascript:void(0);" class="fl att_tianjia" style="width:95px;">添加来源类型</a>
							</div>
						</div>
						<div class="clear"></div>
			<display:table name="propList" requestURI="${ctx}/propManage/sourceTypeList.do" id="p" class="mt10 cas_table" size="${totalCount}" pagesize="20"  sort="list" defaultsort="1">
<%-- 				<display:column title="<input type='checkbox' class='chkall'/>" style="width:5%;"><input type='checkbox' class='chk' value="${p.id}"/></display:column>
 --%>				<display:column title="序号" style="width:20%;" property="id"></display:column>
				<display:column title="名称" style="width:35%;" property="type_name"></display:column>
				<display:column title="编码" style="width:20%;" property="code"></display:column>
 				<display:column title="操作" style="width:20%;">
					<a href="javascript:editPropShow('${p.id}','${p.type_name}','${p.code}');" >修改</a>
					<a href="javascript:doDelProp(${p.id});" >删除</a>
 				</display:column>
			</display:table>
						<div class="clear"></div> 
					</div>
				</div>
				<div class="clear"></div>
				<!-- end -->
			</div>
		</div>
	</div>
	<!-- 试题内容（结束） -->
</div>
<!-- 弹出框 -->
<div class="toumingdu att_xueke" style="display:none;">
	<div class="TJ_cueke">
		<div class="fl shitin_xinzeng01" style="margin-top:60px;margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">来源类型：</span><em class="fr">*</em></p>
			<input type="text" class="fl att_bianji01" id="rsName" />
			<input type="hidden" id="rsType"  value="${type}" />
			<input type="hidden" id="mode"  />
		</div>
		<div class="clear"></div>
		<div class="fl mt30 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">编码：</span><em class="fr">*</em></p>
			<input type="text" class="fl att_bianji01" id="rsCode"/>
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
	
	
		$('#laiyuan').click(function(){
			document.location.href = "${ctx}/propManage/sourceList.do";
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
	//保存来源类型
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
				alert ("名称长度不能大于50！");
				$('#rsName').focus();
				return;
			}
 			if ($('#rsCode').val().length>20){
				alert ("编码长度不能大于20！");
				$('#rsCode').focus();
				return;
			}
			//添加
			if ($('#mode').val() == 'add'){
				var url = '${ctx}/propManage/sourceTypeList.do?mode=add';
				var params = 'propName=' + $('#rsName').val();
				params += '&code='+$('#rsCode').val();
				params += '&type='+$('#rsType').val();
				
				$.ajax({
					url: url,
					type: 'POST',
					data: params,
					dataType: 'text',
					success: function(result){
						if(result == 'success'){
						   alert('来源类型添加成功！');
						   document.location.href = document.location.href.split("#")[0];
						}else{
							alert('名称或编码重复且不能保存!');
						};
					}
				});
			}
			//修改
			else if ($('#mode').val() == 'edit'){
				var url = '${ctx}/propManage/sourceTypeList.do?mode=edit';
				var params = 'propName=' + $('#rsName').val();
				params += '&code='+$('#rsCode').val();
				params += '&id='+$('#rsId').val();
				
				$.ajax({
					url: url,
					type: 'POST',
					data: params,
					dataType: 'text',
					success: function(result){
						if(result == 'success'){
						   alert('来源类型修改成功！');
						   document.location.href = document.location.href.split("#")[0];
						}else{
							alert('名称或编码重复且不能保存!');
						};
					}
				});
			}
			
			$('.att_xueke').hide();
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
});

//表示修改页面
function editPropShow(_id,_name,_code){
	$('#rsName').val(_name);
	$('#rsCode').val(_code);
	$('#rsId').val(_id);
	$('#mode').val('edit');
	$('.att_xueke').show();
};

//删除
var force_del_url;
var force_del_params;
function doDelProp(id,prop_val_id){
	if(!confirm("您确定要删除吗？"))return;
	var url = '${ctx}/propManage/sourceTypeList.do?mode=del';
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
		      alert('来源类型删除成功！');
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
						      alert('来源类型删除成功！');
						      document.location.href = document.location.href.split("#")[0];
						   }else{
						      alert('来源类型删除失败！');
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