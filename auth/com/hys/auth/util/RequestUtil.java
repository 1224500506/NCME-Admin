package com.hys.auth.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class RequestUtil {
	public static String getParameter(HttpServletRequest request, String param) {
		if (StringUtils.isBlank(param)) {
			return "";
		}
		if (null == request.getParameter(param)) {
			return "";
		}
		return request.getParameter(param);
	}

	public static void setAttribute(HttpServletRequest request, String param,
			Object object) {
		if (StringUtils.isBlank(param)) {
			return;
		}
		request.setAttribute(param, object);
	}

	public static Object getAttribute(HttpServletRequest request, String param) {
		if (StringUtils.isBlank(param)) {
			return null;
		}
		return request.getAttribute(param);
	}

}
