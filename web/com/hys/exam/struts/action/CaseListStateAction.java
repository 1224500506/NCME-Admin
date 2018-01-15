package com.hys.exam.struts.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.StrutsUtil;
import com.hys.common.util.DateUtils;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.model.CaseCase;
import com.hys.exam.model.CaseCasePatient;
import com.hys.exam.model.CasePatient;
import com.hys.exam.model.CasePatientDiagnosis;
import com.hys.exam.service.local.CaseManage;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：病例审核平台
 * 
 * 作者：Tiger 2016-09-17
 * 
 * 描述：
 * 
 * 说明:
 */
public class CaseListStateAction extends BaseAction {

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
		String caseType = request.getParameter("caseType");
		String deliOpinion = request.getParameter("opinion");
		String[] array = request.getParameterValues("caseId");
		String userName = request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString();
        if (array != null) {
            for (String anArray : array) {
        			Boolean result = localCaseManage.setCaseState(Long.valueOf(anArray), Integer.valueOf(caseType),userName,deliOpinion);
            }
        }
        if(type.equals("1"))
        	response.sendRedirect("caseManage.do?type=1");
        else
           	response.sendRedirect("caseManage.do?type=2&state=1");        
        return null;
 	}
}
