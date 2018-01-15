package com.hys.auth.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.constants.Constants;
import com.hys.auth.model.HysUsers;
import com.hys.auth.util.IntegerUtil;
import com.hys.auth.util.PageList;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.CasePatientPropVal;
import com.hys.exam.model.HysUserRoleProp;

/**
 * 
 * 标题：用户管理-增加用户
 * 
 * 作者：zdz 2009-12-30
 * 
 * 描述：
 * 
 * 说明:
 */
public class UserManageAddUserAction extends BaseActionSupport {

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resultStr = "";
		String loginName = RequestUtil.getParameter(request, "loginName");
		String roleIds = RequestUtil.getParameter(request, "newRolesIds");
		String realName = RequestUtil.getParameter(request, "realName");
		String deptName = RequestUtil.getParameter(request, "deptNames");
		Integer sex = Integer.valueOf(RequestUtil.getParameter(request, "sex"));
		String workUnit = RequestUtil.getParameter(request, "workUnit");
		String phoneNumber = RequestUtil.getParameter(request, "mobilPhone");
		Integer enable = Integer.valueOf(RequestUtil.getParameter(request, "status"));
		
		HysUsers user = new HysUsers();
		
		user.setLoginName(loginName);
		user.setRealName(realName);
		user.setDeptName(deptName);
		user.setSex(sex);
		user.setWorkUnit(workUnit);
		user.setPhoneNumber(phoneNumber);
		user.setEnable(enable);
		
		HysUsers result = facade.valid(loginName, null);
		if(result == null)
		{
			facade.saveUsers(user);
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
			resultStr = "success";
		}
		else 
			resultStr = "repeatUserName";
		//response.sendRedirect("getUsers.do");
		StrutsUtil.renderText(response, resultStr);
		return null;
	}


}
