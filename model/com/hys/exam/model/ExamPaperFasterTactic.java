package com.hys.exam.model;

import java.io.Serializable;
import java.util.List;

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
public class ExamPaperFasterTactic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2815889691215945983L;
	
	
	/**
	 * 策略ID
	 */
	private Long id;
	
	/**
	 * 试卷ID
	 */
	private Long paper_id;
	
	/**
	 * 策略模板名称
	 */
	private String tactic_name;
	
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
	 * 试卷分类ID
	 */
	private Long exam_paper_type_id;
	
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
	 * 题型，试题分类，试题数量
	 */
	private List<ExamPaperFasterTactic1> examPaperFasterTactic1;
	
	/**
	 * 级联属性
	 */
	private List<ExamPaperFasterTactic2> examPaperFasterTactic2;
	
	/**
	 * 非级联属性
	 */
	private List<ExamPaperFasterTactic3> examPaperFasterTactic3;

	/**
	 * Source
	 */
	private List<ExamPaperFasterTactic3> examPaperFasterTactic4;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTactic_name() {
		return tactic_name;
	}

	public void setTactic_name(String tacticName) {
		tactic_name = tacticName;
	}

	public String getQuestion_type_id() {
		return question_type_id;
	}

	public void setQuestion_type_id(String questionTypeId) {
		question_type_id = questionTypeId;
	}

	public Long getExam_paper_type_id() {
		return exam_paper_type_id;
	}

	public void setExam_paper_type_id(Long examPaperTypeId) {
		exam_paper_type_id = examPaperTypeId;
	}

	public List<ExamPaperFasterTactic1> getExamPaperFasterTactic1() {
		return examPaperFasterTactic1;
	}

	public void setExamPaperFasterTactic1(
			List<ExamPaperFasterTactic1> examPaperFasterTactic1) {
		this.examPaperFasterTactic1 = examPaperFasterTactic1;
	}

	public List<ExamPaperFasterTactic2> getExamPaperFasterTactic2() {
		return examPaperFasterTactic2;
	}

	public void setExamPaperFasterTactic2(
			List<ExamPaperFasterTactic2> examPaperFasterTactic2) {
		this.examPaperFasterTactic2 = examPaperFasterTactic2;
	}

	public List<ExamPaperFasterTactic3> getExamPaperFasterTactic3() {
		return examPaperFasterTactic3;
	}

	public void setExamPaperFasterTactic3(
			List<ExamPaperFasterTactic3> examPaperFasterTactic3) {
		this.examPaperFasterTactic3 = examPaperFasterTactic3;
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

	public Long getPaper_id() {
		return paper_id;
	}

	public void setPaper_id(Long paperId) {
		paper_id = paperId;
	}

	public List<ExamPaperFasterTactic3> getExamPaperFasterTactic4() {
		return examPaperFasterTactic4;
	}

	public void setExamPaperFasterTactic4(
			List<ExamPaperFasterTactic3> examPaperFasterTactic4) {
		this.examPaperFasterTactic4 = examPaperFasterTactic4;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	

}
