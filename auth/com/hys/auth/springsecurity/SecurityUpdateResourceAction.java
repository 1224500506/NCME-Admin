package com.hys.auth.springsecurity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.model.HysResources;
import com.hys.auth.struts.action.BaseActionSupport;
import com.hys.auth.util.IntegerUtil;
import com.hys.auth.util.LongUtil;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;

/**
 * 更新资源
 * 
 * @author zdz
 * 
 */
public class SecurityUpdateResourceAction extends BaseActionSupport {

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String rsId = RequestUtil.getParameter(request, "rsId");
		if (StringUtils.isBlank(rsId)) {
			StrutsUtil.renderText(response, "出错了，id为空了");
			return null;
		}
		String rsValue = RequestUtil.getParameter(request, "rsValue");
		if (StringUtils.isBlank(rsValue)) {
			StrutsUtil.renderText(response, "出错了，资源链接未填写");
			return null;
		}
		Integer rsType = IntegerUtil.parseInteger(RequestUtil.getParameter(request, "rsType").equals("") ? "2" : RequestUtil.getParameter(request, "rsType"));
		String rsName = RequestUtil.getParameter(request, "rsName");
		HysResources resource = new HysResources();
		resource.setId(LongUtil.parseLong(rsId));
		resource.setValue(rsValue);
		resource.setName(rsName);
		resource.setType(rsType);
		logger.info("更新资源：" + resource);
		int count = facade.updateResource(resource);
		if (count > 0) {
			StrutsUtil.renderText(response, "success");
		}
		return null;
	}

}
