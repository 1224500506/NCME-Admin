package com.hys.exam.model;
/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony Jul 6, 2011
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPaperAndPaper implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 26381688982821490L;

	/**
	 * 题型
	 */
	private Integer question_label_id;
	
	/**
	 * 试题总数
	 */
	private Integer allNum;
	
	/**
	 * 要抽取的试题总数
	 */
	private Integer selNum;
	
	/**
	 * 分数
	 */
	private Double question_score;

	public Integer getQuestion_label_id() {
		return question_label_id;
	}

	public void setQuestion_label_id(Integer questionLabelId) {
		question_label_id = questionLabelId;
	}

	public Integer getAllNum() {
		return allNum;
	}

	public void setAllNum(Integer allNum) {
		this.allNum = allNum;
	}

	public Integer getSelNum() {
		return selNum;
	}

	public void setSelNum(Integer selNum) {
		this.selNum = selNum;
	}

	public Double getQuestion_score() {
		return question_score;
	}

	public void setQuestion_score(Double questionScore) {
		question_score = questionScore;
	}

}
