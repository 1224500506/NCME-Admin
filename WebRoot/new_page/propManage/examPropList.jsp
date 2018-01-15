<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/new_page/top.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理平台</title>
</head>

<body class="bjs" onkeydown="entersearch();">
<div>
	<!-- 题库内容 -->
	<div class="center">
		<div class="tk_center01" style="min-height:40px;">
			<div class="tk_zuo">
			<c:if test="${type eq '1'}">
				<form id="sfrm" action="${ctx }/propManage/propList.do" method="POST">
			</c:if>
			<c:if test="${type ne '1'}">
				<form id="sfrm" action="${ctx }/propManage/propList.do?id=${prop_val1}&type=${type}&imgtype=${imgtypesel}" method="POST">
			</c:if>
			<input name="type" value="${type }" type="hidden">
			<input name="id" value="${prop_val1 }" type="hidden">
			<input name="imgtype" value="${imgtypesel }" type="hidden">
			
				<div class="tik_xuanze">
					<div class="mt10 fl tk_tixing">
						<em class="fl">
							<c:if test="${type eq '1'}">一级学科名称：</c:if>
							<c:if test="${type eq '2'}">二级学科名称：</c:if>
							<c:if test="${type eq '3'}">三级学科名称：</c:if>
							<c:if test="${type eq '4'}">单元名称：</c:if>
							<c:if test="${type eq '5'}">知识点名称：</c:if>
						</em>
						<input type="text"  class="fl tik_select" name="sname" value="${sname}"/>
					</div>
					<div class="mt10 fl mll8 tk_tixing">
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
				<div class="mt10 fl tk_tixing">
					<c:if test="${parents.get(0).type != null}"><em>${parents.get(0).name}</em></c:if><%-- <a href="${ctx}/propManage/propList.do?id=${parents.get(0).prop_val_id}&type=${parents.get(0).type+1}"></a> --%>
					<c:if test="${parents.get(1).type != null}"><em>>${parents.get(1).name}</em></c:if>
					<c:if test="${parents.get(2).type != null}"><em>>${parents.get(2).name}</em></c:if>
					<c:if test="${parents.get(3).type != null}"><em>>${parents.get(3).name}</em></c:if>
				</div>
				<div class="fr cas_anniu">
					<a href="javascript:void(0);" class="fl att_tianjia" style="width:95px;margin-right:15px;">
						<c:if test="${type==1}">添加一级学科</c:if>
						<c:if test="${type==2}">添加二级学科</c:if>
						<c:if test="${type==3}">添加三级学科</c:if>
						<c:if test="${type==4}">添加单元</c:if>
						<c:if test="${type==5}">添加知识点</c:if>
					</a> 
					<c:if test="${type!=1}"><a href="${ctx}/propManage/propList.do?id=${parentid}&type=${type-1}&imgtype=${imgtypesel}" class="fl" style="width:95px;">返回</a></c:if>
				</div>
			</div>
			<div class="clear"></div>

			<display:table name="propList" requestURI="${ctx}/propManage/propList.do" id="p" class="mt10 cas_table" size="${totalCount}" pagesize="20"  sort="list" defaultsort="1">
				<display:setProperty name="sort.amount" value="list" />
				<display:column title="序号" style="width:5%;" property="id"></display:column>
				<display:column title="名称" style="width:20%;" sortable="true" property="name"></display:column>
				<display:column title="类型" style="width:10%;" sortable="true" property="typeName"></display:column>
				<display:column title="编码" style="width:15%;" sortable="true" property="code"></display:column>
				<c:if test="${type==1}"><display:column title="人物画像类型" style="width:15%;" sortable="true" property="jobName"></display:column></c:if>
				<c:if test="${type!=1}"><display:column title="人物画像类型" style="width:15%;" property="jobName"></display:column></c:if>
				<display:column title="是否科室" style="width:10%;">
					<c:if test="${p.ext_type==1}">是</c:if>
					<c:if test="${p.ext_type!=1}">否</c:if>
				</display:column>
				<c:if test="${p.type==5}">
				<display:column title="已关联ICD10" style="width:10%;">
					<c:forEach items="${p.icdList}" var="icd">
						<c:if test="${icd.type==10}">  
							${icd.name} 
						</c:if>
					</c:forEach>
				</display:column>
				</c:if>
				<display:column title="操作" style="width:25%;">
					<a href="javascript:editPropShow('${p.id}','${p.name}','${p.code}','${p.ext_type}','${p.img_type}');">修改</a> 
					<a href="javascript:doDelProp(${p.id},${p.prop_val_id});">删除</a>
					<c:if test="${p.type>2}">
						<a href="javascript:movePropShow('${p.prop_val_id}','${p.type}','${p.name}','${p.id}');">移动属性</a>
					</c:if>
					<c:choose>
						<c:when test="${p.type==1}"> <a href="${ctx}/propManage/propList.do?id=${p.prop_val_id}&type=2&imgtype=${p.img_type}">二级学科列表</a></c:when>
						<c:when test="${p.type==2}"> <a href="${ctx}/propManage/propList.do?id=${p.prop_val_id}&type=3&imgtype=${p.img_type}">三级学科列表</a></c:when>
						<c:when test="${p.type==3}"> <a href="${ctx}/propManage/propList.do?id=${p.prop_val_id}&type=4&imgtype=${p.img_type}">单元列表</a></c:when>
						<c:when test="${p.type==4}"> <a href="${ctx}/propManage/propList.do?id=${p.prop_val_id}&type=5&imgtype=${p.img_type}">知识点列表</a></c:when>
						<c:when test="${p.type==5}"> <a href="javascript:moveQuestionShow('${p.id}','${p.name}');">移动试题</a><a href="javascript:moveMaterialShow('${p.id}','${p.name}');">移动素材</a></c:when>
					</c:choose>
				</display:column>
			</display:table>

			<div class="clear"></div> 
		</div>
	</div>
		<!-- 试题内容（结束） -->

