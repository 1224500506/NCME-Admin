package com.hys.exam.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropType;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.framework.web.action.BaseAction;

/**
 * 学科管理
 * @author Han
 *
 */
public class ExamRegionListAction extends BaseAction {
	
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
	
		//取得查询条件
		String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
		String type = request.getParameter("type") == null ? "20" : request.getParameter("type");
		String sname = request.getParameter("sname") == null ? "" : request.getParameter("sname");
		ExamProp prop = new ExamProp();
		
		//取得一级学科
		if(type.equals("20") && id.equals("0")){
			prop.setType(Integer.valueOf(type));
			prop.setName(sname);
			List<ExamProp> list = localExamPropValFacade.getPropListByType(prop);
			
			List<ExamPropType> tlist = localExamPropValFacade.getExamPropTypeList();
			
			request.setAttribute("parentid", "0");
			request.setAttribute("type", type);
			request.setAttribute("prop_val1", id);
			request.setAttribute("propList", list);
			request.setAttribute("ctype", tlist);
			request.setAttribute("sname", sname);
		}else{
			
			//取得二级以上学科
			ExamPropQuery query  = new ExamPropQuery();
			
			int currentPage = PageUtil.getPageIndex(request);
			int pageSize = PageUtil.getPageSize(request);
			
			query.setPageNo(currentPage);
			query.setPageSize(pageSize);
			
			query.setSysPropId(Long.valueOf(id));
			query.setName(sname);
			ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
			List<ExamPropType> tlist = localExamPropValFacade.getExamPropTypeList();
			pl.setList(rprop.getReturnList());
			pl.setObjectsPerPage(pageSize);
			pl.setPageNumber(currentPage);
			pl.setFullListSize(rprop.getTotal_count());

			Long parentPropId = localExamPropValFacade.getParentPropId(Long.valueOf(id));

			request.setAttribute("parentid", parentPropId);
			request.setAttribute("type", type);
			request.setAttribute("prop_val1", id);
			request.setAttribute("propList", pl);
			request.setAttribute("ctype", tlist);
			request.setAttribute("sname", sname);
		}
		
		return "SUCCESS";
	}

}
