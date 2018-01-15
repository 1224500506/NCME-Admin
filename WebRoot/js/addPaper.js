var selectedMode = "-1";// 已选策略模式
var seleQuesList = new Array();// 所选试题ids
//保存被选中试题记录数组
var recordArray = new Array();
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

function foo(obj) {
	 var mode = ($(obj).val());
	    seleQuesList = [];
	    switch (mode) {
	    case '-1':
	      var tip = "确认放弃下面所设置的策略条件？";
	      if (confirm(tip)) {
	        selectedMode = "-1";
	        resetMode();
	        offMode();
	      } else {
	        //$(obj).val(selectedMode);
	    	  $('#paperModeSelect').text("请选择")
	        return;
	      }
	      break;
	    case '1':
	      if (checkOne(mode)) {
	        if (initHand()) {
	          selectedMode = mode;
	          showMode($("#paperMode_1"));
	          break;
	        } else {
	        	 $('#paperModeSelect').text("请选择")
	          break;
	        }
	      } else {
	    	  $('#paperModeSelect').text("请选择")
	        break;
	      }
	    case '2':
	      if (checkOne(mode)) {
	        if (initHand()) {
	          selectedMode = mode;
	          showMode($("#paperMode_2"));
	          break;
	        } else {
	        	 $('#paperModeSelect').text("请选择")
	          break;
	        }
	      } else {
	    	  $('#paperModeSelect').text("请选择")
	        break;
	      }
	    case '4':
	      if (checkOne(mode)) {
	        selectedMode = mode;
	        showMode($("#mode_mid"));
	        $("#childPaperNum").val("1");
	        break;
	      } else {
	    	  $('#paperModeSelect').text("请选择")
	        break;
	      }
	    case '3':
	      if (checkOne(mode)) {
	        if (initHand()) {
	          selectedMode = mode;
	          showMode($("#mode_handmade"));
	          $("#childPaperNum").val("1");
	          break;
	        } else {
	        	 $('#paperModeSelect').text("请选择")
	          break;
	        }
	      } else {
	    	  $('#paperModeSelect').text("请选择")
	        break;
	      }
	    case '5':
	      if (checkOne(mode)) {
	        selectedMode = mode;
	        showMode($("#mode_fine"));
	        break;
	      } else {
	    	  $('#paperModeSelect').text("请选择")
	        break;
	      }
	    }
}

