//监考人
var stuNamesId1 = [];
var stuNamesId = [];
var stuNamesInfo = [];
//判卷人
var teacherNamesId1 = [];
var teacherNamesId = [];
var teacherNamesInfo = [];

var paperNamesId = [];
var paperNamesInfos = [];
var paperNamesId1 = [];
//var paperInfo2 = new Array();
//var lastTabs = new Array();
var userNamesId1 = [];
var userNamesId = [];
var userNamesId_back = [];
var userNamesInfo = [];
var myUserNamesId = [];
var myUserNamesInfo = [];

var orgNamesId1 = [];
var orgNamesId = [];
var orgNamesInfo = [];

//含有主观题标识
var existsFlag = false;
$(function () {
	
	$("#tabs-2").hide();
	$("#tabs-3").hide();
    $("#buttonOneDown").click(function () {

        if ($("#examExaminationName").val() == "") {
            alert("请输入考试名称");
            $("#examExaminationName").focus();
            return;
        }
        if ($("#curTypeId").val() == "") {
            alert("请选择目录");
            $("#typeNames01").focus();
            return;
        }
        if ($("#examTimes").val() == "") {
            alert("请输入考试时长");

            $("#examTimes").focus();
            return;
        }
        var reg = new RegExp("^[1-9][0-9]*");
        var examTimesValue = $("#examTimes").val();
        if (!reg.test(examTimesValue)) {
            alert("请输入正整数！");
            $("#examTimes").focus();
            return;
        }

        if ($("#startTime").val() == "") {
            alert("请输入开始时间");
            $("#startTime").focus();
            return;
        }
        if ($("#endTime").val() == "") {
            alert("请输入结束时间");
            $("#endTime").focus();
            return;
        }
        if ($("input[name='examExamination.isCutScreen'][title='ok']").attr("checked") == 'checked') {
            if ($("#showScreeNum").val() == "") {
                alert("请输入限制切屏次数");
                $("#showScreeNum").focus();
                return;
            }
        }
        if ($("#passValue").val() == "") {
            $("#passValue").focus();
            alert("请输入通过标准！");
            return;
        } else {
                if ($("#passValue").val() > 100) {
                    alert("不能超过100%");
                    $("#passValue").focus();
                    return;
                }
        }

        $("#tabs-1").hide();
        $("#tabs-2").toggle();
        $("#tabs-3").hide();
    });

    $("#buttonTwoUp").click(function () {
        $("#tabs-1").toggle();
        $("#tabs-2").hide();
        $("#tabs-3").hide();
    });
    $("#buttonTwoDown").click(function () {
        if (paperNamesId.length == 0) {
            alert("请添加试卷！");
            return;
        }
        if (stuNamesId.length == 0) {
            alert("请添加监考人！");
            return;
        }
        $("#tabs-1").hide();
        $("#tabs-2").hide();
        $("#tabs-3").toggle();
    });

    $("#buttonThreeUp").click(function () {
        $("#tabs-1").hide();
        $("#tabs-2").toggle();
        $("#tabs-3").hide();
    });

});

$(function () {
    //添加监考人
    $("#addStudent-link").click(function () {

        var e = basePath + "/examManage/examStudentDetail.do?method=list&model.userType=2";
        var c = 950;
        var d = 1500;
        window.open(e, "", "top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width=" + d + ",height=" + c);
    });

    //添加考生
    $("#addUser-link").click(function () {
    	var e = basePath + "/examManage/examUserDetail.do?method=list&model.userType=1";
        var c = 1000;
        var d = 1300;
        window.open(e, "", "top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width=" + d + ",height=" + c);
    });
    $("#addTeacher-link").click(function () {
        var e = basePath + "/manage/exam/toAddOlemUserPage.do?modeType=panjuan";
        var c = 1000;
        var d = 1300;
        window.open(e, "", "top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width=" + d + ",height=" + c);

    });

    //添加试卷
    $("#addPaper-link").click(function () {
        if (paperNamesId.length == 1) {
            alert("只能选择一张试卷");
            return;
        }
        //alert("ddd");
        var e = basePath + "/examManage/examPaperDetail.do";
        var c = 950;
        var d = 1300;
        window.open(e, "", "top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width=" + d + ",height=" + c);

    });
    
    $('#addOrganization').click(function(){
    	var e = basePath + "/examManage/hospitalList.do?type=23";
    	
    	var c = 950;
    	var d = 1500;
    	window.open(e, "", "top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width=" + d + ",height=" + c);
    });
    
    $("#submit-link").click(function () {
        if (confirm("确定保存？")) {
            var c = basePath + "/examManage/examSave.do";
            $("#stuNamesId").val(stuNamesId);
            $("#userNamesId").val(userNamesId);
            $("#paperNamesId").val(paperNamesId);
            $("#orgNamesId").val(orgNamesId);
         /*   if($("#userNamesId").val() == null || $("#userNamesId").val() == "")
            {
            	alert("请选择考生!");
            }*/
            var d = document.fm1;
            d.action = c;
            $("#fm1").submit();
        }
    });

});

