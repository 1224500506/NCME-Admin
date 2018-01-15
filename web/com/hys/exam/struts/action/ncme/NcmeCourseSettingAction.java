package com.hys.exam.struts.action.ncme;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Utils;
import com.hys.exam.model.StudyCourseSetting;
import com.hys.exam.service.local.NcmeCourseCreditManage;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：
 * 
 * 作者：chenlaibin 2014-4-14
 * 
 * 描述：
 * 
 * 说明:
 */
public class NcmeCourseSettingAction extends BaseAction {

	private NcmeCourseCreditManage ncmeCourseCreditManage;

	public void setNcmeCourseCreditManage(
			NcmeCourseCreditManage ncmeCourseCreditManage) {
		this.ncmeCourseCreditManage = ncmeCourseCreditManage;
	}
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = ParamUtils.getParameter(request, "method");
		if(method.equals("edit")){
			return this.edit(mapping, form, request, response);
		}else if(method.equals("update")){
			return this.update(mapping, form, request, response);
		}
		return null;
	}
	
	private String edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		StudyCourseSetting setting = this.ncmeCourseCreditManage.getStudyCourseSetting();
		request.setAttribute("setting", setting);
		return "edit";
	}

	private String update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Long id = ParamUtils.getLongParameter(request, "id", 1);
		Double time = ParamUtils.getDoubleParameter(request, "time", 0);
		int row = this.ncmeCourseCreditManage.saveStudyCourseSetting(id, time);
		Utils.renderText(response, row+"");
		return null;
	}

}
