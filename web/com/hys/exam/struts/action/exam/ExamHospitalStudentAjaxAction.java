package com.hys.exam.struts.action.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamICD;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropType;
import com.hys.exam.model.SystemUser;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.framework.web.action.BaseAction;


public class ExamHospitalStudentAjaxAction extends BaseAction {
	
	private ExamPropValFacade localExamPropValFacade;
	
	private SystemUserManage systemUserManage ;
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}
	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}
	public void setSystemUserManage(SystemUserManage systemUserManage) {
		this.systemUserManage = systemUserManage;
	}


	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		String orgIds = request.getParameter("orgIds") == null ? "1" : request.getParameter("orgIds");
		SystemUser item = new SystemUser();
		item.setUserType(1);
		item.setProfTitle(orgIds); /// use profTitle instead of work_unit_id
		List<SystemUser> userList = systemUserManage.getListByItem(item);
		
		JSONObject result = new JSONObject();
		if(userList.equals(null) || userList.size() < 1)
		{
			StrutsUtil.renderText(response, "fail");
			return null;
		}	
		else{ 
			result.put("list", userList);
			StrutsUtil.renderText(response, result.toString());
			return null;
		}
	}
}
