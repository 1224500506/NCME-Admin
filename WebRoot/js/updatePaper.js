var selectedMode = "-1";// 已选策略模式
var seleQuesList = new Array();// 所选试题ids

var paperIds = new Array();// 试卷id
var paperInfo = new Array();// 试卷信息
var paperInfo2 = new Array();// 试卷信息ids
var paperNamesId = [];
var paperNamesInfos = [];
var paperNamesId1 = [];
var propInfo;
var label;
var count;
var sumCount = 0;
var sumScore = 0;
// A1 A2 A3/A4 B1 X 填空  判断  简答  名词解释  案例分析   //类型总分数
var scoreTot = new Array(0,0,0,0,0,0,0,0,0,0);
//A1 A2 A3/A4 B1 X 填空  判断  简答  名词解释  案例分析   //题号
var showNum = new Array(0,0,0,0,0,0,0,0,0,0);

var seleDataArray = new Array();// 卷中卷设置信息


$(function() {



	//快捷策略
	$("#paperMode_1_edit_submit").click(function() {
		var questionLabelIds = "";
		$("input[name='labelcount']").each(function() {
			  var curId = $(this).prop("id");
		      var labelId = curId.substr(11, curId.length);
		      var qNum = $(this).val();
		      var qSoc = $("#labelscore_" + labelId).val();
		      var lableName = $("#labelName_" + labelId).text();
		      var limitNum = eval($("#mid_span_" + labelId).text());
		      if ("" != qNum) {
		        var patrn = /^[0-9]{1}[0-9]*$/;
		        if (testDemo(patrn, qNum)) {
		          qNum = eval(qNum);
		          if (qNum > limitNum) {
		            alert(lableName + "所设题量只能是正整数并且不能大于" + limitNum + "！");
		            flag = true;
		            $(this).val(0);
		            return false;
		          }
		        } else {
		          alert(lableName + "所设题量只能是正整数并且不能大于" + limitNum + "！");
		          flag = true;
		          $(this).val(0);
		          return false;
		        }
		      }
			var count = $(this).val();
		    var score = $("#labelscore_" + labelId).val();
		    var info = labelId + "_" + count + "_" + score; 
		    questionLabelIds += info + ",";
		});
		
		 if ($("#paperName").val().trim() == '') {
			   alert('请填写试卷名称！');
			   return;
		 }
		 if ($("#typeNames01").text() == '') {
		   alert('请选择试卷目录！');
		   return;
		 }
		 if ($("#curTypeId").val() == '') {
		   alert('请重新选择试卷目录！');
		   return;
		 }
	  if($("#1txtInd").text() == '') {
	    alert("请填写选择学科！");
	    return;
	  }
	  if ($("#total_quest").val() == 0) {
	    alert("请选择题型设置题量！");
	    return;
	  }
	  $("#questionNum").val($("#total_quest").val());
	  $("#paperScore").val($("#total_score").val());
	  $("#questionLabelIds").val(questionLabelIds);
	  if (confirm("确认保存试卷？")) {
		
	    var a = basePath + "/paperManage/paperUpdate.do?method=update";
	      var param = $("#fm1").serialize();
	      $.ajax({
	    	  url:a,
	    	  type:'post',
	    	  data:param,
	    	  dataType:'text',
	    	  success:function(result){
	    		if (result == "success") {
	    			alert(" 修改成功!");
	    			window.location.href = basePath + "/paperManage/paperList.do"; 
	    		}
	    		else if (result == "duplicate") {
	    			alert(" 试卷名称重复！");
	    		}
	    		else
	    		{
	    			alert(" 修改失败!");
	    		}
	    	  },
	      	});
		  }
	  });
	
	  // 未添加
	  // 精细策略提交
	  $("#mode_quick_edit_submit").click(function() {
		  var questionLabelIds = "";
			$("input[name='labelcount']").each(function() {
				var curId = $(this).prop("id");
				var labelId = curId.substr(11, curId.length);
				var count = $(this).val();
			    var score = $("#labelscore_" + labelId).val();
			    var info = labelId + "_" + count + "_" + score; 
			    questionLabelIds += info + ",";
			});
			
			 if ($("#paperName").val().trim() == '') {
				   alert('请填写试卷名称！');
				   return;
			 }
			 if ($("#typeNames01").text() == '') {
			   alert('请选择试卷目录！');
			   return;
			 }
			 if ($("#curTypeId").val() == '') {
			   alert('请重新选择试卷目录！');
			   return;
			 }
		    if($("#page2typeNames").text() == '') {
		      alert("请填写选择学科！");
		      $(".tr_loc").remove();		      
		      if($('#stu_td').length==0){
			      var html = new Array();
				  html.push("<tr align='center' valign='middle'><td colspan='7' id='stu_td'>--请选择学科--</td></tr>");
				  var lastTr = $("#stuNames").find("tr").last();				 
				  lastTr.after(html.join(""));
		      	}
		      return;
		    }
		    if ($("#total_quest").val() == 0) {
		      alert("请选择题型设置题量！");
		      return;
		    }
		    $("#questionNum").val($("#total_quest").val());
		    $("#paperScore").val($("#total_score").val());
		    $("#questionLabelIds").val(questionLabelIds);


	    var str = new Array();//
	    var patrn = /^[0-9]{1}[0-9]{0,2}$/;
	    var flag = false;
	    var flag2 = false;
	    var temp = 0;
	    $(".input_loc").each(function() {
	      var ids = this.id;
	      var val = $(this).val();
	      if (val == '') {
	        $(this).val("0");
	        val = 0;
	      }
	      if (testDemo(patrn, $(this).val())) {
	    	temp += eval(val);
	        if (eval(val) > 100) {
	          flag = true;
	          return;
	        } else {
	          $(".span_loc").each(function() {
	            var idstr2 = this.id;
	            var index2 = idstr2.indexOf('loc_');
	            var ids2 = idstr2.substr(index2 + 4, idstr2.length);
	            var val2 = $(this).text();
	            if (ids == ids2) {
	              if (eval(val) <= eval(val2)) {
	                str.push(ids + "+" + val);
	                temp = temp + eval(val);
	                return;
	              } else {
	                flag2 = true;
	                return;
	              }
	            }
	          });
	        }
	      } else {
	        flag = true;
	        return;
	      }
	    });

	    if (flag2) {
	      alert("内容比例不能超过最大数！");
	      return;
	    }

	    if (flag) {
	      alert("内容比例必须是整数，不能大于100！");
	      return;
	    } 
	   /* if (temp != 100) {
	        alert("内容比例之和必须等于100%！");
	        return;
	      } */
	    if ($("#labeltd2").text() != "") {
	    	alert("请选择题型！");
	    	return;
	    }
	    $("#seleQues").val(str.join("_"));
	    if (confirm("您确定要通过此策略生成试卷吗？如果还需修改，请按取消，不再修改请按确定！")) {
		      var str = new Array();
		      $(".input_loc").each(function() {
		        str.push(this.id + "+" + $(this).val());
		      });
	
		      $("#seleQues").val(str.join("_"));
		      
		      $("#propIds").val($("#page2propIds").val());
		      $("#levelIds").val($("#page2levelIds").val());
		      $("#laiIds").val($("#page2laiIds").val());
		      $("#grade").val($("#page2grade").val());
	
		      var a = basePath + "/paperManage/paperUpdate.do?method=update";
		      var param = $("#fm1").serialize();
		      $.ajax({
		    	  url:a,
		    	  type:'post',
		    	  data:param,
		    	  dataType:'text',
		    	  success:function(result){
		    		if (result == "success") {
		    			alert(" 修改成功!");
		    			window.location.href = basePath + "/paperManage/paperList.do"; 
		    		}
		    		else if (result == "duplicate") {
		    			alert(" 试卷名称重复！");
		    		}
		    		else
		    		{
		    			alert(" 修改失败!");
		    		}
		    	},
		      });
		     
	    } else 
	    {
	      return;
	    }
	  });
	  
  // 手工策略查询
  $("#sele-link-hand").click(function() {
    var quesScor = $("#quesScor").val();
    var qNum1 = $("#3questionNum").val();
    if (qNum1 == '') {
        $("#3questionNum").focus();
    	return false;;
    }
    var qNum2 = $("#_hand_quesNum").val();
    var num = parseInt(qNum1)+parseInt(qNum2); 
    $("#questionNum").val(num);
    
    if (quesScor == '') {
        $("#quesScor").val("1");
        var url = basePath +"/paperManage/paperSelectQuestion.do?method=quesListHand";
        var bHeight = $(window).height()-100;
        var bWidth = $(window).width()-200;
        window.open("about:blank", "subQuesList", "top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width=" + bWidth + ",height=" + bHeight);

        $("#seleQues").val(seleQuesList.join("_"));
          var f = document.fm1;
          f.action = url;
          f.target = "subQuesList";
          $("#fm1").submit();
    } else if (quesScor != '') {
      var patrn = /^[1-9]{1}[0-9]{0,2}$/;
      if (testDemo(patrn, quesScor)) {
        if (quesScor > 100) {
          alert("每题分数不能大于100分");
          $("#quesScor").focus();
        } else {
          var url = basePath +"/paperManage/paperSelectQuestion.do?method=quesListHand";
          var bHeight = $(window).height()-100;
          var bWidth = $(window).width()-200;
          window.open("about:blank", "subQuesList", "top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width=" + bWidth + ",height=" + bHeight);

          $("#seleQues").val(seleQuesList.join("_"));
            var f = document.fm1;
            f.action = url;
            f.target = "subQuesList";

            $("#fm1").submit();
        }
      } else {
        alert("每题分数至少1分，不能大于100分");
        $("#quesScor").focus();
      }
    }
  });
  
  //  卷中卷
  $("#submit-link-edit-mid").click(function() {
	  
	 if ($("#paperName").val().trim() == '') {
		   alert('请填写试卷名称！');
		   return;
	 }
	 if ($("#typeNames01").text() == '') {
	   alert('请选择试卷目录！');
	   return;
	 }
	 if ($("#curTypeId").val() == '') {
	   alert('请重新选择试卷目录！');
	   return;
	 }	
	if (paperIds.length == 0) {
	    alert("请选择试卷！");
	    return;
	}
	var totQues = $("#total_quest").val();
	var totSoc = $("#total_score").val();
	
	if (totQues == 0) {
    	alert("请选择试题！");
    	return;
    }
    
    if (totSoc == 0) {
    	alert("分数不能为0！");
    	return;
    }
    
	if(totQues > 300) {
		alert("所设总题量不能大于300道！");
		return;
	}
	$("#questionNum").val(totQues);
	$("#paperScore").val(totSoc);
	
	var questionLabelIds = "";
	$("input[name='labelcount']").each(function() {
		var curId = $(this).prop("id");
		var labelId = curId.substr(11, curId.length);
		var count = $(this).val();
		var score = $("#labelscore_" + labelId).val();
		var info = labelId + "_" + count + "_" + score; 
		questionLabelIds += info + ",";
	});
	
	$("#midinfo").val(questionLabelIds);

	if (confirm("确认保存试卷？")) {
		
		var a = basePath + "/paperManage/paperUpdate.do?method=update";
		var param = $("#fm1").serialize();
		
		$.ajax({
			url:a,
			type:'post',
			data:param,
			dataType:'text',
			success:function(result){
				if (result == "success") {
	    			alert(" 修改成功!");
	    			window.location.href = basePath + "/paperManage/paperList.do"; 
	    		}
	    		else if (result == "duplicate") {
	    			alert(" 试卷名称重复！");
	    		}
	    		else
	    		{
	    			alert(" 修改失败!");
	    		}
			},
		});
		
	} else {
	    return;
	}
  });
  
	 
  // 手工策略提交
  $("#submit-link-edit_hand").click(function() {
	 if ($("#paperName").val().trim() == '') {
		   alert('请填写试卷名称！');
		   return;
	 }
	 if ($("#typeNames01").text() == '') {
	   alert('请选择试卷目录！');
	   return;
	 }
	 if ($("#curTypeId").val() == '') {
	   alert('请重新选择试卷目录！');
	   return;
	 }
  	var queNu = $("#_hand_quesNum").val();
    var queSor = $("#hand_quesSocr").val();
    if(queNu > 300) {
      alert("试题数量不能大于300道，请重新选择！");
      return;
    }
    $("#typeNames").val($("#typeNames01").text());
    $("#questionNum").val(queNu);
    $("#paperScore").val(queSor);
   
    if ($("#handmode_question_table").find("tr").length == 1) {
    	alert("请选择试题！");
    	return;
    }
    
    if (confirm("确认保存试卷？")) {
    	$("#seleQues").val(seleQuesList.join("_"));
    	var a = basePath + "/paperManage/paperUpdate.do?method=update";
	      var param = $("#fm1").serialize();
	      $.ajax({
	    	  url:a,
	    	  type:'post',
	    	  data:param,
	    	  dataType:'text',
	    	  success:function(result){
	    		if (result == "success") {
		    		alert(" 修改成功!");
		    		window.location.href = basePath + "/paperManage/paperList.do"; 
		    	}
	    		else if (result == "duplicate") {
		    		alert(" 试卷名称重复！");
		    	}
		    	else
		    	{
		    		alert(" 修改失败!");
		    	}
	    	},
	      });
    } else {
      return;
    }
});

// 重置策略设置
function resetMode() {
  $("#pageModes input").each(function() {
    if($(this).attr("type") != "button" && $(this).attr("type") != "submit"
        && $(this).attr("id") != "paperScore" && $(this).attr("id") != "questionNum"
        && $(this).attr("id") != "childPaperNum" && $(this).attr("type") != "hidden") {
      $(this).val("");
    }
  });
  $("input[name='titleId']").each(function() {
	  $(this).attr("checked", false);
  });
  $("#pageModes textarea").each(function() {
    $(this).text("");
  });
  $("#pageModes .duouan").each(function() {
	    $(this).text("");
  });
  initQuestionLabelTable($("#questionLabelList"));
  initQuestionLabelTable2($("#questionLabelList2"));
  initStuNamesTable();
  $('#quesScor').val(1);
  $('#3questionNum').val(10);
  $("#added").html("");
  
  clearQuesContent();
  clearTypes();
  clearQuesNum();
  resetPaperMid();
  PopupSelector._selItems = {
    cat : [],
    loc : [],
    ind : [],
    ind2 : [],
    ind3 : [],
    ind4 : [],
    ind5 : [],
    ind6 : [],
    comptype : []
  };
}

// 关闭当前策略模式
function offMode() {
  $("#pageModes > div:visible").slideUp("slow", function() {
    $("#paperScore").removeAttr("readonly");
    //$("#paperScore").val("");
    $("#questionNum").removeAttr("readonly");
    //$("#questionNum").val("");
    $("#childPaperNum").removeAttr("readonly");
    //$("#childPaperNum").val("");
  });
}

// 检查选择策略前条件
function checkOne(mode) {
  if ("-1" != mode && selectedMode != "-1") {
    if (confirm("确认放弃此策略设置，重新选择？")) {
      //selectedMode = "-1";
      resetMode();
      offMode();
      return true;
    } else {
      return false;
    }
  }
  if ($("#paperName").val().trim() == '') {
    alert('填写试卷名称');
    return false;
  } else {
	  if($("#paperName").val().length>=30) {
		  alert("试卷名称长度在30字以内");
		  return false;
	  }
  }

  if ($("#typeNames01").text() == '') {
    alert('请选择试卷目录');
    return false;
  }

  if ($("#curTypeId").val() == '') {
    alert('请重新选择试卷目录');
    return false;
  }

  if (confirm("试卷分数、试题数量、试卷数量将会作为各策略的抽题规则之一，设置后不能修改，是否确定？")) {
    return true;
  } else {
    return false;
  }
}

// 初始化手工组卷
function initHand() {
 
  var childPaperNum = $("#childPaperNum").val();

  var patrn = /^[0-9]{1}[0-9]{0,2}$/;

  if (testDemo(patrn, childPaperNum)) {
    if (childPaperNum > 50) {
      alert("试卷数量不能大于50！");
      return false;
    } else {
      $("#hand_quesSocr").val(100);
      $("#hand_quesNum").val(0);
      /*$("#paperScore").attr({
        readonly : "readonly"
      });
      $("#questionNum").attr({
        readonly : "readonly"
      });*/
      $("#childPaperNum").attr({
        readonly : "readonly"
      });
      return true;
    }
  } else {
    alert("试卷数量至少大于1，小于等于50！");
    return false;
  }
  return true;
}

// 显示所选策略模式
function showMode(mode) {

  offMode();
  mode.slideDown("slow", function() {
  mode.find("div").css("display", "block");
  });
}

// 匹配字符串
function testDemo(re, s) {
  return re.test(s);
}
/**
 * @param quesList
 * @description set table information
 * */
});

