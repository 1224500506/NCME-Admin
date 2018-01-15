package com.hys.exam.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.PageUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.queryObj.ExamExaminationQuery;
import com.hys.exam.struts.action.exam.ExamListAction;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-3-20
 * 
 * 描述：
 * 
 * 说明:
 */
public class SiteExamListAction extends ExamListAction {

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {

		Long curTypeId = ParamUtils.getLongParameter(request, "curTypeId", 0);

		if (curTypeId > 0) {
			Integer currentPage = PageUtil.getPageIndex(request);

			ExamExaminationQuery query = new ExamExaminationQuery();
		//	query.setExam_type_id(curTypeId);
			query.setName(request.getParameter("queryExamName"));
			query.setCreate_time(request.getParameter("queryCreateTime"));
			query.setState(Constants.STATUS_1);

			List<ExamExamination> examList = examExaminationFacade.getExaminationList(query);

			int size = examExaminationFacade.getExaminationListSize(query);

			Page<ExamExamination> page = new Page<ExamExamination>();
			page.setCurrentPage(currentPage);
			page.setCount(size);
			page.setList(examList);

			request.setAttribute("ExamList", page);
		}

		saveToken(request);// 设置token

		return Constants.SUCCESS;
	}
}
