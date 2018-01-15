<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/commons/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>资源管理平台</title>
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
</head> 

<%@include file="/new_page/top.jsp"%>
  
  <body class="bjs">
	<!-- 题库内容 
	<div class="center">
		<div class="tk_center01" style="min-height:120px;">
			<div class="tk_zuo">
				<div class="tik_xuanze">
					<div class="mt20 fl tk_tixing">
						<em class="fl">角色：</em>
						<div class="fl tik_select" >
							<div class="tik_position_2">
								<b class="ml5">请选择</b>
								<p class="tik_position01"><i class="tk_bjt10"></i></p>
							</div>
							<ul class="fl tk_list_1 tk_list" style="display:none;">
								<li>请选择</li>
								<li>管理员</li>
								<li>医学编辑</li>
								<li>专家</li>
							</ul>
						</div>
					</div>
					<div class="mt20 fl ml20 tk_tixing">
						<span class="fl">创建时间：</span>
						<input type="text"  class="fl tik_select"/>
					</div>
					<div class="mt20 ml5 fl tk_tixing">
						<span class="fl">状态：</span>
						<div class="fl tik_select" >
							<div class="tik_position_2">
								<b class="ml5">请选择</b>
								<p class="tik_position01"><i class="tk_bjt10"></i></p>
							</div>
							<ul class="fl tk_list_1 tk_list" style="display:none;">
								<li>请选择</li>
								<li>启用</li>
								<li>禁用</li>
							</ul>
						</div>
					</div>
					
				</div>
				<div class="clear"></div>
				
			</div>
			<div class="clear"></div>
			<div class="mt20 tk_you shiti" style="">
				<a href="javascript:void(0);" class="fl tk_chaxun">查询</a>
				<a href="javascript:void(0);" class="fl ml30 tk_chaxun01">清空</a>
			</div>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>-->
	<!--  -->
	<div class="center none" style="">
	<div class="tk_center01">
		<div class="mt10 kit1_tiaojia">
			<div class="fr cas_anniu">
				<a href="javascript:void(0);" class="fl exp1_tianjia06" style="width:95px;">添加角色</a>
			</div>
		</div>
		<div class="clear"></div>
		<div style="height:30px;"></div>
		<display:table name="roles" requestURI="${ctx}/security/saveRole.do" style="font-size:12px;width: 100%;top:0px; margin:0px;padding:0px;" id="element" class="mt10 cas_table" size="${totalCount}" pagesize="20">
			<display:column
				title="序号"
				style="width:10%;text-align:center">
				${element_rowNum}
			</display:column>
			<display:column property="nameDesc" sortable="true" title="角色名" style="width:20%;text-align:center"></display:column>
			<display:column property="name" sortable="true" title="权限" style="width:35%;text-align:center"></display:column>
			<display:column title="状态" style="width:10%;text-align:center">
				<c:if test="${element.status eq 1}">启用</c:if>
				<c:if test="${element.status eq 0}">禁用</c:if>
			</display:column>
			<display:column title="操作" style="width:25%;text-align:center">
				<a href="javascript:showRole(${element.id},'${element.nameDesc}');" class="exp1_sty exp1_chakan01">查看</a>
				<a href="javascript:authority(${element.id});" class="exp1_sty exp1_chakan05">修改</a>
				<a href="${ctx}/security/deleteRole.do?&rid=${element.id}">
					<c:if test="${element.status eq 1}">禁用</c:if>
					<c:if test="${element.status eq 0}">启用</c:if>
				</a>
				<a href="${ctx}/security/deleteRole.do?did=${element.id}" >删除</a>
			</display:column>
				
			
		</display:table>
		<div class="clear"></div>
	</div>
</div>
		<!-- 试题内容（结束） -->
	

<div class="clear"></div>

