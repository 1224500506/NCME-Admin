package com.hys.exam.struts.action.exam.paper;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.sessionfacade.local.ExamQuestionLabelFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑系统
 * 
 * 作者：张伟清 2013-03-07
 * 
 * 描述：跳转到添加页面
 * 
 * 说明:
 */
public class PaperAddToAction extends BaseAction {

	private ExamQuestionLabelFacade examQuestionLabelFacade ;
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<ExamQuestionLabel> questLabel = examQuestionLabelFacade.getQuestionLabel(1) ;
		
		request.setAttribute("questLableList", questLabel) ;
		
		return Constants.SUCCESS;
	}

	public void setExamQuestionLabelFacade(ExamQuestionLabelFacade examQuestionLabelFacade) {
		this.examQuestionLabelFacade = examQuestionLabelFacade;
	}
}