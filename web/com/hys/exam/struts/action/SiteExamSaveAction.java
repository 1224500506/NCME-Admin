package com.hys.exam.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.NumberUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.SystemSiteCourseTypeExam;
import com.hys.exam.sessionfacade.local.SystemSiteFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-3-20
 * 
 * 描述：
 * 
 * 说明:
 */
public class SiteExamSaveAction extends BaseAction {
	private SystemSiteFacade systemSiteFacade;

	public void setSystemSiteFacade(SystemSiteFacade systemSiteFacade) {
		this.systemSiteFacade = systemSiteFacade;
	}

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {
		if (isTokenValid(request, true)) {
			String[] array = request.getParameterValues("curIds");

			Long siteId = ParamUtils.getLongParameter(request, "siteId", 0L);

			Long courseTypeId = ParamUtils.getLongParameter(request, "courseTypeId", 0L);

			if (array.length > 0) {
				List<SystemSiteCourseTypeExam> list = new ArrayList<SystemSiteCourseTypeExam>();

				for (int i = 0; i < array.length; ++i) {

					SystemSiteCourseTypeExam s = new SystemSiteCourseTypeExam();
					s.setSiteId(siteId);
					s.setCourseTypeId(courseTypeId);
					s.setExamId(NumberUtil.parseLong(array[i], 0));

					list.add(s);
				}

				systemSiteFacade.addSystemSiteCourseTypeExamList(list);

				request.setAttribute(Constants.MESSAGE, Constants.MESSAGE_1);
			} else {
				saveToken(request);// 设置token
			}
		}

		return Constants.SUCCESS;
	}

}
