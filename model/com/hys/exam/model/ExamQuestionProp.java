package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-13
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamQuestionProp implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8475630612173847603L;
	
	/**
	 * 试题ID
	 */
	private Long question_id;
	
	/**
	 * 属性值
	 */
	private Long prop_val_id;

	/**
	 * 属性值名称
	 */
	private String prop_val_name;
	
	public Long getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Long questionId) {
		question_id = questionId;
	}

	public Long getProp_val_id() {
		return prop_val_id;
	}

	public void setProp_val_id(Long propValId) {
		prop_val_id = propValId;
	}

	public String getProp_val_name() {
		return prop_val_name;
	}

	public void setProp_val_name(String propValName) {
		prop_val_name = propValName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((prop_val_id == null) ? 0 : prop_val_id.hashCode());
		result = prime * result
				+ ((question_id == null) ? 0 : question_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ExamQuestionProp other = (ExamQuestionProp) obj;
		if (prop_val_id == null) {
			if (other.prop_val_id != null)
				return false;
		} else if (!prop_val_id.equals(other.prop_val_id))
			return false;
		if (question_id == null) {
			if (other.question_id != null)
				return false;
		} else if (!question_id.equals(other.question_id))
			return false;
		return true;
	}
	
	
	
}
