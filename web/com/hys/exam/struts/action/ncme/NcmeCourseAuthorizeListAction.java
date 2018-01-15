package com.hys.exam.struts.action.ncme;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.PageUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.NcmeAdminOrgan;
import com.hys.exam.model.NcmeCourseCredit;
import com.hys.exam.model.NcmeCreditType;
import com.hys.exam.model.system.SystemSiteVO;
import com.hys.exam.service.local.SystemSiteManage;
import com.hys.exam.sessionfacade.local.NcmeAdminOrganFacade;
import com.hys.exam.sessionfacade.local.NcmeCourseCreditFacade;
import com.hys.exam.sessionfacade.local.NcmeCreditTypeFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：课程授权
 * 
 * 作者：陈明凯 2013-5-17
 * 
 * 描述：
 * 
 * 说明:
 */
public class NcmeCourseAuthorizeListAction extends BaseAction {

	private NcmeAdminOrganFacade ncmeadminorganfacade;

	private NcmeCreditTypeFacade ncmeCreditTypeFacade;

	private NcmeCourseCreditFacade ncmeCourseCreditFacade;
	
	private SystemSiteManage systemSiteManage;

	public void setNcmeCourseCreditFacade(
			NcmeCourseCreditFacade ncmeCourseCreditFacade) {
		this.ncmeCourseCreditFacade = ncmeCourseCreditFacade;
	}

	public void setNcmeadminorganfacade(
			NcmeAdminOrganFacade ncmeadminorganfacade) {
		this.ncmeadminorganfacade = ncmeadminorganfacade;
	}

	public void setNcmeCreditTypeFacade(
			NcmeCreditTypeFacade ncmeCreditTypeFacade) {
		this.ncmeCreditTypeFacade = ncmeCreditTypeFacade;
	}
	
	public void setSystemSiteManage(SystemSiteManage systemSiteManage) {
		this.systemSiteManage = systemSiteManage;
	}

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List<NcmeAdminOrgan> ncmeAdminOrganList = ncmeadminorganfacade.getNcmeAdminOrganList();

		List<NcmeCreditType> ncmeCreditTypeList = ncmeCreditTypeFacade.getNcmeCreditTypeList();
		
		List<SystemSiteVO> systemSiteList = this.systemSiteManage.getSystemSiteList();

		request.setAttribute("ncmeAdminOrganList", ncmeAdminOrganList);
		request.setAttribute("ncmeCreditTypeList", ncmeCreditTypeList);
		request.setAttribute("systemSiteList", systemSiteList);

		Page<NcmeCourseCredit> page = PageUtil.getPageByRequest(request);

		NcmeCourseCredit n = new NcmeCourseCredit();

		String creditYear = ParamUtils.getParameter(request, "creditYear");
		if(StringUtils.isBlank(creditYear)){
			creditYear = DateUtil.getYear();
		}
		n.setCreditYear(creditYear);
		n.setCourseType(ParamUtils.getIntParameter(request, "courseType", -1));
		n.setCreditType(ParamUtils.getParameter(request, "creditType"));
		n.setCourseId(ParamUtils.getParameter(request, "courseId"));
		n.setStudyCourseName(ParamUtils.getParameter(request, "studyCourseName"));
		////n.setOrganId(ParamUtils.getParameter(request, "organId"));
		n.setSiteId(ParamUtils.getLongParameter(request, "siteId", 0L));
		n.setStartDate(DateUtil.parse(ParamUtils.getParameter(request, "startDate"), "yyyy-MM-dd"));
		n.setEndDate(DateUtil.parse(ParamUtils.getParameter(request, "endDate"), "yyyy-MM-dd"));

		ncmeCourseCreditFacade.getNcmeCourseCreditList(page, n);

		request.setAttribute("pageList", page);
		request.setAttribute("currentYear", creditYear);

		saveToken(request);

		return Constants.SUCCESS;
	}

}