<div class="clear"></div>
</div>
<!-- 学科弹出框 -->
<div class="toumingdu att_xueke" style="display:none;">
	<div class="TJ_cueke">
		<div class="fl shitin_xinzeng01" style="margin-top:45px;margin-left:40px;">
			<p class="fl" style="width:110px;"><span class="fr">人物画像类型：</span><em class="fr">*</em></p>
				<select class="fl att_bianji01" id="rsImg_type">
					<c:choose>
						<c:when test="${type==1}">
							<c:forEach items="${classList}" var="job">
								<option value="${job.code}">${job.name}人物类型</option>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach items="${classList}" var="job">
								<c:if test="${job.code==imgtypesel}">
									<option value="${job.code}">${job.name}人物类型</option>
								</c:if>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</select>
		</div>
		<div class="clear"></div>
		<div class="fl mt15 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:110px;"><span class="fr">
				<c:if test="${type==1}">学科名称：</c:if>
				<c:if test="${type==2}">学科名称：</c:if>
				<c:if test="${type==3}">学科名称：</c:if>
				<c:if test="${type==4}">单元名称：</c:if>
				<c:if test="${type==5}">知识点名称：</c:if>
			</span><em class="fr">*</em></p>
			<input type="text" maxlength="200" class="fl att_bianji01" id="rsName"/>
			<input type="hidden" id="rsType"  value="${type}" />
		</div>
		<div class="clear"></div>
		<div class="fl mt15 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:110px;"><span class="fr">
				<c:if test="${type==1}">学科编码：</c:if>
				<c:if test="${type==2}">学科编码：</c:if>
				<c:if test="${type==3}">学科编码：</c:if>
				<c:if test="${type==4}">单元编码：</c:if>
				<c:if test="${type==5}">知识点编码：</c:if>
			</span><em class="fr">*</em></p>
			<%-- <input type="text" class="fl att_bianji01" id="rsCode" readonly placeholder="自动生成"/>
			<input type="hidden" id="rsId" />
			<input type="hidden" id="rsmode" />
			<input type="hidden" id="rsProp_val1" value="${prop_val1}" /> --%>
			
			<input type="text" class="fl att_bianji01" maxlength="200"  id="rsCode" />
			<input type="hidden" id="rsId" />
			<input type="hidden" id="rsmode" />
			<input type="hidden" id="rsProp_val1" value="${prop_val1}" />
			
		</div>
		<div class="clear"></div>
		<div class="fl mt15 shitin_xinzeng01" style="margin-left:40px;">
			<p class="fl" style="width:110px;"><span class="fr">是否科室：</span><em class="fr">*</em></p>
			<select class="fl att_bianji01" id="rsExt_type">
				<option value='0'>否</option>
				<option value='1'>是</option>
			</select>
		</div>
		<div class="clear"></div>
		<div class="ca1_anniu" style="width:200px;">
			<a href="javascript:void(0);" class="fl anniu att_baocun">保存</a>
			<a href="javascript:void(0);" class="fl ml30 anniu att_fanhui">返回</a>
		</div>
	</div>
