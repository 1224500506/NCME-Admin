<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
	<%@ include file="/commons/taglibs.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Advanced Edit</title>
		<script type="text/javascript" src="${ctx}/fckeditor/fckeditor.js"></script>
<style type="text/css">
#red{
  COLOR:#FF0000;
  FONT-SIZE:12px; 
}
.body1{
  BACKGROUND-COLOR:#FFFFFF;
  MARGIN-LEFT:0px;
  MARGIN-RIGHT:0px;
  MARGIN-TOP:0px;
  MARGIN-BOTTOM:0px;
  OVERFLOW:HIDDEN;
}
.but2 {
BORDER-RIGHT: #7b9ebd 1px solid;
PADDING-RIGHT: 2px;
BORDER-TOP: #7b9ebd 1px solid;
PADDING-LEFT: 2px;
FONT-SIZE: 12px;
FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#cecfde);
BORDER-LEFT: #7b9ebd 1px solid;
CURSOR: hand;
COLOR: black;
PADDING-TOP: 2px;
BORDER-BOTTOM: #7b9ebd 1px solid
}
.input_style {
border:1px solid #4682B4;
font-family:Arial, Helvetica, sans-serif;
font-size:12px;
cursor:hand;
}
a img{
  BORDER-RIGHT:0px; 
  BORDER-TOP:0px; 
  BORDER-LEFT:0px; 
  BORDER-BOTTOM:0px
}
a:hover{
  FONT-SIZE:12px; 
  COLOR:#ff0000; 
  TEXT-DECORATION:none
}
a:link{
  FONT-SIZE:12px; 
  COLOR:#000000; 
  TEXT-DECORATION:none
}
a:visited{
  FONT-SIZE:12px; 
  COLOR:#000000; 
  TEXT-DECORATION:none
}
a:active{
  FONT-SIZE:12px; 
  COLOR:#000000; 
  TEXT-DECORATION:none
}
a.menu:hover{
  FONT-SIZE:12px; 
  COLOR:#FFFFFF; 
  TEXT-DECORATION:none
}
a.menu:link{
  FONT-SIZE:12px; 
  COLOR:#FFFFFF; 
  TEXT-DECORATION:none
}
a.menu:visited{
  FONT-SIZE:12px; 
  COLOR:#FFFFFF; 
  TEXT-DECORATION:none
}
a.menu:active{
  FONT-SIZE:12px; 
  COLOR:#FFFFFF; 
  TEXT-DECORATION:none
}
a.topmenu:hover{
  FONT-SIZE:12px; 
  COLOR:#FFFFFF; 
  TEXT-DECORATION:none
}
a.topmenu:link{
  FONT-SIZE:12px; 
  COLOR:#FFFFFF; 
  TEXT-DECORATION:none
}
a.topmenu:visited{
  FONT-SIZE:12px; 
  COLOR:#FFFFFF; 
  TEXT-DECORATION:none
}
a.topmenu:active{
  FONT-SIZE:12px; 
  COLOR:#FFFFFF; 
  TEXT-DECORATION:none
}
form {
	margin:0;
	padding:0;
}
td {
	font-size:9pt;
	color:#000000;
}
</style>
	</head>
	<body class="body1">
		<table style="width: 600px; height: 430px" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="80%"></td>
				<td width="20%"><a href="#" onclick="rv()">确定</a> | <a href="#" onclick="window.close();">关闭</a></td>
			</tr>
			<tr>
				<td colspan="2">
					<form action="${ctx}/questionManage/uploadFile.do" encType="multipart/form-data" method="post" target="hidden_frame">
						上传附件:<input type="file" name="uploadfile" size="30" class="input_style" />
						&nbsp;<input type="submit" value="上传" class="but2"><br/>
						<span id="red">(文件类型：jpg , gif, wmv, bmp, png, wav, avi 附件不能大于1M )</span>
					</form>
				</td>
			</tr>				
			<tr>
				<td style="width: 600px; height: 430px" valign="top" colspan="2" align="center">
					<form action="result.jsp" method="post" name="mf">
						<textarea rows="2" cols="2" name="Content" id="Content"></textarea>
					</form>
				</td>
			</tr>
		</table>
		<script type="text/javascript">
			function trew(att,type){
				if(type=='wmv'){
					var tmp = "";
					tmp +="<object classid=\"clsid:6BF52A52-394A-11D3-B153-00C04F79FAA6\" codebase=\"http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701\" width=\"200\" height=\"200\">";					
 					tmp +="<param name=\"url\" value=\""+att+"\" />";
  					tmp +="<param name=\"width\" value=\"200\" />";
  					tmp +="<param name=\"height\" value=\"200\" />";
  					tmp +="<param name=\"AUTOSTART\" value=\"0\">";
  					tmp +="<embed type=\"application/x-mplayer2\" src=\""+att+"\" width=\"200\" height=\"200\"></embed>";
 					tmp +="</object>";
					var er = FCKeditorAPI.GetInstance('Content');
					var _tmp = er.GetHTML();
					er.SetHTML(_tmp+tmp, false);
				}
				if(type=='pic'){
					var tmp = "<img src=\'"+att+"\' onload=\'javascript:if(this.width &gt; 200)this.width = 200\' galleryimg=\'no\'>";
					var er = FCKeditorAPI.GetInstance('Content');
					var _tmp = er.GetHTML();
					er.SetHTML(_tmp+tmp, false);				
				}

				/*
				if(type=='swf'){
					var tmp = "<embed width=\"100\" height=\"60\""; 
					tmp +="pluginspage=\"http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash\""; 
					tmp +=" type=\"application/x-shockwave-flash\" swliveconnect=\"TRUE\" name=\"flashad\""; 
					tmp +=" wmode=\"opaque\" quality=\"autohigh\"  src=\""+att+"\"/>";
					var er = FCKeditorAPI.GetInstance('Content');
					var _tmp = er.GetHTML();
					er.SetHTML(_tmp+tmp, false);
				}
				*/
			}
			function rv(){
				var er = FCKeditorAPI.GetInstance('Content');
				var tmp = er.GetXHTML(false);
				returnValue=tmp;
				this.close();
			}
			
			function load(){
				var parentwin = window.dialogArguments;
				var URLParams = new Object();  
				var aParams = document.location.search.substr(1).split('&');
				for (var i=0; i < aParams.length; i++) {  
				    var aParam = aParams[i].split('=');  
				     URLParams[aParam[0]] = aParam[1];  
				}
				var tmp = parentwin.document.getElementById(URLParams["tx"]).value;
				return tmp;
			}
			
			window.onload=function(){
				var oFCKeditor = new FCKeditor('Content');
				oFCKeditor.BasePath = '${ctx}/fckeditor/';
				oFCKeditor.ToolbarSet = 'Exam';
				oFCKeditor.Width = '100%';
				oFCKeditor.Height = '430';
				oFCKeditor.ReplaceTextarea(); 
				document.getElementById('Content').value=load();
			}
		</script>
		<iframe name='hidden_frame' id="hidden_frame" style='display:none'/> 	
	</body>
</html>