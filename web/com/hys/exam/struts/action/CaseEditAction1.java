package com.hys.exam.struts.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.common.util.DateUtils;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.model.CaseCase;
import com.hys.exam.model.CaseCasePatient;
import com.hys.exam.model.CaseDisease;
import com.hys.exam.model.CaseDiseaseDiagnosis;
import com.hys.exam.model.CasePatient;
import com.hys.exam.model.CasePatientDiagnosis;
import com.hys.exam.model.CasePatientPropVal;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.CaseManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：病历管理平台
 * 
 * 作者：Tiger 2016-09-17
 * 
 * 描述：
 * 
 * 说明:修改病例
 */
public class CaseEditAction1 extends BaseAction {

	CaseManage localCaseManage;
	private ExamPropValFacade localExamPropValFacade;
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}
	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}
	public CaseManage getLocalCaseManage() {
		return localCaseManage;
	}
	public void setLocalCaseManage(CaseManage localCaseManage) {
		this.localCaseManage = localCaseManage;
	}
	@SuppressWarnings("unchecked")
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String mode = request.getParameter("mode");
		String type = request.getParameter("type");
		String caseId = request.getParameter("caseId");
		String courseString = "";
		String courseIds = "";
		String subjectString = "";
		String subjectids = "";
		String ICD10_names = "";
		CaseCasePatient newCasePatient = new CaseCasePatient();
		if(caseId != null && !caseId.equals(""))
		{
			CaseCase curCase= localCaseManage.getCaseById(Long.valueOf(caseId));
			CasePatient patient = localCaseManage.getPatientById(curCase.getpId());
			CaseDisease disease = localCaseManage.getDiseaseById(curCase.getDiseaseId());
			
			String pCertificate = patient.getCertificate();
			Date birthday = null;
			if(pCertificate != null && pCertificate.length() == 18)
			{
				String Certificate = pCertificate.substring(6, 14);
				birthday = DateUtil.parse(Certificate, "yyyyMMdd");
			}
			
			if(birthday != null)
			{
				patient.setpAge(getAge(birthday));
				patient.setBirthday(DateUtils.formatDate(birthday, "yyyy-MM-dd"));
	
			}
			Date onlineDate = DateUtil.parse(curCase.getOnlineDate());
			curCase.setOnlineDate(DateUtils.formatDate(onlineDate, "yyyy-MM-dd"));	

			Date createDate = DateUtil.parse(curCase.getCreateDate(),"yyyy-MM-dd");
			curCase.setCreateDate(DateUtils.formatDate(createDate, "yyyy-MM-dd"));
			Date diagDate = DateUtil.parse(patient.getDiagDate());
			patient.setDiagDate(DateUtils.formatDate(diagDate, "yyyy-MM-dd"));
			patient.setpAge(getAge(birthday));
			newCasePatient.setCaseObject(curCase);
			newCasePatient.setPatientObject(patient);
			newCasePatient.setCaseDiseaseObject(disease);
			newCasePatient.setPatientDiagnosisObject(getPatientDiagnosis(patient.getId()));
			newCasePatient.setDiseaseDiagnosisObject(getDiseaseDiagnosis(disease.getId()));
			newCasePatient.setDiseaseDiagnosis(localCaseManage.getDiseaseDiagnosisById(disease.getId()));
			newCasePatient.setPatientDiagnosis(localCaseManage.getPatientDiagnosisById(patient.getId()));
			newCasePatient.setDiseaseDiscuss(localCaseManage.getDiseaseDiscussById(disease.getId()));
			List<CasePatientPropVal> patientProp = new ArrayList<CasePatientPropVal>();
			patientProp = localCaseManage.getCasePropByPatientId(patient.getId());
			for (int i = 0 ; i < patientProp.size() ; i ++)
			{
				CasePatientPropVal curProp = patientProp.get(i);
				if (curProp.getType() <= 5){
					courseString += curProp.getPropName() + ",";
					courseIds += curProp.getPropId() + ",";
				}
				else {
					subjectString += curProp.getPropName() + ",";
					subjectids += curProp.getPropId() + ",";
				}
				ExamPropQuery query  = new ExamPropQuery();
				
				try{
					query.setSysPropId(curProp.getPropId());
					ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
					
					if (rprop != null)
						for (int k=0; k<rprop.getReturnList().size(); k++)
							for (int g=0; g<rprop.getReturnList().get(k).getIcdList().size(); g++)
								ICD10_names += " " + rprop.getReturnList().get(k).getIcdList().get(g).getName();
				} catch(Exception e){}
			}
		}
		if (courseString != "")
		{
			courseString = courseString.substring(0,courseString.length() - 1);	
		}
		if (courseIds != "")
		{
			courseIds = courseIds.substring(0,courseIds.length() - 1);	
		}
		if (subjectString != "")
		{
			subjectString = subjectString.substring(0,subjectString.length() - 1);	
		}
		if (subjectids != "")
		{
			subjectids = subjectids.substring(0,subjectids.length() - 1);	
		}
		if(newCasePatient.getPatientObject().getNumber1Type() == null){
			newCasePatient.getPatientObject().setNumber1Type(-1);
		}
		if(newCasePatient.getPatientObject().getNumber2Type() == null){
			newCasePatient.getPatientObject().setNumber2Type(-1);
		}
		if(newCasePatient.getCaseDiseaseObject() == null){
			newCasePatient.getCaseDiseaseObject().setDiseaseType(-1);
		}
		request.setAttribute("type", type);
		request.setAttribute("caseData", newCasePatient);
		request.setAttribute("diseaseDiscuss", newCasePatient.getDiseaseDiscuss().size());
		request.setAttribute("mode", mode);
		request.setAttribute("courseStr", courseString);
		request.setAttribute("courseIds", courseIds);
		request.setAttribute("subjectStr", subjectString);
		request.setAttribute("subjectids", subjectids);
		request.setAttribute("ICD", ICD10_names);
		
		if(mode.equals("edit"))
		{
			return "edit2";	
		}
		else
		{
			return "edit1";
		}
		
	}
	@SuppressWarnings("deprecation")
	public Integer getAge(Date Birthday){
		Date now = new Date();
		if (Birthday == null)
			return 0;
		int age = now.getYear() - Birthday.getYear();
		return age;
	}
	public String getPatientDiagnosis(Long patientId){
		String result = "";
		List<CasePatientDiagnosis> curPatientDiagnosis = localCaseManage.getPatientDiagnosisById(patientId);
		for(int i = 0 ; i < curPatientDiagnosis.size(); i++){
			result += curPatientDiagnosis.get(i).getDiagnosis();
			if(i != curPatientDiagnosis.size() - 1){
				result += ",";
			}
		}
		return result;
	}
	public String getDiseaseDiagnosis(Long diseaseId){
		String result = "";
		List<CaseDiseaseDiagnosis> curDiseaseDiagnosis = localCaseManage.getDiseaseDiagnosisById(diseaseId);
		for(int i = 0 ; i < curDiseaseDiagnosis.size(); i++){
			result += curDiseaseDiagnosis.get(i).getDiagnosis();
			if(i != curDiseaseDiagnosis.size() - 1){
				result += ",";
			}
		}
		return result;
	}
}
