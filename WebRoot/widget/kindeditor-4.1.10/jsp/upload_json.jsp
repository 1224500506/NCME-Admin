<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="net.sf.json.*" %>
<%@ page import="org.apache.struts.upload.MultipartRequestWrapper" %>
<%

/**
 * KindEditor JSP
 * 
 * 本JSP程序是演示程序，建议不要直接在实际项目中使用。
 * 如果您确定直接使用本程序，使用之前请仔细确认相关安全设置。
 * 
 */

//文件保存目录路径
String savePath = pageContext.getServletContext().getRealPath("/") + "upload/exam_upload/";
//String savePath = EnvironmentUtil.EXAM_UPLOAD_PATH;
//String saveUrl  = request.getContextPath() + "/upload/exam_upload/";

//数据库保存目录URL
String saveUrl  = "/resource/exam_upload/";

//定义允许上传的文件扩展名
HashMap<String, String> extMap = new HashMap<String, String>();
extMap.put("multimedia", "gif,jpg,jpeg,png,bmp,wmv,avi,mpg,mp4");
//extMap.put("flash", "swf,flv");
//extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
//extMap.put("media", "wmv,avi");
//extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

//最大文件大小  //单位bits
long maxSize = 104857600;

response.setContentType("text/html; charset=UTF-8");

if(!ServletFileUpload.isMultipartContent(request)){
	out.println(getError("请选择文件。"));
	return;
}

//检查目录
File uploadDir = new File(savePath);
if(!uploadDir.exists()) {
    uploadDir.mkdirs();
}

if(!uploadDir.isDirectory()){
	out.println(getError("上传目录不存在。"));
	return;
}
//检查目录写权限
if(!uploadDir.canWrite()){
	out.println(getError("上传目录没有写权限。"));
	return;
}

//创建文件夹
savePath += "/";
saveUrl += "/";
File saveDirFile = new File(savePath);
if (!saveDirFile.exists()) {
	saveDirFile.mkdirs();
}
SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
String ymd = sdf.format(new Date());
savePath += ymd + "/";
saveUrl += ymd + "/";
File dirFile = new File(savePath);
if (!dirFile.exists()) {
	dirFile.mkdirs();
}



//Struts2请求 包装过滤器
MultipartRequestWrapper wrapper = (MultipartRequestWrapper) request;
//获得上传文件名
String fileName;
//request文件最大值在struts2.xml 中有限制
try {
    fileName=new String();// = wrapper.getFileNames("imgFile")[0];
} catch (Exception e) {
    out.println(getError("上传文件大小超过限制，最大100M。"));
    return;
}
//获得文件过滤
File file = new File("");// = wrapper.getFiles("imgFile")[0];

//检查扩展名
String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
if(!Arrays.<String>asList(extMap.get("multimedia").split(",")).contains(fileExt)) {
    out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get("multimedia") + "格式。"));
    return;
}
//检查文件大小
if(file.length() > maxSize) {
    out.println(getError("上传文件大小超过限制，最大100M。"));
    return;
}

//重构上传图片的名称
SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
String newFileName = java.util.UUID.randomUUID().toString() + "." + fileExt;  //采用时间+UUID的方式随即命名
byte[] buffer = new byte[1024];
//获取文件输出流
FileOutputStream fos = new FileOutputStream(savePath +"/" + newFileName);
//获取内存中当前文件输入流
InputStream in = new FileInputStream(file);
try {
    int num = 0;
    while ((num = in.read(buffer)) > 0) {
        fos.write(buffer, 0, num);
    }
} catch (Exception e) {
    e.printStackTrace(System.err);
} finally {
    in.close();
    fos.close();
}
//发送给 KE
JSONObject obj = new JSONObject();
obj.put("error", 0);
obj.put("url", saveUrl +"/" + newFileName);
///zswz/attached/image/20111129/  image 20111129195421_593.jpg
out.println(obj.toString());
%>
<%!
private String getError(String message) {
	JSONObject obj = new JSONObject();
	obj.put("error", 1);
	obj.put("message", message);
	return obj.toString();
}
%>