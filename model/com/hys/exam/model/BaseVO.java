package com.hys.exam.model;

import java.io.Serializable;

public class BaseVO   implements Serializable{
	private static final long serialVersionUID = 20170515201011001L;
	
	private Long id1 ;
	private String key1 ;
	private String value1 ;
	private String source_id ;

	public Long getId1() {
		return id1;
	}
	public void setId1(Long id1) {
		this.id1 = id1;
	}
	public String getKey1() {
		return key1;
	}
	public void setKey1(String key1) {
		this.key1 = key1;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getSource_id() {
		return source_id;
	}

	public void setSource_id(String source_id) {
		this.source_id = source_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
}
