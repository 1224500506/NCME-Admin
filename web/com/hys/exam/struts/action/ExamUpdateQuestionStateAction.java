package com.hys.exam.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.StrutsUtil;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.exam.struts.form.ExamQuestionForm;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-20
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamUpdateQuestionStateAction extends BaseAction {

	private ExamQuestionFacade localExamQuestionFacade;
	
	public ExamQuestionFacade getLocalExamQuestionFacade() {
		return localExamQuestionFacade;
	}

	public void setLocalExamQuestionFacade(
			ExamQuestionFacade localExamQuestionFacade) {
		this.localExamQuestionFacade = localExamQuestionFacade;
	}



	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String idsStr = request.getParameter("id");
		int state = Integer.parseInt(request.getParameter("state").toString());
		String[] idsArray = idsStr.split(",");
		String opinion = request.getParameter("opinion");
		
		List<Long> ids = new ArrayList<Long>();
		
		for (int i=0; i<idsArray.length; i++)
			ids.add(Long.parseLong(idsArray[i]));
	
		String userName = request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString();
		
		localExamQuestionFacade.updateQuestionStateByIdArr(ids, state, userName, opinion);
		
		StrutsUtil.renderText(response, "success");
		return null;

	}

}
