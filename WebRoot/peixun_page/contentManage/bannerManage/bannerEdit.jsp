<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>

<%@include file="/commons/taglibs.jsp"%>


<script type="text/javascript" src="${ctx}/peixun_page/js/utils.js"></script>
        <script type="text/javascript" src="${ctx}/js/jedate/jedate.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/fileUpload.js"></script>
</head>
<%-- <%@include file="/peixun_page/userManage/top_sentence.jsp"%> --%>
<%@include file="/peixun_page/top_banner.jsp"%>
    
<body>
<!-- 查询条件 -->
 
<div class="center">
	<h2 class="main_title">
<%-- 		<c:if test="${model.id==null ||advert.id==0}">添加Banner</c:if> --%>
		<c:if test="${model.id>0}">编辑Banner</c:if>
	</h2>
</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="form_cont">											
	<form name="bannerForm" id="frm_add"
	 action="${ctx}/contentManage/bannerManage.do?method=save" method="post" enctype="multipart/form-data">
		<input type="hidden" id="nameId" name="id" value="<%=request.getAttribute("id")%>">
		<div class="input_div">							
			<label><em style="color:red">*</em>banner分类</label>
			<div class="f1 select">
		    <div class=""  style="margin:0 20px 0 0">
			<div class="tik_position">
						<b class="ml5">全部</b>
						<p class="position01"><i class="bjt10"></i></p>
					</div>
					<ul class="fl list" style="display:none;" >
		 			 	<select class="fl select" id="reType" style="display:none;" name="model.type" value="<%=request.getAttribute("type")%>" >
					 	
					 		<option value='1'<c:if test="${type == 1}">selected</c:if>>网页端</option>
							<option value='2'<c:if test="${type == 2}">selected</c:if>>移动端</option>
							
					 	</select>	
						 <li>网页端</li>
						 <li>移动端</li>
					</ul>
				</div>
	 		</div>
		 </div>
		<div class="input_div">
			<label class="fl" style="width:6em;text-align:right;margin-top:6px;"><em style="color:red">*</em>授权站点：</label>
			<ul class="">
				<c:forEach items="${siteList}" var="site">
					<li><input class="site_check" name="site" type="checkbox" value="${site.id}"/>${site.domainName}</li>
				</c:forEach>	
			</ul>
			<c:forEach items="${checkSite}" var="chk">				
				<input name="cchhkk" type="hidden" value="${chk.id}"/>
			</c:forEach>
		</div>
		<div class="input_div">
			<label><em style="color:red">*</em>banner标题</label>
			<input type="text" size="40" id="reTitle" name="model.name" value="<%=request.getAttribute("name")%>"/>
		</div>
		
		
		<!-- banner -->
		 <div class="input_div">
             <label><em style="color:red">*</em>banner图片</label>
                  <div class=" fileinput fileinput-new" data-provides="fileinput">
                      <div class="avatar fileinput-preview fileinput-exists thumbnail">
                      <div id="localImag">
                           	<c:if test="${model.url !=  null}">
			               		<img src="${model.url}" onerror="imgonload(this,'${url}');" id="Image"  name="model.url" width="800px" height="208px">
			               	</c:if>
			            </div>
			           </div>
			           <input onchange="previewFile()" type="file"  id="selImage" name="matFile" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
	               		<input type="hidden" id="cover" name="model.cover" value="<%=request.getAttribute("cover")%>">
	               	</div>
         </div>
		
		<div class="input_div">
			<label>链接地址：</label>
			<input type="text" id="reSource" size="40" name="model.target_url" value="<%=request.getAttribute("target_url")%>"/>
		</div>
		<div class="input_div">
			<label>banner内容</label>
			
			 <script type="text/plain" id="editor" name="model.remark"><%=request.getAttribute("remark")%> </script>
			
		</div>
		<div class="clear"></div> 
		<p class="fl cas_anniu">
			<a href="javascript:save();" class="fr" style="width:140px;margin:10px 20px 0 0;">确认提交</a>
		</p>
		<p class="fl cas_anniu">
			<span style="width:5em;">&nbsp;</span>
			<a href="${ctx}/contentManage/bannerManage.do?method=list" class="fl" style="width:70px;margin-top:10px;">返回</a>
		</p>
		</form>

</div>
<div id="container"></div>

<script type="text/javascript">
	
