package com.hys.exam.struts.action;

import java.lang.Thread.State;
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
import com.hys.exam.model.CasePatient;
import com.hys.exam.model.CasePatientDiagnosis;
import com.hys.exam.model.CasePatientPropVal;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.CaseManage;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：病历管理平台
 * 
 * 作者：Tiger 2016-09-17
 * 
 * 描述：
 * 
 * 说明:
 */
public class CaseManageAction extends BaseAction {

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
		List<String> searchList = new ArrayList<String>();
		searchList.add(request.getParameter("patientName"));
		searchList.add(request.getParameter("examTime"));
		searchList.add(request.getParameter("examination"));
		if (("-1").equals(request.getParameter("state")))
			searchList.add(null);
		else
			searchList.add(request.getParameter("state"));
		searchList.add(request.getParameter("propIds"));
		searchList.add(request.getParameter("zutiIds"));
		
		
		List<CaseCase> caseList = new ArrayList<CaseCase>();
		List<CaseCasePatient> casePatientList = new ArrayList<CaseCasePatient>();
		caseList = localCaseManage.getCaseList(searchList);
		
		
		
		for(int i = 0 ; i < caseList.size() ; i++){
			if(!caseList.get(i).getState().equals(5))
			{				
				String courseString = "";
				CasePatient patient = localCaseManage.getPatientById(caseList.get(i).getpId());
				String Certificate = patient.getCertificate();
				Date birthday = DateUtil.parse(patient.getBirthday(), "yyyy-MM-dd");
				patient.setpAge(getAge(birthday));
				CaseCasePatient newCasePatient = new CaseCasePatient();
				newCasePatient.setCaseObject(caseList.get(i));
				newCasePatient.setPatientObject(patient);
				newCasePatient.setPatientDiagnosisObject(getPatientDiagnosis(patient.getId()));
				
				List<CasePatientPropVal> patientProp = new ArrayList<CasePatientPropVal>();
				patientProp = localCaseManage.getCasePropByPatientId(patient.getId());
				for (int j = 0 ; j < patientProp.size() ; j ++)
				{
					CasePatientPropVal curProp = patientProp.get(j);
					if (curProp.getType() <= 5){
						courseString += curProp.getPropName() + ",";
					}
				}
				if (courseString != "")
				{
					courseString = courseString.substring(0,courseString.length() - 1);
				}
				newCasePatient.setPropNames(courseString);
				casePatientList.add(newCasePatient);
			}
		}
		request.setAttribute("type", type);
		request.setAttribute("patientName", request.getParameter("patientName"));
		request.setAttribute("examTime", request.getParameter("examTime"));
		request.setAttribute("examination", request.getParameter("examination"));
		request.setAttribute("state", request.getParameter("state"));
		request.setAttribute("propIds", request.getParameter("propIds"));
		request.setAttribute("ICD", request.getParameter("ICD"));
		request.setAttribute("propNames", request.getParameter("propNames"));
		request.setAttribute("zutiIds", request.getParameter("zutiIds"));
		request.setAttribute("zutiNames", request.getParameter("zutiNames"));

		request.setAttribute("totalCount", caseList.size());
		request.setAttribute("casePatientList", casePatientList);
		return "success";
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
}
