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
<script type="text/javascript" src="${ctx}/new_page/js/jquery.js"></script>

</head>
	<body class = "bjs">
		<div class="center">
		<div class="tk_xuanxiag">
			<div class="xingzeng_k">
				<i></i><h2 class="fl">浏览病例</h2>
			</div>
			<div class="clear"></div>
			<div  style="width:1080px;margin:0 auto;">
				<div class="fr cas_anniu">
					<c:if test="${caseData.caseObject.state!=4 && caseData.caseObject.state!=3}">
						<a href="javascript:toUpdate(${caseData.caseObject.id});" class="fl" style="width:70px;margin-right:15px;">编辑</a>
						<a href="javascript:setCaseState(${caseData.caseObject.id});" class="fl" style="width:70px;">禁用</a>
					</c:if>
				</div>
			</div>
			<div class="clear"></div>
			<!-- 患者信息 -->
			<div class="thi_shitineirong" style="width:1080px;margin:0 auto;">
				<div class="cas3_jchu" style="border-top:none;border-bottom:2px solid #075387;padding-top:10px;">
					<em>患者信息</em>
				</div>
				<ul class="mt10">
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">学科：</span></p>
							<div class="fl chakan_kt" style="overflow:auto; height:auto;">${courseStr}</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">就诊时间：</span></p>
							<div class="fl chakan_kt">${caseData.patientObject.diagDate}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">姓名：</span></p>
							<div class="fl chakan_kt">${caseData.patientObject.PName}</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">性别：
								<c:if test="${caseData.patientObject.sex == 0}"><div class="fl chakan_kt">男</div></c:if>
								<c:if test="${caseData.patientObject.sex == 1}"><div class="fl chakan_kt">女</div></c:if></span>
							</p>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">年龄：</span></p>
							<div class="fl chakan_kt">${caseData.patientObject.pAge}岁</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">出生日期：</span></p>
							<div class="fl chakan_kt">${caseData.patientObject.birthday}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">门诊号：</span></p>
							<div class="fl chakan_kt">${caseData.patientObject.number1}</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">诊断：</span></p>
							<div class="fl chakan_kt" style="overflow:auto; height:auto;">${caseData.patientDiagnosisObject}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">创建时间：</span></p>
							<div class="fl chakan_kt">${caseData.caseObject.createDate}</div>
						</div>
						<div class="fl shitin_xinzeng">
							<p class="fl"><span class="fr">审核时间：</span></p>
							<div class="fl chakan_kt">${caseData.caseObject.onlineDate}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">基本病情：</span></p>
							<div class="fl chakan_kt" style="height:auto;width:860px">${caseData.patientObject.diagName}</div>
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
							<b class="ml5">
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
					<textarea placeholder="最大500字" name="" cols="" rows="" class="fl" disabled style="width:680px;height:50px;border:1px solid #dddddd;" >${caseData.caseDiseaseObject.complaint}</textarea>
				</div>
				<div class="clear"></div>
				<div class="fl mt20 shitin_xinzeng01">
					<p class="fl"><span class="fr">现病史：</span></p>
					<textarea placeholder="最大500字" name="" cols="" rows="" class="fl" disabled style="width:680px;height:50px;border:1px solid #dddddd;">${caseData.caseDiseaseObject.currentDisease}</textarea>
				</div>
				<div class="clear"></div>
				<div class="fl mt20 shitin_xinzeng01">
					<p class="fl"><span class="fr">个人史：</span></p>
					<ul class="fl ca1_gerenshi">
						<li style="height: 45px;">
							<span class="fl" style="text-align:center;height:45px;line-height:45px;">既往史</span>
							<textarea rows="" cols="" class="fl" placeholder="无传染病史" style="width:86%;height:100%">${caseData.caseDiseaseObject.history1}</textarea>
						</li>
						<li style="height: 45px;">
							<span class="fl" style="text-align:center;height:45px;line-height:45px;">个人史</span>
							<textarea rows="" cols="" class="fl" placeholder="无传染病史" style="width:86%;height:100%">${caseData.caseDiseaseObject.history2}</textarea>
						</li>
						<li style="height: 45px;">
							<span class="fl" style="text-align:center;height:45px;line-height:45px;">过敏史</span>
							<textarea rows="" cols="" class="fl" placeholder="无传染病史" style="width:86%;height:100%">${caseData.caseDiseaseObject.history3}</textarea>
						</li>
						<li style="height: 45px;">
							<span class="fl" style="text-align:center;height:45px;line-height:45px;">家族史</span>
							<textarea rows="" cols="" class="fl" placeholder="无传染病史" style="width:86%;height:100%">${caseData.caseDiseaseObject.history4}</textarea>
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
									<c:if test="${caseData.caseDiseaseObject.bodyState1Type == 0}">正常</c:if>
									<c:if test="${caseData.caseDiseaseObject.bodyState1Type == 1}">异样</c:if>
								</span>
								<input type="text" class="fl" value = "${caseData.caseDiseaseObject.bodyState1}"/>
							</li>
							<li>
								<span class="fl">头部</span>
								<span class="fl">
									<c:if test="${caseData.caseDiseaseObject.bodyState2Type == 0}">正常</c:if>
									<c:if test="${caseData.caseDiseaseObject.bodyState2Type == 1}">异样</c:if>
								</span>
								<input type="text" placeholder="头晕，发热" class="fl" value = "${caseData.caseDiseaseObject.bodyState2}"/>
							</li>
							<li>
								<span class="fl">颈部</span>
								<span class="fl">
									<c:if test="${caseData.caseDiseaseObject.bodyState3Type == 0}">正常</c:if>
									<c:if test="${caseData.caseDiseaseObject.bodyState3Type == 1}">异样</c:if>
								</span>
								<input type="text" class="fl" value = "${caseData.caseDiseaseObject.bodyState3}"/>
							</li>
							<li>
								<span class="fl">胸部</span>
								<span class="fl">
								<c:if test="${caseData.caseDiseaseObject.bodyState4Type == 0}">正常</c:if>
									<c:if test="${caseData.caseDiseaseObject.bodyState4Type == 1}">异样</c:if>
								</span>
								<input type="text" class="fl" value = "${caseData.caseDiseaseObject.bodyState4}"/>
							</li>
							<li>
								<span class="fl">腹部</span>
								<span class="fl">
									<c:if test="${caseData.caseDiseaseObject.bodyState5Type == 0}">正常</c:if>
									<c:if test="${caseData.caseDiseaseObject.bodyState5Type == 1}">异样</c:if>
								</span>
								<input type="text" class="fl" value = "${caseData.caseDiseaseObject.bodyState5}"/>
							</li>
							<li>
								<span class="fl">四肢</span>
								<span class="fl">
									<c:if test="${caseData.caseDiseaseObject.bodyState6Type == 0}">正常</c:if>
									<c:if test="${caseData.caseDiseaseObject.bodyState6Type == 1}">异样</c:if>
								</span>
								<input type="text" class="fl" value = "${caseData.caseDiseaseObject.bodyState6}"/>
							</li>
							<li>
								<span class="fl">皮肤</span>
								<span class="fl">
									<c:if test="${caseData.caseDiseaseObject.bodyState7Type == 0}">正常</c:if>
									<c:if test="${caseData.caseDiseaseObject.bodyState7Type == 1}">异样</c:if>
								</span>
								<input type="text" class="fl" value = "${caseData.caseDiseaseObject.bodyState7}"/>
							</li>
							<li>
								<span class="fl">淋巴</span>
								<span class="fl">
									<c:if test="${caseData.caseDiseaseObject.bodyState8Type == 0}">正常</c:if>
									<c:if test="${caseData.caseDiseaseObject.bodyState8Type == 1}">异样</c:if>
								</span>
								<input type="text" class="fl" value = "${caseData.caseDiseaseObject.bodyState8}"/>
							</li>
						</ul>
				</div>
				<div class="clear"></div>
				<div class="fl mt20 shitin_xinzeng01">
					<p class="fl"><span class="fr">辅助检查：</span></p>
					<ul class="fl ca1_gerenshi">
							<li style="height:115px;">
								<span class="fl" style="height:115px;">化验</span>
								<textarea rows="" cols="" class="fl" style="width:70%;height:100%">${caseData.caseDiseaseObject.assay}</textarea>
								<p class="fr" style="width:100px">
								<c:forEach items="${caseData.caseDiseaseObject.assayFileArray}" var="attFile">
									<a href="javascript:download('${attFile}');" class="fr" style="float:right;margin-right:5px;margin-top:3px;width:80px;height:23px;line-height:23px;background:#feaf27;border:1px solid #feaf27;">查看图片</a>
								</c:forEach>
								</p>
							</li>
							<li style="height:115px;">
								<span class="fl" style="height:115px;">影像学</span>
								<textarea rows="" cols="" class="fl" style="width:70%;height:100%">${caseData.caseDiseaseObject.image}</textarea>
								<p class="fr" style="width:100px">
								<c:forEach items="${caseData.caseDiseaseObject.imageFileArray}" var="attFile">
									<a href="javascript:download('${attFile}');" class="fr" style="float:right;margin-right:5px;margin-top:3px;width:80px;height:23px;line-height:23px;background:#feaf27;border:1px solid #feaf27;">查看图片</a>
								</c:forEach>
								</p>
							</li>
							<li style="height:115px;">
								<span class="fl" style="height:115px;">其他</span>
								<textarea rows="" cols="" class="fl" style="width:70%;height:100%">${caseData.caseDiseaseObject.rest}</textarea>
								<p class="fr" style="width:100px">
								<c:forEach items="${caseData.caseDiseaseObject.restFileArray}" var="attFile">
									<a href="javascript:download('${attFile}');" class="fr" style="float:right;margin-right:5px;margin-top:3px;width:80px;height:23px;line-height:23px;background:#feaf27;border:1px solid #feaf27;">查看图片</a>
								</c:forEach>
								</p>
							</li>
						</ul>
				</div>
				<div class="clear"></div>
				<div class="fl mt20 shitin_xinzeng01">
					<p class="fl"><span class="fr">诊断：</span></p>
					<textarea class="fl tki_bianji" style="width:680px;height:70px" >${caseData.diseaseDiagnosisObject}</textarea>
					
				</div>
				<div class="clear"></div>
				<div class="fl mt20 shitin_xinzeng01">
					<p class="fl"><span class="fr">治疗方案：</span></p>
					<textarea name="" readonly cols="" rows="" class="fl" style="width:570px;height:50px;overflow-y:visible;line-height:30px;">${caseData.caseDiseaseObject.plan}</textarea>
					<input type="button" class="ca1_tianjia" value="查看图片" onclick = 'javascript:download("${caseData.caseDiseaseObject.planFile}");' style="margin-top:10px;background:#feaf27;border:1px solid #feaf27;color:#fff;"/>
				</div>
				<div class="clear"></div>
				<div class="fl mt20 shitin_xinzeng01">
					<p class="fl"><span class="fr">预后：</span></p>
					<textarea readonly name="" cols="" rows="" class="fl" style="width:680px;height:50px;border:1px solid #dddddd;">${caseData.caseDiseaseObject.futureState}</textarea>
				</div>
				<div class="clear"></div>
				<%-- <c:forEach items = "${caseData.diseaseDiscuss}" var = "item">
					<div class="mt20 cas_cha">
						<div class="fl shitin_xinzeng01">
							<p class="fl"><span class="fr">讨论点：</span></p>
							<span>${item.prop}</span>
						</div>
						<div class="fl shitin_xinzeng01">
							<p class="fl ml30"><span class="fr">解析：</span></p>
							<span>${item.analysis}</span>
						</div>
						<div class="clear"></div>
					</div>
				</c:forEach> 
				<div class="mt20 cas_cha">
					<div class="fl shitin_xinzeng01">
						<p class="fl"><span class="fr">讨论点：</span></p>
						<!-- <input type="text" id = "discussProp2"  class="fl tki_bianji" style="width:258px;"/> -->
					</div>
					<div class="fl shitin_xinzeng01"  style="margin-left:270px">
						<p class="fl ml30"><span class="fr">解析：</span></p>
						<!-- <input type="text" value="" class="fl tki_bianji" id = "discussAnalysis2" style=""/> --> 											
					</div>
				</div>--%>
				
				<c:forEach items = "${caseData.diseaseDiscuss}" var = "item">			
					 <div class="mt30 shitin_xinzeng01">
						<div class="fl " >
							<p class="fl"><span class="fr">讨论点：</span></p>
							<div class="fl"><span class="fl" style="font-size:13px;color:rgb(71, 71, 75);line-height:30px;width:320px">${item.prop}</span></div>
						</div>
						<div class="fl ">
							<p class="fl"><span class="fr" >解析：</span></p>
							<div class="fl"><span class = "fl" style="font-size:13px;color:rgb(71, 71, 75);line-height:30px;width:320px">${item.analysis}</span></div>
						</div>
						<div class="clear"></div>
					</div> 
				</c:forEach>
				

				<div class="ca1_anniu xiauibu9" style="margin-left:400px;margin-top:30px;margin-bottom:50px;">
					<a href="javascript:goBack();">返回</a>
				</div>
			</div>	
		</div>	
	</div>
	<form method = "post" id = "diseasefrm" name = "diseasefrm"></form>
