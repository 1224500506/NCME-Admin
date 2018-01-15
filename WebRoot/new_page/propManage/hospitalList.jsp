<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/new_page/top.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理平台</title>
</head>

<body class="bjs">
<div>
	<!-- 题库内容 -->
	<div class="center">
		<div class="tk_center01" style="min-height:40px;">
			<ul class="cas1_xuanxing">
				<li class="action">医院</li>
				<li id="yiyuanjibie" >医院级别</li>
			</ul>
			<div class="tk_zuo">
			<form id="sfrm" action="${ctx}/propManage/hospitalList.do" method="POST">
				<div class="tik_xuanze">
					<div class="mt10 fl tk_tixing" style="margin-left:-80px;">
						<em class="fl">医院级别： </em>
						<select class="fl att_bianji01" id="hlevel" name = "hlevel" style="width:150px;">
							<option value="-1">请选择</option>
							<c:forEach items="${hospitalLevelList}" var="hlevel">
							<option value="${hlevel.id}">${hlevel.name}</option>
							</c:forEach>
						</select>
						</select>
					</div>
					<div class="mt10 fl tk_tixing" style="margin-left:10px;">
						<em class="fl">医院名称：</em>
						<input type="text"  class="fl tik_select" name="sname" value="${sname}" style="width:150px;"/>
					</div>
					
					
					<div class="mt10 fl tk_tixing" style="margin-left:10px;">
						<em class="fl">省/直辖市： </em>
						<select class="fl att_bianji01" id="province" name="province" style="width:150px;">
						<option value="-1">请选择</option>
						<c:forEach items="${region1list}" var="region">
							<option value="${region.prop_val_id}">${region.name}</option>
						</c:forEach>
						</select>
					</div>
					
					<div class="mt10 fl tk_tixing" style="margin-left:10px;">
						<em class="fl">市： </em>
						<select class="fl att_bianji01" id="city" name = "city" style="width:150px;">
							<option value="-1">请选择</option>
						</select>
					</div>
					
					<div class="mt10 fl tk_tixing" style="margin-left:10px;">
						<em class="fl">区县： </em>
						<select class="fl att_bianji01" id="county" name = "county" style="width:150px;">
							<option value="-1">请选择</option>
						</select>
					</div>
					
					<div class="mt10 fl mll8 tk_tixing" style="margin-left:10px;">
						<a href="javascript:void(0);" class="fl tk_chaxun">查询</a>
					</div>
				</div>
			</form>
			</div>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
	<div class="tkbjs"></div>
	<!--  -->
	<div class="center none" style="">
		<div class="tk_center01">
			<div class="mt10 kit1_tiaojia">
				<div class="fr cas_anniu">
					<a href="javascript:void(0);" class="fl att_tianjia" style="width:95px;margin-right:15px">添加医院</a>
					<a href="javascript:void(0);" class="fl att_tianjia03" style="width:95px;margin-right:15px">批量删除</a>
					<a href="javascript:void(0)" class="fl" style="width:95px;">导出</a>
				</div>
			</div>
			<div class="clear"></div>
			<display:table name="propList" excludedParams="type" requestURI="${ctx}/propManage/hospitalList.do?type=${type}" id="p" class="mt10 cas_table" size="${totalCount}" pagesize="20"  sort="list" defaultsort="1">
				<display:column title="<input type='checkbox' class='chkall'/>" style="width:5%;"><input type='checkbox' class='chk' value="${p.id}"/></display:column>
				<display:column title="序号" style="width:15%;" property="id"></display:column>
				<display:column title="医院名称" style="width:20%;" property="name"></display:column>
				<display:column title="医院级别" style="width:10%;" property="hlevelName"></display:column>
 				<display:column title="省" style="width:10%;" property="region1"></display:column>
 				<display:column title="市" style="width:10%;" property="region2"></display:column>
 				<display:column title="区" style="width:10%;" property="region3"></display:column>
				<display:column title="审批机关" style="width:10%;" property="examiner"></display:column>
 				<display:column title="操作" style="width:15%;">
					<a href="javascript:editPropShow('${p.id}','${p.name}','${p.hlevel}','${p.examiner}','${p.regionId1}','${p.regionId2}','${p.regionId}');" >修改</a>
					<a href="javascript:doDelProp(${p.id});" >删除</a>
 				</display:column>
			</display:table>

		</div>
	</div>
		<!-- 试题内容（结束） -->

