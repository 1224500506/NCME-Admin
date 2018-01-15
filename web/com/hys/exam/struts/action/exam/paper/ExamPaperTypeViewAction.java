package com.hys.exam.struts.action.exam.paper;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.PageUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPaperType;
import com.hys.exam.queryObj.ExamPaperTypeQuery;
import com.hys.exam.returnObj.ExamReturnPaperType;
import com.hys.exam.sessionfacade.local.ExamPaperTypeFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑系统
 * 
 * 作者：张伟清 2013-03-07
 * 
 * 描述：查询分类信息列表
 * 
 * 说明:
 */
public class ExamPaperTypeViewAction extends BaseAction {

	private ExamPaperTypeFacade examPaperTypeFacade ;

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 设置token
		saveToken(request);
		//试卷分类名称
		String paperTypeName = ParamUtils.getParameter(request, "paperTypeName", null) ;
		if(paperTypeName != null){
			paperTypeName = URLDecoder.decode(paperTypeName, "utf-8");
		}
		//试卷分类ID
		Long paperTypeId = ParamUtils.getLongParameter(request, "paperTypeId", 0);
		//取得当前页
		Integer currentPage = PageUtil.getPageIndex(request);

		ExamPaperTypeQuery query = new ExamPaperTypeQuery();
		query.setPageNo(currentPage);
		query.setName(paperTypeName);
		query.setParent_id(paperTypeId);

		ExamReturnPaperType paperType = examPaperTypeFacade.getExamPaperTypeListByParentId(query);

		Page<ExamPaperType> page = new Page<ExamPaperType>() ;
		page.setCurrentPage(query.getPageNo()) ;
		page.setList(paperType.getReturnList()) ;
		page.setCount(paperType.getTotal_count()) ;
		
		request.setAttribute("paperTypeName", paperTypeName);
		request.setAttribute("page", page);

		return Constants.SUCCESS;
	}

	public void setExamPaperTypeFacade(ExamPaperTypeFacade examPaperTypeFacade) {
		this.examPaperTypeFacade = examPaperTypeFacade;
	}
}