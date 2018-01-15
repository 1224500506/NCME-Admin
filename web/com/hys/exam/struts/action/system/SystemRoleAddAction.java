package com.hys.exam.struts.action.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.constants.Constants;
import com.hys.auth.model.HysUsers;
import com.hys.auth.util.PageList;
import com.hys.auth.util.PageUtil;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.system.SystemMenu;
import com.hys.exam.model.system.SystemRoleMenuProp;
import com.hys.exam.service.local.SystemMenuManage;
import com.hys.exam.service.local.SystemRoleManage;
import com.hys.exam.struts.action.AppBaseAction;

/**
 * 
 * 标题：用户管理-获取用户
 * 
 * 作者：zdz 2009-12-30
 * 
 * 描述：
 * 
 * 说明:
 */
public class SystemRoleAddAction extends AppBaseAction {
	private SystemMenuManage localSystemMenuManage;
	private SystemRoleManage localSystemRoleManage;

	public SystemMenuManage getLocalSystemMenuManage() {
		return localSystemMenuManage;
	}

	public void setLocalSystemMenuManage(SystemMenuManage localSystemMenuManage) {
		this.localSystemMenuManage = localSystemMenuManage;
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
		
		if (method.equals("list")) {
			return list(actionMapping, actionForm, request, response);
		} else if (method.equals("add")) {
			return addMenutoRole(actionMapping, actionForm, request, response);
		}
		else {
			return list(actionMapping, actionForm, request, response);
		}
	}

	public String list(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String roleId = request.getParameter("roleId");
		Integer rId = null;
		if(roleId != null && !roleId.equals(""))
		{
			rId = Integer.valueOf(roleId);
		}
		List<SystemRoleMenuProp> roleMenuList = localSystemRoleManage.getRoleMenuList(rId);
		SystemMenu searchMenu = new SystemMenu();
		List<SystemMenu> menuList = localSystemMenuManage.getMenuList(searchMenu);
		String roleMenuListStr = "";
		for(SystemRoleMenuProp p : roleMenuList)
		{
			roleMenuListStr += p.getMenuId() + ",";
		}
		
		//YHQ,2017-08-13,根据返回的数据重新组织下数据结构
		List<SystemMenu> peixunMenuList = new ArrayList<SystemMenu>() ;//后台的菜单
		List<SystemMenu> adminMenuList  = new ArrayList<SystemMenu>() ;//资源的菜单
		for (SystemMenu smObj : menuList) {
			String system_type = smObj.getSystem_type() ;
			String menu_type   = smObj.getMenu_type() ;
			if (system_type != null && system_type.trim().equals("资源管理系统")) {
				boolean haveTwoMenu = false ;
				for (SystemMenu adminSMobj : adminMenuList) {
					if (menu_type != null && menu_type.trim().equals(adminSMobj.getMenu_type())) {
						haveTwoMenu = true ;
						List<SystemMenu> adminMenuTwoList = adminSMobj.getSystemMenuList() ;
						if (adminMenuTwoList == null) {
							adminMenuTwoList  = new ArrayList<SystemMenu>() ;
						}
						adminMenuTwoList.add(smObj) ;
						adminSMobj.setSystemMenuList(adminMenuTwoList);
					}
				}
				if (!haveTwoMenu && menu_type != null) {
					SystemMenu adminSM = new SystemMenu() ;
					adminSM.setMenu_type(menu_type);
					List<SystemMenu> adminMenuTwoList = new ArrayList<SystemMenu>() ;
					adminMenuTwoList.add(smObj) ;
					adminSM.setSystemMenuList(adminMenuTwoList);					
					adminMenuList.add(adminSM) ;
				}
			} else if (system_type != null && system_type.trim().equals("后台管理系统")) {
				boolean haveTwoMenu = false ;
				for (SystemMenu peixunSMobj : peixunMenuList) {
					if (menu_type != null && menu_type.trim().equals(peixunSMobj.getMenu_type())) {
						haveTwoMenu = true ;
						List<SystemMenu> peixunMenuTwoList = peixunSMobj.getSystemMenuList() ;
						if (peixunMenuTwoList == null) {
							peixunMenuTwoList  = new ArrayList<SystemMenu>() ;
						}
						peixunMenuTwoList.add(smObj) ;
						peixunSMobj.setSystemMenuList(peixunMenuTwoList);
					}
				}
				if (!haveTwoMenu && menu_type != null) {
					SystemMenu peixunSM = new SystemMenu() ;
					peixunSM.setMenu_type(menu_type);
					List<SystemMenu> peixunMenuTwoList = new ArrayList<SystemMenu>() ;
					peixunMenuTwoList.add(smObj) ;
					peixunSM.setSystemMenuList(peixunMenuTwoList);					
					peixunMenuList.add(peixunSM) ;
				}
			}
		}
		request.setAttribute("adminMenuList", adminMenuList);
		request.setAttribute("peixunMenuList", peixunMenuList);
		
		request.setAttribute("roleMenuList", roleMenuListStr);
		request.setAttribute("menuList", menuList);
		request.setAttribute("roleId", rId);
		return Constants.SUCCESS;
	}
	
	public String addMenutoRole(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String rId = request.getParameter("roleId");
		String menuList = request.getParameter("menuIds");
		Integer roleId = null;
		if(rId != null && !rId.equals(""))
		{
			roleId = Integer.valueOf(rId);
		}
		int result = localSystemRoleManage.setRoleMenuProp(roleId,menuList);
		if(result > 0)
		{
			request.setAttribute("msg", "授予资源成功!");
		}
		else
		{
			request.setAttribute("msg", "授予资源失败!");
		}
		return Constants.INPUT;
	}
}
