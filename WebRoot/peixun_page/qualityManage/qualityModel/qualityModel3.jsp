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
<!-- 查询条件 -->
<div class="center">
	<div class="tiaojian" style="min-height:40px;">
	 <form id="level3_form" action="${ctx}/qualityManage/qualityManage.do" method="post">
		 <%--?pageNum=3&id=${parentId}&backId=${backId}&captionName=${captionName}--%>
		 <input id="pageNum" name="pageNum" type="hidden" value="3">
		 <input id="typeID" name="typeID" type="hidden" value="${parentId}">
		 <input id="backId" name="backId" type="hidden" value="${backId}">
		 <input id="captionName" name="captionName" type="hidden" value="${captionName}">
		<p class="fl" style="margin-right:20px;">
			<span>能力模型二级：</span>
			<input type="text" name="sname" value="${sname}"/>
		</p>
	 </form>
		<div class="fl cas_anniu">
			<a href="javascript:$(level3_form).submit();" class="fl" style="width:70px;">查询</a>
		</div>
		<div class="fr cas_anniu">
			<a href="javascript:void(0);" class="fl add_btn mr20" style="width:140px;">添加二级</a>
			<a href="###" class="fl" style="width:70px;" onclick="goBackLevel('${backId}','${beforeCaptionName}');">返回</a>
			
		</div>
		<div class="clear"></div>
	</div>
</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
		<display:table name="QM_list" requestURI="${ctx}/qualityManage/qualityManage.do" id="p" class="mt10 table"  excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}">
		<display:caption>${captionName}</display:caption>
				<display:column title="序号" style="width:5%;">${p_rowNum}</display:column>
				<display:column title="能力模型二级"  style="width:35%;">${p.name}</display:column>	
				<display:column title="已关联主题"  style="width:35%;">
					<script>var k = 0;</script>
					<c:forEach items = "${p.subjectPropList}" varStatus = "status" var = "qm">
						<script> if(k != 0) document.write(",");k++;</script>&nbsp;${qm.name}
					</c:forEach>
				</display:column>			
				<display:column title="操作" style="width:25%;">
					<a href="javascript:update('${p.id}','${p.name}');" class="edit_btn">修改</a> 
					<a href="javascript:doDelProp(${p.id});">删除</a>							 
				</display:column>
		</display:table>

		<div class="clear"></div> 	
	</div>
</div>
<%-- <div>
	<c:forEach items="${QM_list}" varStatus = "status" var = "a">
		${a.subjectPropList.name};
	</c:forEach>
