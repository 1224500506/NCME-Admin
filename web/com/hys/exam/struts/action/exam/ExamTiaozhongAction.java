package com.hys.exam.struts.action.exam;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.NumberUtil;
import com.hys.exam.constants.Constants;

/**
 * 
 * 标题：删除考试
 * 
 * 作者：陈明凯 2013-3-13
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamTiaozhongAction extends ExamBaseAction {

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			String examId = request.getParameter("examId");
			String typeId = request.getParameter("typeid");
			if(examId != null && !examId.equals("") && typeId!=null && !typeId.equals(""))
			{
				examExaminationFacade.updateExaminationTypeByExamId(Long.valueOf(typeId),Long.valueOf(examId));
				StrutsUtil.renderText(response, "success");
			}
		return null;
	}
	
	protected String recoverExam(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {

		 
			String[] array = request.getParameterValues("curIds");

			if (array.length > 0) {
				List<Long> list = new ArrayList<Long>();

				for (int i = 0; i < array.length; ++i) {
					list.add(NumberUtil.parseLong(array[i], 0));
				}

				examExaminationFacade.recoverExaminationList(list);

				request.getSession().setAttribute(Constants.MESSAGE, Constants.MESSAGE_2);
			} else {
				saveToken(request);// 设置token
			}
		 

		return Constants.SUCCESS;
	}

}
