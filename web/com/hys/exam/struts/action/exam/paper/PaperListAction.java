package com.hys.exam.struts.action.exam.paper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;

import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.queryObj.ExamPaperQuery;
import com.hys.exam.sessionfacade.local.ExamPaperFacade;
import com.hys.exam.sessionfacade.local.ExamPaperTypeFacade;
import com.hys.exam.struts.form.OlemPaperForm;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;

import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑系统
 * 
 * 作者：张伟清 2013-03-07
 * 
 * 描述：查询试卷列表
 * 
 * 说明:
 */
public class PaperListAction extends BaseAction {

	private ExamPaperFacade examPaperFacade;
	
	private ExamPaperTypeFacade examPaperTypeFacade ;

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method=request.getParameter("method");
		if(method != null && method.equals("update")){
		     return updateContral(mapping,form,request,response);	 
		}
		//YHQ，2017-05-17
		if(method != null && method.equals("usingExam")){
		     return usingPaper(mapping,form,request,response);	 
		}
		
		OlemPaperForm paperForm = (OlemPaperForm) form;
		
		String typeIds = paperForm.getType_Ids() == null ? "":paperForm.getType_Ids();
		String typeNames = paperForm.getTypeName() == null ? "":paperForm.getTypeName();
		
		String createDateFrom = request.getParameter("createDateFrom") == null ? "" : request.getParameter("createDateFrom"); 
		String createDateTo = request.getParameter("createDateTo") == null ? "" : request.getParameter("createDateTo"); 
		String searchTypeIds = request.getParameter("type_Ids") == null ? "": request.getParameter("type_Ids"); 
		
		
		ExamPaperQuery query = new ExamPaperQuery();
		query.setType_ids(searchTypeIds);
		query.setName(paperForm.getName());
		query.setState(1);
		
		query.setPaper_mode(paperForm.getPaperMode());
		
		if(null != paperForm.getName() && !"".equals(paperForm.getName())){
			request.setAttribute("name", paperForm.getName());
		}		
		if(null != paperForm.getCreateDate() && !"".equals(paperForm.getCreateDate())){
			query.setCreate_date(paperForm.getCreateDate());
			request.setAttribute("createDate", paperForm.getCreateDate());
		}
		
		if(null != paperForm.getPaperMode() && -1 != paperForm.getPaperMode()){
			query.setPaper_mode(paperForm.getPaperMode());
			request.setAttribute("paperMode", paperForm.getPaperMode());
		}
		
		PageList pl = new PageList();
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = 20;
		pl.setObjectsPerPage(pageSize);
		pl.setPageNumber(currentPage);
		
		List<ExamPaper> paperList = examPaperFacade.getExamPaperList(pl, query, createDateFrom, createDateTo);
		pl.setList(paperList);
		request.setAttribute("List", pl);
		request.setAttribute("type_Ids", typeIds);
		request.setAttribute("typeNames", typeNames);
		//request.setAttribute("paperTypeId", typeId);
		request.setAttribute("createDateFrom", createDateFrom);
		request.setAttribute("createDateTo", createDateTo);
		request.setAttribute("paperMode", query.getPaper_mode());
		
		return Constants.SUCCESS ;
	}
	
	/**
	 * 试卷是否使用，YHQ，2017-05-17
	 * @param ExamPaper exam
	 * @return 试卷id
	 */
	protected String usingPaper(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String paperId = request.getParameter("paperId");
		
		ExamPaper examPaperObj = new ExamPaper() ;
		examPaperObj.setId(Long.parseLong(paperId.trim()));
		boolean usingPaper = examPaperFacade.usingExamPaper(examPaperObj) ;
		if (usingPaper) {			
			StrutsUtil.renderText(response, "usingPaper");			
		}else {
			StrutsUtil.renderText(response, "noUsingPaper");
		}
		return null ;
	}
		
	private String updateContral(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		 Long paperId1 = ParamUtils.getLongParameter(request, "paperId1",0);
		 Long typeId1=ParamUtils.getLongParameter(request, "typeId1",0);
		 ExamPaperQuery paper = new ExamPaperQuery();
		 paper.setId(paperId1);
		 paper.setType_id(typeId1);
	    if(typeId1>0 && typeId1!=null)
		if(examPaperFacade.updateContral(paper)){
			request.setAttribute("typeId1", typeId1);
			request.setAttribute("typeName1",examPaperTypeFacade.getExamPaperTypeById(typeId1).getName());
			 
			StrutsUtil.renderText(response,"success");
		}
		return null;
	}


	public void setExamPaperFacade(ExamPaperFacade examPaperFacade) {
		this.examPaperFacade = examPaperFacade;
	}
	
	public void setExamPaperTypeFacade(ExamPaperTypeFacade examPaperTypeFacade) {
		this.examPaperTypeFacade = examPaperTypeFacade;
	}
}