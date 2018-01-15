package com.hys.exam.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.ExamICD;
import com.hys.exam.model.ExamProp;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.struts.form.ExamPropForm;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-1-19
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamDelPropAction extends BaseAction {

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
		
		String mode = request.getParameter("mode") == null ? "" : request.getParameter("mode");
		String ids = request.getParameter("ids") == null ? "" : request.getParameter("ids");

		if (mode.equals("batchdel"))
		{
			ExamProp prop = new ExamProp();

			String[] idss = ids.split(",");
			Integer dels = 0;
			for (int i = 0; i < idss.length; i++){
				prop.setId(Long.valueOf(idss[i].trim()));
				prop = localExamPropValFacade.getSysPropVal(prop.getId());
				boolean flag = localExamPropValFacade.deletePropVal(prop);
				if (flag) dels++;
			}
			StrutsUtil.renderText(response, dels.toString());
			return null;
		}
		ExamPropForm pform = (ExamPropForm)form;
		ExamProp prop = new ExamProp();
		
		prop.setProp_val_id(pform.getProp_val_id());
		prop.setId(pform.getId());
		
		boolean flag = localExamPropValFacade.deletePropVal(prop);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}

}
