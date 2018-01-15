
//根据后缀得到最大上传大小
function getSizeBySuffix(suffix){
	if ("gif|jpg|jpeg|png|GIF|JPG|PNG|bmp".indexOf(suffix) >= 0) {//图片
		return 10240;  //10M
	}
	if ("mp3|wma|wav".indexOf(suffix) >= 0) {//音频
		return 20480;  //20M
	}
	if ("mp4|wmv|rmvb|mkv|flv".indexOf(suffix) >= 0) {//视频
		return 2097152; //2G 
	}
	if ("ppt|pptx".indexOf(suffix) >= 0 || "doc|docx|pdf|txt".indexOf(suffix) >= 0 || "rar|zip|7z".indexOf(suffix) >= 0) {//ppt、文本、压缩包
		return 51200;  //50M
	}
}

function fileUploadValid(id,type){
	var res = true;
	//判断上传文件类型
	 var path = $("#"+id).val();  
	 if (path != "") {
		 var extStart = path.lastIndexOf('.');
	     var ext = path.substring(extStart+1,path.length);  
		 //type==0表示支持所有的文件类型（图片、音频、视频、ppt、文本、压缩包） type==2 表示图片
		 if (type == 0) {
			 if (!/\.(gif|jpg|jpeg|png|bmp|mp3|wma|wav|mp4|wmv|rmvb|mkv|flv|ppt|pptx|doc|docx|pdf|txt|rar|zip|7z)$/.test(path)) {
				 alert("上传文件格式不对，支持以下类型：gif、jpg、jpeg、png、bmp、mp3、wma、wav、mp4、wmv、rmvb、mkv、flv、ppt、pptx、doc、docx、pdf、txt、rar、zip、7z");
				 res = false;
			 }
		 } 
		 //图片
		 if (type == 2 && !/\.(gif|jpg|jpeg|png|GIF|JPG|PNG|bmp)$/.test(path)) {
			alert("图片类型必须是.gif,jpeg,jpg,png,bmp中的一种");
			res = false;
		 }
		//视频
		 if (type == 1 && !/\.(mp4|wmv|rmvb|mkv|flv)$/.test(path)) {
			alert("视频类型必须是.mp4,wmv,rmvb,mkv,flv中的一种");
			res = false;
		 }
		//PPT
		if (type == 3 && !/\.(ppt|pptx)$/.test(path)) {
			alert("ppt类型必须是.ppt,pptx中的一种");
			res = false;
		}
		//文本
		if (type == 4 && !/\.(doc|docx|pdf|txt)$/.test(path)) {
			alert("文本类型必须是.doc,docx,pdf,txt中的一种");
			res = false;
		}
		//压缩包
		if (type == 5 && !/\.(rar|zip|7z)$/.test(path)) {
			alert("压缩包类型必须是.rar,zip,7z中的一种");
			res = false;
		}
		//音频
		if (type == 6 && !/\.(mp3|wma|wav)$/.test(path)) {
			alert("音频类型必须是.mp3,wma,wav中的一种");
			res = false;
		}
         //判断上传文件大小
         var size ;//文件大小
         if ($.browser.msie && ($.browser.version == "9.0"))
         {
         	try{
             var file = $("#"+id).get(0);
             file.select();
             file.blur();
             var path = document.selection.createRange().text;
             var fso = new ActiveXObject("Scripting.FileSystemObject");
             size = fso.GetFile(path).size/ 1024;
             //弹出文件大小
           }catch(e){
         		alert(e+"\n"+"如果错误为：Error:Automation 服务器不能创建对象；"
				+"\n"+"请按升级IE浏览器为最新或者按照以下方法配置浏览器："
                 +"\n"+"请打开【Internet选项-安全-Internet-自定义级别-ActiveX控件和插件-对未标记为可安全执行脚本的ActiveX控件初始化并执行脚本（不安全）-点击启用-确定】");
             return ;
           }
         } else {
             size = $("#"+id).get(0).files[0].size / 1024;
		 }
	    // var size = $("#"+id).get(0).files[0].size / 1024;
	    if (type == 1 && size > 2097152) {
	        alert('视频大小不能超过2G');  
	        res = false;  
	    }
	    if (type == 2 && size > 10240) {
	    	alert('图片大小不能超过10M');  
	    	res = false;
	    }
	    if (type == 3 && size > 51200) {
	    	alert('ppt大小不能超过50M');  
	    	res = false;
	    }
	    if (type == 4 && size > 51200) {
	    	alert('文本大小不能超过50M');  
	    	res = false;
	    }
	    if (type == 5 && size > 51200) {
	    	alert('压缩包大小不能超过50M');  
	    	res = false;
	    }
	 } else {
		 alert("请选择文件");
		 res = false;
	 }
    return res;
}