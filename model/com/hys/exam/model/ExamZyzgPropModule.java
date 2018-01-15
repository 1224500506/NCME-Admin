package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-12-23
 * 
 * 描述：
 * 
 * 说明: 执业资格考试模块属性
 */
public class ExamZyzgPropModule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6476509713362866335L;
	
	private Long id;
	
	private String name;
	
	/**
	 * 单元
	 */
	private Long unit_id;
	
	/**
	 * 单元
	 */	
	private ExamZyzgPropUnit zyzgPropUnit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(Long unitId) {
		unit_id = unitId;
	}

	public ExamZyzgPropUnit getZyzgPropUnit() {
		return zyzgPropUnit;
	}

	public void setZyzgPropUnit(ExamZyzgPropUnit zyzgPropUnit) {
		this.zyzgPropUnit = zyzgPropUnit;
	}
	

}