<!-- 修改权限 -->
<form action="${ctx}/security/saveRolesData.do" method="post" name="dataFrm" id="dataFrm">
<div class="toumingdu  exp_tang05" style="display:none;">
	<div class="exper_tangkuang01" style="height:580px;width:1000px;background:#fff;border:1px solid #dddddd;padding:0px 0px 10px 0px;border-radius:8px;-moz-border-radius:8px;-webkit-border-radius:8px;-khtml-border-radius:8px;margin:7% auto;">
		<div class="exr_mingchengz">
		<input type="hidden" value="${rolesList}" name="rolesList" id="rolesList"/>
			<div class="thi_shitineirong" style="padding-top:20px;">
				<div class="sts1_border">
					<div style="width:100%;border-bottom:1px solid #dddddd;">
						<div class="shuxing_guanli">
							<p class="fl sts1_guanli">属性管理</p>
							<div class="fl sty1_quanxian">
								<span class="fl ml20">操作权限：</span>
								<p class="fl ml10"><input type="radio" name="yi11" checked="checked" value="只读"/><em class="ml10">只读</em></p>
								<p class="fl ml10"><input type="radio" name="yi11" value="可编辑"/><em class="ml10">可编辑</em></p>
							</div>
							<div class="fl sty1_quanxian" style="border-right:none;">
								<span class="fl ml20">数据权限：</span>
								<p class="fl ml10"><input type="radio" name="yi12"  checked="checked" value="学科"/><em class="ml10">学科</em></p>
								<p class="fl ml10"><input type="radio" name="yi12" value="全部"/><em class="ml10">全部</em></p>
							</div>
						</div>
						<div class="sts1_fuxuan">
							<p style="margin-left:68px;"><input type="checkbox" name="yi1_1" value="学科" class="fl"/><em class="fl ml10">学科</em></p>
							<p><input type="checkbox" name="yi1_2" value="ICD9" class="fl"/><em class="fl ml10">ICD9</em></p>
							<p><input type="checkbox" name="yi1_3" value="ICD10" class="fl"/><em class="fl ml10">ICD10</em></p>
							<p><input type="checkbox" name="yi1_4" value="主题" class="fl"/><em class="fl ml10">主题</em></p>
							<p><input type="checkbox" name="yi1_5" value="来源" class="fl"/><em class="fl ml10">来源</em></p>
							<p><input type="checkbox" name="yi1_6" value="认知水平" class="fl"/><em class="fl ml10">认知水平</em></p>
							<p><input type="checkbox" name="yi1_7" value="职称" class="fl"/><em class="fl ml10">职称</em></p>
						</div>
						<div class="clear"></div>
					</div>
					<div style="width:100%;border-bottom:1px solid #dddddd;">
						<div class="shuxing_guanli">
							<p class="fl sts1_guanli">题库管理</p>
							<div class="fl sty1_quanxian">
								<span class="fl ml20">操作权限：</span>
								<p class="fl ml10"><input type="radio" name="yi21" value="只读" /><em class="ml10">只读</em></p>
								<p class="fl ml10"><input type="radio" name="yi21" value="可编辑"/><em class="ml10">可编辑</em></p>
							</div>
							<div class="fl sty1_quanxian" style="border-right:none;">
								<span class="fl ml20">数据权限：</span>
								<p class="fl ml10"><input type="radio" name="yi22" value="学科"/><em class="ml10">学科</em></p>
								<p class="fl ml10"><input type="radio" name="yi22" value="全部"/><em class="ml10">全部</em></p>
							</div>
						</div>
						<div class="sts1_fuxuan">
							<p style="margin-left:68px;"><input type="checkbox" name="yi2_1" value="题库管理" class="fl"/><em class="fl ml10">题库管理</em></p>
							<p style="margin-left:80px;"><input type="checkbox" name="yi2_2" value="审核试题" class="fl"/><em class="fl ml10">审核试题</em></p>
						</div>
						<div class="clear"></div>
					</div>
					<div style="width:100%;border-bottom:1px solid #dddddd;">
						<div class="shuxing_guanli">
							<p class="fl sts1_guanli">素材管理</p>
							<div class="fl sty1_quanxian">
								<span class="fl ml20">操作权限：</span>
								<p class="fl ml10"><input type="radio" name="yi31" value="只读"/><em class="ml10">只读</em></p>
								<p class="fl ml10"><input type="radio" name="yi31" value="可编辑"/><em class="ml10">可编辑</em></p>
							</div>
							<div class="fl sty1_quanxian" style="border-right:none;">
								<span class="fl ml20">数据权限：</span>
								<p class="fl ml10"><input type="radio" name="yi32" value="学科"/><em class="ml10">学科</em></p>
								<p class="fl ml10"><input type="radio" name="yi32" value="全部"/><em class="ml10">全部</em></p>
							</div>
						</div>
						<div class="sts1_fuxuan">
							<p style="margin-left:68px;"><input type="checkbox" name="yi3_1" value="素材管理" class="fl"/><em class="fl ml10">素材管理</em></p>
							<p style="margin-left:80px;"><input type="checkbox" name="yi3_2" value="审核素材" class="fl"/><em class="fl ml10">审核素材</em></p>
						</div>
						<div class="clear"></div>
					</div>
					<div style="width:100%;border-bottom:1px solid #dddddd;">
						<div class="shuxing_guanli">
							<p class="fl sts1_guanli">专家管理</p>
							<div class="fl sty1_quanxian">
								<span class="fl ml20">操作权限：</span>
								<p class="fl ml10"><input type="radio" name="yi41" value="只读"/><em class="ml10">只读</em></p>
								<p class="fl ml10"><input type="radio" name="yi41" value="可编辑"/><em class="ml10">可编辑</em></p>
							</div>
							<div class="fl sty1_quanxian" style="border-right:none;">
								<span class="fl ml20">数据权限：</span>
								<p class="fl ml10"><input type="radio" name="yi42" value="学科"/><em class="ml10">学科</em></p>
								<p class="fl ml10"><input type="radio" name="yi42" value="全部"/><em class="ml10">全部</em></p>
							</div>
						</div>
						<div class="sts1_fuxuan">
							<p style="margin-left:68px;"><input type="checkbox" name="yi4_1" value="专家委员会" class="fl"/><em class="fl ml10">专家委员会</em></p>
							<p style="margin-left:70px;"><input type="checkbox" name="yi4_2" value="专家管理" class="fl"/><em class="fl ml10">专家管理</em></p>
						</div>
						<div class="clear"></div>
					</div>
					<div style="width:100%;border-bottom:1px solid #dddddd;">
						<div class="shuxing_guanli">
							<p class="fl sts1_guanli">病例管理</p>
							<div class="fl sty1_quanxian">
								<span class="fl ml20">操作权限：</span>
								<p class="fl ml10"><input type="radio" name="yi51" value="只读"/><em class="ml10">只读</em></p>
								<p class="fl ml10"><input type="radio" name="yi51" value="可编辑"/><em class="ml10">可编辑</em></p>
							</div>
							<div class="fl sty1_quanxian" style="border-right:none;">
								<span class="fl ml20">数据权限：</span>
								<p class="fl ml10"><input type="radio" name="yi52" value="学科"/><em class="ml10">学科</em></p>
								<p class="fl ml10"><input type="radio" name="yi52" value="全部"/><em class="ml10">全部</em></p>
							</div>
						</div>
						<div class="sts1_fuxuan">
							<p style="margin-left:68px;"><input type="checkbox" name="yi5_1" value="病例管理" class="fl"/><em class="fl ml10">病例管理</em></p>
							<p style="margin-left:80px;"><input type="checkbox" name="yi5_2" value="审核病例" class="fl"/><em class="fl ml10">审核病例</em></p>
						</div>
						<div class="clear"></div>
					</div>
					<div style="width:100%;">
						<div class="shuxing_guanli">
							<p class="fl sts1_guanli">系统维护</p>
							<div class="fl sty1_quanxian">
								<span class="fl ml20">操作权限：</span>
								<p class="fl ml10"><input type="radio" name="yi61" value="只读"/><em class="ml10">只读</em></p>
								<p class="fl ml10"><input type="radio" name="yi61" value="可编辑"/><em class="ml10">可编辑</em></p>
							</div>
							<div class="fl sty1_quanxian" style="border-right:none;">
								<span class="fl ml20">数据权限：</span>
								<p class="fl ml10"><input type="radio" name="yi62" value="学科"/><em class="ml10">学科</em></p>
								<p class="fl ml10"><input type="radio" name="yi62" value="全部"/><em class="ml10">全部</em></p>
							</div>
						</div>
						<div class="sts1_fuxuan">
							<p style="margin-left:68px;"><input type="checkbox" name="yi6_1" value="用户管理" class="fl"/><em class="fl ml10">用户管理</em></p>
							<p style="margin-left:80px;"><input type="checkbox" name="yi6_2" value="权限角色管理" class="fl"/><em class="fl ml10">权限角色管理</em></p>
							<p style="margin-left:80px;"><input type="checkbox" name="yi6_3" value="参数设置" class="fl"/><em class="fl ml10">参数设置</em></p>
						</div>
						<div class="clear"></div>
					</div>
				</div>
				<input type="text" style="display:none;" id="roleId" name="roleId"/>
				<div class="clear"></div>
				<div class="cas_cha" style="margin-left:240px;margin-top:13px;">
					<p class="fl cas_query"><a href="javascript:void(0);" id="go" class="hide01" >保存</a></p>
					<p class="fl cas_query"><a href="javascript:void(0);" class="hide01" >返回</a></p>
				</div>
				<div class="clear"></div>		
			</div>
		</div>
	
	</div>
