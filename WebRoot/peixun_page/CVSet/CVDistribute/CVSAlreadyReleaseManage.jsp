<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/commons/taglibs.jsp"%>
		<script type="text/javascript" src="${ctx}/js/My97Date/WdatePicker.js"></script>
		<title>培训后台</title>
		
		<style type="text/css">
			.cas1_xuanxing {
		    height: 40px;
		    width: 100%;
		    border-bottom: 1px solid #dddddd;
		    margin-bottom: 25px;
			}
			ul {
			    list-style: none;
			    margin: 0;
			    padding: 0;
			    border: 0;
			    font-size: 100%;
			    font: inherit;
			    vertical-align: middle;
			    text-decoration: none;
			}
			ul {
			    display: block;
			    list-style-type: disc;
			    -webkit-margin-before: 1em;
			    -webkit-margin-after: 1em;
			    -webkit-margin-start: 0px;
			    -webkit-margin-end: 0px;
			    -webkit-padding-start: 40px;
			}
			
			.cas1_xuanxing li.action {
			    color: #f48823;
			    font-size: 16px;
			    border-bottom: 2px solid #f48823;
			    font-weight: 600;
			}
			.cas1_xuanxing li {
			    float: left;
			    cursor: pointer;
			    line-height: 40px;
			    font-size: 14px;
			    color: #333;
			    width: 100px;
			    text-align: center;
			}
		</style>
	</head>
<%@include file="/peixun_page/top.jsp"%>

<body>
<!-- 查询条件 -->
<form  id = "queryForm" action="${ctx}/CVSet/CVDistribute.do?method=releaseList&flag=1" method="post">
<div class="center" style="width:98%;">
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl">
			<span style="width: 9em;text-align:right;margin-left:42px;">学科：</span>
			<input type="hidden" class="fl tik_select01" name="groupIds" id="groupIds" value="${groupIds}"/>
			<input type="hidden" class="fl tik_select01" name="groupNames" id="groupNames" value="${groupNames}"/>
			<div class="duouan" id="groupNames01" style="text-align:middle;line-height:25px;height:23px;width:198px;">${groupNames}</div>
		</p>
		<p class="fl">
			<span style="width: 6em;text-align:right;">项目名称：</span>
			<input type="text" name="name" id="name" value="${name}"/>
		</p>
		<p class="fl">
			<span style="width: 7em;text-align:right;">项目负责人：</span>
			<input type="text" name="maker" id="maker" value="${maker}"/>
		</p>
		<p class="fl">
			<span style="width: 6em;text-align:right;">授课形式：</span>
		</p>
		<div class="fl select">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display:none;">
				<select name="forma" id="forma" style="display:none;">
					<option value="0">全部</option>
					<option value="1" <c:if test="${forma == 1}">selected</c:if>>远程</option>
					<option value="3" <c:if test="${forma == 3}">selected</c:if>>面授</option>
				</select>
				<li>全部</li>
				<li>远程</li>
				<li>面授</li>
			</ul>
		</div>
		<p class="fl">
			<span style="width: 6em;text-align:right;">项目状态：</span>
		</p>
		<div class="fl select" style="margin-right:20px;">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display:none;">
				<select name="status" id="status" style="display:none;">
					<option value="-1">全部</option>
					<option value="3" <c:if test="${status == 3}">selected</c:if>>审核合格</option>
					<option value="5" <c:if test="${status == 5}">selected</c:if>>已发布</option>
					<option value="6" <c:if test="${status == 6}">selected</c:if>>已下线</option>
				</select>
				<li>全部</li>
				<li>审核合格</li>
				<li>已发布</li>
				<li>已下线</li>
			</ul>
		</div>
		<div class="clear" style="margin-bottom:10px;"></div>
		<p class="fl">
			<span style="width: 12em;text-align:right;">归属机构：</span>
			<input type="text" name="orgName" id="orgName" value="${orgName}"/>
		</p>
		<p class="fl">
			<span style="width:6em;text-align:right;">项目学分：</span>
		</p>
		<p class="fl">

				<select name="level" id = "level" class="fl select" style="width:80px;" >
					<option value="-1">全部</option>
					<option value="1" <c:if test="${level==1}"> selected</c:if>>国家I类</option>						
					<option value="2" <c:if test="${level==2}"> selected</c:if>>省级I类</option>
					<option value="3" <c:if test="${level==3}"> selected</c:if>>市级I类</option>
					<option value="4" <c:if test="${level==4}"> selected</c:if>>省级II类</option>
					<option value="5" <c:if test="${level==5}"> selected</c:if>>市级II类</option>
					<option value="6" <c:if test="${level==6}"> selected</c:if>>无学分</option>						
				</select>
				<c:if test="${level==6}">
					<input type="text" id = "score" disabled="disabled" name = "score" onkeyup="this.value= this.value.replace(/[^\d.]/g, '');" value="${View.score}" placeholder="请输入学分" style="margin-left:12px;width:8em;" />
				</c:if>
				<c:if test="${level!=6}">
					<input type="text" id = "score" name = "score" onkeyup="this.value= this.value.replace(/[^\d.]/g, '');" value="${View.score}" placeholder="请输入学分" style="margin-left:12px;width:8em;"/>
				</c:if>
		</p>
		<p class="fl">
			<span style="width: 7em;text-align:right;">项目标签：</span>
		</p>
		<div class="fl select">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display: none;">
				<select id = "sign" name = "sign" style = "display:none;">
					<option value = "-1">全部</option>
					<option value = "1"<c:if test="${sign==1}"> selected</c:if>>公需科目</option>
					<option value = "2"<c:if test="${sign==2}"> selected</c:if>>指南解读</option>	
					<option value = "4"<c:if test="${sign==4}"> selected</c:if>>乡医培训</option>									
					<option value = "3"<c:if test="${sign==3}"> selected</c:if>>海外视野</option>										
				</select>
				<li>全部</li>
				<li>公需科目</li>
				<li>指南解读</li>
				<li>乡医培训</li>
				<li>海外视野</li>
			</ul>
		</div>
		<p class="fl">
			<span style="width: 6em;text-align:right;">授权区域：</span>
			<input type="hidden" name="propIds" id="propIds" value="${propIds}"/>
		</p>
		<div class="duouan" id="propNames01" style="width:200px;">${propNames}</div>
		<p class="fl cas_anniu" style="margin:1px 0px 10px 0px;">
			<a href="javascript:query();" class="fl" style="width:70px;margin-left:7em;">查询</a>
		</p>

	</div>
