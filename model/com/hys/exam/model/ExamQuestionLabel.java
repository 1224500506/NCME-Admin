package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：tony 2010-1-12
 * 
 * 描述：
 * 
 * 说明: 试题题型
 */
public class ExamQuestionLabel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2931345468838403686L;

	private Integer id;

	/**
	 * 题型名称
	 */
	private String name;

	/**
	 * 是否为子试题题型 0:不是 1：是
	 */
	private Integer is_child;

	/**
	 * 题型种类
	 */
	private Integer l_type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIs_child() {
		return is_child;
	}

	public void setIs_child(Integer isChild) {
		is_child = isChild;
	}

	public Integer getL_type() {
		return l_type;
	}

	public void setL_type(Integer lType) {
		l_type = lType;
	}

}
