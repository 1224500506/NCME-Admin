package com.hys.exam.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts.upload.FormFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.es.ESConfig;
import com.es.ESUtil;
import com.hys.exam.common.util.StringUtil;

import net.sf.json.JSONObject;

public class FilesUtils extends FileUtils {

	/** 
     * 上传文件_文件名:时间戳加后缀
     * @param request 
     * @param fileDir 
     * @return fileName
     */
	public static String upload(HttpServletRequest request,String fileDir) {  
        
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;   
        
        /**页面控件的文件流**/    
        MultipartFile multipartFile = multipartRequest.getFile("file"); 
        //无文件上传
        if (multipartFile == null || "".equals(multipartFile.getOriginalFilename()))
        	return null;  
        
        /**得到图片保存目录的真实路径**/    
        String logoRealPathDir = request.getSession().getServletContext().getRealPath(fileDir);
        
        /**根据真实路径创建目录**/    
        File logoSaveFile = new File(logoRealPathDir);     
        if(!logoSaveFile.exists())     
            logoSaveFile.mkdirs();           

        String dateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());     
        
        /**获取文件的后缀**/    
        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        
//        /**使用UUID生成文件名称**/    
//        String logImageName = UUID.randomUUID().toString()+ suffix;//构建文件名称     
//        String logImageName = multipartFile.getOriginalFilename();  
        
        String logImageName = dateStr+ suffix;//构建文件名称     
        /**拼成完整的文件保存路径加文件**/    
        String fileName = logoRealPathDir + "/" + logImageName;                
        File file = new File(fileName);          
        
        try {     
            multipartFile.transferTo(file);     
        } catch (Exception e) {     
            e.printStackTrace();     
        }     
        
