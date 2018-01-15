package com.hys.exam.model;

import java.io.Serializable;

public class AdvertImage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8227454914377535068L;
	private Long id;
	private String image;
	private String imageurl;
	private String content;
	private Integer aid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