<input type = "hidden" id = "patientId" name = "patientId" value = ""/>
<input type = "hidden" id = "jsonDiagnosis" name = "jsonDiagnosis" value = ""/>
<input type = "hidden" id = "jsonDiscuss" name = "jsonDiscuss" value = ""/>
<div style="width:100%;height:15px;background:#eaf1f9;"></div>
<div class="clear"></div>
<!-- 新学科弹出框-一级 -->
<div class="toumingdu att_make01" style="display:none;">
	<div class="tk_center09" style="margin:7% auto;">
		<div class="tik_biaoti">
			<span class="fl" style="margin-left:290px;color:#fff;">学科</span>
			<i class="fr bjt_kt"></i>
		</div>
		<div class="clear"></div>
		<div class="xianshikuang">
			<div class="mt15 xs_xuangze">
				<div class="fl xs_kuang">
				</div>
				<div class="fr cas_anniu_4" style="margin-top:25px;">
					<a href="javascript:selectCourse();" class="fr">确定</a>
				</div>
				<div class="clear"></div>
			</div>
			<div class="xs_biaoti">
				<div class="xs_er" id="rootLevel">
					<p class="fl">一级学科</p>
					<i class="fl xs_bjt01"></i>
				</div>
				<div class="fl xs_er attri01" id="firstLevel" style="display:none;" onClick="backLevel(1);">
					<p class="fl" id="first"></p>
					<div style="display:none;" id="firstId"></div>
					<div style="display:none;" id="firstName"></div>
					<em class="fl">></em>
				</div>
				<div class="fl xs_er attri02" id="secondLevel" style="display:none;" onClick="backLevel(2);">
					<p class="fl" id="second"></p>
					<div style="display:none;" id="secondId"></div>
					<div style="display:none;" id="secondName"></div>
					<em class="fl">></em>
				</div>
				<div class="fl xs_er attri03" id="thirdLevel" style="display:none;" onClick="backLevel(3);">
					<p class="fl" id="third"></p>
					<div style="display:none;" id="thirdId"></div>
					<div style="display:none;" id="thirdName"></div>
					<i class="fl xs_bjt01"></i>
				</div>
			</div>
			<div class="clear"></div>
			<ul class="xs_ul">
		
			</ul>
			<div class="clear"></div>
		
		</div>
	</div>
