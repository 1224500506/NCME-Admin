package com.hys.exam.struts.action.exam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.common.util.DateUtils;
import com.hys.exam.common.util.NumberUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamExaminOrg;
import com.hys.exam.model.ExamExaminPaper;
import com.hys.exam.model.ExamExaminUser;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.struts.form.ExamExaminationForm;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-3-11
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamSaveAction extends ExamBaseAction {

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3)
			throws Exception {
		ExamExaminationForm eform = (ExamExaminationForm)arg1;
		ExamExamination e = eform.getExamExamination();
		if (e.getId() != null && e.getId().equals(0L)) {
			//新增
			Date createTime = new Date();
			e.setCreate_time(createTime.toString());

			processPaper(request, e, 0L);
			processUser(e,eform.getStuNamesId(),2);
			processUser(e,eform.getUserNamesId(),1);
			processOrg(e,eform.getOrgNamesId());
			examExaminationFacade.addExamination(e);

		} else {
			
			processPaper(request, e, 0L);
			processUser(e,eform.getStuNamesId(),2);
			processUser(e,eform.getUserNamesId(),1);
			processOrg(e,eform.getOrgNamesId());
			examExaminationFacade.updateExaminationById(e);
		}

		return Constants.SUCCESS;
	}

	/**
	 * @param request
	 * @param curTypeId
	 * @return
	 */
	private ExamExamination getExam(HttpServletRequest request, Long curTypeId) {
		ExamExamination e = new ExamExamination();
		e.setName(request.getParameter("name"));
		e.setExam_type_id(curTypeId);
		e.setExam_type(ParamUtils.getIntParameter(request, "examType", 0));
		e.setExam_num(ParamUtils.getIntParameter(request, "examNum", 0));
		e.setExam_times(ParamUtils.getIntParameter(request, "examTimes", 0) * Constants.MILLISECOND);
		e.setExam_time(ParamUtils.getIntParameter(request, "examTime", 0));
		e.setStart_time(request.getParameter("startTime"));
		e.setEnd_time(request.getParameter("endTime"));
		e.setPass_condition(ParamUtils.getIntParameter(request, "passCondition", 0));
		e.setPass_value(ParamUtils.getDoubleParameter(request, "passValue", 0.0));
		e.setIsnot_see_test_report(ParamUtils.getIntParameter(request, "isnotSeeTestReport", 0));
		e.setState(ParamUtils.getIntParameter(request, "state", 0));
		e.setRemark(request.getParameter("remark"));
		e.setSiteId(ParamUtils.getLongParameter(request, "siteId", 0));
		e.setPaper_display_mode(ParamUtils.getIntParameter(request, "paperDisplayMode", 0));
		
		//切屏
		e.setIs_cut_screen(ParamUtils.getIntParameter(request, "is_cut_screen", 0));
		e.setCut_screen_num(ParamUtils.getIntParameter(request, "cut_screen_num", 0));
		
		return e;
	}

	/**
	 * @param request
	 * @param e
	 */
	private void processPaper(HttpServletRequest request, ExamExamination e, Long examId) {
		String paperIds = request.getParameter("paperNamesId");

		if (paperIds != null) {
			String[] array = paperIds.split(",");
			List<ExamExaminPaper> examinPaperList = new ArrayList<ExamExaminPaper>();

			for (int i = 0; i < array.length; ++i) {
				ExamExaminPaper paper = new ExamExaminPaper();
				paper.setPaper_id(NumberUtil.parseLong(array[i], 0L));
				if (examId > 0L) {
					paper.setExam_id(examId);
				}

				examinPaperList.add(paper);
			}
			e.setExaminPaperList(examinPaperList);
		}
	}
	/**
	 * @param request
	 * @param e
	 */
	private void processUser(ExamExamination e,String userIds, Integer userType) {
		if (userIds != null && !userIds.equals("")) {
			String[] array = userIds.split(",");
			List<ExamExaminUser> examinUserList = new ArrayList<ExamExaminUser>();

			for (int i = 0; i < array.length; ++i) {
				ExamExaminUser user = new ExamExaminUser();
				user.setSystem_user_id(NumberUtil.parseLong(array[i], 0L));
				if (e.getId() != null && e.getId() > 0L) {
					user.setExam_id(e.getId());
				}
				user.setSystem_user_type(userType);
				examinUserList.add(user);
			}
			if(userType.equals(1))
			{
				e.setExaminUserList(examinUserList);
			}
			else
			{
				e.setExaminConsierList(examinUserList);
			}
		}
	}
	

	private void processOrg(ExamExamination e,String orgIds) {
		if (orgIds != null && !orgIds.equals("")) {
			String[] array = orgIds.split(",");
			List<ExamExaminOrg> examinOrgList = new ArrayList<ExamExaminOrg>();

			for (int i = 0; i < array.length; ++i) {
				ExamExaminOrg org = new ExamExaminOrg();
				org.setOrg_id(NumberUtil.parseLong(array[i], 0L));
				if (e.getId() != null && e.getId() > 0L){
					org.setExam_id(e.getId());
				}
				examinOrgList.add(org);
			}
			e.setExaminOrgList(examinOrgList);
		}
	}
}
