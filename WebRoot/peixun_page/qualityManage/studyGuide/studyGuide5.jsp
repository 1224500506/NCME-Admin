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
			<a href="javascript:void(0);" class="fl add_btn mr20" style="width:140px;">添加四级</a>			
				<a href="###" class="fl" style="width:70px;" onclick="goFiveLevelBefore('${param.beforeParentId}','${qualityId}','${beforeParentName}','${param.beforeQualityId}');">返回</a>
			</div>
		<div class="clear"></div>
	</div>
</div>
<div class="clear"></div>
<div class="bjs"></div>

<!-- 内容 -->
<div class="center" style="">
	<input type="hidden" id="stv" name="stv" value=""/>
	<div class="tabs_main">
		<ul class="tab">
			<li id="tab1" class="active">学习地图</li>
			<li id="tab2">ICD10</li>
		</ul>
		<div class="tab1 cont">
			<div class="center01">
				<form id="fm" action="${ctx}/qualityManage/guide.do" method="POST">

					<input id="pageNum" name="pageNum" type="hidden" value="5">
					<input id="parentId" name="parentId" type="hidden" value="${parentId}">
					<input id="qualityId" name="qualityId" type="hidden" value="${qualityId}">
					<input id="parentName" name="parentName" type="hidden" value="${parentName}">
					<div class="tiaojian" style="min-height:40px;">
					<p class="fl" style="margin-right:20px;">
						<input type="text" placeholder="输入学习地图四级名称"  name="name" value="${name}"/>
					</p>
					<div class="fl cas_anniu">
						<a href="javascript:$(fm).submit();" class="fl" style="width:70px;">查询</a>
					</div>
					<div class="clear"></div>
				</div>
				</form>
				<display:table name="list" requestURI="${ctx}/qualityManage/guide.do" id ="p" class="mt10 table" excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}">
					
					<display:column title = "序号" style = "width:5%;" >${p_rowNum}</display:column>
						<display:column title="学习地图四级"  style="width:35%;">${p.name}</display:column>	
						<display:column title="ICD10"  style="width:35%; text-align:left">							
							<c:forEach items="${icdPropList}" var="item">		
					    		<br/><input type="checkbox" name="checkbox" id="${p.id}_${item.id}" style="margin-left:20px;"/>${item.name}<br/>
					    		<c:forEach items="${p.icdPropList}" var="it">
					    			<c:if test="${item.id == it.id}"><script>$('#${p.id}_${item.id}').attr("checked", "checked");</script></c:if>
					    		</c:forEach>
					  	 	</c:forEach>
					  	 	
						</display:column>	
						<display:column title = "操作" style ="width:25%;">
							<a href="javascript:updateGuide(${p.id}, '${p.name}');" class="edit_btn">修改</a>
							<a href="javascript:delGuide(${p.id});" class="">删除</a>
						</display:column>
					
					</display:table>
				<div class="clear"></div>

			</div>
		</div>
		<div class="tab2" style="display:none;">
			<div class="center01">
				<form id="fm1" action="${ctx}/qualityManage/guide.do?pageNum=5&search_ctr=icd&parentId=${parentId}&qualityId=${qualityId}&parentName=${parentName}" method="POST">
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
				<display:table name="new_list" requestURI="${ctx}/qualityManage/guide.do?stv=tab2" id ="p" class="mt10 table" pagesize="15" size="${totalSize}" decorator="com.hys.exam.displaytag.StudyGuideTableDecorator" >
									
					<display:column title = "序号" style = "width:5%;">${p.icdPropList[0].id}</display:column>
					<display:column title="ICD10"  style="width:35%;">${p.icdPropList[0].name}</display:column>	
					<display:column title="学习地图四级"  style="width:35%;" property="name"></display:column>	
					<display:column title = "操作" style ="width:25%;">						
						<a href="javascript:updateGuide(${p.id}, '${p.name}');" class="edit_portraits_btn">修改</a>
						<a href="javascript:updateGuideIcdProp(${p.id}, ${p.icdPropList[0].id});" class="">删除</a>															    				
					</display:column>
				
				</display:table> 
				<div class="clear"></div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
