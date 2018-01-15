<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.bokecc.config.*"%>
<!DOCTYPE HTML>
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <%@include file="/commons/taglibs.jsp"%>
    <script type="text/javascript" src="${ctx}/udeitor/dialogs/internal.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/udeitor/dialogs/video/video.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/udeitor/third-party/webuploader/webuploader.css">
    <script type="text/javascript" src="${ctx}/udeitor/third-party/jquery-1.10.2.min.js"></script>
    <style type="text/css">
      .strTable{width:95%;height:100%;margin:20 auto;border:solid 1px #ADADAD}
      .strTable td{border:solid 1px #ADADAD;}
    </style>
</head>
<body>
<div class="wrapper" style="width:95%;">
    <div id="videoTab">
        <div id="tabBodys" class="tabbody" style="height:95%;">
            <div id="video" class="panel focus" style="height:99%;">
               <table id="strTable" class="strTable">
                  <tr style="height:30px;background-color:#F2F2F2;">
                    <td style="text-align:center;width:5%;">id</td>
                    <td style="text-align:center;width:10%;">名称</td>
                    <td style="text-align:center;width:10%;">文件名</td>
                    <td style="text-align:center;width:10%">路径</td>
                    <td style="text-align:center;width:10%;">操作</td>
                  </tr>
               </table>
               <table id="stbTable" style="display:none;"><tr><td><label for="videoUrl" class="url"><var id="lang_video_url"></var></label></td><td><input id="videoUrl" type="text"></td></tr></table>
               <div id="preview" style="display:none;"><img src="" id="vimg" style="width:100%;height:100%;"></div>
               <div id="videoInfo" style="display:none;">
                   <fieldset>
                       <legend><var id="lang_video_size"></var></legend>
                       <table>
                           <tr><td><label for="videoWidth"><var id="lang_videoW"></var></label></td><td><input class="txt" id="videoWidth" type="text"/></td></tr>
                           <tr><td><label for="videoHeight"><var id="lang_videoH"></var></label></td><td><input class="txt" id="videoHeight" type="text"/></td></tr>
                       </table>
                   </fieldset>
                   <fieldset>
                      <legend><var id="lang_alignment"></var></legend>
                      <div id="videoFloat"></div>
                  </fieldset>
               </div>
            </div>
            <!--<div id="upload" class="panel">
                <div id="upload_left">
                    <div id="queueList" class="queueList">
                        <div class="statusBar element-invisible">
                            <div class="progress">
                                <span class="text">0%</span>
                                <span class="percentage"></span>
                            </div><div class="info"></div>
                            <div class="btns">
                                <div id="filePickerBtn"></div>
                                <div class="uploadBtn"><var id="lang_start_upload"></var></div>
                            </div>
                        </div>
                        <div id="dndArea" class="placeholder">
                            <div class="filePickerContainer">
                                <div id="filePickerReady"></div>
                            </div>
                        </div>
                        <ul class="filelist element-invisible">
                            <li id="filePickerBlock" class="filePickerBlock"></li>
                        </ul>
                    </div>
                </div>
                <div id="uploadVideoInfo">
                    <fieldset>
                        <legend><var id="lang_upload_size"></var></legend>
                        <table>
                            <tr><td><label><var id="lang_upload_width"></var></label></td><td><input class="txt" id="upload_width" type="text"/></td></tr>
                            <tr><td><label><var id="lang_upload_height"></var></label></td><td><input class="txt" id="upload_height" type="text"/></td></tr>
                        </table>
                    </fieldset>
                    <fieldset>
                        <legend><var id="lang_upload_alignment"></var></legend>
                        <div id="upload_alignment"></div>
                    </fieldset>
                </div>
            </div>-->
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/udeitor/third-party/webuploader/webuploader.min.js"></script>
<script type="text/javascript" src="${ctx}/udeitor/dialogs/video/video.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//执行AJAX请求视频素材库
		$.ajax({
			type: 'POST',
			url: "${ctx}/material/MaterialManage.do?mode=listJSON",
			dataType: 'json',
			success : function (data){
				var da = eval(data);
				//拼接字符串
				if(da!=null && da.length>0){
					for(var i = 0 ; i<data.length;i++){
						var savePath = data[i].savePath;
						var str = "<tr style='height:20px;'>"
						          +"<td style='text-align:center;'>"+data[i].id+"</td>" 
						          +"<td style='text-align:center;'>"+data[i].name+"</td>"
						          +"<td style='text-align:center;'>"+data[i].fileName+"</td>"
						          +"<td style='text-align:center;'>"+data[i].savePath+"</td>"
						          +"<td style='text-align:center;'><a href='javascript:selectSavePath("+savePath+");'>选择</a></td>"
						          +"</tr>";
						$("#strTable").append(str);
					}
				}else{
					$("#strTable").append("<tr><td colspan='5' style='height:50px;text-align:center;'>暂无视频素材!</td></tr>");
				}
			}
		});
	}); 
	
	function selectSavePath(obj){
		if(obj!=null){
			$.ajax({
				async: false, 
				type: 'POST',
				url: "${ctx}/material/MaterialManage.do?mode=ccVideo",
				data:{videoid:obj},
				dataType: 'json',
				success : function (data){
					if(data!=null){
						var data = eval(data);
						var conte = eval(data.video);
						//显示层级
						$("#strTable").css("display","none");
						$("#stbTable").css("display","block");
						$("#preview").css("display","block");
						$("#videoInfo").css("display","block");	
						//写入脚本文件
						
						var str = "var swfobj=new SWFObject('http://union.bokecc.com/flash/player.swf?userid=078E396B1332FD8E&videoid=51071753DF02D8F29C33DC5901307461', 'playerswf', '400', '300', '8');"
                                 +"swfobj.addVariable( 'userid' , '078E396B1332FD8E');"
                                 +"swfobj.addVariable( 'videoid' , "+conte.id+");"
                                 +"swfobj.addVariable( 'mode' , 'api');"
                                 +"swfobj.addVariable( 'autostart' , 'false');"
                                 +"swfobj.addVariable( 'jscontrol' , 'true');"
                                 +"swfobj.addParam('allowFullscreen','true');"
                                 +"swfobj.addParam('allowScriptAccess','always');"
                                 +"swfobj.addParam('wmode','transparent');"
                                 +"swfobj.write('swfDiv');";
						var sth= document.createElement("script");
						sth.type = "text/javascript";
						sth.innerHTML = str;   
						alert(str);
						$("#videoUrl").val(sth);
						$("#vimg").attr("src",conte.image);
					}
				}
			});
			
		}else{
			alert("获取视频地址异常!");
		}
	}
</script>
</body>
</html>