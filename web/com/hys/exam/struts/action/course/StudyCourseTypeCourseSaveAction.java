package com.hys.exam.struts.action.course;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.NumberUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.StudyCourseTypeCourse;
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
public class StudyCourseTypeCourseSaveAction extends BaseAction {

	private StudyCourseTypeFacade studyCourseTypeFacade;

	public void setStudyCourseTypeFacade(
			StudyCourseTypeFacade studyCourseTypeFacade) {
		this.studyCourseTypeFacade = studyCourseTypeFacade;
	}

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {

		if (isTokenValid(request, true)) {
			String[] array = request.getParameterValues("curIds");

			if (array.length > 0) {
				Long curTypeId = ParamUtils.getLongParameter(request, "curTypeId", -1L);

				List<StudyCourseTypeCourse> list = new ArrayList<StudyCourseTypeCourse>();

				for (int i = 0; i < array.length; ++i) {
					StudyCourseTypeCourse s = new StudyCourseTypeCourse();
					s.setStudyCourseId(NumberUtil.parseLong(array[i], 0));
					s.setStudyCourseTypeId(curTypeId);
					list.add(s);
				}

				studyCourseTypeFacade.addStudyCourseTypeCourseList(list);

				request.getSession().setAttribute(Constants.MESSAGE, Constants.MESSAGE_1);
			}
		} else {
			saveToken(request);// 设置token
		}

		return Constants.SUCCESS;
	}

}