</div>
</form>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="content01">
		<ul class="cas1_xuanxing">
	        <li  id="cvsetRelease">未发布</li>
	        <li class="action" style="margin-left:30px;">已发布</li>
	    </ul>
	    	<div class="fr cas_anniu">
				<a href="javascript:cvsOffline(-1);" class="fl" style="width:80px;margin-left:10px;margin-bottom:10px;margin-top:-60px;">下线</a>
			</div>
			<display:table name="CVSet" requestURI="${ctx}/CVSet/CVDistribute.do" id="CVS" class="mt10 table" pagesize="15">
					<display:column title="<input type='checkbox' class='chkall'/>" style="width:2%;"><input type='checkbox' class='chk' value="${CVS.id}"/></display:column>
					<display:column title="序号" style="width:3%;">${CVS_rowNum}</display:column>
					<display:column title="学科" style="width:7%;">
						<c:forEach	items="${CVS.userImageList}" varStatus="status" var="image">
							<c:forEach	items="${image.departmentPropList}" varStatus="status" var="prop">
								${prop.name}&nbsp;
							</c:forEach>
						</c:forEach> 
					</display:column>
					<display:column title="项目名称" style="width:14%;">${CVS.name}</display:column>
					<display:column title="项目编号" style="width:10%">${CVS.serial_number}</display:column>
					<display:column title="授课形式" style="width:3%">
						<c:if test = "${CVS.forma == 1}">远程</c:if>
						<c:if test = "${CVS.forma == 3}">面授</c:if>
					</display:column>
					<display:column title="项目负责人" style="width:4%">
						<c:forEach	items="${CVS.managerList}" var="manager">
							${manager.name}&nbsp;
						</c:forEach>
					</display:column>
					<display:column title="项目归属机构" style="width:8%">
						<c:forEach	items="${CVS.organizationList}" var="org">
							${org.name} <br/>
						</c:forEach>
					</display:column>
					<display:column title="审核人" style="width:4%">${CVS.deli_man}</display:column>
					<display:column title="审核时间" style="width:7%;">
						<fmt:formatDate value="${CVS.deli_date}" pattern="yyyy-MM-dd"/>
					</display:column>
					<display:column title="项目状态" style="width:5%;">
						<c:if test="${CVS.status==3}">审核合格</c:if>
						<c:if test="${CVS.status==5}">已发布</c:if>
						<c:if test="${CVS.status==6}">已下线</c:if>
					</display:column>	
					<display:column title="操作" style="width:6%;">						
						<a href="javascript:preView(${CVS.id});">预览</a>
						<a href="${ctx}/CVSetManage.do?mode=get_CVS&id=${CVS.id}">详情</a>
						<c:if test="${CVS.status !=4}">
							<a href="javascript:cvsOffline(${CVS.id});">下线</a>
						</c:if>													 
					</display:column>
		</display:table>
	</div>
