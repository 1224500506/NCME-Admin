package com.hys.auth.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.constants.Constants;
import com.hys.auth.model.HysDataDetail;
import com.hys.auth.model.HysDataList;
import com.hys.auth.model.HysRoles;
import com.hys.auth.util.RequestUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.model.SystemRole;
import com.hys.exam.model.system.SystemAdminOrgan;
import com.hys.exam.sessionfacade.local.SystemRoleFacade;
import com.hys.exam.struts.action.AppBaseAction;

/**
 * 预保存角色
 * 
 * @author zdz
 * 
 */
public class RoleManageAction extends AppBaseAction {
	
	private SystemRoleFacade systemRoleFacade;
	public void setSystemRoleFacade(SystemRoleFacade systemRoleFacade) {
		this.systemRoleFacade = systemRoleFacade;
	}

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String roleName= request.getParameter("roleName");
		String stateStr = request.getParameter("state");
		
		Page<SystemRole> page = this.createPage(request, "element");
		SystemRole searchRole = new SystemRole();

		searchRole.setRoleName(roleName);
		if(stateStr != null && !stateStr.equals("")){
			searchRole.setStatus(Integer.valueOf(stateStr));
			request.setAttribute("state", stateStr);	
		}
		List<SystemRole> roles = systemRoleFacade.getListByItem(searchRole,page);

		request.setAttribute("page", page);
		request.setAttribute("roles", roles);
		request.setAttribute("roleName", roleName);
		return Constants.SUCCESS;
	}

}
