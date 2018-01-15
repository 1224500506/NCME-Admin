<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/taglibs.jsp"%>
<%@include file="/new_page/top.jsp"%>
<script type="text/javascript" src="${ctx}/new_page/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/new_page/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/new_page/js/fileUpload.js"></script>
<title>资源管理平台</title>
<!--[if IE]>
	<script type="text/javascript">var ieFlag = true ;</script>                 
<![endif]-->
<style>
#workunit01 {
				position: absolute;
				top: 32px;
				width: 259px;
				max-height: 400px;
				overflow: auto;
				list-style: none;	
				border: 1px solid #778899;
				border-top: none; 
				background-color:#fff;
				font-size:14px;
		 	}
		 	#workunit01 li:hover{
				background-color: #87CEFA;
				font-size:14px;
				color:#666;
		 	}
</style>
</head>

<body class="bjs">
<input id="workUnitHasChecked" type="hidden" value="1" />
<div>
	<!-- 题库内容 -->
	<div class="center">
		<div class="tk_tk_xuanxiag" style="">
			<div class="xingzeng_k">
				<i></i><h2 class="fl">
							<c:if test="${info.id == null}">添加专家</c:if>
							<c:if test="${info.id != null}">修改专家</c:if>
						</h2>
			</div>
			<form id="frm" action="${ctx}/expert/ExpertManage.do?mode=save" method="POST" enctype="multipart/form-data">
			<input type="hidden" id="flag" name="flag" value="${flag}" /> 
			<div class="thi_shitineirong" style="">
				<ul>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">专家姓名：</span><em class="fr">*</em></p>
							<input type="hidden" name="id" id="id" value="${info.id}" />
							<input type="text" id="rsName" class="fl tki_bianji" style="width:258px;" name="name" value="${info.name}"/>
						</div>
						<div class="fl shitin_xinzeng01">
							<p class="fl ml30"><span class="fr">编码：</span><em class="fr">*</em></p>
							<input type="text" id="rsCode" class="fl tki_bianji" style="width:258px;" name="code" value="${info.code}"  readonly placeholder="自动生成"/>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">性别：</span></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">请选择</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list" style="display:none;">
								<select name="sex" style="display:none;">
									<option value="0" <c:if test="${info.sex eq 0}"> selected</c:if>>请选择</option>
	                                <option value="1" <c:if test="${info.sex eq 1}"> selected</c:if>>男</option>
	                                <option value="2" <c:if test="${info.sex eq 2}"> selected</c:if>>女</option>
								</select>
									<li>请选择</li>
									<li>男</li>
									<li>女</li>
								</ul>
							</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">学历：</span></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">请选择</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list" style="display:none;">
								<select name="grade" style="display:none;">
									<option value="0" <c:if test="${info.grade eq 0}"> selected</c:if>>请选择</option>
	                                <option value="1" <c:if test="${info.grade eq 1}"> selected</c:if>>高中</option>
	                                <option value="2" <c:if test="${info.grade eq 2}"> selected</c:if>>中专</option>
	                                <option value="3" <c:if test="${info.grade eq 3}"> selected</c:if>>大专</option>
	                                <option value="4" <c:if test="${info.grade eq 4}"> selected</c:if>>本科</option>
	                                <option value="5" <c:if test="${info.grade eq 5}"> selected</c:if>>硕士</option>
	                                <option value="6" <c:if test="${info.grade eq 6}"> selected</c:if>>博士</option>
	                                <option value="7" <c:if test="${info.grade eq 7}"> selected</c:if>>博士后</option>
	                                <option value="8" <c:if test="${info.grade eq 8}"> selected</c:if>>其它</option>
								</select>
									<li>请选择</li>
									<li>高中</li>
									<li>中专</li>
									<li>大专</li>
									<li>本科</li>
									<li>硕士</li>
									<li>博士</li>
									<li>博士后</li>
									<li>其它</li>
								</ul>
							</div>
						</div>
						<div class="clear"></div>
					</li>
					<%-- <li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">加入专委会：</span></p>
							<input type="hidden" class="fl tik_select" id="newgroupIds" name="groupIds" value="${info.groupIds}"/>
							<div id="newgroupNames" class="fl tki_bianji takuang_zy">${info.groupNames}</div>

							<p class="fl"><span class="fr">加入专委会：</span></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">请选择</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list list_group" style="display:none;">
								<select name="groupId" style="display:none;">
									<option value="0">请选择</option>
									<c:forEach items="${grouplist}" var="group">
										<option value="${group.id}"<c:if test="${group.id==info.groupId}"> selected</c:if>>${group.name}</option>
									</c:forEach>
								</select>
									<li>请选择</li>
									<c:forEach items="${grouplist}" var="group">
										<li>${group.name}</li>
									</c:forEach>
								</ul>
							</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">职务：</span></p>
							
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">请选择</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list" style="display:none;">
								<select name="office" style="display:none;">
									<option value="">请选择</option>
									<option value="1"<c:if test="${info.office==1}"> selected</c:if>>主任委员</option>
									<option value="2"<c:if test="${info.office==2}"> selected</c:if>>副主任委员</option>
									<option value="3"<c:if test="${info.office==3}"> selected</c:if>>秘书长</option>
									<option value="4"<c:if test="${info.office==4}"> selected</c:if>>学组长</option>
									<option value="5"<c:if test="${info.office==5}"> selected</c:if>>委员</option>
								</select>
									<li>请选择</li>
									<li>主任委员</li>
									<li>副主任委员</li>
									<li>秘书长</li>
									<li>学组长</li>
									<li>委员</li>
								
								</ul>
							</div>
							
							<input type="hidden" class="fl tik_select" id="officeIds" name="officeIds" value="${info.officeIds}"/>
							<div id="officeNames" class="fl tki_bianji takuang_office">${info.officeNames}</div>
						</div>
						<div class="clear"></div>
					</li> --%>
