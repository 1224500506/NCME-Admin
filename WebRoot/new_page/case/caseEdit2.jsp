<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/new_page/top.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理平台</title>
</head>

<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/new_page/js/fileUpload.js"></script>
<script type="text/javascript" src="${ctx}/new_page/js/validate.js"></script>

	<body class = "bjs" id="body">
	<div class="center">
		<div class="tk_xuanxiag">
			<form method = "POST" name = "diseaseForm" id = "diseaseForm" enctype="multipart/form-data">
			<div class="xingzeng_k">
				<i></i><h2 class="fl">修改病例</h2>
			</div>
			<div class="clear"></div>
			<!-- 患者信息 -->
			<div class="thi_shitineirong" style="width:1080px;margin:0 auto;">
				<div class="cas3_jchu" style="border-top:none;border-bottom:2px solid #075387;">
					<em>患者信息</em>
				</div>
				<ul>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">学科：</span><em class="fr">*</em></p>
					<!-- 		<textarea name="" cols="" rows="" class="fl tki_bianji takuang_xk" id="courseStr" onclick="javascript:popUpDlg(1);" readonly="readonly">${courseStr}</textarea> -->
							<input type="hidden" name="course" id="course" value = "${courseIds}">
							<div class="fl tki_bianji takuang_xk" id="courseStr">${courseStr}</div>
						</div>
						<div class="fl shitin_xinzeng01">
						<p class="fl ml30"><span class="fr">ICD10：</span></p>
							<input type="text"  id="ICD" name="ICD" class="fl tki_bianji" readonly value="${ICD}"/>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">主题：</span><em class="fr">*</em></p>
							<div class="fl tki_bianji takuang_xk01" id="subjectStr">${subjectStr}</div>
							<input type="hidden" name="subject" id="subject" value = "${subjectids}">
						</div>
						<div class="fl shitin_xinzeng01">
							<p class="fl ml30">就诊日期：</p>
							<input type="text" class="fl tki_bianji" name="diagnosis_date" style="width:258px;" id="diagnosis_date" onClick="WdatePicker()" readonly="readonly" value = "${caseData.patientObject.diagDate}"/>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl">患者姓名：</p>
							<input type="text" class="fl tki_bianji" name = "pName" id = "pName" style="width:258px;" value = "${caseData.patientObject.PName}"/>
						</div>
						<div class="fl shitin_xinzeng01">
							<p class="fl ml30"><span class="fr">性别：</span><em class="fr">*</em></p>
							<p class="fl">
								<input type="radio" name="nax" <c:if test="${caseData.patientObject.sex == 0}">checked="checked"</c:if> class="fl mt10 ml20" value="0"/>
								<span class="fl">男</span>
								<input type="radio" class="fl mt10 ml30" <c:if test="${caseData.patientObject.sex == 1}">checked="checked"</c:if> name="nax" value ="1"/>
								<span class="fl">女</span>
							</p>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">患者身份证号：</span><em class="fr">*</em></p>
							<input name="pCertificate" id = "pCertificate" class="fl tki_bianji takuang_xk" maxLength = "18" value = "${caseData.patientObject.certificate}"></input>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">国家库：</span><em class="fr">*</em></p>
							<div class="fl tik1_select">
								<div class="tik1_position">
									<b class="ml5" id = "pNationalState" name = "pNationalState" ><c:if test="${caseData.patientObject.nationalState == 1}">是</c:if><c:if test="${caseData.patientObject.nationalState != 1}">否</c:if></b>
									<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list" style="display:none;">
									<!-- <li>请选择</li> -->
									<li>是</li>
									<li>否</li>
								</ul>
							</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">患者出生日期：</span></p>
							<input type="text" class="fl tki_bianji" name = "pBirthDay" id = "pBirthDay" readonly="readonly" style="width:258px;" value = "${caseData.patientObject.birthday}"/>
						</div>
						<div class="fl shitin_xinzeng01">
							<p class="fl ml30"><span class="fr">年龄：</span></p>
							<input type="text" class="fl tki_bianji" style="width:63px;" value = "${caseData.patientObject.pAge}" name = "pAge" id = "pAge" readonly="readonly" />
							<p class="fl ml20" style="width:5px;"><span class="fl">岁</span></p>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">编号类型1：</span></p>
							<div class="fl tik1_select" style="width:110px;">
								<div class="tik1_position">
									<b class="ml5" id = "pType1">
									</b>
									<p class="tik1_position01" style="width:25px;left:80px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list" id="pNT1" style="width:110px;display:none;">
									<li>请选择</li>
									<li>门诊号</li>
									<li>住院号</li>
									<li>床位号</li>
									<li>其他编号</li>
								</ul>
							</div>
							<input type="text" class="fl ml5 tki_bianji" style="width:140px;" name = "pNumber1" id = "pNumber1" value = "${caseData.patientObject.number1}"/>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl ml30"><span class="fr">编号类型2：</span></p>
							<div class="fl tik1_select" style="width:110px;">
								<div class="tik1_position">
									<b class="ml5" id = "pType2">
									</b>
									<p class="tik1_position01" style="width:25px;left:80px;"><i class="tk1_bjt10"></i></p>
								</div>
								<ul class="fl tk1_list" id="pNT2" style="width:110px;display:none;">
									<li>请选择</li>
									<li>门诊号</li>
									<li>住院号</li>
									<li>床位号</li>
									<li>其他编号</li>
								</ul>
							</div>
							<input type="text" class="fl ml5 tki_bianji" style="width:140px;" name = "pNumber2" id = "pNumber2" value = "${caseData.patientObject.number2}"/>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl">手机号：</p>
							<input type="text" name = "pPhoneNumber1" id = "pPhoneNumber1" class="fl tki_bianji" style="width:258px;" maxLength = "11" value = "${caseData.patientObject.phoneNumber1}"/>
						</div>
						<div class="fl shitin_xinzeng01">
							<p class="fl ml30"><span class="fr">固定电话：</span><em class="fr">*</em></p>
							<input type="text"  name = "pPhoneNumber2" id = "pPhoneNumber2" class="fl tki_bianji" style="" value = "${caseData.patientObject.phoneNumber2}"/>							
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<div>
								<p class="fl"><span class="fr">诊断：</span><em class="fr">*</em></p>
								<textarea onKeyup="javascript:view_countzd(this);" placeholder="最大200字"  id = "diagNo1" maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)" cols="" rows="" class="fl" style="width:680px;height:50px;border:1px solid #dddddd;"></textarea>
								<a href="javascript:void(0);" class="fl ml10 anniu att_baocun3">添加诊断</a>
							</div>	
						</div>	
						<div  class="mt10 shitin_xinzeng01">
							<p class="fl" style="margin-left:100px">还能输入：<i class="fr" id="diagNo1_spellNumzd" style="font-size:12px;">200</i></p>
							<p class="fl" style="width:0px">字</p>
						</div>
						<div class="clear"></div>
						<script>var diagIndex=1;</script>
						<c:forEach items = "${caseData.patientDiagnosis}" var = "diagnosis">
							<div class="mt10 paDiag1">
								<div class="fl shitin_xinzeng01">
									<p class="fl"><i class="fr">诊断<script>document.write(diagIndex);diagIndex++;</script>：</i></p>
									<p class="fl" style="width:680px; overflow:auto; height:auto;"><span class="fl diagnose" style="text-align:left">${diagnosis.diagnosis}</span></p>
									<a href="javascript:void(0);" class="fl ml10 anniu" style="margin-left:10px;" onClick="javascript:deleEm(this);">删除</a>
								</div>
								<div class="clear"></div>
							</div>
						</c:forEach>
					</li>

					<li>
						<div class="mt10 fl shitin_xinzeng01">
							<p class="fl"><span class="fr">基本病情：</span><em class="fr">*</em></p>
							<textarea onKeyup="javascript:view_count(this);" placeholder="最大500字" name = "diagName" id = "diagName" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" cols="" rows="" class="fl" style="width:680px;height:50px;border:1px solid #dddddd;">${caseData.patientObject.diagName}</textarea>
						</div>
						<div class="clear"></div>
						<div  class="shitin_xinzeng01">
							<p class="fl" style="margin-left:100px">还能输入：<span class="fr" id="diagName_spellNum" style="font-size:12px;min-width:30px">500</span></p>
							<p class="fl" style="width:0px">字</p>
						</div>
						<div class="clear"></div>
					</li>
				</ul>
				<div class="clear"></div>
			</div>
			<!-- 病例类别 -->
			<div class="cas3_leibie"   style="width:1080px;margin:0 auto;">
				<div class="cas3_jchu" style="margin-top:50px;border-bottom:2px solid #075387;height:60px;border-top:none;">
					<em class="fl"  style="padding-top:8px;">病程类型</em>
					<div class="fl tik1_select mt15 ml15" style="width:150px;">
						<div class="tik1_position">
							<b class="ml5" id = "diseaseTypeView">
								<c:if test="${caseData.caseDiseaseObject.diseaseType == 0}">首诊</c:if>
								<c:if test="${caseData.caseDiseaseObject.diseaseType == 1}">复诊</c:if>
								<c:if test="${caseData.caseDiseaseObject.diseaseType == 2}">入院</c:if>
							</b>
							<p class="tik1_position01" style="width:25px;left:120px;top:1px;height:29px;"><i class="tk1_bjt10"></i></p>
						</div>
						<ul class="fl tk1_list" style="width:150px;display:none;top:30px;">
							<li>首诊</li>
							<li>复诊</li>
							<li>入院</li>
						</ul>
					</div>
				</div>
				<div class="clear"></div>
				<div class="fl mt20 shitin_xinzeng01">
					<p class="fl"><span class="fr">主诉：</span></p>
					<textarea maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" onKeyup="javascript:view_count(this);" placeholder="最大500字" id = "complaint" name="complaint" cols="" rows="" class="fl" style="width:680px;height:50px;border:1px solid #dddddd;">${caseData.caseDiseaseObject.complaint}</textarea>
				</div>
				<div class="clear"></div>
				<div  class="mt10 shitin_xinzeng01">
					<p class="fl" style="margin-left:100px">还能输入：<span class="fr" id="complaint_spellNum" style="font-size:12px;min-width:30px">500</span></p>
					<p class="fl" style="width:0px">字</p>
				</div>
				<div class="clear"></div>
				<div class="fl mt20 shitin_xinzeng01">
					<p class="fl"><span class="fr">现病史：</span></p>
					<textarea maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" onKeyup="javascript:view_count(this);" placeholder="最大500字" id = "currentDisease" name="currentDisease" cols="" rows="" class="fl" style="width:680px;height:50px;border:1px solid #dddddd;">${caseData.caseDiseaseObject.currentDisease}</textarea>
				</div>
				<div class="clear"></div>
				<div  class="mt10 shitin_xinzeng01">
					<p class="fl" style="margin-left:100px">还能输入：<span class="fr" id="currentDisease_spellNum" style="font-size:12px;min-width:30px">500</span></p>
					<p class="fl" style="width:0px">字</p>
				</div>
				<div class="clear"></div>
				<div class="fl mt20 shitin_xinzeng01">
					<p class="fl"><span class="fr">个人史：</span></p>
					<ul class="fl ca1_gerenshi">
						<li style="height: 45px;">
							<span class="fl" style="text-align:center;height:45px;line-height:45px;">既往史</span>
							<textarea rows="" cols="" id = "history1" onKeyup="javascript:view_count5(this);" name = "history1" class="fl" placeholder="最大500字" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" style="width:70%;height:100%">${caseData.caseDiseaseObject.history1}</textarea>
							<div class="fl" style="text-align:center;height:45px;line-height:45px;">还能输入:<em id="spellNum" style="font-size:12px;min-width:30px;margin-left:10px;">500</em><em style="margin-left:5px;">字</em></div>
						</li>
						<li style="height: 45px;">
							<span class="fl" style="text-align:center;height:45px;line-height:45px;">个人史</span>
							<textarea rows="" cols="" id = "history2" onKeyup="javascript:view_count5(this);" name = "history2" class="fl" placeholder="最大500字" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" style="width:70%;height:100%">${caseData.caseDiseaseObject.history2}</textarea>
							<div class="fl" style="text-align:center;height:45px;line-height:45px;">还能输入:<em id="spellNum" style="font-size:12px;min-width:30px;margin-left:10px;">500</em><em style="margin-left:5px;">字</em></div>
						</li>
						<li style="height: 45px;">
							<span class="fl" style="text-align:center;height:45px;line-height:45px;">过敏史</span>
							<textarea rows="" cols="" id = "history3" onKeyup="javascript:view_count5(this);" name = "history3" class="fl" placeholder="最大500字" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" style="width:70%;height:100%">${caseData.caseDiseaseObject.history3}</textarea>
							<div class="fl" style="text-align:center;height:45px;line-height:45px;">还能输入:<em id="spellNum" style="font-size:12px;min-width:30px;margin-left:10px;">500</em><em style="margin-left:5px;">字</em></div>
						</li>
						<li style="height: 45px;">
							<span class="fl" style="text-align:center;height:45px;line-height:45px;">家族史</span>
							<textarea rows="" cols="" id = "history4" onKeyup="javascript:view_count5(this);" name = "history4" class="fl" placeholder="最大500字" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" style="width:70%;height:100%">${caseData.caseDiseaseObject.history4}</textarea>
							<div class="fl" style="text-align:center;height:45px;line-height:45px;">还能输入:<em id="spellNum" style="font-size:12px;min-width:30px;margin-left:10px;">500</em><em style="margin-left:5px;">字</em></div>
						</li>
					</ul>
				</div>
				<div class="clear"></div>
				<div class="fl mt20 shitin_xinzeng01">
					<p class="fl"><span class="fr">体征：</span></p>
					<ul class="fl ca1_gerenshi">
							<li>
								<span class="fl">神经系统</span>
								<span class="fl">
									<select class="fl" id = "bodyState1Type" name = "bodyState1Type" style="width:90px;height:26px;margin-left:5px;margin-top:2px;border:1px solid #dddddd;">
										<c:if test="${caseData.caseDiseaseObject.bodyState1Type == 0}">
											<option value="0">正常</option>
											<option value="1">异样</option>
										</c:if>
										<c:if test="${caseData.caseDiseaseObject.bodyState1Type == 1}">
											<option value="1">异样</option>
											<option value="0">正常</option>
										</c:if>
									</select>
								</span>
								<input type="text" id = "bodyState1" name = "bodyState1" class="fl" value="${caseData.caseDiseaseObject.bodyState1}" 
								placeholder="最多200字" maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)"/>
							</li>
							<li>
								<span class="fl">头部</span>
								<span class="fl">
									<select class="fl" id = "bodyState2Type" name = "bodyState2Type" style="width:90px;height:26px;margin-left:5px;margin-top:2px;border:1px solid #dddddd;">
										<c:if test="${caseData.caseDiseaseObject.bodyState2Type == 0}">
											<option value="0">正常</option>
											<option value="1">异样</option>
										</c:if>
										<c:if test="${caseData.caseDiseaseObject.bodyState2Type == 1}">
											<option value="1">异样</option>
											<option value="0">正常</option>
										</c:if>
									</select>
								</span>
								<input type="text" id = "bodyState2" name = "bodyState2" class="fl" value="${caseData.caseDiseaseObject.bodyState2}"
								placeholder="最多200字" maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)"/>
							</li>
							<li>
								<span class="fl">颈部</span>
								<span class="fl">
									<select class="fl" id = "bodyState3Type" name = "bodyState3Type" style="width:90px;height:26px;margin-left:5px;margin-top:2px;border:1px solid #dddddd;">
										<c:if test="${caseData.caseDiseaseObject.bodyState3Type == 0}">
											<option value="0">正常</option>
											<option value="1">异样</option>
										</c:if>
										<c:if test="${caseData.caseDiseaseObject.bodyState3Type == 1}">
											<option value="1">异样</option>
											<option value="0">正常</option>
										</c:if>
									</select>
								</span>
								<input type="text" id = "bodyState3" name = "bodyState3" class="fl" value="${caseData.caseDiseaseObject.bodyState3}"
								placeholder="最多200字" maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)"/>
							</li>
							<li>
								<span class="fl">胸部</span>
								<span class="fl">
									<select class="fl" id = "bodyState4Type" name = "bodyState4Type" style="width:90px;height:26px;margin-left:5px;margin-top:2px;border:1px solid #dddddd;">
										<c:if test="${caseData.caseDiseaseObject.bodyState4Type == 0}">
											<option value="0">正常</option>
											<option value="1">异样</option>
										</c:if>
										<c:if test="${caseData.caseDiseaseObject.bodyState4Type == 1}">
											<option value="1">异样</option>
											<option value="0">正常</option>
										</c:if>
									</select>
								</span>
								<input type="text" id = "bodyState4" name = "bodyState4" class="fl" value="${caseData.caseDiseaseObject.bodyState4}"
								placeholder="最多200字" maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)"/>
							</li>
							<li>
								<span class="fl">腹部</span>
								<span class="fl">
									<select class="fl" id = "bodyState5Type" name = "bodyState5Type" style="width:90px;height:26px;margin-left:5px;margin-top:2px;border:1px solid #dddddd;">
										<c:if test="${caseData.caseDiseaseObject.bodyState5Type == 0}">
											<option value="0">正常</option>
											<option value="1">异样</option>
										</c:if>
										<c:if test="${caseData.caseDiseaseObject.bodyState5Type == 1}">
											<option value="1">异样</option>
											<option value="0">正常</option>
										</c:if>
									</select>
								</span>
								<input type="text" id = "bodyState5" name = "bodyState5" class="fl" value="${caseData.caseDiseaseObject.bodyState5}"
								placeholder="最多200字" maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)"/>
							</li>
							<li>
								<span class="fl">四肢</span>
								<span class="fl">
									<select class="fl" id = "bodyState6Type" name = "bodyState6Type" style="width:90px;height:26px;margin-left:5px;margin-top:2px;border:1px solid #dddddd;">
										<c:if test="${caseData.caseDiseaseObject.bodyState6Type == 0}">
											<option value="0">正常</option>
											<option value="1">异样</option>
										</c:if>
										<c:if test="${caseData.caseDiseaseObject.bodyState6Type == 1}">
											<option value="1">异样</option>
											<option value="0">正常</option>
										</c:if>
									</select>
								</span>
								<input type="text" id = "bodyState6" name = "bodyState6" class="fl" value="${caseData.caseDiseaseObject.bodyState6}"
								placeholder="最多200字" maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)"/>
							</li>
							<li>
								<span class="fl">皮肤</span>
								<span class="fl">
									<select class="fl" id = "bodyState7Type" name = "bodyState7Type" style="width:90px;height:26px;margin-left:5px;margin-top:2px;border:1px solid #dddddd;">
										<c:if test="${caseData.caseDiseaseObject.bodyState7Type == 0}">
											<option value="0">正常</option>
											<option value="1">异样</option>
										</c:if>
										<c:if test="${caseData.caseDiseaseObject.bodyState7Type == 1}">
											<option value="1">异样</option>
											<option value="0">正常</option>
										</c:if>
									</select>
								</span>
								<input type="text" id = "bodyState7" name = "bodyState7" class="fl" value="${caseData.caseDiseaseObject.bodyState7}"
								placeholder="最多200字"  maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)"/>
							</li>
							<li>
								<span class="fl">淋巴</span>
								<span class="fl">
									<select class="fl" id = "bodyState8Type" name = "bodyState8Type" style="width:90px;height:26px;margin-left:5px;margin-top:2px;border:1px solid #dddddd;">
										<c:if test="${caseData.caseDiseaseObject.bodyState8Type == 0}">
											<option value="0">正常</option>
											<option value="1">异样</option>
										</c:if>
										<c:if test="${caseData.caseDiseaseObject.bodyState8Type == 1}">
											<option value="1">异样</option>
											<option value="0">正常</option>
										</c:if>
									</select>
								</span>
								<input type="text" id = "bodyState8" name = "bodyState8" class="fl" value="${caseData.caseDiseaseObject.bodyState8}"
								placeholder="最多200字" maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)"/>
							</li>
						</ul>
				</div>
				<div class="clear"></div>
				<div class="fl mt20 shitin_xinzeng01" style = "width:1200px;">					
						<p class="fl"><span class="fr">辅助检查：</span></p>
						<ul class="fl ca1_gerenshi">
								<li style="height:100px;">
									<span class="fl" style="height:100px;">化验</span>
									<textarea rows="" cols="" class="fl" id ="assay" name="assay" placeholder="最多500字" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)"
											style="width:71%;height:100%">${caseData.caseDiseaseObject.assay}</textarea>
									<div style="width:630px" id="watchAssayFile">
									<p class = "fl ml10 anniu att_baocun" id="assayfiles_p" style="float:right;margin-right:5px;margin-top:3px;width:80px;height:23px;line-height:23px;">
										<input type="file" id="assayFile" name="assayFile" onchange="javascript:insertAssayFile(0, this, $('#assayFilePath'));" style = "z-index:103;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
										<input type="file" id="assayFile1" name="assayFile1" onchange="javascript:insertAssayFile(1, this, $('#assayFilePath'));" style = "z-index:102;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
										<input type="file" id="assayFile2" name="assayFile2" onchange="javascript:insertAssayFile(2, this, $('#assayFilePath'));" style = "z-index:101;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
										<input type="file" id="assayFile3" name="assayFile3" onchange="javascript:insertAssayFile(3, this, $('#assayFilePath'));" style = "z-index:100;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
										上传图片</p>
									</div>
								</li>
								<li style="height:100px;">
									<span class="fl" style="height:100px;">影像学</span>
									<textarea rows="" cols="" class="fl" id="image" name="image" placeholder="最多500字" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)"
											style="width:71%;height:100%">${caseData.caseDiseaseObject.image}</textarea>
									<div style="width:630px" id="watchImageFile">
									<p class="fl ml10 anniu att_baocun" id="imagefiles_p" style="float:right;margin-right:5px;margin-top:3px;width:80px;height:23px;line-height:23px;">
										<input type="file" id="imageFile" name="imageFile" onchange="javascript:insertImageFile(0, this, $('#imageFilePath'));" style = "z-index:103;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
										<input type="file" id="imageFile1" name="imageFile1" onchange="javascript:insertImageFile(1, this, $('#imageFilePath'));" style = "z-index:102;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
										<input type="file" id="imageFile2" name="imageFile2" onchange="javascript:insertImageFile(2, this, $('#imageFilePath'));" style = "z-index:101;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
										<input type="file" id="imageFile3" name="imageFile3" onchange="javascript:insertImageFile(3, this, $('#imageFilePath'));" style = "z-index:100;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
										上传图片</p>
									</div>
								</li>
								<li style="height:100px;">
									<span class="fl" style="height:100px;">其他</span>
									<textarea rows="" cols="" class="fl" id="rest" name="rest" placeholder="最多500字" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)"
											style="width:71%;height:100%">${caseData.caseDiseaseObject.rest}</textarea>
									<div style="width:630px" id="watchRestFile">
									<p class="fl ml10 anniu att_baocun" id="restfiles_p"  style="float:right;margin-right:5px;margin-top:3px;width:80px;height:23px;line-height:23px;">
										<input type="file" id="restFile" name="restFile" onchange="javascript:insertRestFile(0, this, $('#restFilePath'));" style = "z-index:103;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
										<input type="file" id="restFile1" name="restFile1" onchange="javascript:insertRestFile(1, this, $('#restFilePath'));" style = "z-index:102;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
										<input type="file" id="restFile2" name="restFile2" onchange="javascript:insertRestFile(2, this, $('#restFilePath'));" style = "z-index:101;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
										<input type="file" id="restFile3" name="restFile3" onchange="javascript:insertRestFile(3, this, $('#restFilePath'));" style = "z-index:100;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
										上传图片</p>
									</div>
								</li>
						 </ul>
						 <div class="fl">
								<div id = "assayFilePath" style="width:300px;height:100px;padding-top:7px;margin-left:15px;">
									<i><c:if test="${caseData.caseDiseaseObject.assayFileArray.size()>0}"><em class="delem" onclick="javascript:deleteUploadFile(0,this, $('#assayfiles_p'),$('#watchAssayFile'));">${caseData.caseDiseaseObject.assayFileArray.get(0)}</em></c:if></i> 
									<i><c:if test="${caseData.caseDiseaseObject.assayFileArray.size()>1}"><em class="delem" onclick="javascript:deleteUploadFile(1,this, $('#assayfiles_p'),$('#watchAssayFile'));">${caseData.caseDiseaseObject.assayFileArray.get(1)}</em></c:if></i> 
									<i><c:if test="${caseData.caseDiseaseObject.assayFileArray.size()>2}"><em class="delem" onclick="javascript:deleteUploadFile(2,this, $('#assayfiles_p'),$('#watchAssayFile'));">${caseData.caseDiseaseObject.assayFileArray.get(2)}</em></c:if></i> 
									<i><c:if test="${caseData.caseDiseaseObject.assayFileArray.size()>3}"><em class="delem" onclick="javascript:deleteUploadFile(3,this, $('#assayfiles_p'),$('#watchAssayFile'));">${caseData.caseDiseaseObject.assayFileArray.get(3)}</em></c:if></i> 
								</div>
								<div id = "imageFilePath" style="width:300px;height:100px;padding-top:3px;margin-left:15px;">
									<i><c:if test="${caseData.caseDiseaseObject.imageFileArray.size()>0}"><em class="delem" onclick="javascript:deleteUploadFile(0,this, $('#imagefiles_p'),$('#watchImageFile'));">${caseData.caseDiseaseObject.imageFileArray.get(0)}</em></c:if></i> 
									<i><c:if test="${caseData.caseDiseaseObject.imageFileArray.size()>1}"><em class="delem" onclick="javascript:deleteUploadFile(1,this, $('#imagefiles_p'),$('#watchImageFile'));">${caseData.caseDiseaseObject.imageFileArray.get(1)}</em></c:if></i> 
									<i><c:if test="${caseData.caseDiseaseObject.imageFileArray.size()>2}"><em class="delem" onclick="javascript:deleteUploadFile(2,this, $('#imagefiles_p'),$('#watchImageFile'));">${caseData.caseDiseaseObject.imageFileArray.get(2)}</em></c:if></i> 
									<i><c:if test="${caseData.caseDiseaseObject.imageFileArray.size()>3}"><em class="delem" onclick="javascript:deleteUploadFile(3,this, $('#imagefiles_p'),$('#watchImageFile'));">${caseData.caseDiseaseObject.imageFileArray.get(3)}</em></c:if></i> 
								</div>
								<div id = "restFilePath" style="width:300px;height:100px;padding-top:0px;margin-left:15px;">
									<i><c:if test="${caseData.caseDiseaseObject.restFileArray.size()>0}"><em class="delem" onclick="javascript:deleteUploadFile(0,this, $('#restfiles_p'),$('#watchRestFile'));">${caseData.caseDiseaseObject.restFileArray.get(0)}</em></c:if></i> 
									<i><c:if test="${caseData.caseDiseaseObject.restFileArray.size()>1}"><em class="delem" onclick="javascript:deleteUploadFile(1,this, $('#restfiles_p'),$('#watchRestFile'));">${caseData.caseDiseaseObject.restFileArray.get(1)}</em></c:if></i> 
									<i><c:if test="${caseData.caseDiseaseObject.restFileArray.size()>2}"><em class="delem" onclick="javascript:deleteUploadFile(2,this, $('#restfiles_p'),$('#watchRestFile'));">${caseData.caseDiseaseObject.restFileArray.get(2)}</em></c:if></i> 
									<i><c:if test="${caseData.caseDiseaseObject.restFileArray.size()>3}"><em class="delem" onclick="javascript:deleteUploadFile(3,this, $('#restfiles_p'),$('#watchRestFile'));">${caseData.caseDiseaseObject.restFileArray.get(3)}</em></c:if></i> 
								</div>
						 </div>					 				 	
				</div>
				<div class="clear"></div>
					<div class="mt20 cas_cha">
						<div class="fl shitin_xinzeng01">
							<div>
								<p class="fl"><span class="fr">诊断：</span></p>
								<textarea onKeyup="javascript:view_countzde(this);" placeholder="最大200字"  id = "diseaseDiagNo1" maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)" cols="" rows="" class="fl" style="width:582px;height:50px;border:1px solid #dddddd;"></textarea>
								<a href="javascript:void(0);" class="fl ml10 anniu att_baocun4">添加诊断</a>
							</div>
						</div>
						<div class = "clear"></div>
						<div  class="mt10 shitin_xinzeng01">
						<p class="fl" style="margin-left:100px">还能输入：<i class="fr" id="diseaseDiagNo1_spellNumzde" style="font-size:12px;min-width:30px">200</i></p>
						<p class="fl" style="width:0px">字</p>
						</div>
					</div>
					<div class="clear"></div>
					<script>var diseaseDiagIndex = 0;</script>
					<c:forEach items = "${caseData.diseaseDiagnosis}" var = "diagnosis">
							<div class="mt10 paDiag2">								
									<div class="mt10 fl shitin_xinzeng01">
										<p class="fl"><i class="fr" style="font-color:black;">诊断<script>diseaseDiagIndex++;document.write(diseaseDiagIndex);</script>：</i></p>
										<p class="fl" style="width:680px;overflow:auto; height:auto;"><span class = "fl diagnosedis" style="text-align:left" >${diagnosis.diagnosis}</span></p>
										<a href="javascript:void(0);" class="fl ml10 anniu" style="margin-left:10px;" onClick="javascript:deleEmdis(this);">删除</a>
									</div>
									<div class="clear"></div>
							</div>
							
					</c:forEach>
					<div class="clear"></div>
				<div class="fl mt20 shitin_xinzeng01" style="width:1300px;">
					<p class="fl"><span class="fr">治疗方案：</span></p>
					<textarea maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" onKeyup="javascript:view_count(this);"  id="plan" placeholder="最大500字" name="plan" cols="" rows="" class="fl" style="width:570px;height:100px;overflow-y:visible;line-height:30px;">${caseData.caseDiseaseObject.plan}</textarea>
					<a href="javascript:void(0);" class = "fl ml10 anniu att_baocun5" style ="margin-left:20px;margin-top:60px;">
					<input type="file" name="planFile" id="plan_file" onchange="javascript:$('#planFilePath').text($(this).val());" style = "width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>上传图片</a>
					<div class="fl">
						<div id = "planFilePath" style="width:300px;height:30px;padding-top:65px;margin-left:15px;"></div>								
					</div>		
				</div>
				<div class="clear"></div>
				<div  class="mt10 shitin_xinzeng01">
					<p class="fl" style="margin-left:100px">还能输入：<span class="fr" id="plan_spellNum" style="font-size:12px;min-width:30px">500</span></p>
					<p class="fl" style="width:0px">字</p>
				</div>
				<div class="clear"></div>
				<div class="fl mt20 shitin_xinzeng01">
					<p class="fl"><span class="fr">预后：</span></p>
					<textarea maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" onKeyup="javascript:view_count(this);" placeholder="最大500字" id = "futureState" name="futureState" cols="" rows="" class="fl" style="width:800px;min-height:150px;overflow-y:visible;">${caseData.caseDiseaseObject.futureState}</textarea>
				</div>
				<div class="clear"></div>
				<div  class="mt10 shitin_xinzeng01">
					<p class="fl" style="margin-left:100px">还能输入：<span class="fr" id="futureState_spellNum" style="font-size:12px;min-width:30px">500</span></p>
					<p class="fl" style="width:0px">字</p>
				</div>
				<div class="clear"></div>
					<div class="mt20 cas_cha">
						<div>
							<div class="fl shitin_xinzeng01">
								<p class="fl"><span class="fr">讨论点：</span></p>
								<textarea maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" onKeyup="javascript:view_count(this);" placeholder="最大500字"  id = "discussProp1" class="fl tki_bianji" style="width:300px;"></textarea>
							</div>
							<div class="fl shitin_xinzeng01">
								<p class="fl" style="margin-left:10px"><span class="fr">解析：</span></p>
								<textarea maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" onKeyup="javascript:view_count(this);" placeholder="最大500字" class="fl tki_bianji" id = "discussAnalysis1" style="width:300px;"></textarea>
								<a href="javascript:void(0);" class="fl ml10 anniu ca1_tianjia">添加讨论点</a>
							</div>
							<div class="clear"></div>
						</div>
						<div  class="fl shitin_xinzeng01">
							<p class="fl" style="margin-left:100px">还能输入：<i class="fr" id="discussProp1_spellNum" style="font-size:12px;min-width:30px">500</i></p>
							<p class="fl" style="width:0px">字</p>
						</div>
						<div  class="fl shitin_xinzeng01">
							<p class="fl" style="margin-left:310px">还能输入：<i class="fr" id="discussAnalysis1_spellNum" style="font-size:12px;min-width:30px">500</i></p>
							<p class="fl" style="width:0px">字</p>
						</div>
					</div>
					<script>var discussIndex=0;</script>
					<c:forEach items = "${caseData.diseaseDiscuss}" var = "item">
					<div class="mt30 discuss1">
						<div>	
							<div class="fl shitin_xinzeng01" style="width:440px;">
								<p class="fl ml30" style="width:100px;"><i class="fr">讨论点<script>discussIndex++;document.write(discussIndex);</script>：</i></p>
								<p class="fl" style="width:300px;overflow:auto; height:auto;text-align:left;"><span class = "fl discussprop">${item.prop}</span></p>
							</div>
							<div class="fl shitin_xinzeng01" style="width:440px;">
								<p class="fl ml30" style="width:100px;" ><i class="fr" >解析<script>document.write(discussIndex);</script>：</i></p>
								<p class="fl" style="width:300px;overflow:auto; height:auto;text-align:left;"><span class = "fl discussanl">${item.analysis}</span></p>
							</div>
							<a href="javascript:void(0);" class="fl anniu" style="margin-left:10px;" onClick="javascript:deleDiscuss(this);">删除</a>
						<div class="clear"></div>
						</div>
					</div>
					</c:forEach>

				<div class="ca1_anniu xiauibu9" style="width:200px;margin-left:400px;margin-top:30px;margin-bottom:50px;">
					<a href="javascript:updateCase(${caseData.caseObject.id})" class = "fl">保存</a>
					<a href="javascript:goBack();" class="fl ml30">返回</a>

				</div>
			</div>
			<input type = "hidden" id = "patientId" name = "patientId" value = ""/>
			<input type = "hidden" id = "pSex" name = "pSex" value = ""/>
			<input type = "hidden" id = "pState" name = "pState" value = "${caseData.caseObject.state}"/>
			<input type = "hidden" id = "pNational" name = "pNational" value = ""/>
			<input type = "hidden" id = "pNumberType1" name = "pNumberType1" value = ""/>
			<input type = "hidden" id = "pNumberType2" name = "pNumberType2" value = ""/>
			<input type = "hidden" id = "diseaseType" name = "diseaseType" value = ""/>
			<input type = "hidden" id = "jsonDiseaseDiagnosis" name = "jsonDiseaseDiagnosis" value = ""/>
			<input type = "hidden" id = "jsonPatientDiagnosis" name = "jsonPatientDiagnosis" value = ""/>
			<input type = "hidden" id = "jsonDiscuss" name = "jsonDiscuss" value = ""/>
			
			<input type = "hidden" id = "assayFileState" name = "assayFileState" value = ""/>
			<input type = "hidden" id = "imageFileState" name = "imageFileState" value = ""/>
			<input type = "hidden" id = "restFileState" name = "restFileState" value = ""/>
			
