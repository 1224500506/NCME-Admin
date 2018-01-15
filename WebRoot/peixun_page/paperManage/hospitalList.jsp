<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>培训平台-机构管理</title>
		<%@include file="/commons/taglibs.jsp"%>
		<link rel="stylesheet" href="${ctx}/peixun_page/css/reset.css" />
		<link rel="stylesheet" href="${ctx}/peixun_page/css/index.css" />
		<link rel="stylesheet" href="${ctx}/peixun_page/css/pickadate/default.css" />
		<link rel="stylesheet" href="${ctx}/peixun_page/css/pickadate/default.date.css" />
		<link rel="stylesheet" href="${ctx}/peixun_page/css/fontawesome/css/font-awesome.css" />
		<link rel="stylesheet" href="${ctx}/peixun_page/css/ueditor.min.css" />
		
		<script type="text/javascript" src="${ctx}/peixun_page/js/jquery.js"></script>
		<%-- <script type="text/javascript" src="${ctx}/peixun_page/js/main.js"></script> --%>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/layer/layer.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/picker.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/picker.date.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/pickadate/legacy.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/ueditor/ueditor.config.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/ueditor/ueditor.all.min.js"></script>
		<script type="text/javascript" src="${ctx}/peixun_page/js/lib/ueditor/lang/zh-cn/zh-cn.js"></script>
		<style>
			input[type=text]{padding:0 5px;}
			input:disabled{background:#f0f0f0;color:#ccc;}
			.center{margin:15px auto 0;}
		</style>
	</head>

<body>
<!-- 查询条件 -->
<div class="center">

<form action="${ctx}/examManage/hospitalList.do" id="searchForm" method="post">
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl" style="margin:0 20px 20px 0">
			<span>医院名称：</span>
			<input type="text" name="name" value="${name}"/>
		</p>
		<p class="fl">
			<span style="width:5em;text-align:right;">医院类型：</span>
		</p>
		<div class="fl select" style="margin-right:20px;">
			<div class="tik_position">
				<b class="ml5">全部</b>
				<p class="position01"><i class="bjt10"></i></p>
			</div>
			<ul class="fl list" style="display: none;">
			<select  name="parentId"  style="display:none;" value="${type}">
				<option value="0">全部</option>
				<c:forEach var="item" items="${hosTypeList}">
					<option value="${item.id}" <c:if test="${type==item.id}">selected</c:if>>${item.name}</option>
				</c:forEach>
			</select>
				<li>全部</li>
				<c:forEach var="item" items="${hosTypeList}">
					<li>${item.name}</li>
				</c:forEach>
			</ul>
		</div>
		<p class="fl cas_anniu">
			<a href="javascript:$('#searchForm').submit();" class="fl" style="width:70px;margin-left:10px;">查询</a>
		</p>
	</div>
</form>
</div>
<div class="fr cas_anniu" style="margin-right:65px;">
	<a href="#" id = "addHospital" class="fl exp1_tianjia01" style="width:80px;">添加机构</a>
	<a href="javascript:window.close();" class="fl" style="width:80px;margin-left:10px;">返回</a>
</div>
<div class="clear" style="height:20px"></div>
<div class="bjs"></div>

<!-- 内容 -->
<div class="center" align="center">
 
	<div class="center01">
 		<display:table  name="Page" id="item" requestURI="${ctx}/examManage/hospitalList.do" 
				class="mt10 table" excludedParams="method" size="${page.fullListSize}" pagesize="${page.objectsPerPage}"
				 keepStatus="true"  decorator="com.hys.exam.displaytag.TableOverOutWrapper">
				<display:column
					title="<input type='checkbox' id='checkall' name='checkall' value='all' onclick='javascript:checkAll(this);' />"
					style="width:3%;text-align:center">
					 <input type="checkbox" name="ids" value="${item.name}" id="${item.id}"/>
				</display:column>
				<display:column title="序号" style="width:10%;" property="id"></display:column>
				<display:column title="医院名称" style="width:20%;" property="name"></display:column>
 				<display:column title="省" style="width:10%;" property="region1"></display:column>
 				<display:column title="市" style="width:10%;" property="region2"></display:column>
 				<display:column title="区" style="width:10%;" property="region3"></display:column>
				<display:column title="审批机关" style="width:20%;" property="examiner"></display:column>
		</display:table> 
		
		<div class="clear"></div> 
	</div>
</div>
<script type="text/javascript">
var winObj;
var orgNamesIds;
var orgNames;
selectInit();
function selectInit(){
// select view
	$('.select').click(function(){
		$(".list").css("display","none");
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
	$(document).click(function(){
		$('.list').hide('fast');
	});
	winObj = parent.opener;
   	orgNamesIds = winObj.orgNamesId;
  	orgNames = winObj.orgNamesInfo;
  	
  	if (orgNamesIds != null && orgNamesIds != "")
  	{
  		for (var i = 0; i < orgNamesIds.length; i ++)
  		{
  			$("input[name='ids']").each(function() {
  				if ($(this).attr("id") == orgNamesIds[i])
  				{
  					$(this).remove();
  				}
  			});		
  		}
  		orgNamesIds += ",";
  		orgNames += ",";
  	}
}
   
   
$("#addHospital").click(function () {
     
	$("input[name='ids']").each(function () {
       	if ($(this).attr("checked") == "checked") 
       	{		
     			orgNames += $(this).val()+","; 
     			orgNamesIds += $(this).attr("id") + ",";		
       	}
    });
	//winObj.orgNamesId = orgNamesIds;
	//winObj.orgNamesInfo = orgNames;
	winObj.putTextIn(orgNamesIds, orgNames);
	orgNamesIds = null;
	orgNames = null;
	window.close();
});

function checkAll(obj){
	$("input[name='ids']").each(function()
	{
		$(this).attr("checked", obj.checked) ;
	});
} 

</script>
</body>
</html>