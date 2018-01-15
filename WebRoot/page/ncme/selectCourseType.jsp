<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%@ include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>

<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
</style>
 <script type="text/javascript">

	function doSelect(){
		var subTypes = $("input[type='radio'][name='subTypeIds']:checked");
		var types = $("input[type='checkbox'][name='typeIds']:checked");
		if(subTypes.length == 0 && types.length == 0){
			alert("请至少选择一个课程分类!");
		    return;
		}
		
		var paramIds = '';
		var paramNames = '';
		if(subTypes.length >0){
			var arr = subTypes.val().split("_");
			paramIds += arr[0] + ',';
			paramNames += arr[1] + '，';
		}
		if(types != '' && types.length >0 ){
			for(var i=0; i<types.length; i++){
				var arr = types[i].value.split("_");
				paramIds += arr[0] + ',';
				paramNames += arr[1] + '，';
			}
		}
		parent.$("#courseTypeIds").val(paramIds);
		parent.$("#courseTypeName").val(paramNames);
		parent.$("#courseTypeName").focus();
		parent.dialogClose();
	}
	
	
</script>
	</head>
	<body bgcolor="#E7E7E7">
		
		<div style="margin: auto; text-align: right;">
		<center>
		
		<form name="queryForm" action="" method="post" onsubmit="return check();">
		<table style="font-size:12px;width:95%;text-align: center;" class="ITS" >
		
		<tr>
			<td colspan="2"><strong><font color="red">注：授权类别可以多选，但职业卫生、企业负责人下子类内部只能选择一个。</font></strong></td>
		</tr>
		<tr>
			<td>授权类别</td>
			<td align="left">
				 <c:forEach items="${typeList}" var="type" varStatus="status">
				 	<c:if test="${type.isLastLevel > 0 }">
		        		<input type="checkbox" name="typeIds" value="${type.id }_${type.courseTypeName}"/> 
		        	</c:if>
		        	${type.courseTypeName}<br/>
		        	<c:if test="${type.isLastLevel == 0 }">
		        		<c:forEach items="${type.typeList}" var="st" varStatus="s">
		        			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		        			<input type="radio" name="subTypeIds" value="${st.id }_${type.courseTypeName}一${st.courseTypeName}" />
		        				${st.courseTypeName}<br/>
		        		</c:forEach>
		        	</c:if>
		      	</c:forEach>
			</td>
		</tr>
		
		<tr>
			<td colspan="2"><button type="button" class="btn_03s" onclick="doSelect()">确认</button>&nbsp;&nbsp;
				<button type="reset" class="btn_03s" >重选</button>&nbsp;&nbsp;
				<button type="button" class="btn_03s" onclick="parent.dialogClose();" >关闭</button>
			</td>
		</tr>
		</table>
		
		<br/>
				
			</center>
		</div>
		</form>
	 		
	</body>
</html>
