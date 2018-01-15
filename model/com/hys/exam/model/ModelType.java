package com.hys.exam.model;

import java.io.Serializable;

public class ModelType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8902124126714432L;

	private Long id;
	
	private String name;

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
	
	
}
