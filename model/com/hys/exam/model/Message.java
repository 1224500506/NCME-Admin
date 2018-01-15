package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6883819094579541869L;
	private Long	id;
	private Integer	messageType;
	private String	title;
	private String	content;
	private String	receiver;
	private Date	sendTime;
	private String	creater;
	private String sendType;   //发送类型     1：平台   2：email
	private Integer	sendState;
	private String start_date;  //开始时间
	private String end_date;   //结束时间
	private List<SystemSite> siteList;  //一对多
	private Long site_id; //站点id
	private String pc;
	private String email;
	private String[] sendcheck;
	
	public String[] getSendcheck() {
		return sendcheck;
	}
	public void setSendcheck(String[] sendcheck) {
		this.sendcheck = sendcheck;
	}
	public Long getSite_id() {
		return site_id;
	}
	public void setSite_id(Long site_id) {
		this.site_id = site_id;
	}
	public String getPc() {
		return pc;
	}
	public void setPc(String pc) {
		this.pc = pc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<SystemSite> getSiteList() {
		return siteList;
	}
	public void setSiteList(List<SystemSite> siteList) {
		this.siteList = siteList;
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
	//	private 	reciverId
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMessageType() {
		return messageType;
	}
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public Integer getSendState() {
		return sendState;
	}
	public void setSendState(Integer sendState) {
		this.sendState = sendState;
	}

	

}
