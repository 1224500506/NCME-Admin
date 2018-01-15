package com.hys.exam.model;

import java.io.Serializable;
import java.util.List;

public class CaseCasePatient  implements Serializable {

	private static final long serialVersionUID = 1L;
	private CaseCase caseObject;
	private CasePatient patientObject;
	private CasePatientPropVal patientPropObject;
	private CaseDisease caseDiseaseObject;
	private String diseaseDiagnosisObject;
	private String propNames;
	public String getPropNames() {
		return propNames;
	}
	public void setPropNames(String propNames) {
		this.propNames = propNames;
	}
	private List<CaseDiseaseDiscuss> diseaseDiscuss;
	private List<CaseDiseaseDiagnosis> diseaseDiagnosis;
	private List<CasePatientDiagnosis> patientDiagnosis;
	public List<CaseDiseaseDiagnosis> getDiseaseDiagnosis() {
		return diseaseDiagnosis;
	}
	public void setDiseaseDiagnosis(List<CaseDiseaseDiagnosis> diseaseDiagnosis) {
		this.diseaseDiagnosis = diseaseDiagnosis;
	}
	public List<CasePatientDiagnosis> getPatientDiagnosis() {
		return patientDiagnosis;
	}
	public void setPatientDiagnosis(List<CasePatientDiagnosis> patientDiagnosis) {
		this.patientDiagnosis = patientDiagnosis;
	}
	private String patientDiagnosisObject; 
	
	public CaseDisease getCaseDiseaseObject() {
		return caseDiseaseObject;
	}
	public void setCaseDiseaseObject(CaseDisease caseDiseaseObject) {
		this.caseDiseaseObject = caseDiseaseObject;
	}
	public String getDiseaseDiagnosisObject() {
		return diseaseDiagnosisObject;
	}
	public void setDiseaseDiagnosisObject(String diseaseDiagnosisObject) {
		this.diseaseDiagnosisObject = diseaseDiagnosisObject;
	}
	public List<CaseDiseaseDiscuss> getDiseaseDiscuss() {
		return diseaseDiscuss;
	}
	public void setDiseaseDiscuss(List<CaseDiseaseDiscuss> diseaseDiscuss) {
		this.diseaseDiscuss = diseaseDiscuss;
	}

	public CasePatientPropVal getPatientPropObject() {
		return patientPropObject;
	}
	public void setPatientPropObject(CasePatientPropVal patientPropObject) {
		this.patientPropObject = patientPropObject;
	}
	public String getPatientDiagnosisObject() {
		return patientDiagnosisObject;
	}
	public void setPatientDiagnosisObject(
			String patientDiagnosisObject) {
		this.patientDiagnosisObject = patientDiagnosisObject;
	}
	public CaseCase getCaseObject() {
		return caseObject;
	}
	public void setCaseObject(CaseCase caseObject) {
		this.caseObject = caseObject;
	}
	public CasePatient getPatientObject() {
		return patientObject;
	}
	public void setPatientObject(CasePatient patient) {
		this.patientObject = patient;
	}
	
	
}
