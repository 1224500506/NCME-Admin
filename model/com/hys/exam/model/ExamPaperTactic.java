package com.hys.exam.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-3-9
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPaperTactic implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6446727838296555507L;
	
	private Long id;
	
	/**
	 * 试卷ID
	 */
	private Long paper_id;
	
	/**
	 * 题型ID
	 */
	private Integer question_label_id;
	
	/**
	 * 难度
	 */
	private Integer grade;
	
	/**
	 * 数量
	 */
	private Integer amount;
	
	/**
	 * 分数
	 */
	private Double question_score;
	
	/**
	 * 试题分类ID
	 */
	private String quesiton_type_id;
	
	/**
	 * 试题分类名称
	 */
	private String question_type_name;
	
	/**
	 * 级联属性ID
	 */
	private String cascade_id;
	
	/**
	 * 级联属性名称
	 */
	private String cascade_name;
	
	/**
	 * 副知识点ID
	 */
	private String prop_point2_id;
	
	/**
	 * 副知识点名称
	 */
	private String prop_point2_name;
	
	/**
	 * 认知水平ID
	 */
	private String prop_cognize_id;
	
	/**
	 * 认知水平名称
	 */
	private String prop_cognize_name;
	
	/**
	 * 职称ID
	 */
	private String prop_title_id;
	
	/**
	 * 职称名称
	 */
	private String prop_title_name;
	
	
	/**
	 * 试题属性
	 * questionPropMap<属性类别,ExamQuestionProp>
	 */
	private Map<String,List<ExamQuestionProp>> questionPropMap;
	

	public Map<String, List<ExamQuestionProp>> getQuestionPropMap() {
		return questionPropMap;
	}

	public void setQuestionPropMap(
			Map<String, List<ExamQuestionProp>> questionPropMap) {
		this.questionPropMap = questionPropMap;
	}

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

	public Integer getQuestion_label_id() {
		return question_label_id;
	}

	public void setQuestion_label_id(Integer questionLabelId) {
		question_label_id = questionLabelId;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getQuestion_score() {
		return question_score;
	}

	public void setQuestion_score(Double questionScore) {
		question_score = questionScore;
	}

	public String getQuesiton_type_id() {
		return quesiton_type_id;
	}

	public void setQuesiton_type_id(String quesitonTypeId) {
		quesiton_type_id = quesitonTypeId;
	}

	public String getQuestion_type_name() {
		return question_type_name;
	}

	public void setQuestion_type_name(String questionTypeName) {
		question_type_name = questionTypeName;
	}

	public String getCascade_id() {
		return cascade_id;
	}

	public void setCascade_id(String cascadeId) {
		cascade_id = cascadeId;
	}

	public String getCascade_name() {
		return cascade_name;
	}

	public void setCascade_name(String cascadeName) {
		cascade_name = cascadeName;
	}

	public String getProp_point2_id() {
		return prop_point2_id;
	}

	public void setProp_point2_id(String propPoint2Id) {
		prop_point2_id = propPoint2Id;
	}

	public String getProp_point2_name() {
		return prop_point2_name;
	}

	public void setProp_point2_name(String propPoint2Name) {
		prop_point2_name = propPoint2Name;
	}

	public String getProp_cognize_id() {
		return prop_cognize_id;
	}

	public void setProp_cognize_id(String propCognizeId) {
		prop_cognize_id = propCognizeId;
	}

	public String getProp_cognize_name() {
		return prop_cognize_name;
	}

	public void setProp_cognize_name(String propCognizeName) {
		prop_cognize_name = propCognizeName;
	}

	public String getProp_title_id() {
		return prop_title_id;
	}

	public void setProp_title_id(String propTitleId) {
		prop_title_id = propTitleId;
	}

	public String getProp_title_name() {
		return prop_title_name;
	}

	public void setProp_title_name(String propTitleName) {
		prop_title_name = propTitleName;
	}

	
	
}
