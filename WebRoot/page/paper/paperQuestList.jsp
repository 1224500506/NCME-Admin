<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>试题列表</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<%@include file="/commons/taglibs.jsp"%>
		<style media="all" type="text/css">
			@import url("${ctx}/css/displaytag/site.css");
			@import url("${ctx}/css/displaytag/screen.css");
		</style>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css" />
		
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		
		<style type="text/css">
		#red {
			COLOR: #FF0000;
			FONT-SIZE: 12px;
		}
		
		table.dataintable {
			border-collapse: collapse;
			font-family: Arial, Helvetica, sans-serif;
			font-size: 12px;
		}
		
		table.dataintable td {
			background-color: #EFEFEF;
			border: 1px solid #AAAAAA;
			padding: 5px 15px 5px 5px;
			vertical-align: text-top;
		}
		
		table.dataintable th {
			background-color: #CCCCCC;
			border: 1px solid #888888;
			padding: 5px 15px 5px 5px;
			vertical-align: baseline;
		}
		
		.tdTitle {
			background-color: #CCCCCC;
			border: 1px solid #888888;
			padding: 5px 15px 5px 5px;
			vertical-align: baseline;
			text-align: center;
			font-weight: bold;
			color: black;
		}
		
		.stcx_form_dhs {
			width: 90px;
			border: solid 1px #CCC;
			float: left;
		}
		
		.tdLeft {
			padding: 2px 2px 2px 2px;
			vertical-align: middle;
			text-align: center;
			width: 50px;
		}
		
		.tdRight {
			padding: 2px 2px 2px 2px;
			text-align: left;
		}
		
		.tdRight li {
			margin: 0px 20px 0px 0px;
			list-style-type: none;
		}
		
		.tdRight ul {
			float: left;
		}
		
		#propTable {
			border-collapse: collapse;
			font-family: Arial, Helvetica, sans-serif;
			font-size: 12px;
		}
		
		.xsxjc_search_dh_a {
			width: 100px;
			height: 18px;
			border: solid 1px #7F9DB9;
			line-height: 18px;
		}
		
		.stcx_form_dhs_a {
			width: 60px;
			border: solid 1px #CCC;
			float: left;
		}
		</style>
	</head>
	<body>
		<div id="main2_main">
			<div id="main2_content">
				<div id="xxfb_main">
					<form action="${ctx }/exam/examQuesList.do" method="post">
						<input type="hidden" name="method" id="method" value="quesListHand">
						<input type="hidden" name="questType_2" id="questType_2" value="1">
						<input type="hidden" name="question_label_id" id="question_label_id" value="1">
						<div style="float: left; height: 30px;">
							&nbsp;&nbsp;&nbsp;&nbsp;内容：
							<input name="content" type="text" id="content"  />&nbsp;&nbsp;&nbsp;
							<input type="submit" class="btn_03s" value="搜 索"   />
						</div>
					</form>
					<div style="float: right; height: 30px;">
						每题分数：
						<input name="quesScor" type="text" id="quesScor" readonly />
						剩余题量：
						<input name="haveQues" type="text" id="haveQues" readonly />
						<input type="button" class="btn_03s" value="添加到试卷" onclick="moves();" />
						&nbsp;&nbsp;
						<input type="button" class="btn_03s" value="关 闭" onclick="closeWin();" />
					</div>

					<form name="listfm" method="post">
						<div style="border: 0px solid red; margin: 0px; padding: 0px; float: left; width: 100%;">
							<display:table id="qt" name="questionList" requestURI="${ctx}/exam/examQuesList.do?method=quesListHand" style="width:100%;" 
										   class="its" excludedParams="msg" decorator="com.hys.exam.displaytag.OverOutWrapper">
											
								<display:column title="<input type='checkbox' name='chkall' value='on' onclick='CheckAll(this.form);' style='margin-left:20px;'>全选" style="width:2%;text-align:center">
									<input type="checkbox" name="qid" value="${qt.id}" id="qcid" />
								</display:column>
								<display:column title="题型" style="width:120px;text-align:center">
									${exam:questTypeName(qt.question_label_id)}
								</display:column>
								<display:column property="link2" title="内容" style="white-space:normal;word-break:break-all;overflow:hidden;text-align: left;" />
								<display:column title="创建时间">
									${qt.create_date }
								</display:column>
								<display:column title="操作" style="text-align:center">
									<input type="button" class="btn_04s" value="查看" onclick="lookQuest('${qt.id}');" />
								</display:column>
							</display:table>
						</div>
					</form>
				</div>
			</div>
		</div>
		<script type="text/javascript">

			var winObj = window.opener;
			//每题分数
			var quesScor = winObj.document.getElementById("quesScor").value
			jQuery(function() {
				window.onblur = function() {
					var ele = document.activeElement;
					if (!ele.type) {
						window.focus();
					}
				}
				countQueNum(0);
				var qid = document.getElementsByName("qid");
				var seleQuesList = winObj.seleQuesList;
				checkSeled(qid,seleQuesList);
			});
			
			function closeWin() {
				window.close();
			}
			
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
					winObj.document.getElementById("hand_quesNum").value = haveQues;
				}
				if (0 != quesNum) {
					hand_quesSocr = hand_quesSocr - quesNum * quesScor;
					winObj.document.getElementById("hand_quesSocr").value = hand_quesSocr;
				}
			}
			
			function moves() {
				
				//试题ID
				var qid = document.getElementsByName("qid");
				
				var flag = "";
				var quesNum = 0;//选题数量
				if (qid != null) {
					
					for ( var i = 0; i < qid.length; i++) {
						if (qid[i].checked && qid[i].disabled == false) {
							quesNum = quesNum + 1;
							flag = checkQuesSor(quesNum);
							if (flag == "") {
								winObj.seleQuesList.push(qid[i].value + "+" + quesScor);
								qid[i].disabled="true";
								qid[i].style.display='none';
							} else {
								break;
							}
						}
					}
					if(quesNum ==0){
						alert("请选择试题！");
						return;
					}
			
					if (flag == "") {
						document.listfm.chkall.checked = false;
						countQueNum(quesNum);
						alert("试题添加成功！");
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
					hand_quesSocr = hand_quesSocr - quesNum * quesScor;
					if (hand_quesSocr < 0) {
						flag = "socOver";
					} else {
						flag = "quesOver";
					}
				} else {
					hand_quesSocr = hand_quesSocr - quesNum * quesScor;
					if (hand_quesSocr < 0) {
						flag = "socOver";
					}
				}
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
			
			function CheckAll(form) {
				for ( var i = 0; i < form.elements.length; i++) {
					var e = form.elements[i];
					if (e.name != 'chkall' && e.type == 'checkbox' && e.disabled == false){
						e.checked = form.chkall.checked;
					}
				}
			}
			
			function lookQuest(id){
				window.location.href = "${ctx}/course/studyExamQuesView.do?id="+id ;
			}
		</script>
	</body>
</html>