function goFiveLevelBefore(parentId,qualityId,parentName,beforeQualityId) {
   var pAllName = parentName  ;
   pAllName = encodeURI(pAllName) ;
   if(parentId==''||parentId==null){
	   var aa = localStorage.getItem("ccc");
	   window.location.href = "${ctx}/qualityManage/guide.do?pageNum=4&parentId=" + aa + "&qualityId=" + qualityId + "&beforeQualityId=" + beforeQualityId + "&parentName=" + pAllName ; 
   }else{
	   window.location.href = "${ctx}/qualityManage/guide.do?pageNum=4&parentId=" + parentId + "&qualityId=" + qualityId + "&beforeQualityId=" + beforeQualityId + "&parentName=" + pAllName ;
   }
}

	var win_w = parseInt($(window).width() / 3) + "px";
	var win_h = parseInt($(window).height() / 3) + "px";
	
	var edit_cont = '' +
			'<div class="center">' +
			'<div class="tiaojian clear" style="min-height:40px;">' +
			'<p class="clear" style="margin-bottom:10px;">' +
			'<span class="fl" style="width:8em;text-align:right;display:inline-block;">学习地图四级：</span>' +
			'<input type="text" value="临床电生理性检查" name="edit_name" id="edit_name"/>' +
			'</p>' +
			'</div>' +
			'</div>';

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
            var c_name = $(this).attr("id");
            var c_value = $(this).html();
            $("#stv").val(c_name);
            $(".tabs_main div." + c_name).show().siblings("div").hide();
            $(this).addClass("active").siblings().removeClass("active");
		});
	});
    //---------------------------
    $("#stv").val("<%=request.getParameter("stv")%>");
    if($("#stv").val()=='tab2'){
        var c_name="tab2";
        $(".tabs_main div." + c_name).show().siblings("div").hide();
        $(".tabs_main div." + c_name).addClass("active").siblings().removeClass("active");
        $(".tabs_main ul.tab li:first").removeClass("active");
        $(".tabs_main ul.tab li:last").addClass("active");

    }
    //---------------------------
		if ('${search_ctr}' != '') {
		$(".tabs_main ul.tab li:first").next().addClass("active").siblings().removeClass("active");
		var c_name = $(".tabs_main ul.tab li:first").next().attr("id");
		$(".tabs_main div." + c_name).show().siblings("div").hide();
	}
