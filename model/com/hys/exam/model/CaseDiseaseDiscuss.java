package com.hys.exam.model;

import java.io.Serializable;

public class CaseDiseaseDiscuss  implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long diseaseId;
	private String prop;
	private String analysis;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDiseaseId() {
		return diseaseId;
	}
	public void setDiseaseId(Long diseaseId) {
		this.diseaseId = diseaseId;
	}
	public String getProp() {
		return prop;
	}
	public void setProp(String prop) {
		this.prop = prop;
	}
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	
}