</div>
<!-- 移动属性弹出框 -->
<div class="toumingdu att_xueke01" style="display:none;">
	<div class="TJ_cueke">
		<div class="fl shitin_xinzeng01" style="margin-top:50px;margin-left:30px;">
			<p class="fl" style="width:125px;"><span class="fr">要移动学科名称：</span><em class="fr">*</em></p>
			<input type="hidden" id="cid" />
			<input type="hidden" id="ctype" />
			<input type="hidden" id="cpid" />
			<input type="text" class="fl att_bianji01" id="cname" readonly />
		</div>
		<div class="clear"></div>
 		<div class="fl shitin_xinzeng01" style="margin-top:20px;margin-left:30px;">
			<p class="fl" style="width:125px;"><span class="fr">目标学科名称：</span><em class="fr">*</em></p>
			<div class="fl att_bianji01" id="mname" style="color:#47474B;font-size:13px;line-height:30px"></div>
			<!-- <datalist id="ydsx_list01">
			</datalist> -->
		</div>
		<div class="clear"></div>
		<div class="fl mt20 shitin_xinzeng01" style="margin-left:30px;display:none;">
			<p class="fl" style="width:125px;"><span class="fr">目标学科编码：</span><em class="fr">*</em></p>
			<input type="text" class="fl att_bianji01" id="mcode" readonly/>
			<input type="hidden" id="mid" />
			<input type="hidden" id="mtype" />
			<input type="hidden" id="msid" />
		</div>
		<div class="clear"></div>
		<div style="height:20px;"></div>
		<div class="ca1_anniu" style="width:250px;">
<!-- 			<a href="javascript:void(0);" class="fl anniu att_jiancha">检查</a>
 -->			<a href="javascript:void(0);" class="fl ml30 anniu att_yidong">保存</a>
			<a href="javascript:void(0);" class="fl ml15 anniu att_quxiao">取消</a>
		</div>
	
	</div>
</div>
<!-- 属性弹出框 -->
<div class="toumingdu att_xueke02" style="display:none;">
	<div class="TJ_cueke">
		<div class="fl shitin_xinzeng01" style="margin-top:50px;margin-left:30px;">
			<p class="fl" style="width:125px;"><span class="fr">要移动学科名称：</span><em class="fr">*</em></p>
			<input id="qsid" type="hidden"/>
			<input type="text" class="fl att_bianji01" id="qsname" readonly />
		</div>
		<div class="clear"></div>
			<div class="fl mt20 shitin_xinzeng01" style="margin-left:30px;">
				<p class="fl" style="width:125px;"><span class="fr">目标学科名称：</span><em class="fr">*</em></p>
				<div class="fl att_bianji01" id="qmname" style="color:#47474B;font-size:13px;line-height:30px"></div>
<!-- 				<datalist id="ydst_list01">
				</datalist> -->
			</div>		
		<div class="clear"></div>
			<div class="fl mt20 shitin_xinzeng01" style="margin-left:30px;display:none;">
				<p class="fl" style="width:125px;"><span class="fr">目标学科编码：</span><em class="fr">*</em></p>
				<input type="text" class="fl att_bianji01" id="qmcode" readonly/>
				<input type="hidden" class="fl att_bianji01" id="qmid"/>
				<input type="hidden" class="fl att_bianji01" id="rid"/>
				<input type="hidden" class="fl att_bianji01" id="rType"/>
			</div>
		<div class="clear"></div>
		<div class="ca1_anniu" style="width:250px;">
<!-- 			<a href="javascript:void(0);" class="fl anniu att_jiancha02">检查</a>
 -->			<a href="javascript:void(0);" class="fl ml30 anniu att_yidoong02">保存</a>
			<a href="javascript:void(0);" class="fl ml15 anniu att_quxiao">取消</a>
		</div>
	
	</div>
