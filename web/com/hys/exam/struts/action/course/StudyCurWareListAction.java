package com.hys.exam.struts.action.course;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.LongUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.PageUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.StudyCourseware;
import com.hys.exam.model.system.SystemIndustry;
import com.hys.exam.service.remote.SystemPropManage;
import com.hys.exam.sessionfacade.local.StudyCoursewareFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-26
 * 
 * 描述：课件信息
 * 
 * 说明:
 */
public class StudyCurWareListAction extends BaseAction {

	private StudyCoursewareFacade studyCoursewareFacade;

	private SystemPropManage systemPropManage;

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String[] industryArray = request.getParameterValues("industryId");
		String[] applicableArray = request.getParameterValues("applicableId");
		String[] knowArray = request.getParameterValues("knowId");

		request.setAttribute("industryArray", industryArray);
		request.setAttribute("applicableArray", applicableArray);
		request.setAttribute("knowArray", knowArray);

		this.saveToken(request) ;
		Integer currentPage = PageUtil.getPageIndex(request);
		Page<StudyCourseware> page = new Page<StudyCourseware>();
		page.setCurrentPage(currentPage);

		StudyCourseware courseware = new StudyCourseware();
		courseware.setName(request.getParameter("queryName"));
		courseware.setKeyWord(request.getParameter("queryKeyWord"));
		courseware.setTeacherName(request.getParameter("queryTeacherName"));
		courseware.setMakeYear(request.getParameter("queryYear"));

		if (industryArray != null) {
			List<ExamPropVal> industryList = new ArrayList<ExamPropVal>(industryArray.length);
			for (String string : industryArray) {
				ExamPropVal industry = new ExamPropVal();
				industry.setId(LongUtil.parseLong(string));
				industryList.add(industry);
			}
			courseware.setIndustryList(industryList);
		}

		if (applicableArray != null) {
			List<ExamPropVal> applicableList = new ArrayList<ExamPropVal>(applicableArray.length);
			for (String string : applicableArray) {
				ExamPropVal e = new ExamPropVal();
				e.setId(LongUtil.parseLong(string));
				applicableList.add(e);
			}
			courseware.setApplicableList(applicableList);
		}

		if (knowArray != null) {
			List<ExamPropVal> knowList = new ArrayList<ExamPropVal>(knowArray.length);
			for (String string : knowArray) {
				ExamPropVal e = new ExamPropVal();
				e.setId(LongUtil.parseLong(string));
				knowList.add(e);
			}
			courseware.setKnowList(knowList);
		}

		studyCoursewareFacade.getStudyCoursewareList(page, courseware);

		request.setAttribute("StudyCoursewareList", page);

		//行业
		//List<SystemIndustry> industryList = systemPropManage.getSystemIndustryList(0L);
		List<ExamPropVal> industryList = systemPropManage.getExamPropValList(Constants.SYSTEM_PROP_TYPE_INDUSTRY);
		request.setAttribute("industryList", industryList);
		// 适用对象
		List<ExamPropVal> applicableList = systemPropManage.getExamPropValList(Constants.SYSTEM_PROP_TYPE_APPLICABLE);
		request.setAttribute("applicableList", applicableList);
		// 知识分类
		List<ExamPropVal> knowList = systemPropManage.getExamPropValList(Constants.SYSTEM_PROP_TYPE_KNOW);
		request.setAttribute("knowList", knowList);

		return Constants.SUCCESS;
	}

	public void setSystemPropManage(SystemPropManage systemPropManage) {
		this.systemPropManage = systemPropManage;
	}

	public void setStudyCoursewareFacade(
			StudyCoursewareFacade studyCoursewareFacade) {
		this.studyCoursewareFacade = studyCoursewareFacade;
	}
}
