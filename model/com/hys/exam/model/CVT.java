package com.hys.exam.model;

import java.io.Serializable;

public class CVT  implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private int pId;

	private int type;

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

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}


