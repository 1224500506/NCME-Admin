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
import com.hys.exam.struts.form.FileUploadForm;
import com.hys.exam.util.JsonUtil;
import com.hys.exam.utils.FileLimitUtil;
import com.hys.framework.web.action.BaseAction;

import net.sf.json.JSONObject;

public class FileUploadAction extends BaseAction
{
  protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception{
    FileUploadForm fileUploadForm = (FileUploadForm)form;
    FormFile file = fileUploadForm.getMatFile();
    JSONObject jsonObject = null;

    //2017/01/11, Add by lee
    //if file size is less more 1, then   
    if(file.getFileSize() < 1)
    	return null;
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
//    ss
    System.out.println("FileUploadAction-----yhq--８８８８８８８８８８８８８８８８８８８８８--" + newFile);
    if (!newFile.exists()){
      newFile.createNewFile();
//    	newFile.mkdir(); 
    }
    byte[] b = new byte[2248000];
    InputStream is = file.getInputStream();
    FileOutputStream fos = new FileOutputStream(newFile);
    int read = 0;
    while (read != -1) {
      read = is.read(b, 0, b.length);
      if (read == -1) {
        break;
      }
      fos.write(b, 0, read);
    }
    fos.flush();
    fos.close();
    is.close();
    String fileName = newFile.getName();
    String result = null;
    fileName = fileName.substring(0, fileName.indexOf("."));
    Map<String,String> map = new HashMap<String,String>();
    try {
    	result = ESUtil.updateFile(newFile, ESConfig.DEFAULT_FOLDER, ESConfig.DEFAULT_BUCKET);
    	jsonObject=JSONObject.fromObject(result);
    	 if ((jsonObject != null) && (!jsonObject.equals(""))) {
    	      if ((jsonObject.get("code").equals("200")) && (jsonObject.get("message").equals("successful")))
    	        map.put("message", (String)jsonObject.get("data"));
    	      else
    	        map.put("message", "Fail");
    	    }
    	    else {
    	      map.put("message", "Fail");
    	    }
	} catch (Exception e) {
		System.out.println("FileUploadAction-----yhq----" + e);
		map.put("message", "Fail"); 
	}
    //newFile.delete();
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