</form>
</div>
<div class="clear"></div>
</div>


<script type="text/javascript">
var diagIndex = 1;
var diseaseDiagIndex = 1;
var discussIndex = 1;
var numberType =["请选择","门诊号","住院号","床位号","其他编号"];
var diseaseTypeArray = ["首诊","复诊","入院","出院","手术"];
if("${caseData.patientObject.number1Type}" != -1)
{
	var number1type = ${caseData.patientObject.number1Type};
	$('#pType1').text(numberType[number1type]);
}
if("${caseData.patientObject.number2Type}" != -1)
{
	var number2type = ${caseData.patientObject.number2Type};
	$('#pType2').text(numberType[number2type]);
}
if("${caseData.caseDiseaseObject.diseaseType}" != -1)
{
	var dType = ${caseData.caseDiseaseObject.diseaseType};
	$('#diseaseTypeView').text(diseaseTypeArray[dType]);
}

//手机校验
$('#pPhoneNumber1').blur(function(){
	if($(this).val().length != 11 && $(this).val().length != 0){
  		alert("手机号码不正确！");
  		$('#pPhoneNumber1').val('');
  		return false;
  	}else{
  		var obj = document.getElementById("pPhoneNumber1");
  	    var value = obj.value;
  	    var regTel1 = /^1(3|4|5|7|8)\d{9}$/.test(value);
  	    if (value != "") {
  	      if (!regTel1) { 
  	        alert("手机号输入有误！");
  	        $('#pPhoneNumber1').val("");
  	        return false;
  	      }
  	    }
  	}
});

