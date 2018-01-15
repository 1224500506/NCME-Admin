package com.hys.auth.struts.action;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.constants.Constants;
import com.hys.auth.model.HysRoles;
import com.hys.auth.model.HysUsers;
import com.hys.auth.util.PageList;
import com.hys.auth.util.PageUtil;
import com.hys.auth.util.RequestUtil;

/**
 * 查找用户列表
 * 
 * @author zdz
 * 
 */
public class ProcessFindUsersAction extends BaseActionSupport {

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String loginName = RequestUtil.getParameter(request, "loginName");
		PageList page = new PageList();
		int pageNumber = PageUtil.getPageIndex(request);
		page.setPageNumber(pageNumber);
		List<HysUsers> users = facade.getUsersList(loginName, page);
		page.setList(users);
		request.setAttribute("users", page);
		List<HysRoles> roles = facade.getRolesList();
		for (Iterator iterator = roles.iterator(); iterator.hasNext();) {
			HysRoles name = (HysRoles) iterator.next();
			if(1==name.getId()) {
				iterator.remove();
			}
		}
		RequestUtil.setAttribute(request, "roles", roles);
		return Constants.SUCCESS;
	}

}