</div>
<!-- 属性弹出框 -->
<div class="toumingdu att_xueke03" style="display:none;">
	<div class="TJ_cueke">
		<div class="fl shitin_xinzeng01" style="margin-top:50px;margin-left:30px;">
			<p class="fl" style="width:140px;"><span class="fr">要移动知识点名称：</span><em class="fr">*</em></p>
			<input id="ssid" type="hidden"/>
			<input type="text" class="fl att_bianji01" id="ssname"/>
		</div>
		<div class="clear"></div>
		<div class="fl mt20 shitin_xinzeng01" style="margin-left:30px;">
			<p class="fl" style="width:140px;"><span class="fr">目标知识点名称：</span><em class="fr">*</em></p>
			<div class="fl att_bianji01" id="smname" style="color:#47474B;font-size:13px;line-height:30px"></div>
			<!-- <datalist id="ydsc_list01">
			</datalist> -->
		</div>
		
		<div class="clear"></div>
			<div class="fl mt20 shitin_xinzeng01" style="margin-left:30px;display:none;">
			<p class="fl" style="width:140px;"><span class="fr">目标知识点编码：</span><em class="fr">*</em></p>
			<input type="text" class="fl att_bianji01" id="smcode" readonly/>
			<input type="hidden" id="smid"/>
			<input type="hidden" id="srid"/>
			<input type="hidden" id="srType"/>
		</div>
		<div class="clear"></div>
		<div class="ca1_anniu" style="width:250px;">
			<a href="javascript:void(0);" class="fl ml30 anniu att_yidoong03">保存</a>
			<a href="javascript:void(0);" class="fl ml15 anniu att_quxiao">取消</a>
		</div>
	
	</div>
