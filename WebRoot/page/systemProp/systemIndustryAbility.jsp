<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String row = (String)request.getParameter("row");
	String meg = "";
	if(null != row && !"".equals(row)){
		Integer temp = Integer.parseInt(row);
		if(temp>0)
			meg = "保存成功!";
		else
			meg = "网络故障，保存不成功，请稍候重试!";
	}
	String deleRow = (String)request.getParameter("deleRow");
	if(null != deleRow && !"".equals(deleRow)){
		Integer temp = Integer.parseInt(deleRow);
		if(temp>0)
			meg = "删除成功!";
		else
			meg = "网络故障，删除不成功，请稍候重试!";
	}
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>资源管理系统</title>
		<%@include file="/commons/taglibs.jsp"%>
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/css/public.css">
		<script type="text/javascript" src="${ctx}/js/UI/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/locale/easyui-lang-zh_CN.js"></script>
	</head>
	<body>
		<!--main-->
		<div class="main">
			<!--栏目标题-->
			<div class="title">
				<!--左标题-->
				<div class="title_left">
					<ul>
						
						<li>
							<a href="${ctx }/system/SystemIndustry.do?method=list">系统属性 </a>&gt;${systemIndustry.industryName }  岗位能力
						</li>
					</ul>
				</div>
			</div>
			<div><strong><span style="color:red">
					<%=meg %>
				</span></strong>
			</div>
			<!--功能选项-->
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="gltop_btn">
				<tr>
					<td align="right">
						<!--右按钮-->
						<table border="0" cellspacing="5" cellpadding="0"
							class="gltop_btn_right">
							<tr>
								<td>
									<button target="_self" style="cursor:pointer" onClick="addAbility(${systemIndustry.id })" >
										<span class="tabletitlebtn_bluelongtwo">添加能力</span>
									</button>
									<button target="_self" style="cursor:pointer" onClick="window.location.href='${ctx}/system/SystemIndustry.do?method=list'" >
										<span class="tabletitlebtn_bluelongtwo">返回</span>
									</button>
								</td>
								
							</tr>
						</table>
						<!--右按钮结束-->
					</td>
				</tr>
			</table>

			<table width="90%" border="1">
				<tr>
					<td>序号</td>
					<td>ID</td>
					<td>能力名称</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${list}" var="ability" varStatus="status">
					<tr>
						<td>${status.index + 1 }</td>
						<td>${ability.id }</td>
						<td>${ability.abilityName }</td>
						<td>
							<button target='_self' style="cursor:pointer" onClick="updateAbility('${systemIndustry.id }','${ability.id}')" width='400' height='232' border='0'><span class='tablebtn_bluelong'>修改</span></button>&nbsp;&nbsp;
							<button target='_self' style="cursor:pointer" onClick="delAbility('${systemIndustry.id }','${ability.id}')" width='400' height='232' border='0'><span class='tablebtn_bluelong'>删除</span></button>&nbsp;&nbsp;
							<button target='_self' style="cursor:pointer" onClick="showCourses('${systemIndustry.id }','${ability.id}')" width='400' height='232' border='0'><span class='tablebtn_bluelong'>关联课程</span></button>&nbsp;&nbsp;
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty list }">
					<tr>
						<td colspan="4">该岗位:[${systemIndustry.industryName }]下暂无能力!</td>
					</tr>
				</c:if>
			</table>
			
		</div>
		<div id="dlg" class="easyui-dialog" title="系统属性" closed="true">
			<iframe src="" width="450" height="300" id="dialogFrame" name="dialogFrame" frameborder="0"></iframe>
		</div>
		<!--main结束-->
		<br/>
		<br/>
		<br/>
		<br/>
		
	</body>
</html>
<script>

	//删除能力
	function delAbility(industryId,abilityId) {
    	var childSize = 0;
	    $.ajaxSetup({async: false});
	    $.ajax({
			url: "${ctx}/system/SystemIndustryAbility.do?method=getCountCourses&abilityId="+abilityId,
			type:'get',
			dataType:'text',
			success:function(data){
			  childSize = data;
			}
	    });
	    $.ajaxSetup({async: true});

		if(childSize > 0){
		  alert('请先删除子节点后再删除此节点!');
		  return;
		}

	    if (confirm("确认删除!")) {
		  window.location = "${ctx}/system/SystemIndustryAbility.do?method=delete&abilityId=" + abilityId+"&industryId="+industryId;
		}
	}


	//添加能力
	function addAbility(industryId){
	  $( "#dialogFrame" ).attr("src","${ctx}/system/SystemIndustryAbility.do?method=add&industryId=" + industryId);
 	  var top = $(window).scrollTop() ;
	  var innerWidth = $(window).innerWidth() / 2 ;
	  		//弹出层
	    $( "#dlg" ).dialog({
		  autoOpen: false,
	      height: 340,
	      width: 480,
	      modal: true,//true遮蔽
	      top:top + 70,
	      left:innerWidth - 140,
	      iconCls:'icon-search', //左上角图标,icon文件夹下图标
	      close: function() {
	        allFields.val( "" ).removeClass( "ui-state-error" );
	      }
	    });
	  $("#dlg").dialog("open");
	}

	//关闭弹出层
	function dialogClose(){
	  $("#dlg").dialog( "close" );
	}

	//修改能力
	function updateAbility(industryId,id){
	  $( "#dialogFrame" ).attr("src","${ctx}/system/SystemIndustryAbility.do?method=update&id=" + id + "&industryId="+industryId);

 	  var top = $(window).scrollTop() ;
	  var innerWidth = $(window).innerWidth() / 2 ;
	  		//弹出层
	    $("#dlg").dialog({
		  autoOpen: false,
	      height: 340,
	      width: 480,
	      modal: true,//true遮蔽
	      top:top + 70,
	      left:innerWidth - 140,
	      iconCls:'icon-search', //左上角图标,icon文件夹下图标
	      close: function() {
	        allFields.val( "" ).removeClass( "ui-state-error" );
	      }
	    });

	  $("#dlg").dialog("open");
	}
	
	//查看能力下的课程
	function showCourses(industryId,abilityId){
		window.location.href = "${ctx}/system/SystemIndustryAbility.do?method=courseList&onePage=1&industryId="+industryId+"&abilityId="+abilityId;
	}
</script>