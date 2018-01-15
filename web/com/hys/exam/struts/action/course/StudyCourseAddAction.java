package com.hys.exam.struts.action.course;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Utils;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.sessionfacade.local.StudyCourseFacade;
import com.hys.exam.struts.form.StudyCourseForm;
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
public class StudyCourseAddAction extends BaseAction {
	
	private StudyCourseFacade studyCourseFacade ;
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//批量修改课程状态
		String method = ParamUtils.getParameter(request, "method");
		if("batchUpdate".equals(method)){
			return this.batchUpdate(mapping, form, request, response);
		}
		
		StudyCourseForm curForm = (StudyCourseForm) form ;
		//Long curTypeId = ParamUtils.getLongParameter(request, "curTypeId", 0) ; //取得课程所在分类ID
		
		if(this.isTokenValid(request)){
			//取得课程信息
			StudyCourse course = curForm.getCourse() ;
			//course.setStudyCourseTypeId(curTypeId) ;

			if (course.getStatus() == 1) {
				course.setPubDate(new Date());
			}

			//保存课程信息
			studyCourseFacade.addStudyCourse(course) ;
			
			this.resetToken(request) ;
		}
		
		String path = request.getContextPath() ;
		//response.sendRedirect(path + "/course/studyCourseView.do?curTypeId=" + curTypeId);
		response.sendRedirect(path + "/course/studyCourseView.do");
		
		return null ;
	}
	
	public String batchUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String _courseIds = ParamUtils.getParameter(request, "courseIds");
		Integer status = ParamUtils.getIntParameter(request, "courseStatus", -2);
		int row = this.studyCourseFacade.updateBatchStatus(_courseIds, status);
		Utils.renderText(response, String.valueOf(row));
		return null;
	}

	public void setStudyCourseFacade(StudyCourseFacade studyCourseFacade) {
		this.studyCourseFacade = studyCourseFacade;
	}
}