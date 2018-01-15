<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@include file="/commons/taglibs.jsp"%>
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
                <script type="text/javascript" src="${ctx}/peixun_page/js/utils.js"></script>
                <script type="text/javascript" src="${ctx}/js/util.js"></script>
		<title>培训后台</title>
</head>
<body>
<!-- 头部 -->
<!-- 查询条件 -->
<%@include file="/peixun_page/top.jsp"%>
<div class="center">
<form id="sfrm" action="${ctx}/quality/userImageManage.do?method=list2&id=${pageId}" method="POST">
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl" >
			<span class ="fl ">科室：</span>
		</p>
		<input type="hidden" id="propIds" name="propIds" value="${propIds}"/>
		<input type="hidden" id="propNames" name="propNames" value="${propNames}"/>
		<div class="duouan" id="propNames01">${propNames}</div>
		<p></p>
		<p class="fl" style="margin-left:20px">
			<span>人物画像名称：</span>
			<input type="text" name="sname" id="sname" value="${sname}"/>
			<input type="hidden" id="pageId" name ="pageId" value ="${pageId}"/>
		</p>
		<p class="fl" style="margin-left:20px">
			<span>职称：</span>
			
		</p>

		<div class="fl select">
			<div class="tik_position">
				<b class="ml5" id = "selectStateDuty">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list"  style="display: none;">
			<select name ="duty"  id = "sel_SearchDutyName" style = "display:none;">
				<option value="">全部</option>
				<c:forEach items="${dutyList}" var="item">
					<option value ="${item.id }" <c:if test="${dutyId eq item.id}">selected="selected"</c:if>> ${item.name}</option>
				</c:forEach>
			</select>
			<li>全部</li>
			<c:forEach items ="${dutyList}" var ="dutylistshow">
				<li>${dutylistshow.name}</li>
			</c:forEach>
			</ul>
		</div>
		
		<div class="fl cas_anniu" style="margin-right:20px;">
			<a href="javascript:searchUserImage();" class="fl" style="width:70px;margin-left:10px;">查询</a>
		</div>
	
		<div class="clear"></div>
		<div class="fr cas_anniu" style="margin:20px 0;">
			<a href="javascript:void(0);" class="fl add_portraits_btn" style="width:140px;margin-left:10px;">添加人物画像</a>
			<a href="${ctx}/quality/userImageManage.do" class="fl" style="width:70px;margin-left:10px;">返回</a>
		</div>
		

	</div>
	</form>
</div>

<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
	<form id = "listfrm" name = "listfrm" method = "POST">
		<display:table requestURI="${ctx}/quality/userImageManage.do" id ="p"  class="mt10 table" name="list" excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}" decorator="com.hys.exam.displaytag.QualityOverOutWrapper">
				<display:column title="序号" style="width:5%;" >${p_rowNum}</display:column>
				<display:column title="人物画像名称" style="width:25%;" property="name"></display:column>
				<display:column title="科室" style=" width:10%;">
					<c:forEach items="${p.departmentPropList}" var="prop">
						${prop.name}&nbsp;
					</c:forEach>
				</display:column>
				<display:column title="医院级别职称" style="width:25%;" property="link2"></display:column>
				<display:column title="医院专业排名" style="width:20%">
					<c:forEach items="${p.specialUserImage.majorPropList}" var="prop">
						${prop.name}&nbsp;
					</c:forEach>
				</display:column>
				<display:column title="操作" style="width:15%">
					<a href="javascript:updateImage('${p.id}','${p.name}');" class="edit_portraits_btn">修改</a>
					<a href="javascript:doDel('${p.id}');" class="">删除</a>
				</display:column>
		</display:table>
			
		<div class="clear"></div>
	</form> 
	</div>

</div>


<script type="text/javascript">
var dotexp = /,/g;
var brexp = /<br>/g;

//YHQ，2017-03-14
var tongYongHT = new HashTable();//通用人物画像设置--地区、医院级别、职称
var teShuHT = new HashTable();//特殊人物画像设置--医院专业排名、职称
            
