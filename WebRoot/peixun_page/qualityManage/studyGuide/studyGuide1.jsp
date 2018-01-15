<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<script type="text/javascript" src="${ctx}/peixun/js/My97Date/WdatePicker.js"></script>
<%@include file="/commons/taglibs.jsp"%>
</head>

<%@include file="/peixun_page/top.jsp"%>

<body>
<!-- 查询条件 -->
<div class="center">
	<form id="fm" action="${ctx}/qualityManage/guide.do" method="POST">
	<div class="tiaojian" style="min-height:40px;">
		<%-- <p class="fl" style="margin-right:20px;">
			<span>人物类型：</span>
			<input type="text" name="type" value="${type}"/>
		</p>
		<p class="fl" style="margin-right:20px;">
			<span>科室：</span>
			<input type="text" name="department" value="${department}"/>
		</p> --%>
		<p class="fl">
			<span>学习地图名字：</span>
			<input type="text" name="userImageName" value="${userImageName}"/>
			<span>人物画像名字：</span>
			<input type="text" name="userImage" value="${userImage}"/>
		</p>
		<div class="fl cas_anniu" style = "margin-left:20px;">
			<a href="javascript:$(fm).submit();" class="fl" style="width:70px;">查询</a>
		</div>	
		<div class="fr cas_anniu">
			<a href="javascript:void(0);" class="fl add_btn" style="width:140px;margin-right:20px;">添加学习地图</a>
			<!-- 
			<a href="#" class="fl export_btn" style="width:70px;">导出</a>
			 -->
		</div>
		<div class="clear"></div>
	</div>
	</form>
</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
	<!-- 内容 -->
	
		<display:table name="list" id ="p" class="mt10 table"  requestURI="${ctx}/qualityManage/guide.do" excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}">
			<display:caption>能力管理 > 学习地图</display:caption>
			<display:column title = "序号" style = "width:5%;" >${p_rowNum}</display:column>
			 
			<display:column title ="学习地图名字" style = "width:25%;" >${p.name}</display:column>
			<%-- <display:column title ="科室" style = "width:25%;" >
				<c:forEach items="${p.userImage.departmentPropList}" var="item">
		    		${item.name}&nbsp;
		  	 	</c:forEach>
			</display:column> --%>
			<display:column title ="人物画像" style = "width:25%;">
				<c:forEach items="${p.userImageList}" varStatus="status" var="image">
					${image.name}<br>
				</c:forEach>
				</display:column>
			<display:column title = "操作" style ="width:20%;">
				<a href="javascript:update(${p.id},'${p.name}');"> 修改</a>
				<a href="javascript:del(${p.id});" class="delId">删除</a>
				<a href="###" onclick="goOneLevel('${p.id}','${p.userImage.type.id}','${p.userImage.type.name}');">一级</a>
			</display:column>
		
		</display:table>
		
		<div class="clear"></div> 
	</div>
</div>

<script type="text/javascript">
function goOneLevel(pId,pUserImageTypeId,pUserImageTypeName) {
   var pName = pUserImageTypeName + "人物类型" ;
   pName = encodeURI(pName) ;
   window.location.href = "${ctx}/qualityManage/guide.do?pageNum=2&parentId=" + pId + "&typeId=" + pUserImageTypeId + "&parentName=" + pName ;
}