//选择目录弹出框
		
		
	$("input[name='checkbox']").click(function(){
		
		var word =  $(this).prop('id');
		
		var url = "${ctx}/qualityManage/guide.do";
		var params = "handle=icdUpdate&word=" + word;	
 		if ($(this).prop('checked') == true) 	params += "&method=add";
		else									params += "&method=delete";
					
					
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
					if ($('#'+word+'').prop('checked') == true)
						$('#'+word+'').removeAttr('checked');												
					else 
						$('#'+word+'').attr('checked', 'checked');
				}				
			}

		}); 
				
	});
	
	var str;
	
	$(".add_btn").click(function() {
		/*  var url1 = "${ctx}/qualityManage/guide.do";
		var params1 = "handle=getToAdd&pageNum=5&parentId=${parentId}&name="+$('#add_name').val();
		$.ajax({
			url		:url1,
			data	:params1,
			type	:'post',
			dataType:'json',
			success:function(result){
				alert(result.result[0].name);
			}
		});	 */
		var str_icd='';
	  	var url = '${ctx}/qualityManage/guide.do';
		var params = 'handle=getICD&parentId=${parentId}';
		$.ajax({
			url: url,
			type: 'POST',
			data: params,
			dataType: 'json',
			success:function(result){
				for(var i=0;i<result.result.length;i++)
				{
					str_icd += '<li><span class="checkbox"><input name="ICD_name1" type="checkbox" id=' +result.result[i].id+' value="'+result.result[i].name+'"/></span><span class="username">'+result.result[i].name+'</span></li>';
				}
				var add_cont = '' +
					'<div class="center">' +
						'<div class="tiaojian clear" style="min-height:40px;">' +
							'<p class="clear" style="margin-bottom:10px;">' +
								'<span class="fl" style="width:8em;text-align:right;display:inline-block;">学习地图四级：</span>' +
								'<input type="text" name="add_name" id="add_name"/>' +
								'<input type="hidden" id="icdId" value="" name="ICDID" />'+
							'</p>' +
						'</div>' +
						'<div class="tiaojian clear" style = "min-height:40px">'+
							'<p class="clear" style="margin-bottom:10px">'+
								'<span clss="fl" sytle="width:8em;text-align:right;display:inline-block;">三级ICD10</span>'+
								'<ul class="tabs">'+
									str_icd+
								'</ul>'+
							'</p>'+
						'</div>'+
					'</div>';
				layer.open({
					type: 1,
					title: "添加学习地图四级",
					skin: 'layui-layer-rim', //加上边框
					area: [win_w, win_h], //宽高
					content: add_cont,
					closeBtn: 0,
					btn: ['保存', '取消'],
					yes: function (index, layero) {
						
						var url = "${ctx}/qualityManage/guide.do";
						var params = "handle=add&pageNum=5&parentId="+${parentId}+"&name=" + $('#add_name').val()+"&icdName="+$('#icdId').val();
                        var regx = /^[\u4E00-\u9FA5A-Za-z0-9]+$/;
                        var value = $('#add_name').val();
						if($('#add_name').val() == ''){	
							alert("请输入学科地图四级名称！");
							$('#add_name').focus();
							return false;
						}else if(!regx.test(value)){
                            alert("学习地图名称只能输入 数字英文和汉字!");
                            return false;
                        }if($('#icdId').val() == ''){
							alert("请输入ICD10！");	
							return false;
						}else{
							$.ajax({
								url: url,
								type: 'POST',
								data: params,
								dataType: 'text',
								success: function(result){
									if(result.resutl!=null){
										
									}
									 if (result == "success"){
										alert('添加学习地图四级成功！');
										document.location.href = document.location.href.split("#")[0];
									}
									else{
										alert ("由于网络问题或者是名称重复的原因无法添加！");
										return false;
									} 
								}
							});
							
						}
					},
					btn2: function (index, layero) {
						layer.close(index);
					},
					success:function(index, layero){
						$("input[name=ICD_name1]").click(function(){
							var str = '';
							var ids='';
							$(".tabs input[name=ICD_name1]:checked").each(function(){
								//str+=$(this).val()+",";
								ids += $(this).prop("id")+",";
								
							});
							
							//$(".xs_kuang").val(str);
							$("#icdId").val(ids);
							
						});
						$(".btn1").click(function(){
							/* var t1_str = $(".username").val();
							$(".xs_kuang").val(t1_str); */
							layer.close(index);
						});
					}
				});
			}
		});
	});
			
	$(".edit_btn").click(function() {
		/* layer.open({
			type: 1,
			title: "修改学习地图四级",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, win_h], //宽高
			content: edit_cont,
			closeBtn: 0,
			btn: ['保存', '取消'],
			yes: function (index, layero) {
				//编写保存功能
				layer.close(index);
			},
			btn2: function (index, layero) {
				layer.close(index);
			},
			success:function(index, layero){

			}
		}); */
	});
});


function delGuide(id) {
	var url = "${ctx}/qualityManage/guide.do";
	var params = "handle=delete&id=" + id;
	
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

function updateGuide(id, name) {
	
	layer.open({
		type: 1,
		title: "编辑学习地图四级",
		skin: 'layui-layer-rim', //加上边框
		area: [win_w, win_h], //宽高
		content: edit_cont,
		closeBtn: 0,
		btn: ['保存', '取消'],
		yes: function (index, layero) {
            var regx = /^[\u4E00-\u9FA5A-Za-z0-9]+$/;
            var value = $('#edit_name').val();
           if(!regx.test(value)){
                alert("学习地图名称只能输入 数字英文和汉字!");
                return false;
            }
			var url = "${ctx}/qualityManage/guide.do";
			var params = "handle=update&id=" + id;
			params += "&name=" + $('#edit_name').val();
			
			$.ajax({
				url: url,
				type: 'POST',
				data: params,
				dataType: 'text',
				success: function(result){
					if (result == "success"){
						alert('修改成功！');
						document.location.href = document.location.href.split("#")[0];
					}
					else{
						alert ("由于网络问题或者是名称重复的原因无法修改！");
					}
				}
			});
			layer.close(index);
		},
		btn2: function (index, layero) {
			layer.close(index);
		},
		success:function(index, layero){

		}
	});
	$('#edit_name').val(name);
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
</script>
</body>
</html>