var labelNames = {
		"1":"单选题(A1)",
		"2":"单选题(A2)",
		"3":"单选题(A3/A4)",
		"9":"单选题(B1)",
		"11":"多选题(X)",
		"12":"填空题",
		"13":"判断题",
		"14":"简答题",
		"18":"名词解释",
		"20":"案例分析题" };

function setQuesListTable(quesList) {
	var questionScore = 0;
	var html = "";
	$.each(quesList, function (i, item) {
		html = "<tr id='es"+item.id+"'><td>"+labelNames[item.question_label_id]+"</td>";
		html+= "<td>"+item.content+"</td>";
		
		html+= "<td>"+item.question_score+"</td>";
		html+= "<td><a href='javascript:viewQuestion("+item.id+");'>查看</a><a href=\"javascript:delQuestion("+item.id+","+item.question_score+",this,0)\">删除</a></td></tr>";
		html+= " ";
		seleQuesList.push(item.id+"+"+item.question_score);
		var lastTr = $("#handmode_question_table").find("tr:last");
		lastTr.after(html);
		questionScore += parseInt(item.question_score);
	});
	
	countQueNum(seleQuesList.length, questionScore);
}

function initShow(mode){
    seleQuesList = [];
    switch (mode) {
	    case '1':
	          $("#paperMode_1").show();
	          break;
	    case '2':
	          $("#paperMode_2").show();
	          break;
	    case '4':
	        $("#mode_mid").show();
	        $("#childPaperNum").val("1");
	        break;
	    case '3':
	          $("#mode_handmade").show();
	          $("#childPaperNum").val("1");
	          break;
	    case '5':
	        $("#mode_fine").show();
	        break;
    }
}

