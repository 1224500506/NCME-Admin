package com.hys.auth.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hys.exam.constants.Constants;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2012-03-27
 * 
 * 描述：过滤器
 * 
 * 说明:
 */
public class SecurityFilter extends HttpServlet implements Filter {
	private final static String MESSAGE = "message";

	private static final long serialVersionUID = -6871710000081143385L;

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	private FilterConfig filterConfig;

	// 不需要过滤的页面地址字符串，多个用半角逗号分隔
	private String excludeUrl;

	// 不需要过滤的页面地址数组，经过excludeUrl对,进行split得到
	private String[] excludeUrlArray;

	// session为空时页面跳转的地址
	private String sessionNullUrl;

	// 访问权限范围外页面时跳转的地址
	private String sessionSecurityUrl;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.excludeUrl = filterConfig.getInitParameter("excludeUrl");
		if (excludeUrl != null) {
			// 不需要过滤的页面地址数组，经过excludeUrl对,进行split得到
			excludeUrlArray = excludeUrl.split(",");
		}
		this.sessionNullUrl = filterConfig.getInitParameter("sessionNullUrl");
		this.sessionSecurityUrl = filterConfig
				.getInitParameter("sessionSecurityUrl");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) {
		try {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.setContentType(CONTENT_TYPE);
			
			
			//验证license
			if (!Constants.validateLicense){
				httpRequest.setAttribute(MESSAGE, "许可证无效,请重新导入许可证!");
				httpRequest.getRequestDispatcher(sessionSecurityUrl)
						.forward(httpRequest, httpResponse);
			} 
		} catch (Exception e) {
			filterConfig.getServletContext().log(e.toString());
			e.printStackTrace();
		}
	}

	

	public void destroy() {
	}

}
