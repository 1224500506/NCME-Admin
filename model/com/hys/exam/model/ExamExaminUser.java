package com.hys.exam.model;

import java.io.Serializable;

public class ExamExaminUser implements Serializable {

	private static final long serialVersionUID = -7095402718569397505L;

	/**
	 * 考试ID
	 */
	private Long exam_id;
	
	/**
	 * 试卷ID
	 */
	private Long system_user_id;
	
	private Integer system_user_type;
	
	private SystemUser user;
	
	public Long getExam_id() {
		return exam_id;
	}

	public void setExam_id(Long exam_id) {
		this.exam_id = exam_id;
	}

	public Long getSystem_user_id() {
		return system_user_id;
	}

	public void setSystem_user_id(Long system_user_id) {
		this.system_user_id = system_user_id;
	}

	public Integer getSystem_user_type() {
		return system_user_type;
	}

	public void setSystem_user_type(Integer system_user_type) {
		this.system_user_type = system_user_type;
	}

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}
	
	
}
