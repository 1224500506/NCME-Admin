<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>培训平台-机构管理</title>
		<%@include file="/commons/taglibs.jsp"%>
	</head>

<body>

<div class="layui-layer layui-layer-page layui-layer-rim layer-anim" type="page" times="3" showtime="0" contype="tring"
	style="z-index: 19891017; width:100px; height: 400px" >
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl" style="margin:0 20px 20px 0;"> 
			<span style="width:5em;text-align:right;">机构全称：</span>
			<input type="text" name="model.name" value="${item.name}"/> 
		</p> 
		<p class="fl" style="margin-bottom:20px;"> 
			<span style="width:5em;text-align:right;">地区：</span> 
		</p>
	 
		<select style="width:205px;margin:0 20px 20px 0" class="fl select" name="model.region1_id" id="region1_add"> 
			<option value=0 >省/直辖市</option>
			<c:forEach items="${region1list}" var="region1">
					<option value="${region1.prop_val_id}" <c:if test="${region1.prop_val_id==item.region1_Id}">selected</c:if>>${region1.name}</option>
			</c:forEach>
		</select>
	
		<select style="width:205px;margin:0 20px 20px 0" class="fl select" name="model.region2_id" id="region2_add"> 
			<option value=0 selected class="tik_position">市</option> 
		</select> 
			
		<div class="clear"></div>  
		<p class="fl"> 
			<span style="width:5em;text-align:right;" >机构类型：</span> 
		</p> 	
		<select style="width:205px;margin:0 20px 20px 0" class="fl select" name="model.type" value="${item.type}"> 
			<option value=0 selected>全部</option> 
			<option value=1>医院</option> 
			<option value=2>学会</option> 
		</select> 
			
		<p class="fl"> 
		<span style="width:5em;text-align:right;">机构级别：</span> 
		</p> 
		<select style="width:205px;margin:0 20px 20px 0" class="fl select" name="model.level" value="${item.level}">  
			<option value=0 selected >全部</option> 
			<option value=1>三甲</option> 
			<option value=2>三乙</option> 
			<option value=3>二甲</option> 
			<option value=4>二乙</option> 
		</select> 
			
		<div class="clear"></div> 
		<p class="fl" style="margin:0 20px 20px 0;"> 
			<span style="width:5em;text-align:right;">联系人：</span> 
			<input type="text"  name="model.contact" id="item.contact"/> 
		</p> 
			
		<p class="fl" style="margin-bottom:20px;"> 
			<span style="width:5em;text-align:right;">联系电话：</span> 
		<input type="text"  name="model.phonenumber" id="item.phonenumber"/> 
		</p> 
		<div class="clear"></div> 
		<p class="fl">
			<span style="width:5em;text-align:right;">机构简介：</span>
			<textarea name="" style="width:500px;" rows="5"></textarea>
		</p>  
	</div> 
	<div class="layui-layer-btn">
		<a class="layui-layer-btn0" href="${ctx}/system/peixunOrglist.do?method=add">保存</div>
		<a class="layui-layer-btn1" href="${ctx}/system/peixunOrglist.do?method=list">取消</div> 	
	</div>		
</div>

	
	$(".add_btn").click(function() {
		layer.open({
			type: 1,
			title: "添加机构",
			skin: layui-layer-rim, //加上边框
			area: [win_w, win_h], //宽高
			content: add_cont,
			closeBtn: 0,
			btn: [保存, 取消],
			yes: function (index, layero) {
				//缩写保存功能
				layer.close(index);
			},
			btn2: function (index, layero) {
				layer.close(index);
			},
			success: function(layerb, index){
				$(.select).click(function(){
					$(.list).css(display","none);
					$(this).find(ul).show();
				});
				$(.list li).click(function(){
					var str=$(this).text();
					$(this).parent().parent().find(div).find(b).text(str);
					$(.list).slideUp(50);
				});
				$(.select).click(function(e){
					return false
				});
				$(document).click(function(){
					$(.list).hide(fast);
				});
				$(".btn1").click(function(){
					layer.close(index);
				});
			}
		});
	});
	$(".edit_btn").click(function() {
		layer.open({
			type: 1,
			title: "修改人物画像类型",
			skin: layui-layer-rim, //加上边框
			area: [win_w, win_h], //宽高
			content: edit_cont,
			closeBtn: 0,
			btn: [保存, 取消],
			yes: function (index, layero) {
				//缩写保存功能
				layer.close(index);
			},
			btn2: function (index, layero) {
				layer.close(index);
			},
			success: function(layerb, index){
				$(.select).click(function(){
					$(.list).css(display","none);
					$(this).find(ul).show();
				});
				$(.list li).click(function(){
					var str=$(this).text();
					$(this).parent().parent().find(div).find(b).text(str);
					$(.list).slideUp(50);
				});
				$(.select).click(function(e){
					return false
				});
				$(document).click(function(){
					$(.list).hide(fast);
				});
				$(".btn1").click(function(){
					layer.close(index);
				});
			}
		});
	});
});
</script>


</body>
</html>