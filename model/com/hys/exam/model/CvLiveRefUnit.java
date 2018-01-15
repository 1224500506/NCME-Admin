package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CvLiveRefUnit implements Serializable {

	private static final long serialVersionUID = -5839449755573699986L;
	
	private Long id;
	private Long cv_id;
	private Long unit_id;
	private String courseware_no;
	private String class_no;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCv_id() {
		return cv_id;
	}
	public void setCv_id(Long cv_id) {
		this.cv_id = cv_id;
	}
	public Long getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(Long unit_id) {
		this.unit_id = unit_id;
	}
	public String getCourseware_no() {
		return courseware_no;
	}
	public void setCourseware_no(String courseware_no) {
		this.courseware_no = courseware_no;
	}
	public String getClass_no() {
		return class_no;
	}
	public void setClass_no(String class_no) {
		this.class_no = class_no;
	}
	
}
