<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>视频播放</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <%@include file="/commons/taglibs.jsp"%>
        <script type="text/javascript" src="${ctx}/js/swfobject.js"></script>
    </head>
    <body>
       <div id="swfDiv"></div>
    </body>
    <script>
        var swfobj=new SWFObject('http://union.bokecc.com/flash/player.swf?userid=078E396B1332FD8E&videoid=034282A1F1B06B299C33DC5901307461 ', 'playerswf', '80%', '500', '8');
    	swfobj.addVariable( "userid" , "078E396B1332FD8E");	//	partnerID,用户id
    	swfobj.addVariable( "videoid" , "034282A1F1B06B299C33DC5901307461");	//	spark_videoid,视频所拥有的 api id
    	swfobj.addVariable( "mode" , "api");	//	mode, 注意：必须填写，否则无法播放
    	swfobj.addVariable( "autostart" , "true");	//	开始自动播放，true/false
    	swfobj.addVariable( "jscontrol" , "true");	//	开启js控制播放器，true/false
    	swfobj.addParam('allowFullscreen','true');
    	swfobj.addParam('allowScriptAccess','always');
    	swfobj.addParam('wmode','transparent');
    	swfobj.write('swfDiv');
    	
    	var gobalObj;
        
        //点击播放
        function play(obj){
           $("#swfDiv").css("display","block");
           $(obj).css("display","none");
           gobalObj = obj;
           document.getElementById("playerswf").spark_player_start();
        }
        
        //播放完毕
        function on_spark_player_stop() {
        	 $("#swfDiv").css("display","none");
             $(gobalObj).css("display","block");
    	}
        
        //暂停播放
        function on_spark_player_pause() {
        	$("#swfDiv").css("display","none");
            $(gobalObj).css("display","block");
    	}
    </script>
</html>