</div>
</form>
<!-- 添加角色 -->
<form action="${ctx}/security/saveRole.do" name="saveFrm" id="saveFrm" method="post">
<div class="toumingdu  exp_tang06" style="display:none;">
	<div class="exper_tangkuang01" style="width:450px;height:300px;margin:200px auto;background:#fff;border-radius:8px;-moz-border-radius:8px;-webkit-border-radius:8px;-khtml-border-radius:8px;">
		<div class="exr_mingchengz">
			<div class="thi_shitineirong" style="width:450px;padding-top:50px;">
					<ul>
						<li class="mt15 ml30">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr">角色名称：</span></p>
								<input name="nameDesc" class="fl tki_bianji011"/>
							</div>
							<div class="clear"></div>
						</li>
						<li class="mt15 ml30">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr">权限：</span></p>
								<textarea name="name" cols="" rows="" class="tki_bianji011 stst_quanxian" style="width:202px;"></textarea>
							</div>
							<div class="clear"></div>
						</li>
						<li class="mt30">
							<div class="ca1_anniu" style="width:200px;">
								<a href="javascript:$('#saveFrm').submit();" class="fl hide02" style="margin-right:30px">保存</a>
								<a href="javascript:void(0);" class="fl hide01" >返回</a>
							</div>
							<div class="clear"></div>
						</li>
						
					</ul>	
					<div class="clear"></div>
				</div>
		</div>
	
	</div>
