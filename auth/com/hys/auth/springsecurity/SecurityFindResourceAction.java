package com.hys.auth.springsecurity;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.constants.Constants;
import com.hys.auth.model.HysResources;
import com.hys.auth.model.HysRoles;
import com.hys.auth.struts.action.BaseActionSupport;
import com.hys.auth.util.PageList;
import com.hys.auth.util.PageUtil;
import com.hys.auth.util.RequestUtil;

/**
 * 查询资源
 * 
 * @author zdz
 * 
 */
public class SecurityFindResourceAction extends BaseActionSupport {

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageList pageList = new PageList();
		int pageNumber = PageUtil.getPageIndex(request);
		pageList.setPageNumber(pageNumber);
		pageList.setSortCriterion("TYPE,ID");
		pageList.setSortDirection(SortOrderEnum.ASCENDING);
		List<HysResources> resourceList = facade.getAllResource(pageList);
		List<HysRoles> roles = facade.getRolesList();
		pageList.setList(resourceList);
		RequestUtil.setAttribute(request, "resourceList", pageList);
		RequestUtil.setAttribute(request, "roles", roles);
		return Constants.SUCCESS;
	}

}
