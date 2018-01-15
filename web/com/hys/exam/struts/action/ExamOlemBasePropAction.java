package com.hys.exam.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.model.ExamOlemPropRefBaseProp;
import com.hys.exam.sessionfacade.local.ExamOlemPropFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-2-14
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamOlemBasePropAction extends BaseAction {

	private ExamOlemPropFacade localExamOlemPropFacade;

	
	public ExamOlemPropFacade getLocalExamOlemPropFacade() {
		return localExamOlemPropFacade;
	}


	public void setLocalExamOlemPropFacade(
			ExamOlemPropFacade localExamOlemPropFacade) {
		this.localExamOlemPropFacade = localExamOlemPropFacade;
	}


	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String olemPropId = request.getParameter("olemPropId") == null ? "0" : request.getParameter("olemPropId");
		
		
		List<ExamOlemPropRefBaseProp> proplist = localExamOlemPropFacade.getExamOlemPropRefBasePropList(Long.valueOf(olemPropId));
		
		request.setAttribute("olemPropId", olemPropId);
		request.setAttribute("proplist", proplist);
		
		return "SUCCESS";
	}

}
