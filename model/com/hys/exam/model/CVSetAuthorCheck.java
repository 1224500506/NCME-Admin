package com.hys.exam.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;


public class CVSetAuthorCheck implements Serializable {
	
	/**
	 * @author taoLiang
	 * @desc 此类用户授权地区等check
	 */
	private static final long serialVersionUID = -2595766215794070380L;

	private Long id;
	
	private String name;//项目名称

	private List<ExamProp> examPropList;
	
	private Integer numericConstants;//数字常量    0.相等 1.不相等

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

	public List<ExamProp> getExamPropList() {
		return examPropList;
	}

	public void setExamPropList(List<ExamProp> examPropList) {
		this.examPropList = examPropList;
	}

	public Integer getNumericConstants() {
		return numericConstants;
	}

	public void setNumericConstants(Integer numericConstants) {
		this.numericConstants = numericConstants;
	}
	
}