function setStu() {
	if(stuNamesId1 != null && stuNamesId1.length > 0){
	    $("#stu_td").remove();
	    $('.stulastr').remove();
	    var html = [];
	    var lastTr = $("#stuNames").find("tr").last();
	
	    for (var i = 0; i < stuNamesInfo.length; i++) {
	        var pStr = stuNamesInfo[i];
	        html.push("<tr align='center' class = 'stulastr' valign='middle' id='stuNames_tr_");
	        html.push(stuNamesId1[i]);
	        html.push("'>");
	        html.push("<td width='10%'> ");
	        html.push(pStr[0]);
	        html.push("</td>");
	        html.push("<td width='20%'>");
	        html.push(pStr[1]);
	        html.push("</td>");
	        html.push("<td width='20%'>");
	        html.push(pStr[2]);
	        html.push("</td>");
	        html.push("<td width='10%'>");
	        html.push(pStr[3]);
	        html.push("</td>");
	        html.push("<td width='10%'>");
	        if(pStr[4] == 1)
	        {
	        	html.push("学员");
	        }
	        if(pStr[4] == 2)
	        {
	        	html.push("编辑");
	        }
	        if(pStr[4] == 3)
	        {
	        	html.push("专家");
	        }
	        if(pStr[4] == 4)
	        {
	        	html.push("医院");
	        }
	        if(pStr[4] == 5)
	        {
	        	html.push("企业");
	        }
	        if(pStr[4] == 6)
	        {
	        	html.push("协会");
	        }
	        if(pStr[4] == 7)
	        {
	        	html.push("学会");
	        }
	        if(pStr[4] == 8)
	        {
	        	html.push("出版社");
	        }
	        
	        html.push("</td>");
	        html.push("<td width='20%'>");
	        html.push(pStr[5]);
	        html.push("</td>");
	        html.push("<td width = '10%'>");
	        html.push("<a class = 'edit_btn' href='javascript:showStudent(");
	        html.push(stuNamesId1[i]);
	        html.push(")'>查看</a>");
	        html.push("<a href='javascript:delTea1(");
	        html.push(stuNamesId1[i]);
	        html.push(")'>删除</span>");
	        html.push("</td>");
	        html.push("</tr>");
	
	
	    }
	    // stuNamesId1.length = 0;
	    // stuNamesInfo.length = 0;
	     
	     //$("#ksTd").attr("rowSpan",$("#userNames").find("tr").length+20);
	     //stuNames2.length = 0;
	    lastTr.after(html.join(""));
	}
}



