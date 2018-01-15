<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>高级编辑</title>
	<link rel="stylesheet" type="text/css" href="${ctxAll}/css/public.css"/>
	<script type="text/javascript" src="${ctxAll}/js/jquery.js"></script>
    <script charset="utf-8" src="${ctxAll}/widget/kindeditor-4.1.10/kindeditor.js"></script>
    <script charset="utf-8" src="${ctxAll}/widget/kindeditor-4.1.10/lang/zh_CN.js"></script>        
</head>
<body class="body1">
<table style="width: 600px; height: 430px" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td><span style="color: red; font-weight: bold;">* 注：上传文件大小不能超过100M。格式gif,jpg,jpeg,png,bmp,wmv,mp4</span></td>
        <td colspan="3" align="right">
            <input name="button" type="button" class="btn_green" onclick="rv();" id="button1" value="确定"/>
            <input name="button" type="button" class="btn_green" onclick="window.close();" id="button2" value="关闭"/>
        </td>
    </tr>
    <tr>
        <td style="width:600px; height:430px;" valign="top" colspan="3" align="center">
            <form action="result.jsp" method="post" name="mf">
                <textarea cols="100" rows="5" name="Content" id="Content"></textarea>
            </form>
        </td>
    </tr>
</table>

<script>
    function rv() {    	
        var URLParams = new Object();
        var aParams = document.location.search.substr(1).split('&');
        for (var i = 0; i < aParams.length; i++) {
            var aParam = aParams[i].split('=');
            URLParams[aParam[0]] = aParam[1];
        }
        var er = editor.html();
        window.opener.document.getElementById(URLParams["tx"]).value = er;
        this.close();
    }
    function load() {       
		var URLParams = new Object();  
		var aParams = document.location.search.substr(1).split('&');
		for (var i=0; i < aParams.length; i++) {  
		    var aParam = aParams[i].split('=');  
		     URLParams[aParam[0]] = aParam[1];  
		}
		var tmp = window.opener.document.getElementById(URLParams["tx"]).value;
		return tmp;
    }        
    
       
    KindEditor.ready(function(K) {
        window.editor = K.create('#Content', {
            items: [
                'source', '|', 'undo', 'redo', '|', 'preview', 'template', 'cut', 'copy', 'paste',
                'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
                'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|','specialchar', 'image',
                'media', 'table', 'hr', 'emoticons', 'pagebreak',
                'anchor'
            ],
            uploadJson: '${ctxAll}/questionManage/uploadFile.do',
            formatUploadUrl: false,
            afterBlur: function(){this.sync();}
        });
    });
        
    document.getElementById('Content').value = load(); 
</script>
<iframe name='hidden_frame' id="hidden_frame" style='display:none'/>
</body>
</html>