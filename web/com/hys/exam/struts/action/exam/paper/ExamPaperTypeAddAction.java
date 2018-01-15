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
 * 描述：添加分类信息列表
 * 
 * 说明:
 */
public class ExamPaperTypeAddAction extends BaseAction {

	private ExamPaperTypeFacade examPaperTypeFacade ;

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		//父分类ID
		Long paperTypeId = ParamUtils.getLongParameter(request, "paperTypeId", 0) ;
		//分类名称
		String paperTypeName = ParamUtils.getParameter(request, "paperTypeName", null) ;
		//分类备注
		String remark = ParamUtils.getParameter(request, "remark", null) ;
		
		ExamPaperType retType = null ;
		if(paperTypeId >= 0){
			//查询父分类信息
			ExamPaperType parentType = examPaperTypeFacade.getExamPaperTypeById(paperTypeId) ;
			
			ExamPaperType paperType = new ExamPaperType() ;
			paperType.setName(paperTypeName);
			paperType.setParent_id(parentType.getId());
			paperType.setLayer(parentType.getLayer()+1);
			paperType.setState(Constants.STATUS_1);
			paperType.setSub_sys_id(parentType.getSub_sys_id());
			paperType.setRemark(remark);
			
			retType = examPaperTypeFacade.addExamPaperType(paperType) ;
		}
		
		Utils.renderText(response, retType == null ? "0" : String.valueOf(retType.getId())) ;
		
		return null;
	}

	public void setExamPaperTypeFacade(ExamPaperTypeFacade examPaperTypeFacade) {
		this.examPaperTypeFacade = examPaperTypeFacade;
	}
}