</div>
<script type="text/javascript">
$(function(){
	//导航切换
/*	$('.tk_center>ul>li').mousemove(function(){
		$(this).addClass('action').siblings('.action').removeClass('action');
		var n=$(this).index();
		$('.tk_er div').eq(n).show().siblings().hide();
	});
*/	

	//select menu
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "mar_left0" + menuindex;
		$('.tk_mainmenu>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
	
	//查询学科
		$('.tk_chaxun').click(function(){
			$('#sfrm').submit();
			
		});	
	//添加学科
		$('.att_tianjia').click(function(){
			$('.att_xueke').show();
			$('#rsName').val('');
			$('#rsCode').val('');
			$('#rsExt_type').val('1');
			$('#rsImg_type').val('0');
			$('#rsmode').val('add');
            //IE9的情况下默认选中第一个
            if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE9.0"&&null==$('#rsImg_type').val()){
                $('#rsImg_type').each(function (i, j) {
                    $(j).find("option:selected").attr("selected", false);
                    $(j).find("option").first().attr("selected", true);
                })
            }
		});
	//保存
		$('.att_baocun').click(function(){

            if(null==$('#rsImg_type').val()){
                alert("请选择人物画像！");
                return;
			}

			if (!$('#rsName').val()){
				alert("请输入名称！");
				return;
			}
			
			var rsName = $('#rsName').val();
			if (rsName.indexOf(" ")>-1){
				alert("名称不能为空格！");
				return;
			}
			
			if (rsName.length>50){
				alert("学科名称长度不能大于50！");
				return;
			}
			
			if (rsName.indexOf("&")>-1){
				alert("名称中不能包含'&'！");
				return;
			}
			
			if (!$('#rsCode').val()){
				alert("请输入编码！");
				return;
			}
			
			if ($('#rsCode').val().indexOf(" ")>-1){
				alert("编码不能为空格！");
				return;
			}
			
			if ($('#rsCode').val().length>20){
				alert("学科编码长度不能大于20！");
				return;
			}
			
			if ($('#rsmode').val() == 'add'){
				var url = '${ctx}/propManage/addProp.do?source=xueke';
			}
			else if ($('#rsmode').val() == 'edit'){
				var url = '${ctx}/propManage/editProp.do?source=xueke';
				if (!$('#rsId').val())
					return;
			}

			var params = '';
			params = 'propName=' + $('#rsName').val();
			params += '&id='+$('#rsId').val();
			params += '&code='+$('#rsCode').val();
			params += '&type='+$('#rsType').val();
			params += '&prop_val1='+$('#rsProp_val1').val();
			params += '&ext_type='+$('#rsExt_type').val();
			params += '&img_type='+$('#rsImg_type').val();
			
			$.ajax({
					type: 'POST',
					url: url,
					data : params,
					dataType: 'text',
					success : function (b){
					      if ($(rsmode).val() == 'add') {
					      	if (b == "success") {
					      		alert('添加成功！');
					      		$('.att_xueke').hide();
					      		//添加项目时重新生成学科导航页面
													$.ajax({
										                //接口地址
										                url:'${ctxQiantaiURL}/courseManage/courseList.do?logIndex=false&eh=626',
										                //请求方式
										                type:'post',
										                //返回数据类型
										                dataType:'json',
										                //请求成功时处理方式
										                success:function(result){
										                },
										            });
					      	}else {
					      		alert('名称或者编码重复，无法保存！');
								return false;
							}
					      } else {
					      	if (b == "success"){
						      	alert('修改成功！');
						      	//添加项目时重新生成学科导航页面
													$.ajax({
										                //接口地址
										                url:'${ctxQiantaiURL}/courseManage/courseList.do?logIndex=false&eh=626',
										                //请求方式
										                type:'post',
										                //返回数据类型
										                dataType:'json',
										                //请求成功时处理方式
										                success:function(result){
										                },
										            });
					      	}else
						   		alert('名称或者编码重复，无法保存！');
					      }
					      document.location.href = document.location.href.split("#")[0];
					},
					error: function(){
					   	  if ($(rsmode).val() == 'add')
					   		alert('添加失败!');
					   	  else
					   	  	alert('修改失败!');
					   	  $('.att_xueke').hide();
					}
				});
			
			//$('.att_xueke').hide();
		});
	//返回
		$('.att_fanhui').click(function(){
			$('.att_xueke').hide();
		});
	
	
	//移动属性
		$('.att_jiancha').click(function(){
			checkTargetPropId();
		});
		function checkTargetPropId(){
			if (!$('#mid').val()){
				alert ('请输入目标学科名称'); return ;
			}
			
			var url = '${ctx}/propManage/viewProp.do';
			var params = 'id=' + $('#mid').val();
			
			$.ajax({
					type: 'POST',
					url: url,
					data : params,
					dataType: 'text',
					success: function(result){
						if (result != ''){
							var tmp = result.split('||');
							//$('#mname').val(tmp[0]);
							$('#msid').val(tmp[1]);
							$('#mtype').val(tmp[2]);
							MovePropPost();
						}
						else {
							//$('#mname').val('');
							$('#msid').val('');
							$('#mtype').val('');
							alert('没有找到可用属性！');
						}
					}
			});
		}
	//移动属性	
		$('.att_yidong').click(function(){
			checkTargetPropId();
		});
		function MovePropPost(){
			if (!$('#msid').val()){
				alert ('目标属性不存在!'); return ;
			}
			if(($('#ctype').val()-1)!=$('#mtype').val()){
				alert('所移动的级别不符合!'); return;
			}
			
			var url = '${ctx}/propManage/moveProp.do';
			var params = 'prop_val1=' + $('#msid').val();
			params += '&prop_val_id='+$('#cid').val();
			params += '&id='+$('#cpid').val();
			params += '&type='+$('#ctype').val();

			$.ajax({
					type: 'POST',
					url: url,
					data : params,
					dataType: 'text',
					success: function(result){
						if (result == 'success'){
							alert ('移动成功!');
							document.location.href = document.location.href.split("#")[0];
						}
						else {
							alert('移动失败');
						}
					}
			});
		}
	//取消
		$('.att_quxiao').click(function(){
			$('.att_xueke01').hide();
			$('.att_xueke02').hide();
			$('.att_xueke03').hide();
		});

	//移动试题
		$('.att_jiancha02').click(function(){
			checkTargetPropId02();
		});
		function checkTargetPropId02(){
			if (!$('#qmid').val()){
				alert ('请输入目标学科'); return ;
			}
			
			var url = '${ctx}/propManage/viewProp.do';
			var params = 'id=' + $('#qmid').val();
			
			$.ajax({
					type: 'POST',
					url: url,
					data : params,
					dataType: 'text',
					success: function(result){
						if (result != ''){
							var tmp = result.split('||');
							//$('#qmname').val(tmp[0]);
							$('#rid').val(tmp[1]);
							$('#rtype').val(tmp[2]);
							MovePropPost02();
						}
						else {
							//$('#qmname').val('');
							$('#rid').val('');
							$('#rtype').val('');
							alert('没有找到可用属性！');
						}
					}
			});
		}
	//移动试题
		$('.att_yidoong02').click(function(){
			checkTargetPropId02();
		});
		function MovePropPost02(){
			if (!$('#rid').val()){
				alert ('目标属性不存在!'); return ;
			}
			if(($('#qsid').val())==$('#rid').val()){
				alert('标属性与移动属性相同!'); return;
			}
			
			var url = '${ctx}/propManage/replaceQuestionProp.do';
			var params = 'prop_val_id=' + $('#rid').val();
			params += '&id='+$('#qsid').val();
			params += '&type=4';

			$.ajax({
					type: 'POST',
					url: url,
					data : params,
					dataType: 'text',
					success: function(result){
						if (result == 'success'){
							alert ('移动成功!');
							document.location.href = document.location.href.split("#")[0];
						}
						else {
							alert('该知识点没有任何关联试题！');
						}
					}
			});
			
		}
	
	//移动素材
		$('.att_jiancha03').click(function(){
			checkTargetPropId03();
		});
		function checkTargetPropId03(){
			if (!$('#smid').val()){
				alert ('请输入目标知识点！'); return ;
			}
			
			var url = '${ctx}/propManage/viewProp.do';
			var params = 'id=' + $('#smid').val();
			
			$.ajax({
					type: 'POST',
					url: url,
					data : params,
					dataType: 'text',
					success: function(result){
						if (result != ''){
							var tmp = result.split('||');
							//$('#smname').val(tmp[0]);
							$('#srid').val(tmp[1]);
							$('#srtype').val(tmp[2]);
							MovePropPost03();
						}
						else {
							//$('#smname').val('');
							$('#srid').val('');
							$('#srtype').val('');
							alert('没有找到可用属性！');
						}
					}
			});
		}
	
		$('.att_yidoong03').click(function(){
			checkTargetPropId03();
		});
		function MovePropPost03(){
			if (!$('#srid').val()){
				alert ('目标属性不存在!'); return ;
			}
/* 			if ($('#srtype').val()!=5){
				alert ('所移动的级别不符合!'); return ;
			}
 */			if(($('#ssid').val())==$('#srid').val()){
				alert('标属性与移动属性相同!'); return;
			}
			
			window.location.reload(true);
			var url = '${ctx}/propManage/replaceMaterialProp.do';
			var params = 'prop_val_id=' + $('#srid').val();
			params += '&id='+$('#ssid').val();
			params += '&type='+$('#srtype').val();

			$.ajax({
					type: 'POST',
					url: url,
					data : params,
					dataType: 'text',
					success: function(result){
						if (result == 'success'){
							alert ('移动成功!');
							document.location.href = document.location.href.split("#")[0];
						}
						else {
							alert('该知识点没有任何关联素材！');
						}
					}
			});
			
		}
	
});

