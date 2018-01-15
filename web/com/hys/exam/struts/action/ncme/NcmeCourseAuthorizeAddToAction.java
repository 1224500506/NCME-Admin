package com.hys.exam.struts.action.ncme;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.constants.Constants;
import com.hys.exam.model.NcmeAdminOrgan;
import com.hys.exam.model.NcmeCreditType;
import com.hys.exam.model.system.SystemSiteVO;
import com.hys.exam.service.local.SystemSiteManage;
import com.hys.exam.sessionfacade.local.NcmeAdminOrganFacade;
import com.hys.exam.sessionfacade.local.NcmeCreditTypeFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-5-22
 * 
 * 描述：
 * 
 * 说明:
 */
public class NcmeCourseAuthorizeAddToAction extends BaseAction {

	private NcmeAdminOrganFacade ncmeadminorganfacade;

	private NcmeCreditTypeFacade ncmeCreditTypeFacade;

	private SystemSiteManage systemSiteManage;

	public void setNcmeadminorganfacade(
			NcmeAdminOrganFacade ncmeadminorganfacade) {
		this.ncmeadminorganfacade = ncmeadminorganfacade;
	}

	public void setNcmeCreditTypeFacade(
			NcmeCreditTypeFacade ncmeCreditTypeFacade) {
		this.ncmeCreditTypeFacade = ncmeCreditTypeFacade;
	}

	
	public SystemSiteManage getSystemSiteManage() {
		return systemSiteManage;
	}

	public void setSystemSiteManage(SystemSiteManage systemSiteManage) {
		this.systemSiteManage = systemSiteManage;
	}

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse arg3) throws Exception {

		List<NcmeAdminOrgan> ncmeAdminOrganList = ncmeadminorganfacade.getNcmeAdminOrganList();

		List<NcmeCreditType> ncmeCreditTypeList = ncmeCreditTypeFacade.getNcmeCreditTypeList();

		List<SystemSiteVO> systemSiteList = this.systemSiteManage.getSystemSiteList();

		request.setAttribute("ncmeAdminOrganList", ncmeAdminOrganList);
		request.setAttribute("ncmeCreditTypeList", ncmeCreditTypeList);
		request.setAttribute("systemSiteList", systemSiteList);
		

		saveToken(request);
		return Constants.SUCCESS;
	}

}