var idArrayStr='';
var newarray1 = new Array();
var newarrayId = new Array();
function select_init() {
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
var win_w = parseInt($(window).width()/1.3) + "px";
var win_h = "580px";
		
	 
function update(id,name){
 var option_maker = '<select id="sel_userLeixing" style="display:none;">'+"\n"+'<option value=""'+'>请选择</option>', li_maker ='';
		var li_maker_temp = '<li>请选择</li>';		
	   	var params = "mode=guide";
		$.ajax({
		    url:"${ctx}/quality/userImageManage.do",
		    type: 'POST',
		   	data:params,
		   	async:false,
		    dataType: 'JSON',
		    success: function(result){		    	   
			   for(var i=0;i<result.userLeixing.length;i++){
			   option_maker += '<option value='+result.userLeixing[i].type.id+'>'+result.userLeixing[i].type.name+'</option>';
			   li_maker_temp += '<li>'+result.userLeixing[i].type.name+'</li>'+"\n";			   
			   }	
			   li_maker = 	option_maker + '</select>' + li_maker_temp;	  
			 	
			   var edit_portraits_cont = 
			'<div class="center">'+
			'<div class ="tiaojian center">'+
				'<p class ="fl" style="min-hegiht:40px">'+
				'<span class="fl" style="width:8em;text-align:center"><font color="red">*</font>学习地图名字 : </span>'+
				'<input type="text" name="guideName" id="guideName" class ="fl">'+
				'</p>'+
				'<input type="hidden"  value="" name="user_image" id="user_image_id"/>'+
				'<div class ="clear"></div>'+
				'<div class = "bjs" style = "margin-top:10px;"></div>'+
				'<div class="center">'+
				'<div class="fl guideDiv" style = "margin-left:85px;"></div>' +
				'</div>'+
			'</div>'+
			'<div class ="clear"></div>'+
			'<div class="tiaojian center">' +
					//'<p class="fl" style="text-align:right;min-height:40px;">' +
					'<p>' +
						'<span class="fl" style="margin-left:20px;width:6em;text-align:center;min-height:40px;">人物类型：</span>' +
						'<div class="fl select">' +
							'<div class="tik_position">' +
								'<b class="ml5">请选择</b>' +
								'<p class="position01"><i class="bjt10"></i></p>' +
							'</div>' +
							'<ul class="fl list myList" style="display: none;">' +							
								li_maker +								
							'</ul>' +
						'</div>' +
					'</p>' +
					'<p>' +
						'<span style="margin-left:20px;width:4em;text-align:center;min-height:40px">科室：</span>' +
						'<input type="hidden" class="fl tik_select" id="propIds" name="propIds" value="${propIds}"/>' +
						'<input type="hidden" class="fl tik_select" id="propNames" name="propNames" value="${propNames}"/>' +
						'<div class="duouan" id="propNames01"></div>' +'<input type="hidden" value="" id="prop_editId"/>' +
					'</p>' +
					//'<p class="fl" style="width:6em;text-align:center;min-height:40px;">' +
					'<p>' +
						'<span class="fl" style="margin-left:20px;width:6em;text-align:center;min-height:40px;"><font color="red">*</font>人物画像：</span>' +
						'<div class="fl select">' +
						'<div class="tik_position" id="selectimage">' +
						'<b class="ml5" id="image">请选择</b>' +
						'<p class="position01"><i class="bjt10"></i></p>' +
						'</div>' +
						'<ul class="fl list" id="str_user_image" style="max-height:140px; display:none; overflow-x:hidden; overflow-y:scroll;">' +
						
						'</ul>' +
						'</div><div style="margin-left:20px;margin-top:55px;" class = "fl cas_anniu"><a class ="fl" href="javascript:addNameOfStudy()">添加</a></div>'+
					'</p>' +

			'</div></div><input type="hidden" id="userLeixing_id" value= />';
			var str_userImage='';var str_user_image_id='';
			var url = "${ctx}/qualityManage/guide.do";
			$.ajax({
				type:'POST',
				url :url,
				data:'handle=getUpdateData&id='+id,
				dataType:'json',
				success:function(result){
					if(result != '')
					{
						if(result.result[0].userImageList != '')
						{
							for(var j=0;j<result.result[0].userImageList.length;j++){
									var str_image_item = '<em class="delem" onClick="javascript:delem1(this);" value="'+result.result[0].userImageList[j].id+'">'+ result.result[0].userImageList[j].name+'</em><br>';
									str_userImage += str_image_item;
									newarray1.push(str_image_item);
									newarrayId.push(result.result[0].userImageList[j].id);
									str_user_image_id+=result.result[0].userImageList[j].id+',';
							}
							 $('.guideDiv').html(str_userImage);
							 $('#user_image_id').val(str_user_image_id);
						}
					}
				}
			}); 
			
		 		layer.open({
					type: 1,
					title: "修改学习地图",
					skin: 'layui-layer-rim', //加上边框
					area: [win_w, win_h], //宽高
					content: edit_portraits_cont,
					closeBtn: 0,
					btn: ['保存', '取消'],
					yes: function (index, layero) {
					
							var regx = /^[\u4E00-\u9FA5A-Za-z0-9]+$/
					        var value = $('#guideName').val();//人物画像名称

							if($('#guideName').val().trim() == ''){
								alert('请输入学习地图名称！');
								return false;
							}else if($('#guideName').val().trim() .length>20){
								alert("学习地图名称最多20字！");
								return false;
							}else if(!regx.test(value)){
				                alert("学习地图名称只能输入 数字英文和汉字!");
								return false;
							}else if($('#user_image_id').val().trim() == ''){
								alert('请输入人物画像!');
								return ;
							}
							
							var url = "${ctx}/qualityManage/guide.do";
							$.ajax({
								type:'POST',
								url :url,
								data:'handle=update&id='+id+'&imageId='+$('#user_image_id').val()+'&name='+$('#guideName').val(),
								datatype:'text',
								success:function(text){
									if(text == 'success'){
										alert(" 修改成功！");
								 		document.location.href = document.location.href.split("#")[0];
								 	}else if(text == 'error'){
								 		alert(" 名称重复,修改失败！");
								 		return false;
								 	}
								}
								
							});
							newarray1.length=0;
							newarrayId.length = 0;
							idArrayStr = ''; 
					},
					btn2: function (index, layero) {
						newarray1.length=0;
						idArrayStr = '';
						layer.close(index);
					},
					success:function(index, layero){
						select_init();
						$('#propNames01').click(function(){				
							<%--
							initPropList("科室", "${ctx}/propManage/getPropListAjax.do?extType=1", 1, 0, 5, 3, $('#propIds'), $('#propNames01')); 
							--%>
							initPropList("科室", "${ctx}/propManage/getPropListAjax.do?", 1, 0, 5, 3, $('#propIds'), $('#propNames01')); 
							$('.att_make01').show();
							$(".list1,.search_btn").click(function(){
								layer.close(index);
							});
						});
						
						$('#guideName').val(name);
						$('.myList li').click(function(){
							var param='';
							var str=$(this).text();						
							$('#userLeixing_id').val($('#sel_userLeixing').val());
							$(this).parent().parent().find('div').find('b').text(str);
							$(this).parent().find('option').prop('selected', '');
							$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
							$('#userLeixing_id').val($('#sel_userLeixing').val());
							$('.list').slideUp(50);
							var str_user_image = '';var str_user_image_id = '<select id="userImage_id" name="userImage_name" style="display:none;">';
							if($('#propNames01').text() != '')
								param = 'mode=guide_userImage&id='+$('#userLeixing_id').val()+'&propIds='+$('#propIds').val();
							else
								param = 'mode=guide_userImage&id='+$('#userLeixing_id').val();
							
							$.ajax({		    		    
							    type: 'POST',
							    url:"${ctx}/quality/userImageManage.do",
							   	data:param,
							    dataType: 'JSON',
							    success: function(B){	
							    	var str_user_image_temp = '';
							    	if(B.result != '' && B.result.length >0 ){				    	
								    	for(var i=0; i<B.result.length;i++){
								    		
								    		str_user_image_id += '<option value='+B.result[i].id+'>'+B.result[i].name+'</option>';
								    		str_user_image_temp += '<li>'+B.result[i].name+'</li>';
								    	}	
								    	str_user_image = str_user_image_id +'</select>'+str_user_image_temp;
								    				    
								    	$('#str_user_image').html(str_user_image);
								    	select_init();
							    	}
							    	else{
							    		$('#image').text('');
							    		$('#str_user_image').html('<ul></ul>');
							    	}
								}
						    });
						});
						$('.cas_anniu_4 a').click(function(){
							selectProp();
							var param='';				
							$('#userLeixing_id').val($('#sel_userLeixing').val());
							var str_user_image = '';var str_user_image_id = '<select id="userImage_id" name="userImage_name" style="display:none;">';
							if($('#propNames01').text() != '')
								param = 'mode=guide_userImage&id='+$('#userLeixing_id').val()+'&propIds='+$('#propIds').val();
							else
								param = 'mode=guide_userImage&id='+$('#userLeixing_id').val();
							
							$.ajax({		    		    
							    type: 'POST',
							    url:"${ctx}/quality/userImageManage.do",
							   	data:param,
							    dataType: 'JSON',
							    success: function(B){	
							    	var str_user_image_temp = '';
							    	if(B.result != '' && B.result.length >0){				    	
								    	for(var i=0; i<B.result.length;i++){
								    		
								    		str_user_image_id += '<option value='+B.result[i].id+'>'+B.result[i].name+'</option>';
								    		str_user_image_temp += '<li>'+B.result[i].name+'</li>';
								    	}	
								    	str_user_image = str_user_image_id +'</select>'+str_user_image_temp;
								    				    
								    	$('#str_user_image').html(str_user_image);
								    	select_init();
							    	}
							    	else{
							    		$('#image').text('');
							    		$('#str_user_image').html('<ul></ul>');
							    	}							
								}
						    });
							
						});
					}
				});
				 
		    }
	    });
		 
		
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
//选择目录弹出框
		
		
	 
	$('.add_btn').click(function() {
		var option_maker = '<select id="sel_userLeixing" style="display:none;">'+"\n"+'<option value="">请选择</option>', li_maker ='';
		var params = "mode=guide";
		var li_maker_temp = '<li>请选择</li>';		
	   	
		$.ajax({
		    url:"${ctx}/quality/userImageManage.do",
		    type: 'POST',
		   	data:params,
		    dataType: 'JSON',
		    success: function(result){		    	   
			   for(var i=0;i<result.userLeixing.length;i++){
				   option_maker += '<option value='+result.userLeixing[i].type.id+'>'+result.userLeixing[i].type.name+'</option>';
				   li_maker_temp += '<li>'+result.userLeixing[i].type.name+'</li>'+"\n";			   
			   }	
			   li_maker = 	option_maker + '</select>' + li_maker_temp;	  
			 
			   var add_portraits_cont = 
					'<div class="center">'+
					'<div class ="tiaojian center">'+
					'<p class ="fl" style="min-hegiht:40px">'+
					'<span class="fl" style="width:8em;text-align:center"><font color="red">*</font>学习地图名字 : </span>'+
					'<input type="text" name="guideName" id="guideName" class ="fl">'+
					'</p>'+
					'<input type="hidden"  value="" name="user_image" id="user_image_id"/>'+
					'<div class ="clear"></div>'+
					'<div class = "bjs" style = "margin-top:10px;"></div>'+
					'<div class="center">'+
					'<div class="fl guideDiv"></div>' +
					'</div>'+
					'</div>'+
					'<div class ="clear"></div>'+
					'<div class="tiaojian center">' +
					//'<p class="fl" style="text-align:right;min-height:40px;">' +
					'<p>' +
						'<span class="fl" style="margin-left:40px;width:6em;text-align:center;min-height:40px;">人物类型：</span>' +
						'<div class="fl select">' +
							'<div class="tik_position">' +
								'<b class="ml5">请选择</b>' +
								'<p class="position01"><i class="bjt10"></i></p>' +
							'</div>' +
							'<ul class="fl list myList" style="display: none;">' +							
								li_maker +								
							'</ul>' +
						'</div>' +
					'</p>' +
					'<p>' +
						'<span style="margin-left:20px;width:4em;text-align:center;min-height:40px">科室：</span>' +
						'<input type="hidden" class="fl tik_select" id="propIds" name="propIds" value="${propIds}"/>' +
						'<input type="hidden" class="fl tik_select" id="propNames" name="propNames" value="${propNames}"/>' +
						'<div class="duouan" id="propNames01"></div>' +'<input type="hidden" value="" id="prop_editId"/>' +
					'</p>' +
					//'<p class="fl" style="width:6em;text-align:center;min-height:40px;">' +
					'<p>' +
						'<span class="fl" style="margin-left:20px;width:6em;text-align:center;min-height:40px;"><font color="red">*</font>人物画像：</span>' +
						'<div class="fl select">' +
						'<div class="tik_position" id="selectimage">' +
						'<b class="ml5" id="image">请选择</b>' +
						'<p class="position01"><i class="bjt10"></i></p>' +
						'</div>' +
						'<ul class="fl list" id="str_user_image" style="max-height:140px; display:none; overflow-x:hidden; overflow-y:scroll;">' +
						
						'</ul>' +
						'</div>'+
					'</p><div style="clear:both"></div>' +
					'<div style="margin-left:20px;margin-top:55px;" class = "fl cas_anniu"><a class ="fl" href="javascript:addNameOfStudy()">添加</a></div>'+
					'</div></div><input type="hidden" id="userLeixing_id" value= />';
					
					layer.open({
					type: 1,
					title: "添加学习地图",
					skin: 'layui-layer-rim', //加上边框
					area: [win_w, win_h], //宽高
					content: add_portraits_cont,
					closeBtn: 0,
					btn: ['保存', '取消'],
					yes: function (index, layero) {
						var regx = /^[\u4E00-\u9FA5A-Za-z0-9]+$/
				        var value = $('#guideName').val();//人物画像名称

						if($('#guideName').val().trim() == ''){
							alert('请输入学习地图名称！');
							return false;
						}else if($('#guideName').val().trim() .length>20){
							alert("学习地图名称最多20字！");
							return false;
						}else if(!regx.test(value)){
			                alert("学习地图名称只能输入 数字英文和汉字!");
							return false;
						}else if($("#user_image_id").val().trim() == ""){
							alert('请选择人物画像！');
							return false;
						}
						else{
			 				$.ajax({
								type:'post',
								url:'${ctx}/qualityManage/guide.do',
								data:'handle=add&name='+$('#guideName').val()+'&userImage_id='+$('#user_image_id').val()+'&pageNum='+1,
								dataType:'text',
								success:function(result){
									if(result == 'success'){
										alert("添加成功！");
										document.location.href = document.location.href.split("#")[0];									
									}else if(result =='error'){
										alert("名称重复，无法添加！");
										return false;
									}
								}
							});
							newarray1.length=0;
							newarrayId.length=0;
							idArrayStr = '';
						}
					},
					btn2: function (index, layero) {
						layer.close(index);
						newarray1.length=0;
						newarrayId.length=0;
						idArrayStr = '';
					},
					success:function(index, layero){
						select_init();
						$('#propNames01').click(function(){
							<%--
							initPropList("科室", "${ctx}/propManage/getPropListAjax.do?extType=1", 1, 0, 5, 3, $('#propIds'), $('#propNames01')); 
							--%>
							initPropList("科室", "${ctx}/propManage/getPropListAjax.do?", 1, 0, 5, 3, $('#propIds'), $('#propNames01')); 
							$('.att_make01').show();
							$(".list1,.search_btn").click(function(){
								layer.close(index);
							});	
						});
							$('.myList li').click(function(){
							var param='';
							var str=$(this).text();						
							$('#userLeixing_id').val($('#sel_userLeixing').val());
							$(this).parent().parent().find('div').find('b').text(str);
							$(this).parent().find('option').prop('selected', '');
							$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
							$('#userLeixing_id').val($('#sel_userLeixing').val());
							$('.list').slideUp(50);
							var str_user_image = '';var str_user_image_id = '<select id="userImage_id" name="userImage_name" style="display:none;">';
							if($('#propNames01').text() != '')
								param = 'mode=guide_userImage&id='+$('#userLeixing_id').val()+'&propIds='+$('#propIds').val();
							else
								param = 'mode=guide_userImage&id='+$('#userLeixing_id').val();
							
							$.ajax({		    		    
							    type: 'POST',
							    url:"${ctx}/quality/userImageManage.do",
							   	data:param,
							    dataType: 'JSON',
							    success: function(B){	
							    	var str_user_image_temp = '';	
							    	if(B.result != '' && B.result.length >0){				    	
								    	for(var i=0; i<B.result.length;i++){
								    		
								    		str_user_image_id += '<option value='+B.result[i].id+'>'+B.result[i].name+'</option>';
								    		str_user_image_temp += '<li>'+B.result[i].name+'</li>';
								    	}	
								    	str_user_image = str_user_image_id +'</select>'+str_user_image_temp;
								    				    
								    	$('#str_user_image').html(str_user_image);
								    	select_init();
							    	}
							    	else{
							    		$('#image').text('');
							    		$('#str_user_image').html('<ul></ul>');
							    	}
								}
						    });
						});
						$('.cas_anniu_4 a').click(function(){
							selectProp();
							var param='';				
							$('#userLeixing_id').val($('#sel_userLeixing').val());
							var str_user_image = '';var str_user_image_id = '<select id="userImage_id" name="userImage_name" style="display:none;">';
							if($('#propNames01').text() != '')
								param = 'mode=guide_userImage&id='+$('#userLeixing_id').val()+'&propIds='+$('#propIds').val();
							else
								param = 'mode=guide_userImage&id='+$('#userLeixing_id').val();
							
							$.ajax({		    		    
							    type: 'POST',
							    url:"${ctx}/quality/userImageManage.do",
							   	data:param,
							    dataType: 'JSON',
							    success: function(B){	
							    	var str_user_image_temp = '';				    	
							    	if(B.result != '' && B.result.length >0){				    	
								    	for(var i=0; i<B.result.length;i++){
								    		
								    		str_user_image_id += '<option value='+B.result[i].id+'>'+B.result[i].name+'</option>';
								    		str_user_image_temp += '<li>'+B.result[i].name+'</li>';
								    	}	
								    	str_user_image = str_user_image_id +'</select>'+str_user_image_temp;
								    				    
								    	$('#str_user_image').html(str_user_image);
								    	select_init();
							    	}
							    	else{
							    		$('#image').text('');
							    		$('#str_user_image').html('<ul></ul>');
							    	}
							    }
						    });
							
						});						
					}
				});
		    }
	    });
		 
	});
	 
	 
});

 
function addNameOfStudy(){
	var index=0;
     var addstr='';
      var imageStr = '';
     if($("#selectimage").text() != "请选择" && $("#selectimage").text() != '')
     {  
     	 addstr='<em class="delem" onClick="javascript:delem1(this);">'+$('#selectimage').find('b').text()+'</em><br>';
	      if(newarrayId.length==0){
	      
	       	newarray1.push(addstr);
	       	newarrayId.push($('#userImage_id').val());
	       	for (var i=0; i<newarray1.length; i++)
	     		imageStr += newarray1[i].toString();
	     	
	     	
		  	$('.guideDiv').html(imageStr);
		  	idArrayStr = $('#user_image_id').val(); 
		  	idArrayStr+=$('#userImage_id').val()+',';
		 	 $('#user_image_id').val(idArrayStr);
	       }
	      if(newarrayId.length != 0){
	       for (var i=0; i<newarrayId.length; i++)
	       {
	              if(newarrayId[i] == $('#userImage_id').val()) 
	              	index++;   
	        }
	     	if(index == 0){
	     	newarray1.push(addstr);
	    	newarrayId.push($('#userImage_id').val());
	     	for (var i=0; i<newarray1.length; i++)
	     		imageStr += newarray1[i].toString();
	     	
	     	
		  	$('.guideDiv').html(imageStr);
		  	idArrayStr = $('#user_image_id').val(); 
		  	idArrayStr+=$('#userImage_id').val()+',';
		 	 $('#user_image_id').val(idArrayStr);
		 }
     }
     }else{
     	alert('请输入人物画像！');
     }
    
	 
}
 
