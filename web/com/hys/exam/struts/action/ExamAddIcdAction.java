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
 * 添加ICD属性
 * @author Han
 *
 */
public class ExamAddIcdAction extends BaseAction {
	
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
		ExamICD prop = new ExamICD();
		
		prop.setCode(pform.getCode());
		prop.setType(pform.getType());
		prop.setNameEn(pform.getNameEn());
		prop.setName(pform.getPropName());
		//prop.setC_type(pform.getC_type());
		prop.setHint(pform.getHint());
		
		boolean flag = localExamPropValManage.addIcdVal(prop);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}

}
