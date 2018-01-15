<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<%@include file="/commons/taglibs.jsp"%>
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/default090909.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
		<link rel="stylesheet" href="${ctx}/js/Validform_v5.3/css/valid.css" type="text/css" media="all" />

		<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/Validform_v5.3.js"></script>
        <script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
  <style type="text/css">
	.butn3 {
			_width: 150px;
			_height: 25px;
			padding: 2px 20px 1px 4px;
			padding: 2px 20px 0 4px;
			_padding: 0 20px 0 2px;
			border: 1px solid #f2eff6;
			background: url(${ctx}/images/lay_ico.gif) no-repeat;
			background-position: right -47px;
			background-position: right -46px;
	}
  </style>        
	</head>
	<body>
		<form class="fm1" id="fm1" name="fm1" action="${ctx}/exam/examSave.do" method="post">
			<input type="hidden" id="curTypeId" name="curTypeId" value="${param.curTypeId}" />
			<input type="hidden" id="id" name="id" value="${ExamExamination.id}" />
			<input type="hidden" id="cutScreenNum" name="cutScreenNum" value="1" />
			<input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="${sessionScope['org.apache.struts.action.TOKEN']}" />
			<table border="0" cellspacing="1" cellpadding="0" width="100%" class="gridtable">
				<tr>
					<td align="center" colspan="3" class="row_tip" height="25">
						考试信息
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25" >
						&nbsp;&nbsp;所属目录：
					</td>
					<td width="50%">
						<c:set var="ExamExaminTypeSize" value="${fn:length(ExamTypeList) - 1}"></c:set>
					    <c:forEach items="${ExamTypeList}" var="list" varStatus="status">
					        ${list.name}
					        <c:if test="${ExamExaminTypeSize != status.index }">
					        ->
					        </c:if>
					    </c:forEach>
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>考试名称：
					</td>
					<td width="50%">
						<input type="text" id="name" name="name" class="editInput" maxlength="100" size="25" value="${ExamExamination.name}" datatype="*1-100" nullmsg="请输入考试名称!" />
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>所属站点：
					</td>
					<td width="50%">
						<select id="siteId" name="siteId" datatype="*" nullmsg="请选择站点!">
							<option value="">请选择站点</option>
							<c:forEach items="${siteList }" var="site">
								<option value="${site.id }" <c:if test="${ExamExamination.siteId eq site.id}">selected</c:if>>${site.aliasName }--(${site.domainName })</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>考试类别：
					</td>
					<td width="50%">
						<input type="radio" id="examType" name="examType" value="1" checked>考试
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>

				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>练习次数:
					</td>
					<td width="50%">
						<input type="text" id="examNum" name="examNum" class="editInput" value="1" maxlength="4" size="25" value="1" readonly datatype="n1-4" nullmsg="请输入练习次数!" />
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>考试时长（分钟）：
					</td>
					<td width="50%">
						<input type="text" id="examTimes" name="examTimes" class="editInput" maxlength="4" size="25" value="<fmt:formatNumber value="${ExamExamination.exam_times / 1000 }" pattern="0"/>" datatype="n1-4" nullmsg="请输入考试时长!" />
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>考试时间：
					</td>
					<td width="50%">
						<input type="radio" id="examTime" name="examTime" value="1" checked>随时
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>开始时间: 
					</td>
					<td width="50%">
						<input type="text" name="startTime" id="startTime" value="${fn:substring(ExamExamination.create_time,0,19)}"  onClick="setStartTime()" readonly="readonly" size="28" datatype="*1-20" errormsg="请输入结束时间！" nullmsg="请输入结束时间!">
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>结束时间: 
					</td>
					<td width="50%">
						<input type="text" name="endTime" id="endTime" value="${fn:substring(ExamExamination.end_time,0,19)}" onClick="setEndTime()" readonly="readonly" size="28" datatype="*1-20" errormsg="请输入结束时间！" nullmsg="请输入结束时间!">
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>通过标准:
					</td>
					<td width="50%">
						<input type="radio" id="passCondition" name="passCondition" value="1" <c:if test="${ExamExamination.pass_condition eq 1}">checked</c:if> <c:if test="${ExamExamination eq null}">checked</c:if>>分数
						<input type="radio" id="passCondition" name="passCondition" value="2" <c:if test="${ExamExamination.pass_condition eq 2}">checked</c:if>>得分率
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>通过值:
					</td>
					<td width="50%">
						<input type="text" id="passValue" name="passValue" class="editInput" maxlength="3" size="25" 
						value="<fmt:formatNumber value="${ExamExamination.pass_value}" pattern="0"/>" 
						datatype="passValue" nullmsg="请输入通过值!" /> <font color="red">注：如果60分,输入6;如果80分输入8</font>
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>考生查看测评报告:
					</td>
					<td width="50%">
						<input type="radio" id="isnotSeeTestReport" name="isnotSeeTestReport" value="1" <c:if test="${ExamExamination.isnot_see_test_report eq 1}">checked</c:if> <c:if test="${ExamExamination eq null}">checked</c:if>>允许
						<input type="radio" id="isnotSeeTestReport" name="isnotSeeTestReport" value="0" <c:if test="${ExamExamination.isnot_see_test_report eq 0}">checked</c:if>>不允许
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>试卷显示方式:
					</td>
					<td width="50%">
						<input type="radio" id="paperDisplayMode" name="paperDisplayMode" value="1" <c:if test="${ExamExamination.paper_display_mode eq 1}">checked</c:if>>单向
						<input type="radio" id="paperDisplayMode" name="paperDisplayMode" value="2" <c:if test="${ExamExamination.paper_display_mode eq 2}">checked</c:if><c:if test="${ExamExamination eq null}">checked</c:if>>双向
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>考试状态: 
					</td>
					<td width="50%">
						<input type="radio" id="state" name="state" value="1" <c:if test="${ExamExamination.state eq 1}">checked</c:if> <c:if test="${ExamExamination eq null}">checked</c:if>>正常
						<input type="radio" id="state" name="state" value="2" <c:if test="${ExamExamination.state eq 2}">checked</c:if>>禁用
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						是否允许切屏:
					</td>
					<td width="50%">
						<input type="radio" id="is_cut_screen" name="is_cut_screen" value="0" <c:if test="${ExamExamination.is_cut_screen eq 0}">checked</c:if> <c:if test="${ExamExamination eq null}">checked</c:if>
						 >允许
						<input type="radio" id="is_cut_screen" name="is_cut_screen" value="1" <c:if test="${ExamExamination.is_cut_screen eq 1}">checked</c:if> 
						 >限制切屏
						&nbsp;&nbsp;&nbsp;<span >
						<font color="red">切屏次数：</font><input id="cut_screen_num" name="cut_screen_num" type="text" value="${ExamExamination.cut_screen_num }" /> </span>注：如果允许,则不需要填写
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						&nbsp;&nbsp;考试注意事项：
					</td>
					<td>
						<textarea rows="5" cols="50" id="remark" name="remark" ignore="ignore" datatype="*1-100" errormsg="备注最多100个字符！">${ExamExamination.remark}</textarea>
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" height="25">
						<font class="red_star">*</font>选择试卷：
					</td>
					<td>
					  <div name="paperName" id="paperName" style="display:inline-block;float:left;">
					    <c:set var="tempString" value="," />
                        <c:forEach items="${ExamExamination.examinPaperList}" var="list" varStatus="status">
                          <c:set var="paperIds" value="${paperIds}${list.paper_id}${tempString}"/>
                          <span id="paper_${list.paper_id}">
                          <c:if test="${status.index >0 }"><br/></c:if>
                          <a href="javascript:void(0);" onclick="deletePaper(${list.paper_id});" class='butn3'>${list.paper_name}</a></span>
                        </c:forEach>
                      </div>
                      <div style="display:inline-block;float:right;">
                          <input type="button" class="btn_03s" value="选择" onclick="javascript:openPaperWindow()">	
                      </div>
                      <input type="hidden" id="paperIds" name="paperIds" value="${paperIds}" datatype="*1-200" nullmsg="请选择试卷信息!">
					</td>
					<td>
					  <div class="Validform_checktip"></div>
					</td>
				</tr>
				
				<tr>
					<td colspan="6" align="center" class="row_tip">
						<input type="submit" class="btn_03s" value="保 存" />
						<input type="button" class="btn_03s" value="返 回" onclick="history.go(-1)" />
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">
			$(function() {
			  	$(".fm1").Validform({
		          datatype:{//自定义datatype
			        "passValue":function(gets,obj,curform,datatype){
			            if(gets.length == 0){
			              return "请输入通过值!";
			            }
			            var reg =  /^\d+$/;

			            if(!reg.test(gets)){
			              return "请输入数字类型的通过值!";
			            }

			            if($("input:checked[name='passCondition']").val() == 2){
			              if(gets > 100){
			                return "得分通过率不能大于100!";
			              }
			            }

			            return true;
					}
		         }
	         });
		    });
		    function openPaperWindow(){
		        var url = "${ctx}/exam/examPaperIndex.do";
        	    openwindow(url,"paperWindow",1200,700);
		    }
		    function openwindow(url,name,iWidth,iHeight){
		    	var url;     //转向网页的地址;
		    	var name;    //网页名称，可为空;
		    	var iWidth;  //弹出窗口的宽度;
		    	var iHeight; //弹出窗口的高度;
		    	var iTop = (window.screen.availHeight-30-iHeight)/2;       //获得窗口的垂直位置;
		    	var iLeft = (window.screen.availWidth-10-iWidth)/2;        //获得窗口的水平位置;
		    	window.open(url,name,'height='+iHeight+',innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=yes,resizeable=no,location=no,status=no');
		    }
		    function addPaper(paperId,paperName){
		        var paperIds = $("input[name ='paperIds']").val();

		        paperIds += paperId + ",";

		        $("input[name ='paperIds']").val(paperIds);

		        var htmlStr = $("div[name ='paperName']").html();

		        if(htmlStr != ""){
		          htmlStr += "<span id='paper_"+paperId+"'><br/><a href='javascript:void(0);' onclick='deletePaper("+paperId+");' class='butn3'>"+paperName+"</a></span>";
		        }else{
		          htmlStr += "<span id='paper_"+paperId+"'><a href='javascript:void(0);' onclick='deletePaper("+paperId+");' class='butn3'>"+paperName+"</a></span>";
		        }

		        $("div[name ='paperName']").html(htmlStr);
		    }
		    function deletePaper(id){
		      var paperIds = $("input[name ='paperIds']").val();

		      paperIds = paperIds.replace(id+",","");

		      $("input[name ='paperIds']").val(paperIds);

		      $("#paper_"+id).remove();
		    }
		    function getPaperIds(){
		      return $("input[name ='paperIds']").val();
		    }
			function setStartTime(){
				if($("#examTimes").val() == ''){
					alert("请先填写考试时长!");
					$("#examTimes").focus();
					return;
				}
				if($("input:checked[name='examTime']").val() == '1'){
					WdatePicker({
						dateFmt:'yyyy-MM-dd HH:mm:ss',
						minDate:'%y-%M-%d 00:00:00',
						maxDate:'#F{$dp.$D(\'endTime\',{m:-' + $("#examTimes").val() + '});}'})
				}else{
					WdatePicker({
						onpicked:function(){
							$('#endTime').val(setStartOrEndTime($dp.cal.getDateStr(),1));
						},
						dateFmt:'yyyy-MM-dd HH:mm:ss',
						minDate:'%y-%M-%d 00:00:00',
						maxDate:'#F{$dp.$D(\'endTime\',{m:-' + $("#examTimes").val() + '});}'
					});
				}
			}
			function setEndTime(){
				if($("#examTimes").val() == ''){
					alert("请先填写考试时长!");
					$("#examTimes").focus();
					return;
				}
				if($("input:checked[name='examTime']").val() == 1){
					WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\',{m:' + $("#examTimes").val() + '})||\'%y-%M-%d 00:00:00\'}'})
				}else{
					WdatePicker({onpicked:function(){$dp.$('startTime').value=setStartOrEndTime($dp.cal.getDateStr(),2);},dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\',{m:' + $("#examTimes").val() + '})||\'%y-%M-%d 00:00:00\'}'})
				}
			}
			
		</script>
	</body>
</html>