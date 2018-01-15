package com.hys.exam.struts.action.exam.paper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPaperType;
import com.hys.exam.sessionfacade.local.ExamPaperTypeFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑系统
 * 
 * 作者：张伟清 2013-03-07
 * 
 * 描述：修改分类信息列表
 * 
 * 说明:
 */
public class ExamPaperTypeEditAction extends BaseAction {

	private ExamPaperTypeFacade examPaperTypeFacade ;

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		//分类ID
		Long primaryId = ParamUtils.getLongParameter(request, "primaryId", 0) ;
		//分类名称
		String paperTypeName = ParamUtils.getParameter(request, "paperTypeName", null) ;
		//分类备注
		String remark = ParamUtils.getParameter(request, "remark", null) ;
		
		if(primaryId > 0){
			ExamPaperType paperType = new ExamPaperType() ;
			paperType.setName(paperTypeName);
			paperType.setRemark(remark);
			paperType.setId(primaryId) ;
			
			examPaperTypeFacade.updateExamPaperType(paperType) ;
		}
		
		Utils.renderText(response, String.valueOf(Constants.STATUS_1)) ;
		
		return null;
	}

	public void setExamPaperTypeFacade(ExamPaperTypeFacade examPaperTypeFacade) {
		this.examPaperTypeFacade = examPaperTypeFacade;
	}
}