//Draw qustion-label table.
function showQuestionLabelTable(result)
{
	  $("input[name='labelcount']").each(function(key,val){
		  $(this).parent().parent().remove();
	  });
	  $("input[name='labelscore']").each(function(key,val){
		  $(this).parent().parent().remove();
	  });
	  $("#labeltd").remove();
	  $("#quest_table_last_tr").remove();
	  $.each(result.tactic1list, function (i, item)
	  {
		    label = item.label;
		  	count = item.labelQuestionNum;
		    
		    var html = new Array();
		    var lastTr = $("#questionLabelList").find("tr").last();
		    html.push("<tr align='center' class = 'labeltr' valign='middle' id='labelNames_tr_");
	        html.push(label.id);
	        html.push("'>");
	        html.push("<td width='30%'><span id = 'labelName_");
	        html.push(label.id);
	        html.push("'>");
	        html.push(label.name);
	        html.push("</span></td>");
	        html.push("<td width='30%'><span id = 'mid_span_");
	        html.push(label.id);
	        html.push("'>");
	        html.push(count);
	        html.push("</span></td>");
	        html.push("<td width='30%'>");
	        html.push("<input type = 'text' id = 'labelcount_");
	        html.push(label.id);
	        html.push("' name = 'labelcount' value = '" + item.num + "'/></td>");
	        html.push("<td width='10%'>");
	        html.push("<input type = 'text' id = 'labelscore_");
	        html.push(label.id);
	        html.push("' name = 'labelscore' value = '" + item.score + "'/></td>");
	        html.push("</td>");
	        html.push("</tr>");
	        lastTr.after(html.join(""));
	  });
	  var totQues = 0;
	  var totSoc = 0;
	  var flag = false;
	  $("input[name='labelcount']").change(function(){
		  var totQues = 0;
		  var totSoc = 0;
		  var flag = false;
		  $("input[name='labelcount']").each(function() {
		  var curId = $(this).prop("id");
	      var lableId = curId.substr(11, curId.length);
	      var qNum = $(this).val();
	      var qSoc = $("#labelscore_" + lableId).val();
	      var lableName = $("#labelName_" + lableId).text();
	      var limitNum = eval($("#mid_span_" + lableId).text());
	      if ("" != qNum) {
	        var patrn = /^[0-9]{1}[0-9]*$/;
	        if (testDemo(patrn, qNum)) {
	          qNum = eval(qNum);
	          if (qNum > limitNum) {
	            alert(lableName + "所设题量只能是正整数并且不能大于" + limitNum + "！");
	            flag = true;
	            $(this).val(0);
	            return false;
	          } else {
	        	qSoc = eval(qSoc);
	            if (testDemo(patrn, qSoc) && qSoc <= 100) {
	            	totQues += qNum;
	            	totSoc += qNum * qSoc;
	            } else {
	              alert(lableName + "每题分数最小为1并且不能大于100！");
	              $(this).val(0);
	              flag = true;
	              return false;
	            }
	          }
	        } else {
	          alert(lableName + "所设题量只能是正整数并且不能大于" + limitNum + "！");
	          flag = true;
	          $(this).val(0);
	          return false;
	        }
	      }
		  });
		  $("#total_quest").val(totQues);
		  $("#total_score").val(totSoc);
    });
	  $("input[name='labelscore']").change(function(){
		  var totQues = 0;
		  var totSoc = 0;
		  var flag = false;
		  $("input[name='labelscore']").each(function() {
		  var curId = $(this).prop("id");
	      var lableId = curId.substr(11, curId.length);
	      var qNum = $("#labelcount_" + lableId).val(); 
	      var qSoc = $(this).val(); 
	      var lableName = $("#labelName_" + lableId).text();
	      var limitNum = eval($("#mid_span_" + lableId).text());
	      if ("" != qSoc) {
	        var patrn = /^[0-9]{1}[0-9]*$/;
	        if (testDemo(patrn, qSoc)) {
	        	qSoc = eval(qSoc);
	          if (qSoc > 100) {
	        	alert(lableName + "每题分数最小为1并且不能大于100！");
	            $(this).val(0);
	            flag = true;
	            return false;
	          } else {
	        	qNum = eval(qNum);
	            if (testDemo(patrn, qNum) && qNum <= limitNum) {
	            	totQues += qNum;
	            	totSoc += qNum * qSoc;
	            } else {
	            alert(lableName + "所设题量只能是正整数并且不能大于" + limitNum + "！");
	              $(this).val(0);
	              flag = true;
	              return false;
	            }
	          }
	        } else {
	          alert(lableName + "所设题量只能是正整数并且不能大于" + limitNum + "！");
	          flag = true;
	          $(this).val(0);
	          return false;
	        }
	      }
		  });
		  $("#total_quest").val(totQues);
		  $("#total_score").val(totSoc);
    });
	  $("input[name='labelscore']").focusout(function(key,val){
		  var curId = $(this).prop("id");
	      var lableId = curId.substr(11, curId.length);
	      var qNum = $("#labelcount_" + lableId).val();
	      if (eval(qNum) == 0){
	    	  alert("选题量为零时分数不能大于零。请选择正确的选题量以及分数！");
	    	  $(this).val(0);
	    	  return false;
	      }
	  });
	  var html = new Array();
	  var lastTr = $("#questionLabelList").find("tr").last();
	  html.push("<tr align = 'center' class = 'labeltr' valign = 'middle' id = 'quest_table_last_tr'>");
	  html.push("<td width = '30%'>已选题量分数汇总:</td>");
	  html.push("<td width = '30%'>已选总题量:<input type = 'text' readonly id = 'total_quest' name = 'total_quest' value = '" + result.paper.question_num + "'/></td>");
	  html.push("<td width = '30%'>已选总分数:<input type = 'text' readonly id = 'total_score' name = 'total_score' value = '" + result.paper.paper_score + "'/></td>");
	  html.push("<td width = '10%'></td>");
	  html.push("</tr>");
	  lastTr.after(html.join(""));
}
function showQuestionLabelTable2(result)
{
	$("#stu_td").remove();
	
	/*
	 * 2017.01.24 Add By IE
	 *  To　Change QuestionLabelTable2
	 */
	
	$('.tr_loc').remove();
	
	$('#questionLabelList2 #label_td').remove();
	$('#stunames #label_td').remove();
	
	/*
	 *End. 
	 */
	
	var lastTr = $("#stuNames").find("tr").last();
	var html3 = [];
	$.each(result.tactic2list, function (i, item){
		html3.push("<tr class='tr_loc'>");
        html3.push("<td id='td1_loc");
        html3.push(item.unit_id);
        html3.push("'>");
        html3.push(item.unit_name);
        html3.push("</td>");
        html3.push("<td id='td2_loc");
        html3.push(item.unit_id);
        html3.push("'>");
        html3.push("<input onfocus='checkTotalbfb();' onblur='checkTotalbfb();' class='input_loc' size='10' type='text' id='");
        html3.push(item.unit_id);
        html3.push("' value = '");
        html3.push(item.percent);
        html3.push("'>");
        html3.push("%");
        html3.push("</td>");
        html3.push("<td id='td3_loc");
        html3.push(item.unit_id);
        html3.push("'>");
        html3.push("最多可分配");
        html3.push("<span class='span_loc' id='span_loc_");
        html3.push(item.unit_id);
        html3.push("'>");
        html3.push("</span> ");
        html3.push(" %");
        html3.push("</td>");
        html3.push("</tr>");
	 });
		html3.push("<tr class='tr_loc'>");
	    html3.push("<td>");
	    html3.push("汇总：");
	    html3.push("</td>");
	    html3.push("<td>");
	    html3.push("<input type='text' id='totalbfb' value='0' size='10' readonly>%");
	    html3.push("</td>");
	    html3.push("<td>");			    
	    html3.push("</td>");
	    html3.push("</tr>");
	 lastTr.after(html3.join(""));	
	 $('.input_loc').change(function(key, val){
			var patrn = /^[0-9]{1}[0-9]{0,2}$/;
			var percent = $(this).val();
			if (testDemo(patrn, percent)) {
				 if (percent > 100) {
				 alert("内容比例不能大于100！");
				 $(this).val(0);
				 return false;
			 }
			} else {
				alert("内容比例必须为数值！");
				$(this).val(0);
				return false;
			}
			var totPercent = 0;
			$('.input_loc').each(function(key, val){
				totPercent += eval($(this).val().trim());
			});
			$("#totalbfb").val(totPercent);
	 });		
	  $("input[name='labelcount']").each(function(key,val){
		  $(this).parent().parent().remove();
	  });
	  $("input[name='labelscore']").each(function(key,val){
		  $(this).parent().parent().remove();
	  });
	  $("#labeltd2").remove();
	  $("#quest_table_last_tr").remove();
	  $.each(result.tactic1list, function (i, item)
	  {
		    label = item.label;
		  	count = item.labelQuestionNum;		    
		    var html = new Array();
		    var lastTr = $("#questionLabelList2").find("tr").last();
		    html.push("<tr align='center' class='labeltr2' valign='middle' id='labelNames_tr_");
	        html.push(label.id);
	        html.push("'>");
	        html.push("<td width='30%'><span id = 'labelName_");
	        html.push(label.id);
	        html.push("'>");
	        html.push(label.name);
	        html.push("</span></td>");
	        html.push("<td width='30%'><span id = 'mid_span_");
	        html.push(label.id);
	        html.push("'>");
	        html.push(count);
	        html.push("</span></td>");
	        html.push("<td width='30%'>");
	        html.push("<input type = 'text' id = 'labelcount_");
	        html.push(label.id);
	        html.push("' name = 'labelcount' value = '" + item.num + "'/></td>");
	        html.push("<td width='10%'>");
	        html.push("<input type = 'text' id = 'labelscore_");
	        html.push(label.id);
	        html.push("' name = 'labelscore' value = '" + item.score + "'/></td>");
	        html.push("</td>");
	        html.push("</tr>");
	        lastTr.after(html.join(""));
	  });
	  $("input[name='labelscore']").focusout(function(key,val){
		  var curId = $(this).prop("id");
	      var lableId = curId.substr(11, curId.length);
	      var qNum = $("#labelcount_" + lableId).val();
	      if (eval(qNum) == 0){
	    	  alert("选题量为零时分数不能大于零。请选择正确的选题量以及分数！");
	    	  $(this).val(0);
	    	  return false;
	      }
	  });
	  var html = new Array();
	  var lastTr = $("#questionLabelList2").find("tr").last();
	  html.push("<tr align = 'center' class='labeltr2' valign = 'middle' id = 'quest_table_last_tr'>");
	  html.push("<td width = '30%'>已选题量分数汇总:</td>");
	  html.push("<td width = '30%'>已选总题量:<input type = 'text' readonly id = 'total_quest' name = 'total_quest' value = '" + result.paper.question_num + "'/></td>");
	  html.push("<td width = '30%'>已选总分数:<input type = 'text' readonly id = 'total_score' name = 'total_score' value = '" + result.paper.paper_score + "'/></td>");
	  html.push("<td width = '10%'></td>");
	  html.push("</tr>");
	  lastTr.after(html.join(""));
	  var totQues = 0;
	  var totSoc = 0;
	  var flag = false;
	  $("input[name='labelcount']").change(function(){
		  var totQues = 0;
		  var totSoc = 0;
		  var flag = false;
		  $("input[name='labelcount']").each(function() {
		  var curId = $(this).prop("id");
	      var lableId = curId.substr(11, curId.length);
	      var qNum = $(this).val();
	      var qSoc = $("#labelscore_" + lableId).val();
	      var lableName = $("#labelName_" + lableId).text();
	      var limitNum = eval($("#mid_span_" + lableId).text());
	      if ("" != qNum) {
	        var patrn = /^[0-9]{1}[0-9]*$/;
	        if (testDemo(patrn, qNum)) {
	          qNum = eval(qNum);
	          if (qNum > limitNum) {
	            alert(lableName + "所设题量只能是正整数并且不能大于" + limitNum + "！");
	            flag = true;
	            $(this).val(0);
	            return false;
	          } else {
	        	qSoc = eval(qSoc);
	            if (testDemo(patrn, qSoc) && qSoc <= 100) {
	            	totQues += qNum;
	            	totSoc += qNum * qSoc;
	            } else {
	              alert(lableName + "每题分数最小为1并且不能大于100！");
	              $(this).val(0);
	              flag = true;
	              return false;
	            }
	          }
	        } else {
	          alert(lableName + "所设题量只能是正整数并且不能大于" + limitNum + "！");
	          flag = true;
	          $(this).val(0);
	          return false;
	        }
	      }
		  });
		  $("#total_quest").val(totQues);
		  $("#total_score").val(totSoc);
    });
	  $("input[name='labelscore']").change(function(){
		  var totQues = 0;
		  var totSoc = 0;
		  var flag = false;
		  $("input[name='labelscore']").each(function() {
		  var curId = $(this).prop("id");
	      var lableId = curId.substr(11, curId.length);
	      var qNum = $("#labelcount_" + lableId).val(); 
	      var qSoc = $(this).val(); 
	      var lableName = $("#labelName_" + lableId).text();
	      var limitNum = eval($("#mid_span_" + lableId).text());
	      if ("" != qSoc) {
	        var patrn = /^[0-9]{1}[0-9]*$/;
	        if (testDemo(patrn, qSoc)) {
	        	qSoc = eval(qSoc);
	          if (qSoc > 100) {
	        	alert(lableName + "每题分数最小为1并且不能大于100！");
	            $(this).val(0);
	            flag = true;
	            return false;
	          } else {
	        	qNum = eval(qNum);
	            if (testDemo(patrn, qNum) && qNum <= limitNum) {
	            	totQues += qNum;
	            	totSoc += qNum * qSoc;
	            } else {
	            alert(lableName + "所设题量只能是正整数并且不能大于" + limitNum + "！");
	              $(this).val(0);
	              flag = true;
	              return false;
	            }
	          }
	        } else {
	          alert(lableName + "所设题量只能是正整数并且不能大于" + limitNum + "！");
	          flag = true;
	          $(this).val(0);
	          return false;
	        }
	      }
		  });
		  $("#total_quest").val(totQues);
		  $("#total_score").val(totSoc);
    });
	  var totQues = $('#total_quest').val();
	  $(".span_loc").each(function() {
          var idstr2 = $(this).prop("id");
          var index2 = idstr2.indexOf('loc_');
          var ids2 = idstr2.substr(index2 + 4, idstr2.length);
          if(totQues == 0) {
          	$(this).text(0);
          	return;
          }
          var limit = (result.propInfo[ids2])/totQues;
          
          if(limit >= 1)
          {
          	limit = 100;
          }
          else
          {
          	limit = limit*100;
          }
          $(this).text(limit);
	  });
}

