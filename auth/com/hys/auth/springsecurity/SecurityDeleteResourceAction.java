package com.hys.auth.springsecurity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.struts.action.BaseActionSupport;
import com.hys.auth.util.IntegerUtil;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;


/**
 * 删除资源
 * 
 * @author zdz
 * 
 */
public class SecurityDeleteResourceAction extends BaseActionSupport {

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = RequestUtil.getParameter(request, "id");
		if (StringUtils.isBlank(id)) {
			StrutsUtil.renderText(response, "出错了，ID为空");
			return null;
		}
		int count = facade.deleteResource(IntegerUtil.parseInteger(id));
		if (count > 0) {
			StrutsUtil.renderText(response, "success");
		} else {
			StrutsUtil.renderText(response, "抱歉没有该记录，无法删除！");
		}
		return null;
	}

}
