package com.hys.exam.model;

import java.io.Serializable;

public class CVUnit implements Serializable {

	/**
	 * boy 
	 * 
	 */
	private static final long serialVersionUID = -5172346305325905061L;

	private Long id;
	
	private String name;
	
	private Integer type;
	
	private Integer point;
	
	private Integer state;
	
	private String content;
	
	private Integer isBound;
	
	private Double quota;
	
	private Integer sequenceNum ; //YHQ，2017-05-24，出现顺序

	private String key_words ;

	private Integer unitMakeType;//0.创建直播课程时所生成单元 1.直播回放单元 2.直播课程设置成点播单元
		
	
	public Integer getSequenceNum() {
		return sequenceNum;
	}

	public void setSequenceNum(Integer sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIsBound() {
		return isBound;
	}

	public void setIsBound(Integer isBound) {
		this.isBound = isBound;
	}

	public Double getQuota() {
		return quota;
	}

	public void setQuota(Double quota) {
		this.quota = quota;
	}

	public Integer getUnitMakeType() {
		return unitMakeType;
	}

	public void setUnitMakeType(Integer unitMakeType) {
		this.unitMakeType = unitMakeType;
	}

	public String getKey_words() {
		return key_words;
	}

	public void setKey_words(String key_words) {
		this.key_words = key_words;
	}
}
