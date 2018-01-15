<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/taglibs.jsp"%>
<title>培训后台</title>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
</head>

<%@include file="/peixun_page/top.jsp"%>

<body>
<!-- 查询条件 -->
<div class="center">
	<div class="tiaojian" style="min-height:40px;">
		<div class="fr cas_anniu">
			<a href="#" class="fl add_btn mr20" style="width:140px;">添加三级</a>
			<!-- <a href="javascript:history.go(-1);" class="fl" style="width:70px;">返回</a> -->
			<a href="###" class="fl" style="width:70px;" onclick="goFourLevelBefore('${parentId}','${param.beforeQualityId}','${beforeParentName}');">返回</a>
		</div>
		<div class="clear"></div>
	</div>
</div>
<div class="clear"></div>
<div class="bjs"></div>

<!-- 内容 -->
<div class="center" style="">
	<div class="tabs_main">
		<ul class="tab">
			<li id="tab1" class="active">学习地图</li>
			<li id="tab2">ICD10</li>
		</ul>
		<div class="tab1 cont">
			<div class="center01">
				<form id="fm" action="${ctx}/qualityManage/guide.do?pageNum=4&parentId=${parentId}&qualityId=${qualityId}" method="POST">
				<input type="hidden" name="parentName" value="${parentName}"/>
				<div class="tiaojian" style="min-height:40px;">
					<p class="fl" style="margin-right:20px;">
						<input type="text" placeholder="输入学习地图三级名称" name="name" value="${name}"/>
					</p>
					<div class="fl cas_anniu">
						<a href="javascript:$(fm).submit();" class="fl" style="width:70px;">查询</a>
					</div>
					<div class="clear"></div>
				</div>
				</form>
				<display:table name="list" requestURI="${ctx}/qualityManage/guide.do" id ="p" class="mt10 table" excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}">
					
					<display:column title = "序号" style = "width:5%;" >${p_rowNum}</display:column>
						<display:column title="学习地图三级"  style="width:35%;">${p.name}</display:column>	
						<display:column title="ICD10"  style="width:35%;">
							<c:forEach items="${p.icdPropList}" var="item">
					    		${item.name}&nbsp;
					  	 	</c:forEach>
						</display:column>	
						<display:column title = "操作" style ="width:25%;">
							<a href="javascript:updateGuide(${p.id}, '${p.name}');" class="edit_btn">修改</a>
							<a href="javascript:delGuide(${p.id});" class="">删除</a>
							<a href="###" onclick="goFourLevel('${p.id}','${param.parentId}','${qualityId}','${param.beforeQualityId}','${parentName}','${p.name}');">四级</a>
						</display:column>
					
					</display:table>
				<div class="clear"></div>

			</div>
		</div>
		<div class="tab2" style="display:none;">
			<div class="center01">
				<form id="fm1" action="${ctx}/qualityManage/guide.do?pageNum=4&search_ctr=icd&parentId=${parentId}&qualityId=${qualityId}" method="POST">
				<input type="hidden" name="parentName" value="${parentName}"/>
				<div class="tiaojian" style="min-height:40px;">
					<p class="fl" style="margin-right:20px;">
						<input type="text" placeholder="输入ICD10名称" name="icd_name" value="${icd_name}"/>
					</p>
					<div class="fl cas_anniu">
						<a href="javascript:$(fm1).submit();" class="fl" style="width:70px;">查询</a>
					</div>
					<div class="clear"></div>
				</div>
				</form>
					<%--&parentName=${parentName}--%>
				<display:table name="new_list" requestURI="${ctx}/qualityManage/guide.do?pageNum=4&search_ctr=icd&parentId=${parentId}&qualityId=${qualityId}" id ="p" class="mt10 table"
							   pagesize="15" size="${new_list_totalSize}" decorator="com.hys.exam.displaytag.StudyGuideTableDecorator">
					
					<display:column title = "序号" style = "width:5%;">${p.icdPropList[0].id}</display:column>
					<display:column title="ICD10"  style="width:35%;">${p.icdPropList[0].name}</display:column>
					<display:column title="学习地图三级"  style="width:35%;" property="name"></display:column>	
					<display:column title = "操作" style ="width:25%;">						
						<a href="javascript:updateGuide(${p.id}, '${p.name}');" class="edit_portraits_btn">修改</a>
						<a href="javascript:updateGuideIcdProp(${p.id}, ${p.icdPropList[0].id});" class="">删除</a>												
			    		<a href="###" onclick="doFourLevel('${p.id}','${parentName}','${p.name}');">四级</a>						
					</display:column>
					
					</display:table>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