</div>
<!-- 主题弹出框 -->
<div class="toumingdu att_xueke" style="display:none;">
	<div class="TJ_cueke" style="height:400px;">
		<div class="fl shitin_xinzeng01" style="margin-top:40px;margin-left:40px;">
			<p class="fl" style="width:100px;"><span class="fr">医院名称：</span><em class="fr">*</em></p>
			<input type="text" class="fl att_bianji01" id="rsName" />
			<input type="hidden" id="rsType"  value="${type}" />
			<input type="hidden" id="mode"  />
		</div>
		<div class="clear"></div>
		<div class="fl mt20 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:100px;"><span class="fr">医院级别：</span><em class="fr">*</em></p>
			<select class="fl att_bianji01" id="yhlevel">
				<option value="-1">请选择</option>
				<c:forEach items="${hospitalLevelList}" var="yhlevel">
					<option value="${yhlevel.id}">${yhlevel.name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="clear"></div>
		<div class="fl mt20 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:100px;"><span class="fr">审批机关：</span></p>
			<input type="text" class="fl att_bianji01" id="rsExaminer"/>
			<input type="hidden" id="rsProp_val1" value="0" />
			<input type="hidden" id="rsId" />
		</div>
		<div class="clear"></div>
		<div class="fl mt20 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:100px;"><span class="fr">省/直辖市：</span><em class="fr">*</em></p>
			<select class="fl att_bianji01" id="region1">
				<option value="-1">请选择</option>
				<c:forEach items="${region1list}" var="region">
					<option value="${region.prop_val_id}">${region.name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="clear"></div>
		<div class="fl mt20 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:100px;"><span class="fr">市：</span><em class="fr">*</em></p>
			<select class="fl att_bianji01" id="region2">
				<option value="-1">请选择</option>
			</select>
		</div>
		<div class="clear"></div>
		<div class="fl mt20 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:100px;"><span class="fr">区县：</span><em class="fr">*</em></p>
<!-- 			<input type="text" class="fl att_bianji01" id="rsPropId"/>
 -->			<select class="fl att_bianji01" id="rsPropId">
				<option value="-1">请选择</option>
			</select>
		</div>
		<div class="clear"></div>
		<div class="ca1_anniu" style="width:200px;">
			<a href="javascript:void(0);" class="fl anniu att_baocun">保存</a>
			<a href="javascript:void(0);" class="fl ml30 anniu att_fanhui">返回</a>
		</div>
		
		<input type="hidden" id="h_region2" value="0" />
		<input type="hidden" id="h_region3" value="0" />
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

	
		$('#yiyuanjibie').click(function(){
			document.location.href = "${ctx}/propManage/propHospitalList.do?type=23";
		});
		
		
		$('input[name="sname"]').blur(function(){
			var reg = new RegExp("^[\u4e00-\u9fa5_a-zA-Z0-9]+$");//匹配汉子、数字、字母及下划线
			var sname = $('input[name="sname"]').val();
			if(sname != ""){
				if(!reg.test(sname)){
					alert("医院名称仅支持汉字、字母和数字！");
					$('input[name="sname"]').val("");
					return;
				}
			}
		});
		
	//查询学科
		$('.tk_chaxun').click(function(){
			$('#sfrm').submit();
			
		});	
	//添加学科
		$('.att_tianjia').click(function(){
			$('.att_xueke').show();
			$('#rsName').val('');
			$('#rsExaminer').val('');
			$('#rsId').val('');
			$('#region2 option[value="-1"]').siblings('option').remove();
			$('#rsPropId option[value="-1"]').siblings('option').remove();
			$('#mode').val('add');
		});
	//保存属性
		$('.att_baocun').click(function(){
			var reg = new RegExp("^[\u4e00-\u9fa5_a-zA-Z0-9]+$");//匹配汉子、数字、字母及下划线
			if (!$('#rsName').val()){
				alert("请输入医院名称！");
				$('#rsName').focus();
				return;
			}
			if(!reg.test($('#rsName').val())){
				alert("医院名称仅支持汉字、字母和数字！");
				return;
			}
			if ($('#yhlevel').val() == "-1"){
				alert("请选择医院级别！");
				return;
			} 
			if(!reg.test($('#rsExaminer').val())){
				alert("审批机关仅支持汉字、字母和数字！");
				return;
			} 
			if ($('#rsPropId').val() == "-1"){
				alert("请选择地域！");
				return;
			}
			//添加
			if ($('#mode').val() == 'add'){
				var url = '${ctx}/propManage/hospitalList.do?mode=save';
				var params = 'propName=' + $('#rsName').val();
				params += '&examiner='+$('#rsExaminer').val();
				params += '&type='+$('#yhlevel').val();
				params += '&propid='+$('#rsPropId').val();
				
				$.ajax({
					url: url,
					type: 'POST',
					data: params,
					dataType: 'text',
					success: function(result){
						if(result == 'success'){
						   alert('医院添加成功！');
						   document.location.href = document.location.href.split("#")[0];
						}else{
							alert('医院名称重复,无法保存!');
							return false;
						};
					}
				});
			}
			//修改
			else if ($('#mode').val() == 'edit'){
				var url = '${ctx}/propManage/hospitalList.do?mode=save';
				var params = 'propName=' + $('#rsName').val();
				params += '&examiner='+$('#rsExaminer').val();
				params += '&propid='+$('#rsPropId').val();
				params += '&type='+$('#yhlevel').val();
				params += '&id='+$('#rsId').val();
				
				$.ajax({
					url: url,
					type: 'POST',
					data: params,
					dataType: 'text',
					success: function(result){
						if(result == 'success'){
						   alert('医院修改成功！');
						   $('.att_xueke').hide();
						   document.location.href = document.location.href.split("#")[0];
						}else{
							alert('医院名称重复,修改失败!');
						};
					}
				});
			}
			
			//$('.att_xueke').hide();
		});
	//返回
		$('.att_fanhui').click(function(){
			$('#region1').val("");
			$('#region2').val("");
			$('#rsPropId').val("");
			$('.att_xueke').hide();
		});
	//check all
		$('.chkall').click(function(){
			if ($(this).attr('checked') == 'checked')
				$('.chk').attr('checked' , 'checked');
			else
				$('.chk').removeAttr('checked');
		});
		
		$('#region1').change(function(){
			var url = "${ctx}/propManage/getPropListAjax.do?id=" + $(this).val();
			
			$.ajax({
			    url:url,
			    type: 'POST',
			    dataType: 'json',
			    success: function(result){
				   if(result != ''){
				   		updatePropList($('#region2'), result);
				   		updatePropList($('#rsPropId'), null);
				   };
			    }
			});
		});
		
		
		$('#province').change(function(){
			var url = "${ctx}/propManage/getPropListAjax.do?id=" + $(this).val();
			
			$.ajax({
			    url:url,
			    type: 'POST',
			    dataType: 'json',
			    success: function(result){
				  if(result != ''){
				   		updatePropList($('#city'), result);
				   		
				   }; 
			    }
			});
		});
		$('#region2').change(function(){
			var url = "${ctx}/propManage/getPropListAjax.do?id=" + $(this).val();
			
			$.ajax({
			    url:url,
			    type: 'POST',
			    dataType: 'json',
			    success: function(result){
				   if(result != ''){
				   		updatePropList($('#rsPropId'), result);
				   };
			    }
			});
		});
		
		
		$('#city').change(function(){
			var url = "${ctx}/propManage/getPropListAjax.do?id=" + $(this).val();
			
			$.ajax({
			    url:url,
			    type: 'POST',
			    dataType: 'json',
			    success: function(result){
				  if(result != ''){
				   		updatePropList($('#county'), result);
				   };
			    }
			});
		});
		$('.att_tianjia03').click(function(){
			if ($('.chk:checked').length<=0){
				alert ("没有选择项目。");
				return;
			}
			
			if(!confirm("您确定要删除吗？"))return;
			var url = '${ctx}/propManage/hospitalList.do?mode=batchdel';

			var array = new Array();var dels=0;
			$('.chk:checked').each(function(key, val){
				array.push($(val).val());
				dels++;
			});
			var params = 'ids='+array.toString();
			
			$.ajax({
				url: url,
				type: 'POST',
				data: params,
				dataType: 'text',
				success: function(result){
					if(result != '0'){
					   if(eval(dels-result)!=0){
						alert(dels+'个医院中'+result+'个医院目删除成功，其他'+ eval(dels - result)+'个医院因为在别的地方使用无法删除！');
						document.location.href = document.location.href.split("#")[0];
						}
					    else{
					    alert(result+'个医院删除成功!');
						document.location.href = document.location.href.split("#")[0];
						}
					}else{
						alert('操作失败！');
					};
				}
			});
			
		});
		
});

	function updatePropList(obj, result, _default){
		
		var str = "<option value='-1'>请选择</option>";
		if (result!=null)
		$(result.list).each(function(key, value){
			str += "<option value='"+value.prop_val_id+"'>"+value.name+"</option>";
		});
		
		$(obj).html(str);
		$(obj).val(_default);
	}
	
	

