var mouseX = 0;
var mouseY = 0;
$(function(){
	$('.tik_biaoti').mousedown(function(e){		
		mouseX = e.pageX;
		mouseY = e.pageY;
		offset = $('.tik_biaoti').position();
		divX = offset.left;
		divY = offset.top;
		divmouseX = mouseX - divX;
		divmouseY = mouseY - divY;
	
		$('.att_make01').mousemove(function(e){
			offsetX = e.pageX - divmouseX;
			offsetY = e.pageY - divmouseY;
			$('.tk_center09').css("margin-left", offsetX+'px');
			$('.tk_center09').css("margin-top", offsetY+'px');	
		});
		$('.tik_biaoti').mouseup(function(){
			$('.att_make01').unbind("mousemove");
		});
	});
	
	$('.TJ_cueke').mousedown(function(e){
		//window.captureEvents(Evnet.MOUSEMOVE);
		mouseX = e.pageX;
		mouseY = e.pageY;
		offset = $('.TJ_cueke').offset();
		divX = offset.left;
		divY = offset.top;
		divmouseX = mouseX - divX;
		divmouseY = mouseY - divY;
		
		if (divmouseY>30) return;
		
		$(this).parent().mousemove(function(e){
			offsetX = e.clientX - divmouseX;
			offsetY = e.clientY - divmouseY;
			$('.TJ_cueke').css("margin-left", offsetX+'px');
			$('.TJ_cueke').css("margin-top", offsetY+'px');	
		});
		
		$(this).parent().mouseup(function(){
			$(this).unbind("mousemove");
		});
	});
});