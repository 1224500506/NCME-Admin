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
	<form id = "level2_form" action="${ctx}/qualityManage/qualityManage.do" method="post">
	    <input id="pageNum" name="pageNum" type="hidden" value="2">
	    <input id="typeID" name="typeID" type="hidden" value="${typeID}">
	    <input id="captionName" name="captionName" type="hidden" value="${captionName}">
		<p class="fl" style="margin-right:20px;">
			<span>能力模型一级：</span>
			<input type="text" name="sname" value="${sname}"/>
		</p>
	</form>
		<div class="fl cas_anniu">
			<a href="javascript:$(level2_form).submit();" class="fl" style="width:70px;">查询</a>
		</div>
		<div class="fr cas_anniu">
			<a href="javascript:void(0);" class="fl add_btn mr20" style="width:140px;">添加一级</a>
			<a href="${ctx}/qualityManage/qualityManage.do?method=list" class="fl" style="width:70px;">返回</a>
		</div>
		<div class="clear"></div>
	</div>
</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
		<display:table name="QM_list" requestURI="${ctx}/qualityManage/qualityManage.do" id="p" class="mt10 table" excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}" >
		<display:caption>${captionName}</display:caption>
				<display:column title="序号" style="width:10%;">${p_rowNum}</display:column>
				<display:column title="能力模型一级"  style="width:70%;">${p.name}</display:column>				
				<display:column title="操作" style="width:20%;">
					<a href="javascript:update('${p.id}','${p.name}');" class="edit_btn">修改</a> 
					<a href="javascript:doDelProp(${p.id});">删除</a>					
					<a href="###" onclick="goTwoLevel('${p.id}','${typeID}','${captionName}','${p.name}');">二级</a>				 
				</display:column>
		</display:table>
		<div class="clear"></div> 		
	</div>
</div>

<script type="text/javascript">
var oldName = "" ;
function goTwoLevel(pId,backId,captionName,pName) {
   var pAllName = captionName + "> " + pName ;
   pAllName = encodeURI(pAllName) ;
   window.location.href = "${ctx}/qualityManage/qualityManage.do?typeID=" + pId + "&pageNum=3&backId=" + backId + "&captionName=" + pAllName ;
}

