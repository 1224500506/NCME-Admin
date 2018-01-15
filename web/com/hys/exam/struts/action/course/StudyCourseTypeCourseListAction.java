package com.hys.exam.struts.action.course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.PageUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.sessionfacade.local.StudyCourseTypeFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-3-1
 * 
 * 描述：
 * 
 * 说明:
 */
public class StudyCourseTypeCourseListAction extends BaseAction {

	private StudyCourseTypeFacade studyCourseTypeFacade;

	public void setStudyCourseTypeFacade(
			StudyCourseTypeFacade studyCourseTypeFacade) {
		this.studyCourseTypeFacade = studyCourseTypeFacade;
	}

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {

		Long curTypeId = ParamUtils.getLongParameter(request, "curTypeId", 0);

		if (curTypeId > 0) {
			StudyCourse course = new StudyCourse();
			course.setStudyCourseTypeId(curTypeId);
			course.setStudyCourseName(request.getParameter("studyCourseName"));
			course.setTeacher(request.getParameter("teacher"));
			course.setKeyWord(request.getParameter("keyWord"));
			course.setStudyCourseType(ParamUtils.getIntParameter(request, "studyCourseType", 0));

			Page<StudyCourse> page = PageUtil.getPageByRequest(request);

			studyCourseTypeFacade.getStudyCourseList(page, course);

			request.setAttribute("page", page);

			saveToken(request);// 设置token
		}

		return Constants.SUCCESS;
	}

}
