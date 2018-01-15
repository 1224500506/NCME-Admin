<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<%@include file="/commons/taglibs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
</head>
<script type="text/javascript">
 
	function m_over(tr){  
	    tr.className=tr.className + " " + "trmo";  
	}  
	function m_out(tr){  
	    var cn = tr.className.replace(/\s+trmo/,'');  
	    tr.className = cn;  
	}  
		
		
function del(id){
   if(confirm('确认删除吗?')){
	   var url = '${ctx}/system/systemSite.do?method=delete';
	   var params = 'selId=' + id;
	   $.ajax({
				type: 'POST',
				url: url,
				data : params,
				dataType: 'text',
				success : function (b){
				      if (b == 'success'){
				      	alert('删除成功！');
				      	document.location.href = document.location.href.split("#")[0];
				      }	
				      else
				      	alert('删除失败.请检查属性的关联！');
				},
				error: function(){
				   	  alert('删除失败.请检查属性的关联！');
				}
			});
   }
}
function doResult(originalRequest){
   var result = originalRequest.responseText;
   if(result == '1'){
   	 alert('操作成功！');
      window.location = window.location;
   }else{
      alert('操作失败！');
   }
}

function hadChk(chkName) {
	/* var names = document.getElementsByName(chkName);
	for (var i = 0, len = names.length; i < len; i++) {
		if (names[i].checked) {
			return true;
		}
	}
	return false; */
	//2017/01/11, Change by lee
	var isChecked = "";
	$("input:checked").each(function(){
		if($(this).prop("checked"))
			isChecked += $(this).val() +",";
	});
	return isChecked;
}
//[批量删除] 
function delBatch() {
	var url = '${ctx}/system/systemSite.do';
	var params = 'method=delete&selId=';
	if (hadChk('selId') == ""){
		alert("至少选择一项！");
		return;
	}
	params+=hadChk().slice(0, -1);
	
	if(confirm('确认删除吗?')){
	/* 
		document.getElementById('queryForm').action = url;
		document.getElementById('queryForm').submit(); */
	
		$.ajax({
			type: 'POST',
			url: url,
			data : params,
			dataType: 'text',
			success : function (b){
			      if (b == 'success'){
			      	alert('删除成功！');
			      	document.location.href = document.location.href.split("#")[0];
			      }	
			      else {
			      	alert('此站点已被关联，无法删除！');
			      	document.location.href = document.location.href.split("#")[0];
			      }
			},
			error: function(){
			   	  alert('系统出错，无法删除！');
			   	  document.location.href = document.location.href.split("#")[0];
			}
		});
		
	
		/* var doAjax = new Ajax.Request(
		   url,
		   {
		      method : 'POST',
		      parameters : params,
		      onComplete : doResult
		   }
		   ); */
	}		
}
//[新增] 
function add() {
		document.getElementById('method').value = 'add';
		document.forms[0].submit();
}
//[查询] 
function query() {
		document.getElementById('queryForm').value = 'list';
		document.getElementById('queryForm').submit();
}
//[修改] 
function edit(id) {
		document.location.href = "${ctx}/system/systemSite.do?method=edit&id="+id;
}

function exam(id){
        document.location.href = "${ctx}/system/systemSite.do?method=exam&id="+id;
}

</script>
</head>
<%@include file="/peixun_page/top.jsp"%>

