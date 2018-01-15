package com.hys.exam.struts.action.course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.model.HysUsers;
import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.StudyCourseType;
import com.hys.exam.model.SystemUser;
import com.hys.exam.sessionfacade.local.StudyCourseTypeFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-2-27
 * 
 * 描述：
 * 
 * 说明:
 */
public class StudyCourseTypeSaveAction extends BaseAction {

	private StudyCourseTypeFacade studyCourseTypeFacade;

	public void setStudyCourseTypeFacade(
			StudyCourseTypeFacade studyCourseTypeFacade) {
		this.studyCourseTypeFacade = studyCourseTypeFacade;
	}

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {

		if (isTokenValid(request, true)) {
			StudyCourseType s = new StudyCourseType();
			Long id = ParamUtils.getLongParameter(request, "id", 0L);
			s.setId(id);
			s.setCourseTypeName(request.getParameter("courseTypeName"));
			s.setParentCourseTypeId(ParamUtils.getLongParameter(request, "curTypeId", -1L));
			s.setSeq(ParamUtils.getIntParameter(request, "seq", 0));
			s.setAllClassHours(ParamUtils.getIntParameter(request, "allClassHours", 0));
			s.setType(ParamUtils.getIntParameter(request, "type", 0));
			s.setExamineRequire(request.getParameter("examineRequire"));
			s.setTrainPrinciples(request.getParameter("trainPrinciples"));
			s.setIntroduction(request.getParameter("introduction"));

			s.setSubjectId(request.getParameter("subjectId"));
			s.setSubjectName(request.getParameter("subjectName"));
			s.setSubject2Id(request.getParameter("subject2Id"));
			s.setSubject2Name(request.getParameter("subject2Name"));
			s.setGuide(request.getParameter("guide"));
			s.setExpId(request.getParameter("expId"));
			s.setKeyGuide(request.getParameter("keyGuide"));
			s.setPracGuide(request.getParameter("pracGuide"));

			SystemUser user = LogicUtil.getSystemUser(request);

			s.setCreateUserId(user.getUserId());
			s.setLastUpdateUserId(user.getUserId());

			studyCourseTypeFacade.saveStudyCourseType(s);

			request.getSession().setAttribute(Constants.MESSAGE, Constants.MESSAGE_1);

			if (id == null || id == 0L) {
				request.setAttribute(Constants.IS_RELOAD_ADD, Constants.IS_RELOAD_ADD);
			}

		} else {
			saveToken(request);// 设置token
		}
		return Constants.SUCCESS;
	}

}
