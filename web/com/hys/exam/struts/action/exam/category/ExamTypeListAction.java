package com.hys.exam.struts.action.exam.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.PageUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamExaminType;
import com.hys.exam.queryObj.ExamExaminTypeQuery;
import com.hys.exam.returnObj.ExamReturnExaminType;
import com.hys.exam.sessionfacade.local.ExamExaminTypeFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试分类列表
 * 
 * 作者：陈明凯 2013-3-7
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamTypeListAction extends BaseAction {

	private ExamExaminTypeFacade examExaminTypeFacade;

	public void setExamExaminTypeFacade(ExamExaminTypeFacade examExaminTypeFacade) {
		this.examExaminTypeFacade = examExaminTypeFacade;
	}

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {

		Long curTypeId = ParamUtils.getLongParameter(request, "curTypeId", 0);

		Integer currentPage = PageUtil.getPageIndex(request);

		ExamExaminTypeQuery query = new ExamExaminTypeQuery();
		query.setPageNo(currentPage);
		query.setName(request.getParameter("queryExamTypeName"));
		query.setParent_id(curTypeId);

		ExamReturnExaminType examReturnExaminType = examExaminTypeFacade
				.getExamExaminTypeListByParentId(query);

		Page<ExamExaminType> page = new Page<ExamExaminType>();
		page.setCurrentPage(currentPage);
		page.setCount(examReturnExaminType.getTotal_count());
		page.setList(examReturnExaminType.getReturnList());

		request.setAttribute("ExamTypeList", page);

		saveToken(request);// 设置token

		return Constants.SUCCESS;
	}

}
