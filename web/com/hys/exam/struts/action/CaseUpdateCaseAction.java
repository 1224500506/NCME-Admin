package com.hys.exam.struts.action;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.StrutsUtil;
import com.hys.common.util.DateUtils;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CaseCase;
import com.hys.exam.model.CaseDisease;
import com.hys.exam.model.CaseDiseaseDiagnosis;
import com.hys.exam.model.CaseDiseaseDiscuss;
import com.hys.exam.model.CasePatient;
import com.hys.exam.model.CasePatientDiagnosis;
import com.hys.exam.model.CasePatientPropVal;
import com.hys.exam.service.local.CaseManage;
import com.hys.exam.struts.form.CaseCaseForm;
import com.hys.exam.utils.FilesUtils;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：病历管理平台
 * 
 * 作者：Tiger 2016-09-17
 * 
 * 描述：
 * 
 * 说明:更新病例
 */
public class CaseUpdateCaseAction extends BaseAction {

	CaseManage localCaseManage;
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
		String type = request.getParameter("type");
		String caseId = request.getParameter("caseId");
		String course = request.getParameter("course");
		String subject = request.getParameter("subject");
		if(caseId != null && !caseId.equals(""))
		{
			CaseCase curCase = localCaseManage.getCaseById(Long.valueOf(caseId));
			CasePatient curPatient = localCaseManage.getPatientById(curCase.getpId());
			CaseDisease curDisease  = localCaseManage.getDiseaseById(curCase.getDiseaseId());
			curPatient = getPatientFromRequest(request, curPatient);
			localCaseManage.updateCasePatient(curPatient);
			if(curPatient != null)
			{
				String jsonDiagnosisStr = request.getParameter("jsonPatientDiagnosis");
				JSONArray jsonDiagnosisArr = JSONArray.fromObject(jsonDiagnosisStr);
				List<CasePatientDiagnosis> patientDiagnosis = localCaseManage.getPatientDiagnosisById(curPatient.getId());
				Integer diagCount = jsonDiagnosisArr.size();
				for(Integer i = 0 ; i < diagCount ; i ++){
					String diagnosis = jsonDiagnosisArr.getJSONObject(i).getString("patientDiag");
					Boolean isexist = false;
					for(Integer j = 0 ; j < patientDiagnosis.size() ; j++){
						if(diagnosis.equals(patientDiagnosis.get(j).getDiagnosis())){
							isexist = true;
						}			
					}
					if(!isexist){
						CasePatientDiagnosis newDiagnosis = new CasePatientDiagnosis();
						newDiagnosis.setPatientId(curPatient.getId());
						newDiagnosis.setDiagnosis(diagnosis);
						localCaseManage.addPatientDiagnosis(newDiagnosis);	
					}
						
				}
			}
			curDisease = getDiseaseFromRequest(request,curDisease);
			
			if(((CaseCaseForm) form).getPlanFile()!=null && ((CaseCaseForm) form).getPlanFile().getFileName() != ""){
				curDisease.setPlanFile(FilesUtils.caseUpload(((CaseCaseForm) form).getPlanFile(), request, Constants.UPLOAD_FILE_PATH_CASE_PATIENT,curPatient.getId(),"disease_plan"));
			}
			if(((CaseCaseForm) form).getAssayFile()!=null && ((CaseCaseForm) form).getAssayFile().getFileName() != ""){
				curDisease.setAssayFile(FilesUtils.caseUpload(((CaseCaseForm) form).getAssayFile(), request, Constants.UPLOAD_FILE_PATH_CASE_PATIENT,curPatient.getId(),"disease_assay"));
			}
			if(((CaseCaseForm) form).getImageFile()!=null && ((CaseCaseForm) form).getImageFile().getFileName() != ""){
				curDisease.setImageFile((FilesUtils.caseUpload(((CaseCaseForm) form).getImageFile(), request, Constants.UPLOAD_FILE_PATH_CASE_PATIENT,curPatient.getId(),"disease_image")));
			}
			if(((CaseCaseForm) form).getRestFile()!=null &&((CaseCaseForm) form).getRestFile().getFileName() != ""){
				curDisease.setRestFile((FilesUtils.caseUpload(((CaseCaseForm) form).getRestFile(), request, Constants.UPLOAD_FILE_PATH_CASE_PATIENT,curPatient.getId(),"disease_rest")));
			}
			localCaseManage.updateCaseDisease(curDisease);
			if(curDisease != null)
			{
				String jsonDiagnosisStr = request.getParameter("jsonDiseaseDiagnosis");
				String jsonDiscussStr = request.getParameter("jsonDiscuss");
				JSONArray jsonDiagnosisArr = JSONArray.fromObject(jsonDiagnosisStr);
				JSONArray jsonDiscussArr = JSONArray.fromObject(jsonDiscussStr);
				List<CaseDiseaseDiagnosis> diseaseDiagnosis = localCaseManage.getDiseaseDiagnosisById(curDisease.getId());
				List<CaseDiseaseDiscuss> diseaseDiscuss = localCaseManage.getDiseaseDiscussById(curDisease.getId());
				Integer diagCount = jsonDiagnosisArr.size();
				for(Integer i = 0 ; i < diagCount ; i ++){
					String diagnosis = jsonDiagnosisArr.getJSONObject(i).getString("diseaseDiag");
					Boolean isexist = false;
					for(Integer j = 0 ; j < diseaseDiagnosis.size() ; j++){
						if(diagnosis.equals(diseaseDiagnosis.get(j).getDiagnosis())){
							isexist = true;
						}
					}	
					if(!isexist){
						CaseDiseaseDiagnosis newDiagnosis = new CaseDiseaseDiagnosis();
						newDiagnosis.setDiseaseId(curDisease.getId());
						newDiagnosis.setDiagnosis(diagnosis);
						localCaseManage.addCourseDiagnosis(newDiagnosis);	
					}
				}
				Integer discussCount = jsonDiscussArr.size();
				for(Integer i = 0 ; i < discussCount ; i ++){
					String discussProp = jsonDiscussArr.getJSONObject(i).getString("discussProp");
					String discussAnalysis = jsonDiscussArr.getJSONObject(i).getString("discussAnalysis");
					Boolean isexist = false;
					for(int j = 0 ; j < diseaseDiscuss.size() ; j++){
						if(discussProp.equals(diseaseDiscuss.get(j).getProp()) && discussAnalysis.equals(diseaseDiscuss.get(j).getAnalysis()))
						{
							isexist = true;
						}	
					}
					if(!isexist){
						CaseDiseaseDiscuss newDiscuss = new CaseDiseaseDiscuss();
						newDiscuss.setDiseaseId(curDisease.getId());
						newDiscuss.setProp(discussProp);
						newDiscuss.setAnalysis(discussAnalysis);
						localCaseManage.addCourseDisscuss(newDiscuss);
					}
				}
				localCaseManage.deleteCasePropVal(curPatient.getId());
				if(course != null)
				{
					String[] courseArray = course.split(",");
					for(String anCourse : courseArray){
						CasePatientPropVal newProp = new CasePatientPropVal();
						newProp.setPatientId(curPatient.getId());
						anCourse = anCourse.trim();
						if (!anCourse.equals("")){
							newProp.setPropId(Long.valueOf(anCourse));
							localCaseManage.addPatientProp(newProp);
						}
					}
				}
				if(subject != null)
				{
					String[] subjectArray = subject.split(",");	
					for(String anSubject : subjectArray){
						CasePatientPropVal newProp = new CasePatientPropVal();
						newProp.setPatientId(curPatient.getId());
						anSubject = anSubject.trim();
						if (!anSubject.equals("")){
							newProp.setPropId(Long.valueOf(anSubject));
							localCaseManage.addPatientProp(newProp);
						}
					}
				}
			}
		}
		if(type.equals("2")) type = "2&state=1";
		response.sendRedirect("caseManage.do?type="+type);
		
