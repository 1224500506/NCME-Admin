package com.hys.exam.struts.action.course;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Utils;
import com.hys.exam.model.StudyCourseType;
import com.hys.exam.sessionfacade.local.StudyCourseTypeFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-26
 * 
 * 描述：课程分类 是否免费
 * 
 * 说明:
 */
public class StudyCourseTypeIsFreeAction extends BaseAction {

	private StudyCourseTypeFacade studyCourseTypeFacade;

	public void setStudyCourseTypeFacade(StudyCourseTypeFacade studyCourseTypeFacade) {
		this.studyCourseTypeFacade = studyCourseTypeFacade;
	}
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String msg = "" ;
		Long primaryId = ParamUtils.getLongParameter(request, "primaryId", 0) ;
		boolean flag = ParamUtils.getBooleanParameter(request, "checkFlag") ;
		
		StudyCourseType curType = studyCourseTypeFacade.getStudyCourseTypeById(primaryId) ;
		if(curType != null){
			List<StudyCourseType> typeList = studyCourseTypeFacade.getAllChildCurTypeByTypeId(primaryId) ;
			typeList.add(curType) ;
			
			for (StudyCourseType type : typeList) {
				type.setIsFree(flag ? 1 : 0) ;
			}
			
			studyCourseTypeFacade.updateCurTypeBatch(typeList) ;
			msg = "ajax.ok" ;
		}
		
		Utils.renderText(response, msg) ;
		
		return null;
	}
}
