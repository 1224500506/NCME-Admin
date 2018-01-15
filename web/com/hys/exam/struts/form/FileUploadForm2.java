package com.hys.exam.struts.form;

import org.apache.struts.upload.FormFile;
import com.hys.framework.web.form.BaseForm;

public class FileUploadForm2 extends BaseForm {

	private static final long serialVersionUID = 1L;
	
	private String fileName;   //上传文件的名称  
	
    private String fileSize;   //上传文件的大小  
    
    private String uploadTime; //上传文件的日期  
    
    private FormFile matFile1;     //上传文件

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

	public FormFile getMatFile1() {
		return matFile1;
	}

	public void setMatFile1(FormFile matFile1) {
		this.matFile1 = matFile1;
	}

}
