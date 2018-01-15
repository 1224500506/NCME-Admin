package com.hys.exam.struts.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import com.hys.auth.util.StrutsUtil;
import com.hys.common.util.DateUtils;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.exception.FrameworkException;
import com.hys.framework.web.action.BaseAction;

public class UploadFileAction extends BaseAction {
	
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
 		DynaActionForm uform = (DynaActionForm) form;
		FormFile file = (FormFile) uform.get("imgFile");
		
		//文件类型
		String fileType = getExtention(file.getFileName()).toUpperCase();
		//生成新的文件名
		String createFileName=java.util.UUID.randomUUID().toString() + "." + fileType;

		if(isOverSize(file)){
			if(isFileType(file)){
				
				String serverPathDir = request.getSession().getServletContext().getRealPath("upload");
				//String url = copy(file,createFileName,serverPathDir);
				
				String dir = getconf("UploadPath");
				// 获取流媒体Server Path
				String mediaServer = getconf("MediaServer");
				// 设定上传目录
				String tmp = DateUtils.formatDate(new Date(), "yyyyMMdd");
				String uploadPath = serverPathDir+dir + "/" + tmp;
				setPath(uploadPath);
				// 返回url
				String path = uploadPath+ "/" +createFileName;
				String url = request.getSession().getServletContext().getContextPath() + "/upload" + dir + "/"+ tmp + "/" + createFileName;
				
				try{
					FileOutputStream outer = new FileOutputStream(new File(path));
					byte[] buffer = file.getFileData();
					outer.write(buffer);
					outer.close();
					file.destroy();
				}catch(Exception e){
					e.printStackTrace();
				}
				
				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", url);
				
				///zswz/attached/image/20111129/  image 20111129195421_593.jpg
				//out.println(obj.toString());
				StrutsUtil.renderText(response, obj.toString());
				
				/*
				response.setContentType("text/html;charset=UTF-8");
				StringBuffer sb = new StringBuffer();
				sb.append("<script>");
				if(fileType.equals("GIF")|| fileType.equals("JPG")|| fileType.equals("BMP")|| fileType.equals("PNG")){
					sb.append("parent.trew(\'"+url+"\',\'pic\');");
				}
				if(fileType.equals("WMV") || fileType.equals("WAV") || fileType.equals("AVI")){
					sb.append("parent.trew(\'"+url+"\',\'wmv\');");
				}
				sb.append("</script>");
				response.getWriter().write(sb.toString());		
				response.getWriter().flush();
				response.getWriter().close();
			*/
			}else{
				response.setContentType("text/html;charset=UTF-8");
				StringBuffer sb = new StringBuffer();
				sb.append("<script type=\"text/javascript\">");
				sb.append("alert('上传文件类型错误!!!');");
				sb.append("</script>");
				response.getWriter().write(sb.toString());	
				response.getWriter().flush();
				response.getWriter().close();
			}
		}else{
			response.setContentType("text/html;charset=UTF-8");
			StringBuffer sb = new StringBuffer();
			sb.append("<script type=\"text/javascript\">");
			sb.append("alert('上传文件大小不能超过100M!!!');");
			sb.append("</script>");
			response.getWriter().write(sb.toString());			
			response.getWriter().flush();
			response.getWriter().close();
		}
		return null;
	}
	
	private boolean isOverSize(FormFile vfile) {
		int MaxFileSize = Integer.parseInt(getconf("AllowMaxFileSize"));
		if (vfile.getFileSize() > MaxFileSize)
			return false;
		else
			return true;
	}

	private boolean isFileType(FormFile vfile) {
		String fileName = vfile.getFileName();
		String fileType = fileName.substring(fileName.indexOf(".") + 1);
		String UpFileType = getconf("AllowUploadFile").toLowerCase();

		if (UpFileType.indexOf(fileType.toLowerCase()) == -1)
			return false;
		else
			return true;
	}	
	
	private static String getconf(String var) {
		String tmp = "";
		try {
			Locale locale = Locale.getDefault();
			ResourceBundle resource = ResourceBundle.getBundle("upload",locale);
			tmp = resource.getString(var);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp;
	}
	
	private static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".") + 1;
		return fileName.substring(pos).toUpperCase();
	}
	
	private String copy(FormFile src,String fileName , String serverPath) throws FrameworkException {
		// 获取上传路径
		String dir = getconf("UploadPath");
		// 获取流媒体Server Path
		String mediaServer = getconf("MediaServer");
		// 设定上传目录
		String tmp = DateUtils.formatDate(new Date(), "yyyyMMdd");
		String uploadPath = serverPath+dir + "/" + tmp;
		setPath(uploadPath);
		// 返回url
		String path = uploadPath+ "/" +fileName;
		
		try {
			FileOutputStream outer = new FileOutputStream(new File(path));
			byte[] buffer = src.getFileData();
			outer.write(buffer);
			outer.close();
			src.destroy();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		/*InputStream in = null;
		OutputStream out = null;
		byte[] b = null;
		try {
			b = new byte[src.getFileSize()];
			in = new BufferedInputStream(src.getInputStream());
			out = new BufferedOutputStream(new FileOutputStream(uploadPath + "/" + fileName), src.getFileSize());
			int len = 0;
			while ((len = in.read(b)) > 0) {
				out.write(b, 0, len);
			}
			if (StringUtils.checkNull(path)) {
				throw new FrameworkException("");
			}
		} catch (Exception e) {
			throw new FrameworkException("", e);
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (Exception e) {
					throw new FrameworkException("", e);
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					throw new FrameworkException("", e);
				}
			}
		}*/
		return path;
	}

	// 设置上传目录
	private void setPath(String s) {
		try {
			File cfile = new File(s);
			if (!cfile.exists()){
				cfile.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
