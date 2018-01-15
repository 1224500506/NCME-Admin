package com.hys.exam.model;

import java.util.List;
import java.util.Map;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-5-16
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPaperFasterTacticX implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6514494114288778776L;
	/**
	 * 试题题型
	 */
	private int question_label_id;
	
	private List<ExamQuestion> examQuestionList;
	
	/**
	 * 试题数量
	 */
	private int num;
	
	/**
	 * 试题分类ID
	 * 1,2,3
	 */
	private String question_type_id;
	
	/**
	 * 试题分类名称
	 * ###,####,####
	 */
	private String question_type_name;
	
	/**
	 * 试题分数
	 */
	private Double score;
	
	/**
	 * 是否是多媒体试题
	 * １:是
	 */
	private Integer isnot_multimedia;
	
	/**
	 * 试题难度
	 * 1:初级
	 * 2:中级
	 * 3:高级
	 */
	private Integer grade;
	

	/**
	 * 非级联试题属性
	 * questionPropMap<属性类别,ExamQuestionProp>
	 */
	private Map<String,List<ExamQuestionProp>> questionPropMap;
	
	
	/**
	 * 级联试题属性
	 */
	private List<ExamPaperFasterTactic2> examPaperFasterTactic2;
	
	

	public int getQuestion_label_id() {
		return question_label_id;
	}

	public void setQuestion_label_id(int questionLabelId) {
		question_label_id = questionLabelId;
	}

	public List<ExamQuestion> getExamQuestionList() {
		return examQuestionList;
	}

	public void setExamQuestionList(List<ExamQuestion> examQuestionList) {
		this.examQuestionList = examQuestionList;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getQuestion_type_id() {
		return question_type_id;
	}

	public void setQuestion_type_id(String questionTypeId) {
		question_type_id = questionTypeId;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Map<String, List<ExamQuestionProp>> getQuestionPropMap() {
		return questionPropMap;
	}

	public void setQuestionPropMap(
			Map<String, List<ExamQuestionProp>> questionPropMap) {
		this.questionPropMap = questionPropMap;
	}

	public List<ExamPaperFasterTactic2> getExamPaperFasterTactic2() {
		return examPaperFasterTactic2;
	}

	public void setExamPaperFasterTactic2(
			List<ExamPaperFasterTactic2> examPaperFasterTactic2) {
		this.examPaperFasterTactic2 = examPaperFasterTactic2;
	}

	public Integer getIsnot_multimedia() {
		return isnot_multimedia;
	}

	public void setIsnot_multimedia(Integer isnotMultimedia) {
		isnot_multimedia = isnotMultimedia;
	}

	public String getQuestion_type_name() {
		return question_type_name;
	}

	public void setQuestion_type_name(String questionTypeName) {
		question_type_name = questionTypeName;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	
	

}
