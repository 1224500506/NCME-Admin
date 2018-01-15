package com.hys.exam.struts.action.course;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.StudyCourseType;
import com.hys.exam.sessionfacade.local.StudyCourseTypeFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-2-27
 * 
 * 描述：
 * 
 * 说明:
 */
public abstract class StudyCourseTypeBaseAction extends BaseAction {

	private StudyCourseTypeFacade studyCourseTypeFacade;

	public void setStudyCourseTypeFacade(
			StudyCourseTypeFacade studyCourseTypeFacade) {
		this.studyCourseTypeFacade = studyCourseTypeFacade;
	}

	/**
	 * @param request
	 * @return
	 */
	protected String getStudyCourseType(HttpServletRequest request) {
		Long id = ParamUtils.getLongParameter(request, "curTypeId", -1L);

		if (id >= 0) {
			List<StudyCourseType> list = studyCourseTypeFacade
					.getStudyCourseTypeListById(id);

			request.setAttribute("StudyCourseTypeList", list);
		}

		id = ParamUtils.getLongParameter(request, "id", -1L);

		if (id > 0) {
			StudyCourseType studyCourseType = studyCourseTypeFacade.getStudyCourseTypeById(id);

			if (studyCourseType != null) {
				request.setAttribute("StudyCourseType", studyCourseType);
			}
		}

		return Constants.SUCCESS;
	}
}
