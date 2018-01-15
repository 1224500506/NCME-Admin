package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-12-13
 * 
 * 描述：
 * 
 * 说明: 试题,子系统,试题分类关联表
 */
public class ExamSubSysQuestType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3231092338459823031L;

	//试题ID
	private Long question_id;
	
	//分类ID
	private Long sub_type_id;
	
	//分类名称
	private String sub_type_name;
	
	//子系统ID
	private Long sub_sys_id;
	
	//子系统名称
	private String sub_sys_name;
	
	//状态
	private Integer state;

	public Long getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Long questionId) {
		question_id = questionId;
	}

	public Long getSub_type_id() {
		return sub_type_id;
	}

	public void setSub_type_id(Long subTypeId) {
		sub_type_id = subTypeId;
	}

	public Long getSub_sys_id() {
		return sub_sys_id;
	}

	public void setSub_sys_id(Long subSysId) {
		sub_sys_id = subSysId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getSub_type_name() {
		return sub_type_name;
	}

	public void setSub_type_name(String subTypeName) {
		sub_type_name = subTypeName;
	}

	public String getSub_sys_name() {
		return sub_sys_name;
	}

	public void setSub_sys_name(String subSysName) {
		sub_sys_name = subSysName;
	}
	
}