</div> --%>
<script type="text/javascript">
function goBackLevel(backId,captionName) {
   var pAllName = captionName ;
   pAllName = encodeURI(pAllName) ;
   window.location.href = "${ctx}/qualityManage/qualityManage.do?typeID=" + backId + "&pageNum=2&captionName=" + pAllName ;
}
$(function(){
	
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "lc_left0" + menuindex;
		$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");	
	
	
	var win_w = parseInt($(window).width() / 2) + "px";
	var win_h = parseInt($(window).height() / 2) + "px";
	var add_cont = '' +
			'<div class="center">' +
				'<div class="tiaojian clear" style="min-height:40px;">' +
					'<p class="clear" style="margin-bottom:10px;">' +
						'<span class="fl" style="width:8em;text-align:right;display:inline-block;"><font color="red">*</font>能力模型二级：</span>' +
						'<input type="text" id="nengli_add"/><input type="hidden" id="add_zuti_ids"/>' +
					'</p>' +
					'<p class="fl" style="margin-right:10px;width:400px">' +
						'<span class="fl" style="width:8em;text-align:right;display:inline-block;"><font color="red">*</font>选择主题：</span>' +
						'<textarea name="subjects" style="width:70%;height:200px;" readonly placeholder="勾选右侧主题名称"></textarea>' +
					'</p>' +
					'<div class="fl subject_list" style="width:300px;height:200px;overflow-x:hidden;overflow-y:auto;border: 1px solid #cfcfcf;padding:10px;box-sizing:border-box;line-height:25px;">' +
						'<c:forEach items = "${zutilist}" varStatus = "status" var = "zuti">'+
							'<input type="checkbox" value="${zuti.name}" name="${zuti.id}"/>${zuti.name}<br>'+
						'</c:forEach>'+
					'</div> ' +
				'</div>' +
			'</div>';
	
	$(".add_btn").click(function() {
	
		layer.open({
			type: 1,
			title: "添加能力模型二级",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w], //宽高
			content: add_cont,
			closeBtn: 0,
			btn: ['保存', '取消'],
			yes: function (index, layero) {			   
				//编写保存功能
				var regx = /^[\u4E00-\u9FA5A-Za-z0-9]+$/
	            var value = $('#nengli_add').val();//人物画像名称
	            if(!regx.test(value)){
	                alert("能力模型二级名称只能输入 数字英文和汉字!");
	                return;
	            }else if(value.trim().length>20){
					alert("能力模型二级名称最多20字！");
					return;
				}else if($('#nengli_add').val().trim() == ""){
					alert("请输入能力模型二级!");
					return;
				}else if($('#add_zuti_ids').val().trim() == ""){
					alert("请选择主题！");
					return;
				}
				zuti_ids = $('#add_zuti_ids').val().split(",");
				var url = "${ctx}/qualityManage/qualityManage.do";
				var params = 'mode=add&zutiIds='+zuti_ids+'&parentId=${parentId}&name='+$('#nengli_add').val();
				$.ajax({
					type:'POST',
					url:url,
					data:params,
					dataType:'text',
					success:function(b){
						if(b == 'success'){
							alert('添加成功');
					  		document.location.href = document.location.href.split("#")[0];
					  	}else if(b == 'nameIsSame'){
					  	   alert('名字重复，请重新输入名字！');
					  	}
					  	else {
					  	   alert('添加失败!');
					  	}					  		
					},
					error:function(){
						alert('添加失败!');
					}
				});
			},
			btn2: function (index, layero) {
				layer.close(index);
			},
			success:function(index, layero){			
				 $(".subject_list").click(function(){
					var str = '';var str1 = '';
					$(".subject_list input[type=checkbox]:checked").each(function(){
						str+=$(this).val()+"\n";
						if (str1 == "") {
							str1+=$(this).prop("name");
						}
						else {
							str1 += "," + $(this).prop("name");
						}
//						console.log($(this).val());
					});
//					console.log(str);
					
					$("textarea[name=subjects]").val(str);
					$('#add_zuti_ids').val(str1);
				}); 
			}
		});
	});
	

})
function doDelProp(del_id) {

	if(!confirm("确定要删除吗?")) return;	
	var url='${ctx}/qualityManage/qualityManage.do?mode=delete&id='+del_id;
	if(del_id != ''){
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
	  		document.location.href = document.location.href.split("#")[0];
	  	}else
	  		alert('删除失败!');
	  },
	  error:function(){
	  	alert('删除失败!');
	  }
		
	});
}
var win_w = parseInt($(window).width() / 2) + "px";
var win_h = parseInt($(window).height() / 2) + "px";
var edit_cont = '' +
			'<div class="center">' +
			'<div class="tiaojian clear" style="min-height:40px;">' +
			'<p class="clear" style="margin-bottom:10px;">' +
			'<span class="fl" style="width:8em;text-align:right;display:inline-block;"><font color="red">*</font>能力模型二级：</span>' +
			'<input type="text" id="nengli_edit"/><input type="hidden" id="edit_zuti_ids"/>' +
			'</p>' +
			'<p class="fl" style="margin-right:10px;width:400px">' +
			'<span class="fl" style="width:8em;text-align:right;display:inline-block;"><font color="red">*</font>选择主题：</span>' +
			'<textarea name="subjects" id="display_init_val" style="width:70%;height:200px;" readonly placeholder="勾选右侧主题名称">' +
			'</textarea>' +
			'</p>' +
			'<div class="fl subject_list" style="width:300px;height:200px;overflow-x:hidden;overflow-y:auto;border: 1px solid #cfcfcf;padding:10px;box-sizing:border-box;line-height:25px;">' +
			'<c:forEach items = "${zutilist}" varStatus = "status" var = "zuti">'+
					'<input type="checkbox" value="${zuti.name}" name="${zuti.id}"/>${zuti.name}<br>'+
			'</c:forEach>'+
			'</div> ' +
			'</div>' +
			'</div>'; 
