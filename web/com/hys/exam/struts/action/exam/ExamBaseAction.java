package com.hys.exam.struts.action.exam;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamExaminType;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.model.SystemSite;
import com.hys.exam.service.local.SystemSiteManage;
import com.hys.exam.sessionfacade.local.ExamExaminTypeFacade;
import com.hys.exam.sessionfacade.local.ExamExaminationFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-3-8
 * 
 * 描述：
 * 
 * 说明:
 */
public abstract class ExamBaseAction extends BaseAction {

	protected ExamExaminationFacade examExaminationFacade;

	protected ExamExaminTypeFacade examExaminTypeFacade;
	
	protected SystemSiteManage systemSiteManage;

	public void setExamExaminationFacade(
			ExamExaminationFacade examExaminationFacade) {
		this.examExaminationFacade = examExaminationFacade;
	}

	public void setExamExaminTypeFacade(ExamExaminTypeFacade examExaminTypeFacade) {
		this.examExaminTypeFacade = examExaminTypeFacade;
	}
	
	public void setSystemSiteManage(SystemSiteManage systemSiteManage) {
		this.systemSiteManage = systemSiteManage;
	}

	/**
	 * @param request
	 * @return
	 */
	protected String getExam(HttpServletRequest request) {
		Long id = ParamUtils.getLongParameter(request, "curTypeId", -1L);

		if (id >= 0) {
			List<ExamExaminType> list = examExaminTypeFacade.getExamExaminTypeRootListByChildId(id);

			Collections.reverse(list);

			request.setAttribute("ExamTypeList", list);
		}

		id = ParamUtils.getLongParameter(request, "id", -1L);

		if (id > 0) {
			ExamExamination examExamination = examExaminationFacade.getExamExaminationById(id);

			if (examExamination != null) {
				request.setAttribute("ExamExamination", examExamination);
			}
		}
		
		return Constants.SUCCESS;
	}

	//站点列表
	protected List<SystemSite> getSystemSite(){
		SystemSite ss = new SystemSite();
		return this.systemSiteManage.getListByItem(ss);
	}
	
}