//修改
function editPropShow(_id,_name,_hlevel,_code,_regionId1,_regionId2,_propid){
	$('#rsName').val(_name);
	$('#yhlevel').val(_hlevel);
	$('#rsExaminer').val(_code);
	$('#rsId').val(_id);
	$('#rsPropId').val(_propid);
	$('#mode').val('edit');
	
	$('#region1').val(_regionId1);
	$('#h_region2').val(_regionId2);
	$('#h_region3').val(_propid);
	
	var url = "${ctx}/propManage/getPropListAjax.do?id=" + _regionId1;
	
	$.ajax({
	    url:url,
	    type: 'POST',
	    dataType: 'json',
	    success: function(result){
		   if(result != ''){
		   		updatePropList($('#region2'), result, $('#h_region2').val());
		   };
	    }
	});

	url = "${ctx}/propManage/getPropListAjax.do?id=" + _regionId2;
	$.ajax({
	    url:url,
	    type: 'POST',
	    dataType: 'json',
	    success: function(result){
		   if(result != ''){
		   		updatePropList($('#rsPropId'), result, $('#h_region3').val());
		   };
	    }
	});

	$('.att_xueke').show();
};

//删除
function doDelProp(id){
	if(!confirm("您确定要删除吗？"))return;
	var url = '${ctx}/propManage/hospitalList.do?mode=del';
	if(id!=''){
		var params = 'id=' +id;
	}

	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'text',
	    success: function(result){
		   if(result == 'success'){
		      alert('医院删除成功！');
		      document.location.href = document.location.href.split("#")[0];
		   }else{
		   		alert('医院删除失败请检查属性的关联!');
		   };
	    }
	    });
}




