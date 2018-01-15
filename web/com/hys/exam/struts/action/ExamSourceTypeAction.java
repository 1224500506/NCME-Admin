package com.hys.exam.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.ExamICD;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamSourceType;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.struts.form.ExamPropForm;
import com.hys.framework.web.action.BaseAction;

/**
 * 来源类型管理
 * @author Han
 *
 */
public class ExamSourceTypeAction extends BaseAction {
	
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

		String method = RequestUtil.getParameter(request, "mode");
		if (method.equals("add")){
			return add(mapping, form, request, response);
		}
		else if (method.equals("edit")){
			return update(mapping, form, request, response);
		}
		else if (method.equals("del")){
			return del(mapping, form, request, response);
		}
		else{
			return list(mapping, form, request, response);
		}
	}
	
	/**
	 * 取得来源类型目录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String sname = request.getParameter("sname") == null ? "" : request.getParameter("sname");
		ExamSourceType prop = new ExamSourceType();
		prop.setType_name(sname);
		List<ExamSourceType> list = localExamPropValFacade.getSourceTypeList(prop);
		request.setAttribute("propList", list);
		request.setAttribute("sname", sname);
		return "SUCCESS";
	}
	
	/**
	 * 添加来源类型
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ExamPropForm pform = (ExamPropForm)form;
		ExamSourceType prop = new ExamSourceType();
		
		prop.setCode(pform.getCode());
		prop.setType_name(pform.getPropName());
		
		boolean flag = localExamPropValFacade.addSourceType(prop);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}
	
	/**
	 * 修改来源类型
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ExamPropForm pform = (ExamPropForm)form;
		ExamSourceType prop = new ExamSourceType();
		
		prop.setCode(pform.getCode());
		prop.setType_name(pform.getPropName());
		prop.setId(pform.getId());
		
		boolean flag = localExamPropValFacade.updateSourceType(prop);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}
	
	/**
	 * 删除来源类型
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ExamPropForm pform = (ExamPropForm)form;
		ExamSourceType prop = new ExamSourceType();
		
	//	prop.setProp_val_id(pform.getProp_val_id());
		prop.setId(pform.getId());
		
		boolean flag = localExamPropValFacade.deleteSourceType(prop);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
		return null;
	}
}
