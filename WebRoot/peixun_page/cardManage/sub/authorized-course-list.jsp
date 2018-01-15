<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" src="${ctx}/js/UI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/UI/locale/easyui-lang-zh_CN.js"></script> 
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培训后台</title>
<%@include file="/commons/taglibs.jsp"%>
</head>
<%@include file="/peixun_page/top.jsp"%>

<body>

<div class="clear"></div>
<!-- 查询条件 -->
<div class="center">
	<div class="tiaojian" style="min-height:40px;">
	    <form name="queryForm"  id="search" action="${ctx}/cardManage/SystemCardType.do?method=getStudyCourse" method="post">
	    <input type="hidden" id="isAuthorized" name="isAuthorized" value="1" />
		<input type="hidden" id="typeId" name="typeId" value="${typeId }" />
		<p class="fl">
			<span>课程名称：</span>			
			<input name="courseName" id="courseName" value="">
		</p>
		</form>
		<div class="fl cas_anniu">
			<a href="javascript:query();" class="fl" style="width:70px;margin-left:70px;">查询</a>
		</div>
	</div>
</div>
<div class="clear"></div>
<div class="bjs"></div>
<!-- 内容 -->
<div class="center" style="">
	<div class="center01">
		<div class="mt5 kit1_tiaojia">
			<div class="fr cas_anniu">
				<a href="#" class="fl xuexike" style="width:80px;">课程分类</a>
				<a href="#" class="fl leca_list" style="width:80px;margin-left:10px;">课程类表</a>
				<a href="javascript:doRefresh();" class="fl" style="width:80px;margin-left:10px;">课刷新本页</a>
				<a href="leqrnCard_2.html" class="fl" style="width:80px;margin-left:10px;">返回</a>
			</div>
		</div>
		<div class="clear"></div>
			<display:table requestURI="${ctx}/cardManage/SystemCardType.do?method=getStudyCourse&isAuthorized=${isAuthorized }&type=${typeId }"
				 id="isStudyCourse" cellpadding="0" cellspacing="0" partialList="true" 
				 excludedParams="method" size="${page.count}" pagesize="${page.pageSize}" name="page.list" list="${page.list}"
				 class="mt10 table" keepStatus="true">
					<display:caption><span style="color:red">${meg}</span></display:caption>
					<display:column title="序号" style="text-align:center">
						${isStudyCourse_rowNum}
					</display:column>
					<display:column property="id" title="ID" style="text-align:center"/>
					<display:column property="studyCourseName" title="课程名称" style="text-align:center"/>
					<display:column property="summary" title="摘要" style="text-align:center"/>
					<display:column property="teacher" title="主讲老师" style="text-align:center"/>
					<display:column property="createDate" title="创建时间" style="text-align:center"/>
					<display:column property="pubDate" title="发布时间" style="text-align:center"/>
					<display:column title="操作" media="html" style="text-align:center">
						<a href="javascript:deleteCourseAuthorized('${typeId }','${isStudyCourse.id }')" >解除授权</a>&nbsp;&nbsp;
					</display:column>
			</display:table>
		<!-- <table class="mt10 table">
			<tr class="tr">
				<th width="10%">序号</th>
				<th width="10%">ID</th>
				<th width="15%">课程名称</th>
				<th width="15%">摘要</th>
				<th width="10%">主讲老师</th>
				<th width="20%">创建时间</th>
				<th width="10%">发布时间</th>
				<th width="10%">操作</th>
			</tr>
			<tr class="tr">
				<td width="10%"></td>
				<td width="10%"></td>
				<td width="15%"></td>
				<td width="15%"></td>
				<td width="10%"></td>
				<td width="20%"></td>
				<td width="10%"></td>
				<td width="10%">
					<a href="#" class="">解除授权</a>
				</td>
			</tr>
			
		</table> -->
		<div class="clear"></div> 
		<!-- 分页 -->
		<!-- <div class="fenye">
			<ul class="fl fen_ul1">
				<li><a href="#">上一页</a></li>
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				
				<li><a href="#">...</a></li>
				<li><a href="#">下一页</a></li>
			</ul>
			<p class="fl ml10 fen_ul2">共20页</p>
		</div> -->
	</div>
</div>
<!-- 修改密码 -->
<div class="toumingdu make01" style="display:none;">
	