</div>
<script type="text/javascript">
var diagIndex = 1;
var diseaseDiagIndex = 1;
var discussIndex = 1;
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
		$('.ca1_erji>li:eq(5)').addClass('action').siblings('.action').removeClass('action');
		$('.zia_yi06').show().siblings().hide();
	});
	$('.xiauibu6').click(function(){
		$('.ca1_erji>li:eq(6)').addClass('action').siblings('.action').removeClass('action');
		$('.zia_yi07').show().siblings().hide();
	});
	$('.xiauibu7').click(function(){
		$('.ca1_erji>li:eq(7)').addClass('action').siblings('.action').removeClass('action');
		$('.zia_yi08').show().siblings().hide();
	});
	$('.xiauibu8').click(function(){
		$('.ca1_erji>li:eq(8)').addClass('action').siblings('.action').removeClass('action');
		$('.zia_yi09').show().siblings().hide();
	});

	//新学科弹出框
		$('.takuang_xk').click(function(){
			$('.att_make01').show();
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
		//四级
		$('.attr_xueke03').click(function(){
			$('.att_make03').hide();
			$('.att_make04').show();
		});
		$('.attri03').click(function(){
			$('.att_make03').show();
			$('.att_make04').hide();
			$('.att_make05').hide();
		});
		//五级
		$('.attr_xueke04').click(function(){
			$('.att_make04').hide();
			$('.att_make05').show();
		});
		$('.attri04').click(function(){
			$('.att_make04').show();
			$('.att_make05').hide();
		});
		$('.cas_anniu_4,.bjt_kt').click(function(){
			$('.att_make01').hide();
			$('.att_make02').hide();
			$('.att_make03').hide();
			$('.att_make04').hide();
			$('.att_make05').hide();
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
				var curDiagId = '#diagNo'+diagIndex;
				diagIndex++;
				var newDiagId = 'diagNo' + diagIndex;
				var newDiag ='<li><div class="fl shitin_xinzeng01"><p class="fl"><span class="fr">诊断' + (diagIndex-1) + ' ：</span></p><span class="fl ml10" id = "';
				newDiag += newDiagId + '">' + $('#diagNo1').val() + '</div><div class="clear"></div></li>';
				$(newDiag).insertAfter($(curDiagId).parent().parent());
				
				$('#diagNo1').val("");
			}
			else
			{
				alert("请输入诊断");
			}
		});
		$('.att_baocun4').click(function(){
			if($('#diseaseDiagNo1').val() != "")
			{
				var curDiseaseDiagId = '#diseaseDiagNo' + diseaseDiagIndex;
				diseaseDiagIndex ++;
				var newDiagId = 'diseaseDiagNo' + diseaseDiagIndex;
				var newDiag ='<li><div class="fl shitin_xinzeng01"><p class="fl"><span class="fr">诊断' + (diseaseDiagIndex-1) + ' ：</span></p><span class="fl ml10" id = "';
				newDiag += newDiagId + '">' + $('#diseaseDiagNo1').val() + '</div><div class="clear"></div></li>';
				$(newDiag).insertAfter($(curDiseaseDiagId).parent().parent());
				$('#diseaseDiagNo1').val("");
			}
			else
			{
				alert("请输入诊断");
			}
		});
		$('.ca1_tianjia').click(function(){
			if($('#discussProp1').val() == "")
			{
				alert("Input discussProp");
				return;
			}
			if($('#discussAnalysis1').val() == "")
			{
				alert("Input discussAnalysis");
				return;
			}
			var curdiscussProp = '#discussProp' + discussIndex;
			discussIndex ++;
			var newPropId = 'discussProp' + discussIndex;
			var newAnalysisId = 'discussAnalysis' + discussIndex;
			var newDiag ='<li><div class="mt30 cas_cha"><p class="fl"><em class="fl">讨论点：</em><span style = "width:360px;" class="fl ml10" id = "' + newPropId +'">'+ $('#discussProp1').val() + '</span></p><p class="ml30 fl"><em class="fl">解析：</em>';
			newDiag += '<span class="fl ml10" style = "width:360px;" id = "' + newAnalysisId + '">' + $('#discussAnalysis1').val() + '</span></p></div><div class="clear"></div></li>';
			$(newDiag).insertAfter($(curdiscussProp).parent().parent().parent());
			$('#discussProp1').val("");
			$('#discussAnalysis1').val("");
		});
		$('#save').click(function(){
			var url = "${ctx}/caseManage/casePatientSave.do";
			if($('#pNationalState').text() == '是')
			{
				/*if($('.takuang_xk').val() ==""){
					alert("Input Course!");
					return;
				}
				if($('.tki_bianji takuang_xk').val() ==""){
					alert("Input Title!");
					return;
				}
				else */if($('#diagnosis_date').val() ==""){
					alert("Select the diagnosis Date!");
					return;
				}
				else if($('#pName').val() ==""){
					alert("Input Course!");
					return;
				}
				else if($('#pBirthDay').val() ==""){
					alert("Input Course!");
					return;
				}
				else if($('#pAge').val() ==""){
					alert("Input Age!");
					return;
				}
				else if($('#pNumber1').val() ==""){
					alert("Input Number1!");
					return;
				}
				else if($('#pNumber2').val() ==""){
					alert("Input Number2!");
					return;
				}
				var pCerti = $('#pCertificate').val();
				if(pCerti =="" || pCerti.length != 18)
				{
					alert("Input Certificate!");
					return;
				}
				if(diagIndex == 1)
				{
					alert("Add Diagnosis!");
				}
				if($('#pPhoneNumber1').val() =="" || $('#pPhoneNumber1').val().length != 11)
				{
					alert("Input PhoneNumber!");
					return;
				}
				if($('#pPhoneNumber2').val() =="" || $('#pPhoneNumber2').val().length != 12)
				{
					alert("Input PhoneNumber!");
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
			else
			{
				params += "&pNationalState=" + 0;
			}
			switch($('#pNumberType1').text())
			{
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
			$.ajax({
					type: 'POST',
					url: url,
					data : params,
					dataType: 'text',
					success : function (newId){
						if(newId != null)
						{
							alert('添加成功！');
							$('.cas1_xuanxing>li').addClass('action').siblings('.action').removeClass('action');
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
				alert(params);
		});
		$('#perfect').click(function(){
			var url = "${ctx}/caseManage/caseSaveCase.do";
			if($('#patientId').val() ==""){
				alert("Add Patient");
				return;
			}
			if($('#pNationalState').text() == '是')
			{
				 if($('#disease_date').val() ==""){
					alert("Select Disease Date!");
					return;
				}
				else if($('#diseaseType').val() ==""){
					$('#diseaseType').val("0");
				}
				else if($('#Complaint').val() ==""){
					alert("Input Complaint!");
					return;
				}
			}
			var params;
			params = "?patientId =" + $('#patientId').val();
			params = "&diseaseDate =" + $('#disease_date').val();
			params += "&diseaseType=" + $('#diseaseType').val();
			params += "&complaint=" + $('#Complaint').val();
			params += "&currentDisease=" + $('#currentDisease').val();
			params += "&history1=" + $('#history1').val();
			params += "&history2=" + $('#history2').val();
			params += "&history3=" + $('#history3').val();
			params += "&history4=" + $('#history4').val();
			params += "&bodyState1Type=" + ($('#bodyState1Type').val()?$('#bodyState1Type').val():0);
			params += "&bodyState1=" + $('#bodyState1').val();
			params += "&bodyState2Type=" + ($('#bodyState2Type').val()?$('#bodyState2Type').val():0);
			params += "&bodyState2=" + $('#bodyState2').val();	
			params += "&bodyState3Type=" + ($('#bodyState3Type').val()?$('#bodyState3Type').val():0);
			params += "&bodyState3=" + $('#bodyState3').val();	
			params += "&bodyState4Type=" + ($('#bodyState4Type').val()?$('#bodyState4Type').val():0);
			params += "&bodyState4=" + $('#bodyState4').val();	
			params += "&bodyState5Type=" + ($('#bodyState5Type').val()?$('#bodyState5Type').val():0);
			params += "&bodyState5=" + $('#bodyState5').val();	
			params += "&bodyState6Type=" + ($('#bodyState6Type').val()?$('#bodyState6Type').val():0);
			params += "&bodyState6=" + $('#bodyState6').val();	
			params += "&bodyState7Type=" + ($('#bodyState7Type').val()?$('#bodyState7Type').val():0);
			params += "&bodyState7=" + $('#bodyState7').val();	
			params += "&bodyState8Type=" + ($('#bodyState8Type').val()?$('#bodyState8Type').val():0);
			params += "&bodyState8=" + $('#bodyState8').val();
			params += "&assay=" + $('#assay').val();
			params += "&assay_file=" + $('#assay_file').val();
			params += "&image=" + $('#image').val();
			params += "&image_file=" + $('#image_file').val();
			params += "&rest=" + $('#rest').val();
			params += "&rest_file=" + $('#rest_file').val();
			params += "&plan=" + $('#plan').val();
			params += "&plan_file=" + $('#plan_file').val();
			params += "&futureState=" + $('#futureState').val();
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
			for(i = 2 ; i <= discussIndex ; i++){
				var discussProp = '#discussProp' +i;
				var discussAnalysis = '#discussAnalysis' +i;
				var jsonStr = JSON.stringify({discussProp : $(discussProp).text(),discussAnalysis:$(discussAnalysis).text()});
				discussArray += jsonStr;
				if(i != discussIndex)
				{
					discussArray += ",";
				}
	//			params += "&discussProp" +  index + "=" + $(discussProp).text();
	//			params += "&discussAnalysis" +  index + "=" + $(discussAnalysis).text();
				index++;
			}
			discussArray += "]";
			$('#jsonDiscuss').val(discussArray);
			document.getElementById("diseaseForm").action = url;
    		document.getElementById("diseaseForm").submit();
	/*		$.ajax({
					type: 'POST',
					url: url,
					data : params,
					dataType: 'text',
					success : function (b){
						alert('添加成功！');
						$('.cas1_xuanxing>li').addClass('action').siblings('.action').removeClass('action');
						var n = $('.cas1_xuanxing>li').index();
						$('.cas1_certen .ca1_jichu').eq(n).show().siblings().hide();
						
					},
					error: function(){
					   	alert('添加失败');
					   	document.location.href = document.location.href.split("#")[0];
					}
				});*/
		});
});
function popUpDlg(type, id, title) {

	var url = '${ctx}/questionManage/adSearchQuestion.do';
	var params = 'handle=jump';
	params += '&type=' + type;
	params += '&id=' + id;

	if (type == 1) {
		$('#rootLevel').show();
		$('#firstLevel').hide();
		$('#secondLevel').hide();
		$('#thirdLevel').hide();		
	} else if (type == 2) {
		$('#rootLevel').hide();
		
		$('#first').text(title);
		$('#firstId').text(id);
		$('#firstName').text(title);
		
		$('#firstLevel').show();
		$('#secondLevel').hide();
		$('#thirdLevel').hide();	
	} else if (type == 3) {
		$('#rootLevel').hide();
		$('#firstLevel').show();
		
		$('#second').text(title);
		$('#secondId').text(id);
		$('#secondName').text(title);
		
		$('#secondLevel').show();
		$('#thirdLevel').hide();	
	} else if (type == 4) {
		$('#rootLevel').hide();
		$('#firstLevel').show();
		$('#secondLevel').show();
		
		$('#third').text(title);
		$('#thirdId').text(id);
		$('#thirdName').text(title);
		
		$('#thirdLevel').show();	
	} else {
	}
	
	$.ajax({
		type: 'POST',
		url: url,
		data : params,
		dataType: 'json',
		success : function (course){
			var str = '';
			for (var i=0; i<course.length; i++) {
				str += '<li><div class="attr_xueke">';
				if (type == 4) str += '<input type="checkbox" class="fl" style="margin-top:5px;" name="unit" onclick="subView2();" value="' + course[i].name + '"/>' + '<div id="unit" style="display:none;">' + course[i].id + '</div>' ;
				str += '<p class="fl"><em class="fl"';
				if (type < 4) {
					str += 'onclick="popUpDlg(';
					str += (course[i].type+1) + ',' + course[i].prop_val_id + ',\'' + course[i].name + '\'';
					str += ');"';
				}
				str += '>';
				str += course[i].name;
				str += '</em>';
				if (type < 4)	str += '<i class="fl ml10 kti_bjt2"></i>';
				str += '</p></div><div class="clear"></div></li>';
			}
			$('.xs_ul').empty();
			$('.xs_ul').append(str);
		   	$('.att_make01').show();
		},
		error: function(){
			alert('error');
		}
	});	
}
function subView2(){
	var courseName = '';
	var courseIds = '';
	$("input[name='unit']").each(function () {
	    if ($(this).is(':checked')) {
			if (courseName == '') 	courseName += this.value;
			else					courseName += ', ' + this.value;
			
			if (courseIds == '')		courseIds += $('#unit').text();
			else					courseIds += ', ' + $('#unit').text();
			
			alert($('#unit').text());
	    }
 	});
 	$('.xs_kuang').text(courseName);
 	$('#course').val(courseIds);
}

function selectCourse() {
	$('#courseStr').text($('.xs_kuang').text());
}
function goBack() {
	history.go(-1);
	//	document.location.href = "${ctx}/caseManage/caseManage.do?type=" + ${mode};
}
function download(fileName) {
		if(fileName != null && fileName != "")
		{
			var url = "${ctx}/storage/upload/case/" + fileName;
			//document.location.href = url;
			var newWnd = window.open(url,"Download Window", "width=600, height=400, ");
		}
		else
		{
			alert("没有上传的文件！");
		}
		
}
function setCaseState(caseId){
	var url = "${ctx}/caseManage/setCaseState.do";
	if(caseId != null && caseId.val !=""){
		var params = "caseId="+caseId;  
		params+= "&state=4";  
		$.ajax({
			type: 'POST',
			url: url,
			data : params,
			dataType: 'text',
			success : function (newId){
				if(newId == "success")
				{
					alert('操作成功！');
				}
				else
				{
					alert('操作失败');
				}
				document.location.href = document.location.href.split("#")[0];
			},
			error: function(){
			   	alert('添加失败');
				document.location.href = document.location.href.split("#")[0];
			}
		});
	}
}
function toUpdate(caseId)
{
	document.location.href = "${ctx}/caseManage/caseEdit1.do?type=${type}&mode=edit&caseId="+caseId;
}
$('input').prop("readonly","readonly");
$('textarea').prop("readonly","readonly");

</script>
</body>
</html> 