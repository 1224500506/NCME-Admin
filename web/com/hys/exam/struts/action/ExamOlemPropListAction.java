package com.hys.exam.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.queryObj.ExamOlemPropQuery;
import com.hys.exam.returnObj.ExamReturnOlemProp;
import com.hys.exam.sessionfacade.local.ExamOlemPropFacade;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-1-28
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamOlemPropListAction extends BaseAction {

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
		
		
		PageList pl = new PageList();
	
		String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
		String type = request.getParameter("type") == null ? "1" : request.getParameter("type");
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = PageUtil.getPageSize(request);
		
		ExamOlemPropQuery query = new ExamOlemPropQuery();
		query.setPageNo(currentPage);
		query.setPageSize(pageSize);
		query.setParent_id(Long.valueOf(id));
		
		ExamReturnOlemProp olemProp = localExamOlemPropFacade.getExamOlemPropList(query);
		
		pl.setList(olemProp.getReturnList());
		pl.setObjectsPerPage(pageSize);
		pl.setPageNumber(currentPage);
		pl.setFullListSize(olemProp.getTotal_count());
		
		request.setAttribute("olemProplist",pl);
		request.setAttribute("olemPropParentId", id);
		request.setAttribute("olemPropType", type);
		
		return "SUCCESS";
	}

}
