<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/new_page/top.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理平台</title>
</head>

<script type="text/javascript" src="${ctx}/js/question.js"></script>

<body class="bjs">
<div class="center">
<div class="tk_xuanxiag">
	<div class="xingzeng_k">
		<i></i><h2 class="fl">查看试题</h2>
	</div>
	<div class="thi_shitineirong">
		<input type="hidden" name="id" value="${id}">
		<ul>
			<li>
				<div class="fl shitin_xinzeng">
					<p class="fl"><span class="fr">试题类型：</span><em class="fr">*</em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${labelName}</div>
				</div>
				<div class="fl shitin_xinzeng">
					<p class="fl ml30"><span class="fr">试题难度：</span><em class="fr">*</em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">
						<c:if test="${quest.grade==1}">初级</c:if>
						<c:if test="${quest.grade==2}">中级</c:if>
						<c:if test="${quest.grade==3}">高级</c:if>					
					</div>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">学科：</span><em class="fr">*</em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${unit_names}</div>					
				</div>
				<div class="fl shitin_xinzeng01">
					<p class="fl ml30"><span class="fr">ICD10：</span><em class="fr"></em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${ICD10_names}</div>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">主题：</span><em class="fr">*</em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${subject_names}</div>
				</div>
				<div class="fl shitin_xinzeng01">
					<p class="fl ml30"><span class="fr">来源：</span><em class="fr"></em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${source_names}</div>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">其他来源：</span><em class="fr"></em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${quest.source}</div>
				</div>
				<div class="fl shitin_xinzeng01">
					<p class="fl ml30"><span class="fr">篇名：</span><em class="fr"></em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${quest.surname}</div>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="fl shitin_xinzeng01">
					<p class="fl"><span class="fr">职称：</span><em class="fr">*</em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${sector_names}</div>
				</div>
				<div class="fl shitin_xinzeng">
					<p class="fl ml30"><span class="fr">认知水平：</span><em class="fr"></em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${cognize_names}</div>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="fl shitin_xinzeng">
					<p class="fl"><span class="fr">是否上传国家库：</span><em class="fr">*</em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;"><c:if test="${quest.state==0}">否</c:if><c:if test="${quest.state!=0}">是</c:if>
					</div>
				</div>
				<div class="fl shitin_xinzeng">
					<p class="fl ml30"><span class="fr">是否多媒体试题：</span><em class="fr">*</em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${isMedia}</div>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="shitin_xinzeng01">
					<p class="fl"><span class="fr">试题内容：</span><em class="fr">*</em></p>
					<div class="fl exp_zhuanjiak" style="width:258px;">${quest.content}</div>
				</div>
				<div class="clear"></div>
			</li>
			<li>
				<div class="mt10 shitin_xinzeng01">
					<p class="fl"><span class="fr">试题解析：</span></p>
					<div class="fl exp_zhuanjiak">${quest.analyse}</div>
				</div>
				<div class="clear"></div>
			</li>
			
			<c:forEach items="${quest.childQuestionList}" var="child_qust">
				<ul class="naw">
					<li class="zishiti" style="margin-top:10px;">
						<div class="shitin_xinzeng01">
							<p class="fl"><span class="fr">子试题类型：</span><em class="fr">*</em></p>
							<div class="fl fl exp_zhuanjiak">单选题(B1)</div>
						</div>
						<div class="clear"></div>
					</li>
					<li class="zishiti">
						<div class="mt10 shitin_xinzeng01">
							<p class="fl"><span class="fr">子试题内容：</span><em class="fr">*</em></p>
							<div class="fl exp_zhuanjiak">${child_qust.content}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li class="zishiti">
						<div class="mt10 shitin_xinzeng01">
							<p class="fl"><span class="fr">子试题答案：</span><em class="fr">*</em></p>
							<div class="fl exp_zhuanjiak">${child_qust.questionKeyList[0].content}</div>
						</div>
						<div class="clear"></div>
					</li>
					<li class="zishiti">
						<div class="mt10 shitin_xinzeng01">
							<p class="fl"><span class="fr">子试题分析：</span></p>
							<div class="fl exp_zhuanjiak" >${child_qust.analyse}</div>
						</div>
						<div class="clear"></div>
					</li>
				</ul>			
			</c:forEach>
			
			<li>
				<div class="mt10 ca1_anniu" style="width:300px;">
					<c:if test="${type!=1}">
						<c:if test="${quest.state==1}">
					<a href="javascript:updateState(2);" class="fl" style="margin-right:15px;">合格</a>
					<a href="javascript:updateState(3);" class="fl" style="margin-right:15px;">不合格</a>
						</c:if>
					<a href="${ctx}/questionManage/questionManage.do?handle=main2" class="fl" style="">返回</a>
					</c:if>
					<c:if test="${type==1}">
					<a href="${ctx}/questionManage/questionManage.do" class="fl" style="">返回</a>
					</c:if>
				</div>
				<div class="clear"></div>
			</li>
		</ul>
	
	</div>
</div>
</div>

<script type="text/javascript">

	var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
	var type = "${type}";
	if (type == "1") submenuindex = 1;
	var menu = "tk_bjt0" + menuindex;
	var submenu = "mar_left0" + menuindex;
	$('.'+menu+'').parent().addClass("action");
	$('.'+submenu+'').show();
	$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");


function updateState(state) {
	
		
	var opinion;
	if (state == 3) {
		opinion = prompt("请添加审核意见!","");
		if (opinion == null && opinion == "") {
			return;
		}
	}
	if (opinion == null) opinion = "";
	
	if(state != 3 || opinion != ""){
	
		var url = '${ctx}/questionManage/updateQuestionState.do';
		var params = 'state=' + state;
		params += '&opinion=' + opinion;
		params += '&id=' + "${id}";
   		
		$.ajax({
			type: 'POST',
			url: url,
			data : params,
			dataType: 'text',
			success : function (b){
		      	alert('成功！');
		      	document.location.href = "${ctx}/questionManage/questionManage.do?handle=main2";
			},
			error: function(){
			   	  alert('失败.请检查属性的关联!');
			}
		});
	}

}

</script>		
</body>
</html>