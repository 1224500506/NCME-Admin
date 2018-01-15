<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="robots" content="index,follow">
    <meta name="apple-mobile-web-app-title" content="继续再教育平台">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="keyword" content="">
    <meta name="description" content="">
    <title>中国继续医学教育网</title>
    <%@include file="/commons/taglibs.jsp"%>
    <script type="text/javascript" src="${ctx}/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.mobile.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.bxslider.js"></script>
    <script type="text/javascript" src="${ctx}/js/iconfont.js"></script>
    <script type="text/javascript" src="${ctx}/js/main.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/swfobject.js"></script>
    
    <link href="${ctx}/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/new_page/css/global.css?rev=d0180079bf5750e145db7c5b30055926" rel="stylesheet">
    <link href="${ctx}/css/rating.css?rev=13b658db2ab0d58477058a850227b6b6" rel="stylesheet">
   <link href="${ctx}/new_page/css/iconfont.css" rel="stylesheet">  
  
  <style type="text/css">
  
  .box{
height: 88px;
position:  fixed;
width: 70%;
z-index: 10;


}
#labcard{
font-weight: bold;
}
#labpwd{
font-weight: bold;
}
.bgc{
    width: 100%;
    height: 100%;
    background-color: rgba(51, 51, 51, 0.5);
    position:absolute;
    top: 0;
    left: 0;
    z-index: 100;
    display: none;
    
}

  </style>
  
</head>
<body class="view">
<div class="bgc"></div>
<div class="container" style="margin:0 auto;margin-left:180px;">
    <div class="course_video" style="position:relative;">
        <span class="logo"></span>        
       <div class="box">
        <div class="course_title" style="display:block;">
            <h2 id="className">${pmname}</h2>
            <h3>
                <span id="classType">学科：${pmxueke}</span><span id="quota"></span>
                <span id="classTeacher">素材类型：${pmsucaitype}</span>
                <span id="classDB">专家：${pmzhuan}</span>
            </h3>
        </div>
       </div> 
        
        <div id="preview" style="width:100%;height:90%;background:#000;color:#FFF;margin-top:100px;box-sizing:border-box;  text-align: center;">
            <!-- 封面信息 -->
            <c:if test="${fengmian==null || fengmian==''}">
               <img src="${ctx}/images/proLogo.jpg" name="fengmian" id="fengmian" style="width:100%;height:60%;margin-top:11%;">
            </c:if>
            <c:if test="${fengmian!=null && fengmian!=''}">
               <img src="${fengmian}" name="fengmian" id="fengmian" style="height:100%">
            </c:if> 
           
            
        </div>
        <div id="swfDiv" style="display:none;margin-left:10px;width:100%;height:94%;position:absolute;z-index:99999;top:100px;left:0;margin-bottom:30px;"></div> 
    </div>
    
</div>
<script>
    //设置资源管理系统全局变量
   var adminURL = "${ctxAdminURL}";//"http://101.200.85.213:8090/admin"; 
   basePath="${ctx}";
    
    var vFlag = false ; //YHQ，2017-03-08，是否视频
    var vPlayFlag = false ; //YXT，2017-07-13，是否开始观看视频（过滤广告时间）    
	var currUnitMediaTypeVal;//当前单元媒体类型
	var currUnitMediaIdVal = '${currUnitMediaIdVal}';//当前单元媒体id
		//2.切换到下一单元，5.判断单元里的素材类型			
        //if (currUnitMediaTypeVal != '') {//null为无类型,paper为试卷,talk为讨论点,bingli为病例,video为视频
    	  //先清空div内容
    	  $("#preview").empty();
    	  //隐藏欢迎页面
    	  $("#fengmian").css("display","none");    	  
    	  try {        	   
        	   $("#playerswf").remove();
        	   $("#swfDiv").css("display","none");
          }catch(e){}
          
          //先设置显示区颜色
          $("#preview").css("background","black");
          //先清空div内容
    	  $("#preview").empty();
    	  //隐藏欢迎页面
    	  $("#fengmian").css("display","none");
    	          	
          //2.切换到下一单元，7.加载单元信息
          //if (currUnitMediaTypeVal == 'video') {	          	
	          	swfobj = new SWFObject('http://union.bokecc.com/flash/player.swf', 'playerswf', '100%', '88%', '8');
		        swfobj.addVariable( "userid" , "078E396B1332FD8E");	//	partnerID,用户id
		        swfobj.addVariable( "videoid" , currUnitMediaIdVal);	//	spark_videoid,视频所拥有的 api id
		        swfobj.addVariable( "mode" , "api");	//	mode, 注意：必须填写，否则无法播放
		        swfobj.addVariable( "autostart" , "true");	//	开始自动播放，true/false
		        swfobj.addVariable( "jscontrol" , "true");	//	开启js控制播放器，true/false
		        swfobj.addVariable( "control_enable" , "1");       
		        swfobj.addParam('allowFullscreen','true');
		        swfobj.addParam('allowScriptAccess','always');
		        swfobj.addParam('wmode','transparent');       
		        swfobj.write('swfDiv');    
		        $("#swfDiv").css("display","block");
		        $("#preview img").css("display","none");		        
    
    //播放完毕
    function on_spark_player_stop() {
    	 $("#swfDiv").css("display","none");
    	 $("#fengmian").css("display","block");
    }
    
    //暂停播放
    function on_spark_player_pause() {       
    	$("#fengmian").css("display","block");
    }
    
    //开始播放时回调，YHQ，2017-03-08，获取视频长度（秒）
    function on_spark_player_start() {
    }
    
    function disp(){
   	 $(".box").css("background-color","white"); 
    }
    function disn(){
   	 $(".box").css("background-color",""); 
    }
 </script>
			      
     
     
</body>
</html>