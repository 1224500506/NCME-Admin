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
import com.hys.exam.model.ExamICD;
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
 * 说明:删除病例
 */
public class CaseDeleteCaseAction extends BaseAction {

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
		String caseId = request.getParameter("caseId") ;
		String mode = request.getParameter("mode") == null ? "" : request.getParameter("mode");
		String ids = request.getParameter("ids") == null ? "" : request.getParameter("ids");
		
		if (mode.equals("batchdel"))
		{
			

			String[] idss = ids.split(",");
			Integer dels = 0;
			for (int i = 0; i < idss.length; i++){
				//prop.setId(Long.valueOf(idss[i].trim()));
				boolean flag = localCaseManage.deleteCase(Long.valueOf(idss[i]));
				if (flag) dels++;
			}
			StrutsUtil.renderText(response, dels.toString());
			return null;
		}
		
		if(caseId != null && caseId != ""){
			Boolean result = localCaseManage.deleteCase(Long.valueOf(caseId));
			if(result)
				StrutsUtil.renderText(response, "success");
			else
			{
				StrutsUtil.renderText(response, "fail");
			}
		}
		else
		{
			StrutsUtil.renderText(response, "fail");
		}
		return null;
	}

}
