package com.hys.exam.struts.action.course;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.NumberUtil;
import com.hys.exam.model.StudyCourseElement;
import com.hys.exam.sessionfacade.local.StudyCourseElementFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-26
 * 
 * 描述：删除课程组件信息
 * 
 * 说明:
 */
public class StudyCurWareDelAction extends BaseAction {
	
	private StudyCourseElementFacade studyCourseElementFacade ;

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//课程ID
		Long courseId = ParamUtils.getLongParameter(request, "courseId", 0) ;
		//课程分类ID
		Long curTypeId = ParamUtils.getLongParameter(request, "curTypeId", 0) ;

		String moveFlag = ParamUtils.getParameter(request, "moveFlag", null) ;
		if(moveFlag == null){
			String[] array = request.getParameterValues("wareIds");
			if (array != null && array.length > 0) {
				List<StudyCourseElement> eleList = new ArrayList<StudyCourseElement>();
				
				for (int i = 0; i < array.length; ++i) {
					StudyCourseElement ele = new StudyCourseElement();
					ele.setId(NumberUtil.parseLong(array[i]));
					eleList.add(ele);
				}
				studyCourseElementFacade.deleteStudyCourseElement(eleList) ;
			}
		}else{
			Long elementId = ParamUtils.getLongParameter(request, "elementId", 0) ;
			studyCourseElementFacade.deleteStudyCourseElement(elementId) ;
		}

		String path = request.getContextPath() ;
		response.sendRedirect(path + "/course/studyCurWareView.do?curTypeId="+curTypeId+"&courseId="+courseId);
		
		return null;
	}

	public void setStudyCourseElementFacade(StudyCourseElementFacade studyCourseElementFacade) {
		this.studyCourseElementFacade = studyCourseElementFacade;
	}
}