<%-- 					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">学组：</span></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">请选择</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list list_subgroup" style="display:none;">
								<select id='subGroupId' name="subGroupId" style="display:none;">
									<option value="">请选择</option>
									<c:forEach items="${sublist}" var="group">
										<option value="${group.id}"<c:if test="${group.id==info.subGroupId}"> selected</c:if>>${group.name}</option>
									</c:forEach>
								</select>
									<li>请选择</li>
									<c:forEach items="${sublist}" var="group">
										<li>${group.name}</li>
									</c:forEach>
							</ul>
							</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">届期：</span></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">请选择</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list list_termlist" style="display:none;">
								<select name="term" style="display:none;">
									<option value="">请选择</option>
									<c:forEach items="${termlist}" var="group">
										<option value="${group.id}"<c:if test="${group.id==info.term}"> selected</c:if>>${group.startDateStr} - ${group.endDateStr}</option>
									</c:forEach>
								</select>
									<li>请选择</li>
									<c:forEach items="${termlist}" var="group">
										<li>${group.startDateStr} - ${group.endDateStr}</li>
									</c:forEach>
								</ul>
							</div>
						</div>
						<div class="clear"></div>
					</li>
 --%>					<%-- <li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">任职状态：</span><em class="fr">*</em></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">在职</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list rzzt_select" style="display:none;">
								<select name="state" style="display:none;">
									<option value="1"<c:if test="${info.state==1}"> selected</c:if>>在职</option>
									<option value="2"<c:if test="${info.state==2}"> selected</c:if>>离职</option>
								</select>
									<li>在职</li>
									<li>离职</li>
								</ul>
							</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">离职时间：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;display:none;" id="rzsj_txt" readonly value=""/>
							<input type="text" class="fl tki_bianji" style="width:258px;display:none;" id="lzsj_txt" readonly name="breakDate" value="<fmt:formatDate value="${info.breakDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker()" />
						</div>
						<div class="clear"></div>
					</li> --%>
					<%-- <li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">职称：</span></p>
								<input type="hidden" class="fl tik_select" id="job" name="job" value="${info.job}"/>
								<div id="jobNames" class="fl tki_bianji takuang_job"><c:forEach items="${proplist}" var="prop"><c:if test="${prop.prop_val_id==info.job}">${prop.name}</c:if></c:forEach></div>
							
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">请选择</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list" style="display:none;">
								<select name="job" style="display:none;">
									<option value="">请选择</option>
									<c:forEach items="${proplist}" var="prop">
										<option value="${prop.id}"<c:if test="${prop.id==info.job}"> selected</c:if>>${prop.name}</option>
									</c:forEach>
								</select>
									<li>请选择</li>
									<c:forEach items="${proplist}" var="prop">
										<li>${prop.name}</li>
									</c:forEach>
								</ul>
							</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">状态：</span><em class="fr">*</em></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">启用</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list" style="display:none;">
								<select name="lockState" style="display:none;" >
									<option value="1"<c:if test="${info.lockState==1}"> selected</c:if>>启用</option>
									<option value="2"<c:if test="${info.lockState==2}"> selected</c:if>>禁用</option>
								</select>
									<li>启用</li>
									<li>禁用</li>
								</ul>
							</div>
						</div>
						<div class="clear"></div>
					</li> --%>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">学科：</span><em class="fr">*</em></p>
							<input type="hidden" class="fl tik_select" id="groupIds" name="propIds" value="${info.propIds}"/>
							<div id="groupNames" class="fl tki_bianji takuang_xk">${info.propNames}</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">职称：</span></p>
								<input type="hidden" class="fl tik_select" id="job" name="job" value="${info.job}"/>
								<div id="jobNames" class="fl tki_bianji takuang_job"><c:forEach items="${proplist}" var="prop"><c:if test="${prop.prop_val_id==info.job}">${prop.name}</c:if></c:forEach></div>
							
<%-- 							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">请选择</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list" style="display:none;">
								<select name="job" style="display:none;">
									<option value="">请选择</option>
									<c:forEach items="${proplist}" var="prop">
										<option value="${prop.id}"<c:if test="${prop.id==info.job}"> selected</c:if>>${prop.name}</option>
									</c:forEach>
								</select>
									<li>请选择</li>
									<c:forEach items="${proplist}" var="prop">
										<li>${prop.name}</li>
									</c:forEach>
								</ul>
							</div> --%>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng" style="position: relative;">
							<p class="fl"><span class="fr">工作单位：</span><em class="fr">*</em></p>
							
							<div class="fl tik1_select" style="width:118px;">
								<div class="tik1_position">
									<b class="ml5">请选择</b>
									<input id="workUnitType" name="workUnitType" value="" type="hidden">
									<p class="tik1_position01" style="width:25px;left:90px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list" style="display:none;width:118px;">
								<select id="orgType" name="orgType" style="display:none;" >
									<option value=""  <c:if test="${info.workUnitType==''}">selected</c:if>>请选择</option>
									<option value="4" <c:if test="${info.workUnitType=='医疗机构'}">selected</c:if>>医疗机构</option>
									<option value="5" <c:if test="${info.workUnitType=='医学院校'}">selected</c:if>>医学院校</option>
									<option value="6" <c:if test="${info.workUnitType=='学/协会'}">selected</c:if>>学/协会</option>
									<option value="7" <c:if test="${info.workUnitType=='出版社'}">selected</c:if>>出版社</option>
									<option value="8" <c:if test="${info.workUnitType=='社会单位'}">selected</c:if>>社会单位</option>
								</select>
									<li>请选择</li>
									<li>医疗机构</li>
									<li>医学院校</li>
									<li>学/协会</li>
									<li>出版社</li>
									<li>社会单位</li>
								</ul>
							</div>
							
							<input type="text" class="fl tki_bianji" style="width:137px;margin-left:3px;" id="workUnit" name="workUnit" value="${info.workUnit}" list="workunit01" />
							<ul id="workunit01" style="position: absolute;top：152px;left:130px;display:none;z-index:999">
								<!--[if IE]>
                   					<select size="0" id="hospitalListSelectId" class="fl att_bianji01" style="display:none;margin-left:0px;width:258px;height:0px;"></select>                
								<![endif]-->
							</ul>
						</div>
						<div class="fl shitin_xinzeng" >
							<p class="fl ml30"><span class="fr">单位职务：</span></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">请选择</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list" style="display:none;">
								<select name="workUnit_office" style="display:none;">
									<option value="">请选择</option>
									<option value="1"<c:if test="${info.workUnit_office==1}"> selected</c:if>>院长</option>
									<option value="2"<c:if test="${info.workUnit_office==2}"> selected</c:if>>副院长</option>
									<option value="8"<c:if test="${info.workUnit_office==8}"> selected</c:if>>校长</option>
									<option value="9"<c:if test="${info.workUnit_office==9}"> selected</c:if>>副校长</option>
									<option value="10"<c:if test="${info.workUnit_office==10}"> selected</c:if>>书记</option>
									<option value="11"<c:if test="${info.workUnit_office==11}"> selected</c:if>>副书记</option>
									<option value="12"<c:if test="${info.workUnit_office==12}"> selected</c:if>>处长</option>
									<option value="13"<c:if test="${info.workUnit_office==13}"> selected</c:if>>副处长</option>
									<option value="5"<c:if test="${info.workUnit_office==5}"> selected</c:if>>所长</option>
									<option value="6"<c:if test="${info.workUnit_office==6}"> selected</c:if>>副所长</option>
									<option value="14"<c:if test="${info.workUnit_office==14}"> selected</c:if>>科长</option>
									<option value="15"<c:if test="${info.workUnit_office==15}"> selected</c:if>>副科长</option>
									<option value="3"<c:if test="${info.workUnit_office==3}"> selected</c:if>>主任</option>
									<option value="4"<c:if test="${info.workUnit_office==4}"> selected</c:if>>副主任</option>
									<option value="7"<c:if test="${info.workUnit_office==7}"> selected</c:if>>护士长</option>
									<option value="16"<c:if test="${info.workUnit_office==16}"> selected</c:if>>副护士长</option>
									<option value="17"<c:if test="${info.workUnit_office==17}"> selected</c:if>>秘书</option>
								</select>
									<li>请选择</li>
									<li>院长</li>
									<li>副院长</li>
									<li>校长</li>
									<li>副校长</li>
									<li>书记</li>
									<li>副书记</li>
									<li>处长</li>
									<li>副处长</li>
									<li>所长</li>
									<li>副所长</li>
									<li>科长</li>
									<li>副科长</li>
									<li>主任</li>
									<li>副主任</li>
									<li>护士长</li>
									<li>副护士长</li>
									<li>秘书</li>
								</ul>
							</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">固定电话：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" maxlength="30" id="phone1" name="phone1" value="${info.phone1}"/>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">手机号：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" maxlength="11" name="phone2" id = "phone2" value="${info.phone2}"/>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">邮箱：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="email" id="email" value="${info.email}"/>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">国籍：</span><em class="fr">*</em></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">中国</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list" style="display:none;">
								<select name="isNation" style="display:none;">
									<option value="0"<c:if test="${info.isNation==0}"> selected</c:if>>中国</option>
									<option value="1"<c:if test="${info.isNation==1}"> selected</c:if>>外国</option>
								</select>
									<li>中国</li>
									<li>外国</li>
								</ul>
							</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">秘书姓名：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="clerkName" value="${info.clerkName}"/>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">秘书电话：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="clerkPhone" value="${info.clerkPhone}"/>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">银行卡号：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" maxlength="19" name="bankCard" id="bankCard" value="${info.bankCard}"/>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">开户行：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="bank" value="${info.bank}"/>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
						<p class="fl"><span class="fr">身份证号：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="identityNum" id="identityNum" value="${info.identityNum}"/>
						</div>
						<div class="fl shitin_xinzeng">
						<p class="fl ml30"><span class="fr">专项能力项目师资：</span></p>
							<input type="text" class="fl tki_bianji" style="width:258px;" name="specialAbility" id="specialAbility" value="${info.specialAbility}"/>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01" >
							<p class="fl"><span class="fr" style="align:top">名师 ：</span></p>
							<input type="checkbox" style="margin-left:5px;margin-top:6px;width:18px;height:18px" name="personage" id="personage" class="fr" <c:if test="${info.personage == 1}" >checked</c:if> value="1"/>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30" style="margin-left:267px;"><span class="fr">专家照片：</span></p>
							<input type="hidden" name="photo" id="photo"/>
							<div class="fl tik_select" style="width:256px;height:30px">
								<div class="tik1_position" style="overflow:hidden;width:260px;">
									<span>
										<c:if test="${fn:length(info.photo)>=35}">${fn:substring(info.photo, 0,35)}...</c:if>
										<c:if test="${fn:length(info.photo)<35}">${info.photo}</c:if>
									</span>
									<p class="mate_position" style="width:40px;">上传</p>
								</div>
								<input type="file" id="matFile" name="matFile" onchange="javascript:$(this).siblings().find('span').text($(this).val());"  style="position:absolute;opacity:0;overflow:hidden;left:220px;right:0px;padding:0px;width:41px;height:30px"/>
								<!--<input type="file" name="photo" onchange="javascript:$(this).siblings().find('span').text($(this).val());"style="position:absolute;opacity:0;overflow:hidden;left:220px;right:0px;padding:0px;width:41px;height:30px"/>-->
							</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">是否新增账号：</span></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5">是</b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<c:if test="${info.userName==null || info.userName==''}">
								<ul class="fl tk1_list" style="display:none;">
								<select name="isAddAccount" id = "isAddAccount" style="display:none;">
									<option value="0" selected>否</option>
									<option value="1">是</option>
								</select>
									<li>否</li>
									<li>是</li>
								</ul>
								</c:if>
							</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">账号：</span><em class="fr" id = "accountFlag" style = "display:none;">*</em></p>
							<input type="text" class="fl tki_bianji" readonly style="width:258px;" id="userName" name="userName" value="${info.userName}"/>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">简介：</span></p>
							<textarea maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" onKeyup="javascript:view_count(this);" placeholder="最大500字" id="summary" name="summary" cols="" rows="" class="fl tki_bianji" style="width:680px;height:50px">${info.summary}</textarea>
						</div>
						<div  class="shitin_xinzeng01">
							<p class="fl" style="margin-left:100px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">500</span></p>
							<p class="fl" style="width:0px">字</p>
						</div>
						<div class="clear"></div>
					</li>
					<li class="mt10">
						<div class="ca1_anniu" style="width:200px;">
							<a href="javascript:void(0);" class="fl anniu att_baocun">保存</a>
							<a href="javascript:goBack();" class="fl ml30 anniu att_fanhui">返回</a>
						</div>
						<div class="clear"></div>
					</li>
					
				</ul>	
			</div>
			<input type = "hidden" id = "gid" name = "gid" value = "${groupId}">
			<input type = "hidden" id = "sid" name = "sid" value = "${studyId}">
			</form>
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
	
	selectInit();
	
	//when select a group at form, get subgroups and terms of the group and redraw combo boxs.
		$('.list_group li').click(function(){
			var selectval = $(this).parent().find('option').eq($(this).index()-1).val();
			var url = '${ctx}/expert/ExpertManage.do?mode=getsub&gid=' + selectval;
			$.ajax({
			    url:url,
			    type: 'GET',
			    dataType: 'json',
			    success: function(result){
				   if(result != ''){
				   		var subgroup = result.sublist;
				   		var termlist = result.termlist;
				   		
				   		$('.list_subgroup select option').remove();
				   		$('.list_subgroup li').remove();
				   		var newoptions = "<option value=''>请选择</option>";
				   		var newlis = "<li>请选择</li>";
				   		$.each(subgroup, function(key, val){
				   			newoptions += "<option value='" + val.id + "'>" + val.name  + "</option>";
				   			newlis += "<li>" + val.name  + "</li>";
				   		});
				   		$('.list_subgroup select').html(newoptions);
				   		$('.list_subgroup select').after(newlis);
				   		
				   		////////////////////////////////
				   		$('.list_termlist select option').remove();
				   		$('.list_termlist li').remove();
				   		newoptions = "<option value=''>请选择</option>";
				   		newlis = "<li>请选择</li>";
				   		$.each(termlist, function(key, val){
				   			newoptions += "<option value='" + val.id + "'>" + val.startDateStr + " - " + val.endDateStr + "</option>";
				   			newlis += "<li>" + val.startDateStr + " - " + val.endDateStr + "</li>";
				   		});
				   		$('.list_termlist select').html(newoptions);
				   		$('.list_termlist select').after(newlis);
				   		
				   		selectInit();
				   }else{
				   		alert('失败!');
				   };
			    }
			    });

		});

		$(document).click(function(){
			$('.tk1_list').hide('fast');
		});

		$('.att_baocun').click(function(){
			if($("#"))
			if(!$('#rsName').val()){
				alert("名称不能为空");
				return;
			}
			
			if($('#groupIds').val() == null || $('#groupIds').val() =="")
			{
				alert("请选择学科!");
				return;
			}
			if($('#workUnit').val() == null || $('#workUnit').val() =="")
			{
				alert("请输入工作单位!");
				return;
			}
			//检查工作单位正确性
			checkWorkUnit();
			/*if($('#phone2').val() == null || $('#phone2').val() =="")
			{
				alert("请输入手机号!");
				return;
			}*/
			
			if($('#isAddAccount').val() == 1 && $("#userName").val() == "")
			{
				alert("请输入账号!");
				return;
			}
			/*if($("#matFile").val()==null || $("#matFile").val()==""){
				alert("请选择专家 照片!");
				return;
			}*/
			
/* 			var re = /^[_a-z0-9-]+([_a-z0-9-]+)*@/i;
			if ($('#email').val() !="" && !re.test($('#email').val())){
				alert("邮箱格式不对！");
				return;
			} */
			//提交前判断是否已经完成工作单位校验
			var workUnitHasChecked= $("#workUnitHasChecked").val();
			var expertID=$("#id").val();
			//只有在增加的时候判断
			if(expertID==''){
				if(workUnitHasChecked==0||workUnitHasChecked==''){
					return;
				}
			}
			
			if ($('#newgroupIds').val()!="" && $('select[name=office]').val() == 1){
				var url = '${ctx}/expert/ExpertGroupManage.do?mode=findmaster&id='+$('input[name=id]').val()+'&ids='+$('#newgroupIds').val();
				
				$.ajax({
					type: 'POST',
					url: url,
					dataType: 'text',
					success:function(result){
						if(result == "success"){
							alert('此委员会已有主任委员。请从新选择职务！');
						}
						else{
							saveExpert();
						}
					}
				});
			}
			else
				saveExpert();	
		});
		
		
		$('.takuang_xk').click(function(){
			initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#groupIds'), $('#groupNames'));
			$('.att_make01').show();
		});
		
		$('.takuang_zy').click(function(){
			initPropList("专委会", "${ctx}/expert/ExpertGroupListAjax.do", 0, 0, 2, 1, $('#newgroupIds'), $('#newgroupNames'));
			$('.att_make01').show();
		});
		
		$('.takuang_job').click(function(){
			initPropList("职称", "${ctx}/propManage/getPropListAjax.do", 24, 0, 1, 1, $('#job'), $('#jobNames'));
			$('.att_make01').show();
		});
		
		$('.takuang_office').click(function(){
			initPropList("职务", "${ctx}/propManage/getPropListAjax.do", 11, 0, 2, 1, $('#officeIds'), $('#officeNames'));
			$('.att_make01').show();
		});
		
		$('.rzzt_select option:selected').each(function(){
			if ($(this).text() == "离职"){
				$('#rzsj_txt').hide();
				$('#lzsj_txt').show();
			}else{
				$('#rzsj_txt').show();
				$('#lzsj_txt').hide();
			}
		});
		$('.rzzt_select li').click(function(){
			if ($(this).text() == "离职"){
				$('#rzsj_txt').hide();
				$('#lzsj_txt').show();
			}else{
				$('#rzsj_txt').show();
				$('#lzsj_txt').hide();
			}
		});
		
		$("#hospitalListSelectId").change(function(){
		var str = $("#hospitalListSelectId").find("option:selected").text()
		   $('#workUnit').val(str) ;
		   $("#hospitalListSelectId").hide();
		});
});

