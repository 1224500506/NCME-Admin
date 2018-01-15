package com.hys.exam.struts.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.es.ESConfig;
import com.es.ESUtil;
import com.hys.exam.struts.form.FileUploadForm2;
import com.hys.exam.util.JsonUtil;
import com.hys.exam.utils.FileLimitUtil;
import com.hys.framework.web.action.BaseAction;

import net.sf.json.JSONObject;

public class FileUploadAction2 extends BaseAction
{
  protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception{
    FileUploadForm2 fileUploadForm = (FileUploadForm2)form;
    FormFile file = fileUploadForm.getMatFile1();
    JSONObject jsonObject = null;
    jsonObject = FileLimitUtil.validate(file);
   if(!((Integer)jsonObject.get("code")==1)){
   	response.setCharacterEncoding("UTF-8");  
   	response.setContentType("application/json; charset=utf-8");
       response.getWriter().write(jsonObject.toString());
       response.getWriter().flush();
       response.getWriter().close();
   	return null;
   }
    
    
    File newFile = new File(request.getSession().getServletContext().getRealPath("/") + "/upload/" + file.getFileName());
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
    String fileName = newFile.getName();
    String result = null, msgTxtString = "Fail";
    fileName = fileName.substring(0, fileName.indexOf("."));
    Map<String,String> map = new HashMap<String,String>();
    try {
    	result = ESUtil.updateFile(newFile, ESConfig.DEFAULT_FOLDER, ESConfig.DEFAULT_BUCKET);
        //newFile.delete();
         jsonObject = JSONObject.fromObject(result);        
        if ((jsonObject != null) && (!jsonObject.equals(""))) {
          if ((jsonObject.get("code").equals("200")) && (jsonObject.get("message").equals("successful")))          
        	  msgTxtString = (String)jsonObject.get("data") ;
        }        
    }catch (Exception e) {    	
    	System.out.println(e) ;
    }
    
    map.put("message", msgTxtString);
    
    response.getWriter().write(JsonUtil.map2json(map));
    response.getWriter().flush();
    response.getWriter().close();
    return null;
  }

  public boolean judgeFileName(String fileName){
    if ((fileName != null) && (!"".equals(fileName))){
      fileName = fileName.toLowerCase();
      return (fileName.endsWith(".zip")) || (fileName.endsWith(".rar")) || (fileName.endsWith(".7z")) || (fileName.endsWith(".tar.gz"));
    }
    return false;
  }
}