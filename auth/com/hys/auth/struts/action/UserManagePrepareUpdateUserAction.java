package com.hys.auth.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.constants.Constants;
import com.hys.auth.model.HysUsers;
import com.hys.auth.util.RequestUtil;

/**
 * 
 * 标题：用户管理-预更新用户
 * 
 * 作者：zdz 2009-12-30
 * 
 * 描述：
 * 
 * 说明:
 */
public class UserManagePrepareUpdateUserAction extends BaseActionSupport {

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().removeAttribute("user");
		String id = RequestUtil.getParameter(request, "id");
		logger.info("得到参数：id=" + id);
		Integer idint = StringUtils.isNotBlank(id) ? Integer.parseInt(id) : -1;
		HysUsers user = facade.getUsers(idint);
		request.setAttribute("user_id", user.getId());
		request.setAttribute("id", id);
		request.setAttribute("user", user);
		return Constants.SUCCESS;
	}

}