function viewQuestion(id){
	var url = ctxJS+"/paperManage/viewQuestion.do?id="+id+"&handle=v";
	var bHeight = $(window).height()-100;
	var bWidth = $(window).width()-200;
	
	window.open(url, "viewQuestion", "top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width=" + bWidth + ",height=" + bHeight);
}


function delQuestion(qid,score,htmlStr,label) {
  if(confirm("确认删除试题？")) {
    var ele = qid + "+" + score;
    var index = -1;
    //删除数组中该题
    for(i=0;i<seleQuesList.length;i++) {
      if(seleQuesList[i] == ele) {
        index = i;
        break;
      }
    }
    if(index > -1) {
      seleQuesList.splice(index,1);
      $("#es"+qid).remove();
    }
    //重置总题数和分数
    var oldNum = $("#hand_quesNum").val();
    var oldScor = $("#hand_quesSocr").val();
    $("#hand_quesNum").val(parseInt(oldNum)+1);
    $("#hand_quesSocr").val(parseInt(oldScor)+score);
    
    $("#_hand_quesNum").val($("#_hand_quesNum").val()-1);
    $("#_hand_quesSocr").val($("#_hand_quesSocr").val()-score);
  }
}

function countQueNum(quesNum, questionScore) {
	var quesScor = $("#quesScor").val();
	//总题量
	var questionNum = quesNum;
	//剩余分数
	var hand_quesSocr = $("#hand_quesSocr").val();
	//已选题量
	var seleQues = seleQuesList.length;
	//剩余题量
	var haveQues = questionNum - seleQues;
	if (haveQues < 0) {
		alert("超过剩余题量，请重新选择！");
	} else {
		$("#_hand_quesNum").val(seleQues);
		$("#hand_quesNum").val(seleQues);
	}
	if (0 != quesNum) {
		$("#_hand_quesSocr").val(questionScore);
		$("#hand_quesSocr").val(parseInt($("#hand_quesSocr").val()) - questionScore);
	}
}