function goFourLevelBefore(parentId,qualityId,parentName) {
   var aaa = localStorage.getItem("bbb");
   var pAllName = parentName  ;
   pAllName = encodeURI(pAllName) ;
   if(qualityId==''||qualityId==null){
	   window.location.href = "${ctx}/qualityManage/guide.do?pageNum=3&parentId=" + parentId + "&qualityId=" + aaa + "&parentName=" + pAllName ;
   }else{
	   window.location.href = "${ctx}/qualityManage/guide.do?pageNum=3&parentId=" + parentId + "&qualityId=" + qualityId + "&parentName=" + pAllName ;
   }
}
function goFourLevel(parentId,beforeParentId,qualityId,beforeQualityId,parentName,pName) {
   localStorage.setItem("ccc", beforeParentId);
   var pAllName = "能力管理 > 学习地图 >" + parentName + " > " + pName ;
   pAllName = encodeURI(pAllName) ;
   window.location.href = "${ctx}/qualityManage/guide.do?pageNum=5&parentId=" + parentId + "&beforeParentId=" + beforeParentId + "&qualityId=" + qualityId 
                        + "&beforeQualityId=" + beforeQualityId + "&parentName=" + pAllName ;
}
function doFourLevel(pId,parentName,pName) {
   var pAllName = parentName + " > " + pName ;
   pAllName = encodeURI(pAllName) ;
   window.location.href = "${ctx}/qualityManage/guide.do?pageNum=5&parentId=" + pId + "&parentName=" + pAllName ;
}

	
	var win_w = parseInt($(window).width() / 3.5) + "px";
	var win_h = parseInt($(window).height() / 2.5) + "px";
	
	

