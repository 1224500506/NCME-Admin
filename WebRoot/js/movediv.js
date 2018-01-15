var mouseX = 0;
var mouseY = 0;
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