function delTea1(stuId) {
    if (confirm("确认删除吗？")) {
        for (var i = 0; i < stuNamesId.length; i++) {
            if (stuId == stuNamesId[i]) {
                stuNamesId.splice(i, 1);
                stuNamesId1.splice(i, 1);
                stuNamesInfo.splice(i, 1);
                $("#stuNames_tr_" + stuId).remove();
                break;
            }
        }
        if (stuNamesId.length == 0) {
            initTea(1);
        }
    }
}
//判卷人
function showTeacher(id) {
    $("#teacherNames").slideDown("slow");
}
//设置考生
function setUser() {
	if(userNamesId.length > 0 && userNamesId != null)
	{
		$("#user_td").remove();
	    var html = new Array();
	    $("#userNames tr:gt(0)").remove();
	    var lastTr = $("#userNames").find("tr").last();
	    for (var i = 0; i < userNamesInfo.length; i++) {
	    	 var pStr = userNamesInfo[i];
	         html.push("<tr align='center' valign='middle' class = 'trlast' id='userNames_tr_");
//	         html.push(userNamesId1[i]);
	         html.push(pStr[6]);
	         html.push("'>");
	         html.push("<td width='10%'> ");
	         html.push(pStr[0]);
	         html.push("</td>");
	         html.push("<td width='20%'>");
	         html.push(pStr[1]);
	         html.push("</td>");
	         html.push("<td width='20%'>");
	         html.push(pStr[2]);
	         html.push("</td>");
	         html.push("<td width='10%'>");
	         html.push(pStr[3]);
	         html.push("</td>");
	         html.push("<td width='10%'>");
	         if(pStr[4] == 1){
	         	html.push("学员");
	         }
	         if(pStr[4] == 2){
	         	html.push("编辑");
	         }
	         if(pStr[4] == 3){
	         	html.push("专家");
	         }
	         if(pStr[4] == 4){
	         	html.push("医院");
	         }
	         if(pStr[4] == 5){
	         	html.push("企业");
	         }
	         if(pStr[4] == 6){
	         	html.push("协会");
	         }
	         if(pStr[4] == 7){
	         	html.push("学会");
	         }
	         if(pStr[4] == 8){
	         	html.push("出版社");
	         }
	         
	         html.push("</td>");
	         html.push("<td width='15%'>");
	         html.push(pStr[5]);
	         html.push("</td>");
	         html.push("<td width = '15%'>");
	         html.push("<a class = 'edit_btn' href='javascript:showUser(");
	         html.push(pStr[6]);
//	         html.push(userNamesId1[i]);
	         html.push(")'>查看</a>");
	         html.push("<a href='javascript:deleteUser(");
	         html.push(pStr[6]);
//	         html.push(userNamesId1[i]);
	         html.push(")'>删除</span>");
	         html.push("</td>");
	         html.push("</tr>");
	    }
	    $("#ksTd").attr("rowSpan",$("#userNames").find("tr").length+20);

	    lastTr.after(html.join(""));
	    //// serialize/////
//	    userNamesId1.length = 0;
//	    userNamesInfo.length = 0;
//	    myUserNamesId.length = 0;
	}
}

