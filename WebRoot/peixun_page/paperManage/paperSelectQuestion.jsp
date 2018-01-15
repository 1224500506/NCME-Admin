<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/commons/taglibs.jsp"%>
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
		<title>培训后台</title>
	</head>
<link rel="stylesheet" href="${ctx}/peixun_page/css/reset.css" />
<link rel="stylesheet" href="${ctx}/peixun_page/css/index.css" />
<script type="text/javascript" src="${ctx}/peixun_page/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/peixun_page/js/main.js"></script>

<body>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
		<form id="listfm" name="listfm" method="post" action="${ctx}/paperManage/paperSelectQuestion.do?method=quesListHand">
			<input type="hidden" name="question_label_id" value="${qform.question_label_id }"/>
			<input type="hidden" name="questTitle_9_2" value="${qform.questTitle_9_2}"/>
			<input type="hidden" name="questType_2" value="${qform.questType_2}"/>
			<input type="hidden" name="questCognize_8_2" value="${qform.questCognize_8_2}"/>
			<input type="hidden" name="grade" value="${qform.grade}"/>
			<input type="hidden" name="renderSelIds" value="${qform.renderSelIds}"/>
		<div class="mt5 kit1_tiaojia">
			<div class="fl">
				<p>
				<span>每题分数：</span><input type="text" name="quesScor" id="quesScor" readonly/>
				<span style="margin-left:80px">还能选择的试题量：</span><input type="text" name="haveQues" id="haveQues" readonly />
				&nbsp;&nbsp;&nbsp;&nbsp;
				<span>内容：</span><input type="text" name="content" value="${content }"/>
				</p>
			</div>
			<div class="fr cas_anniu">
				<a href="javascript:search();" class="fl" style="width:80px;margin-right:30px">搜索</a>
				<a href="javascript:moves();" class="fl" style="width:80px;margin-right:30px">添加试卷</a>
				<a href="javascript:window.close();" class="fl" style="width:80px;">关闭</a>
			</div>
		</div>
		<div class="clear"></div>
		
			<display:table id="qt" name="questionList" requestURI="${ctx}/paperManage/paperSelectQuestion.do"
							style="width:100%;" decorator="com.hys.exam.displaytag.OverOutWrapper" class="mt10 table" excludedParams="msg">
				<display:column title="<input type='checkbox' class='chkall' id='chkall' value=''>" style="width:5%;text-align:center">
					<input type="checkbox" class='chk' name="qid" value="${qt.id}" id="${qt.id}" onclick="record(this)"/>
				</display:column>
				<display:column title="题型" style="width:15%;text-align:center">
				<c:choose>
					<c:when test="${qt.question_label_id eq 1}">单选题(A1)</c:when>
					<c:when test="${qt.question_label_id eq 2}">单选题(A2)</c:when>
					<c:when test="${qt.question_label_id eq 3}">单选题(A3/A4)</c:when>
					<c:when test="${qt.question_label_id eq 9}">单选题(B1)</c:when>
					<c:when test="${qt.question_label_id eq 11}">多选题(X)</c:when>
					<c:when test="${qt.question_label_id eq 12}">填空题</c:when>
					<c:when test="${qt.question_label_id eq 13}">判断题</c:when>
					<c:when test="${qt.question_label_id eq 14}">简答题</c:when>
					<c:when test="${qt.question_label_id eq 18}">名词解释</c:when>
					<c:when test="${qt.question_label_id eq 20}">案例分析题</c:when>
				</c:choose>
				</display:column>
				<display:column property="link2" title="内容" style="width:70%;white-space:normal;word-break:break-all;overflow:hidden;text-align: left;">
				</display:column>
				<display:column title="操作" style="width:10%;text-align:center">
					<a href="javascript:viewQuestion(${qt.id});">查看</a>
				</display:column>
			</display:table>
			
		<div class="clear"></div> 
		</form>
	</div>
</div>

<script type="text/javascript">

var winObj = window.opener;
//每题分数
var quesScor = winObj.document.getElementById("quesScor").value;

