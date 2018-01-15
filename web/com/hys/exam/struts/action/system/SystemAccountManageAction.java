package com.hys.exam.struts.action.system;

import com.hys.auth.constants.Constants;
import com.hys.auth.util.PageUtil;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.model.SystemRole;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemUserType;
import com.hys.exam.service.local.SystemRoleManage;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.struts.action.AppBaseAction;
import com.hys.exam.utils.MD5Util;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SystemAccountManageAction extends AppBaseAction {
	
	private SystemUserManage localSystemUserManage;
	
	private SystemRoleManage localSystemRoleManage;
	
	
	public SystemUserManage getLocalSystemUserManage() {
		return localSystemUserManage;
	}

	public void setLocalSystemUserManage(SystemUserManage localSystemUserManage) {
		this.localSystemUserManage = localSystemUserManage;
	}

	public SystemRoleManage getLocalSystemRoleManage() {
		return localSystemRoleManage;
	}

	public void setLocalSystemRoleManage(SystemRoleManage localSystemRoleManage) {
		this.localSystemRoleManage = localSystemRoleManage;
	}

	protected String actionExecute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = RequestUtil.getParameter(request, "method");
		
		if (method.equals("search")) {
			return search(actionMapping, actionForm, request, response);
		} else if (method.equals("resetPass")) {
			return resetPass(actionMapping, actionForm, request, response);
		}
		else if (method.equals("editRole")) {
				return editRole(actionMapping, actionForm, request, response);
		}
		else if (method.equals("updateState")) {
			return updateState(actionMapping, actionForm, request, response);
		}
		else if (method.equals("executeHtml")) {
			return "executeHtml";
		}
		else {
			return search(actionMapping, actionForm, request, response);
		}
	}

	/**
	 * 查询
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String search(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String accountName = request.getParameter("accountName");
		String userName = request.getParameter("userName");
		String workUnit = request.getParameter("workUnit");
		String stateStr = request.getParameter("state");
		String typeId = request.getParameter("typeId");
		String roleId = request.getParameter("roleId");
		String propIds = request.getParameter("propIds");
		String propNames = request.getParameter("propNames");
		String mobilPhone = request.getParameter("mobilPhone");
		
		Integer state = null;
		if(stateStr != null && !stateStr.equals(""))
		{
			state = Integer.valueOf(stateStr);
		}
		
		Integer type = null;
		if(typeId != null && !typeId.equals("") && !typeId.equals("-1"))
		{
			type = Integer.valueOf(typeId);
		}
		
		Integer role = null;
		if(roleId != null && !roleId.equals("") && !roleId.equals("-1"))
		{
			role = Integer.valueOf(roleId);
		}
		SystemUser item = new SystemUser();
		
		item.setAccountName(accountName);
		item.setRealName(userName);
		item.setWorkUnit(workUnit);
		item.setAccount_status(state);
		item.setUserType(type);
		item.setDeptName(propIds);
		item.setMobilPhone(mobilPhone);
		
		Page<SystemUser> pageList = createPage(request,"systemUser");
		int pageNumber = PageUtil.getPageIndex(request);
		pageList.setCurrentPage(pageNumber);
		
		localSystemUserManage.querySystemAccountList(pageList,item,role);
		List<SystemUserType> userTypeList = localSystemUserManage.getUserTypeList();
		SystemRole newRole = new SystemRole();
		//设置角色的状态为启用
		newRole.setStatus(1);
		List<SystemRole> roleList = localSystemRoleManage.getListByItem(newRole);
		for(SystemUser u : pageList.getList())
		{
			u.setRoleList(localSystemUserManage.getUserRoleList(u.getAccountId()));
		}	
		
		request.setAttribute("page", pageList);
		request.setAttribute("item", item);
		request.setAttribute("typeList", userTypeList);
		request.setAttribute("roleList", roleList);
		request.setAttribute("accountName", accountName);
		request.setAttribute("userName", userName);
		request.setAttribute("workUnit", workUnit);
		request.setAttribute("state", state);
		request.setAttribute("typeId", type);
		request.setAttribute("roleId", role);
		request.setAttribute("propIds", propIds);
		request.setAttribute("propNames", propNames);
			
		return Constants.SUCCESS;
	}
	
	public String resetPass(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String accountName = request.getParameter("accountName");
		String newPass = MD5Util.string2MD5("123456");
		int result = localSystemUserManage.setPass(accountName, newPass);
		if(result>0)
		{
			StrutsUtil.renderText(response, "success");
		}
		else
		{
			StrutsUtil.renderText(response, "success");
		}
		return null;
	}
	public String editRole(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String accountId = request.getParameter("accountId");
		String roleString = request.getParameter("newRole");
		if(roleString.charAt(roleString.length()-1) == ',')
		{
			roleString = roleString.substring(0,roleString.length()-1);
		}
		String[] roleList = roleString.trim().split(",");

		Integer aId = null;
		if(accountId != null && !accountId.equals(""))
		{
			aId = Integer.valueOf(accountId);
		}
		else
		{
			StrutsUtil.renderText(response, "fail");
			return null;
		}
		int result = localSystemUserManage.updateAccountRole(aId,roleList);
		if(result > 0)
		{
			StrutsUtil.renderText(response, "success");
		}
		else
		{
			StrutsUtil.renderText(response, "fail");
		}
		return null;
	}
	
	public String updateState(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String temid = request.getParameter("id");
		Integer id = null;
		if(temid != null && !temid.equals(""))
		{
			id = Integer.valueOf(temid);
		}
		else
		{
			StrutsUtil.renderText(response, "fail");
			return null;
		}
		String temstate = request.getParameter("oldState");
		Integer oldState = null;
		if(temstate != null && !temstate.equals(""))
		{
			oldState = Integer.valueOf(temstate);
			if(oldState == 1)
			{
				oldState = 2;
			}
			else
			{
				oldState = 1;
			}
		}		
		int result = localSystemUserManage.updateAccountState(id,oldState);
		if(result > 0)
		{
			StrutsUtil.renderText(response, "success");
		}
		else
		{
			StrutsUtil.renderText(response, "fail");
		}
		return null;
	}
}
