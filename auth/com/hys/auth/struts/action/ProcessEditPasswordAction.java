package com.hys.auth.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.constants.Constants;
import com.hys.auth.model.HysUsers;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.SecurityUtil;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.utils.MD5Util;

/**
 * 修改密码
 * 
 * @author zdz
 * 
 */
public class ProcessEditPasswordAction extends BaseActionSupport {

	private SystemUserManage localSystemUserManage;
	
	public SystemUserManage getLocalSystemUserManage() {
		return localSystemUserManage;
	}

	public void setLocalSystemUserManage(SystemUserManage localSystemUserManage) {
		this.localSystemUserManage = localSystemUserManage;
	}
	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1, HttpServletRequest request, HttpServletResponse arg3) throws Exception {
		String oldPwd = RequestUtil.getParameter(request, "oldpwd");
		String newPwd = RequestUtil.getParameter(request, "newpwd");
		String userName = SecurityUtil.getUserName();
		logger.info("用户名:" + userName);
		SystemUser users = localSystemUserManage.getItemByAccountName(userName, MD5Util.string2MD5(oldPwd));
		if (users == null) {
			request.setAttribute("msg", "原密码不正确！");
			return Constants.INPUT;
		}
		
		int count = localSystemUserManage.setPass(userName,MD5Util.string2MD5(newPwd));
		if (count == 1) {
			request.setAttribute("msg", "修改密码成功！");
			return Constants.SUCCESS;
		}
		return "input";
	}
}
