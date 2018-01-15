package com.hys.exam.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.StrutsUtil;
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
public class ExamOlemDelBasePropAction extends BaseAction {

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
		
		String olem_prop_id = request.getParameter("olem_prop_id") == null ? "0" : request.getParameter("olem_prop_id");
		String base_prop_id = request.getParameter("base_prop_id") == null ? "0" : request.getParameter("base_prop_id");

		if ((!olem_prop_id.equals("0")) && (!base_prop_id.equals("0"))) {
			ExamOlemPropRefBaseProp prop  = new ExamOlemPropRefBaseProp();
			prop.setBase_prop_id(Long.valueOf(base_prop_id));
			prop.setOlem_prop_id(Long.valueOf(olem_prop_id));
			boolean flag = localExamOlemPropFacade.deleteExamOlemPropRefBaseProp(prop);
			if (flag) {
				StrutsUtil.renderText(response, "success");
			} else {
				StrutsUtil.renderText(response, "error");
			}
		} else{
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}


}
