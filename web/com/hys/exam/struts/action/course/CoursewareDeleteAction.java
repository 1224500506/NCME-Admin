package com.hys.exam.struts.action.course;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.model.HysUsers;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.common.util.NumberUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.StudyCourseware;
import com.hys.exam.model.SystemUser;
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
public class CoursewareDeleteAction extends BaseAction {
	private StudyCoursewareFacade studyCoursewareFacade;

	public void setStudyCoursewareFacade(
			StudyCoursewareFacade studyCoursewareFacade) {
		this.studyCoursewareFacade = studyCoursewareFacade;
	}

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {

		if (isTokenValid(request, true)) {
			String[] array = request.getParameterValues("ids");

			if (array.length > 0) {
				List<StudyCourseware> list = new ArrayList<StudyCourseware>();

				SystemUser user = LogicUtil.getSystemUser(request);

				for (int i = 0; i < array.length; ++i) {
					StudyCourseware s = new StudyCourseware();
					s.setId(NumberUtil.parseLong(array[i]));
					s.setLastUpdateUserId(user.getUserId());
					list.add(s);
				}

				studyCoursewareFacade.deleteStudyCourseware(list);

				request.getSession().setAttribute(Constants.MESSAGE, Constants.MESSAGE_2);
			}

		} else {
			saveToken(request);// 设置token
		}

		return Constants.SUCCESS;
	}

}