</div>
</form>
<!-- 查看权限 -->
<div class="toumingdu  exp_tang04" style="display:none;">
	<div class="exper_tangkuang01" style="width:450px;height:330px;margin:200px auto;background:#fff;border-radius:8px;-moz-border-radius:8px;-webkit-border-radius:8px;-khtml-border-radius:8px;">
		<div class="exr_mingchengz">
			
			<div class="thi_shitineirong" style="padding-top:30px;padding-left:50px;">
					<p style="font-size:16px;color:#333;margin-left:150px;" id = "roleName"></p>
					<ul style="margin-left:10px;">
						<li class="" style="margin-top:10px;">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr">属性管理：</span></p>
								<div class="fl" style="font-size:14px;color:#333;line-height:30px;"><span class="ml10" name="auth1" id="auth1"></span></div>
							</div>
							<div class="clear"></div>
						</li>
						<li class="" style="margin-top:-10px;">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr">题库管理：</span></p>
								<div class="fl" style="font-size:14px;color:#333;line-height:30px;"><span class="ml10" name="auth2" id="auth2"></span></div>
							</div>
							<div class="clear"></div>
						</li>
						<li class="" style="margin-top:-10px;">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr">素材管理：</span></p>
								<div class="fl" style="font-size:14px;color:#333;line-height:30px;"><span class="ml10" name="auth3" id="auth3"></span></div>
							</div>
							<div class="clear"></div>
						</li>
						<li class="" style="margin-top:-10px;">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr">专家管理：</span></p>
								<div class="fl" style="font-size:14px;color:#333;line-height:30px;"><span class="ml10" name="auth4" id="auth4"></span></div>
							</div>
							<div class="clear"></div>
						</li>
						<li class="" style="margin-top:-10px;">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr">病例管理：</span></p>
								<div class="fl" style="font-size:14px;color:#333;line-height:30px;"><span class="ml10" name="auth5" id="auth5"></span></div>
							</div>
							<div class="clear"></div>
						</li>
							<li class="" style="margin-top:-10px;">
							<div class="fl shitin_xinzeng04">
								<p class="fl"><span class="fr">系统维护：</span></p>
								<div class="fl" style="font-size:14px;color:#333;line-height:30px;"><span class="ml10" name="auth6" id="auth6"></span></div>
							</div>
							<div class="clear"></div>
						</li>
						<li class="">
							<div class="cas_cha">
								<p class="fl cas_query" style="margin-left:60px;"><a href="javascript:void(0);" class="hide01" >返回</a></p>
								
							</div>
							<div class="clear"></div>
						</li>
						
					</ul>	
					<div class="clear"></div>
				</div>
		</div>
	
	</div>
