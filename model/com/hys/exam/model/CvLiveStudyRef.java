package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CvLiveStudyRef implements Serializable {

	private static final long serialVersionUID = -4329091807921776017L;
	
	private Long id;
	private String class_no;//直播间ID
	private Long start_time;//直播开始时间
	private Long end_time;//直播结束时间
	private Integer total_live_length;//直播各场次累计时长
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClass_no() {
		return class_no;
	}
	public void setClass_no(String class_no) {
		this.class_no = class_no;
	}
	public Long getStart_time() {
		return start_time;
	}
	public void setStart_time(Long start_time) {
		this.start_time = start_time;
	}
	public Long getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Long end_time) {
		this.end_time = end_time;
	}
	public Integer getTotal_live_length() {
		return total_live_length;
	}
	public void setTotal_live_length(Integer total_live_length) {
		this.total_live_length = total_live_length;
	}

}
