package com.hys.auth.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * xss攻击的预防过滤器 
 * @author Admin
 *
 */
public class XssFilter implements Filter {
	
	private static final Logger logger=Logger.getLogger(XssFilter.class);
	
	/**   
	* 需要排除的页面   
	*/    
	private String excludedPages;
	
	private String[] excludedPageArray; 

	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
		//初始化时得到要排除出的页面
		excludedPages = filterConfig.getInitParameter("excludeURL");     
		if (StringUtils.isNotEmpty(excludedPages)) {     
			excludedPageArray = excludedPages.split(",");     
		}     
    }
	
    @Override
    public void destroy() {
    	
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    					throws IOException, ServletException {
    	boolean isExcludedPage = false;     
    	for (String page : excludedPageArray) {//判断是否在过滤url之外     
    		if (((HttpServletRequest) request).getServletPath().contains(page)) {     
    			isExcludedPage = true;   
    			if (logger.isInfoEnabled()) {
    				logger.info("-----排除xss路径："+((HttpServletRequest) request).getServletPath());
    			}
    			break;     
    		}     
    	}     
    	//如果需要过滤，则走xss用户请求包装类  
    	if (!isExcludedPage) {    
    		chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response); 
    	}else{
    		chain.doFilter(request, response);
    	}     
    }

}