<body>
	<div class="none" style="">
	<form name="queryForm" action="${ctx}/system/systemSite.do"	method="post" id="queryForm">
		<div class="center">
				<input type="hidden" id="method" name="method" value="list" />
				<div class="tiaojian" style="min-height:40px;">
					<p class="fl">
						<span>域名：</span> <input type="text" id="domainName" name="domainName" value="${item.domainName}"/>
					</p>
					<p class="fl jianju">
						<span>别名：</span> <input type="text" id="aliasName" name="aliasName" value="${item.aliasName}"/>
					</p>
					<div class="fl cas_anniu">
						<a href="javascript:query();" class="fl"
							style="width:70px;margin-left:70px;">查询</a>
					</div>
				</div>
		</div>
		<div class="bjs"></div>
		<div class="center none" style="">
			<div class="center01">

				<div class="mt20 kit1_tiaojia">
					<div class="fr cas_anniu">
						<a href="#" class="fl xuexike" style="width:80px;">新增</a>
						<a href="javascript:delBatch();" class="fl" style="width:80px;margin-left:10px;">删除</a>
					</div>
				</div>
				<div class="clear"></div>
				<display:table requestURI="${ctx}/system/systemSite.do?method=list"
					id="systemSite" cellpadding="0" cellspacing="0" partialList="true"
					excludedParams="method" size="${page.count}"
					pagesize="${page.pageSize}" name="page.list" list="${page.list}"
					style="font-size:12px;" class="mt10 table" keepStatus="true"
					decorator="com.hys.exam.displaytag.TableOverOutWrapper">
					<display:column title="<input type='checkbox' class='chkall'/>全选" style="text-align:center;width:10%"><input type='checkbox' class='chk' value="${systemSite.id}"/></display:column>

					<display:column title="序号" style="text-align:center;width:10%;">
						${systemSite_rowNum}
						</display:column>
					<display:column property="id" title="ID"
						style="text-align:center;width:10%" />
					<display:column property="domainName" title="站点域名"
						style="text-align:center;width:25%" />
					<display:column property="aliasName" title="别名"
						style="text-align:center;width:25%" />
					<display:column property="remark" title="备注"
						style="text-align:center;width:10%" />
					<display:column title="操作" media="html"
						style="text-align:center;width:10%">
						<a href="javascript:viewDlg(${systemSite.id});">编辑</a>

					</display:column>
				</display:table>
				<div class="clear"></div>
			</div>
		</div>
	</form>
	</div>
<div class="toumingdu make02" style="display:none;">
<input type="hidden" name="org.apache.struts.taglib.html.TOKEN"  value="${sessionScope['org.apache.struts.action.TOKEN']}" />
	<div class="lec_center" style="height:330px;">
		<div style="padding-top:40px;">
			<!-- 	
			<div class="mt10 lc_shengcheng">
				<span><i style="color:red;"></i>站点ID：</span>
				<input type="text" name="model.id" />
			</div>
			 -->
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span><i style="color:red;">*</i>站点域名：</span>
				<input type="text" id="domainName_add" name="model.domainName" datatype="s1-250" errormsg="请输入站点域名！"/>
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span><i style="color:red;">*</i>别名：</span>
				<input type="text" id="aliasName_add" name="model.aliasName" datatype="s1-250" errormsg="请输入站点别名！"/>
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span><!-- <i style="color:red;">*</i> -->备注：</span>
				<input type="text" id="remark_add" name="model.remark" />
			</div>
			<div class="clear"></div>
			<div class="cas_anniu" style="margin-top:40px;margin-left:170px;">
				<a href="javascript:addSite();" class="fl queren" style="width:70px;">确认</a>
				<a href="#" class="fl queren" id="returnId" style="width:70px;margin-left:40px;">取消</a>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>
<!-- 编辑 -->
<div class="toumingdu make03" style="display:none;">
	<div class="lec_center" style="height:300px;">
		<div style="padding-top:40px;"> 
			<input type="hidden" name="id" id="id01"/>
			<div class="mt10 lc_shengcheng">
				<span><i style="color:red">*</i>站点域名：</span>
				<input type="text" name="domainName" id="domainName01" datatype="s1-250" errormsg="请输入站点域名！"/>
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span><i style="color:red">*</i>别名：</span>
				<input type="text" name="aliasName" id="aliasName01" datatype="s1-250" errormsg="请输入站点别名！"/>
			</div>
			<div class="clear"></div>
			<div class="mt10 lc_shengcheng">
				<span><!-- <i style="color:red">*</i> -->备注：</span>
				<input type="text" name="remark" id="remark01" />
			</div>
			<div class="clear"></div>
			<div class="cas_anniu" style="margin-top:40px;margin-left:170px;">
				<a href="javascript:updateSite(${item.id});"   class="fl queren" style="width:70px;">确认</a>
				<a href="${ctx}/system/systemSite.do?method=list" class="fl queren" style="width:70px;margin-left:40px;">取消</a>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>
	<script type="text/JavaScript">

