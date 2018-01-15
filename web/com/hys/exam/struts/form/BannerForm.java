package com.hys.exam.struts.form;


import org.apache.struts.upload.FormFile;

import com.hys.exam.model.Advert;
import com.hys.framework.web.form.BaseForm;

/**
 * banner 管理
 * @author weeho
 *
 */
public class BannerForm extends BaseForm{

	
	private static final long serialVersionUID = 189625609171665011L;

	private String method ;
	
	private Advert model = new Advert();

	private FormFile matFile;
	
	
	private String cover; 
	
	
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public FormFile getMatFile() {
		return matFile;
	}
	public void setMatFile(FormFile matFile) {
		this.matFile = matFile;
	}
	public Advert getModel() {
		return model;
	}
	public void setModel(Advert model) {
		this.model = model;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}

}