function sechan(){
	var url = '${ctx}/propManage/getHospitalListAjax.do';
		var params = "cname="+workUnit;
		$.ajax({
			type: 'POST',
			url: url,
			data:params,
			dataType: 'json',
			success:function(result){
				if(result.list.length == 0){
					alert("没有这家机构！");
					$('#workUnit').val("");
					return false;
				}else{
					////提交前判断是否已经完成工作单位校验:成功校验，隐藏域写出1。
					$("#workUnitHasChecked").val("1");
				}		
			}
		});
}
	// 2017-01-11 han
	function saveExpert(){
			if ($('#rsmode').val() == 'add'){
				var url = '${ctx}/expert/ExpertManage.do?mode=add';
				alert("add");
			}
			else if ($('#rsmode').val() == 'edit'){
				var url = '${ctx}/expert/ExpertManage.do?mode=edit';
				alert("edit");
				if (!$('#rsId').val())
					return;
			}
			if($("#matFile").val()==''){ //'${info.photo}'!='' || 
				$.ajax({
					async : false,
					type: 'POST',
					url: "${ctx}/expert/ExpertManage.do?mode=save",
					data : $("#frm").serialize(),
					dataType: 'json',
					success : function (data){
						var result = eval(data);
						if(result.message=='success'){
							//提示框
							alert("专家信息保存成功.")
							//location.href = "${ctx}/expert/ExpertManage.do";
							if(result.flagg=='2'){
								window.location.href=document.referrer;
							}else{
								if($(".bjs .tk_er a[href$='/expert/ExpertManage.do']").length==1){//解决专委会下学组成员修改信息后跳转到专家列表后菜单栏未选中问题
									var actionID = $(".bjs .tk_er a[href$='/expert/ExpertManage.do']").attr('id');
									document.getElementById(actionID).click();
								}
							}
						}
                                                if(result.message=='accountExist'){							
							alert("账户已存在！")							
						}
                                                if(result.message=='accountCreateErr'){							
							alert("创建账户时出错，请重试！")							
						}
					},
					error: function(data){
						alert('专家信息保存失败.');
					}
				});	
			}else{
				//alert("842");
				//alert($('#office').val());
				 /* var form=document.getElementById("frm");
			     var formData=new FormData(form);
			     var oReq = new XMLHttpRequest();
			     oReq.onreadystatechange=function(){
			       if(oReq.readyState==4){
			         if(oReq.status==200){
			             var json=JSON.parse(oReq.responseText,function(k,v){
			            	 if(!(v instanceof Object)){
			            		 if(v!="Fail"){
			            			 $("#photo").val(v);
			 		       			//上传素材信息
			 		       			$.ajax({
			 							async : false,
			 							type: 'POST',
			 							url: "${ctx}/expert/ExpertManage.do?mode=save",
			 							data : $("#frm").serialize(),
			 							dataType: 'json',
			 							success : function (data){
			 								var result = eval(data);
			 								if(result.message=='success'){
			 									//提示框
			 									alert("专家信息上传成功.")
			 									location.href = "${ctx}/expert/ExpertManage.do";
			 								}
			                                 if(result.message=='accountExist'){							
			                                         alert("账户已存在！")							
			                                 }
			                                 if(result.message=='accountCreateErr'){							
			                                         alert("创建账户时出错，请重试！")							
			                                 }
			 							},
			 							error: function(data){
			 								alert('专家信息上传失败.');
			 							}
			 						});	
			            		 }else{
			            			 alert("上传服务器异常,请稍后重试...");
			            		 }
			            	 }
			             });
			         }
			       }
			     }
			     //为了方便下次上传
			     oReq.open("POST", "${ctx}/file/fileUpload.do");
			     oReq.send(formData);   */
			     //判断上传文件的格式和大小
				if(!fileUploadValid("matFile",2)){
					return;
				}
				$("#frm").ajaxSubmit({
					type: 'post',
					url: '${ctx}/file/fileUpload.do',
					success: function(data) {
						try{
							var v = JSON.parse(data).message;
							if(v!="Fail"){
								$("#photo").val(v);
				       			//上传素材信息
				       			$.ajax({
									async : false,
									type: 'POST',
									url: "${ctx}/expert/ExpertManage.do?mode=save",
									data : $("#frm").serialize(),
									dataType: 'json',
									success : function (data){
										var result = eval(data);
										if(result.message=='success'){
											//提示框
											alert("专家信息上传成功.")
											//location.href = "${ctx}/expert/ExpertManage.do";
											if(result.flagg=='2'){
												window.location.href=document.referrer;
											}else{
												if($(".bjs .tk_er a[href$='/expert/ExpertManage.do']").length==1){//解决专委会下学组成员修改信息后跳转到专家列表后菜单栏未选中问题
													var actionID = $(".bjs .tk_er a[href$='/expert/ExpertManage.do']").attr('id');
													document.getElementById(actionID).click();
												}
											}
										}
		                                if(result.message=='accountExist'){							
		                                        alert("账户已存在！")							
		                                }
		                                if(result.message=='accountCreateErr'){							
		                                        alert("创建账户时出错，请重试！")							
		                                }
									},
									error: function(data){
										alert('专家信息上传失败.');
									}
								});
							}else{
								alert("上传服务器异常,请稍后重试...");
							}
						}catch(e){
							alert("上传服务器异常,请稍后重试...");
						}
					},
					error: function(data){
						alert("上传服务器异常,请稍后重试...");
					}
				});
			}
			 //$('#frm').submit(); 
	}			

	//下拉框
	function selectInit(){
		$('.tik1_select').click(function(){
			$(".tk1_list").css("display","none");
			$(this).find('ul').show();
			return false;
		});
		
		$('.tk1_list li').click(function(){
			var str=$(this).text();
			$(this).parent().parent().find('div').find('b').text(str);
			$(this).parent().find('option').prop('selected', '');
			$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
			
			if($(this).parent().find('select').prop("name") == "isAddAccount" && str == "是")
			{
				$("#userName").removeAttr("readonly");
				$("#userName").removeAttr("disabled");
				$("#accountFlag").show();
			}else if($(this).parent().find('select').prop("name") == "orgType"){
				var strType=$(".tik1_position .ml5").text();
				if(str == "医疗机构" || str == "医学院校" || str == "学/协会" || str == "出版社" || str == "社会单位"){
					$("#workUnit").removeAttr("readonly");
				}
				if(str!=strType){
					$('#workUnit').val("");
				}
				$("#workUnitType").val(str);
				//$('#workunit01').html("");
			}
			else
			{
				$("#userName").attr("disabled","disabled");
				$("#accountFlag").hide();
				$("#userName").val("");
			}
			
			$('.tk1_list').slideUp(50);
		});
		
		$('.tk1_list option:selected').each(function(){
			var str=$(this).text();
			$(this).parent().parent().parent().find('b').text(str);
		});
	}

	
