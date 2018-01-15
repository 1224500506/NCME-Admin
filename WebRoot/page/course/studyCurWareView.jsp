<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>课程管理</title>
		<%@include file="/commons/taglibs.jsp"%>
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/button.css" rel="stylesheet" type="text/css" />
		
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/bootstrap/easyui.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/js/UI/themes/icon.css"/>
		
		<script type="text/javascript" src="${ctx}/js/UI/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/UI/locale/easyui-lang-zh_CN.js"></script>
		
		<style media="all" type="text/css">
			@import url("${ctx}/css/displaytag/site.css");
			@import url("${ctx}/css/displaytag/screen.css");
		</style>
	</head>
	<body>
		<div style="padding-left:12px;width:99%;">
			<table class="gridtable" cellpadding="1" cellspacing="0" border="0" width="100%">
				<tr>
					<td class="row" align="right">
						<input id="addCur" type="button" class="btn_03s" value="增加课件" />
						<input name="addPrac" examType="11" type="button" class="btn_03s" value="增加课前练习" />
						<input name="addPrac" examType="13" type="button" class="btn_03s" value="增加课后练习" />
						<input type="button" class="btn_03s" value="删除组件" onclick="delCurWare();" />
					</td>
				</tr>
			</table>
			<br />
		</div>
		<div>
			<center>
				<form action="" id="formB" name="formB" method="post">
				<input type="hidden" id="courseId" name="courseId" value="${param.courseId}" />
				<input type="hidden" id="formBId" name="curTypeId" value="${param.curTypeId}" />
				<input type="hidden" id="elementId" name="elementId" />
				<input type="hidden" id="moveFlag" name="moveFlag" />
				<input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="${sessionScope['org.apache.struts.action.TOKEN']}" />
				<display:table id="row" name="page" requestURI="${ctx}/course/studyCurWareView.do" style="font-size:12px;width:98%;" class="ITS">
					<display:caption>组件查询结果</display:caption>
					<display:column title="顺序" style="text-align:center">
						${row_rowNum}
					</display:column>
					<display:column title="<input type='checkbox' id='chckAll' name='chckAll' onclick='checkAll(this);'>" style="text-align:center;width:2%">
						<input type="checkbox" id="wareIds" name="wareIds" value="${row.courseElementId}" />
					</display:column>
					<display:column title="知识名称" style="text-align:center">
						${row.name}
					</display:column>
					<display:column property="teacherName" title="主讲老师" style="text-align:center" />
					<display:column property="keyWord" title="关键字" style="text-align:center" />
					<display:column property="times" title="时长" style="text-align:center" />
					<display:column title="组件类型" style="text-align:center">
						<c:choose>
							<c:when test="${row.courseElementType eq 1}">
								单屏课程
							</c:when>
							<c:when test="${row.courseElementType eq 2}">
								三屏课程
							</c:when>
							<c:when test="${row.courseElementType eq 3}">
								flash
							</c:when>
							<c:when test="${row.courseElementType eq 4}">
								文档课程
							</c:when>
							<c:when test="${row.courseElementType eq 11}">
								课前练习
							</c:when>
							<c:when test="${row.courseElementType eq 12}">
								课中练习
							</c:when>
							<c:when test="${row.courseElementType eq 13}">
								课后练习
							</c:when>
							<c:otherwise>
								&nbsp;
							</c:otherwise>
						</c:choose>
					</display:column>
					<display:column title="课件类型" style="text-align:center">
						<c:choose>
							<c:when test="${row.type eq 1}">
								单屏课程
							</c:when>
							<c:when test="${row.type eq 2}">
								三屏课程
							</c:when>
							<c:when test="${row.type eq 3}">
								flash
							</c:when>
							<c:when test="${row.type eq 4}">
								文档课程
							</c:when>
							<c:otherwise>
								习题练习
							</c:otherwise>
						</c:choose>
					</display:column>
					<display:column title="操作" style="text-align:center;width:11%">
						<input type="button" class="btn_04s" value="删除" onclick="delCurWare2('${row.courseElementId}')" />
						<input type="button" class="btn_04s" value="上移" onclick="upMove('${row.seq}','${row.courseElementId}');" />
						<input type="button" class="btn_04s" value="下移" onclick="downMove('${row.seq}','${row.courseElementId}');" />
						<input type="button" class="btn_04s" value="预览" name="preView" eleType="${row.courseElementType}" eleId="${row.courseElementId}" />
					</display:column>
				</display:table>
				</form>
			</center>
		</div>
		
		<div style="padding-left:12px;width:99%;">
			<table class="gridtable" cellpadding="1" cellspacing="0" border="0" width="100%">
				<tr>
					<td class="row" align="right">
						<input id="addCur" type="button" class="btn_03s" onclick="window.location.href='${ctx}/course/studyCourseView.do'" value="返回列表" />
					</td>
				</tr>
			</table>
			<br />
		</div>
		
		<!-- 弹出层开始 -->
		<div id="elementInfo" class="easyui-window" title="" closed="true" iconCls="icon-save" style="padding:5px;">
			<div id="initHtml"></div>
		</div>
		<!-- 弹出层结束 -->
		
		<script type="text/javascript">
			var examIds ;
			var iframe = "<iframe id='eleInfo' name='eleInfo' src='' width='100%' height='100%' frameborder='0' scrolling='scroll'></iframe>" ;
			$("#addCur").click(function(){
				$("#initHtml").html("") ;
				$("#initHtml").html(iframe) ;
				$('#elementInfo').window({
					title: '&nbsp;课件信息',
					width: 1100,
					height: 720,
					modal: true,
					shadow: false,
					closed: false,
					minimizable:false,
					collapsible:false,
					maximizable:false,
					top:40,
					left:100
				});
				examIds = new Array() ;
				var curId = $("#courseId").val();
				$("#eleInfo").attr("src", "${ctx}/course/studyCurWareList.do?curId=" + curId) ;
			}) ;

			$("[name='addPrac']").click(function(){
				$("#initHtml").html("") ;
				$("#initHtml").html(iframe) ;
				$('#elementInfo').window({
					title: '&nbsp;习题信息',
					width: 1210,
					height: 550,
					modal: true,
					shadow: false,
					closed: false,
					minimizable:false,
					collapsible:false,
					maximizable:false,
					top:18,
					left:45
				});
				examIds = new Array() ;
				var curId = $("#courseId").val();
				var examType = $(this).attr("examType") ;
				$("#eleInfo").attr("src", "${ctx}/course/studyPaperIndex.do?curId=" + curId+"&examType="+examType) ;
			}) ;
			
			$("[name='preView']").click(function(){
				var eleId = $(this).attr("eleId");
				var eleType = $(this).attr("eleType");
				
				var url = "${ctx}/course/studyCurWarePreview.do?eleId="+eleId+"&eleType="+eleType;
        	    openwindow(url,"videoWindow",1200,700);
			});
			
			function checkAll(element){
				$("input[name='wareIds']").each(function(){
					$(this).attr("checked", element.checked) ;
				});
			}
			
			function upMove(index, eleId){
				if(index <= 1){
					alert("当前数据不能进行上移操作!") ;
					return false ;
				}
				$("#elementId").val(eleId) ;
				$("#moveFlag").val('') ;
				document.formB.action = "${ctx}/course/studyCurWareMove.do" ;
				document.formB.submit()
			}
			
			function downMove(index, eleId){
				var maxSeq = '${maxSeq}' ;
				 maxSeq = (maxSeq == '' || maxSeq == null) ? 1 : parseInt(maxSeq) ;   
				if(index >= maxSeq){
					alert("当前数据不能进行下移操作!") ;
					return false ;
				}
				$("#elementId").val(eleId) ;
				$("#moveFlag").val(eleId) ;
				document.formB.action = "${ctx}/course/studyCurWareMove.do" ;
				document.formB.submit()
			}
			
			function delCurWare(){
				var curIds = $("input[name='wareIds']:checked") ;
				if(curIds.length == 0){
					alert("请您至少选择一项需要删除的数据!") ;
					return false ;
				}
				if(confirm("确认删除吗?")){
					$("#moveFlag").attr("value", '') ;
					document.formB.action = "${ctx}/course/studyCurWareDel.do" ;
					document.formB.submit() ;
				}
			}

			function delCurWare2(rowId){
				if(confirm("确认删除吗?")){
					$("#moveFlag").attr("value", rowId) ;
					$("#elementId").attr("value", rowId) ;
					document.formB.action = "${ctx}/course/studyCurWareDel.do" ;
					document.formB.submit() ;
				}
			}
			
			function reloadPage(){
				window.location.reload(true) ;
				$("#eleInfo").attr("src", "") ;
				$('#elementInfo').window("close") ;
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
		</script>
	</body>
</html>