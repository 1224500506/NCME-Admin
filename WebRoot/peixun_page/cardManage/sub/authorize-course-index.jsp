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
<script type="text/javascript">
$(function(){	
	
	$('.queren').click(function(){		
		$('.make03').hide();
	});
	$('.leca_list').click(function(){
		$('.make03').show();
	});
	$('.leca_gangwei>li').click(function(){
		$(this).addClass('action').siblings('.action').removeClass('action');
		var n=$(this).index();
		$('.leqrnc div').eq(n).show().siblings().hide();
	});
	

})
</script>
</body>
</html>
