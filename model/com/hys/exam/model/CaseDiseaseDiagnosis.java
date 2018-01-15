package com.hys.exam.model;

import java.io.Serializable;

public class CaseDiseaseDiagnosis  implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long diseaseId;
	private String diagnosis;
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
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	
}
