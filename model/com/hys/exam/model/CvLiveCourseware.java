package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CvLiveCourseware implements Serializable {

	private static final long serialVersionUID = -5839449755573699986L;
	
	private Long id;
	private String courseware_no;
	private String courseware_num;
	private String courseware_url;
	private String courseware_token;
	private String subject;
	private String class_no;
	private Long courseware_create_time;
	private Long duration;
	private Date screenshot;
	private Long recordId;
	private String description;
	private Long cv_id;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCourseware_no() {
		return courseware_no;
	}
	public void setCourseware_no(String courseware_no) {
		this.courseware_no = courseware_no;
	}
	public String getCourseware_num() {
		return courseware_num;
	}
	public void setCourseware_num(String courseware_num) {
		this.courseware_num = courseware_num;
	}
	public String getCourseware_url() {
		return courseware_url;
	}
	public void setCourseware_url(String courseware_url) {
		this.courseware_url = courseware_url;
	}
	public String getCourseware_token() {
		return courseware_token;
	}
	public void setCourseware_token(String courseware_token) {
		this.courseware_token = courseware_token;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getClass_no() {
		return class_no;
	}
	public void setClass_no(String class_no) {
		this.class_no = class_no;
	}
	public Long getCourseware_create_time() {
		return courseware_create_time;
	}
	public void setCourseware_create_time(Long courseware_create_time) {
		this.courseware_create_time = courseware_create_time;
	}
	
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public Date getScreenshot() {
		return screenshot;
	}
	public void setScreenshot(Date screenshot) {
		this.screenshot = screenshot;
	}
	
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getCv_id() {
		return cv_id;
	}
	public void setCv_id(Long cv_id) {
		this.cv_id = cv_id;
	}
	
}