$(function() {
  $("#paperMode").change(function() {
    var mode = ($(this).val());
    seleQuesList = [];
    switch (mode) {
    case '-1':
      var tip = "确认放弃下面所设置的策略条件？";
      if (confirm(tip)) {
        selectedMode = "-1";
        resetMode();
        offMode();
      } else {
        $(this).val(selectedMode);
        return;
      }
      break;
    case '1':
      if (checkOne(mode)) {
        if (initHand()) {
          selectedMode = mode;
          showMode($("#paperMode_1"));
          break;
        } else {
          $(this).val(selectedMode);
          break;
        }
      } else {
        $(this).val(selectedMode);
        break;
      }
    case '2':
      if (checkOne(mode)) {
        if (initHand()) {
          selectedMode = mode;
          showMode($("#paperMode_2"));
          break;
        } else {
          $(this).val(selectedMode);
          break;
        }
      } else {
        $(this).val(selectedMode);
        break;
      }
    case '4':
      if (checkOne(mode)) {
        selectedMode = mode;
        showMode($("#mode_mid"));
        $("#childPaperNum").val("1");
        break;
      } else {
        $(this).val(selectedMode);
        break;
      }
    case '3':
      if (checkOne(mode)) {
        if (initHand()) {
          selectedMode = mode;
          showMode($("#mode_handmade"));
          $("#childPaperNum").val("1");
          break;
        } else {
          $(this).val(selectedMode);
          break;
        }
      } else {
        $(this).val(selectedMode);
        break;
      }
    case '5':
      if (checkOne(mode)) {
        selectedMode = mode;
        showMode($("#mode_fine"));
        break;
      } else {
        $(this).val(selectedMode);
        break;
      }
    }
  });

  $("#qLabelId").change(function(){
    var qlabel = $(this).val();
    $("#questionLabelId").val(qlabel);
  });

  // 精细策略 添加策略
  $("#mode_fine_add").click(
      function() {
        var tIndex = $("#mode_fine_cont_t").find("tr").size();
        if (tIndex == 1) {
          $("#mode_fine_cont_th").after(
              "<tr id='mode-fine-tr-" + tIndex + "'></tr>");
        } else {
          var temp = tIndex - 1;
          $("#mode-fine-tr-" + temp).after(
              "<tr id='mode-fine-tr-" + tIndex + "'></tr>");
        }
        var html = [];
        html.push("<td>" + tIndex + "</td>");
        html.push("<td>tingxing</td>");
        html.push("<td>2</td>");
        html.push("<td>33</td>");
        html.push("<td>44</td>");
        html.push("<td>55</td>");
        html.push("<td>66</td>");
        html.push("<td>77</td>");
        html.push("<td><a href='#' class='mode_fine_del' id='mode-fine-a-"
            + tIndex + "'>删除</a></td>");

        $("#mode-fine-tr-" + tIndex).html(html.join(""));

        $("#mode-fine-a-" + tIndex).bind("click", function() {
          var str = "确认删除此条策略？";
          if (confirm(str)) {
            $("#mode-fine-tr-" + tIndex).remove();
            // 重新排序
            $("#mode_fine_cont_t").find("tr:gt(0)").each(function(i, obj) {
              var tt = i + 1;
              obj.cells[0].innerHTML = tt;
              $(obj).attr("id", "mode-fine-tr-" + tt);
              $(obj).find("td").find("a").attr("id", "mode-fine-a-" + tt);
            });
          } else {
            return;
          }
        });
      });
  $('#addExamType-link').click(function(){
	if($('#propIds').val() == '')
	{
		 alert("请填写选择学科!");
		 $('.labeltr').remove();
		 var dd= $('#labeltd');
		 if($('#labeltd').length==0) {
			 var html = new Array();
			 html.push("<tr align='center' valign='middle'><td colspan='4' id='labeltd'>--请选择题型--</td></tr>");
			 var lastTr = $("#questionLabelList").find("tr").last();
			 lastTr.after(html.join(""));
		 	 }
		 return false;
	}
	var url = basePath + "/examManage/examPaperQuestionFind.do";
	url += "?propIds=" + $('#propIds').val();
	url += "&levelIds=" + $('#levelIds').val();
	url += "&laiIds=" + $('#laiIds').val();
	url += "&grade=" + $('#grade').val();
	$.ajax({
	    url:url,
	    type: 'POST',
	    dataType: 'json',
	    success: function(result){
		   if(result != ''){
			   updateQuestionLabelTable(result.list1);
		   };
	    }
	});		
    
  });
  function updateQuestionLabelTable(result)
  {
	  $("input[name='labelcount']").each(function(key,val){
		  $(this).parent().parent().remove();
	  });
	  $("input[name='labelscore']").each(function(key,val){
		  $(this).parent().parent().remove();
	  });
	  $("#labeltd").remove();
	  $("#quest_table_last_tr").remove();
	  for(var i = 0 ; i < result.length ; i++)
	  {
		    label = result[i].label;
		  	count = result[i].count;
		    
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
	        html.push("' name = 'labelcount' value = '0'/></td>");
	        html.push("<td width='10%'>");
	        html.push("<input type = 'text' id = 'labelscore_");
	        html.push(label.id);
	        html.push("' name = 'labelscore' value = '0'/></td>");
	        html.push("</td>");
	        html.push("</tr>");
	        lastTr.after(html.join(""));
	  }
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
	  var html = new Array();
	  var lastTr = $("#questionLabelList").find("tr").last();
	  html.push("<tr align = 'center' class = 'labeltr' valign = 'middle' id = 'quest_table_last_tr'>");
	  html.push("<td width = '30%'>已选题量分数汇总:</td>");
	  html.push("<td width = '30%'>已选总题量:<input type = 'text' readonly id = 'total_quest' name = 'total_quest'/></td>");
	  html.push("<td width = '30%'>已选总分数:<input type = 'text' readonly id = 'total_score' name = 'total_score'/></td>");
	  html.push("<td width = '10%'></td>");
	  html.push("</tr>");
	  lastTr.after(html.join(""));
  }
  
  $("#init-examTypeNum").click(function(){
	  if(confirm("您确定要删除吗?"))
	  {
		  $("input[name='labelcount']").each(function(key,val){
			  $(this).val('0');
		  });
		  $("input[name='labelscore']").each(function(key,val){
			  $(this).val('0');
		  });
		  $("#total_quest").val(0);
		  $("#total_score").val(0);
	  }
  });
  
  //快捷策略
  $("#paperMode_1_submit").click(function() {
	var questionLabelIds = "";
	$("input[name='labelcount']").each(function() {
		var curId = $(this).prop("id");
		var labelId = curId.substr(11, curId.length);
		var count = $(this).val();
	    var score = $("#labelscore_" + labelId).val();
	    var info = labelId + "_" + count + "_" + score; 
	    questionLabelIds += info + ",";
	});
	
    if($("#paperName").val().trim() == '') {
      alert("请填写试卷名称！");
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
      var a = basePath + "/examManage/paperAdd.do?method=save";
      var b = document.fm1;
      
      b.target = "";
      b.action = a;
//      $("#fm1").submit();
      var param = $("#fm1").serialize();
      $.ajax({
			url:a,
			type:'post',
			data:param,
			dataType:'text',
			success:function(returnId){
				if (returnId == "success") {
	    			alert(" 保存成功!");
	    			window.location.href = basePath + "/paperManage/paperList.do"; 
	    		}
	    		else if (returnId == "duplicate") {
	    			alert(" 试卷名称重复！");
	    		}
	    		else
	    		{
	    			alert(" 保存失败!");
	    		}
			},
		});
    }
  });
  var addflag=0;
  $('#addExamType-link2').click(function(){
	  
	  var page2propIdsTemp = '';	
	  
		if($('#page2propIds').val() == '')
		{
			 alert("请选择学科!");
			 $('.labeltr2').remove();
			 $('.tr_loc').remove();
			 var dd= $('#labeltd2');
			 if($('#labeltd2').length == 0) {
				 var html = new Array();
				 html.push("<tr align='center' valign='middle'><td colspan='4' id='labeltd2'>--请选择题型--</td></tr>");
				 var lastTr = $("#questionLabelList2").find("tr").last();				 
				 lastTr.after(html.join(""));
			 	 }
			 if($('#stu_td').length == 0) {
				 var html = new Array();
				 html.push("<tr align='center' valign='middle'><td colspan='7' id='stu_td'>--请选择学科-</td></tr>");
				 var lastTr = $("#stuNames").find("tr").last();				 
				 lastTr.after(html.join(""));
			 	 }
			 
			 /*
			   2017.01.24 Add By IE 
			  */
			 
			 addflag=0;
			 
			 /*
			   End.
			  */
			 return false;
		}

		if(addflag==0){
			var url = basePath + "/examManage/examPaperQuestionFind.do";
			url += "?propIds=" + $('#page2propIds').val();
			url += "&levelIds=" + $('#page2levelIds').val();
			url += "&laiIds=" + $('#page2laiIds').val();
			url += "&grade=" + $('#page2grade').val();
			$.ajax({
			    url:url,
			    type: 'POST',
			    dataType: 'json',
			    success: function(result){
				   if(result != ''){
					   propInfo = result.propInfo;
					   updateQuestionLabelTable2(result.list1);
				   };
			    }
			});		
			addflag=1;
			page2propIdsTemp = $('#page2propIds').val();
		}
		
		/*
		 * 2017.01.24 Add By IE
		 * To add 问题 
		 */
		
		if (addflag==1) {
			if($('#page2propIds').val() == page2propIdsTemp) {
				return false;
			} else {
				var url = basePath + "/examManage/examPaperQuestionFind.do";
				url += "?propIds=" + $('#page2propIds').val();
				url += "&levelIds=" + $('#page2levelIds').val();
				url += "&laiIds=" + $('#page2laiIds').val();
				url += "&grade=" + $('#page2grade').val();
				$.ajax({
				    url:url,
				    type: 'POST',
				    dataType: 'json',
				    success: function(result){
					   if(result != ''){
						   propInfo = result.propInfo;
						   updateQuestionLabelTable2(result.list1);
					   };
				    }
				});		
				addflag=1;
				page2propIdsTemp = $('#page2propIds').val();
			}
		}
		
		//End.
		
	  });
	  function updateQuestionLabelTable2(result)
	  {
		$("#stu_td").remove();
		
		/*
		 * 2017.01.24 Add By IE
		 *  To　Change QuestionLabelTable2
		 */
		
		$('.tr_loc').remove();
		
		/*
		 *End. 
		 */
		
		var lastTr = $("#stuNames").find("tr").last();
		var html3 = [];
		var xueke_str = $("#page2typeNames").text();
		if(xueke_str.length != 0)
		{
			xueke_str = xueke_str.slice(0,-1);
		}
		var xueke_textarea = xueke_str.split(",");
		var xueke_id = $("#page2propIds").val().split(",");
		for(var i = 0; i<xueke_textarea.length; i++){
			html3.push("<tr class='tr_loc'>");
	        html3.push("<td id='td1_loc");
	        html3.push(xueke_id[i]);
	        html3.push("'>");
	        html3.push(xueke_textarea[i]);
	        html3.push("</td>");
	        html3.push("<td id='td2_loc");
	        html3.push(xueke_id[i]);
	        html3.push("'>");
	        html3.push("<input onfocus='checkTotalbfb();' onblur='checkTotalbfb();' class='input_loc' size='10' type='text' id='");
	        html3.push(xueke_id[i]);
	        html3.push("'>");
	        html3.push("%");
	        html3.push("</td>");
	        html3.push("<td id='td3_loc");
	        html3.push(xueke_id[i]);
	        html3.push("'>");
	        html3.push("最多可分配");
	        html3.push("<span class='span_loc' id='span_loc_");
	        html3.push(xueke_id[i]);
	        html3.push("'>");
	        html3.push("0</span> ");
	        html3.push(" %");
	        html3.push("</td>");
	        html3.push("</tr>");
		 }	
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
				   //写入百分比
			  //  var data = countQuesTemp('');
			  //  writeCountTemp(data);			
		  $("input[name='labelcount']").each(function(key,val){
			  $(this).parent().parent().remove();
		  });
		  $("input[name='labelscore']").each(function(key,val){
			  $(this).parent().parent().remove();
		  });
		  $("#labeltd2").remove();
		  $("#quest_table_last_tr").remove();
		  for(var i = 0 ; i < result.length ; i++)
		  {
			    label = result[i].label;
			  	count = result[i].count;
			    
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
		        html.push("' name = 'labelcount' value = '0'/></td>");
		        html.push("<td width='10%'>");
		        html.push("<input type = 'text' id = 'labelscore_");
		        html.push(label.id);
		        html.push("' name = 'labelscore' value = '0'/></td>");
		        html.push("</td>");
		        html.push("</tr>");
		        lastTr.after(html.join(""));
		  }
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
			  $(".span_loc").each(function() {
		            var idstr2 = $(this).prop("id");
		            var index2 = idstr2.indexOf('loc_');
		            var ids2 = idstr2.substr(index2 + 4, idstr2.length);
		            if(totQues == 0) {
		            	$(this).text(0);
		            	return;
		            }
		            var limit = (propInfo[ids2])/totQues;
		            
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
		  var html = new Array();
		  var lastTr = $("#questionLabelList2").find("tr").last();
		  html.push("<tr align = 'center' class='labeltr2' valign = 'middle' id = 'quest_table_last_tr'>");
		  html.push("<td width = '30%'>已选题量分数汇总:</td>");
		  html.push("<td width = '30%'>已选总题量:<input type = 'text' readonly id = 'total_quest' name = 'total_quest'/></td>");
		  html.push("<td width = '30%'>已选总分数:<input type = 'text' readonly id = 'total_score' name = 'total_score'/></td>");
		  html.push("<td width = '10%'></td>");
		  html.push("</tr>");
		  lastTr.after(html.join(""));
	  }
	  
	  $("#init-examTypeNum2").click(function(){
		  if(confirm("您确定要删除吗?"))
		  {
			  $("input[name='labelcount']").each(function(key,val){
				  $(this).val('0');
			  });
			  $("input[name='labelscore']").each(function(key,val){
				  $(this).val('0');
			  });
			  $(".span_loc").each(function() {
				  $(this).text('0');
			  });
			  $(".input_loc").each(function() {
				  $(this).text('0');
			  });
			  $("#total_quest").val(0);
			  $("#total_score").val(0);
		  }
	  });
	  // 未添加
	  // 精细策略提交
	  $("#mode_quick_submit").click(function() {
		  var questionLabelIds = "";
			$("input[name='labelcount']").each(function() {
				var curId = $(this).prop("id");
				var labelId = curId.substr(11, curId.length);
				var count = $(this).val();
			    var score = $("#labelscore_" + labelId).val();
			    var info = labelId + "_" + count + "_" + score; 
			    questionLabelIds += info + ",";
			});
			
		    if($("#paperName").val().trim()== '') {
		      alert("请填写试卷名称！");
		      return;
		    }
		    ////if($("#page2typeNames").val() == '') {	//chenlb  0706 update
		    if($("#page2propIds").val() == '') {
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
	
		      var a = basePath + "/examManage/paperAdd.do?method=save";
		      var param = $("#fm1").serialize();
		      $.ajax({
		    	  url:a,
		    	  type:'post',
		    	  data:param,
		    	  dataType:'text',
		    	  success:function(result){
		    		if(result == "success")
		    		{
		    			alert("添加成功!");
		    			window.location.href = basePath + "/paperManage/paperList.do"; 
		    		}
		    		else
		    		{
		    			alert("添加失败!");
		    			window.location.reload(true);
		    		}
		    	},
		      });
		     
	/*	      var b = document.fm1;
		      b.target = "";
		      b.action = a;
		      $("#fm1").submit();
		    */
	    } else 
	    {
	      return;
	    }
	  });

  
  // 手工策略查询
  $("#sele-link-hand").click(function() {
    var quesScor = $("#quesScor").val();
    //每次查询清空数组
    recordArray = [];
    var qNum1 = $("#3questionNum").val();
    if (qNum1 == '') {
        alert("请设置试题数量");
        $("#3questionNum").focus();
    	return false;;
    }
    var qNum2 = $("#_hand_quesNum").val();
    var num = parseInt(qNum1)+parseInt(qNum2); 
    //$("#questionNum").val($("#3questionNum").val());
    $("#questionNum").val(num);
    if (quesScor == '') {
      //alert("请设置每题分数");
      //$("#quesScor").focus();
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
  // 手工策略提交
  $("#submit-link-hand").click(function() {
    /*var queNu = $("#questionNum").val();
    var hanQu = $("#hand_quesNum").val();
    if (queNu == hanQu) {
      alert("请选择试题！");
      return;
    } else if (hanQu != 0) {
      alert("试题数量不足，请选择试题！");
      return;
    }*/
    var queNu = $("#hand_quesNum").val();
    var queSor = $("#hand_quesSocr").val();
    if(queNu > 300) {
      alert("试题数量不能大于300道，请重新选择！");
      return;
    }
    /*if(queSor > 500) {
      alert("试题分数不能大于500分，请重新选择");
      return;
    }*/
    $("#questionNum").val(queNu);
    $("#paperScore").val(queSor);
    /*var totSc = $("#paperScore").val();
    var hanQuS = $("#hand_quesSocr").val();*/
    /*if (totSc == hanQuS) {
      alert("请选择试题！");
      return;
    } else if (hanQuS != 0) {
      alert("试题分数不满足，请重新选择试题调整分数！");
      return;
    }*/
    if ($("#handmode_question_table").find("tr").length == 1) {
    	alert("请选择试题！");
    	return;
    }
    
    if (confirm("确认保存试卷？")) {
//      msgpanel();
      var url = basePath + "/examManage/paperAdd.do?method=save";
      $("#seleQues").val(seleQuesList.join("_"));
      var f = document.fm1;
      f.target = "";
      f.action = url;

//      $("#fm1").submit();
//      $("#mask").height(jQuery(document).height()).show();
      var param = $("#fm1").serialize();
      $.ajax({
			url:url,
			type:'post',
			data:param,
			dataType:'text',
			success:function(returnId){
				if (returnId == "success") {
	    			alert(" 保存成功!");
	    			window.location.href = basePath + "/paperManage/paperList.do"; 
	    		}
	    		else if (returnId == "duplicate") {
	    			alert(" 试卷名称重复！");
	    		}
	    		else
	    		{
	    			alert(" 保存失败!");
	    		}
			},
		});
    } else {
    	return;
    }
  });

  // 手工策略查询
  $("#reset-link-hand").click(function() {
    resetMode();
  });
 
 

  //添加试卷
  $("#selPaper-link").click(function() {
    var e = basePath + "/examManage/examPaperDetail2.do";
    var c = 1000;
    var d = 1300;
    window.open(e, "", "top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width=" + d + ",height=" + c);
  });

  // 卷中卷提交
  $("#submit-link-mid").click(function() {
    if (paperIds.length == 0) {
      alert("请选择试卷！");
      return;
    }
    var totQues = $("#total_quest").val();
    var totSoc = $("#total_score").val();
    if(totQues > 300) {
      alert("所设总题量不能大于300道！");
      return;
    }
    /*if(totSoc > 500) {
      alert("所设总分数不能大于500分！");
      return;
    }*/
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
    	 var a = basePath + "/examManage/paperAdd.do?method=save";
         var b = document.fm1;
         b.target = "";
         b.action = a;
//         $("#fm1").submit();
         var param = $("#fm1").serialize();
         $.ajax({
 			url:a,
 			type:'post',
 			data:param,
 			dataType:'text',
 			success:function(returnId){
 				if (returnId == "success") {
 	    			alert(" 保存成功!");
 	    			window.location.href = basePath + "/paperManage/paperList.do"; 
 	    		}
 	    		else if (returnId == "duplicate") {
 	    			alert(" 试卷名称重复！");
 	    		}
 	    		else
 	    		{
 	    			alert(" 保存失败!");
 	    		}
 			},
 		});
    } else {
    	return;
    }
  });

  $("#clsm header img").click(function() {
    $(this).parents("#clsm").slideUp("fast");
  });

  clsm();

  $("#selTitleAll").click(function() {
      /*$("input[name='titleId']").each(function() {
        this.checked = true;
      });*/

      $("input[name='titleId']").prop("checked", true);
  });
  $("#selTitleEmpty").click(function() {
//      $("input[name='titleId']").each(function() {
//          this.checked = false;
//      });
      $("input[name='titleId']").prop("checked", false);
  });
  $("#selTitleOk").click(function() {
      clearQuesStud();
      var ttext = "";
      var checkText = "";
      $(".titlesTr").each(function() {
          var flag = false;
          $(this).children("td").eq(1).children("input").each(function() {
              if($(this).is(':checked')) {
                  flag = true;
                  var textThis = $(this).next("label").html();
                  checkText = checkText+textThis + "  ";
              }
          });
          if(flag) {
            ttext = ttext + $(this).children("td").eq(0).html();
            ttext = ttext+checkText + "\r\n";
          }
          checkText = "";
      });
      $("textarea[name='titleText']").text($.trim(ttext));
      $("#maskTitle").css("display", "none");
      $("#black_bg").hide();
      $("#layer_5").hide();
  });
});

function clsm(){
  $("#clsm").slideToggle("fast");
}

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
  initQuestionLabelTable($("#questionLabelList2"));
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
  /*var paperScore = $("#paperScore").val();
  var questionNum = $("#questionNum").val();*/
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



//设置试卷
function setPaper(){
  var html =[];
  $("#paperInfo_temp").remove();
  var lastTr = $("#lastTr");
  var pIds = "";
  if($("tr[id^='paperNames_tr']") != null){
	  $('tr[id^="paperNames"]').each(function(){
		  id = $(this).prop('id');
		  var  Ids = id.slice(14, id.length)+"_";
		  pIds +=Ids;
	  });
  }
  
  for (var i = 0; i < paperNamesInfos.length; i++) {
      var pStr = paperNamesInfos[i];
      html.push("<tr align='center' valign='middle' id='paperNames_tr_");
      html.push(paperNamesId1[i]);
      html.push("'>");
      html.push("<td width='20%'> ");
      html.push(pStr[0]);
      html.push("</td>");
      html.push("<td width='20%'>");
      if (pStr[1] == "1") {
          html.push("快捷策略");
      } else if (pStr[1] == "2") {
          html.push("精细策略");
      } else if (pStr[1] == "4") {
          html.push("卷中卷");
      } else {
          html.push("手工组卷");
      }
      html.push("</td>");
      html.push("<td width='12%'>");
      html.push(pStr[2]);
      html.push("</td>");
      html.push("<td width='12%'>");
      html.push(pStr[3]);
      html.push("</td>");
      html.push("<td width='20%'>");
      html.push(pStr[4]);
      html.push("</td>");
      html.push("<td>");
      html.push("<a href='javascript:viewPaper(");
      html.push(paperNamesId1[i] +");'>查看</a>");
      html.push("<a href='javascript:deletePaper(");
      html.push(paperNamesId1[i]);
      html.push(");'>删除</a>");
      html.push("</td>");
      html.push("</tr>");
      pIds += paperNamesId1[i] +"_";
      paperIds.push(paperNamesId1[i]);
  }
  
  paperNamesId1.length = 0;
  paperNamesInfos.length = 0;
  $("#papersIds").val(pIds);
  lastTr.after(html.join(""));
  setPaperLabelInfo(pIds);
}
/**
 * @param pIds
 * Add by Tiger.
 * Draw paper of table type in 卷中卷.
 */
function setPaperLabelInfo(pIds)
{
	  var flag = false;
	  //init table.
	  $('#lastTr2').siblings().each(function(key,val){
		  $(this).remove();
	  });
	  
	  //Get the count of questions.
	  var url = basePath+'/paperManage/countLabelId.do';
	  $.ajax({
			url: url,
			type: 'POST',
			data: "paperIds="+pIds,
			dataType: 'json',
			success: function(result){
				if(result != ""){
					$("#labelList_temp").remove();
					var html = new Array();
					
					var lastTr = $("#paperInfo2").find("tr[id='lastTr2']");
					$("#paperInfo2 tr[id^='labelNames_tr']").each(function(){ $(this).remove(); });
					
					for(var i = 0 ; i < result.length ; i++)
					{
					    label = result[i].label;
					  	count = result[i].count;
					    if(count != 0)
					    {							    
							    html.push("<tr align='center' valign='middle' id='labelNames_tr_");
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
						        html.push("' name = 'labelcount' value = '0'/></td>");
						        html.push("<td width='10%'>");
						        html.push("<input type = 'text' id = 'labelscore_");
						        html.push(label.id);
						        html.push("' name = 'labelscore' value = '0'/></td>");
						        html.push("</td>");
						        html.push("</tr>");
					    }
	   			   }	
				   
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
					  //Calculate the score when score of questions changed by user.
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
					  var html = new Array();
					  var lastTr = $("#paperInfo2").find("tr").last();
					  html.push("<tr align = 'center' valign = 'middle' id = 'quest_table_last_tr'>");
					  html.push("<td width = '30%'>汇总:</td>");
					  html.push("<td width = '30%'>已选总题量:<input type = 'text' readonly id = 'total_quest' name = 'total_quest'/></td>");
					  html.push("<td width = '30%'>已选总分数:<input type = 'text' readonly id = 'total_score' name = 'total_score'/></td>");
					  html.push("<td width = '10%'></td>");
					  html.push("</tr>");
					  lastTr.after(html.join(""));
				}
			}
		});
}

function deletePaper(pId) {
    if (confirm("确认删除吗？")) {
        for (var i = 0; i < paperNamesId.length; i++) {
            if (pId == paperNamesId[i]) {
                paperNamesId.splice(i, 1);
                $("#paperNames_tr_" + pId).remove();
                break;
            }
        }
    }
    if (paperNamesId.length == 0) {
        initPaper();
    }
    /*if(paperIds.length != 0) {
        setQuesNum();
    }*/ else {
        var totQuesNum = 0;
        $(".mid_span").each(function(){
            var spanId = $(this).get(0).id;
            var lableId = spanId.substr(8,spanId.length);
            $("#mid_quesNum"+lableId).get(0).disabled = "disabled";
            $("#mid_quesNum"+lableId).val("");
            $("#mid_quesSoc"+lableId).get(0).disabled = "disabled";
            $("#mid_quesSoc"+lableId).val("");
            $(this).text("0");
        });
        $("#mid_quesTotNum").text(totQuesNum);
        
        var pIds = "";
        if($("tr[id^='paperNames_tr']") != null){
      	  $('tr[id^="paperNames"]').each(function(){
      		  id = $(this).prop('id');
      		  var  Ids = id.slice(14, id.length)+"_";
      		  pIds +=Ids;
      	  });
        }
        setPaperLabelInfo(pIds);
        
    }
}
//删除试卷为空时初始
function initPaper(){
	$("#paperInfo_temp").remove();
    var lastTr = $("#lastTr");
    
    var html = [];
    html.push("<tr id='paperInfo_temp' align='center'>");
    html.push("<td colspan='7'>");
    html.push("请选择试卷，最多可选中10张试卷！");
    html.push("</td>");
    html.push("</tr>");
    lastTr.after(html.join(""));
    
    $("#labelList_temp").remove();
    $('#lastTr2').siblings().each(function(key,val){
		  $(this).remove();
	});
    
    var lastTr = $("#lastTr2");
 
    var html = [];
    html.push("<tr id='labelList_temp' align='center'>");
    html.push("<td colspan='6'>");
    html.push("目前没有选择试题！");
    html.push("</td>");
    html.push("</tr>");
    lastTr.after(html.join(""));
}



function resetPaperMid() {
    paperIds = [];
    $("tr[id^='paperInfo_tr_']").remove();
    initPaper();
    var totQuesNum = 0;
    $(".mid_span").each(function(){
        var spanId = $(this).get(0).id;
        var lableId = spanId.substr(8,spanId.length);
        $("#mid_quesNum"+lableId).get(0).disabled = "disabled";
        $("#mid_quesNum"+lableId).val("");
        $("#mid_quesSoc"+lableId).get(0).disabled = "disabled";
        $("#mid_quesSoc"+lableId).val("");
        $(this).text("0");
    });

      $("#mid_quesTotNum").text(totQuesNum);
}

//查看试卷
function viewPaper(pId) {
    var url = basePath + "/paperManage/examPaperView.do?handle=view&paperId=" + pId;
    window.open(url);
}

function viewQuestion(qid) {
  //var url = basePath + "/manage/examQuestion/getQuestion.do?id="+qid +"&modeType=close";
  var url = basePath + "/paperManage/viewQuestion.do?id="+qid +"&handle=v";
  window.open(url);
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

    //取得当前试题被删除后的所属下标
//    var indexTr = $("#es" + qid).index() ;

    if(index > -1) {
      seleQuesList.splice(index,1);
      $("#es"+qid).remove();
    }

/*    //取得试题下标以后的所有试题TR
    var htmlStrTab = htmlStr + "Tab" ;
    var trInfo2 = $("#"+ htmlStrTab + " tr:gt("+ (indexTr - 1) +")") ;
    trInfo2.each(function(i){
        var tdIndex = i + indexTr ;
        $(this).find("td:eq(0)").html(tdIndex) ;
    });

    //删掉html字符串中该题数据
    var newHtml = getTableInnerHtml($("#"+htmlStrTab).html(), htmlStrTab) ;
    $("#"+htmlStr).val(newHtml);
*/
    //取得分数与题号数组编号
/*    var htmlArray = getNumAndScoreTot(htmlStrTab) ;
    var htmlNum = htmlArray[0] ;
    var htmlTot = htmlArray[1] ;
    //改变题量
    $("#"+htmlNum).val(parseInt($("#"+htmlNum).val()) - 1) ;
    //改变试题分数
    $("#"+htmlTot).val(parseInt($("#"+htmlTot).val()) - score) ;
*/
    //重置总题数和分数
    var oldNum = $("#hand_quesNum").val();
    var oldScor = $("#hand_quesSocr").val();
    $("#hand_quesNum").val(parseInt(oldNum)+1);
    $("#hand_quesSocr").val(parseInt(oldScor)+score);
    
    $("#_hand_quesNum").val($("#_hand_quesNum").val()-1);
    $("#_hand_quesSocr").val($("#_hand_quesSocr").val()-score);
    
    //总分数
/*    var oldTotal = $("#"+label).text();
    $("#"+label).text(parseInt(oldTotal) - score);
*/  }
}

function getNumAndScoreTot(htmlStrTab){
    if(htmlStrTab === 'theHtmlTab'){
        return new Array("num0","scoreTot0") ;
    }else if(htmlStrTab === 'theHtml9Tab'){
        return new Array("num9","scoreTot1") ;
    }else if(htmlStrTab === 'theHtml1Tab'){
        return new Array("num1","scoreTot2") ;
    }else if(htmlStrTab === 'theHtml2Tab'){
        return new Array("num2","scoreTot3") ;
    }else if(htmlStrTab === 'theHtml3Tab'){
        return new Array("num3","scoreTot4") ;
    }else if(htmlStrTab === 'theHtml4Tab'){
        return new Array("num4","scoreTot5") ;
    }else if(htmlStrTab === 'theHtml5Tab'){
        return new Array("num5","scoreTot6") ;
    }else if(htmlStrTab === 'theHtml6Tab'){
        return new Array("num6","scoreTot7") ;
    }else if(htmlStrTab === 'theHtml7Tab'){
        return new Array("num7","scoreTot8") ;
    }else{
        return new Array("num8","scoreTot9") ;
    }
}

//重新组装试题页面Table信息
function getTableInnerHtml(innerHtml, tableId){
    var tableInner = "<table width='100%' border='0' cellspacing='0' cellpadding='0' class='table_a' id='"+ tableId +"'>" ;
    return tableInner + innerHtml + $("#endHtml").val() ;
}

function setTitles() {
	var titles  = new Array();
    $("input:checkbox[name='titleId']:checked").each(function(){
  		titles.push($(this).val());
    });
    $("#questionTitleIds").val(titles);
    $("#questionTitleNames").val($("textarea[name='titleText']:eq(0)").text());
}
function setQuesNum(){
  var rd;
  $.ajax({
    type:"post",
    url:"manage/paper/getPaperAndPaper.do",
    dataType:"json",
    async:false,
    data:"paperIds="+paperIds.join("_"),
    success: function(rdata) {
        rd = rdata;
      },
      error: function() {
        alert("error");
      }
    });

  var totQuesNum = 0;
  $(".mid_span").each(function(){
    var spanId = $(this).get(0).id;
    var lableId = spanId.substr(8,spanId.length);
    $("#mid_quesNum"+lableId).get(0).disabled = "disabled";
    $("#mid_quesNum"+lableId).val("");
    $("#mid_quesSoc"+lableId).get(0).disabled = "disabled";
    $("#mid_quesSoc"+lableId).val("");
    $(this).text("0");
    if(null != rd){
      for(var i=0; i<rd.length; i++){
        if(rd[i]["questionLabelId"] == lableId){
          var allNum = rd[i]["allNum"];
          totQuesNum = totQuesNum+allNum;
          $(this).text(allNum);
          $("#mid_quesNum"+lableId).get(0).disabled = "";
          $("#mid_quesSoc"+lableId).get(0).disabled = "";
          $("#mid_quesSoc"+lableId).val(1);
        }
      }
    }
  });

  $("#mid_quesTotNum").text(totQuesNum);

}

function checkTotalQues2(){
  var totQues = 0;
  var totSoc = 0;
  var flag = false;
  $(".mid_quesNum").each(function(){
    var spanId = $(this).get(0).id;
    var lableId = spanId.substr(11,spanId.length);
    var qNum = $(this).val();
    var qSoc = $("#mid_quesSoc"+lableId).val();
    var lableName = $("#mid_lableName"+lableId).text();
    var limitNum = eval($("#mid_span"+lableId).text());
    if("" != qNum && qNum !=0){
      var patrn = /^[1-9]{1}[0-9]*$/;
      if(testDemo(patrn,qNum)){
        qNum = eval(qNum);
        if(qNum>limitNum){
          $(this).val("");
          alert(lableName+"所设题量只能是正整数并且不能大于"+limitNum+"！");
          $(this).focus();
          flag = true;
          return false;
        }else {
          if(testDemo(patrn,qSoc) && qSoc <=100){
            qSoc = eval(qSoc);
            totQues = eval(totQues)+eval(qNum);
            totSoc = eval(totSoc)+eval(qNum)*eval(qSoc);
          }else {
            $("#mid_quesSoc"+lableId).val(1);
            alert(lableName+"每题分数最小为1并且不能大于100！");
            $("#mid_quesSoc"+lableId).focus();
            flag = true;
            return false;
          }
        }
      }else {
        $(this).val("");
        alert(lableName+"所设题量只能是正整数并且不能大于"+limitNum+"！");
        $(this).focus();
        flag = true;
        return false;
      }
    }
  });

  $("#mid_quesTotSel").text(totQues);
  $("#mid_quesTotSoc").text(totSoc);
}

function getKeyCode(evt){
  evt = (evt) ? evt : ((window.event) ? window.event : "") //兼容IE和Firefox
  var key = evt.keyCode?evt.keyCode:evt.which; //兼容IE和Firefox获得keyBoardEvent对象的键值
    if(key>=48 && key<=57){
      return true;
    }
    return false;
}

function setTemplateParamjx() {
  //题库   questionTypeNames
  $("#questionTypeNames").val($("#txtInd").val());  //firefox  chrome 用text()取不到
  //学科  questionStudyNames
  $("#questionStudyNames").val($("#txtLoccc").val());
  //难度
  $("#questionCognizeNames").val($("#txtInd4").val());
  //试用级别   适用级别
  $("#questionTitleNames").val($("#txtInd6").val());
  //题型   questionLabelNames
  $("#questionLabelNames").val($("#txtInd2").val());
}
function setTemplateParam() {
  //题库   questionTypeNames
  $("#questionTypeNames").val($("#1txtInd").val());
  //学科  questionStudyNames
  $("#questionStudyNames").val($("#txtLocc").val());
  //难度
  $("#questionCognizeNames").val($("#1txtInd4").val());
  //试用级别与难度   适用级别与难度
  //$("#questionTitleNames").val($("#1txtInd6").text());
  //题型   questionLabelNames
  $("#questionLabelNames").val($("#1txtInd2").val());
}
function msgpanel() {
  $("#maind").mask("组卷中,请等待...");
}

function closeTPanl() {
    $("#maskTitle").css("display", "none");
    $("#black_bg").hide();
    $("#layer_5").hide();
}
function initQuestionLabelTable(obj)
{
	$(obj).html("");
	var html = [];
    html.push("<tr class='tr'>");
    html.push("<th width='30%'>题型</th>");
    html.push("<th width='30%'>可选择题量</th>");
    html.push("<th width='30%'>本次选题量</th>");
    html.push("<th width='10%'>每题分数</th>");
    html.push("</tr>");
    html.push("<tr align='center' valign='middle'>");
    html.push("<td colspan='4' id='labeltd'>--请选择题型--</td>");
    html.push("</tr>");
    $(obj).html(html.join(""));
}
function initStuNamesTable()
{
	$("#stuNames").html("");
	var html = [];
    html.push("<tr class='tr'>");
    html.push("<th width='25%'>学科</th>");
    html.push("<th width='35%'>请分配内容比例</th>");
    html.push("<th width='40%'>说明</th>");
    html.push("</tr>");
    html.push("<tr align='center' valign='middle'>");
    html.push("<td colspan='3' id='labeltd'>--请选择学科--</td>");
    html.push("</tr>");
    $("#stuNames").html(html.join(""));
}