$(function(){
	
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "lc_left0" + menuindex;
		$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
	
		select_init();
		$('#propNames01').click(function(){
			//initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
			initPropList("科室", "${ctx}/propManage/getPropListAjax.do?extType=1&flag=1", 1, 0, 5, 3, $('#propIds'), $('#propNames01'));
			$('.att_make01').show();
		});
		
		$('.cas_anniu_4,.bjt_kt').click(function(){
		$('#prop_editId').val($('#propIds02').val());
			$('.att_make01').hide();
		});
		
	//Display Update and UserImage Dialog
	var win_w = parseInt($(window).width()  / 1.8) + "px";
	var win_h = parseInt($(window).height() / 2) + "px";
	var add_portraits_cont = '' +
			'<div class="center">' +
				'<div class="tiaojian clear" style="min-height:40px;">' +
					'<p style="margin-right:20px;">' +
						'<span style="width:12em;text-align:right;"><font color="red">*</font>人物画像类型：</span>' +
						'<input type="text" value="${userImage.type.name}人物画像" disabled id="leixing" name ="leixing"/>' +
					'</p>' +
				'</div>' +
				'<div class="tiaojian clear" style="min-height:40px;">' +
					'<p>' +
						'<span style="width:12em;text-align:right;"><font color="red">*</font>人物画像名称：</span>' +
						'<input type="text" value="" id = "edit1" />' +'<input type="hidden" value="" id = "edit_id"/>'+'<div class="NameCount" style = "margin-left:5px;"></div>'+
					'</p>' +
				'</div>' +
				'<div class="tiaojian clear" style="min-height:40px;">' +
					'<p>' +
						'<span style="width:12em;text-align:right;"><font color="red">*</font>科室：</span>' +
						'<input type="hidden" class="fl tik_select" id="propIds02" name="propIds02" value="${propIds}"/>' +
						'<input type="hidden" class="fl tik_select" id="propNames_" name="propNames" value="${propNames}"/>' +
						'<div class="duouan subject_input" id="propNames02"></div>' +'<input type="hidden" value="" id="prop_editId"/>' +
					'</p>' +
				'</div>' +
				'<div class="tiaojian clear" style="min-height:40px;">' +
					'<p>' +
						'<span style="width:12em;text-align:right;line-height:20px;"><font color="red">*</font>地区、医院级别、职称：</span>' +
						'<input type="text" style="width:70%;" class="level_input" id ="general" placeholder="通用人物画像设置" readonly/><input type="hidden" id="hospital" /><input type="hidden" id="area" /><input type="hidden" id="duty" />' +
					'</p>' +
				'</div>' +
				'<div class="tiaojian clear" style="min-height:40px;">' +
					'<p>' +
						'<span style="width:12em;text-align:right;line-height:20px;">医院专业排名、职称：</span>' +
						'<input type="text" class="title_input" style="width:50%;" id = "special" placeholder="特殊人物画像设置" readonly/>' +
					'</p>' +
				'</div>' +
			'</div><input type="hidden" id="areaIds" name="areaIds"/><input type="hidden" id="hospitalIds" name="hospitalIds"/><input type="hidden" id="dutyIds" name="dutyIds"/><input type="hidden" id="backhospitalIds" name="backhospitalIds"/><input type="hidden" id="backdutyIds" name="backdutyIds"/><input type="hidden" id="backareaIds" name="backareaIds"/>' +
			'<input type = "hidden" id = "majorLevelIds" name = "majorLevelIds"/><input type = "hidden" id = "majorIds" name = "majorIds"/><input type="hidden" id ="dutyIds_special" name="dutyIds_special"/><input type = "hidden" id = "backmajorLevelIds" name = "backmajorLevelIds"/><input type = "hidden" id = "backmajorIds" name = "backmajorIds"/><input type="hidden" id ="backdutyIds_special" name="backdutyIds_special"/>';
	$(".add_portraits_btn").click(function() {		
		layer.open({
			type: 1,
			title: "添加人物画像",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, win_h], //宽高
			content: add_portraits_cont,
			closeBtn: 0,
			btn: ['保存', '取消'],
			yes: function (index, layero) {
				if($('#edit1').val() == ''){
					alert("输入人物画像名称！");
					$('#edit1').focus();
					return false;
				}
				if($('#edit1').val().length > 20){
					alert("画像名称不能超过20个汉字！");
					$('#edit1').focus();
					return false;
				}
				if(!filterChart($('#edit1').val())){
					$('#edit1').focus();
					return false;
				}
				 if($('.subject_input').text() == ''){
					alert("请选择科室！");
				
					$('.subject_input').click();
					return false;
				} 
				
				else if($('#general').val() == ''){
					alert("输入通用人物画像！");
					
					$('.level_input').click();
					return false;
				}
				//有效性检查
				var regx = /^[\u4E00-\u9FA5A-Za-z0-9]+$/
	            var value = $('#edit1').val();//人物画像名称
	            if(!regx.test(value))
	            {
	                alert("人物画像名称只能输入 数字英文和汉字!");
	                return false;
	            }
				//编写保存功能			
				var url= "${ctx}/quality/userImageManage.do";
				var params = 'mode=add&typeId=${id}&id='+$('#edit_id').val()+'&name='+$('#edit1').val() + '&propIds='+$('#propIds02').val();
				params += '&areaIds='+$('#areaIds').val() + '&hospitalIds=' + $('#hospitalIds').val() + '&dutyIds=' + $('#dutyIds').val();
				params += '&majorLevelIds='+$('#majorLevelIds').val() +'&majorIds='+$('#majorIds').val() + '&dutyIds_special='+$('#dutyIds_special').val();
				$.ajax({
					type: 'POST',
					url: url,
					data : params,
					dataType: 'text',
					success:function(b){
					 	if(b == 'success'){
					 		alert('添加成功！');
					 		document.location.href = document.location.href.split("#")[0];
					 		layer.close(index);
					 	}else if(b=='error1'){
					 	 		alert('人物画像重复！保存失败！');
					 	 		return false;
					 	 }
					     else
					         alert('添加失败');
					 }
				});
				
				
				
			},
			btn2: function (index, layero) {
				layer.close(index);
			},
			success: function(layero, index){
				$(".subject_input").click(function(){
					initPropList("科室", "${ctx}/propManage/getPropListAjax.do?extType=1&flag=1", 1, 0, 5, 3, $('#propIds02'), $('#propNames02'));
					$('.att_make01').show(); 
					
				});
				$('.cas_anniu_4,.bjt_kt').click(function(){
						$('.att_make02').hide();
				});
				$(".level_input").click(function(){	
				
					var str_H; var str_H_name = ''; var str_H_id = '<select id="sel_hospital" class="tk_list" style="display:none;">'+"\n";
					var str_A; var str_A_name = ''; var str_A_id = '<select id="sel_area" style="display:none;">'+"\n";
					var str_J; var str_J_name = ''; var str_J_id = '<select id="sel_duty" style="display:none;">'+"\n";
					var str_areaIds = $('#areaIds').val();
					var str_hospitalIds = $('#hospitalIds').val();
					var str_dutyIds = $('#dutyIds').val();
					var url = "${ctx}/quality/userImageManage.do";
					var params = 'mode=generalImageAdd';
					var params1 = 'mode=userImageDuty&typeid='+${pageId};
					
					$.ajax({
						type:'post',
						url:url,
						data:params,
						dataType:'json',
						success:function(result){				
							for(var i=0; i<result.hospital.length; i++){
								str_H_id += '<option value='+result.hospital[i].id+'>'+result.hospital[i].name+'</option>'+"\n";
								str_H_name += '<li>'+result.hospital[i].name + '</li>'+"\n";								
							}		
							str_H = str_H_id + '</select>'+"\n" + str_H_name;										
							//$('#hospital').val(str_H);
							
							for(var i=0; i<result.area.length; i++){
								str_A_id += '<option value='+result.area[i].id+'>'+result.area[i].name+'</option>'+"\n";
								str_A_name += '<li>'+result.area[i].name + '</li>'+"\n";								
							}
							str_A = str_A_id + '</select>'+"\n" + str_A_name;
							
							$.ajax({
								type:'post',
								url:url,
								data:params1,
								dataType:'json',
								success:function(result){				
								
									for(var i=0; i<result.dutyList.length; i++){
										str_J_id += '<option value='+result.dutyList[i].id+'>'+result.dutyList[i].name+'</option>'+"\n";
										str_J_name += '<li>'+result.dutyList[i].name + '</li>'+"\n";								
									}		
									str_J = str_J_id + '</select>'+"\n" + str_J_name;
									//$('#duty').val(str_J);
									
							var url_edit = '${ctx}/quality/userImageManage.do';
							
							var param_edit = 'mode=getGeneralImage&area='+str_areaIds +'&hospital='+str_hospitalIds+'&duty='+str_dutyIds;
							var str_edit='';
							$.ajax({
								type:'post',
								url:url_edit,
								data:param_edit,
								dataType:'json',
								success:function(result){
									//alert(result.duty[0].name);
									var newnarray = new Array();
									var funcnarray = new Array();
									for(var i=0;i<result.duty.length;i++){
										var ahj = "地区:"+result.area[i].name+"->"+"医院级别:" +result.hospital[i].name+"->" +"职称:" +result.duty[i].name;
                                                                                tongYongHT.add(ahj,ahj); //YHQ，2017-03-14，通用人物画像设置--地区、医院级别、职称                                                                          
										//var ahj_ = '<em class="delem" onClick="delemGen(this);">' + "地区:"+ result.area[i].name +"&nbsp;&nbsp;&nbsp;"+"医院级别:" +result.hospital[i].name+"&nbsp;&nbsp;&nbsp;" +"职称:" +result.duty[i].name+'</em>';																	
                                                                                var ahj_ = '<em class="delem" onClick="delemGen(this);">' + ahj + '</em>'; //YHQ，2017-03-14，通用人物画像设置--地区、医院级别、职称
										newnarray.push(ahj_);
										funcnarray.push(ahj);
									}
									$('#qqplayer').html(newnarray.toString().replace(dotexp,"<br>"));
									$('#add_function').val(funcnarray.toString().replace(dotexp,";"));
								}
							});
													
						var sub_cont = '<div class="tiaojian" style="min-height:40px;padding:20px;">' +
							'<p class="fl" style="width:70%;margin:0 20px 20px 0;"><div  rows="5" class="general" id="qqplayer"></div></p><input type="hidden" id="add_function" />' +
							//'<p class="fl cas_anniu"><a href="javascript:void(0);" class="fl confirm_btn" style="margin-left:20px;width:70px;margin-top:40px;">确定</a></p>' +
							'<div class="clear radio_list" style="margin:10px 0;padding:10px 0;font-size:16px;border-bottom:1px solid #cfcfcf;">' +
								
							'</div>' +
							'<p class="fl area_level">' +
							'<span style="width:6em;text-align:right;">地区级别：</span>' +
							'<div class="fl select">' +
							'<div class="tik_position">' +
							'<b class="ml5" id="Area">请选择</b>' +
							'<p class="position01"><i class="bjt10"></i></p>' +
							'</div>' +
							'<ul class="fl list" style="display:none;">' + 
							//地区级别
							str_A+
							'</ul>' +
							'</div>' +
							'</p>' +
							'<p class="fl area_detail" style="margin-right:20px;position:relative;display:none;">' +
								'<span style="width:8em;text-align:right;">选择详细地区：</span>' +
								'<input type="text" class="area_input" />' +
								'<img src="images/area_list.jpg" style="display:none;position:absolute;top:30px;left:8em;width:50%;" /> ' +
							'</p>' +
							'<p class="fl">' +
							'<span style="width:6em;text-align:right;">医院级别：</span>' +
							'<div class="fl select">' +
							'<div class="tik_position">' +
							'<b class="ml5" id="Hospital">请选择</b>' +
							'<p class="position01"><i class="bjt10"></i></p>' +
							'</div>' +
							'<ul class="fl list" id="hospital_ul" style="display:none;">' + str_H +
							'</ul>' +
							'</div>' +
							'</p>' +
							'<p class="fl">' +
							'<span style="width:4em;text-align:right;">职称：</span>' +
							'<div class="fl select">' +
							'<div class="tik_position">' +
							'<b class="ml5" id="Duty">请选择</b>' +
							'<p class="position01"><i class="bjt10"></i></p>' +
							'</div>' +
							'<ul class="fl list" style="display:none;">' +
							str_J +
							'</ul>' +
							'</div>' +
							'</p>' +
							'<p style="margin-left:20px" class="fl cas_anniu" ><a href="javascript:void(0);" class="fl" id="add_ahj" style="width:50px;">添加</a></p>' +
							'</div>';
								
						layer.open({
						type: 1,
						title: "地区、医院级别、职称",
						skin: 'layui-layer-rim', //加上边框
						area: [win_w, win_h], //宽高
						content: sub_cont,
						closeBtn: 0,
						btn: ['确定','取消'],
						yes: function (index, layero) {
							if($('#qqplayer').text() == ''){
								alert('没有添加！');
								return false;
							}
							$('#general').val($('#add_function').val());
							$('#backhospitalIds').val($('#hospitalIds').val());
							$('#backareaIds').val($('#areaIds').val());
							$('#backdutyIds').val($('#dutyIds').val());
							layer.close(index);
						},							
						success: function(layerc, index){						
							
								select_init();
	
								$(".search_btn").click(function(){
									layer.close(index);
								});
								$(".area_input").focus(function(){
									$(this).next("img").show().click(function(){
										$(this).hide();
										$(this).prev(".area_input").val("北京");
									});
								});
								
								$('#add_ahj').click(function(){
									
								var ahj = "地区:"+$('#Area').text()+"->"+"医院级别:" +$('#Hospital').text()+"->" +"职称:" +$('#Duty').text()+";";
                                                                                     
                                         //YHQ，2017-03-14，通用人物画像设置--地区、医院级别、职称
                                         if (tongYongHT.containsKey(ahj)) {
                                             alert("重复数据！") ;
                                             return ;
                                         } else {
                                             tongYongHT.add(ahj,ahj);
                                         }
                                                                                                                                                                             
								//var ahj_ = '<em class="delem" onClick="delemGen(this);">' + "地区:"+$('#Area').text()+"&nbsp;&nbsp;&nbsp;"+"医院级别: " +$('#Hospital').text()+"&nbsp;&nbsp;&nbsp;" +"职称:" +$('#Duty').text()+'</em><br>';
                                                                                     var ahj_ = '<em class="delem" onClick="delemGen(this);">' + ahj + '</em><br>'; //YHQ，2017-03-14，通用人物画像设置--地区、医院级别、职称
								
								selstr = $('#qqplayer').html();
								selarray = selstr.split("<br>");
								var newnarray = new Array();
								$(selarray).each(function(key, val){
									if (val != "") newnarray.push(val);
								});
								newnarray.push(ahj_);
								$('#qqplayer').html(newnarray.toString().replace(dotexp,"<br>"));
								
								selstr = $('#add_function').val();
								selarray = selstr.split(";");
								var funcnarray = new Array();
								$(selarray).each(function(key, val){
									if (val != "") funcnarray.push(val);
								});
								funcnarray.push(ahj);
								
								$('#add_function').val(funcnarray.toString().replace(dotexp,";"));
								$('#hospitalIds').val($('#hospitalIds').val()+$('#sel_hospital').val()+",");
								$('#areaIds').val($('#areaIds').val()+$('#sel_area').val()+",");
								$('#dutyIds').val($('#dutyIds').val()+$('#sel_duty').val()+",");	
										
																			
								});
								/* $(".confirm_btn").click(function(){
									if($('#qqplayer').text() == ''){
										alert('没有添加！');
										return false;
									}
									$('#general').val($('#add_function').val());
									$('#backhospitalIds').val($('#hospitalIds').val());
									$('#backareaIds').val($('#areaIds').val());
									$('#backdutyIds').val($('#dutyIds').val());
									layer.close(index);
								}); */
						}
					});	
					}
				});		
			}		
						
	});							
});
				$(".title_input").click(function(){
					var str_m;var str_m_name =''; var str_m_id = '<select id="sel_major" class="tk_list" style="display:none;">' + "\n" ;
					var str_duty;var str_duty_name =''; var str_duty_id ='<select id="sel_duty_spec" class ="list" style = "diplay:none">'+"\n";
					var str_m_l;var str_ml_name=''; var str_ml_id = '<select id="sel_majorLevel" class = "tk_list" style="display:none;">'+"\n";
					var url="${ctx}/quality/userImageManage.do";
					var params = 'mode=special&typeid='+${pageId};
					//Add by IE
					$.ajax({
						type:'post',
						url:url,
						data:params,
						dataType:'json',
						success:function(result){
							str_ml_id += '<option value="">请选择</option>'+"\n";
							str_ml_name +='<li>请选择</li>'+"\n";
							if(result.majorLvl != '' && result.majorLvl != null){
								for(var i=0;i<result.majorLvl.length;i++){
									str_ml_id += '<option value=' + result.majorLvl[i].id + '>' +result.majorLvl[i].id + "级别" + '</option>'+ "\n";
									str_ml_name += '<li>'+result.majorLvl[i].id+"级别"+'</li>' +"\n";
								}
							}
							str_ml = str_ml_id+'</select>' + "\n" + str_ml_name;
							
						//End
							$.ajax({
								type:'POST',
								url:url,
								data:params,
								dataType:'json',
								success:function(result){
									
									for(var i=0;i<result.major.length;i++){
										str_m_id += '<option value =' + result.major[i].id + '>' + result.major[i].name + '</option>'+"\n";
										str_m_name += '<li>' + result.major[i].name + '</li>' +"\n";
									}
									str_m = str_m_id + '</select>' + "\n" + str_m_name;
									$('#major').val(str_m);
									
									for(var i=0;i<result.duty.length;i++){
										str_duty_id += '<option value =' + result.duty[i].id + '>' + result.duty[i].name + '</option>'+"\n";
										str_duty_name += '<li>' + result.duty[i].name + '</li>' +"\n";
									}
									
									var url_edit = '${ctx}/quality/userImageManage.do';
									var str_majorIds = $('#majorIds').val();
									var str_dutyIds = $('#dutyIds_special').val();
									var param_edit = 'mode=getSpecialImage&major='+str_majorIds +'&duty='+str_dutyIds;
									var str_edit='';
									$.ajax({
										type:'post',
										url:url_edit,
										data:param_edit,
										dataType:'json',
										success:function(result){
											//alert(result.duty[0].name);
											var newnarray = new Array();
											var funcnarray = new Array();
											for(var i=0;i<result.duty.length;i++){
												var spec = "专业级别:"+result.majorLevel[i].name+"->"+"医院专业排名:"+result.major[i].name+"->"+"职称:" +result.duty[i].name;
												var spec_ = '<em class="delem" onClick="delemSpec(this);">' + "专业级别:"+ result.majorLevel[i].name +"&nbsp;&nbsp;&nbsp;" +"医院专业排名:"+ result.major[i].name +"&nbsp;&nbsp;&nbsp;" + "职称:" +result.duty[i].name+'</em>';																	
												newnarray.push(spec_);
												funcnarray.push(spec);
											}
											$('#specialDisplay').html(newnarray.toString().replace(dotexp,"<br>"));
											$('#add_function').val(funcnarray.toString().replace(dotexp,";"));
										}
									});
									str_duty = str_duty_id + '</select>' + "\n" + str_duty_name;
									var title_cont = '<div class="tiaojian" style="min-height:40px;padding:20px;">' +
									'<p class="fl" style="width:70%;margin:0 20px 20px 0;"><div  rows="5"  class="general" id = "specialDisplay" class="fl" name=""></div></p><input type="hidden" id="add_function"/>' +
									//'<p class="fl cas_anniu"><a href="javascript:void(0);" class="fl confirm_btn" style="width:70px;margin-left:20px;margin-top:40px;">确定</a></p>' +
									'<div class="clear" style="margin-top:20px;"></div> ' +
									
									'<p class="fl area_level">' +
									
									'<span style="width:6em;text-align:right;">专业级别：</span>' +
									'<div class="fl select">' +
									'<div class="tik_position">' +
									'<b class="ml5" id ="MajorLevel">请选择</b>' +
									'<p class="position01"><i class="bjt10"></i></p>' +
									'</div>' +
									'<ul class="fl list level" style="display:none;">' + str_ml +
									
									'</ul>' +
									'</div>' +
									'</p>' +
									
									'<p class="fl area_level">' +
									
									'<span style="width:6em;text-align:right;">专业排名：</span>' +
									'<div class="fl select">' +
									'<div class="tik_position">' +
									'<b class="ml5" id ="Major">请选择</b>' +
									'<p class="position01"><i class="bjt10"></i></p>' +
									'</div>' +
									'<ul class="fl list" id="majorList" style="display:none;">' + str_m +
									
									'</ul>' +
									'</div>' +
									'</p>' +
				
									'<p class="fl">' +
									'<span style="width:4em;text-align:right;">职称：</span>' +
									'<div class="fl select">' +
									'<div class="tik_position">' +
									'<b class="ml5" id ="duty_spec">请选择</b>' +
									'<p class="position01"><i class="bjt10"></i></p>' +
									'</div>' +
									'<ul class="fl list" style="display:none;">' + str_duty +
								
									'</ul>' +
									'</div>' +
									'</p>' +
									'<p style="margin-left:20px" class="fl cas_anniu"><a href="javascript:void(0);" class="fl" id= "add_Special" style="width:70px;margin-left:20px;">添加</a></p>' +
									'</div>';
									layer.open({
										type: 1,
										title: "医院专业排名、职称",
										skin: 'layui-layer-rim', //加上边框
										area: [win_w, win_h], //宽高
										content: title_cont,
										closeBtn: 0,
										btn: ['确定','取消'],
										yes: function (index, layero) {
											if($('#specialDisplay').text() == ''){
												alert('没有添加！');
												return false;
											}
											$('#special').val($('#add_function').val());
											$('#backmajorLevelIds').val($('#majorLevelIds').val());
											$('#backmajorIds').val($('#majorIds').val());
											$('#backdutyIds_special').val($('#dutyIds_special').val());
											layer.close(index);
										},														
										success: function(layerc, index){
										
											select_init();
										//Add By IE	
											$('.level li').click(function(){
												str_m_id = '<select id="sel_major" class="tk_list" style="display:none;">' + "\n";
												str_m_name='';
												str_m = '';
												var	param = 'mode=special&level='+$('#sel_majorLevel').val()+'&typeid='+${pageId};
												
												$.ajax({		    		    
												    type: 'POST',
												    url:"${ctx}/quality/userImageManage.do",
												   	data:param,
												    dataType: 'JSON',
												    success: function(B){	
												    	var str_user_image_temp = '';
												    	if(B.major != '' && B.major.length >0 ){				    	
													    	for(var i=0; i<B.major.length;i++){
													    		
													    		str_m_id += '<option value =' + B.major[i].id + '>' + B.major[i].name + '</option>'+"\n";
																str_m_name += '<li>' + B.major[i].name + '</li>' +"\n";
													    	}	
													    	str_m = str_m_id + '</select>' + "\n" + str_m_name;
													    	$('#majorList').html(str_m);
													    	select_init();
												    	}
												    	else{
												    		$('#Major').text('');
												    		$('#sel_major').html('<ul></ul>');
												    	}
													}
											    });
											});
											
										//End													
											$('#add_Special').click(function(){
												
											if($('#MajorLevel').text() == "请选择"){
												alert("请选择专业级别");
												return false;
											}
											if($('#duty_spec').text() == "请选择" || $('#Major').text() =="请选择") {
												return ;
											}
											else{
													if(idCheck($('#majorIds').val(),$('#sel_major').val()) || idCheck($('#dutyIds_special').val(),$('#sel_duty_spec').val()))
													{
														
														var spec ="专业级别:" + $('#MajorLevel').text() +"->"+"医院专业排名:" + $('#Major').text() +"->"+"职称:" + $('#duty_spec').text() + "\n";
														var spec_ = '<em class="delem" onClick="delemSpec(this);">' + "专业级别:"+ $('#MajorLevel').text() +"&nbsp;&nbsp;" +"医院专业排名:"+ $('#Major').text() +"&nbsp;&nbsp;&nbsp;" + "职称:" +$('#duty_spec').text()+'</em>';
													
														selstr = $('#specialDisplay').html();
														selarray = selstr.split("<br>");
														var newnarray = new Array();
														$(selarray).each(function(key, val){
															if (val != "") newnarray.push(val);
														});
														newnarray.push(spec_);
														$('#specialDisplay').html(newnarray.toString().replace(dotexp,"<br>"));
														
														selstr = $('#add_function').val();
														selarray = selstr.split(";");
														var funcnarray = new Array();
														$(selarray).each(function(key, val){
															if (val != "") funcnarray.push(val);
														});
														funcnarray.push(spec);
														
														$('#add_function').val(funcnarray.toString().replace(dotexp,";"));								
														$('#majorLevelIds').val($('#majorLevelIds').val()+$('#sel_majorLevel').val() + ",");									
														$('#majorIds').val($('#majorIds').val()+$('#sel_major').val() + ",");
														$('#dutyIds_special').val($('#dutyIds_special').val()+$('#sel_duty_spec').val() +"," );
														
													}else { return ;}
												}
											});
											$(".confirm_btn").click(function(){
												
													if($('#specialDisplay').text() == ''){
														alert('没有添加！');
														return false;
													}
												$('#special').val($('#add_function').val());
												$('#backmajorLevelIds').val($('#majorLevelIds').val());
												$('#backmajorIds').val($('#majorIds').val());
												$('#backdutyIds_special').val($('#dutyIds_special').val());
												layer.close(index);
											});	
										}
									});
								}								
							});
						}								
					});
				});
			}
		});
	});	
	
	var edit_portraits_cont = '' +
			'<div class="center">' +
				'<div class="tiaojian clear" style="min-height:40px;">' +
					'<p style="margin-right:20px;">' +
						'<span style="width:12em;text-align:right;"><font color="red">*</font>人物画像类型：</span>' +
						'<input type="text" value="${list.list[0].type.name}人物画像" disabled id="leixing" name ="leixing"/>' +
					'</p>' +
				'</div>' +
				'<div class="tiaojian clear" style="min-height:40px;">' +
					'<p>' +
						'<span style="width:12em;text-align:right;"><font color="red">*</font>人物画像名称：</span>' +
						'<input type="text" value="" id = "edit1" />' +'<input type="hidden" value="" id = "edit_id"/>'+'<div class="NameCount" style="margin-left:5px;"></div>'+
					'</p>' +
				'</div>' +
				'<div class="tiaojian clear" style="min-height:40px;">' +
					'<p>' +
						'<span style="width:12em;text-align:right;"><font color="red">*</font>科室：</span>' +
						'<input type="hidden" class="fl tik_select" id="propIds02" name="propIds02" value="${propIds}"/>' +
						'<input type="hidden" class="fl tik_select" id="propNames_" name="propNames" value="${propNames}"/>' +
						'<div class="duouan subject_input" id="propNames02"></div>' +'<input type="hidden" value="" id="prop_editId"/>' +
					'</p>' +
				'</div>' +
				'<div class="tiaojian clear" style="min-height:40px;">' +
					'<p>' +
						'<span style="width:12em;text-align:right;line-height:20px;"><font color="red">*</font>地区、医院级别、职称：</span>' +
						'<input type="text" style="width:70%;" class="level_input" id ="general" placeholder="通用人物画像设置" readonly/><input type="hidden" id="hospital" /><input type="hidden" id="area" /><input type="hidden" id="duty" />' +
					'</p>' +
				'</div>' +
				'<div class="tiaojian clear" style="min-height:40px;">' +
					'<p>' +
						'<span style="width:12em;text-align:right;line-height:20px;">医院专业排名、职称：</span>' +
						'<input type="text" class="title_input" style="width:50%;" id = "special" placeholder="特殊人物画像设置" readonly/>' +'<input type = "hidden" id ="majorLevel"/><input type = "hidden" id ="major"/><input type ="hidden" id="duty"/>' +
					'</p>' +
				'</div>' +
			'</div><input type="hidden" id="areaIds" name="areaIds"/><input type="hidden" id="hospitalIds" name="hospitalIds"/><input type="hidden" id="dutyIds" name="dutyIds"/><input type="hidden" id="backhospitalIds" name="backhospitalIds"/><input type="hidden" id="backdutyIds" name="backdutyIds"/><input type="hidden" id="backareaIds" name="backareaIds"/>' +
			'<input type = "hidden" id = "majorLevelIds" name = "majorLevelIds"/><input type = "hidden" id = "majorIds" name = "majorIds"/><input type="hidden" id ="dutyIds_special" name="dutyIds_special"/><input type = "hidden" id = "backmajorLevelIds" name = "backmajorLevelIds"/><input type = "hidden" id = "backmajorIds" name = "backmajorIds"/><input type="hidden" id ="backdutyIds_special" name="backdutyIds_special"/>';
	$(".edit_portraits_btn").click(function() {
		layer.open({
			type: 1,
			title: "修改人物画像",
			skin: 'layui-layer-rim', //加上边框
			area: [win_w, win_h], //宽高
			content: edit_portraits_cont,
			closeBtn: 0,
			btn: ['保存', '取消'],
			yes: function (index, layero) {
				//编写保存功能			
				if($('#edit1').val() == ''){
					alert('请输入人物画像名称！');
					$('#edit1').focus();
					return false;
					
				}
				if($('#edit1').val().length > 20){
					alert("画像名称不能超过20个汉字！");
					$('#edit1').focus();
					return false;
				}
				if(!filterChart($('#edit1').val())){
					$('#edit1').focus();
					return false;
				}
				if($('#propNames02').text() == ''){
					alert('请输入科室！');
					$('#propNames02').click();
					return false;
					
				}
				if($('.level_input').val() == ''){
					alert('请输入通用人物画像！');
					$('.level_input').click();
					return false;
					
				}
				//有效性检查
				var regx = /^[\u4E00-\u9FA5A-Za-z0-9]+$/
			    var value = $('#edit1').val();//人物画像名称
			    if(!regx.test(value))
			    {
			        alert("人物画像名称只能输入 数字英文和汉字!");
			        return false;
			    }
				var url= "${ctx}/quality/userImageManage.do";
				var params = 'mode=update&typeId=${id}&id='+$('#edit_id').val()+'&name='+$('#edit1').val() + '&propIds='+$('#propIds02').val();
				params += '&areaIds='+$('#areaIds').val() + '&hospitalIds=' + $('#hospitalIds').val() + '&dutyIds=' + $('#dutyIds').val();
				params += '&majorLevelIds='+$('#majorLevelIds').val() +'&majorIds='+$('#majorIds').val() + '&dutyIds_special=' + $('#dutyIds_special').val();
				$.ajax({
					type: 'POST',
					url: url,
					data : params,
					dataType: 'text',
					success:function(b){
					 	if(b == 'success'){
					 		alert('修改成功！');
					 		document.location.href = document.location.href.split("#")[0];
					 	}
					 	else if(b=='error1'){
					 	 		alert('人物画像重复！保存失败！');
					 	 		return false;
					 	 }
					 	else
					 		alert('修改失败!');
					 },
					 error:function(){
					 	alert('修改失败!');
					 }
				});
				
				
				layer.close(index);
			},
			btn2: function (index, layero) {
				layer.close(index);
			},
			success: function(layero, index){
				$(".subject_input").click(function(){
						initPropList("科室", "${ctx}/propManage/getPropListAjax.do?extType=1&flag=1", 1, 0, 5, 3, $('#propIds02'), $('#propNames02'));
						$('.att_make01').show();					
				});
				$('.cas_anniu_4,.bjt_kt').click(function(){
					$('.att_make01').hide();
				});
				$(".level_input").click(function(){	
					$('#prop_editId').val($('#propIds02').val());
					var str_H; var str_H_name = ''; var str_H_id = '<select id="sel_hospital" class="tk_list" style="display:none;">'+"\n";
					var str_A; var str_A_name = ''; var str_A_id = '<select id="sel_area" style="display:none;">'+"\n";
					var str_J; var str_J_name = ''; var str_J_id = '<select id="sel_duty" style="display:none;">'+"\n";
					
					var str_areaIds = $('#areaIds').val();
					var str_hospitalIds = $('#hospitalIds').val();
					var str_dutyIds = $('#dutyIds').val();
					
					var url = "${ctx}/quality/userImageManage.do";
					var params = 'mode=generalImageAdd';
					var params1 = 'mode=userImageDuty&typeid='+${pageId};
					
					$.ajax({
						type:'post',
						url:url,
						data:params,
						dataType:'json',
						success:function(result){				
						
							for(var i=0; i<result.hospital.length; i++){
								str_H_id += '<option value='+result.hospital[i].id+'>'+result.hospital[i].name+'</option>'+"\n";
								str_H_name += '<li>'+result.hospital[i].name + '</li>'+"\n";								
							}		
							str_H = str_H_id + '</select>'+"\n" + str_H_name;										
							//$('#hospital').val(str_H);
							
							for(var i=0; i<result.area.length; i++){
								str_A_id += '<option value='+result.area[i].id+'>'+result.area[i].name+'</option>'+"\n";
								str_A_name += '<li>'+result.area[i].name + '</li>'+"\n";								
							}
							str_A = str_A_id + '</select>'+"\n" + str_A_name;
							
							$.ajax({
								type:'post',
								url:url,
								data:params1,
								dataType:'json',
								success:function(result){				
								
									for(var i=0; i<result.dutyList.length; i++){
										str_J_id += '<option value='+result.dutyList[i].id+'>'+result.dutyList[i].name+'</option>'+"\n";
										str_J_name += '<li>'+result.dutyList[i].name + '</li>'+"\n";								
									}		
									str_J = str_J_id + '</select>'+"\n" + str_J_name;
									//$('#duty').val(str_J);
									
							var url_edit = '${ctx}/quality/userImageManage.do';
							var param_edit = 'mode=getGeneralImage&area='+str_areaIds +'&hospital='+str_hospitalIds+'&duty='+str_dutyIds ;
							var str_edit='';
							$.ajax({
								type:'post',
								url:url_edit,
								data:param_edit,
								dataType:'json',
								success:function(result){
									var newnarray = new Array();
									var funcnarray = new Array();
									for(var i=0;i<result.duty.length;i++){
										var ahj = "地区:"+result.area[i].name+"->"+"医院级别:" +result.hospital[i].name+"->" +"职称:" +result.duty[i].name;
                                                                                
                                                                                //YHQ，2017-03-14，通用人物画像设置--地区、医院级别、职称                                                                                
                                                                                tongYongHT.add(ahj,ahj);
                                                                                
										//var ahj_ = '<em class="delem" onClick="delemGen(this);">' + "地区:"+ result.area[i].name +"&nbsp;&nbsp;&nbsp;"+"医院级别:" +result.hospital[i].name+"&nbsp;&nbsp;&nbsp;" +"职称:" +result.duty[i].name+'</em>';																	
                                                                                var ahj_ = '<em class="delem" onClick="delemGen(this);">' + ahj + '</em>';//YHQ，2017-03-14，通用人物画像设置--地区、医院级别、职称
										newnarray.push(ahj_);
										funcnarray.push(ahj);
									}
									$('#qqplayer').html(newnarray.toString().replace(dotexp,"<br>"));
									$('#add_function').val(funcnarray.toString().replace(dotexp,";"));
								}
							});
							
													
						var sub_cont = '<div class="tiaojian" style="min-height:40px;padding:20px;">' +
							'<p class="fl" style="width:70%;margin:0 20px 20px 0;"><div class="fl general" id="qqplayer"></div></p><input type="hidden" id="add_function" />' +
							//'<p class="fl cas_anniu"><a href="javascript:void(0);" class="fl confirm_btn" style="margin-left:20px;width:70px;margin-top:40px;">确定</a></p>' +
							'<div class="clear radio_list" style="margin:10px 0;padding:10px 0;font-size:16px;border-bottom:1px solid #cfcfcf;">' +
							'</div>' +
							'<p class="fl area_level">' +
							'<span style="width:6em;text-align:right;">地区级别：</span>' +
							'<div class="fl select">' +
							'<div class="tik_position">' +
							'<b class="ml5" id="Area">请选择</b>' +
							'<p class="position01"><i class="bjt10"></i></p>' +
							'</div>' +
							'<ul class="fl list" style="display:none;">' + 
							//地区级别
							str_A+
							'</ul>' +
							'</div>' +
							'</p>' +
							'<p class="fl area_detail" style="margin-right:20px;position:relative;display:none;">' +
								'<span style="width:8em;text-align:right;">选择详细地区：</span>' +
								'<input type="text" class="area_input" />' +
								'<img src="images/area_list.jpg" style="display:none;position:absolute;top:30px;left:8em;width:50%;" /> ' +
							'</p>' +
							'<p class="fl">' +
							'<span style="width:6em;text-align:right;">医院级别：</span>' +
							'<div class="fl select">' +
							'<div class="tik_position">' +
							'<b class="ml5" id="Hospital">请选择</b>' +
							'<p class="position01"><i class="bjt10"></i></p>' +
							'</div>' +
							'<ul class="fl list" id="hospital_ul" style="display:none;">' + str_H +
							//add:area,hospital,duty:hospital display
							//$('#hospital').val()+
							'</ul>' +
							'</div>' +
							'</p>' +
							'<p class="fl">' +
							'<span style="width:4em;text-align:right;">职称：</span>' +
							'<div class="fl select">' +
							'<div class="tik_position">' +
							'<b class="ml5" id="Duty">请选择</b>' +
							'<p class="position01"><i class="bjt10"></i></p>' +
							'</div>' +
							'<ul class="fl list" style="display:none;">' +
							str_J +
							'</ul>' +
							'</div>' +
							'</p>' +
							'<p style="margin-left:20px" class="fl cas_anniu" ><a href="javascript:void(0);" class="fl" id="add_ahj" style="width:50px;">添加</a></p>' +
							'</div>';
								
						layer.open({
						type: 1,
						title: "地区、医院级别、职称",
						skin: 'layui-layer-rim', //加上边框
						area: [win_w, win_h], //宽高
						content: sub_cont,
						closeBtn: 0,
						btn: ['确定','取消'],
						yes:  function (index, layero) {
							if($('#qqplayer').text() == ''){
								alert('没有添加！');
								return false;
							}
							$('#general').val($('#add_function').val());
							$('#backhospitalIds').val($('#hospitalIds').val());
							$('#backareaIds').val($('#areaIds').val());
							$('#backdutyIds').val($('#dutyIds').val());
							layer.close(index);
						},				
						success: function(layerc, index){									
							
							select_init();

							$(".search_btn").click(function(){
								layer.close(index);
							});

							$(".area_input").focus(function(){
								$(this).next("img").show().click(function(){
									$(this).hide();
									$(this).prev(".area_input").val("北京");
								});
							});
							
							$('#add_ahj').click(function(){
								var ahj = "地区:"+$('#Area').text()+"->"+"医院级别:" +$('#Hospital').text()+"->" +"职称:" +$('#Duty').text();
                                                                              
                                //YHQ，2017-03-14，通用人物画像设置--地区、医院级别、职称
                                if (tongYongHT.containsKey(ahj)) {
                                    alert("重复数据！") ;
                                    return ;
                                } else {
                                    tongYongHT.add(ahj,ahj);
                                }
                                                                              
								//var ahj_ = '<em class="delem" onClick="delemGen(this);">' + "地区:"+$('#Area').text()+"&nbsp;&nbsp;&nbsp;"+"医院级别:" +$('#Hospital').text()+"&nbsp;&nbsp;&nbsp;" +"职称:" +$('#Duty').text()+'</em><br>';
                                var ahj_ = '<em class="delem" onClick="delemGen(this);">' + ahj + '</em><br>'; //YHQ，2017-03-14，通用人物画像设置--地区、医院级别、职称
								
								selstr = $('#qqplayer').html();
								selarray = selstr.split("<br>");
								var newnarray = new Array();
								$(selarray).each(function(key, val){
									if (val != "") newnarray.push(val);
								});
								newnarray.push(ahj_);
								$('#qqplayer').html(newnarray.toString().replace(dotexp,"<br>"));
								
								selstr = $('#add_function').val();
								selarray = selstr.split(";");
								var funcnarray = new Array();
								$(selarray).each(function(key, val){
									if (val != "") funcnarray.push(val);
								});
								funcnarray.push(ahj);
								
								$('#add_function').val(funcnarray.toString().replace(dotexp,";"));
								$('#hospitalIds').val($('#hospitalIds').val()+$('#sel_hospital').val()+",");
								$('#areaIds').val($('#areaIds').val()+$('#sel_area').val()+",");
								$('#dutyIds').val($('#dutyIds').val()+$('#sel_duty').val()+",");

							
								
							});
							/* $(".confirm_btn").click(function(){
							
									if($('#qqplayer').text() == ''){
										alert('没有添加！');
										return false;
									}
									$('#general').val($('#add_function').val());
									$('#backhospitalIds').val($('#hospitalIds').val());
									$('#backareaIds').val($('#areaIds').val());
									$('#backdutyIds').val($('#dutyIds').val());
									layer.close(index);
							}); */
						}
						
					});	
					}
				});		
			}		
						
	});	
						
});
				$(".title_input").click(function(){			
						var str_m;var str_m_name =''; var str_m_id = '<select id="sel_major" class="tk_list" style="display:none;">' + "\n" ;
						var str_duty;var str_duty_name =''; var str_duty_id ='<select id="sel_duty_spec" class ="list" style = "diplay:none">'+"\n";
						var str_m_l;var str_ml_name=''; var str_ml_id = '<select id="sel_majorLevel" class = "tk_list" style="display:none;">'+"\n";
						var url="${ctx}/quality/userImageManage.do";
						var params = 'mode=special&typeid='+${pageId};
					//Add By IE
						$.ajax({
						type:'post',
						url:url,
						data:params,
						dataType:'json',
						success:function(result){
							str_ml_id += '<option value="">请选择</option>'+"\n";
							str_ml_name +='<li>请选择</li>'+"\n";
							if(result.majorLvl != '' && result.majorLvl != null){
								for(var i=0;i<result.majorLvl.length;i++){
									str_ml_id += '<option value=' + result.majorLvl[i].id + '>' +result.majorLvl[i].id + "级别" + '</option>'+ "\n";
									str_ml_name += '<li>'+result.majorLvl[i].id+"级别"+'</li>' +"\n";
								}
							}
							str_ml = str_ml_id+'</select>' + "\n" + str_ml_name;
							$.ajax({
								type:'POST',
								url:url,
								data:params,
								dataType:'json',
								success:function(result){
									
									for(var i=0;i<result.major.length;i++){
										str_m_id += '<option value =' + result.major[i].id + '>' + result.major[i].name + '</option>'+"\n";
										str_m_name += '<li>' + result.major[i].name + '</li>' +"\n";
									}
									str_m = str_m_id + '</select>' + "\n" + str_m_name;
									$('#major').val(str_m);
									
									for(var i=0;i<result.duty.length;i++){
										str_duty_id += '<option value =' + result.duty[i].id + '>' + result.duty[i].name + '</option>'+"\n";
										str_duty_name += '<li>' + result.duty[i].name + '</li>' +"\n";
									}
									var url_edit = '${ctx}/quality/userImageManage.do';
									var str_majorLevelIds = $('#majorLevelIds').val();
									var str_majorIds = $('#majorIds').val();
									var str_dutyIds = $('#dutyIds_special').val();
									var param_edit = 'mode=getSpecialImage&major='+str_majorIds +'&duty='+str_dutyIds+'&majorLevel='+str_majorLevelIds;
									var str_edit='';
									$.ajax({
										type:'post',
										url:url_edit,
										data:param_edit,
										dataType:'json',
										success:function(result){
											//alert(result.duty[0].name);
											var newnarray = new Array();
											var funcnarray = new Array();
											if(result.majorLevel.length != 0){
												for(var i=0;i<result.duty.length;i++){
													var spec = "专业级别:"+result.majorLevel[i].name+"级别->"+"医院专业排名:"+result.major[i].name+"->"+"职称:" +result.duty[i].name;
													var spec_ = '<em class="delem" onClick="delemSpec(this);">' + "专业级别:"+ result.majorLevel[i].name +"级别&nbsp;&nbsp;&nbsp;" +"医院专业排名:"+ result.major[i].name +"&nbsp;&nbsp;&nbsp;" + "职称:" +result.duty[i].name+'</em>';																	
													newnarray.push(spec_);
													funcnarray.push(spec);
												}
											}
											$('#specialDisplay').html(newnarray.toString().replace(dotexp,"<br>"));
											$('#add_function').val(funcnarray.toString().replace(dotexp,";"));
										}
									});
									str_duty = str_duty_id + '</select>' + "\n" + str_duty_name;
									var title_cont = '<div class="tiaojian" style="min-height:40px;padding:20px;">' +
									'<p class="fl" style="width:70%;margin:0 20px 20px 0;"><div  rows="5"  class="general" id = "specialDisplay" class="fl" name=""></div></p><input type="hidden" id="add_function"/>' +
									//'<p class="fl cas_anniu"><a href="javascript:void(0);" class="fl confirm_btn" style="width:70px;margin-left:20px;margin-top:40px;">确定</a></p>' +
									'<div class="clear" style="margin-top:20px;"></div> ' +
									
									'<p class="fl area_level">' +
									
									'<span style="width:6em;text-align:right;">专业级别：</span>' +
									'<div class="fl select">' +
									'<div class="tik_position">' +
									'<b class="ml5" id ="MajorLevel">请选择</b>' +
									'<p class="position01"><i class="bjt10"></i></p>' +
									'</div>' +
									'<ul class="fl list level" style="display:none;">' + str_ml +
									
									'</ul>' +
									'</div>' +
									'</p>' +
									
									'<p class="fl area_level">' +
									'<span style="width:6em;text-align:right;">专业排名：</span>' +
									'<div class="fl select">' +
									'<div class="tik_position">' +
									'<b class="ml5" id ="Major">请选择</b>' +
									'<p class="position01"><i class="bjt10"></i></p>' +
									'</div>' +
									'<ul class="fl list" id="majorList" style="display:none;">' + str_m +
									
									'</ul>' +
									'</div>' +
									'</p>' +
				
									'<p class="fl">' +
									'<span style="width:4em;text-align:right;">职称：</span>' +
									'<div class="fl select">' +
									'<div class="tik_position">' +
									'<b class="ml5" id ="duty_spec">请选择</b>' +
									'<p class="position01"><i class="bjt10"></i></p>' +
									'</div>' +
									'<ul class="fl list" style="display:none;">' + str_duty +
								
									'</ul>' +
									'</div>' +
									'</p>' +
									'<p style="margin-left:20px" class="fl cas_anniu"><a href="javascript:void(0);" class="fl" id= "add_Special" style="width:70px;margin-left:20px;">添加</a></p>' +
									'</div>';
									layer.open({
										type: 1,
										title: "医院专业排名、职称",
										skin: 'layui-layer-rim', //加上边框
										area: [win_w, win_h], //宽高
										content: title_cont,
										closeBtn: 0,
										btn: ['确定','取消'],
										yes: function (index, layero) {
											if($('#specialDisplay').text() == ''){
												alert('没有添加！');
												return false;
											}
											$('#special').val($('#add_function').val());
											$('#backmajorLevelIds').val($('#majorLevelIds').val());
											$('#backmajorIds').val($('#majorIds').val());
											$('#backdutyIds_special').val($('#dutyIds_special').val());
											layer.close(index);
										},														
										success: function(layerc, index){
										
											select_init();	
											
											$('.level li').click(function(){
												str_m_id = '<select id="sel_major" class="tk_list" style="display:none;">' + "\n";
												str_m_name='';
												str_m = '';
												var	param = 'mode=special&level='+$('#sel_majorLevel').val()+'&typeid='+${pageId};
												
												$.ajax({		    		    
												    type: 'POST',
												    url:"${ctx}/quality/userImageManage.do",
												   	data:param,
												    dataType: 'JSON',
												    success: function(B){	
												    	var str_user_image_temp = '';
												    	if(B.major != '' && B.major.length >0 ){				    	
													    	for(var i=0; i<B.major.length;i++){
													    		
													    		str_m_id += '<option value =' + B.major[i].id + '>' + B.major[i].name + '</option>'+"\n";
																str_m_name += '<li>' + B.major[i].name + '</li>' +"\n";
													    	}	
													    	str_m = str_m_id + '</select>' + "\n" + str_m_name;
													    	$('#majorList').html(str_m);
													    	select_init();
												    	}
												    	else{
												    		$('#Major').text('');
												    		$('#sel_major').html('<ul></ul>');
												    	}
													}
											    });
											});
																							
											$('#add_Special').click(function(){
											if($('#MajorLevel').text() == "请选择"){
												alert("请选择专业级别");
												return false;
											}
											if($('#duty_sepc').text() =="请选择" || $('#sel_duty').val() == '' || $('#Major').text == "请选择" || $('#sel_Major').val() == '')
											{
												alert("不可能添加特使人物画像！");
											}else{
													if(idCheck($('#majorIds').val(),$('#sel_major').val()) || idCheck($('#dutyIds_special').val(),$('#sel_duty_spec').val()))
													{
													
														var spec ="专业级别:" + $('#MajorLevel').text() +"->"+"医院专业排名:" + $('#Major').text() +"->"+"职称:" + $('#duty_spec').text() + "\n";
														var spec_ = '<em class="delem" onClick="delemSpec(this);">' + "专业级别:"+ $('#MajorLevel').text() +"&nbsp;&nbsp;" +"医院专业排名:"+ $('#Major').text() +"&nbsp;&nbsp;&nbsp;" + "职称:" +$('#duty_spec').text()+'</em>';
														
														selstr = $('#specialDisplay').html();
														selarray = selstr.split("<br>");
														var newnarray = new Array();
														$(selarray).each(function(key, val){
															if (val != "") newnarray.push(val);
														});
														newnarray.push(spec_);
														$('#specialDisplay').html(newnarray.toString().replace(dotexp,"<br>"));
														
														selstr = $('#add_function').val();
														selarray = selstr.split(";");
														var funcnarray = new Array();
														$(selarray).each(function(key, val){
															if (val != "") funcnarray.push(val);
														});
														funcnarray.push(spec);
														
														$('#add_function').val(funcnarray.toString().replace(dotexp,";"));	
														$('#majorLevelIds').val($('#majorLevelIds').val()+$('#sel_majorLevel').val() + ",");									
														$('#majorIds').val($('#majorIds').val()+$('#sel_major').val() + ",");
														$('#dutyIds_special').val($('#dutyIds_special').val()+$('#sel_duty_spec').val() +"," );
												}else {
													return ;
												}
											}
											});
											/* $(".confirm_btn").click(function(){
											
												if($('#specialDisplay').text() == ''){
													alert('没有添加！');
													return false;
												}
												$('#special').val($('#add_function').val());
												$('#backmajorIds').val($('#majorIds').val());
												$('#backdutyIds_special').val($('#dutyIds_special').val());
												layer.close(index);
											});	 */
										}
									});
									}								
								});
						}
					});
				});
			}
		});
	});
	
	

});

		
function updateImage(_id,_name){
	$('#prop_editId').val($('#propIds02').val());
	$('#edit_id').val(_id);
	$('#edit1').val(_name);
	
	var url= "${ctx}/quality/userImageManage.do";
	//var params = 'mode=uhaha&typeId=' + ${pageId};
	var params = 'mode=uhaha&typeId=' + _id;
	$.ajax({
		type: 'POST',
		url: url,
		data : params,
		dataType: 'JSON',
		success:function(R){
			var q='';
			var str_xueke = '';	   var str_xuekeId = '';
			var str_hospital = ''; var str_hospitalId = '';
			var str_special = '';  var str_special = '';
			
		 	/* for(var i=0;i<R.update_display.length;i++){		 	
		 		if(_id == R.update_display[i].id) q=i;
		 	}	 */
		 				 		  
 			for(var j=0; j<R.update_display[0].departmentPropList.length; j++){
 					str_xueke += R.update_display[0].departmentPropList[j].name + ",";
 					str_xuekeId += R.update_display[0].departmentPropList[j].id + ",";		 		    	
 		 	}	
 		 	$('#propNames02').text(str_xueke);
 		 	$('#propNames_').val(str_xueke);
 		 	$('#propIds02').val(str_xuekeId);
 		 	for(var j=0; j<R.update_display[0].generalUserImage.areaPropList.length; j++){
 		 		var str_area_tempId = ''; var str_hospital_tempId = ''; var str_duty_tempId = '';
				var str_hospital_temp = '';
				if(R.update_display[0].generalUserImage.areaPropList.length >= 1){
					str_hospital_temp += "地区:" + R.update_display[0].generalUserImage.areaPropList[j].name+ "->";
					str_area_tempId += R.update_display[0].generalUserImage.areaPropList[j].id+ ",";
				}
				else{
					str_hospital_temp += "";
					str_area_tempId += "";
				}
				$('#areaIds').val($('#areaIds').val()+str_area_tempId);
				
				if(R.update_display[0].generalUserImage.hospitalPropList.length >= 1){
					str_hospital_temp += "医院级别:" + R.update_display[0].generalUserImage.hospitalPropList[j].name+ "->";
					str_hospital_tempId += R.update_display[0].generalUserImage.hospitalPropList[j].id+ ",";
				}else {str_hospital_temp += "";
					   str_hospital_tempId += "";	
				}
				$('#hospitalIds').val($('#hospitalIds').val()+str_hospital_tempId);
				
				if(R.update_display[0].generalUserImage.dutyPropList.length >= 1){					
					str_hospital_temp += "职称:" + R.update_display[0].generalUserImage.dutyPropList[j].name+ ";";
					str_duty_tempId += R.update_display[0].generalUserImage.dutyPropList[j].id+ ",";
				}else {
					str_hospital_temp += "";
					str_duty_tempId += "";				
				}		
				$('#dutyIds').val($('#dutyIds').val()+str_duty_tempId);		
				str_hospital += str_hospital_temp;	 		    						
 		 	}	
 		 	$('#general').val(str_hospital);
		 	for(var j=0; j<R.update_display[0].specialUserImage.majorPropList.length; j++){
		 		var str_special_temp = '';var str_specialMajorLevelId_temp = '';var str_specialMajorId_temp = '';var str_specialDutyId_temp = '';
		 		if(R.update_display[0].specialUserImage.majorLevelPropList.length >= 1){
					str_special_temp += "专业级别:" + R.update_display[0].specialUserImage.majorLevelPropList[j].name+ "级别->";
					str_specialMajorLevelId_temp += R.update_display[0].specialUserImage.majorLevelPropList[j].id+ ",";
				}
				else{
					str_special_temp += "";
					str_specialMajorLevelId_temp += "";
				}
		 		$('#majorLevelIds').val($('#majorLevelIds').val()+str_specialMajorLevelId_temp);
		 		if(R.update_display[0].specialUserImage.majorPropList.length >= 1){
					str_special_temp += "医院专业排名:" + R.update_display[0].specialUserImage.majorPropList[j].name+ "->";
					str_specialMajorId_temp += R.update_display[0].specialUserImage.majorPropList[j].id+ ",";
				}
				else{
					str_special_temp += "";
					str_specialMajorId_temp += "";
				}	
				$('#majorIds').val($('#majorIds').val()+str_specialMajorId_temp);
				if(R.update_display[0].specialUserImage.dutyPropList.length >= 1){
					str_special_temp += "职称:" + R.update_display[0].specialUserImage.dutyPropList[j].name+ ";";
					str_specialDutyId_temp += R.update_display[0].specialUserImage.dutyPropList[j].id+ ",";
				}
				else{
					str_special_temp += "";
					str_specialDutyId_temp += "";
				}	
				$('#dutyIds_special').val($('#dutyIds_special').val()+str_specialDutyId_temp);
		 		str_special += str_special_temp;
		 		
		 	}
		 	$('#special').val(str_special);
		 	$('#backhospitalIds').val($('#hospitalIds').val());
			$('#backareaIds').val($('#areaIds').val());
			$('#backdutyIds').val($('#dutyIds').val());
			$('#backmajorIds').val($('#majorIds').val());
			$('#backmajorLevelIds').val($('#majorLevelIds').val());
			$('#backdutyIds_special').val($('#dutyIds_special').val());
			//有效性检查
			var regx = /^[\u4E00-\u9FA5A-Za-z0-9]+$/
		    var value = $('#edit1').val();//人物画像名称
		    if(!regx.test(value))
		    {
		        alert("人物画像名称只能输入 数字英文和汉字!");
		        return false;
		    }
		 },
		 error:function(){
		 	alert('修改失败!');
		 }
	});
	
	
}

