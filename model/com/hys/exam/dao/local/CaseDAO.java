package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.CaseCase;
import com.hys.exam.model.CaseDisease;
import com.hys.exam.model.CaseDiseaseDiagnosis;
import com.hys.exam.model.CaseDiseaseDiscuss;
import com.hys.exam.model.CasePatient;
import com.hys.exam.model.CasePatientDiagnosis;
import com.hys.exam.model.CasePatientPropVal;


public interface CaseDAO {
	public Long addPateint(CasePatient newPatient);
	public Long addPatientDiagnosis(CasePatientDiagnosis newDiagnosis);
	public Long addDisease(CaseDisease newDisease);
	public Long addCourseDiagnosis(CaseDiseaseDiagnosis newDiagnosis);
	public Long addCourseDisscuss(CaseDiseaseDiscuss newDiscuss);
	public Long addCase(CaseCase newCase);
	public Long addPatientProp(CasePatientPropVal newProp);
	public Boolean deleteCase(Long id);
	public CasePatient getPatientById(Long id);
	public CaseCase getCaseById(Long id);
	public CaseDisease getDiseaseById(Long id);
	public  List<CasePatientDiagnosis> getPatientDiagnosisById(Long patientId);
	public  List<CaseDiseaseDiagnosis> getDiseaseDiagnosisById(Long diseaseId);
	public  List<CaseDiseaseDiscuss> getDiseaseDiscussById(Long diseaseId);
	public List<CasePatientPropVal> getCasePropByPatientId(Long PatientId);
	public CasePatientPropVal getCasePropByPatientIdAndPropId(Long PatientId,Long PropId);
	public Boolean setCaseState(Long id,Integer state,String userName,String deliOpinion);
	public List<CaseCase> getCaseList(List<String> searchCase);
	public void updateCasePatient(CasePatient patient);
	public void updateCaseDisease(CaseDisease disease);
	public void deleteCasePropVal(Long patientid);
	public void updateCase(CaseCase newCase);
	
}
