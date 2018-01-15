package com.hys.exam.struts.action.expert;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.ExpertGroupTerm;
import com.hys.exam.service.local.ExpertGroupManage;
import com.hys.framework.web.action.BaseAction;

/**
 * 届期管理
 * @author Han
 *
 */
public class ExpertTermAction extends BaseAction {

	private ExpertGroupManage localExpertgroupManage;
	
	public ExpertGroupManage getLocalExpertgroupManage() {
		return localExpertgroupManage;
	}

	public void setLocalExpertgroupManage(ExpertGroupManage localExpertgroupManage) {
		this.localExpertgroupManage = localExpertgroupManage;
	}
	
	/**
	 * 取得符合查询条件的届期
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String sname = request.getParameter("sname") == null ? "" : request.getParameter("sname");
		
		ExpertGroup group = new ExpertGroup();
		group.setName(sname);
		List<ExpertGroupTerm> list = localExpertgroupManage.getTermList(group);
		
		request.setAttribute("sname", sname);
		request.setAttribute("termList", list);
		return "SUCCESS";
	}

}
