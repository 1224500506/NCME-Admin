package com.hys.exam.struts.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.StrutsUtil;
import com.hys.common.util.DateUtils;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.model.CaseCase;
import com.hys.exam.model.CaseCasePatient;
import com.hys.exam.model.CasePatient;
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
 * 说明:禁用患者状态
 */
public class CaseSetCaseStateAction extends BaseAction {

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
		String caseId = request.getParameter("caseId")==null?"0":request.getParameter("caseId");
		String state = request.getParameter("state")==null?"1":request.getParameter("state");
		String userName = request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString();
		if(caseId != null && caseId != ""){
			Boolean result = localCaseManage.setCaseState(Long.valueOf(caseId), Integer.valueOf(state),userName,"");
			if(result)
				StrutsUtil.renderText(response, "success");
			else
				StrutsUtil.renderText(response, "fail");
		}
		else
		{
			StrutsUtil.renderText(response, "fail");
		}
		return null;
	}

}
