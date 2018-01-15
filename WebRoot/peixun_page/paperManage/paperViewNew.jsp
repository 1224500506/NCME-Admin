<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/commons/taglibs.jsp"%>
		<script type="text/javascript" src="${ctxAll}/js/My97Date/WdatePicker.js"></script>
		<link rel="stylesheet" href="${ctxAll}/peixun_page/css/reset.css" />
		<link rel="stylesheet" href="${ctxAll}/peixun_page/css/index.css" />
		<link rel="stylesheet" href="${ctxAll}/peixun_page/css/pickadate/default.css" />
		<link rel="stylesheet" href="${ctxAll}/peixun_page/css/pickadate/default.date.css" />
		<link rel="stylesheet" href="${ctxAll}/peixun_page/css/fontawesome/css/font-awesome.css" />
		<link rel="stylesheet" href="${ctxAll}/peixun_page/css/ueditor.min.css" />
		
		<!-- 样式开始 -->
		<link rel="stylesheet" href="${ctxAll}/peixun_page/css/jquery.bxslider.css" />
		<link rel="stylesheet" href="${ctxAll}/peixun_page/css/animate.min.css"/>
		<link rel="stylesheet" href="${ctxAll}/peixun_page/css/global.css" />
		<link rel="stylesheet" href="${ctxAll}/peixun_page/css/rating.css" />
        <!-- 样式结束 -->
        
        <script type="text/javascript" src="${ctxAll}/peixun_page/js/jquery.js"></script>
		<script type="text/javascript" src="${ctxAll}/peixun_page/js/lib/layer/layer.js"></script>
		
		<link rel="stylesheet" href="${ctxAll}/peixun_page/js/lib/layer/skin/layer.css" />
        
        <%--         			
			<script type="text/javascript" src="${ctxAll}/js/jquery-1.11.1.min.js"></script>
		--%>
	</head>
<body>	
<!-- 开始 -->
    <div class="content no_padding" style="width:70%; margin: 80px auto;" >
        <h1 class="main_title_2" style="border-bottom: 2px solid #12bce1;line-height: 30px;">${paper.name}<span id="score" style="float: right;"></span></h1>
        
        <c:set value=""  var="keyAll"        scope="request" />
        
        <c:set value="0" var="radioQuestNum" scope="request" />
		<c:set value="0" var="checkQuestNum" scope="request" />
		<c:set value="0" var="pdQuestNum"    scope="request" />
		<c:set value="0" var="tkQuestNum"    scope="request" />
		<c:set value="0" var="jdQuestNum"    scope="request" />
		<c:set value="0" var="mcjsQuestNum"  scope="request" />
		<c:set value="0" var="nlfxQuestNum"  scope="request" />	
   <form id="frm1">
        <c:forEach items="${quesList}" var="que" varStatus="i">
        <ul class="comments_list">
            <li>
            	<c:set value="" var="questTypeStr"  scope="request" />
            	
	            <c:if test="${que.question_label_id eq 1 || que.question_label_id eq 2}">	            	
	            	<c:set var="questTypeStr" value="单选题" />
	            	<c:set var="radioQuestNum" value="${radioQuestNum + 1 }" />
	            </c:if>
            	<c:if test="${que.question_label_id eq 11}">            		
            		<c:set var="questTypeStr" value="多选题" />
            		<c:set var="checkQuestNum" value="${checkQuestNum + 1 }" />
            	</c:if>
            	<c:if test="${que.question_label_id eq 13}">            		
            		<c:set var="questTypeStr" value="判断题" />
					<c:set var="pdQuestNum" value="${pdQuestNum + 1 }" />
				</c:if>
				
				<c:if test="${que.question_label_id eq 12}">					
					<c:set var="questTypeStr" value="填空题" />
					<c:set var="tkQuestNum" value="${tkQuestNum + 1 }" />
				</c:if>
				<c:if test="${que.question_label_id eq 14}">					
					<c:set var="questTypeStr" value="简答题" />
					<c:set var="jdQuestNum" value="${jdQuestNum + 1 }" />
				</c:if>
				<c:if test="${que.question_label_id eq 18}">					
					<c:set var="questTypeStr" value="名词解释" />
					<c:set var="mcjsQuestNum" value="${mcjsQuestNum + 1 }" />
				</c:if>
				<c:if test="${que.question_label_id eq 20}">					
					<c:set var="questTypeStr" value="案例分析题" />
					<c:set var="nlfxQuestNum" value="${nlfxQuestNum + 1 }" />
				</c:if>
				
				<h3 class="font_bold">${questTypeStr}:${i.index+1}. ${que.content}</h3>
            	
            	<c:set var="keyAll" value="" />
                <c:forEach items="${que.questionKeyList}" var="key" varStatus="j">
               			<c:choose>
							<c:when test="${que.question_label_id eq 1 || que.question_label_id eq 2}">
								<p><input type="radio" class="radioQuestChkClass" onclick="radioQuestIsPass('${que.id}','${key.id}','${key.id}','${que.question_label_id}');" name="dan_${que.id}" value="${key.id}" <c:if test="${isPassExam}">disabled="disabled"</c:if> />${result[j.index]}.&nbsp;&nbsp;${key.content}</p>
							</c:when>
							<c:when test="${que.question_label_id eq 11}">
								<p><input type="checkbox" class="checkQuestChkClass" onclick="checkQuestIsPass('${que.id}','${key.id}',this,'${que.question_label_id}');" name="dan_${que.id}" value="${key.id}" <c:if test="${isPassExam}">disabled="disabled"</c:if> />${result[j.index]}.&nbsp;&nbsp;${key.content}</p>
							</c:when>
							<c:when test="${que.question_label_id eq 13}">
								<p><input type="radio" class="radioQuestChkClass" onclick="pdQuestIsPass('${que.id}','${key.id}','1','${que.question_label_id}');" name="dan_${key.id}" value="1" <c:if test="${isPassExam}">disabled="disabled"</c:if> />正确  &nbsp;&nbsp; <input type="radio" class="radioQuestChkClass" onclick="pdQuestIsPass('${que.id}','${key.id}','0','${que.question_label_id}');" name="dan_${key.id}" value="0" <c:if test="${isPassExam}">disabled="disabled"</c:if> />错误</p>
							</c:when>
							
							<c:when test="${que.question_label_id eq 12 || que.question_label_id eq 14 || que.question_label_id eq 18 || que.question_label_id eq 20}">
								<span><textarea maxlength="500" class="blankQuestChkClass" name="dan_${que.id}" cols="" rows="" class="fl tki_bianji01"  style="width:580px;" id="${que.id}_${key.id}_${que.question_label_id}" <c:if test="${isPassExam}">disabled="disabled"</c:if>></textarea></span><br><br>
							</c:when>
														
						</c:choose>
                </c:forEach>                
            </li>
        </ul>
        </c:forEach>
        
        <br><br>
        <div class="text_center" <c:if test="${isPassExam}">style="display:none;"</c:if>><button type="button" class="btn btn_blue btn_submit" onclick="subExam();">提交</button> </div>
  </form>      
    </div>
