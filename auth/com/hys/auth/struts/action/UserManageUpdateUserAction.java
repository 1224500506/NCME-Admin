package com.hys.auth.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.constants.Constants;
import com.hys.auth.model.HysUsers;
import com.hys.auth.util.IntegerUtil;
import com.hys.auth.util.RequestUtil;
import com.hys.exam.model.HysUserRoleProp;

/**
 * 
 * 标题：用户管理-更新用户
 * 
 * 作者：zdz 2009-12-30
 * 
 * 描述：
 * 
 * 说明:
 */
public class UserManageUpdateUserAction extends BaseActionSupport {

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().removeAttribute("user");
		HysUsers user = new HysUsers();
		
		user.setId(Long.valueOf(RequestUtil.getParameter(request, "id")));
		user.setLoginName(RequestUtil.getParameter(request, "userId"));

		String realName = RequestUtil.getParameter(request, "realName");
		String workUnit = RequestUtil.getParameter(request, "workUnit");
		Integer enable = Integer.valueOf(RequestUtil.getParameter(request, "curStatus"));
		String roleIds = RequestUtil.getParameter(request, "curRolesIds");
		
		Integer sex = Integer.valueOf(RequestUtil.getParameter(request, "sex"));
		String phoneNumber = RequestUtil.getParameter(request, "mobilPhone");
		String deptName = RequestUtil.getParameter(request, "curPropNames");
		
		user.setRealName(realName);
		user.setWorkUnit(workUnit);
		user.setEnable(enable);
		user.setSex(sex);
		user.setPhoneNumber(phoneNumber);
		user.setDeptName(deptName);
		
		facade.updateUsers(user);

		facade.deleteUserRoles(user.getId());
		if(roleIds != null && !roleIds.equals(""))
		{
			String[] rolesArray = roleIds.split(",");
			for(String anRole : rolesArray){
				anRole.trim();
				HysUserRoleProp roleProp = new HysUserRoleProp();
				Long roleId = Long.valueOf(anRole);
				roleProp.setUserid(user.getId());
				roleProp.setRoleid(roleId);
				facade.saveUserRoles(roleProp);
			}
		}
/*		if (StringUtils.isNotBlank(loginName) && StringUtils.isNotBlank(password) && StringUtils.isNotBlank(roleId)) {
			user.setLoginName(loginName);
			user.setRealName(realName);
			user.setPassword(password);
			user.setEnable(1);
			user.setRoleId(IntegerUtil.parseInteger(roleId));
			facade.updateUsers(user);
		} else {
			request.setAttribute("exception", "更新用户信息失败！参数传递有问题！");
		}	*/

		response.sendRedirect("getUsers.do");
		return null;
	}
}