function doDelProp(del_id) {

	if(!confirm("您确定要删除吗?")) return;	
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
	  		alert('删除失败!请查看所属能力模型二级！');
	  },
	  error:function(){
	  	alert('删除失败!');
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
		$('.duouan').click(function(){
			$('.att_make01').show();
		});
		$('.bjt_kt,.cas_anniu_4').click(function(){
			$('.att_make01').hide();
			$('.att_make02').hide();
			$('.att_make03').hide();
			$('.att_make04').hide();
		});
		//二级
		$('.attr_xueke').click(function(){
			$('.att_make01').hide();
			$('.att_make02').show();
		});
		$('.attri01').click(function(){
			$('.att_make02').hide();
			$('.att_make01').show();
			$('.att_make03').hide();
			$('.att_make04').hide();
			$('.att_make05').hide();
		});
		//三级
		$('.attr_xueke02').click(function(){
			$('.att_make02').hide();
			$('.att_make03').show();
		});
		$('.attri02').click(function(){
			$('.att_make02').show();
			$('.att_make03').hide();
			$('.att_make04').hide();
			$('.att_make05').hide();
		});
		//调整
		$('.tiaozheng').click(function(){
			$('.make02').show();
		});
		$('.queren').click(function(){
			$('.make02').hide();
		});
	var win_w = parseInt($(window).width() / 3) + "px";
	var win_h = parseInt($(window).height() / 4) + "px";
	/* var add_cont = '' +
			'<div class="center">' +
				'<div class="tiaojian clear" style="min-height:40px;">' +
					'<p class="">' +
						'<span class="fl" style="width:8em;text-align:right;">能力模型一级：</span>' +
						'<div class="fl select">' +
						'<div class="tik_position">' +
						'<b class="ml5">请选择能力模型一级</b>' +
						'<p class="position01"><i class="bjt10"></i></p>' +
						'</div>' +
						'<ul class="fl list" style="display:none;">' +
						'<li>医学知识</li>' +
						'<li>专业技能</li>' +
						'<li>职业素质</li>' +
						'<li>自我发展</li>' +
						'</ul>' +
						'</div>' +
					'</p>' +
				'</div>' +
			'</div>'; */
	var add_cont = '' +
			'<div class="center">' +
			'<div class="tiaojian clear" style="min-height:40px;overflow:auto;">' +
			'<p class="clear" style="margin-bottom:10px;">' +
			'<span class="fl" style="width:8em;text-align:right;display:inline-block;"><font color = "red">*</font>能力模型一级：</span>' +
			'<input type="text" id="nengli_add" style="width:300px;"/>' +
			'</p>' +
			'</div>' +
			'</div>';
	var edit_cont = '' +
			'<div class="center">' +
			'<div class="tiaojian clear" style="min-height:40px;">' +
			'<p class="clear" style="margin-bottom:10px;">' +
			'<span class="fl" style="width:8em;text-align:right;display:inline-block;"><font color = "red">*</font>能力模型一级：</span>' +
			'<input type="text" value="" id="nengli_edit" />' +'<input type="Hidden" value="" id="edit_id" />最多20字(只能输入 数字英文和汉字)'+
			'</p>' +
			'</div>' +
			'</div>';
	/* var edit_cont = '' +
			'<div class="center">' +
			'<div class="tiaojian clear" style="min-height:40px;">' +
			'<p class="">' +
			'<span class="fl" style="width:8em;text-align:right;">能力模型一级：</span>' +
			'<div class="fl select">' +
			'<div class="tik_position">' +
			'<b class="ml5">医学知识</b>' +
			'<p class="position01"><i class="bjt10"></i></p>' +
			'</div>' +
			'<ul class="fl list" style="display:none;">' +
			'<li>专业技能</li>' +
			'<li>职业素质</li>' +
			'<li>自我发展</li>' +
			'</ul>' +
			'</div>' +
			'</p>' +
			'</div>' +
			'</div>'; */
	$(".add_btn").click(function() {
		layer.open({
			type: 1,
			title: "添加能力模型一级",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w], //宽高
			content: add_cont,
			closeBtn: 0,
			btn: ['保存', '取消'],
			yes: function (index, layero) {		
				if($("#nengli_add").val().trim() == ""){
					alert("请输入能力模型一级名称！");
					return ;
					
				}
				var regx = /^[\u4E00-\u9FA5A-Za-z0-9]+$/
	            var value = $('#nengli_add').val();//人物画像名称
	            if(!regx.test(value))
	            {
	                alert("能力模型一级名称只能输入 数字英文和汉字!");
	                return false;
	            }
	            if($("#nengli_add").val().trim().length>20){
					alert("能力模型一级名称最多20字！");
					return ;
					
				}
				//编写保存功能
				var url = "${ctx}/qualityManage/qualityManage.do";
				params = 'mode=add&name='+$('#nengli_add').val()+'&id='+'${typeID}';
				$.ajax({
					type: 'POST',
					url: url,
					data : params,
					dataType: 'text',
					success:function(b){
					 	if(b == 'success'){
					 		alert('添加成功！');
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
					 	return false;
					 }
				});
				
			},
			btn2: function (index, layero) {
				layer.close(index);
			},
			success:function(index, layero){
				$('.select').click(function(){
					$('.list').css('display","none');
					$(this).find('ul').show();
				});
				$('.list li').click(function(){
					var str=$(this).text();
					$(this).parent().parent().find('div').find('b').text(str);
					$('.list').slideUp(50);
				});
				$('.select').click(function(e){
					return false;
				});
				$(document).click(function(){
					$('.list').hide('fast');
				});
			}
		});
	});
	$(".edit_btn").click(function() {
		layer.open({
			type: 1,
			title: "修改能力模型一级",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w], //宽高
			content: edit_cont,
			closeBtn: 0,
			btn: ['保存', '取消'],
			yes: function (index, layero) {
				//编写保存功能		
				var newName = $('#nengli_edit').val() ;
				if(newName == ''){
					alert("请输入能力模型一级！");
					return ;		
				}
				var regx = /^[\u4E00-\u9FA5A-Za-z0-9]+$/
	            var value = $('#nengli_edit').val();//人物画像名称
	            if(!regx.test(value))
	            {
	                alert("能力模型一级只能输入 数字英文和汉字!");
	                return false;
	            }
	            if($("#nengli_edit").val().trim().length>20){
					alert("能力模型一级名称最多20字！");
					return ;
					
				}
				
				url= "${ctx}/qualityManage/qualityManage.do";
				params = 'mode=edit&id='+$('#edit_id').val()+'&name='+$('#nengli_edit').val() + '&typeid=' + ${typeID} + "&oldName=" + oldName ;
				$.ajax({
					type: 'POST',
					url: url,
					data : params,
					dataType: 'text',
					success:function(b){
					 	if(b == 'success'){
					 		alert('修改成功！');
                            history.go(0);
//                            document.location.href = document.location.href.split("#")[0];
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
				
			},
			btn2: function (index, layero) {
				layer.close(index); 
			},
			success:function(index, layero){
				$('.select').click(function(){
					$('.list').css('display","none');
					$(this).find('ul').show();
				});
				$('.list li').click(function(){
					var str=$(this).text();
					$(this).parent().parent().find('div').find('b').text(str);
					$('.list').slideUp(50);
				});
				$('.select').click(function(e){
					return false;
				});
				$(document).click(function(){
					$('.list').hide('fast');
				});
			}
		});
	});
});
function update(id_, name_){
    oldName = name_ ;
	$('#edit_id').val(id_);
	$('#nengli_edit').val(name_);	

}
</script>
</body>
</html>