$(function(){
			
	var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
	var submenu = "lc_left0" + menuindex;
	$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
	$('.'+submenu+'').show();
	$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
	
	//	标签页
	$(".tabs_main ul.tab li:first").addClass("active");
	$(".tabs_main .cont.first").show().siblings(".cont").hide();
	$(".tabs_main ul.tab li").each(function(){
		$(this).click(function(){
			$(this).addClass("active").siblings().removeClass("active");
			var c_name = $(this).attr("id");
			$(".tabs_main div." + c_name).show().siblings("div").hide();
		});
	});
	
	if ('${search_ctr}' != '') {
		$(".tabs_main ul.tab li:first").next().addClass("active").siblings().removeClass("active");
		var c_name = $(".tabs_main ul.tab li:first").next().attr("id");
		$(".tabs_main div." + c_name).show().siblings("div").hide();
	}
	
//选择目录弹出框
	
	
	$(".add_btn").click(function() {
		var str_icd='';	
		
			/* $('#changeICD').keyup()
			{
				var str;
				var name = $(this).val();
				var url = '${ctx}/qualityManage/guide.do';
				var params = 'handle=getICDByKey&name='+name;
				$.ajax({
					url		:url,
					data	:params,
					type	:'post',
					dataType:'json',
					success:function(result){
						if(result.result != null){
							for(var i=0;i<result.result[i].length;i++){
									
							}
							alert(str);
						}
						else return ;
					}
				});
			} */var add_cont = '' +
					'<div class="center">' +
						'<div class="tiaojian clear" style="min-height:40px;">' +
							'<p class="clear" style="margin-bottom:10px;">' +
								'<span class="fl" style="width:8em;text-align:right;display:inline-block;"><font color = "red">*</font>学习地图三级：</span>' +
								'<input type="text" name="add_name" id="add_name"/>' +
							'</p>' +
						'</div>' +
						'<div class = "clear"></div>'+
						
						'<p style="margin-left:50px;" class="fl cas_anniu tiaojian clear">'+
							'<input type="text" id="search_name" class="fl"/>'+
							'<a herf="javascript:void(0);" id="search_icd" class="fl"> 查询</a>'+
						'</p>'+
						'<div class="tiaojian clear" style="min-height:40px;">'+
							
								'<p style="margin-right:20px;"><span><font color ="red">*</font>ICD10:</span></p>' +
								'<textarea type="text" class="fl xs_kuang" readonly></textarea>'+
								'<input type="hidden" id="ICDId" value="" name="icdName"/>'+
							
						'</div>'+
						'<div class="clear"></div>'+
						
						
						'<div class="tiaojian clear">'+
						'<ul class="tabs">'+
							'</ul>'+
						'</div>'+
					'</div>';
					layer.open({
					type: 1,
					title: "添加学习地图三级",
					skin: 'layui-layer-rim', //加上边框
					area: [win_w, win_h], //宽高
					content: add_cont,
					closeBtn: 0,
					btn: ['保存', '取消'],
					
					yes: function (index, layero) {
                        var regx = /^[\u4E00-\u9FA5A-Za-z0-9]+$/;
                        var value = $('#add_name').val();
						if($('#add_name').val() == ''){
							alert("请输入学科地图三级名称!");
							$('#add_name').focus();
							return false;
						}else if(!regx.test(value)){
                            alert("学习地图名称只能输入 数字英文和汉字!");
                            return false;
                        } else if($('.xs_kuang').val() == ''){
							alert("请输入ICD10！");
							return false;
						}
						
							var url = "${ctx}/qualityManage/guide.do";
							var params = "handle=add&pageNum=4&parentId="+${parentId}+"&qualityId="+${qualityId}+"&name=" + $('#add_name').val()+"&icdName="+$('#ICDId').val();
							
							$.ajax({
								url: url,
								type: 'POST',
								data: params,
								dataType: 'text',
								success: function(result){
									$('.xs_kuang').val('');
									if (result == "success"){	
										alert('添加学习地图三级成功！');					
										document.location.href = document.location.href.split("#")[0];
									}
									else if(result == "error"){
										alert ("由于网络问题或者是名称重复的原因无法添加！");
										return false;
									}
								}
							});	
							
							
						
					},
					btn2: function (index, layero) {
						$('.xs_kuang').val('');
						layer.close(index);
					},
					success:function(index, layero){
					var url = '${ctx}/qualityManage/guide.do';
					 var params = 'handle=getICD';
					/*$.ajax({
						url: url,
						type: 'POST',
						data: params,
						dataType: 'json',
						success:function(result){
							for(var i=0;i<result.result.length;i++)
							{
								str_icd += '<li><span class="checkbox"><input name="ICD_name" onClick = "javascript:clickDown(this);" type="checkbox" id=' +result.result[i].id+' value="'+result.result[i].name+'"/></span><span class="username">'+result.result[i].name+'</span></li>';
							}
							$('.tabs').html(str_icd);
						}
					}); */
						$('#search_icd').click(function(){
							params = 'handle=getICD&searchName='+ $('#search_name').val();
							str_icd ='<li><input type="checkbox" name="allSelectButtonForICD" value="1" id="allSelectButtonForICD"/>全选</li>';
							$.ajax({
								url: url,
								type: 'POST',
								data: params,
								dataType: 'json',
								success:function(result){
									for(var i=0;i<result.result.length;i++)
										{
											str_icd += '<li><span class="checkbox"><input name="ICD_name" onClick="javascript:clickDown(this);" type="checkbox" id=' +result.result[i].id+' value="'+result.result[i].name+'"/></span><span class="username">'+result.result[i].name+'</span></li>';
										}
										$('.tabs').html(str_icd);
										
								}
								
							});
						});
						$("#allSelectButtonForICD").live({"click":function(){
							if($(this).is(":checked")){
								$("input[name=ICD_name][type=checkbox]").attr("checked","checked");
								$("input[name=ICD_name][type=checkbox]").each(function(){
									clickDown(this);
								});
							}else{
								$("input[name=ICD_name][type=checkbox]").removeAttr("checked");
								$("input[name=ICD_name][type=checkbox]").each(function(){
									clickDown(this);
								});
							}
						}});
						$(".btn1").click(function(){
							var t1_str = $(".username").val();
							$(".xs_kuang").val(t1_str);
							layer.close(index);
						});
					}
				});
	});
	
});


