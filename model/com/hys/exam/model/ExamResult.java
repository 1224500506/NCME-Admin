package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-1-18
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9004882208055943890L;

	/**
	 * 用户考试记录_id
	 */
	private Long examLogId;

	/**
	 * 试题答案
	 */
	private String questionResult;

	/**
	 * 是否正确
	 */
	private Integer isnotRight;

	/**
	 * 试题类型
	 */
	private Integer type;

	/**
	 * 父试题id
	 */
	private Long parentQuestionId;

	/**
	 * 答卷试题顺序
	 */
	private Integer seq;

	/**
	 * 每题分数
	 */
	private Integer score;
	
	/**
	 * ID
	 */
	private Long id;
	
	/**
	 * 试题ID
	 */
	private Long questionId;
	
	/**
	 * 试题选中答案
	 */
	private java.lang.String selectResult;
	
	/**
	 * 试题正确个数
	 */
	private java.lang.String rightCount;

	public Long getExamLogId() {
		return examLogId;
	}

	public void setExamLogId(Long examLogId) {
		this.examLogId = examLogId;
	}

	public String getQuestionResult() {
		return questionResult;
	}

	public void setQuestionResult(String questionResult) {
		this.questionResult = questionResult;
	}

	public Integer getIsnotRight() {
		return isnotRight;
	}

	public void setIsnotRight(Integer isnotRight) {
		this.isnotRight = isnotRight;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getParentQuestionId() {
		return parentQuestionId;
	}

	public void setParentQuestionId(Long parentQuestionId) {
		this.parentQuestionId = parentQuestionId;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public java.lang.String getSelectResult() {
		return selectResult;
	}

	public void setSelectResult(java.lang.String selectResult) {
		this.selectResult = selectResult;
	}

	public java.lang.String getRightCount() {
		return rightCount;
	}

	public void setRightCount(java.lang.String rightCount) {
		this.rightCount = rightCount;
	}	
	
	
	
}