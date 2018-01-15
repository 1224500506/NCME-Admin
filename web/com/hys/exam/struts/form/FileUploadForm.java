package com.hys.exam.struts.form;

import org.apache.struts.upload.FormFile;
import com.hys.framework.web.form.BaseForm;

public class FileUploadForm extends BaseForm {

	private static final long serialVersionUID = 1L;
	
	private String fileName;   //上传文件的名称  
	
    private String fileSize;   //上传文件的大小  
    
    private String uploadTime; //上传文件的日期  
    
    private FormFile matFile;     //上传文件

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public FormFile getMatFile() {
		return matFile;
	}

	public void setMatFile(FormFile matFile) {
		this.matFile = matFile;
	}

}