function delGuide(id) {
	//删除前查询该三级能力下是否有四级，如果有则提示不能删除
	$.ajax({
		url: "${ctx}/qualityManage/guide.do",
		type: 'POST',
		data: "handle=getNextStudyGuide&id="+id+"&level=3",
		dataType: 'json',
		success: function(result){
			if (result.size>0){
				alert ("该三级学习地图下包含四级，不能被删除！");
				return false;
			}else {
				var url = "${ctx}/qualityManage/guide.do";
				var params = "handle=delete&id=" + id;
				if(!confirm("您确定要删除吗?")) return;
				$.ajax({
					url: url,
					type: 'POST',
					data: params,
					dataType: 'text',
					success: function(result){
						if (result == "success"){
							alert ("删除成功！");
							document.location.href = document.location.href.split("#")[0];
						}
						else{
							alert ("删除不成功！");
						}
					}
				});	
			}
		}
	});	
	
}

function updateGuide(id, name) {
 var str_icd='';
	$.ajax({
		url	:'${ctx}/qualityManage/guide.do',
		data:"handle=getICD&id="+id,
		type:'post',
		dataType:'json',
		success:function(result){
			var icd='';
		/* 	if(result.list != ''){
				for(var i=0;i<result.list.length;i++)
					$('#ICDId').val($('#ICDId').val()+result.list[i].id+",");
			}
			alert($('#ICDId').val());
			icd= $('#ICDId').val();
			var icdList = icd.split(","); */
			var selectedICDNames = "";
			var selectedICDIDs = "";
			for(var i=0;i<result.result.length;i++)
					{
						var isChecked = "";
						for(var j=0;j<result.list.length;j++){
							if(result.list[j].id==result.result[i].id){
								isChecked = 'checked';
								selectedICDNames += result.result[i].name + ",";
								selectedICDIDs += result.result[i].id + ",";
								break;
							}
						}
						str_icd += '<li><span class="checkbox"><input name="ICD_name" type="checkbox" id=' +result.result[i].id+' value="'+result.result[i].name+'" '+isChecked+'/></span><span class="username">'+result.result[i].name+'</span></li>';
					}
					var edit_cont = '' +
					'<div class="center">' +
						'<div class="tiaojian clear" style="min-height:40px;">' +
							'<p class="clear" style="margin-bottom:10px;">' +
								'<span class="fl" style="width:8em;text-align:right;display:inline-block;"><font color="red">*</font>学习地图三级：</span>' +
								'<input type="text" name="edit_name" id="edit_name" value="" />' +
							'</p>' +
						'</div>' +
						'<div class="tiaojian clear" style="min-height:40px;">'+
							
								'<p style="margin-right:20px;"><span><font color ="red"></font>ICD10:</span></p>' +
								'<textarea type="text" class="fl xs_kuang" readonly>'+selectedICDNames+'</textarea>'+
								'<input type="hidden" id="ICDId" value="'+selectedICDIDs+'" name="icdName"/>'+
							
						'</div>'+
						'<div class="clear"></div>'+
						
						
						'<div class="tiaojian clear">'+
						'<ul class="tabs">'+
							str_icd +
							'</ul>'+
						'</div>'+
					'</div>';
					layer.open({
					type: 1,
					title: "编辑学习地图三级",
					skin: 'layui-layer-rim', //加上边框
					area: [win_w, win_h], //宽高
					content: edit_cont,
					closeBtn: 0,
					btn: ['保存', '取消'],
					yes: function (index, layero) {
                        var regx = /^[\u4E00-\u9FA5A-Za-z0-9]+$/;
                        var value = $('#edit_name').val();
						if($('.xs_kuang').val() == ''){
							alert("请输入ICD10！");
							return false;
						}
						if($('#edit_name').val == ''){
							alert('请输入学习地图三级！');
							return false;
						}else if(!regx.test(value)){
                            alert("学习地图名称只能输入 数字英文和汉字!");
                            return false;
                        }
						
						var url = "${ctx}/qualityManage/guide.do";
						var params = "handle=update&id=" + id;
						params += "&name=" + $('#edit_name').val()+"&icdName="+$('#ICDId').val();
						
						$.ajax({
							url: url,
							type: 'POST',
							data: params,
							dataType: 'text',
							success: function(result){
								if (result == "success"){
									alert('修改成功');
									document.location.href = document.location.href.split("#")[0];
									
								}
								else if(result == "error"){
									alert ("由于网络问题或者是名称重复的原因无法修改！");
									return false;
								}
							}
						});
						
					},
					btn2: function (index, layero) {
						layer.close(index);
					},
					success:function(index, layero){
						$('#edit_name').val(name);
						
						var str1 = '';
						var ids1 ='';
						$(".tabs input[name=ICD_name]:checked").each(function(){
							str1 +=$(this).val()+",";
							ids1 += $(this).prop("id")+",";
						});
						$(".xs_kuang").val(str1);
						$("#ICDId").val(ids1);
						
						$("input[name=ICD_name]").click(function(){
							var str = '';
							var ids='';
							$(".tabs input[name=ICD_name]:checked").each(function(){
								str+=$(this).val()+",";
								ids += $(this).prop("id")+",";
							});
							$(".xs_kuang").val(str);
							$("#ICDId").val(ids);
						});
						$(".btn1").click(function(){
							var t1_str = $(".username").val();
							$(".xs_kuang").val(t1_str);
							layer.close(index);
						});
					}
				});
		}
	});
	
	
}

