<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/new_page/top.jsp"%>

<html>
<head>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理平台</title>
</head>

<body class="bjs">
<div>
	<!-- 题库内容 -->
	<div class="center">
		<div class="tk_center01">
			<div class="mt10 kit1_tiaojia">
				<div class="mt10 fl tk_tixing">
					<em class="fl">${groupName}</em>
				</div>
				<div class="fr cas_anniu">
					<a href="javascript:void(0);"  class="fl exp1_tianjia" style="width:95px;margin-right:15px;">添加届期</a>
					<!-- <a href="javascript:window.history.back();" class="fl" style="width:95px;">返回</a> -->
					<a href="${ctx}/expert/ExpertTerm.do" class="fl" style="width:95px;">返回</a>
				</div>
			</div>
			<div class="clear"></div>
			<display:table name="termList" requestURI="${ctx}/expert/ExpertGroupTerm.do?gid=${gid}" id="p" class="mt10 cas_table" size="${totalCount}" pagesize="20">
				<display:column title="序号" style="width:10%;" property="id"></display:column>
				<display:column title="届期开始时间" style="width:35%;" property="startDateStr">${p.startDate}</display:column>
				<display:column title="届期截止时间" style="width:35%;" property="endDateStr"></display:column>
 				<display:column title="操作" style="width:20%;">
					<a href="javascript:editPropShow('${p.id}','${p.startDateStr}','${p.endDateStr}');" >修改</a>
					<a href="javascript:doDelProp(${p.id});" >删除</a>
				</display:column>
			</display:table>
			<input type="hidden" id="organize" value="${organizedate}" />
		</div>
	</div>
	
		<!-- 试题内容（结束） -->
		
<!-- 届期弹出框 -->
<div class="toumingdu experts01" style="display:none;">
	<div class="TJ_cueke">
		<div class="fl shitin_xinzeng01" style="margin-top:60px;margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">开始时间：</span><em class = "fr">*</em></p>
			<input type="hidden" id="termId">
			<input type="hidden" id="mode">
			<input type="text" class="fl att_bianji01" onClick="WdatePicker()" readonly="readonly" placeholder="" id="startDate"/>
		</div>
		<div class="clear"></div>
		<div class="fl mt30 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:90px;"><span class="fr">截止时间：</span><em class = "fr">*</em></p>
			<input type="text" class="fl att_bianji01" onClick="WdatePicker()" readonly="readonly" placeholder="" id="endDate"/>
		</div>
		<div class="clear"></div>
		<div style="height:30px;"></div>
		<div class="ca1_anniu" style="width:200px;">
			<a href="javascript:void(0);" class="fl anniu att_baocun">保存</a>
			<a href="javascript:void(0);" class="fl ml30 anniu att_fanhui">返回</a>
		</div>
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
	
	//添加届期弹框
		$('.exp1_tianjia').click(function(){
			$('.experts01').show();
		});
	
	//添加学科
		$('.exp1_tianjia').click(function(){
			$('.experts01').show();
			$('#termId').val('');
			$('#startDate').val('');
			$('#endDate').val('');
			$('#mode').val('add');
		});
	//保存
		$('.att_baocun').click(function(){
			if (!$('#startDate').val() || !$('#endDate').val()){
				alert("请选择开始时间和截止时间！");
				return;
			}
			if ($('#startDate').val() == $('#endDate').val()){
				alert("截止时间必须得大于开始时间！");
				$('#endDate').val('');
				return;
			}
			if ($('#startDate').val() > $('#endDate').val()){
				alert("时间交叉！ 请选择正确的时间！");
				$('#endDate').val('');
				return;
			}
			var organizedate = $('#organize').val();
			var startDate = $('#startDate').val();
			if(organizedate!=''){
				if(startDate < organizedate){
					alert("开始时间（"+startDate+"）不能小于专委会成立时间（"+organizedate+"）！");
					$('#startDate').val('');
					return;
				}
			} 
			
			if ($('#mode').val() == 'add'){
				var url = '${ctx}/expert/ExpertGroupTerm.do';
				var params = 'gid=' + "${gid}";
				params += '&mode=add';
				params += '&startDate='+$('#startDate').val();
				params += '&endDate='+$('#endDate').val();
				
				$.ajax({
					url: url,
					type: 'POST',
					data: params,
					dataType: 'text',
					success: function(result){
						if(result == 'success'){
						   alert('添加成功！');
						   document.location.href = document.location.href.split("#")[0];
						}else if(result == 'error1'){
							alert('添加失败!');
							$('.experts01').hide();
						}
						else
						{
							alert('届期时间不能交叉！');
						};
					}
				});
			}
			//修改
			else if ($('#mode').val() == 'edit'){
				var url = '${ctx}/expert/ExpertGroupTerm.do';
				var params = 'gid=' + "${gid}";
				params += '&mode=edit';
				params += '&id='+$('#termId').val();
				params += '&startDate='+$('#startDate').val();
				params += '&endDate='+$('#endDate').val();
				
				$.ajax({
					url: url,
					type: 'POST',
					data: params,
					dataType: 'text',
					success: function(result){
						if(result == 'success'){
						   alert('修改成功！');
						   document.location.href = document.location.href.split("#")[0];
						}else if(result = 'error1'){
							alert('届期时间不能交叉！');
						}
						else{
							alert("届期时间不能交叉！");
						};
					}
				});
			}
			
		});
		
		$('.att_fanhui').click(function(){
			$('.experts01').hide();
		});
	
	

});

//表示修改
function editPropShow(_id,_start,_end){
	$('#termId').val(_id);
	$('#startDate').val(_start);
	$('#endDate').val(_end);
	$('#mode').val('edit');
	$('.experts01').show();
};

//删除
function doDelProp(id){
	var url = '${ctx}/expert/ExpertGroupTerm.do';
	if(id!=''){
		var params = 'id=' +id;
		params += '&mode=del';
	}

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
		   		alert('删除失败请检查属性的关联!');
		   };
	    }
	    });
}
function goBack() {
		window.history.back();
}
</script>
</body>
</html>