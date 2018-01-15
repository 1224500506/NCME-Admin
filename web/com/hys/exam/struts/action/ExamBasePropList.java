package com.hys.exam.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.model.ExamProp;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
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
public class ExamBasePropList extends BaseAction {
	
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
		PageList pl = new PageList();
	
		
		String prop_id = request.getParameter("prop_id") == null ? "0" : request.getParameter("prop_id");
		String olemPropId = request.getParameter("olemPropId") == null ? "0" : request.getParameter("olemPropId");
		String type = request.getParameter("type") == null ? "1" : request.getParameter("type");
		ExamProp prop = new ExamProp();
		if(type.equals("1") && prop_id.equals("0")){
			prop.setType(1);
			List<ExamProp> list = localExamPropValFacade.getPropListByType(prop);
			request.setAttribute("olemPropId", olemPropId);
			request.setAttribute("type", type);
			request.setAttribute("propList", list);
		}else{
			
			ExamPropQuery query  = new ExamPropQuery();
			
			int currentPage = PageUtil.getPageIndex(request);
			int pageSize = PageUtil.getPageSize(request);
			
			query.setPageNo(currentPage);
			query.setPageSize(pageSize);
			
			query.setSysPropId(Long.valueOf(prop_id));
			ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
			
			pl.setList(rprop.getReturnList());
			pl.setObjectsPerPage(pageSize);
			pl.setPageNumber(currentPage);
			pl.setFullListSize(rprop.getTotal_count());
			request.setAttribute("olemPropId", olemPropId);
			request.setAttribute("type", type);
			request.setAttribute("propList", pl);
		}
		
		return "SUCCESS";
	}
}
