package com.hys.exam.struts.action.exam;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamExaminType;
import com.hys.exam.model.SystemSite;

/**
 * 
 * 标题：新增考试信息
 * 
 * 作者：陈明凯 2013-3-8
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamAddAction extends ExamBaseAction {

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {

		Long id = ParamUtils.getLongParameter(request, "curTypeId", -1L);

		if (id >= 0) {
			List<ExamExaminType> list = examExaminTypeFacade.getExamExaminTypeRootListByChildId(id);

			Collections.reverse(list);

			request.setAttribute("ExamTypeList", list);
		}
		
		//站点列表
		List<SystemSite> siteList = getSystemSite();
		request.setAttribute("siteList", siteList);

		return Constants.SUCCESS;
	}
}