</div>
<!-- 课程分类弹框 -->
<div class="toumingdu make02" style="display:none;">
	<div class="leca_center">
		<p class="lace_biaoti">课程目录</p>
		<div class="clear"></div>
		<ul class="lace_kecheng">
		<c:forEach item=array></c:forEach>
			<li><input type="checkbox" value="岗位"/><span>岗位</span></li>
			<li><input type="checkbox" value="分类"/><span>分类</span></li>
			<li><input type="checkbox" value="公共专项"/><span>公共专项</span></li>
			<li><input type="checkbox" value="基层云学院"/><span>基层云学院</span></li>
			<li><input type="checkbox" value="胜任力培训"/><span>胜任力培训</span></li>
		</ul>
		<div class="clear"></div>
		<div class="cas_anniu" style="margin-top:40px;margin-left:170px;">
			<a href="javascript:getChecked()" class="fl queren"  id="icon-ok" style="width:70px;">确认</a>
			<a href="javascript:back();" class="fl queren" id="quxiao" style="width:70px;margin-left:40px;">取消</a>
		</div>
	</div>
</div>
<div class="toumingdu make03" style="display:none;">
	<div class="leca_center01">
		<ul class="fl leca_gangwei">
			<li class="action">岗位</li>
			<li>分类</li>
			<li>公共专项</li>
			<li>基础云学院</li>
			<li>胜任力培训</li>
		</ul>
		<div class="clear"></div>
		<div class="lc_bjt"></div>
		<div class="mt10 tiaojian" style="min-height:40px;margin-left:40px;">
			<p class="fl">
				<span>时间：</span>
				<div class="fl select" >
					<div class="tik_position">
						<b class="ml5">请选择</b>
						<p class="position01"><i class="bjt10"></i></p>
					</div>
					<ul class="fl list" style="display:none;">
						<li>请选择</li>
						<li>衢州市公共卫生素养专项培训平台</li>
						<li>安全100</li>
						<li>山东黄金</li>
					</ul>
				</div>
			
			</p>
			<p class="fl" style="margin-left:40px;">
				<span>课程名称：</span>
				<input type="text" />
			</p>
			<div class="fl cas_anniu">
				<a href="#" class="fl" style="width:70px;margin-left:70px;">查询</a>
			</div>
		</div>
		
		<div class="mt5 kit1_tiaojia">
			<div class="fr cas_anniu">
				<a href="#" class="fl" style="width:80px;margin-right:20px;">授权所选</a>
				<a href="#" class="fl queren" style="width:80px;margin-right:40px;">取消</a>
			</div>
		</div>
		<div class="clear"></div>
		<div class="leqrnc">
			<div class="mt10 collos">
				<table class="table">
					<tr class="tr">
						<th width="10%"><input type="checkbox" />全选</th>
						<th width="10%">序号</th>
						<th width="10%">ID</th>
						<th width="15%">课程名称</th>
						<th width="15%">摘要</th>
						<th width="10%">主讲老师</th>
						<th width="20%">创建时间</th>
						<th width="10%">课程目录</th>
					</tr>
					<tr class="tr">
						<td width="10%"><input type="checkbox" /></td>
						<td width="10%"></td>
						<td width="10%"></td>
						<td width="15%"></td>
						<td width="15%"></td>
						<td width="10%"></td>
						<td width="20%"></td>
						<td width="10%"></td>
					</tr>
					<tr class="tr">
						<td width="10%"><input type="checkbox" /></td>
						<td width="10%"></td>
						<td width="10%"></td>
						<td width="15%"></td>
						<td width="15%"></td>
						<td width="10%"></td>
						<td width="20%"></td>
						<td width="10%"></td>
					</tr>
				</table>
			</div>
			<div class="mt10 collos" style="display:none;">
				<table class="table">
					<tr class="tr">
						<th width="10%"><input type="checkbox" />全选</th>
						<th width="10%">序号</th>
						<th width="10%">ID</th>
						<th width="15%">课程名称</th>
						<th width="15%">摘要</th>
						<th width="10%">主讲老师</th>
						<th width="20%">创建时间</th>
						<th width="10%">课程目录</th>
					</tr>
					<tr class="tr">
						<td width="10%"><input type="checkbox" /></td>
						<td width="10%"></td>
						<td width="10%"></td>
						<td width="15%"></td>
						<td width="15%"></td>
						<td width="10%"></td>
						<td width="20%"></td>
						<td width="10%"></td>
					</tr>
					<tr class="tr">
						<td width="10%"><input type="checkbox" /></td>
						<td width="10%"></td>
						<td width="10%"></td>
						<td width="15%"></td>
						<td width="15%"></td>
						<td width="10%"></td>
						<td width="20%"></td>
						<td width="10%"></td>
					</tr>
					<tr class="tr">
						<td width="10%"><input type="checkbox" /></td>
						<td width="10%"></td>
						<td width="10%"></td>
						<td width="15%"></td>
						<td width="15%"></td>
						<td width="10%"></td>
						<td width="20%"></td>
						<td width="10%"></td>
					</tr>
				</table>
			</div>
			<div class="mt10 collos" style="display:none;">
				<table class="table">
					<tr class="tr">
						<th width="10%"><input type="checkbox" />全选</th>
						<th width="10%">序号</th>
						<th width="10%">ID</th>
						<th width="15%">课程名称</th>
						<th width="15%">摘要</th>
						<th width="10%">主讲老师</th>
						<th width="20%">创建时间</th>
						<th width="10%">课程目录</th>
					</tr>
					<tr class="tr">
						<td width="10%"><input type="checkbox" /></td>
						<td width="10%"></td>
						<td width="10%"></td>
						<td width="15%"></td>
						<td width="15%"></td>
						<td width="10%"></td>
						<td width="20%"></td>
						<td width="10%"></td>
					</tr>
					<tr class="tr">
						<td width="10%"><input type="checkbox" /></td>
						<td width="10%"></td>
						<td width="10%"></td>
						<td width="15%"></td>
						<td width="15%"></td>
						<td width="10%"></td>
						<td width="20%"></td>
						<td width="10%"></td>
					</tr>
					<tr class="tr">
						<td width="10%"><input type="checkbox" /></td>
						<td width="10%"></td>
						<td width="10%"></td>
						<td width="15%"></td>
						<td width="15%"></td>
						<td width="10%"></td>
						<td width="20%"></td>
						<td width="10%"></td>
					</tr>
				</table> 
			</div>
			<div class="mt10 collos" style="display:none;">
				<table class="table">
					<tr class="tr">
						<th width="10%"><input type="checkbox" />全选</th>
						<th width="10%">序号</th>
						<th width="10%">ID</th>
						<th width="15%">课程名称</th>
						<th width="15%">摘要</th>
						<th width="10%">主讲老师</th>
						<th width="20%">创建时间</th>
						<th width="10%">课程目录</th>
					</tr>
					<tr class="tr">
						<td width="10%"><input type="checkbox" /></td>
						<td width="10%"></td>
						<td width="10%"></td>
						<td width="15%"></td>
						<td width="15%"></td>
						<td width="10%"></td>
						<td width="20%"></td>
						<td width="10%"></td>
					</tr>
					<tr class="tr">
						<td width="10%"><input type="checkbox" /></td>
						<td width="10%"></td>
						<td width="10%"></td>
						<td width="15%"></td>
						<td width="15%"></td>
						<td width="10%"></td>
						<td width="20%"></td>
						<td width="10%"></td>
					</tr>
					<tr class="tr">
						<td width="10%"><input type="checkbox" /></td>
						<td width="10%"></td>
						<td width="10%"></td>
						<td width="15%"></td>
						<td width="15%"></td>
						<td width="10%"></td>
						<td width="20%"></td>
						<td width="10%"></td>
					</tr>
				</table>
			</div>
			<div class="mt10 collos" style="display:none;">
				<table class="table">
					<tr class="tr">
						<th width="10%"><input type="checkbox" />全选</th>
						<th width="10%">序号</th>
						<th width="10%">ID</th>
						<th width="15%">课程名称</th>
						<th width="15%">摘要</th>
						<th width="10%">主讲老师</th>
						<th width="20%">创建时间</th>
						<th width="10%">课程目录</th>
					</tr>
					<tr class="tr">
						<td width="10%"><input type="checkbox" /></td>
						<td width="10%"></td>
						<td width="10%"></td>
						<td width="15%"></td>
						<td width="15%"></td>
						<td width="10%"></td>
						<td width="20%"></td>
						<td width="10%"></td>
					</tr>
				</table>
			</div>
		</div>
		<!-- 分页 -->
			<div class="clear"></div> 
			<div class="fenye">
				<ul class="fl fen_ul1">
					<li><a href="#">上一页</a></li>
					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					
					<li><a href="#">...</a></li>
					<li><a href="#">下一页</a></li>
				</ul>
				<p class="fl ml10 fen_ul2">共20页</p>
			</div>
	</div>
