package com.hys.exam.struts.form;

import org.apache.struts.upload.FormFile;

import com.hys.framework.web.form.BaseForm;

public class BannerImage_Form extends BaseForm {

	/**
	 * Ma
	 */
	private static final long serialVersionUID = 1L;
	
	private FormFile matFile;
	
	private Integer id;
	private String image;
	private String imageurl;
	private String content;
	private Integer aid;
	public FormFile getMatFile() {
		return matFile;
	}
	public void setMatFile(FormFile matFile) {
		this.matFile = matFile;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	
	
}