//表示修改页面
function editPropShow(_id,_name,_code,_ext_type,_img_type){
	$('.att_xueke').show();
	$('#rsName').val(_name);
	$('#rsCode').val(_code);
	$('#rsExt_type').val(_ext_type);
	$('#rsImg_type').val(_img_type);
	$('#rsId').val(_id);
	$('#rsmode').val('edit');
}

//删除
var force_del_url;
var force_del_params;
function doDelProp(id,prop_val_id){

	if(!confirm("您确定要删除吗？"))return;
	var url = '${ctx}/propManage/delProp.do';
	if(id!='' && prop_val_id!=''){
		var params = 'id=' +id;
		params +='&prop_val_id='+prop_val_id
	}
	//本页面发起的删除
	params +='&dflag=1';
	force_del_url = url;
	force_del_params = params;
	$.ajax({
			type: 'POST',
			url: url,
			data : params,
			dataType: 'text',
			success : function (b){
			      if (b == 'success'){
			      	  alert('删除成功！');
			      	  document.location.href = document.location.href.split("#")[0];
			      }	else if(b == 'hassub'){
			    	  alert('该学科存在下级学科，不可删除！若要删除，请先将下级学科删除后才能删除本学科！');
				      document.location.href = document.location.href.split("#")[0];
			      }else if(b == 'used'){
			    	  alert('该学科已被使用，不可删除！');
				      document.location.href = document.location.href.split("#")[0];
			      }
			      else{
			    	  alert("删除失败，请检查属性的关联!");
			      }
			      	/* if(confirm('该属性已被使用，若删除，则使用该属性的地方均被删除，确定删除!')){
		   			
					var url = force_del_url;
					var params = force_del_params + "&type=-100";
				
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
						      alert('删除失败！');
						   };
					    }
					 });
		   		} */
			},
			error: function(){
			   	  alert('删除失败请检查属性的关联!');
			}
		});
}