function viewDlg(id){
	  var url="${ctx}/system/systemSite.do";
	  var params = "method=updateEdit&id=" +id;
	  $.ajax({
	  	url:url,
	  	type:'POST',
	  	data:params,
	  	dataType:'json',
	  	success:function(result){
	  		if(result!=""){
	  				$('#id01').val(result.item.id);
	  				$('#domainName01').val(result.item.domainName);
	  				$('#aliasName01').val(result.item.aliasName);
	  				$('#remark01').val(result.item.remark);
	  				$('.make03').show();
	  		}
	  	}
	  });
} 

function addSite() {
	if($('#domainName_add').val() == "") {	
		alert("请输入站点域名！");
	}else if($('#domainName_add').val().length > 200) {
		alert("站点域名长度不能超过200！");
	}else if($('#aliasName_add').val() == "") {
		alert("请输入别名！");
	}else if($('#aliasName_add').val().length > 200) {
		alert("别名长度不能超过200！");
	}else if($('#remark_add').val().length > 200) {
		alert("备注长度不能超过200！");
	}else{
		var url = "${ctx}/system/systemSite.do";
		var params = "method=save";
		params += "&domainName=" + $('#domainName_add').val();
		params += "&aliasName=" + $('#aliasName_add').val();
		params += "&remark=" + $('#remark_add').val();
		
		$.ajax({
		  	url:url,
		  	type:'POST',
		  	data:params,
		  	dataType:'text',
		  	success:function(result){
		  		if (result != null) {
			  		if (result == "success") {
			  			alert("新增成功！");
			  			document.location.href = document.location.href.split("#")[0];
			  		}else if (result == "duplicated domainName"){		  	
			  			alert("站点域名重复！");		
			  			
			  		}else if(result == 'empty'){
			  			alert("站点域名为空");
			  		}
			  	}
		  	}
		  });
	  }
}

function updateSite() {
	
	if($('#domainName01').val() == "") {	
		alert("请输入站点域名！");
		return;
	}else if($('#domainName01').val().length > 200) {
		alert("站点域名长度不能超过200！");
		return;
	}else if($('#aliasName01').val() == "") {
		alert("请输入别名！");
		return;
	}else if($('#aliasName01').val().length > 200) {
		alert("别名长度不能超过200！");
		return;
	}else if($('#remark01').val().length > 200) {
		alert("备注长度不能超过200！");
		return;
	}
	
	
	var url = "${ctx}/system/systemSite.do";
	var params = "method=updateByAjax";
	params += "&id=" + $('#id01').val();
	params += "&domainName=" + $('#domainName01').val();
	params += "&aliasName=" + $('#aliasName01').val();
	params += "&remark=" + $('#remark01').val();
	
	$.ajax({
	  	url:url,
	  	type:'POST',
	  	data:params,
	  	dataType:'text',
	  	success:function(result){
	  		if (result != null) {
		  		if (result == "success") {
		  			alert("更新成功");
		  			document.location.href = document.location.href.split("#")[0];
		  		}else if (result == "duplicated domainName"){		  	
		  			alert("站点域名重复！");		
		  			$('#domainName01').val("");
		  		}else if(result == "empty"){
		  			alert("站点域名为空！");	
		  		}
		  	}
	  	}
	  });
}

$(function(){

//select menu
	var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
	var submenu = "lc_left0" + menuindex;
	$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
	$('.'+submenu+'').show();
	$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");

//生成学习卡
	$('.xuexike').click(function(){
		 $('.make02').show();
		 $("#domainName_add").val("");
		 $("#aliasName_add").val("");
		 $("#remark_add").val("");
		/* window.location.href = ctx + '/system/systemSite.do?method=add'; */
	});
	$('#returnId').click(function(){
		$('.make02').hide();
	});
	
	$('.chkall').click(function(){
			if ($(this).attr('checked') == 'checked')
				$('.chk').attr('checked' , 'checked');
			else
				$('.chk').removeAttr('checked');
	});


});
		/*
		$('#editSave').click(function(){
			$('.make03').show();
		});
		*/
		//select 选中已选项
		/*
		$("#clientId ").val(${systemSiteForm.model.clientId}); 
		$("#applicationId ").val(${systemSiteForm.model.applicationId});  
		*/

	  </script>
</body>
</html>
