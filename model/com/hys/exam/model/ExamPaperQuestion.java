package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-3-2
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPaperQuestion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1733304830222598345L;
	
	
	private Long id;
	
	/**
	 * 试卷ID
	 */
	private Long paper_id;
	
	/**
	 * 试题ID
	 */
	private Long question_id;
	
	/**
	 * 试题分数
	 */
	private Double question_score;
	
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

	public Long getPaper_id() {
		return paper_id;
	}

	public void setPaper_id(Long paperId) {
		paper_id = paperId;
	}

	public Long getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Long questionId) {
		question_id = questionId;
	}

	public Double getQuestion_score() {
		return question_score;
	}

	public void setQuestion_score(Double questionScore) {
		question_score = questionScore;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	
	

}
