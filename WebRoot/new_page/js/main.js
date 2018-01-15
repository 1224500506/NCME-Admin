//头部
$(function(){
	
	//页面初始化
	index.init();
})
var index={
	init :function(){
		//调用的方法
		this.tophtml();
		this.navmousemove();
		
	
	
	
	},
	//headerhtml
	tophtml : function(){
				html='';
				html+='<div style="margin:0 auto;width:92%;">'
				html+='<div class="fl mt5 tk_left">'
				html+='<img src="images/logo1.png" alt="" />'
				html+='</div>'
				html+='<div class="fl tk_center">'
				html+='<ul>'
				html+='<li>属性管理</li>'
				html+='<li>题库管理</li>'
				html+='<li>素材管理</li>'
				html+='<li>专家管理</li>'
				html+='<li>病例管理</li>'
				html+='<li>系统维护</li>'
				html+='</ul>'
				html+='</div>'
				
				
				html+='<div class="fr mr20 lc_right">'
				html+='<p><i class="fl lc_bjt07"></i><span class="fl">管理员</span></p>'
				html+='<div class="clear"></div>'
				html+='<div class="guanliyuan"  style="display:none;">'
				html+='<a href="#" class="password">修改密码</a>'
				html+='<a href="landing.html">退出系统</a>'
				html+='</div>'
				html+='</div>'
				$('.header').append(html);
	
			},

	navmousemove : function(){
				//导航切换
				$('.tk_center>ul>li').mousemove(function(){
					$(this).addClass('action').siblings('.action').removeClass('action');
					var n=$(this).index();
					$('.tk_er div').eq(n).show().siblings().hide();
				});
				$('.lc_right p').mousemove(function(){
					$('.guanliyuan').show();
				});
				$('.lc_er').mouseleave(function(){
					$('.guanliyuan').hide();
				});

			},
	



}

















































































































































































