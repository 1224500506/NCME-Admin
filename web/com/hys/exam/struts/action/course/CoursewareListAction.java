package com.hys.exam.struts.action.course;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.LongUtil;
import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.PageUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.StudyCourseware;
import com.hys.exam.service.remote.SystemPropManage;
import com.hys.exam.sessionfacade.local.StudyCoursewareFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-2-25
 * 
 * 描述：
 * 
 * 说明:
 */
public class CoursewareListAction extends BaseAction {
	private StudyCoursewareFacade studyCoursewareFacade;

	private SystemPropManage systemPropManage;

	public void setSystemPropManage(SystemPropManage systemPropManage) {
		this.systemPropManage = systemPropManage;
	}

	public void setStudyCoursewareFacade(
			StudyCoursewareFacade studyCoursewareFacade) {
		this.studyCoursewareFacade = studyCoursewareFacade;
	}

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		saveToken(request);// 设置token
		
		String industryIds = ParamUtils.getParameter(request, "industryIds");
		String applicableIds = ParamUtils.getParameter(request, "applicableIds");
		String knowIds = ParamUtils.getParameter(request, "knowIds");
		
		Integer currentPage = PageUtil.getPageIndex(request);
		Page<StudyCourseware> page = new Page<StudyCourseware>();
		page.setCurrentPage(currentPage);

		StudyCourseware courseware = new StudyCourseware();

		courseware.setName(request.getParameter("queryName"));
		courseware.setKeyWord(request.getParameter("queryKeyWord"));
		courseware.setTeacherName(request.getParameter("queryTeacherName"));
		courseware.setMakeYear(request.getParameter("queryYear"));

		if (StringUtils.isNotBlank(industryIds)) {
			String [] industryArray = industryIds.split(",");
			List<ExamPropVal> industryList = new ArrayList<ExamPropVal>(industryArray.length);
			for (String string : industryArray) {
				if(StringUtils.isBlank(string)) continue;
				ExamPropVal industry = new ExamPropVal();
				industry.setId(LongUtil.parseLong(string));
				industryList.add(industry);
			}
			courseware.setIndustryList(industryList);
		}

		if (StringUtils.isNotBlank(applicableIds)) {
			String [] applicableArray = applicableIds.split(",");
			List<ExamPropVal> applicableList = new ArrayList<ExamPropVal>(applicableArray.length);
			for (String string : applicableArray) {
				if(StringUtils.isBlank(string)) continue;
				ExamPropVal e = new ExamPropVal();
				e.setId(LongUtil.parseLong(string));
				applicableList.add(e);
			}
			courseware.setApplicableList(applicableList);
		}

		if (StringUtils.isNotBlank(knowIds)) {
			String [] knowArray = knowIds.split(",");
			List<ExamPropVal> knowList = new ArrayList<ExamPropVal>(knowArray.length);
			for (String string : knowArray) {
				if(StringUtils.isBlank(string)) continue;
				ExamPropVal e = new ExamPropVal();
				e.setId(LongUtil.parseLong(string));
				knowList.add(e);
			}
			courseware.setKnowList(knowList);
		}

		studyCoursewareFacade.getStudyCoursewareList(page, courseware);

		request.setAttribute("StudyCoursewareList", page);
		
		request.setAttribute("industryIds", industryIds);
		request.setAttribute("applicableIds", applicableIds);
		request.setAttribute("knowIds", knowIds);
		
		request.setAttribute("obj_industry", request.getParameter("obj_industry"));
		request.setAttribute("obj_applicable", request.getParameter("obj_applicable"));
		request.setAttribute("obj_know", request.getParameter("obj_know"));

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

}
