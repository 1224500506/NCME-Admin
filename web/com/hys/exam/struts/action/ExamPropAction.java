package com.hys.exam.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.model.ExamProp;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-1-18
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPropAction extends BaseAction {
	
	private ExamPropValFacade localExamPropValFacade;
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String type = request.getParameter("type") == null ? "0" : request.getParameter("type");
		String sname = request.getParameter("sname") == null ? "" : request.getParameter("sname");
		ExamProp prop = new ExamProp();
		prop.setName(sname);
		prop.setType(Integer.valueOf(type));
		List<ExamProp> list = localExamPropValFacade.getPropListByType(prop);
		request.setAttribute("type", type);
		request.setAttribute("propList", list);
		request.setAttribute("sname", sname);
		return "SUCCESS";
	}

}
