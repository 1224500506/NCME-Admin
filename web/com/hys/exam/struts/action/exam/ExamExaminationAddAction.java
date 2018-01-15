package com.hys.exam.struts.action.exam;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamExamination;

public class ExamExaminationAddAction extends ExamBaseAction {

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExamExamination newExamin;
		
		return Constants.SUCCESS;
	}
	protected ExamExamination getInfoFromRequest(HttpServletRequest request)
	{
		ExamExamination newObj = new ExamExamination();
		return newObj;
	}
}