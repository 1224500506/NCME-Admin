package com.hys.exam.struts.action.course;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.constants.Constants;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.sessionfacade.local.StudyCourseFacade;
import com.hys.exam.struts.form.StudyCourseForm;
import com.hys.exam.utils.FilesUtils;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-26
 * 
 * 描述：添加课程信息
 * 
 * 说明:
 */
public class StudyCourseEditAction extends BaseAction {
	
	private StudyCourseFacade studyCourseFacade ;
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		StudyCourseForm curForm = (StudyCourseForm) form ;
		//Long curTypeId = ParamUtils.getLongParameter(request, "curTypeId", 0) ; //取得课程所在分类ID
		
		if(this.isTokenValid(request)){
			//取得课程信息
			StudyCourse course = curForm.getCourse() ;
			course.setCourseImgPath(course.getId()+".png");

			StudyCourse old = studyCourseFacade.getStudyCourseById(course.getId());

			if (old.getStatus() != 1 && course.getStatus() == 1) {
				// 原状态不为发布状态 设置发布时间
				course.setPubDate(new Date());
			}

			//保存课程信息
			studyCourseFacade.updateStudyCourse(course) ;
			FilesUtils.courseUpload(((StudyCourseForm)form).getFile(),request, Constants.COURSE_IMG_PATH, course.getId());

			this.resetToken(request) ;
		}
		
		String path = request.getContextPath() ;
		response.sendRedirect(path + "/course/studyCourseView.do");
		
		return null ;
	}

	public void setStudyCourseFacade(StudyCourseFacade studyCourseFacade) {
		this.studyCourseFacade = studyCourseFacade;
	}
}