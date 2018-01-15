package com.hys.exam.model;

import java.io.Serializable;

public class CasePatientPropVal  implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long patientId;
	private Long propId;
	private String propName;
	private Integer type;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPropName() {
		return propName;
	}
	public void setPropName(String propName) {
		this.propName = propName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public Long getPropId() {
		return propId;
	}
	public void setPropId(Long propId) {
		this.propId = propId;
	}
}
