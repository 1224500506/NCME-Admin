package com.hys.exam.struts.action.course;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.StudyCourseware;
import com.hys.exam.model.system.SystemIndustry;
import com.hys.exam.service.remote.SystemPropManage;
import com.hys.exam.sessionfacade.local.StudyCoursewareFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-2-26
 * 
 * 描述：
 * 
 * 说明:
 */
public class CoursewareUpdateAction extends BaseAction {
	private StudyCoursewareFacade studyCoursewareFacade;

	private SystemPropManage systemPropManage;

	public void setSystemPropManage(SystemPropManage systemPropManage) {
		this.systemPropManage = systemPropManage;
	}

	public void setStudyCoursewareFacade(
			StudyCoursewareFacade studyCoursewareFacade) {
		this.studyCoursewareFacade = studyCoursewareFacade;
	}

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {

		Long id = ParamUtils.getLongParameter(request, "id", 0L);

		if (id != 0) {
			StudyCourseware s = studyCoursewareFacade.getStudyCoursewareById(id);

			//行业
			//List<SystemIndustry> industryList = systemPropManage.getSystemIndustryList(0L);
			List<ExamPropVal> industryList = systemPropManage.getExamPropValList(Constants.SYSTEM_PROP_TYPE_INDUSTRY);
			request.setAttribute("industryList", industryList);
			// 适用对象
			List<ExamPropVal> applicableList = systemPropManage.getExamPropValList(Constants.SYSTEM_PROP_TYPE_APPLICABLE);
			request.setAttribute("applicableList", applicableList);
			// 知识分类
			List<ExamPropVal> knowList = systemPropManage.getExamPropValList(Constants.SYSTEM_PROP_TYPE_KNOW);
			request.setAttribute("knowList", knowList);

			request.setAttribute("StudyCourseware", s);

			saveToken(request);// 设置token
		}

		
		return Constants.SUCCESS;
	}

}