function showStudent(id) {
	layer.open({
		type: 1,
		title: "编辑详情",
		skin: 'layui-layer-rim', //加上边框
		area: [win_w, win_h], //宽高
		content: add_cont,
		closeBtn: 0,
		btn: ['返回'],
		yes: function (index, layero) {
			//缩写保存功能
			layer.close(index);
		},
		btn2: function (index, layero) {
			layer.close(index);
		},
		success: function(layerb, index){
			$(".btn1").click(function(){
				layer.close(index);
			});
		}
	});
	var url = basePath + "/system/editManage.do?method=view&rtype=1";
	var params = "&id="+id;

	$.ajax({
	    url:url,
	    type: 'POST',
	    data: params,
	    dataType: 'json',
	    success: function(result){
		   if(result != ''){
		      var item = result.item;
		      var config = result.config;
		      $('#userId').val(item.userId);
		      $('#realName1').val(item.realName);
		      $('#accountName').val(item.accountName);
		      $('#deptName').val(item.deptName);
		      $('#mobilPhone1').val(item.mobilPhone);
		   }else{
		   		alert('检查内容失败!');
		   }
	    }
	});
}
function showUser(id) {
	layer.open({
		type: 1,
		title: "学员详情",
		skin: 'layui-layer-rim', //加上边框
		area: [win_w1, win_h1], //宽高
		content: add_user_cont,
		closeBtn: 0,
		btn: ['返回'],
		yes: function (index, layero) {
			//缩写保存功能
			layer.close(index);
		},
		btn2: function (index, layero) {
			layer.close(index);
		},
		success: function(layerb, index){
			$(".btn1").click(function(){
				layer.close(index);
			});
		}
	});
		//var url = basePath + "/system/editManage.do?method=view&rtype=1";
		var url = basePath + "/system/editManage.do?method=view&rtype=1";
		var params = "&id="+id;

		$.ajax({
		    url:url,
		    type: 'POST',
		    data: params,
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
				   if(result != ''){
					      var item = result.item;
					      var config = result.config;
					      $('#certificateType').val(item.certificateType);
					      $('#certificateNo').val(item.certificateNo);
					      $('#realName1').val(item.realName);
					      $('#sex1').val(item.sex);
					      $('#accountName').val(item.accountName);
					      $('#education').val(item.education);
					      $('#deptName').val(item.deptName);
					      $('#profession').val(item.profession);
					      $('#profTitle').val(item.profTitle);
					      $('#mobilPhone1').val(item.mobilPhone);
					      $('#workUnit1').val(item.workUnit);
					      if(config != null){
						      $('#userProvinceName').val(config.userProvinceName);
						      $('#userCityName').val(config.userCityName);
						      $('#userCountiesName').val(config.userCountiesName);
					      } 
					      $('#address').val(item.userConfig.address);
					   }else{
					   		alert('检查内容失败!');
					   }
			   }else{
			   		alert('检查内容失败!');
			   }
		    }
		});
	
}
function deleteUser(userId) {
    if (confirm("确认删除吗？")) {
    	for(var i= 0; i< myUserNamesId.length;i++){
    		if(userId == myUserNamesId[i].split("+")[0]){
    			myUserNamesId.splice(i, 1);
    			break;
    		}
    	}
        for (var i = 0; i < userNamesId.length; i++) {
            if (userId == userNamesId[i]) {
                userNamesId.splice(i, 1);
                $("#userNames_tr_" + userId).remove();
                break;
            }
        }
        for (var j = 0; j < userNamesId_back.length; j++) {
            if (userId == userNamesId_back[j]) {
                userNamesId_back.splice(j, 1);
                break;
            }
        }
        if (userNamesId.length == 0) {
            initTea(3);
        }
    }
}
function initTea(type) {
    if (type == 1) {
        var lastTr = $("#stuNames").find("tr").last();
        if($(lastTr).attr("id") != "stu_td") {
            var html = [];
            html.push("<tr id='stu_td' align='center'>");
            html.push("<td colspan='7'>");
            html.push("请点击左侧选择监考人");
            html.push("</td>");
            html.push("</tr>");
            lastTr.after(html.join(""));
        }
    } else if (type == 2) {
        var lastTr = $("#teacherNames").find("tr").last();
        if($(lastTr).attr("id") != "teacher_td") {
            var html = [];
            html.push("<tr id='teacher_td' class='teacherTd' align='center'>");
            html.push("<td colspan='7'>");
            html.push("请点击左侧选择判卷人");
            html.push("</td>");
            html.push("</tr>");
            lastTr.after(html.join(""));
        }
    } else if (type == 3) {
        var lastTr = $("#userNames").find("tr").last();
        if($(lastTr).attr("id") != "user_td") {
            var html = [];
            html.push("<tr id='user_td' align='center'>");
            html.push("<td colspan='7'>");
            html.push("--添加考生--");
            html.push("</td>");
            html.push("</tr>");
            lastTr.after(html.join(""));
        }
    } else if (type == 4) {
        var lastTr = $("#orgTable").find("tr").last();
        if($(lastTr).attr("id") != "org_td") {
            var html = [];
            html.push("<tr id='org_td' align='center'>");
            html.push("<td colspan='2'>");
            html.push("--添加机构--");
            html.push("</td>");
            html.push("</tr>");
            lastTr.after(html.join(""));
        }
    }

}
function setTeacher() {
    $("#teacher_td").remove();
    var html = new Array();
    var lastTr = $("#teacherNames").find("tr").last();

    for (var i = 0; i < teacherNamesInfo.length; i++) {
        var pStr = teacherNamesInfo[i];
        html.push("<tr align='center'  valign='middle' id='teacherNames_tr_");
        html.push(teacherNamesId1[i]);
        html.push("'>");
        html.push("<td width='120'> ");
        html.push(pStr[0]);
        html.push("</td>");
        html.push("<td width='90'>");
        html.push(pStr[1]);
        html.push("</td>");
        html.push("<td width='80'>");
        html.push(pStr[2]);
        html.push("</td>");
        html.push("<td width='80'>");
        html.push(pStr[3]);
        html.push("</td>");
        html.push("<td width='80'>");
        html.push(pStr[4]);
        html.push("</td>");
        html.push("<td width='140'>");
        html.push(pStr[5]);
        html.push("</td>");
        html.push("<td>");
        html.push("<span class='tablebtn_blue' onclick='showUser(");
        html.push(teacherNamesId1[i]);
        html.push(")'>查看</span>");
        html.push("<span class='tablebtn_blue' onclick='delTeacher(");
        html.push(teacherNamesId1[i]);
        html.push(")'>删除</span>");
        html.push("</td>");
        html.push("</tr>");
    }
    teacherNamesId1.length = 0;
    teacherNamesInfo.length = 0;
    lastTr.after(html.join(""));
}
function delTeacher(teacherId) {
    if (confirm("确认删除吗？")) {
        for (var i = 0; i < teacherNamesId.length; i++) {
            if (teacherId == teacherNamesId[i]) {
                teacherNamesId.splice(i, 1);
                $("#teacherNames_tr_" + teacherId).remove();
                break;
            }
        }
        if (teacherNamesId.length == 0) {
            initTea(2);
        }
    }
}
function setPaper() {
   if(paperNamesInfos != null && paperNamesInfos.length > 0){
		$("#paper_td").remove();
	    var html = new Array();
	    var lastTr = $("#stuPapers").find("tr").last();
	    for (var i = 0; i < paperNamesInfos.length; i++) {
	        var pStr = paperNamesInfos[i];
	        html.push("<tr align='center' valign='middle' id='paperNames_tr_");
	        html.push(paperNamesId1[i]);
	        html.push("'>");
	        html.push("<td width='25%'> ");
	        html.push(pStr[0]);
	        html.push("</td>");
	        html.push("<td width='25%'>");
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
	        html.push("<td width='10%'>");
	        html.push(pStr[2]);
	        html.push("</td>");
	        html.push("<td width='10%'>");
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
	
	
	    }
	    paperNamesId1.length = 0;
	    paperNamesInfos.length = 0;
	
	    lastTr.after(html.join(""));
   }
 }