function updateGuideIcdProp(id, prop_id) {
	
	var word =  id + '_' + prop_id;	
		
	var url = "${ctx}/qualityManage/guide.do";
	var params = "handle=icdUpdate&word=" + word;	
	params += "&method=delete";
					
					
		$.ajax({
			url: url,
			type: 'POST',
			data: params,
			dataType: 'text',
			success: function(result){
				if (result == "success"){
				
					document.location.href = document.location.href.split("#")[0];
				}
				else{					
				}				
			}

		}); 
}
function clickDown(obj){
	
	var str = $(".xs_kuang").val();
	var ids=$("#ICDId").val();
	if($(obj).prop("checked") == true)
	{
		var selarray = str.split(",");
		var newnarray = new Array();
		$(selarray).each(function(key, val){
			if(val != "")
			{
				newnarray.push(val);
			}
		});
		newnarray.push($(obj).val());
		selarray = ids.split(",");
		
		var newarray = new Array();
		$(selarray).each(function(key, val){
			if(val != "")
			{
				newarray.push(val);
			}
		});
		newarray.push($(obj).prop("id"));
		$(".xs_kuang").val(newnarray.toString());
		$("#ICDId").val(newarray.toString());
	}
	else
	{
		var selarray = str.split(",");
		selidarray = ids.split(",");
		var newnarray = new Array();
		$(selarray).each(function(key, val){
			if (val == $(obj).val() && selidarray[key] == $(obj).prop("id"))
			{
				
			}
			else
			{
				newnarray.push(val);
			}
		});
		
		var newarray = new Array();
		$(selidarray).each(function(key, val){
			if (val != $(obj).prop("id")) newarray.push(val);
		});
		$(".xs_kuang").val(newnarray.toString());
		$("#ICDId").val(newarray.toString());
	}		
}
</script>
</body>
</html>