//表示属性页面
function movePropShow(_id,_type,_name,_pid){
	$('.att_xueke01').show();	
	$('#cid').val(_id);
	$('#ctype').val(_type);
	$('#cpid').val(_pid);

	$('#cname').val(_name);
	$('#mname').val('');
	$('#mid').val('');
	$('#mtype').val('');
	$('#mcode').val('');
	
	var url = '${ctx}/propManage/getPropListAjax.do?type=${type}'//;id='+${parentid};
	
	$.ajax({
		type: 'POST',
		url: url,
		dataType: 'json',
		success:function(result){
			
			datalist = "";
			for (item in result.list){
				datalist += "<option value='"+result.list[item].name+"'></option>";
			}
			$('#ydsx_list01').html(datalist);
		}
	});

}

//表示移动试题页面
function moveQuestionShow(_id, _name){
	$('.att_xueke02').show();
	$('#qsid').val(_id);
	$('#qsname').val(_name);

	$('#rid').val('');
	$('#qmid').val('');
	$('#qmname').val('');
	$('#qmcode').val('');

	var url = '${ctx}/propManage/getPropListAjax.do?type=5';
	
	$.ajax({
		type: 'POST',
		url: url,
		dataType: 'json',
		success:function(result){
			
			datalist = "";
			for (item in result.list){
				datalist += "<option value='"+result.list[item].name+"'></option>";
			}
			$('#ydst_list01').html(datalist);
			
		}
	});
}