function viewPaper(pId) {
    var url = basePath + "/paperManage/examPaperView.do?handle=view&paperId=" + pId;
    window.open(url);
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
        $(".teacherTd").remove();
        initTeacher();
        $("#teacherNames").hide();
        //判卷id数组清空
        teacherNamesId.length = 0;
    }
}
function initTeacher() {
    var lastTr = $("#teacherNames").find("tr").last();
    var html = [];

    html.push("<tr id='teacher_td' class='teacherTd' align='center'>");
    html.push("<td colspan='7'>");
    html.push("--请选择判卷人--");
    html.push("</td>");
    html.push("</tr>");
    lastTr.after(html.join(""));

}
function initPaper() {
    var lastTr = $("#stuPapers").find("tr").last();
    var html = [];
    html.push("<tr id='paper_td' align='center'>");
    html.push("<td colspan='7'>");
    html.push("请点击左侧选择试卷");
    html.push("</td>");
    html.push("</tr>");
    lastTr.after(html.join(""));
}
function showOrHiddenOrgNamesTr(obj){
	var show = $(obj).attr("show");
	//执行展开操作
	if(show==0){
		$("tr[id^=orgNames_tr_].hidden").addClass("show");
		$("tr[id^=orgNames_tr_].hidden").removeClass("hidden");
		$("tr[id^=orgNames_tr_].show").attr("hidden", false);
		$(obj).attr("show",1);
		$("#orgNames_td_show_or_hidden_html").html("点击收缩");
	}else{
		$("tr[id^=orgNames_tr_].show").addClass("hidden");
		$("tr[id^=orgNames_tr_].show").removeClass("show");
		$("tr[id^=orgNames_tr_].hidden").attr("hidden", true);
		$(obj).attr("show",0);
		$("#orgNames_td_show_or_hidden_html").html("点击展开");
	}
}
/**
 * 设置组织及组织下的考生
 * @param OrgIds
 * @returns
 */
