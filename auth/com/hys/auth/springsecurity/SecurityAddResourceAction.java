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
 * 添加资源
 * 
 * @author zdz
 * 
 */
public class SecurityAddResourceAction extends BaseActionSupport {

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String value = RequestUtil.getParameter(request, "value");
		if (StringUtils.isBlank(value)) {
			StrutsUtil.renderText(response, "链接不能为空！");
			logger.info("链接不能为空");
			return null;
		}
		if (facade.getResource(value) != null) {
			StrutsUtil.renderText(response, "链接已存在！");
			logger.info("链接已存在");
			return null;
		}
		String type = RequestUtil.getParameter(request, "type");
		String name = RequestUtil.getParameter(request, "name");
		HysResources resource = new HysResources();
		resource.setName(name);
		resource.setType(IntegerUtil.parseInteger(type));
		resource.setValue(value);
		Long id = facade.saveResource(resource);
		if (id > 0) {
			StrutsUtil.renderText(response, "success");
			logger.info("链接保存成功" + resource);
		}
		return null;
	}

}