function doDel(del_id) {

	if(!confirm("您确定要删除吗?")) return;	
	var url='${ctx}/quality/userImageManage.do?mode=delete&id='+del_id;
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
	  		alert('别的项目正在利用中，不能删除!');
	  },
	  error:function(){
	  	alert('删除失败!');
	  },
		
	});
}



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
function searchUserImage(){
	$('#propNames').val($('#propNames01').text());
	$('#sfrm').submit();
}
function delemGen(obj){
    //YHQ，2017-03-14
    tongYongHT.remove($(obj).html()) ;
    
		var i = $(obj).index()/2;
		//delete text
		var selstr = $(obj).parent().html();
		var selarray = selstr.split("<br>");
		var newnarray = new Array();
		$(selarray).each(function(key, val){
			if (key != i) newnarray.push(val);
		});
		$('#qqplayer').html(newnarray.toString().replace(dotexp, "<br>"));
		
		//delete view str
		var deletecode = "";
		selstr = $('#add_function').val();
		selarray = selstr.split(";");
		newarray = new Array();
		$(selarray).each(function(key, val){
			if (key != i) newarray.push(val);
			else
				deletecode = val;
		});
		$('#add_function').val(newarray.toString().replace(dotexp, ";"));
		
		//delete hospitalId
		var deletecode = "";
		selstr = $('#hospitalIds').val();
		selarray = selstr.split(",");
		newHosarray = new Array();
		$(selarray).each(function(key, val){
			if (key != i) newHosarray.push(val);
			else
				deletecode = val;
		});
		$('#hospitalIds').val(newHosarray.toString());
		//delete code
		var deletecode = "";
		selstr = $('#areaIds').val();
		selarray = selstr.split(",");
		newAreaarray = new Array();
		$(selarray).each(function(key, val){
			if (key != i) newAreaarray.push(val);
			else
				deletecode = val;
		});
		$('#areaIds').val(newAreaarray.toString());
		
		//delete code
		var deletecode = "";
		selstr = $('#dutyIds').val();
		selarray = selstr.split(",");
		newdeutyArray = new Array();
		$(selarray).each(function(key, val){
			if (key != i) newdeutyArray.push(val);
			else
				deletecode = val;
		});
		$('#dutyIds').val(newdeutyArray.toString());
		
		$('.delem').off('click');
		$('.delem').click(function(){
			delem($(this));
		});
		
	}
	function delemSpec(obj){
			var i = $(obj).index()/2;
			//delete text
			var selstr = $(obj).parent().html();
			var selarray = selstr.split("<br>");
			var newnarray = new Array();
			$(selarray).each(function(key, val){
				if (key != i) newnarray.push(val);
			});
			$('#specialDisplay').html(newnarray.toString().replace(dotexp, "<br>"));
			
			//delete view str
			var deletecode = "";
			selstr = $('#add_function').val();
			selarray = selstr.split(";");
			newarray = new Array();
			$(selarray).each(function(key, val){
				if (key != i) newarray.push(val);
				else
					deletecode = val;
			});
			$('#add_function').val(newarray.toString().replace(dotexp, ";"));
			
			//delete code
			var deletecode = "";
			selstr = $('#majorIds').val();
			selarray = selstr.split(",");
			newMajorarray = new Array();
			$(selarray).each(function(key, val){
				if (key != i) newMajorarray.push(val);
				else
					deletecode = val;
			});
			$('#majorIds').val(newMajorarray.toString());
			
			//delete code
			var deletecode = "";
			selstr = $('#dutyIds_special').val();
			selarray = selstr.split(",");
			newdeutyArray = new Array();
			$(selarray).each(function(key, val){
				if (key != i) newdeutyArray.push(val);
				else
					deletecode = val;
			});
			$('#dutyIds_special').val(newdeutyArray.toString());
			
			$('.delem').off('click');
			$('.delem').click(function(){
				delem($(this));
			});
		
	}
function idCheck(arrayVal,element)
{
	var index=0;
	var selArray = arrayVal.split(",");
	for(var i = 0 ; i < selArray.length ; i++)
	{
		if(selArray[i] == element)
		{
			index ++;
		}
	}
	if(index == 0)
		return true;
	else return false;
}
</script>

</body>
</html>