function setOrg(OrgIds) {
	if(OrgIds != null && OrgIds !=""){
	    $("#org_td").remove();
	    $(".orgtrlast").remove();
	    var html = [];
	    var lastTr = $("#orgTable").find("tr").last();
	    var pStr = orgNamesInfo;
	    var hiddenTr = true;
	    var showOrgMaxLength = 5;
	    for (var i in pStr) {
	    	if(i == showOrgMaxLength && pStr.length > showOrgMaxLength && hiddenTr){
	    		hiddenTr = false;
	    	}
	        html.push("<tr align='center' class='orgtrlast"+(hiddenTr?"":" hidden")+ "' valign='middle' id='orgNames_tr_");
	        html.push(orgNamesId1[i][0]);
	        html.push("'>");
	        html.push("<td width='70%'> ");
	        html.push(pStr[i][0]);
	        html.push("</td>");
	        html.push("<td width = '30%'>");
	        html.push("<a href='javascript:delOrg(");
	        html.push(orgNamesId1[i][0] +",");
	        html.push("\""+pStr[i][0]+"\"");
	        html.push(")'>删除</a>");
	        html.push("</td>");
	        html.push("</tr>");
	    }
	    if(!hiddenTr){
	    	//添加展开按钮操作
			html.push("<tr align='center' class='trlast' valign='middle' id='orgNames_tr_show_or_hidden' >");
			html.push("<td colspan='7' style='cursor:pointer' align='center' onclick='showOrHiddenOrgNamesTr(this)' show='0' id='orgNames_td_show_or_hidden_html'>点击展开 </td>");
	    }
	    orgNamesId1.length = 0;
	    lastTr.after(html.join(""));
	    //取机构下的用户
	    /*
	     * change by IE
	    */
	    //userNamesInfo.splice(0,userNamesInfo.length);//清空数组 
		var params = "orgIds=" + OrgIds;
	    $.ajaxSetup({ async: false });
	    $.ajax({
	        type: "post",
	        url: basePath + "/examManage/examOrgStudent.do",
	        dataType: "json",
	        data: params,
	        success: function (data) {
	        	var userIds = [];
	        	var newUserIds = [];
	            if (data != null && data != "") {
	                $.each(data.list, function (i, item) {
	                	userIds.push(item.userId);
	                });
	            }
	            for (var i in userIds) {
	                var con = false;
	                for (var j = 0; j < userNamesId_back.length; j++) {
	                    if (userIds[i] == userNamesId_back[j]) {
	                        con = true;
	                        break;
	                    }
	                }
	                if (!con) {
	                    userNamesId.push(userIds[i]);
	                    newUserIds.push(userIds[i]);
	                }
	            }
//	            $("#userNames tr:gt(0)").remove();
	            if (userNamesId.length == 0) {
	                initTea(3);
	            } else {
	                $.ajax({
	                    type: "post",
	                    url: basePath + "/examManage/examOrgStudent.do",
	                    dataType: "json",
	                    data: params,
	                    success: function (data) {
	                        $.each(data.list, function (i, item) {
	                        	var flag = false;
	                        	for(var j in newUserIds)
	                        	{
	                        		if(newUserIds[j] == item.userId)
	                        		{
	                        			flag = true;
	                        			break;
	                        		}
	                        	}
	                        	if(flag)
	                        	{
	                        	     var temp2 = [];
	                                 userNamesId1.push(item.userId);
	                                 userNamesId_back.push(item.userId);
	                                 temp2.push(item.realName);
	                                 temp2.push(item.accountName);
	                                 temp2.push(item.workUnit);
	                                 temp2.push(item.deptName);
	                                 temp2.push(item.userType);
	                                 temp2.push(item.mobilPhone);
	                                 temp2.push(item.userId);
	                                 userNamesInfo.push(temp2);                                 
	                        	} 
	                        });
	                        userNamesId=removeDuplicatedItem(userNamesId);
	                        userNamesInfo=removeDuplicatedItem(userNamesInfo);
	                        setUser();
	                    }
	                });
	            }
	        }
	    });
	    //如果组织为空，添加提示域
	    if (orgNamesId.length == 0) {
	        initTea(4);
	    }
	    $.ajaxSetup({ async: true });
	    $("#jgTd").attr("rowSpan",$("#orgTable").find("tr").length+2);
	    $("#ksTd").attr("rowSpan",$("#userNames").find("tr").length+2);
	}
}
/**
 * 删除多个组织
 * @param orgId
 * @returns
 */
