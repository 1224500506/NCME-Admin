package com.hys.exam.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.ExamProp;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.struts.form.ExamPropForm;
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
public class ExamAddPropAction extends BaseAction {
	
	private ExamPropValFacade localExamPropValManage;
	
	public ExamPropValFacade getLocalExamPropValManage() {
		return localExamPropValManage;
	}

	public void setLocalExamPropValManage(ExamPropValFacade localExamPropValManage) {
		this.localExamPropValManage = localExamPropValManage;
	}

	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ExamPropForm pform = (ExamPropForm)form;
		ExamProp prop = new ExamProp();
		
		prop.setCode(pform.getCode());
		prop.setType(pform.getType());
		prop.setProp_val1(pform.getProp_val1());
		prop.setName(pform.getPropName());
		prop.setExt_type(pform.getExt_type());
		if (pform.getC_type() != null)
			prop.setC_type(pform.getC_type());
		else
			prop.setC_type(0);
		
		boolean flag = localExamPropValManage.addPropVal(prop);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}

}
