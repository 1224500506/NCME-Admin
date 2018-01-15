package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：tony 2009-12-29
 * 
 * 描述：
 * 
 * 说明: 试题答案
 */
public class ExamQuestionKey implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6636878852761700904L;

	/**
	 * 答案ID
	 */
	private Long id;
	
	/**
	 * 试题ID
	 */
	private Long question_id;
	
	/**
	 * 选项内容
	 */
	private String content;
	
	/**
	 * 是否正确
	 * 1：正确
	 * 0：错误
	 */
	private Integer isnot_true;
	
	/**
	 * 顺序
	 */
	private Integer seq;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Long getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Long questionId) {
		question_id = questionId;
	}

	public Integer getIsnot_true() {
		return isnot_true;
	}

	public void setIsnot_true(Integer isnotTrue) {
		isnot_true = isnotTrue;
	}
	
	

}
