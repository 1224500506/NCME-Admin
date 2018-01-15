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
import com.hys.exam.model.StudyCourseType;
import com.hys.exam.model.SystemUser;
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
public class StudyCourseTypeDeleteAction extends BaseAction {
	private StudyCourseTypeFacade studyCourseTypeFacade;

	public void setStudyCourseTypeFacade(
			StudyCourseTypeFacade studyCourseTypeFacade) {
		this.studyCourseTypeFacade = studyCourseTypeFacade;
	}

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (isTokenValid(request, true)) {
			String[] array = request.getParameterValues("ids");

			if (array.length > 0) {
				List<StudyCourseType> list = new ArrayList<StudyCourseType>();
				SystemUser user = LogicUtil.getSystemUser(request);

				StudyCourseType studyCourseType = studyCourseTypeFacade.getStudyCourseTypeById(NumberUtil.parseLong(array[0]));

				for (int i = 0; i < array.length; ++i) {
					StudyCourseType s = new StudyCourseType();
					s.setId(NumberUtil.parseLong(array[i], -1));
					s.setLastUpdateUserId(user.getUserId());
					s.setParentCourseTypeId(studyCourseType.getParentCourseTypeId());

					list.add(s);
				}
				studyCourseTypeFacade.deleteStudyCourseType(list);

				request.getSession().setAttribute(Constants.MESSAGE, Constants.MESSAGE_2);
				request.setAttribute(Constants.IS_RELOAD_DELETE, Constants.IS_RELOAD_DELETE);
			}
		} else {
			saveToken(request);// 设置token
		}

		return Constants.SUCCESS;
	}

}