</div>
<div id="dlgCourseType" class="easyui-dialog" title="查找课程分类" closed="true">
		<div>
			<div style="display: inline-block;width:280px;float:left;">
				<div style="font-size:14px;font-weight:bolder;padding: 0 0 5px 10px;">
				  &nbsp;
				</div>
				<ul id="menuTree" class="easyui-tree" style="float:left;width:97%;margin-left:5px;">	
			</div>
		</div>
	</div>

<script type="text/javascript">

	var url="${ctx}/course/studyCourseMenu.do";
	var params = "";

	 $.ajax({
	 	url:url,
	 	type:'POST',
	 	data: params,
	 	dataType:'json',
	 	success:function(array){
	 		if(array!=""){
						
	 		}
	 	}
	 });

function back(){
	window.history.back();
}

$(function() {
	    	
	$('.xuexike').click(function(){
		$('.make02').show();
	});
	$('.queren').click(function(){
		$('.make02').hide();
	});
	$('.leca_list').click(function(){
		$('.make03').show();
	});
	$('.queren').click(function(){		
		$('.make03').hide();
	});
	/* $('.queren').click(function(){
		$('.make02').hide();
		$('.make03').hide();
	});
	
	$('.leca_gangwei>li').click(function(){
		$(this).addClass('action').siblings('.action').removeClass('action');
		var n=$(this).index();
		$('.leqrnc div').eq(n).show().siblings().hide();
	}); */
	
	     
		   	//树形
		 	/* $('#menuTree').tree({
		 		checkbox:true,
		 		cascadeCheck:false,//定义是否支持级联选择。
		 		onlyLeafCheck:true,//定义是否只在叶子节点之前显示复选框。
		 			url:'${ctx}/course/studyCourseMenu.do',
		 			onClick:function(node){
		 				
		 			},
		 			onBeforeLoad : function(node, param) {
		 			if (node) {
		 				$(this).tree('options').url = '${ctx}/course/studyCourseMenu.do?parentId=' + node.id;
		 			} else {
		 				$(this).tree('options').url = '${ctx}/course/studyCourseMenu.do';
		 			}
		 		}
		 	}); 
		 	
		 	$( "#dlgCourseType" ).dialog({
			  autoOpen: false,
		      height: 300,
		      width: 320,
		      modal: true,//true遮蔽
		      iconCls:'icon-search', //左上角图标,icon文件夹下图标
		      buttons: [{ 
						text: '提交', 
						iconid: 'icon-ok', 
						handler: function() { 
							getChecked(); //选中
							//$('#dlgCourseType').dialog( "close" );
						} 
						}, { 
						text: '取消', 
						iconCls: 'icon-cancel', 
						handler: function() { 
							$("#dlgCourseType").dialog( "close" ); 
						} 
						}] ,
		      close: function() {
		        allFields.val( "" ).removeClass( "ui-state-error" );
		      }
		    }); */
	  });

	//[查询] 
	function query() {
		$('#search').submit();
	}
	
	//选择课程分类
	function add(typeId){
		
		//$( "#dialogFrame" ).attr('src','${ctx}/page/systemCard/sub/authorize-course-index.jsp?cardTypeId='+typeId);
		//$('#dlg').dialog('open');
		
		$('#dlgCourseType').dialog('open');
	}
	
	//选择课程
	function addCourse(typeId){
		$( "#dialogFrame" ).attr('src','${ctx}/cardManage/systemCard/sub/authorize-course-index.jsp?cardTypeId='+typeId);
		$('#dlg').dialog('open');
	}

	//解除授权
	function deleteCourseAuthorized(typeId, courseId){
		if(confirm("确认解除吗?")){
			var p = {'typeId':typeId,'courseId':courseId};
			$.post("${ctx }/cardManage/SystemCardType.do?method=deleteCourseAuthorized", p,
	  			function(data){
	  				if(data >0){
	  					alert("解除成功!");
	  				}
	  				else{
	  					alert("由于网络原因,解除不成功,请稍候再试!");
	  				}
	  				window.location.reload(true);
	  		});
		}
	}

	
	//取得选中IDs
	function getChecked(){
		//var nodes = $('#menuTree').tree('getChecked');
		var nodes = $("input:checked");
		var s = '';
		for(var i=0; i<nodes.length; i++){
			if (s != '') s += ',';
			s += nodes[i].value;
		}
		if(s == ''){
			alert("亲，你还没选择课程分类呢！");
			return;
		}
		//$('#dlgCourseType').dialog( "close" );
		
		//授权
		var typeId = '${typeId}';
		var p = {'cardTypeId':typeId,'courseTypeIds':s};
		
		$.post("${ctx }/cardManage/SystemCardType.do?method=batchCourseAuthorized", p,
  			function(data){
  				if(data >0){
  					alert("授权成功!");
  				}
  				else if(data == 0){
  					alert("由于网络原因,授权不成功,请稍候再试!");
  				}else {
  					alert("该分类下没有要授权的课程!");
  				}
  				window.location.reload(true);
  		});

	}

function doRefresh(){
	window.location.reload(true);
}


</script>
</body>
</html>