//表示移动素材页面
function moveMaterialShow(_id, _name){
	$('.att_xueke03').show();
	$('#ssid').val(_id);
	$('#ssname').val(_name);

	$('#srid').val('');
	$('#smid').val('');
	$('#smname').val('');
	$('#smcode').val('');


	var url = '${ctx}/propManage/getPropListAjax.do?type=5';
	
	$.ajax({
		type: 'POST',
		url: url,
		dataType: 'json',
		success:function(result){
			
			datalist = "";
			for (item in result.list){
				datalist += "<option value='"+result.list[item].name+"'></option>";
			}
			$('#ydsc_list01').html(datalist);
			
		}
	});
}
/* 
function searchIdByName(){
	var Name = $('#mname').val();
	var url = '${ctx}/propManage/propList.do?mode=searchIdByName&name='+Name;
	
	$.ajax({
		type: 'POST',
		url: url,
		
		dataType: 'json',
		success:function(result){
			
			if (result !=null && result != '' && result.result.length > 0) {
			 	$('#mcode').val(result.result[0].code);
			 	$('#mid').val(result.result[0].id);
			 	//$('#mname').val(result.result[0].name);
			} else {
				$('#mid').val('');
				$('#mcode').val('');
			}
		}
	});
}

$('#qmname').keyup(function(){
	searchIdByName02();
});
$('#qmname').blur(function(){
	searchIdByName02();
});
function searchIdByName02(){
	var Name = $('#qmname').val();
	var url = '${ctx}/propManage/propList.do?mode=searchIdByName&name='+Name;	
	$.ajax({
		type: 'POST',
		url: url,
		
		dataType: 'json',
		success:function(result){
			
			if (result !=null && result != '' && result.result.length > 0) {
			 	$('#qmid').val(result.result[0].id);
			 	$('#qmcode').val(result.result[0].code);
			 	$('#qmname').val(result.result[0].name);
			} else {
				$('#qmid').val('');
				$('#qmcode').val('');
			}
		}
	});
}
$('#smname').keyup(function(){
	searchIdByName03();
});
$('#smname').blur(function(){
	searchIdByName03();
});
function searchIdByName03(){
	var Name = $('#smname').val();
	var url = '${ctx}/propManage/propList.do?mode=searchIdByName&name='+Name;	
	$.ajax({
		type: 'POST',
		url: url,
		
		dataType: 'json',
		success:function(result){
			
			if (result !=null && result != '' && result.result.length > 0) {
			 	$('#smid').val(result.result[0].id);
			 	$('#smcode').val(result.result[0].code);
			 	$('#smname').val(result.result[0].name);
			} else {
				$('#smid').val('');
				$('#smcode').val('');
			}
		}
	});
/* 	var name = $('#smname').val();
	
	var strName ='', strId = '';
	$.ajax({
		type:'post',
		url:'${ctx}/material/MaterialManage.do',
		data:'mode=getByAjax&name='+name,
		dataType:'json',
		success:function(Material){
			if(Material !=null && Material != '' && Material.list.length > 0){
				strName = Material.list[0].name;
				$('#smname').val(strName);
				$('#smid').val(Material.list[0].id);
			} else {
				$('#smid').val('');
			}
		}
	}); * /
} */

	$('#mname').click(function(){
		initPropList("学科","${ctx}/propManage/getPropListAjax.do", 1, 0, ${type-1}, ${type-1}, $('#mid'), $('#mname'));
		$('.att_make01').show();
	});
	
	$('#qmname').click(function(){
		initPropList("学科","${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 5, $('#qmid'), $('#qmname'));
		$('.att_make01').show();
	});
	
	$('#smname').click(function(){
		initPropList("学科","${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 5, $('#smid'), $('#smname'));
		$('.att_make01').show();
	});
	
	function updatePropList(result){
		
		var str = "";
		var check = eval($('.xs_checklvl').text());
		var select = eval($('.xs_selectlvl').text());
		$(result.list).each(function(key, value){
			str += "<li><div class=''>";
			if (check <= value.type)
				str += '<input class="fl" style="margin-top:5px;" type="radio" name="_selradio">';

			if ($(kuangcode).prop('id') == "icdIds")
				str += '<p class="fl attr_xueke04"' + ' id="'+ value.id +'"' + '><em class="fl">' + value.name + '</em>';
			else
				str += '<p class="fl attr_xueke04"' + ' id="'+ value.prop_val_id +'"' + '><em class="fl">' + value.name + '</em>';
			
			if (select > value.type){
				str += '<i class="fl ml10 kti_bjt2"></i>';
			}
			str += "</div><div class='clear'></div></li>";
		});
		
		$('.xs_ul').html(str);
		initPropWndProp();
		initPropWndRadio();
	}
	
	function initPropWndRadio(){
	
		$('.xs_ul input[type="radio"]').off('click');
		$('.xs_ul input[type="radio"]').click(function(){
			var p = $(this).parent().find('.attr_xueke04').eq(0);
			var id = $(p).prop('id');
			var propname = '<em class="delem">';
			if (viewpath) propname+= $('.xs_biaoti').text().trim();
			propname+= $(p).find('em').text() + '</em><br>';
			
			if ($(this).prop('checked')){
				$('.xs_kuangcode').text(id.toString());

				$('.xs_kuang').html(propname.toString().replace(dotexp,"<br>"));
			}
			else{
				$('.xs_kuangcode').text('');
				$('.xs_kuang').html('');
			}
			
			$('.delem').off('click');
			$('.delem').click(function(){
				delem($(this));
			});
		
		});
		
		//selected item mark checked.
		$('.xs_ul input[type="radio"]').each(function(key, val){
			var p = $(this).parent().find('.attr_xueke04').eq(0);
			var id = $(p).prop('id');
			var selstr = $('.xs_kuangcode').text();
			var selarray = selstr.split(",");
			var idx = selarray.indexOf(id);
			
			if (idx>=0) $(this).prop("checked", true);
		});
	}
	
	function selectProp(){
		$(kuangcode).val($('.xs_kuangcode').text());
		var selPropHtml = $('.xs_kuang').html().replace(brexp,"");
		$('.xs_kuang').html(selPropHtml);
		$(kuang).text($('.xs_kuang').text());
	}
	
	function entersearch(){
	   var event = window.event || arguments.callee.caller.arguments[0];
	   if (event.keyCode == 13) {
		   $('#sfrm').submit();
	   }
	}
		
</script>
</body>
</html> 