        return logImageName;  
    } 
	/** 
     * 上传文件_文件名:时间戳加后缀
     * @param request 
     * @param fileDir 
     * @return fileName
     */
	public static String upload(FormFile file,HttpServletRequest request,String fileDir) {  
        
        
        
        //无文件上传
        if (file == null || "".equals(file.getFileName()))
        	return null;  
        
        /**得到图片保存目录的真实路径**/    
        String logoRealPathDir = request.getSession().getServletContext().getRealPath(fileDir);
        logoRealPathDir =  logoRealPathDir.replaceAll("study_admin", "study");
        
        /**根据真实路径创建目录**/    
        File logoSaveFile = new File(logoRealPathDir);     
        if(!logoSaveFile.exists())     
            logoSaveFile.mkdirs(); 
        
        String dateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        
        /**获取文件的后缀**/    
        String suffix = file.getFileName().substring(file.getFileName().lastIndexOf("."));
        
//        /**使用UUID生成文件名称**/    
//        String logImageName = UUID.randomUUID().toString()+ suffix;//构建文件名称     
//        String logImageName = multipartFile.getOriginalFilename();  
        
        String logImageName = dateStr+ suffix;//构建文件名称     
        /**拼成完整的文件保存路径加文件**/    
        String fileName = logoRealPathDir + "/" + logImageName;                
        
        try {     
        	FileOutputStream outer = new FileOutputStream(new File(fileName));   
            byte[] buffer = file.getFileData();   
            outer.write(buffer);   
            outer.close();   
            file.destroy();      
        } catch (Exception e) {     
            e.printStackTrace();     
        }     
        
        return logImageName;  
    } 
	
	/** 
     * 上传文件_文件名:时间戳加后缀
     * @param request 
     * @param fileDir 
     * @return fileName
     */
	public static String bugLogUpload(FormFile file,HttpServletRequest request,String fileDir) {  
        
        
        
        //无文件上传
        if (file == null || "".equals(file.getFileName()))
        	return null;  
        
        /**得到图片保存目录的真实路径**/    
        String logoRealPathDir = request.getSession().getServletContext().getRealPath(fileDir);
        
        /**根据真实路径创建目录**/    
        File logoSaveFile = new File(logoRealPathDir);     
        if(!logoSaveFile.exists())     
            logoSaveFile.mkdirs(); 
        
        String dateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        /**获取文件的后缀**/    
        String suffix = file.getFileName().substring(file.getFileName().lastIndexOf("."));
        
//        /**使用UUID生成文件名称**/    
//        String logImageName = UUID.randomUUID().toString()+ suffix;//构建文件名称     
//        String logImageName = multipartFile.getOriginalFilename();  
        
        String logImageName = dateStr+ suffix;//构建文件名称     
        /**拼成完整的文件保存路径加文件**/    
        String fileName = logoRealPathDir + "/" + logImageName;                
        
        try {     
        	FileOutputStream outer = new FileOutputStream(new File(fileName));   
            byte[] buffer = file.getFileData();   
            outer.write(buffer);   
            outer.close();   
            file.destroy();      
        } catch (Exception e) {     
            e.printStackTrace();     
        }     
        
        return logImageName;  
    } 
	
	/**
	 * 
	 * @param file
	 * @param request
	 * @param fileDir
	 * @return
	 */
	public static String courseUpload(FormFile file,HttpServletRequest request,String fileDir,long id) {  
        
        
        
        //无文件上传
        if (file == null || "".equals(file.getFileName()))
        	return null;  
        
        /**得到图片保存目录的真实路径**/    
        String logoRealPathDir = request.getSession().getServletContext().getRealPath(fileDir);
        String newLogoRelPathDir = logoRealPathDir.replace("study_admin", "study");
        
        /**根据真实路径创建目录**/    
        File logoSaveFile = new File(newLogoRelPathDir);     
        if(!logoSaveFile.exists())     
            logoSaveFile.mkdirs();              
        
        /**获取文件的后缀**/    
        String suffix = file.getFileName().substring(file.getFileName().lastIndexOf("."));
        
//        /**使用UUID生成文件名称**/    
//        String logImageName = UUID.randomUUID().toString()+ suffix;//构建文件名称     
//        String logImageName = multipartFile.getOriginalFilename();  
        
        String logImageName = suffix;//构建文件名称     
        /**拼成完整的文件保存路径加文件**/    
        String fileName = newLogoRelPathDir + "/" + id + logImageName;                
        
        try {     
        	FileOutputStream outer = new FileOutputStream(new File(fileName));   
            byte[] buffer = file.getFileData();   
            outer.write(buffer);   
            outer.close();   
            file.destroy();      
        } catch (Exception e) {     
            e.printStackTrace();     
        }     
        
        return id + logImageName;  
    } 
	
	/**
	 * 
	 * @param file
	 * @param request
	 * @param fileDir
	 * @return
	 */
	public static String caseUpload(FormFile file,HttpServletRequest request,String fileDir,long id,String type) {  
        
        
        
        //无文件上传
        if (file == null || "".equals(file.getFileName()))
        	return null;  
        
        /**得到图片保存目录的真实路径**/    
        String logoRealPathDir = request.getSession().getServletContext().getRealPath(fileDir);
        String newLogoRelPathDir = logoRealPathDir;
        /**根据真实路径创建目录**/    
        File logoSaveFile = new File(newLogoRelPathDir);     
        if(!logoSaveFile.exists())     
            logoSaveFile.mkdirs();              
        
        /**获取文件的后缀**/    
        String suffix = file.getFileName().substring(file.getFileName().lastIndexOf("."));
        
//        /**使用UUID生成文件名称**/    
//        String logImageName = UUID.randomUUID().toString()+ suffix;//构建文件名称     
//        String logImageName = multipartFile.getOriginalFilename();  
        
        String logImageName = suffix;//构建文件名称     
        /**拼成完整的文件保存路径加文件**/    
        String fileName = newLogoRelPathDir + "/" + type + "_" + id + logImageName;         
        
        try {     
        	FileOutputStream outer = new FileOutputStream(new File(fileName));   
            byte[] buffer = file.getFileData();   
            outer.write(buffer);   
            outer.close();   
            file.destroy();      
        } catch (Exception e) {     
            e.printStackTrace();     
        }     
        
        return type + "_" + id + logImageName;  
    } 
	/**
	 * 
	 * Description: 取出classpath下的文件内容,逐条扫描
	 * @author: yunshine
	 * @param path
	 * @return
	 */
	public static Properties getFileInfo(String path){
		Properties prop = new Properties();
		
		InputStream is = FilesUtils.class.getClassLoader().getResourceAsStream(path);
		try {
			prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}
	/**
	 * 
	 * @param file
	 * @param request
	 * @param fileDir
	 * @return
	 */
	public static String materialUpload(FormFile file,HttpServletRequest request,String fileDir,Long id,String defaultName,String savePath) {  
        
        
        
        //无文件上传
        if (file == null || "".equals(file.getFileName()))
        	return null;  
        
        /**得到图片保存目录的真实路径**/    
        String logoRealPathDir = request.getSession().getServletContext().getRealPath(fileDir);
        String newLogoRelPathDir = logoRealPathDir + "/" + savePath;
        /**根据真实路径创建目录**/    
        File logoSaveFile = new File(newLogoRelPathDir);     
        if(!logoSaveFile.exists())     
            logoSaveFile.mkdirs();              
        
        /**获取文件的后缀**/    
        String suffix = file.getFileName().substring(file.getFileName().lastIndexOf("."));
        
//        /**使用UUID生成文件名称**/    
//        String logImageName = UUID.randomUUID().toString()+ suffix;//构建文件名称     
//        String logImageName = multipartFile.getOriginalFilename();  
        
        String logImageName = suffix;//构建文件名称     
        /**拼成完整的文件保存路径加文件**/    
        String fileName = "";
        if(id != null)
        	fileName = newLogoRelPathDir + "/" + id + logImageName;
        else
        	fileName = newLogoRelPathDir + "/" + defaultName;
        
        try {     
        	FileOutputStream outer = new FileOutputStream(new File(fileName));   
            byte[] buffer = file.getFileData();   
            outer.write(buffer);   
            outer.close();   
            file.destroy();      
        } catch (Exception e) {     
            e.printStackTrace();     
        }     
        
        return  id + logImageName;  
    } 
	
	public static String fileUpload(FormFile file,HttpServletRequest request,String fileDir,Long id,String defaultName,String savePath) {  
        
        
        
        //无文件上传
        if (file == null || "".equals(file.getFileName()))
        	return null;  
        
        /**得到图片保存目录的真实路径**/    
        String logoRealPathDir = request.getSession().getServletContext().getRealPath(fileDir);
        
        String newLogoRelPathDir = "";
        if (!StringUtil.checkNull(savePath)) {
        	newLogoRelPathDir = logoRealPathDir + "/" + savePath;
        } else {
        	newLogoRelPathDir = logoRealPathDir;
        }
        
        /**根据真实路径创建目录**/    
        File logoSaveFile = new File(newLogoRelPathDir);     
        if(!logoSaveFile.exists())     
            logoSaveFile.mkdirs();              
        
        /**获取文件的后缀**/    
        String suffix = file.getFileName().substring(file.getFileName().lastIndexOf("."));
        
//        /**使用UUID生成文件名称**/    
//        String logImageName = UUID.randomUUID().toString()+ suffix;//构建文件名称     
//        String logImageName = multipartFile.getOriginalFilename();  
        
        String logImageName = "";
        //logImageName = suffix;//构建文件名称     
        /**拼成完整的文件保存路径加文件**/    
        String fileName = "";
        if(id != null)
        	fileName = newLogoRelPathDir + "/" + id + logImageName;
        else
        	fileName = newLogoRelPathDir + "/" + defaultName;
        
        try {     
        	FileOutputStream outer = new FileOutputStream(new File(fileName));   
            byte[] buffer = file.getFileData();   
            outer.write(buffer);   
            outer.close();   
            file.destroy();      
        } catch (Exception e) {     
            e.printStackTrace();     
        }     
        
        return  id + logImageName;  
    } 
	
    /**
     * @param file
     * @param request
     * @param fileNameId
     * @return
     * @throws Exception
     * @
     */
    public static String fileUploadToCloud(FormFile file,HttpServletRequest request,Long fileNameId) throws Exception {          
        //无文件上传
        if (file == null || "".equals(file.getFileName()))
        	return null;  
        
        String filePath = request.getSession().getServletContext().getRealPath("/") + "/tmp/userAvatarUpload/" ;        
        File newFile = new File(filePath); //+ file.getFileName()
        if (!newFile.exists()) {
        	newFile.mkdirs();
        }
        newFile = new File(filePath + file.getFileName()) ;
        if (!newFile.exists()){
          newFile.createNewFile();
        }
        byte[] b = new byte[2248000];
        int readBytes = 0;
        InputStream is = file.getInputStream();
        while (readBytes < 2248000) {
          int read = is.read(b, readBytes, 2248000 - readBytes);
          if (read == -1) {
            break;
          }
          readBytes += read;
        }
        
        FileOutputStream fos = new FileOutputStream(newFile);
        fos.write(b, 0, readBytes);
        fos.flush();
        fos.close();
        is.close();
        
        String sourceKey = null ;
        String result = ESUtil.updateFile(newFile, ESConfig.DEFAULT_FOLDER, ESConfig.DEFAULT_BUCKET);
        //newFile.delete();
        JSONObject jsonObject = JSONObject.fromObject(result);
        Map<String,String> map = new HashMap<String,String>();
        if ((jsonObject != null) && (!jsonObject.equals(""))) {
          if ((jsonObject.get("code").equals("200")) && (jsonObject.get("message").equals("successful"))) {
        	  sourceKey = (String)jsonObject.get("data") ;        	 
        	
        	  
        	  return sourceKey ;
          }
        }
                
        return null;                
    } 
}
