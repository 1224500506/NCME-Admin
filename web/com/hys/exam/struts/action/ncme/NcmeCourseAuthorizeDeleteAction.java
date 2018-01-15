package com.hys.exam.struts.action.ncme;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.constants.Constants;
import com.hys.exam.model.NcmeCourseCredit;
import com.hys.exam.sessionfacade.local.NcmeCourseCreditFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-5-24
 * 
 * 描述：
 * 
 * 说明:
 */
public class NcmeCourseAuthorizeDeleteAction extends BaseAction {
	private NcmeCourseCreditFacade ncmeCourseCreditFacade;

	public void setNcmeCourseCreditFacade(
			NcmeCourseCreditFacade ncmeCourseCreditFacade) {
		this.ncmeCourseCreditFacade = ncmeCourseCreditFacade;
	}

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {
		if (isTokenValid(request, true)) {

			String[] auth = request.getParameterValues("ids");

			List<NcmeCourseCredit> list = new ArrayList<NcmeCourseCredit>(auth.length);

			for (int i = 0; i < auth.length; ++i) {
				String[] array = auth[i].split("_");
				if (array.length >= 3) {
					NcmeCourseCredit n = new NcmeCourseCredit();
					n.setCreditYear(array[0]);
					n.setCourseId(array[1]);
					n.setSiteId(new Long(array[2]));

					list.add(n);
				}
			}

			ncmeCourseCreditFacade.deleteNcmeCourseCreditList(list);
		} else {
			saveToken(request);// 设置token
		}
		return Constants.SUCCESS;
	}
}
