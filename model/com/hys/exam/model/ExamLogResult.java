package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * 标题：考试答案日志实体类
 * 
 * 作者：张建国 2017-02-22
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamLogResult implements Serializable {

	private static final long serialVersionUID = -9004882208055943890L;

	//主键Id
	private Long id;
	
	//用户考试记录Id
	private Long examLogId;
	
	//试题Id
	private Long questionId;
	
	//试题答案
	private String questionResult;
	
	//是否正确
	private int isnotRight;
	
	//试题类型
	private int questionLabelId;
	
	//顺序
	private int seq;
	
	//父试题I
	private Long parentId;
	
	//试题选中答案
	private String selectResult;
	
	//试题正确答案个数
	private String rightCount;
	
	//得分
	private double score;
	
	// 真实分数
	private double realityscore;
	
	

	public double getRealityscore() {
		return realityscore;
	}

	public void setRealityscore(double realityscore) {
		this.realityscore = realityscore;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getExamLogId() {
		return examLogId;
	}

	public void setExamLogId(Long examLogId) {
		this.examLogId = examLogId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getQuestionResult() {
		return questionResult;
	}

	public void setQuestionResult(String questionResult) {
		this.questionResult = questionResult;
	}

	public int getIsnotRight() {
		return isnotRight;
	}

	public void setIsnotRight(int isnotRight) {
		this.isnotRight = isnotRight;
	}

	public int getQuestionLabelId() {
		return questionLabelId;
	}

	public void setQuestionLabelId(int questionLabelId) {
		this.questionLabelId = questionLabelId;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getSelectResult() {
		return selectResult;
	}

	public void setSelectResult(String selectResult) {
		this.selectResult = selectResult;
	}

	public String getRightCount() {
		return rightCount;
	}

	public void setRightCount(String rightCount) {
		this.rightCount = rightCount;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
}