package com.hys.exam.model;

import java.io.Serializable;
import java.util.ArrayList;
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
public class ExamPaperFasterTactic2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3475487730956265092L;
	
	/**
	 * 策略模板ID
	 */
	private Long tactic_id;
	
	/**
	 * 一级学科ID
	 */
	private Long study1_id;
	
	/**
	 * 一级学科名称
	 */
	private String study1_name;
	
	/**
	 * 二级学科
	 */
	private Long study2_id;
	
	/**
	 * 二级学科名称
	 */
	private String study2_name;
	
	/**
	 * 三级学科
	 */
	private Long study3_id;
	
	/**
	 * 三级学科名称
	 */
	private String study3_name;
	
	/**
	 * 单元
	 */
	private Long unit_id;
	
	/**
	 * 单元名称
	 */
	private String unit_name;
	
	/**
	 * 知识点
	 */
	private Long point_id;
	
	/**
	 * 知识点名称
	 */
	private String point_name;
	
	/**
	 * 符合策略的试题总数
	 */
	private int allQuestionNum;
	
	/**
	 * 分配后的试题总数
	 */
	private int selQuestionNum;
	
	private int percent;

    private List<Long> examQuestionIds = new ArrayList<Long>();
    private List<ExamQuestion> examQuestions = new ArrayList<ExamQuestion>();
	public Long getTactic_id() {
		return tactic_id;
	}

	public void setTactic_id(Long tacticId) {
		tactic_id = tacticId;
	}

	public Long getStudy1_id() {
		return study1_id;
	}

	public void setStudy1_id(Long study1Id) {
		study1_id = study1Id;
	}

	public String getStudy1_name() {
		return study1_name;
	}

	public void setStudy1_name(String study1Name) {
		study1_name = study1Name;
	}

	public Long getStudy2_id() {
		return study2_id;
	}

	public void setStudy2_id(Long study2Id) {
		study2_id = study2Id;
	}

	public String getStudy2_name() {
		return study2_name;
	}

	public void setStudy2_name(String study2Name) {
		study2_name = study2Name;
	}

	public Long getStudy3_id() {
		return study3_id;
	}

	public void setStudy3_id(Long study3Id) {
		study3_id = study3Id;
	}

	public String getStudy3_name() {
		return study3_name;
	}

	public void setStudy3_name(String study3Name) {
		study3_name = study3Name;
	}

	public Long getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(Long unitId) {
		unit_id = unitId;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unitName) {
		unit_name = unitName;
	}

	public Long getPoint_id() {
		return point_id;
	}

	public void setPoint_id(Long pointId) {
		point_id = pointId;
	}

	public String getPoint_name() {
		return point_name;
	}

	public void setPoint_name(String pointName) {
		point_name = pointName;
	}

	public int getAllQuestionNum() {
		return allQuestionNum;
	}

	public void setAllQuestionNum(int allQuestionNum) {
		this.allQuestionNum = allQuestionNum;
	}

	public int getSelQuestionNum() {
		return selQuestionNum;
	}

	public void setSelQuestionNum(int selQuestionNum) {
		this.selQuestionNum = selQuestionNum;
	}
    public List<Long> getExamQuestionIds() {
        return examQuestionIds;
    }

    public void setExamQuestionIds(List<Long> examQuestionIds) {
        this.examQuestionIds = examQuestionIds;
    }

    public List<ExamQuestion> getExamQuestions() {
        return examQuestions;
    }

    public void setExamQuestions(List<ExamQuestion> examQuestions) {
        this.examQuestions = examQuestions;
    }

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

}
