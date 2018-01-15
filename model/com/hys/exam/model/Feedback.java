package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hys.exam.utils.StringUtils;

public class Feedback implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4149282096487772347L;
	private Long id;
	private String creater;
	private String telphone;
	private String system;  //操作系统   1.window  2.ios  3.android   4.mac
	private Date backtime;
	private Integer state;
	private String content;
	private String image;
	private String systemversion;
	private String start_date;  //开始时间
	private String end_date;   //结束时间 
	private String site;//对应一个站点   反馈时 登陆的只有一个站点
	private String email;
	private List<SystemSite> siteList;  //一对多
	private List<Reply> replyList;  //一对多
	//分割相片
	public String[] getImages(){  
        if(!StringUtils.isBlank(this.getImage())){  
            String[] images = this.getImage().split(",");  
            return images;  
        }  
        return null;  
    } 
	
	
	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public List<Reply> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getSystemversion() {
		return systemversion;
	}
	public void setSystemversion(String systemversion) {
		this.systemversion = systemversion;
	}
	public List<SystemSite> getSiteList() {
		return siteList;
	}
	public void setSiteList(List<SystemSite> siteList) {
		this.siteList = siteList;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
	
	

	public Date getBacktime() {
		return backtime;
	}
	public void setBacktime(Date backtime) {
		this.backtime = backtime;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}




	public String getEnd_date() {
		return end_date;
	}




	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}




	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	
	
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	

}
