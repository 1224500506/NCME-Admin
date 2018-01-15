package com.hys.auth.struts.action;



import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：用户登出
 * 
 * 作者：zdz，2009 7 24
 * 
 * 描述：
 * 
 * 说明:
 */
public class LogoutAction extends BaseAction {

	@SuppressWarnings("unchecked")
	@Override
	protected String actionExecute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 清空session中的值
		Enumeration<Object> e = (Enumeration)request.getSession().getAttributeNames();

		String key;
		while (e.hasMoreElements()) {
			key = (String) e.nextElement();
			request.getSession().removeAttribute(key);
		}

		response.sendRedirect("login.jsp");

		return null;
	}

}
