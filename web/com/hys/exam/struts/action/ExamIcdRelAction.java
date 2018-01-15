package com.hys.exam.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.RequestUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.model.ExamICD;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropType;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.PageList;
import com.hys.exam.common.util.PageUtil;
import com.hys.framework.web.action.BaseAction;

/**
 * 与ICD关联的知识点
 * @author Han
 *
 */
public class ExamIcdRelAction extends BaseAction {
	
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
		else
			return list(mapping, form, request, response);
	}
	
	/**
	 * 添加ICD关联知识点
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
		
		String type = request.getParameter("type") == null ? "0" : request.getParameter("type");
		String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
		String propIds = request.getParameter("groupIds") == null ? "" : request.getParameter("groupIds");
		
		ExamICD prop = new ExamICD();
		prop.setId(Long.valueOf(id));
		prop.setPropIds(propIds);
		
		localExamPropValFacade.updateIcdVal(prop);
		
		response.sendRedirect("icdList.do?type="+type);
		return null;
	}

	/**
	 * 取得ICD关联知识点
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
		String type = request.getParameter("type") == null ? "0" : request.getParameter("type");
		String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
		ExamICD prop = new ExamICD();

		prop.setId(Long.valueOf(id));
		prop.setType(Integer.valueOf(type));
		Page<ExamICD> page = PageUtil.getPageByRequest(request);
		localExamPropValFacade.getIcdListByType(page,prop);
		ExamICD list = page.getList().get(0);

		request.setAttribute("type", type);
		request.setAttribute("propList", list);
		return "SUCCESS";
	}

}
