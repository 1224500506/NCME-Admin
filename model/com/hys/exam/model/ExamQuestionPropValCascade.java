package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-11-4
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamQuestionPropValCascade implements Serializable{
	
	private static final long serialVersionUID = -7800117336200233967L;

	private Long question_id;
	
	private String propval_name;
	
	private String propval_id;

	public Long getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Long questionId) {
		question_id = questionId;
	}

	public String getPropval_name() {
		return propval_name;
	}

	public void setPropval_name(String propvalName) {
		propval_name = propvalName;
	}

	public String getPropval_id() {
		return propval_id;
	}

	public void setPropval_id(String propvalId) {
		propval_id = propvalId;
	}

	
}