$(function(){
	
		//select menu
		var CDCode = window.localStorage.getItem("CDCode");
		var code = CDCode.split("-");
		var menuindex = code[0];
		var submenuindex = code[1];
		var submenu = "lc_left0" + menuindex;
		var recordArrayLong = 0;
		
		$('.lc_center>ul').find('li').eq(menuindex-1).addClass("action");
		$('.'+submenu+'').show();
		$('.'+submenu+'>a').eq(submenuindex-1).addClass("action");
		
		
		$('.queren').click(function(){
			$('.make02').hide();		
		});
		$('.duouan').click(function(){
			$('.att_make01').show();
		});
		
			//check all
		$('.chkall').click(function(){
			if ($(this).attr('checked') == 'checked'){
				//全选
				$('.chk').attr('checked' , 'checked');
				$('input:checkbox[name=qid]:checked').each(function(i){
					if( $(this).css("display")!='none' ) {
						//循环一次总数量加一
						recordArrayLong++;
				       	//获取被选checkbox的父节点后边的第一个兄弟
						var selectItemNextTD = $(this).parent().next("td");
						//获取被选checkbox的父节点后边的第二个兄弟
						var selectItemNextTDNextTD = $(selectItemNextTD).next("td");
						//如果记录被选中，则记录;记录被选记录的id，类型，内容
						winObj.recordArray.push({id:this.value,type:$(selectItemNextTD).text(),content:$(selectItemNextTDNextTD).text()});
					}
			    });
			}else{
				$('input:checkbox[name=qid]:checked').each(function(i){
					if( $(this).css("display")!='none' ) {
						//全部取消
						$('.chk').removeAttr('checked');
					}
			    });
				//清空集合
				winObj.recordArray.splice(0, recordArrayLong);
			}
		});
		
		countQueNum(0);
		var qid = document.getElementsByName("qid");
		var seleQuesList = winObj.seleQuesList;
		checkSeled(qid,seleQuesList);
});

			//计算剩余题量和分数
			function countQueNum(quesNum) {
				
				//总题量
				var questionNum = winObj.document.getElementById("questionNum").value;
				//剩余分数
				var hand_quesSocr = winObj.document.getElementById("hand_quesSocr").value;
				//已选题量
				var seleQues = winObj.seleQuesList.length;
				//剩余题量
				var haveQues = questionNum - seleQues;
			
				jQuery("#haveQues").val(haveQues);
				jQuery("#quesScor").val(quesScor);
				if (haveQues < 0) {
					alert("超过剩余题量，请重新选择！");
				} else {
					winObj.document.getElementById("_hand_quesNum").value = seleQues;
					
					winObj.document.getElementById("hand_quesNum").value = haveQues;
				}
				if (0 != quesNum) {
					var total_score = winObj.document.getElementById("_hand_quesSocr").value;
					
					
					if(total_score.trim()==="" || typeof(total_score) == undefined){
						
						total_score = "0";
						
					}
					winObj.document.getElementById("_hand_quesSocr").value = parseInt(total_score) + parseInt(quesNum * quesScor);
				
					hand_quesSocr = hand_quesSocr - quesNum * quesScor;
					winObj.document.getElementById("hand_quesSocr").value = hand_quesSocr;
				}
			}
			
			function moves() {
				//试题ID
				var qid = document.getElementsByName("qid");

				var tableobj = winObj.document.getElementById("handmode_question_table");
				
				var flag = "";
				var quesNum = 0;//选题数量
				
				
				//添加试题
				//如果记录数组不为空则开始遍历处理
				//处理逻辑还是用以前的逻辑，不做修改
				if (winObj.recordArray) {
					$.each(winObj.recordArray,function(index,value){
						quesNum = quesNum + 1;
						flag = checkQuesSor(quesNum);
						if (flag == "") {
							winObj.seleQuesList.push(value.id + "+" + quesScor);
							
							var rowhtml = "<tr id='es"+ value.id +"'><td>"+value.type+"</td>";
							rowhtml+= "<td>"+value.content+"</td>";
							rowhtml+= "<td>"+quesScor+"</td>";
							rowhtml+= "<td><a href='javascript:viewQuestion("+value.id+");'>查看</a><a href=\"javascript:delQuestion("+value.id+","+quesScor+",this,0)\">删除</a></td></tr>";
							$(tableobj).append(rowhtml);
							var checkedItem = "input[value=" + value.id +"]";
					    	if( $(checkedItem) ) {
					    		$(checkedItem).attr("disabled",true);
					    		$(checkedItem).css("display","none");
					    	}
						} else {
							return false;
						}
						
						    
					});
				}
				
				if(quesNum ==0){
					alert("请选择试题！");
					return false;
				}
		
				if (flag == "") {
				//	document.listfm.chkall.checked = false;
					var divobj = winObj.document.getElementById("handmode_question");
					$(divobj).show();
					countQueNum(quesNum);
					alert("试题添加成功！");
					window.close();
				} else if (flag == "quesOver") {
					countQueNum(quesNum - 1);
					alert("试卷题量已满足！");
					window.close();
				} else if (flag == "socOver") {
					countQueNum(quesNum - 1);
					alert("试卷分数已满足！");
					window.close();
				}

			
			}
			
			//检查试卷剩余题量和分数
			function checkQuesSor(quesNum) {
				var flag = "";
				//总题量
				var questionNum = winObj.document.getElementById("questionNum").value;
				//剩余分数
				var hand_quesSocr = winObj.document.getElementById("hand_quesSocr").value;
				//已选题量
				var seleQues = winObj.seleQuesList.length;
				//剩余题量
				var haveQues = questionNum - seleQues;
			
				if (questionNum == winObj.seleQuesList.length) {
					//hand_quesSocr = hand_quesSocr - quesNum * quesScor;
					if (hand_quesSocr < 0) {
						//flag = "socOver";
					} else {
						flag = "quesOver";
					}
				}/*  else {
					hand_quesSocr = hand_quesSocr - quesNum * quesScor;
					if (hand_quesSocr < 0) {
						flag = "socOver";
					}
				} */
				return flag;
			
			}
			
			function checkSeled(qid,seleQuesList){
				if (qid != null) {
					if(seleQuesList.length>0){
						for ( var i = 0; i < qid.length; i++) {
							for(var j=0; j<seleQuesList.length;j++){
								var str = qid[i].value;
								var temp = seleQuesList[j].split("+");
								if( str == temp[0]){
									qid[i].disabled="true";
									qid[i].style.display='none';
								}
							}
						}
					}
				}
			}