<!-- 结束 -->

<script type="text/javascript">
	//window.parent.QueryQuota('${unitId}');
    var paperId = '${paperId}';
    
    var examId = '${examId}';
    
    //试卷试题总数
    var questionCount = ${radioQuestNum + checkQuestNum + pdQuestNum + tkQuestNum + jdQuestNum + mcjsQuestNum + nlfxQuestNum} ; //questionCount
    
    //已经提交的试题数
    var logCount = 0;
    
    //是否已经通过考试
    var isPassExam = ${isPassExam} ;
    if (isPassExam) {
       alert("你已经通过考试不可再考！");
    }
    

	$(function(){
			//select menu
			var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
			var submenu = "lc_left0" + menuindex;
			$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
			$('.'+submenu+'').show();
			$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
			
			$('.select').click(function(){
				$('.list').css("display","none");
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
	});
	
	
	function radioQuestIsPass(questionId,keyId,keyIsTrue,questionLabel){
	   	dxSunAnswer(questionId,keyId,keyIsTrue,questionLabel,true);
	}
	
	function checkQuestIsPass(questionId,keyId,thisObj,questionLabel){
		var adIds = "";  
		$("input:checkbox[name=" + thisObj.name + "]").each(function(i){  
			if ($(this).attr('checked')) {
				if(adIds == ''){
					adIds = $(this).val();  
				}else{  
					adIds += "," + $(this).val() ;  
				}
			}			  
		});
		
		if (adIds != '') {
		   dxSunAnswer(questionId,keyId,adIds,questionLabel,true);
		}			   	
	}
	
	function pdQuestIsPass(questionId,keyId,keyIsTrue,questionLabel){
	   	dxSunAnswer(questionId,keyId,keyIsTrue,questionLabel,true);
	}

	/*
	 * @author 张建国
	 * @time   2017-02-21
	 * @param  questionId(试题Id)/keyId(选项Id)
	 * 说       明：单项选择题提交答案
	 */
    function dxSunAnswer(questionId,keyId,keyIsTrue,questionLabel,asyncFlag){    	
    	if ('${mode}' == 'view') {//预览
    	   return ;
    	}
    		
		var url = "${ctxAll}/exam/submitQuestion.do?method=subQuestionOne";		
		var userId = window.parent.userId;		
		var unitId = '${unitId}';
		
    	//通过AJAX提交试题答案
    	$.ajax({
   		    url:url,
   		    type: 'POST',
   		    dataType: 'json',
   		    async: asyncFlag,   		    
   		    data:{
   		    	examId:examId,
   		    	paperId:paperId,
   		    	questionId:questionId,
   		    	keyId:keyId,
   		    	keyIsTrue:keyIsTrue,
   		    	questionLabel:questionLabel,
   		    	userId:userId,
   		    	unitId:unitId   		    	
   		    },
   		    success: function(data){   
   		    	var result = eval(data);
   		    	if(result.message!='success'){
   		    		alert("试题提交失败.");
   		    	}
   		    },
   		    error: function(data){
   		    	if (data.readyState == 0) {
   		    		alert('答题出错了：服务器维护中！请在服务器维护结束后重新登录再答题！') ;
   		    	} else {
   		    	   	alert('答题出错了：err=' + data.readyState) ;
   		    	}   		       
   		    }
   		});
    }
	
	/*
	 * @author 张建国
	 * @time   2017-02-21
	 * 说       明: 提交试卷
	 */
	function subExam(){
		if ('${mode}' == 'view') {//预览
    	   return ;
    	}
    	
		 var unitId = '${unitId}';
		 var userId = window.parent.userId;
		 var url = "${ctxAll}/exam/submitQuestion.do?method=CountScore";
		//判断试卷是否有题
		if(questionCount==0 || questionCount==null){
			alert("暂无试题,无需提交.");
			return;
		}
		
		var rqLogNum = 0 ;
		$(".radioQuestChkClass").each(function(index,value){		   
		   if (value.checked) rqLogNum++ ;
		});
		
		var cqLogNum = 0 ;
		var cqLogNumName = "" ;
		$(".checkQuestChkClass").each(function(index,value){
		   if (value.checked && cqLogNumName != value.name) {
		      cqLogNumName = value.name ;
		      cqLogNum++;
		   }
		});
		
		var bqLogNum = 0 ;
		$(".blankQuestChkClass").each(function(index,value){		   
		   if ($.trim(value.value) != "") bqLogNum++ ;
		});
		
		logCount = rqLogNum + cqLogNum + bqLogNum ;
		
		//判断是否答完所有试题
		if(questionCount > logCount){
			var result = questionCount - logCount;
			alert("还有"+result+"道题未答.");
			return;
		}else{
			//输入的提交时算分
			$(".blankQuestChkClass").each(function(index,value){			
			   var allIdKeys = value.id.split("_");
			   dxSunAnswer(allIdKeys[0],allIdKeys[1],value.value,allIdKeys[2],false);
			});
			
			var win_w = "550px";
			var win_h = "180px";
	
	    	$.ajax({
	   		    url:url,
	   		    type: 'POST',
	   		    dataType: 'json',
	   		    data:{
	   		    	examId:examId,
	   		    	unitId:unitId,
	   		    	userId:userId
	   		    },
	   		    success: function(data){   
	   		    	var result = eval(data);
	   		    	if(result.message!='success'){
	   		    		$("#score").text("分数:"+result.score+"未通过请继续答题");
	   		    		$("#score").css("font-size","14px");
	   		    		$("#score").css("color","red");
	   		    		
	   		    		var alertContent = "<p>很遗憾，您未通过本次考试！</p><br><p>本次考试，您答对了" + result.questRightNum + "道题，答错了" 
	   		    		                 + result.questNotRightNum + "道题，得分：" + result.score + "分，未达到达标要求。</p>" ;
	   		    		
	   		    		//layer.alert(alertContent) ;
	   		    			   		    		
	   		    		layer.open({							
							title: "",
							area: [win_w, win_h],								
							content: alertContent							
						});
	   		    			   		    		
	   		    	}else{
	   		    		$("#score").text("分数:"+result.score+"通过");
	   		    		$("#score").css("font-size","14px");
	   		    		$("#score").css("color","red");	   		    		
	   		    		
	   		    		$("input").attr("disabled",true); 
	   		    		$("textarea").attr("disabled",true);
	   		    		$("button").attr("disabled",true);
	   		    		$("button").css("display","none");
	   		    			   		    		
	   		    		var alertContent = "<p>恭喜您，通过了本次考试！</p><br><p>本次考试，您答对了" + result.questRightNum + "道题，答错了" 
	   		    		                 + result.questNotRightNum + "道题，得分：" + result.score + "分，达到达标要求。</p>" ;
	   		    		
	   		    		layer.open({							
							title: "",
							area: [win_w, win_h],								
							content: alertContent,
							closeBtn: 0,
							btn: ['确定'],
							yes: function (index, layero) {	
								layer.close(index);
								//parent.location.reload();
								$('#pm' + unitId , window.parent.document).html('&#xe61c;');
     		    				$('#unit' + unitId , window.parent.document).css("color","#ADADAD");
     		    				$('#unit' + unitId , window.parent.document).click();
							},
							btn2: function (index, layero) {
								layer.close(index);
								//parent.location.reload();
								$('#pm' + unitId , window.parent.document).html('&#xe61c;');
     		    				$('#unit' + unitId , window.parent.document).css("color","#ADADAD");
     		    				$('#unit' + unitId , window.parent.document).click();
							}						
						});
	   		    	}
	   		    }
	   		
	   		});			
		}
		
	}
    
    
    
    /*
    更改框架页面样式
    
    **/ 
    window.onscroll = function () { 
	   	 var t = document.documentElement.scrollTop || document.body.scrollTop;
	   	 if (t > 0) { 
	   		 window.parent.disp();
	   	 } else { 
	   		 window.parent.disn();
	   	 } 
   	};
   

</script>

</body>
</html>