$(function(){
	var province = "${province}";
	var city = "${city}";
	var county = "${county}";
	var hlevel = "${hlevel}";
	
	if(hlevel.trim() !="" && typeof(hlevel) != "undefined"){
		$('#hlevel').val(hlevel);
	}
	if(province.trim() !="" && typeof(province) != "undefined"){
		$('#province').val(province);
	}
	var url = "${ctx}/propManage/getPropListAjax.do?id=" + province;
	var url1 = "${ctx}/propManage/getPropListAjax.do?id=" + city;
	initPro(url, 'city', city);
	initPro(url1, 'county', county);
	/* $.ajax({
	    url:url,
	    type: 'POST',
	    dataType: 'json',
	    success: function(result){
		   if(result != ''){
		   		updatePropList($('#city'), result,null);
		   };
		   
		   if(city.trim() !="" && typeof(city) != "undefined"){
				$('#city').val(city);
			}
	    }
	}); */
})

function initPro(url, region, obj){
	$.ajax({
	    url:url,
	    type: 'POST',
	    dataType: 'json',
	    success: function(result){
		   if(result != ''){
		   		updatePropList($('#'+region), result,null);
		   };
		   
		   if(obj.trim() !="" && typeof(obj) != "undefined"){
				$('#'+region).val(obj);
			}
	    }
	});
}
</script>
</body>
</html> 