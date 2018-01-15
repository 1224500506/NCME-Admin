package com.hys.exam.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.model.ExamICD;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropType;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.queryObj.ExamQuestionQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.PageList;
import com.hys.exam.common.util.PageUtil;
import com.hys.framework.web.action.BaseAction;

/**
 * 取得ICD目录
 * @author Han
 *
 */
public class ExamIcdListAction extends BaseAction {
	
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
		String type = request.getParameter("type") == null ? "1" : request.getParameter("type");
		String sname = request.getParameter("sname") == null ? "" : request.getParameter("sname");
		String method = RequestUtil.getParameter(request, "mode");
		
		ExamICD prop = new ExamICD();
		{
			prop.setType(Integer.valueOf(type));
			prop.setName(sname);
			Page<ExamICD> page = PageUtil.getPageByRequest(request);
			//localExamPropValFacade.getIcdListByType(page, prop);
			
			PageList pl = new PageList();
			
			String sort = request.getParameter("sort")!=null ? request.getParameter("sort") : "id";
			String dir = request.getParameter("dir")!=null ? request.getParameter("dir") : "desc";

			page.setSortCriterion(sort);
			pl.setSortCriterion(sort);
			if (dir.equals("asc"))
				page.setSortDirection(SortOrderEnum.ASCENDING);
			else
				page.setSortDirection(SortOrderEnum.DESCENDING);
			pl.setSortDirection(page.getSortDirection());
			
			localExamPropValFacade.getIcdListByType(page, prop);
			
			if (method.equals("getListByType")){
				JSONObject result = new JSONObject();
				result.put("list", page.getList());
				StrutsUtil.renderText(response, result.toString());
				return null;
			}
			
			pl.setList(page.getList());
			pl.setObjectsPerPage(page.getPageSize());
			pl.setPageNumber(page.getCurrentPage());
			pl.setFullListSize(page.getCount());

			request.setAttribute("type", type);
			request.setAttribute("propList", pl);
			request.setAttribute("sname", sname);
			request.setAttribute("totalCount", page.getCount());
			request.setAttribute("pagesize", page.getPageSize());
		}		
		return "SUCCESS";
	}

}