function view_count(obj) {
	
	var next_obj = $(obj).next();
	var str = $(obj).val();
	var left_count = 500-str.length;
	var spell = $(obj).parent().parent().find('#spellNum');
	$(spell).text(left_count);
}

function goBack() {
		window.history.back();
}
var selectLength = 0;
//对比输入框关键词的变化
keywordLength=0;
//表示属性页面
$('#workUnit').keyup(function(e){
	workUnitValFlag=false;
	selectLength = 0;
	var orgType=document.getElementById("orgType").value;
	var keyword = $('#workUnit').val().trim();
	if (keyword == ""){
		return false;
	}
	switch(e.keyCode){
		case 37:
		case 38:
		case 39:
		case 40:
		case 13:
		case 9:
		return false;
	}
	//判断输入了新字符，避免输入汉字时重复提交
	if(keyword.length==keywordLength){
	return false;
	}else{
		keywordLength=keyword.length;
	}
	
/* 	try {							   
	   if (ieFlag) {//IE9及以下
	      $("#hospitalListSelectId").show();
	   }
	} catch(e){//IE10及以上、Chrome、Firefox、Safari			   
	} */
	
	var params = "sname="+keyword;
	var temp;
	if(orgType=='4'){
		var url = '${ctx}/propManage/getHospitalListAjax.do';
		$.ajax({
			type: 'POST',
			url: url,
			data: params,
			dataType: 'json',
			success:function(result){
				datalist = "";
				/* if ("placeholder" in document.createElement("input")) { */
					for(var i=0;i<result.list.length;i++){
						datalist += "<li value='"+result.list[i].name+"'  onclick='setValToInput(this)'>"+result.list[i].name+"</li>";
						temp += "<li value='"+result.list[i].name+"'  onclick='setValToInput(this)'>"+result.list[i].name+"</li>";
					}
					$('#workunit01').html(datalist);
					$("#workunit01").css("display","block")
				/* }else{
					for(var i=0;i<result.list.length;i++){
							datalist +="<li value='"+result.list[i].name+"' onclick='setValToInput(this)'>"+result.list[i].name+"</li>";
							temp += "<li value='"+result.list[i].name+"' onclick='setValToInput(this)'>"+result.list[i].name+"</li>";
					}
					$("#hospitalListSelectId option").remove();
					
					var ele = document.getElementById("hospitalListSelectId");
					if(result.list.length > 0){
						$("#hospitalListSelectId").attr("size",result.list.length);
						selectLength = result.list.length;
						if(selectLength >= 5){
							ele.style.setProperty('height', '200px');
						}else{
							$("#hospitalListSelectId").attr("size",result.list.length+10);
							ele.style.setProperty('height', result.list.length+'00px');
						}
						
					}else{
						ele.style.setProperty('height', '0px');
					}
					$("#hospitalListSelectId").append(datalist);
					$("#workunit01").css("display","block")
				} */
			}
		});
		
	}else if(orgType=='5' || orgType=='6' || orgType=='7' || orgType=='8'){
		var url = '${ctx}/propManage/getOrgListAjax.do';
		var params = "orgType="+orgType;
		$.ajax({
			type: 'POST',
			url: url,
			dataType: 'json',
			data:params,
			success:function(result){
				datalist = "";
			/* 	if ("placeholder" in document.createElement("input")) { */
					for(var i=0;i<result.list.length;i++){
						datalist += "<li value='"+result.list[i].name+"' onclick='setValToInput(this)'>"+result.list[i].name+"</li>";
					}
					$('#workunit01').html(datalist);
					$("#workunit01").css("display","block");
				/* }else{
					for(var i=0;i<result.list.length;i++){
						datalist += "<li value='"+result.list[i].name+"' onclick='setValToInput(this)'>"+result.list[i].name+"</li>";
					}
					$("#hospitalListSelectId option").remove();
					
					var ele = document.getElementById("hospitalListSelectId");
					if(result.list.length > 0){
						$("#hospitalListSelectId").attr("size",result.list.length);
						selectLength = result.list.length;
						if(selectLength >= 5){
							ele.style.setProperty('height', '200px');
						}else{
							$("#hospitalListSelectId").attr("size",result.list.length+10);
							ele.style.setProperty('height', '100px');
						}
					}else{
						ele.style.setProperty('height', '0px');
					}
					$("#hospitalListSelectId").append(datalist);
					$("#workunit01").css("display","block");
				} */
			}
		});
		
	}else if(orgType==''){
		alert("请先选择机构类型！");
	}
});