function update(id_, name_){

	var list = "";
	
	var url = "${ctx}/qualityManage/qualityManage.do";
	var params = 'mode=total&id='+id_;	
	$.ajax({
			type:'POST',
			url:url,
			data:params,
			dataType:'json',
			success:function(result){
				list = result.item.subjectPropList;
				
				var str1 = '';				
				for (var i=0; i<list.length; i++) {
						str1 += list[i].id + ",";					
				}
				$('#edit_zuti_ids').val(str1);
				
				
				var str = '';	
				$(".subject_list input[type=checkbox]").each(function(){
					for (var i=0; i<list.length; i++) {
						if ($(this).prop('name') == list[i].id) {
							$(this).prop('checked', 'checked');
							str += $(this).val() + "\n";
						}
					}
				});	
				
				$('#display_init_val').val(str);		
				
			},
			error:function(){
				alert('添加失败!');
			}
		});
		layer.open({
			type: 1,
			title: "修改能力模型二级",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w], //宽高
			content: edit_cont,
			closeBtn: 0,
			btn: ['保存', '取消'],
			yes: function (index, layero) {
			    var oldName = name_ ;
			    var newName = $('#nengli_edit').val() ;
				zuti_ids = $('#edit_zuti_ids').val().split(",");
				var url = "${ctx}/qualityManage/qualityManage.do";
				var params = 'mode=twoLevelNameIsSame&parentId=${parentId}&name=' + newName ;
				var nameIsSame = 0 ;
				var regx = /^[\u4E00-\u9FA5A-Za-z0-9]+$/
	            if(!regx.test(newName)){
	                alert("能力模型二级名称只能输入 数字英文和汉字!");
	                return;
	            }else if(newName.trim().length>20){
					alert("能力模型二级名称最多20字！");
					return;
				}else if($('#nengli_edit').val().trim() == ""){
					alert("请输入能力模型二级!");
					return;
				}else if($('#edit_zuti_ids').val().trim() == ""){
					alert("请选择主题！");
					return;
				}
				if (oldName != newName) {
				    $.ajax({
						type:'POST',
						url:url,
						data:params,
						dataType:'text',
						async:false,
						success:function(data1){
							if(data1.result == 'yes'){
						  		nameIsSame = 1 ;
						  		alert('名字重复，请重新输入名字！');
						  		return ;
						    }
						},
						error:function(data2){
							alert('查找能力模型二级名称失败!');
							nameIsSame = 2 ;
							return ;
						}
				   });
				}
				
				params = 'mode=edit&id='+id_+'&zutiIds='+zuti_ids+'&parentId=${parentId}&name='+ newName + "&oldName=" + oldName ;
				
				$.ajax({
					type:'POST',
					url:url,
					data:params,
					dataType:'text',
					success:function(b){
						if(b == 'success'){
					  		document.location.href = document.location.href.split("#")[0];
					  	}else if(b == 'nameIsSame'){
					  	    alert('名字重复，请重新输入名字！');
					  	}else if(b == 'error'){
					  	    alert('修改失败！');
					  	}
					  	else{					  							 		
					 		alert('修改失败！');
					 	}
					},
					error:function(){
						alert('修改失败!');
					}
				});
				
			
				//编写保存功能
				
			},
			btn2: function (index, layero) {
				layer.close(index);
			},
			success:function(index, layero){
				
				$(".subject_list").click(function(){
					var str = '';var str1 = '';
					$(".subject_list input[type=checkbox]:checked").each(function(){
						str+=$(this).val()+"\n";
						str1+=$(this).prop("name")+",";
//						console.log($(this).val());
					});
//					console.log(str);
					$("textarea[name=subjects]").val(str);
					$('#edit_zuti_ids').val(str1);
					
				});
			}
		});
		$('#nengli_edit').val(name_);		
	
}
</script>
</body>
</html>
