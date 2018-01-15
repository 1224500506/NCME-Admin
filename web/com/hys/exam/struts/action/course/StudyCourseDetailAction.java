package com.hys.exam.struts.action.course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.sessionfacade.local.StudyCourseFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-26
 * 
 * 描述：跳转到 添加课程信息
 * 
 * 说明:
 */
public class StudyCourseDetailAction extends BaseAction {
	
	private StudyCourseFacade studyCourseFacade ;
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		this.saveToken(request) ;
		Long courseId = ParamUtils.getLongParameter(request, "courseId", 0) ;
		
		StudyCourse course = studyCourseFacade.getStudyCourseById(courseId) ;
		
		request.setAttribute("course", course) ;
		
		return "SUCCESS" ;
	}

	public void setStudyCourseFacade(StudyCourseFacade studyCourseFacade) {
		this.studyCourseFacade = studyCourseFacade;
	}
}