package com.hys.exam.struts.action.exam.category;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamExaminType;
import com.hys.exam.sessionfacade.local.ExamExaminTypeFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试分类新增
 * 
 * 作者：陈明凯 2013-3-7
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamTypeAddAction extends BaseAction {

	private ExamExaminTypeFacade examExaminTypeFacade;

	public void setExamExaminTypeFacade(
			ExamExaminTypeFacade examExaminTypeFacade) {
		this.examExaminTypeFacade = examExaminTypeFacade;
	}

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {

		Long id = ParamUtils.getLongParameter(request, "curTypeId", -1L);

		if (id >= 0) {
			List<ExamExaminType> list = examExaminTypeFacade
					.getExamExaminTypeRootListByChildId(id);

			Collections.reverse(list);

			request.setAttribute("ExamExaminTypeList", list);
		}

		saveToken(request);// 设置token

		return Constants.SUCCESS;
	}

}
