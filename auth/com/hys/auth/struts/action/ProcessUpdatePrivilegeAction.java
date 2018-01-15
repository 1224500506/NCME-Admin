package com.hys.auth.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.model.HysUsers;
import com.hys.auth.util.IntegerUtil;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;

/**
 * 修改权限
 * 
 * @author zdz
 * 
 */
public class ProcessUpdatePrivilegeAction extends BaseActionSupport {

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = RequestUtil.getParameter(request, "uid");
		String roleId = RequestUtil.getParameter(request, "rid");
		if (StringUtils.isBlank(id) || StringUtils.isBlank(roleId)) {
			StrutsUtil.renderText(response, "1");
			return null;
		}
		HysUsers user = facade.getUsers(IntegerUtil.parseInteger(id));
		if (user == null) {
			StrutsUtil.renderText(response, "1");
			return null;
		}
		user.setRoleId(IntegerUtil.parseInteger(roleId));
		int key = facade.updateUsers(user);
		if (key > 0) {
			StrutsUtil.renderText(response, "200");
		}
		return null;
	}

}
