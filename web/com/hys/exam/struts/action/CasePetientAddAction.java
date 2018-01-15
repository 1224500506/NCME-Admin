package com.hys.exam.struts.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.StrutsUtil;
import com.hys.auth.util.Utils;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CasePatient;
import com.hys.exam.model.CasePatientDiagnosis;
import com.hys.exam.model.CasePatientPropVal;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.queryObj.ExamQuestionQuery;
import com.hys.exam.service.local.CaseManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionLabelFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionTypeFacade;
import com.hys.exam.struts.form.ExamQuestionForm;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;

import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：病历管理平台
 * 
 * 作者：Tiger 2016-09-17
 * 
 * 描述：
 * 
 * 说明:添加患者信息
 */
public class CasePetientAddAction extends BaseAction {

	CaseManage localCaseManage;
	public CaseManage getlocalCaseManage() {
		return localCaseManage;
	}
	public void setlocalCaseManage(CaseManage localCaseManage) {
		this.localCaseManage = localCaseManage;
	}
	@SuppressWarnings("unchecked")
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String diagnosis_date = request.getParameter("diagnosis_date");
		String pName = request.getParameter("pName");
		Integer pSex = Integer.valueOf(request.getParameter("pSex"));
		//String birthday = request.getParameter("pBirthDay");
		String pCertificate = request.getParameter("pCertificate");
		Integer pNationalState = null;
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
		
		
		if(request.getParameter("pNationalState") != null)
		{
			pNationalState = Integer.valueOf(request.getParameter("pNationalState"));
		}
		Integer pNumberType1 = null;
		if(request.getParameter("pNumberType1") != null)
		{
			pNumberType1 = Integer.valueOf(request.getParameter("pNumberType1"));
		}
		String pNumber1 = request.getParameter("pNumber1");
		Integer pNumberType2 = null;
		if(request.getParameter("pNumberType2") != null){
			pNumberType2 = Integer.valueOf(request.getParameter("pNumberType2"));	
		}
		String pNumber2 = request.getParameter("pNumber2");
		String phoneNumber1 = request.getParameter("pPhoneNumber1");
		String phoneNumber2 = request.getParameter("pPhoneNumber2");	
		String diagName = request.getParameter("diagName");
		String course = request.getParameter("course");
		String subject = request.getParameter("subject");
		CasePatient newPatient = new CasePatient();
		newPatient.setDiagDate(diagnosis_date);
		newPatient.setPName(pName);
		newPatient.setSex(pSex);
		if(birthday != null)
		{
			newPatient.setBirthday(DateUtil.format(birthday, "yyyy-MM-dd"));	
		}
		newPatient.setCertificate(pCertificate);
		newPatient.setNationalState(pNationalState);
		newPatient.setNumber1Type(pNumberType1);
		newPatient.setNumber2Type(pNumberType2);
		newPatient.setNumber1(pNumber1);
		newPatient.setNumber2(pNumber2);
		newPatient.setDiagName(diagName);
		newPatient.setPhoneNumber1(phoneNumber1);
		newPatient.setPhoneNumber2(phoneNumber2);
		Long newId = localCaseManage.addPatient(newPatient);
		if(newId != null){
			Integer diagCount = Integer.valueOf(request.getParameter("diagCount"));
			for(Integer i = 0 ; i < diagCount ; i ++){
				String diagParam = "diagNo" + i.toString();
				CasePatientDiagnosis newDiagnosis = new CasePatientDiagnosis();
				newDiagnosis.setDiagnosis(request.getParameter(diagParam));
				newDiagnosis.setPatientId(newId);
				localCaseManage.addPatientDiagnosis(newDiagnosis);
			}
			if(course != null && !course.equals(""))
			{
				String[] courseArray = course.split(",");
				for(String anCourse : courseArray){
					anCourse = anCourse.trim();
					CasePatientPropVal newProp = new CasePatientPropVal();
					newProp.setPatientId(newId);
					newProp.setPropId(Long.valueOf(anCourse));
					localCaseManage.addPatientProp(newProp);
				}
			}
			if(subject != null && !subject.equals(""))
			{
				String[] subjectArray = subject.split(",");	
				for(String anSubject : subjectArray){
					anSubject = anSubject.trim();
					CasePatientPropVal newProp = new CasePatientPropVal();
					newProp.setPatientId(newId);
					newProp.setPropId(Long.valueOf(anSubject));
					localCaseManage.addPatientProp(newProp);
				}
			}
		}
		
		StrutsUtil.renderText(response, newId.toString());
		return null;
	}
	private Integer getAge(Date birthday) {
		// TODO Auto-generated method stub
		return null;
	}
		

}
