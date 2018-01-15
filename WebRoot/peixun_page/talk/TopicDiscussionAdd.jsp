<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="UTF-8">
	    <title></title>
	    <%@include file="/commons/taglibs.jsp"%>
		<link rel="stylesheet" href="${ctxAll}/peixun_page/css/reset.css" />
		<link rel="stylesheet" href="${ctxAll}/peixun_page/css/index.css" />
		<script type="text/javascript" src="${ctxAll}/peixun_page/js/jquery.js"></script>
		<script src="${ctxAll}/js/jquery-1.11.1.min.js"></script>
		<script src="${ctxAll}/js/jquery.mobile.custom.min.js"></script>
		<script src="${ctxAll}/js/jquery.bxslider.js"></script>
		<script src="${ctxAll}/js/iconfont.js"></script>
		<script src="${ctxAll}/js/main.min.js"></script>
		<link href="${ctxAll}/peixun_page/css/animate.min.css" rel="stylesheet">
		<link href="${ctxAll}/peixun_page/css/jquery.bxslider.css" rel="stylesheet">
		<link href="${ctxAll}/peixun_page/css/global.css" rel="stylesheet">
	</head>
<body>
	<form id="fm1" name="fm1" method="post">
	<div class="center" style="">
	<div id = "tabs-1">
		<div class="clear"></div>
		<div class="thi_shitineirong ca1_jichu" style="width:940px;margin:0 auto;">
			<ul style="margin-top:30px;">
				<li style="margin-top:20px;">
					<div class="fl shitin_xinzeng01">
						<p class="fl"><span class="fr"><i style="color:red;">*</i>讨论标题：</span></p>
						<input name="talkContent" type="text" id="talkContent" class="fl tki_bianji"/>
					</div>
					<div class="clear"></div>
				</li>
				<li style="margin-top:5px;">
					<div class="fl shitin_xinzeng01">
						<p class="fl"><span class="fr"><i style="color:red;">*</i>讨论解析：</span></p>
						<p class="fl" style="width:600px;">
							<textarea  id="talkAnsy" name ="talkAnsy" style="width: 600px; height: 240px;"></textarea>
						</p>
					</div>
					<div class="clear"></div>
				</li>
				<li style="margin-top:15px;">
					<div class="cas_anniu" style="width:70px;margin:40px auto;">
					<a href="javascript:void(0);" class="fl queren" 
					style=" width:70px; width: 70px;
    				margin-top: 167px;
    				position: fixed;
    				margin-left: 337px;
    				z-index: 2;" onclick="subTalk();">确定</a>
    				<a href="javascript:void(0);" class="fl queren" 
					style=" width:70px; width: 70px;
    				margin-top: 167px;
    				position: fixed;
    				margin-left: 423px;
    				z-index: 2;" onclick="closew();">取消</a>
					</div>
					<div class="clear"></div>
			</li>
			</ul>
			<div class="clear"></div>
		</div>
	</div>
	</div>
	</form>
	<script type="text/javascript">
	
	function closew(){
		 //dialog.close();
	  var dialog = window.parent.ue.getDialog("gmap");
	  dialog.close();
	}
	
	
	  /*
	   * @author 张建国
	   * @time   2017-02-24
	   * 说      明：提交讨论点表单
	   */
	   
	   
	   
	   
	  function subTalk(){

		  //判断非空
		  if($("#talkContent").val()==''){
			  alert("请输入讨论点标题.");
			  return;
		  }
		  if($("#talkAnsy").val()==''){
			  alert("请输入讨论点解析.");
			  return;
		  }
		/*   if($("#createDate").val()==''){
			  alert("请输入创建时间.");
			  return;
		  }
		  if($("#createUser").val()==''){
			  alert("请输入创建人名称.");
			  return;
		  } */
		  //通过AJAX方式插入讨论点
		  $.ajax({
      		url:"${ctx}/talk/topicDiscussionManage.do?handle=saveOneTopic",
   		    type: 'POST',
   		    dataType: 'json',
   		    data:{
   		    	talkContent:$("#talkContent").val(),
   		    	talkAnsy:$("#talkAnsy").val(),
   		    	/* createDate:$("#createDate").val(),
   		    	createUser:$("#createUser").val() */
   		    },
    		success: function(data){   
    		    	var result = eval(data);
    		    	if(result.message=='success'){
    		    		//获取已经保存的讨论点Id
    		    		select(result.talkId);
    		    	}
    		    }
    		});
	  }
	  
	  /*
		 * @author 张建国
		 * @time   2017-02-23
		 * @param  id
		 * 说     明： 选中讨论点
		 */
		 function select(talkId){
		  
		    var content = $("#talkContent").val();  
		  
			if(talkId!='' && talkId!=null){
				 var html = "<img src=\"${ctxAll}/images/talk.jpg\" width=\"100\" height=\"100\" _url='"+talkId+"' alt=\"talk\" onclick=\"preview("+talkId+",'talk');\"></br><p style=\"font-size:15px;\">"+content+"</p>";
			     window.parent.ue.execCommand( 'inserthtml', html );
			     var dialog = window.parent.ue.getDialog("gmap");
			     //判断该单元任务点是否选中
			     if(window.parent.isRWD=='true'){
					//调用父页面的视频完成指标函数
					window.parent.comTaolun();
				 }
			     dialog.close();
			}
		}
	  

	</script>
</body>
</html>