package com.hys.exam.model;
/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-5-9
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPaperFasterTactic1 implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1520502456293630507L;
	
	/**
	 * 策略模板ID
	 */
	private Long tactic_id;
	
	/**
	 * 题型ID
	 */
	private Integer question_label_id;
	
	private ExamQuestionLabel label;
	
	/**
	 * 试题数量
	 */
	private Integer num;
	
	/**
	 * 试题分数
	 */
	private Double score;
	
	private Integer labelQuestionNum;

	public Long getTactic_id() {
		return tactic_id;
	}

	public void setTactic_id(Long tacticId) {
		tactic_id = tacticId;
	}

	public Integer getQuestion_label_id() {
		return question_label_id;
	}

	public void setQuestion_label_id(Integer questionLabelId) {
		question_label_id = questionLabelId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public ExamQuestionLabel getLabel() {
		return label;
	}

	public void setLabel(ExamQuestionLabel label) {
		this.label = label;
	}

	public Integer getLabelQuestionNum() {
		return labelQuestionNum;
	}

	public void setLabelQuestionNum(Integer labelQuestionNum) {
		this.labelQuestionNum = labelQuestionNum;
	}
	

}
