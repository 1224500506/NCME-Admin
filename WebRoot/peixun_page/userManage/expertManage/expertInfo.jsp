<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<%@include file="/commons/taglibs.jsp"%>
</head>
<link rel="stylesheet" href="${ctx}/new_page/css/reset.css" />
<link rel="stylesheet" href="${ctx}/new_page/css/index.css" />
<script type="text/javascript" src="${ctx}/new_page/js/jquery.js"></script>
<style type="text/css">
	#gotoPage{
		cursor: pointer;
	}
</style>
<%@include file="/peixun_page/top.jsp"%>
<body class="bjs">
<div>
	<!-- 题库内容 -->
	<div class="center">
		<div class="tk_tk_xuanxiag" style="">
			<div class="xingzeng_k">
				<i></i><h2 class="fl">查看专家</h2>
			</div>
			<div class="thi_shitineirong" style="">
				<ul>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">专家姓名：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">${info.name}</div>
						</div>
						<div class="fl shitin_xinzeng01">
							<p class="fl ml30"><span class="fr">编码：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">${info.code}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">加入专委会：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">${info.groupNames}</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">职务：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">
								<c:if test="${info.office==1}">主任委员</c:if>
								<c:if test="${info.office==2}">副主任委员</c:if>
								<c:if test="${info.office==3}">秘书长</c:if>
								<c:if test="${info.office==4}">学组长</c:if>
								<c:if test="${info.office==5}">委员</c:if>
							</div>
						</div>
						<div class="clear"></div>
					</li>
<%-- 					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">学组：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">${info.subGroupName}</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">届期：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">${info.termStr}</div>
						</div>
						<div class="clear"></div>
					</li>
 --%>					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">任职状态：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">
								<c:if test="${info.state==1}">在职</c:if>
								<c:if test="${info.state==2}">离职</c:if>
							</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">离职时间：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">${info.breakDate.toString()}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">职称：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">${info.jobName}</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">状态：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">
								<c:if test="${info.lockState==0}">绑定</c:if>
								<c:if test="${info.lockState==1}">未绑定</c:if>
							</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">学科：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">${info.propNames}</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">工作单位：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">${info.workUnit}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">固定电话：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">${info.phone1}</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">手机号：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">${info.phone2}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">邮箱：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">${info.email}</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">秘书姓名：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">${info.clerkName}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">秘书电话：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">${info.clerkPhone}</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">国籍：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">
								<c:if test="${info.isNation==0}">中国</c:if>
								<c:if test="${info.isNation==1}">外国</c:if>
							</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">银行卡号：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">${info.bankCard}</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">开户行：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">${info.bank}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">身份证号：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">${info.identityNum}</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">专家照片：</span></p>
							<div class="fl exp_zhuanjiak" style="width:258px;">
							<img alt="" title="${info.name}" src="${info.photo}"  width="60px" height="80px">
							</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">简介：</span></p>
							<div class="fl exp_zhuanjiak" style="width:680px;">${info.summary}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li class="mt10">
						<div class="ca1_anniu" style="width:200px;">
							<a href="${ctx}/expert/ExpertManage.do?" class="fl ml30 anniu">返回</a>
						</div>
						<div class="clear"></div>
					</li>
					
				</ul>	
			</div>
		</div>
		
	</div>
	
		<!-- 试题内容（结束） -->
			
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
	

});
</script>
</body>
</html>