</div>

<script type="text/javascript">

var str = '';

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
	
	$('#go').click(function(){
		$('#dataFrm').action = "${ctx}/security/saveRolesData.do";
		$('#dataFrm').submit();
	});	
	$('.hide01').click(function(){
		$('.exp_tang04').hide();
		$('.exp_tang05').hide();
		$('.exp_tang06').hide();
	});
	$('.exp1_tianjia06').click(function(){
		$('.exp_tang06').show();
	});

});

/* function deleteRow(caseId, state){
	/* if (state == 1){
		alert ("无法删除！");
		return;
	} 
	var url = "${ctx}/security/deleteRole.do";
	if(caseId != null && caseId.val !=""){
		var params = "did="+caseId;  
		$.ajax({
					type: 'POST',
					url: url,
					data : params,
					dataType: 'text',
					success : function (newId){
						if(newId == "OH_OK")
						{
							alert('删除成功！');
						}
						else
						{
							alert('删除失败！');
						}
						 document.location.href = document.location.href.split("#")[0];
					},
					error: function(){
					   	alert('删除失败！');
						document.location.href = document.location.href.split("#")[0];
			} 
		});
	};
} ; */
	function showRole(id,roleName) {
	
		$('#auth1').text("");
		$('#auth2').text("");
		$('#auth3').text("");
		$('#auth4').text("");
		$('#auth5').text("");
		$('#auth6').text("");
		$('#roleName').text(roleName);
		var url = '${ctx}/security/getRolesList.do';
		var param = 'roleId=' + id;
			
			$.ajax({
				url: url,
				type: 'POST',
				data: param,
				dataType: 'json',
				success: function(result){
					if(result != null){
					
						var str1 = "";
						var str2 = "";
						var str3 = "";
						var str4 = "";
						var str5 = "";
						var str6 = "";
						
						for (var i = 0; i < result.length; i ++) {
							var rolesList = result[i];
							for (var j = 0; j < rolesList.length; j ++) {
								var role = rolesList[j];
								var manageKind = role.manageKind;
								var variety = role.variety;
								
								if (manageKind == 1) {
									var variety1 = variety.split(",")[0];
									if (variety1 == "null") {
										variety1 = "";
									}
									var variety2 = variety.split(",")[1];
									if (variety2 == "null") {
										variety2 = "";
									}
									var variety3 = variety.split(",")[2];
									if (variety3 == "null") {
										variety3 = "";
									}
									var variety4 = variety.split(",")[3];
									if (variety4 == "null") {
										variety4 = "";
									}
									var variety5 = variety.split(",")[4];
									if (variety5 == "null") {
										variety5 = "";
									}
									var variety6 = variety.split(",")[5];
									if (variety6 == "null") {
										variety6 = "";
									}
									var variety7 = variety.split(",")[6];
									if (variety7 == "null") {
										variety7 = "";
									}
									str1 = variety1 + "  " + variety2 + "  " + variety3 + "  " + variety4 + "  " + variety5 + "  " + variety6 + "  " + variety7;
									var string = str1;
									$('#auth1').text(str1);
								}
								
								if (manageKind == 2) {
									var variety1 = variety.split(",")[0];
									if (variety1 == "null") {
										variety1 = "";
									}
									var variety2 = variety.split(",")[1];
									if (variety2 == "null") {
										variety2 = "";
									}
									str2 = variety1 + "  " + variety2;
									$('#auth2').text(str2);
								}
								
								if (manageKind == 3) {
									var variety1 = variety.split(",")[0];
									if (variety1 == "null") {
										variety1 = "";
									}
									var variety2 = variety.split(",")[1];
									if (variety2 == "null") {
										variety2 = "";
									}
									str3 = variety1 + "  " + variety2;
									$('#auth3').text(str3);
								}
								
								if (manageKind == 4) {
									var variety1 = variety.split(",")[0];
									if (variety1 == "null") {
										variety1 = "";
									}
									var variety2 = variety.split(",")[1];
									if (variety2 == "null") {
										variety2 = "";
									}
									str4 = variety1 + "  " + variety2;
									$('#auth4').text(str4);
								}
								
								if (manageKind == 5) {
									var variety1 = variety.split(",")[0];
									if (variety1 == "null") {
										variety1 = "";
									}
									var variety2 = variety.split(",")[1];
									if (variety2 == "null") {
										variety2 = "";
									}
									str5 = variety1 + "  " + variety2;
									$('#auth5').text(str5);
								}
								
								if (manageKind == 6) {
									var variety1 = variety.split(",")[0];
									if (variety1 == "null") {
										variety1 = "";
									}
									var variety2 = variety.split(",")[1];
									if (variety2 == "null") {
										variety2 = "";
									}
									var variety3 = variety.split(",")[2];
									if (variety3 == "null") {
										variety3 = "";
									}
									str6 = variety1 + "  " + variety2 + "  " + variety3;
									$('#auth6').text(str6);
								}
							}
						}
					}	
				}
			});	
			$('.exp_tang04').show();
	}

	function authority(id) {
		
		$('#roleId').val(id);
		
		$("input[name='yi11']").attr("checked", "");
		$("input[name='yi12']").attr("checked", "");
		$("input[name='yi1_1']").attr("checked", "");
		$("input[name='yi1_2']").attr("checked", "");
		$("input[name='yi1_3']").attr("checked", "");
		$("input[name='yi1_4']").attr("checked", "");
		$("input[name='yi1_5']").attr("checked", "");
		$("input[name='yi1_6']").attr("checked", "");
		$("input[name='yi1_7']").attr("checked", "");
		$("input[name='yi21']").attr("checked", "");
		$("input[name='yi22']").attr("checked", "");
		$("input[name='yi2_1']").attr("checked", "");
		$("input[name='yi2_2']").attr("checked", "");
		$("input[name='yi31']").attr("checked", "");
		$("input[name='yi32']").attr("checked", "");
		$("input[name='yi3_1']").attr("checked", "");
		$("input[name='yi3_2']").attr("checked", "");
		$("input[name='yi41']").attr("checked", "");
		$("input[name='yi42']").attr("checked", "");
		$("input[name='yi4_1']").attr("checked", "");
		$("input[name='yi4_2']").attr("checked", "");
		$("input[name='yi51']").attr("checked", "");
		$("input[name='yi52']").attr("checked", "");
		$("input[name='yi5_1']").attr("checked", "");
		$("input[name='yi5_2']").attr("checked", "");
		$("input[name='yi61']").attr("checked", "");
		$("input[name='yi62']").attr("checked", "");
		$("input[name='yi6_1']").attr("checked", "");
		$("input[name='yi6_2']").attr("checked", "");
		$("input[name='yi6_3']").attr("checked", "");

		var url = '${ctx}/security/getRolesList.do';
		var param = 'roleId=' + id;
			
			$.ajax({
				url: url,
				type: 'POST',
				data: param,
				dataType: 'json',
				success: function(result){
				
					if(result != null){
					
						for (var i = 0; i < result.length; i ++) {
							var rolesList = result[i];
							for (var j = 0; j < rolesList.length; j ++) {
								var role = rolesList[j];
								
								var controlAuth= "";
								var manageKind = "";
								var statisticsAuth = "";
								var variety = "";
								
								controlAuth = role.controlAuth;
								manageKind = role.manageKind;
								statisticsAuth = role.statisticsAuth;
								variety = role.variety;
								
								if (manageKind == 1) {
									if (controlAuth == "只读") {
										$("input[name='yi11']").each(function(){
											if ($(this).val() == "只读") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									} else if (controlAuth == "可编辑") {
										$("input[name='yi11']").each(function(){
											if ($(this).val() == "可编辑") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									}

									if (statisticsAuth == "学科") {
										$("input[name='yi12']").each(function(){
											if ($(this).val() == "学科") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									} else if (statisticsAuth == "全部") {
										$("input[name='yi12']").each(function(){
											if ($(this).val() == "全部") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									}
									
									var str1 = variety.split(",")[0];
									if (str1 != "null") {
										$("input[name='yi1_1']").attr("checked", "checked");
									}
									var str2 = variety.split(",")[1];
									if (str2 != "null") {
										$("input[name='yi1_2']").attr("checked", "checked");
									}
									var str3 = variety.split(",")[2];
									if (str3 != "null") {
										$("input[name='yi1_3']").attr("checked", "checked");
									}
									var str4 = variety.split(",")[3];
									if (str4 != "null") {
										$("input[name='yi1_4']").attr("checked", "checked");
									}
									var str5 = variety.split(",")[4];
									if (str5 != "null") {
										$("input[name='yi1_5']").attr("checked", "checked");
									}
									var str6 = variety.split(",")[5];
									if (str6 != "null") {
										$("input[name='yi1_6']").attr("checked", "checked");
									}
									var str7 = variety.split(",")[6];
									if (str7 != "null") {
										$("input[name='yi1_7']").attr("checked", "checked");
									}
								}
								
								if (manageKind == 2) {
									if (controlAuth == "只读") {
										$("input[name='yi21']").each(function(){
											if ($(this).val() == "只读") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									} else if (controlAuth == "可编辑") {
										$("input[name='yi21']").each(function(){
											if ($(this).val() == "可编辑") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									}

									if (statisticsAuth == "学科") {
										$("input[name='yi22']").each(function(){
											if ($(this).val() == "学科") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									} else if (statisticsAuth == "全部") {
										$("input[name='yi22']").each(function(){
											if ($(this).val() == "全部") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									}
									
									var str1 = variety.split(",")[0];
									if (str1 != "null") {
										$("input[name='yi2_1']").attr("checked", "checked");
									}
									var str2 = variety.split(",")[1];
									if (str2 != "null") {
										$("input[name='yi2_2']").attr("checked", "checked");
									}
								}
								
								if (manageKind == 3) {
									if (controlAuth == "只读") {
										$("input[name='yi31']").each(function(){
											if ($(this).val() == "只读") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									} else if (controlAuth == "可编辑") {
										$("input[name='yi31']").each(function(){
											if ($(this).val() == "可编辑") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									}

									if (statisticsAuth == "学科") {
										$("input[name='yi32']").each(function(){
											if ($(this).val() == "学科") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									} else if (statisticsAuth == "全部") {
										$("input[name='yi32']").each(function(){
											if ($(this).val() == "全部") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									}
									
									var str1 = variety.split(",")[0];
									if (str1 != "null") {
										$("input[name='yi3_1']").attr("checked", "checked");
									}
									var str2 = variety.split(",")[1];
									if (str2 != "null") {
										$("input[name='yi3_2']").attr("checked", "checked");
									}
								}
								
								if (manageKind == 4) {
									if (controlAuth == "只读") {
										$("input[name='yi41']").each(function(){
											if ($(this).val() == "只读") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									} else if (controlAuth == "可编辑") {
										$("input[name='yi41']").each(function(){
											if ($(this).val() == "可编辑") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									}

									if (statisticsAuth == "学科") {
										$("input[name='yi42']").each(function(){
											if ($(this).val() == "学科") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									} else if (statisticsAuth == "全部") {
										$("input[name='yi42']").each(function(){
											if ($(this).val() == "全部") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									}
									
									var str1 = variety.split(",")[0];
									if (str1 != "null") {
										$("input[name='yi4_1']").attr("checked", "checked");
									}
									var str2 = variety.split(",")[1];
									if (str2 != "null") {
										$("input[name='yi4_2']").attr("checked", "checked");
									}
								}
								
								if (manageKind == 5) {
									if (controlAuth == "只读") {
										$("input[name='yi51']").each(function(){
											if ($(this).val() == "只读") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									} else if (controlAuth == "可编辑") {
										$("input[name='yi51']").each(function(){
											if ($(this).val() == "可编辑") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									}

									if (statisticsAuth == "学科") {
										$("input[name='yi52']").each(function(){
											if ($(this).val() == "学科") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									} else if (statisticsAuth == "全部") {
										$("input[name='yi52']").each(function(){
											if ($(this).val() == "全部") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									}
									
									var str1 = variety.split(",")[0];
									if (str1 != "null") {
										$("input[name='yi5_1']").attr("checked", "checked");
									}
									var str2 = variety.split(",")[1];
									if (str2 != "null") {
										$("input[name='yi5_2']").attr("checked", "checked");
									}
								}
								
								if (manageKind == 6) {
									if (controlAuth == "只读") {
										$("input[name='yi61']").each(function(){
											if ($(this).val() == "只读") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									} else if (controlAuth == "可编辑") {
										$("input[name='yi61']").each(function(){
											if ($(this).val() == "可编辑") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									}

									if (statisticsAuth == "学科") {
										$("input[name='yi62']").each(function(){
											if ($(this).val() == "学科") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									} else if (statisticsAuth == "全部") {
										$("input[name='yi62']").each(function(){
											if ($(this).val() == "全部") {
												$(this).attr("checked", "checked");
												return;
											} 
										});
									}
									
									var str1 = variety.split(",")[0];
									if (str1 != "null") {
										$("input[name='yi6_1']").attr("checked", "checked");
									}
									var str2 = variety.split(",")[1];
									if (str2 != "null") {
										$("input[name='yi6_2']").attr("checked", "checked");
									}
									var str3 = variety.split(",")[2];
									if (str3 != "null") {
										$("input[name='yi6_3']").attr("checked", "checked");
									}
								}
							}
							
						}
					}	
				}
			});
			$('.exp_tang05').show();
	}

</script>
  </body>
</html>
