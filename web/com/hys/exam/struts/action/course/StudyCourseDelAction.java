package com.hys.exam.struts.action.course;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.NumberUtil;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.sessionfacade.local.StudyCourseFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-26
 * 
 * 描述：删除课程信息
 * 
 * 说明:
 */
public class StudyCourseDelAction extends BaseAction {
	
	private StudyCourseFacade studyCourseFacade;

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//Long curTypeId = ParamUtils.getLongParameter(request, "curTypeId", 0) ;
		String method = ParamUtils.getParameter(request, "method", null) ;
		if(method == null){
			String[] array = request.getParameterValues("curIds");
			if (array != null && array.length > 0) {
				List<StudyCourse> curList = new ArrayList<StudyCourse>();
				
				for (int i = 0; i < array.length; ++i) {
					StudyCourse cur = new StudyCourse();
					cur.setId(NumberUtil.parseLong(array[i]));
					curList.add(cur);
				}
				studyCourseFacade.deleteStudyCourse(curList) ;
			}
		}else{
			Long curId = ParamUtils.getLongParameter(request, "courseId", 0) ;
			studyCourseFacade.deleteStudyCourse(curId) ;
		}

		String path = request.getContextPath() ;
		//response.sendRedirect(path + "/course/studyCourseView.do?curTypeId=" + curTypeId);
		response.sendRedirect(path + "/course/studyCourseView.do");
		
		return null;
	}

	public void setStudyCourseFacade(StudyCourseFacade studyCourseFacade) {
		this.studyCourseFacade = studyCourseFacade;
	}
}