function addShijuan(){
	if ($('.chk:checked').length<=0){
		alert ("没有选择项目。");
		return;
	}
	
	if(!confirm("您确定要添加吗？"))return;
//	var url = '${ctx}/propManage/delProp.do?mode=batchdel';

	var array = new Array();
	$('.chk:checked').each(function(key, val){
		array.push($(val).val());
	});
	var params = 'ids='+array.toString();
	
}
function viewQuestion(id){
	var url = "${ctx}/paperManage/viewQuestion.do?id="+id+"&handle=v";
	var bHeight = $(window).height()-100;
	var bWidth = $(window).width()-200;
	
	window.open(url, "viewQuestion", "top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width=" + bWidth + ",height=" + bHeight);
}
function search(){
	//重新点击搜索的时候，清空以前的记录
	winObj.recordArray = [];
	$("#listfm").submit();
}

//记录点击过的记录
function record(obj){
	//获取被选checkbox的父节点后边的第一个兄弟
	var selectItemNextTD = $(obj).parent().next("td");
	//获取被选checkbox的父节点后边的第二个兄弟
	var selectItemNextTDNextTD = $(selectItemNextTD).next("td");
	//如果记录被选中，则记录;记录被选记录的id，类型，内容
	if (obj.checked) {
		winObj.recordArray.push({id:obj.value,type:$(selectItemNextTD).text(),content:$(selectItemNextTDNextTD).text()});
	}else{
		var arrIndex = 0;
		//如果查找到就删除，删除的变量
		var flag = false;
		//如果是取消选中，则从记录查找，如果查找到就删除，理论上一定能找到
		$.each(winObj.recordArray,function(index,value){
		    $.each(value,function(index,value){
		    	if(value == obj.value){
		    		winObj.recordArray.splice(arrIndex, 1);
		    		flag = true;
		    	}
		    });
		    if (flag) {
		    	return false;
		    }
		    arrIndex ++;
		});
	}
	
}

//如果被选中过，翻页后回来还会被选中
function checked(){
	if (winObj.recordArray) {
		$.each(winObj.recordArray,function(index,value){
				var checkedItem = "input[value=" + value.id +"]";
		    	if( $(checkedItem) ) {
		    		$(checkedItem).attr("checked", true)
		    	}
			    
		});
	}
	
}
checked();

</script>
</body>
</html>