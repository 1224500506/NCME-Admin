<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/new_page/top.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="${ctx}/new_page/css/reset.css" />
<link rel="stylesheet" href="${ctx}/new_page/css/index.css" />
<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/new_page/js/jquery.js"></script>
</head>
	<body class = "bjs" onkeydown="entersearch();">
		<div class="center">
		<form action="${ctx}/caseManage/caseManage.do?type=${type}" method="post" name="searchFrm" id="searchFrm">
		<div class="tk_center01" style="min-height:120px;">

			<div class="tk_zuo">
				<div class="tik_xuanze">
					<div class="mt10 fl tk_tixing">
						<em class="fl">姓名：</em>
						<input type="text" name="patientName" class="fl tik_select" value="${patientName}"/>
					</div>
					<div class="mt10 ml20 fl tk_tixing">
						<span class="fl">就诊时间：</span>
						<input type="text" class="fl tik_select" name="examTime" onClick="WdatePicker()" readonly value="${examTime}">
					</div>
					<div class="mt10 ml10 fl tk_tixing">
						<span class="fl">诊断：</span>
						<input type="text" name="examination" class="fl tik_select" value="${examination}"/>
					</div>
					<div class="mt10 ml5 fl tk_tixing">
						<span class="fl">状态：</span>
						<div class="fl tik_select" >
							<div class="tik_position_2">
								<b class="ml5" id="sel_state"></b>
								<p class="tik_position01"><i class="tk_bjt10"></i></p>
							</div>
							<ul class="fl tk_list_1 tk_list" style="display:none;">
							<select id="state" name="state" style="display:none;">
								<option value="-1">请选择</option>
								<c:if test="${type==1}">
									<option value="0"<c:if test="${state==0}"> selected</c:if>>未上传</option>
								</c:if>
								<c:if test="${type!=3}">
								<option value="1"<c:if test="${state==1}"> selected</c:if>>未审核</option>
								</c:if>
								<c:if test="${type!=2}">
								<option value="2"<c:if test="${state==2}"> selected</c:if>>不合格</option>
								<option value="3"<c:if test="${state==3}"> selected</c:if>>合格</option>
								<option value="4"<c:if test="${state==4}"> selected</c:if>>禁用</option>
								</c:if>
							</select>
								<li>请选择</li>
								<c:if test="${type==1}">
									<li>未上传</li>
								</c:if>
								<c:if test="${type!=3}">
								<li>未审核</li>
								</c:if>
								<c:if test="${type!=2}">
								<li>不合格</li>
								<li>合格</li>
								<li>禁用</li>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
				<div class="clear"></div>
				<div class="tik_xuanze">
					<div class="mt10 fl tk_tixing">
						<em class="fl">学科：</em>
						<input type="hidden" class="fl tik_select" id="propIds" name="propIds" value="${propIds}"/>
						<input type="hidden" class="fl tik_select" id="propNames01" name="propNames" value="${propNames}"/>
						<div class="fl tik_select01 takuang_xk" id="propNames">${propNames}</div>
					<input type="hidden" name="course" id="course">
					</div>
					<div class="mt10 ml20 fl tk_tixing">
						<span class="fl">ICD10：</span>
						<input type="hidden" class="fl tik_select" id="icdIds" name="icdIds" value="${icdIds}"/>
						<input type="hidden" class="fl tik_select" id="icdNames" name="icdNames" value="${icdNames}"/>
						<div class="fl tik_select01 takuang_xk" id="icdNames01">${icdNames}</div>
