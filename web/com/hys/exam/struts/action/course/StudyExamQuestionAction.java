package com.hys.exam.struts.action.course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.PageUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.queryObj.ExamPaperQuery;
import com.hys.exam.sessionfacade.local.ExamPaperFacade;
import com.hys.exam.sessionfacade.local.ExamPaperTypeFacade;
import com.hys.exam.struts.form.OlemPaperForm;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-03-14
 * 
 * 描述：根据分类查询试卷信息列表
 * 
 * 说明:
 */
public class StudyExamQuestionAction extends BaseAction {

	private ExamPaperFacade examPaperFacade ;
	
	private ExamPaperTypeFacade examPaperTypeFacade ;

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		OlemPaperForm paperForm = (OlemPaperForm) form;
		Long typeId = paperForm.getTypeId();

		ExamPaperQuery query = new ExamPaperQuery();

		query.setName(paperForm.getName());
		query.setState(1);
		query.setType_id(typeId);

		if (null != paperForm.getName() && !"".equals(paperForm.getName())) {
			request.setAttribute("name", paperForm.getName());
		}
		
		if (null != paperForm.getCreateDate() && !"".equals(paperForm.getCreateDate())) {
			query.setCreate_date(paperForm.getCreateDate());
			request.setAttribute("createDate", paperForm.getCreateDate());
		}
		
		if (null != paperForm.getPaperMode() && -1 != paperForm.getPaperMode()) {
			query.setPaper_mode(paperForm.getPaperMode());
			request.setAttribute("paperMode", paperForm.getPaperMode());
		}

		Page<ExamPaper> page = PageUtil.getPageByRequest(request);
		query.setPageNo(page.getCurrentPage());
		query.setPageSize(page.getPageSize());

		List<ExamPaper> paperList = new ArrayList<ExamPaper>();
		page.setCount(examPaperFacade.getExamPaperListSize(query));

		for (Iterator<ExamPaper> iterator = paperList.iterator(); iterator.hasNext();) {
			ExamPaper type = iterator.next();
			type.setType_name(examPaperTypeFacade.getExamPaperTypeById(type.getType_id()).getName());
		}
		
		page.setList(paperList);

		request.setAttribute("page", page);
		request.setAttribute("paperTypeId", typeId);
		
		return Constants.SUCCESS ;
	}
	
	public void setExamPaperFacade(ExamPaperFacade examPaperFacade) {
		this.examPaperFacade = examPaperFacade;
	}
	
	public void setExamPaperTypeFacade(ExamPaperTypeFacade examPaperTypeFacade) {
		this.examPaperTypeFacade = examPaperTypeFacade;
	}
}
