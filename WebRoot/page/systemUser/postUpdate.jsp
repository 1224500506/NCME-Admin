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
<title>编辑</title>
<%@include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/default090909.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/lay.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">

<link rel="stylesheet" href="${ctx}/js/Validform_v5.3/css/valid.css" type="text/css" media="all" />
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/Validform_v5.3.js"></script>

</head>
<body>
<form class="fm1" id="fm1" name="fm1" action="${ctx}/system/SystemPostHistory.do?method=update" method="post">
<input type="hidden" id="id" name="id" value="${postHistory.id}" />
<table border="0" cellspacing="1" cellpadding="0" width="90%" class="gridtable">
	<tr>
		<td align="center" colspan="3" class="row_tip" height="25">
			快递信息
		</td>
	</tr>
	<tr>
		<td class="row_tip" height="25" width="20%" align="right">
			姓名:
		</td>
		<td width="60%">
			${postHistory.userName }
		</td width="20%">
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			手机号:
		</td>
		<td>
			${postHistory.mobilePhone }
		</td>
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			地址:
		</td>
		<td align="left">
			${postHistory.address }
		</td>
	    <td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	
	<tr>
		<td class="row_tip" height="25" align="right">
			证书名:
		</td>
		<td>
			${postHistory.certificateName }
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			<span style="color:red">*</span>快递编号:
		</td>
		<td>
			<input type="text" id="expressNo" name="expressNo"
			 value="${postHistory.expressNo}" maxlength="250" size="25"
			 datatype="s1-20" errormsg="请输入快递编号！" />
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>
	</tr>
	<tr>
		<td class="row_tip" height="25" align="right">
			<span style="color:red">*</span>快递列表:
		</td>
		<td>
		<select id="expressId" name="expressId" datatype="n" errormsg="请选择快递！" >
			<option value="-1">----请选择---</option>
			<c:forEach var="item" items="${expressList}" varStatus="stat" >
				<option value="${item.id}">${item.name}</option>
			</c:forEach>
		</select>
		</td>	
		<td>
          <div class="Validform_checktip"></div>
        </td>
     </tr>
     
     <c:if test="${not empty postHistory.expressNo }">
     	<tr>
			<td class="row_tip" height="25" align="right">
				快递详情:
			</td>
			<td>
				<iframe id="frame1" src="http://api.kuaidi100.com/api?id=ff4e609b157d46ab&com=${postHistory.expressCode }&nu=${postHistory.expressNo }&valicode=&show=2&muti=&order=desc" frameborder="0"  marginwidth="0" marginheight="0"  scrolling="auto"  style="width:100%;height:100%;"></iframe>
			</td>	
			<td>
	          <div class="Validform_checktip"></div>
	        </td>
		</tr>
     	
     </c:if>
	
	<tr>
		<td colspan="6" align="center" class="row_tip">
		    <input type="submit" class="btn_03s" value="保 存" />
		    <input type="button" class="btn_03s" value="返 回" onclick="window.location.href='${ctx}/system/SystemPostHistory.do?method=list'"/>
		</td>
	</tr>
</table>
<input type="hidden" id="id" name="id" value="${order.id}"/>
</form>
<script type="text/javascript">
    $(".fm1").Validform({
      	datatype:{//自定义datatype类型 浮点类型
	    "float":function(gets,obj,curform,datatype){
				var reg = new RegExp("^\\d+(\\.\\d+)?$");
				if (!reg.exec(gets)){
				  return false;
				}
				return true;
			}
      	}
    });
    
    $("#expressId").val(${postHistory.expressId}); 
</script>
</body>
</html>