//获取点击的li,并赋值到input中
function setValToInput(e){
	
	var obj=$(e);
	if(obj.length>0){	
	var liObj=obj[0].innerHTML;
	$("#workUnit").val(liObj);
	$("#workunit01").css("display","none");	
	}
}
//检查工作单位正确性
function checkWorkUnit(){

	var orgType=document.getElementById("orgType").value;
	//工作单位已经检查
	$("#workUnitHasChecked").val("0");
	var workUnit= $('#workUnit').val();	
	
	if (workUnit == '') {
	   return ;
	}
	
	if(orgType=='4'){
		var url = '${ctx}/propManage/getHospitalListAjax.do';
		var params = "cname="+workUnit;
		$.ajax({
			type: 'POST',
			url: url,
			data:params,
			async:false,
			dataType: 'json',
			success:function(result){
				if(result.list.length == 0){
					alert("没有这家机构！");
					$('#workUnit').val("");
					return false;
				}else{
					////提交前判断是否已经完成工作单位校验:成功校验，隐藏域写出1。
					$("#workUnitHasChecked").val("1");
				}		
			}
		});
	}else if(orgType=='5' || orgType=='6' || orgType=='7' || orgType=='8'){
		var url = '${ctx}/propManage/getOrgListAjax.do';
		var params = "orgType="+orgType + "&sname=" +workUnit;
		$.ajax({
			type: 'POST',
			url: url,
			dataType: 'json',
			data:params,
			async:false,
			success:function(result){
				if(result.list.length == 0){
					alert("没有这家机构！");
					$('#workUnit').val("");
					return false;
				}else{
					$("#workUnitHasChecked").val("1");
				}	
			}
		});
	}
}

