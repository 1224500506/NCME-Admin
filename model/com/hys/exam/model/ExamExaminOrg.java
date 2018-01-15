package com.hys.exam.model;

import java.io.Serializable;

public class ExamExaminOrg implements Serializable {

	private static final long serialVersionUID = -7095402718569397505L;

	/**
	 * 考试ID
	 */
	private Long exam_id;
	
	/**
	 * 试卷ID
	 */
	private Long org_id;
	
	private ExamHospital org;

	public Long getExam_id() {
		return exam_id;
	}

	public void setExam_id(Long exam_id) {
		this.exam_id = exam_id;
	}

	public Long getOrg_id() {
		return org_id;
	}

	public void setOrg_id(Long org_id) {
		this.org_id = org_id;
	}

	public ExamHospital getOrg() {
		return org;
	}

	public void setOrg(ExamHospital org) {
		this.org = org;
	}
	
}
