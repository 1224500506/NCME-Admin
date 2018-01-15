package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.CaseCase;
import com.hys.exam.model.CaseDisease;
import com.hys.exam.model.CaseDiseaseDiagnosis;
import com.hys.exam.model.CaseDiseaseDiscuss;
import com.hys.exam.model.CasePatient;
import com.hys.exam.model.CasePatientDiagnosis;
import com.hys.exam.model.CasePatientPropVal;
import com.hys.framework.service.BaseService;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-27
 * 
 * 描述：
 * 
 * 说明:
 */
public interface CaseManage extends BaseService {

	public Long addPatient(CasePatient newPatient);
	public Long addPatientDiagnosis(CasePatientDiagnosis newDiagnosis);
	public Long addDisease(CaseDisease newPatient);
	public Long addCourseDiagnosis(CaseDiseaseDiagnosis newDiagnosis);
	public Long addCourseDisscuss(CaseDiseaseDiscuss newDisscuss);
	public Long addPatientProp(CasePatientPropVal newProp);
	public Long addCase(CaseCase newCase);
	public Boolean deleteCase(Long id);
	public CasePatient getPatientById(Long id);
	public CaseCase getCaseById(Long id);
	public CaseDisease getDiseaseById(Long id);
	public CasePatientPropVal getCasePropByPatientIdAndPropId(Long patientId,Long propId);
	public void updateCasePatient(CasePatient patient);
	public void updateCaseDisease(CaseDisease disease);
	public List<CasePatientPropVal> getCasePropByPatientId(Long PatientId);
	public List<CasePatientDiagnosis> getPatientDiagnosisById(Long patientId);
	public List<CaseDiseaseDiagnosis> getDiseaseDiagnosisById(Long diseaseId);
	public  List<CaseDiseaseDiscuss> getDiseaseDiscussById(Long diseaseId);
	public Boolean setCaseState(Long id,Integer state,String userName,String deliOpinion);
	public List<CaseCase> getCaseList(List<String> searchCase);
//	public List<CaseCase> getCaseList(CaseCase searchCase);
	public void deleteCasePropVal(Long patientid);
	public void updateCase(CaseCase newCase);
}