view_count($('#summary'));


	/* when select zhiceng and weyuanhui, select dlg modify to radiobox */ //2017-01-11 han
function updatePropList(result){
	var kuangcode_id = $(kuangcode).prop('id');
	if (kuangcode_id == 'job'){
		
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
	else
	{
		var str = "";
		var check = eval($('.xs_checklvl').text());
		var select = eval($('.xs_selectlvl').text());
		$(result.list).each(function(key, value){
			str += "<li><div class=''>";
			if (check <= value.type)
				str += '<input class="fl" style="margin-top:5px;" type="checkbox">';

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
	}
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
	var kuangcode_id = $(kuangcode).prop('id');
	if (kuangcode_id == 'job'){
		$(kuangcode).val($('.xs_kuangcode').text());
		var selPropHtml = $('.xs_kuang').html().replace(brexp,"");
		$('.xs_kuang').html(selPropHtml);
		$(kuang).text($('.xs_kuang').text());
	}
	else{	
		$(kuangcode).val($('.xs_kuangcode').text());
		var selPropHtml = $('.xs_kuang').html().replace(brexp,",");
		$('.xs_kuang').html(selPropHtml);
		$(kuang).text($('.xs_kuang').text());
	}
}

//座机校验
$('#phone1').blur(function(){
	var obj = document.getElementById("phone1");
    var value = obj.value;
    var regTel1 = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.test(value);//带区号的固定电话
//    var regTel2 = /^(\d{7,8})(-(\d{3,}))?$/.test(value);//不带区号的固定电话
    if (value != "") {
      if (!regTel1) { 
        alert("电话号码输入有误！示例：010-12345678");
        $('#phone1').val("");
        return false;
      }
    }
});

//手机校验
$('#phone2').blur(function(){
	var obj = document.getElementById("phone2");
    var value = obj.value;
    var regTel1 = /^1(3|4|5|7|8)\d{9}$/.test(value);//带区号的固定电话
//    var regTel2 = /^(\d{7,8})(-(\d{3,}))?$/.test(value);//不带区号的固定电话
    if (value != "") {
      if (!regTel1) { 
        alert("手机号输入有误！");
        $('#phone2').val("");
        return false;
      }
    }
});

//邮箱校验
$('#email').blur(function(){
	var obj = document.getElementById("email");
    var value = obj.value;
    var regTel1 = /^[^\.@]+@[^\.@]+\.[a-z]+$/.test(value);
    if (value != "") {
      if (!regTel1) { 
        alert("邮箱输入有误！");
        $('#email').val("");
        return false;
      }
    }
});

//银行卡校验
$('#bankCard').blur(function(){
	var obj = document.getElementById("bankCard");
    var value = obj.value;
    var regTel1 = /^\d{16}|\d{19}$/.test(value);
    if (value != "") {
      if (!regTel1) { 
        alert("银行卡号输入有误（16-19位的数字）！");
        $('#bankCard').val("");
        return false;
      }
    }
});

//身份证校验
$('#identityNum').blur(function(){
	var obj = document.getElementById("identityNum");
    var value = obj.value;
    var regTel1 = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);
    if (value != "") {
      if (!regTel1) { 
        alert("身份证号输入有误！");
        $('#identityNum').val("");
        return false;
      }
    }
});

//专项能力项目师资校验
$('#specialAbility').blur(function(){
	var obj = document.getElementById("specialAbility");
    var value = obj.value;
    var regTel1 = /(^NCME+\d{12}[A-Z]\d{4}$)/.test(value);
    if (value != "") {
      if (!regTel1) { 
        alert("专项能力项目师资输入有误！");
        $('#specialAbility').val("");
        return false;
      }
    }
});

</script>
</body>
</html>