function delem1(obj){
		var i = $(obj).index() / 2.0;
		//delete text
		var selstr = $(obj).parent().html();
		var selarray = selstr.split("<br>");
		newarray1.length = 0;
		$(selarray).each(function(key, val){
			if (key != i && val != "") 
			{
				newarray1.push(val + "<br>");
			}
		});
		 var imageStr = '';
	     for (var i=0; i<newarray1.length; i++)
	     	imageStr += newarray1[i].toString();
	     	
		  $('.guideDiv').html(imageStr);
		
		//delete code
		var deletecode = "";
		selstr = $('#user_image_id').val();
		selarray = selstr.split(",");
		newarray = new Array();
		$(selarray).each(function(key, val){
			if (key != i) newarray.push(val);
			else
				deletecode = val;
		});
		$('#user_image_id').val(newarray.toString());

		//delete check
		var checklist = $('.attr_xueke04');
		$(checklist).each(function(key, val){
			if(deletecode == $(val).prop('id'))
				$(val).siblings().eq(0).prop("checked", "");
		});

		$('.delem').off('click');
		$('.delem').click(function(){
			delem($(this));
		});
}
function del(id){
	if(!confirm("您确定要删除吗?")) return;
	$.ajax({
		type:'post',
		url:'${ctx}/qualityManage/guide.do',
		data:'handle=delete&id='+id,
		dataType:'text',
		success:function(result){
			if(result == 'success'){
				alert("删除成功！");
				document.location.href = document.location.href.split("#")[0];
			}
			else {
				alert("无法删除！该学习地图包含下一级内容！")
			}
		}
	});
}



</script>
</body>
</html>