		return null;
}
	
	public CaseDisease getDiseaseFromRequest(HttpServletRequest request,CaseDisease curDisease){
		
		String diseaseDate = request.getParameter("disease_date");
		Integer diseaseType = Integer.valueOf(request.getParameter("diseaseType"));
		String complaint = request.getParameter("complaint");
		String currentDisease = request.getParameter("currentDisease");
		String history1 = request.getParameter("history1");
		String history2 = request.getParameter("history2");
		String history3= request.getParameter("history3");
		String history4 = request.getParameter("history4");
		Integer bodyState1Type = Integer.valueOf(request.getParameter("bodyState1Type"));
		String bodyState1 = request.getParameter("bodyState1");
		Integer bodyState2Type = Integer.valueOf(request.getParameter("bodyState2Type"));
		String bodyState2 = request.getParameter("bodyState2");
		Integer bodyState3Type = Integer.valueOf(request.getParameter("bodyState3Type"));
		String bodyState3 = request.getParameter("bodyState3");
		Integer bodyState4Type = Integer.valueOf(request.getParameter("bodyState4Type"));
		String bodyState4 = request.getParameter("bodyState4");
		Integer bodyState5Type = Integer.valueOf(request.getParameter("bodyState5Type"));
		String bodyState5 = request.getParameter("bodyState5");
		Integer bodyState6Type = Integer.valueOf(request.getParameter("bodyState6Type"));
		String bodyState6 = request.getParameter("bodyState6");
		Integer bodyState7Type = Integer.valueOf(request.getParameter("bodyState7Type"));
		String bodyState7 = request.getParameter("bodyState7");
		Integer bodyState8Type = Integer.valueOf(request.getParameter("bodyState8Type"));
		String bodyState8 = request.getParameter("bodyState8");
		
		String assay = request.getParameter("assay");
		String image = request.getParameter("image");
		String rest = request.getParameter("rest");
		
		String plan = request.getParameter("plan");
		String futureState = request.getParameter("futureState");
		curDisease.setDiseaseDate(diseaseDate);
		curDisease.setDiseaseType(diseaseType);
		curDisease.setComplaint(complaint);
		curDisease.setCurrentDisease(currentDisease);
		curDisease.setHistory1(history1);
		curDisease.setHistory2(history2);
		curDisease.setHistory3(history3);
		curDisease.setHistory4(history4);
		curDisease.setBodyState1(bodyState1);
		curDisease.setBodyState2(bodyState2);
		curDisease.setBodyState3(bodyState3);
		curDisease.setBodyState4(bodyState4);
		curDisease.setBodyState5(bodyState5);
		curDisease.setBodyState6(bodyState6);
		curDisease.setBodyState7(bodyState7);
		curDisease.setBodyState8(bodyState8);
		curDisease.setBodyState1Type(bodyState1Type);
		curDisease.setBodyState2Type(bodyState2Type);
		curDisease.setBodyState3Type(bodyState3Type);
		curDisease.setBodyState4Type(bodyState4Type);
		curDisease.setBodyState5Type(bodyState5Type);
		curDisease.setBodyState6Type(bodyState6Type);
		curDisease.setBodyState7Type(bodyState7Type);
		curDisease.setBodyState8Type(bodyState8Type);
		curDisease.setAssay(assay);
		curDisease.setImage(image);
		curDisease.setRest(rest);
		curDisease.setPlan(plan);
		curDisease.setPlan(plan);
		curDisease.setFutureState(futureState);
		return curDisease;
	}
	public CasePatient getPatientFromRequest(HttpServletRequest request, CasePatient oldPatient){
		String diagnosis_date = request.getParameter("diagnosis_date");
		String pName = request.getParameter("pName");
		Integer pSex = Integer.valueOf(request.getParameter("pSex"));
		String pCertificate = request.getParameter("pCertificate");
		Date birthday = null;
		//String pCertificate = request.getParameter("pCertificate");
		if(pCertificate != null && pCertificate.length() == 18)
		{
			String Certificate = pCertificate.substring(6, 14);
			
			if(Certificate != null)
			{
				birthday = DateUtil.parse(Certificate,"yyyyMMdd");
			}	
		}
		
		Integer pNationalState = Integer.valueOf(request.getParameter("pNational"));
		String pNumberType1 =request.getParameter("pNumberType1");
		if(pNumberType1 != null && !pNumberType1.equals(""))
		{
			oldPatient.setNumber1Type(Integer.valueOf(pNumberType1));
		}
		String pNumber1 = request.getParameter("pNumber1");
		String pNumberType2 =request.getParameter("pNumberType2");
		if(pNumberType2 != null && !pNumberType2.equals(""))
		{
			oldPatient.setNumber2Type(Integer.valueOf(pNumberType2));
		}
		String pNumber2 = request.getParameter("pNumber2");
		String phoneNumber1 = request.getParameter("pPhoneNumber1");
		String phoneNumber2 = request.getParameter("pPhoneNumber2");	
		String diagName = request.getParameter("diagName");
		oldPatient.setDiagDate(diagnosis_date);
		oldPatient.setPName(pName);
		oldPatient.setSex(pSex);
		oldPatient.setBirthday(DateUtils.formatDate(birthday, "yyyy-MM-dd"));
		oldPatient.setCertificate(pCertificate);
		oldPatient.setNationalState(pNationalState);
		oldPatient.setNumber1(pNumber1);
		oldPatient.setNumber2(pNumber2);
		oldPatient.setDiagName(diagName);
		oldPatient.setPhoneNumber1(phoneNumber1);
		oldPatient.setPhoneNumber2(phoneNumber2);
		return oldPatient;
	}
}
