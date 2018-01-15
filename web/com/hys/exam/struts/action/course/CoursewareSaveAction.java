package com.hys.exam.struts.action.course;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.model.HysUsers;
import com.hys.auth.util.LongUtil;
import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.StudyCourseware;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemIndustry;
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
public class CoursewareSaveAction extends BaseAction {
	private StudyCoursewareFacade studyCoursewareFacade;

	public void setStudyCoursewareFacade(
			StudyCoursewareFacade studyCoursewareFacade) {
		this.studyCoursewareFacade = studyCoursewareFacade;
	}

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if(isTokenValid(request,true)){
			StudyCourseware courseware = new StudyCourseware();
			courseware.setId(ParamUtils.getLongParameter(request, "id", 0L));
			//courseware.setCode(request.getParameter("code"));
			courseware.setName(request.getParameter("name"));
			courseware.setType(ParamUtils.getIntParameter(request, "type", 0));
			courseware.setTimes(ParamUtils.getIntParameter(request, "times", 0));
			courseware.setClassHours(ParamUtils.getDoubleParameter(request, "classHours", 0.0));
			courseware.setSummary(request.getParameter("summary"));
			courseware.setKeyWord(request.getParameter("keyWord"));
			courseware.setTeacherName(request.getParameter("teacherName"));
			courseware.setTeacherTitle(request.getParameter("teacherTitle"));
			courseware.setTeacherUnit(request.getParameter("teacherUnit"));
			courseware.setMakeYear(request.getParameter("makeYear"));
			courseware.setRemark(request.getParameter("remark"));
			courseware.setMeaning(request.getParameter("meaning"));
			courseware.setGains(request.getParameter("gains"));
			courseware.setAttentions(request.getParameter("attentions"));
			courseware.setPath(request.getParameter("path"));
			courseware.setStatus(Constants.STATUS_1);
			courseware.setFilePath(request.getParameter("filePath"));
			courseware.setPublishPath(request.getParameter("publishPath"));
			courseware.setRelativeAddress(request.getParameter("relativeAddress"));

			SystemUser user = LogicUtil.getSystemUser(request);

			courseware.setCreateUserId(user.getUserId());
			courseware.setLastUpdateUserId(user.getUserId());

			String[] industry = request.getParameterValues("industryId");

			if (industry != null) {
				List<ExamPropVal> industryList = new ArrayList<ExamPropVal>();
				for (int i = 0; i < industry.length; i++) {
					ExamPropVal s = new ExamPropVal();
					s.setId(LongUtil.parseLong(industry[i]));

					industryList.add(s);
				}
				courseware.setIndustryList(industryList);
			}

			String[] applicable = request.getParameterValues("applicableId");

			if (applicable != null) {
				List<ExamPropVal> applicableList = new ArrayList<ExamPropVal>();
				for (int i = 0; i < applicable.length; i++) {
					ExamPropVal s = new ExamPropVal();
					s.setId(LongUtil.parseLong(applicable[i]));

					applicableList.add(s);
				}
				courseware.setApplicableList(applicableList);
			}

			String[] know = request.getParameterValues("knowId");

			if (know != null) {
				List<ExamPropVal> knowList = new ArrayList<ExamPropVal>();
				for (int i = 0; i < know.length; i++) {
					ExamPropVal s = new ExamPropVal();
					s.setId(LongUtil.parseLong(know[i]));

					knowList.add(s);
				}
				courseware.setKnowList(knowList);
			}

			studyCoursewareFacade.saveStudyCourseware(courseware);

			request.getSession().setAttribute(Constants.MESSAGE, Constants.MESSAGE_1);
		}else{
			saveToken(request);// 设置token
		}

		return Constants.SUCCESS;
	}
}