function delAll(orgId) {
    for (var i = 0; i < orgNamesId.length; i++) {
        if (orgId == orgNamesId[i]) {
            orgNamesId.splice(i, 1);
            $("#orgNames_tr_" + orgId).remove();
            break;
        }
    }

    $.ajax({
        type: "post",
        url: basePath + "/manage/exam/getUserByOrgId.do",
        dataType: "json",
        data: "orgId=" + orgId,
        success: function (data) {
            $.each(data, function (i, item) {
                for (var i = 0; i < userNamesId.length; i++) {
                    if (item.id == userNamesId[i]) {
                        userNamesId.splice(i, 1);
                        $("#userNames_tr_" + item.id).remove();
                        break;
                    }
                }

                for (var j = 0; j < userNamesId_back.length; j++) {
                    if (item.id == userNamesId_back[j]) {
                        userNamesId_back.splice(j, 1);
                        break;
                    }
                }
            });
            if (userNamesId.length == 0) {
                initTea(3);
            }
        }
    });

    if (orgNamesId.length == 0) {
        initTea(4);
    }
}
/**
 * 删除一个机构
 * @param orgId
 * @param orgName
 * @returns
 */
function delOrg(orgId,orgName) {
	var oName = "";
	var oId = "";
    if (confirm("确认删除吗？")) {
    	var params = "orgIds=" + orgId;
        for (var i = 0; i < orgNamesId.length; i++) {
            if (orgId != orgNamesId[i]) {
                oId += orgNamesId[i] + ",";
                oName += orgNamesInfo[i][0] + ",";
            }
            else
            {
            	orgNamesId.splice(i, 1);
            	orgNamesInfo.splice(i, 1);
            	$("#orgNames_tr_" + orgId).remove();
            }
        }
        
     
        if(oId[oId.length-1] == ",")
		{
			oId = oId.slice(0, oId.length-1);
			oName = oName.slice(0, oName.length-1);
		}
	
        $('#orgNames').val(oName);
        $('#orgNamesId').val(oId);
        $.ajax({
            type: "post",
            url: basePath + "/examManage/examOrgStudent.do",
            dataType: "json",
            data: params,
            success: function (data) {
                $.each(data.list, function (i, item) {
                    for (var i = 0; i < userNamesId.length; i++) {
                        if (item.userId == userNamesId[i]) {
                            userNamesId.splice(i, 1);
                            $("#userNames_tr_" + item.userId).remove();
                            break;
                        }
                    }

                    for (var j = 0; j < userNamesId_back.length; j++) {
                        if (item.userId == userNamesId_back[j]) {
                            userNamesId_back.splice(j, 1);
                            break;
                        }
                    }
                });
                if (userNamesId.length == 0) {
                    initTea(3);
                }
            }
        });
        if (orgNamesId.length == 0) {
            initTea(4);
        }
    }
}
/**
 * 数组率重 
 * add by whq 
 * @param ar
 * @returns
 */
function removeDuplicatedItem(ar) {
    var ret = [];

    for (var i = 0, j = ar.length; i < j; i++) {
        if (ret.indexOf(ar[i]) === -1) {
            ret.push(ar[i]);
        }
    }
    bubbleSort(ret)
    return ret;
}
/**
 * 数组排序 
 * add by whq 
 * @param ar
 * @returns
 */
function bubbleSort(array){
    /*给每个未确定的位置做循环*/
    for(var unfix=array.length-1; unfix>0; unfix--){
      /*给进度做个记录，比到未确定位置*/
      for(var i=0; i<unfix;i++){
        if(array[i]>array[i+1]){
          var temp = array[i];
          array.splice(i,1,array[i+1]);
          array.splice(i+1,1,temp);
        }
      }
    }
  }