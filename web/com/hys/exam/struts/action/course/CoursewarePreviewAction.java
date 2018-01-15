package com.hys.exam.struts.action.course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.StudyCourseware;
import com.hys.exam.sessionfacade.local.StudyCoursewareFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：课件预览
 * 
 * 作者：陈明凯 2013-5-15
 * 
 * 描述：
 * 
 * 说明:
 */
public class CoursewarePreviewAction extends BaseAction {
	private StudyCoursewareFacade studyCoursewareFacade;

	public void setStudyCoursewareFacade(
			StudyCoursewareFacade studyCoursewareFacade) {
		this.studyCoursewareFacade = studyCoursewareFacade;
	}

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {
		Long id = ParamUtils.getLongParameter(request, "id", 0L);

		StudyCourseware s = studyCoursewareFacade.getStudyCoursewareById(id);
		
		request.setAttribute("StudyCourseware", s);

		return Constants.SUCCESS;
	}

}