</div>

<!-- 新学科弹出框-一级 -->



<script type="text/javascript">
var msg = "${msg}";
if(msg != "")
{
	alert(msg);
}
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
		$(document).click(function () {
            $('.list').hide('fast');
        });
		
$(function(){
//选择目录弹出框
		$('.duouan').click(function(){
			$('.att_make01').show();
		});
		$('.bjt_kt,.cas_anniu_4').click(function(){
			$('.att_make01').hide();
			$('.att_make02').hide();
			$('.att_make03').hide();
			$('.att_make04').hide();
		});
		//二级
		$('.attr_xueke').click(function(){
			$('.att_make01').hide();
			$('.att_make02').show();
		});
		$('.attri01').click(function(){
			$('.att_make02').hide();
			$('.att_make01').show();
			$('.att_make03').hide();
			$('.att_make04').hide();
			$('.att_make05').hide();
		});
		//三级
		$('.attr_xueke02').click(function(){
			$('.att_make02').hide();
			$('.att_make03').show();
		});
		$('.attri02').click(function(){
			$('.att_make02').show();
			$('.att_make03').hide();
			$('.att_make04').hide();
			$('.att_make05').hide();
		});
		//调整
		$('.tiaozheng').click(function(){
			$('.make02').show();
		});
		$('.queren').click(function(){
			$('.make02').hide();
		});

		$('#groupNames01').click(function(){
			initPropList("学科", "${ctx}/propManage/getPropListAjax.do", 1, 0, 5, 3, $('#groupIds'), $('#groupNames01'));
			$('.att_make01').show();
		});
		
		$("#propNames01").click(function(){
			initPropList("区域", "${ctx}/propManage/getPropListAjax.do", 20, 0, 1, 1, $('#propIds'), $('#propNames01'));
			$('.att_make01').show(); 
		});
		
		$('#cvsetRelease').click(function () {
            document.location.href = "${ctx}/CVSet/CVDistribute.do?method=releaseList";
        });
        
        $('.chkall').click(function(){
			if ($(this).attr('checked') == 'checked')
				$('.chk').attr('checked' , 'checked');
			else
				$('.chk').removeAttr('checked');
		});
});

	function query(){
		var groupNames01 = $("#groupNames01").html();
		$("#groupNames").val(groupNames01);
		document.getElementById("queryForm").submit();
		// $('#queryForm').submit();
	}
function distribute(CVSid)
{
	window.location.href = "${ctx}/CVSet/CVDistribute.do?method=addAuthorizationView&cvSetId="+CVSid;
}
/**
 * 项目预览，显示同前台。
 */
function preView(CVSid)
{
	window.open("${ctxQiantaiURL}/projectView.do?id="+CVSid);
}
/**
 * 项目详情，显示同前台。
 */
function showDetail(CVSid)
{	
	window.open("${ctxQiantaiURL}/entityManage/entityView.do?type=play2&id="+CVSid+"&paramType=project");
}


//授权---项目发布--taoLiang
function cvsOffline(cvSetId){

	var params = 'method=cvSetReleaseAndOffline&cvSetId='+cvSetId+'&flag=6'
	//判断是否为批量
	if(cvSetId == -1){
		var cvSetIds = $("input[class='chk']:checked");
		if(cvSetIds.length == 0){
			alert("请先选择要下线的项目!");
		    return;
		}
		var selectedIds = '';
		if(cvSetIds != '' && cvSetIds.length >0 ){
			for(var i=0; i<cvSetIds.length; i++){
				selectedIds += cvSetIds[i].value + ',';
			}
		}
		params = 'method=cvSetReleaseAndOffline&cvSetIds='+selectedIds+'&flag=6'
	}
	if (!confirm("确定下线该项目？下线后，学员将无法查看或学习该项目。")) return;
	$.ajax({
		type:'post',
		url: '${ctx}/CVSet/CVDistribute.do',
		data: params,
		dataType:'JSON',
		success:function(result){
			if(result.msg == 'success'){
				alert("项目下线成功！");
				document.location.href = "${ctx}/CVSet/CVDistribute.do?method=releaseList&flag=1";
			}else if(result.msg == 'fail'){
				alert("项目下线失败！");
			}else{
				alert("项目下线失败！");
			}
		},error: function(){
			alert("网络错误，请刷新网页重新操作！");
		}
	});
}
</script>
</body>
</html>