package com.hys.auth.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.IntegerUtil;
import com.hys.auth.util.StrutsUtil;

/**
 * 
 * 标题：用户管理-删除用户
 * 
 * 作者：zdz 2009-12-30
 * 
 * 描述：
 * 
 * 说明:
 */
public class UserManageDeleteUserAction extends BaseActionSupport {

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Integer id = IntegerUtil.parseInteger(request.getParameter("id"));
		if (id != null) {
			logger.info("删除用户信息");
			facade.deleteUsers(id);

		}
		response.sendRedirect("getUsers.do");
		return null;
	}

}
