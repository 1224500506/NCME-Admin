<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/new_page/top.jsp"%>
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="${ctx}/new_page/css/reset.css" />
<link rel="stylesheet" href="${ctx}/new_page/css/index.css" />
<style>
 .time-bar.fl{
 	font-size:13px;
 	color:#333;
 	height: 25px;
    line-height: 25px;
 }
</style>
<script type="text/javascript" src="${ctx}/new_page/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/new_page/js/fileUpload.js"></script>
<script type="text/javascript" src="${ctx}/new_page/js/validate.js"></script>
</head>
	<body class = "bjs">
	<div class="center">
		<div class="tk_center01">
			<form method = "POST" name = "diseaseForm" id = "diseaseForm" enctype="multipart/form-data">
			<ul class="cas1_xuanxing">
				<li  class="action" id="base_info">基础信息</li>
				<li id="base_info2">病程信息</li>
			</ul>
			<div class="clear"></div>
			<div class="att1_leixing">
				<div class="cas1_certen">
					<!-- 基础信息 -->
					<div class="thi_shitineirong ca1_jichu" style="width:1080px;margin:0 auto;">
						<ul style="margin-top:30px;">
							<li style="margin-top:20px;">
							<div class="fl shitin_xinzeng01">
									<p class="fl"><span class="fr">学科：</span><em class="fr">*</em></p>
									<input type="hidden" class="fl tik_select" id="propIds" name="propIds" value="${propIds}"/>
									<input type="hidden" class="fl tik_select" id="propNames01" name="propNames" value="${propNames}"/>
									<div class="fl tki_bianji takuang_xk" id="propNames">${propNames}</div>
									
							</div>
							<div class="fl shitin_xinzeng01">
								<p class="fl ml30"><span class="fr">ICD10：</span></p>
								
								<input type="text"  id="ICD" name="ICD" class="fl tki_bianji" readonly value="${ICD}"/>
							</div>
							<div class="clear"></div>
							</li>
							<li style="margin-top:5px;">
								<div class="fl shitin_xinzeng01">
									<p class="fl"><span class="fr">主题：</span><em class="fr">*</em></p>
									<input type="hidden" class="fl tik_select" id="zutiIds" name="zutiIds" value="${zutiIds}"/>
									<input type="hidden" class="fl tik_select" id="zutiNames01" name="zutiNames" value="${zutiNames}"/>
									<div class="fl tki_bianji takuang_xk01" id="zutiNames">${zutiNames}</div>
								</div>
								<div class="fl shitin_xinzeng01">
									<p class="fl ml30">就诊日期：</p>
									<input type="text" class="fl tki_bianji" name="diagnosis_date" style="width:258px;" id="diagnosis_date" onClick="WdatePicker()" readonly="readonly">
								</div>
								<div class="clear"></div>
							</li>
							<li style="margin-top:5px;">
								<div class="fl shitin_xinzeng01">
									<p class="fl">患者姓名：</p>
									<input type="text" class="fl tki_bianji" name = "pName" id = "pName" style="width:258px;"/>
								</div>
								<div class="fl shitin_xinzeng01">
									<p class="fl ml30"><span class="fr">性别：</span><em class="fr">*</em></p>
									<p class="fl">
										<input type="radio" name="nax" checked="checked" value = "0" class="fl mt10 ml20"/>
										<span class="fl">男</span>
										<input type="radio" class="fl mt10 ml30" value = "1" name="nax"/>
										<span class="fl">女</span>
									</p>
								</div>
								<div class="clear"></div>
							</li>
							<li style="margin-top:5px;">
								<div class="fl shitin_xinzeng01">
									<p class="fl"><span class="fr">患者身份证号：</span><em class="fr">*</em></p>
									<input name="pCertificate" id = "pCertificate" class="fl tki_bianji" maxLength = "18"></input>
								</div>
								<div class="fl shitin_xinzeng">
									<p class="fl ml30"><span class="fr">是否上传国家库：</span><em class="fr">*</em></p>
									<div class="fl tik1_select">
										<div class="tik1_position">
											<b class="ml5" id = "pNationalState" name = "pNationalState">是</b>
											<p class="tik1_position01" style="width:25px;"><i class="tk1_bjt10"></i></p>
										</div>
										<ul class="fl tk1_list" style="display:none;">
											<li>是</li>
											<li>否</li>
										</ul>
									</div>
								</div>
								<div class="clear"></div>
							</li>
														<li style="margin-top:5px;">
								<div class="fl shitin_xinzeng01">
									<p class="fl"><span class="fr">患者出生日期：</span></p>
									<input type="text" class="fl tki_bianji" name = "pBirthDay"  id = "pBirthDay" readonly="readonly" style="width:258px;"/>
									<a href="javascript:goBack();" ></a>
								</div>
								<!-- <script type="text/javascript">$('#pCertificate').blur(function(){
									$('#pBirthDay').val(${newPatient.birthday});
								});
								</script> -->
								<div class="fl shitin_xinzeng01">
									<p class="fl ml30"><span class="fr">年龄：</span></p>
									<input type="text" class="fl tki_bianji" name = "pAge" id = "pAge" readonly="readonly" style="width:63px;"/>
									<p class="fl ml20" style="width:5px;"><span class="fl">岁</span></p>
								</div>
								<div class="clear"></div>
							</li>
							<li style="margin-top:5px;">
								<div class="fl shitin_xinzeng01">
									<p class="fl"><span class="fr">编号类型1：</span></p>
									<div class="fl tik1_select" style="width:110px;">
										<div class="tik1_position">
											<b class="ml5" id = "pNumberType1">请选择</b>
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
									<input type="text" name = "pNumber1" id = "pNumber1" class="fl ml5 tki_bianji" style="width:140px;"/>
								</div>
								<div class="fl shitin_xinzeng">
									<p class="fl ml30"><span class="fr">编号类型2：</span></p>
									<div class="fl tik1_select" style="width:110px;">
										<div class="tik1_position">
											<b class="ml5" id = "pNumberType2">请选择</b>
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
									<input type="text" name = "pNumber2" id ="pNumber2" class="fl ml5 tki_bianji" style="width:140px;"/>
								</div>
								<div class="clear"></div>
							</li>
							<li style="margin-top:5px;">
								<div class="fl shitin_xinzeng01">
									<p class="fl">手机号：</p>
									<input type="text" name = "pPhoneNumber1" id = "pPhoneNumber1" class="fl tki_bianji" style="width:258px;" maxLength = "11"/>
								</div>
								<div class="fl shitin_xinzeng01">
									<p class="fl ml30"><span class="fr">固定电话：</span><em class="fr">*</em></p>
									<input type="text" name = "pPhoneNumber2" id = "pPhoneNumber2" class="fl tki_bianji" style=""/>
									
								</div>
								<div class="clear"></div>
							</li>
							<li>
								<div class="fl shitin_xinzeng01">
									<p class="fl"><span class="fr">诊断：</span><em class="fr">*</em></p>
									<textarea cols="" placeholder="最多200字" onKeyup="javascript:view_count1(this);" id = "diagNo1" maxLength = "200" onpropertychange="if(value.length>200) value=value.substr(0,200)" onpropertychange="if(value.length>200) value=value.substr(0,200)" rows="" class="fl" style="width:680px;height:70px;border:1px solid #dddddd;"></textarea>
									<a href="javascript:void(0);" class="fl ml10 anniu att_baocun3" style="margin-top:14px;">添加诊断</a>
								</div>								
								<div  class="shitin_xinzeng01">
									<p class="fl" style="margin-left:100px">还能输入：<span class="fr" id="spellNum0" style="font-size:12px;min-width:30px">200</span></p>
									<p class="fl" style="width:0px">字</p>
								</div>
								<div class="clear"></div>	
							</li>
							<li style="margin-top:5px;">
								<div class="fl shitin_xinzeng01">
									<p class="fl"><span class="fr">基本病情：</span><em class="fr">*</em></p>
									<textarea name="" cols="" onKeyup="javascript:view_count(this);" placeholder="最多500字" name = "diagName" id = "diagName" maxLength = "500" onpropertychange="if(value.length>500) value=value.substr(0,500)" onpropertychange="if(value.length>500) value=value.substr(0,500)" rows="" class="fl" style="width:680px;height:50px;border:1px solid #dddddd;"></textarea>
									<span class="fr mt5 ml30" style="margin-left:65px" id="diagName_zi_Number"></span>
								</div>
								<div  class="shitin_xinzeng01">
									<p class="fl" style="margin-left:100px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">500</span></p>
									<p class="fl" style="width:0px">字</p>
								</div>
								<div class="clear"></div>
							</li>
							<li style="margin-top:15px;">
								<div class="ca1_anniu" style="width:200px;">
									<a href="javascript:void(0);" class="fl anniu att_baocun" id = "save">下一步</a>
									<a href="javascript:goBack();" class="fl ml30 anniu att_baocun">返回</a>
								</div>
								<div class="clear"></div>
							</li>
						</ul>
						<div class="clear"></div>
					</div>
							
			
				<div class="ca1_jichu" style="display:none;">
					<ul class="ca1_erji">
						<li class="action"><p class="fl"><span class="fr">主诉</span></p></li>
						<li>现病史</li>
						<li>个人史</li>
						<li>体征</li>
						<li>辅助检查</li>
						<li>诊断</li>
						<li>治疗方案</li>
						<li>预后</li>
						<li>讨论点</li>
					</ul>
					<div class="clear"></div>
					<div class="ca1_erxuanxing" style="width:1080px;margin:0 auto;">
						<!-- 主诉 -->
						<div class="ca1_bingkuang zia_yi01"  style="">
							<div class="mt30 cas_cha">
								<p class="fl time-bar">
									<em class="fl">病程时间：</em>
									<input type="hidden" name="disease_date" id="disease_date"/>
									<input type="text" style="width:40px;text-align:center;" name="diseaseYear" id="diseaseYear" value="0" maxlength="3"/> 年 
									<input type="text" style="width:40px;text-align:center;" name="diseaseMonth" id="diseaseMonth" value="0" maxlength="2"/> 月 
									<input type="text" style="width:40px;text-align:center;" name="diseaseDay" id="diseaseDay" value="0" maxlength="2"/> 日 
								</p>
								<p class="fl ml20">
									<em class="fl">病程类型：</em>
									<select class="fl" id = "diseaseType" name = "diseaseType" style="width:150px;height:25px;border:1px solid #dddddd;">
										<option value="0">首诊</option>
										<option value="1">复诊</option>
										<option value="2">入院</option>
									</select>
								</p>
								<div class="clear"></div>
							</div>
							<div class="mt10 cas_cha">
								<p class="fl"><em class="fl">主<i style="padding:0px 13px;"></i>诉：</em>
								<textarea onKeyup="javascript:view_count2(this);" placeholder="最大500字" id = "complaint" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" name="complaint" cols="" rows="" class="fl" style="width:800px;min-height:150px;overflow-y:visible;"></textarea></p>
								<div class="clear"></div>
							</div>
							<div  class="shitin_xinzeng01">
								<p class="fl" style="margin-left:40px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">500</span></p>
								<p class="fl" style="width:0px">字</p>
							</div>
							<div class="ca1_anniu xiauibu1" style="margin-left:450px;">
								<a href="javascript:void(0);" class = "fl">下一步</a>
							</div>
						</div>
						<!-- 现病史 -->
						<div class="ca1_bingkuang zia_yi02" style="display:none;">
							<div class="mt30 cas_cha">
								<p class="fl"><em class="fl">现病史：</em>
								<textarea onKeyup="javascript:view_count2(this);" placeholder="最大500字" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" id = "currentDisease" name="currentDisease" cols="" rows="" class="fl" style="width:800px;min-height:150px;overflow-y:visible;"></textarea></p>
								<div class="clear"></div>
							</div>
							<div  class="shitin_xinzeng01">
								<p class="fl" style="margin-left:20px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">500</span></p>
								<p class="fl" style="width:0px">字</p>
							</div>
							<div class="ca1_anniu " style="margin-left:300px;width:200px;">
								<a href="javascript:void(0);" class = "fl anniu shangibu2">上一步</a>
								<a href="javascript:void(0);" class = "fl anniu xiauibu2" style = "margin-left:20px;">下一步</a>
							</div>
						</div>
						<!-- 个人史 -->
						<div class="ca1_bingkuang zia_yi03" style="display:none;">
							<div class="mt30 cas_cha">
								<p class="fl">
									<em class="fl">个人史：</em>
									<ul class="fl ca1_gerenshi">
										<li style="height: 45px;">
											<span class="fl" style="text-align:center;height:45px;line-height:45px;">既往史</span>
											<textarea rows="" cols="" id = "history1" onKeyup="javascript:view_count5(this);" name = "history1" class="fl" placeholder="最大500字" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" style="width:60%;height:100%"></textarea>
											<div class="fl" style="text-align:center;height:45px;line-height:45px;">还能输入:<em id="spellNum" style="font-size:12px;min-width:30px;margin-left:10px;">500</em><em style="margin-left:5px;">字</em></div>
										</li>
										<li style="height: 45px;">
											<span class="fl" style="text-align:center;height:45px;line-height:45px;">个人史</span>
											<textarea rows="" cols="" id = "history2" onKeyup="javascript:view_count5(this);" name = "history2" class="fl" placeholder="最大500字" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" style="width:60%;height:100%"></textarea>
											<div class="fl" style="text-align:center;height:45px;line-height:45px;">还能输入:<em id="spellNum" style="font-size:12px;min-width:30px;margin-left:10px;">500</em><em style="margin-left:5px;">字</em></div>
										</li>
										<li style="height: 45px;">
											<span class="fl" style="text-align:center;height:45px;line-height:45px;">过敏史</span>
											<textarea rows="" cols="" id = "history3" onKeyup="javascript:view_count5(this);" name = "history3" class="fl" placeholder="最大500字" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" style="width:60%;height:100%"></textarea>
											<div class="fl" style="text-align:center;height:45px;line-height:45px;">还能输入:<em id="spellNum" style="font-size:12px;min-width:30px;margin-left:10px;">500</em><em style="margin-left:5px;">字</em></div>
										</li>
										<li style="height: 45px;">
											<span class="fl" style="text-align:center;height:45px;line-height:45px;">家族史</span>
											<textarea rows="" cols="" id = "history4" onKeyup="javascript:view_count5(this);" name = "history4" class="fl" placeholder="最大500字" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" style="width:60%;height:100%"></textarea>
											<div class="fl" style="text-align:center;height:45px;line-height:45px;">还能输入:<em id="spellNum" style="font-size:12px;min-width:30px;margin-left:10px;">500</em><em style="margin-left:5px;">字</em></div>
										</li>
									</ul>
								<div class="clear"></div>
							</div>
							<div class="ca1_anniu " style="margin-left:300px;width:200px;">
								<a href="javascript:void(0);" class = "fl anniu shangibu3">上一步</a>
								<a href="javascript:void(0);" class = "fl anniu xiauibu3" style = "margin-left:20px;">下一步</a>
							</div>
						</div>
						<!-- 体征 -->
						<div class="ca1_bingkuang zia_yi04" style="display:none;">
							<div class="mt30 cas_cha">
								<p class="fl">
									<em class="fl">体征：</em>
									<ul class="fl ca1_gerenshi">
										<li>
											<span class="fl">神经系统</span>
											<span class="fl">
												<select class="fl" id = "bodyState1Type" name = "bodyState1Type" style="width:78px;height:100%;line-height:100%;outline:none;border:none;">
													<option value="0">正常</option>
													<option value="1">异样</option>
												</select>
											</span>
											<span class="fl">
												<input type="text" id = "bodyState1" name = "bodyState1" class="fl" placeholder="最大200字" maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)"/>
											</span>
										</li>
										<li>
											<span class="fl">头部</span>
											<span class="fl">
												<select class="fl" id = "bodyState2Type" name = "bodyState2Type" style="width:78px;height:100%;line-height:100%;outline:none;border:none;">
													<option value="0">正常</option>
													<option value="1">异样</option>
												</select>
											</span>
											<span class="fl">
												<input type="text" class="fl" id = "bodyState2" name = "bodyState2" placeholder="最大200字" maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)" />
											</span>
										</li>
										<li>
											<span class="fl">颈部</span>
											<span class="fl">
												<select class="fl" id = "bodyState3Type" name = "bodyState3Type" style="width:78px;height:100%;line-height:100%;outline:none;border:none;">
													<option value="0">正常</option>
													<option value="1">异样</option>
												</select>
											</span>
											<span class="fl">
												<input type="text" class="fl" id = "bodyState3" name = "bodyState3" placeholder="最大200字" maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)" />
											</span>
										</li>
										<li>
											<span class="fl">胸部</span>
											<span class="fl">
												<select class="fl" id = "bodyState4Type" name = "bodyState4Type" style="width:78px;height:100%;line-height:100%;outline:none;border:none;">
													<option value="0">正常</option>
													<option value="1">异样</option>
												</select>
											</span>
											<span class="fl">
												<input type="text" class="fl" id = "bodyState4" name = "bodyState4" placeholder="最大200字" maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)" />
											</span>
										</li>
										<li>
											<span class="fl">腹部</span>
											<span class="fl">
												<select class="fl" id = "bodyState5Type" name = "bodyState5Type" style="width:78px;height:100%;line-height:100%;outline:none;border:none;">
													<option value="0">正常</option>
													<option value="1">异样</option>
												</select>
											</span>
											<span class="fl">
												<input type="text" class="fl" id = "bodyState5" name = "bodyState5" placeholder="最大200字" maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)" />
											</span>
										</li>
										<li>
											<span class="fl">四肢</span>
											<span class="fl">
												<select class="fl" id = "bodyState6Type" name = "bodyState6Type" style="width:78px;height:100%;line-height:100%;outline:none;border:none;">
													<option value="0">正常</option>
													<option value="1">异样</option>
												</select>
											</span>
											<span class="fl">
												<input type="text" class="fl" id = "bodyState6" name = "bodyState6" placeholder="最大200字" maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)" />
											</span>
										</li>
										<li>
											<span class="fl">皮肤</span>
											<span class="fl">
												<select class="fl" id = "bodyState7Type" name = "bodyState7Type" style="width:78px;height:100%;line-height:100%;outline:none;border:none;">
													<option value="0">正常</option>
													<option value="1">异样</option>
												</select>
											</span>
											<span class="fl">
												<input type="text" class="fl" id = "bodyState7" name = "bodyState7" placeholder="最大200字" maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)" />
											</span>
										</li>
										<li>
											<span class="fl">淋巴</span>
											<span class="fl">
												<select class="fl" id = "bodyState8Type" name = "bodyState8Type" style="width:78px;height:100%;line-height:100%;outline:none;border:none;">
													<option value="0">正常</option>
													<option value="1">异样</option>
												</select>
											</span>
											<span class="fl">
												<input type="text" class="fl" id = "bodyState8" name = "bodyState8" placeholder="最大200字" maxlength="200" onpropertychange="if(value.length>200) value=value.substr(0,200)" />
											</span>
										</li>
									</ul>

								
								<div class="clear"></div>
							</div>
							<div class="ca1_anniu " style="margin-left:300px;width:200px;">
								<a href="javascript:void(0);" class = "fl anniu shangibu4">上一步</a>
								<a href="javascript:void(0);" class = "fl anniu xiauibu4" style = "margin-left:20px;">下一步</a>
							</div>
						</div>
						<!-- 辅助检查 -->
						<div class="ca1_bingkuang zia_yi05"  style="display:none;">
							<div class="mt30 cas_cha">
								<p class="fl">
									<em class="fl">辅助检查：</em>
								</p>
									<ul class="fl ca1_gerenshi" style="border: none;">
									<div>
										<li style="height:100px;width: 80%;display: inline-block;border: 1px solid #dddddd;">
											<span class="fl" style="height:100px;">化验</span>
											<textarea rows="" cols="" class="fl" id ="assay" name="assay" style="width:84%;height:100%" placeholder="最大500字" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)"></textarea>
										</li>
										<p class = "fl ml10 anniu att_baocun" id="assayfiles_p" style="float:right;margin-right:5px;margin-top:3px;width:80px;height:23px;line-height:23px;">
												<input type="file" id="assayFile" name="assayFile" onchange="javascript:insertAssayFile(0, this, $('#assayFilePath'));" style = "z-index:103;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
												<input type="file" id="assayFile1" name="assayFile1" onchange="javascript:insertAssayFile(1, this, $('#assayFilePath'));" style = "z-index:102;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
												<input type="file" id="assayFile2" name="assayFile2" onchange="javascript:insertAssayFile(2, this, $('#assayFilePath'));" style = "z-index:101;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
												<input type="file" id="assayFile3" name="assayFile3" onchange="javascript:insertAssayFile(3, this, $('#assayFilePath'));" style = "z-index:100;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
												上传图片</p>
									</div>
									<div>
										<li style="height:100px;width: 80%;display: inline-block;border: 1px solid #dddddd;border-top:none;border-bottom:none;">
											<span class="fl" style="height:100px;">影像学</span>
											<textarea rows="" cols="" class="fl" id="image" name="image" style="width:84%;height:100%" placeholder="最大500字" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)"></textarea>
										</li>
										<p class="fl ml10 anniu att_baocun" id="imagefiles_p" style="float:right;margin-right:5px;margin-top:3px;width:80px;height:23px;line-height:23px;">
												<input type="file" id="imageFile" name="imageFile" onchange="javascript:insertImageFile(0, this, $('#imageFilePath'));" style = "z-index:103;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
												<input type="file" id="imageFile1" name="imageFile1" onchange="javascript:insertImageFile(1, this, $('#imageFilePath'));" style = "z-index:102;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
												<input type="file" id="imageFile2" name="imageFile2" onchange="javascript:insertImageFile(2, this, $('#imageFilePath'));" style = "z-index:101;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
												<input type="file" id="imageFile3" name="imageFile3" onchange="javascript:insertImageFile(3, this, $('#imageFilePath'));" style = "z-index:100;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
												上传图片</p>
									</div>
									<div>
										<li style="height:100px;width: 80%;display: inline-block;border: 1px solid #dddddd;">
											<span class="fl" style="height:100px;">其他</span>
											<textarea rows="" cols="" class="fl" id="rest" name="rest" style="width:84%;height:100%" placeholder="最大500字" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)"></textarea>
										</li>
										<p class="fl ml10 anniu att_baocun" id="restfiles_p"  style="float:right;margin-right:5px;margin-top:3px;width:80px;height:23px;line-height:23px;">
												<input type="file" id="restFile" name="restFile" onchange="javascript:insertRestFile(0, this, $('#restFilePath'));" style = "z-index:103;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
												<input type="file" id="restFile1" name="restFile1" onchange="javascript:insertRestFile(1, this, $('#restFilePath'));" style = "z-index:102;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
												<input type="file" id="restFile2" name="restFile2" onchange="javascript:insertRestFile(2, this, $('#restFilePath'));" style = "z-index:101;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
												<input type="file" id="restFile3" name="restFile3" onchange="javascript:insertRestFile(3, this, $('#restFilePath'));" style = "z-index:100;width:50px;position:absolute;opacity:0;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
												上传图片</p>
									</div>
										
									</ul>
								<div class="fl">
									<div id = "assayFilePath" style="width:300px;height:100px;padding-top:7px;margin-left:15px;"><i></i> <i></i> <i></i> <i></i></div>
									<div id = "imageFilePath" style="width:300px;height:100px;padding-top:3px;margin-left:15px;"><i></i> <i></i> <i></i> <i></i></div>
									<div id = "restFilePath" style="width:300px;height:100px;padding-top:0px;margin-left:15px;"><i></i> <i></i> <i></i> <i></i></div>
								</div>								
								<div class="clear"></div>								
							</div>
							<div class="ca1_anniu " style="margin-left:300px;width:200px;">
								<a href="javascript:void(0);" class = "fl anniu shangibu5">上一步</a>
								<a href="javascript:void(0);" class = "fl anniu xiauibu5" style = "margin-left:20px;">下一步</a>
							</div>
						</div>
						<!-- 诊断 -->
						<div class="ca1_bingkuang zia_yi06"  style="display:none;">
							<ul>
							<li>
								<!-- <div class="fl shitin_xinzeng01">
									<p class="fl"><span class="fr">诊断：</span></p>
									<input type="text" placeholder="最大500子" id = "diseaseDiagNo1" class="fl tki_bianji" style="width:400px;"/>
									<a href="javascript:void(0);" class="fl ml10 anniu att_baocun4">添加诊断</a>
								</div> -->
								<div class="mt30 cas_cha">
									<p class="fl"><span class="fr">诊断：</span></p>
									<textarea cols="" onKeyup="javascript:view_count6(this);" placeholder="最多200字" id = "diseaseDiagNo1" rows="" maxlength = "200" onpropertychange="if(value.length>200) value=value.substr(0,200)" class="fl tki_bianji" style="width:680px;height:50px;border:1px solid #dddddd;"></textarea>
									<a href="javascript:void(0);" class="fl ml10 anniu att_baocun4" style="margin-top:14px;">添加诊断</a>
								</div>	
								<div class="clear"></div>
								<div  class="shitin_xinzeng01">
									<p class="fl" style="margin-left:50px">还能输入：<span class="fr" id="spellNum6" style="font-size:12px;min-width:30px">200</span></p>
									<p class="fl" style="width:0px">字</p>
								</div>
								<div class="clear"></div>
							</li>
							</ul>
							<div class="ca1_anniu " style="margin-left:300px;width:200px;">
								<a href="javascript:void(0);" class = "fl anniu shangibu6">上一步</a>
								<a href="javascript:void(0);" class = "fl anniu xiauibu6" style = "margin-left:20px;">下一步</a>
							</div>
						</div>
						<!-- 治疗方案 -->
						<div class="ca1_bingkuang zia_yi07" style="display:none;">
							<div class="mt30 cas_cha">
								<p style="overflow:hidden;">
									<em class="fl">治疗方案：</em>
									<textarea onKeyup="javascript:view_count2(this);" placeholder="最大500字" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" name="plan" cols="" rows="" id = "plan" class="fl" style="width:750px;min-height:150px;overflow-y:visible;"></textarea>
								</p>							
								<a href="javascript:void(0);" class = "fl" style ="margin-left: 65px;margin-top: 20px;border-radius: 5px;-moz-border-radius: 5px;-webkit-border-radius: 5px;-khtml-border-radius: 5px;color: #333;border: 1px solid #4778c7;display: block;width: 80px;height: 26px;line-height: 26px;text-align: center;font-size: 12px;">
								<input type="file" class = "fl" name="planFile" id="plan_file" onchange="javascript:$('#plan_file_view').text($(this).val());" style = "width:50px;position:absolute;opacity:0;overflow:hidden;" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>上传图片</a>
								<div style = "margin-top: 26px;margin-left: 25px;float: left;" id = "plan_file_view"></div>
								
								<div class="clear"></div>
							</div>
							<div  class="shitin_xinzeng01">
								<p class="fl" style="margin-left:40px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">500</span></p>
								<p class="fl" style="width:0px">字</p>
							</div>
							<div class="ca1_anniu " style="margin-left:300px;width:200px;">
								<a href="javascript:void(0);" class = "fl anniu shangibu7">上一步</a>
								<a href="javascript:void(0);" class = "fl anniu xiauibu7" style = "margin-left:20px;">下一步</a>
							</div>
						</div>
						<!-- 预后 -->
						<div class="ca1_bingkuang zia_yi08" style="display:none;">
							<div class="mt30 cas_cha">
								<p class="fl">
									<em class="fl">预后：</em>
									<textarea onKeyup="javascript:view_count2(this);" placeholder="最大500字" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" id = "futureState" name="futureState" cols="" rows="" class="fl" style="width:800px;min-height:150px;overflow-y:visible;"></textarea>
								</p>
								<div class="clear"></div>
							</div>
							<div  class="shitin_xinzeng01">
								<p class="fl" style="margin-left:10px">还能输入：<span class="fr" id="spellNum" style="font-size:12px;min-width:30px">500</span></p>
								<p class="fl" style="width:0px">字</p>
							</div>
							<div class="ca1_anniu " style="margin-left:300px;width:200px;">
								<a href="javascript:void(0);" class = "fl anniu shangibu8">上一步</a>
								<a href="javascript:void(0);" class = "fl anniu xiauibu8" style = "margin-left:20px;">下一步</a>
							</div>
						</div>
						<!-- 讨论点 -->
						<div class="ca1_bingkuang zia_yi09"  style="display:none;">
						<ul>
							<li>
								<div class="mt30 cas_cha">
									<p class="fl">
										<em class="fl">讨论点：</em>										
										<textarea onKeyup="javascript:view_count3(this);" placeholder="最大500字" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" id="discussProp1" class="fl" style="width:360px;height:50px;border:1px solid #dddddd;"></textarea>
									</p>
									<p class="fl" style="margin-left:30px">
										<em class="fl cas_cha" style="font-size:13px">解析：</em>
										<textarea onKeyup="javascript:view_count4(this);" placeholder="最大500字" maxlength="500" onpropertychange="if(value.length>500) value=value.substr(0,500)" id="discussAnalysis1" class="fl" style="width:360px;height:50px;border:1px solid #dddddd;"></textarea>
										<input type="button" class="fl ca1_tianjia" value="添加讨论点" style="margin-top:14px;"/>
									</p>
									<div class="clear"></div>
									<div  class="shitin_xinzeng01">
										<p class="fl" style="margin-left:20px">还能输入：<i class="fr" id="spellNum3" style="font-size:12px;min-width:30px">500</i></p>
										<p class="fl" style="width:0px">字</p>
									</div>
									<div  class="shitin_xinzeng01" style="margin-left:440px">
										<p class="fl" style="margin-left:10px">还能输入：<i class="fr" id="spellNum4" style="font-size:12px;min-width:30px">500</i></p>
										<p class="fl" style="width:0px">字</p>
									</div>
									
								</div>
							</li>
						</ul>
							<div class="ca1_anniu xiauibu9" style="margin-left:400px;margin-top:100px;width:200px;">
								<a href="javascript:void(0);" class = "fl anniu shangibu9">上一步</a>
								<a href="javascript:void(0);" class = "fl anniu" style = "margin-left:20px;" id = "perfect">完成</a>
							</div>
						</div>
						<!-- 结束 -->
					</div>
				</div>
				</div>
				</div>
					<input type = "hidden" id = "patientId" name = "patientId" value = ""/>
					<input type = "hidden" id = "jsonDiagnosis" name = "jsonDiagnosis" value = ""/>
					<input type = "hidden" id = "jsonDiscuss" name = "jsonDiscuss" value = ""/>
				</form>
				<!-- end -->
			</div>
		
