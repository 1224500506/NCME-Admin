package com.hys.auth.springsecurity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.model.HysResources;
import com.hys.auth.struts.action.BaseActionSupport;
import com.hys.auth.util.IntegerUtil;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;

/**
 * 获取资源
 * 
 * @author zdz
 * 
 */
public class SecurityGetResourceAction extends BaseActionSupport {

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = RequestUtil.getParameter(request, "resourceId");
		if (StringUtils.isBlank(id)) {
			StrutsUtil.renderText(response, "出错了，id不能为空");
			return null;
		}
		HysResources resource = facade.getResource(IntegerUtil.parseInteger(id));
		if (resource == null) {
			StrutsUtil.renderText(response, "出错了，该用户不存在");
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(resource.getId()).append(",");
		sb.append(resource.getValue()).append(",");
		sb.append(resource.getType() == null ? "" : resource.getType()).append(",");
		sb.append(resource.getName() == null ? "" : resource.getName());
		StrutsUtil.renderText(response, sb.toString());
		logger.info("资源：" + resource);
		return null;
	}

}
