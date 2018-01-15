package com.hys.auth.springsecurity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.struts.action.BaseActionSupport;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;

/**
 * 关联角色与资源
 * 
 * @author zdz
 * 
 */
public class SecurityRoleResourceAction extends BaseActionSupport {

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String roleIds = RequestUtil.getParameter(request, "roleIds");
		String resourceId = RequestUtil.getParameter(request, "resourceId");
		if (StringUtils.isBlank(resourceId)) {
			StrutsUtil.renderText(response, "出错了，资源为空！");
			logger.info("出错了，资源为空！");
			return null;
		}
		logger.info("roleIds:" + roleIds + " resourceId:" + resourceId);
		int count = facade.saveRelateRoleAndResource(roleIds, resourceId);
		if (count > 0) {
			StrutsUtil.renderText(response, "success");
			logger.info("关联成功");
		}
		return null;
	}

}
