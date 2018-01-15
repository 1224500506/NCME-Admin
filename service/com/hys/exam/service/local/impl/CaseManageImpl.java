package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.CaseDAO;
import com.hys.exam.dao.local.ExamImportQuestAttDAO;
import com.hys.exam.model.CaseCase;
import com.hys.exam.model.CaseDisease;
import com.hys.exam.model.CaseDiseaseDiagnosis;
import com.hys.exam.model.CaseDiseaseDiscuss;
import com.hys.exam.model.CasePatient;
import com.hys.exam.model.CasePatientDiagnosis;
import com.hys.exam.model.CasePatientPropVal;
import com.hys.exam.model.ExamQuestAtt;
import com.hys.exam.model.ExamQuestRelation;
import com.hys.exam.service.local.CaseManage;
import com.hys.exam.service.local.ExamImportQuestManage;
import com.hys.framework.service.impl.BaseMangerImpl;

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
public class CaseManageImpl extends BaseMangerImpl implements
		CaseManage {

	private CaseDAO localCaseDAO;
	
	public CaseDAO getLocalCaseDAO() {
		return localCaseDAO;
	}

	public void setLocalCaseDAO(CaseDAO localCaseDAO) {
		this.localCaseDAO = localCaseDAO;
	}

	@Override
	public Long addPatient(CasePatient newPatient) {
		// TODO Auto-generated method stub
		return localCaseDAO.addPateint(newPatient);
	}
	@Override
	public Long addDisease(CaseDisease newDisease) {
		// TODO Auto-generated method stub
		return localCaseDAO.addDisease(newDisease);
	}

	@Override
	public Long addCourseDiagnosis(CaseDiseaseDiagnosis newDiagnosis) {
		// TODO Auto-generated method stub
		return localCaseDAO.addCourseDiagnosis(newDiagnosis);
	}

	@Override
	public Long addPatientDiagnosis(CasePatientDiagnosis newDiagnosis) {
		// TODO Auto-generated method stub
		return localCaseDAO.addPatientDiagnosis(newDiagnosis);
	}

	@Override
	public Long addCourseDisscuss(CaseDiseaseDiscuss newDiscuss) {
		// TODO Auto-generated method stub
		return localCaseDAO.addCourseDisscuss(newDiscuss);
	}
	@Override
	public Long addCase(CaseCase newCase) {
		// TODO Auto-generated method stub
		return localCaseDAO.addCase(newCase);
	}
	@Override
	public CasePatient getPatientById(Long id) {
		// TODO Auto-generated method stub
		return localCaseDAO.getPatientById(id);
	}
	@Override
	public CaseCase getCaseById(Long id) {
		// TODO Auto-generated method stub
		return localCaseDAO.getCaseById(id);
	}
	@Override
	public List<CaseCase> getCaseList(List<String> searchCase) {
		// TODO Auto-generated method stub
		return localCaseDAO.getCaseList(searchCase);
	}

	@Override
	public Boolean deleteCase(Long id) {
		// TODO Auto-generated method stub
		return localCaseDAO.deleteCase(id);
	}
	@Override
	public Boolean setCaseState(Long id,Integer state,String userName,String deliOpinion) {
		// TODO Auto-generated method stub
		return localCaseDAO.setCaseState(id,state,userName,deliOpinion);
	}
	@Override
	public List<CasePatientDiagnosis> getPatientDiagnosisById(Long patientId)
	{
		return localCaseDAO.getPatientDiagnosisById(patientId);
	}
	@Override
	public List<CaseDiseaseDiagnosis> getDiseaseDiagnosisById(Long diseaseId)
	{
		return localCaseDAO.getDiseaseDiagnosisById(diseaseId);
	}
	@Override
	public CaseDisease getDiseaseById(Long id) {
		// TODO Auto-generated method stub
		return localCaseDAO.getDiseaseById(id);
	}

	@Override
	public List<CaseDiseaseDiscuss> getDiseaseDiscussById(Long diseaseId) {
		// TODO Auto-generated method stub
		return localCaseDAO.getDiseaseDiscussById(diseaseId);
	}

	@Override
	public void updateCasePatient(CasePatient patient) {
		// TODO Auto-generated method stub
		localCaseDAO.updateCasePatient(patient);
	}
	@Override
	public void updateCaseDisease(CaseDisease disease) {
		// TODO Auto-generated method stub
		localCaseDAO.updateCaseDisease(disease);
	}

	@Override
	public Long addPatientProp(CasePatientPropVal newProp) {
		// TODO Auto-generated method stub
		return localCaseDAO.addPatientProp(newProp);
	}
	@Override
	public CasePatientPropVal getCasePropByPatientIdAndPropId(Long patientId,Long propId) {
		// TODO Auto-generated method stub
		return localCaseDAO.getCasePropByPatientIdAndPropId(patientId, propId);
	}

	@Override
	public void deleteCasePropVal(Long patientid) {
		// TODO Auto-generated method stub
		localCaseDAO.deleteCasePropVal(patientid);
	}

	@Override
	public void updateCase(CaseCase newCase) {
		// TODO Auto-generated method stub
		localCaseDAO.updateCase(newCase);
	}

	@Override
	public List<CasePatientPropVal> getCasePropByPatientId(Long PatientId) {
		// TODO Auto-generated method stub
		return localCaseDAO.getCasePropByPatientId(PatientId);
	}
}
