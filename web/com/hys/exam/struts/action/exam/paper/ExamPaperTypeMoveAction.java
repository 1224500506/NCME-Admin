package com.hys.exam.struts.action.exam.paper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Utils;
import com.hys.exam.model.ExamPaperType;
import com.hys.exam.sessionfacade.local.ExamPaperTypeFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑系统
 * 
 * 作者：张伟清 2013-03-07
 * 
 * 描述：移动分类信息列表
 * 
 * 说明:
 */
public class ExamPaperTypeMoveAction extends BaseAction {

	private ExamPaperTypeFacade examPaperTypeFacade ;

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Long parentId = ParamUtils.getLongParameter(request, "parentId", 0) ;
		String oldCode = ParamUtils.getParameter(request, "oldCode", null) ;
		
		boolean flag = true;
		if(parentId > 0) {
			ExamPaperType paperType =new ExamPaperType();
			paperType.setParent_id(parentId);
			paperType.setCode(oldCode);
			
			flag = examPaperTypeFacade.updateMovePaperType(paperType);
		}
		
		if(flag) {
			Utils.renderText(response, "ajax.ok");
		}else {
			Utils.renderText(response, "ajax.error");
		}
		
		return null;
	}

	public void setExamPaperTypeFacade(ExamPaperTypeFacade examPaperTypeFacade) {
		this.examPaperTypeFacade = examPaperTypeFacade;
	}
}