//固话校验
$('#pPhoneNumber2').blur(function(){
	//if($(this).val().length != 12 && $(this).val().length != 11 && $(this).val().length != 0){
  	var patrn = /^[0-9]{3,4}[-,－][0-9]{8,9}$/;
	if($(this).val() =="" || !patrn.test($(this).val()))
	{
		alert("固定电话号码不正确！请按照如下格式填写：123-12345678或1234-12345678");
		$(this).val('');
		return false;
	}
});

$('#pNT2').click(function(){
	if($('#pType1').text() == $('#pType2').text()){
		if($('#pType2').text()!='请选择'){
			alert("编号类型不能重复！");
			$('#pType2').text('请选择');
			$('#pNumber2').val("");
		}
	}
	if($('#pType2').text()=='请选择'){
		$('#pNumber2').val("");
	}
});
$('#pNT1').click(function(){
	if($('#pType1').text() == $('#pType2').text()){
		if($('#pType1').text()!='请选择'){
			alert("编号类型不能重复！");
			$('#pType1').text('请选择');
			$('#pNumber1').val("");
		}
	}
	if($('#pType1').text()=='请选择'){
		$('#pNumber1').val("");
	}
});



$(function(){
	//select menu
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var type = ${type};
		var submenuindex;
		if(type == 1){
			 submenuindex = 1;
		}
		else
		{
			submenuindex = 2;
		}
		var submenu = "mar_left0" + menuindex;
		$('.tk_mainmenu>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
	

	$('.discuss1').each(function(index,domEle){
		index = index + 2;
		discussIndex++;
		var discussId = "discussProp" + index;
		var analysisId = "discussAnalysis" + index;
		$(domEle).find(".shitin_xinzeng01").find("span").eq(0).attr("id",discussId);
		$(domEle).find(".shitin_xinzeng01").find("span").eq(1).attr("id",analysisId);
	});
	$('.paDiag1').each(function(index,domEle){
		diagIndex++;
		index = index + 2;
		var diagId = "diagNo" + index;
		$(domEle).find(".shitin_xinzeng01").find("span").attr("id",diagId);
	});
	$('.paDiag2').each(function(index,domEle){
		index = index + 2;
		diseaseDiagIndex++;
		var diagId = "diseaseDiagNo" + index;
		$(domEle).find(".shitin_xinzeng01").find("span").attr("id",diagId);
	});
	//导航切换
	$('.tk_center>ul>li').mousemove(function(){
		$(this).addClass('action').siblings('.action').removeClass('action');
		var n=$(this).index();
		$('.tk_er div').eq(n).show().siblings().hide();
	});
	//下拉框
		$('.tik1_select').click(function(){
		 $(".tk1_list").css("display","none");
			$(this).find('ul').show();
		});
		$('.tk1_list li').click(function(){
			var str=$(this).text();
			$(this).parent().parent().find('div').find('b').text(str);
			//$(this).parent().hide();
			$('.tk1_list').slideUp(50);
		});
		$('.tik1_select').click(function(e){
			return false;
		});
		$(document).click(function(){
			$('.tk1_list').hide('fast');
		});
	
		//更多查询
		$('.mat1_chaxun').click(function(){
			$('.tk_yingcang').show();
			$('.shiti').hide();
			$('.none').hide();
		});
		$('.mat1_baochun').click(function(){
			$('.tk_yingcang').hide();
			$('.shiti').show();
			$('.none').show();
		});
		//修改密码
		$('.password').click(function(){
			$('.make01').show();
		});
		$('.denglu_5').click(function(){
			$('.make01').hide();
		});
		$('.att_baocun3').click(function(){
			if($('#diagNo1').val() != "")
			{
				var curDiagId = '#diagNo' + diagIndex;
				diagIndex++;
				var newDiagId = 'diagNo' + diagIndex;
				var newDiag ='<div class="mt10"><div class="fl shitin_xinzeng01"><p class="fl"><i class="fr">诊断'+(diagIndex-1)+'：</i></p><p class="fl" style="width:680px; overflow:auto; height:auto;"><span class="fl diagnose" maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)" style="text-align:left;" id = "';
				newDiag += newDiagId + '">' + $('#diagNo1').val() + '</span></p><a href="javascript:void(0);" class="fl ml10 anniu" style="margin-left:10px;" onClick="javascript:deleEm(this);">删除</a></div><div class="clear"></div></div>';
				//newDiag += '<li></li>';
				
				$(newDiag).insertAfter($(curDiagId).parent().parent().parent());
				
				$('#diagNo1').val("");
			}
			else
			{
				alert("请输入诊断");
			}
			return false;
		});
		$('.att_baocun4').click(function(){
			if($('#diseaseDiagNo1').val() != "")
			{
				var curDiseaseDiagId = '#diseaseDiagNo' + diseaseDiagIndex;
				diseaseDiagIndex ++;
				var newDiagId = 'diseaseDiagNo' + diseaseDiagIndex;
				var newDiag ='<div class ="mt10 paDiag2"><div class="mt10 fl shitin_xinzeng01"><p class="fl"><i class="fr">诊断'+(diseaseDiagIndex-1)+'：</i></p><p class="fl" style="width:680px; overflow:auto; height:auto;"><span class="fl diagnosedis" maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)" style="text-align:left;" id = "';
				newDiag += newDiagId + '">' + $('#diseaseDiagNo1').val() + '</span></p><a href="javascript:void(0);" class="fl ml10 anniu" style="margin-left:10px;" onClick="javascript:deleEmdis(this);">删除</a></div><div class="clear"></div></div>';
				$(newDiag).insertAfter($(curDiseaseDiagId).parent().parent().parent());
				$('#diseaseDiagNo1').val("");
			}
			else
			{
				alert("请输入诊断");
			}
			return false;
		});
		$('.ca1_tianjia').click(function(){
			if($('#discussProp1').val() == "")
			{
				alert("请输入讨论点");
				return;
			}
			if($('#discussAnalysis1').val() == "")
			{
				alert("请输入解析");
				return;
			}
			var curdiscussProp = '#discussProp' + discussIndex;
			discussIndex ++;
			var newPropId = 'discussProp' + discussIndex;
			var newAnalysisId = 'discussAnalysis' + discussIndex;
			var newDiag ='<div><div class="mt30 discuss1"><div class="fl shitin_xinzeng01" style="width:440px;"><p class="fl ml30" style="width:100px;"><i class="fr">讨论点'+(discussIndex -1)+'：</i></p><p class="fl" style="width:300px;overflow:auto; height:auto;text-align:left;"><span class = "fl discussprop" id = "' + newPropId +'">'+ $('#discussProp1').val() + '</span></p></div><div class="fl shitin_xinzeng01" style="width:440px;"><p class="fl ml30" style="width:100px;" ><i class="fr" >解析'+(discussIndex -1)+'：</i></p>';
			newDiag += '<p class="fl" style="width:300px;overflow:auto; height:auto;text-align:left;"><span class = "fl discussanl" id = "' + newAnalysisId + '">' + $('#discussAnalysis1').val() + '</span></p></div><a href="javascript:void(0);" class="fl anniu" style="margin-left:10px;" onClick="javascript:deleDiscuss(this);">删除</a><div class="clear"></div></div></div>';
			$(newDiag).insertAfter($(curdiscussProp).parent().parent().parent());
			$('#discussProp1').val("");
			$('#discussAnalysis1').val("");
		});
		$('#courseStr').click(function(){
			initPropList("学科","${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#course'), $('#courseStr'));
			$('.att_make01').show();
		});		
		$('.takuang_xk01').click(function(){
			initPropList("主题","${ctx}/propManage/getPropListAjax.do" ,7, 0, 1, 0, $('#subject'), $('#subjectStr'));
			$('.att_make01').show();
		});
});


function goBack() {
	window.history.back();
}
function updateCase(id)
{
	
	if($('#pType1').text() == "请选择" && $('#pNumber1').val() !=""){
		alert("请选择编号类型1!");
		return;
	}
	else if($('#pType2').text() == "请选择" && $('#pNumber2').val() !=""){
		alert("请选择编号类型2!");
		return;
	}
	else if($('#pType1').text() != "请选择" && $('#pNumber1').val() ==""){
		alert("请输入编号类型1!");
		return;
	}
	else if($('#pType2').text() != "请选择" && $('#pNumber2').val() ==""){
		alert("请输入编号类型2！");
		return;
	} 
		
	
	if($('#pNationalState').text() == '是')
	{
		if($('#course').val() ==""){
			alert("请选择学科!");
				return;
		}
		if($('#subject').val() ==""){
			alert("请选择主题!");
				return;
		}
		if($('#pCertificate').val() ==""){
			alert("请输入患者身份证号!");
			return;
		}
		/* else if($('#pBirthDay').val() ==""){
			alert("请输入患者出生日期!");
			return;
		}
		else if($('#pAge').val() ==""){
			alert("请输入年龄!");
			return;
		} 
		if($('#pNumber1').val() ==""){
			alert("请输入编号类型1!");
			return;
		}
		if($('#pNumber2').val() ==""){
			alert("请输入编号类型2!");
			return;
		}*/
		if(diagIndex == 1)
		{
			alert("请添加诊断!");
			return;
		}
		var patrn = /^[0-9]{3,4}[-,－][0-9]{8,9}$/;
		if($('#pPhoneNumber2').val() =="" || !patrn.test($('#pPhoneNumber2').val()))
		{
			alert("固定电话号码不正确！请按照如下格式填写：123-12345678");
			return;
		}
		 if($('#disease_date').val() ==""){
			alert("请选择病程时间!");
			return;
		}
		else if($('#Complaint').val() ==""){
			alert("请输入主诉!");
			return;
		}
	}
	if($('#pNationalState').text() == '是')
	{
		$('#pNational').val("1");
	}
	else /* if($('#pNationalState').text() == '否') */
	{
		$('#pNational').val("0");
	}
	/* else
	{
		$('#pNational').val("-1");
	} */
	for(var j = 0 ; j < numberType.length ; j++)
	{
		if($('#pType1').text() == numberType[j]){
			$('#pNumberType1').val(j);
		}
	}
	for(j = 0 ; j < numberType.length ; j++)
	{
		if($('#pType2').text() == numberType[j]){
			$('#pNumberType2').val(j);
		}
	}
	for(j = 0 ; j < diseaseTypeArray.length ; j++)
	{
		if($('#diseaseTypeView').text() == diseaseTypeArray[j]){
			$('#diseaseType').val(j);
		}
	}	
		var diseaseArray = "[";
		var index = 0;
		for(i = 2 ; i <= diagIndex ; i++){
			var patientDiag = '#diagNo' +i;
			var jsonStr = JSON.stringify({patientDiag : $(patientDiag).text()});
			index++;
			diseaseArray += jsonStr;
			if(i != diagIndex)
			{
				diseaseArray += ",";
			}
		}
		diseaseArray += "]";
		$('#jsonPatientDiagnosis').val(diseaseArray);
		
		diseaseArray = "[";
		index = 0;
		for(i = 2 ; i <=diseaseDiagIndex ; i++){
			var diseaseDiag = '#diseaseDiagNo' +i;
			var jsonStr = JSON.stringify({diseaseDiag : $(diseaseDiag).text()});
			index++;
			diseaseArray += jsonStr;
			if(i != diseaseDiagIndex)
			{
				diseaseArray += ",";
			}
		}
		diseaseArray += "]";
		$('#jsonDiseaseDiagnosis').val(diseaseArray);
		var discussArray = "[";
		index = 0;
		for(i = 2 ; i <= discussIndex ; i++){
			var discussProp = '#discussProp' +i;
			var discussAnalysis = '#discussAnalysis' +i;
			var jsonStr = JSON.stringify({discussProp : $(discussProp).text(),discussAnalysis:$(discussAnalysis).text()});
			discussArray += jsonStr;
			if(i != discussIndex)
			{
				discussArray += ",";
			}
			index++;
		}
		discussArray += "]";
		$('#jsonDiscuss').val(discussArray);
		$('#pSex').val($("input[name='nax']:checked").val());
	
	//
	var state = "";
	$('#assayFilePath').find('i').each(function(index){
		if($(this).text())
			state+="1,";
		else
			state+="0,";
	});
	$('#assayFileState').val(state);
	state = "";
	$('#imageFilePath').find('i').each(function(index){
		if($(this).text())
			state+="1,";
		else
			state+="0,";
	});
	$('#imageFileState').val(state);
	state = "";
	$('#restFilePath').find('i').each(function(index){
		if($(this).text())
			state+="1,";
		else
			state+="0,";
	});
	$('#restFileState').val(state);
	//判断上传文件的格式和大小---begin---
	if($("#assayFile").val() != "" && !fileUploadValid("assayFile",2)){
        return ;
	}
	if($("#assayFile1").val() != "" && !fileUploadValid("assayFile1",2)){
        return ;
	}
	if($("#assayFile2").val() != "" && !fileUploadValid("assayFile2",2)){
        return ;
	}
	if($("#assayFile3").val() != "" && !fileUploadValid("assayFile3",2)){
        return ;
	}
	if($("#imageFile").val() != "" && !fileUploadValid("imageFile",2)){
        return ;
	}
	if($("#imageFile1").val() != "" && !fileUploadValid("imageFile1",2)){
        return ;
	}
	if($("#imageFile2").val() != "" && !fileUploadValid("imageFile2",2)){
        return ;
	}
	if($("#imageFile3").val() != "" && !fileUploadValid("imageFile3",2)){
        return ;
	}
	if($("#restFile").val() != "" && !fileUploadValid("restFile",2)){
        return ;
	}
	if($("#restFile1").val() != "" && !fileUploadValid("restFile1",2)){
        return ;
	}
	if($("#restFile2").val() != "" && !fileUploadValid("restFile2",2)){
        return ;
	}
	if($("#restFile3").val() != "" && !fileUploadValid("restFile3",2)){
        return ;
	}
	if($("#plan_file").val() != "" && !fileUploadValid("plan_file",2)){
        return ;
	}
	//判断上传文件的格式和大小---end---
	document.getElementById("diseaseForm").action = "${ctx}/caseManage/updateCase.do?type=" + ${type} + "&caseId=" + id;
	document.getElementById("diseaseForm").submit();
	alert("保存成功！");
}

//display birthday and age
$('#pCertificate').blur(function(){
	var birthday_row = $('#pCertificate').val();
  	if($(this).val().length > 0){
        if (IdentityCodeValidRegister($("#pCertificate").val())==false) { 
            alert("身份证号不正确！");
            $('#pCertificate').val("");
	  		$('#pBirthDay').val('');
	  		$('#pAge').val('');
            return false;
        }
  	}else{
  		$('#pCertificate').val("");
	  	$('#pBirthDay').val('');
	  	$('#pAge').val('');
        return false;
  	}
  	birthday_ = birthday_row.substring(6,14);
  	year_ = birthday_.substring(0,4);
  	day_ = birthday_.substring(6,8);
  	month_ =	birthday_.substring(4,6);
  	birthday_ = year_ + '-' + month_ + '-' + day_;
  	$('#pBirthDay').val(birthday_);
  	var year;
  	today = new Date();
  	year = today.getFullYear();
  	$('#pAge').val(year-parseInt(year_));  	
  	
});

function view_count(obj) {
	
	var str = $(obj).val();
	var left_count = 500-str.length;
	var id = $(obj).prop('id');
	var spell = $('#'+id+'_spellNum');
	$(spell).text(left_count);
}
function view_count5(obj){
	//var next_obj = $(obj).next();
	var str = $(obj).val();
	var left_count = 500-str.length;
	var spell = $(obj).parent().find('#spellNum');
	$(spell).text(left_count);
}
function view_countzd(obj) {
	
	var str = $(obj).val();
	var left_count = 200-str.length;
	var id = $(obj).prop('id');
	var spell = $('#'+id+'_spellNumzd');
	$(spell).text(left_count);
}
function view_countzde(obj) {
	
	var str = $(obj).val();
	var left_count = 200-str.length;
	var id = $(obj).prop('id');
	var spell = $('#'+id+'_spellNumzde');
	$(spell).text(left_count);
}

$('textarea').each(function(){
	view_count($(this));
	view_count5($(this));
});
function deleEm(obj) {
	var curId  = $(obj).prev().children().prop("id").substring('diagNo'.length);
	$(obj).parent().parent().remove();
	diagIndex --;
	$('.diagnose').each(function(){
		var id =  $(this).prop("id").substring('diagNo'.length);
		if (id > curId) {
			id --;
			$(this).attr("id", 'diagNo' + id);
			$(this).parent().prev().children().text('诊断' + (id-1) + '：');
		}
	});
	
}
function deleEmdis(obj) {
	var curId  = $(obj).prev().children().prop("id").substring('diseaseDiagNo'.length);
	$(obj).parent().parent().remove();
	diseaseDiagIndex --;
	$('.diagnosedis').each(function(){
		var id =  $(this).prop("id").substring('diseaseDiagNo'.length);
		if (id > curId) {
			id --;
			$(this).attr("id", 'diseaseDiagNo' + id);
			$(this).parent().prev().children().text('诊断' + (id-1) + '：');
		}
	});
	
}
function deleDiscuss(obj) {
	var curId  = $(obj).prev().prev().children().next().children().prop("id").substring('discussProp'.length);
	$(obj).parent().parent().remove();
	discussIndex --;
/* 	$('.discussanl').each(function(){
		var id =  $(this).prop("id").substring('discussAnalysis'.length);
		if (id > curId) {
			id --;
			$(this).attr("id", 'discussAnalysis' + id);
			$(this).parent().prev().children().text('解析' + (id-1) + '：');
		}
	});
	$('.discussprop').each(function(){
		var id =  $(this).prop("id").substring('discussProp'.length);
		if (id > curId) {
			id --;
			$(this).attr("id", 'discussProp' + id);
			$(this).parent().prev().children().text('讨论点' + (id-1) + '：');
		}
	});
 */	
}


function selectProp() {

		$(kuangcode).val($('.xs_kuangcode').text());
 		var selPropHtml = $('.xs_kuang').html().replace(brexp,",");
		$('.xs_kuang').html(selPropHtml);
		$(kuang).text($('.xs_kuang').text());

		var url = "${ctx}/propManage/getPropListAjax.do";
		var ids = $(kuangcode).val();
		
		if ($(kuangcode).prop('id') == "course"){
		$('#ICD').val("");
		if(ids != null && ids !=""){
			var params = "mode=getICD&ids="+ids;  
			$.ajax({
				type: 'POST',
				url: url,
				data : params,
				dataType: 'text',
				success : function (newId){
					$('#ICD').val(newId);
				}
			});
		}
		}
}
function insertAssayFile(index, fileObj, divObj){
	var filepath = $(fileObj).val();
	var index=0;
	if (filepath){
		//filenames = '<em class="delem" onclick="javascript:deleteUploadFile('+index+',this, $(\'#assayfiles_p\'),$(\'#watchAssayFile\'));">'+filepath+'</em>';
		filenames = '<em class="delem" onclick="javascript:deleteUploadFile('+index+',this, $(\'#assayfiles_p\'));">'+filepath+'</em>';
		//views =  '<p id=\"'+index+'\" onclick="javascript:download(filepath);" class = "fl ml10 anniu att_baocun" id="assayfiles_p" style="float:right;margin-right:5px;margin-top:3px;width:80px;height:23px;line-height:23px;">预览</p>';
		//$('#watchAssayFile').find('p').eq(index).before(views);
		$(divObj).find('i').eq(index).html(filenames);
		$(fileObj).hide();
	}
}

function insertImageFile(index, fileObj, divObj)
{
	var filepath = $(fileObj).val();
	if (filepath){
		filenames = '<em class="delem" onclick="javascript:deleteUploadFile('+index+',this, $(\'#imagefiles_p\'));">'+filepath+'</em>';
		$(divObj).find('i').eq(index).html(filenames);
		$(fileObj).hide();
	}
}

function insertRestFile(index, fileObj, divObj)
{
	var filepath = $(fileObj).val();
	if (filepath){
		filenames = '<em class="delem" onclick="javascript:deleteUploadFile('+index+',this, $(\'#restfiles_p\'));">'+filepath+'</em>';
		$(divObj).find('i').eq(index).html(filenames);
		$(fileObj).hide();
	}
}

//function deleteUploadFile(index, emObj, pObj,vObj)
function deleteUploadFile(index, emObj, pObj)
{
	//$(vObj).find('p').eq(index).remove();
	$(emObj).remove();
	//$(vObj).find('p').eq(index).val('');
	//$(vObj).find('p').eq(index).show();
	$(pObj).find('input[type=file]').eq(index).val('');
	$(pObj).find('input[type=file]').eq(index).show();
}

$('#assayFilePath').find('i').each(function(index){
	if($(this).text())
		$('#assayfiles_p').find('input[type=file]').eq(index).hide();
});
$('#imageFilePath').find('i').each(function(index){
	if($(this).text())
		$('#imagefiles_p').find('input[type=file]').eq(index).hide();
});
$('#restFilePath').find('i').each(function(index){
	if($(this).text())
		$('#restfiles_p').find('input[type=file]').eq(index).hide();
});

	function download(fileName) {
		alert(fileName);
		var url = "${ctx}/storage/upload/case/" + fileName;
		//document.location.href = url;
		window.open(url,"Download Window", "width=600, height=400, ");
	}

</script>
</body>
</html>
