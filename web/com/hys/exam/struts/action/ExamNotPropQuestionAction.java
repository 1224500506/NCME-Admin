package com.hys.exam.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.queryObj.ExamQuestionQuery;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionLabelFacade;
import com.hys.exam.struts.form.ExamQuestionForm;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkException;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-11-4
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamNotPropQuestionAction extends BaseAction {

	private ExamQuestionLabelFacade localExamQuestionLabelFacade;
	
	private ExamQuestionFacade localExamQuestionFacade;


	public ExamQuestionLabelFacade getLocalExamQuestionLabelFacade() {
		return localExamQuestionLabelFacade;
	}


	public void setLocalExamQuestionLabelFacade(
			ExamQuestionLabelFacade localExamQuestionLabelFacade) {
		this.localExamQuestionLabelFacade = localExamQuestionLabelFacade;
	}


	public ExamQuestionFacade getLocalExamQuestionFacade() {
		return localExamQuestionFacade;
	}


	public void setLocalExamQuestionFacade(
			ExamQuestionFacade localExamQuestionFacade) {
		this.localExamQuestionFacade = localExamQuestionFacade;
	}


	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		List<ExamQuestionLabel> questLabel = localExamQuestionLabelFacade.getQuestionLabel(1);
		ExamQuestionForm qform = (ExamQuestionForm)form;
		ExamQuestionQuery query  = new ExamQuestionQuery();
		
		

		query.setState(6);
		
		int questionLabelId = request.getAttribute("questionLabelId") != null ? ((Integer) request.getAttribute("questionLabelId")) : -1;		
		if(questionLabelId>0){
			query.setQuestion_label_ids(questionLabelId+"");
			request.setAttribute("questionLabelId", questionLabelId);
		}else if (qform.getQuestion_label_id() != null &&  qform.getQuestion_label_id().intValue() > 0){
			query.setQuestion_label_ids(qform.getQuestion_label_id().toString());
			request.setAttribute("questionLabelId", qform.getQuestion_label_id());
		}
				
		
		// 试题内容
		String content = request.getAttribute("content") != null ? ((String) request.getAttribute("content")) : "";		
		if(!StringUtils.checkNull(content)){
			query.setContent(content);
			request.setAttribute("content", qform.getContent());
		} else if(qform.getContent() != null && !qform.getContent().trim().equals("")){
			query.setContent(qform.getContent());
			request.setAttribute("content", qform.getContent());
		}

		// 创建者
		String author = request.getAttribute("author") != null ? ((String) request.getAttribute("author")) : "";
		if(!StringUtils.checkNull(author)){
			query.setAuthor(author);
			request.setAttribute("author", author);
		} else if(qform.getAuthor() != null && !qform.getAuthor().trim().equals("")){
			query.setAuthor(qform.getAuthor());
			request.setAttribute("author", qform.getAuthor());
		}
		
		// 试题来源
		if(qform.getSource() != null && !qform.getSource().trim().equals("")){
			query.setSource(qform.getSource());
		}
		// 是否含有试题分析
		if(qform.getIsExistAnalyse() != null && qform.getIsExistAnalyse().intValue() != -1){
			query.setIsExistAnalyse(qform.getIsExistAnalyse());
		}
		
		//创建时间
		String createDate = request.getAttribute("createDate") != null ? ((String) request.getAttribute("createDate")) : "";
		if(!StringUtils.checkNull(createDate)){
			query.setCreate_date(createDate);
			request.setAttribute("createDate", createDate);
		} else if(!StringUtils.checkNull(qform.getCreate_date())){
			query.setCreate_date(qform.getCreate_date());
			request.setAttribute("createDate", qform.getCreate_date());
		}
		
		PageList pl = new PageList();
		
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = getPageSize(request);
		
		query.setPageNo(currentPage);
		query.setPageSize(pageSize);
		
		int count = localExamQuestionFacade.getQuestionSize(query);
		if(count <=0){
			pl.setList(null);
			pl.setFullListSize(0);
		}else{
			pl.setList(localExamQuestionFacade.getQuestionList(query));
			pl.setObjectsPerPage(pageSize);
			pl.setPageNumber(currentPage);
			pl.setFullListSize(count);
		}

		request.setAttribute("labelList", questLabel);
		request.setAttribute("questionList", pl);
		
		
		return "SUCCESS";
	}
	
	
	public static Integer getPageSize(HttpServletRequest request)
		throws FrameworkException {
		try {
			return GenericValidator.isBlankOrNull(request
					.getParameter("pagesize")) ? 500 : (Integer.parseInt(request
					.getParameter("pagesize")));
		
		} catch (NumberFormatException ne) {
			throw new FrameworkException(ErrorCode.ec01528, ne);
		}
	}

}
