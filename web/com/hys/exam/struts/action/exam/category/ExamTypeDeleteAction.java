package com.hys.exam.struts.action.exam.category;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.common.util.NumberUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamExaminType;
import com.hys.exam.sessionfacade.local.ExamExaminTypeFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：删除课程分类
 * 
 * 作者：陈明凯 2013-3-7
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamTypeDeleteAction extends BaseAction {
	private ExamExaminTypeFacade examExaminTypeFacade;

	public void setExamExaminTypeFacade(
			ExamExaminTypeFacade examExaminTypeFacade) {
		this.examExaminTypeFacade = examExaminTypeFacade;
	}

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {

		if (isTokenValid(request, true)) {
			String[] array = request.getParameterValues("ids");

			if (array.length > 0) {
				List<ExamExaminType> list = new ArrayList<ExamExaminType>();

				ExamExaminType examExaminType = examExaminTypeFacade
						.getExamExaminTypeById(NumberUtil.parseLong(array[0]));

				for (int i = 0; i < array.length; ++i) {
					ExamExaminType e = new ExamExaminType();
					e.setId(NumberUtil.parseLong(array[i], -1));
					e.setParent_id(examExaminType.getParent_id());

					list.add(e);
				}

				examExaminTypeFacade.deleteExamExaminTypeList(list);

				request.getSession().setAttribute(Constants.MESSAGE, Constants.MESSAGE_2);
				request.setAttribute(Constants.IS_RELOAD_DELETE, Constants.IS_RELOAD_DELETE);
			} else {
				saveToken(request);// 设置token
			}
		}
		return Constants.SUCCESS;
	}
}