<div class="clear"></div>
</div>
<script type="text/javascript">
	var year = document.getElementById("diseaseYear");
	var month = document.getElementById("diseaseMonth");
	var day = document.getElementById("diseaseDay");
	year.onkeyup = function(){
		this.value=this.value.replace(/\D/g,'');
		if(year.value>100){
			year.value = 0;
		}
	}
	month.onkeyup = function(){
		this.value=this.value.replace(/\D/g,'');
		if(month.value>11){
			month.value = 0;
		}
	}
	day.onkeyup = function(){
		this.value=this.value.replace(/\D/g,'');
		if(day.value>30){
			day.value = 0;
		}
	}
</script>
<script type="text/javascript">
var diagIndex = 1;
var diseaseDiagIndex = 1;
var discussIndex = 1;



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
	// 一级选项切换
	$('.cas1_xuanxing>li').click(function(){
		$(this).addClass('action').siblings('.action').removeClass('action');
		var n=$(this).index();
		$('.cas1_certen .ca1_jichu').eq(n).show().siblings().hide();
	});
	// 二级选项切换
	$('.ca1_erji>li').click(function(){
		$(this).addClass('action').siblings('.action').removeClass('action');
		var n=$(this).index();
		$('.ca1_erxuanxing .ca1_bingkuang').eq(n).show().siblings().hide();
	});
	$('.xiauibu1').click(function(){
		$('.ca1_erji>li:eq(1)').addClass('action').siblings('.action').removeClass('action');
		$('.zia_yi02').show().siblings().hide();
	});
	$('.xiauibu2').click(function(){
		$('.ca1_erji>li:eq(2)').addClass('action').siblings('.action').removeClass('action');
		$('.zia_yi03').show().siblings().hide();
	});
	$('.xiauibu3').click(function(){
		$('.ca1_erji>li:eq(3)').addClass('action').siblings('.action').removeClass('action');
		$('.zia_yi04').show().siblings().hide();
	});
	$('.xiauibu4').click(function(){
		$('.ca1_erji>li:eq(4)').addClass('action').siblings('.action').removeClass('action');
		$('.zia_yi05').show().siblings().hide();
	});
	$('.xiauibu5').click(function(){
		////判断上传文件的格式和大小---begin---
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
		//判断上传文件的格式和大小---end---
		$('.ca1_erji>li:eq(5)').addClass('action').siblings('.action').removeClass('action');
		$('.zia_yi06').show().siblings().hide();
	});
	$('.xiauibu6').click(function(){
		$('.ca1_erji>li:eq(6)').addClass('action').siblings('.action').removeClass('action');
		$('.zia_yi07').show().siblings().hide();
	});
	$('.xiauibu7').click(function(){
		//判断上传文件的格式和大小
		if($("#plan_file").val() != "" && !fileUploadValid("plan_file",2)){
	        return ;
		}
		$('.ca1_erji>li:eq(7)').addClass('action').siblings('.action').removeClass('action');
		$('.zia_yi08').show().siblings().hide();
	});
	$('.xiauibu8').click(function(){
		$('.ca1_erji>li:eq(8)').addClass('action').siblings('.action').removeClass('action');
		$('.zia_yi09').show().siblings().hide();
	});
	$('.shangibu2').click(function(){
		$('.ca1_erji>li:eq(0)').addClass('action').siblings('.action').removeClass('action');
		$('.zia_yi01').show().siblings().hide();
	});
	$('.shangibu3').click(function(){
		$('.ca1_erji>li:eq(1)').addClass('action').siblings('.action').removeClass('action');
		$('.zia_yi02').show().siblings().hide();
	});
	$('.shangibu4').click(function(){
		$('.ca1_erji>li:eq(2)').addClass('action').siblings('.action').removeClass('action');
		$('.zia_yi03').show().siblings().hide();
	});
	$('.shangibu5').click(function(){
		$('.ca1_erji>li:eq(3)').addClass('action').siblings('.action').removeClass('action');
		$('.zia_yi04').show().siblings().hide();
	});
	$('.shangibu6').click(function(){
		$('.ca1_erji>li:eq(4)').addClass('action').siblings('.action').removeClass('action');
		$('.zia_yi05').show().siblings().hide();
	});
	$('.shangibu7').click(function(){
		$('.ca1_erji>li:eq(5)').addClass('action').siblings('.action').removeClass('action');
		$('.zia_yi06').show().siblings().hide();
	});
	$('.shangibu8').click(function(){
		$('.ca1_erji>li:eq(6)').addClass('action').siblings('.action').removeClass('action');
		$('.zia_yi07').show().siblings().hide();
	});
	$('.shangibu9').click(function(){
		$('.ca1_erji>li:eq(7)').addClass('action').siblings('.action').removeClass('action');
		$('.zia_yi08').show().siblings().hide();
	});
	//新学科弹出框
		$('.tik_select').click(function(){
			$(".tk_list").css("display","none");
			$(this).find('ul').show();
			return false;
		});
		$('.tk_list li').click(function(){
			var str=$(this).text();
			$(this).parent().parent().find('div').find('b').text(str);
			$(this).parent().find('option').prop('selected', '');
			$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
			$('.tk_list').slideUp(50);
		});
		
		$('.tk_list option:selected').each(function(){
			var str=$(this).text();
			$(this).parent().parent().parent().find('b').text(str);
		});
		
		$(document).click(function(){
			$('.tk_list').hide('fast');
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
		
		//check all
		$('.chkall').click(function(){
			if ($(this).attr('checked') == 'checked')
				$('.chk').attr('checked' , 'checked');
			else
				$('.chk').removeAttr('checked');
		});
		

		$('#propNames').click(function(){
			initPropList("学科","${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames'));
			$('.att_make01').show();
		});		
		$('.takuang_xk01').click(function(){
			initPropList("主题","${ctx}/propManage/getPropListAjax.do" ,7, 0, 1, 0, $('#zutiIds'), $('#zutiNames'));
			$('.att_make01').show();
		});
		
		//清空
		$('.tk_chaxun01').click(function(){
			$('#searchFrm').find('input').val('');
			$('#propNames').text('');
			$('#zutiNames').text('');
			$('.tik_select').find('b').text("请选择");
			$('#state').val('-1');
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
			$('#diag_zi_Number').text("");
			if($('#diagNo1').val() != "")
			{
				var curDiagId = '#diagNo'+diagIndex;
				diagIndex++;
				var newDiagId = 'diagNo' + diagIndex;
				var newDiag ='';
				newDiag += '\n';
				newDiag += '<li><div class="mt20 fl shitin_xinzeng01"><p class="fl diagnose" style="line-height:inherit" id = "';         
				newDiag += newDiagId + '"><span class="fr">诊断' + (diagIndex-1) + ' ：</span></p><div class="fl ml10 " style="width:680px;text-align:left;margin-left:0px;font-size:14px;color:#47474b;" name="diagNoValues"  id="v_'+newDiagId+'"  >' + $('#diagNo1').val() + '</div><a href="javascript:void(0);" class="fl ml10 anniu att_shanchu" style="margin-left:10px;" onClick="javascript:deleEm(this);">删除</a></P></div><div class="clear"></div></li>';
				$(newDiag).insertAfter($(curDiagId).parent().parent());
				
				$('#diagNo1').val("");
				view_count($('#diagNo1'));
			}
			else
			{
				alert("请输入诊断！");
			}
		});
		$('.att_baocun4').click(function(){
			if($('#diseaseDiagNo1').val() != "")
			{
				var curDiseaseDiagId = '#diseaseDiagNo' + diseaseDiagIndex;
				diseaseDiagIndex ++;
				var newDiagId = 'diseaseDiagNo' + diseaseDiagIndex;
				var newDiag ='<li><div class="mt20 fl shitin_xinzeng01" ><p class="fl diseasediag" style="line-height:inherit" ';  
				newDiag += '><span class="fr" >诊断' + (diseaseDiagIndex-1) + ' ：</span></p><div class="fl mt110" style="width:620px;text-align:left;margin-left:10px;font-size:14px;color:#47474b;" id="';
				newDiag += newDiagId + '">' + $('#diseaseDiagNo1').val() + '</div><a href="javascript:void(0);" class="fl ml10 anniu att_shanchu" style="margin-left:10px;" onClick="javascript:deleEmdisease(this);">删除</a></P></div><div class="clear"></div></li>';
				$(newDiag).insertAfter($(curDiseaseDiagId).parent().parent());
				$('#diseaseDiagNo1').val("");
				view_count2($('#diseaseDiagNo1'));
			}
			else
			{
				alert("请输入诊断！");
			}
		});
		$('.ca1_tianjia').click(function(){
			if($('#discussProp1').val() == "")
			{
				alert("请输入讨论点！");
				return;
			}
			if($('#discussAnalysis1').val() == "")
			{
				alert("请输入讨论解析！");
				return;
			}
			var curdiscussProp = '#discussProp' + discussIndex;
			discussIndex ++;
			var newPropId = 'discussProp' + discussIndex;
			var newAnalysisId = 'discussAnalysis' + discussIndex;
			var newDiag ='<li><div class="mt30 cas_cha"><div class="fl"><em class="fl discuss" id = "' + newPropId + '">讨论点'+ (discussIndex - 1) + '：</em><span style = "width:335px;" class="fl ml103" id = "v_' + newPropId +'">'+ $('#discussProp1').val() + '</span></div><div class="ml30 fl"><em class="fl discussanl" id = "' + newAnalysisId + '">解析'+ (discussIndex - 1) + '：</em>';
			newDiag += '<span class="fl ml104" style = "width:335px;" id="v_'+newAnalysisId+'" >' + $('#discussAnalysis1').val() + '</span></div><a href="javascript:void(0);" class="fl ml10 anniu att_shanchu" name="discussAnalysiss"    style="margin-left:10px;" onClick="javascript:deleEmdiscuss(this);">删除</a></P></div><div class="clear"></div></li>';
			$(newDiag).insertAfter($(curdiscussProp).parent().parent().parent());
			$('#discussProp1').val("");
			$('#discussAnalysis1').val("");
			view_count3($('#discussProp1'));
			view_count4($('#discussAnalysis1'));
		});
		$('#save').click(function(){
			var url = "${ctx}/caseManage/casePatientSave.do";
			if($('#pNationalState').text() == '是')
			{
				if($('#propIds').val() ==""){
					alert("请选择学科!");
					return;
				}
				if($('#zutiIds').val() ==""){
					alert("请选择主题!");
					return;
				}
				if($('#pCertificate').val() ==""){
					alert("请输入患者身份证号!");
					return;
				}
				/* else if($('#pNumberType1').text() == "请选择"){
					alert("请选择编号类型1!");
					return;
				}
				else if($('#pNumberType2').text() == "请选择"){
					alert("请选择编号类型2!");
					return;
				}*/
				else if($('#pNumberType1').text() == "请选择" && $('#pNumber1').val() !=""){
					alert("请选择编号类型1!");
					return;
				}
				else if($('#pNumberType2').text() == "请选择" && $('#pNumber2').val() !=""){
					alert("请选择编号类型2!");
					return;
				}
				else if($('#pNumberType1').text() != "请选择" && $('#pNumber1').val() ==""){
					alert("请输入编号类型1!");
					return;
				}
				else if($('#pNumberType2').text() != "请选择" && $('#pNumber2').val() ==""){
					alert("请输入编号类型2！");
					return;
				} 
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
				if($('#diagName').val() =="")
				{
					alert("请输入基本病情!");
					return;
				}

			}
			var params;
			params = "diagnosis_date=" + $('#diagnosis_date').val();
			params += "&pName=" + $('#pName').val();
			params += "&pSex=" + $("input[name='nax']:checked").val();
			params += "&pBirthDay=" + $('#pBirthDay').val();
			params += "&pAge=" + $('#pAge').val();
			params += "&pCertificate=" + $('#pCertificate').val();
			if($('#pNationalState').text() == '是')
			{
				params += "&pNationalState=" + 1;
			}
			else if($('#pNationalState').text() == '否')
			{
				params += "&pNationalState=" + 0;
			}
			else
			{
				params += "&pNationalState=" + -1;
			}
			switch($('#pNumberType1').text())
			{
				case '请选择':
					params += "&pNumberType1=" + 0;
					break;
				case '门诊号':
					params += "&pNumberType1=" + 1;
					break;
				case '住院号':
					params += "&pNumberType1=" + 2;
					break;
				case '床位号':
					params += "&pNumberType1=" + 3;
					break;
				case '其他编号':
					params += "&pNumberType1=" + 4;
					break;
			}
			params += "&pNumber1=" + $('#pNumber1').val();
			switch($('#pNumberType2').text())
			{
				case '请选择':
					params += "&pNumberType2=" + 0;
					break;
				case '门诊号':
					params += "&pNumberType2=" + 1;
					break;
				case '住院号':
					params += "&pNumberType2=" + 2;
					break;
				case '床位号':
					params += "&pNumberType2=" + 3;
					break;
				case '其他编号':
					params += "&pNumberType2=" + 4;
					break;
			}
			params += "&pNumber2=" + $('#pNumber2').val();
			params += "&pPhoneNumber1=" + $('#pPhoneNumber1').val();
			params += "&pPhoneNumber2=" + $('#pPhoneNumber2').val();
			var index = 0;
			for(i = 2 ; i <=diagIndex ; i++){
				var diagId = '#diagNo' +i;
				params += "&diagNo" +  index + "=" + $(diagId).text();
				index++;
			}
			params += "&diagCount=" + index;
			params += "&diagName=" + $('#diagName').val();
			params +="&course=" + $('#propIds').val();
			params +="&subject=" + $('#zutiIds').val();
			
			//chenlb add
			var diagNoValues = document.getElementsByName("diagNoValues");
			var diagParams = "";
			for(var i = 2 ; i <=diagNoValues.length +1; i++){
				var diseaseDiag = '#diseaseDiagNo' +i;
				var v_diseaseDiag = '#v_diagNo' +i;
				var textValue = $(v_diseaseDiag).text();
				diagParams = diagParams + textValue;
				if(i != diagNoValues+1)
				{
					diagParams += ",";
				}
			}
			
			params +="&diagParams="+diagParams;
			
			$.ajax({
					type: 'POST',
					url: url,
					data : params,
					dataType: 'text',
					success : function (newId){
						if(newId != null)
						{
							//alert('添加成功！');
							$('.cas1_xuanxing>li').addClass('action').siblings('.action').removeClass('action');
							$('#base_info2').addClass('action');
							var n = $('.cas1_xuanxing>li').index();
							$('.cas1_certen .ca1_jichu').eq(n).show().siblings().hide();
							$('#patientId').val(newId);
						}
						else
						{
							alert('添加失败');
						   	document.location.href = document.location.href.split("#")[0];
						}
						
					},
					error: function(){
					   	alert('添加失败');
						document.location.href = document.location.href.split("#")[0];
					}
				});
		});
		$('#perfect').click(function(){
			var url = "${ctx}/caseManage/caseSaveCase.do?type=1";
 			if($('#patientId').val() ==""){
				alert("请输入基础信息！");
				return;
			}
			if($('#pNationalState').text() == '是')
			{
				 if($('#diseaseType').val() ==""){
					$('#diseaseType').val("0");
				}
				else if($('#Complaint').val() ==""){
					alert("请输入主诉!");
					return;
				}
			} 
			var diseaseArray = "[";
			var index = 0;
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
			$('#jsonDiagnosis').val(diseaseArray);
			var discussArray = "[";
			index = 0;
			
			var discussAnalysiss = document.getElementsByName("discussAnalysiss");
			var _length = discussAnalysiss.length;
			for(i = 2 ; i <= _length+1 ; i++){
				var discussProp = '#v_discussProp' +i;
				var discussAnalysis = '#v_discussAnalysis' +i;
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
			document.getElementById("diseaseForm").action = url;
    		document.getElementById("diseaseForm").submit();
		});
});

$('#diagNo1').keyup(function(){
	var zi_length = $('#diagNo1').val();
	$('#diag_zi_Number').text(500-zi_length.length);
});



//display birthday and age
//身份证验证
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

$('#pPhoneNumber2').blur(function(){
  	var patrn = /^[0-9]{3,4}[-,－][0-9]{8,9}$/;
	if($(this).val() =="" || !patrn.test($(this).val()))
	{
		$(this).val('');
		alert("固定电话号码不正确！请按照如下格式填写：123-12345678或1234-12345678");
		document.getElementById('pPhoneNumber2').blur();  //2017.9.7 xh 在360最新版本号9.1.0.348内核版本：55.0.2883.87 下；Google Chrome最新版本62.0.3202.9下，为防止此方法中的alert不停地显示
		return false;
	}
});
  
$('#pNT2').click(function(){
	if($('#pNumberType1').text() == $('#pNumberType2').text()){
		if($('#pNumberType2').text()!='请选择'){
			alert("编号类型不能重复！");
			$('#pNumberType2').text('请选择');
			$('#pNumber2').val("");
		}
	}
	if($('#pNumberType2').text()=='请选择'){
		$('#pNumber2').val("");
	}
});
$('#pNT1').click(function(){
	if($('#pNumberType1').text() == $('#pNumberType2').text()){
		if($('#pNumberType1').text()!='请选择'){
			alert("编号类型不能重复！");
			$('#pNumberType1').text('请选择');
			$('#pNumber1').val("");
		}
	}
	if($('#pNumberType1').text()=='请选择'){
		$('#pNumber1').val("");
	}
});
function goBack() {
		window.history.back();
}

function view_count(obj) {
	
	var next_obj = $(obj).next();
	var str = $(obj).val();
	var left_count = 500-str.length;
	var spell = $(obj).parent().parent().find('#spellNum');
	$(spell).text(left_count);
}
function view_count1(obj) {
	
	var next_obj = $(obj).next();
	var str = $(obj).val();
	var left_count = 200-str.length;
	var spell = $(obj).parent().parent().find('#spellNum0');
	$(spell).text(left_count);
}
function view_count2(obj) {
	
	var next_obj = $(obj).next();
	var str = $(obj).val();
	var left_count = 500-str.length;
	var spell = $(obj).parent().parent().parent().find('#spellNum');
	$(spell).text(left_count);
}
function view_count6(obj) {
	
	var next_obj = $(obj).next();
	var str = $(obj).val();
	var left_count = 200-str.length;
	var spell = $(obj).parent().parent().parent().find('#spellNum6');
	$(spell).text(left_count);
}
function view_count3(obj) {
	
	var next_obj = $(obj).next();
	var str = $(obj).val();
	var left_count = 500-str.length;
	var spell = $(obj).parent().parent().parent().find('#spellNum3');
	$(spell).text(left_count);
}
function view_count4(obj) {
	
	var next_obj = $(obj).next();
	var str = $(obj).val();
	var left_count = 500-str.length;
	var spell = $(obj).parent().parent().parent().find('#spellNum4');
	$(spell).text(left_count);
}
function view_count5(obj){
	var next_obj = $(obj).next();
	var str = $(obj).val();
	var left_count = 500-str.length;
	var spell = $(obj).parent().find('#spellNum');
	$(spell).text(left_count);
}
function deleEm(obj) {
	var curId  = $(obj).prev().prev().prop("id").substring('diagNo'.length);
	$(obj).parent().parent().remove();
	diagIndex --;
	$('.diagnose').each(function(){
		var id =  $(this).prop("id").substring('diagNo'.length);
		if (id > curId) {
			id --;
			$(this).attr("id", 'diagNo' + id);
			$(this).children().text('诊断' + (id-1) + ' ：');
		}
		
	});
	
	
	//chenlb add 20170603
	$('.ml10 ').each(function(){
		var id =  $(this).prop("id").substring('v_diagNo'.length);
		if (id > curId) {
			id --;
			$(this).attr("id", 'v_diagNo' + id);
		}
		
	});
	
}
function deleEmdisease(obj) {
	var curId  = $(obj).prev().prev().prop("id").substring('diseaseDiagNo'.length);
	$(obj).parent().parent().remove();
	diseaseDiagIndex --;
	$('.diseasediag').each(function(){
		var id =  $(this).prop("id").substring('diseaseDiagNo'.length);
		if (id > curId) {
			id --;
			$(this).attr("id", 'diseaseDiagNo' + id);
			$(this).children().text('诊断' + (id-1) + ' ：');
		}
	});
	
}
function deleEmdiscuss(obj) {
	var curId  = $(obj).prev().children().prop("id").substring('discussAnalysis'.length);
	$(obj).parent().parent().remove();
	discussIndex --;
	$('.discussanl').each(function(){
		var id =  $(this).prop("id").substring('discussAnalysis'.length);
		if (id > curId) {
			id --;
			$(this).attr("id", 'discussAnalysis' + id);
			$(this).text('解析' + (id-1) + '：');
		}
	});
	$('.discuss').each(function(){
		var id =  $(this).prop("id").substring('discussProp'.length);
		if (id > curId) {
			id --;
			$(this).attr("id", 'discussProp' + id);
			$(this).text('讨论点' + (id-1) + '：');
		}
	});
	
	$('.ml104').each(function(){
		var id =  $(this).prop("id").substring('v_discussAnalysis'.length);
		if (id > curId) {
			id --;
			$(this).attr("id", 'v_discussAnalysis' + id);
		}
		
	});
	
	$('.ml103').each(function(){
		var id =  $(this).prop("id").substring('v_discussProp'.length);
		if (id > curId) {
			id --;
			$(this).attr("id", 'v_discussProp' + id);
		}
	});
	
}
function selectProp() {

		$(kuangcode).val($('.xs_kuangcode').text());
		var selPropHtml = $('.xs_kuang').html().replace(brexp,",");
		$('.xs_kuang').html(selPropHtml);
		$(kuang).text($('.xs_kuang').text());

		var url = "${ctx}/propManage/getPropListAjax.do";
		var ids = $(kuangcode).val();
		
		if ($(kuangcode).prop('id') == "propIds"){
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
				},
			});
		}
		} 
}

function insertAssayFile(index, fileObj, divObj)
{
	var filepath = $(fileObj).val();
	if (filepath){
		filenames = '<em class="delem" onclick="javascript:deleteUploadFile('+index+',this, $(\'#assayfiles_p\'));">'+filepath+'</em>';
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

function deleteUploadFile(index, emObj, pObj)
{
	$(emObj).remove();
	$(pObj).find('input[type=file]').eq(index).val('');
	$(pObj).find('input[type=file]').eq(index).show();
}
</script>
</body>
</html> 