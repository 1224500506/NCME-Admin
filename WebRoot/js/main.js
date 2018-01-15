 //头部
$(function(){
	
	//页面初始化
	index.init();
})
var index={
	init :function(){
		//调用的方法
		this.navmousemove();
		this.posdword();
		this.posdwordClick();
		this.xialaClick();
	
	
	
	},
	//导航切换
	navmousemove : function(){
				$('.lc_center>ul>li').mousemove(function(){
					$(this).addClass('action').siblings('.action').removeClass('action');
					var n=$(this).index();
					$('.lc_er div').eq(n).show().siblings().hide();
				});
				$('.lc_right p').mousemove(function(){
					$('.guanliyuan').show();
				});
				$('.lc_er').mouseleave(function(){
					$('.guanliyuan').hide();
				});
	},
	//修改密码
	posdword : function(){
				html='';
				html+='<div class="sy_conter">'
				html+='<div class="con">'
				html+='	<div class="denglu_1">'
				html+='<a class="zhanghao" href="#">修改密码</a>'
				html+='</div>'
				html+='<div class="clear denglu_2">'
				html+='<div class="dl_shoujihao">'
				html+='<em class="fl"></em>'
				html+='<input type="text" placeholder="请输入原密码" class="fl telphoto"/>'
				html+='</div>'
				html+='<p class="clear tishi_1"></p>'
				html+='</div>'
				html+='<div class="clear denglu_3">'
				html+='<div class="dl_shoujihao" style="position:relative;margin-top:-10px;">'
				html+='<em class="fl"></em>'
				html+='<input type="password" placeholder="请设置新登录密码" class="fl telphoto1"/>'
				html+='</div>'
				html+='<p class="clear tishi_3"></p>'
				html+='</div>'
				html+='<div class="clear denglu_3">'
				html+='<div class="dl_shoujihao" style="position:relative;margin-top:-10px;">'
				html+='<em class="fl"></em>'
				html+='<input type="password" placeholder="请在输入密码" class="fl telphoto1"/>'
				html+='</div>'
				html+='<p class="clear tishi_3"></p>'
				html+='</div>'
				html+='<div class="clear denglu">'
				html+='<em href="#" style="margin-top:2px;margin-bottom:20px;" class="queding">提交</em>'
				html+='</div>'
				html+='</div>'
				html+='</div>'
				$('.make01').append(html);
	},
	//导航切换
	posdwordClick : function(){
				$('.password').click(function(){
					$('.make01').show();
				});
				$('.denglu').click(function(){
					$('.make01').hide();
				});
	},	
	//下拉框
	xialaClick : function(){
				$('.select').click(function(){
					 $('.list').css('display","none');
					$(this).find('ul').show();
				});
				$('.list li').click(function(){
					var str=$(this).text();
					$(this).parent().parent().find('div').find('b').text(str);
					$('.list').slideUp(50);
				});
				$('.select').click(function(e){
					return false
				});
				$(document).click(function(){
					$('.list').hide('fast');
				});
	},		


}

















































































































































