<%-- 						<input type="text"  id="ICD" name="ICD" class="fl tik_select" readonly value="${ICD}"/>
 --%>					</div>
					<div class="mt10 ml10 fl tk_tixing">
						<span class="fl">主题：</span>
						<input type="hidden" class="fl tik_select" id="zutiIds" name="zutiIds" value="${zutiIds}"/>
						<input type="hidden" class="fl tik_select" id="zutiNames01" name="zutiNames" value="${zutiNames}"/>
						<div class="fl tik_select02 takuang_xk01" id="zutiNames">${zutiNames}</div>
					</div>
				</div>
				<div class="clear"></div>
				
			</div>
			<div class="clear"></div>
			<div class="mt10 tk_you shiti" style="">
				<a href="javascript:search();" class="fl tk_chaxun">查询</a>
				<a href="javascript:void(0);" class="fl ml30 tk_chaxun01">清空</a>
			</div>
			<div class="clear"></div>
		</div>
		</form>
		<div class="clear"></div>
	</div>
	<div class="tkbjs"></div>
	<!--  -->
	<div class="center none" style="">
	<div class="tk_center01">
		<div class="mt10 kit1_tiaojia">
			<div class="fr cas_anniu">
				<c:if test="${type == 1}">
					<a href="${ctx}/new_page/case/caseImport.jsp" class="fl" style="width:95px;margin-right:15px;">添加病例</a>
					<a href="javascript:void(0);" onclick="exportCase();" class="fl" style="width:95px;margin-right:15px;">导出病例</a>
					<a href="javascript:void(0);" onclick="daliangdel();" class="fl" style="width:95px;">批量删除</a>
				</c:if>
				<c:if test="${type == 2 || type == 3}">
					<a href="javascript:void(0);" onclick="setCaseStates(3);" class="fl" style="width:95px;margin-right:15px;">合格</a>
					<a href="javascript:void(0);" onclick="setCaseStates(2);" class="fl" style="width:95px;margin-right:15px;">不合格</a>
					<a href="javascript:void(0);" onclick="setCaseStates(4);" class="fl" style="width:95px;">禁用</a>
				</c:if>
			</div>
		</div>
		<c:if test="${type != 1}">
			<ul class="cas1_xuanxing">
					<li class="<c:if test="${type==3}">action</c:if>" onclick="javascript:location.href='${ctx}/caseManage/caseManage.do?type=3'">已审核</li>
					<li class="<c:if test="${type==2}">action</c:if>" onclick="javascript:location.href='${ctx}/caseManage/caseManage.do?type=2'">未审核</li>
			</ul>
		</c:if>
		<div class="clear"></div>
			<form name = "listfrm" id="listfrm" method = "post">
			<display:table name="casePatientList" requestURI="${ctx}/caseManage/caseManage.do" id="p" class="mt10 cas_table" size="${totalCount}" pagesize="20"  sort="list" defaultsort="1">
				<display:column title="<input type='checkbox' class='chkall'/>" style="width:3%;"><input type='checkbox' class='chk' name = "caseId" value="${p.caseObject.id}"/></display:column>
				<display:column title="序号" style="width:4%;">${p_rowNum}</display:column>
				<display:column title="学科" style="width:8%;" property="propNames"></display:column>
				<display:column title="姓名" sortable="true" style="width:5%;" sortProperty="p.pname" >${p.patientObject.PName}</display:column>
				<display:column title="性别" sortable="true" style="width:5%;" sortProperty="p.sex">
					<c:if test = "${p.patientObject.sex == 0}">男</c:if>
					<c:if test = "${p.patientObject.sex == 1}">女</c:if>
				</display:column>
 				<display:column title="年龄" style="width:5%;"> ${p.patientObject.pAge}</display:column>
 				<display:column title="诊断" style="width:10%;" maxLength="27">${p.patientDiagnosisObject}</display:column>
 				<display:column title="基本病情" style="width:16%;"  maxLength="40">${p.patientObject.diagName}</display:column>
 				<display:column title="状态" style="width:7%;" sortable="true" sortProperty="c.state">
 						<c:if test = "${p.caseObject.state == 0}">未上传</c:if>
 						<c:if test = "${p.caseObject.state == 1}">未审核</c:if>
 						<c:if test = "${p.caseObject.state == 2}">不合格</c:if>
 						<c:if test = "${p.caseObject.state == 3}">合格</c:if>
 						<c:if test = "${p.caseObject.state == 4}">禁用</c:if>
 						<c:if test = "${p.caseObject.state == 5}">删除</c:if>
 						<input type = "hidden" id = "stateObj_${p.caseObject.id}" name = "stateobj" value= "${p.caseObject.state}"/>	
 				</display:column>
 				<display:column title="审核意见" style="width:10%;">${p.caseObject.deliOpinion}</display:column>
 				<display:column title="审核人" style="width:6%;" sortable="true" sortProperty="c.deli_man">${p.caseObject.deliMan}</display:column>
 				<display:column title="操作" style="width:12%;">
					
					<a href="javascript:viewCase(${p.caseObject.id})" >查看</a>
					<c:if test = "${p.caseObject.state == 1 or p.caseObject.state ==0 or p.caseObject.state ==2 }">
						<a href="javascript:editCase(${p.caseObject.id})" >修改</a>					
					</c:if>
					<c:if test="${p.caseObject.state==4}"><a href="javascript:setCaseState(${p.caseObject.id},0)" >启用</a></c:if>
					<c:if test="${p.caseObject.state!=4}"><a href="javascript:setCaseState(${p.caseObject.id},4)" >禁用</a></c:if>
					<c:if test = "${p.caseObject.state != 3}">
						<a href="javascript:deleteCase(${p.caseObject.id}, ${p.caseObject.state})" >删除</a>
					</c:if>
	
 				</display:column>
			</display:table>
		</form>
		<div class="clear"></div> 
	</div>
	</div>

