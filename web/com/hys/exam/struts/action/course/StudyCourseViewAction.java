package com.hys.exam.struts.action.course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.PageUtil;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.sessionfacade.local.StudyCourseFacade;
import com.hys.exam.struts.form.StudyCourseForm;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：查询课程列表信息
 * 
 * 说明:
 */
public class StudyCourseViewAction extends BaseAction {
	
//	private SystemSiteFacade systemSiteFacade ;
	
	private StudyCourseFacade studyCourseFacade ;

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		//==============================
		//通过分类id查看被授权的课程
		String method = ParamUtils.getParameter(request, "method");
		if(StringUtils.isNotBlank(method) && method.equals("viewCreditCourse")){
			return this.viewCreditCourse(mapping, form, request, response);
		}
		//==============================
		
		
		StudyCourseForm curForm = (StudyCourseForm) form ;
		//取得页面查询参数信息
		StudyCourse course = curForm.getCourse() ;
		//课程分类ID
		//Long curTypeId = ParamUtils.getLongParameter(request, "curTypeId", 0) ; 
		//course.setStudyCourseTypeId(curTypeId) ;
		
		String CourseTypes = ParamUtils.getParameter(request, "CourseTypes");
		String CourseTypeIds = ParamUtils.getParameter(request, "CourseTypeIds");
		
		//取得站点信息
//		List<SystemSite> siteList = systemSiteFacade.getSystemSiteList() ;

		//查询课程信息
		Page<StudyCourse> page = PageUtil.getPageByRequest(request);
		
		//查询课程列表信息
		Integer status = ParamUtils.getIntParameter(request, "status", -1);
		course.setStatus(status);
		studyCourseFacade.getStudyCourseList(page, course, CourseTypeIds) ;
		
//		request.setAttribute("siteList", siteList) ;

		request.setAttribute("page", page) ;
		request.setAttribute("CourseTypes", CourseTypes);
		request.setAttribute("CourseTypeIds", CourseTypeIds);

		return "SUCCESS";
	}

//	public void setSystemSiteFacade(SystemSiteFacade systemSiteFacade) {
//		this.systemSiteFacade = systemSiteFacade;
//	}
	
	public String viewCreditCourse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long typeId = ParamUtils.getLongParameter(request, "typeId", -100L);
		if(typeId>-100){
			StudyCourseForm curForm = (StudyCourseForm)form ;
			StudyCourse course = curForm.getCourse();
			course.setStudyCourseTypeId(typeId);
			String CourseTypes = ParamUtils.getParameter(request, "CourseTypes");
			String CourseTypeIds = ParamUtils.getParameter(request, "CourseTypeIds");
			Page<StudyCourse> page = PageUtil.getPageByRequest(request);
			
			//查询课程列表信息
			studyCourseFacade.getStudyCreditCourseList(page, course, CourseTypeIds);
			
			request.setAttribute("page", page) ;
			request.setAttribute("CourseTypes", CourseTypes);
			request.setAttribute("CourseTypeIds", CourseTypeIds);
			request.setAttribute("typeId", typeId);
		}
		return "viewCreditCourse";
	}

	public void setStudyCourseFacade(StudyCourseFacade studyCourseFacade) {
		this.studyCourseFacade = studyCourseFacade;
	}
}