$(function(){
//下拉框
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "lc_left0" + menuindex;
		$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
			
		var idstr="";
		$('input[name="cchhkk"]').each(function(){
				idstr+=$(this).val()+",";
		});
		var ids = idstr.split(",");
		$('input[name="site"]').each(function(){
			for(var i=0; i<ids.length; i++){
				//alert($(this).val());
				if(ids[i] == $(this).val())
					$(this).prop('checked', true);
			}
		});
		
	selectInit();

	window.ue = UE.getEditor('editor');
	
	/*IE9.0、IE10.0下,编辑器输入内容过多时,下方的 "确认提交"及"返回" 按钮会被遮挡住
	  $("#editor").css({"height":"500px"}); 
	*/
	
/*  	var ids = idstr.split(",");
	$('input[name="site"]').each(function(){
		for(var i=0; i<ids.length; i++){
			//alert($(this).val());
			if(ids[i] == $(this).val())
				$(this).prop('checked', true);
		}
	}); */
}); 
function selectInit(){
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
		var optionsValue=$("#reType option:selected").val();
		
		if(optionsValue=='3'){
			$('#province').css("display","block");//province的display属性设置为block（显示）
		}else {
			$('#province').css("display","none");//province的display属性设置为none（隐藏）
		}
		
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

function save() {
	var reType = $('#reType').val();
	if ($.trim(reType) == ""){
		alert ("请选择banner分类！");
		return;
	}
	var reTitle = $('#reTitle').val();
	if ($.trim(reTitle) == ""){
		alert ("请输入banner标题！");
		return;
	}
	if($("#reTitle").val().length>100){   //长度可自定义
        alert("超出了标题的最大长度，请控制标题数为50字");
        $("#reTitle").focus();
		return;
    }else{
        //未超出……
    }

	if($("#selImage").val()!=""){
		if (!fileUploadValid("selImage",2)) {
			return ;
		}
	$("#frm_add").ajaxSubmit({
		type:'post',
		url:'${ctx}/file/fileUpload.do',
// 		url:'${ctx}/contentManage/bannerManage.do?method=ajaxImage',
		success:function(data){
			var v= JSON.parse(data).message;
			if(v!="Fail"){
				$('#cover').val(v);
				$.ajax({
					url :"${ctx}/contentManage/bannerManage.do?method=save",
					data:$("#frm_add").serialize(),
					type:'post',
					dataType:'text',
					success:function(result){
					if(result != 'fail'){
						alert('保存成功');
						document.location.href = "${ctx}/contentManage/bannerManage.do?method=list";
						 return true;
					   }else{
					   		alert('banner标题重复，请重新命名');
					   		doucment.getElementById("reTitle").value = "请重新输入标题";
					   		return false;  
					   }
					},
					error:function(){
						alert('网络不好，保存失败！');
						return false;
					}
				})
			}
			else {
				alert('图片上传失败');
				return false;
			}
			
		},
		error:function(){
			alert('图片上传失败！');
		}
	});
	}else{
// 		$("#frm_add").submit();
// 		document.location.href = "${ctx}/contentManage/bannerManage.do?method=list";
			$.ajax({
					url :"${ctx}/contentManage/bannerManage.do?method=save",
					data:$("#frm_add").serialize(),
					type:'post',
					dataType:'text',
					success:function(result){
					if(result != 'fail'){
						alert('保存成功');
						document.location.href = "${ctx}/contentManage/bannerManage.do?method=list";
						 return true;
					   }else{
					   		alert('banner标题重复，请重新命名');
					   		doucment.getElementById("reTitle").value = "请重新输入标题";
					   		return false;  
					   }
					},
					error:function(){
						alert('网络不好，保存失败！');
						return false;
					}
				});
	}
	
}	
//预览相片
function previewFile() {
	 var docObj = document.getElementById("selImage");
     var imgObjPreview = document.getElementById("Image");
     if(docObj.files && docObj.files[0])
     {
         //火狐下，直接设img属性
         /* imgObjPreview.style.display = 'block';
         imgObjPreview.style.width = '150px';
         imgObjPreview.style.height = '180px'; */
         //imgObjPreview.src = docObj.files[0].getAsDataURL();
         //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
         imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
     }
     else
     {
         //IE下，使用滤镜
         docObj.select();
         var imgSrc = document.selection.createRange().text;
         var localImagId = document.getElementById("localImag"); //必须设置初始大小
//          localImagId.style.width = "150px";
//          localImagId.style.height = "180px"; //图片异常的捕捉，防止用户修改后缀来伪造图片
         try {
             localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
             localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
         } catch(e) {
             alert("您上传的图片格式不正确，请重新选择!");
             return false;
         }
         imgObjPreview.style.display = 'none';
         document.selection.empty();
     }
     return true;
 }
</script>
</body>
</html> 