package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Reply implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8063814200020020258L;
	private Long id;
	private String reply_content;
	private String replyer;
	private String reply_time;
	private Integer reply_type;
//	private List<Feedback> feedBacks;
	private Long	 fid;
	
	
	public Long getFid() {
		return fid;
	}
	public void setFid(Long fid) {
		this.fid = fid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	
	
	public String getReply_time() {
		return reply_time;
	}
	public void setReply_time(String reply_time) {
		this.reply_time = reply_time;
	}
	public Integer getReply_type() {
		return reply_type;
	}
	public void setReply_type(Integer reply_type) {
		this.reply_type = reply_type;
	}
	
}

