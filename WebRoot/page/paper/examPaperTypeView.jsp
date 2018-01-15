<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>试卷分类</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<%@include file="/commons/taglibs.jsp"%>
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/button.css" rel="stylesheet" type="text/css" />
		
		<link href="${ctx}/js/UI/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/js/UI/themes/icon.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="${ctx}/js/UI/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
		
		<style media="all" type="text/css">
			@import url("${ctx}/css/displaytag/site.css");
			@import url("${ctx}/css/displaytag/screen.css");
		</style>
	</head>
	<body>
		
		<div style="padding-left:12px;width:99%;">
			<form action="" method="post" id="formA" name="formA">
			<input type="hidden" id="paperTypeId" name="paperTypeId" value="${param.paperTypeId}" />
			<table class="gridtable" cellpadding="1" cellspacing="0" border="0" width="100%">
				<tr>
					<td class="row">目录名称:
						<input type="text" id="paperTypeName" name="paperTypeName" maxlength="50" value="${paperTypeName}" />
						<input type="button" class="btn_03s" value="查  询" onclick="selectPaperType();" />
						<input type="button" class="btn_03s" value="添加分类" onclick="addPaperTypeShow();" />
						<input type="button" class="btn_03s" value="删除分类" name="delete" />
					</td>
				</tr>
			</table>
			</form>
			<br />
		</div>

		<div>
			<center>
				<display:table id="row" name="page" requestURI="${ctx}/course/studyCourseView.do" style="font-size:12px;width:98%;" class="ITS">
					<display:caption>试卷分类查询结果</display:caption>

					<display:column title="序号" style="text-align:center">
						${row_rowNum}
					</display:column>
					<display:column title="<input type='checkbox' id='checkAll' name='checkAll' onclick='checkAll(this);' />" style="text-align:center;width:5%">
						<input type="checkbox" id="primIds" name="primIds" value="${row.id}" />
					</display:column>
					<display:column property="name" title="名称" style="text-align:center" />
					<display:column property="code" title="编码" style="text-align:center" />
					<display:column title="操作" style="text-align:center;width:11%">
						<input type="button" class="btn_04s" value="修改" primaryId="${row.id}" name="update" />
						<input type="button" class="btn_04s" value="调整" rowId="${row.id}" rowCode="${row.code}" rowName="${row.name}" name="adjust" />
					</display:column>
				</display:table>
				
			</center>
		</div>
		
		<!-- 弹出层开始 -->
		<div id="editInfo" class="easyui-window" title="" closed="true" iconCls="icon-save" style="padding:5px;">
			<table border="0" cellspacing="1" cellpadding="0" width="100%" class="gridtable">
				<tr>
					<td align="center" colspan="3" class="row_tip" height="25">
						<span id="editTitle"></span>
						<input type="hidden" id="editType" name="editType" />
						<input type="hidden" id="primId" name="primId" />
					</td>
				</tr>
				<tr>
					<td class="row_tip" align="right" height="25">
						<font class="red_star">*</font>分类名称：
					</td>
					<td width="20%">
						<input type="text" id="pTypeName" name="pTypeName" class="editInput" maxlength="25" size="25" />
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td class="row_tip" align="right" height="25">
						备注：
					</td>
					<td>
						<textarea id="remark" name="remark" rows="5" cols="60"></textarea>
					</td>
					<td>
						<div class="Validform_checktip"></div>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center" class="row_tip">
						<input type="button" name="btn_sub" class="btn_03s" value="保 存" />
						<input type="button" class="btn_03s" value="关 闭" onclick="closeWin2();" />
					</td>
				</tr>
			</table>
		</div>
		
		
		<!-- 弹出层结束 -->
		
		<!-- 弹出层开始 -->
		<div id="editInfo2" class="easyui-window" title="" closed="true" iconCls="icon-save" style="padding:5px;">
			<div id="initHtml"></div>
		</div>
		<form id="ajustForm" name="ajustForm" method="post" target="eleInfo">
			<input type="hidden" id="rowId" name="rowId" />
			<input type="hidden" id="rowName" name="rowName" />
			<input type="hidden" id="rowCode" name="rowCode" />
			
			<input type="hidden" id="parentName" name="parentName" />
		</form>
		<!-- 弹出层结束 -->
		
		<script type="text/javascript">
			
			var iframe = "<iframe id='eleInfo' name='eleInfo' src='' width='100%' height='100%' frameborder='0' scrolling='scroll'></iframe>" ;
			
			$(document).ready(function(){
				$("[name='btn_sub']").click(function(){
					
					var typeName = $("#pTypeName").val() ;
					if(typeName == null || typeName == ''){
						alert("请您填写分类名称!") ;
						return false ;
					}

					var remark = $("#remark").val() ;
					if(remark != null && remark != ''){
						var remarkLen = remark.getBytes() ;
						if(remarkLen > 500){
							alert("填写的分类备注信息,请不要超过250个汉字!") ;
							return false;
						}
					}
					
					var editType = $("#editType").val() ;
					if(editType > 0){
						var pTypeId = $("#paperTypeId").val() ;
						var url = '${ctx}/exam/paper/examPaperTypeAdd.do' ;
						var datas = 'paperTypeId='+pTypeId+"&paperTypeName="+typeName+"&remark="+remark;
						editInfo('您确定添加当前分类吗?', url, datas, 1) ;
					}else{
						var primId = $("#primId").val() ; ;
						var url = '${ctx}/exam/paper/examPaperTypeEdit.do' ;
						var datas = 'primaryId='+primId+"&paperTypeName="+typeName+"&remark="+remark;
						editInfo('您确定修改当前分类吗?', url, datas, 1) ;
					}
				}) ;

				$("[name='update']").click(function(){
					$("#editType").attr("value", '') ;
					$('#editInfo').window({
						title: '&nbsp;分类信息',
						width: 800,
						height: 300,
						modal: true,
						shadow: false,
						closed: false,
						minimizable:false,
						collapsible:false,
						maximizable:false,
						top:40,
						left:200
					});
					
					$("#editTitle").html("修改分类")
					var pId = $(this).attr("primaryId") ;

					$.ajax({
						type:"post",
						url:'${ctx}/exam/paper/examPaperTypeDetail.do',
						data:'primaryId='+pId,
						success:function(retData) {
							if(retData != null){
								var temData = eval('('+ retData +')') ;
								$("#pTypeName").val(temData.name) ;
								$("#remark").val(temData.remark) ;
								$("#primId").val(temData.id) ;
							}
						},
						error:function() {
							alert('操作失败！');
						}
					});
				}) ;
				
				$("[name='delete']").click(function(){
					var primIds = $("input[name='primIds'][type='checkbox']:checked") ;
					 
					if(primIds.length == 0){
						alert("请您至少选择一条需要删除的数据!") ;
						return false ;
					}

					var temp = ''
					primIds.each(function(){
						var prinId = $(this).val() ;
						temp = temp == '' ? (temp += prinId) : (temp += ',' + prinId) ;  
					}) ;
					
					var url = '${ctx}/exam/paper/examPaperTypeDelete.do' ;
					var datas = 'primIds='+temp;
					editInfo('您确定删除当前所选分类吗?', url, datas, 1) ;
				}) ;

				$("[name='adjust']").click(function(){
					var parentNode = parent.$("#paperTree").tree('getSelected');
					$("#parentName").attr("value", parentNode.text) ;
					$("#rowId").attr("value", $(this).attr("rowId")) ;
					$("#rowName").attr("value", $(this).attr("rowName")) ;
					$("#rowCode").attr("value", $(this).attr("rowCode")) ;
					
					$('#editInfo2').window({
						title: '&nbsp;分类信息',
						width: 800,
						height: 400,
						modal: true,
						shadow: false,
						closed: false,
						minimizable:false,
						collapsible:false,
						maximizable:false,
						top:100,
						left:200
					});
					
					$("#initHtml").html("") ; $("#initHtml").html(iframe) ;
					$("#ajustForm").attr("action", "${ctx}/page/paper/examPaperTypeMove.jsp") ;
					$("#ajustForm").submit() ;
				}) ;
				
				
			});
			
			function selectPaperType(){
				var name = encodeURI(encodeURI($("#paperTypeName").val())) ;
				window.location.href = "${ctx}/exam/paper/examPaperTypeView.do?paperTypeId=${param.paperTypeId}&paperTypeName=" + name ;
				//document.formA.action = "" ;
				//document.formA.submit() ;
			}
			
			function addPaperTypeShow(){
				$("#editType").attr("value", 1) ;
				$('#editInfo').window({
					title: '&nbsp;分类信息',
					width: 800,
					height: 300,
					modal: true,
					shadow: false,
					closed: false,
					minimizable:false,
					collapsible:false,
					maximizable:false,
					top:40,
					left:200
				});
				$("#editTitle").html("新增分类")
			}
			
			function closeWin(){
				$('#editInfo').window("close") ;
				window.location.reload(true) ;
			}

			function closeWin2(){
				$('#editInfo').window("close") ;
			}
			
			function editInfo(msg, url, datas, alertType){
				if(confirm(msg)) {
					$.ajax({
						type:"post",
						url:url,
						data:datas,
						success:function(id) {
							if(id > 0){
								alert("操作成功") ;
								if(alertType == 1){
									parent.reloadAdd();
								}else{
									parent.reloadDelete();
								}
							}else{
								alert("操作失败") ;
							}
							closeWin();
						},
						error:function() {
							alert('操作失败！');
							closeWin();
						}
					});
				}
			}
			
			function checkAll(element){
				$("input[name='primIds'][type='checkbox']").each(function(){
					$(this).attr("checked", element.checked);
				}) ;
			}
		</script>
	</body>
</html>