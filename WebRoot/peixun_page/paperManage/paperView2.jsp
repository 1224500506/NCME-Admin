<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
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
		<script type="text/javascript" src="${ctxAll}/peixun_page/js/jquery.js"></script>
		<script type="text/javascript" src="${ctxAll}/play/js/jquery-1.11.1.min.js"></script>
	</head>
<!-- 内容 -->
<div class="center" style="margin-top:100px;">
	<div class="ks_biaoti">
		<span class="ml20">${paper.name}</span>
	</div>
	<div class="kaos_shiti">
		<c:forEach items="${quesList}" var="que" varStatus="i">
		<div class="kshi">
			<dl>
				<dt>${i.index+1}. ${que.content}</dt>
				<c:forEach items="${que.questionKeyList}" var="key" varStatus="j">
					<dd>
						<c:choose>
							<c:when test="${que.question_label_id eq 1 || que.question_label_id eq 2 ||que.question_label_id eq 9}">
								<input type="radio" onclick="dxSunAnswer('${que.id}','${key.id}','${key.isnot_true}','${que.question_label_id}');" name="dan_${que.id}"/>
								<em><b>${result[j.index]}.</b>&nbsp;&nbsp;${key.content}</em>
							</c:when>
							<c:when test="${que.question_label_id eq 11}">
								<input type="checkbox" name="dan_${que.id}"/>
								<em><b>${result[j.index]}.</b>&nbsp;&nbsp;${key.content}</em>
							</c:when>
							<c:when test="${que.question_label_id eq 19 || que.question_label_id eq 13}">
								<span></span>
							</c:when>
							<c:when test="${que.question_label_id eq 16}">
								<span class="style4">题目：<c:out value="${key.content}" />
								</span>
							</c:when>
						</c:choose>
					</dd>
				</c:forEach>
			</dl>
		</div>
		</c:forEach>

	</div>
	<div class="cas_anniu" style="width:90px;margin:30px auto;">
		<a href="javascript:void(0);" onclick="subExam();" class="fl queren" style="width:70px;">提交</a>
	</div>
	<div style="height:70px;"></div>
</div>
<script type="text/javascript">

    var paperId = '${paperId}';
    
    var examId = '${examId}';
    
    //试卷试题总数
    var questionCount = '${questionCount}';
    
    //已经提交的试题数
    var logCount = 0;

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

	/*
	 * @author 张建国
	 * @time   2017-02-21
	 * @param  questionId(试题Id)/keyId(选项Id)
	 * 说       明：单项选择题提交答案
	 */
    function dxSunAnswer(questionId,keyId,keyIsTrue,questionLabel){
		
		var url = window.parent.peixunURL+"/exam/submitQuestion.do?method=subQuestionOne";
		
		var userId = window.parent.userId;
		
		var unitId = '${unitId}';
		
	
    	//通过AJAX提交试题答案
    	$.ajax({
   		    url:url,
   		    type: 'POST',
   		    dataType: 'json',
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
   		    	}else{
   		    		logCount = logCount + 1;
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
		//判断试卷是否有题
		if(questionCount==0 || questionCount==null){
			alert("暂无试题,无需提交.");
			return;
		}
		//判断是否答完所有试题
		if(questionCount>logCount){
			var result = questionCount - logCount;
			alert("还有"+result+"道题未答.");
			return;
		}else{
			alert("您已完成本次考试.");
		}
		
	}

</script>
</html>