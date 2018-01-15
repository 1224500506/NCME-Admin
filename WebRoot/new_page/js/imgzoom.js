var isopen = false;
          var newImg;
          var isclose = 0;
          var w = 200;  //将图片宽度+200
          var h = 200;  // 将图片高度 +200
          $(document).ready(function(){
        	  
        	  $(".zoom").bind("click", function(){
            		 newImg = this;
            		 if (!isopen )
            		 {
            		    isopen = true; 
            		    $(this).width($(this).width() + w);
                	    $(this).height($(this).height() + h);
                	    moveImg(10, 10);
                	    $(this).css("z-index","2");
                	    $("#shade").show();
                	    $(this).parent().siblings().find(".zoom").css("z-index","0");
          
            		 }
            		 else
            		 {
            			isopen = false;
            			$(this).width($(this).width() - w);
            			$(this).height($(this).height() - h);
            			$(this).siblings().on("click");
            			moveImg(-10, -10);
            			$("#shade").hide();
            			
            		 }
            		
            	 });
        	  
          });
          //移位
          i = 0;
           function moveImg(left,top)
          {
          	var offset = $(newImg).offset();
          	$(newImg).offset({ top: offset.top + top, left: offset.left + left});
          	if (i == 10) 
          	{
          		i =0;
          		return;
          	}
          	setTimeout("moveImg("+left+","+top+")", 10);
          	i++;
          }

		  //<div id="imgBox" style="width:100px; height:100px; background:#cccccc">  
		  // </div>  