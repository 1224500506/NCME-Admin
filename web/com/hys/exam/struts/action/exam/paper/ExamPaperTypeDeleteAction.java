package com.hys.exam.struts.action.exam.paper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.NumberUtil;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.sessionfacade.local.ExamPaperTypeFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑系统
 * 
 * 作者：张伟清 2013-03-07
 * 
 * 描述：添加分类信息列表
 * 
 * 说明:
 */
public class ExamPaperTypeDeleteAction extends BaseAction {

	private ExamPaperTypeFacade examPaperTypeFacade ;

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String mesg = "error" ;
		String primIds = ParamUtils.getParameter(request, "primIds", null) ;
		if(primIds != null && !"".equals(primIds)){
			String[] primArray = primIds.split(",") ;
			List<Long> delArray = new ArrayList<Long>() ; 
			for (String string : primArray) {
				Long primaryId = NumberUtil.parseLong(string, 0) ;
				if(primaryId == 0){
					continue ;
				}
				delArray.add(primaryId) ;
			}
			
			examPaperTypeFacade.deleteExamPaperType(delArray) ;
			
			mesg = String.valueOf(Constants.STATUS_1) ;
		}
		
		Utils.renderText(response, mesg) ;
		
		return null;
	}

	public void setExamPaperTypeFacade(ExamPaperTypeFacade examPaperTypeFacade) {
		this.examPaperTypeFacade = examPaperTypeFacade;
	}
}