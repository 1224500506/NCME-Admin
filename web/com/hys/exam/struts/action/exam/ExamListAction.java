package com.hys.exam.struts.action.exam;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.Utils;

import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.queryObj.ExamExaminationQuery;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;

/**
 * 
 * 标题：考试列表
 * 
 * 作者：陈明凯 2013-3-8
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamListAction extends ExamBaseAction {

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,HttpServletRequest request, HttpServletResponse arg3) throws Exception {
		String method = request.getParameter("method");//YHQ，2017-05-17		
		if (method != null && method.equals("usingExam")) {
			return usingExamination(arg0,arg1,request,arg3) ;
		}
		
		
		String curTypeId = request.getParameter("curTypeId");
		String typeName = request.getParameter("typeNames");
		String queryExamName = request.getParameter("queryExamName");
		
			//Integer currentPage = PageUtil.getPageIndex(request);
						
			ExamExaminationQuery query = new ExamExaminationQuery();
			if (curTypeId != null && !curTypeId.equals("")) {
				query.setExam_type_id(curTypeId);
			}
			query.setName(queryExamName);
			query.setCreate_time(request.getParameter("queryCreateTime"));
			Integer examState = ParamUtils.getIntParameter(request, "examState", -1);
			if(!examState.equals(-1))
			{
				query.setState(examState);
			}
			Integer examType = ParamUtils.getIntParameter(request, "examType", -1);
			if(!examType.equals(-1))
			{
				query.setExam_type(examType);
			}
			
			PageList pl = new PageList();
			int curentPage = PageUtil.getPageIndex(request);
			int pageSize = 15;
			
			pl.setObjectsPerPage(pageSize);
			pl.setPageNumber(curentPage);
			
			examExaminationFacade.getExaminationList(pl, query);
			if(null != pl){
				Date date = new Date();
				List<ExamExamination> examList = pl.getList();
				if(!Utils.isListEmpty(examList)){
					for(ExamExamination exam : examList){
						Date endDate = DateUtil.parse(exam.getEnd_time(), DateUtil.FORMAT_SHORT);
						if(endDate.before(date)){
							exam.setState(3);
						}
					}
				}
			}
			
			
			request.setAttribute("ExamList", pl);
			request.setAttribute("curTypeId", curTypeId);
			request.setAttribute("typeNames", typeName);
			request.setAttribute("queryExamName", queryExamName);
			request.setAttribute("examType", examType);
			request.setAttribute("examState", examState);
			saveToken(request);// 设置token

		return Constants.SUCCESS;
	}
	
	/**
	 * 考试是否使用，YHQ，2017-05-17
	 * @param ExamExamination exam
	 * @return 考试id
	 */
	protected String usingExamination(ActionMapping arg0, ActionForm arg1,HttpServletRequest request, HttpServletResponse response) throws Exception {		
		String examId = request.getParameter("examId");
		ExamExamination examObj = new ExamExamination() ;
		examObj.setId(Long.parseLong(examId));
		boolean usingExam = examExaminationFacade.usingExamination(examObj) ;
		request.getSession().setAttribute(Constants.MESSAGE, Constants.MESSAGE_2);
		if (usingExam) {			
			StrutsUtil.renderText(response, "usingExam");			
		}else {
			StrutsUtil.renderText(response, "noUsingExam");
		}
		return null ;
	}
	
}
