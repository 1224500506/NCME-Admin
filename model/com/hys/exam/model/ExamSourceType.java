package com.hys.exam.model;

import java.io.Serializable;

public class ExamSourceType implements Serializable {

	private static final long serialVersionUID = 6892283049349351308L;

	private Long id;
	
	private String type_name;
	
	private String code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	
	
}