<div class="clear"></div>

<script type="text/javascript">
var ajaxurl;

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

		$('.tik_select').click(function(){
			$(".tk_list").css("display","none");
			$(this).find('ul').show();
			return false;
		});
		$('.tk_list li').click(function(){
			var str=$(this).text();
			$(this).parent().parent().find('div').find('b').text(str);
			$(this).parent().find('option').prop('selected', '');
			$(this).parent().find('option').eq($(this).index()-1).prop('selected', 'selected');
			$('.tk_list').slideUp(50);
		});
		
		$('.tk_list option:selected').each(function(){
			var str=$(this).text();
			$(this).parent().parent().parent().find('b').text(str);
		});
		
		$(document).click(function(){
			$('.tk_list').hide('fast');
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
		
		//check all
		$('.chkall').click(function(){
			if ($(this).attr('checked') == 'checked')
				$('.chk').attr('checked' , 'checked');
			else
				$('.chk').removeAttr('checked');
		});
		

		$('#propNames').click(function(){
			initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#propIds'), $('#propNames'));
			$('.att_make01').show();
		});		
		$('#icdNames01').click(function(){
			initPropList("请输入ICD10名称并选择","${ctx}/propManage/icdList.do", 10, 0, 1, 0, $('#icdIds'), $('#icdNames01'));
			$('.att_make01').show();
		});		
		
		$('#zutiNames').click(function(){
			initPropList("请输入主题名称并选择","${ctx}/propManage/getPropListAjax.do", 7, 0, 1, 0, $('#zutiIds'), $('#zutiNames'));
			$('.att_make01').show();
		});
		
		//清空
		$('.tk_chaxun01').click(function(){
			$('#searchFrm').find('input').val('');
			$('#propNames').text('');
			$('#zutiNames').text('');
			$('#icdNames01').text('');
			$('.tik_select').find('b').text("请选择");
			$('#state').val('-1');
		});
		
});
function deleteCase(caseId, state){
	if (state != 0 && state != 1 && state != 4){
		alert ("无法删除！");
		return;
	}
	if (!confirm("确定删除?"))
	{
		return;
	}
	var url = "${ctx}/caseManage/deleteCase.do";
	if(caseId != null && caseId.val !=""){
		var params = "caseId="+caseId;  
		$.ajax({
					type: 'POST',
					url: url,
					data : params,
					dataType: 'text',
					success : function (newId){
						if(newId == "success")
						{
							alert('病例删除成功！');
						}
						else
						{
							alert('病例删除失败！');
						}
						document.location.href = document.location.href.split("#")[0];
					},
					error: function(){
					   	alert('病例删除失败！');
						document.location.href = document.location.href.split("#")[0];
			}
		});
	};
};
function daliangdel(){		
			if ($('.chk:checked').length<=0){
				alert ("没有选择病例。");
				return;
			}
			
			if(!confirm("您确定要删除吗？"))return;
			var url = '${ctx}/caseManage/deleteCase.do?mode=batchdel';

			var array = new Array();var dels=0;
			$('.chk:checked').each(function(key, val){
				array.push($(val).val());
				dels++;
			});
			var params = 'ids='+array.toString();
			
			$.ajax({
				url: url,
				type: 'POST',
				data: params,
				dataType: 'text',
				success: function(result){
					if(result != '0'){
					   if(eval(dels-result)!=0){
						alert(dels+'个病例中'+result+'个病例删除成功，其他'+ eval(dels - result)+'个病例因为在别的地方使用无法删除！');
						document.location.href = document.location.href.split("#")[0];
						}
					else{
					    alert(result+'个病例删除成功!');
						document.location.href = document.location.href.split("#")[0];
						}
					
					}else{
						alert('有相关信息无法删除！');
					};
				}
			});
			
	
	/*var url = "${ctx}/caseManage/deleteCase.do";
	
		params = "caseId="+caseId;  
		$.ajax({
					type: 'POST',
					url: url,
					data : params,
					dataType: 'text',
					success : function (newId){
						if(newId == "success")
						{
							alert('删除成功！');
						}
						else
						{
							alert('删除失败！');
						}
						document.location.href = document.location.href.split("#")[0];
					},
					error: function(){
					   	alert('删除失败！');
						document.location.href = document.location.href.split("#")[0];
			}
		}); */
} 
function setCaseStates(caseType){
			var opinion = ""; 
			if (!checkCaseIds()) {
	            alert("请选择要审核的病例!");
	            return false;
	        }
	        if(caseType == 3){
	        	if(!confirm("您确定要审核通过吗?"))
	        	{
	        		return false;
	        	}
	        }
	        else if(caseType == 2){
	        	opinion = prompt("请添加审核意见!","");
	        	if (opinion == null) return false;
 	        	if(opinion == "" || opinion == null) {
	        		alert("请添加审核意见!");
	        		return false;
	        	} else {
	    			if(opinion != '' && !/^[^\<>~!`@#$%&*(),.?{};'"+/|]*$/.test(opinion)){  
	    				alert("审核意见只支持汉字、英文字母和数字，请输入正确的内容！");
	    				return false;
	    			} 
	         	}
	        }
	       /*  var flag = true;
	        if(caseType == 2 || caseType == 3)
	        {
	        	$('.chk').each(function(key,val){
	        		if($(this).prop("checked") == true)
	        		{
	        			var id = $(this).val(); 
	        			var stateObj = "#stateObj_" + id;
	        			if($(stateObj).val() == "4")
	        			{
	        				alert("无法删除禁用状态的病例!");
	        				flag = false;
	        				return false;
	        			} 
	        		}
	        	});
	        }
	        if(flag == true)
	        {
	        	document.getElementById("listfrm").action = "${ctx}/caseManage/setCaseListState.do?type=${type}"+"&caseType=" + caseType +"&opinion=" + opinion;
	        	document.getElementById("listfrm").submit();
	        } */
	        
	        var count = 0;
	        var idsArr = new Array();
	        $('.chk:checked').each(function(){
	        	idsArr.push($(this).val());
	        	count++;
	        });
	        var url = "${ctx}/caseManage/setCaseListState.do";
			var params = "caseId="+idsArr.toString();
			params+= "&caseType="+caseType;
			params+= "&opinion=" + opinion; 
			$.ajax({
				type: 'POST',
				url: url,
				data : params,
				dataType: 'text',
				success : function (b){
					if(b != "")
					{
						if (eval(b)!=count)
							alert(count+'个病例中'+b+'个病例审核成功，其他'+eval(count-b)+'个病例因为状态病例不能审核！');
						else
							alert(count+'个病例审核成功');
				      	window.location.reload(true);
					}
					//window.location.reload(true);
				},
				error: function(){
				   	alert('操作失败');
					//document.location.href = document.location.href.split("#")[0] + "&state=" + $("#state").val();
				}
			});
}
function setCaseState(caseId, state){
	var url = "${ctx}/caseManage/setCaseState.do";
	if(caseId != null && caseId.val !=""){
		var params = "caseId="+caseId;
		params+= "&state="+state; 
		if(state==4){
			if(!confirm("您确定要禁用吗?")){
				return false;
			}
		}else{
			if(!confirm("您确定要启用吗?")){
				return false;
			}
		}
		$.ajax({
			type: 'POST',
			url: url,
			data : params,
			dataType: 'text',
			success : function (newId){
				if(newId == "success")
				{
					alert('操作成功！');
					window.location.reload(true);
				}
				else
				{
					alert('不可能的操作！');
				}
				//window.location.reload(true);
			},
			error: function(){
			   	alert('操作失败');
				//document.location.href = document.location.href.split("#")[0] + "&state=" + $("#state").val();
			}
		});
	};
}
function exportCase(){
	 if (!checkCaseIds()) {
            alert("请选择要导出的病例!");
            return false;
        }
        document.getElementById("listfrm").action = "${ctx}/caseManage/downLoadCaseListFile.do";
        document.getElementById("listfrm").submit();
}
function checkCaseIds() {
        var checked = false;
        $("input[name='caseId']").each(function () {
            if ($(this).is(':checked')) {
                checked = true;
                return false;
            }
        });
        return checked;
}

function viewCase(id){
	    document.getElementById("listfrm").action = "${ctx}/caseManage/caseEdit1.do?type=${type}&caseId=" + id +"&mode=" + ${type};
        document.getElementById("listfrm").submit();
}

function editCase(id){
	window.location.href="${ctx}/caseManage/caseEdit1.do?type=1&caseId="+id+"&mode=edit";
}

if($('#sel_state').text() == "")  $('#sel_state').text("请选择");
function search() {

	$('#propNames01').val($('#propNames').text());
	$('#zutiNames01').val($('#zutiNames').text());
	$('#icdNames').val($('#icdNames01').text());
	
	$('#searchFrm').submit();
}

function entersearch(){
   var event = window.event || arguments.callee.caller.arguments[0];
   if (event.keyCode == 13) {
       search();
   }
}
</script>
</body>
</html> 