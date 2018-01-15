package com.hys.exam.struts.action;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.common.util.DateUtils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CaseCase;
import com.hys.exam.model.CaseDisease;
import com.hys.exam.model.CaseDiseaseDiagnosis;
import com.hys.exam.model.CaseDiseaseDiscuss;
import com.hys.exam.model.CasePatient;
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
 * 说明: 添加病例列表
 */
public class CaseCaseAddAction extends BaseAction {

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
		if(type == null || type =="")
		{
			type = "1";
		}
		String pId = request.getParameter("patientId");
		Long patientId = Long.valueOf(pId);
		CaseDisease newDisease = getDiseaseFromRequest(request);
		
		if(((CaseCaseForm) form).getPlanFile()!=null){
			newDisease.setPlanFile(FilesUtils.caseUpload(((CaseCaseForm) form).getPlanFile(), request, Constants.UPLOAD_FILE_PATH_CASE_PATIENT,patientId,"disease_plan"));
		}
		if(((CaseCaseForm) form).getAssayFile()!=null){
			newDisease.setAssayFile(FilesUtils.caseUpload(((CaseCaseForm) form).getAssayFile(), request, Constants.UPLOAD_FILE_PATH_CASE_PATIENT,patientId,"disease_assay"));
		}
		if(((CaseCaseForm) form).getImageFile()!=null){
			newDisease.setImageFile((FilesUtils.caseUpload(((CaseCaseForm) form).getImageFile(), request, Constants.UPLOAD_FILE_PATH_CASE_PATIENT,patientId,"disease_image")));
		}
		if(((CaseCaseForm) form).getRestFile()!=null){
			newDisease.setRestFile((FilesUtils.caseUpload(((CaseCaseForm) form).getRestFile(), request, Constants.UPLOAD_FILE_PATH_CASE_PATIENT,patientId,"disease_rest")));
		}
		
		//添加新的病程
		Long diseaseId = localCaseManage.addDisease(newDisease);
			String jsonDiagnosisStr = request.getParameter("jsonDiagnosis");
			String jsonDiscussStr = request.getParameter("jsonDiscuss");
			JSONArray jsonDiagnosisArr = JSONArray.fromObject(jsonDiagnosisStr);
			JSONArray jsonDiscussArr = JSONArray.fromObject(jsonDiscussStr);

			Integer diagCount = jsonDiagnosisArr.size();
			for(Integer i = 0 ; i < diagCount ; i ++){
				String diagnosis = jsonDiagnosisArr.getJSONObject(i).getString("diseaseDiag");
				CaseDiseaseDiagnosis newDiagnosis = new CaseDiseaseDiagnosis();
				newDiagnosis.setDiseaseId(diseaseId);
				newDiagnosis.setDiagnosis(diagnosis);
				//添加病程里的诊断
				localCaseManage.addCourseDiagnosis(newDiagnosis);
			}
			
			Integer discussCount = jsonDiscussArr.size();
			for(Integer i = 0 ; i < discussCount ; i ++){
				String discussProp = jsonDiscussArr.getJSONObject(i).getString("discussProp");
				String discussAnalysis = jsonDiscussArr.getJSONObject(i).getString("discussAnalysis");
				CaseDiseaseDiscuss newDiscuss = new CaseDiseaseDiscuss();
				newDiscuss.setDiseaseId(diseaseId);
				newDiscuss.setProp(discussProp);
				newDiscuss.setAnalysis(discussAnalysis);
				//添加讨论点
				localCaseManage.addCourseDisscuss(newDiscuss);
			}
			
			CaseCase newCase = new CaseCase();
			newCase.setpId(patientId);
			newCase.setDiseaseId(diseaseId);
			Date createDate = new Date();
			newCase.setCreateDate(DateUtils.formatDate(createDate, "yyyy-MM-dd"));
			CasePatient curPatient = localCaseManage.getPatientById(patientId);
			if(curPatient.getNationalState().equals(1))
			{
				newCase.setState(1);
			}
			else
			{
				newCase.setState(0);
			}
			//添加病例
			localCaseManage.addCase(newCase);
			if(type.equals("2")) type = "2&state=1";
			response.sendRedirect("caseManage.do?type="+type);
			return null;		
	}
	
	public CaseDisease getDiseaseFromRequest(HttpServletRequest request){
		
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
		
		CaseDisease newDisease = new CaseDisease();
		newDisease.setDiseaseDate(diseaseDate);
		newDisease.setDiseaseType(diseaseType);
		newDisease.setComplaint(complaint);
		newDisease.setCurrentDisease(currentDisease);
		newDisease.setHistory1(history1);
		newDisease.setHistory2(history2);
		newDisease.setHistory3(history3);
		newDisease.setHistory4(history4);
		newDisease.setBodyState1(bodyState1);
		newDisease.setBodyState2(bodyState2);
		newDisease.setBodyState3(bodyState3);
		newDisease.setBodyState4(bodyState4);
		newDisease.setBodyState5(bodyState5);
		newDisease.setBodyState6(bodyState6);
		newDisease.setBodyState7(bodyState7);
		newDisease.setBodyState8(bodyState8);
		newDisease.setBodyState1Type(bodyState1Type);
		newDisease.setBodyState2Type(bodyState2Type);
		newDisease.setBodyState3Type(bodyState3Type);
		newDisease.setBodyState4Type(bodyState4Type);
		newDisease.setBodyState5Type(bodyState5Type);
		newDisease.setBodyState6Type(bodyState6Type);
		newDisease.setBodyState7Type(bodyState7Type);
		newDisease.setBodyState8Type(bodyState8Type);
		newDisease.setAssay(assay);
		newDisease.setImage(image);
		newDisease.setRest(rest);
		newDisease.setPlan(plan);
		newDisease.setPlan(plan);
		newDisease.setFutureState(futureState);
		return newDisease;
	}
}
