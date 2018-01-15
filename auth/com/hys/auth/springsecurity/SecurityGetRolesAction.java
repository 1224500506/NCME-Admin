package com.hys.auth.springsecurity;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.model.HysRoles;
import com.hys.auth.struts.action.BaseActionSupport;
import com.hys.auth.util.IntegerUtil;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;

/**
 * 获取角色
 * 
 * @author zdz
 * 
 */
public class SecurityGetRolesAction extends BaseActionSupport {

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resourceId = RequestUtil.getParameter(request, "rid");
		if (StringUtils.isBlank(resourceId)) {
			StrutsUtil.renderText(response, "出错了");
			return null;
		}
		List<HysRoles> roles = facade.getRoleByResourceId(IntegerUtil.parseInteger(resourceId));
		List<Long> ids = new ArrayList<Long>();
		;
		for (HysRoles role : roles) {
			ids.add(role.getId());
		}

		StrutsUtil.renderText(response, StringUtils.join(ids.toArray(), ","));
		logger.info("返回角色" + StringUtils.join(ids.toArray(), ","));
		return null;
	}

}
