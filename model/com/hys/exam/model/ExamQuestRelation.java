package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-27
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamQuestRelation implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1289895036134248260L;


	private Long id;
	
	
	private Long c_Id;
	
	private String x_y;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getC_Id() {
		return c_Id;
	}

	public void setC_Id(Long cId) {
		c_Id = cId;
	}

	public String getX_y() {
		return x_y;
	}

	public void setX_y(String xY) {
		x_y = xY;
	}



}
