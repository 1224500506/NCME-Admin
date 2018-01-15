package com.hys.exam.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.ExamOlemProp;
import com.hys.exam.sessionfacade.local.ExamOlemPropFacade;
import com.hys.exam.struts.form.ExamOlemPropForm;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-2-9
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamOlemUpdatePropAction extends BaseAction {


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
		
		ExamOlemPropForm olemPropForm = (ExamOlemPropForm)form;
		
		ExamOlemProp prop = new ExamOlemProp();
		
		prop.setOlem_prop_name(olemPropForm.getOlem_prop_name());
		prop.setId(olemPropForm.getId());
		
		
		boolean flag = localExamOlemPropFacade.updateExamOlemProp(prop);
		
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}

}
