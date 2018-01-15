<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/commons/taglibs.jsp"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/button.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/icon.css">
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_v5.3/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/UI/locale/easyui-lang-zh_CN.js"></script>
<style media="all" type="text/css">
	@import url("${ctx}/css/displaytag/site.css");
	@import url("${ctx}/css/displaytag/screen.css");
</style>
 <script type="text/javascript">


//[查询] 
function query() {
		
	document.forms[0].submit();
}


//生成学习卡
function createCards(){
	  $( "#dialogFrame" ).attr("src","${ctx}/system/SystemCard.do?method=createCards");
	  var top = $(window).scrollTop() ;
	  var innerWidth = $(window).innerWidth() / 2 ;
	  		//弹出层
	    $("#dlg").dialog({
		  autoOpen: false,
	      height: 340,
	      width: 480,
	      modal: true,//true遮蔽
	      top:top + 70,
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

function doRefresh(){
	var meg = '${meg}';
	if(meg != null && meg !=''){
		alert(meg);
		document.forms[0].submit();
	}
}

//导出卡记录
function exportCards(recordId){
	window.location.href = "${ctx}/system/SystemCard.do?method=exportCards&recordId="+recordId;
}

</script>
	</head>
	<body bgcolor="#E7E7E7" onload="doRefresh()">
		
		<div style="margin: auto; text-align: right;">
		<center>
		
		<form name="queryForm" action="${ctx}/system/SystemCard.do" method="post">
		<input type="hidden" id="method" name="method" value="createCardList" />
		<table style="font-size:12px;width:95%;text-align: center;" class="ITS" >
		<tr>
			<td>制卡管理</td>
			<td align="right">
				<button type="button" class="btn_03s" onclick="javascript:createCards();" >生成学习卡</button>
			</td> 
		</tr>
		
		</table>
		
		<br/>
			
				<display:table requestURI="${ctx}/system/SystemCard.do?method=createCardList"
				 id="cardRecord" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 style="font-size:12px;width:95%;" class="ITS" keepStatus="true">
					<display:column title="序号" style="text-align:center">
						${cardRecord_rowNum}
					</display:column>
					<display:column property="id" title="ID" style="text-align:center"/>
					<display:column property="cardStartInt" title="开始卡号" style="text-align:center" sortable="true"/>
					<display:column property="cardEndInt" title="结束卡号" style="text-align:center" sortable="true"/>
					<display:column property="cardSum" title="卡数量" style="text-align:center" sortable="true"/>
					<display:column property="cardUserdSum" title="已使用数量" style="text-align:center" sortable="true"/>
					<display:column property="createDate" title="生成日期" style="text-align:center" sortable="true" />
					<display:column property="description" title="描述" style="text-align:center" />
					<display:column title="操作" media="html" style="text-align:center">
						<a href="javascript:exportCards('${cardRecord.id }')" >导出卡号密码</a>
					</display:column>
				</display:table>
			</center>
		</form>
	 	</div>
	 	<div id="dlg" class="easyui-dialog" title="批量生成学习卡" closed="true">
			<iframe src="" width="450" height="300" id="dialogFrame" name="dialogFrame" frameborder="0"></iframe>
		</div>
	</body>
</html>
