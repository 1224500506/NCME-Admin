package com.hys.auth.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringUtils;

/**
 * 用户请求包装类
 * @author Admin
 *
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		 super(request);
	}
	
    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
        	if ("model.content".equals(parameter)) {
        		encodedValues[i] = values[i];
        	} else {
        		encodedValues[i] = stripXSS(values[i]);
        	}
        }
        return encodedValues;
    }
    
    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        return stripXSS(value);
    }
    
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return stripXSS(value);
    }
    
    private String stripXSS(String value) {
    	if (StringUtils.isNotBlank(value)) {
    		value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
            value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
            value = value.replaceAll("'", "&#39;");
            value = value.replaceAll("eval\\((.*)\\)", "");
            value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
            value = value.replaceAll("script", "");
            value = value.replaceAll("\"", "&quot;");
            //将xss攻击的一些特殊字符进行替换
            value = value.replaceAll("iframe", "");
            value = value.replaceAll("alert", "");
            value = value.replaceAll("svg", "");
            value = value.replaceAll("onload", "");
            value = value.replaceAll("onmouseover", "");
            value = value.replaceAll("onfocus", "");
            value = value.replaceAll("onerror", "");
            value = value.replaceAll("xss", "");
            value = value.replaceAll("confirm", "");
            value = value.replaceAll("prompt", "");
    	}
    	return value;
    }
}
