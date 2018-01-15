package com.hys.exam.struts.action.exam;

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

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-3-12
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPaperListAction extends ExamBaseAction {
	private ExamPaperFacade examPaperFacade ;
	
	private ExamPaperTypeFacade examPaperTypeFacade ;

	public void setExamPaperFacade(ExamPaperFacade examPaperFacade) {
		this.examPaperFacade = examPaperFacade;
	}
	
	public void setExamPaperTypeFacade(ExamPaperTypeFacade examPaperTypeFacade) {
		this.examPaperTypeFacade = examPaperTypeFacade;
	}

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm form,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {

		OlemPaperForm paperForm = (OlemPaperForm) form;
		Long typeId = paperForm.getTypeId();

		ExamPaperQuery query = new ExamPaperQuery();

		query.setName(paperForm.getName());
		query.setState(1);
		query.setType_id(typeId);

		if (null != paperForm.getName() && !"".equals(paperForm.getName())) {
			request.setAttribute("name", paperForm.getName());
		}
		if (null != paperForm.getCreateDate()
				&& !"".equals(paperForm.getCreateDate())) {
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

		List<ExamPaper> paperList = examPaperFacade.getExamPaperList(null, query, null, null);
		page.setCount(examPaperFacade.getExamPaperListSize(query));

		for (Iterator<ExamPaper> iterator = paperList.iterator(); iterator
				.hasNext();) {
			ExamPaper type = iterator.next();
			type.setType_name(examPaperTypeFacade.getExamPaperTypeById(
					type.getType_id()).getName());
		}
		page.setList(paperList);

		request.setAttribute("page", page);
		request.setAttribute("paperTypeId", typeId);
		
		return Constants.SUCCESS ;
	}

}
