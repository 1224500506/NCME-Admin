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
 * 说明:执业资格考试职称属性
 */
public class ExamZyzgPropTitle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5319323308551476003L;
	
	private Integer id;
	
	private String name;

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

}
